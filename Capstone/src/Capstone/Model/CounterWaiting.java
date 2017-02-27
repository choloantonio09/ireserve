package Capstone.Model;

import Capstone.Database.Connect;

public class CounterWaiting {
    private String code = null;
    private int start = 0;
    private int end = 0;
    private int number = 0;
    private String code_temp = null;
    private boolean check = false;
    boolean found = false;
    Connect c;
    public String getAccountNumber(){
        try {
        	c = new Connect();
            c.pst = c.con.prepareCall("{call select_id_waiting()}");
            c.pst.execute();
            c.rs = c.pst.getResultSet();
	            while (c.rs.next()) {
	            	found = true;
	                code = c.rs.getString("waiting_id");                  
	            }
	            if(!found){
	            	code = "W000000000";
	            }
	            for (int i = code.length()-1; i >= 0; i--) {
	                if (Character.isDigit(code.charAt(i))&&check == false){
	                	end = i;
	                	check = true;
	                }//END IF
	                if (Character.isLetter(code.charAt(i))&&check == true){
	    	            start = i+1;
	    	            check = false;
	    	            break;
	                }
	    		}//END FOR
	        	code_temp = code.substring(start, end+1);
	        	number = Integer.parseInt(code_temp);
	        	number++;
	        	code_temp = code.substring(0, end+1);
	            if(number >= 0 && number <= 9)
	                code_temp = code.replaceAll(code_temp, String.valueOf(code_temp.substring(0, end)+number));
	            else if(number >= 10 && number <= 99)
	                code_temp = code.replaceAll(code_temp, String.valueOf(code_temp.substring(0, end-1)+number));
	            else if(number >= 100 && number <= 999)
	        		code_temp = code.replaceAll(code_temp, String.valueOf(code_temp.substring(0, end-2)+number));
	            else if(number >= 1000 && number <= 9999)
	        		code_temp = code.replaceAll(code_temp, String.valueOf(code_temp.substring(0, end-3)+number));
	            else if(number >= 10000 && number <= 999999)
	        		code_temp = code.replaceAll(code_temp, String.valueOf(code_temp.substring(0, end-4)+number));
	            else if(number >= 100000 && number <= 9999999)
	        		code_temp = code.replaceAll(code_temp, String.valueOf(code_temp.substring(0, end-5)+number));
	            else if(number >= 1000000 && number <= 99999999)
	        		code_temp = code.replaceAll(code_temp, String.valueOf(code_temp.substring(0, end-6)+number));
	            else if(number >= 10000000 && number <= 999999999)
	        		code_temp = code.replaceAll(code_temp, String.valueOf(code_temp.substring(0, end-7)+number));
	            code = code_temp;
	            c.rs.close();
	            c.pst.close();
	            c.con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return code;        
    }
}
