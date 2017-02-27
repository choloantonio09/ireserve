package Capstone.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.validator.routines.EmailValidator;

import Capstone.Database.Connect;
import Capstone.Model.ModelCategory;
import Capstone.Model.ModelEventFood;
import Capstone.Model.ModelEventPackage;
import Capstone.Model.ModelFloor;
import Capstone.Model.ModelFunctionRoom;
import Capstone.Model.ModelNormalFood;
import Capstone.Model.ModelNormalPackage;
import Capstone.Model.ModelReserve;
import Capstone.Model.ModelSales;
import Capstone.Model.ModelSalesEventOrder;
import Capstone.Model.ModelSalesNormalOrder;
import Capstone.Model.ModelSize;
import Capstone.Model.ModelStandardRoom;
import Capstone.Model.ModelTable;
import Capstone.Model.ModelWaiting;
import Capstone.Option.StringUtil;
import Capstone.Table.CategoryTable;
//import Capstone.Table.EventFoodTable;
import Capstone.Table.EventPackageTable;
import Capstone.Table.EventSalesReserveTable;
import Capstone.Table.NormalFoodTable;
import Capstone.Table.NormalPackageTable;
import Capstone.Table.NormalSalesTable;
import Capstone.Table.ReservationTable;
import Capstone.Table.ReserveTable;
import Capstone.Table.RoomTable;
import Capstone.Table.SizeTable;
import Capstone.Table.WaitingTable;
import Capstone.View.Categories;
import Capstone.View.CategoriesForm;
import Capstone.View.Dashboard;
import Capstone.View.EventBillingReserve;
import Capstone.View.EventFoodForm;
import Capstone.View.EventOrderReserve;
import Capstone.View.FloorPlanForm;
import Capstone.View.Foods;
import Capstone.View.NormalFoodForm;
import Capstone.View.NormalSales;
import Capstone.View.POSDesign;
import Capstone.View.Packages;
import Capstone.View.RoomPlanForm;
import Capstone.View.Selection;
import Capstone.View.Sizes;
import Capstone.View.TableForm;
import Capstone.View.Tables;
import Capstone.View.WaitingForm;
import Capstone.View.WindowEventBillingReserve;
import Capstone.View.WindowEventOrderReserve;
import Capstone.View.WindowNormalBillingDinein;
import Images.GetImageResource;

public class AddController{

	Connect con;
	
	StringUtil su;
	
	Categories c;
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
	TableForm tf;
	ModelTable mt;

	Packages p;
	ModelNormalPackage mnp;
	ModelEventPackage mep;
	EventPackageTable ept;
	NormalPackageTable npt;
	
	Sizes s;
	SizeTable st;
	ModelSize ms;
	
	ModelReserve mr;
	ReserveTable rt;
	ReservationTable ret;

	NormalSales nsl;
	ModelSales nmsl;
	NormalSalesTable nslt;
	
	ModelSalesNormalOrder nmrsl;

	EventOrderReserve eor;
	ModelSalesEventOrder emrsl;
	EventSalesReserveTable esrt;
	
	WaitingForm w; 
	ModelWaiting mw; 
	WaitingTable wt;
	
	RoomPlanForm rpf;
	RoomTable rpt;
	
	FloorPlanForm fpf; 
	ModelFloor mfp;
	ModelStandardRoom msr;	
	ModelFunctionRoom mfr;	

	public Thread thr;

	//Add Category
	public AddController(CategoriesForm cf, CategoryTable ct){
		this.cf = cf;
		this.ct = ct;
		this.cf.save(new AddCategories());
	}//end of Add Category
	
	//Add Foods
	public AddController(Foods f, ModelNormalFood mnf, NormalFoodTable nft){
		this.f = f;
		this.mnf = mnf;
		this.nft = nft;
		this.f.normaladdsize(new AddNormalFoodSizes());
	}//end of Add Foods

	//Add Foods
//	public AddController(Foods f, ModelEventFood mef, EventFoodTable eft){
//		this.f = f;
//		this.eft = eft;
//		this.mef = mef;
//		this.f.eventaddsize(new AddEventFoodSizes());
//	}//end of Add Foods
	
	//Add Foods
	public AddController(NormalFoodForm nff,  NormalFoodTable nft){
		this.nff = nff;
		this.nft = nft;
		this.nff.save(new AddNormalFoods());
	}//end of Add Foods

	//Add Foods
//	public AddController(EventFoodForm eff,  EventFoodTable eft){
//		this.eff = eff;
//		this.eft = eft;
//		this.eff.save(new AddEventFoods());
//	}//end of Add Foods
	
	//Add Tables
	public AddController(TableForm tf, ModelStandardRoom msr){
		this.tf = tf;
		this.msr = msr;
	}//end of Add Tables
	
	//Add Package
	public AddController(Packages p, ModelNormalPackage mnp, NormalPackageTable npt){
		this.p = p;
		this.mnp = mnp;
		this.npt = npt;
		this.p.normalcreateAndUpdate(new CreateAndUpdateNormalPackage());
	}//end of Add Package
	
	//Add Sizes
	public AddController(Sizes s, ModelSize ms, SizeTable st){
		this.s = s;
		this.ms = ms;
		this.st = st;
		this.s.add(new AddSizes());
	}//end of Add Sizes	

	//Add Sizes
//	public AddController(ReserveForm r,  ReserveTable rt, ReservationTable ret){
//		this.r = r;
//		this.rt = rt;
//		this.ret = ret;
//	}//end of Add Sizes
	
	//Add Sizes
	public AddController(NormalSales nsl, ModelSales nmsl, NormalSalesTable nslt){
		this.nsl = nsl;
		this.nmsl = nmsl;
		this.nslt = nslt;
		this.nsl.add(new NormalDineinAddMeal());
		this.nsl.add(new NormalTakeoutAddMeal());
	}//end of Add Sizes
	
	//Add Sizes
	public AddController(EventOrderReserve eor, ModelSalesEventOrder emrsl, EventSalesReserveTable esrt){
		this.eor = eor;
		this.emrsl = emrsl;
		this.esrt = esrt;
		this.eor.add(new EventReserveAddMeal());
	}//end of Add Sizes
	
	public AddController(WaitingForm w, ModelWaiting mw, WaitingTable wt) {
		// TODO Auto-generated constructor stub
		this.w = w;
		this.mw = mw;
		this.wt = wt;
		this.w.save(new AddWaiting());
		
	}

//	public AddController(NormalOrderReserve nor, ModelSalesNormalOrder nmrsl, NormalSalesReserveTable nsrt) {
//		this.nor= nor;
//		this.nmrsl = nmrsl;
//		this.nsrt = nsrt;
//		this.nor.add(new NormalReserveAddMeal());
//		// TODO Auto-generated constructor stub
//	}
	
	//Add FloorPlan
	public AddController(RoomPlanForm rpf,  ModelFunctionRoom mfr, ModelStandardRoom msr, RoomTable rpt){
		this.rpf = rpf;
		this.mfr = mfr;
		this.msr = msr;;
		this.rpt = rpt;
	}//end of FloorPlan

	//class of adding categories
	class AddCategories implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
				try {
					su = new StringUtil();
					mc = cf.getCategoryField();
					if(StringUtil.isAlphabeticWithSpaceApostropheOrHyphen(mc.getName()) == true && StringUtil.doesStartsAndEndsWithLetter(mc.getName()) == true){	
						con = new Connect();
						con.pst = con.con.prepareCall("{call insert_categories(?,?,?)}");
						mc.setName(mc.getName().substring(0,1).toUpperCase()+mc.getName().substring(1).toLowerCase());
						con.pst.setString(1, mc.getId());
						con.pst.setString(2, mc.getName());
						con.pst.setString(3, mc.getStatus());
						con.pst.execute();
						Dashboard.ct.ViewCategory();
						ct.CounterCategory();
						cf.dispose();
						JOptionPane.showMessageDialog(null, "Category successfully added");
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
		
	}//end of class of adding categories
	
	//class of adding normal foods
	class AddNormalFoods implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				mnf = nff.getNormalFoodField();
				if(StringUtil.isAlphanumericWithSpaceApostropheOrHyphen(mnf.getName()) == true && StringUtil.doesStartsAndEndsWithNumberandLetter(mnf.getName()) == true
						&& StringUtil.isNumber(mnf.getName()) == false){	
					GetImageResource gir = new GetImageResource();
					String destination = gir.GetImage(mnf.getPhotos(), mnf.getId() + ".jpg", "add");
					con = new Connect();
					con.pst = con.con.prepareCall("{call insert_normal_foods(?,?,?,?,?,?)}");
					con.pst.setString(1, mnf.getId());
					con.pst.setString(2, mnf.getCategory());
					con.pst.setString(3, mnf.getName());
					con.pst.setString(4, destination);
					con.pst.setString(5, mnf.getStatus());
					con.pst.setString(6, mnf.getType());
					con.pst.execute();
					Dashboard.nft.ViewNormalFood();
					nft.CounterNormalFood();
					con.pst.close();
					con.con.close();	
					nff.dispose();
					JOptionPane.showMessageDialog(null, mnf.getType()+" Food successfully added");
				}/*else if(validateEmptyString(mnf.getName()) == false){
					JOptionPane.showMessageDialog(null, "Empty field");
				}*/else{
					JOptionPane.showMessageDialog(null, "Invalid input");
				}
			} catch (SQLException | IOException e1) {
				JOptionPane.showMessageDialog(null, "Food name already exists.");
			}
		}
	}//end of class of adding normal food

	//class of adding event foods
