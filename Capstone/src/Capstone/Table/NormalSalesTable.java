package Capstone.Table;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

import Capstone.Database.Connect;
import Capstone.Model.CounterNormalOrder;
import Capstone.View.NormalSales;
import net.proteanit.sql.DbUtils;

public class NormalSalesTable {
	Connect con;
	NormalSales ns;
	String type;
	public NormalSalesTable(NormalSales ms){
		this.ns = ms;
		ViewListCategory();
		ViewListPackage();
		CounterOrder();
	}
	public void ViewListCategory(){
		ArrayList arrayCategories = new ArrayList<String>();
		try{
			Connect c = new Connect();
			c.pst = c.con.prepareCall("{call select_list_categories()}");
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			arrayCategories.add("[View all]");
			while (c.rs.next()) { 
				String c_name = c.rs.getString("Category Name");
				arrayCategories.add(c_name);
			}
			c.rs.close();
			c.pst.close();
			c.con.close();
			DefaultComboBoxModel<String> mpCategories = new DefaultComboBoxModel(arrayCategories.toArray());
			ns.listCategories.setModel(mpCategories);
		}catch(SQLException ed){
			ed.printStackTrace();
		}			
	}
	public void ViewListPackage(){
		ArrayList arrayPackages = new ArrayList<String>();
		arrayPackages.add("Regular");
		DefaultComboBoxModel<String> mpPackages = new DefaultComboBoxModel(arrayPackages.toArray());
		ns.listPackage.setModel(mpPackages);				
	}
	public void CounterOrder(){
		CounterNormalOrder cc = new CounterNormalOrder();
		cc.getAccountNumber();
		if(!cc.getAccountNumber().isEmpty()){
			ns.tfOrder.setText(cc.getAccountNumber());
		}
	}
	public void ViewTableOrders(String rid, String type){
		this.type = type;
		Connect c;					
		String total = null;		
		String orderid = null;
		boolean found = false;
		try {
			c = new Connect();
			if(type.equalsIgnoreCase("Dine-in")){
				c.pst = c.con.prepareCall("call select_all_food_guest_dinein_normal_orders(?)");
				
			}else if(type.equalsIgnoreCase("Take-out")){
				c.pst = c.con.prepareCall("call select_all_food_guest_takeout_normal_orders(?)");		
			}else if(type.equalsIgnoreCase("Reserve")){
				c.pst = c.con.prepareCall("call select_all_food_normal_orders(?)");	
			}
			c.pst.setString(1, rid);
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			ns.tblOrders.setModel(DbUtils.resultSetToTableModel(c.rs));
			c.rs.close();	
			c.pst.close();
			c.con.close();
			
			c = new Connect();
			if(type.equalsIgnoreCase("Dine-in")){
				c.pst = c.con.prepareCall("call select_all_guest_dinein_normal_orders(?)");
			}else if(type.equalsIgnoreCase("Take-out")){
				c.pst = c.con.prepareCall("call select_all_guest_takeout_normal_orders(?)");				
			}else if(type.equalsIgnoreCase("Reserve")){
				c.pst = c.con.prepareCall("call select_all_normal_orders(?)");	
			}
			c.pst.setString(1, rid);
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			while(c.rs.next()){
            	found = true;
				total = c.rs.getString("order_total");
				orderid = c.rs.getString("order_id");
				ns.tfTotal.setText(total);
				ns.tfOrder.setText(orderid);
				ns.btnSettle.setText("Update");
				ns.btnSettle.setName("Update");
			}
			if(!found){
				ns.btnSettle.setText("Add");
				ns.btnSettle.setName("Add");
				ns.tfTotal.setText("0.0");
			}
			
		c.rs.close();
		c.pst.close();
		c.con.close();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
