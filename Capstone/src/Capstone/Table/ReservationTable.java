package Capstone.Table;

import java.sql.SQLException;

import Capstone.Database.Connect;
import Capstone.Model.CalendarModel;
import Capstone.Model.ModelDesks;
import Capstone.View.POSDesign;
import net.proteanit.sql.DbUtils;

public class ReservationTable {
	static Connect con;
	static POSDesign r;
	static CalendarModel cnm;
	static CalendarModel cem;
	static CalendarModel cd;
	ModelDesks md;
	public ReservationTable(POSDesign r){
		this.r = r;
		ViewNormalReservation();
		ViewEventReservation();
		ViewDinein();
	}
	public static void ViewNormalReservation(){
		try {
			cnm = new CalendarModel();
			cnm = r.getCalendarData();
			con = new Connect();
			con.pst = con.con.prepareCall("{call select_table_normal_reserve(?,?)}");
			con.pst.setString(1, cnm.getDate());
			con.pst.setString(2, cnm.getId());
			con.pst.execute();
			con.rs = con.pst.getResultSet();
			r.tblNormalReserve.setModel(DbUtils.resultSetToTableModel(con.rs));
			con.rs.close();
			con.pst.close();
			con.con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}	

	public static void ViewEventReservation(){
		try {
			cem = new CalendarModel();
			cem = r.getCalendarData();
			con = new Connect();
			con.pst = con.con.prepareCall("{call select_table_event_reserve(?,?)}");
			con.pst.setString(1, cem.getDate());
			con.pst.setString(2, cem.getId());
			con.pst.execute();
			con.rs = con.pst.getResultSet();
			r.tblEventReserve.setModel(DbUtils.resultSetToTableModel(con.rs));
			con.rs.close();
			con.pst.close();
			con.con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}	
	public static void ViewDinein(){
		try {
			cd = new CalendarModel();
			cd = r.getCalendarData();
			con = new Connect();
			con.pst = con.con.prepareCall("{call select_table_occupy(?,?,?)}");
			con.pst.setString(1, "");
			con.pst.setString(2, cd.getDate());
			con.pst.setString(3, cd.getId());
			con.pst.execute();
			con.rs = con.pst.getResultSet();
			r.tblDinein.setModel(DbUtils.resultSetToTableModel(con.rs));
			con.rs.close();
			con.pst.close();
			con.con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}	

//	public void ViewTableDetails(int j){
//		try {
//			md =  new ModelDesks();
//			md = r.getFieldData(j);
//			cm = new CalendarModel();
//			cm = r.getCalendarData();
//			con = new Connect();
//			con.pst = con.con.prepareCall("{call select_table_info(?,?)}");
//			con.pst.setString(1, md.getId());
//			con.pst.setString(2, cm.getDate());
//			con.pst.execute();
//			con.rs = con.pst.getResultSet();
//			r.tblReserve.setModel(DbUtils.resultSetToTableModel(con.rs));
//			r.tblReserve.removeColumn(r.tblReserve.getColumnModel().getColumn(6));
//			con.rs.close();
//			con.pst.close();
//			con.con.close();
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
//	}	
	
}