//		class AddEventFoods implements ActionListener{
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				try {
//					mef = eff.getEventFoodField();
//					if(validateName(mef.getName()) == true && validateEmptyString(mef.getName()) == true){	
//						GetImageResource gir = new GetImageResource();
//						String destination = gir.GetImage(mef.getPhotos(), mef.getId() + ".jpg", "add");
//						con = new Connect();
//						con.pst = con.con.prepareCall("{call insert_event_foods(?,?,?,?,?)}");
//						con.pst.setString(1, mef.getId());
//						con.pst.setString(2, mef.getCategory());
//						con.pst.setString(3, mef.getName());
//						con.pst.setString(4, destination);
//						con.pst.setString(5, mef.getStatus());
//						con.pst.execute();
//						Dashboard.eft.ViewEventFood();
//						eft.CounterEventFood();
//						con.pst.close();
//						con.con.close();	
//						JOptionPane.showMessageDialog(null, "Successfully Add Event Food");
//					}else if(validateEmptyString(mef.getName()) == false){
//						JOptionPane.showMessageDialog(null, "Empty field");						
//					}else{
//						JOptionPane.showMessageDialog(null, "Invalid character");
//					}
//				} catch (SQLException | IOException e1) {
//					e1.printStackTrace();
//				}
//			}
//		}//end of class of adding event food
		
	//class of adding normal foods
	class AddNormalFoodSizes implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				mnf = f.getNormalFoodField();
				if(StringUtil.isNumber(String.valueOf(mnf.getFoodPrice())) == true){
						if(mnf.getFoodPrice() > 0.00){
						con = new Connect();
						con.pst = con.con.prepareCall("{call insert_normal_food_sizes(?,?,?,?)}");
						con.pst.setString(1, mnf.getId());
						con.pst.setString(2, mnf.getSize());
						con.pst.setString(3, mnf.getDesc());
						con.pst.setDouble(4, mnf.getFoodPrice());
						con.pst.execute();
						Dashboard.nft.ViewNormalFoodSizes(mnf.getId());
						Dashboard.nft.ViewNormalNotInSize(mnf.getId(), false, null);
						JOptionPane.showMessageDialog(null, "Food size successfully added");
						con.pst.close();
						con.con.close();	
					}else{
						JOptionPane.showMessageDialog(null, "Does not accept negative value for price..");
					}
				}	else{
					JOptionPane.showMessageDialog(null, "Invalid price.");
				}
			} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Invalid input");
			}
		}
	}//end of class of adding normal food sizes
	
	//class of adding normal foods
