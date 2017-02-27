package Capstone.Table;

import java.sql.SQLException;






import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import net.proteanit.sql.DbUtils;
import Capstone.Database.Connect;
import Capstone.Model.CounterCategory;
import Capstone.View.Categories;
import Capstone.View.CategoriesForm;

public class CategoryTable {
	Connect con;
	Categories c;
	CategoriesForm cf;
	public CategoryTable(Categories c){
		this.c = c;
	}
	public CategoryTable(CategoriesForm cf){
		this.cf = cf;
	}
	public void ViewCategory(){
		try {
			con = new Connect();
			con.pst = con.con.prepareCall("{call select_all_categories()}");
			con.pst.execute();
			con.rs = con.pst.getResultSet();
			c.tblCategories.setModel(DbUtils.resultSetToTableModel(con.rs));
			con.rs.close();
			con.pst.close();
			con.con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	public void CounterCategory(){
		CounterCategory cc = new CounterCategory();
		cc.getAccountNumber();
		if(!cc.getAccountNumber().isEmpty()){
			cf.tfId.setText(cc.getAccountNumber());
		}
	}
}
