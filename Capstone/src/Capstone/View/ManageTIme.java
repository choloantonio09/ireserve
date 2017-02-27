package Capstone.View;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import Capstone.Controller.UpdateController;
import Capstone.Database.Connect;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.metal.MetalButtonUI;
import javax.swing.table.TableModel;

import java.awt.Color;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import javax.swing.ImageIcon;

public class ManageTIme extends JPanel implements ListSelectionListener{
	private JComboBox cbTime;
	private JLabel lblTime;
	private JTable tblTime;
	private JPanel pnlTimeTable;
	private JPanel pnlButtons;
	private JButton btnAdd;
	private JButton btnCancel;
	private JButton btnUpdate;
	private JRadioButton rdbtnEvent;
	private final ButtonGroup bgType = new ButtonGroup();
	protected String type;
	protected String time;
	protected String converttime;
	protected String meridem;
	protected Connect con;
	private JRadioButton rdbtnRegular;
	protected String timetype;
	protected boolean found;
	private String id;
	private JLabel lblStatus;
	private JComboBox cbStatus;
	private String status;
	private String tempTime;

	/**
	 * Create the panel.
	 */
	public ManageTIme() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(new MigLayout("", "[grow]", "[][][grow]"));
		
		JPanel panel = new JPanel();
		add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[][300px]", "[][][]"));
		
		lblTime = new JLabel("Time:");
		lblTime.setFont(new Font("Monospaced", Font.PLAIN, 15));
		panel.add(lblTime, "cell 0 0");
		
		cbTime = new JComboBox();
		cbTime.setFont(new Font("Monospaced", Font.PLAIN, 15));
		panel.add(cbTime, "cell 1 0,growx");
		
		JLabel lblType = new JLabel("Type:");
		lblType.setFont(new Font("Monospaced", Font.PLAIN, 15));
		panel.add(lblType, "cell 0 1");

