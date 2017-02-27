package Capstone.Table;

import java.sql.SQLException;

import net.proteanit.sql.DbUtils;
import Capstone.Database.Connect;
import Capstone.Model.CalendarModel;
import Capstone.Model.ModelDesks;
import Capstone.Model.ModelSalesReport;
import Capstone.View.*;

public class ReportsTable {
	Connect con;
	Reports rp;

	ModelSalesReport cm;
	ModelDesks md;
	private String sum;
//	public ReportsTable(Reports rp){
//		this.rp = rp;
//		ViewSalesReport();
//		SumOfSalesReport();
//	}
//	public void ViewSalesReport(){
//		try {
//			cm = new ModelSalesReport();
//			cm = rp.getCalendarData();
//			con = new Connect();
//			con.pst = con.con.prepareCall("{call select_all_sales_report(?,?)}");
//			con.pst.setString(1, cm.getDateStart());
//			con.pst.setString(2, cm.getDateEnd());
//			con.pst.execute();
//			con.rs = con.pst.getResultSet();
//			rp.tblSalesReport.setModel(DbUtils.resultSetToTableModel(con.rs));
//			con.rs.close();
//			con.pst.close();
//			con.con.close();
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
//	}	
//
//	public void SumOfSalesReport(){
//		try {
//			cm = new ModelSalesReport();
//			cm = rp.getCalendarData();
//			con = new Connect();
//			con.pst = con.con.prepareCall("{call select_sales_report_sum(?,?)}");
//			con.pst.setString(1, cm.getDateStart());
//			con.pst.setString(2, cm.getDateEnd());
//			con.pst.execute();
//			con.rs = con.pst.getResultSet();
//			while(con.rs.next()){
//				sum = con.rs.getString("Overall");
//			}
//			rp.tfOverall.setText(sum);
//			con.rs.close();
//			con.pst.close();
//			con.con.close();
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
//	}	
}
