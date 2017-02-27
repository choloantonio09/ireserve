package Capstone.View;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.metal.MetalButtonUI;
import javax.swing.table.TableModel;

import Capstone.Database.Connect;
import Capstone.Model.CounterTable;
import net.miginfocom.swing.MigLayout;
import net.proteanit.sql.DbUtils;

public class TableAssignment extends JPanel implements ListSelectionListener {
	private static JTable tblTable;
	private static JComboBox cbTable;
	private JButton btnAdd;
	private JButton btnRemove;
	private JPanel pnlTables;
	private JPanel pnlButtons;
	private JPanel pnlForms;
	protected static Connect con;
	protected String capacity;
	protected String sectionid;
	private String sectionname;
	private String description;
	public static boolean boolCheck;
	private JLabel lblTableName;
	private JLabel lblChair;
	protected String roomname;
	protected String roomarea;
	protected String roomcapacity;
	private JLabel lblRoomName;
	protected String roomid;
	private JSpinner spnTable;
	private static JComboBox cbRoom;
	private JScrollPane spTable;
	private JTextField tfChair;
	private JLabel lblChair_1;
	private JLabel lblTotal;
	private JLabel lblRemainingRoomCapacity;
	private JTextField tfTotalAccumulatedCapacity;
	private JTextField tfTotalRoomCapacity;
	private JLabel lblCalculatedCapacity;
	private JTextField tfTotal;
	private String tablename;
	private int chairint;
	private int tableint;
	private int total;
	private JLabel lblRoomId;
	private JTextField tfRoomId;
	private String tableid;
	protected String chair;
	private JScrollPane spTotalTable;
	private int totalchair;
	private boolean found;
	private int tempChair;
	private static JTable tblTotalTable;
	private JLabel lblFloor;
	private static JComboBox cbFloor;

	/**
	 * Create the panel.
	 */
	public TableAssignment() {
		setLayout(new MigLayout("", "[grow]", "[][][grow]"));
		
		pnlForms = new JPanel();
		add(pnlForms, "cell 0 0,grow");
		pnlForms.setLayout(new MigLayout("", "[][300px][][300px]", "[][][][][][]"));
		
		lblFloor = new JLabel("Floor:");
		lblFloor.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlForms.add(lblFloor, "cell 0 0");
		
		cbFloor = new JComboBox();
		cbFloor.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				ViewComboboxRoom();
				GetCapacity_RoomIdViaName();				
				ViewTable();
				ViewSumOfChair();
				GetTotalAccumulatedChair();
			}
		});
		cbFloor.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlForms.add(cbFloor, "cell 1 0,growx");
		
		lblRoomName = new JLabel("Room:");
		lblRoomName.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlForms.add(lblRoomName, "cell 2 0");
		
		cbRoom = new JComboBox();
		cbRoom.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				GetCapacity_RoomIdViaName();				
				ViewTable();
				ViewSumOfChair();
				GetTotalAccumulatedChair();
			}
		});
		cbRoom.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlForms.add(cbRoom, "cell 3 0,growx");
		
		lblTableName = new JLabel("Table  Type:");
		lblTableName.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlForms.add(lblTableName, "cell 0 2");
		
		cbTable = new JComboBox();
		cbTable.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				ViewTable();
				ViewSumOfChair();
				GetChairViaTable();
				CalculatedTableAndChair();
				GetTotalAccumulatedChair();
			}
		});
		pnlForms.add(cbTable, "flowx,cell 1 2,growx");
		cbTable.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		lblChair_1 = new JLabel("Chair:");
		lblChair_1.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlForms.add(lblChair_1, "cell 2 2");
		
		tfChair = new JTextField();
		tfChair.setEditable(false);
		tfChair.setEnabled(false);
		tfChair.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlForms.add(tfChair, "cell 3 2,growx");
		tfChair.setColumns(10);
		
		lblCalculatedCapacity = new JLabel("Chair x Table (Total):");
		lblCalculatedCapacity.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlForms.add(lblCalculatedCapacity, "cell 2 3");
		
		tfTotal = new JTextField();
		tfTotal.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfTotal.setEnabled(false);
		tfTotal.setEditable(false);
		tfTotal.setColumns(10);
		pnlForms.add(tfTotal, "cell 3 3,growx");
		
		lblRoomId = new JLabel("Table ID:");
		lblRoomId.setFont(new Font("Monospaced", Font.PLAIN, 15));
