package Capstone.Table;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

import net.proteanit.sql.DbUtils;
import Capstone.Database.Connect;
import Capstone.Model.*;
import Capstone.View.EventOrderReserve;
import Capstone.View.Sizes;

public class EventSalesReserveTable {
	Connect con;
	EventOrderReserve es;
	public EventSalesReserveTable(EventOrderReserve es){
		this.es = es;
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
			arrayCategories.add("[View all food category]");
			while (c.rs.next()) { 
				String c_name = c.rs.getString("Category Name");
				arrayCategories.add(c_name);
			}
			c.rs.close(); 
			c.pst.close();
			c.con.close();
			DefaultComboBoxModel<String> mpCategories = new DefaultComboBoxModel(arrayCategories.toArray());
			es.listCategories.setModel(mpCategories);
		}catch(SQLException ed){
			ed.printStackTrace();
		}			
	}
	public void ViewListPackage(){
		ArrayList arrayPackages = new ArrayList<String>();
		arrayPackages.add("[View all packages]");
		arrayPackages.add("Regular");
		arrayPackages.add("Event");
		DefaultComboBoxModel<String> mpPackages = new DefaultComboBoxModel(arrayPackages.toArray());
		es.listPackage.setModel(mpPackages);				
	}
	public void CounterOrder(){
		CounterEventOrder cc = new CounterEventOrder();
		cc.getAccountNumber();
		if(!cc.getAccountNumber().isEmpty()){
			es.tfOrder.setText(cc.getAccountNumber());
		}
	}
	
	public void ViewTableOrders(String rid){
		Connect c;					
		String total = null;		
		String orderid = null;
		boolean found = false;
		try {
			c = new Connect();
			c.pst = c.con.prepareCall("call select_all_food_event_orders(?)");
			c.pst.setString(1, rid);
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			es.tblOrders.setModel(DbUtils.resultSetToTableModel(c.rs));
			c.rs.close();
			c.pst.close();
			c.con.close();
			
			c = new Connect();
			c.pst = c.con.prepareCall("call select_all_event_orders(?)");
			c.pst.setString(1, rid);
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			while(c.rs.next()){
            	found = true;
				total = c.rs.getString("order_total");
				orderid = c.rs.getString("order_id");
				es.tfTotal.setText(total);
				es.tfOrder.setText(orderid);
				es.btnSettle.setText("Update");
				es.btnSettle.setName("Update");
			}
			if(!found){
				es.btnSettle.setText("Add");
				es.btnSettle.setName("Add");
				es.tfTotal.setText("0.0");
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
