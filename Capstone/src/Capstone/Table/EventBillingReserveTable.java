package Capstone.Table;
import java.sql.SQLException;

import net.proteanit.sql.DbUtils;
import Capstone.Database.Connect;
import Capstone.View.EventBillingReserve;

public class EventBillingReserveTable {
	Connect con;
	EventBillingReserve eb;
	public EventBillingReserveTable(EventBillingReserve eb){
		this.eb = eb;
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
	
	public void ViewTableOrders(String rid){
		Connect c;					
		double total = 0.0;	
		double balance = 0.0;			
		String orderid = null;		
		String status = null;
		boolean found = false;
		try {
			c = new Connect();
			c.pst = c.con.prepareCall("call select_all_food_event_orders(?)");
			c.pst.setString(1, rid);
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			eb.tblOrders.setModel(DbUtils.resultSetToTableModel(c.rs));
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
				total = c.rs.getDouble("order_total");
				balance = c.rs.getDouble("order_balance");
				orderid = c.rs.getString("order_id");
				status = c.rs.getString("order_status");
				if(status.equalsIgnoreCase("Balance")){
					eb.tfTotal.setText(String.valueOf(balance));
					eb.fixed_total = balance;				
					eb.tfChange.setText(String.valueOf(balance-Double.parseDouble(eb.tfCharge.getText())));
				}else if(status.equalsIgnoreCase("Unpaid")){
					eb.tfTotal.setText(String.valueOf(total));
					eb.fixed_total = total;		
					eb.tfChange.setText(String.valueOf(total-Double.parseDouble(eb.tfCharge.getText())));
					
				}
				eb.tfOrderId.setText(orderid);
//				b.btnSettle.setText("Update");
//				b.btnSettle.setName("Update");
			}
			if(!found){
//				s.btnSettle.setText("Add");
//				s.btnSettle.setName("Add");
				eb.tfTotal.setText("0.0");
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
