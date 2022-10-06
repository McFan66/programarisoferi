/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import gui.FrmLoadingRaport;
import gui.FrmRapoarte;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import utils.HibernateUtil;

/**
 *
 * @author Stefan
 */
public class RapoarteController {
    
    private FrmRapoarte frmRapoarte;
    private FrmLoadingRaport frmLoadingRaport;
    
    
    public void actionCreate(JFrame parent){
        frmRapoarte = new FrmRapoarte(parent, true);
        frmRapoarte.setTitle("Generare rapoarte");
        DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel();
        defaultComboBoxModel.addElement("--Selectati raportul--");
        defaultComboBoxModel.addElement("Raport Utilizatori");
        frmRapoarte.getCmbRapoarte().setModel(defaultComboBoxModel);
      //  frmRapoarte.getChooserDataInceput()
        frmRapoarte.setRapoarteController(this);
        frmRapoarte.setLocationRelativeTo(parent);
        frmRapoarte.setVisible(true);
    }
    
    private void generareRaport() throws URISyntaxException, JRException{
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (!session.isOpen()) {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
        }
        SessionImpl sessionConn = (SessionImpl) session;
        File tt = new File(".");
        System.out.println(tt.getAbsolutePath());
        Connection connection = sessionConn.connection();
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("dataInceput", new Date(frmRapoarte.getChooserDataInceput().getDate().getTime()));
        parameters.put("dataSfarsit", new Date(frmRapoarte.getChooserDataFinal().getDate().getTime()));
        File reportFolder = new File(getClass().getResource("/rapoarte").toURI());
        Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
        InputStream myFile = new InputStream() {
            @Override
            public int read() throws IOException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        if (frmRapoarte.getCmbRapoarte().getSelectedIndex()==0){
            JOptionPane.showMessageDialog(frmRapoarte, "Va rugam sa selectati un raport.");
            return;
        }else
        if (frmRapoarte.getCmbRapoarte().getSelectedIndex()==1){
            myFile = getClass().getResourceAsStream("/rapoarte/RaportUtilizatoriFinal.jrxml");
        }
        JasperDesign jasperDesign = JRXmlLoader.load(myFile);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);//.fillReport(jasperReport, parameters,new JRBeanCollectionDataSource(ie.test.BeanFactory.getCalcs()));
        JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
        JDialog raport = new JDialog(frmRapoarte);
        raport.setContentPane(jasperViewer.getContentPane());
        raport.setSize(jasperViewer.getSize());
        raport.setVisible(true);
    }
    
    public void doWork(){
        frmLoadingRaport = new FrmLoadingRaport(frmRapoarte, true);
        frmLoadingRaport.setLocationRelativeTo(frmRapoarte);
        frmLoadingRaport.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
                    @Override
                    protected String doInBackground() throws URISyntaxException, IOException, JRException {
                        //Thread.sleep(5000);
                        generareRaport();
                        frmLoadingRaport.dispose();
                        return null;
                    }

                    @Override
                    protected void done() {
                        frmLoadingRaport.dispose();
                    }
                };
                worker.execute();
                frmLoadingRaport.setLocationRelativeTo(frmRapoarte);
                frmLoadingRaport.setVisible(true);
                System.out.println(frmLoadingRaport.getWidth() + " " + frmLoadingRaport.getHeight() + " salut");
                try {
                    worker.get();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
    
    
    
    
}
