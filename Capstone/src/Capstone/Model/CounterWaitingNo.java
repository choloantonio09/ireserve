package Capstone.Model;

import java.text.SimpleDateFormat;
import java.util.Date;

import Capstone.Database.Connect;

public class CounterWaitingNo {
    private int code = 0;
    boolean found = false;
    Connect c;
    public int getAccountNumber(){
        try {
        	c = new Connect();
            c.pst = c.con.prepareCall("{call select_id_waiting_no(?)}");    	    
            c.pst.setString(1, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            c.pst.execute();
            c.rs = c.pst.getResultSet();
	            while (c.rs.next()) {
	            	found = true;
	                code = c.rs.getInt("waiting_no");                  
	            }
	            if(!found){
	            	code = 0;
	            }
	            code++;
	            c.rs.close();
	            c.pst.close();
	            c.con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return code;        
    }
}
