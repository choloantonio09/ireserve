//package Capstone.Table;
//
//import java.awt.Component;
//import java.awt.Image;
//import java.io.File;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//import javax.swing.DefaultComboBoxModel;
//import javax.swing.ImageIcon;
//import javax.swing.JLabel;
//import javax.swing.JTable;
//import javax.swing.table.DefaultTableCellRenderer;
//
//import net.proteanit.sql.DbUtils;
//import Capstone.Database.Connect;
//import Capstone.Model.CounterEventFood;
//import Capstone.View.Foods;
//import Capstone.View.EventFoodForm;
//
//public class EventFoodTable {
//	Connect con;
//	Foods f;
//	EventFoodForm eff;
//	public EventFoodTable(Foods f){
//		this.f = f;
//	}
//	public EventFoodTable(EventFoodForm eff){
//		this.eff = eff;
//	}
//	//ViewFood Method
//	public void ViewEventFood(){
//		Thread thr=new Thread(new Runnable(){
//			  public void run()
//			  {
//			      try{
//			    	  try {
//							con = new Connect();
//							con.pst = con.con.prepareCall("{call select_all_event_foods()}");
//							con.pst.execute();
//							con.rs = con.pst.getResultSet();
//							f.tblEventFoods.setModel(DbUtils.resultSetToTableModel(con.rs));
//							con.rs.close();
//							con.pst.close();
//							con.con.close();
//							f.tblEventFoods.getColumnModel().getColumn(3).setCellRenderer(new ImageRenderer(con.rs));
//							((DefaultTableCellRenderer)	f.tblEventFoods.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
//							f.tblEventFoods.setRowHeight(40);
//						} catch (SQLException e1) {
//							e1.printStackTrace();
//						}
//						Thread.sleep(0);
//				   }catch(Exception e){
//					   
//				   }
//			   }
//			 }); thr.start();
//		
//	}//end of ViewFood Method
//		
//	//CounterFood Method
//	public void CounterEventFood(){
//		CounterEventFood cf = new CounterEventFood();
//		cf.getAccountNumber();
//		if(!cf.getAccountNumber().isEmpty()){
//			eff.tfId.setText(cf.getAccountNumber());
//		}
//	}//end of CounterFood Method
//
//	//Viewing the category method
// 	public void ViewCategory(){
//		ArrayList arrayCategory = new ArrayList<String>();
//		try{
//			Connect c = new Connect();
//			c.pst = c.con.prepareCall("{call select_cb_categories(?)}");
//			c.pst.setString(1, "ACTIVE");
//			c.pst.execute();
//			c.rs = c.pst.getResultSet();
//			while (c.rs.next()) { 
//				String cat_name = c.rs.getString("Category Name");
//				arrayCategory.add(cat_name);
//			}
//			c.rs.close();
//			c.pst.close();
//			c.con.close();
//			DefaultComboBoxModel<String> mpCatName = new DefaultComboBoxModel(arrayCategory.toArray());
//			eff.cbCategory.setModel(mpCatName);
//		}catch(Exception ed){
//			ed.printStackTrace();
//		}			
//	}
// 	//end of Viewing the category method
// 	
//
//
//	//Viewing the event food table size method
//	public void ViewEventFoodSizes(String eventfoodid){
//		Thread thr=new Thread(new Runnable(){
//			  public void run()
//			  {
//			      try{
//				    	  try {
//							Connect con = new Connect();
//							con.pst = con.con.prepareCall("{call select_all_event_food_sizes(?)}");
//							con.pst.setString(1, eventfoodid);
//							con.pst.execute();
//							con.rs = con.pst.getResultSet();
//							f.tblEventFoodSizes.setModel(DbUtils.resultSetToTableModel(con.rs));
//							con.rs.close();
//							con.pst.close();
//							con.con.close();
//						} catch (SQLException e1) {
//							e1.printStackTrace();
//						}
//						Thread.sleep(0);
//				   }catch(Exception e){
//					   
//				   }
//			   }
//			 }); thr.start();
//					
//	}//end of Viewing the event food table size method
//	
//	
// 	//Viewing of not in event size table from combobox size method
// 	public void ViewEventNotInSize(String eventfoodid, boolean sizeCheck, String size){
// 		Thread thr=new Thread(new Runnable(){
//			  public void run()
//			  {
//			      try{
//			    	  	ArrayList arraySize = new ArrayList<String>();
//						boolean pick;
//						pick = false;
//						try{
//							Connect c = new Connect();
//							c.pst = c.con.prepareCall("{call select_cb_notin_event_food_sizes(?)}");
//							c.pst.setString(1, eventfoodid);
//							c.pst.execute();
//							c.rs = c.pst.getResultSet();
//							while (c.rs.next()) { 
//								String size_name = c.rs.getString("Size");
//								arraySize.add(size_name);
//							}
//							if(sizeCheck == true){
//								arraySize.add(size);
//							}
//							arraySize.add("[Edit Sizes]");
//							if(arraySize.size() == 1){
//								pick = false;
//							}else{
//								pick = true;
//							}
//							c.rs.close();
//							c.pst.close();
//							c.con.close();
//							DefaultComboBoxModel<String> mpSizeEvent = new DefaultComboBoxModel(arraySize.toArray());
//							f.cbEventSizes.setModel(mpSizeEvent);
//						}catch(Exception ed){
//							ed.printStackTrace();
//						}
//						if(pick == false){
//							f.pickEventFalse();
//							
//						}else{
//							f.pickEventTrue();
//						}
//						Thread.sleep(0);
//				   }catch(Exception e){
//					   
//				   }
//			   }
//			 }); thr.start();
//				 		
// 	}
// 	//end of Viewing of not in event size table from combobox size method
// 	
// 	
//	//class ImageRenderer
//	class ImageRenderer extends DefaultTableCellRenderer {
//		ResultSet rs;
//		public ImageRenderer(ResultSet rs1) {
//			rs=rs1;	
//		}
//		
//		@Override
//		public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected,boolean hasFocus, int row, int column){  
//			JLabel label = new JLabel();
//			try{
//				String currentDirectory;
//				File file = new File("");
//				currentDirectory = file.getAbsolutePath();
//				ImageIcon image = new ImageIcon(new ImageIcon(currentDirectory+"\\"+value).getImage()
//				.getScaledInstance(table.getColumnModel().getColumn(3).getPreferredWidth(), table.getRowHeight(), Image.SCALE_FAST) );   
//				//value is parameter which filled by byteOfImage
//				label.setHorizontalAlignment(JLabel.CENTER);
//				label.setIcon(image);
//			}
//			catch(Exception ex){}
//			return label;
//		}
//	}//end of class ImageRenderer
//}