//	class AddEventFoodSizes implements ActionListener{
//		@Override
//		public void actionPerformed(ActionEvent arg0) {
//			try {
//				mef = f.getEventFoodField();
//				if(validateName(mef.getDesc()) == true && validateEmptyString(mef.getDesc()) == true){	
//					con = new Connect();
//					con.pst = con.con.prepareCall("{call insert_event_food_sizes(?,?,?,?,?,?)}");
//					con.pst.setString(1, mef.getId());
//					con.pst.setString(2, mef.getSize());
//					con.pst.setString(3, mef.getDesc());
//					con.pst.setDouble(4, mef.getFoodPrice());
//					con.pst.setInt(5, mef.getDiscount());
//					con.pst.setDouble(6, mef.getPackagePrice());
//					con.pst.execute();
//					Dashboard.eft.ViewEventFoodSizes(mef.getId());
//					Dashboard.eft.ViewEventNotInSize(mef.getId(), false, null);
//					JOptionPane.showMessageDialog(null, "Successfully Add Event Food Sizes");
//				}else if(validateEmptyString(mef.getDesc()) == false){
//					JOptionPane.showMessageDialog(null, "Empty field");						
//				}else{
//					JOptionPane.showMessageDialog(null, "Invalid character");
//				}
//				con.pst.close();
//				con.con.close();	
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//		}
//	}//end of class of adding normal food sizes
	
	//class of adding sizes		
	class AddSizes implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				ms = s.getSizesField();
				if(StringUtil.isAlphanumericWithSpaceApostropheOrHyphen(ms.getName()) == true && StringUtil.doesStartsAndEndsWithNumberandLetter(ms.getName()) == true
						&& StringUtil.isNumber(ms.getName()) == false){	
				con = new Connect();
				con.pst = con.con.prepareCall("{call insert_sizes(?)}");
				con.pst.setString(1, ms.getName());
					con.pst.execute();
					st.ViewSize();
					s.tfSizeName.setText(null);
					JOptionPane.showMessageDialog(null, "Size successfully added.");
				}/*else if(validateEmptyString(ms.getName()) == false){
					JOptionPane.showMessageDialog(null, "Empty field");						
				}*/else{
					JOptionPane.showMessageDialog(null, "Invalid input");
				}
				con.pst.close();
				con.con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}//end of class of adding sizes

	//class of adding packages
	class CreateAndUpdateNormalPackage implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(p.btnNormalCreate.getName().equalsIgnoreCase("CREATE")){
				DefaultTableModel dtm = (DefaultTableModel) p.tblNormalFoods.getModel();
			    int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
			    Object[][] tableData = new Object[nRow][nCol];
	        	try {
	        		mnp = p.getModelNormalPackage();
					if(StringUtil.isAlphanumericWithSpaceApostropheOrHyphen(mnp.getName()) == true && StringUtil.doesStartsAndEndsWithNumberandLetter(mnp.getName()) == true
							&& StringUtil.isNumber(mnp.getName()) == false && validateEmptyString(mnp.getName())){	
						GetImageResource gir = new GetImageResource();
						String destination = gir.GetImage(mnp.getPhotos(), mnp.getId() + ".jpg", "add");
		        		con = new Connect();
		        		con.pst = con.con.prepareCall("{call insert_normal_packages(?,?,?,?,?,?,?,?,?,?)}");
		        		con.pst.setString(1, mnp.getId());
		        		con.pst.setString(2, mnp.getName());
		        		con.pst.setString(3, mnp.getServingSize());
		        		con.pst.setString(4, destination);
		        		con.pst.setString(5, mnp.getType());
		        		con.pst.setDouble(6, mnp.getTotal());
		        		con.pst.setInt(7, mnp.getDiscountbypercent());
		        		con.pst.setDouble(8, mnp.getDiscountbyprice());
		        		con.pst.setString(9, mnp.getDiscount());		        		
		        		con.pst.setDouble(10, mnp.getOriginalprice());
		        		con.pst.execute();			
					    for (int i = 0 ; i < nRow ; i++){
					        for (int j = 0 ; j < nCol ; j++){
					            tableData[i][j] = dtm.getValueAt(i,j);
					        }
							con.pst = con.con.prepareCall("{call insert_normal_food_packages(?,?,?,?,?,?)}");
							con.pst.setString(1, mnp.getId());
							con.pst.setString(2, tableData[i][0].toString());
							con.pst.setString(3, tableData[i][1].toString());
							con.pst.setString(4, tableData[i][2].toString());
							con.pst.setString(5, tableData[i][3].toString());
							con.pst.setString(6, tableData[i][4].toString());
							con.pst.execute();						
					    }
					    p.RefreshPackage();
						JOptionPane.showMessageDialog(null, mnp.getType()+ " Package successfully added.");
						con.pst.close();
					    con.con.close();
					}else if(validateEmptyString(mnp.getName()) == false){
						JOptionPane.showMessageDialog(null, "Empty field");						
					}else{
						JOptionPane.showMessageDialog(null, "Invalid input");
					}
			    } catch (SQLException | IOException e) {
					e.printStackTrace();
				}
				
			}else if(p.btnNormalCreate.getName().equalsIgnoreCase("UPDATE")){
				DefaultTableModel dtm = (DefaultTableModel) p.tblNormalFoods.getModel();
			    int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
			    Object[][] tableData = new Object[nRow][nCol];
	        	try {
	        		mnp = p.getModelNormalPackage();
					if(StringUtil.isAlphanumericWithSpaceApostropheOrHyphen(mnp.getName()) == true && StringUtil.doesStartsAndEndsWithNumberandLetter(mnp.getName()) == true
							&& StringUtil.isNumber(mnp.getName()) == false){	
						GetImageResource gir = new GetImageResource();
						String destination = gir.GetImage(mnp.getPhotos(), mnp.getId() + ".jpg", "add");
		        		con = new Connect();
		        		con.pst = con.con.prepareCall("{call update_normal_packages(?,?,?,?,?,?,?,?,?,?)}");
		        		con.pst.setString(1, mnp.getId());
		        		con.pst.setString(2, mnp.getName());
		        		con.pst.setString(3, mnp.getServingSize());
		        		con.pst.setString(4, destination);
		        		con.pst.setString(5, mnp.getType());
		        		con.pst.setDouble(6, mnp.getTotal());
		        		con.pst.setInt(7, mnp.getDiscountbypercent());
		        		con.pst.setDouble(8, mnp.getDiscountbyprice());
		        		con.pst.setString(9, mnp.getDiscount());		        		
		        		con.pst.setDouble(10, mnp.getOriginalprice());
		        		con.pst.execute();			
		        		con.pst = con.con.prepareCall("{call delete_normal_food_packages(?)}");
						con.pst.setString(1, mnp.getId());
		        		con.pst.execute();			
					    for (int i = 0 ; i < nRow ; i++){
					        for (int j = 0 ; j < nCol ; j++){
					            tableData[i][j] = dtm.getValueAt(i,j);
					        }
							con.pst = con.con.prepareCall("{call update_normal_food_packages(?,?,?,?,?,?)}");
							con.pst.setString(1, mnp.getId());
							con.pst.setString(2, tableData[i][0].toString());
							con.pst.setString(3, tableData[i][1].toString());
							con.pst.setString(4, tableData[i][2].toString());
							con.pst.setString(5, tableData[i][3].toString());
							con.pst.setString(6, tableData[i][4].toString());
							con.pst.execute();						
					    }
						p.RefreshPackage();
						JOptionPane.showMessageDialog(null, mnp.getType()+ " Package successfully updated.");
						con.pst.close();
					    con.con.close();
		        	}/*else if(validateEmptyString(mnp.getName()) == false){
						JOptionPane.showMessageDialog(null, "Empty field");						
					}*/else{
						JOptionPane.showMessageDialog(null, "Invalid input");
					}
			    } catch (SQLException | IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}//end of class of adding packages
	
	//class of adding packages
//	class CreateAndUpdateEventPackage implements ActionListener{
//		@Override
//		public void actionPerformed(ActionEvent arg0) {
//			if(p.btnEventCreate.getName().equalsIgnoreCase("CREATE")){
//				DefaultTableModel dtm = (DefaultTableModel) p.tblEventFoods.getModel();
//			    int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
//			    Object[][] tableData = new Object[nRow][nCol];
//	        	try {
//	        		mep = p.getModelEventPackage();
//					if(validateName(mep.getName()) == true && validateEmptyString(mep.getName()) == true){	
//						GetImageResource gir = new GetImageResource();
//						String destination = gir.GetImage(mep.getPhotos(), mep.getId() + ".jpg", "add");
//		        		con = new Connect();
//		        		con.pst = con.con.prepareCall("{call insert_event_packages(?,?,?,?,?)}");
//		        		con.pst.setString(1, mep.getId());
//		        		con.pst.setString(2, mep.getName());
//		        		con.pst.setString(3, mep.getServingSize());
//		        		con.pst.setString(4, destination);
//		        		con.pst.setDouble(5, mep.getTotal());
//		        		con.pst.execute();			
//					    for (int i = 0 ; i < nRow ; i++){
//					        for (int j = 0 ; j < nCol ; j++){
//					            tableData[i][j] = dtm.getValueAt(i,j);
//					        }
//							con.pst = con.con.prepareCall("{call insert_event_food_packages(?,?,?,?,?,?)}");
//							con.pst.setString(1, mep.getId());
//							con.pst.setString(2, tableData[i][0].toString());
//							con.pst.setString(3, tableData[i][1].toString());
//							con.pst.setString(4, tableData[i][2].toString());
//							con.pst.setString(5, tableData[i][3].toString());
//							con.pst.setString(6, tableData[i][4].toString());
//							con.pst.execute();						
//					    }
//					    ept.ViewEventPackage();
//	
//						p.tfEventTotal.setText("0.0");
//						p.spnEventServingSize.setValue(1);
//						p.tfEventPackageName.setText("");
//						p.btnEventCreate.setText("CREATE");
//						p.btnEventCreate.setEnabled(true);
//						p.btnEventDelete.setEnabled(false);
//						p.btnEventRemove.setEnabled(false);
//						DefaultTableModel dm = (DefaultTableModel) p.tblEventFoods.getModel();
//						int rowCount = dm.getRowCount();
//						//Remove rows one by one from the end of the table
//						for (int i = rowCount - 1; i >= 0; i--) {
//						    dm.removeRow(i);
//						}					
//						JOptionPane.showMessageDialog(null, "Successfully Add Event Package");
//						con.pst.close();
//					    con.con.close();
//		        	}else if(validateEmptyString(mep.getName()) == false){
//						JOptionPane.showMessageDialog(null, "Empty field");						
//					}else{
//						JOptionPane.showMessageDialog(null, "Invalid character");
//					}
//			    } catch (SQLException | IOException e) {
//					e.printStackTrace();
//				}
//				
//			}else if(p.btnEventCreate.getName().equalsIgnoreCase("UPDATE")){
//				DefaultTableModel dtm = (DefaultTableModel) p.tblEventFoods.getModel();
//			    int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
//			    Object[][] tableData = new Object[nRow][nCol];
//	        	try {
//	        		mep = p.getModelEventPackage();
//					if(validateName(mep.getName()) == true && validateEmptyString(mep.getName()) == true){	
//						GetImageResource gir = new GetImageResource();
//						String destination = gir.GetImage(mep.getPhotos(), mep.getId() + ".jpg", "add");
//		        		con = new Connect();
//		        		con.pst = con.con.prepareCall("{call update_event_packages(?,?,?,?,?)}");
//		        		con.pst.setString(1, mep.getId());
//		        		con.pst.setString(2, mep.getName());
//		        		con.pst.setString(3, mep.getServingSize());
//		        		con.pst.setString(4, destination);
//		        		con.pst.setDouble(5, mep.getTotal());
//		        		con.pst.execute();			
//		        		con.pst = con.con.prepareCall("{call delete_event_food_packages(?)}");
//						con.pst.setString(1, mep.getId());
//		        		con.pst.execute();		
//					    for (int i = 0 ; i < nRow ; i++){
//					        for (int j = 0 ; j < nCol ; j++){
//					            tableData[i][j] = dtm.getValueAt(i,j);
//					        }
//							con.pst = con.con.prepareCall("{call update_event_food_packages(?,?,?,?,?,?)}");
//							con.pst.setString(1, mep.getId());
//							con.pst.setString(2, tableData[i][0].toString());
//							con.pst.setString(3, tableData[i][1].toString());
//							con.pst.setString(4, tableData[i][2].toString());
//							con.pst.setString(5, tableData[i][3].toString());
//							con.pst.setString(6, tableData[i][4].toString());
//							con.pst.execute();						
//					    }
//					    ept.ViewEventPackage();
//	
//						p.tfEventTotal.setText("0.0");
//						p.spnEventServingSize.setValue(1);
//						p.tfEventPackageName.setText("");
//						p.btnEventCreate.setText("CREATE");
//						p.btnEventCreate.setEnabled(true);
//						p.btnEventDelete.setEnabled(false);
//						p.btnEventRemove.setEnabled(false);
//						DefaultTableModel dm = (DefaultTableModel) p.tblEventFoods.getModel();
//						int rowCount = dm.getRowCount();
//						//Remove rows one by one from the end of the table
//						for (int i = rowCount - 1; i >= 0; i--) {
//						    dm.removeRow(i);
//						}					
//						JOptionPane.showMessageDialog(null, "Successfully Update Event Package");
//						con.pst.close();
//					    con.con.close();
//		        	}else if(validateEmptyString(mep.getName()) == false){
//						JOptionPane.showMessageDialog(null, "Empty field");						
//					}else{
//						JOptionPane.showMessageDialog(null, "Invalid character");
//					}
//			    } catch (SQLException | IOException e) {
//					e.printStackTrace();
//				}
//			}
//			
//		}
//	}//end of class of adding packages
//	
	//class of adding reserve
//	class AddReserve implements ActionListener{
//		@Override
//		public void actionPerformed(ActionEvent arg0) {
//			if(r.btnSave.getName().equalsIgnoreCase("ADD")){
//					try {
//						mr = r.getFieldData();
//						con = new Connect();
//						con.pst = con.con.prepareCall("{call insert_reserve(?,?,?,?,?,?,?)}");
//						con.pst.setString(1, mr.getId());
//						con.pst.setString(2, mr.getName());
//						con.pst.setString(3, mr.getContact());
//						con.pst.setString(4, mr.getEmail());
//						con.pst.setString(5, mr.getType());
//						con.pst.setString(6, mr.getGuest());
//						con.pst.setString(7, mr.getMeridem());
//						con.pst.execute();
//
//						for(int i = 0 ; i < TableAppointment.table; i++){
//							if(mr.getType().equalsIgnoreCase("Normal")){
//								if(TableAppointment._table[i] != null){
//									mr = r.getFieldData();
//									
//							        Calendar calStart = Calendar.getInstance();
//							        calStart.setTime(new SimpleDateFormat("HH:mm:ss").parse(mr.getTime()));
//							        calStart .add(Calendar.MINUTE, -30);
//							        SimpleDateFormat sdfStart = new SimpleDateFormat("HH:mm:ss");
//							        
//							        Calendar calEnd = Calendar.getInstance();
//							        calEnd.setTime(new SimpleDateFormat("HH:mm:ss").parse(mr.getTime()));
//							        calEnd .add(Calendar.MINUTE, 30);
//							        SimpleDateFormat sdfEnd = new SimpleDateFormat("HH:mm:ss");
//							        
//									con = new Connect();								
//									con.pst = con.con.prepareCall("{call insert_reserve_desks(?,?,?,?,?,?,?,?)}");
//									con.pst.setString(1, mr.getId());
//									con.pst.setString(2, TableAppointment.lblTable[i].getName());
//									con.pst.setString(3, mr.getDate());
//									con.pst.setString(4, sdfStart.format(calStart.getTime()));		//time end
//									con.pst.setString(5, mr.getTime());
//									con.pst.setString(6, sdfEnd.format(calEnd.getTime()));		//time end
//									con.pst.setString(7, "Reserve");
//									con.pst.setInt(8, (int) TableAppointment.spnTable[i].getValue());
//									con.pst.execute();
//								}
//							}else if (mr.getType().equalsIgnoreCase("Event")){
//								if(TableAppointment._table[i] != null){
//									mr = r.getFieldData();
//									
//							        Calendar calStart = Calendar.getInstance();
//							        calStart.setTime(new SimpleDateFormat("HH:mm:ss").parse(mr.getTime()));
//							        calStart .add(Calendar.HOUR, -2);
//							        SimpleDateFormat sdfStart = new SimpleDateFormat("HH:mm:ss");
//							        
//							        Calendar calEnd = Calendar.getInstance();
//							        calEnd.setTime(new SimpleDateFormat("HH:mm:ss").parse(mr.getTime()));
//							        calEnd .add(Calendar.HOUR, 2);
//							        SimpleDateFormat sdfEnd = new SimpleDateFormat("HH:mm:ss");
//							        
//									con = new Connect();								
//									con.pst = con.con.prepareCall("{call insert_reserve_desks(?,?,?,?,?,?,?,?)}");
//									con.pst.setString(1, mr.getId());
//									con.pst.setString(2, TableAppointment.lblTable[i].getName());
//									con.pst.setString(3, mr.getDate());
//									con.pst.setString(4, sdfStart.format(calStart.getTime()));		//time end
//									con.pst.setString(5, mr.getTime());
//									con.pst.setString(6, sdfEnd.format(calEnd.getTime()));		//time end
//									con.pst.setString(7, "Reserve");
//									con.pst.setInt(8, (int) TableAppointment.spnTable[i].getValue());
//									con.pst.execute();
//								}
//							}
//						}
////						if(validateName(mr.getName()) == false && validateEmptyString(mr.getName()) == false){	
//							r.dispose();
//							ret.ViewReservation();
//							TableDesign.SetTables();
//							TableDesign.ViewDecorate();
//							TableDesign.clock.suspend();
//							TableDesign.ViewOccupied();
//							TableAppointment.panel.removeAll();
//							TableAppointment.SetTableSize();
//							TableAppointment.ViewTimeTable();
//							TableAppointment.ViewOccupiedTimeTable();				
//							JOptionPane.showMessageDialog(null, "Reserve Successfully Added");
////			        	}else if(validateEmptyString(mr.getName()) == true){
////							JOptionPane.showMessageDialog(null, "Empty field");						
////						}else{
////							JOptionPane.showMessageDialog(null, "Invalid character");
////						}
//						con.pst.close();
//						con.con.close();
//					} catch (SQLException | ParseException e1) {
//						e1.printStackTrace();
//					}
//				
//			}else if(r.btnSave.getName().equalsIgnoreCase("UPDATE")){
//
//				try {
//					mr = r.getFieldData();
//					con = new Connect();
//					con.pst = con.con.prepareCall("{call update_reserve(?,?,?,?}");
//					con.pst.setString(1, mr.getId());
//					con.pst.setString(2, mr.getName());
//					con.pst.setString(3, mr.getContact());
//					con.pst.setString(4, mr.getEmail());
////					if(validateName(mr.getName()) == false && validateEmptyString(mr.getName()) == false){
////						con.pst.execute();
//						rt.CounterReserve();
//						ret.ViewReservation();
//						JOptionPane.showMessageDialog(null, "Reserve Successfully Updated");
////		        	}else if(validateEmptyString(mr.getName()) == true){
////						JOptionPane.showMessageDialog(null, "Empty field");						
////					}else{
////						JOptionPane.showMessageDialog(null, "Invalid character");
////					}
//					con.pst.close();
//					con.con.close();
//				} catch (SQLException | ParseException e1) {
//					e1.printStackTrace();
//				}
//			}
//			
//		}
//		
//	}//end of class of adding reserve
	
	//class of adding reserve
		class AddWaiting implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(w.btnSave.getName().equalsIgnoreCase("ADD")){
						try {
							mw = w.getFieldData();
							con = new Connect();
							con.pst = con.con.prepareCall("{call insert_waiting(?,?,?)}");
							con.pst.setString(1, mw.getId());
							con.pst.setString(2, mw.getName());
							con.pst.setString(3, mw.getNo());
							con.pst.execute();
							con.pst.close();
							con.con.close();
							w.btnSave.setName("UPDATE");
							JOptionPane.showMessageDialog(null, "Waiting customer successfully added");
						} catch (SQLException | ParseException e1) {
							e1.printStackTrace();
						}
					
				}else if(w.btnSave.getName().equalsIgnoreCase("UPDATE")){

					try {
						mw = w.getFieldData();
						con = new Connect();
						con.pst = con.con.prepareCall("{call update_waiting(?,?,?)}");
						con.pst.setString(1, mw.getId());
						con.pst.setString(2, mw.getName());
						con.pst.setString(3, mw.getNo());
						con.pst.execute();
						con.pst.close();
						con.con.close();
						JOptionPane.showMessageDialog(null, "Waiting customer successfully updated");
					} catch (SQLException | ParseException e1) {
						e1.printStackTrace();
					}
				}
				
			}
			
		}//end of class of adding reserve
	
	//class of adding dinein meal
	class NormalDineinAddMeal implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(nsl.btnSettle.getText().equalsIgnoreCase("Add") && nsl.lblTakeDinein.getText().equalsIgnoreCase("Dine-in")&& nsl.checkTable == true){//DINEIN WITH TABLESSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
				DefaultTableModel dtm = (DefaultTableModel) nsl.tblOrders.getModel();
			    int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
			    Object[][] tableData = new Object[nRow][nCol];
	        	try {
	        		nmsl = nsl.getFieldData();
	        		con = new Connect();
	        		System.out.println(nmsl.getType());
					if(nmsl.getType().equalsIgnoreCase("Dine-in")){
		        		con.pst = con.con.prepareCall("{call insert_guest_dinein(?,?)}");
		        		con.pst.setString(1, nmsl.getId());
		        		con.pst.setString(2, nmsl.getName());
		        		con.pst.execute();	
			        }
	        		
					for(int k = 0 ; k < POSDesign.i; k++){
						if(nmsl.getType().equalsIgnoreCase("Dine-in")){
							if(POSDesign.tablevalue[k] != null){
								nmsl = nsl.getFieldData();								
								String currenttime = new SimpleDateFormat("HH:mm:ss").format(new Date());
								String currentdate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
								
						        Calendar calStart = Calendar.getInstance();
						        calStart.setTime(new SimpleDateFormat("HH:mm:ss").parse(currenttime));
						        calStart .add(Calendar.MINUTE, 0);
						        SimpleDateFormat sdfStart = new SimpleDateFormat("HH:mm:ss");

						        Calendar calCurrent = Calendar.getInstance();
						        calCurrent.setTime(new SimpleDateFormat("HH:mm:ss").parse(currenttime));
						        calCurrent .add(Calendar.MINUTE, 30);
						        SimpleDateFormat sdfCurrent = new SimpleDateFormat("HH:mm:ss");
						        
						        Calendar calEnd = Calendar.getInstance();
						        calEnd.setTime(new SimpleDateFormat("HH:mm:ss").parse(currenttime));
						        calEnd .add(Calendar.MINUTE, 60);
						        SimpleDateFormat sdfEnd = new SimpleDateFormat("HH:mm:ss");
						        
								con = new Connect();								
								con.pst = con.con.prepareCall("{call insert_guest_dinein_desks(?,?,?,?,?,?,?,?)}");
								con.pst.setString(1, nmsl.getId());
								con.pst.setString(2, POSDesign.lblTable[k].getName());
								con.pst.setString(3, currentdate);
								con.pst.setString(4, sdfStart.format(calStart.getTime()));		//time end
								con.pst.setString(5, sdfCurrent.format(calStart.getTime()));
								con.pst.setString(6, sdfEnd.format(calEnd.getTime()));		//time end
								con.pst.setString(7, "Occupied");
								con.pst.setInt(8, (int)  POSDesign.spnTable[k].getValue());
								System.out.println((int)  POSDesign.spnTable[k].getValue());
								con.pst.execute();
							}
						}
					}
	        		
	        		con = new Connect();
	        		con.pst = con.con.prepareCall("{call insert_normal_order(?,?,?,?)}");
	        		con.pst.setString(1, nmsl.getOrderId());
	        		con.pst.setString(2, nmsl.getId());
	        		con.pst.setString(3, nmsl.getType());
	        		con.pst.setDouble(4, nmsl.getTotal());
	        		con.pst.execute();			
				    for (int i = 0 ; i < nRow ; i++){
				        for (int j = 0 ; j < nCol ; j++){
				            tableData[i][j] = dtm.getValueAt(i,j);
				        }
				        if(nmsl.getType().equals("Dine-in")){
				        	con.pst = con.con.prepareCall("{call insert_dinein_normal_order(?,?,?,?,?,?)}");
				        }else if(nmsl.getType().equals("Reserve")){
				        	con.pst = con.con.prepareCall("{call insert_reserve_normal_order(?,?,?,?,?,?)}");
				        }
						con.pst.setString(1, nmsl.getOrderId());
						con.pst.setString(2, tableData[i][0].toString());
						con.pst.setString(3, tableData[i][1].toString());
						con.pst.setString(4, tableData[i][2].toString());
						con.pst.setString(5, tableData[i][3].toString());
						con.pst.setString(6, tableData[i][4].toString());
						con.pst.execute();						
				    }
				    nslt.ViewTableOrders(nmsl.getId(), nmsl.getType());
					JOptionPane.showMessageDialog(null, "Successfully Add Orders");
					Selection.ret.ViewEventReservation();
					Selection.ret.ViewNormalReservation();
					POSDesign.SetTables();
					POSDesign.ViewDecorate();
					POSDesign.ViewOccupied();
					POSDesign.clock.suspend();
					POSDesign.ViewOccupied();
					POSDesign.ViewAllTableSetText();
					
					nsl.dispose();
					WindowNormalBillingDinein ab = new WindowNormalBillingDinein(nmsl.getId(), nmsl.getType(), nmsl.getStatus(), nmsl.getName());
					ab.jf.setModal(true);
					ab.jf.setVisible(true);
					
					con.pst.close();
				    con.con.close();
			    } catch (SQLException | ParseException e) {
					e.printStackTrace();
				}
				
			}else if(nsl.btnSettle.getText().equalsIgnoreCase("Update") && nsl.lblTakeDinein.getText().equalsIgnoreCase("Dine-in") && nsl.checkTable == true){
				DefaultTableModel dtm = (DefaultTableModel) nsl.tblOrders.getModel();
			    int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
			    Object[][] tableData = new Object[nRow][nCol];
	        	try {
	        		nmsl = nsl.getFieldData();
	        		con = new Connect();
	        		con.pst = con.con.prepareCall("{call update_normal_order(?,?,?,?)}");
	        		con.pst.setString(1, nmsl.getOrderId());
	        		con.pst.setString(2, nmsl.getId());
	        		con.pst.setString(3, nmsl.getType());
	        		con.pst.setDouble(4, nmsl.getTotal());
	        		con.pst.execute();			
			        if(nmsl.getType().equals("Dine-in")){
		        		con.pst = con.con.prepareCall("{call delete_dinein_normal_orders(?)}");
			        }else if(nmsl.getType().equals("Reserve")){
		        		con.pst = con.con.prepareCall("{call delete_reserve_normal_orders(?)}");
			        }
	        		con.pst.setString(1, nmsl.getOrderId());
	        		con.pst.execute();		
				    for (int i = 0 ; i < nRow ; i++){
				        for (int j = 0 ; j < nCol ; j++){
				            tableData[i][j] = dtm.getValueAt(i,j);
				        }
				        if(nmsl.getType().equals("Dine-in")){
							con.pst = con.con.prepareCall("{call update_dinein_normal_order(?,?,?,?,?,?)}");
				        }else if(nmsl.getType().equals("Reserve")){
							con.pst = con.con.prepareCall("{call update_reserve_normal_order(?,?,?,?,?,?)}");
				        }
		        		con.pst.setString(1, nmsl.getOrderId());
						con.pst.setString(2, tableData[i][0].toString());
						con.pst.setString(3, tableData[i][1].toString());
						con.pst.setString(4, tableData[i][2].toString());
						con.pst.setString(5, tableData[i][3].toString());
						con.pst.setString(6, tableData[i][4].toString());
						con.pst.execute();				
				    }
				    if(nRow == 0){
				        if(nmsl.getType().equals("Dine-in")){
				        	con.pst = con.con.prepareCall("{call delete_dinein_normal_orders(?)}");
				        }else if(nmsl.getType().equals("Reserve")){
				        	con.pst = con.con.prepareCall("{call delete_reserve_normal_orders(?)}");
				        }
		        		con.pst.setString(1, nmsl.getOrderId());
		        		con.pst.execute();		
				    }
				    nslt.ViewTableOrders(nmsl.getId(), nmsl.getType());
					JOptionPane.showMessageDialog(null, "Successfully Update Orders");
					Selection.ret.ViewEventReservation();
					Selection.ret.ViewNormalReservation();
					POSDesign.SetTables();
					POSDesign.ViewDecorate();
					POSDesign.ViewOccupied();
					POSDesign.clock.suspend();
					POSDesign.ViewOccupied();

					nsl.dispose();
					WindowNormalBillingDinein ab = new WindowNormalBillingDinein(nmsl.getId(), nmsl.getType(), nmsl.getStatus(), nmsl.getName());
					ab.jf.setModal(true);
					ab.jf.setVisible(true);
					
					con.pst.close();
				    con.con.close();
			    } catch (SQLException e) {
					e.printStackTrace();
				}
			}else if(nsl.btnSettle.getText().equalsIgnoreCase("Add") && nsl.lblTakeDinein.getText().equalsIgnoreCase("Dine-in") && nsl.checkTable == false){ //DINEIN NO TABLESSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
				DefaultTableModel dtm = (DefaultTableModel) nsl.tblOrders.getModel();
			    int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
			    Object[][] tableData = new Object[nRow][nCol];
	        	try {
	        		nmsl = nsl.getFieldData();
	        		con = new Connect();
			        if(nmsl.getType().equals("Dine-in")){
		        		con.pst = con.con.prepareCall("{call insert_guest_dinein(?,?)}");
		        		con.pst.setString(1, nmsl.getId());
		        		con.pst.setString(2, nmsl.getName());
		        		con.pst.execute();	
			        }
	        		con.pst = con.con.prepareCall("{call insert_normal_order(?,?,?,?)}");
	        		con.pst.setString(1, nmsl.getOrderId());
	        		con.pst.setString(2, nmsl.getId());
	        		con.pst.setString(3, nmsl.getType());
	        		con.pst.setDouble(4, nmsl.getTotal());
	        		con.pst.execute();			
				    for (int i = 0 ; i < nRow ; i++){
				        for (int j = 0 ; j < nCol ; j++){
				            tableData[i][j] = dtm.getValueAt(i,j);
				        }
				        if(nmsl.getType().equals("Dine-in")){
				        	con.pst = con.con.prepareCall("{call insert_dinein_normal_order(?,?,?,?,?,?)}");
				        }else if(nmsl.getType().equals("Reserve")){
				        	con.pst = con.con.prepareCall("{call insert_reserve_normal_order(?,?,?,?,?,?)}");
				        }
						con.pst.setString(1, nmsl.getOrderId());
						con.pst.setString(2, tableData[i][0].toString());
						con.pst.setString(3, tableData[i][1].toString());
						con.pst.setString(4, tableData[i][2].toString());
						con.pst.setString(5, tableData[i][3].toString());
						con.pst.setString(6, tableData[i][4].toString());
						con.pst.execute();						
				    }
				    nslt.ViewTableOrders(nmsl.getId(), nmsl.getType());
					JOptionPane.showMessageDialog(null, "Successfully Add Orders");
					Selection.ret.ViewEventReservation();
					Selection.ret.ViewNormalReservation();
					POSDesign.SetTables();
					POSDesign.ViewDecorate();
					POSDesign.ViewOccupied();
					POSDesign.clock.suspend();
					POSDesign.ViewOccupied();

					nsl.dispose();
					WindowNormalBillingDinein ab = new WindowNormalBillingDinein(nmsl.getId(), nmsl.getType(), nmsl.getStatus(), nmsl.getName());
					ab.jf.setModal(true);
					ab.jf.setVisible(true);
					
					con.pst.close();
				    con.con.close();
			    } catch (SQLException e) {
					e.printStackTrace();
				}
				
			}else if(nsl.btnSettle.getText().equalsIgnoreCase("Update") && nsl.lblTakeDinein.getText().equalsIgnoreCase("Dine-in") && nsl.checkTable == false){
				DefaultTableModel dtm = (DefaultTableModel) nsl.tblOrders.getModel();
			    int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
			    Object[][] tableData = new Object[nRow][nCol];
	        	try {
	        		nmsl = nsl.getFieldData();
	        		con = new Connect();
	        		con.pst = con.con.prepareCall("{call update_normal_order(?,?,?,?)}");
	        		con.pst.setString(1, nmsl.getOrderId());
	        		con.pst.setString(2, nmsl.getId());
	        		con.pst.setString(3, nmsl.getType());
	        		con.pst.setDouble(4, nmsl.getTotal());
	        		con.pst.execute();			
			        if(nmsl.getType().equals("Dine-in")){
		        		con.pst = con.con.prepareCall("{call delete_dinein_normal_orders(?)}");
			        }else if(nmsl.getType().equals("Reserve")){
		        		con.pst = con.con.prepareCall("{call delete_reserve_normal_orders(?)}");
			        }
	        		con.pst.setString(1, nmsl.getOrderId());
	        		con.pst.execute();		
				    for (int i = 0 ; i < nRow ; i++){
				        for (int j = 0 ; j < nCol ; j++){
				            tableData[i][j] = dtm.getValueAt(i,j);
				        }
				        if(nmsl.getType().equals("Dine-in")){
							con.pst = con.con.prepareCall("{call update_dinein_normal_order(?,?,?,?,?,?)}");
				        }else if(nmsl.getType().equals("Reserve")){
							con.pst = con.con.prepareCall("{call update_reserve_normal_order(?,?,?,?,?,?)}");
				        }
		        		con.pst.setString(1, nmsl.getOrderId());
						con.pst.setString(2, tableData[i][0].toString());
						con.pst.setString(3, tableData[i][1].toString());
						con.pst.setString(4, tableData[i][2].toString());
						con.pst.setString(5, tableData[i][3].toString());
						con.pst.setString(6, tableData[i][4].toString());
						con.pst.execute();				
				    }
				    if(nRow == 0){
				        if(nmsl.getType().equals("Dine-in")){
				        	con.pst = con.con.prepareCall("{call delete_dinein_normal_orders(?)}");
				        }else if(nmsl.getType().equals("Reserve")){
				        	con.pst = con.con.prepareCall("{call delete_reserve_normal_orders(?)}");
				        }
		        		con.pst.setString(1, nmsl.getOrderId());
		        		con.pst.execute();		
				    }
				    nslt.ViewTableOrders(nmsl.getId(), nmsl.getType());
					JOptionPane.showMessageDialog(null, "Successfully Update Orders");
					Selection.ret.ViewEventReservation();
					Selection.ret.ViewNormalReservation();
					POSDesign.SetTables();
					POSDesign.ViewDecorate();
					POSDesign.ViewOccupied();
					POSDesign.clock.suspend();
					POSDesign.ViewOccupied();

					nsl.dispose();
					WindowNormalBillingDinein ab = new WindowNormalBillingDinein(nmsl.getId(), nmsl.getType(), nmsl.getStatus(), nmsl.getName());
					ab.jf.setModal(true);
					ab.jf.setVisible(true);
					
					con.pst.close();
				    con.con.close();
			    } catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
	}//end of class of adding dinein meal
	
	//class of adding takeout meal
		class NormalTakeoutAddMeal implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(nsl.btnSettle.getText().equalsIgnoreCase("Add") && nsl.lblTakeDinein.getText().equalsIgnoreCase("Take-out")){
					DefaultTableModel dtm = (DefaultTableModel) nsl.tblOrders.getModel();
				    int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
				    Object[][] tableData = new Object[nRow][nCol];
		        	try {
		        		nmsl = nsl.getFieldData();
		        		con = new Connect();
		        		con.pst = con.con.prepareCall("{call insert_guest_takeout(?)}");
		        		con.pst.setString(1, nmsl.getId());
		        		con.pst.execute();	
		        		
		        		con = new Connect();
		        		con.pst = con.con.prepareCall("{call insert_normal_order(?,?,?,?)}");
		        		con.pst.setString(1, nmsl.getOrderId());
		        		con.pst.setString(2, nmsl.getId());
		        		con.pst.setString(3, nmsl.getType());
		        		con.pst.setDouble(4, nmsl.getTotal());
		        		con.pst.execute();			
					    for (int i = 0 ; i < nRow ; i++){
					        for (int j = 0 ; j < nCol ; j++){
					            tableData[i][j] = dtm.getValueAt(i,j);
					        }
							con.pst = con.con.prepareCall("{call insert_takeout_normal_order(?,?,?,?,?,?)}");
							con.pst.setString(1, nmsl.getOrderId());
							con.pst.setString(2, tableData[i][0].toString());
							con.pst.setString(3, tableData[i][1].toString());
							con.pst.setString(4, tableData[i][2].toString());
							con.pst.setString(5, tableData[i][3].toString());
							con.pst.setString(6, tableData[i][4].toString());
							con.pst.execute();						
					    }
					    nslt.ViewTableOrders(nmsl.getId(), "Take-out");
						JOptionPane.showMessageDialog(null, "Successfully Add Orders");
						
						nsl.dispose();
						WindowNormalBillingDinein ab = new WindowNormalBillingDinein(nmsl.getId(), nmsl.getType(), nmsl.getStatus(), nmsl.getName());
						ab.jf.setModal(true);
						ab.jf.setVisible(true);
						con.pst.close();
					    con.con.close();
				    } catch (SQLException e) {
						e.printStackTrace();
					}
					
				}else if(nsl.btnSettle.getText().equalsIgnoreCase("Update") && nsl.lblTakeDinein.getText().equalsIgnoreCase("Take-out")){
					DefaultTableModel dtm = (DefaultTableModel) nsl.tblOrders.getModel();
				    int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
				    Object[][] tableData = new Object[nRow][nCol];
		        	try {
		        		nmsl = nsl.getFieldData();
		        		con = new Connect();
		        		con.pst = con.con.prepareCall("{call update_normal_order(?,?,?,?)}");
		        		con.pst.setString(1, nmsl.getOrderId());
		        		con.pst.setString(2, nmsl.getId());
		        		con.pst.setString(3, nmsl.getType());
		        		con.pst.setDouble(4, nmsl.getTotal());
		        		con.pst.execute();			
		        		con.pst = con.con.prepareCall("{call delete_takeout_normal_orders(?)}");
		        		con.pst.setString(1, nmsl.getOrderId());
		        		con.pst.execute();		
					    for (int i = 0 ; i < nRow ; i++){
					        for (int j = 0 ; j < nCol ; j++){
					            tableData[i][j] = dtm.getValueAt(i,j);
					        }
							con.pst = con.con.prepareCall("{call update_takeout_normal_order(?,?,?,?,?,?)}");
			        		con.pst.setString(1, nmsl.getOrderId());
							con.pst.setString(2, tableData[i][0].toString());
							con.pst.setString(3, tableData[i][1].toString());
							con.pst.setString(4, tableData[i][2].toString());
							con.pst.setString(5, tableData[i][3].toString());
							con.pst.setString(6, tableData[i][4].toString());
							con.pst.execute();				
					    }
					    if(nRow == 0){
			        		con.pst = con.con.prepareCall("{call delete_takeout_normal_orders(?)}");
			        		con.pst.setString(1, nmsl.getOrderId());
			        		con.pst.execute();		
					    }
					    nslt.ViewTableOrders(nmsl.getId(), "Take-out");
						JOptionPane.showMessageDialog(null, "Successfully Update Orders");

						nsl.dispose();
						WindowNormalBillingDinein ab = new WindowNormalBillingDinein(nmsl.getId(), nmsl.getType(), nmsl.getStatus(), nmsl.getName());
						ab.jf.setModal(true);
						ab.jf.setVisible(true);
						
						con.pst.close();
					    con.con.close();
				    } catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
			}
		}//end of class of adding takeout meal
		

