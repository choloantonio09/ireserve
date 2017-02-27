package Capstone.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import org.apache.commons.validator.routines.EmailValidator;

import Capstone.Database.Connect;
import Capstone.Model.ModelCategory;
import Capstone.Model.ModelEventFood;
import Capstone.Model.ModelFloor;
import Capstone.Model.ModelNormalFood;
import Capstone.Model.ModelTable;
import Capstone.Option.StringUtil;
import Capstone.Table.CategoryTable;
//import Capstone.Table.EventFoodTable;
import Capstone.Table.NormalFoodTable;
import Capstone.View.CategoriesForm;
import Capstone.View.Dashboard;
import Capstone.View.EventFoodForm;
import Capstone.View.FloorPlan;
import Capstone.View.Foods;
import Capstone.View.NormalFoodForm;
import Capstone.View.TableForm;
import Capstone.View.Tables;
import Images.GetImageResource;

public class UpdateController {
	Connect con;
	StringUtil su;
	
	CategoriesForm cf;
	ModelCategory mc;
	CategoryTable ct;

	Foods f;
	NormalFoodForm nff;
	ModelNormalFood mnf;
	NormalFoodTable nft;
	EventFoodForm eff;
	ModelEventFood mef;
//	EventFoodTable eft;

	Tables t;
	ModelTable mt;
	
	FloorPlan fp;
	ModelFloor mfp;

	int width[]= new int[100];
	int height[]= new int[100];
	int x[]= new int[100];
	int y[]= new int[100];
	int i = 0;
	int count;

	private TableForm tf;
	
	//Category Update
	public UpdateController(CategoriesForm cf,  CategoryTable ct){
		this.cf = cf;
		this.ct = ct;
		cf.save(new UpdateCategories());
	}//end of Category Update
	
	//Food Update
	public UpdateController(NormalFoodForm nff, NormalFoodTable nft){
		this.nff = nff;
		this.nft = nft;
		this.nff.save(new UpdateNormalFoods());
	}//end of Food Update

	public UpdateController(Foods f, ModelNormalFood mnf, NormalFoodTable nft){
		this.f = f;
		this.mnf = mnf;
		this.nft = nft;
//		this.eft = eft;
		this.f.normalremovesize(new RemoveNormalFoodSizes());
		this.f.normalupdatesize(new UpdateNormalFoodSizes());
//		this.f.eventremovesize(new RemoveEventFoodSizes());
//		this.f.eventupdatesize(new UpdateEventFoodSizes());
	}//end of Food Update

	//Food Update
//	public UpdateController(EventFoodForm eff, EventFoodTable eft){
//		this.eff = eff;
//		this.eft = eft;
//		this.eff.save(new UpdateEventFoods());
//	}//end of Food Update

	//Add Tables
//	public UpdateController(Tables t, ModelTable mt, DeskTable dt){
//		this.t = t;
//		this.mt = mt;
//		this.dt = dt;
//		this.t.save(new SaveLocation());
//		this.t.update(new UpdateTable());
//		this.t.status(new UpdateTableStatus());
//	}//end of Add Tables

	//Add Tables
//	public UpdateController(TableForm tf,  DeskTable dt){
//		this.tf = tf;
//		this.dt = dt;
//		this.tf.save(new UpdateTable());
//		this.t.status(new UpdateTableStatus());
//	}//end of Add Tables
	
	//Add FloorPlan
//	public UpdateController(FloorPlan fp, ModelFloor mfp, FloorTable fpt){
//		this.fp = fp;
//		this.mfp = mfp;
//		this.fpt = fpt;
//		this.fp.add(new UpdateFloorPlan());
//	}//end of FloorPlan
	
