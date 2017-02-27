package Capstone.Model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ModelSalesReport {
	String start;
	String end;
	public ModelSalesReport(){
		getDateStart();
		getDateEnd();
	}
	
	public String getDateStart(){
		
		return start;
	}

	public String getDateEnd(){
		
		return end;
	}

	public void setDateStart(Date date){
		start = new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	public void setDateEnd(Date date){
		end = new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
}