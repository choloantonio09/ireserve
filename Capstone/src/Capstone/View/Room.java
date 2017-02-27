package Capstone.View;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.metal.MetalButtonUI;
import javax.swing.table.TableModel;

import Capstone.Database.Connect;
import Capstone.Model.CounterRoom;
import Capstone.Option.StringUtil;

import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JTextField;

public class Room extends JPanel implements ListSelectionListener {
	private static JTable tblFloorSection;
	static JComboBox cbSection;
	private static JComboBox cbFloor;
	private JButton btnAdd;
	private JButton btnRemove;
	private JScrollPane spFloorSection;
	private JPanel pnlTables;
	private JPanel pnlButtons;
	private JPanel pnlForms;
	protected static Connect con;
	protected String floorid;
	protected String sectionid;
	private String sectionname;
	private String floorname;
	public static boolean boolCheck;
	private JLabel lblTotatlCapacityOf;
	private JTextField tfFloorArea;
	private JLabel label_1;
	private JTextField tfRoomName;
	private JLabel label_2;
	private JLabel lblRoomCapacity;
	private JLabel lblSumOfCapacity;
	private JTextField tfRoomArea;
	private JTextField tfRoomCapacity;
	private JTextField tfTotalArea;
	private int floorarea;
	protected String roomname;
	protected String roomarea;
	protected String roomcapacity;
	private JLabel lblRoomId;
	private JTextField tfRoomId;
	protected String roomid;
	private boolean found;
	private int total;
	private JButton btnUpdate;
	private JButton btnCancel;
	private String id;
	private String capacity;
	protected int tc;
	protected int fc;

	/**
	 * Create the panel.
	 */
	public Room() {
		setLayout(new MigLayout("", "[grow]", "[][][grow]"));
		
		pnlForms = new JPanel();
		add(pnlForms, "cell 0 0,grow");
		pnlForms.setLayout(new MigLayout("", "[][300px]", "[][][][][][][][]"));
		
		lblRoomId = new JLabel("Room ID:");
		lblRoomId.setFont(new Font("Monospaced", Font.PLAIN, 15));
//		pnlForms.add(lblRoomId, "cell 0 0");
		
		tfRoomId = new JTextField();
		tfRoomId.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfRoomId.setEnabled(false);
		tfRoomId.setEditable(false);
		tfRoomId.setColumns(10);
//		pnlForms.add(tfRoomId, "cell 1 0,growx");
		
		JLabel lblFloorName = new JLabel("Floor:");
		pnlForms.add(lblFloorName, "cell 0 1");
		lblFloorName.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		cbFloor = new JComboBox();
		cbFloor.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				ViewTableFloorSection();
				TotalArea();
				btnAdd.setEnabled(true);
				btnUpdate.setEnabled(false);
				btnCancel.setEnabled(false);
				btnRemove.setEnabled(false);
			}
		});
		pnlForms.add(cbFloor, "cell 1 1,growx");
		cbFloor.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		JLabel lblSectionName = new JLabel("Section:");
		pnlForms.add(lblSectionName, "cell 0 2");
		lblSectionName.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		cbSection = new JComboBox();
		cbSection.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(cbSection.getSelectedIndex() == cbSection.getItemCount() - 1 && boolCheck == false){
					boolCheck = true;
					SectionForm sf = new SectionForm();
					sf.setSize(400,400);
					sf.setModal(true);
					sf.setVisible(true);
				}
			}
		});
		pnlForms.add(cbSection, "cell 1 2,growx");
		cbSection.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		lblTotatlCapacityOf = new JLabel("Total capacity of the floor:");
		lblTotatlCapacityOf.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlForms.add(lblTotatlCapacityOf, "cell 0 7");
		
		tfFloorArea = new JTextField();
		tfFloorArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfFloorArea.setEnabled(false);
		tfFloorArea.setEditable(false);
		tfFloorArea.setColumns(10);
		pnlForms.add(tfFloorArea, "cell 1 7,growx");
		
		label_1 = new JLabel("Room label:");
		label_1.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlForms.add(label_1, "cell 0 3");
		
		tfRoomName = new JTextField();
		tfRoomName.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfRoomName.setColumns(10);
		pnlForms.add(tfRoomName, "cell 1 3,growx");
		
		label_2 = new JLabel("Room area (sqm):");
		label_2.setFont(new Font("Monospaced", Font.PLAIN, 15));
