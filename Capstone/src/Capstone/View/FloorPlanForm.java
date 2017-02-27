package Capstone.View;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField; 
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.metal.MetalButtonUI;
import javax.swing.table.TableModel;

import com.toedter.calendar.IDateEditor;

import Capstone.Database.Connect;
import Capstone.Model.CounterFloorPlan;
import Capstone.Option.StringUtil;
import net.miginfocom.swing.MigLayout;
import net.proteanit.sql.DbUtils;

public class FloorPlanForm extends JPanel implements ListSelectionListener{
	public static IDateEditor dateEditor;
	public JTextField tfFloorId;
	JTextField tfFloorName;
	public JButton btnSave;
	public JLabel lblFloorDesc;
	public JLabel lblFloorArea;
	JTextField tfFloorDesc;
	JTextField tfFloorArea;
	public JLabel lblFloorId;
	public JLabel lblFloorName;
	public JPanel pnlForm;
	public JPanel pnlFloors;
	JPanel frame;
	public JLabel lblTable;
	protected Connect con;
	private JTable tblFloors;
	private JScrollPane spFloors;
	private JPanel panel;
	protected String id;
	protected String name;
	protected String desc;
	protected String area;
	private JButton btnUpdate;
	private JButton btnCancel;
	/**
	 * Create the panel.
	 */
	/**
	 * 
	 */
	public FloorPlanForm() {

		setLayout(new MigLayout("", "[grow]", "[][][grow]"));
		
		pnlForm = new JPanel();
		add(pnlForm, "cell 0 0,grow");
		pnlForm.setLayout(new MigLayout("", "[][300px]", "[][][][]"));
		
		lblFloorId = new JLabel("Floor ID:");
//		pnlForm.add(lblFloorId, "cell 0 0");
		lblFloorId.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		tfFloorId = new JTextField();
		tfFloorId.setEnabled(false);
//		pnlForm.add(tfFloorId, "cell 1 0,growx");
		tfFloorId.setEditable(false);
		tfFloorId.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfFloorId.setColumns(10);
		
		lblFloorName = new JLabel("Floor name:");
		pnlForm.add(lblFloorName, "cell 0 1");
		lblFloorName.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		tfFloorName = new JTextField();
		tfFloorName.setText(" ");
		pnlForm.add(tfFloorName, "cell 1 1,growx");
		tfFloorName.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfFloorName.setColumns(10);
		
		lblFloorDesc = new JLabel("Floor description:");
		pnlForm.add(lblFloorDesc, "cell 0 2");
		lblFloorDesc.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		tfFloorDesc = new JTextField();
		pnlForm.add(tfFloorDesc, "cell 1 2,growx");
		tfFloorDesc.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfFloorDesc.setColumns(10);
		
		lblFloorArea = new JLabel("Floor area (sqm): ");
//		pnlForm.add(lblFloorArea, "cell 0 3");
		lblFloorArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		tfFloorArea = new JTextField();
		tfFloorArea.setEnabled(false);
		tfFloorArea.setEditable(false);
//		pnlForm.add(tfFloorArea, "cell 1 3,growx");
		tfFloorArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfFloorArea.setColumns(10);
		
		panel = new JPanel();
		add(panel, "cell 0 1");
		panel.setLayout(new MigLayout("", "[grow][][]", "[]"));
		
		btnSave = new JButton();
		btnSave.setText("Add");
		panel.add(btnSave, "cell 0 0,alignx center");
		btnSave.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				id = tfFloorId.getText();
				name = tfFloorName.getText();
				desc = tfFloorDesc.getText();
				area = tfFloorArea.getText();
				try {
					if(StringUtil.isAlphanumericWithSpaceApostropheOrHyphen(name) == true && validateEmptyString(name) == true) {
						con = new Connect();
						con.pst = con.con.prepareCall("{call insert_floor_plan(?,?,?,?,?)}");
						con.pst.setString(1, id);
						con.pst.setString(2, name);
						con.pst.setString(3, desc);
						con.pst.setString(4, area);	
						con.pst.setString(5, "ACTIVE");	
						con.pst.execute();
						RefreshForm();
						JOptionPane.showMessageDialog(null, "Floor added successfully.");
						con.pst.close();
						con.con.close();
					}else if(validateEmptyString(name) == false){
						JOptionPane.showMessageDialog(null, "Empty field");						
					}else{
						JOptionPane.showMessageDialog(null, "Invalid character");
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Floor name already exists.");
				}
			}
		});
		btnSave.setContentAreaFilled(false);
		btnSave.setOpaque(false);
		btnSave.setIcon(new ImageIcon(FloorPlanForm.class.getResource("/Images/Icon/plus.PNG")));
		btnSave.setName("ADD");
		btnSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSave.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		btnUpdate = new JButton();
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				id = tfFloorId.getText();
				name = tfFloorName.getText();
				desc = tfFloorDesc.getText();
				area = tfFloorArea.getText();
				try {
					if(StringUtil.isAlphanumericWithSpaceApostropheOrHyphen(name) == true && validateEmptyString(name) == true) {
						con = new Connect();
						con.pst = con.con.prepareCall("{call update_floor_plan(?,?,?)}");
						con.pst.setString(1, id);
						con.pst.setString(2, name);
						con.pst.setString(3, desc);
						con.pst.execute();
						RefreshForm();
						btnSave.setEnabled(true);
						btnUpdate.setEnabled(false);
						btnCancel.setEnabled(false);
						JOptionPane.showMessageDialog(null, "Floor updated successfully.");
						con.pst.close();
						con.con.close();
					}else if(validateEmptyString(name) == false){
						JOptionPane.showMessageDialog(null, "Empty field");						
					}else{
						JOptionPane.showMessageDialog(null, "Invalid character");
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Floor name already exists.");
				}
			}
		});
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setEnabled(false);
		btnUpdate.setIcon(new ImageIcon(FloorPlanForm.class.getResource("/Images/Icon/update.png")));
		btnUpdate.setText("Update");
		btnUpdate.setOpaque(false);
		btnUpdate.setName("ADD");
		btnUpdate.setFont(new Font("Monospaced", Font.PLAIN, 15));
		btnUpdate.setContentAreaFilled(false);
		panel.add(btnUpdate, "cell 1 0");
		
		btnCancel = new JButton();
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RefreshForm();
				btnSave.setEnabled(true);
				btnUpdate.setEnabled(false);
				btnCancel.setEnabled(false);
			}
		});
		btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancel.setEnabled(false);
		btnCancel.setIcon(new ImageIcon(FloorPlanForm.class.getResource("/Images/Icon/cancel.png")));
		btnCancel.setText("Cancel");
		btnCancel.setOpaque(false);
		btnCancel.setName("ADD");
		btnCancel.setFont(new Font("Monospaced", Font.PLAIN, 15));
		btnCancel.setContentAreaFilled(false);
		panel.add(btnCancel, "cell 2 0");
		
		pnlFloors = new JPanel();
		add(pnlFloors, "cell 0 2,grow");
		pnlFloors.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		spFloors = new JScrollPane();
		pnlFloors.add(spFloors, "cell 0 0,grow");
		
		tblFloors = new JTable();
		spFloors.setViewportView(tblFloors);
		// Handle the listener
		ListSelectionModel selectionModel = tblFloors.getSelectionModel();		
		selectionModel.addListSelectionListener( this );
		
		CounterFloorPlan();
		ViewTableFloor();
		DefaultButtonUI();
		ViewBuildingArea();
	}	

	/**
	 * Default UI
	 */
	public void DefaultButtonUI(){
		btnSave.setUI(new MetalButtonUI());
		btnUpdate.setUI(new MetalButtonUI());
		btnCancel.setUI(new MetalButtonUI());
	}
	
	/**
	 * Refresh
	 */
	public void RefreshForm(){
		CounterFloorPlan();
		ViewTableFloor();
		ViewBuildingArea();
		Room.ViewComboboxSection();
		Room.ViewComboboxFloor();
		Room.ViewTableFloorSection();
		TableAssignment.ViewComboboxFloor();
		tfFloorDesc.setText(null);
		tfFloorName.setText(null);
	}
	
	/**
	 * View Table Floor
	 */
	public void ViewTableFloor(){
			try {
				con = new Connect();
				con.pst = con.con.prepareCall("{call select_table_floor()}");
				con.pst.execute();
				con.rs = con.pst.getResultSet();
				tblFloors.setModel(DbUtils.resultSetToTableModel(con.rs));
				con.rs.close();
				con.pst.close();
				con.con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
	}

	/**
	 * Counter floor plan
	 */
	public void CounterFloorPlan(){
		CounterFloorPlan cp = new CounterFloorPlan();
		cp.getAccountNumber();
		if(!cp.getAccountNumber().isEmpty()){
			tfFloorId.setText(cp.getAccountNumber());
		}
	}//end of CounterFood Method
	
	public void ViewBuildingArea(){
			try {
				con = new Connect();
				con.pst = con.con.prepareCall("{call select_table_building()}");
				con.pst.execute();
				con.rs = con.pst.getResultSet();
				while(con.rs.next()){
					area = con.rs.getString("Final area of the Restaurant");
					tfFloorArea.setText(area);
				}
				con.rs.close();
				con.pst.close();
				con.con.close();
			}catch(Exception e){
					e.printStackTrace();
			}
	}
 	public void valueChanged(ListSelectionEvent event){
		if( event.getSource() == tblFloors.getSelectionModel() && event.getFirstIndex() >= 0 ){
			TableModel model = (TableModel)tblFloors.getModel();
			int row = tblFloors.getSelectedRow();
			tblFloors.getSelectedColumn();
			if(row!=-1){
				id = model.getValueAt(row, 0).toString();
				name = model.getValueAt(row, 1).toString();
				desc  = model.getValueAt(row, 2).toString();
				
				tfFloorId.setText(id);
				tfFloorName.setText(name);
				tfFloorDesc.setText(desc);
				
				btnSave.setEnabled(false);
				btnUpdate.setEnabled(true);
				btnCancel.setEnabled(true);
			}
		}
 	}
	public boolean isNumber(String no) {   //no only
		String regex = "\\d+";
		try {
		if(no.matches(regex)) {  
		    return true;
		}else {
			return false;
		}
		}catch(Exception e){
			return false;
		}
	}
	//Mary End
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
}
