package Capstone.Model;

import Capstone.Database.Connect;

public class CounterTable {
    private String code = null;
    private int start = 0;
    private int end = 0;
    private int number = 0;
    private String code_temp = null;
    private boolean check = false;
    boolean found = false;
    Connect c;
//    public static void main(String[] args	){
//    	for (int i = 0; i < 30; i++) {
//        	new CounterTable("T01-", i);			
//		}
//    }
//    CounterTable(String floorid, int tableid){
//    	System.out.println(getAccountNumber(floorid, tableid));
//    }
    public String getAccountNumber(String floorid){
        try {
        	c = new Connect();
            c.pst = c.con.prepareCall("{call select_id_desk(?)}");
            c.pst.setString(1, floorid);
            c.pst.execute();
            c.rs = c.pst.getResultSet();
	            while (c.rs.next()) {
	            	found = true;
	                code = c.rs.getString("desk_number");                  
	            }
	            if(!found){
	            	code = String.valueOf("0");
	            }
	        	number = Integer.parseInt(code);
	        	number++;
	            code = String.valueOf(number);
	            c.rs.close();
	            c.pst.close();
	            c.con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        	return code;        
    }
}