	//class of updating categories
	class UpdateCategories implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
				try {
					mc = cf.getCategoryField();
					if(StringUtil.isAlphabeticWithSpaceApostropheOrHyphen(mc.getName()) == true && StringUtil.doesStartsAndEndsWithLetter(mc.getName()) == true){	
						con = new Connect();
						con.pst = con.con.prepareCall("{call update_categories(?,?,?)}");
						mc.setName(mc.getName().substring(0,1).toUpperCase()+mc.getName().substring(1).toLowerCase());
						con.pst.setString(1, mc.getId());
						con.pst.setString(2, mc.getName());
						con.pst.setString(3, mc.getStatus());
						con.pst.execute();
						Dashboard.ct.ViewCategory();
						cf.dispose();
						JOptionPane.showMessageDialog(null, "Category successfully updated");
					}/*else if(validateEmptyString(mc.getName()) == false){
						JOptionPane.showMessageDialog(null, "Empty field");
					}*/else{
						JOptionPane.showMessageDialog(null, "Invalid input");
					}
					con.pst.close();
					con.con.close();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Category name already exists.");
				}
		}
	}//end of class of updating categories
	
	//class of updating category status
	class UpdateCategoryStatus implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				mc = cf.getCategoryField();
				con = new Connect();
				con.pst = con.con.prepareCall("{call update_status_category(?,?)}");
				con.pst.setString(1, mc.getId());
				con.pst.setString(2, mc.getStatus());
				con.pst.execute();
//				if(mc.getStatus().equals("ACTIVE"))
//					c.btnStatus.setText("INACTIVE");
//				else
//					c.btnStatus.setText("ACTIVE");
				ct.ViewCategory();
				JOptionPane.showMessageDialog(null, "Successfully Update Category Status");
				con.pst.close();
				con.con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}//end of class of updating category status
	
	//class of updating categories
	class UpdateNormalFoods implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				mnf = nff.getNormalFoodField();
				if(StringUtil.isAlphanumericWithSpaceApostropheOrHyphen(mnf.getName()) == true && StringUtil.doesStartsAndEndsWithNumberandLetter(mnf.getName()) == true
						&& StringUtil.isNumber(mnf.getName()) == false){
					GetImageResource gir = new GetImageResource();
					String destination = gir.GetImage(mnf.getPhotos(), mnf.getId() + ".jpg", "update");
					con = new Connect();
					con.pst = con.con.prepareCall("{call update_normal_foods(?,?,?,?,?,?)}");
					con.pst.setString(1, mnf.getId());
					con.pst.setString(2, mnf.getName()); 
					con.pst.setString(3, mnf.getCategory());
					con.pst.setString(4, destination);
					con.pst.setString(5, mnf.getStatus());
					con.pst.setString(6, mnf.getType());					
					con.pst.execute();
					Dashboard.nft.ViewNormalFood();
					nff.dispose();
					JOptionPane.showMessageDialog(null, mnf.getType() + " Food successfully updated.");
					con.pst.close();
					con.con.close();
				}/*else if(validateEmptyString(mnf.getName()) == false){
					JOptionPane.showMessageDialog(null, "Empty field");
				}*/else{
					JOptionPane.showMessageDialog(null, "Invalid input");
				}
			} catch (SQLException | IOException e1) {
				JOptionPane.showMessageDialog(null, "Food name already exists.");
			}
		}
	}//end of class of updating categories

	//class of updating categories
	class UpdateEventFoods implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				mef = eff.getEventFoodField();
				if(StringUtil.isAlphanumericWithSpaceApostropheOrHyphen(mef.getName()) == true && StringUtil.doesStartsAndEndsWithNumberandLetter(mef.getName()) == true
						&& StringUtil.isNumber(mef.getName()) == false){
					GetImageResource gir = new GetImageResource();
					String destination = gir.GetImage(mef.getPhotos(), mef.getId() + ".jpg", "update");
					con = new Connect();
					con.pst = con.con.prepareCall("{call update_event_foods(?,?,?,?)}");
					con.pst.setString(1, mef.getId());
					con.pst.setString(2, mef.getName());
					con.pst.setString(3, mef.getCategory());
					con.pst.setString(4, destination);
					con.pst.execute();
//					Dashboard.eft.ViewEventFood();
					JOptionPane.showMessageDialog(null, "Successfully Update Event Food");
					con.pst.close();
					con.con.close();
				}/*else if(validateEmptyString(mnf.getName()) == false){
					JOptionPane.showMessageDialog(null, "Empty field");
				}*/else{
					JOptionPane.showMessageDialog(null, "Invalid input");
				}
			} catch (SQLException | IOException e1) {
				e1.printStackTrace();
			}
		}
	}//end of class of updating categories
	
	//class of updating normal food status
	class UpdateNormalFoodStatus implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				mnf = f.getNormalFoodField();
				con = new Connect();
				con.pst = con.con.prepareCall("{call update_status_normal_food(?,?)}");
				con.pst.setString(1, mnf.getId());
				con.pst.setString(2, mnf.getStatus());
				con.pst.execute();
