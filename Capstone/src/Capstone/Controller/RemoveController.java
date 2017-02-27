package Capstone.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Capstone.Database.Connect;
import Capstone.Model.ModelEventPackage;
import Capstone.Model.ModelNormalPackage;
import Capstone.Model.ModelSales;
import Capstone.Model.ModelSalesEventOrder;
import Capstone.Model.ModelSalesNormalOrder;
import Capstone.Model.ModelSize;
import Capstone.Table.EventPackageTable;
import Capstone.Table.EventSalesReserveTable;
import Capstone.Table.NormalPackageTable;
import Capstone.Table.NormalSalesTable;
import Capstone.Table.SizeTable;
import Capstone.View.EventOrderReserve;
import Capstone.View.NormalSales;
import Capstone.View.Packages;
import Capstone.View.Sizes;

public class RemoveController {
	
	Connect con;
	
	Sizes s;
	ModelSize ms;
	SizeTable st;

	Packages p;
	ModelNormalPackage mnp;
	ModelEventPackage mep;
	EventPackageTable ept;
	NormalPackageTable npt;

	NormalSales nsl;
	ModelSales nmsl;
	NormalSalesTable nslt;
	
	ModelSalesNormalOrder nmrsl;

	EventOrderReserve eor;
	ModelSalesEventOrder emrsl;
	EventSalesReserveTable esrt;
	
	//Remove Sizes
	public RemoveController(Sizes s, ModelSize ms, SizeTable st){
		this.s = s;
		this.ms = ms;
		this.st = st;
		this.s.remove(new RemoveSizes());
	}//end of Remove Sizes

	//Remove Package
	public RemoveController(Packages p, ModelNormalPackage mnp,  NormalPackageTable npt){
		this.p = p;
		this.mnp = mnp;
		this.npt = npt;
		this.mep = mep;
		this.ept = ept;
		this.p.normalremove(new RemoveNormalMeal());
		this.p.normaldelete(new DeleteNormalPackage());
		
	}//end of Remove Package

	//Remove Order
	public RemoveController(NormalSales nsl, ModelSales msl, NormalSalesTable nslt){
		this.nsl = nsl;
		this.nmsl = msl;
		this.nslt = nslt;
		this.nsl.remove(new RemoveNormalOrderMeal());
		
	}//end of Remove Order

//	//Remove Order
//	public RemoveController(NormalOrderReserve nor, ModelSalesNormalOrder nmrsl, NormalSalesReserveTable nsrt){
//		this.nor = nor;
//		this.nmrsl = nmrsl;
//		this.nsrt = nsrt;
//		this.nor.remove(new RemoveNormalReserveOrderMeal());
//		
//	}//end of Remove Order


	//Remove Order
	public RemoveController(EventOrderReserve eor, ModelSalesEventOrder emrsl, EventSalesReserveTable esrt){
		this.eor = eor;
		this.emrsl = emrsl;
		this.esrt = esrt;
		this.eor.remove(new RemoveEventReserveOrderMeal());
		
	}//end of Remove Order
	
	//class of removing sizes
	class RemoveSizes implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				ms = s.getSizesField();
				con = new Connect();
				con.pst = con.con.prepareCall("{call remove_sizes(?)}");
				con.pst.setString(1, ms.getName());
				con.pst.execute();
				st.ViewSize();
				s.tfSizeName.setText(null);
				s.btnRemove.setEnabled(false);
				con.pst.close();
				con.con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public class RemoveNormalMeal implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			Double total = 0.00;
			Double totalTemp;
			
			DefaultTableModel model = (DefaultTableModel) p.tblNormalFoods.getModel();
			   int[] rows = p.tblNormalFoods.getSelectedRows();
			   for(int i=0;i<rows.length;i++){
					totalTemp = Double.parseDouble(p.tfNormalTotal.getText());
					Double price = Double.parseDouble(p.tblNormalFoods.getModel().getValueAt(rows[i]-i, 4).toString()); 
					total = totalTemp - price;
			     model.removeRow(rows[i]-i);
					p.tfNormalTotal.setText(String.valueOf(total));
					p.tfOriginalPrice.setText(String.valueOf(total));
					p.originalprice = total;
					p.btnNormalRemove.setEnabled(false);
					p.RetrieveOriginalTotal();
					p.CalculateDiscountTotal();
			   }
			   
		}		
	}
