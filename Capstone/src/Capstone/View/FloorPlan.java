package Capstone.View;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import Capstone.Model.ModelFloor;
import Capstone.Table.RoomTable;
import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;

public class FloorPlan extends JPanel {
	private JPanel pnlFloors;
	private JTabbedPane tpFloors;
	protected RoomTable rpt;
	protected ModelFloor mfp;
	private JPanel pnlSections;
	private JPanel pnlRooms;
	private JPanel pnlBuilding;
	private JPanel pnlTables;
	private JPanel pnlTableAssignment;
	private JPanel pnlTableType;

	public FloorPlan() {
		setBounds(100, 100, 976, 453);
		setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		tpFloors = new JTabbedPane(JTabbedPane.TOP);
		tpFloors.setUI(new AquaTabbedPaneUI());
		tpFloors.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(tpFloors, "cell 0 0,grow");
		
		pnlBuilding = new JPanel();
		tpFloors.addTab("Building", null, pnlBuilding, null);
		pnlBuilding.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		pnlFloors = new JPanel();
		tpFloors.addTab("Floor", null, pnlFloors, null);
		pnlFloors.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		pnlRooms = new JPanel();
		tpFloors.addTab("Room", null, pnlRooms, null);
		pnlRooms.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
//		pnlTableType = new JPanel();
//		tpFloors.addTab("Table type", null, pnlTableType, null);
//		pnlTableType.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		pnlTables = new JPanel();
		tpFloors.addTab("Table", null, pnlTables, null);
		pnlTables.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		pnlTableAssignment = new JPanel();
		tpFloors.addTab("Table assignment", null, pnlTableAssignment, null);
		pnlTableAssignment.setLayout(new MigLayout("", "[grow]", "[grow]"));
//		selectionModel.addListSelectionListener( this );
		Thread thr=new Thread(new Runnable(){
			  public void run()
			  {
			      try{
			    	  	PanelFloors();			    	  
						Thread.sleep(0);
				   }catch(Exception e){
					   
				   }
			   }
			 }); thr.start();
	}
	
	public void PanelFloors(){
		
		Building  b = new Building();
		pnlBuilding.add(b, "cell 0 0, grow");

		FloorPlanForm fpf = new FloorPlanForm();
		pnlFloors.add(fpf, "cell 0 0, grow");

		Room r = new Room();
		pnlRooms.add(r, "cell 0 0, grow");
		
//		TableType  tt = new TableType();
//		pnlTableType.add(tt, "cell 0 0, grow");
		
		Tables  t = new Tables();
		pnlTables.add(t, "cell 0 0, grow");
		
		TableAssignment  ta = new TableAssignment();
		pnlTableAssignment.add(ta, "cell 0 0, grow");
		
		pnlBuilding.revalidate();
		pnlBuilding.repaint();

		pnlFloors.revalidate();
		pnlFloors.repaint();

		pnlTableType.revalidate();
		pnlTableType.repaint();
		
		pnlTables.revalidate();
		pnlTables.repaint();
		
		pnlTableAssignment.revalidate();
		pnlTableAssignment.repaint();
	}
	
//	//Handler for list selection changes
// 	public void valueChanged(ListSelectionEvent event){
//		if( event.getSource() == tblFloor.getSelectionModel() && event.getFirstIndex() >= 0 ){
//			TableModel model = (TableModel)tblFloor.getModel();
//			int row = tblFloor.getSelectedRow();
//			if(row!=-1){
//				id = model.getValueAt(row, 0).toString();
//				main = (int) model.getValueAt(row, 1);
//				function = (int) model.getValueAt(row, 2);
//				total = (int) model.getValueAt(row, 3);
//				remaining = (int) model.getValueAt(row, 4);
//				status = model.getValueAt(row, 5).toString();
//				btnAdd.setEnabled(true);
//				btnExpandFloor.setEnabled(true);
//				btnCancel.setEnabled(true);
//				try {
//					con = new Connect();
//					con.pst = con.con.prepareCall("{call select_table_room(?)}");
//					con.pst.setString(1, id);
//					con.pst.execute();
//					con.rs = con.pst.getResultSet();
//					tblRooms.setModel(DbUtils.resultSetToTableModel(con.rs));
//					con.rs.close();
//					con.pst.close();
//					con.con.close();
//				} catch (SQLException e1) {
//					e1.printStackTrace();
//				}
//				
//				try {
//					con = new Connect();
//					con.pst = con.con.prepareCall("{call select_capacity_table_floor(?)}");
//					con.pst.setString(1, id);
//					con.pst.execute();
//					con.rs = con.pst.getResultSet();
//					while(con.rs.next()){
//						totalcapacity = con.rs.getString("plan_total_capacity");
//					}
//					con.rs.close();
//					con.pst.close();
//					con.con.close();
//				} catch (SQLException e1) {
//					e1.printStackTrace();
//				}
//			}
//		}
// 	}//end of Handler for list selection changes
}
