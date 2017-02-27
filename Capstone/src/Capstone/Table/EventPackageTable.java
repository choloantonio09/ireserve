package Capstone.Table;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

import net.proteanit.sql.DbUtils;
import Capstone.Database.Connect;
import Capstone.Model.CounterNormalPackage;
import Capstone.Model.CounterEventPackage;
import Capstone.View.Packages;
public class EventPackageTable {
	Connect con;
	Packages p;
	public EventPackageTable(Packages p){
		this.p = p;

		ViewEventPackage();
		CounterEventPackage();
	}
	public void ViewEventPackage(){
		Thread thr=new Thread(new Runnable(){
			  public void run()
			  {
			      try{
			    	  	ArrayList arrayPackages = new ArrayList<String>();
						try{
							Connect c = new Connect();
							c.pst = c.con.prepareCall("{call select_event_list_packages()}");
							c.pst.execute();
							c.rs = c.pst.getResultSet();
							arrayPackages.add("[Add more]");
							while (c.rs.next()) { 
								String p_name = c.rs.getString("Package Name");
								arrayPackages.add(p_name);
							}
							c.rs.close();
							c.pst.close();
							c.con.close();
							DefaultComboBoxModel<String> mpEventPackName = new DefaultComboBoxModel(arrayPackages.toArray());
							p.listEventPackage.setModel(mpEventPackName);
						}catch(SQLException ed){
							ed.printStackTrace();
						}			
						Thread.sleep(0);
				   }catch(Exception e){
					   
				   }
			   }
			 }); thr.start();
						
	}
	//CounterFood Method
	public void CounterEventPackage(){
		CounterEventPackage cp = new CounterEventPackage();
		cp.getAccountNumber();
		if(!cp.getAccountNumber().isEmpty()){
			p.tfEventPackageId.setText(cp.getAccountNumber());
		}
	}//end of CounterFood Method
}