//	//class of adding meal
//		class NormalReserveAddMeal implements ActionListener{
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				if(nor.btnSettle.getText().equalsIgnoreCase("Add")){
//					DefaultTableModel dtm = (DefaultTableModel) nor.tblOrders.getModel();
//				    int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
//				    Object[][] tableData = new Object[nRow][nCol];
//		        	try {
//		        		nmrsl = nor.getFieldData();
//		        		con = new Connect();
//		        		con.pst = con.con.prepareCall("{call insert_Normal_order(?,?,?,?)}");
//		        		con.pst.setString(1, nmrsl.getOrderId());
//		        		con.pst.setString(2, nmrsl.getId());
//		        		con.pst.setString(3, nmrsl.getType());
//		        		con.pst.setDouble(4, nmrsl.getTotal());
//		        		con.pst.execute();			
//					    for (int i = 0 ; i < nRow ; i++){
//					        for (int j = 0 ; j < nCol ; j++){
//					            tableData[i][j] = dtm.getValueAt(i,j);
//					        }
//							con.pst = con.con.prepareCall("{call insert_reserve_Normal_order(?,?,?,?,?,?)}");
//							con.pst.setString(1, nmrsl.getOrderId());
//							con.pst.setString(2, tableData[i][0].toString());
//							con.pst.setString(3, tableData[i][1].toString());
//							con.pst.setString(4, tableData[i][2].toString());
//							con.pst.setString(5, tableData[i][3].toString());
//							con.pst.setString(6, tableData[i][4].toString());
//							con.pst.execute();						
//					    }
//					    nsrt.ViewTableOrders(nmrsl.getId());
//						JOptionPane.showMessageDialog(null, "Successfully Add Orders");
//
//						WindowNormalBillingReserve ab = new WindowNormalBillingReserve(nmrsl.getId(), nmrsl.getType(), "Dine-in", nmrsl.getName());
//						ab.jf.setVisible(true);
//						ab.pnlBills.repaint();
//						ab.pnlBills.revalidate();
//						nsl.dispose();
//						
//						con.pst.close();
//					    con.con.close();
//				    } catch (SQLException e) {
//						e.printStackTrace();
//					}
//					
//				}else if(nor.btnSettle.getText().equalsIgnoreCase("Update")){
//					DefaultTableModel dtm = (DefaultTableModel) nor.tblOrders.getModel();
//				    int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
//				    Object[][] tableData = new Object[nRow][nCol];
//		        	try {
//		        		nmrsl = nor.getFieldData();
//		        		con = new Connect();
//		        		con.pst = con.con.prepareCall("{call update_Normal_order(?,?,?,?)}");
//		        		con.pst.setString(1, nmrsl.getOrderId());
//		        		con.pst.setString(2, nmrsl.getId());
//		        		con.pst.setString(3, nmrsl.getType());
//		        		con.pst.setDouble(4, nmrsl.getTotal());
//		        		con.pst.execute();			
//		        		con.pst = con.con.prepareCall("{call delete_reserve_Normal_orders(?)}");
//		        		con.pst.setString(1, nmrsl.getOrderId());
//		        		con.pst.execute();		
//					    for (int i = 0 ; i < nRow ; i++){
//					        for (int j = 0 ; j < nCol ; j++){
//					            tableData[i][j] = dtm.getValueAt(i,j);
//					        }
//							con.pst = con.con.prepareCall("{call update_reserve_Normal_order(?,?,?,?,?,?)}");
//			        		con.pst.setString(1, nmrsl.getOrderId());
//							con.pst.setString(2, tableData[i][0].toString());
//							con.pst.setString(3, tableData[i][1].toString());
//							con.pst.setString(4, tableData[i][2].toString());
//							con.pst.setString(5, tableData[i][3].toString());
//							con.pst.setString(6, tableData[i][4].toString());
//							con.pst.execute();				
//					    }
//					    if(nRow == 0){
//			        		con.pst = con.con.prepareCall("{call delete_Normal_orders(?)}");
//			        		con.pst.setString(1, nmrsl.getOrderId());
//			        		con.pst.execute();		
//					    }
//					    nsrt.ViewTableOrders(nmrsl.getId());
//						JOptionPane.showMessageDialog(null, "Successfully Update Orders");
//
//						WindowNormalBillingReserve ab = new WindowNormalBillingReserve(nmrsl.getId(), nmrsl.getType(), "Dine-in", nmrsl.getName());
//						ab.jf.setVisible(true);
//						ab.pnlBills.repaint();
//						ab.pnlBills.revalidate();
//						nsl.dispose();
//						
//						con.pst.close();
//					    con.con.close();
//				    } catch (SQLException e) {
//						e.printStackTrace();
//					}
//				}
//				
//			}
//		}//end of class of adding meal
		
	//class of adding meal
		class EventReserveAddMeal implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(eor.btnSettle.getText().equalsIgnoreCase("Add")){
					DefaultTableModel dtm = (DefaultTableModel) eor.tblOrders.getModel();
				    int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
				    Object[][] tableData = new Object[nRow][nCol];
		        	try {
		        		emrsl = eor.getFieldData();
		        		con = new Connect();
		        		con.pst = con.con.prepareCall("{call insert_event_order(?,?,?,?)}");
		        		con.pst.setString(1, emrsl.getOrderId());
		        		con.pst.setString(2, emrsl.getId());
		        		con.pst.setString(3, emrsl.getType());
		        		con.pst.setDouble(4, emrsl.getTotal());
		        		con.pst.execute();			
					    for (int i = 0 ; i < nRow ; i++){
					        for (int j = 0 ; j < nCol ; j++){
					            tableData[i][j] = dtm.getValueAt(i,j);
					        }
							con.pst = con.con.prepareCall("{call insert_reserve_event_order(?,?,?,?,?,?)}");
							con.pst.setString(1, emrsl.getOrderId());
							con.pst.setString(2, tableData[i][0].toString());
							con.pst.setString(3, tableData[i][1].toString());
							con.pst.setString(4, tableData[i][2].toString());
							con.pst.setString(5, tableData[i][3].toString());
							con.pst.setString(6, tableData[i][4].toString());
							con.pst.execute();						
					    }
					    esrt.ViewTableOrders(emrsl.getId());
						JOptionPane.showMessageDialog(null, "Successfully Add Orders");

						eor.dispose();
						WindowEventBillingReserve ab = new WindowEventBillingReserve(emrsl.getId(), emrsl.getType(), "Dine-in", emrsl.getName());
						ab.jf.setModal(true);
						ab.jf.setVisible(true);
						
						con.pst.close();
					    con.con.close();
				    } catch (SQLException e) {
						e.printStackTrace();
					}
					
				}else if(eor.btnSettle.getText().equalsIgnoreCase("Update")){
					DefaultTableModel dtm = (DefaultTableModel) eor.tblOrders.getModel();
				    int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
				    Object[][] tableData = new Object[nRow][nCol];
		        	try {
		        		emrsl = eor.getFieldData();
		        		con = new Connect();
		        		con.pst = con.con.prepareCall("{call update_event_order(?,?,?,?)}");
		        		con.pst.setString(1, emrsl.getOrderId());
		        		con.pst.setString(2, emrsl.getId());
		        		con.pst.setString(3, emrsl.getType());
		        		con.pst.setDouble(4, emrsl.getTotal());
		        		con.pst.execute();			
		        		con.pst = con.con.prepareCall("{call delete_reserve_event_orders(?)}");
		        		con.pst.setString(1, emrsl.getOrderId());
		        		con.pst.execute();		
					    for (int i = 0 ; i < nRow ; i++){
					        for (int j = 0 ; j < nCol ; j++){
					            tableData[i][j] = dtm.getValueAt(i,j);
					        }
							con.pst = con.con.prepareCall("{call update_reserve_event_order(?,?,?,?,?,?)}");
			        		con.pst.setString(1, emrsl.getOrderId());
							con.pst.setString(2, tableData[i][0].toString());
							con.pst.setString(3, tableData[i][1].toString());
							con.pst.setString(4, tableData[i][2].toString());
							con.pst.setString(5, tableData[i][3].toString());
							con.pst.setString(6, tableData[i][4].toString());
							con.pst.execute();				
					    }
					    if(nRow == 0){
			        		con.pst = con.con.prepareCall("{call delete_event_orders(?)}");
			        		con.pst.setString(1, emrsl.getOrderId());
			        		con.pst.execute();		
					    }
					    esrt.ViewTableOrders(emrsl.getId());
						JOptionPane.showMessageDialog(null, "Successfully Update Orders");
						
						eor.dispose();
						WindowEventBillingReserve ab = new WindowEventBillingReserve(emrsl.getId(), emrsl.getType(), "Dine-in", emrsl.getName());
						ab.jf.setModal(true);
						ab.jf.setVisible(true);
						
						con.pst.close();
					    con.con.close();
				    } catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
			}
		}//end of class of adding meal
		