//				if(mnf.getStatus().equals("ACTIVE"))
//					f.btnNormalStatus.setText("INACTIVE");
//				else
//					f.btnNormalStatus.setText("ACTIVE");
//				nft.ViewNormalFood();
				JOptionPane.showMessageDialog(null, "Successfully Update Normal Food Status");
				con.pst.close();
				con.con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}//end of class of updating normal food status

	//class of updating event food status
//	class UpdateEventFoodStatus implements ActionListener{
//		@Override
//		public void actionPerformed(ActionEvent arg0) {
//			try {
//				mef = f.getEventFoodField();
//				con = new Connect();
//				con.pst = con.con.prepareCall("{call update_status_event_food(?,?)}");
//				con.pst.setString(1, mef.getId());
//				con.pst.setString(2, mef.getStatus());
//				con.pst.execute();
////				if(mef.getStatus().equals("ACTIVE"))
////					f.btnEventStatus.setText("INACTIVE");
////				else
////					f.btnEventStatus.setText("ACTIVE");
//				eft.ViewEventFood();
//				JOptionPane.showMessageDialog(null, "Successfully Update Event Food Status");
//				con.pst.close();
//				con.con.close();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//		}
//	}//end of class of updating event food status
	
	//class of removing normal food sizes
	class RemoveNormalFoodSizes implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				mnf = f.getNormalFoodField();
				con = new Connect();
				con.pst = con.con.prepareCall("{call delete_normal_food_size(?,?)}");
				con.pst.setString(1, mnf.getId());
				con.pst.setString(2, mnf.getSizeTable());
				con.pst.execute();
				f.btnNormalCancelSize.doClick();
				JOptionPane.showMessageDialog(null, "Food " + mnf.getSize()+ " size successfully removed.");
				con.pst.close();
				con.con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}//end of class of removing normal food sizes

	//class of removing event food sizes
//	class RemoveEventFoodSizes implements ActionListener{
//		@Override
//		public void actionPerformed(ActionEvent arg0) {
//			try {
//				mef = f.getEventFoodField();
//				con = new Connect();
//				con.pst = con.con.prepareCall("{call delete_event_food_size(?,?)}");
//				con.pst.setString(1, mef.getId());
//				con.pst.setString(2, mef.getSizeTable());
//				con.pst.execute();
//				f.btnEventCancelSize.doClick();
//				JOptionPane.showMessageDialog(null, "Successfully Remove Event Food Sizes");
//				con.pst.close();
//				con.con.close();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//		}
//	}//end of class of removing event food sizes
	
	//class of updating normal food sizes
	class UpdateNormalFoodSizes implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				mnf = f.getNormalFoodField();
				if(StringUtil.isNumber(String.valueOf(mnf.getFoodPrice())) == true){
					if(mnf.getFoodPrice() > 0.00){
						con = new Connect();
						con.pst = con.con.prepareCall("{call update_normal_food_size(?,?,?,?,?)}");
						con.pst.setString(1, mnf.getId());
						con.pst.setString(2, mnf.getSize());
						con.pst.setString(3, mnf.getDesc());
						con.pst.setDouble(4, mnf.getFoodPrice());
						con.pst.setString(5, mnf.getSizeTable());
						con.pst.execute();
						f.btnNormalCancelSize.doClick();
						JOptionPane.showMessageDialog(null, "Food size successfully updated.");
						con.pst.close();
						con.con.close();
					}else{
						JOptionPane.showMessageDialog(null, "Does not accept negative value for price..");
					}
				}else{
				JOptionPane.showMessageDialog(null, "Invalid price.");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Invalid Input");				
			}
		}
	}//end of class of updating normal food sizes
	
	//class of updating event food sizes
