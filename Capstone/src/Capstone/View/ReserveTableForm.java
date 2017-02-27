package Capstone.View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.plaf.metal.MetalButtonUI;

import com.toedter.calendar.IDateEditor;

import Capstone.Database.Connect;
import Capstone.Model.ModelReserve;
import net.miginfocom.swing.MigLayout;

public class ReserveTableForm extends JDialog {
	public static IDateEditor dateEditor;
	public JTextField tfReserveId;
	JTextField tfName;
	public JButton btnSave;
	public JButton btnCancel;
	public JLabel lblContactNo;
	public JLabel lblEmailAddress;
	JTextField tfContactNo;
	JTextField tfEmailAddress;
	public JLabel lblDateOfReserve;
	public JLabel lblTimeReserved;
	public JLabel lblReserveId;
	public JLabel lblName;
	public JLabel lblReservationType;
	public final ButtonGroup bgType = new ButtonGroup();
	public JLabel lblFieldsWith;
	public JPanel pnlForm;
	public JPanel pnlTables;
	public JTextField txtTables;
	public JTextField txtReserveForm;
	public JPanel pnlButtons;
	JPanel frame;
	public JScrollPane scrollPane;
	public JTextField txtCapacity;
	public JLabel lblTable;
	public JSpinner spnTable;
	public JLabel lblTotalCapacityAccumulated;
	public JTextField tfTableCapacity;
	public JTextField tfDate;
	public JTextField tfTime;
	public JTextField tfType;
	private JLabel label;
	JTextField tfCapacity;
	protected ModelReserve mr;
	protected Connect con;
	protected Calendar calStart;
	protected Calendar calEnd;
	protected Calendar current;
	protected SimpleDateFormat stfcurrent;
	/**
	 * Create the panel.
	 */
	public ReserveTableForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				POSDesign.tblEventReserve.clearSelection();
				POSDesign.tblNormalReserve.clearSelection();
			}
		});
		setResizable(false);
		setModal(true);
		setTitle("Reserve");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame = new JPanel();
		frame.setBorder(new LineBorder(new Color(0, 0, 0)));
		frame.setLayout(new MigLayout("", "[][grow]", "[][][grow][]"));
		
		getContentPane().add(frame);
		lblFieldsWith = new JLabel("Fields with (*) are required");
		lblFieldsWith.setFont(new Font("Monospaced", Font.PLAIN, 12));
		frame.add(lblFieldsWith, "cell 0 0 2 1,alignx center");
		
		txtTables = new JTextField();
		txtTables.setEnabled(false);
		txtTables.setHorizontalAlignment(SwingConstants.CENTER);
		txtTables.setText("TABLE");
		txtTables.setFont(new Font("Monospaced", Font.PLAIN, 15));
		txtTables.setEditable(false);
		txtTables.setColumns(10);
		frame.add(txtTables, "flowx,cell 0 1,growx");
		
		txtReserveForm = new JTextField();
		txtReserveForm.setEnabled(false);
		txtReserveForm.setHorizontalAlignment(SwingConstants.CENTER);
		txtReserveForm.setText("RESERVE FORM");
		txtReserveForm.setFont(new Font("Monospaced", Font.PLAIN, 15));
		txtReserveForm.setEditable(false);
		txtReserveForm.setColumns(10);
		frame.add(txtReserveForm, "cell 1 1,growx");
		
		scrollPane = new JScrollPane();
		frame.add(scrollPane, "cell 0 2,grow");
		
		pnlTables = new JPanel();
		scrollPane.setViewportView(pnlTables);
		pnlTables.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlTables.setLayout(new MigLayout("", "[grow][grow]", "[][]"));
		
		pnlForm = new JPanel();
		pnlForm.setBorder(new LineBorder(new Color(0, 0, 0)));
		frame.add(pnlForm, "cell 1 2,grow");
		pnlForm.setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][]"));
		
		lblReserveId = new JLabel("Reserve ID:");
		pnlForm.add(lblReserveId, "cell 0 0");
		lblReserveId.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		tfReserveId = new JTextField();
		pnlForm.add(tfReserveId, "cell 1 0,growx");
		tfReserveId.setEditable(false);
		tfReserveId.setText("R0000001");
		tfReserveId.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfReserveId.setColumns(10);
		
		lblName = new JLabel("Name*:");
		pnlForm.add(lblName, "cell 0 1");
		lblName.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		tfName = new JTextField();
		tfName.setText("Beejay Mundoc");
		tfName.setEditable(false);
		pnlForm.add(tfName, "cell 1 1,growx");
		tfName.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfName.setColumns(10);
		
		lblContactNo = new JLabel("Contact no*:");
		pnlForm.add(lblContactNo, "cell 0 2");
		lblContactNo.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		tfContactNo = new JTextField();
		tfContactNo.setText("09178096040");
		tfContactNo.setEditable(false);
		pnlForm.add(tfContactNo, "cell 1 2,growx");
		tfContactNo.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfContactNo.setColumns(10);
		
		lblEmailAddress = new JLabel("Email Address:");
		pnlForm.add(lblEmailAddress, "cell 0 3");
		lblEmailAddress.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		tfEmailAddress = new JTextField();
		tfEmailAddress.setText("bjmundoc@gmail.com");
		tfEmailAddress.setEditable(false);
		pnlForm.add(tfEmailAddress, "cell 1 3,growx");
		tfEmailAddress.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfEmailAddress.setColumns(10);
		
		label = new JLabel("Capacity:");
		label.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlForm.add(label, "cell 0 4");
		
		tfCapacity = new JTextField();
		tfCapacity.setText("6");
		tfCapacity.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfCapacity.setEditable(false);
		tfCapacity.setColumns(10);
		pnlForm.add(tfCapacity, "cell 1 4,growx");
		
		lblTotalCapacityAccumulated = new JLabel("Table Capacity:");
		lblTotalCapacityAccumulated.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlForm.add(lblTotalCapacityAccumulated, "cell 0 5");
		
		tfTableCapacity = new JTextField();
		tfTableCapacity.setText("6");
		tfTableCapacity.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfTableCapacity.setEditable(false);
		tfTableCapacity.setColumns(10);
		pnlForm.add(tfTableCapacity, "cell 1 5,growx,aligny top");
		
		lblDateOfReserve = new JLabel("Date reserved:");
		pnlForm.add(lblDateOfReserve, "cell 0 6");
		lblDateOfReserve.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		tfDate = new JTextField();
		tfDate.setText("2016-09-08");
		tfDate.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfDate.setEditable(false);
		tfDate.setColumns(10);
		pnlForm.add(tfDate, "cell 1 6,growx");
		
		lblTimeReserved = new JLabel("Time reserved:");
		pnlForm.add(lblTimeReserved, "cell 0 7");
		lblTimeReserved.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		lblReservationType = new JLabel("Reservation Type:");
		pnlForm.add(lblReservationType, "cell 0 8");
		lblReservationType.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		tfTime = new JTextField();
		tfTime.setText("04:30:00 PM");
		tfTime.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfTime.setEditable(false);
		tfTime.setColumns(10);
		pnlForm.add(tfTime, "cell 1 7,growx");
		
		tfType = new JTextField();
		tfType.setText("Normal");
		tfType.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfType.setEditable(false);
		tfType.setColumns(10);
		pnlForm.add(tfType, "cell 1 8,growx");
		
		pnlButtons = new JPanel();
		pnlButtons.setBorder(new LineBorder(new Color(0, 0, 0)));
		frame.add(pnlButtons, "cell 0 3 2 1,grow");
		pnlButtons.setLayout(new MigLayout("", "[grow]", "[]"));
		
		btnSave = new JButton();
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		        try {
	        	mr = getFieldData();
				con = new Connect();								
				calStart = Calendar.getInstance();
				calStart.setTime(new SimpleDateFormat("HH:mm:ss").parse(mr.getTime()));
				SimpleDateFormat sdfStart = new SimpleDateFormat("HH:mm:ss");
				
				calEnd = Calendar.getInstance();
				calEnd.setTime(new SimpleDateFormat("HH:mm:ss").parse(mr.getTime()));
				calEnd .add(Calendar.MINUTE, 60);
				SimpleDateFormat sdfEnd = new SimpleDateFormat("HH:mm:ss");
				
				current = Calendar.getInstance();
				current.setTime(new SimpleDateFormat("HH:mm:ss").parse(mr.getTime()));
				current .add(Calendar.MINUTE, 30);
				stfcurrent = new SimpleDateFormat("HH:mm:ss");
				for(int i = 0 ; i < POSDesign.i; i++){	
					System.out.println(POSDesign.tablevalueid[i]);
						if(POSDesign.tablevalueid[i] != null){
							System.out.println(i + " yea");
							con.pst = con.con.prepareCall("{call insert_reserve_desks(?,?,?,?,?,?,?,?)}");
							con.pst.setString(1, mr.getId());
							con.pst.setString(2, POSDesign.tablevalueid[i]);
							con.pst.setString(3, mr.getDate());
							con.pst.setString(4, sdfStart.format(calStart.getTime()));		//time end
							con.pst.setString(5,  stfcurrent.format(current.getTime()));
							con.pst.setString(6, sdfEnd.format(calEnd.getTime()));		//time end
							con.pst.setString(7, "Occupied");
							con.pst.setInt(8, (int) POSDesign.spnTable[i].getValue());
							con.pst.execute();
						}
				}
				con.pst = con.con.prepareCall("{call seat_reserve(?,?)}");
				con.pst.setString(1, mr.getId());
				con.pst.setString(2,  stfcurrent.format(current.getTime()));
				con.pst.execute();
				
				Selection.ret.ViewEventReservation();
				Selection.ret.ViewNormalReservation();
				Selection.ret.ViewDinein();
				
				
				POSDesign.SetTables();
				POSDesign.ViewDecorate();
				POSDesign.clock.suspend();
				POSDesign.ViewOccupied();
				dispose();
				} catch (ParseException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnSave.setContentAreaFilled(false);
		btnSave.setOpaque(false);
		btnSave.setIcon(new ImageIcon(ReserveTableForm.class.getResource("/Images/Icon/selecttable.png")));
		btnSave.setName("ADD");
		pnlButtons.add(btnSave, "flowx,cell 0 0,alignx right");
		btnSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSave.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		btnCancel = new JButton("");
		btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancel.setIcon(new ImageIcon(ReserveTableForm.class.getResource("/Images/Icon/exit.png")));
		btnCancel.setContentAreaFilled(false);
		btnCancel.setOpaque(false);
		pnlButtons.add(btnCancel, "cell 0 0");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				POSDesign.tblEventReserve.clearSelection();
				POSDesign.tblNormalReserve.clearSelection();
			}
		});
		btnCancel.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		txtCapacity = new JTextField();
		txtCapacity.setText("CAPACITY");
		txtCapacity.setHorizontalAlignment(SwingConstants.CENTER);
		txtCapacity.setFont(new Font("Monospaced", Font.PLAIN, 15));
		txtCapacity.setEnabled(false);
		txtCapacity.setEditable(false);
		txtCapacity.setColumns(10);
		frame.add(txtCapacity, "cell 0 1,growx");
		DefaultButtonColor();
		

	}	
	
	public ModelReserve getFieldData() throws ParseException{
		ModelReserve mr = new ModelReserve();
		mr.setId(tfReserveId.getText());
		mr.setName(tfName.getText());
		mr.setContact(tfContactNo.getText());
		mr.setEmail(tfEmailAddress.getText());
		mr.setDate(tfDate.getText());
		mr.setType(tfType.getText());
		mr.setGuest(Integer.parseInt(tfCapacity.getText()));
		mr.setTime(new SimpleDateFormat("HH:mm:ss").format(new Date()));
//		mr.setMeridem(lblMeridem.getText());
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
//	        dateChooser.setMinSelectableDate(min);
	        dateEditor.setMinSelectableDate(min);
	 }
    //COLORS
	public void DefaultButtonColor(){

		btnSave.setUI(new MetalButtonUI());
		btnCancel.setUI(new MetalButtonUI());
	}	

	public String ConvertReserveTime(String d_time, String meridem){
		if(meridem.equalsIgnoreCase("AM")){
			if(d_time.equalsIgnoreCase("12:00:00")){
				d_time = "00:00:00";
			}else if(d_time.equalsIgnoreCase("12:30:00")){
				d_time = "00:30:00";						
			}
		}else if(meridem.equalsIgnoreCase("PM")){
			if(d_time.equalsIgnoreCase("01:00:00")){
				d_time = "12:00:00";
			}else if(d_time.equalsIgnoreCase("01:30:00")){
				d_time = "12:30:00";						
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
}
