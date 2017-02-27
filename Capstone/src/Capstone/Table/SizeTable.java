package Capstone.Table;
import java.sql.SQLException;

import net.proteanit.sql.DbUtils;
import Capstone.Database.Connect;
import Capstone.View.Sizes;

public class SizeTable {
	Connect con;
	Sizes s;
	public SizeTable(Sizes s){
		this.s = s;
		ViewSize();
	}
	public void ViewSize(){
		try {
			con = new Connect();
			con.pst = con.con.prepareCall("{call select_all_sizes()}");
			con.pst.execute();
			con.rs = con.pst.getResultSet();
			s.tblSizes.setModel(DbUtils.resultSetToTableModel(con.rs));
			con.rs.close();
			con.pst.close();
			con.con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
