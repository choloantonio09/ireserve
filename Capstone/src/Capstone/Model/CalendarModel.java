package Capstone.Model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarModel {
	String dateString;
	String roomid;
	public CalendarModel(){
		getDate();
	}
	
	public String getDate(){
		return dateString;
	}

	public void setDate(Date date){
			dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	public void setDate(String date){
		dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
}
	public void setId(String roomid){
		this.roomid = roomid;
}

	public String getId() {
		return roomid;
	}
}