//	class UpdateEventFoodSizes implements ActionListener{
//		@Override
//		public void actionPerformed(ActionEvent arg0) {
//			try {
//				mef = f.getEventFoodField();
//					if(validateName(mef.getDesc()) == true && validateEmptyString(mef.getDesc()) == true){
//					con = new Connect();
//					con.pst = con.con.prepareCall("{call update_event_food_size(?,?,?,?,?,?,?)}");
//					con.pst.setString(1, mef.getId());
//					con.pst.setString(2, mef.getSize());
//					con.pst.setString(3, mef.getDesc());
//					con.pst.setDouble(4, mef.getFoodPrice());
//					con.pst.setInt(5, mef.getDiscount());
//					con.pst.setDouble(6, mef.getPackagePrice());
//					con.pst.setString(7, mef.getSizeTable());
//					con.pst.execute();
//					f.btnEventCancelSize.doClick();
//					JOptionPane.showMessageDialog(null, "Successfully Update Event Food Sizes");
//					con.pst.close();
//					con.con.close();
//				}else if(validateEmptyString(mef.getDesc()) == false){
//					JOptionPane.showMessageDialog(null, "Empty field");
//				}else{
//					JOptionPane.showMessageDialog(null, "Invalid character");
//				}
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//		}
//	}//end of class of updating event food sizes
	
	//class of updating table