//	//class of adding room
//		class AddRoomPlan implements ActionListener{
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//
//				try {
////							if(rpf.btnAdd.getName().equalsIgnoreCase("Add")){
////								if(validateName(mfp.getName()) == false && validateEmptyString(mfp.getName()) == false){	
//							int totalcapacity = 0;
//							boolean check  = false;
//							boolean found = false;
//							mfp = rpf.getFloorField();
//							if(mfp.getTotalCapacity() <= mfp.getTotalFloorCapacity()){
//								con = new Connect();
//								con.pst = con.con.prepareCall("{call select_capacity_floor()}");
//								con.pst.execute();
//								con.rs = con.pst.getResultSet();
//								while(con.rs.next()){
//									found = true;
//									totalcapacity = con.rs.getInt("plan_total_capacity");	
//									if(totalcapacity >=  mfp.getTotalFloorCapacity()){
//										check = true;
//									}else{
//										check = false;
//										break;
//									}
//								}
//								if(!found){
//									check = true;
//								}
//								if(check == true){
//									con = new Connect();
//									con.pst = con.con.prepareCall("{call update_floor_plan(?,?,?,?)}");
//									con.pst.setString(1, mfp.getId());
//									con.pst.setInt(2, mfp.getMain());
//									con.pst.setInt(3, mfp.getFunction());
//									con.pst.setInt(4, mfp.getRemaining());
//									con.pst.execute();
//									if(rpf.chckbxStandardRoomCapacity.isSelected()){
//										msr = rpf.getStandardField();
//										con = new Connect();
//										con.pst = con.con.prepareCall("{call insert_normal_room_plan(?,?,?,?,?,?,?,?,?,?)}");
//										con.pst.setString(1, msr.getId());
//										con.pst.setString(2, msr.getFloorId());
//										con.pst.setString(3, msr.getName());
//										con.pst.setString(4, msr.getDesc());
//										con.pst.setInt(5, msr.getCapacity());
//										con.pst.setInt(6, msr.getTable());
//										con.pst.setInt(7, msr.getChair());
//										con.pst.setInt(8, msr.getTotalCapacity());
//										con.pst.setString(9, msr.getType());
//										con.pst.setString(10, msr.getStatus());
//										con.pst.execute(); 
//										String standardid = null;
//										for(int i = 0 ; i < msr.getTable(); i++){
//											con = new Connect();
//											CounterTable ct = new CounterTable();
//											standardid = ct.getAccountNumber(msr.getId() + "-", i);
//											if(!standardid.isEmpty()){
//												con.pst = con.con.prepareCall("{call insert_desks(?,?,?,?)}");
//												con.pst.setString(1, msr.getId());
//												con.pst.setString(2, standardid);
//												con.pst.setInt(3, msr.getChair());
//												con.pst.setString(4, "ACTIVE");
//												con.pst.execute();
//											}
//										}
//									}
//									if(rpf.chckbxFunctionRoomCapacity.isSelected()){
//										mfr = rpf.getFunctionField();
//										con = new Connect();
//										con.pst = con.con.prepareCall("{call insert_event_room_plan(?,?,?,?,?,?,?,?,?,?)}");
//										con.pst.setString(1, mfr.getId());
//										con.pst.setString(2, mfr.getFloorId());
//										con.pst.setString(3, mfr.getName());
//										con.pst.setString(4, mfr.getDesc());
//										con.pst.setInt(5, mfr.getCapacity());
//										con.pst.setInt(6, mfr.getTable());
//										con.pst.setInt(7, mfr.getChair());
//										con.pst.setInt(8, mfr.getTotalCapacity());
//										con.pst.setString(9, mfr.getType());
//										con.pst.setString(10, mfr.getStatus());
//										con.pst.execute(); 	
//										String functionid = null;
//										for(int i = 0 ; i < mfr.getTable(); i++){
//											con = new Connect();
//											CounterTable ct = new CounterTable();
//											functionid = ct.getAccountNumber(mfr.getId() + "-", i);
//											if(!functionid.isEmpty()){
//												con.pst = con.con.prepareCall("{call insert_desks(?,?,?,?)}");
//												con.pst.setString(1, mfr.getId());
//												con.pst.setString(2, functionid);
//												con.pst.setInt(3, mfr.getChair());
//												con.pst.setString(4, "ACTIVE");
//												con.pst.execute();
//											}
//										}
//									}									    
//									
//									Dashboard.fpt.ViewFloorPlan();
//									try {
//										con = new Connect();
//										con.pst = con.con.prepareCall("{call select_table_room(?)}");
//										con.pst.setString(1, mfp.getId());
//										con.pst.execute();
//										con.rs = con.pst.getResultSet();
//										FloorPlan.tblRooms.setModel(DbUtils.resultSetToTableModel(con.rs));
//										con.rs.close();
//										con.pst.close();
//										con.con.close();
//									} catch (SQLException e1) {
//										e1.printStackTrace();
//									}
//									rpf.dispose();
//									con.pst.close();
//									con.con.close();	
//									JOptionPane.showMessageDialog(null,"Floor " + mfp.getId() + " Added");
//								}else{
//									JOptionPane.showMessageDialog(null, "The size or capacity must be smaller or equal than the previous floor.");
//								}
//							}else{
//								JOptionPane.showMessageDialog(null, "You can only accumulate " + mfp.getTotalFloorCapacity() + " remaining capacity\nTotal accumulated capacity: " + mfp.getTotalCapacity());								
//							}
////							}
////							}else if(validateEmptyString(mfp.getName()) == true){
////								JOptionPane.showMessageDialog(null, "Empty field");						
////							}else{
////								JOptionPane.showMessageDialog(null, "Invalid character");
////							}
//				} catch (SQLException e1) {
//					e1.printStackTrace();
//				}
//			}
//		}//end of class of adding room
		