//	public class RemoveEventMeal implements ActionListener{
//		public void actionPerformed(ActionEvent arg0) {
//			Double total = 0.00;
//			Double totalTemp;
//			
//			DefaultTableModel model = (DefaultTableModel) p.tblEventFoods.getModel();
//			   int[] rows = p.tblEventFoods.getSelectedRows();
//			   for(int i=0;i<rows.length;i++){
//					totalTemp = Double.parseDouble(p.tfEventTotal.getText());
//					Double price = Double.parseDouble(p.tblEventFoods.getModel().getValueAt(rows[i]-i, 4).toString()); 
//					total = totalTemp - price;
//			     model.removeRow(rows[i]-i);
//					p.tfEventTotal.setText(String.valueOf(total));
//					p.btnEventRemove.setEnabled(false);
//			   }
//			   
//		}		
//	}

	public class DeleteNormalPackage implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
    		try {
        		mnp = p.getModelNormalPackage();
				con = new Connect();
	    		con.pst = con.con.prepareCall("{call delete_normal_food_packages(?)}");
				con.pst.setString(1, mnp.getId());
	    		con.pst.execute();	
	    		con.pst = con.con.prepareCall("{call delete_normal_packages(?)}");
				con.pst.setString(1, mnp.getId());
	    		con.pst.execute();	
				con.pst.close();
			    con.con.close();
			    p.RefreshPackage();
			    JOptionPane.showMessageDialog(null, "Normal Package Deleted");
				DefaultTableModel dm = (DefaultTableModel) p.tblNormalFoods.getModel();
				int rowCount = dm.getRowCount();
				//Remove rows one by one from the end of the table
				for (int i = rowCount - 1; i >= 0; i--) {
				    dm.removeRow(i);
				}				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}		
	}
//
//	public class DeleteEventPackage implements ActionListener{
//		public void actionPerformed(ActionEvent arg0) {
//			try {
//        		mep = p.getModelEventPackage();
//				con = new Connect();
//	    		con.pst = con.con.prepareCall("{call delete_event_food_packages(?)}");
//				con.pst.setString(1, mep.getId());
//	    		con.pst.execute();	
//	    		con.pst = con.con.prepareCall("{call delete_event_packages(?)}");
//				con.pst.setString(1, mep.getId());
//	    		con.pst.execute();	
//				con.pst.close();
//			    con.con.close();
//			    ept.ViewEventPackage();
//
//				p.tfEventTotal.setText("0.0");
//				p.spnEventServingSize.setValue(1);
//				p.tfEventPackageName.setText("");
//				p.btnEventCreate.setText("CREATE");
//				p.btnEventCreate.setEnabled(true);
//				p.btnEventDelete.setEnabled(false);
//				p.btnEventRemove.setEnabled(false);
//				DefaultTableModel dm = (DefaultTableModel) p.tblEventFoods.getModel();
//				int rowCount = dm.getRowCount();
//				//Remove rows one by one from the end of the table
//				for (int i = rowCount - 1; i >= 0; i--) {
//				    dm.removeRow(i);
//				}					
//			    JOptionPane.showMessageDialog(null, "Event Package Deleted");
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}		
//	}
//	
	public class RemoveNormalOrderMeal implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			Double total = 0.00;
			Double totalTemp;
			
			DefaultTableModel model = (DefaultTableModel) nsl.tblOrders.getModel();
			   int[] rows = nsl.tblOrders.getSelectedRows();
			   for(int i=0;i<rows.length;i++){
					totalTemp = Double.parseDouble(nsl.tfTotal.getText());
					Double price = Double.parseDouble(nsl.tblOrders.getModel().getValueAt(rows[i]-i, 4).toString()); 
					total = totalTemp - price;
					model.removeRow(rows[i]-i);
					nsl.tfTotal.setText(String.valueOf(total));
					nsl.btnCancel.doClick();
			   }
			   
		}		
	}
	public class RemoveEventReserveOrderMeal implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			Double total = 0.00;
			Double totalTemp;
			
			DefaultTableModel model = (DefaultTableModel) eor.tblOrders.getModel();
			   int[] rows = eor.tblOrders.getSelectedRows();
			   for(int i=0;i<rows.length;i++){
					totalTemp = Double.parseDouble(eor.tfTotal.getText());
					Double price = Double.parseDouble(eor.tblOrders.getModel().getValueAt(rows[i]-i, 4).toString()); 
					total = totalTemp - price;
					model.removeRow(rows[i]-i);
					eor.tfTotal.setText(String.valueOf(total));
					eor.btnCancel.doClick();
			   }
			   
		}		
	}

//	public class RemoveNormalReserveOrderMeal implements ActionListener{
//		public void actionPerformed(ActionEvent arg0) {
//			Double total = 0.00;
//			Double totalTemp;
//			
//			DefaultTableModel model = (DefaultTableModel) nor.tblOrders.getModel();
//			   int[] rows = nor.tblOrders.getSelectedRows();
//			   for(int i=0;i<rows.length;i++){
//					totalTemp = Double.parseDouble(nor.tfTotal.getText());
//					Double price = Double.parseDouble(nor.tblOrders.getModel().getValueAt(rows[i]-i, 4).toString()); 
//					total = totalTemp - price;
//					model.removeRow(rows[i]-i);
//					nor.tfTotal.setText(String.valueOf(total));
//					nor.btnCancel.doClick();
//			   }
//			   
//		}		
//	}
}
