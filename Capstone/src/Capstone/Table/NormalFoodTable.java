package Capstone.Table;

import java.awt.Component;
import java.awt.Image;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import net.proteanit.sql.DbUtils;
import Capstone.Database.Connect;
import Capstone.Model.CounterNormalFood;
import Capstone.Model.CounterEventFood;
import Capstone.View.Foods;
import Capstone.View.NormalFoodForm;

public class NormalFoodTable {
	Connect con;
	Foods f;
	NormalFoodForm nff;
	public NormalFoodTable(Foods f){
		this.f = f;
	}
	public NormalFoodTable(NormalFoodForm nff){
		this.nff = nff;
	}
	//ViewFood Method
	public void ViewNormalFood(){
		Thread thr=new Thread(new Runnable(){
			  public void run()
			  {
			      try{
			    	  try {
			    		  con = new Connect();
			    		  con.pst = con.con.prepareCall("{call select_all_normal_foods()}");
			    		  con.pst.execute();
			    		  con.rs = con.pst.getResultSet();
			    		  f.tblNormalFoods.setModel(DbUtils.resultSetToTableModel(con.rs));
			    		  con.rs.close();
			    		  con.pst.close();
			    		  con.con.close();
			    		  f.tblNormalFoods.getColumnModel().getColumn(3).setCellRenderer(new ImageRenderer(con.rs));
			    		  ((DefaultTableCellRenderer)	f.tblNormalFoods.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
			    		  f.tblNormalFoods.setRowHeight(40);
			    	  } catch (SQLException e1) {
			    		  e1.printStackTrace();
			    	  }
						Thread.sleep(0);
				   }catch(Exception e){
					   
				   }
			   }
		}); thr.start();
	}//end of ViewFood Method
		
	//CounterFood Method
	public void CounterNormalFood(){
		CounterNormalFood cf = new CounterNormalFood();
		cf.getAccountNumber();
		if(!cf.getAccountNumber().isEmpty()){
			nff.tfId.setText(cf.getAccountNumber());
		}
	}//end of CounterFood Method

	//CounterFood Method
	public void CounterEventFood(){
		CounterEventFood cf = new CounterEventFood();
		cf.getAccountNumber();
		if(!cf.getAccountNumber().isEmpty()){
			nff.tfId.setText(cf.getAccountNumber());
		}
	}//end of CounterFood Method

	//Viewing the category method
 	public void ViewCategory(){
		ArrayList arrayCategory = new ArrayList<String>();
		try{
			Connect c = new Connect();
			c.pst = c.con.prepareCall("{call select_cb_categories(?)}");
			c.pst.setString(1, "ACTIVE");
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			while (c.rs.next()) { 
				String cat_name = c.rs.getString("Category Name");
				arrayCategory.add(cat_name);
			}
			c.rs.close();
			c.pst.close();
			c.con.close();
			DefaultComboBoxModel<String> mpCatName = new DefaultComboBoxModel(arrayCategory.toArray());
			nff.cbCategory.setModel(mpCatName);
		}catch(Exception ed){
			ed.printStackTrace();
		}			
	}
 	//end of Viewing the category method
 	
	//Viewing the normal food table size method
	public void ViewNormalFoodSizes(String normalfoodid){
		Thread thr=new Thread(new Runnable(){
			  public void run()
			  {
			      try{	  
						try {
							Connect con = new Connect();
							con.pst = con.con.prepareCall("{call select_all_normal_food_sizes(?)}");
							con.pst.setString(1, normalfoodid);
							con.pst.execute();
							con.rs = con.pst.getResultSet();
							f.tblNormalFoodSizes.setModel(DbUtils.resultSetToTableModel(con.rs));
							con.rs.close();
							con.pst.close();
							con.con.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						Thread.sleep(0);
				   }catch(Exception e){
					   
				   }
			   }
			 }); thr.start();
	}//end of Viewing the normal food table size method
 	

 	//Viewing of not in normal size table from combobox size method
 	public void ViewNormalNotInSize(String normalfoodid, boolean sizeCheck, String size){
 		Thread thr=new Thread(new Runnable(){
			  public void run()
			  {
			      try{
			    	  	ArrayList arraySize = new ArrayList<String>();
						boolean pick;
						pick = false;
						try{
							Connect c = new Connect();
							c.pst = c.con.prepareCall("{call select_cb_notin_normal_food_sizes(?)}");
							c.pst.setString(1, normalfoodid);
							c.pst.execute();
							c.rs = c.pst.getResultSet();
							while (c.rs.next()) { 
								String size_name = c.rs.getString("Size");
								arraySize.add(size_name);
							}
							if(sizeCheck == true){
								arraySize.add(size);
							}
							arraySize.add("[Edit Sizes]");
							if(arraySize.size() == 1){
								pick = false;
							}else{
								pick = true;
							}
							c.rs.close();
							c.pst.close();
							c.con.close();
							DefaultComboBoxModel<String> mpSizeNormal = new DefaultComboBoxModel(arraySize.toArray());
							f.cbNormalSizes.setModel(mpSizeNormal);
						}catch(Exception ed){
							ed.printStackTrace();
						}
						if(pick == false){
							f.pickNormalFalse();
							
						}else{
							f.pickNormalTrue();
						}
						Thread.sleep(0);
				   }catch(Exception e){
					   
				   }
			   }
			 }); thr.start();
	 		
 	}
 	//end of Viewing of not in normal size table from combobox size method
	
	//class ImageRenderer
	class ImageRenderer extends DefaultTableCellRenderer {
		ResultSet rs;
		public ImageRenderer(ResultSet rs1) {
			rs=rs1;	
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected,boolean hasFocus, int row, int column){  
			JLabel label = new JLabel();
			try{
				String currentDirectory;
				File file = new File("");
				currentDirectory = file.getAbsolutePath();
				ImageIcon image = new ImageIcon(new ImageIcon(currentDirectory+"\\"+value).getImage()
				.getScaledInstance(table.getColumnModel().getColumn(3).getPreferredWidth(), table.getRowHeight(), Image.SCALE_FAST) );   
				//value is parameter which filled by byteOfImage
				label.setHorizontalAlignment(JLabel.CENTER);
				label.setIcon(image);
			}
			catch(Exception ex){}
			return label;
		}
	}//end of class ImageRenderer
}