//		//class of adding room
//		class AddFloorPlan implements ActionListener{
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				try {
////							if(rpf.btnAdd.getName().equalsIgnoreCase("Add")){
////								if(validateName(mfp.getName()) == false && validateEmptyString(mfp.getName()) == false){	
//							int totalcapacity = 0;
//							boolean check  = false;
//							boolean found = false;
//							mfp = fpf.getFloorField();
//							if(mfp.getTotalCapacity() <= mfp.getTotalFloorCapacity()){
//								con = new Connect();
//								con.pst = con.con.prepareCall("{call select_capacity_floor()}");
//								con.pst.execute();
//								con.rs = con.pst.getResultSet();
//								while(con.rs.next()){
//									found = true;
//									totalcapacity = con.rs.getInt("plan_total_capacity");	
//									if(totalcapacity >=  mfp.getTotalFloorCapacity()){
//										check = true;
//									}else{
//										check = false;
//										break;
//									}
//								}
//								if(!found){
//									check = true;
//								}
//								if(check == true){
//									con = new Connect();
//									con.pst = con.con.prepareCall("{call insert_floor_plan(?,?,?,?,?,?)}");
//									con.pst.setString(1, mfp.getId());
//									con.pst.setInt(2, mfp.getMainRoom());
//									con.pst.setInt(3, mfp.getFunctionRoom());
//									con.pst.setInt(4, mfp.getTotalFloorCapacity());
//									con.pst.setInt(5, mfp.getRemainingCapacity());
//									con.pst.setString(6, mfp.getStatus());
//									con.pst.execute();
//									if(fpf.chckbxStandardRoomCapacity.isSelected()){
//										msr = fpf.getStandardField();
//										con = new Connect();
//										con.pst = con.con.prepareCall("{call insert_normal_room_plan(?,?,?,?,?,?,?,?,?,?)}");
//										con.pst.setString(1, msr.getId());
//										con.pst.setString(2, msr.getFloorId());
//										con.pst.setString(3, msr.getName());
//										con.pst.setString(4, msr.getDesc());
//										con.pst.setInt(5, msr.getCapacity());
//										con.pst.setInt(6, msr.getTable());
//										con.pst.setInt(7, msr.getChair());
//										con.pst.setInt(8, msr.getTotalCapacity());
//										con.pst.setString(9, msr.getType());
//										con.pst.setString(10, msr.getStatus());
//										con.pst.execute();
//										String standardid = null;
//										for(int i = 0 ; i < msr.getTable(); i++){
//											con = new Connect();
//											CounterTable ct = new CounterTable();
//											standardid = ct.getAccountNumber(msr.getId() + "-", i);
//											if(!standardid.isEmpty()){
//												con.pst = con.con.prepareCall("{call insert_desks(?,?,?,?)}");
//												con.pst.setString(1, msr.getId());
//												con.pst.setString(2, standardid);
//												con.pst.setInt(3, msr.getChair());
//												con.pst.setString(4, "ACTIVE");
//												con.pst.execute();
//											}
//										}
//									}
//									if(fpf.chckbxFunctionRoomCapacity.isSelected()){
//										mfr = fpf.getFunctionField();
//										con = new Connect();
//										con.pst = con.con.prepareCall("{call insert_event_room_plan(?,?,?,?,?,?,?,?,?,?)}");
//										con.pst.setString(1, mfr.getId());
//										con.pst.setString(2, mfr.getFloorId());
//										con.pst.setString(3, mfr.getName());
//										con.pst.setString(4, mfr.getDesc());
//										con.pst.setInt(5, mfr.getCapacity());
//										con.pst.setInt(6, mfr.getTable());
//										con.pst.setInt(7, mfr.getChair());
//										con.pst.setInt(8, mfr.getTotalCapacity());
//										con.pst.setString(9, mfr.getType());
//										con.pst.setString(10, mfr.getStatus());
//										con.pst.execute(); 	
//										String functionid = null;
//										for(int i = 0 ; i < mfr.getTable(); i++){
//											con = new Connect();
//											CounterTable ct = new CounterTable();
//											functionid = ct.getAccountNumber(mfr.getId() + "-", i);
//											if(!functionid.isEmpty()){
//												con.pst = con.con.prepareCall("{call insert_desks(?,?,?,?)}");
//												con.pst.setString(1, mfr.getId());
//												con.pst.setString(2, functionid);
//												con.pst.setInt(3, mfr.getChair());
//												con.pst.setString(4, "ACTIVE");
//												con.pst.execute();
//											}
//										}
//									}
//									
//									Dashboard.fpt.ViewFloorPlan();
//									try {
//										con = new Connect();
//										con.pst = con.con.prepareCall("{call select_table_room(?)}");
//										con.pst.setString(1, mfp.getId());
//										con.pst.execute();
//										con.rs = con.pst.getResultSet();
//										FloorPlan.tblRooms.setModel(DbUtils.resultSetToTableModel(con.rs));
//										con.rs.close();
//										con.pst.close();
//										con.con.close();
//									} catch (SQLException e1) {
//										e1.printStackTrace();
//									}
//									fpt.CounterFloorPlan();
//									fpf.dispose();
//									con.pst.close();
//									con.con.close();	
//									JOptionPane.showMessageDialog(null,"Floor " + mfp.getId() + " Added");
//								}else{
//									JOptionPane.showMessageDialog(null, "The size or capacity must be smaller or equal than the previous floor.");
//								}
//							}else{
//								JOptionPane.showMessageDialog(null, "You can only accumulate " + mfp.getTotalFloorCapacity() + " remaining capacity\nTotal accumulated capacity: " + mfp.getTotalCapacity());					
//							}
////							}
////							}else if(validateEmptyString(mfp.getName()) == true){
////								JOptionPane.showMessageDialog(null, "Empty field");						
////							}else{
////								JOptionPane.showMessageDialog(null, "Invalid character");
////							}
//				} catch (SQLException e1) {
//					e1.printStackTrace();
//				}
//			}
//		}//end of class of adding room
		
//		//class of adding tables
//		class ManageTables implements ActionListener{
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				msr = tf.getStandardField()	;
//				if(msr.getCapacity() >= msr.getTotalCapacity()){
//					try {
//						con = new Connect();
//						con.pst = con.con.prepareCall("{call remove_desks(?)}");
//						con.pst.setString(1, msr.getId());
//						con.pst.execute();
//						  thr=new Thread(new Runnable(){
//							  public void run()
//							  {
//									tf.btnSave.setEnabled(false);
//									tf.btnCancel.setEnabled(false);
//							      try{
//										String standardid = null;
//										for(int i = 0; i < msr.getTable(); i++){
//											tf.progressBar.setString("Inserting " + standardid + " Progress: " + i * 100 / msr.getTable() + "%");
//										    tf.progressBar.setValue(i * 100 / msr.getTable());
//											con = new Connect();
//											CounterTable ct = new CounterTable();
//											standardid = ct.getAccountNumber(msr.getId() + "-", i);
//											if(!standardid.isEmpty()){
//												con.pst = con.con.prepareCall("{call insert_desks(?,?,?,?)}");
//												con.pst.setString(1, msr.getId());
//												con.pst.setString(2, standardid);
//												con.pst.setInt(3, msr.getChair());
//												con.pst.setString(4, "ACTIVE");
//												con.pst.execute();
//											}
//										    if(i == msr.getTable()-1){
//											    tf.progressBar.setString("Complete.");
//											    tf.progressBar.setValue(100);
//												con = new Connect();
//												con.pst = con.con.prepareCall("{call update_desks(?,?,?,?)}");
//												con.pst.setString(1, msr.getId());
//												con.pst.setInt(2, msr.getTable());
//												con.pst.setInt(3, msr.getChair());
//												con.pst.setInt(4, msr.getTotalCapacity());
//												con.pst.execute();
//
//												Dashboard.dt.ViewRooms();
//												try {
//													con = new Connect();
//													con.pst = con.con.prepareCall("{call select_table_desk(?)}");
//													con.pst.setString(1, msr.getId());
//													con.pst.execute();
//													con.rs = con.pst.getResultSet();
//													Tables.tblTables.setModel(DbUtils.resultSetToTableModel(con.rs));
//													con.rs.close();
//													con.pst.close();
//													con.con.close();
//												} catch (SQLException e1) {
//													e1.printStackTrace();
//												}
//												con.pst.close();
//												con.con.close();
//												JOptionPane.showMessageDialog(null, msr.getId() + " Has been updated.\nTable: " +msr.getTable() +"\nChair: " + msr.getChair() );
//												tf.dispose();
//										    }
//										}
//										Thread.sleep(0);
//								   }catch(Exception e){
//									   
//								   }
//							   }
//							 }); thr.start();
//					} catch (SQLException e) {
//						e.printStackTrace();
//					}	
//				}else{
//					JOptionPane.showMessageDialog(null, "You can only accumulate " + msr.getCapacity() + " remaining capacity\nTotal accumulated capacity: " + msr.getTotalCapacity());								
//				}
//			}
//		}//end of class of adding tables
		

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
