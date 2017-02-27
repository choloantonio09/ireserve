	package Capstone.Table;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

import net.proteanit.sql.DbUtils;
import Capstone.Database.Connect;
import Capstone.Model.CounterNormalPackage;
import Capstone.Model.CounterEventPackage;
import Capstone.View.Packages;
public class NormalPackageTable {
	Connect con;
	Packages p;
	public NormalPackageTable(Packages p){
		this.p = p;
		ViewNormalPackage();
		CounterNormalPackage();
		ViewEventPackage();
	}
	public void ViewNormalPackage(){
		ArrayList arrayPackages = new ArrayList<String>();
		try{
			Connect c = new Connect();
			c.pst = c.con.prepareCall("{call select_normal_list_packages()}");
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			while (c.rs.next()) { 
				String p_name = c.rs.getString("Package Name");
				arrayPackages.add(p_name);
			}
			c.rs.close();
			c.pst.close();
			c.con.close();
			DefaultComboBoxModel<String> mpNormalPackName = new		 DefaultComboBoxModel(arrayPackages.toArray());
			p.listNormalPackage.setModel(mpNormalPackName);
		}catch(SQLException ed){
			ed.printStackTrace();
		}			
		
	}
	public void ViewEventPackage(){
	  	ArrayList arrayPackages = new ArrayList<String>();
		try{
			Connect c = new Connect();
			c.pst = c.con.prepareCall("{call select_event_list_packages()}");
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			while (c.rs.next()) { 
				String p_name = c.rs.getString("Package Name");
				arrayPackages.add(p_name);
			}
			c.rs.close();
			c.pst.close();
			c.con.close();
			DefaultComboBoxModel<String> mpEventPackName = new		 DefaultComboBoxModel(arrayPackages.toArray());
			p.listEventPackage.setModel(mpEventPackName);
		}catch(SQLException ed){
			ed.printStackTrace();
		}			
	}

	//CounterFood Method
	public void CounterNormalPackage(){
		CounterNormalPackage cp = new CounterNormalPackage();
		cp.getAccountNumber();
		if(!cp.getAccountNumber().isEmpty()){
			p.tfNormalPackageId.setText(cp.getAccountNumber());
		}
	}//end of CounterFood Method

}
