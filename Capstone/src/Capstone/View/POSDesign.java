package Capstone.View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.metal.MetalButtonUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.toedter.calendar.IDateEditor;
import com.toedter.calendar.JDateChooser;

import Capstone.Controller.AddController;
import Capstone.Controller.RemoveController;
import Capstone.Database.Connect;
import Capstone.Model.CalendarModel;
import Capstone.Model.CountDinein;
import Capstone.Model.CountTakeout;
import Capstone.Model.CounterNormalOrder;
import Capstone.Model.ModelSales;
import Capstone.Model.ModelSalesEventOrder;
import Capstone.Table.EventSalesReserveTable;
import Capstone.Table.NormalSalesTable;
import Capstone.Table.ReservationTable;
import Capstone.Table.ReserveTable;
import net.miginfocom.swing.MigLayout;
import net.proteanit.sql.DbUtils;

public class POSDesign extends JPanel implements ListSelectionListener{
	public static IDateEditor dateEditor;
	private JPanel panel;
	static JTabbedPane tabbedPane;
	private JButton btnNewButton;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JTabbedPane tabbedPane_1;
	private JPanel pnlSearch;
	private static JPanel pnlTables;
	private JPanel panel_8;
	private JPanel panel_9;
	public static JTable tblNormalReserve;
	private ReservePanel rp;
	private JScrollPane scrollPane;
	private JLabel lblCapacity;
	private static JLabel lblRemaining;
	private static JLabel lblMainRoom;
	private JPanel panel_13;
	private JPanel pnlEvent;
	public static JTable tblEventReserve;
	private JScrollPane scrollPane_1;
	public JTable tblDinein;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	static ReserveTable ret;
	private String ridEvent;
	private String rnameEvent;
	private String ridNormal;
	private String rnameNormal;
	private String rcontactNormal;
	private String remailNormal;
	private String rcapacityNormal;
	private String rdateNormal;
	private String rtimeNormal;
	static int m;
	protected static String did;
	protected static boolean[] select;
	protected static String[] countString;
	protected static String[] confirmstatus;
	
	public static JToggleButton btnDynamic[];
	public static int i = 0;
	private static String[] tableid;
	private static String[] tabledesc;
	static String[] tablecapacity;
	private static int y;
	private static int x;
	public static Thread clock;
	private static int iDesk;
	private static String[] buttonid;
	private static String[] deskid;
	private static String[] deskdesc;
	private static String[] deskname;
	private static String[] deskdate;
	private static String[] desktime;
	private static String[] deskstatus;
	private static int[] capacity;
	private static String[] is_extended;
	private static String[] desk;
	private static Date[] date;
	private static SimpleDateFormat[] sdfIn;
	private static SimpleDateFormat[] sdfOut;
	private static Date[] m_Date;
	private static String[] description;
	private static boolean[] selecttable;
	public static String[] tablevalue;
	public static String[] tablevalueid;
	private JLabel lblFloor;
	private JLabel lblRoom;
	private JComboBox cbFloor;
	private static JComboBox cbRoom;
	public static String roomid;
	private static Thread thr;
	private static Thread thrGetPlanIdOfRoom;
	private JButton btnOrder;
	protected String customername = "Guest";
	protected static String[] figure;
	private static int roomcapacityminus;
	public static JLabel[] lblTable;
	public static JSpinner[] spnTable;
	private static String roomtype;
	private static int[] deskcapacity;
	public static int spnTableCapacity;
	private static int[] minuscapacity;
	private static JDateChooser dateChooser;
	private static boolean foundButton;
	public JRadioButton rdbtnRegular;
	private JRadioButton rdbtnEvent;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel panel_15;
	private JLabel lblType;
	private JPanel panel_16;
	private JLabel lblReserveId;
	private JLabel labelReserveId;
	private JLabel lblName;
	private JLabel labelName;
	private JLabel lblContact;
	private JLabel labelContact;
	private JLabel labelEmail;
	private JLabel lblEmail;
	private JLabel lblGuest;
	private JLabel labelGuest;
	private JLabel labelStatus;
	private JButton btnStartEvent;
	private JLabel lblStatus;
	private String eventreserveid;
	private String eventroomid;
	private String eventname;
	private String eventcontact;
	private String eventemail;
	private String eventguest;
	private String eventdate;
	private String eventtime;
	private String eventstatus;
	private String event_is_extended;
	private String eventdesk;
	private Object _eventdate;
	private SimpleDateFormat eventsdfIn;
	private SimpleDateFormat eventsdfOut;
	private Date event_m_Date;
	protected String count;
	private Thread clockEvent;
	private JButton btnOrderEvent;
	private boolean found;
	private JButton btnViewOrders;
	private JPanel pnlButtons;
	private String total;
	private String orderid;
	private String orderstatus;
	private JButton btnCancelReservation;
	private JButton btnSearch;
	private boolean eventFound;
	private static JLabel lblNoTablesIn;
	private JLabel lblThereAreNo;
	private String eventreserveroom = null;

