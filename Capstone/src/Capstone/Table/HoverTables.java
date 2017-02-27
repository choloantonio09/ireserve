//package Capstone.Table;
//
//import java.sql.SQLException;
//
//import net.proteanit.sql.DbUtils;
//import Capstone.Database.Connect;
//import Capstone.Model.CalendarModel;
//import Capstone.Model.ModelDesks;
//import Capstone.View.HoverTable;
//import Capstone.View.POS;
//import Capstone.View.Reservation;
//
//public class HoverTables {
//	Connect con;
//	POS p;
//	Reservation r;
//	CalendarModel cm;
//	ModelDesks md;
//	public HoverTables(POS p){
//		this.p = p;
//	}
//	public HoverTables(Reservation r){
//		this.r = r;
//	}
//	public void ViewTableDetails(int j){
//		try {
//			md =  new ModelDesks();
//			md = p.getFieldData(j);
//			cm = new CalendarModel();
//			cm = p.getCalendarData();
//			con = new Connect();
//			con.pst = con.con.prepareCall("{call select_waiting_table_info(?,?)}");
//			con.pst.setString(1, md.getId());
//			con.pst.setString(2, cm.getDate());
//			con.pst.execute();
//			con.rs = con.pst.getResultSet();
//			HoverTable.tblTables.setModel(DbUtils.resultSetToTableModel(con.rs));
//			HoverTable.tblTables.removeColumn(HoverTable.tblTables.getColumnModel().getColumn(6));
//			con.rs.close();
//			con.pst.close();
//			con.con.close();
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
//	}
//
//	public void ViewTableDetailsReserve(int j){
//		try {
//			md =  new ModelDesks();
////			md = r.getFieldData(j);
//			cm = new CalendarModel();
////			cm = r.getCalendarData();
//			con = new Connect();
//			con.pst = con.con.prepareCall("{call select_table_info(?,?)}");
//			con.pst.setString(1, md.getId());
//			con.pst.setString(2, cm.getDate());
//			con.pst.execute();
//			con.rs = con.pst.getResultSet();
//			HoverTable.tblTables.setModel(DbUtils.resultSetToTableModel(con.rs));
//			HoverTable.tblTables.removeColumn(HoverTable.tblTables.getColumnModel().getColumn(6));
//			con.rs.close();
//			con.pst.close();
//			con.con.close();
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
//	}	
//}