		rdbtnRegular = new JRadioButton("Regular");
		rdbtnRegular.setSelected(true);
		rdbtnRegular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type = "Regular";
				ViewTableTime();
			}
		});
		bgType.add(rdbtnRegular);
		rdbtnRegular.setFont(new Font("Monospaced", Font.PLAIN, 15));
		panel.add(rdbtnRegular, "cell 1 1");
		
		rdbtnEvent = new JRadioButton("Event");
		rdbtnEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type = "Event";
				ViewTableTime();
			}
		});
		bgType.add(rdbtnEvent);
		rdbtnEvent.setFont(new Font("Monospaced", Font.PLAIN, 15));
		panel.add(rdbtnEvent, "cell 1 1");
		
		lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Monospaced", Font.PLAIN, 15));
		panel.add(lblStatus, "cell 0 2");
		
		cbStatus = new JComboBox();
		cbStatus.setModel(new DefaultComboBoxModel(new String[] {"ACTIVE", "INACTIVE"}));
		cbStatus.setFont(new Font("Monospaced", Font.PLAIN, 15));
		panel.add(cbStatus, "cell 1 2,growx");
		
		pnlButtons = new JPanel();
		add(pnlButtons, "cell 0 1,grow");
		pnlButtons.setLayout(new MigLayout("", "[][][]", "[]"));
		
		btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				time = (String) cbTime.getSelectedItem();
				converttime = ConvertReserveTime(time.substring(0, 8), time.substring(9, 11));
				meridem = time.substring(9, 11);
				if(rdbtnEvent.isSelected()){
					timetype = "Event";
				}else if(rdbtnRegular.isSelected()){
					timetype = "Regular	";	
				}
				found = false;
				try{
					int i = 0;
					Connect c = new Connect();
					c.pst = c.con.prepareCall("{call select_table_time(?)}");
					c.pst.setString(1, timetype.trim());
					c.pst.execute();
					c.rs = c.pst.getResultSet();
					while (c.rs.next()) { 
						String d_time = c.rs.getString("Time");
						if(d_time.equals(converttime)){
							found = true;
							break;
						}else{
							found = false;
						}
					}
					c.rs.close();
					c.pst.close();
					c.con.close();
				}catch(Exception ed){
					ed.printStackTrace();
				}		
				if(found == false){
					try {
							con = new Connect();
							con.pst = con.con.prepareCall("{call insert_time(?,?,?,?)}");
							con.pst.setString(1, converttime);
							con.pst.setString(2, meridem);
							con.pst.setString(3, timetype.trim());	
							con.pst.setString(4, (String) cbStatus.getSelectedItem());					
							con.pst.execute();
							RefreshForm();
							JOptionPane.showMessageDialog(null, "Successfully Add Time");
							con.pst.close();
							con.con.close();	
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(null, time + " already exist.");
				}
			}
		});
		btnAdd.setIcon(new ImageIcon(ManageTIme.class.getResource("/Images/Icon/plus.png")));
		btnAdd.setFont(new Font("Monospaced", Font.PLAIN, 15));
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdd.setContentAreaFilled(false);
		pnlButtons.add(btnAdd, "cell 0 0");
		
		btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				time = (String) cbTime.getSelectedItem();
				converttime = ConvertReserveTime(time.substring(0, 8), time.substring(9, 11));
				meridem = time.substring(9, 11);
				if(rdbtnEvent.isSelected()){
					timetype = "Event";
				}else if(rdbtnRegular.isSelected()){
					timetype = "Regular	";	
				}

				found = false;
				try{
					int i = 0;
					Connect c = new Connect();
					c.pst = c.con.prepareCall("{call select_table_time(?)}");
					c.pst.setString(1, timetype.trim());
					c.pst.execute();
					c.rs = c.pst.getResultSet();
					while (c.rs.next()) { 
						String d_time = c.rs.getString("Time");
						if(d_time.equals(converttime) && !(tempTime.equalsIgnoreCase(converttime))){
							found = true;
							break;
						}else{
							found = false;
						}
					}
					c.rs.close();
					c.pst.close();
					c.con.close();
				}catch(Exception ed){
					ed.printStackTrace();
				}		
				if(found == false){
					try {
							con = new Connect();
							con.pst = con.con.prepareCall("{call update_time(?,?,?,?,?)}");
							con.pst.setString(1, id);
							con.pst.setString(2, converttime);
							con.pst.setString(3, meridem);
							con.pst.setString(4, timetype.trim());	
							con.pst.setString(5, (String) cbStatus.getSelectedItem());					
							con.pst.execute();
							RefreshForm();
							JOptionPane.showMessageDialog(null, "Successfully Update Time");
							con.pst.close();
							con.con.close();	
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(null, time + " already exist.");
				}
			}
		});
		btnUpdate.setIcon(new ImageIcon(ManageTIme.class.getResource("/Images/Icon/update.png")));
		btnUpdate.setEnabled(false);
		btnUpdate.setFont(new Font("Monospaced", Font.PLAIN, 15));
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setContentAreaFilled(false);
		pnlButtons.add(btnUpdate, "cell 1 0");
		
		btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RefreshForm();
			}
		});
		btnCancel.setIcon(new ImageIcon(ManageTIme.class.getResource("/Images/Icon/cancel.png")));
		btnCancel.setEnabled(false);
		btnCancel.setFont(new Font("Monospaced", Font.PLAIN, 15));
		btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancel.setContentAreaFilled(false);
		pnlButtons.add(btnCancel, "cell 2 0");
		
		pnlTimeTable = new JPanel();
		add(pnlTimeTable, "cell 0 2,grow");
		pnlTimeTable.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JScrollPane spTimeTable = new JScrollPane();
		pnlTimeTable.add(spTimeTable, "cell 0 0,grow");
		
		tblTime = new JTable();
		spTimeTable.setViewportView(tblTime);
		btnAdd.setUI(new MetalButtonUI());
		btnUpdate.setUI(new MetalButtonUI());
		btnCancel.setUI(new MetalButtonUI());
		RefreshForm();
		ListSelectionModel selectionModel = tblTime.getSelectionModel();		
		selectionModel.addListSelectionListener( this );
	}

	public void RefreshForm(){
		if(rdbtnEvent.isSelected()){
			type = "Event";
		}else if(rdbtnRegular.isSelected()){
			type = "Regular";
		}
		ViewComboboxTime();
		ViewTableTime();
		btnAdd.setEnabled(true);
		btnUpdate.setEnabled(false);
		btnCancel.setEnabled(false);
	}
 	public void ViewComboboxTime(){
		ArrayList arrayTime = new ArrayList<String>();
		try{
			int i = 0;
			Connect c = new Connect();
			c.pst = c.con.prepareCall("{call select_desk_time(?)}");
			c.pst.setString(1, "ACTIVE");
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			while (c.rs.next()) { 
				String time = c.rs.getString("desk_time_value");
				String meridem = c.rs.getString("desk_time_meridem");
				arrayTime.add(ConvertReserveTime(time) +  " " +  meridem);
				i++;
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
 	public void ViewTableTime(){
		try{
			int i = 0;
			Connect c = new Connect();
			c.pst = c.con.prepareCall("{call select_table_time(?)}");
			c.pst.setString(1, type);
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			tblTime.setModel(DbUtils.resultSetToTableModel(c.rs));
			c.rs.close();
			c.pst.close();
			c.con.close();
		}catch(Exception ed){
			ed.printStackTrace();
		}			
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

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if( e.getSource() == tblTime.getSelectionModel() && e.getFirstIndex() >= 0 ){
			TableModel model = (TableModel)tblTime.getModel();
			int row = tblTime.getSelectedRow();
			tblTime.getSelectedColumn();
			if(row!=-1){
				id = model.getValueAt(row, 0).toString();
				tempTime = ConvertReserveTime(model.getValueAt(row, 1).toString());
				cbTime.setSelectedItem(ConvertReserveTime(model.getValueAt(row, 1).toString()) + " " + model.getValueAt(row, 2).toString());
				status = model.getValueAt(row, 3).toString();
				cbStatus.setSelectedItem(status);
				btnAdd.setEnabled(false);
				btnUpdate.setEnabled(true);
				btnCancel.setEnabled(true);
			}
		}
		
	}
}
