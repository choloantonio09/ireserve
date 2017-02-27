package Capstone.View;

import java.text.SimpleDateFormat;
import java.util.Date;

public class count {

	private static String countString;
	private static Date m_Date;
	static Thread clock;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		m_Date = new Date("04/07/2018 2:15:10 PM");
		clock = new Thread(){
			public void run(){
				try {
					for(;;){
						Date currentDate = new Date();
						if (m_Date.after(currentDate))
						{
							// Calculate difference in dates
							long numericalDifference =  m_Date.getTime() - currentDate.getTime();

							// Divide by 1000 to find number of seconds difference
							numericalDifference = numericalDifference / 1000;
							System.out.println(numericalDifference);
							// Get seconds
							int seconds = (int) numericalDifference % 60;
							
							// Get minutes
							numericalDifference = numericalDifference / 60;
							int minutes = (int) numericalDifference % 60;

							// Get hours
							numericalDifference = numericalDifference / 60;
							int hours = (int) numericalDifference % 24;

							// Get days
							numericalDifference = numericalDifference / 24;
							int days = (int) numericalDifference % 365;

							// Get days
							numericalDifference = numericalDifference / 7;
							int week = (int) numericalDifference;
							
							// Get month
							numericalDifference = numericalDifference / 3;
							int month = (int) numericalDifference;

							// Generate date string
							countString = week   + "week, " +
										  days    + "days, " + 
										  hours   + "hrs, " +
										  minutes + "mins, " +
										  seconds + "secs, " + 
										  month
										  ;
							
							System.out.println(countString);
							if(hours < 2){
								System.out.println("2 hours has been passed. Please make a preparations");
							}
						}
						else{
							countString = "Countdown reached!";
						System.out.println(countString);
						clock.stop();
						}
						sleep(1000);
					}
				} catch (InterruptedException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		};
		clock.start();
	}

}
