package Capstone.Table;
import java.sql.SQLException;

import net.proteanit.sql.DbUtils;
import Capstone.Database.Connect;
import Capstone.View.NormalBillingDinein;

public class NormalBillingDineinTable {
	Connect con;
	NormalBillingDinein nb;
	public NormalBillingDineinTable(NormalBillingDinein nb){
		this.nb = nb;
//		ViewListCategory();
//		ViewListPackage();
//		CounterOrder();
	}
//	public void ViewListCategory(){
//		ArrayList arrayCategories = new ArrayList<String>();
//		try{
//			Connect c = new Connect();
//			c.pst = c.con.prepareCall("{call select_all_categories()}");
//			c.pst.execute();
//			c.rs = c.pst.getResultSet();
//			arrayCategories.add("[View all]");
//			while (c.rs.next()) { 
//				String c_name = c.rs.getString("Category Name");
//				arrayCategories.add(c_name);
//			}
//			c.rs.close();
//			c.pst.close();
//			c.con.close();
//			DefaultComboBoxModel<String> mpCategories = new DefaultComboBoxModel(arrayCategories.toArray());
//			s.listCategories.setModel(mpCategories);
//		}catch(SQLException ed){
//			ed.printStackTrace();
//		}			
//	}
//	public void ViewListPackage(){
//		ArrayList arrayPackages = new ArrayList<String>();
//		arrayPackages.add("[View all]");
//		arrayPackages.add("Normal Package");
//		arrayPackages.add("Event Package");
//		DefaultComboBoxModel<String> mpPackages = new DefaultComboBoxModel(arrayPackages.toArray());
//		s.listPackage.setModel(mpPackages);				
//	}
//	public void CounterOrder(){
//		CounterOrder cc = new CounterOrder();
//		cc.getAccountNumber();
//		if(!cc.getAccountNumber().isEmpty()){
//			b.tfOrderId.setText(cc.getAccountNumber());
//		}
//	}
	
	public void ViewTableOrders(String rid, String type){
		Connect c;					
		double total = 0.0;	
		double balance = 0.0;			
		String orderid = null;		
		String status = null;
		boolean found = false;
		try {
			c = new Connect();
			if(type.equals("Dine-in")){
				c.pst = c.con.prepareCall("call select_all_food_guest_dinein_normal_orders(?)");
			}else if(type.equals("Reserve")){
				c.pst = c.con.prepareCall("call select_all_food_normal_orders(?)");
			}else if(type.equals("Take-out")){
				c.pst = c.con.prepareCall("call select_all_food_guest_takeout_normal_orders(?)");
			}
			c.pst.setString(1, rid);
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			nb.tblOrders.setModel(DbUtils.resultSetToTableModel(c.rs));
			c.rs.close();
			c.pst.close();
			c.con.close();
			
			c = new Connect();
			if(type.equals("Dine-in")){
				c.pst = c.con.prepareCall("call select_all_guest_dinein_normal_orders(?)");
			}else if(type.equals("Reserve")){
				c.pst = c.con.prepareCall("call select_all_normal_orders(?)");
			}else if(type.equals("Take-out")){
				c.pst = c.con.prepareCall("call select_all_guest_takeout_normal_orders(?)");
			}
			c.pst.setString(1, rid);
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			while(c.rs.next()){
            	found = true;
				total = c.rs.getDouble("order_total");
				balance = c.rs.getDouble("order_balance");
				orderid = c.rs.getString("order_id");
				status = c.rs.getString("order_status");
				if(status.equalsIgnoreCase("Balance")){
					nb.tfTotal.setText(String.valueOf(balance));
					nb.fixed_total = balance;				
					nb.tfChange.setText(String.valueOf(balance-Double.parseDouble(nb.tfCharge.getText())));
				}else if(status.equalsIgnoreCase("Unpaid")){
					nb.tfTotal.setText(String.valueOf(total));
					nb.fixed_total = total;		
					nb.tfChange.setText(String.valueOf(total-Double.parseDouble(nb.tfCharge.getText())));
					
				}
				nb.tfOrderId.setText(orderid);
//				b.btnSettle.setText("Update");
//				b.btnSettle.setName("Update");
			}
			if(!found){
//				s.btnSettle.setText("Add");
//				s.btnSettle.setName("Add");
				nb.tfTotal.setText("0.0");
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