//	class UpdateTable implements ActionListener{
//		@Override
//		public void actionPerformed(ActionEvent arg0) {
//			if(tf.btnSave.getName().equalsIgnoreCase("UPDATE")){
//				try {
//					mt = tf.getTableField();
//					con = new Connect();
//					con.pst = con.con.prepareCall("{call update_desks(?,?,?,?,?)}");
//					con.pst.setString(1, mt.getId());
//					con.pst.setString(2, mt.getDesc());
//					con.pst.setInt(3, mt.getCapacity());
//					con.pst.setString(4, mt.getSymbol());
//					con.pst.setString(5, mt.getFloor());
//					con.pst.execute();
//					JOptionPane.showMessageDialog(null, "Successfully Update Table");
//					Dashboard.t.ViewDecorate();
//					Dashboard.dt.ViewTable();
//					tf.dispose();
//					con.pst.close();
//					con.con.close();
//				} catch (SQLException e1) {
//					e1.printStackTrace();
//				} 
//			}
//		}
//	}//end of class of updating table
//	
//	//class of updating table status
//		class UpdateTableStatus implements ActionListener{
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				try {
//					mt = t.getTableField();
//					con = new Connect();
//					con.pst = con.con.prepareCall("{call update_status_desk(?,?)}");
//					con.pst.setString(1, mt.getId());
//					con.pst.setString(2, mt.getStatus());
//					con.pst.execute();
////					if(mt.getStatus().equals("ACTIVE"))
////						t.btnStatus.setText("INACTIVE");
////					else
////						t.btnStatus.setText("ACTIVE");
//					JOptionPane.showMessageDialog(null, "Successfully Update Table Status");
//					t.btnCancel.doClick();
//					con.pst.close();
//					con.con.close();
//				} catch (SQLException e1) {
//					e1.printStackTrace();
//				}
//			}
//		}//end of class of updating table status
//		
//	//class of updating table decoration
//	class SaveLocation implements ActionListener{
//		@Override
//		public void actionPerformed(ActionEvent arg0) {
//			if(arg0.getActionCommand() == "EDIT"){
//				i = 0;
//				t.btnCancelDecorate.setEnabled(true);
//				t.btnSave.setActionCommand("SAVE");
//				t.btnSave.setText("SAVE");
//				Connect c;
//				try {
//					c = new Connect();
//					c.pst = c.con.prepareCall("{call select_desk_buttons(?,?)}");
//					c.pst.setString(1, "ACTIVE");
//					c.pst.setString(2, t.cbFloorPlan.getSelectedItem().toString());
//					c.pst.execute();
//					c.rs = c.pst.getResultSet();
//					while(c.rs.next()){
//						t.btnDynamic[i].setEnabled(true);
//						i++;
//					}
//					t.editing = true;
//					c.rs.close();
//					c.pst.close();
//					c.con.close();
//				} catch (SQLException e1) {
//					e1.printStackTrace();
//				}
//			}else if(arg0.getActionCommand() == "SAVE"){
//				try {
//					t.lblSelectedButton.setText("");
//					mt = t.getTableField();				
//					count = mt.getCount();
//			    	for(int j = 0; j < count; j++){
//						mt = t.getTable2Field(j);	
//						con = new Connect();
//						con.pst = con.con.prepareCall("{call update_desk_location(?,?,?,?,?)}");
//						con.pst.setString(1, mt.getDescription());
//						con.pst.setInt(2, mt.getWidth());
//						con.pst.setInt(3, mt.getHeight());
//						con.pst.setInt(4, mt.getX());
//						con.pst.setInt(5, mt.getY());
//						con.pst.execute();
//						con.pst.close();
//						con.con.close();
//			    	}
//					JOptionPane.showMessageDialog(null, "Successfully Save Table Location");
//					dt.ViewTable();
//					t.ViewDecorate();
//					t.btnSave.setActionCommand("EDIT");
//					t.btnSave.setText("EDIT");
//					t.btnCancelDecorate.setEnabled(false);
//					t.pnlDecorate.setEnabled(true);
//					t.editing = false;
//					
//				} catch (SQLException e1) {
//					e1.printStackTrace();
//				} 
//			}
//		}
//	}//end of class of updating table decoration

//	//class of adding event foods
//		class UpdateFloorPlan implements ActionListener{
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				try {
//					mfp = fp.getFloorField();
//					if(fp.btnAdd.getName().equalsIgnoreCase("Update")){
////						if(validateName(mfp.getName()) == false && validateEmptyString(mfp.getName()) == false){	
//							GetFloorImageResource gir = new GetFloorImageResource();
//							String destination = gir.GetImage(mfp.getDestination(), mfp.getId() + ".jpg", "update");
//							con = new Connect();
//							con.pst = con.con.prepareCall("{call update_floor_plan(?,?,?,?)}");
//							con.pst.setString(1, mfp.getId());
//							con.pst.setString(2, mfp.getName());
//							con.pst.setString(3, destination);
//							con.pst.setString(4, mfp.getStatus());
//							con.pst.execute();
//							Dashboard.fpt.ViewFloorPlan();
//							fpt.CounterFloorPlan();
//							Dashboard.btnFloor.doClick();
//							con.pst.close();
//							con.con.close();	
//							JOptionPane.showMessageDialog(null, "Successfully Update Floor Plan");
//					}
////					}else if(validateEmptyString(mfp.getName()) == true){
////						JOptionPane.showMessageDialog(null, "Empty field");						
////					}else{
////						JOptionPane.showMessageDialog(null, "Invalid character");
////					}
//				} catch (SQLException | IOException e1) {
//					e1.printStackTrace();
//				}
//			}
//		}//end of class of adding event food

	// validate empty
   public static boolean validateEmptyString( String name ){
	   if(name.isEmpty()){
		   return false;
	   }else{
		   return true;
	   }
   } // end method validateEmptyString
   
   public boolean validateName(String strname){ //Method
			if (strname.matches("[a-zA-Z '-]+")){
				return true;
		}else {
				return false;
		}
	}//Method
   
   public String  validateDecimal(String number){//Method
	      String pattern = "[0-9]+([,.][0-9]{1,2})?";
	      Pattern r = Pattern.compile(pattern);
	      Matcher m = r.matcher(number);
	      if (m.find( )) {
	    	 return  m.group(0);
	      }else {
	    	  return number;
	      }
   }//Method

	public boolean validateEmail(String mail){ //Method for checking email
		if(EmailValidator.getInstance().isValid(mail)){
			return true;			
		}else {
			return false;
		}
	}//Method
}
