//package Capstone.View;
//
//import javax.imageio.ImageIO;
//import javax.swing.JPanel;
//
//import net.miginfocom.swing.MigLayout;
//
//import javax.swing.JScrollPane;
//import javax.swing.JSpinner.DateEditor;
//import javax.swing.JOptionPane;
//import javax.swing.JTextField;
//import javax.swing.JToggleButton;
//import javax.swing.ListSelectionModel;
//import javax.swing.SpinnerDateModel;
//import javax.swing.SwingConstants;
//import javax.swing.UIManager;
//
//import java.awt.Dimension;
//import java.awt.Font;
//import java.awt.Graphics;
//import java.awt.Insets;
//
//import javax.swing.JButton;
//
//import java.awt.Color;
//
//import javax.swing.border.LineBorder;
//import javax.swing.event.AncestorEvent;
//import javax.swing.event.AncestorListener;
//import javax.swing.event.ListSelectionEvent;
//import javax.swing.event.ListSelectionListener;
//import javax.swing.plaf.metal.MetalButtonUI;
//import javax.swing.table.TableModel;
//import javax.swing.text.DateFormatter;
//
//import Capstone.Controller.AddController;
//import Capstone.Database.Connect;
//import Capstone.Model.CalendarModel;
//import Capstone.Model.ModelDesks;
//import Capstone.Model.ModelFloor;
//import Capstone.Model.ModelReserve;
//import Capstone.Model.ModelTable;
//import Capstone.Model.ModelWaiting;
//import Capstone.Table.FloorTable;
//import Capstone.Table.HoverTables;
//import Capstone.Table.POSTable;
//import Capstone.Table.ReservationTable;
//import Capstone.Table.ReserveTable;
//import Capstone.Table.WaitingTable;
//
//import java.awt.event.ActionListener;
//import java.awt.event.ComponentEvent;
//import java.awt.event.ComponentListener;
//import java.awt.event.FocusEvent;
//import java.awt.event.FocusListener;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.awt.SystemColor;
//import java.awt.event.ActionEvent;
//import java.awt.geom.AffineTransform;
//import java.awt.image.AffineTransformOp;
//import java.awt.image.BufferedImage;
//import java.awt.Cursor;
//import java.beans.PropertyChangeEvent;
//import java.beans.PropertyChangeListener;
//import java.io.File;
//import java.sql.SQLException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//
//import javax.swing.ButtonGroup;
//
//import com.toedter.calendar.IDateEditor;
//import com.toedter.calendar.JCalendar;
//
//import javax.swing.JSpinner;
//import javax.swing.event.ChangeListener;
//import javax.swing.event.ChangeEvent;
//import javax.swing.JTable;
//import javax.swing.JLabel;
//import javax.swing.JRadioButton;
//
//import java.awt.event.ItemListener;
//import java.awt.event.ItemEvent;
//
//public class POS extends JPanel implements MouseListener, FocusListener, ListSelectionListener, ActionListener, ItemListener {
////	private JButton btnDynamic;
//
//    private static  IDateEditor  dateEditor;
//	private static String[] description;
//	public static JToggleButton[] btnDynamic;
//
//	ModelTable mt;
//	static String[] id;
//	static String desc[];
//	static int width;
//	static int height;
//	static int x;
//	static int y;
//	static int i = 0; 
//	String status;
//	public boolean editing = false;
//	static boolean boolCheck = true;
//	String floor_name;
//
//	FloorPlan f;
//	ModelFloor mf;
//	FloorTable ft;
//	
//	
//	/**Create the panel.**/
//	public static BackgroundImageApp pnlDecorate;
//	static File file;
//	private final ButtonGroup bgMode = new ButtonGroup();
//	private static JPanel pnlSetDecor;
//	private JScrollPane scrollPane_1;
//	public static JPanel pnlNavigations;
//	private static JCalendar calendar;
//	private static JPanel pnlReserveList;
//	private JSpinner spinner;
//	DateFormatter formatter;
//	DateEditor editor;
//	Calendar cal;
//	SpinnerDateModel model;
//	DateFormatter formatter2;
//	DateEditor editor2;
//	Calendar cal2;
//	SpinnerDateModel model2;
//	public static JTable tblTables;
//	private static JScrollPane spTables;
//	static JButton btnNewWaiting;
//	static JButton btnEditWaiting;
//	static JButton btnCancelSelection;
//	static JButton btnSelectTable;
//	static JPanel pnlButtons;
//	
//	String wid;
//	String wname;
//	String wno;
//	String type = "Normal";
//	private static JScrollPane scrollPane_2;
//	private static JTextField tfWaitingId;
//	protected static String[] deskstatus;
//	protected static String[] deskdate;
//	protected static String[] desktime;
//	protected static String[] countString;
//	protected static Date[] m_Date;
//    static JButton btnCancelTableSelection;
//	private static JScrollPane scrollPane;
//	static boolean enable = true;
//	private static String[] deskid;
//	private static String[] deskdesc;
//	private static int iDesk;
//	static Thread clock;
//	static Date[] date;
//	static SimpleDateFormat sdfIn[];
//	static SimpleDateFormat sdfOut[];
//	static String desk[];
//	private static JPanel pnlTables;
//	private static JLabel[] lblTables;
//	private static JPanel pnlTable;
//	static boolean select[];
//	static boolean selecttable[];
//	private JTextField tfDate;
//	private static JButton btnOrder;
//	private JPanel pnlCalendar;
//	private static JTextField txtNavigation;
//	private static JTextField txtTableList;
//	private JTextField txtDate;
//	private JRadioButton rdbtnDineIn;
//	private JRadioButton rdbtnTakeout;
//	private final ButtonGroup bgTables = new ButtonGroup();
//	public JRadioButton rdbtnWaitingList;
//	private JButton btnBillOut;
//	private String dtid;
//	private String did;
//	private JPanel panel_1;
//	private JLabel label;
//	private JPanel panel_2;
//	private JLabel lblVacant;
//	private JPanel panel_3;
//	private JLabel lblPreparing;
//	private JButton btnCancelWaiting;
//	private JLabel lblReserved;
//	private JPanel panel_4;
//	
//	public POS() {
//		addAncestorListener(new AncestorListener() {
//			public void ancestorAdded(AncestorEvent arg0) {
//			}
//			public void ancestorMoved(AncestorEvent arg0) {
//			}
//			public void ancestorRemoved(AncestorEvent arg0) {
//				clock.suspend();
//			}
//		});
//		System.setProperty("Quaqua.tabLayoutPolicy","wrap");
//        // set the Quaqua Look and Feel in the UIManager
//		try {
//			UIManager.setLookAndFeel(ch.randelshofer.quaqua.QuaquaManager.getLookAndFeel());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}		
//		setBackground(SystemColor.menu);
//		setBorder(new LineBorder(new Color(0, 0, 0)));
//		setVisible(true);
//		setLayout(new MigLayout("", "[grow]", "[][720][grow]"));
//		
//		JPanel panel = new JPanel();
//		add(panel, "cell 0 0,grow");
//		panel.setLayout(new MigLayout("", "[50px][][50px][][50px][][50px][]", "[50,grow]"));
//		
//		panel_1 = new JPanel();
//		panel_1.setOpaque(true);
//		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
//		panel_1.setBackground(Color.GREEN);
//		panel.add(panel_1, "cell 0 0,grow");
//		panel_1.setLayout(new MigLayout("", "[]", "[]"));
//		
//		label = new JLabel("OCCUPIED");
//		label.setFont(new Font("Monospaced", Font.PLAIN, 15));
//		panel.add(label, "cell 1 0");
//		
//		panel_2 = new JPanel();
//		panel_2.setOpaque(true);
//		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
//		panel_2.setBackground(Color.CYAN);
//		panel.add(panel_2, "cell 2 0,grow");
//		panel_2.setLayout(new MigLayout("", "[]", "[]"));
//		
//		lblVacant = new JLabel("VACANT");
//		lblVacant.setFont(new Font("Monospaced", Font.PLAIN, 15));
//		panel.add(lblVacant, "cell 3 0");
//		
//		panel_3 = new JPanel();
//		panel_3.setOpaque(true);
//		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
//		panel_3.setBackground(Color.RED);
//		panel.add(panel_3, "cell 4 0,grow");
//		panel_3.setLayout(new MigLayout("", "[]", "[]"));
//		
//		lblPreparing = new JLabel("PREPARING");
//		lblPreparing.setFont(new Font("Monospaced", Font.PLAIN, 15));
//		panel.add(lblPreparing, "cell 5 0");
//		
//		panel_4 = new JPanel();
//		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
//		panel_4.setOpaque(true);
//		panel_4.setBackground(Color.YELLOW);
//		panel.add(panel_4, "cell 6 0,grow");
//		
//		lblReserved = new JLabel("RESERVED");
//		lblReserved.setFont(new Font("Monospaced", Font.PLAIN, 15));
//		panel.add(lblReserved, "cell 7 0");
//		scrollPane_1 = new JScrollPane();
//		add(scrollPane_1, "cell 0 1,grow");
//
//		
//		pnlSetDecor = new JPanel();
//		pnlSetDecor.setBorder(new LineBorder(new Color(0, 0, 0)));
//		scrollPane_1.setViewportView(pnlSetDecor);
//		pnlSetDecor.setLayout(null);
//		
//		pnlNavigations = new JPanel();
//		pnlNavigations.setBorder(new LineBorder(new Color(0, 0, 0)));
//		add(pnlNavigations, "cell 0 2,grow");
//		pnlNavigations.setLayout(new MigLayout("", "[][grow]", "[]"));
//		
//		pnlCalendar = new JPanel();
//		pnlCalendar.setBorder(new LineBorder(new Color(0, 0, 0)));
//		pnlNavigations.add(pnlCalendar, "cell 0 0,grow");
//		pnlCalendar.setLayout(new MigLayout("", "[grow]", "[][][][]"));
//		
//		txtDate = new JTextField();
//		txtDate.setText("DATE");
//		txtDate.setHorizontalAlignment(SwingConstants.CENTER);
//		txtDate.setFont(new Font("Monospaced", Font.PLAIN, 15));
//		txtDate.setEnabled(false);
//		txtDate.setEditable(false);
//		txtDate.setColumns(10);
//		pnlCalendar.add(txtDate, "cell 0 0,growx");
//		
//		tfDate = new JTextField();
//		pnlCalendar.add(tfDate, "cell 0 1,growx");
//		tfDate.setFont(new Font("Monospaced", Font.PLAIN, 15));
//		tfDate.setEditable(false);
//		tfDate.setColumns(10);
//		
//		calendar = new JCalendar();
//		pnlCalendar.add(calendar, "cell 0 2");
//		calendar.getYearChooser().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		calendar.getMonthChooser().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		calendar.getDayChooser().getDayPanel().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		calendar.setTodayButtonVisible(true);
//		
//		calendar.setMinSelectableDate(new Date());        
//		//SPINNER TIME
//	    model = new SpinnerDateModel();
//	    cal = Calendar.getInstance();
//        cal.set(Calendar.SECOND, 0);	
//        model.setValue(cal.getTime());
//		spinner = new JSpinner(model);
//		pnlCalendar.add(spinner, "cell 0 3,grow");
//		spinner.setFont(new Font("Monospaced", Font.PLAIN, 15));
//		
//		editor = new DateEditor(spinner, "HH:mm");
//		formatter = (DateFormatter)editor.getTextField().getFormatter();
//		formatter.setAllowsInvalid(false); // this makes what you want
//		formatter.setOverwriteMode(true);
//		//SPINNER TIME
//		spinner.setEditor(editor);
//		spinner.addChangeListener(new ChangeListener() {
//			public void stateChanged(ChangeEvent arg0) {
//				tblTables.clearSelection();
//				pnlButtons.removeAll();
//				
//		        btnNewWaiting = new JButton("New Reserve");
//		        pnlButtons.add(btnNewWaiting, "cell 0 0,grow");
//		        Listener();
//		        DefaultButtonColor();
//		        pnlButtons.repaint();
//		        pnlButtons.revalidate();
//			}
//		});
//		calendar.getDayChooser().addPropertyChangeListener(new PropertyChangeListener() {
//			public void propertyChange(PropertyChangeEvent arg0) {
//				tfDate.setText(String.valueOf(new SimpleDateFormat("MMMM dd, yyyy").format(calendar.getDate())));
//				Transaction.pt.ViewWaiting();
//				ViewDecorate();
//				ViewOccupied();
//				clock.suspend();
//				ViewOccupied();
//				RadioButtonListener();
//			}
//		});
//		
//		pnlReserveList = new JPanel();
//		pnlReserveList.setBorder(new LineBorder(new Color(0, 0, 0)));
//		pnlNavigations.add(pnlReserveList, "cell 1 0,grow");
//		pnlReserveList.setLayout(new MigLayout("", "[][grow]", "[]"));
//        
//        pnlTable = new JPanel();
//        pnlReserveList.add(pnlTable, "cell 1 0,grow");
//        pnlTable.setLayout(new MigLayout("", "[grow]", "[][][][grow]"));
//        
//        txtTableList = new JTextField();
//        txtTableList.setEnabled(false);
//        txtTableList.setHorizontalAlignment(SwingConstants.CENTER);
//        txtTableList.setText("TABLES");
//        txtTableList.setFont(new Font("Monospaced", Font.PLAIN, 15));
//        txtTableList.setEditable(false);
//        txtTableList.setColumns(10);
//        pnlTable.add(txtTableList, "cell 0 0,growx");
//        
//        rdbtnWaitingList = new JRadioButton("WAITING");
//        rdbtnWaitingList.setSelected(true);
//        bgTables.add(rdbtnWaitingList);
//        rdbtnWaitingList.setFont(new Font("Monospaced", Font.PLAIN, 15));
//        pnlTable.add(rdbtnWaitingList, "flowx,cell 0 1,alignx left");
//        
//        rdbtnDineIn = new JRadioButton("DINE-IN");
//        bgTables.add(rdbtnDineIn);
//        rdbtnDineIn.setFont(new Font("Monospaced", Font.PLAIN, 15));
//        pnlTable.add(rdbtnDineIn, "cell 0 1,alignx left,growy");
//        
//        spTables = new JScrollPane();
//        pnlTable.add(spTables, "flowx,cell 0 2 1 2,grow");
//        
//        tblTables = new JTable();
//        tblTables.setRowHeight(30);
//        tblTables.setFont(new Font("Monospaced", Font.PLAIN, 15));
//        spTables.setViewportView(tblTables);
//        
//        rdbtnTakeout = new JRadioButton("TAKE-OUT");
//        bgTables.add(rdbtnTakeout);
//        rdbtnTakeout.setFont(new Font("Monospaced", Font.PLAIN, 15));
//        pnlTable.add(rdbtnTakeout, "cell 0 1,alignx left,growy");
//        
//        pnlButtons = new JPanel();
//        pnlReserveList.add(pnlButtons, "cell 0 0,grow");
//        pnlButtons.setLayout(new MigLayout("", "[grow]", "[][][][][]"));
//        
//        txtNavigation = new JTextField();
//        txtNavigation.setEnabled(false);
//        txtNavigation.setText("NAVIGATION");
//        txtNavigation.setHorizontalAlignment(SwingConstants.CENTER);
//        txtNavigation.setFont(new Font("Monospaced", Font.PLAIN, 15));
//        txtNavigation.setEditable(false);
//        txtNavigation.setColumns(10);
//        pnlButtons.add(txtNavigation, "cell 0 0,growx");
//        
//        btnNewWaiting = new JButton("New Waiting");
//        btnNewWaiting.setFont(new Font("Monospaced", Font.PLAIN, 15));
//        pnlButtons.add(btnNewWaiting, "cell 0 1,grow");
//        
//        btnOrder = new JButton("Order");
//        btnOrder.setFont(new Font("Monospaced", Font.PLAIN, 15));
//        pnlButtons.add(btnOrder, "cell 0 2	,grow");
//        
//        RadioButtonListener();
//    	SelectDefaultImage();
//    	Listener();
//    	DefaultButtonColor();
//	}
//	
//		
//	//COLORS
//	public Color getPanelColor(){
//		return SystemColor.menu;
//	}
//	public static Color getButtonBackgroundColor(){
//        return new Color(27, 12, 95);
//    }
//	public Color getButtonHoverBackgroundColor(){
//		return new Color(194, 253, 249);
//	}
//	public static Color getButtonForegroundColor(){
//		return new Color(215, 203, 0);
//	}
//	public Color getButtonHoverForegroundColor(){
//		return Color.BLACK;
//	}
//
//    public Color getTextFieldColor(){
//		return SystemColor.controlHighlight;
//    }    
//    public void RadioButtonListener(){
//
//        rdbtnWaitingList.addItemListener(this);
//        rdbtnDineIn.addItemListener(this);
//        rdbtnTakeout.addItemListener(this);
//    	
//    }
//    public void ListenerWhenClickTable(){
//
//    	btnCancelSelection.addMouseListener(this);
//    	btnCancelSelection.addFocusListener(this);
//    	btnCancelSelection.addActionListener(this);
//
//		btnCancelSelection.setUI(new MetalButtonUI());
//		btnCancelSelection.setBackground(getButtonBackgroundColor());
//		btnCancelSelection.setForeground(getButtonForegroundColor());
//		
//
//
//    }
//    public void Listener(){
//    	btnNewWaiting.addMouseListener(this);
//    	btnNewWaiting.addFocusListener(this);
//    	btnNewWaiting.addActionListener(this);
//
//    	btnOrder .addMouseListener(this);
//    	btnOrder.addFocusListener(this);
//    	btnOrder.addActionListener(this);
//
//		ListSelectionModel selectionModel = tblTables.getSelectionModel();		
//		selectionModel.addListSelectionListener( this );
//    }
//    //COLORS
//	public static void DefaultButtonColor(){
//		btnNewWaiting.setUI(new MetalButtonUI());
//		btnNewWaiting.setBackground(getButtonBackgroundColor());
//		btnNewWaiting.setForeground(getButtonForegroundColor());
//		
//		btnOrder.setUI(new MetalButtonUI());
//		btnOrder.setBackground(getButtonBackgroundColor());
//		btnOrder.setForeground(getButtonForegroundColor());
//
//	}	
//	public void DefaultPanelColor(){
//		setBackground(getPanelColor());
//		pnlButtons.setBackground(getPanelColor());
//		pnlDecorate.setBackground(getPanelColor());
//		pnlSetDecor.setBackground(getPanelColor());
//		pnlTable.setBackground(getPanelColor());
//		pnlReserveList.setBackground(getPanelColor());
//		pnlNavigations.setBackground(getPanelColor());
//		
//	}	
//	
//	public static CalendarModel getCalendarData(){
//		CalendarModel cm = new CalendarModel();
//		cm.setDate(calendar.getDate());
//		return cm;
//	}
//	 public void setMinSelectableDate(Date min) {
//	        calendar.setMinSelectableDate(min);
//	        dateEditor.setMinSelectableDate(min);
//    }
//	//Selecting the image to database
//	public void SelectDefaultImage(){		
//		try{
//			Connect c = new Connect();
//			c.pst = c.con.prepareCall("{call select_all_floor()}");
//			c.pst.execute();
//			c.rs = c.pst.getResultSet();
//			if (c.rs.first()) { 
//				String file_dest = c.rs.getString("Destination");
//				file = new File(file_dest);
//			}
//			c.rs.close();
//			c.pst.close();
//			c.con.close();
//		}catch(Exception ed){
//			ed.printStackTrace();
//		}	
//	}
//	//end of Selecting the image to database
//	
//	//View Occupied
//	public static void ViewOccupied(){
//		try {
//			iDesk = 0;
//			Connect c = new Connect();
//			c.pst = c.con.prepareCall("{call select_table_info(?,?)}");
//			c.pst.setString(1, "");
//			c.pst.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(calendar.getDate()));
//			c.pst.execute();
//			c.rs = c.pst.getResultSet();
//			while(c.rs.next()){
//				deskid[iDesk] = c.rs.getString("desk_id");
//				deskdesc[iDesk] = c.rs.getString("Table");
//				deskdate[iDesk] = c.rs.getString("Date");
//				desktime[iDesk] = c.rs.getString("Time");
//				deskstatus[iDesk] = c.rs.getString("Status");
//				desk[iDesk] = deskdate[iDesk] + " " +desktime[iDesk];
//				date[iDesk] = null;
//				sdfIn[iDesk] = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			    sdfOut[iDesk] = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
//			    String input = desk[iDesk];
//			    if(desk[iDesk].equalsIgnoreCase("null null")){
//				    date[iDesk] = sdfIn[iDesk].parse("2011-03-03 2:15:10");
//			    }else if(desk[iDesk].equalsIgnoreCase("")){
//				    date[iDesk] = sdfIn[iDesk].parse("2011-03-03 2:15:10");
//			    }else{
//				    date[iDesk] = sdfIn[iDesk].parse(input);
//			    }							
//				m_Date[iDesk] = new Date(sdfOut[iDesk].format(date[iDesk]));
//				iDesk++;
//			}
//			c.rs.close();
//			c.pst.close();
//			c.con.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		clock = new Thread(){
//			public void run(){
//				try {
//					for(;;){
//							for(int j = 0; j < i; j++){
//								for (int k = 0; k < i; k++) {									
//									Date currentDate = new Date();
//									if(btnDynamic[j].getActionCommand().equalsIgnoreCase(deskdesc[k])){
//										select[j] = false;
//										if (m_Date[k].after(currentDate)){
//											// Calculate difference in dates
//											long numericalDifference =  m_Date[k].getTime() - currentDate.getTime();
//	
//											// Divide by 1000 to find number of seconds difference
//											numericalDifference = numericalDifference / 1000;
//											
//											// Get seconds
//											int seconds = (int) numericalDifference % 60;
//	
//											// Get minutes
//											numericalDifference = numericalDifference / 60;
//											int minutes = (int) numericalDifference % 60;
//	
//											// Get hours
//											numericalDifference = numericalDifference / 60;
//											int hours = (int) numericalDifference % 24;
//	
//											// Get days
//											numericalDifference = numericalDifference / 24;
//											int days = (int) numericalDifference % 365;
//
//											// Get week
//											numericalDifference = numericalDifference / 7;
//											int week = (int) numericalDifference;
//											
//											if(week == 0 ){
//												countString[k] =  days + "D, " + hours   + "H, " + minutes + "M, " + seconds + "S, ";		
//											}else if(week == 0 && days == 0){
//												countString[k] =  hours   + "H, " + minutes + "M, " + seconds + "S, ";		
//											}else if(week == 0 && hours == 0 && days == 0){
//												countString[k] =  minutes + "M, " + seconds + "S, ";
//												
//											}else if(week == 0 && hours == 0 && days == 0 && minutes == 0){
//												countString[k] = seconds + "S, ";
//											}else{
//												countString[k] =  week + "W, " + days + "D, " + hours   + "H, " + minutes + "M, " + seconds + "S, ";														
//											}
//												// Generate date string
//												if(deskstatus[k].equalsIgnoreCase("Reserve")){
//												btnDynamic[j].setText("<html><center>" + desc[j] +  "<br>" + 
//														countString[k] + 
//														"<br>("+deskstatus[k]+")" + "</center></html>");	
//												btnDynamic[j].setBackground(Color.YELLOW);
//												btnDynamic[j].setBorder(new LineBorder(Color.BLACK));	
//												
//												}
//												else if(deskstatus[k].equalsIgnoreCase("Occupied")){
//													btnDynamic[j].setText("<html><center>" + desc[j] +  "<br>" + 
//															countString[k] + 
//															"<br>("+deskstatus[k]+")" + "</center></html>");	
//													btnDynamic[j].setBackground(Color.GREEN);
//													btnDynamic[j].setBorder(new LineBorder(Color.BLACK));	
//													
//												}else{
//
//													btnDynamic[j].setText("<html><center>" + desc[j] +  "<br>"  + 
//															"(Vacant)" + "</center></html>");	
//													btnDynamic[j].setBackground(Color.CYAN);
//													btnDynamic[j].setBorder(new LineBorder(Color.BLACK));		
//												}
//											if(hours < 2 && days == 0 && week == 0 && deskstatus[k].equalsIgnoreCase("Reserve")){
//												System.out.println("Table " +btnDynamic[j].getActionCommand()+": 2 hours has been passed. Please make a preparations");
//												btnDynamic[j].setBackground(Color.RED);
//												btnDynamic[j].setBorder(new LineBorder(Color.BLACK));		
//											}
//										}else{
//											if(deskstatus[k].equalsIgnoreCase("Reserve")){
//												btnDynamic[j].setText("<html><center>" + desc[j] +  "<br>"  + 
//														"(Vacant)" + "</center></html>");	
//												btnDynamic[j].setBackground(Color.CYAN);
//												btnDynamic[j].setBorder(new LineBorder(Color.BLACK));		
//												
//											}else if(deskstatus[k].equalsIgnoreCase("Occupied")){
//												btnDynamic[j].setText("<html><center>" + desc[j] +  "<br>"  + 
//														"(Vacant)" + "</center></html>");	
//												btnDynamic[j].setBackground(Color.CYAN);
//												btnDynamic[j].setBorder(new LineBorder(Color.BLACK));														
//											}else{
//												btnDynamic[j].setText("<html><center>" + desc[j] +  "<br>"  + 
//														"(Vacant)" + "</center></html>");	
//												btnDynamic[j].setBackground(Color.CYAN);
//												btnDynamic[j].setBorder(new LineBorder(Color.BLACK));														
//											}
//										}
//									}else if(select[j] == true){
//										btnDynamic[j].setText("<html><center>" + desc[j] + "<br>"  + 
//												"(Vacant)" + "</center></html>");					
//										btnDynamic[j].setBackground(Color.CYAN);			
//										btnDynamic[j].setBorder(new LineBorder(Color.BLACK));			
//									}
//								
//									
//							}
//						}
//						sleep(1000);
//						pnlDecorate.repaint();
//						pnlDecorate.revalidate();
//						pnlDecorate.updateUI();
//						pnlSetDecor.repaint();
//						pnlSetDecor.revalidate();
//						pnlSetDecor.updateUI();
//					}
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//		};
//		clock.start();
//	}
//	
//	//Viewing the image and buttons on the panel
//	public static void ViewDecorate(){
//		i = 0;
//		pnlSetDecor.removeAll();
//		
//		scrollPane = new JScrollPane();
//		scrollPane.setBounds(0, 0, 1000, 700);
//		pnlSetDecor.add(scrollPane);
//		pnlDecorate = new BackgroundImageApp(file);
//		scrollPane.setViewportView(pnlDecorate);
//		
//		//Set Icon
//		pnlDecorate.removeAll();
//		pnlDecorate.updateUI();
//		pnlDecorate.revalidate();
//		pnlDecorate.repaint();
//		pnlDecorate.setLayout(null);
//		pnlDecorate.setEnabled(false);
//		pnlSetDecor.setPreferredSize(new Dimension(1000,700));
//		Transaction.p.DefaultPanelColor();
//		
//		Connect c;
//		try {
//			c = new Connect();
//			c.pst = c.con.prepareCall("{call count_tables()}");
//			c.pst.execute();	
//			c.rs = c.pst.getResultSet();
//			int count = 0;
//			while(c.rs.next()){
//				count = c.rs.getInt("COUNT");				
//			}
//		    description = new String[count];
//		    desc = new String[count];
//		    id = new String[count];
//			btnDynamic = new JToggleButton[count];
//			deskdesc = new String[count+1];
//			deskid = new String[count+1];
//			deskdate = new String[count+1];
//			desktime = new String[count+1];
//			countString = new String[count];
//			m_Date = new Date[count];
//			date = new Date[count];
//			sdfIn = new SimpleDateFormat[count];
//			sdfOut = new SimpleDateFormat[count];
//			desk = new String[count+1];
//			deskstatus = new String[count+1];
//			lblTables = new JLabel[count];
//			select = new boolean[count];
//			selecttable = new boolean[count];
//			
//			c.rs.close();
//			c.pst.close();
//			c.con.close();
//			
//			c = new Connect();
//			c.pst = c.con.prepareCall("{call select_desk_buttons(?)}");
//			c.pst.setString(1, "ACTIVE");
//			c.pst.execute();
//			c.rs = c.pst.getResultSet();
//			while(c.rs.next()){
//				id[i] = c.rs.getString("desk_id");
//				desc[i] = c.rs.getString("desk_desc");
//				width = c.rs.getInt("desk_width");
//				height = c.rs.getInt("desk_height");
//				x = c.rs.getInt("desk_location_x");
//				y= c.rs.getInt("desk_location_y");
//				btnDynamic[i] = new JToggleButton(String.valueOf(desc));
//				btnDynamic[i].setBounds(x, y, width, height);
//				btnDynamic[i].setName(desc[i]);
//				btnDynamic[i].setFont(new Font("Monospaced", Font.PLAIN, 12));
//				btnDynamic[i].setActionCommand(desc[i]);
//				btnDynamic[i].setHorizontalAlignment(SwingConstants.CENTER );
//				btnDynamic[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//				pnlDecorate.add(btnDynamic[i]);
//            	description[i] = desc[i];
//				select[i] = true;
//				selecttable[i] = false;
//				btnDynamic[i].setDoubleBuffered(true);
//				btnDynamic[i].setMargin(new Insets(0, 0, 0, 0));
//				//Button Dynamic Action Listener
//				btnDynamic[i].addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent e) {			        	
//			    		CalendarModel cm = new CalendarModel();
//			    		cm = getCalendarData();
//			    		for(int j = 0; j < i; j++){
//			    			if(enable == true){
//			    				btnDynamic[j].setBackground(Color.CYAN);		    				
//			    			}
//			    			if(e.getActionCommand() == btnDynamic[j].getActionCommand()){
//			    				if(btnDynamic[j].isSelected() == true && enable == false){
//				    				btnDynamic[j].setBackground(Color.RED);
//				    				
//				    		        lblTables[j]  = new JLabel(btnDynamic[j].getActionCommand());
//				    		        lblTables[j].setName(id[j]);
//				    		        pnlTables.add(lblTables[j] , "cell 0 "+j+",grow");
//				    				selecttable[j] = true;
//				    				System.out.println("SELECTED" + selecttable[j] + " " + j);
//				    		        pnlTables.repaint();
//				    		        pnlTables.revalidate();
//				    		        pnlTable.repaint();
//				    		        pnlTable.revalidate();
//
//					    		}else if(btnDynamic[j].isSelected() == false && enable == false){
//				    				btnDynamic[j].setBackground(Color.CYAN);
//				    				selecttable[j] = false;
//				    		        pnlTables.remove(lblTables[j]);
//				    				System.out.println("NOT SELECTED" + selecttable[j] + " " + j);
//				    		        
//				    		        pnlTables.repaint();
//				    		        pnlTables.revalidate();
//				    		        pnlTable.repaint();
//				    		        pnlTable.revalidate();
//				    		        
//					    		}else if(enable == true){
//				    				btnDynamic[j].setBackground(Color.RED);
//									pnlButtons.removeAll();
//									POSTable pt = new POSTable(Transaction.p);
//									pt.ViewTableDetails(j);
//
//								    Navigation();
//							        
//							        btnCancelTableSelection = new JButton("Go Back");
//							        pnlButtons.add(btnCancelTableSelection, "cell 0 4,grow");
//									btnCancelTableSelection.setUI(new MetalButtonUI());
//									btnCancelTableSelection.setBackground(getButtonBackgroundColor());
//									btnCancelTableSelection.setForeground(getButtonForegroundColor());
//									btnCancelTableSelection.addActionListener(new ActionListener() {
//										@Override
//										public void actionPerformed(ActionEvent arg0) {
//											// TODO Auto-generated method stub
//												pnlButtons.removeAll();
//												pnlTable.removeAll();
//
//										        Transaction.p.Tables();
//										        Navigation();
//										        
//												POSTable rt = new POSTable(Transaction.p);
//												rt.ViewWaiting();
//
//												Transaction.p.RadioButtonListener();
//										        ViewDecorate();
//										        clock.suspend();
//										        ViewOccupied();
//										        Transaction.p.Listener();
//										        DefaultButtonColor();
//										        pnlButtons.repaint();
//										        pnlButtons.revalidate();
//										        pnlTable.repaint();
//										        pnlTable.revalidate();
//											
//										}
//									});
//							        pnlButtons.repaint();
//							        pnlButtons.revalidate();
//					    		}
//			    			}			
//		            	}
////			    		
//			        	}
//				  });
//				//End of Button Dynamic Action Listener
//				
//				btnDynamic[i].addMouseListener(new MouseListener() {
//					
//					@Override
//					public void mouseReleased(MouseEvent e) {
//						// TODO Auto-generated method stub
//						
//					}
//					
//					@Override
//					public void mousePressed(MouseEvent e) {
//						// TODO Auto-generated method stub
//						
//					}
//					
//					@Override
//					public void mouseExited(MouseEvent e) {
//						// TODO Auto-generated method stub
////						HoverTable.jf.dispose();
//					}
//					
//					@Override
//					public void mouseEntered(MouseEvent e) {
//						// TODO Auto-generated method stub
////						for(int j = 0; j < i; j++){
////			    			if(e.getSource() == btnDynamic[j]){
////								HoverTable ht = new HoverTable();
////								ht.jf.setLocation(btnDynamic[j].getLocation().x, btnDynamic[j].getLocation().y);
////								ht.jf.setUndecorated(true);
////								ht.jf.setSize(600, 100);
////								ht.jf.setVisible(true);
////			    				HoverTables rt = new HoverTables(Transaction.p);
////								rt.ViewTableDetails(j);
////			    			}
////		            	}
//					}
//					
//					@Override
//					public void mouseClicked(MouseEvent e) {
//						// TODO Auto-generated method stub
//						
//					}
//				});
//				
//				
//				
//				btnDynamic[i].setBackground(Color.CYAN);
//				btnDynamic[i].setForeground(Color.BLACK);
//				btnDynamic[i].setUI(new MetalButtonUI());
//				pnlDecorate.revalidate();
//				pnlDecorate.repaint();
//				i++;				
//			}
//			pnlSetDecor.repaint();
//			pnlSetDecor.revalidate();
//			c.rs.close();
//			c.pst.close();
//			c.con.close();
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
//	}
//	//end of Viewing the image and buttons on the panel
//
//	//Image scale to fit on Panel
//	public static class BackgroundImageApp extends JPanel {
//	
//			BufferedImage originalImage;
//			BufferedImage scaledImage;
//	
//			BackgroundImageApp(File file) {
//			    setPreferredSize(new Dimension(320, 200));
//			    try {
//			        originalImage = ImageIO.read(file);
//			    } catch(Exception e){}
//	
//			    addComponentListener(new ResizerListener());
//			}
//	
//			public void resize() {
//			    AffineTransform at = new AffineTransform();
//			    new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
//			    revalidate();
//			    updateUI();
//			    repaint();
//			}
//	
//			@Override
//			public void paintComponent(Graphics g) {
//				super.paintComponent(g);
//	
//				double widthScaleFactor = getWidth() / (double)getWidth();
//				double heightScaleFactor = getHeight() / (double)getHeight();
//				double scaleFactor = (widthScaleFactor > heightScaleFactor)? heightScaleFactor : widthScaleFactor;
//	
//				int width = (int)(getWidth() * scaleFactor);
//				int height = (int)(getHeight() * scaleFactor);
//	
//				g.drawImage(originalImage, 0, 0, width, height, null);
//			}
//	
//	
//			class ResizerListener implements ComponentListener {
//			    @Override
//			    public void componentResized(ComponentEvent e) {
//			        resize();
//			    }
//	
//			    @Override public void componentHidden(ComponentEvent e) {}
//			    @Override public void componentMoved(ComponentEvent e) {}
//			    @Override public void componentShown(ComponentEvent e) {}
//		}
//	}
//	@Override
//	public void mouseClicked(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//	
//	@Override
//	public void mouseEntered(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		arg0.getComponent().setBackground(getButtonHoverBackgroundColor());
//		arg0.getComponent().setForeground(getButtonHoverForegroundColor());
//	}
//
//
//	@Override
//	public void mouseExited(MouseEvent arg0) {
//		arg0.getComponent().setBackground(getButtonBackgroundColor());
//		arg0.getComponent().setForeground(getButtonForegroundColor());
//	}
//
//	@Override
//	public void mousePressed(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseReleased(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void focusGained(FocusEvent e) {
//		// TODO Auto-generated method stub
//		e.getComponent().setBackground(getButtonHoverBackgroundColor());
//		e.getComponent().setForeground(getButtonHoverForegroundColor());
//		
//	}
//
//	@Override
//	public void focusLost(FocusEvent e) {
//		// TODO Auto-generated method stub
//		e.getComponent().setBackground(getButtonBackgroundColor());
//		e.getComponent().setForeground(getButtonForegroundColor());
//		
//	}
//	
//	public ModelDesks getFieldData(int j){
//		ModelDesks md = new ModelDesks();
//		md.setId(id[j]);
//		return md;
//	}
//
//
//	@Override
//	public void valueChanged(ListSelectionEvent event) {
//		// TODO Auto-generated method stub
//		if( event.getSource() == tblTables.getSelectionModel() && event.getFirstIndex() >= 0 ){
//			TableModel model = (TableModel)tblTables.getModel();
//			int row = tblTables.getSelectedRow();
//			if(row!=-1){
//				pnlButtons.removeAll();
//				
//				txtNavigation = new JTextField();
//		        txtNavigation.setEnabled(false);
//		        txtNavigation.setText("NAVIGATION");
//		        txtNavigation.setHorizontalAlignment(SwingConstants.CENTER);
//		        txtNavigation.setFont(new Font("Monospaced", Font.PLAIN, 15));
//		        txtNavigation.setEditable(false);
//		        txtNavigation.setColumns(10);
//		        pnlButtons.add(txtNavigation, "cell 0 0,growx");
//		        
//				if(rdbtnWaitingList.isSelected()){
//					wid = tblTables.getValueAt(row, 0).toString();
//					try {
//						Connect con = new Connect();
//						con.pst = con.con.prepareCall("{call select_info_waiting(?)}");
//						con.pst.setString(1, wid);
//						con.pst.execute();
//						con.rs = con.pst.getResultSet();
//						while(con.rs.next()){
//							wname = con.rs.getString("waiting_name");
//							wno = con.rs.getString("waiting_no");
//						}
//						con.rs.close();
//						con.pst.close();
//						con.con.close();
//					} catch (SQLException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//
//			        btnSelectTable = new JButton("Edit table");
//			        btnSelectTable.setFont(new Font("Monospaced", Font.PLAIN, 15));
//			        pnlButtons.add(btnSelectTable, "cell 0 1,grow");
//			        
//			        btnEditWaiting = new JButton("Edit Waiting");
//			        btnEditWaiting.setFont(new Font("Monospaced", Font.PLAIN, 15));
//			        pnlButtons.add(btnEditWaiting, "cell 0 2,grow");
//			        
//			        btnCancelWaiting = new JButton("Cancel Waiting");
//			        btnCancelWaiting.setFont(new Font("Monospaced", Font.PLAIN, 15));
//			        pnlButtons.add(btnCancelWaiting, "cell 0 3,grow");
//
//			        btnCancelSelection = new JButton("Go Back");
//			        btnCancelSelection.setFont(new Font("Monospaced", Font.PLAIN, 15));
//			        pnlButtons.add(btnCancelSelection, "cell 0 4,grow");
//			        
//			    	btnEditWaiting.addMouseListener(this);
//			    	btnEditWaiting.addFocusListener(this);
//			    	btnEditWaiting.addActionListener(this);
//
//					btnEditWaiting.setUI(new MetalButtonUI());
//					btnEditWaiting.setBackground(getButtonBackgroundColor());
//					btnEditWaiting.setForeground(getButtonForegroundColor());
//
//			    	btnCancelWaiting.addMouseListener(this);
//			    	btnCancelWaiting.addFocusListener(this);
//			    	btnCancelWaiting.addActionListener(this);
//
//			    	btnCancelWaiting.setUI(new MetalButtonUI());
//			    	btnCancelWaiting.setBackground(getButtonBackgroundColor());
//			    	btnCancelWaiting.setForeground(getButtonForegroundColor());
//					
//			    	btnSelectTable.addMouseListener(this);
//			    	btnSelectTable.addFocusListener(this);
//			    	btnSelectTable.addActionListener(this);
//
//					btnSelectTable.setUI(new MetalButtonUI());
//					btnSelectTable.setBackground(getButtonBackgroundColor());
//					btnSelectTable.setForeground(getButtonForegroundColor());
//					
//				}else if(rdbtnDineIn.isSelected()){
//					did = tblTables.getValueAt(row, 0).toString();
//			        btnSelectTable = new JButton("Edit table");
//			        btnSelectTable.setFont(new Font("Monospaced", Font.PLAIN, 15));
//			        pnlButtons.add(btnSelectTable, "cell 0 1,grow");
//			        
//					dtid = tblTables.getValueAt(row, 0).toString();
//					btnBillOut = new JButton("View bill");
//			        btnBillOut.setFont(new Font("Monospaced", Font.PLAIN, 15));
//			        pnlButtons.add(btnBillOut, "cell 0 2,grow");
//
//			        btnCancelSelection = new JButton("Go Back");
//			        btnCancelSelection.setFont(new Font("Monospaced", Font.PLAIN, 15));
//			        pnlButtons.add(btnCancelSelection, "cell 0 3,grow");
//
//			    	btnSelectTable.addMouseListener(this);
//			    	btnSelectTable.addFocusListener(this);
//			    	btnSelectTable.addActionListener(this);
//
//			    	
//			    	btnBillOut.addMouseListener(this);
//			    	btnBillOut.addFocusListener(this);
//			    	btnBillOut.addActionListener(this);
//			    	
//					btnBillOut.setUI(new MetalButtonUI());
//					btnBillOut.setBackground(getButtonBackgroundColor());
//					btnBillOut.setForeground(getButtonForegroundColor());
//					
//					btnSelectTable.setUI(new MetalButtonUI());
//					btnSelectTable.setBackground(getButtonBackgroundColor());
//					btnSelectTable.setForeground(getButtonForegroundColor());
//				}else if( rdbtnTakeout.isSelected()){
//					dtid = tblTables.getValueAt(row, 0).toString();
//					btnBillOut = new JButton("View bill");
//			        btnBillOut.setFont(new Font("Monospaced", Font.PLAIN, 15));
//			        pnlButtons.add(btnBillOut, "cell 0 1,grow");
//			    	
//			    	btnBillOut.addMouseListener(this);
//			    	btnBillOut.addFocusListener(this);
//			    	btnBillOut.addActionListener(this);
//			    	
//					btnBillOut.setUI(new MetalButtonUI());
//					btnBillOut.setBackground(getButtonBackgroundColor());
//					btnBillOut.setForeground(getButtonForegroundColor());
//
//			        btnCancelSelection = new JButton("Go Back");
//			        btnCancelSelection.setFont(new Font("Monospaced", Font.PLAIN, 15));
//			        pnlButtons.add(btnCancelSelection, "cell 0 2,grow");
//				}
//		        
//		        ListenerWhenClickTable();
//		        pnlButtons.repaint();
//		        pnlButtons.revalidate();		        
//		        
//			}
//		}
//	}
//
//
//	@Override
//	public void actionPerformed(ActionEvent arg0) {
//		// TODO Auto-generated method stub
//		if(arg0.getSource() == btnNewWaiting){
//			btnNewWaiting.requestFocus();
//			pnlSetDecor.removeAll();
//			Waiting w = new Waiting();
//			WaitingTable wt = new WaitingTable(w);
//			ModelWaiting mw = new ModelWaiting();
//			new AddController(w, mw, wt);
//			wt.CounterWaiting();
//			wt.CounterWaitingNo();
//			w.setBounds(0, 0, 1000, 700);
//			w.btnAdd.setText("ADD");
//			pnlSetDecor.add(w);
//
//			pnlSetDecor.repaint();
//			pnlSetDecor.revalidate();			
//		}else if(arg0.getSource() == btnOrder){
//			WindowNormalOrder ao = new WindowNormalOrder(type, "", "Dine-in");
//			ao.jf.setVisible(true);
//			ao.pnlSales.repaint();
//			ao.pnlSales.revalidate();
//			
//		}else if(arg0.getSource() == btnSelectTable){
//			if(btnSelectTable.getText().equalsIgnoreCase("Edit Table")){
//
//				enable = false;			
//				pnlTable.removeAll();
//				
//				scrollPane_2 = new JScrollPane();
//				if(rdbtnWaitingList.isSelected()){
//					tfWaitingId = new JTextField(wid);
//				}else if(rdbtnDineIn.isSelected()){
//					tfWaitingId = new JTextField(did);
//				}
//				pnlTable.add(tfWaitingId, "cell 0 1,grow");
//				pnlTable.add(scrollPane_2, "cell 0 2,grow");
//				
//				pnlTables = new JPanel();
//				scrollPane_2.setViewportView(pnlTables);
//		        pnlTables .setLayout(new MigLayout("", "[grow][grow,fill]", "[]"));
//		        pnlTables.repaint();
//		        pnlTables.revalidate();
//		        
//		        pnlTable.repaint();
//		        pnlTable.revalidate();
//		        btnSelectTable.setText("Add Table");
//		        
//			}else if(btnSelectTable.getText().equalsIgnoreCase("Add Table")){
//				if(rdbtnWaitingList.isSelected()){					
//					for(int j = 0; j < i; j++){
//						if(selecttable[j] == true){
//							try {
//								Connect con = new Connect();
//								con.pst = con.con.prepareCall("{call insert_waiting_desks(?,?,?)}");
//								con.pst.setString(1, tfWaitingId.getText());
//								con.pst.setString(2, lblTables[j].getName());
//								con.pst.setString(5, "Occupied");
//								con.pst.execute();
//								con.pst.close();
//								con.con.close();
//							} catch (SQLException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//						}
//					}
//			        btnSelectTable.setText("Edit Table");
//			        btnCancelSelection.doClick();
//					for(int j = 0; j < i; j++){
//						btnDynamic[j].setSelected(false);
//					}					
//				}else if(rdbtnDineIn.isSelected()){
//					for(int j = 0; j < i; j++){ 
//						if(selecttable[j] == true){
//							try {
//								Connect con = new Connect();
//								con.pst = con.con.prepareCall("{call insert_guest_dinein_desks(?,?,?)}");
//								con.pst.setString(1, tfWaitingId.getText());
//								con.pst.setString(2, lblTables[j].getName());
//								con.pst.setString(3, "Occupied");
//								con.pst.execute();
//								con.pst.close();
//								con.con.close();
//							} catch (SQLException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//						}
//					}
//			        btnSelectTable.setText("Edit Table");
//			        btnCancelSelection.doClick();
//					for(int j = 0; j < i; j++){
//						btnDynamic[j].setSelected(false);
//					}					
//				}
//			}
//	        
//		}else if(arg0.getSource() == btnCancelWaiting){
//			int reply = JOptionPane.showConfirmDialog(null, "Do you really want to cancel reservation?", "Cancel", JOptionPane.YES_NO_OPTION);
//	        if (reply == JOptionPane.YES_OPTION) {
//				try {
//					Connect con = new Connect();
//					con.pst = con.con.prepareCall("{call cancel_waiting(?)}");
//					con.pst.setString(1, wid);
//					con.pst.execute();
//					JOptionPane.showMessageDialog(null, "Waiting Cancelled");
//					POSTable rt = new POSTable(Transaction.p);
//					rt.ViewWaiting();
//
//					RadioButtonListener();
//			        ViewDecorate();
//			        clock.suspend();
//			        ViewOccupied();
//			        Transaction.p.Listener();
//			        DefaultButtonColor();
//					con.pst.close();
//					con.con.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//	        }
//	        else {
//	        	
//	        }
//			
//		}else if(arg0.getSource() == btnBillOut){
//			
//			if(rdbtnDineIn.isSelected()){
//				WindowNormalBillingDinein ab= new WindowNormalBillingDinein(dtid, type, "Dine-in");
//				ab.jf.setVisible(true);
//				ab.pnlBills.repaint();
//				ab.pnlBills.revalidate();
//			}else if(rdbtnTakeout.isSelected()){
//				WindowNormalBillingTakeout ab = new WindowNormalBillingTakeout(dtid, type, "Take-out");
//				ab.jf.setVisible(true);
//				ab.pnlBills.repaint();
//				ab.pnlBills.revalidate();
//			}
//		}else if(arg0.getSource() == btnEditWaiting){
//		
//			pnlSetDecor.removeAll();
//			pnlButtons.removeAll();
//			Waiting w = new Waiting();
//			ModelWaiting mw = new ModelWaiting();
//			WaitingTable wt = new WaitingTable(w);
//			new AddController(w, mw, wt);
//			w.tfWaitingId.setText(wid);
//			w.tfName.setText(wname);
//			w.btnAdd.setText("UPDATE");
//			w.setBounds(0, 0, 1000, 700);
//			pnlSetDecor.add(w);
//
//			Navigation();
//	        Listener();
//	        DefaultButtonColor();
//	        
//			pnlSetDecor.repaint();
//			pnlSetDecor.revalidate();
//		}else if(arg0.getSource() == btnCancelSelection){
//			type = "Normal";
//			enable = true;
//			tblTables.clearSelection();
//			Navigation();
//			Tables();			
//	        ViewDecorate();
//	        clock.suspend();
//	        ViewOccupied();
//	        pnlButtons.repaint();
//	        pnlButtons.revalidate();
//	        pnlTable.repaint();
//	        pnlTable.revalidate();
//		}
//	}
//
//
//	@Override
//	public void itemStateChanged(ItemEvent e) {
//		// TODO Auto-generated method stub
//		if(e.getSource() == rdbtnWaitingList){
//    		Transaction.pt.ViewWaiting();
//		}else if(e.getSource() == rdbtnDineIn){
//    		Transaction.pt.ViewDinein();
//		}else if(e.getSource() == rdbtnTakeout){
//    		Transaction.pt.ViewTakeout();
//		}
//		Navigation();
//	}
//	public void Tables(){
//		pnlTable.removeAll();
//        txtTableList = new JTextField();
//        txtTableList.setEnabled(false);
//        txtTableList.setHorizontalAlignment(SwingConstants.CENTER);
//        txtTableList.setText("TABLES");
//        txtTableList.setFont(new Font("Monospaced", Font.PLAIN, 15));
//        txtTableList.setEditable(false);
//        txtTableList.setColumns(10);
//        pnlTable.add(txtTableList, "cell 0 0,growx");
//        
//        rdbtnWaitingList = new JRadioButton("WAITING");
//        rdbtnWaitingList.setSelected(true);
//        bgTables.add(rdbtnWaitingList);
//        rdbtnWaitingList.setFont(new Font("Monospaced", Font.PLAIN, 15));
//        pnlTable.add(rdbtnWaitingList, "flowx,cell 0 1,alignx left");
//        
//        rdbtnDineIn = new JRadioButton("DINE-IN");
//        bgTables.add(rdbtnDineIn);
//        rdbtnDineIn.setFont(new Font("Monospaced", Font.PLAIN, 15));
//        pnlTable.add(rdbtnDineIn, "cell 0 1,alignx left,growy");
//        
//        spTables = new JScrollPane();
//        pnlTable.add(spTables, "flowx,cell 0 2 1 2,grow");
//        
//        tblTables = new JTable();
//        tblTables.setRowHeight(30);
//        tblTables.setFont(new Font("Monospaced", Font.PLAIN, 15));
//        spTables.setViewportView(tblTables);
//        
//        rdbtnTakeout = new JRadioButton("TAKE-OUT");
//        bgTables.add(rdbtnTakeout);
//        rdbtnTakeout.setFont(new Font("Monospaced", Font.PLAIN, 15));
//        pnlTable.add(rdbtnTakeout, "cell 0 1,alignx left,growy");
//		rdbtnWaitingList.setSelected(true);
//		POSTable pt = new POSTable(Transaction.p);
//		pt.ViewWaiting();
//		RadioButtonListener();
//	}
//	public static void Navigation(){
//		pnlButtons.removeAll();
//		
//		txtNavigation = new JTextField();
//        txtNavigation.setEnabled(false);
//        txtNavigation.setText("NAVIGATION");
//        txtNavigation.setHorizontalAlignment(SwingConstants.CENTER);
//        txtNavigation.setFont(new Font("Monospaced", Font.PLAIN, 15));
//        txtNavigation.setEditable(false);
//        txtNavigation.setColumns(10);
//        pnlButtons.add(txtNavigation, "cell 0 0,growx");
//        
//        btnNewWaiting = new JButton("New Waiting");
//        btnNewWaiting.setFont(new Font("Monospaced", Font.PLAIN, 15));
//        pnlButtons.add(btnNewWaiting, "cell 0 1,grow");
//        
//        btnOrder = new JButton("Order");
//        btnOrder.setFont(new Font("Monospaced", Font.PLAIN, 15));
//        pnlButtons.add(btnOrder, "cell 0 2,grow");
//
////        btnExtendMins = new JButton("Extend 30 Mins");
////        btnExtendMins.setForeground(new Color(215, 203, 0));
////        btnExtendMins.setFont(new Font("Monospaced", Font.PLAIN, 15));
////        btnExtendMins.setBackground(new Color(27, 12, 95));
////        pnlButtons.add(btnExtendMins, "cell 0 3,grow");
//        
//
//		Transaction.p.RadioButtonListener();
//        Transaction.p.Listener();
//        DefaultButtonColor();
//	}
//}
//
