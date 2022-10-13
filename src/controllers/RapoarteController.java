/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import gui.FrmLoadingRaport;
import gui.FrmRapoarte;
import gui.FrmVizualizareRapoarte;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import models.AppSingleTone;
import models.DateRaport;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import services.DateRaportService;
import services.DateRaportServiceImpl;
import tablemodel.ColumnResizer1;
import tablemodels.TableModelDateRaport;
import utils.HibernateUtil;

/**
 *
 * @author Stefan
 */
public class RapoarteController {

    private FrmRapoarte frmRapoarte;
    private FrmLoadingRaport frmLoadingRaport;
    private FrmVizualizareRapoarte frmVizualizareRapoarte;
    private TableModelDateRaport tableModelDateRaport = new TableModelDateRaport();
    private DateRaportService dateRaportService = new DateRaportServiceImpl();
    private DefaultListModel<String> defaultListModel = new DefaultListModel<>();
    private ArrayList<DateRaport> rapoarteInQueue = new ArrayList<>();
    private File saveReportFolder = null;
    private FileWriter fileWriter = null;
    private File userConfig = new File("./userconfig.txt");
    private Scanner scanner;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");

    public void actionCreate(JFrame parent) {
        frmRapoarte = new FrmRapoarte(parent, true);
        frmRapoarte.setTitle("Generare rapoarte");
        DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel();
        defaultComboBoxModel.addElement("--Selectati raportul--");
        defaultComboBoxModel.addElement("Raport Utilizatori");
        defaultComboBoxModel.addElement("Raport Soferi");
        frmRapoarte.getCmbRapoarte().setModel(defaultComboBoxModel);
        frmRapoarte.getLstRapoarte().setModel(defaultListModel);
        try {
            scanner = new Scanner(userConfig);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RapoarteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        saveReportFolder = new File(scanner.nextLine());
        frmRapoarte.getLblInfoFolder().setText(saveReportFolder.getPath());
        //  frmRapoarte.getChooserDataInceput()
        frmRapoarte.setRapoarteController(this);
        frmRapoarte.setLocationRelativeTo(parent);
        frmRapoarte.setVisible(true);
    }

    public void generareRaport(int index) throws URISyntaxException, JRException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (!session.isOpen()) {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
        }
        SessionImpl sessionConn = (SessionImpl) session;
        File tt = new File(".");
        System.out.println(tt.getAbsolutePath());
        Connection connection = sessionConn.connection();
        Map<String, Object> parameters = new HashMap<String, Object>();
        File reportFolder = new File(getClass().getResource("/rapoarte").toURI());
        Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
        InputStream myFile = new InputStream() {
            @Override
            public int read() throws IOException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        parameters.put("dataInceput", new Date(frmRapoarte.getChooserDataInceput().getDate().getTime()));
        parameters.put("dataSfarsit", new Date(frmRapoarte.getChooserDataFinal().getDate().getTime()));
        if (index == 0) {
            JOptionPane.showMessageDialog(frmRapoarte, "Va rugam sa selectati un raport.");
            return;
        } else if (index == 1) {
            myFile = getClass().getResourceAsStream("/rapoarte/RaportUtilizatoriFinal.jrxml");
        }
        if (index == 2) {
            myFile = getClass().getResourceAsStream("/rapoarte/Raport_Inregistrari_Bun.jrxml");
            parameters.put("listaSoferi", new ArrayList(Arrays.asList()));
            parameters.put("listaTiruri", new ArrayList(Arrays.asList()));
            System.out.println("L-am luat");
        }
        JasperDesign jasperDesign = JRXmlLoader.load(myFile);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);//.fillReport(jasperReport, parameters,new JRBeanCollectionDataSource(ie.test.BeanFactory.getCalcs()));                                
        JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
        System.out.println(String.format("%s\\%s", reportFolder.getPath(), "Raport"));
        JasperExportManager.exportReportToPdfFile(jasperPrint, String.format("%s\\%s(%s - %s).pdf", saveReportFolder.getPath(), frmRapoarte.getCmbRapoarte().getSelectedItem().toString(),
                sdf.format(frmRapoarte.getChooserDataInceput().getDate().getTime()), sdf.format(frmRapoarte.getChooserDataFinal().getDate().getTime())));
        JDialog raport = new JDialog(frmRapoarte);
        raport.setContentPane(jasperViewer.getContentPane());
        raport.setSize(jasperViewer.getSize());
        raport.setVisible(true);
    }

