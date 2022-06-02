/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administrare;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class CnpValidator {
    
    private static boolean validateData(String cnp) {
        String date = cnp.substring(1 , 7);
        if (date.trim().equals(""))
	{
	    return false;
	}
	else
	{
	    SimpleDateFormat sdfrmt = new SimpleDateFormat("yyMMdd");
	    sdfrmt.setLenient(false);
	    try
	    {
	        Date javaDate = sdfrmt.parse(date); 
	    }
	    catch (ParseException e)
	    {
	        return false;
	    }
	    return true;
	}
    }
    
    public boolean validate(String cnp) {
        if(cnp.trim().length() < 13 || cnp.trim().length() > 13) {
            return false;
        }
        if(('1' <= cnp.charAt(0) && cnp.charAt(0) <= '6') || cnp.charAt(0) == '9') {
            if(validateData(cnp)) {
                int[] test = {2,7,9,1,4,6,3,5,8,2,7,9};
                int s = 0;
                for(int i = 0; i < 12 ;i++) {
                    s += test[i] * Character.getNumericValue(cnp.charAt(i));
                }
                int c = s % 11;
                if(c == 10) {
                    c = 1;
                }
                return c == Character.getNumericValue(cnp.charAt(12));
            }
        }
        return false;
    }
     
}