	/**
	 * Create the panel.
	 */
	public POSDesign() {

		setLayout(new MigLayout("", "[89px][831px,grow]", "[743px,grow]"));
		panel_9 = new JPanel();
		panel_9.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(panel_9, "cell 0 0,grow");
		panel_9.setLayout(new MigLayout("", "[34px][35px]", "[39px][30px][168px][30px][189px]"));
		
		btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main.displayPanel.removeAll();
				Selection s = new Selection();
				Main.displayPanel.add(s, "cell 0 0 3 1,grow");
				Main.displayPanel.repaint();
				Main.displayPanel.revalidate();
				Main.frame.setSize(850,650);
				Main.frame.setLocationRelativeTo(null);
			}
		});
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setOpaque(false);
		btnNewButton.setContentAreaFilled(false);
		panel_9.add(btnNewButton, "cell 0 0 2 1,growx,aligny top");
		btnNewButton.setIcon(new ImageIcon(POSDesign.class.getResource("/Images/Icon/home.PNG")));
		
		JLabel label = new JLabel("<html>R<br>E<br>S<br>E<br>R<br>V<br>E<br>D</html>");
		label.setFont(new Font("Monospaced", Font.PLAIN, 15));
		panel_9.add(label, "cell 1 4,alignx center,aligny center");
		
		JLabel label_1 = new JLabel("<html>P<br>R<br>E<br>P<br>A<br>R<br>I<br>N<br>G</html>");
		label_1.setFont(new Font("Monospaced", Font.PLAIN, 15));
		panel_9.add(label_1, "cell 0 4,alignx center,aligny top");
		
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(true);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(new Color(166, 161, 18));
		panel_9.add(panel_1, "cell 1 3,grow");
		
		JPanel panel_10 = new JPanel();
		panel_10.setOpaque(true);
		panel_10.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_10.setBackground(new Color(178, 73, 55));
		panel_9.add(panel_10, "cell 0 3,grow");
		panel_10.setLayout(new MigLayout("", "[]", "[]"));
		
		JLabel label_2 = new JLabel("<html>V<br>A<br>C<br>A<br>N<br>T</html>");
		label_2.setFont(new Font("Monospaced", Font.PLAIN, 15));
		panel_9.add(label_2, "cell 1 2,alignx center,aligny center");
		
		JLabel label_3 = new JLabel("<html>O<br>C<br>C<br>U<br>P<br>I<br>E<br>D</html>");
		label_3.setFont(new Font("Monospaced", Font.PLAIN, 15));
		panel_9.add(label_3, "cell 0 2,alignx center,aligny top");
		
		JPanel panel_11 = new JPanel();
		panel_11.setOpaque(true);
		panel_11.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_11.setBackground(new Color(35, 182, 90));
		panel_9.add(panel_11, "cell 0 1,grow");
		panel_11.setLayout(new MigLayout("", "[]", "[]"));
		
		JPanel panel_12 = new JPanel();
		panel_12.setOpaque(true);
		panel_12.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_12.setBackground(new Color(51, 122, 152));
		panel_9.add(panel_12, "cell 1 1,grow");
		panel_12.setLayout(new MigLayout("", "[]", "[]"));
		
		panel_8 = new JPanel();
		panel_8.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(panel_8, "cell 1 0,grow");
		panel_8.setLayout(new MigLayout("", "[831px,grow]", "[743px,grow]"));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setUI(new AquaTabbedPaneUI());
		panel_8.add(tabbedPane, "cell 0 0,grow");
		
		panel = new JPanel();
		tabbedPane.addTab("POS", null, panel, null);
		panel.setLayout(new MigLayout("", "[401px,grow][][][395px,grow]", "[][408px,grow][274px,grow]"));
		
		lblMainRoom = new JLabel("Main Room");
		lblMainRoom.setFont(new Font("Monospaced", Font.PLAIN, 15));
		panel.add(lblMainRoom, "cell 0 0,growx");
		
		lblCapacity = new JLabel("Available capacity:");
		lblCapacity.setFont(new Font("Monospaced", Font.PLAIN, 15));
		panel.add(lblCapacity, "cell 1 0");
		
		lblRemaining = new JLabel("84");
		lblRemaining.setFont(new Font("Monospaced", Font.PLAIN, 15));
		panel.add(lblRemaining, "cell 2 0");
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.getVerticalScrollBar().setUnitIncrement(20);
		scrollPane_3.getHorizontalScrollBar().setUnitIncrement(20);
		panel.add(scrollPane_3, "cell 0 1 3 1,grow");
		
		pnlTables = new JPanel();
		scrollPane_3.setViewportView(pnlTables);
		pnlTables.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlTables.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		pnlSearch = new JPanel();
		pnlSearch.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(pnlSearch, "cell 3 1,grow");
		pnlSearch.setLayout(new MigLayout("", "[][grow][]", "[][grow][grow]"));
		
		btnOrder = new JButton("Order");
		btnOrder.setUI(new MetalButtonUI());
		btnOrder.setOpaque(false);
		btnOrder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnOrder.setContentAreaFilled(false);
		btnOrder.setIcon(new ImageIcon(POSDesign.class.getResource("/Images/Icon/order.png")));
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int cellX = 1;
				int cellY = 1;
				boolean check = false;
				
				for(int k = 0 ; k < i; k++){
					if(tablevalue[k] != null){
						check = true;
					}
				}if(check == false){
					NormalSales ns = new NormalSales();
					NormalSalesTable nst = new NormalSalesTable(ns);
					nst.CounterOrder();
					ModelSales ms = new ModelSales();
					new RemoveController(ns, ms, nst);
					new AddController(ns, ms, nst);
					ns.tfName.setText(customername);
					ns.tfType.setText("Take-out");
					ns.checkTable = false;
					ns.lblTakeDinein.setText("Take-out");	
					ns.tabbedPane.remove(ns.pnlTables);
					ns.tabbedPane.revalidate();
					ns.tabbedPane.repaint();
					CountTakeout cc = new CountTakeout();
					cc.getAccountNumber();
					if(!cc.getAccountNumber().isEmpty()){
						ns.tfId.setText(cc.getAccountNumber());
					}	
					ns.setModal(true);
					ns.setSize(1000,666);
					ns.setVisible(true);
				}else 
				if(check == true){
					NormalSales ns = new NormalSales();
					NormalSalesTable nst = new NormalSalesTable(ns);
					nst.CounterOrder();
					ModelSales ms = new ModelSales();
					new RemoveController(ns, ms, nst);
					new AddController(ns, ms, nst);
					ns.tfName.setText(customername);
					for(int k = 0 ; k < i; k++){
						if(tablevalue[k] != null ){
							ns.tfType.setText("Dine-in");
							ns.checkTable = true;
							ns.lblTakeDinein.setText("Dine-in");
							lblTable[k] = new JLabel();
							lblTable[k].setName(tableid[k]);
							spnTable[k] = new JSpinner();
							lblTable[k].setText(tabledesc[k]);
							lblTable[k].setHorizontalAlignment(SwingConstants.CENTER);
							spnTable[k].setModel(new SpinnerNumberModel(capacity[k], 1, capacity[k], 1));
							((DefaultEditor) spnTable[k] .getEditor()).getTextField().setEditable(false);
							spnTable[k].setEditor(new JSpinner.DefaultEditor(spnTable[k]));
							spnTable[k].setValue(capacity[k]);
							ns.pnlTables.add(lblTable[k], "cell 0 "+cellX+++", grow");
							ns.pnlTables.add(spnTable[k], "cell 1 "+cellY+++", grow");
							spnTable[k].addChangeListener(new ChangeListener(){
								@Override
								public void stateChanged(ChangeEvent e) {
									for(int j = 0 ; j < i; j++){
										if(e.getSource() == spnTable[j]){
											spnTableCapacity = 0;
											for(int k = 0; k < i; k++){
												if(tablevalue[k] != null){
													spnTableCapacity = (int) spnTable[k].getValue();
												}
											}
											updateUI();
										}
									}
								}							
							});
						}
						CountDinein cc = new CountDinein();
						cc.getAccountNumber();
						if(!cc.getAccountNumber().isEmpty()){
							ns.tfId.setText(cc.getAccountNumber());
						}				
					}
					ns.setModal(true);
					ns.setSize(1000,666);
					ns.setVisible(true);
				}
			}
		});
		pnlSearch.add(btnOrder, "cell 0 0");
		
		dateChooser = new JDateChooser(new Date());
		dateChooser.getDateEditor().setEnabled(false);
		dateChooser.setMinSelectableDate(dateChooser.getDate());
		dateChooser.setDateFormatString("MMMM dd, yyyy");
		pnlSearch.add(dateChooser, "cell 1 0,growx,aligny center");
		
		panel_15 = new JPanel();
		pnlSearch.add(panel_15, "cell 2 0");
		panel_15.setLayout(new MigLayout("", "[][][]", "[]"));
		
		lblType = new JLabel("Type:");
		lblType.setFont(new Font("Monospaced", Font.PLAIN, 15));
		panel_15.add(lblType, "cell 0 0");
		
		rdbtnRegular = new JRadioButton("Regular");
		panel_15.add(rdbtnRegular, "cell 1 0");
		rdbtnRegular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewNormalRoom();
				ViewGetPlanIdOfRoom();
				SetTables();
				ViewDecorate();	  
				clock.suspend();
				ViewOccupied();
				clockEvent.suspend();
				ViewEvent();
				ReservationTable.ViewDinein();
				ReservationTable.ViewNormalReservation();
				ReservationTable.ViewEventReservation();
				ViewAllTableSetText();
				ViewNormalRoom();
			}
		});
		rdbtnRegular.setSelected(true);
		buttonGroup.add(rdbtnRegular);
		rdbtnRegular.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		rdbtnEvent = new JRadioButton("Event");
		panel_15.add(rdbtnEvent, "cell 2 0");
		rdbtnEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewEventRoom();
				ViewGetPlanIdOfRoom();
				SetTables();
				ViewDecorate();	  
				clock.suspend();
				ViewOccupied();
				clockEvent.suspend();
				ViewEvent();
				ReservationTable.ViewDinein();
				ReservationTable.ViewNormalReservation();
				ReservationTable.ViewEventReservation();
				ViewAllTableSetText();
				ViewEventRoom();
			}
		});
		buttonGroup.add(rdbtnEvent);
		rdbtnEvent.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		panel_13 = new JPanel();
		panel_13.setOpaque(true);
		panel_13.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlSearch.add(panel_13, "cell 0 1 3 1,grow");
		panel_13.setLayout(new MigLayout("", "[][grow]", "[]"));
		
		panel_16 = new JPanel();
		panel_13.add(panel_16, "cell 0 0 2 1,grow");
		panel_16.setLayout(new MigLayout("", "[][grow][][grow][]", "[]"));
		
		lblFloor = new JLabel("Floor:");
		panel_16.add(lblFloor, "cell 0 0");
		lblFloor.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		cbFloor = new JComboBox();
		panel_16.add(cbFloor, "cell 1 0,growx");
		cbFloor.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(rdbtnRegular.isSelected()){
					ViewNormalRoom();					
				}
				else if(rdbtnEvent.isSelected()){
					ViewEventRoom();					
				}
				ViewGetPlanIdOfRoom();
				SetTables();
				ViewDecorate();	  
				clock.suspend();
				ViewOccupied();
				clockEvent.suspend();
				ViewEvent();
				ReservationTable.ViewDinein();
				ReservationTable.ViewNormalReservation();
				ReservationTable.ViewEventReservation();
				ViewAllTableSetText();
				if(rdbtnRegular.isSelected()){
					ViewNormalRoom();					
				}
				else if(rdbtnEvent.isSelected()){
					ViewEventRoom();					
				}
			}
		});
		cbFloor.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		lblRoom = new JLabel("Room:");
		panel_16.add(lblRoom, "cell 2 0");
		lblRoom.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		cbRoom = new JComboBox();
		panel_16.add(cbRoom, "cell 3 0,growx");
		cbRoom.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				SetTables();
				ViewDecorate();	  
				clock.suspend();
				ViewOccupied();
				clockEvent.suspend();
				ViewEvent();
				ReservationTable.ViewDinein();
				ReservationTable.ViewNormalReservation();
				ReservationTable.ViewEventReservation();
				ViewAllTableSetText();
			}
		});
		cbRoom.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SetTables();
				ViewGetPlanIdOfRoom();
				ViewDecorate();	  
				clock.suspend();
				ViewOccupied();
				clockEvent.suspend();
				ViewEvent();
				ReservationTable.ViewDinein();
				ReservationTable.ViewNormalReservation();
				ReservationTable.ViewEventReservation();
				ViewAllTableSetText();
				if(rdbtnRegular.isSelected()){
					ViewNormalRoom();
				}else if(rdbtnEvent.isSelected()){
					ViewEventRoom();
				}
			}
		});
		panel_16.add(btnSearch, "cell 4 0,grow");
		
		pnlEvent = new JPanel();
		pnlEvent.setOpaque(true);
		pnlEvent.setBackground(new Color(51, 122, 152));
		pnlEvent.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlSearch.add(pnlEvent, "cell 0 2 3 1,grow");
		pnlEvent.setLayout(new MigLayout("", "[grow]", "[grow,center]"));		
		
		tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setUI(new AquaTabbedPaneUI());
		panel.add(tabbedPane_1, "cell 0 2 4 1,grow");
		
		panel_4 = new JPanel();
		tabbedPane_1.addTab("Normal Reserve", null, panel_4, null);
		panel_4.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		scrollPane = new JScrollPane();
		panel_4.add(scrollPane, "cell 0 0,grow");
		
		tblNormalReserve = new JTable();
		tblNormalReserve.setModel(new DefaultTableModel(
			new Object[][] {
				{"R000000003", " Manny Pacquiao", "6", "Normal", "2016-09-08", " 04:30:00", "PM", " Reserve"},
			},
			new String[] {
				"Reserve Id", "Name", "Capacity", "Type", "Date", "Time", "Meridem", "Status"
			}
		));
		scrollPane.setViewportView(tblNormalReserve);
		
		panel_5 = new JPanel();
		tabbedPane_1.addTab("Event Reserve", null, panel_5, null);
		panel_5.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		scrollPane_1 = new JScrollPane();
		panel_5.add(scrollPane_1, "flowx,cell 0 0,grow");
		
		tblEventReserve = new JTable();
		tblEventReserve.setModel(new DefaultTableModel(
			new Object[][] {
				{"R000000002", "Rody Duterte", "50", " Event", "2016-09-08", "02:30:00", " PM", "Reserve"},
			},
			new String[] {
				"Reserve Id", "Name", "Packs", "Type", "Date", "Time", "Meridem", "Status"
			}
		));
		scrollPane_1.setViewportView(tblEventReserve);
		
		panel_6 = new JPanel();
		tabbedPane_1.addTab("Dine in", null, panel_6, null);
		panel_6.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		scrollPane_2 = new JScrollPane();
		panel_6.add(scrollPane_2, "cell 0 0,grow");
		
		tblDinein = new JTable();
		tblDinein.setModel(new DefaultTableModel(
			new Object[][] {
				{"DI000000001", "Guest", "10", "2016-09-08", "16:30:00", "17:00:00"},
				{"R000000001", "Beejay Mundoc", "6", "2016-09-08", "16:30:00", "17:00:00"},
				{"R000000004", "Isaac", "100", "2016-09-08", "16:30:00", "18:30:00"},
			},
			new String[] {
				"Dinein Id", "Name", "Capacity", "Date", "Time started", "Time end"
			}
		));
		scrollPane_2.setViewportView(tblDinein);

		
		ListSelectionModel selectionModel = tblNormalReserve.getSelectionModel();		
		selectionModel.addListSelectionListener( this );
		ListSelectionModel selectionModel1 = tblEventReserve.getSelectionModel();		
		selectionModel1.addListSelectionListener( this );
  		PanelReserve();
		SetUI();
		ViewFloor();
		ViewNormalRoom();
		ViewGetPlanIdOfRoom();
		SetTables();
		dateChooser.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				  thrGetPlanIdOfRoom=new Thread(new Runnable(){
					  public void run()
					  {
					      try{
							ViewAllTableSetText();
							ViewDecorate();			    	  
							ViewOccupied();
							clock.suspend();
							ViewOccupied();
							ViewEvent();
							clockEvent.suspend();
							ViewEvent();
							ReservationTable.ViewDinein();
							ReservationTable.ViewNormalReservation();
							ReservationTable.ViewEventReservation();
							Thread.sleep(0);
						   }catch(Exception e){
						   }
					   }
					 }); thrGetPlanIdOfRoom.start();
			}
		});
	}

	 public void setMinSelectableDate(Date min) {
	        dateChooser.setMinSelectableDate(min);
	        dateEditor.setMinSelectableDate(min);
	 }
	public void PanelReserve(){
		rp = new ReservePanel();
		tabbedPane.addTab("Add Reservation", null, rp, null);

		ret = new ReserveTable(rp);
		ret.CounterReserve();
	}
	
	public void SetUI(){
		 btnNewButton.setUI(new MetalButtonUI());
	}
	//end of Selecting the image to database
	public static void SetTables(){
		Connect c;
		try {
			c = new Connect();
			c.pst = c.con.prepareCall("{call count_tables_with_id(?)}");
			c.pst.setString(1, roomid);
			c.pst.execute();	
			c.rs = c.pst.getResultSet();
			int count = 0;
			while(c.rs.next()){
				count = c.rs.getInt("COUNT");				
			}
			lblTable = new JLabel[count];
			spnTable = new JSpinner[count];
			btnDynamic = new JToggleButton[count];
		    tabledesc = new String[count];
		    tableid = new String[count];
			tablecapacity = new String[count];
			deskdesc = new String[count+1];
			deskid = new String[count+1];
			deskdate = new String[count+1];
			desktime = new String[count+1];
			deskname = new String[count+1];
			countString = new String[count];
			m_Date = new Date[count];
			date = new Date[count];
			sdfIn = new SimpleDateFormat[count];
			sdfOut = new SimpleDateFormat[count];
			desk = new String[count+1];
			deskstatus = new String[count+1];
			select = new boolean[count];
			capacity = new int[count];
			is_extended = new String[count];
			buttonid = new String[count];
			confirmstatus = new String[count];
		    description = new String[count];
			selecttable = new boolean[count];
			tablevalue = new String[count];
			tablevalueid = new String[count];
			deskcapacity = new int[count];
			minuscapacity = new int[count];
			figure = new String[count];
			c.rs.close();
			c.pst.close();
			c.con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public CalendarModel getCalendarData(){
		CalendarModel cm = new CalendarModel();
		cm.setDate(dateChooser.getDate());
		cm.setId(roomid);
		return cm;
	}
	
	public static void ViewOccupied(){
		try {
			Connect c = new Connect();
			iDesk = 0;
			c.pst = c.con.prepareCall("{call select_table_info(?,?,?)}");
			c.pst.setString(1, "");
			c.pst.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(dateChooser.getDate()));
			c.pst.setString(3, roomid);
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			while(c.rs.next()){
				foundButton = true;
				buttonid[iDesk] = c.rs.getString("ID");
				deskid[iDesk] = c.rs.getString("desk_id");
				deskdesc[iDesk] = c.rs.getString("Table");
				deskname[iDesk] = c.rs.getString("Name");
				deskdate[iDesk] = c.rs.getString("Date");
				desktime[iDesk] = c.rs.getString("Time");
				deskstatus[iDesk] = c.rs.getString("Status");
				capacity[iDesk] = c.rs.getInt("Table Capacity");
				deskcapacity[iDesk] = c.rs.getInt("Capacity");
				is_extended[iDesk] = c.rs.getString("isExtended");
				minuscapacity[iDesk] = capacity[iDesk] -  deskcapacity[iDesk];
				
				desk[iDesk] = deskdate[iDesk] + " " +desktime[iDesk];
				date[iDesk] = null;
				sdfIn[iDesk] = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    sdfOut[iDesk] = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			    String input = desk[iDesk]; 
			    if(desk[iDesk].equalsIgnoreCase("null null")){
				    date[iDesk] = sdfIn[iDesk].parse("2011-03-03 2:15:10");
			    }else if(desk[iDesk].equalsIgnoreCase("")){
				    date[iDesk] = sdfIn[iDesk].parse("2011-03-03 2:15:10");
			    }else{
				    date[iDesk] = sdfIn[iDesk].parse(input);
			    }							
				m_Date[iDesk] = new Date(sdfOut[iDesk].format(date[iDesk]));
				iDesk++;
			}
			c.rs.close();
			c.pst.close();
			c.con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		clock = new Thread(){
			public void run(){
				try {
					for(;;){
							for(int j = 0; j < i; j++){
								for (int k = 0; k < i; k++) {				
									Date currentDate = new Date();
									if(foundButton == true){
										if(btnDynamic[j].getActionCommand().equalsIgnoreCase(deskdesc[k])){
											select[j] = false;
											confirmstatus[j] = deskstatus[k];
											
											if (m_Date[k].after(currentDate)){
												// Calculate difference in dates
												long numericalDifference =  m_Date[k].getTime() - currentDate.getTime();
		
												// Divide by 1000 to find number of seconds difference
												numericalDifference = numericalDifference / 1000;
												
												// Get seconds
												int seconds = (int) numericalDifference % 60;
		
												// Get minutes
												numericalDifference = numericalDifference / 60;
												int minutes = (int) numericalDifference % 60;
		
												// Get hours
												numericalDifference = numericalDifference / 60;
												int hours = (int) numericalDifference % 24;
		
												// Get days
												numericalDifference = numericalDifference / 24;
												int days = (int) numericalDifference % 365;

												// Get week
												numericalDifference = numericalDifference / 7;
												int week = (int) numericalDifference;
												
												// Get month
												numericalDifference = numericalDifference / 30;
												if(week == 0 && days != 0 && hours != 0 && minutes !=0 ){
													countString[k] = "<html>" + days + " Days<br> " + hours   + " Hrs.<br>" + minutes + " Mins.<br>"  + seconds + " Secs." + " Left </html> </html>";		
												}else if(week == 0 && days == 0 && hours != 0 && minutes !=0 ){
													countString[k] = "<html>" +  hours   + " Hrs.<br>" + minutes + " Mins.<br>"  + seconds + " Secs." + " Left </html>";		
												}else if(week == 0 && hours == 0 && days == 0 && minutes !=0 ){
													countString[k] = "<html>" +  minutes + " Mins.<br>"  + seconds + " Secs." + " Left </html>";
												}else if(week == 0 && hours == 0 && days == 0 && minutes == 0){
													countString[k] = "<html>" + seconds + " Secs." + " Left </html>";
												}else{
													countString[k] =  week + "W, " + days + "D, " + hours   + "H, " + minutes + "M" + " Left";														
												}
												
													// Generate date string
													if(hours < 2 && days == 0 && week == 0 && deskstatus[k].equalsIgnoreCase("Reserve") && btnDynamic[j].isSelected() == false){
														btnDynamic[j].setText("<html><center>" + tabledesc[j] + "<br>" + deskname[k] +  "</center></html>");	
														btnDynamic[j].setBackground(new Color(178, 73, 55));
													}//RESERVE
													else if(deskstatus[k].equalsIgnoreCase("Occupied") && btnDynamic[j].isSelected() == false){
														btnDynamic[j].setText("<html><center>" +figure[j] + "<br>"+ tabledesc[j] + "<br>" + deskname[k] + "("+deskcapacity[k]  + ")<br> " + minuscapacity[k] + " excess</center></html>");	
														btnDynamic[j].setBackground(new Color(35, 182, 90));
														btnDynamic[j].setToolTipText(buttonid[k]);
													}//OCCUPIED
													else{
														if(btnDynamic[j].isSelected() == false){
															btnDynamic[j].setText("<html><center>" + figure[j] + "<br/>"+tabledesc[j] +"<br>"+capacity[j] + "</center></html>");				
											  				btnDynamic[j].setBackground(new Color(51, 122, 152));
														}
													}//VACANT
												if(hours == 0 && week == 0 && days == 0 && minutes == 0 && seconds == 0){
													if(deskstatus[k].equalsIgnoreCase("Reserve")){
														String[] options = null;
														if(is_extended[k].equalsIgnoreCase("NO")){
														    options = new String[] {"Yes! " + deskname[k] + "is here", "Not yet, just extend 30 minutes"};								 						
														}else if(is_extended[k].equalsIgnoreCase("YES")){
														    options = new String[] {"Yes! " + deskname[k] + "is here", "Not yet, just cancel the reserve"};														
														}
													    int reply = JOptionPane.showOptionDialog(null, "Is "+deskname[k]+ " is here?" , deskname[k] + " Reservation",
													        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
													        null, options, options[0]);
//														int reply = JOptionPane.showConfirmDialog(null, "Is "+deskname[k]+ " is here?" , "Option", JOptionPane.YES_NO_OPTION);
												        if (reply == 0) {
															try {
														        Calendar cal = Calendar.getInstance();
														        cal .add(Calendar.HOUR, 1);
														        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
																Connect con = new Connect();
																con.pst = con.con.prepareCall("{call seat_reserve(?,?)}");
																con.pst.setString(1, buttonid[k]);
																con.pst.setString(2, sdf.format(cal.getTime()));	
																con.pst.execute();
																SetTables();
																ViewDecorate();
																ViewOccupied();
																clock.suspend();
																ViewOccupied();
																con.pst.close();
																con.con.close();
															} catch (SQLException e1) {
																e1.printStackTrace();
															}	
												        }
												        else {
												        	if(is_extended[k].equalsIgnoreCase("NO")){
																try {
															        Calendar cal = Calendar.getInstance();
															        cal .add(Calendar.MINUTE, 30);
															        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
																	Connect con = new Connect();
																	con.pst = con.con.prepareCall("{call extend_reserve(?,?)}");
																	con.pst.setString(1, buttonid[k]);
																	con.pst.setString(2, sdf.format(cal.getTime()));									
																	con.pst.execute();
																	SetTables();
																	ViewDecorate();
																	ViewOccupied();
																	clock.suspend();
																	ViewOccupied();
																	con.pst.close();
																	con.con.close();
																} catch (SQLException e1) {
																	e1.printStackTrace();
																}											
												        	}else  if(is_extended[k].equalsIgnoreCase("YES")){
																try {
																	Connect con = new Connect();
																	con.pst = con.con.prepareCall("{call cancel_reserve(?)}");
																	con.pst.setString(1, buttonid[k]);
																	con.pst.execute();
																	SetTables();
																	ViewDecorate();
																	ViewOccupied();
																	clock.suspend();
																	ViewOccupied();
																	con.pst.close();
																	con.con.close();
																} catch (SQLException e1) {
																	e1.printStackTrace();
																}											
												        	}
												        }
													}else {
														try {
															Connect con = new Connect();
															con.pst = con.con.prepareCall("{call end_reserve(?)}");
															con.pst.setString(1, buttonid[k]);
															con.pst.execute();
															SetTables();
															ViewDecorate();
															ViewOccupied();
															clock.suspend();
															ViewOccupied();
															con.pst.close();
															con.con.close();
														} catch (SQLException e1) {
															e1.printStackTrace();
														}											
										        	}
												}
											}else{
												if(deskstatus[k].equalsIgnoreCase("Reserve") && btnDynamic[j].isSelected() == false){
													btnDynamic[j].setText("<html><center>" + tabledesc[j] + "</center></html>");	
													btnDynamic[j].setBackground(new Color(166, 161, 18));
												}//RESERVE
												else if(deskstatus[k].equalsIgnoreCase("Occupied") && btnDynamic[j].isSelected() == false){
													btnDynamic[j].setText("<html><center>" +figure[j]+"<br>"+ tabledesc[j] + "<br>" + deskname[k] + "("+deskcapacity[k]  + ")<br> " + minuscapacity[k] + " excess</center></html>");	
													btnDynamic[j].setBackground(new Color(35, 182, 90));
													btnDynamic[j].setToolTipText(buttonid[k]);
												}//OCCUPIED
												else{
													if(btnDynamic[j].isSelected() == false){
														btnDynamic[j].setText("<html><center>" + figure[j] + "<br/>"+tabledesc[j] +"<br>"+capacity[j] + "</center></html>");				
										  				btnDynamic[j].setBackground(new Color(51, 122, 152));
													}								
												}//VACANT
											}
										}else if(select[j] == true){
											if(btnDynamic[j].isSelected() == false){
												btnDynamic[j].setText("<html><center>" + figure[j] + "<br/>"+tabledesc[j] +"<br>"+capacity[j] + "</center></html>");				
								  				btnDynamic[j].setBackground(new Color(51, 122, 152));
											}
										}//VACANT
									}
							}
						}
						sleep(1000);
						pnlTables.repaint();
						pnlTables.revalidate();
						pnlTables.updateUI();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		clock.start();
	}
	
	//Viewing the image and buttons on the panel
	public static void ViewDecorate(){

		  thr=new Thread(new Runnable(){
			  private boolean tablefound;

			public void run()
			  {
			      try{
			  		x = 0;
			  		i = 0;
			  		y = 0;
			  		pnlTables.removeAll();			  		
			  		ViewGetPlanIdOfRoom();
			  		try {
			  		Connect c;
			  			c = new Connect();
			  			c.pst = c.con.prepareCall("{call select_desk_buttons(?)}");
			  			c.pst.setString(1, roomid);
			  			c.pst.execute();
			  			c.rs = c.pst.getResultSet(); 
			  			while(c.rs.next()){
			  				tablefound = true;
			  				tableid[i] = c.rs.getString("desk_id");
			  				tabledesc[i] = c.rs.getString("desk_desc");
			  				capacity[i] = c.rs.getInt("desk_capacity");
			  				figure[i] = c.rs.getString("table_description");
			  				btnDynamic[i] = new JToggleButton("<html><center>" +figure[i] + "<br/>"+tabledesc[i]+"<br/>"+capacity[i]+"<center></html>");
			  				btnDynamic[i].setName(tableid[i]);
			  				btnDynamic[i].setFont(new Font("Monospaced", Font.PLAIN, 13));
			  				btnDynamic[i].setActionCommand(tabledesc[i]);
			  				btnDynamic[i].setHorizontalAlignment(SwingConstants.CENTER );
			  				btnDynamic[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			  				btnDynamic[i].setHorizontalTextPosition(JButton.CENTER);
			  				btnDynamic[i].setVerticalTextPosition(JButton.CENTER);
			  				btnDynamic[i].setForeground(Color.WHITE);
			  				btnDynamic[i].setUI(new MetalButtonUI());
			  				btnDynamic[i].setBackground(new Color(51, 122, 152));
			  				if(x % 5 == 0 && x!=0){
			  					pnlTables.add(btnDynamic[i], "cell "+x+++"  "+y+++",grow");					
			  					x = 0;
			  				}else{
			  					pnlTables.add(btnDynamic[i], "cell "+x+++"  "+y+",grow");
			  				}
			  				
			              	description[i] = tabledesc[i];
			  				select[i] = true;
			  				selecttable[i] = false;
			  				confirmstatus[i] = "Vacant";
			  				tablevalue[i] = null;
			  				tablevalueid[i] = null;
			  				tablecapacity[i] = null;
			  				
//			  				//Button Dynamic Action Listener
			  				btnDynamic[i].addActionListener(new ActionListener() {
			  					private boolean found = false;
			  					private String total;
			  					private String orderid;
			  					private String orderstatus;
								private String order_status;
			  					public void actionPerformed(ActionEvent e) {   				

			  						for(int j = 0; j < i; j++){
			  							for (int k = 0; k < i; k++) {
			  								if(btnDynamic[j].getActionCommand().equalsIgnoreCase(e.getActionCommand())){
			  				    				if(confirmstatus[j].equalsIgnoreCase("Reserve") || confirmstatus[j].equalsIgnoreCase("Preparing")){
			  				    					ReserveOrder ro = new ReserveOrder();
			  				    					ro.setSize(800, 400);
			  				    					ReserveOrder.tfTables.setText(btnDynamic[j].getActionCommand());
			  				    					ro.tfName.setText(deskname[k]);
			  				    					ReserveOrder.rname = deskname[k];
			  				    					ReserveOrder.reserveid= buttonid[k];
			  										ReserveOrder.rid= buttonid[k];
			  				    					try {
			  											Connect con = new Connect();
			  											con.pst = con.con.prepareCall("{call select_type_from_reserve_id(?)}");
			  											con.pst.setString(1, buttonid[k]);
			  											con.pst.execute();
			  											con.rs = con.pst.getResultSet();
			  											while(con.rs.next()){
			  												ReserveOrder.rtype = con.rs.getString("reserve_type");
			  											}
			  											con.rs.close();
			  											con.pst.close();
			  											con.con.close();
			  											
			  											con = new Connect();
			  											con.pst = con.con.prepareCall("{call select_table_orders(?)}");
			  											con.pst.setString(1, buttonid[k]);
			  											con.pst.execute();
			  											con.rs = con.pst.getResultSet();
			  											ReserveOrder.tblOrderList.setModel(DbUtils.resultSetToTableModel(con.rs));	
			  											con.rs.close();
			  											con.pst.close();
			  											con.con.close();
			  											
			  											con = new Connect();
			  											con.pst = con.con.prepareCall("call select_all_orders(?)");
			  											con.pst.setString(1, buttonid[k]);
			  											con.pst.execute();
			  											con.rs = con.pst.getResultSet();
			  											while(con.rs.next()){
			  								            	found  = true;
			  												total = con.rs.getString("order_total");
			  												orderid = con.rs.getString("order_id");
			  												orderstatus = con.rs.getString("order_status");
			  												ReserveOrder.tfTotal.setText(total);
			  												ReserveOrder.tfOrderId.setText(orderid);
			  												ReserveOrder.tfStatus.setText(orderstatus);
			  												ro.btnOrder.setText("Update Orders");
			  												ro.btnOrder.setName("Update Orders");  
			  											}
			  											if(!found){
			  												ro.btnOrder.setText("Add Orders");
			  												ro.btnOrder.setName("Add Orders");
			  												ReserveOrder.tfTotal.setText("0.0");
			  												ReserveOrder.tfStatus.setText("No order yet");
			  											}
			  											
			  											con.rs.close();
			  											con.pst.close();
			  											con.con.close();
			  										} catch (SQLException e2) {
			  											e2.printStackTrace();
			  										}
			  				    					ro.setModal(true);
			  				    					ro.setVisible(true);
			  					    				break;
			  				    				}else if(confirmstatus[j].equalsIgnoreCase("Occupied")){		
  													String myname = null;
			  				    					try {

			  				    						Connect c;
			  				    							c = new Connect();
			  				    							c.pst = c.con.prepareCall("{call select_button_name(?,?,?)}");
			  				    							c.pst.setString(1, btnDynamic[j].getToolTipText());
			  				    							c.pst.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			  				    							c.pst.setString(3, roomid);
			  				    							c.pst.execute();
			  				    							c.rs = c.pst.getResultSet();
			  				    							while(c.rs.next()){
			  				    								myname = c.rs.getString("Name");
			  				    							}
			  				    							c.rs.close();
			  				    							c.pst.close();
			  				    							c.con.close();
			  				    					} catch (SQLException ex) {
			  				    						ex.printStackTrace();
			  				    					}
			  											String[] options = null;
			  										    options = new String[] {"Go Back, Not yet", "View Orders", "Add orders", "Finish dine-in"};		
			  										    int reply = JOptionPane.showOptionDialog(null, btnDynamic[j].getActionCommand()+" ("+myname+ " ) seat here. What will you do?" , deskname[k] + " Option",
			  										        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
			  										        null, options, options[0]);
			  									        if (reply == 3) {
			  												try {
			  													
			  													Connect con = new Connect();
			  													con.pst = con.con.prepareCall("{call check_order_status(?)}");
			  													con.pst.setString(1, btnDynamic[j].getToolTipText());
			  													con.pst.execute();
			  													con.rs = con.pst.getResultSet();
			  													while(con.rs.next()){
			  														order_status = con.rs.getString("order_status");
			  													}
			  													if(order_status.equalsIgnoreCase("Balance") || order_status.equalsIgnoreCase("Unpaid")){
			  														JOptionPane.showMessageDialog(null, "Please complete your payment first.");
			  													}else{
			  														con.pst = con.con.prepareCall("{call end_session(?)}");
			  														con.pst.setString(1, btnDynamic[j].getToolTipText());
			  														con.pst.execute();
			  														ViewAllTableSetText();
			  														SetTables();
			  														ViewDecorate();
			  														ViewOccupied();
			  														clock.suspend();
			  														ViewOccupied();
			  														Selection.ret.ViewNormalReservation();
			  														Selection.ret.ViewDinein();
			  														JOptionPane.showMessageDialog(null, "Dining-in ended..");
			  														con.pst.close();
			  														con.con.close();
			  														
			  													}
			  												} catch (SQLException e1) {
			  													e1.printStackTrace();
			  												}
			  									        }else if(reply == 2	){
			  												NormalSales ns = new NormalSales();
			  												NormalSalesTable nst = new NormalSalesTable(ns);
			  												nst.CounterOrder();
			  												ModelSales ms = new ModelSales();
			  												new RemoveController(ns, ms, nst);
			  												new AddController(ns, ms, nst);
//			  												nst.ViewTableOrders(rid, type);
			  												ns.tfName.setText(myname);
			  												if(myname.equals("Guest")){
			  													ns.tfType.setText("Dine-in");
			  												}else{
			  													ns.tfType.setText("Reserve");
			  												}
			  												ns.tfId.setText(btnDynamic[j].getToolTipText());
	  														ns.lblTakeDinein.setText("Dine-in");
	  														ns.tabbedPane.remove(ns.pnlTables);
	  														ns.checkTable = false;
	  														ns.tabbedPane.repaint();
			  												ns.setModal(true);
			  												ns.setSize(1000,666);
			  												ns.setVisible(true);
			  									        }else if(reply == 1){
					  				    					DineinOrder ro = new DineinOrder();
					  				    					ro.setSize(800, 400);
					  				    					DineinOrder.tfTables.setText(btnDynamic[j].getActionCommand());
					  				    					ro.tfName.setText(myname);
			  												if(myname.equals("Guest")){
						  										DineinOrder.rtype = "Dine-in";
			  												}else{
						  										DineinOrder.rtype = "Reserve";
			  												}
					  				    					DineinOrder.rname =myname;
					  				    					DineinOrder.reserveid= btnDynamic[j].getToolTipText();
					  				    					try {
					  				    						Connect con = new Connect();
					  											con = new Connect();
					  											con.pst = con.con.prepareCall("{call select_dinein_orders(?)}");
					  											con.pst.setString(1, btnDynamic[j].getToolTipText());
					  											con.pst.execute();
					  											con.rs = con.pst.getResultSet();
					  											DineinOrder.tblOrderList.setModel(DbUtils.resultSetToTableModel(con.rs));
					  											con.rs.close();
					  											con.pst.close();
					  											con.con.close();
					  											
					  											con = new Connect();
					  											con.pst = con.con.prepareCall("call dinein_normal_orders(?)");
					  											con.pst.setString(1, btnDynamic[j].getToolTipText());
					  											con.pst.execute();
					  											con.rs = con.pst.getResultSet();
					  											while(con.rs.next()){
					  								            	found  = true;
					  												total = con.rs.getString("order_total");
					  												orderid = con.rs.getString("order_id");
					  												orderstatus = con.rs.getString("order_status");
					  												DineinOrder.rid = orderid;
					  												DineinOrder.reserveid = btnDynamic[j].getToolTipText();
					  												DineinOrder.tfTotal.setText(total);
					  												DineinOrder.tfOrderId.setText(orderid);
					  												DineinOrder.tfStatus.setText(orderstatus);
					  												ro.btnOrder.setText("Update Orders");
					  												ro.btnOrder.setName("Update Orders");  
					  											}
					  											if(!found){
						  									  		CounterNormalOrder cc = new CounterNormalOrder();
						  									  		cc.getAccountNumber();
						  									  		if(!cc.getAccountNumber().isEmpty()){
						  									  			DineinOrder.tfOrderId.setText(cc.getAccountNumber());
						  												DineinOrder.rid = cc.getAccountNumber();
						  									  		}
					  												ro.btnOrder.setText("Add Orders");
					  												ro.btnOrder.setName("Add Orders");
					  												DineinOrder.tfTotal.setText("0.0");
					  												DineinOrder.tfStatus.setText("No order yet");
					  											}
					  											
					  											con.rs.close();
					  											con.pst.close();
					  											con.con.close();
					  										} catch (SQLException e2) {
					  											e2.printStackTrace();
					  										}
					  				    					ro.setModal(true);
					  				    					ro.setVisible(true);
					  				    				}
			  						    				break;
			  				    				}else{
			  					    				if(btnDynamic[j].isSelected() == true){
			  					    					btnDynamic[j].setBackground(new Color(0, 0, 0));
			  					    					tablevalue[j] = btnDynamic[j].getActionCommand();
			  					    					tablevalueid[j] = btnDynamic[j].getName();
			  						    				break;
			  							    		}else if(btnDynamic[j].isSelected() == false){
			  											btnDynamic[j].setBackground(new Color(51, 122, 152));
			  						    				tablevalue[j] = null;
			  						    				tablevalueid[j] = null;
			  						    				tablecapacity[j] = null;
			  						    				break;
			  							    		}
			  				    				}
			  								}
			  							}
			  						}
			  		        	}
			  				});
			  				i++;				
			  			}
			  			if(!tablefound){
			  				pnlTables.removeAll();

			  				lblNoTablesIn = new JLabel("THERE ARE NO TABLES IN THIS AREA.");
			  				lblNoTablesIn.setFont(new Font("Monospaced", Font.PLAIN, 25));
			  				pnlTables.add(lblNoTablesIn, "cell 0 0,alignx center");
			  				
			  			}
			  			pnlTables.repaint();
			  			pnlTables.revalidate();
			  			c.rs.close();
			  			c.pst.close();
			  			c.con.close();
			  		} catch (SQLException e1) {
			  			e1.printStackTrace();
			  		}
						Thread.sleep(0);
				   }catch(Exception e){
					   
				   }
			   }
			 }); thr.start();
	}
	//end of Viewing the image and buttons on the panel

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub

		if( arg0.getSource() == tblNormalReserve.getSelectionModel() && arg0.getFirstIndex() >= 0 ){
			try {
				TableModel model = (TableModel)tblNormalReserve.getModel();
				int row = tblNormalReserve.getSelectedRow();
				int total = 0;
				ridNormal = model.getValueAt(row, 0).toString();
				rnameNormal = model.getValueAt(row, 1).toString();
				rcapacityNormal = model.getValueAt(row, 2).toString();
				rdateNormal = model.getValueAt(row, 4).toString();
				rtimeNormal = model.getValueAt(row, 5).toString();
				if(row!=-1){
					String[] options = null;
					    options = new String[] {"Select Table", "Advance Order","Cancel Reservation", "Go Back"};			
				    int reply = JOptionPane.showOptionDialog(null, "What will you do?" , "Reservation Option",
				        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
				        null, options, options[0]);
//					int reply = JOptionPane.showConfirmDialog(null, "Is "+deskname[k]+ " is here?" , "Option", JOptionPane.YES_NO_OPTION);
			        if (reply == 0) {
			        	ReserveTableForm rtf = new ReserveTableForm();
		        		try {
		        			Connect con = new Connect();
		        			con.pst = con.con.prepareCall("{call select_info_reserve(?)}");
		        			con.pst.setString(1, ridNormal);
		        			con.pst.execute();
		        			con.rs = con.pst.getResultSet();
		        			while(con.rs.next()){ 	
		        				remailNormal = con.rs.getString("reserve_email_address");
		        				rcontactNormal =  con.rs.getString("reserve_contact_no");
		        			}
				        	rtf.tfReserveId.setText(ridNormal);
				        	rtf.tfName.setText(rnameNormal);
				        	rtf.tfCapacity.setText(rcapacityNormal);
				        	rtf.tfContactNo.setText(rcontactNormal);
				        	rtf.tfEmailAddress.setText(remailNormal);
				        	rtf.tfDate.setText(rdateNormal);
				        	rtf.tfTime.setText(rtimeNormal);
		        			con.rs.close();
		        			con.pst.close();
		        			con.con.close();
		        		} catch (SQLException e1) {
		        			e1.printStackTrace();
		        		}

		        		m = 0;
		        		for(int j = 0 ; j < i; j++){
		        			if(tablevalue[j] != null){							
		        			}
		        		}
		        		for(int k = 0 ; k < i; k++){
							if(tablevalue[k] != null){
								lblTable[k] = new JLabel();
								lblTable[k].setName(tableid[k]);
								spnTable[k] = new JSpinner();
								lblTable[k].setText(tabledesc[k]);
								spnTable[k].setModel(new SpinnerNumberModel(capacity[k], 1, capacity[k], 1));
								((DefaultEditor) spnTable[k] .getEditor()).getTextField().setEditable(false);
								spnTable[k].setEditor(new JSpinner.DefaultEditor(spnTable[k]));
								spnTable[k].setValue(capacity[k]);
								rtf.pnlTables.add(lblTable[k], "cell 0 "+k+", grow");
								rtf.pnlTables.add(spnTable[k], "cell 1 "+k+", grow");
								spnTable[k].addChangeListener(new ChangeListener(){

									@Override
									public void stateChanged(ChangeEvent e) {
										for(int j = 0 ; j < i; j++){
											if(e.getSource() == spnTable[j]){
												spnTableCapacity = 0;
												for(int k = 0; k < i; k++){
													if(tablevalue[k] != null){
														spnTableCapacity = (int) spnTable[k].getValue();
													}
												}
												updateUI();
											}
										}
									}							
								});
							}
						}
						rtf.setSize(800,650);
						rtf.tfTableCapacity.setText(String.valueOf(total));
			        	rtf.setVisible(true);

			        }else if (reply == 1){
			    		NormalSales ns = new NormalSales();
							NormalSalesTable nst = new NormalSalesTable(ns);
							nst.CounterOrder();
							ModelSales ms = new ModelSales();
							new RemoveController(ns, ms, nst);
							new AddController(ns, ms, nst);
//							nst.ViewTableOrders(rid, type);
							ns.tfName.setText(rnameNormal);
							ns.tfType.setText("Reserve");
							ns.tfId.setText(ridNormal);
							ns.lblTakeDinein.setText("Dine-in");
							ns.tabbedPane.remove(ns.pnlTables);
							ns.checkTable = false;
							ns.tabbedPane.repaint();
							ns.setModal(true);
							ns.setSize(1000,666);
							ns.setVisible(true);
			        }else if (reply == 2){
						int reply1 = JOptionPane.showConfirmDialog(null, "Do you really want to cancel reservation?", "Cancel", JOptionPane.YES_NO_OPTION);
				        if (reply1 == JOptionPane.YES_OPTION) {
							try {
								Connect con = new Connect();
								con.pst = con.con.prepareCall("{call cancel_reserve(?)}");
								con.pst.setString(1, ridNormal);
								con.pst.execute();
								JOptionPane.showMessageDialog(null, "Reserve Cancelled");
								ReservationTable.ViewNormalReservation();
								
								POSDesign.SetTables();
								POSDesign.ViewDecorate();
								POSDesign.clock.suspend();
								POSDesign.ViewOccupied();
								
								con.pst.close();
								con.con.close();
							} catch (SQLException ex) {
								// TODO Auto-generated catch block
								ex.printStackTrace();
							}
				        }
				        else {
				        	
				        }
			        	
			        }else{
			        	tblNormalReserve.clearSelection();
			        }
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}else if( arg0.getSource() == tblEventReserve.getSelectionModel() && arg0.getFirstIndex() >= 0 ){
			try {

				TableModel model = (TableModel)tblEventReserve.getModel();
				int row = tblEventReserve.getSelectedRow();
				ridEvent = model.getValueAt(row, 0).toString();
				rnameEvent = model.getValueAt(row, 1).toString();
				if(row!=-1){
					String[] options = null;
					    options = new String[] {"Start event", "Advance Order","Cancel Reservation", "Go Back"};			
				    int reply = JOptionPane.showOptionDialog(null, "What will you do?" , "Reservation Option",
				        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
				        null, options, options[0]);
//					int reply = JOptionPane.showConfirmDialog(null, "Is "+deskname[k]+ " is here?" , "Option", JOptionPane.YES_NO_OPTION);
			        if (reply == 0) {
			        	String[] options1 = null;
					    options1 = new String[] {"Go back", "Yes ! " +  rnameEvent + " is here"};														
					    int reply1 = JOptionPane.showOptionDialog(null, "Is "+rnameEvent+ " is here? " , rnameEvent + "Event  Reservation",
					        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
					        null, options1, options1[0]);
					    if(reply1 == 1){
							try {
						        Calendar cal = Calendar.getInstance();
						        cal .add(Calendar.HOUR, 3);
						        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
								Connect con = new Connect();
								con.pst = con.con.prepareCall("{call seat_reserve(?,?)}");
								con.pst.setString(1, ridEvent);
								con.pst.setString(2, sdf.format(cal.getTime()));	
								con.pst.execute();
								clockEvent.suspend();
								ViewEvent();
								Selection.ret.ViewEventReservation();
								JOptionPane.showMessageDialog(null, "Event started.");
								con.pst.close();
								con.con.close();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}	
					    }else{
					    	
					    }
			        	
			        	
			        }else if (reply == 1){
			        	DineinEventOrder ro = new DineinEventOrder();
						ro.setSize(800, 400);
						ro.tfName.setText(rnameEvent);
						DineinEventOrder.tfRoom.setText((String) cbRoom.getSelectedItem());
						DineinEventOrder.rtype = "Reserve";
						DineinEventOrder.rname =rnameEvent;
						DineinEventOrder.reserveid= ridEvent;
						try {
							Connect con = new Connect();
							con = new Connect();
							con.pst = con.con.prepareCall("{call select_all_food_event_orders(?)}");
							con.pst.setString(1, ridEvent);
							con.pst.execute();
							con.rs = con.pst.getResultSet();
							DineinEventOrder.tblOrderList.setModel(DbUtils.resultSetToTableModel(con.rs));
							con.rs.close();
							con.pst.close();
							con.con.close();
							
							con = new Connect();
							con.pst = con.con.prepareCall("call dinein_event_orders(?)");
							con.pst.setString(1, ridEvent);
							con.pst.execute();
							con.rs = con.pst.getResultSet();
							while(con.rs.next()){
				            	found  = true;
								total = con.rs.getString("order_total");
								orderid = con.rs.getString("order_id");
								orderstatus = con.rs.getString("order_status");
								DineinEventOrder.rid = orderid;
								DineinEventOrder.reserveid = ridEvent;
								DineinEventOrder.tfTotal.setText(total);
								DineinEventOrder.tfOrderId.setText(orderid);
								DineinEventOrder.tfStatus.setText(orderstatus);
								ro.btnOrder.setText("Update Orders");
								ro.btnOrder.setName("Update Orders");  
							}
							if(!found){
						  		CounterNormalOrder cc = new CounterNormalOrder();
						  		cc.getAccountNumber();
						  		if(!cc.getAccountNumber().isEmpty()){
						  			DineinEventOrder.tfOrderId.setText(cc.getAccountNumber());
									DineinEventOrder.rid = cc.getAccountNumber();
						  		}
								ro.btnOrder.setText("Add Orders");
								ro.btnOrder.setName("Add Orders");
								DineinEventOrder.tfTotal.setText("0.0");
								DineinEventOrder.tfStatus.setText("No order yet");
							}
							
							clockEvent.suspend();
							ViewEvent();

							con.rs.close();
							con.pst.close();
							con.con.close();
						} catch (SQLException e2) {
							e2.printStackTrace();
						}
						ro.setModal(true);
						ro.setVisible(true);
			        }else if (reply == 2){
						int reply1 = JOptionPane.showConfirmDialog(null, "Do you really want to cancel reservation?", "Cancel", JOptionPane.YES_NO_OPTION);
				        if (reply1 == JOptionPane.YES_OPTION) {
							try {
								Connect con = new Connect();
								con.pst = con.con.prepareCall("{call cancel_reserve(?)}");
								con.pst.setString(1, ridEvent);
								con.pst.execute();
								JOptionPane.showMessageDialog(null, "Reserve Cancelled");
								ReservationTable.ViewEventReservation();
								
								clockEvent.suspend();
								ViewEvent();

								con.pst.close();
								con.con.close();
							} catch (SQLException ex) {
								// TODO Auto-generated catch block
								ex.printStackTrace();
							}
				        }
				        else {
				        	
				        }
			        	
			        }else{
			        	tblEventReserve.clearSelection();
			        }
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

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

 	
 	public static void ViewAllTableSetText(){
 		try{
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
			int roomcapacity = 0;
			while (c.rs.next()) { 
				roomname = c.rs.getString("room_name"); 
				roomcapacity = c.rs.getInt("room_capacity");
			}
			lblMainRoom.setText(roomname);
			lblRemaining.setText(String.valueOf(roomcapacity - roomcapacityminus));
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

	//Viewing the room method
 	public void ViewNormalRoom(){
 		boolean found = false;
		ArrayList arrayRoomName = new ArrayList<String>();
		try{
			Connect c = new Connect();
			c.pst = c.con.prepareCall("{call select_cb_normal_room_via_floor_name(?)}");
			c.pst.setString(1, (String) cbFloor.getSelectedItem());
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			while (c.rs.next()) { 
				found = true;
				String roomname = c.rs.getString("Name");
				arrayRoomName.add(roomname);
			}
			if(!found){
				lblMainRoom.setText("No selected room");
				pnlTables.removeAll();

  				lblNoTablesIn = new JLabel("THERE IS NO TABLES IN THIS AREA.");
  				lblNoTablesIn.setFont(new Font("Monospaced", Font.PLAIN, 25));
  				pnlTables.add(lblNoTablesIn, "cell 0 0,alignx center");
				pnlTables.repaint();
				pnlTables.revalidate();
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
 		boolean found = false;
		ArrayList arrayRoomName = new ArrayList<String>();
		try{
			Connect c = new Connect();
			c.pst = c.con.prepareCall("{call select_cb_event_room_via_floor_name(?)}");
			c.pst.setString(1, (String) cbFloor.getSelectedItem());
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			while (c.rs.next()) {
				found = true;
				String roomname = c.rs.getString("Name");
				arrayRoomName.add(roomname);
			}
			if(!found){
				lblMainRoom.setText("No selected room");
  				pnlTables.removeAll();

  				lblNoTablesIn = new JLabel("THERE IS NO TABLE IN THIS AREA.");
  				lblNoTablesIn.setFont(new Font("Monospaced", Font.PLAIN, 25));
  				pnlTables.add(lblNoTablesIn, "cell 0 0,alignx center");
				pnlTables.repaint();
				pnlTables.revalidate();
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
 	
 	public void ViewEvent(){
		try {
			Connect c = new Connect();
			c.pst = c.con.prepareCall("{call select_event_occupy(?,?)}");
			c.pst.setString(1, (String) cbRoom.getSelectedItem());
			c.pst.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(dateChooser.getDate()));
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			while(c.rs.next()){
				eventFound = true;
				eventreserveid  = c.rs.getString("reserve_id");
				eventroomid = c.rs.getString("reserve.room_id");
				eventreserveroom = c.rs.getString("room_name");
				eventname = c.rs.getString("reserve_name");
				eventcontact = c.rs.getString("reserve_contact_no");
				eventemail = c.rs.getString("reserve_email_address");
				eventguest = c.rs.getString("reserve_no_of_people");
				eventdate = c.rs.getString("reserve_date");
				eventtime = c.rs.getString("reserve_time");
				eventstatus = c.rs.getString("reserve_status");
				event_is_extended = c.rs.getString("reserve_is_extended");
//				minuscapacity = capacity -  deskcapacity;
				eventdesk = eventdate + " " +eventtime;
				_eventdate = null;
				eventsdfIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    eventsdfOut = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			    String input = eventdesk; 
			    if(eventdesk.equalsIgnoreCase("null null")){
			    	_eventdate = eventsdfIn.parse("2011-03-03 2:15:10");
			    }else if(eventdesk.equalsIgnoreCase("")){
			    	_eventdate = eventsdfIn.parse("2011-03-03 2:15:10");
			    }else{
			    	_eventdate = eventsdfIn.parse(input);
			    }							
				event_m_Date = new Date(eventsdfOut.format(_eventdate));
			}
			c.rs.close();
			c.pst.close();
			c.con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		pnlEvent.removeAll();
 		lblReserveId = new JLabel("Reserve ID:");
		lblReserveId.setForeground(Color.WHITE);
		lblReserveId.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlEvent.add(lblReserveId, "cell 0 0");
		
		labelReserveId = new JLabel(eventreserveid);
		labelReserveId.setForeground(Color.WHITE);
		labelReserveId.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlEvent.add(labelReserveId, "cell 1 0");
		
		labelStatus = new JLabel(eventstatus);
		labelStatus.setForeground(Color.WHITE);
		labelStatus.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlEvent.add(labelStatus, "cell 1 5,alignx left,aligny top");
		
		lblStatus = new JLabel("Status:");
		lblStatus.setForeground(Color.WHITE);
		lblStatus.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlEvent.add(lblStatus, "cell 0 5,alignx left,aligny top");
		
		lblGuest = new JLabel("Guest:");
		lblGuest.setForeground(Color.WHITE);
		lblGuest.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlEvent.add(lblGuest, "cell 0 4,alignx left,aligny top");
		
		lblEmail = new JLabel("Email:");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlEvent.add(lblEmail, "cell 0 3,alignx left,aligny top");
		
		labelGuest = new JLabel(eventguest);
		labelGuest.setForeground(Color.WHITE);
		labelGuest.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlEvent.add(labelGuest, "cell 1 4,alignx left,aligny top");
		
		labelEmail = new JLabel(eventemail);
		labelEmail.setForeground(Color.WHITE);
		labelEmail.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlEvent.add(labelEmail, "cell 1 3,alignx left,aligny top");
		
		labelContact = new JLabel(eventcontact);
		labelContact.setForeground(Color.WHITE);
		labelContact.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlEvent.add(labelContact, "cell 1 2,alignx left,aligny top");
		
		labelName = new JLabel(eventname);
		labelName.setForeground(Color.WHITE);
		labelName.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlEvent.add(labelName, "cell 1 1,alignx left,aligny top");
		
		lblName = new JLabel("Name:");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlEvent.add(lblName, "cell 0 1,alignx left,aligny top");
		
		lblContact = new JLabel("Contact:");
		lblContact.setForeground(Color.WHITE);
		lblContact.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlEvent.add(lblContact, "cell 0 2,alignx left,aligny top");

		pnlButtons = new JPanel();
		pnlButtons.setOpaque(false);
		pnlEvent.add(pnlButtons, "cell 0 6 2 2, growx");
		pnlButtons.setLayout(new MigLayout("", "[]", "[]"));
		
		btnStartEvent = new JButton("Start event");
		btnStartEvent.addActionListener(new ActionListener() {
			private String order_status;

			@Override
			public void actionPerformed(ActionEvent e) {
				if(btnStartEvent.getText().equals("Start event")){
					String[] options = null;
					options = new String[] {"Go back", "Yes ! " +  eventname + " is here"};														
					int reply = JOptionPane.showOptionDialog(null, "Is "+eventname+ " is here? " , eventname + "Event  Reservation",
							JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
							null, options, options[0]);
					if(reply == 1){
						try {
							Calendar cal = Calendar.getInstance();
							cal .add(Calendar.HOUR, 3);
							SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
							Connect con = new Connect();
							con.pst = con.con.prepareCall("{call seat_reserve(?,?)}");
							con.pst.setString(1, eventreserveid);
							con.pst.setString(2, sdf.format(cal.getTime()));	
							con.pst.execute();
							clockEvent.suspend();
							ViewEvent();
							Selection.ret.ViewEventReservation();
							JOptionPane.showMessageDialog(null, "Event started.");
							con.pst.close();
							con.con.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}	
					}else{
						
					}
				}else if(btnStartEvent.getText().equals("Finish event")){
						String[] options = null;
						options = new String[] {"Go back", "Yes ! End " + eventname + " reservation"};														
						int reply = JOptionPane.showOptionDialog(null, "End " + eventname+ " reservation? " , eventname + "Event  Reservation",
								JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
								null, options, options[0]);
						if(reply == 1){
							try {
									Connect con = new Connect();
									con.pst = con.con.prepareCall("{call check_order_status_event(?)}");
										con.pst.setString(1, eventreserveid);
										con.pst.execute();
										con.rs = con.pst.getResultSet();
										while(con.rs.next()){
											order_status = con.rs.getString("order_status");
										}
										if(order_status.equalsIgnoreCase("Balance") || order_status.equalsIgnoreCase("Unpaid")){
											JOptionPane.showMessageDialog(null, "Please complete your payment first.");
										}
										else{
											con.pst = con.con.prepareCall("{call end_session(?)}");
											con.pst.setString(1, eventreserveid);
											con.pst.execute();
											clockEvent.suspend();
											ViewEvent();
											Selection.ret.ViewEventReservation();
											JOptionPane.showMessageDialog(null, "Event ended.");
											con.pst.close();
											con.con.close();
										}
									
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
						}else{
							
						}
				}
			}
		});
		btnStartEvent.setOpaque(false);
		btnStartEvent.setEnabled(false);
		btnStartEvent.setUI(new MetalButtonUI());
		btnStartEvent.setForeground(Color.WHITE);
		btnStartEvent.setIcon(new ImageIcon(POSDesign.class.getResource("/Images/Icon/save.png")));
		btnStartEvent.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnStartEvent.setContentAreaFilled(false);
		pnlButtons.add(btnStartEvent, "cell 0 0");

		btnOrderEvent = new JButton("Order");
		btnOrderEvent.setEnabled(false);
		btnOrderEvent.setIcon(new ImageIcon(POSDesign.class.getResource("/Images/Icon/order.png")));
		btnOrderEvent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EventOrderReserve ns = new EventOrderReserve();
				EventSalesReserveTable nst = new EventSalesReserveTable(ns);
				ModelSalesEventOrder ms = new ModelSalesEventOrder();
				new RemoveController(ns, ms, nst);
				new AddController(ns, ms, nst);
				nst.CounterOrder();
				nst.ViewTableOrders(eventreserveid);
				ns.tfId.setText(eventreserveid);
				ns.tfName.setText(eventname);
				ns.tfType.setText("Reserve");
				ns.lblTakeDinein.setText("Dine-in");
				ns.tabbedPane.remove(ns.pnlTables);
				ns.checkTable = false;
				ns.tabbedPane.repaint();
				ns.setModal(true);
				ns.setSize(1000,666);
				ns.setVisible(true);
			}
		});
		btnOrderEvent.setOpaque(false);
		btnOrderEvent.setUI(new MetalButtonUI());
		btnOrderEvent.setForeground(Color.WHITE);
		btnOrderEvent.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnOrderEvent.setContentAreaFilled(false);
		pnlButtons.add(btnOrderEvent, "cell 1 0");
		
		btnViewOrders = new JButton("View Orders");
		btnViewOrders.setEnabled(false);
		btnViewOrders.setIcon(new ImageIcon(POSDesign.class.getResource("/Images/Icon/generate.png")));
		btnViewOrders.addActionListener(new ActionListener() {
			private String total;
			private String orderid;
			private String orderstatus;

			@Override
			public void actionPerformed(ActionEvent e) {
					DineinEventOrder ro = new DineinEventOrder();
					ro.setSize(800, 400);
					ro.tfName.setText(eventname);
					DineinEventOrder.tfRoom.setText((String) cbRoom.getSelectedItem());
					DineinEventOrder.rtype = "Reserve";
					DineinEventOrder.rname =eventname;
					DineinEventOrder.reserveid= eventreserveid;
					try {
						Connect con = new Connect();
						con = new Connect();
						con.pst = con.con.prepareCall("{call select_all_food_event_orders(?)}");
						con.pst.setString(1, eventreserveid);
						con.pst.execute();
						con.rs = con.pst.getResultSet();
						DineinEventOrder.tblOrderList.setModel(DbUtils.resultSetToTableModel(con.rs));
						con.rs.close();
						con.pst.close();
						con.con.close();
						
						con = new Connect();
						con.pst = con.con.prepareCall("call dinein_event_orders(?)");
						con.pst.setString(1, eventreserveid);
						con.pst.execute();
						con.rs = con.pst.getResultSet();
						while(con.rs.next()){
			            	found  = true;
							total = con.rs.getString("order_total");
							orderid = con.rs.getString("order_id");
							orderstatus = con.rs.getString("order_status");
							DineinEventOrder.rid = orderid;
							DineinEventOrder.reserveid = eventreserveid;
							DineinEventOrder.tfTotal.setText(total);
							DineinEventOrder.tfOrderId.setText(orderid);
							DineinEventOrder.tfStatus.setText(orderstatus);
							ro.btnOrder.setText("Update Orders");
							ro.btnOrder.setName("Update Orders");  
						}
						if(!found){
					  		CounterNormalOrder cc = new CounterNormalOrder();
					  		cc.getAccountNumber();
					  		if(!cc.getAccountNumber().isEmpty()){
					  			DineinEventOrder.tfOrderId.setText(cc.getAccountNumber());
								DineinEventOrder.rid = cc.getAccountNumber();
					  		}
							ro.btnOrder.setText("Add Orders");
							ro.btnOrder.setName("Add Orders");
							DineinEventOrder.tfTotal.setText("0.0");
							DineinEventOrder.tfStatus.setText("No order yet");
						}
						
						con.rs.close();
						con.pst.close();
						con.con.close();
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
					ro.setModal(true);
					ro.setVisible(true);
			}
		});
		btnViewOrders.setOpaque(false);
		btnViewOrders.setUI(new MetalButtonUI());
		btnViewOrders.setForeground(Color.WHITE);
		btnViewOrders.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnViewOrders.setContentAreaFilled(false);
		pnlButtons.add(btnViewOrders, "cell 2 0");
		
		btnCancelReservation = new JButton("Cancel reservation");
		btnCancelReservation.setEnabled(false);
		btnCancelReservation.setIcon(new ImageIcon(POSDesign.class.getResource("/Images/Icon/remove.png")));
		btnCancelReservation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int reply1 = JOptionPane.showConfirmDialog(null, "Do you really want to cancel reservation?", "Cancel", JOptionPane.YES_NO_OPTION);
		        if (reply1 == JOptionPane.YES_OPTION) {
					try {
						Connect con = new Connect();
						con.pst = con.con.prepareCall("{call cancel_reserve(?)}");
						con.pst.setString(1, eventreserveid);
						con.pst.execute();
						JOptionPane.showMessageDialog(null, "Reserve Cancelled");
						Selection.ret.ViewEventReservation();
						SetTables();
						clockEvent.suspend();
						ViewEvent();
						con.pst.close();
						con.con.close();
					} catch (SQLException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}
		        }
		        else {
		        	
		        }
			}
		});
		btnCancelReservation.setOpaque(false);
		btnCancelReservation.setUI(new MetalButtonUI());
		btnCancelReservation.setForeground(Color.WHITE);
		btnCancelReservation.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancelReservation.setContentAreaFilled(false);
		pnlButtons.add(btnCancelReservation, "cell 3 0");
		if(!eventFound ){
			NoAssignedEvents();
		}

		clockEvent = new Thread(){
			public void run(){
				try {
					for(;;){ 	
						if(eventFound){
						if(eventreserveroom.equalsIgnoreCase((String)cbRoom.getSelectedItem())){
						Date currentDate = new Date();
							if (event_m_Date.after(currentDate)){	
								// Calculate difference in dates
								long numericalDifference =  event_m_Date.getTime() - currentDate.getTime();

								// Divide by 1000 to find number of seconds difference
								numericalDifference = numericalDifference / 1000;
								
								// Get seconds
								int seconds = (int) numericalDifference % 60;

								// Get minutes
								numericalDifference = numericalDifference / 60;
								int minutes = (int) numericalDifference % 60;

								// Get hours
								numericalDifference = numericalDifference / 60;
								int hours = (int) numericalDifference % 24;

								// Get days
								numericalDifference = numericalDifference / 24;
								int days = (int) numericalDifference % 365;

								// Get week
								numericalDifference = numericalDifference / 7;
								int week = (int) numericalDifference;
								
								// Get month
								numericalDifference = numericalDifference / 30;
								if(week == 0 && days != 0 && hours != 0 && minutes !=0 ){
									count = "<html>" + days + " Days<br> " + hours   + " Hrs.<br>" + minutes + " Mins.<br>"  + seconds + " Secs." + " Left </html> </html>";		
								}else if(week == 0 && days == 0 && hours != 0 && minutes !=0 ){
									count = "<html>" +  hours   + " Hrs.<br>" + minutes + " Mins.<br>"  + seconds + " Secs." + " Left </html>";		
								}else if(week == 0 && hours == 0 && days == 0 && minutes !=0 ){
									count = "<html>" +  minutes + " Mins.<br>"  + seconds + " Secs." + " Left </html>";
								}else if(week == 0 && hours == 0 && days == 0 && minutes == 0){
									count = "<html>" + seconds + " Secs." + " Left </html>";
								}else{
									count =  week + "W, " + days + "D, " + hours   + "H, " + minutes + "M" + " Left";														
								}

								// Generate date string
								if(hours < 2 && days == 0 && week == 0  && eventstatus.equalsIgnoreCase("Reserve") ){
									btnOrderEvent.setEnabled(true);
									btnViewOrders.setEnabled(true);
									btnStartEvent.setEnabled(true);
									btnCancelReservation.setEnabled(true);
									btnOrderEvent.setText("Advance Order");
									pnlEvent.show();
									btnStartEvent.setText("Start event");	
									pnlEvent.setBackground(new Color(178, 73, 55));
									 AssignedEvents();
								}//RESERVE
								else if(eventstatus.equalsIgnoreCase("Occupied") ){
									btnOrderEvent.setEnabled(true);
									btnViewOrders.setEnabled(true);
									btnCancelReservation.setEnabled(true);
									btnOrderEvent.setText("Add Order");
									pnlEvent.show();
									btnStartEvent.setEnabled(true);
									btnStartEvent.setText("Finish event");
									btnCancelReservation.hide();
									pnlEvent.setBackground(new Color(35, 182, 90));
									 AssignedEvents();
								}//OCCUPIED
								else if(eventstatus.equalsIgnoreCase("Reserve")){
									pnlEvent.show();
									btnOrderEvent.setEnabled(true);
									btnCancelReservation.setEnabled(false);
									btnOrderEvent.setText("Advance Order");
									btnViewOrders.setEnabled(true);
									btnStartEvent.setEnabled(true);
									btnStartEvent.setText("Start event");
									pnlEvent.setBackground(new Color(166, 161, 18));
									 AssignedEvents();
								}//OCCUPIED
								else{
									NoAssignedEvents();
								}
						} else{
//							if (eventstatus.equalsIgnoreCase("Occupied") ){
//								btnOrderEvent.setEnabled(true);
//								btnCancelReservation.setEnabled(false);
//								btnViewOrders.setEnabled(true);
//								pnlEvent.show();
//								btnStartEvent.setEnabled(true);
//								btnStartEvent.setText("Finish event");
//								pnlEvent.setBackground(new Color(35, 182, 90));
//							}//OCCUPIED
//							else if(eventstatus.equalsIgnoreCase("Reserve")){
//								pnlEvent.show();
//								btnOrderEvent.setEnabled(true);
//								btnCancelReservation.setEnabled(true);
//								btnViewOrders.setEnabled(true);
//								btnStartEvent.setEnabled(true);
//								btnStartEvent.setText("Start event");	
//								pnlEvent.setBackground(new Color(166, 161, 18));
//							}//OCCUPIED
//							else{
//								btnCancelReservation.setEnabled(false);
//								btnOrderEvent.setEnabled(false);
//								btnViewOrders.setEnabled(false);
//								btnStartEvent.setEnabled(false);
//								pnlEvent.hide();
//								pnlEvent.setBackground(new Color(51, 122, 152));
//							}

							NoAssignedEvents();
						}
						}else{
							NoAssignedEvents();
						}
						sleep(1000);
						pnlEvent.revalidate();
						pnlEvent.repaint();
					}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		clockEvent.start();
 	}
 	public void NoAssignedEvents(){
		pnlEvent.removeAll();
		
		lblThereAreNo = new JLabel("THERE ARE NO ASSIGNED EVENTS IN THIS ROOM.");
		lblThereAreNo.setFont(new Font("Monospaced", Font.PLAIN, 20));
		lblThereAreNo.setForeground(Color.WHITE);
		pnlEvent.add(lblThereAreNo, "cell 0 0,alignx center");
		pnlEvent.setBackground(new Color(51, 122, 152));
		pnlEvent.revalidate();
		pnlEvent.repaint();
 	}
 	public void AssignedEvents(){
			pnlTables.removeAll();

			lblNoTablesIn = new JLabel("THERE IS AN EVENT IN THIS AREA.");
			lblNoTablesIn.setFont(new Font("Monospaced", Font.PLAIN, 25));
			pnlTables.add(lblNoTablesIn, "cell 0 0,alignx center");
 	}
}