//		pnlForms.add(lblRoomId, "cell 0 5");
		
		tfRoomId = new JTextField();
		tfRoomId.setText((String) null);
		tfRoomId.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfRoomId.setEnabled(false);
		tfRoomId.setEditable(false);
		tfRoomId.setColumns(10);
//		pnlForms.add(tfRoomId, "cell 1 5,growx");
		
		lblChair = new JLabel("Table:");
		lblChair.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlForms.add(lblChair, "cell 0 3");
		
		spnTable = new JSpinner();
		spnTable.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				CalculatedTableAndChair();
			}
		});
		spnTable.setFont(new Font("Monospaced", Font.PLAIN, 15));
		spnTable.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		((DefaultEditor) spnTable.getEditor()).getTextField().setEditable(false);
		spnTable.setEditor(new JSpinner.DefaultEditor(spnTable));
		pnlForms.add(spnTable, "cell 1 3,growx");
		
		lblTotal = new JLabel("Total accumulated capacity:");
		lblTotal.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlForms.add(lblTotal, "cell 0 4");
		
		tfTotalAccumulatedCapacity = new JTextField();
		tfTotalAccumulatedCapacity.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfTotalAccumulatedCapacity.setEnabled(false);
		tfTotalAccumulatedCapacity.setEditable(false);
		tfTotalAccumulatedCapacity.setColumns(10);
		pnlForms.add(tfTotalAccumulatedCapacity, "cell 1 4,growx");
		
		lblRemainingRoomCapacity = new JLabel("Total room capacity:");
		lblRemainingRoomCapacity.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlForms.add(lblRemainingRoomCapacity, "cell 2 4");
		
		tfTotalRoomCapacity = new JTextField();
		tfTotalRoomCapacity.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfTotalRoomCapacity.setEnabled(false);
		tfTotalRoomCapacity.setEditable(false);
		tfTotalRoomCapacity.setColumns(10);
		pnlForms.add(tfTotalRoomCapacity, "cell 3 4,growx");
		
		pnlButtons = new JPanel();
		add(pnlButtons, "cell 0 1");
		pnlButtons.setLayout(new MigLayout("", "[grow][grow]", "[23px,fill]"));
		
		btnAdd = new JButton("Generate tables");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					description = (String) cbTable.getSelectedItem();
					roomname = (String) cbRoom.getSelectedItem();
					chair = tfChair.getText();
					tableint = (int) spnTable.getValue();
						GetCapacity_RoomIdViaName();
						GetChairViaTable();
						for (int i = 0; i < tableint; i++) {
							CounterTable cp = new CounterTable();
							String tablecode = cp.getAccountNumber(tableid);
							System.out.println(tablecode);
							con = new Connect();
							con.pst = con.con.prepareCall("{call insert_desks(?,?,?,?,?,?)}");
							con.pst.setString(1, roomid);
							con.pst.setString(2, tableid);
							con.pst.setString(3, tablecode);
							con.pst.setString(4, tableid + "-" + tablecode);
							con.pst.setString(5, chair);
							con.pst.setString(6, "ACTIVE");
							con.pst.execute();
						}
						RefreshForm();
						con.pst.close();
						con.con.close();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Duplicate section.");
				}
			}
		});
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdd.setContentAreaFilled(false);
		btnAdd.setOpaque(false);
		btnAdd.setFont(new Font("Monospaced", Font.PLAIN, 15));
		btnAdd.setIcon(new ImageIcon(TableAssignment.class.getResource("/Images/Icon/plus.png")));
		pnlButtons.add(btnAdd, "cell 0 0,alignx center");
		
		btnRemove = new JButton("Remove");
		btnRemove.setEnabled(false);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GetCapacity_RoomIdViaName();
				try {
					con = new Connect();
					con.pst = con.con.prepareCall("{call delete_room(?)}");
					con.pst.setString(1, tableid);
					con.pst.execute();
					RefreshForm();
					con.pst.close();
					con.con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnRemove.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRemove.setContentAreaFilled(false);
		btnRemove.setOpaque(false);
		btnRemove.setFont(new Font("Monospaced", Font.PLAIN, 15));
		btnRemove.setIcon(new ImageIcon(TableAssignment.class.getResource("/Images/Icon/remove.png")));
		pnlButtons.add(btnRemove, "cell 1 0,alignx center");
		
		pnlTables = new JPanel();
		add(pnlTables, "cell 0 2,grow");
		pnlTables.setLayout(new MigLayout("", "[grow][grow]", "[grow]"));
		
		spTable = new JScrollPane();
		pnlTables.add(spTable, "cell 1 0,grow");
		
		tblTable = new JTable();
		spTable.setViewportView(tblTable);
		
		spTotalTable = new JScrollPane();
		pnlTables.add(spTotalTable, "cell 0 0,grow");
		
		tblTotalTable = new JTable();
		spTotalTable.setViewportView(tblTotalTable);
		ViewComboboxFloor();
		ViewComboboxTables();
		ViewComboboxRoom();
		GetCapacity_RoomIdViaName(); 
		GetChairViaTable();
		GetTotalAccumulatedChair();
		CalculatedTableAndChair();
		ViewTable();
		ViewSumOfChair();
		DefaultButtonUI();
		ListSelectionModel selectionModel = tblTotalTable.getSelectionModel();		
		selectionModel.addListSelectionListener( this );
	}


	//Handler for list selection changes
 	public void valueChanged(ListSelectionEvent event){
		if( event.getSource() == tblTotalTable.getSelectionModel() && event.getFirstIndex() >= 0 ){
			TableModel model = (TableModel) tblTotalTable.getModel();
			int row = tblTotalTable.getSelectedRow();
			if(row!=-1){
				tableid = model.getValueAt(row, 0).toString();
				btnRemove.setEnabled(true);
			}
		}
 	}//end of Handler for list selection changes
	/**
	 * Default UI
	 */
	public void DefaultButtonUI(){
		btnAdd.setUI(new MetalButtonUI());
		btnRemove.setUI(new MetalButtonUI());
	}
	
	/**
	 * Refresh
	 */
	public void RefreshForm(){
		ViewTable();
		ViewSumOfChair();	
		GetTotalAccumulatedChair();
		spnTable.setValue(1);
	}
	
	public void CalculatedTableAndChair(){
		chairint = Integer.parseInt(tfChair.getText());
		tableint = (int) spnTable.getValue();
		
		total = chairint * tableint;
		tfTotal.setText(String.valueOf(total));
	}
	public void GetChairViaTable(){
		try {
			tablename  = (String) cbTable.getSelectedItem();
			con = new Connect();
			con.pst = con.con.prepareCall("{call get_capacity_via_table(?)}");
			con.pst.setString(1, tablename);
			con.pst.execute();
			con.rs = con.pst.getResultSet();
			while(con.rs.next()){
				capacity = con.rs.getString("table_chair");
				tableid = con.rs.getString("table_id");
			}
			tfRoomId.setText(tableid);
			tfChair.setText(capacity);
			con.rs.close();
			con.pst.close();
			con.con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void GetCapacity_RoomIdViaName(){
		try {
			description = (String) cbRoom.getSelectedItem();
			con = new Connect();
			con.pst = con.con.prepareCall("{call get_room_capacity_via_name(?)}");
			con.pst.setString(1, description);
			con.pst.execute();
			con.rs = con.pst.getResultSet();
			while(con.rs.next()){
				roomid = con.rs.getString("room_id");
				capacity = con.rs.getString("room_capacity");
			}
			tfTotalRoomCapacity.setText(capacity);
			con.rs.close();
			con.pst.close();
			con.con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void GetTotalAccumulatedChair(){
		try {
			tempChair= 0;
			description = (String) cbRoom.getSelectedItem();
			con = new Connect();
			con.pst = con.con.prepareCall("{call select_total_accumulated_chair(?)}");
			con.pst.setString(1, (String) cbRoom.getSelectedItem());
			con.pst.execute();
			con.rs = con.pst.getResultSet();
			while(con.rs.next()){
				found = true;
				totalchair = con.rs.getInt("Total chair");
				tempChair += totalchair;
			}
			if(!found){
				tfTotalAccumulatedCapacity.setText("0");
			}else{
				tfTotalAccumulatedCapacity.setText(String.valueOf(tempChair));
			}
			con.rs.close();
			con.pst.close();
			con.con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	//Viewing of combobox size method
	public static  void ViewComboboxTables(){
		ArrayList arrayDescription = new ArrayList<String>();
		try{
			Connect c = new Connect();
			c.pst = c.con.prepareCall("{call select_cb_table()}");
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			while (c.rs.next()) { 
				String tabledescription = c.rs.getString("table_description");
				arrayDescription.add(tabledescription);
			}
			c.rs.close();
			c.pst.close();
			c.con.close();
			DefaultComboBoxModel<String> mpDescription = new DefaultComboBoxModel(arrayDescription.toArray());
			cbTable.setModel(mpDescription);
		}catch(Exception ed){
			ed.printStackTrace();
		}
	}
	//end of Viewing of combobox size method

	//Viewing of combobox size method
	public static  void ViewComboboxRoom(){
		ArrayList arrayDescription = new ArrayList<String>();
		try{
			Connect c = new Connect();
			c.pst = c.con.prepareCall("{call select_cb_room_via_floor(?)}");
			c.pst.setString(1, (String) cbFloor.getSelectedItem());
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			while (c.rs.next()) { 
				String tabledescription = c.rs.getString("room_name");
				arrayDescription.add(tabledescription);
			}
			c.rs.close();
			c.pst.close();
			c.con.close();
			DefaultComboBoxModel<String> mpDescription = new DefaultComboBoxModel(arrayDescription.toArray());
			cbRoom.setModel(mpDescription);
		}catch(Exception ed){
			ed.printStackTrace();
		}
	}
	//end of Viewing of combobox size method

	//Viewing of combobox size method
	public static  void ViewComboboxFloor(){
		ArrayList arrayFloor = new ArrayList<String>();
		try{
			Connect c = new Connect();
			c.pst = c.con.prepareCall("{call select_cb_floor_plan()}");
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			while (c.rs.next()) { 
				String floor_floorname = c.rs.getString("plan_name");
				arrayFloor.add(floor_floorname);
			}
			c.rs.close();
			c.pst.close();
			c.con.close();
			DefaultComboBoxModel<String> mpFloor = new DefaultComboBoxModel(arrayFloor.toArray());
			cbFloor.setModel(mpFloor);
		}catch(Exception ed){
			ed.printStackTrace();
		}
	}
	//end of Viewing of combobox size method
	/**
	 * View Table Floor
	 */
	public static void ViewTable(){
			try {
				con = new Connect();
				con.pst = con.con.prepareCall("{call select_table_desk_via_room_name(?)}");
				con.pst.setString(1, (String)  cbRoom.getSelectedItem());
				con.pst.execute();
				con.rs = con.pst.getResultSet();
				tblTable.setModel(DbUtils.resultSetToTableModel(con.rs));
				con.rs.close();
				con.pst.close();
				con.con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
	}

	/**
	 * View Table Floor
	 */
	public static void ViewSumOfChair(){
			try {
				con = new Connect();
				con.pst = con.con.prepareCall("{call select_sum_of_chair(?)}");
				con.pst.setString(1, (String) cbRoom.getSelectedItem());
				con.pst.execute();
				con.rs = con.pst.getResultSet();
				tblTotalTable.setModel(DbUtils.resultSetToTableModel(con.rs));
				con.rs.close();
				con.pst.close();
				con.con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
	}
}
