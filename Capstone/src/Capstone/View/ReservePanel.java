package Capstone.View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.plaf.metal.MetalButtonUI;

import org.apache.commons.validator.routines.EmailValidator;

import com.toedter.calendar.IDateEditor;
import com.toedter.calendar.JDateChooser;

import Capstone.Database.Connect;
import Capstone.Model.ModelReserve;
import net.miginfocom.swing.MigLayout;

public class ReservePanel extends JPanel {
	public static IDateEditor dateEditor;
	public JTextField tfReserveId;
	JTextField tfName;
	public JButton btnSave;
	public JLabel lblContactNo;
	public JLabel lblEmailAddress;
	JTextField tfContactNo;
	JTextField tfEmailAddress;
	public JLabel lblDateOfReserve;
	public JLabel lblTimeReserved;
	public JLabel lblReserveId;
	public JLabel lblName;
	public JLabel lblReservationType;
	public JLabel lblType;
	public JPanel pnlForm;
	public JPanel pnlButtons;
	JPanel frame;
	public JLabel lblTable;
	public JSpinner spnTable;
	public JLabel lblTotalCapacityAccumulated;
	private JRadioButton rdbtnEvent;
	private JRadioButton rdbtnNormal;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblAvailableCapacity;
	private static JLabel lblAvailableCapacityNormal;
	private static JDateChooser dateChooser;
	private JComboBox cbTime;
	private Object meridem;
	private static JSpinner spnCapacity;
	private static JComboBox cbRoom;
	private static String roomid;
	private static String roomtype;
	private static int roomcapacityminus;
	private JLabel lblSelectFloor;
	protected Connect con;
	protected ModelReserve mr;
	private String type;
	private JLabel lblFloor;
	private JComboBox cbFloor;
	private JPanel panel;
	private String usedroom;
	private String time;
	private boolean roomfound;
	/**
	 * Create the panel.
	 */
	public ReservePanel() {

		setLayout(new MigLayout("", "[grow]", "[grow][]"));
		
		pnlForm = new JPanel();
		pnlForm.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(pnlForm, "cell 0 0,grow");
		pnlForm.setLayout(new MigLayout("", "[][500px]", "[][][][][][]"));
		
		lblReserveId = new JLabel("Reserve ID:");
		pnlForm.add(lblReserveId, "cell 0 0");
		lblReserveId.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		tfReserveId = new JTextField();
		pnlForm.add(tfReserveId, "cell 1 0,growx");
		tfReserveId.setEditable(false);
		tfReserveId.setText("R000000004");
		tfReserveId.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfReserveId.setColumns(10);
		
		lblName = new JLabel("Name*:");
		pnlForm.add(lblName, "cell 0 1");
		lblName.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		tfName = new JTextField();
		pnlForm.add(tfName, "cell 1 1,growx");
		tfName.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfName.setColumns(10);
		
		lblContactNo = new JLabel("Contact no*:");
		pnlForm.add(lblContactNo, "cell 0 2");
		lblContactNo.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		tfContactNo = new JTextField();
		pnlForm.add(tfContactNo, "cell 1 2,growx");
		tfContactNo.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfContactNo.setColumns(10);
		
		lblEmailAddress = new JLabel("Email Address:");
		pnlForm.add(lblEmailAddress, "cell 0 3");
		lblEmailAddress.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		tfEmailAddress = new JTextField();
		pnlForm.add(tfEmailAddress, "cell 1 3,growx");
		tfEmailAddress.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfEmailAddress.setColumns(10);
		
		panel = new JPanel();
		pnlForm.add(panel, "cell 0 5 2 1,grow");
		panel.setLayout(new MigLayout("", "[][grow]", "[][][][][][]"));
		
		lblFloor = new JLabel("Floor:");
		panel.add(lblFloor, "cell 0 2,grow");
		lblFloor.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		cbFloor = new JComboBox();
		panel.add(cbFloor, "cell 1 2,grow");
		cbFloor.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rdbtnNormal.isSelected()){
					ViewNormalRoom();					
				}
				else if(rdbtnEvent.isSelected()){
					ViewEventRoom();					
				}
				ViewAllTableSetText();
			}
		});
		cbFloor.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		lblSelectFloor = new JLabel("Available Room:");
		panel.add(lblSelectFloor, "cell 0 3,grow");
		lblSelectFloor.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		cbRoom = new JComboBox();
		cbRoom.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				ViewAllTableSetText();
			}
		});
		panel.add(cbRoom, "cell 1 3,grow");
		cbRoom.setFont(new Font("Monospaced", Font.PLAIN, 15));
		cbRoom.setModel(new DefaultComboBoxModel(new String[] {"Floor B"}));
		
		lblDateOfReserve = new JLabel("Date reserved:");
		panel.add(lblDateOfReserve, "cell 0 0,grow");
		lblDateOfReserve.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		dateChooser = new JDateChooser(new Date());
		dateChooser.getDateEditor().setEnabled(false);
		dateChooser.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(dateChooser, "cell 1 0,grow");
		dateChooser.setDateFormatString("MMMM dd, yyyy");
		dateChooser.setMinSelectableDate(new Date());
		
		lblTimeReserved = new JLabel("Time reserved:");
		panel.add(lblTimeReserved, "cell 0 1,grow");
		lblTimeReserved.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		cbTime = new JComboBox();
		cbTime.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(rdbtnNormal.isSelected()){
					ViewNormalRoom();					
				}
				else if(rdbtnEvent.isSelected()){
					ViewEventRoom();					
				}
				ViewAllTableSetText();
			}
		});
		cbTime.setFont(new Font("Monospaced", Font.PLAIN, 15));
		panel.add(cbTime, "cell 1 1,grow");
		
		lblTotalCapacityAccumulated = new JLabel("Capacity:");
		panel.add(lblTotalCapacityAccumulated, "cell 0 4,grow");
		lblTotalCapacityAccumulated.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		spnCapacity = new JSpinner();
		panel.add(spnCapacity, "cell 1 4,grow");
		spnCapacity.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		((DefaultEditor) spnCapacity.getEditor()).getTextField().setEditable(false);
		spnCapacity.setEditor(new JSpinner.DefaultEditor(spnCapacity));
		spnCapacity.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		lblAvailableCapacity = new JLabel("Available Capacity:");
		panel.add(lblAvailableCapacity, "cell 0 5,grow");
		lblAvailableCapacity.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		lblAvailableCapacityNormal = new JLabel("0");
		panel.add(lblAvailableCapacityNormal, "cell 1 5,grow");
		lblAvailableCapacityNormal.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		lblReservationType = new JLabel("Reservation Type:");
		pnlForm.add(lblReservationType, "cell 0 4");
		lblReservationType.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		lblType = new JLabel("");
		lblType.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlForm.add(lblType, "flowx,cell 1 4");
		
		rdbtnNormal = new JRadioButton("Regular");
		rdbtnNormal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				type = "Regular";
				ViewComboboxTime();
				ViewFloor();
				ViewNormalRoom();
				ViewAllTableSetText();
			}
		});
		rdbtnNormal.setSelected(true);
		buttonGroup.add(rdbtnNormal);
		rdbtnNormal.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlForm.add(rdbtnNormal, "cell 1 4");
		
		rdbtnEvent = new JRadioButton("Event");
		rdbtnEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type = "Event";
				ViewComboboxTime();
				ViewFloor();
				ViewEventRoom();
				ViewAllTableSetText();
			}
		});
		buttonGroup.add(rdbtnEvent);
		rdbtnEvent.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlForm.add(rdbtnEvent, "cell 1 4");
		
		pnlButtons = new JPanel();
		pnlButtons.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(pnlButtons, "cell 0 1,grow");
		pnlButtons.setLayout(new MigLayout("", "[grow]", "[]"));
		
		btnSave = new JButton();
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					mr = getFieldData();
					if(validateName(mr.getName()) == true && validateEmptyString(mr.getName()) == true && validateEmail(mr.getEmail()) == true ){	
						con = new Connect();
						con.pst = con.con.prepareCall("{call insert_reserve(?,?,?,?,?,?,?,?,?,?)}");
						con.pst.setString(1, mr.getId());
						con.pst.setString(2, mr.getName());
						con.pst.setInt(3, mr.getGuest());
						con.pst.setString(4, mr.getContact());
						con.pst.setString(5, mr.getEmail());
						con.pst.setString(6, mr.getType());
						con.pst.setString(7, mr.getDate());
						con.pst.setString(8, mr.getTime());
						con.pst.setString(9, mr.getMeridem());
						con.pst.setString(10, roomid);
						con.pst.execute();
						RefreshForm();
						JOptionPane.showMessageDialog(null, "Reserve Successfully Added");
						con.pst.close();
						con.con.close();
		        	}else if(validateEmptyString(mr.getName()) == false){
						JOptionPane.showMessageDialog(null, "Empty field");						
					}else if(validateEmail(mr.getEmail()) == false){
						JOptionPane.showMessageDialog(null, "Invalid email");						
					}else{
						JOptionPane.showMessageDialog(null, "Invalid character");
					}
				} catch (SQLException | ParseException e1) {
					e1.printStackTrace();
				}
			
			}
		});
		btnSave.setContentAreaFilled(false);
		btnSave.setOpaque(false);
		btnSave.setIcon(new ImageIcon(ReservePanel.class.getResource("/Images/Icon/save.png")));
		btnSave.setName("ADD");
		pnlButtons.add(btnSave, "flowx,cell 0 0,alignx right");
		btnSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSave.setFont(new Font("Monospaced", Font.PLAIN, 15));
		DefaultButtonColor();
		if(rdbtnNormal.isSelected()){
			type = "Regular";
		}else{
			type = "Event";
		}
		ViewComboboxTime();
		ViewFloor();
		ViewNormalRoom();
		ViewAllTableSetText();
	}	
	
	public ModelReserve getFieldData() throws ParseException{
		ModelReserve mr = new ModelReserve();
		mr.setId(tfReserveId.getText());
		mr.setName(tfName.getText());
		mr.setContact(tfContactNo.getText());
		mr.setEmail(tfEmailAddress.getText());
		mr.setDate(new SimpleDateFormat("yyyy-MM-dd").format(dateChooser.getDate()));
		if(rdbtnNormal.isSelected()){
			mr.setType(rdbtnNormal.getText());			
		}else{
			mr.setType(rdbtnEvent.getText());			
		}
		mr.setGuest((int) spnCapacity.getValue());
		String newtime = (String) cbTime.getSelectedItem();
		String newmeridem = (String) cbTime.getSelectedItem();
		newtime = newtime.substring(0, 8);
		newmeridem = newmeridem.substring(9, 11);
		mr.setTime(ConvertReserveTime(newtime, newmeridem));
		mr.setMeridem(newmeridem);
		return mr;
	}
	
	//COLORS
	public Color getPanelColor(){
		return SystemColor.menu;
	}
	public Color getButtonBackgroundColor(){
        return new Color(27, 12, 95);
    }
	public Color getButtonHoverBackgroundColor(){
		return new Color(194, 253, 249);
	}
	public Color getButtonForegroundColor(){
		return new Color(215, 203, 0);
	}
	public Color getButtonHoverForegroundColor(){
		return Color.BLACK;
	}
	
	public void add(ActionListener add){
		btnSave.addActionListener(add);
	}
	
    public Color getTextFieldColor(){
		return SystemColor.controlHighlight;
    }    
	 public void setMinSelectableDate(Date min) {
	        dateChooser.setMinSelectableDate(min);
	        dateEditor.setMinSelectableDate(min);
	 }
    //COLORS
	public void DefaultButtonColor(){

		btnSave.setUI(new MetalButtonUI());
	}	
	public String ConvertReserveTime(String d_time){
 		if(d_time.equalsIgnoreCase("00:00:00")){
			d_time = "12:00:00";
		}else if(d_time.equalsIgnoreCase("00:30:00")){
			d_time = "12:30:00";
		}else if(d_time.equalsIgnoreCase("13:00:00")){
			d_time = "01:00:00";
		}else if(d_time.equalsIgnoreCase("13:30:00")){
			d_time = "01:30:00";						
		}else if(d_time.equalsIgnoreCase("14:00:00")){
			d_time = "02:00:00";						
		}else if(d_time.equalsIgnoreCase("14:30:00")){
			d_time = "02:30:00";						
		}else if(d_time.equalsIgnoreCase("15:00:00")){
			d_time = "03:00:00";						
		}else if(d_time.equalsIgnoreCase("15:30:00")){
			d_time = "03:30:00";						
		}else if(d_time.equalsIgnoreCase("16:00:00")){
			d_time = "04:00:00";						
		}else if(d_time.equalsIgnoreCase("16:30:00")){
			d_time = "04:30:00";						
		}else if(d_time.equalsIgnoreCase("17:00:00")){
			d_time = "05:00:00";						
		}else if(d_time.equalsIgnoreCase("17:30:00")){
			d_time = "05:30:00";						
		}else if(d_time.equalsIgnoreCase("18:00:00")){
			d_time = "06:00:00";						
		}else if(d_time.equalsIgnoreCase("18:30:00")){
			d_time = "06:30:00";						
		}else if(d_time.equalsIgnoreCase("19:00:00")){
			d_time = "07:00:00";						
		}else if(d_time.equalsIgnoreCase("19:30:00")){
			d_time = "07:30:00";						
		}else if(d_time.equalsIgnoreCase("20:00:00")){
			d_time = "08:00:00";						
		}else if(d_time.equalsIgnoreCase("20:30:00")){
			d_time = "08:30:00";						
		}else if(d_time.equalsIgnoreCase("21:00:00")){
			d_time = "09:00:00";						
		}else if(d_time.equalsIgnoreCase("21:30:00")){
			d_time = "09:30:00";						
		}else if(d_time.equalsIgnoreCase("22:00:00")){
			d_time = "10:00:00";						
		}else if(d_time.equalsIgnoreCase("22:30:00")){
			d_time = "10:30:00";						
		}else if(d_time.equalsIgnoreCase("23:00:00")){
			d_time = "11:00:00";						
		}else if(d_time.equalsIgnoreCase("23:30:00")){
			d_time = "11:30:00";					
		}			
	return d_time;
}