//		pnlForms.add(label_2, "cell 0 5");
		
		tfRoomArea = new JTextField();
		tfRoomArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfRoomArea.setColumns(10);
//		pnlForms.add(tfRoomArea, "cell 1 5,growx");
		
		lblRoomCapacity = new JLabel("Room capacity:");
		lblRoomCapacity.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlForms.add(lblRoomCapacity, "cell 0 5");
		
		tfRoomCapacity = new JTextField();
		tfRoomCapacity.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfRoomCapacity.setColumns(10);
		pnlForms.add(tfRoomCapacity, "cell 1 5,growx");
		
		lblSumOfCapacity = new JLabel("Total capacity of all room within the floor:");
		lblSumOfCapacity.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlForms.add(lblSumOfCapacity, "cell 0 6");
		
		tfTotalArea = new JTextField();
		tfTotalArea.setEnabled(false);
		tfTotalArea.setEditable(false);
		tfTotalArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfTotalArea.setColumns(10);
		pnlForms.add(tfTotalArea, "cell 1 6,growx");
		
		pnlButtons = new JPanel();
		add(pnlButtons, "cell 0 1");
		pnlButtons.setLayout(new MigLayout("", "[grow][][grow][]", "[23px,fill]"));
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					roomid = tfRoomId.getText();
					floorname = (String) cbFloor.getSelectedItem();
					sectionname = (String) cbSection.getSelectedItem();
					roomname = tfRoomName.getText();
					roomarea ="0";
					roomcapacity = tfRoomCapacity.getText();
					tc = Integer.parseInt(tfTotalArea.getText());
					fc = Integer.parseInt(tfFloorArea.getText());
					if((Integer.parseInt(roomcapacity) +tc) <= fc){
						if(StringUtil.isAlphanumericWithSpaceApostropheOrHyphen(roomname) == true && validateEmptyString(roomname) == true && isNumber(roomarea) == true  && isNumber(roomcapacity) == true ) {
							GetFloorIdViaName();
							GetSectionIdViaName();
							con = new Connect();
							con.pst = con.con.prepareCall("{call insert_room(?,?,?,?,?,?)}");
							con.pst.setString(1, roomid);
							con.pst.setString(2, floorid);
							con.pst.setString(3, sectionid);
							con.pst.setString(4, roomname);
							con.pst.setString(5, roomarea);
							con.pst.setString(6, roomcapacity);
							con.pst.execute();
							RefreshForm();
							JOptionPane.showMessageDialog(null, "Room added successfully.");
							con.pst.close();
							con.con.close();
						}else if(validateEmptyString(roomname) == false){
							JOptionPane.showMessageDialog(null, "Empty field");						
						}else if(isNumber(roomarea) == false ||   isNumber(roomcapacity) == false){
							JOptionPane.showMessageDialog(null, "Invalid number");						
						}else{
							JOptionPane.showMessageDialog(null, "Invalid input");
						}
					}else{
						JOptionPane.showMessageDialog(null, "Exceeds the maximum capacity of the room.");
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Room name already exists..");
				}
			}
		});
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdd.setContentAreaFilled(false);
		btnAdd.setOpaque(false);
		btnAdd.setFont(new Font("Monospaced", Font.PLAIN, 15));
		btnAdd.setIcon(new ImageIcon(Room.class.getResource("/Images/Icon/plus.PNG")));
		pnlButtons.add(btnAdd, "cell 0 0,alignx center");
		
		btnRemove = new JButton("Remove");
		btnRemove.setEnabled(false);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GetFloorIdViaName();
				GetSectionIdViaName();
				try {
					con = new Connect();
					con.pst = con.con.prepareCall("{call delete_room(?,?)}");
					con.pst.setString(1, floorid);
					con.pst.setString(2, sectionid);
					con.pst.execute();
					RefreshForm();
					con.pst.close();
					con.con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					roomid = tfRoomId.getText();
					sectionname = (String) cbSection.getSelectedItem();
					roomname = tfRoomName.getText();
					roomarea ="0";
					roomcapacity = tfRoomCapacity.getText();
					fc = Integer.parseInt(tfFloorArea.getText());
					if((Integer.parseInt(roomcapacity) +tc) <= fc){
						if(StringUtil.isAlphanumericWithSpaceApostropheOrHyphen(roomname) == true && validateEmptyString(roomname) == true && isNumber(roomarea) == true  && isNumber(roomcapacity) == true ) {
 							GetFloorIdViaName();
							GetSectionIdViaName();
							con = new Connect();
							con.pst = con.con.prepareCall("{call update_room(?,?,?,?)}");
							con.pst.setString(1, roomid);
							con.pst.setString(2, sectionid);
							con.pst.setString(3, roomname);	
							con.pst.setString(4, roomcapacity);
							con.pst.execute();
							RefreshForm();
							JOptionPane.showMessageDialog(null, "Room updated successfully.");
							con.pst.close();
							con.con.close();
						}else if(validateEmptyString(roomname) == false){
							JOptionPane.showMessageDialog(null, "Empty field");						
						}else if(isNumber(roomarea) == false ||   isNumber(roomcapacity) == false){
							JOptionPane.showMessageDialog(null, "Invalid number");						
						}else{
							JOptionPane.showMessageDialog(null, "Invalid input");
						}
					}else{
						JOptionPane.showMessageDialog(null, "Exceeds the maximum capacity of the room.");
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Room name already exists..");
				}
			}
		});
		btnUpdate.setEnabled(false);
		btnUpdate.setIcon(new ImageIcon(Room.class.getResource("/Images/Icon/update.png")));
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setOpaque(false);
		btnUpdate.setFont(new Font("Monospaced", Font.PLAIN, 15));
		btnUpdate.setContentAreaFilled(false);
		pnlButtons.add(btnUpdate, "cell 1 0");
		btnRemove.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRemove.setContentAreaFilled(false);
		btnRemove.setOpaque(false);
		btnRemove.setFont(new Font("Monospaced", Font.PLAIN, 15));
		btnRemove.setIcon(new ImageIcon(Room.class.getResource("/Images/Icon/remove.png")));
		pnlButtons.add(btnRemove, "cell 2 0,alignx center");
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RefreshForm();
			}
		});
		btnCancel.setEnabled(false);
		btnCancel.setIcon(new ImageIcon(Room.class.getResource("/Images/Icon/cancel.png")));
		btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancel.setOpaque(false);
		btnCancel.setFont(new Font("Monospaced", Font.PLAIN, 15));
		btnCancel.setContentAreaFilled(false);
		pnlButtons.add(btnCancel, "cell 3 0");
		
		pnlTables = new JPanel();
		add(pnlTables, "cell 0 2,grow");
		pnlTables.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		spFloorSection = new JScrollPane();
		pnlTables.add(spFloorSection, "cell 0 0,grow");
		
		tblFloorSection = new JTable();
		spFloorSection.setViewportView(tblFloorSection);
		DefaultButtonUI();
		Thread thr=new Thread(new Runnable(){
			  public void run()
			  {
			      try{
			    	  CounterRoom();
			    	  ViewBuildingArea();
			    	  ViewComboboxSection();
			    	  ViewComboboxFloor();
			    	  ViewTableFloorSection();
			    	  TotalArea();
						Thread.sleep(0);
				   }catch(Exception e){
					   
				   }
			   }
			 }); thr.start();
		ListSelectionModel selectionModel = tblFloorSection.getSelectionModel();		
		selectionModel.addListSelectionListener( this );
	}


	//Handler for list selection changes
 	public void valueChanged(ListSelectionEvent event){
		if( event.getSource() == tblFloorSection.getSelectionModel() && event.getFirstIndex() >= 0 ){
			TableModel model = (TableModel) tblFloorSection.getModel();
			int row = tblFloorSection.getSelectedRow();
			if(row!=-1){
				id = model.getValueAt(row, 0).toString();
				roomname = model.getValueAt(row, 1).toString();
				sectionname = model.getValueAt(row, 2).toString();	
				capacity = model.getValueAt(row, 3).toString();
				
				tfRoomId.setText(id);
				tfRoomName.setText(roomname);
				tfRoomCapacity.setText(capacity);
				cbSection.setSelectedItem(sectionname);
				
				tc = Integer.parseInt(tfTotalArea.getText()) - Integer.parseInt(capacity);
				
				btnRemove.setEnabled(true);
				btnUpdate.setEnabled(true);
				btnCancel.setEnabled(true);
				btnAdd.setEnabled(false);
			}
		}
 	}//end of Handler for list selection changes
	/**
	 * Default UI
	 */
	public void DefaultButtonUI(){
		btnAdd.setUI(new MetalButtonUI());
		btnRemove.setUI(new MetalButtonUI());
		btnUpdate.setUI(new MetalButtonUI());
		btnCancel.setUI(new MetalButtonUI());
	}
	
	/**
	 * Refresh
	 */
	public void RefreshForm(){
		Thread thr=new Thread(new Runnable(){
			  public void run()
			  {
			      try{
						ViewComboboxSection();
						ViewTableFloorSection();
						TableAssignment.ViewComboboxRoom();
						CounterRoom();
						TotalArea();
						tfRoomName.setText(null);
						tfRoomArea.setText(null);
						tfRoomCapacity.setText(null);
						btnAdd.setEnabled(true);
						btnUpdate.setEnabled(false);
						btnCancel.setEnabled(false);
						btnRemove.setEnabled(false);
						Thread.sleep(0);
				   }catch(Exception e){
					   
				   }
			   }
			 }); thr.start();
	}
	
	public void GetFloorIdViaName(){
		try {
			con = new Connect();
			con.pst = con.con.prepareCall("{call get_floor_id_via_name(?)}");
			con.pst.setString(1, floorname);
			con.pst.execute();
			con.rs = con.pst.getResultSet();
			while(con.rs.next()){
				floorid = con.rs.getString("plan_id");
			}
			con.rs.close();
			con.pst.close();
			con.con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void GetSectionIdViaName(){
		try {
			con = new Connect();
			con.pst = con.con.prepareCall("{call get_section_id_via_name(?)}");
			con.pst.setString(1, sectionname);
			con.pst.execute();
			con.rs = con.pst.getResultSet();
			while(con.rs.next()){
				sectionid = con.rs.getString("section_id");
			}
			con.rs.close();
			con.pst.close();
			con.con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	//Viewing of combobox size method
		public static  void ViewComboboxSection(){
			ArrayList arraySection = new ArrayList<String>();
			try{
				Connect c = new Connect();
				c.pst = c.con.prepareCall("{call select_cb_section()}");
				c.pst.execute();
				c.rs = c.pst.getResultSet();
				while (c.rs.next()) { 
					String section_name = c.rs.getString("section_name");
					arraySection.add(section_name);
				}
				arraySection.add("[Add new section...]");
				c.rs.close();
				c.pst.close();
				c.con.close();
				DefaultComboBoxModel<String> mpSection = new DefaultComboBoxModel(arraySection.toArray());
				cbSection.setModel(mpSection);
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
		public static void ViewTableFloorSection(){
				try {
					con = new Connect();
					con.pst = con.con.prepareCall("{call select_table_room_via_floor_name(?)}");
					con.pst.setString(1, (String)  cbFloor.getSelectedItem());
					con.pst.execute();
					con.rs = con.pst.getResultSet();
					tblFloorSection.setModel(DbUtils.resultSetToTableModel(con.rs));
					con.rs.close();
					con.pst.close();
					con.con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}

		public void ViewBuildingArea(){
				try {
					con = new Connect();
					con.pst = con.con.prepareCall("{call select_table_building()}");
					con.pst.execute();
					con.rs = con.pst.getResultSet();
					while(con.rs.next()){
						tfFloorArea.setText(con.rs.getString("Final area of the Restaurant"));
					}
					con.rs.close();
					con.pst.close();
					con.con.close();
				}catch(Exception e){
						e.printStackTrace();
				}
		}
		/**
		 * Counter floor plan
		 */
		public void CounterRoom(){
			CounterRoom cp = new CounterRoom();
			cp.getAccountNumber();
			if(!cp.getAccountNumber().isEmpty()){
				tfRoomId.setText(cp.getAccountNumber());
			}
		}//end of CounterFood Method
		

		public void TotalArea(){
				try {
					total = 0;
					con = new Connect();
					con.pst = con.con.prepareCall("{call sum_room_area(?)}");	
					con.pst.setString(1, (String)  cbFloor.getSelectedItem());
					con.pst.execute();
					con.rs = con.pst.getResultSet();
					while(con.rs.next()){
						found = true;
						floorarea = con.rs.getInt("total");	
						total = floorarea + total;
					}
					if(!found){ 
						tfTotalArea.setText("0");							
					}else{
						tfTotalArea.setText(String.valueOf(total));	
					}
					con.rs.close();
					con.pst.close();
					con.con.close();
				}catch(Exception e){
						e.printStackTrace();
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