    private int addDateRaportToDatabaseAndQueue() {
        DateRaport dateRaport = new DateRaport();
        dateRaport.setDataSubmit(Calendar.getInstance().getTime());
        dateRaport.setNumeRaport((String) frmRapoarte.getCmbRapoarte().getSelectedItem());
        dateRaport.setReportPath("");
        dateRaport.setStare("In generare");
        dateRaport.setUtilizator(AppSingleTone.getAppSingleToneInstance().getUtilizatorAutentificat());
        dateRaport.setIdUtilizator(AppSingleTone.getAppSingleToneInstance().getUtilizatorAutentificat().getId());
        dateRaportService.addDateRaport(dateRaport);
        rapoarteInQueue.add(dateRaport);
        setModelToLst();
        return dateRaport.getId();
    }

    public void setModelToLst() {
        defaultListModel.removeAllElements();
        for (DateRaport dp : rapoarteInQueue) {
            defaultListModel.addElement((rapoarteInQueue.indexOf(dp) + 1) + "-" + dp.getNumeRaport() + " " + dp.getStare());
        }
        frmRapoarte.getLstRapoarte().setModel(defaultListModel);
    }

    private SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
        @Override
        protected String doInBackground() throws URISyntaxException, IOException, JRException, InterruptedException {
            Thread.sleep(3000);
            generareRaport(1);
            System.out.println("Am intrat in doInBackground");
            return null;
        }

        @Override
        protected void done() {
            setModelToLst();
        }
    };

    public void runReport() {
        if(!isFormValid()) {
            return;
        } 
        int index = frmRapoarte.getCmbRapoarte().getSelectedIndex();
        int queueIndex = rapoarteInQueue.size();
        int id = addDateRaportToDatabaseAndQueue();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                SwingWorker<String, String> worker = new SwingWorker<String, String>() {
                    @Override
                    protected String doInBackground() throws URISyntaxException, IOException, JRException, InterruptedException {
                        Thread.sleep(4000);
                        generareRaport(index);
                        return null;
                    }

                    @Override
                    protected void done() {
                        DateRaport dateRaport = dateRaportService.getDateRaportById(id);
                        dateRaport.setDataGenerare(Calendar.getInstance().getTime());
                        dateRaport.setStare("Generat");
                        dateRaport.setReportPath(String.format("%s\\%s(%s-%s).pdf", saveReportFolder.getPath(), dateRaport.getNumeRaport(), sdf.format(frmRapoarte.getChooserDataInceput().getDate().getTime()), sdf.format(frmRapoarte.getChooserDataFinal().getDate().getTime())));
                        dateRaportService.addDateRaport(dateRaport);
                        rapoarteInQueue.set(queueIndex, dateRaport);
                        setModelToLst();
                    }
                };

                worker.execute();
            }
        });
        t.start();
    }

    public void setConfiguration() throws IOException {
        JFileChooser chooserFolderRapoarte = frmRapoarte.getFileChooserFolderRaport();
        chooserFolderRapoarte.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooserFolderRapoarte.setAcceptAllFileFilterUsed(false);

        if (chooserFolderRapoarte.showOpenDialog(frmRapoarte) == JFileChooser.APPROVE_OPTION) {
            saveReportFolder = new File(chooserFolderRapoarte.getSelectedFile().getAbsolutePath());
            frmRapoarte.getLblInfoFolder().setText(saveReportFolder.getPath());
            if (!saveReportFolder.exists()) {
                saveReportFolder.mkdir();
            }
            fileWriter = new FileWriter("./userconfig.txt", false);
            fileWriter.write(saveReportFolder.getPath());
            fileWriter.close();
        }
    }

    private void updateAndSetModelToTable(JTable table) {
        tableModelDateRaport.setListaDateRaport(dateRaportService.getAll());
        table.setModel(tableModelDateRaport);
        ColumnResizer1.resizeRowHeightAndColumnsWidth(table);
    }

    public void actionRead(JFrame parent) {
        frmVizualizareRapoarte = new FrmVizualizareRapoarte();
        frmVizualizareRapoarte.setLocationRelativeTo(parent);
        updateAndSetModelToTable(frmVizualizareRapoarte.getTblDateRaport());
        frmVizualizareRapoarte.setVisible(true);

    }

    private boolean isFormValid() {
        if(saveReportFolder == null) {
            JOptionPane.showMessageDialog(frmRapoarte, "Selectati o locatie valida pentru a salva rapoartele");
            return false;
        }
        return true;
    }

}