public String ConvertReserveTime(String d_time, String meridem){
	if(meridem.equalsIgnoreCase("AM")){
		if(d_time.equalsIgnoreCase("12:00:00")){
			d_time = "00:00:00";
		}else if(d_time.equalsIgnoreCase("12:30:00")){
			d_time = "00:30:00";						
		}
	}else if(meridem.equalsIgnoreCase("PM")){

		if(d_time.equalsIgnoreCase("12:00:00")){
			d_time = "12:00:00";
		}else if(d_time.equalsIgnoreCase("12:30:00")){
			d_time = "12:30:00";						
		}else if(d_time.equalsIgnoreCase("01:00:00")){
			d_time = "13:00:00";
		}else if(d_time.equalsIgnoreCase("01:30:00")){
			d_time = "13:30:00";						
		}else if(d_time.equalsIgnoreCase("02:00:00")){
			d_time = "14:00:00";						
		}else if(d_time.equalsIgnoreCase("02:30:00")){
			d_time = "14:30:00";						
		}else if(d_time.equalsIgnoreCase("03:00:00")){
			d_time = "15:00:00";						
		}else if(d_time.equalsIgnoreCase("03:30:00")){
			d_time = "15:30:00";						
		}else if(d_time.equalsIgnoreCase("04:00:00")){
			d_time = "16:00:00";						
		}else if(d_time.equalsIgnoreCase("04:30:00")){
			d_time = "16:30:00";						
		}else if(d_time.equalsIgnoreCase("05:00:00")){
			d_time = "17:00:00";						
		}else if(d_time.equalsIgnoreCase("05:30:00")){
			d_time = "17:30:00";						
		}else if(d_time.equalsIgnoreCase("06:00:00")){
			d_time = "18:00:00";						
		}else if(d_time.equalsIgnoreCase("06:30:00")){
			d_time = "18:30:00";						
		}else if(d_time.equalsIgnoreCase("07:00:00")){
			d_time = "19:00:00";						
		}else if(d_time.equalsIgnoreCase("07:30:00")){
			d_time = "19:30:00";						
		}else if(d_time.equalsIgnoreCase("08:00:00")){
			d_time = "20:00:00";						
		}else if(d_time.equalsIgnoreCase("08:30:00")){
			d_time = "20:30:00";						
		}else if(d_time.equalsIgnoreCase("09:00:00")){
			d_time = "21:00:00";						
		}else if(d_time.equalsIgnoreCase("09:30:00")){
			d_time = "21:30:00";						
		}else if(d_time.equalsIgnoreCase("10:00:00")){
			d_time = "22:00:00";						
		}else if(d_time.equalsIgnoreCase("10:30:00")){
			d_time = "22:30:00";						
		}else if(d_time.equalsIgnoreCase("11:00:00")){
			d_time = "23:00:00";						
		}else if(d_time.equalsIgnoreCase("11:30:00")){
			d_time = "23:30:00";					
		}			
	}
	return d_time;
}

	public void ViewComboboxTime(){
	ArrayList arrayTime = new ArrayList<String>();
	try{
		int i = 0;
		Connect c = new Connect();
		c.pst = c.con.prepareCall("{call select_table_time(?)}");
		c.pst.setString(1, type);
		c.pst.execute();
		c.rs = c.pst.getResultSet();
		while (c.rs.next()) { 
			String time = c.rs.getString("Time");
			String meridem = c.rs.getString("Meridem");
			arrayTime.add(ConvertReserveTime(time) +  " " +  meridem);
		}
		c.rs.close();
		c.pst.close();
		c.con.close();
		DefaultComboBoxModel<String> mpTime = new DefaultComboBoxModel(arrayTime.toArray());
		cbTime.setModel(mpTime);
	}catch(Exception ed){
		ed.printStackTrace();
	}			
}
 	//end of Viewing the category method

	//Viewing the floor method
 	public void ViewFloor(){
		ArrayList arrayFloorId = new ArrayList<String>();
		try{
			Connect c = new Connect();
			c.pst = c.con.prepareCall("{call select_cb_floor_plan()}");
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			while (c.rs.next()) { 
				String floorid = c.rs.getString("plan_name");
				arrayFloorId.add(floorid);
			}
			c.rs.close();
			c.pst.close();
			c.con.close();
			DefaultComboBoxModel<String> mpFloorId = new DefaultComboBoxModel(arrayFloorId.toArray());
			cbFloor.setModel(mpFloorId); 
		}catch(Exception ed){
			ed.printStackTrace();
		}			
	}
 	//end of Viewing the floor

	//Viewing the room method
 	public void ViewNormalRoom(){
		dateChooser.setMinSelectableDate(new Date());
		dateChooser.setDate(new Date());
		ArrayList arrayRoomName = new ArrayList<String>();
		try{
			Connect c = new Connect();
			c.pst = c.con.prepareCall("{call select_cb_normal_room_via_floor_name(?)}");
			c.pst.setString(1, (String) cbFloor.getSelectedItem());
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			boolean found = false;
			while (c.rs.next()) { 
				found  = true;
				String roomname = c.rs.getString("Name");
				arrayRoomName.add(roomname);
			}
			if(!found){
				lblAvailableCapacityNormal.setText(String.valueOf(0));
			}
			c.rs.close();
			c.pst.close();
			c.con.close();
			DefaultComboBoxModel<String> mpRoomName = new DefaultComboBoxModel(arrayRoomName.toArray());
			cbRoom.setModel(mpRoomName); 
		}catch(Exception ed){
			ed.printStackTrace();
		}			
	}
 	//end of Viewing the room
 	public void ViewEventRoom(){
        Calendar cal = Calendar.getInstance();
        cal .add(Calendar.DATE, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		dateChooser.setMinSelectableDate(cal.getTime());
		dateChooser.setDate(cal.getTime());
 		usedroom = null;
		roomfound = false;
		ArrayList arrayRoomName = new ArrayList<String>();
		try{
			time = (String) cbTime.getSelectedItem();
			Connect c = new Connect();
			c.pst = c.con.prepareCall("{call select_cb_event_room_via_and_date_and_time(?,?,?,?)}");
			c.pst.setString(1,  new SimpleDateFormat("yyyy-MM-dd").format(dateChooser.getDate()));
			c.pst.setString(2, ConvertReserveTime(time.substring(0, 8), time.substring(9, 11)));
			c.pst.setString(3, time.substring(9, 11));
			c.pst.setString(4, (String) cbFloor.getSelectedItem());
			
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			while(c.rs.next()){
				roomfound = true;
				usedroom = c.rs.getString("Name");
			}
			c.rs.close();
			c.pst.close();
			c.pst = c.con.prepareCall("{call select_cb_event_room_via_floor_name(?)}");
			c.pst.setString(1, (String) cbFloor.getSelectedItem());
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			while (c.rs.next()) { 
				String roomname = c.rs.getString("Name");
				if(roomfound){	
					if(!(usedroom.equals(roomname))){
						arrayRoomName.add(roomname);
					}
				}else{
					arrayRoomName.add(roomname);					
				}
				lblAvailableCapacityNormal.setText(String.valueOf(0));
			}
			c.rs.close();
			c.pst.close();
			c.con.close();
			DefaultComboBoxModel<String> mpRoomName = new DefaultComboBoxModel(arrayRoomName.toArray());
			cbRoom.setModel(mpRoomName); 
		}catch(Exception ed){
			ed.printStackTrace();
		}			
	}
 	//end of Viewing the room

 	public static void ViewAllTableSetText(){
 		try{
 			boolean found = false;
 	 		ViewGetPlanIdOfRoom();
 			Connect c = new Connect();
			c.pst = c.con.prepareCall("{call sum_all_capacity(?,?)}");
			c.pst.setString(1, roomid);
			c.pst.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(dateChooser.getDate()));
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			roomcapacityminus = 0;
			while (c.rs.next()) { 
				roomcapacityminus = c.rs.getInt("Capacity");
			}
			c.rs.close();
			c.pst.close();
			c.con.close();
			c = new Connect();
			c.pst = c.con.prepareCall("{call select_all_room_plan(?)}"); 
			c.pst.setString(1, roomid);
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			String roomname = null;
			String roomdesc = null;
			int roomcapacity = 0;  
			while (c.rs.next()) { 
				found = true;
				roomname = c.rs.getString("room_name");
				roomdesc = c.rs.getString("room_area");
				roomcapacity = c.rs.getInt("room_capacity");
			}
			try {

				if(!found){
					spnCapacity.setModel(new SpinnerNumberModel(0, 0, (int) roomcapacity - roomcapacityminus, 1));
					lblAvailableCapacityNormal.setText("0");
				}else{
					spnCapacity.setModel(new SpinnerNumberModel(1, 1, (int) roomcapacity - roomcapacityminus, 1));				
					lblAvailableCapacityNormal.setText(String.valueOf(roomcapacity - roomcapacityminus));
				}
			} catch (Exception e) {
			}
			c.rs.close();
			c.pst.close();
			c.con.close();
		}catch(Exception ed){
			ed.printStackTrace();
		}			
 	}
 	public static void ViewGetPlanIdOfRoom(){
		//get plan_id from room
		try {

			Connect c;
				c = new Connect();
				c.pst = c.con.prepareCall("{call select_id_room_with_name(?)}");
				c.pst.setString(1, (String) cbRoom.getSelectedItem());
				c.pst.execute();
				c.rs = c.pst.getResultSet();
				while(c.rs.next()){
					roomid = c.rs.getString("room_id");
					roomtype = c.rs.getString("section_type");
				}
				c.rs.close();
				c.pst.close();
				c.con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
 	}
 	
 	public void RefreshForm(){
 		
		Selection.ret.ViewEventReservation();
		Selection.ret.ViewNormalReservation();
		POSDesign.ret.CounterReserve();
		POSDesign.SetTables();
		POSDesign.ViewDecorate();
		POSDesign.clock.suspend();
		POSDesign.ViewOccupied();	
		POSDesign.tabbedPane.setSelectedIndex(0);
		tfName.setText(null);
		tfContactNo.setText(null);
		tfEmailAddress.setText(null);
		rdbtnNormal.setSelected(true);
		
 	}

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
   
   public String  validateDecimal(String number){//Method
	      String pattern = "[0-9]+([,.][0-9]{1,2})?";
	      Pattern r = Pattern.compile(pattern);
	      Matcher m = r.matcher(number);
	      if (m.find( )) {
	    	 return  m.group(0);
	      }else {
	    	  return number;
	      }
   }//Method

	public boolean validateEmail(String mail){ //Method for checking email
		if(EmailValidator.getInstance().isValid(mail)){
			return true;			
		}else {
			return false;
		}
	}//Method
}
