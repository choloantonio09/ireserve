package Capstone.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerDateModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.metal.MetalButtonUI;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import Capstone.Database.Connect;
import Capstone.Table.ReservationTable;
import Capstone.View.EventFoodForm.BackgroundImageApp;
import Capstone.View.EventFoodForm.BackgroundImageApp.ResizerListener;
import net.miginfocom.swing.MigLayout;
import net.proteanit.sql.DbUtils;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import com.toedter.components.JSpinField;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.beans.PropertyChangeEvent;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatter;
import javax.swing.SpinnerModel;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import java.awt.Font;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import java.awt.FlowLayout;

public class Query extends JPanel implements ListSelectionListener{
	
	static Connect c;
	DefaultTableCellRenderer rightRenderer;
	
	JSpinner.DateEditor de_tableTimeSpinner;
	JButton btnGoBack;
	JTabbedPane tpReport;
	private int tableIdOccupied = 0;
	public BackgroundImageApp pnlImage;
	private Object normalPackageId = 0;
	private Object eventPackageId = 0;
	private Object normalFoodId = 0;
	private Object eventFoodId = 0;
	private Object reserve_id= 0;
	private Object floorId = 0;
	private Object tableReserveId= 0;
	private Object waiting_id= 0;
	private Object dinein_id = 0;
	private Object takeout_id = 0;
	private JTabbedPane plQuery;
	private JPanel plCustomers;
	private JPanel plTables;
	private JPanel plMenu;
	private JPanel plReservations;
	private JPanel plOverview;
	private JTabbedPane tpMenu;
	private JDateChooser tableDateChooser;
	private JSpinner tableTimeSpinner;
	private JLabel lblTableStatus;
	private JComboBox tableStatus;
	private JTextField tableSearch;
	private JButton btnTableSearch;
	private JTextField foodSearch;
	private JTable availableTables;
	private JScrollPane availableScrollPane;
	private JTable normalFoodTable;
	private JTable custRvTable;
	private JTable custDITable;
	private JTable custTOTable;
	private JDateChooser custFromDateChooser;
	private JScrollPane custRvScrollPane;
	private JScrollPane custDIScrollPane;
	private JScrollPane custTOScrollPane;
	private JLabel lblCustomerInformation;
	private JLabel lblDineIn;
	private JTextField custSearch;
	private JButton btnCustSearch;
	private JSpinner custTimeSpinner;
	private JDateChooser custToDateChooser;
	private SimpleDateFormat sdf;
	private JButton showRVDetails;
	private JButton showDIDetails;
	private JButton showTODetails;
	private JTable roomTable;
	private JScrollPane roomScrollPane;
	private JLabel lblAvailableVacant;
	private JTable unavailableTable;
	private JComboBox roomSelect;
	private JButton btnShowMoreDetails;
	private File file;
	private String eventPath;
	private String destination = "";
	private String destination2 = "";
	private String destination3 = "";
	private String destination4 = "";
	private String destination5 = "";
	private JScrollPane normalFoodScrollpane1;
	private JTable eventFoodTable;
	private JTable eventFoodSizesTable;
	private JTable normalFoodSizesTable;
	private JButton btnFoodSearch;
	private JComboBox foodCategory;
	private JLabel lblFoodCategory;
	private JComboBox foodStatus;
	private JLabel lblFoodStatus;
	private JLabel lblNormalFood;
	private JPanel normalFoodImagePanel;
	private JLabel lblEventFoods;
	private JPanel eventFoodImagePanel;
	private JTable normalPackageTable;
	private JTable eventPackageTable;
	private JTable eventPackageItemsTable;
	private JTable normalPackageItemsTable;
	private JTextField packageSearch;
	private JPanel eventPackageImagePanel;
	private JPanel normalPackageImagePanel;
	private JButton btnPackageSearch;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_3;
	private JScrollPane normalFoodScrollpane2;
	private JScrollPane eventFoodScrollpane2;
	private JPanel foodAndPackagesTab;
	private JPanel customersTab;
	private JLabel lblNewLabel;
	private JLabel overviewFromLabel;
	private JDateChooser fromDateOverview;
	private JDateChooser toDateOverview;
	
	String overviewFromDate;
	String overviewToDate;
	private JPanel panel;
	private JLabel reservedNo;
	private JLabel dineinNo;
	private JLabel takeoutNo;
	private JLabel totalNo;
	private JPanel salesTab;
	private JLabel incomeReserveLbl;
	private JLabel incomeNormalReserve;
	private JLabel incomeDineinLbl;
	private JLabel incomeDinein;
	private JLabel incomeTakeoutLbl;
	private JLabel incomeTakeout;
	private JLabel incomeTotalLbl;
	private JLabel incomeTotal;
	private JPanel salesOverviewPanel;
	private JPanel salesChartMotherPanel;
	private JLabel lblIncomeFromEvent;
	private JLabel incomeEventReserve;
	private JPanel eventFoodOverviewPanel;
	private JPanel eventPackageOverviewPanel;
	private JPanel normalPackageOverviewPanel;
	private JPanel normalFoodOverviewPanel;
	private JLabel lblTopBestNormalFood;
	private JLabel lblTopBestEventFood;
	private JLabel lblTop;
	private JLabel lblTop_1;
	private JLabel lblTop_2;
	private JLabel lblTop_3;
	private JLabel lblTop_4;
	private JLabel lblTopBestNormalPackage;
	private JLabel lblTopBestEventPackage;
	private JLabel label_4;
	private JLabel label_7;
	private JLabel label_10;
	private JLabel label_13;
	private JLabel label_31;
	private JLabel label_16;
	private JLabel label_17;
	private JLabel label_20;
	private JLabel label_22;
	private JLabel label_24;
	private JLabel label_33;
	private JLabel label_34;
	private JLabel label_36;
	private JLabel label_38;
	private JLabel label_41;
	JLabel[] bef;
	JLabel[] bestNormalfood;
	JLabel[] bestEventFood;
	JLabel[] bep;
	JLabel[] bestPackageEvent;
	JLabel[] bnp;
	JLabel[] bestPackageNormal;
	JLabel[] bnf;
	private JTable floorTable;
	

	private JComboBox floorSelect;
	private JScrollPane floorTableScrollPane;
	private JLabel lblFloors;
	private JLabel lblRooms;



	
	private PieDataset createDatasetCustomer() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		
		try {
			c = new Connect();
			String query = "SELECT (SELECT COUNT(*) FROM reserve WHERE reserve_date BETWEEN ? AND ?) AS 'Reserved', "
					+ "(SELECT COUNT(*) FROM guest_dinein WHERE guest_dinein_date BETWEEN ? AND ?) AS 'Dine-in', "
					+ "(SELECT COUNT(*) FROM guest_takeout WHERE guest_takeout_date BETWEEN ? AND ?) AS 'Take-out', "
					+ "(SELECT COUNT(*) FROM waiting WHERE waiting_date BETWEEN ? AND ?) AS 'Waiting';";
			c.pst = c.con.prepareStatement(query);
			c.pst.setString(1, overviewFromDate);
			c.pst.setString(2, overviewToDate);
			c.pst.setString(3, overviewFromDate);
			c.pst.setString(4, overviewToDate);
			c.pst.setString(5, overviewFromDate);
			c.pst.setString(6, overviewToDate);
			c.pst.setString(7, overviewFromDate);
			c.pst.setString(8, overviewToDate);
			
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			
			while(c.rs.next())
			{
				
					dataset.setValue("Reserved", new Integer(c.rs.getInt("Reserved")));
				
					dataset.setValue("Dine-In", new Integer(c.rs.getInt("Dine-in")));
				
					dataset.setValue("Take-Out", new Integer(c.rs.getInt("Take-out")));
				
					//dataset.setValue("Waiting", new Integer(c.rs.getInt("Waiting")));
				
				
			}
			
			c.rs.close();
			c.pst.close();
			c.con.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        
        return dataset;        
    }
	
	private JFreeChart createChartCustomer(PieDataset dataset) {
        
        JFreeChart chart = ChartFactory.createPieChart(
            "CUSTOMER STATISTICS",  // chart title
            dataset,             // data
            true,               // include legend
            true,
            false
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setNoDataMessage("No data available");
        plot.setCircular(false);
        plot.setLabelGap(0.02);
        plot.setLegendLabelGenerator(new
        	    StandardPieSectionLabelGenerator("{0} = {1} ({2})"));
    
        return chart;
        
    }
	
	 public JPanel createPanelCustomer() {
	        JFreeChart chart = createChartCustomer(createDatasetCustomer());
	        ChartPanel chartPanel = new ChartPanel(chart);
	        return chartPanel;
	    }

	 
	 private PieDataset createDatasetSales() {
			DefaultPieDataset datasetSales = new DefaultPieDataset();
			
			try {
				c = new Connect();
				String query = "SELECT "
						+ "(SELECT SUM(Bilang) AS 'Normal Reservations' FROM "
						+ "(SELECT order_total - order_balance AS 'Bilang' FROM normal_orders "
						+ "JOIN reserve ON reserve_id = id WHERE id LIKE 'R%' AND reserve_type = 'Regular' AND order_date BETWEEN ? AND ?) a) AS 'Normal Reservations' , "
						+ "(SELECT SUM(Bilang) AS 'Event Reservations' FROM "
						+ "(SELECT order_total - order_balance AS 'Bilang' FROM event_orders "
						+ "JOIN reserve ON reserve_id = id WHERE id LIKE 'R%' AND reserve_type = 'Event' AND order_date BETWEEN ? AND ?) b) AS 'Event Reservations' , "
						+ "(SELECT SUM(Bilang) AS 'Dine-in Sales' FROM "
						+ "(SELECT order_total - order_balance AS 'Bilang' FROM normal_orders WHERE id LIKE 'DI%' AND order_date BETWEEN ? AND ?) d) AS 'Dine-in Sales', "
						+ "(SELECT SUM(Bilang) AS 'Take-out Sales' FROM "
						+ "(SELECT order_total - order_balance AS 'Bilang' FROM normal_orders WHERE id LIKE 'TO%' AND order_date BETWEEN ? AND ?) c) AS 'Take-out Sales';";
				c.pst = c.con.prepareStatement(query);
				c.pst.setString(1, overviewFromDate);
				c.pst.setString(2, overviewToDate);
				c.pst.setString(3, overviewFromDate);
				c.pst.setString(4, overviewToDate);
				c.pst.setString(5, overviewFromDate);
				c.pst.setString(6, overviewToDate);
				c.pst.setString(7, overviewFromDate);
				c.pst.setString(8, overviewToDate);
				
				c.pst.execute();
				c.rs = c.pst.getResultSet();
				
				while(c.rs.next())
				{
					
						datasetSales.setValue("Normal Reservations", new Integer(c.rs.getInt("Normal Reservations")));
					
						datasetSales.setValue("Event Reservations", new Integer(c.rs.getInt("Event Reservations")));
					
						datasetSales.setValue("Dine-in Sales", new Integer(c.rs.getInt("Dine-in Sales")));
					
						datasetSales.setValue("Take-out Sales", new Integer(c.rs.getInt("Take-out Sales")));
					
					
				}
				
				c.rs.close();
				c.pst.close();
				c.con.close();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	        
	        return datasetSales;        
	    }
		
		private JFreeChart createChartSales(PieDataset datasetSales) {
	        
	        JFreeChart chartSales = ChartFactory.createPieChart(
	            "SALES AMOUNT FROM CUSTOMERS",  // chart title
	            datasetSales,             // data
	            true,               // include legend
	            true,
	            false
	        );

	        PiePlot plotSales = (PiePlot) chartSales.getPlot();
	        plotSales.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
	        plotSales.setNoDataMessage("No data available");
	        plotSales.setCircular(false);
	        plotSales.setLabelGap(0.02);
	        plotSales.setLegendLabelGenerator(new
	        	    StandardPieSectionLabelGenerator("{0} = Php {1} ({2})"));
	    
	        return chartSales;
	        
	    }
		
		 public JPanel createPanelSales() {
		        JFreeChart chartSales = createChartSales(createDatasetSales());
		        ChartPanel chartSalesPanel = new ChartPanel(chartSales);
		        return chartSalesPanel;
		    }
	 
	 
	/**
	 * Create the panel.
	 */
	public Query() {
		
		
		
		
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		setLayout(new MigLayout("", "[][grow]", "[grow]"));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		
		btnGoBack = new JButton("");
		btnGoBack.setUI(new MetalButtonUI());
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.displayPanel.removeAll();
				Selection s = new Selection();
				Main.displayPanel.add(s, "cell 0 0 3 1,grow");
				Main.displayPanel.repaint();
				Main.displayPanel.revalidate();
				Main.frame.setSize(850,650);
				Main.frame.setLocationRelativeTo(null);
			}
		});
		btnGoBack.setIcon(new ImageIcon(Reports.class.getResource("/Images/Icon/home.PNG")));
		btnGoBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGoBack.setContentAreaFilled(false);
		btnGoBack.setOpaque(false);
		add(btnGoBack, "cell 0 0,growx,aligny top");
		
		plQuery = new JTabbedPane(JTabbedPane.TOP);
		plQuery.setUI(new AquaTabbedPaneUI());
		add(plQuery, "cell 1 0,grow");
		ChangeListener changeListener = new ChangeListener() {
		      public void stateChanged(ChangeEvent changeEvent) {
		        JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
		        int index = sourceTabbedPane.getSelectedIndex();
		        if(index==0)
		        {
		        	
		        }
		        else if(index==1)
	            {
		        	ViewAvailableTable();
		        	ViewUnavailableTable();
		        	ViewFloorTable();

		        	tableIdOccupied = 0;
		        	tableReserveId = 0;
		        	reserve_id = 0;
					waiting_id = 0;
					dinein_id = 0;
					takeout_id = 0;
		        	btnShowMoreDetails.setEnabled(false);
	            }
	            else if(index==2)
	            {
	            	ViewCustTableReserve();
					ViewCustTableWaiting();
					ViewCustTableDinein();
					ViewCustTableTakeout();
					
					reserve_id = 0;
					waiting_id = 0;
					dinein_id = 0;
					takeout_id = 0;
					showRVDetails.setEnabled(false);
					//showWLDetails.setEnabled(false);
					showDIDetails.setEnabled(false);
					showTODetails.setEnabled(false);
	            }
	            else if(index==3)
	            {
	            	normalFoodId = "0";
	            	eventFoodId = "0";
	            	eventPackageId = "0";
	            	normalPackageId = "0";
	            	ViewNormalFood();
	            	ViewEventFood();
	            	ViewNormalSizes();
	            	ViewEventSizes();
					normalFoodImagePanel.removeAll();
					eventFoodImagePanel.removeAll();
					ViewNormalPackage();
					ViewEventPackage();
					ViewNormalPackageItems();
					ViewEventPackageItems();
					
					normalPackageImagePanel.removeAll();
					eventPackageImagePanel.removeAll();

	            }
		        
		      }
		    };
		
		plOverview = new JPanel();
		plQuery.addTab("Overview", null, plOverview, null);
		plOverview.setLayout(new MigLayout("", "[119px][182.00px][52.00px][8px][125.00px][55.00px][18px][8px][508px,grow]", "[33px][498px,grow]"));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setUI(new AquaTabbedPaneUI());
		plOverview.add(tabbedPane, "cell 0 1 9 1,grow");
		
		customersTab = new JPanel();
		tabbedPane.addTab("CUSTOMERS", null, customersTab, null);
		customersTab.setLayout(new MigLayout("", "[690px][360px,grow]", "[438px,grow]"));
		
		panel = new JPanel();
		customersTab.add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[680px,grow]", "[420px,grow]"));
		panel.add(createPanelCustomer(), "cell 0 0,grow");
		
		JPanel custOverviewPanel = new JPanel();
		custOverviewPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		customersTab.add(custOverviewPanel, "cell 1 0,grow");
		custOverviewPanel.setLayout(new MigLayout("", "[239px][10px][91px,grow]", "[36px,grow][36px,grow][36px,grow][36px,grow][36px,grow][36px,grow][36px,grow]"));
		
		JLabel lblNoOfReserved = new JLabel("NO. OF RESERVED CUSTOMERS:");
		lblNoOfReserved.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoOfReserved.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNoOfReserved.setForeground(Color.BLACK);
		custOverviewPanel.add(lblNoOfReserved, "cell 0 0 3 1,grow");
		
		reservedNo = new JLabel("n/a");
		reservedNo.setForeground(Color.BLUE);
		reservedNo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		reservedNo.setHorizontalAlignment(SwingConstants.CENTER);
		custOverviewPanel.add(reservedNo, "cell 0 1 3 1,grow");
		
		JLabel lblNoOfDinein = new JLabel("NO. OF DINE-IN CUSTOMERS:");
		lblNoOfDinein.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoOfDinein.setForeground(Color.BLACK);
		lblNoOfDinein.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		custOverviewPanel.add(lblNoOfDinein, "cell 0 2 3 1,grow");
		
		dineinNo = new JLabel("n/a");
		dineinNo.setHorizontalAlignment(SwingConstants.CENTER);
		dineinNo.setForeground(Color.BLUE);
		dineinNo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		custOverviewPanel.add(dineinNo, "cell 0 3 3 1,grow");
		
		JLabel lblNoOfTakeout = new JLabel("NO. OF TAKE-OUT CUSTOMERS:");
		lblNoOfTakeout.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoOfTakeout.setForeground(Color.BLACK);
		lblNoOfTakeout.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		custOverviewPanel.add(lblNoOfTakeout, "cell 0 4 3 1,grow");
		
		takeoutNo = new JLabel("n/a");
		takeoutNo.setHorizontalAlignment(SwingConstants.CENTER);
		takeoutNo.setForeground(Color.BLUE);
		takeoutNo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		custOverviewPanel.add(takeoutNo, "cell 0 5 3 1,grow");
		
		JLabel totalNoLbl = new JLabel("TOTAL NO. OF CUSTOMERS:");
		totalNoLbl.setHorizontalAlignment(SwingConstants.CENTER);
		totalNoLbl.setForeground(Color.BLACK);
		totalNoLbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		custOverviewPanel.add(totalNoLbl, "cell 0 6,grow");
		
		totalNo = new JLabel("n/a");
		totalNo.setHorizontalAlignment(SwingConstants.CENTER);
		totalNo.setForeground(Color.RED);
		totalNo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		custOverviewPanel.add(totalNo, "cell 2 6,grow");
		
		salesTab = new JPanel();
		tabbedPane.addTab("SALES", null, salesTab, null);
		salesTab.setLayout(new MigLayout("", "[678px][380px,grow]", "[451px,grow]"));
		
		salesOverviewPanel = new JPanel();
		salesOverviewPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		salesTab.add(salesOverviewPanel, "cell 1 0,grow");
		salesOverviewPanel.setLayout(new MigLayout("", "[255px][10px][109px,grow]", "[47px,grow][47px,grow][47px,grow][48px,grow][47px,grow][47px,grow][47px,grow][41px,grow][21px,grow]"));
		
		incomeReserveLbl = new JLabel("INCOME FROM REGULAR RESERVATIONS:");
		incomeReserveLbl.setHorizontalAlignment(SwingConstants.CENTER);
		incomeReserveLbl.setForeground(Color.BLACK);
		incomeReserveLbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		salesOverviewPanel.add(incomeReserveLbl, "cell 0 0 3 1,grow");
		
		incomeNormalReserve = new JLabel("n/a");
		incomeNormalReserve.setHorizontalAlignment(SwingConstants.CENTER);
		incomeNormalReserve.setForeground(Color.BLUE);
		incomeNormalReserve.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		salesOverviewPanel.add(incomeNormalReserve, "cell 0 1 3 1,grow");
		
		incomeDineinLbl = new JLabel("INCOME FROM DINE-IN CUSTOMERS:");
		incomeDineinLbl.setHorizontalAlignment(SwingConstants.CENTER);
		incomeDineinLbl.setForeground(Color.BLACK);
		incomeDineinLbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		salesOverviewPanel.add(incomeDineinLbl, "cell 0 4 3 1,grow");
		
		incomeDinein = new JLabel("n/a");
		incomeDinein.setHorizontalAlignment(SwingConstants.CENTER);
		incomeDinein.setForeground(Color.BLUE);
		incomeDinein.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		salesOverviewPanel.add(incomeDinein, "cell 0 5 3 1,grow");
		
		incomeTakeoutLbl = new JLabel("INCOME FROM TAKE-OUT CUSTOMERS:");
		incomeTakeoutLbl.setHorizontalAlignment(SwingConstants.CENTER);
		incomeTakeoutLbl.setForeground(Color.BLACK);
		incomeTakeoutLbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		salesOverviewPanel.add(incomeTakeoutLbl, "cell 0 6 3 1,grow");
		
		incomeTakeout = new JLabel("n/a");
		incomeTakeout.setHorizontalAlignment(SwingConstants.CENTER);
		incomeTakeout.setForeground(Color.BLUE);
		incomeTakeout.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		salesOverviewPanel.add(incomeTakeout, "cell 0 7 3 1,grow");
		
		incomeTotalLbl = new JLabel("TOTAL AMOUNT OF SALES:");
		incomeTotalLbl.setHorizontalAlignment(SwingConstants.CENTER);
		incomeTotalLbl.setForeground(Color.BLACK);
		incomeTotalLbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		salesOverviewPanel.add(incomeTotalLbl, "cell 0 8,grow");
		
		incomeTotal = new JLabel("n/a");
		incomeTotal.setHorizontalAlignment(SwingConstants.CENTER);
		incomeTotal.setForeground(Color.RED);
		incomeTotal.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		salesOverviewPanel.add(incomeTotal, "cell 2 8,grow");
		
		lblIncomeFromEvent = new JLabel("INCOME FROM EVENT RESERVATIONS:");
		lblIncomeFromEvent.setHorizontalAlignment(SwingConstants.CENTER);
		lblIncomeFromEvent.setForeground(Color.BLACK);
		lblIncomeFromEvent.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		salesOverviewPanel.add(lblIncomeFromEvent, "cell 0 2 3 1,grow");
		
		incomeEventReserve = new JLabel("n/a");
		incomeEventReserve.setHorizontalAlignment(SwingConstants.CENTER);
		incomeEventReserve.setForeground(Color.BLUE);
		incomeEventReserve.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		salesOverviewPanel.add(incomeEventReserve, "cell 0 3 3 1,grow");
		
		salesChartMotherPanel = new JPanel();
		salesTab.add(salesChartMotherPanel, "cell 0 0,grow");
		salesChartMotherPanel.setLayout(new MigLayout("", "[680px,grow]", "[420px,grow]"));
		
		salesChartMotherPanel.add(createPanelSales(), "cell 0 0,grow");
		
		
		
		
		foodAndPackagesTab = new JPanel();
		tabbedPane.addTab("FOOD AND PACKAGES", null, foodAndPackagesTab, null);
		foodAndPackagesTab.setLayout(new MigLayout("", "[530px,grow][530px,grow]", "[219px,grow][219px,grow]"));
		
		eventFoodOverviewPanel = new JPanel();
		eventFoodOverviewPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		foodAndPackagesTab.add(eventFoodOverviewPanel, "cell 1 0,grow");
		eventFoodOverviewPanel.setLayout(null);
		
		lblTopBestEventFood = new JLabel("TOP 5 BEST SELLING FOOD (EVENT):");
		lblTopBestEventFood.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblTopBestEventFood.setBounds(10, 11, 510, 26);
		eventFoodOverviewPanel.add(lblTopBestEventFood);
		
		label_16 = new JLabel("TOP 1");
		label_16.setForeground(Color.RED);
		label_16.setHorizontalAlignment(SwingConstants.CENTER);
		label_16.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_16.setBounds(10, 48, 91, 20);
		eventFoodOverviewPanel.add(label_16);
		
		label_17 = new JLabel("TOP 2");
		label_17.setForeground(Color.BLUE);
		label_17.setHorizontalAlignment(SwingConstants.CENTER);
		label_17.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_17.setBounds(10, 79, 91, 20);
		eventFoodOverviewPanel.add(label_17);
		
		label_20 = new JLabel("TOP 3");
		label_20.setForeground(Color.BLUE);
		label_20.setHorizontalAlignment(SwingConstants.CENTER);
		label_20.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_20.setBounds(10, 110, 91, 20);
		eventFoodOverviewPanel.add(label_20);
		
		label_22 = new JLabel("TOP 4");
		label_22.setForeground(Color.BLUE);
		label_22.setHorizontalAlignment(SwingConstants.CENTER);
		label_22.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_22.setBounds(10, 141, 91, 20);
		eventFoodOverviewPanel.add(label_22);
		
		label_24 = new JLabel("TOP 5");
		label_24.setForeground(Color.BLUE);
		label_24.setHorizontalAlignment(SwingConstants.CENTER);
		label_24.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_24.setBounds(10, 172, 91, 20);
		eventFoodOverviewPanel.add(label_24);
		
		bef = new JLabel[5];
		bef[0] = new JLabel("n/a");
		bef[0].setHorizontalAlignment(SwingConstants.CENTER);
		bef[0].setForeground(Color.RED);
		bef[0].setFont(new Font("Tahoma", Font.BOLD, 13));
		bef[0].setBounds(429, 48, 91, 20);
		eventFoodOverviewPanel.add(bef[0]);
		
		bef[1] = new JLabel("n/a");
		bef[1].setHorizontalAlignment(SwingConstants.CENTER);
		bef[1].setForeground(Color.BLUE);
		bef[1].setFont(new Font("Tahoma", Font.BOLD, 13));
		bef[1].setBounds(429, 79, 91, 20);
		eventFoodOverviewPanel.add(bef[1]);
		
		bef[2] = new JLabel("n/a");
		bef[2].setHorizontalAlignment(SwingConstants.CENTER);
		bef[2].setForeground(Color.BLUE);
		bef[2].setFont(new Font("Tahoma", Font.BOLD, 13));
		bef[2].setBounds(429, 110, 91, 20);
		eventFoodOverviewPanel.add(bef[2]);
		
		bef[3] = new JLabel("n/a");
		bef[3].setHorizontalAlignment(SwingConstants.CENTER);
		bef[3].setForeground(Color.BLUE);
		bef[3].setFont(new Font("Tahoma", Font.BOLD, 13));
		bef[3].setBounds(429, 141, 91, 20);
		eventFoodOverviewPanel.add(bef[3]);
		
		bef[4] = new JLabel("n/a");
		bef[4].setHorizontalAlignment(SwingConstants.CENTER);
		bef[4].setForeground(Color.BLUE);
		bef[4].setFont(new Font("Tahoma", Font.BOLD, 13));
		bef[4].setBounds(429, 172, 91, 20);
		eventFoodOverviewPanel.add(bef[4]);
		
		bestEventFood = new JLabel[5];
		bestEventFood[0] = new JLabel("n/a");
		bestEventFood[0].setForeground(Color.RED);
		bestEventFood[0].setFont(new Font("Tahoma", Font.BOLD, 13));
		bestEventFood[0].setBounds(111, 48, 308, 20);
		eventFoodOverviewPanel.add(bestEventFood[0]);
		
		bestEventFood[1] = new JLabel("n/a");
		bestEventFood[1].setForeground(Color.BLUE);
		bestEventFood[1].setFont(new Font("Tahoma", Font.BOLD, 13));
		bestEventFood[1].setBounds(111, 79, 308, 20);
		eventFoodOverviewPanel.add(bestEventFood[1]);
		
		bestEventFood[2] = new JLabel("n/a");
		bestEventFood[2].setForeground(Color.BLUE);
		bestEventFood[2].setFont(new Font("Tahoma", Font.BOLD, 13));
		bestEventFood[2].setBounds(111, 110, 308, 20);
		eventFoodOverviewPanel.add(bestEventFood[2]);
		
		bestEventFood[3] = new JLabel("n/a");
		bestEventFood[3].setForeground(Color.BLUE);
		bestEventFood[3].setFont(new Font("Tahoma", Font.BOLD, 13));
		bestEventFood[3].setBounds(111, 141, 308, 20);
		eventFoodOverviewPanel.add(bestEventFood[3]);
		
		bestEventFood[4] = new JLabel("n/a");
		bestEventFood[4].setForeground(Color.BLUE);
		bestEventFood[4].setFont(new Font("Tahoma", Font.BOLD, 13));
		bestEventFood[4].setBounds(111, 172, 308, 20);
		eventFoodOverviewPanel.add(bestEventFood[4]);
		
		eventPackageOverviewPanel = new JPanel();
		eventPackageOverviewPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		foodAndPackagesTab.add(eventPackageOverviewPanel, "cell 1 1,grow");
		eventPackageOverviewPanel.setLayout(null);
		
		lblTopBestEventPackage = new JLabel("TOP 5 BEST SELLING PACKAGES (EVENT):");
		lblTopBestEventPackage.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblTopBestEventPackage.setBounds(10, 11, 510, 26);
		eventPackageOverviewPanel.add(lblTopBestEventPackage);
		
		label_33 = new JLabel("TOP 1");
		label_33.setForeground(Color.RED);
		label_33.setHorizontalAlignment(SwingConstants.CENTER);
		label_33.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_33.setBounds(10, 48, 91, 20);
		eventPackageOverviewPanel.add(label_33);
		
		label_34 = new JLabel("TOP 2");
		label_34.setForeground(Color.BLUE);
		label_34.setHorizontalAlignment(SwingConstants.CENTER);
		label_34.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_34.setBounds(10, 79, 91, 20);
		eventPackageOverviewPanel.add(label_34);
		
		label_36 = new JLabel("TOP 3");
		label_36.setForeground(Color.BLUE);
		label_36.setHorizontalAlignment(SwingConstants.CENTER);
		label_36.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_36.setBounds(10, 110, 91, 20);
		eventPackageOverviewPanel.add(label_36);
		
		label_38 = new JLabel("TOP 4");
		label_38.setForeground(Color.BLUE);
		label_38.setHorizontalAlignment(SwingConstants.CENTER);
		label_38.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_38.setBounds(10, 141, 91, 20);
		eventPackageOverviewPanel.add(label_38);
		
		label_41 = new JLabel("TOP 5");
		label_41.setForeground(Color.BLUE);
		label_41.setHorizontalAlignment(SwingConstants.CENTER);
		label_41.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_41.setBounds(10, 172, 91, 20);
		eventPackageOverviewPanel.add(label_41);
		
		bep = new JLabel[5];
		bep[0] = new JLabel("n/a");
		bep[0].setHorizontalAlignment(SwingConstants.CENTER);
		bep[0].setForeground(Color.RED);
		bep[0].setFont(new Font("Tahoma", Font.BOLD, 13));
		bep[0].setBounds(429, 48, 91, 20);
		eventPackageOverviewPanel.add(bep[0]);
		
		bep[1] = new JLabel("n/a");
		bep[1].setHorizontalAlignment(SwingConstants.CENTER);
		bep[1].setForeground(Color.BLUE);
		bep[1].setFont(new Font("Tahoma", Font.BOLD, 13));
		bep[1].setBounds(429, 79, 91, 20);
		eventPackageOverviewPanel.add(bep[1]);
		
		bep[2] = new JLabel("n/a");
		bep[2].setHorizontalAlignment(SwingConstants.CENTER);
		bep[2].setForeground(Color.BLUE);
		bep[2].setFont(new Font("Tahoma", Font.BOLD, 13));
		bep[2].setBounds(429, 110, 91, 20);
		eventPackageOverviewPanel.add(bep[2]);
		
		bep[3] = new JLabel("n/a");
		bep[3].setHorizontalAlignment(SwingConstants.CENTER);
		bep[3].setForeground(Color.BLUE);
		bep[3].setFont(new Font("Tahoma", Font.BOLD, 13));
		bep[3].setBounds(429, 141, 91, 20);
		eventPackageOverviewPanel.add(bep[3]);
		
		bep[4] = new JLabel("n/a");
		bep[4].setHorizontalAlignment(SwingConstants.CENTER);
		bep[4].setForeground(Color.BLUE);
		bep[4].setFont(new Font("Tahoma", Font.BOLD, 13));
		bep[4].setBounds(429, 172, 91, 20);
		eventPackageOverviewPanel.add(bep[4]);
		
		bestPackageEvent = new JLabel[5];
		bestPackageEvent[0] = new JLabel("n/a");
		bestPackageEvent[0].setForeground(Color.RED);
		bestPackageEvent[0].setFont(new Font("Tahoma", Font.BOLD, 13));
		bestPackageEvent[0].setBounds(111, 48, 308, 20);
		eventPackageOverviewPanel.add(bestPackageEvent[0]);
		
		bestPackageEvent[1] = new JLabel("n/a");
		bestPackageEvent[1].setForeground(Color.BLUE);
		bestPackageEvent[1].setFont(new Font("Tahoma", Font.BOLD, 13));
		bestPackageEvent[1].setBounds(111, 79, 308, 20);
		eventPackageOverviewPanel.add(bestPackageEvent[1]);
		
		bestPackageEvent[2] = new JLabel("n/a");
		bestPackageEvent[2].setForeground(Color.BLUE);
		bestPackageEvent[2].setFont(new Font("Tahoma", Font.BOLD, 13));
		bestPackageEvent[2].setBounds(111, 110, 308, 20);
		eventPackageOverviewPanel.add(bestPackageEvent[2]);
		
		bestPackageEvent[3] = new JLabel("n/a");
		bestPackageEvent[3].setForeground(Color.BLUE);
		bestPackageEvent[3].setFont(new Font("Tahoma", Font.BOLD, 13));
		bestPackageEvent[3].setBounds(111, 141, 308, 20);
		eventPackageOverviewPanel.add(bestPackageEvent[3]);
		
		bestPackageEvent[4] = new JLabel("n/a");
		bestPackageEvent[4].setForeground(Color.BLUE);
		bestPackageEvent[4].setFont(new Font("Tahoma", Font.BOLD, 13));
		bestPackageEvent[4].setBounds(111, 172, 308, 20);
		eventPackageOverviewPanel.add(bestPackageEvent[4]);
		
		normalPackageOverviewPanel = new JPanel();
		normalPackageOverviewPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		foodAndPackagesTab.add(normalPackageOverviewPanel, "cell 0 1,grow");
		normalPackageOverviewPanel.setLayout(null);
		
		lblTopBestNormalPackage = new JLabel("TOP 5 BEST SELLING PACKAGES (REGULAR):");
		lblTopBestNormalPackage.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblTopBestNormalPackage.setBounds(10, 11, 510, 26);
		normalPackageOverviewPanel.add(lblTopBestNormalPackage);
		
		label_4 = new JLabel("TOP 1");
		label_4.setForeground(Color.RED);
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_4.setBounds(10, 48, 91, 20);
		normalPackageOverviewPanel.add(label_4);
		
		label_7 = new JLabel("TOP 2");
		label_7.setForeground(Color.BLUE);
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_7.setBounds(10, 79, 91, 20);
		normalPackageOverviewPanel.add(label_7);
		
		label_10 = new JLabel("TOP 3");
		label_10.setForeground(Color.BLUE);
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_10.setBounds(10, 110, 91, 20);
		normalPackageOverviewPanel.add(label_10);
		
		label_13 = new JLabel("TOP 4");
		label_13.setForeground(Color.BLUE);
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		label_13.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_13.setBounds(10, 141, 91, 20);
		normalPackageOverviewPanel.add(label_13);
		
		label_31 = new JLabel("TOP 5");
		label_31.setForeground(Color.BLUE);
		label_31.setHorizontalAlignment(SwingConstants.CENTER);
		label_31.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_31.setBounds(10, 172, 91, 20);
		normalPackageOverviewPanel.add(label_31);
		
		bnp = new JLabel[5];
		bnp[0] = new JLabel("n/a");
		bnp[0].setHorizontalAlignment(SwingConstants.CENTER);
		bnp[0].setForeground(Color.RED);
		bnp[0].setFont(new Font("Tahoma", Font.BOLD, 13));
		bnp[0].setBounds(429, 48, 91, 20);
		normalPackageOverviewPanel.add(bnp[0]);
		
		bnp[1] = new JLabel("n/a");
		bnp[1].setHorizontalAlignment(SwingConstants.CENTER);
		bnp[1].setForeground(Color.BLUE);
		bnp[1].setFont(new Font("Tahoma", Font.BOLD, 13));
		bnp[1].setBounds(429, 79, 91, 20);
		normalPackageOverviewPanel.add(bnp[1]);
		
		bnp[2] = new JLabel("n/a");
		bnp[2].setHorizontalAlignment(SwingConstants.CENTER);
		bnp[2].setForeground(Color.BLUE);
		bnp[2].setFont(new Font("Tahoma", Font.BOLD, 13));
		bnp[2].setBounds(429, 110, 91, 20);
		normalPackageOverviewPanel.add(bnp[2]);
		
		bnp[3] = new JLabel("n/a");
		bnp[3].setHorizontalAlignment(SwingConstants.CENTER);
		bnp[3].setForeground(Color.BLUE);
		bnp[3].setFont(new Font("Tahoma", Font.BOLD, 13));
		bnp[3].setBounds(429, 141, 91, 20);
		normalPackageOverviewPanel.add(bnp[3]);
		
		bnp[4] = new JLabel("n/a");
		bnp[4].setHorizontalAlignment(SwingConstants.CENTER);
		bnp[4].setForeground(Color.BLUE);
		bnp[4].setFont(new Font("Tahoma", Font.BOLD, 13));
		bnp[4].setBounds(429, 172, 91, 20);
		normalPackageOverviewPanel.add(bnp[4]);
		
		bestPackageNormal = new JLabel[5];
		bestPackageNormal[4] = new JLabel("n/a");
		bestPackageNormal[4].setForeground(Color.BLUE);
		bestPackageNormal[4].setFont(new Font("Tahoma", Font.BOLD, 13));
		bestPackageNormal[4].setBounds(111, 172, 308, 20);
		normalPackageOverviewPanel.add(bestPackageNormal[4]);
		
		bestPackageNormal[3] = new JLabel("n/a");
		bestPackageNormal[3].setForeground(Color.BLUE);
		bestPackageNormal[3].setFont(new Font("Tahoma", Font.BOLD, 13));
		bestPackageNormal[3].setBounds(111, 141, 308, 20);
		normalPackageOverviewPanel.add(bestPackageNormal[3]);
		
		bestPackageNormal[2] = new JLabel("n/a");
		bestPackageNormal[2].setForeground(Color.BLUE);
		bestPackageNormal[2].setFont(new Font("Tahoma", Font.BOLD, 13));
		bestPackageNormal[2].setBounds(111, 110, 308, 20);
		normalPackageOverviewPanel.add(bestPackageNormal[2]);
		
		bestPackageNormal[1] = new JLabel("n/a");
		bestPackageNormal[1].setForeground(Color.BLUE);
		bestPackageNormal[1].setFont(new Font("Tahoma", Font.BOLD, 13));
		bestPackageNormal[1].setBounds(111, 79, 308, 20);
		normalPackageOverviewPanel.add(bestPackageNormal[1]);
		
		bestPackageNormal[0] = new JLabel("n/a");
		bestPackageNormal[0].setForeground(Color.RED);
		bestPackageNormal[0].setFont(new Font("Tahoma", Font.BOLD, 13));
		bestPackageNormal[0].setBounds(111, 48, 308, 20);
		normalPackageOverviewPanel.add(bestPackageNormal[0]);
		
		normalFoodOverviewPanel = new JPanel();
		normalFoodOverviewPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		foodAndPackagesTab.add(normalFoodOverviewPanel, "cell 0 0,grow");
		normalFoodOverviewPanel.setLayout(null);
		
		lblTopBestNormalFood = new JLabel("TOP 5 BEST SELLING FOOD (REGULAR):");
		lblTopBestNormalFood.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblTopBestNormalFood.setBounds(10, 11, 510, 26);
		normalFoodOverviewPanel.add(lblTopBestNormalFood);
		
		lblTop = new JLabel("TOP 1");
		lblTop.setForeground(Color.RED);
		lblTop.setHorizontalAlignment(SwingConstants.CENTER);
		lblTop.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTop.setBounds(10, 48, 91, 20);
		normalFoodOverviewPanel.add(lblTop);
		
		bestNormalfood = new JLabel[5];
		bestNormalfood[0] = new JLabel("n/a");
		bestNormalfood[0].setForeground(Color.RED);
		bestNormalfood[0].setFont(new Font("Tahoma", Font.BOLD, 13));
		bestNormalfood[0].setBounds(111, 48, 308, 20);
		normalFoodOverviewPanel.add(bestNormalfood[0]);
		
		bnf = new JLabel[5];
		bnf[0] = new JLabel("n/a");
		bnf[0].setForeground(Color.RED);
		bnf[0].setHorizontalAlignment(SwingConstants.CENTER);
		bnf[0].setFont(new Font("Tahoma", Font.BOLD, 13));
		bnf[0].setBounds(429, 48, 91, 20);
		normalFoodOverviewPanel.add(bnf[0]);
		
		lblTop_1 = new JLabel("TOP 2");
		lblTop_1.setForeground(Color.BLUE);
		lblTop_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTop_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTop_1.setBounds(10, 79, 91, 20);
		normalFoodOverviewPanel.add(lblTop_1);
		
		bestNormalfood[1] = new JLabel("n/a");
		bestNormalfood[1].setForeground(Color.BLUE);
		bestNormalfood[1].setFont(new Font("Tahoma", Font.BOLD, 13));
		bestNormalfood[1].setBounds(111, 79, 308, 20);
		normalFoodOverviewPanel.add(bestNormalfood[1]);
		
		bnf[1] = new JLabel("n/a");
		bnf[1].setForeground(Color.BLUE);
		bnf[1].setHorizontalAlignment(SwingConstants.CENTER);
		bnf[1].setFont(new Font("Tahoma", Font.BOLD, 13));
		bnf[1].setBounds(429, 79, 91, 20);
		normalFoodOverviewPanel.add(bnf[1]);
		
		lblTop_2 = new JLabel("TOP 3");
		lblTop_2.setForeground(Color.BLUE);
		lblTop_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblTop_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTop_2.setBounds(10, 110, 91, 20);
		normalFoodOverviewPanel.add(lblTop_2);
		
		//bestNormalFood[2] = new JLabel("TOP 3 BEST SELLING FOOD (NORMAL):");
		bestNormalfood[2] = new JLabel("n/a");
		bestNormalfood[2].setForeground(Color.BLUE);
		bestNormalfood[2].setFont(new Font("Tahoma", Font.BOLD, 13));
		bestNormalfood[2].setBounds(111, 110, 308, 20);
		normalFoodOverviewPanel.add(bestNormalfood[2]);
		
		lblTop_3 = new JLabel("TOP 4");
		lblTop_3.setForeground(Color.BLUE);
		lblTop_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblTop_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTop_3.setBounds(10, 141, 91, 20);
		normalFoodOverviewPanel.add(lblTop_3);
		
		//bestNormalFood[3] = new JLabel("TOP 4 BEST SELLING FOOD (NORMAL):");
		bestNormalfood[3] = new JLabel("n/a");
		bestNormalfood[3].setForeground(Color.BLUE);
		bestNormalfood[3].setFont(new Font("Tahoma", Font.BOLD, 13));
		bestNormalfood[3].setBounds(111, 141, 308, 20);
		normalFoodOverviewPanel.add(bestNormalfood[3]);
		
		lblTop_4 = new JLabel("TOP 5");
		lblTop_4.setForeground(Color.BLUE);
		lblTop_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblTop_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTop_4.setBounds(10, 172, 91, 20);
		normalFoodOverviewPanel.add(lblTop_4);
		
		//bestNormalFood[4] = new JLabel("TOP 5 BEST SELLING FOOD (NORMAL):");
		bestNormalfood[4] = new JLabel("n/a");
		bestNormalfood[4].setForeground(Color.BLUE);
		bestNormalfood[4].setFont(new Font("Tahoma", Font.BOLD, 13));
		bestNormalfood[4].setBounds(111, 172, 308, 20);
		normalFoodOverviewPanel.add(bestNormalfood[4]);
		
		bnf[2] = new JLabel("n/a");
		bnf[2].setHorizontalAlignment(SwingConstants.CENTER);
		bnf[2].setForeground(Color.BLUE);
		bnf[2].setFont(new Font("Tahoma", Font.BOLD, 13));
		bnf[2].setBounds(429, 110, 91, 20);
		normalFoodOverviewPanel.add(bnf[2]);
		
		bnf[3] = new JLabel("n/a");
		bnf[3].setHorizontalAlignment(SwingConstants.CENTER);
		bnf[3].setForeground(Color.BLUE);
		bnf[3].setFont(new Font("Tahoma", Font.BOLD, 13));
		bnf[3].setBounds(429, 141, 91, 20);
		normalFoodOverviewPanel.add(bnf[3]);
		
		bnf[4] = new JLabel("n/a");
		bnf[4].setHorizontalAlignment(SwingConstants.CENTER);
		bnf[4].setForeground(Color.BLUE);
		bnf[4].setFont(new Font("Tahoma", Font.BOLD, 13));
		bnf[4].setBounds(429, 172, 91, 20);
		normalFoodOverviewPanel.add(bnf[4]);
		
		lblNewLabel = new JLabel("STATISTICS:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		plOverview.add(lblNewLabel, "cell 0 0,grow");
		
		overviewFromLabel = new JLabel("FROM:");
		plOverview.add(overviewFromLabel, "cell 2 0,alignx left,growy");
		
		setCustOverviewLabel();
		setSalesOverviewLabel();
		setFoodPackageOverviewLabel();

		
		fromDateOverview = new JDateChooser(new Date());
		fromDateOverview.getDateEditor().setEnabled(false);
		fromDateOverview.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				overviewFromDate = (String) sdf.format(fromDateOverview.getDate());
				overviewToDate = (String) sdf.format(toDateOverview.getDate());
				fromDateOverview.setMaxSelectableDate(toDateOverview.getDate());
				toDateOverview.setMinSelectableDate(fromDateOverview.getDate());
				createDatasetCustomer();
				createDatasetSales();
				panel.removeAll();
				panel.add(createPanelCustomer());
				salesChartMotherPanel.removeAll();
				salesChartMotherPanel.add(createPanelSales());
				setCustOverviewLabel();
				setFoodPackageOverviewLabel();
				setSalesOverviewLabel();

			}
		});
		fromDateOverview.setDateFormatString("MMM dd, yyyy");
		fromDateOverview.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		plOverview.add(fromDateOverview, "cell 4 0,grow");
		
		JLabel overviewToLabel = new JLabel("TO:");
		plOverview.add(overviewToLabel, "cell 6 0,alignx left,growy");
		
		toDateOverview = new JDateChooser(new Date());
		toDateOverview.getDateEditor().setEnabled(false);
		toDateOverview.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				overviewFromDate = (String) sdf.format(fromDateOverview.getDate());
				overviewToDate = (String) sdf.format(toDateOverview.getDate());
				toDateOverview.setMinSelectableDate(fromDateOverview.getDate());
				fromDateOverview.setMaxSelectableDate(toDateOverview.getDate());
				createDatasetCustomer();
				createDatasetSales();
				panel.removeAll();
				panel.add(createPanelCustomer());
				salesChartMotherPanel.removeAll();
				salesChartMotherPanel.add(createPanelSales());
				setCustOverviewLabel();
				setFoodPackageOverviewLabel();
				setSalesOverviewLabel();
				
				
			}
		});
		toDateOverview.setDateFormatString("MMM dd, yyyy");
		toDateOverview.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		toDateOverview.setMinSelectableDate(fromDateOverview.getDate());
		fromDateOverview.setMaxSelectableDate(toDateOverview.getDate());
		plOverview.add(toDateOverview, "cell 8 0,alignx left,growy");
		
		overviewFromDate = (String) sdf.format(fromDateOverview.getDate());
		overviewToDate = (String) sdf.format(toDateOverview.getDate());
		
		plTables = new JPanel();
		plQuery.addTab("Tables and Rooms", null, plTables, null);
						plTables.setLayout(new MigLayout("", "[161px][12px][72px][12px][75px][12px][129px][51px][10px][50px][15px][24px][10px][35px][10px][63px][4px][15px][10px][57px][10px][46px][10px][82px,grow]", "[27px][29px][29px][139px,grow][28px][31px][143px,grow]"));
				//		tabbedPane.addTab("Transactions", null, pnlTransactions, null);
						
						tableDateChooser = new JDateChooser(new Date());
						tableDateChooser.getDateEditor().setEnabled(false);
						plTables.add(tableDateChooser, "cell 0 1,grow");
						tableDateChooser.addPropertyChangeListener(new PropertyChangeListener() {
							public void propertyChange(PropertyChangeEvent arg0) {
								
								ViewAvailableTable();
								ViewUnavailableTable();
								
							}
						});
						tableDateChooser.setDateFormatString("MMM dd, yyyy");
						tableDateChooser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						//tableDateChooser.setMinSelectableDate(new Date());
				
				tableTimeSpinner = new JSpinner( new SpinnerDateModel() );
				
				plTables.add(tableTimeSpinner, "cell 2 1,grow");
				de_tableTimeSpinner = new JSpinner.DateEditor(tableTimeSpinner, "hh:mm a");
				DateFormatter sampleFormatter = (DateFormatter)de_tableTimeSpinner.getTextField().getFormatter();
				sampleFormatter.setAllowsInvalid(false);
				sampleFormatter.setOverwriteMode(true);
				tableTimeSpinner.setEditor(de_tableTimeSpinner);
				tableTimeSpinner.setValue(new Date());
				
				
				 JComponent comp = tableTimeSpinner.getEditor();							//USED TO
				 JFormattedTextField field = (JFormattedTextField) comp.getComponent(0);		//MAKE EVERY CHANGE
				 DefaultFormatter formatter = (DefaultFormatter) field.getFormatter();				//IMMEDIATELY UPDATE THE TABLES
				 formatter.setCommitsOnValidEdit(true);
				
				lblTableStatus = new JLabel("TABLE STATUS:");
				plTables.add(lblTableStatus, "cell 4 1,grow");
				
				
				btnShowMoreDetails = new JButton("SHOW MORE DETAILS");
				btnShowMoreDetails.setEnabled(false);
				btnShowMoreDetails.setVisible(false);
				plTables.add(btnShowMoreDetails, "cell 23 2,grow");
				btnShowMoreDetails.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						if(tableIdOccupied == 1)
						{
							ShowMoreWaiting showWt = new ShowMoreWaiting(waiting_id);
							showWt.setVisible(true);
							showWt.addWindowListener(new WindowAdapter() {
						         public void windowClosed(WindowEvent windowEvent){
						        	 ViewAvailableTable();
						        	 ViewUnavailableTable();
						        	 ViewFloorTable();
						          }        
						       }); 
						}
						else if(tableIdOccupied == 2)
						{
							ShowMoreReserve showRv = new ShowMoreReserve(reserve_id);
							showRv.setVisible(true);
							showRv.addWindowListener(new WindowAdapter() {
						         public void windowClosed(WindowEvent windowEvent){
						        	 ViewAvailableTable();
						        	 ViewUnavailableTable();
						        	 ViewFloorTable();
						          }        
						       }); 
						}
						else if(tableIdOccupied == 3)
						{
							ShowMoreDinein showDi = new ShowMoreDinein(dinein_id);
							showDi.setVisible(true);
							showDi.addWindowListener(new WindowAdapter() {
						         public void windowClosed(WindowEvent windowEvent){
						        	 ViewAvailableTable();
						        	 ViewUnavailableTable();
						        	 ViewFloorTable();
						          }        
						       }); 
						}
					}
				});
				
				availableScrollPane = new JScrollPane();
				plTables.add(availableScrollPane, "cell 0 3 7 1,grow");
				
				availableTables = new JTable(){
				    @Override
				    public boolean isCellEditable(int row, int column) {
				        // This is how we disable editing:
				        return false;
				    }
				};
				
				availableTables.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 11));
				
				availableTables.setBounds(7, 51, 1088, 378);
				availableTables.setAutoCreateRowSorter(true);
				availableScrollPane.setViewportView(availableTables);
				
				tableStatus = new JComboBox();
				
				
				plTables.add(tableStatus, "cell 6 1,grow");
				tableStatus.setModel(new DefaultComboBoxModel(new String[] {"ALL", "ACTIVE", "INACTIVE"}));
				tableStatus.setFont(new Font("Tahoma", Font.PLAIN, 11));
				
				tableSearch = new JTextField();
				plTables.add(tableSearch, "cell 19 1 3 1,grow");
				tableSearch.setToolTipText("");
				tableSearch.setColumns(10);
				
				btnTableSearch = new JButton("SEARCH");
				plTables.add(btnTableSearch, "cell 23 1,grow");
				
				
				JLabel lblTables = new JLabel("TABLES INFORMATION");
				plTables.add(lblTables, "cell 0 0,alignx left,growy");
				lblTables.setFont(new Font("Tahoma", Font.BOLD, 13));
				
				JLabel lblRoom = new JLabel("ROOM:");
				plTables.add(lblRoom, "cell 13 1,grow");
				
				roomSelect = new JComboBox();
				roomSelect.setModel(new DefaultComboBoxModel(new String[] {"ALL"}));
				
				plTables.add(roomSelect, "cell 15 1 3 1,grow");
				roomSelect.setFont(new Font("Tahoma", Font.PLAIN, 11));
				
				
				
		
		plCustomers = new JPanel();
		plQuery.addTab("Customers", null, plCustomers, null);
		
		custFromDateChooser = new JDateChooser(new Date());
		custFromDateChooser.getDateEditor().setEnabled(false);
		custFromDateChooser.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				ViewCustTableReserve();
				ViewCustTableWaiting();
				ViewCustTableDinein();
				ViewCustTableTakeout();
				custToDateChooser.setMinSelectableDate(custFromDateChooser.getDate());
			}
		});
		plCustomers.setLayout(new MigLayout("", "[33px][8px][175.00px][8px][61.00px][9.00px][219px][31.00px][215px][8px][174.00px,grow]", "[16.00px,grow][14.00px,grow][203px,grow][23px][210px,grow]"));
		custFromDateChooser.setDateFormatString("MMM dd, yyyy");
		
		custFromDateChooser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		//custFromDateChooser.setMinSelectableDate();  
		plCustomers.add(custFromDateChooser, "cell 2 0,grow");
		
		custToDateChooser = new JDateChooser(new Date());
		custToDateChooser.getDateEditor().setEnabled(false);
		custToDateChooser.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				ViewCustTableReserve();
				ViewCustTableWaiting();
				ViewCustTableDinein();
				ViewCustTableTakeout();
				custFromDateChooser.setMaxSelectableDate(custToDateChooser.getDate());
				
			}
		});
		custToDateChooser.setDateFormatString("MMM dd, yyyy");
		custToDateChooser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		custToDateChooser.setMinSelectableDate(custFromDateChooser.getDate()); 
		custFromDateChooser.setMaxSelectableDate(custToDateChooser.getDate());
		plCustomers.add(custToDateChooser, "cell 6 0,grow");
				
				custSearch = new JTextField();
				custSearch.setColumns(10);
				plCustomers.add(custSearch, "cell 8 0,grow");
				
				btnCustSearch = new JButton("SEARCH");
				btnCustSearch.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ViewCustTableReserve();
						ViewCustTableWaiting();
						ViewCustTableDinein();
						ViewCustTableTakeout();
					}
				});
				plCustomers.add(btnCustSearch, "cell 10 0,grow");
				
				custRvScrollPane = new JScrollPane();
				plCustomers.add(custRvScrollPane, "cell 0 2 11 1,grow");
				
				custRvTable = new JTable(){
				    @Override
				    public boolean isCellEditable(int row, int column) {
				        // This is how we disable editing:
				        return false;
				    }
				};
				
				ListSelectionModel selectionModelReserveCustomers = custRvTable.getSelectionModel();		
				selectionModelReserveCustomers.addListSelectionListener( this );
				
				custRvTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 11));
				custRvTable.setAutoCreateRowSorter(true);
				custRvScrollPane.setViewportView(custRvTable);
				
				
				
				custDIScrollPane = new JScrollPane();
				plCustomers.add(custDIScrollPane, "cell 0 4 7 1,grow");
				
				custDITable = new JTable(){
				    @Override
				    public boolean isCellEditable(int row, int column) {
				        // This is how we disable editing:
				        return false;
				    }
				};
				custDITable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 11));
				custDIScrollPane.setViewportView(custDITable);
				
				
				ListSelectionModel selectionModelDineinCustomers = custDITable.getSelectionModel();		
				selectionModelDineinCustomers.addListSelectionListener( this );
				
				
				
				custDITable.setAutoCreateRowSorter(true);
				custTOScrollPane = new JScrollPane();
				plCustomers.add(custTOScrollPane, "cell 8 4 3 1,grow");
				
				custTOTable = new JTable(){
				    @Override
				    public boolean isCellEditable(int row, int column) {
				        // This is how we disable editing:
				        return false;
				    }
				};
				
				ListSelectionModel selectionModelTakeoutCustomers = custTOTable.getSelectionModel();		
				selectionModelTakeoutCustomers.addListSelectionListener( this );
				
				
				custTOTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 11));
				custTOScrollPane.setViewportView(custTOTable);
				custTOTable.setAutoCreateRowSorter(true);
				
				
				lblCustomerInformation = new JLabel("RESERVED:");
				plCustomers.add(lblCustomerInformation, "cell 0 1 3 1,grow");
				
				lblDineIn = new JLabel("DINE-IN:");
				plCustomers.add(lblDineIn, "cell 0 3 5 1,grow");
				
				JLabel lblTakeout = new JLabel("TAKE-OUT:");
				plCustomers.add(lblTakeout, "cell 8 3 3 1,grow");
				
				showRVDetails = new JButton("SHOW MORE DETAILS");
				showRVDetails.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~OPEN RESERVE DETAILS WINDOW
						ShowMoreReserve showMoreRv = new ShowMoreReserve(reserve_id);
						showMoreRv.setVisible(true);
						showMoreRv.addWindowListener(new WindowAdapter() {
					         public void windowClosed(WindowEvent windowEvent){
					        	 ViewCustTableReserve();
					        	 ViewCustTableWaiting();
					        	 ViewCustTableDinein();
					        	 ViewCustTableTakeout();
					          }        
					       }); 
					}
				});
				showRVDetails.setEnabled(false);
				showRVDetails.setBackground(Color.LIGHT_GRAY);
				plCustomers.add(showRVDetails, "cell 10 1,grow");
				
				JLabel lblFrom_1 = new JLabel("FROM:");
				plCustomers.add(lblFrom_1, "cell 0 0,grow");
				
				JLabel lblTo_1 = new JLabel("TO:");
				plCustomers.add(lblTo_1, "cell 4 0,grow");
				//selectionModelWaitingCustomers.addListSelectionListener( this );
				
				showDIDetails = new JButton("SHOW MORE DETAILS");
				showDIDetails.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ShowMoreDinein showMoreDI = new ShowMoreDinein(dinein_id);
						showMoreDI.setVisible(true);
						showMoreDI.addWindowListener(new WindowAdapter() {
					         public void windowClosed(WindowEvent windowEvent){
					        	 ViewCustTableReserve();
					        	 ViewCustTableWaiting();
					        	 ViewCustTableDinein();
					        	 ViewCustTableTakeout();
					          }        
					       }); 
					}
				});
				showDIDetails.setEnabled(false);
				showDIDetails.setBackground(Color.LIGHT_GRAY);
				plCustomers.add(showDIDetails, "cell 6 3,grow");
				
				showTODetails = new JButton("SHOW MORE DETAILS");
				showTODetails.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ShowMoreTakeout showMoreTO = new ShowMoreTakeout(takeout_id);
						showMoreTO.setVisible(true);
						showMoreTO.addWindowListener(new WindowAdapter() {
					         public void windowClosed(WindowEvent windowEvent){
					        	 ViewCustTableReserve();
					        	 ViewCustTableWaiting();
					        	 ViewCustTableDinein();
					        	 ViewCustTableTakeout();
					          }        
					       }); 
					}
				});
				showTODetails.setEnabled(false);
				showTODetails.setBackground(Color.LIGHT_GRAY);
				plCustomers.add(showTODetails, "cell 10 3,grow");
				
				ViewCustTableReserve();
				ViewCustTableWaiting();
				ViewCustTableDinein();
				ViewCustTableTakeout();
		
		plMenu = new JPanel();
		plQuery.addTab("Menu", null, plMenu, null);
		plMenu.setBorder(new LineBorder(new Color(0, 0, 0)));
		plMenu.setLayout(new MigLayout("", "[1082px,grow]", "[541px,grow]"));
		
		tpMenu = new JTabbedPane(JTabbedPane.TOP);
		tpMenu.setUI(new AquaTabbedPaneUI());
		plMenu.add(tpMenu, "cell 0 0,grow");
		
		JPanel Food = new JPanel();
		tpMenu.addTab("Foods", null, Food, null);
		Food.setLayout(new MigLayout("", "[91px][10px][238px][85px][10px][269px][263px][4px][73px,grow]", "[42.00px][15.00][33px][170px][35.00px][170px,grow]"));
		
		normalFoodScrollpane1 = new JScrollPane();
		Food.add(normalFoodScrollpane1, "cell 0 3 3 1,grow");
		
		normalFoodTable = new JTable(){
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        // This is how we disable editing:
		        return false;
		    }
		};
		normalFoodTable.setAutoCreateRowSorter(true);
		// Handle the listener
		ListSelectionModel selectionModel = normalFoodTable.getSelectionModel();		
		selectionModel.addListSelectionListener( this );
		
		normalFoodTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 11));
		
		normalFoodScrollpane1.setViewportView(normalFoodTable);
		normalFoodTable.setFillsViewportHeight(true);
		
		normalFoodScrollpane2 = new JScrollPane();
		Food.add(normalFoodScrollpane2, "cell 3 3 3 1,grow");
		
		normalFoodSizesTable = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		normalFoodSizesTable.setAutoCreateRowSorter(true);
		normalFoodScrollpane2.setViewportView(normalFoodSizesTable);
		normalFoodSizesTable.setFillsViewportHeight(true);
		normalFoodSizesTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 11));
		
		
		lblFoodStatus = new JLabel("FOOD STATUS:");
		Food.add(lblFoodStatus, "cell 0 0,grow");
		
		foodStatus = new JComboBox();
		
		foodStatus.setModel(new DefaultComboBoxModel(new String[] {"ALL", "ACTIVE", "INACTIVE"}));
		foodStatus.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Food.add(foodStatus, "cell 2 0,grow");
		
		lblFoodCategory = new JLabel("CATEGORY:");
		Food.add(lblFoodCategory, "cell 3 0,grow");
		
		foodCategory = new JComboBox();
		foodCategory.setModel(new DefaultComboBoxModel(new String[] {"ALL"}));
		foodCategory.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Food.add(foodCategory, "cell 5 0,grow");
		
		foodSearch = new JTextField();
		foodSearch.setColumns(10);
		Food.add(foodSearch, "cell 6 0,grow");
		
		btnFoodSearch = new JButton("SEARCH");
		
		Food.add(btnFoodSearch, "cell 8 0,grow");
		
		lblNormalFood = new JLabel("REGULAR FOOD:");
		lblNormalFood.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		Food.add(lblNormalFood, "cell 0 2 3 1,grow");
		
		normalFoodImagePanel = new JPanel();
		normalFoodImagePanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		Food.add(normalFoodImagePanel, "cell 6 3 3 1,grow");
		normalFoodImagePanel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		lblEventFoods = new JLabel("EVENT FOOD:");
		lblEventFoods.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		Food.add(lblEventFoods, "cell 0 4 3 1,grow");
		
		JScrollPane eventFoodScrollpane1 = new JScrollPane();
		Food.add(eventFoodScrollpane1, "cell 0 5 3 1,grow");
		
		eventFoodTable = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		eventFoodTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 11));
		
		ListSelectionModel selectionModelEventFood = eventFoodTable.getSelectionModel();		
		selectionModelEventFood.addListSelectionListener( this );
		
		eventFoodTable.setAutoCreateRowSorter(true);
		
		eventFoodScrollpane1.setViewportView(eventFoodTable);
		eventFoodTable.setFillsViewportHeight(true);
		
		eventFoodImagePanel = new JPanel();
		eventFoodImagePanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		Food.add(eventFoodImagePanel, "cell 6 5 3 1,grow");
		eventFoodImagePanel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		eventFoodScrollpane2 = new JScrollPane();
		Food.add(eventFoodScrollpane2, "cell 3 5 3 1,grow");
		
		eventFoodSizesTable = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		eventFoodScrollpane2.setViewportView(eventFoodSizesTable);
		eventFoodSizesTable.setFillsViewportHeight(true);
		eventFoodSizesTable.setAutoCreateRowSorter(true);
		
		eventFoodSizesTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 11));
		
		
		JPanel Packages = new JPanel();
		tpMenu.addTab("Packages", null, Packages, null);
		Packages.setLayout(new MigLayout("", "[91px][18px][285.00px][58px][8px][309px][229px][10px][78px,grow]", "[42px][15.00][33px][170px][35px][179px,grow]"));
		
		JLabel lblNormalPackages = new JLabel("REGULAR PACKAGES:");
		lblNormalPackages.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		Packages.add(lblNormalPackages, "cell 0 2 3 1,grow");
		
		JScrollPane scrollPane = new JScrollPane();
		Packages.add(scrollPane, "cell 0 3 3 1,grow");
		
		normalPackageTable = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		normalPackageTable.setAutoCreateRowSorter(true);
		
		ListSelectionModel selectionModelNormalPackage = normalPackageTable.getSelectionModel();		
		selectionModelNormalPackage.addListSelectionListener( this );
		
		normalPackageTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 11));
		
		scrollPane.setViewportView(normalPackageTable);
		normalPackageTable.setFillsViewportHeight(true);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		Packages.add(scrollPane_2, "cell 0 5 3 1,grow");
		
		eventPackageTable = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		eventPackageTable.setAutoCreateRowSorter(true);
		
		eventPackageTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 11));
		
		ListSelectionModel selectionModelEventPackage = eventPackageTable.getSelectionModel();		
		selectionModelEventPackage.addListSelectionListener( this );
		
		
		scrollPane_2.setViewportView(eventPackageTable);
		eventPackageTable.setFillsViewportHeight(true);
		
		JLabel lblEventPackages = new JLabel("EVENT PACKAGES:");
		lblEventPackages.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		Packages.add(lblEventPackages, "cell 0 4 3 1,grow");
		
		scrollPane_3 = new JScrollPane();
		Packages.add(scrollPane_3, "cell 3 5 3 1,grow");
		
		eventPackageItemsTable = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		eventPackageItemsTable.setAutoCreateRowSorter(true);
		scrollPane_3.setViewportView(eventPackageItemsTable);
		eventPackageItemsTable.setFillsViewportHeight(true);
		
		eventPackageItemsTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 11));
		
		
		
		scrollPane_1 = new JScrollPane();
		Packages.add(scrollPane_1, "cell 3 3 3 1,grow");
		
		normalPackageItemsTable = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		normalPackageItemsTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 11));
		
		scrollPane_1.setViewportView(normalPackageItemsTable);
		normalPackageItemsTable.setFillsViewportHeight(true);
		normalPackageItemsTable.setAutoCreateRowSorter(true);
		packageSearch = new JTextField();
		packageSearch.setColumns(10);
		Packages.add(packageSearch, "cell 6 0,grow");
		
		normalPackageImagePanel = new JPanel();
		normalPackageImagePanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		Packages.add(normalPackageImagePanel, "cell 6 3 3 1,grow");
		normalPackageImagePanel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		eventPackageImagePanel = new JPanel();
		eventPackageImagePanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		Packages.add(eventPackageImagePanel, "cell 6 5 3 1,grow");
		eventPackageImagePanel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		btnPackageSearch = new JButton("SEARCH");
		
		Packages.add(btnPackageSearch, "cell 8 0,grow");
		
		JLabel lblRoomInformation = new JLabel("FLOORS AND ROOMS INFORMATION");
		plTables.add(lblRoomInformation, "cell 0 4 3 1,grow");
		lblRoomInformation.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		roomScrollPane = new JScrollPane();
		plTables.add(roomScrollPane, "cell 7 6 17 1,grow");
		
		roomTable = new JTable(){
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        // This is how we disable editing:
		        return false;
		    }
		};
		roomTable.setAutoCreateRowSorter(true);
		
		roomTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 11));
		
		roomScrollPane.setViewportView(roomTable);
		
		lblAvailableVacant = new JLabel("AVAILABLE / VACANT TABLES:");
		plTables.add(lblAvailableVacant, "cell 0 2 3 1,grow");
		
		JLabel lblUnavailable = new JLabel("UNAVAILABLE TABLES:");
		plTables.add(lblUnavailable, "cell 7 2 3 1,grow");
		
		JScrollPane unavailableScrollPane = new JScrollPane();
		plTables.add(unavailableScrollPane, "cell 7 3 17 1,grow");
		
		unavailableTable = new JTable(){
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        // This is how we disable editing:
		        return false;
		    }
		};
		
		unavailableTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 11));
		
		ListSelectionModel selectionModelUnavailableTables = unavailableTable.getSelectionModel();
		selectionModelUnavailableTables.addListSelectionListener(this);
		
		unavailableTable.setAutoCreateRowSorter(true);
		unavailableScrollPane.setViewportView(unavailableTable);
		//de_tableTimeSpinner = new JSpinner.DateEditor(tableTimeSpinner, "HH:mm:ss");
		DateEditor de_rvTimeSpinner = new JSpinner.DateEditor(tableTimeSpinner, "HH:mm:ss");
		
		floorSelect = new JComboBox();
		
		floorSelect.setModel(new DefaultComboBoxModel(new String[] {"ALL"}));
		floorSelect.setFont(new Font("Tahoma", Font.PLAIN, 11));
		plTables.add(floorSelect, "cell 9 1 3 1,grow");
		
		JLabel floorlbl = new JLabel("FLOOR:");
		plTables.add(floorlbl, "cell 7 1,grow");
		
		floorTableScrollPane = new JScrollPane();
		plTables.add(floorTableScrollPane, "cell 0 6 7 1,grow");
		
		floorTable = new JTable(){
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        // This is how we disable editing:
		        return false;
		    }
		};
		
		floorTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 11));
		
		ListSelectionModel selectionModelFloors = floorTable.getSelectionModel();
		selectionModelFloors.addListSelectionListener(this);
		
		floorTable.setAutoCreateRowSorter(true);
		
		floorTableScrollPane.setViewportView(floorTable);
		
		lblFloors = new JLabel("FLOORS:");
		lblFloors.setFont(new Font("Tahoma", Font.BOLD, 12));
		plTables.add(lblFloors, "cell 0 5 3 1,grow");
		
		lblRooms = new JLabel("ROOMS:");
		lblRooms.setFont(new Font("Tahoma", Font.BOLD, 12));
		plTables.add(lblRooms, "cell 7 5 9 1,grow");
		
		
		plQuery.addChangeListener(changeListener);
		tableStatus.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				ViewAvailableTable();
				ViewUnavailableTable();
				ViewFloorTable();
			}
		});
		tableTimeSpinner.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				ViewAvailableTable();
				ViewUnavailableTable();
				ViewFloorTable();
			}
		});
		btnTableSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewAvailableTable();
				ViewUnavailableTable();
				ViewFloorTable();
			}
		});
		roomSelect.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				ViewAvailableTable();
				ViewUnavailableTable();
				ViewFloorTable();
			}
		});
		roomSelect.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				ViewAvailableTable();
				ViewUnavailableTable();
				ViewFloorTable();
			}
		});
		tableTimeSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				ViewAvailableTable();
				ViewUnavailableTable();
				ViewFloorTable();
			}
		});
		tableStatus.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				ViewAvailableTable();
				ViewUnavailableTable();
				ViewFloorTable();
			}
		});
		
		foodStatus.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				ViewNormalFood();
            	ViewEventFood();
            	normalFoodId = "0";
            	eventFoodId = "0";
            	ViewNormalSizes();
            	ViewEventSizes();
				normalFoodImagePanel.removeAll();
				eventFoodImagePanel.removeAll();
            	
			}
		});
		
		foodCategory.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				ViewNormalFood();
            	ViewEventFood();
            	normalFoodId = "0";
            	eventFoodId = "0";
            	ViewNormalSizes();
            	ViewEventSizes();
				normalFoodImagePanel.removeAll();
				eventFoodImagePanel.removeAll();
            	
			}
		});
		
		btnFoodSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewNormalFood();
            	ViewEventFood();
            	normalFoodId = "0";
            	eventFoodId = "0";
            	ViewNormalSizes();
            	ViewEventSizes();
				normalFoodImagePanel.removeAll();
				eventFoodImagePanel.removeAll();
			}
		});
		
		
		btnPackageSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewNormalPackage();
				ViewEventPackage();
				normalPackageId = "0";
            	eventPackageId = "0";
            	ViewNormalPackageItems();
            	ViewEventPackageItems();
				normalPackageImagePanel.removeAll();
				eventPackageImagePanel.removeAll();
			}
		});
		
		floorSelect.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				ViewAvailableTable();
	        	 ViewUnavailableTable();
	        	 AddRooms();
			}
		});

		AddFoodCategory();

    	AddFloors();
    
		AddRooms();
		
   
		

	}
	
public JTable getNormalPackageTable() {
		return normalPackageTable;
	}

	public JTable getEventPackageTable() {
		return eventPackageTable;
	}

	public JTable getEventPackageItemsTable() {
		return eventPackageItemsTable;
	}

	public JTable getNormalPackageItemsTable() {
		return normalPackageItemsTable;
	}

public JTable getNormalFoodTable() {
		return normalFoodTable;
	}
	public JTable getEventFoodTable() {
		return eventFoodTable;
	}
	public JTable getEventFoodSizesTable() {
		return eventFoodSizesTable;
	}
	public JTable getNormalFoodSizesTable() {
		return normalFoodSizesTable;
	}

public class BackgroundImageApp extends JPanel {
		
		BufferedImage originalImage;
		BufferedImage scaledImage;

		BackgroundImageApp(File file) {
		    setPreferredSize(new Dimension(320, 200));
		    try {
		        originalImage = ImageIO.read(file);
		    } catch(Exception e){}

		    addComponentListener(new ResizerListener());
		}

		public void resize() {
		    double widthScaleFactor = getWidth() / (double)originalImage.getWidth();
		    double heightScaleFactor = getHeight() / (double)originalImage.getHeight();
		    double scaleFactor = (widthScaleFactor > heightScaleFactor)? heightScaleFactor : widthScaleFactor;

		    AffineTransform at = new AffineTransform();
		    at.scale(scaleFactor, scaleFactor);

		    AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		    scaledImage = scaleOp.filter(originalImage, scaledImage);
		    revalidate();
		    updateUI();
		    repaint();
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			double widthScaleFactor = getWidth() / (double)getWidth();
			double heightScaleFactor = getHeight() / (double)getHeight();
			double scaleFactor = (widthScaleFactor > heightScaleFactor)? heightScaleFactor : widthScaleFactor;

			int width = (int)(getWidth() * scaleFactor);
			int height = (int)(getHeight() * scaleFactor);

			g.drawImage(originalImage, 0, 0, width, height, null);
		}


		class ResizerListener implements ComponentListener {
		    @Override
		    public void componentResized(ComponentEvent e) {
		        resize();
		    }

		    @Override public void componentHidden(ComponentEvent e) {}
		    @Override public void componentMoved(ComponentEvent e) {}
		    @Override public void componentShown(ComponentEvent e) {}
	}
}
	
	public JTable getRoomTable() {
		return roomTable;
	}
	public JTable getUnavailableTable() {
		return unavailableTable;
	}
	public JTable getCustRvTable() {
		return custRvTable;
	}
	public JTable getCustDITable() {
		return custDITable;
	}
	public JTable getCustTOTable() {
		return custTOTable;
	}
	/*public JTable getCustWLTable() {
		return custWLTable;
	}*/
	
	public JTable getAvailableTable() {
		return availableTables;
	}
	public JTable getFoodTable() {
		return normalFoodTable;
	}
	
	public JTable getFloorTable() {
		return floorTable;
	}
	
	public void ViewNormalImage(){
		//SET ICON
		
		try {
			c = new Connect();
			String query = "SELECT * FROM normal_foods WHERE normal_food_id = ? AND normal_food_type = 'Regular'";
			//System.out.println(normalFoodId);
			//System.out.println(roomId);
			c.pst = c.con.prepareStatement(query);
			c.pst.setString(1, (String) normalFoodId);
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			if(c.rs.next())
			{
				destination2 = c.rs.getString("normal_food_photos");
			}
			normalFoodImagePanel.removeAll();
			file = new File(destination2);
			eventPath = file.getAbsoluteFile().getAbsolutePath();
			pnlImage = new BackgroundImageApp(file);
			pnlImage.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			pnlImage.setBorder(new LineBorder(Color.DARK_GRAY));
			pnlImage.setBackground(SystemColor.menu);
			pnlImage.setLayout(new BorderLayout(0, 0));
			normalFoodImagePanel.add(pnlImage, "cell 0 0, grow");
			normalFoodImagePanel.repaint();
			
			c.pst.close();
			c.rs.close();
			c.con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void ViewEventImage(){
		//SET ICON
		try {
  		  c = new Connect();
  		  String query = "SELECT * FROM normal_foods WHERE normal_food_id = ? AND normal_food_type = 'Event'";
  		  //System.out.println(roomId);
  		  c.pst = c.con.prepareStatement(query);
  		  c.pst.setString(1, (String) eventFoodId);
  		  c.pst.execute();
  		  c.rs = c.pst.getResultSet();
  		  if(c.rs.next())
  		  {
  			  destination3 = c.rs.getString("normal_food_photos");
  		  }
  		  eventFoodImagePanel.removeAll();
  		  file = new File(destination3);
  		  eventPath = file.getAbsoluteFile().getAbsolutePath();
  		  pnlImage = new BackgroundImageApp(file);
  		  pnlImage.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
  		  pnlImage.setBorder(new LineBorder(Color.DARK_GRAY));
  		  pnlImage.setBackground(SystemColor.menu);
  		  pnlImage.setLayout(new BorderLayout(0, 0));
  		  eventFoodImagePanel.add(pnlImage, "cell 0 0, grow");
  		  eventFoodImagePanel.repaint();
  		  
  		  c.pst.close();
  		  c.rs.close();
  		  c.con.close();
  	  } catch (SQLException e) {
  		  // TODO Auto-generated catch block
  		  e.printStackTrace();
  	  }
		
		
		
	}
	
	
	public void ViewNormalPackageImage(){
		//SET ICON
		try {
  		  c = new Connect();
  		  String query = "SELECT * FROM normal_packages WHERE normal_package_id = ?";
  		  //System.out.println(roomId);
  		  c.pst = c.con.prepareStatement(query);
  		  c.pst.setString(1, (String) normalPackageId);
  		  c.pst.execute();
  		  c.rs = c.pst.getResultSet();
  		  if(c.rs.next())
  		  {
  			  destination4 = c.rs.getString("normal_package_photos");
  		  }
  		  normalPackageImagePanel.removeAll();
  		  file = new File(destination4);
  		  eventPath = file.getAbsoluteFile().getAbsolutePath();
  		  pnlImage = new BackgroundImageApp(file);
  		  pnlImage.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
  		  pnlImage.setBorder(new LineBorder(Color.DARK_GRAY));
  		  pnlImage.setBackground(SystemColor.menu);
  		  pnlImage.setLayout(new BorderLayout(0, 0));
  		  normalPackageImagePanel.add(pnlImage, "cell 0 0, grow");
  		  normalPackageImagePanel.repaint();
  		  
  		  c.pst.close();
  		  c.rs.close();
  		  c.con.close();
  	  } catch (SQLException e) {
  		  // TODO Auto-generated catch block
  		  e.printStackTrace();
  	  }
		
		
	}
	
	public void ViewEventPackageImage(){
		//SET ICON
		try {
  		  c = new Connect();
  		  String query = "SELECT * FROM normal_packages WHERE normal_package_id = ?";
  		  //System.out.println(roomId);
  		  c.pst = c.con.prepareStatement(query);
  		  c.pst.setString(1, (String) eventPackageId);
  		  c.pst.execute();
  		  c.rs = c.pst.getResultSet();
  		  if(c.rs.next())
  		  {
  			  destination5 = c.rs.getString("normal_package_photos");
  		  }
  		  eventPackageImagePanel.removeAll();
  		  file = new File(destination5);
  		  eventPath = file.getAbsoluteFile().getAbsolutePath();
  		  pnlImage = new BackgroundImageApp(file);
  		  pnlImage.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
  		  pnlImage.setBorder(new LineBorder(Color.DARK_GRAY));
  		  pnlImage.setBackground(SystemColor.menu);
  		  pnlImage.setLayout(new BorderLayout(0, 0));
  		  eventPackageImagePanel.add(pnlImage, "cell 0 0, grow");
  		  eventPackageImagePanel.repaint();
  		  
  		  c.pst.close();
  		  c.rs.close();
  		  c.con.close();
  	  } catch (SQLException e) {
  		  // TODO Auto-generated catch block
  		  e.printStackTrace();
  	  }
		
		
	}
	
	public void ViewFloorTable()
	{
		try {
			
			c = new Connect();
			String query = "SELECT plan_id AS 'FLOOR ID', plan_name AS 'FLOOR NAME', "
					+ "plan_desc AS 'DESCRIPTION', plan_area AS 'FLOOR AREA' FROM floor_plan;";
			c.pst = c.con.prepareStatement(query);
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			getFloorTable().setModel(DbUtils.resultSetToTableModel(c.rs));
			
			c.rs.close();
			c.pst.close();
			c.con.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void showRooms()
	{
		try {
			
			c = new Connect();
			String query = "";
			
				query = "SELECT room_id AS 'ROOM ID', floor_id 'FLOOR ID', section_name 'SECTION', room_name 'ROOM NAME', room_capacity 'CAPACITY'"
						+ " FROM room JOIN section ON room.section_id = section.section_id WHERE floor_id = ?;";
				
				c.pst = c.con.prepareStatement(query);
				c.pst.setString(1,(String) floorId);

				c.pst.execute();
				c.rs = c.pst.getResultSet();
				getRoomTable().setModel(DbUtils.resultSetToTableModel(c.rs));
				
				c.rs.close();
				c.pst.close();
				c.con.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void getReserveId(int tableid)
	{
		try {
			c = new Connect();
			String custDate = (String) sdf.format(tableDateChooser.getDate());
			String custTime = (String) de_tableTimeSpinner.getFormat().format(tableTimeSpinner.getValue());
			
			String query = "SELECT * FROM waiting_desks WHERE (desk_id = ?) AND (desk_date = ?) AND (? BETWEEN desk_time_start AND desk_time_end);";
			c.pst = c.con.prepareStatement(query);
			c.pst.setInt(1, tableid );
			c.pst.setString(2, custDate);
			c.pst.setString(3, custTime);
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			
			if(c.rs.next())
			{
				tableIdOccupied = 1;
				waiting_id = c.rs.getString("waiting_id");
			}
			
			c.pst.close();
			c.rs.close();
			c.con.close();
			
			c = new Connect();
			String query2 = "SELECT * FROM reserve_desks WHERE (desk_id = ?) AND (desk_date = ?) AND (? BETWEEN desk_time_start AND desk_time_end);";
			c.pst = c.con.prepareStatement(query2);
			c.pst.setInt(1, tableid );
			c.pst.setString(2, custDate);
			c.pst.setString(3, custTime);
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			
			if(c.rs.next())
			{
				tableIdOccupied = 2;
				reserve_id = c.rs.getString("reserve_id");
				
			}
			
			c.pst.close();
			c.rs.close();
			c.con.close();
			
			c = new Connect();
			String query3 = "SELECT * FROM guest_dinein_desks WHERE (desk_id = ?) AND (desk_date = ?) AND (? BETWEEN desk_time_start AND desk_time_end);";
			c.pst = c.con.prepareStatement(query3);
			c.pst.setInt(1, tableid );
			c.pst.setString(2, custDate);
			c.pst.setString(3, custTime);
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			
			if(c.rs.next())
			{
				tableIdOccupied = 3;
				dinein_id = c.rs.getString("guest_dinein_id");
			}
			
			c.pst.close();
			c.rs.close();
			c.con.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ViewCustTableReserve()
	{
		try {
			c = new Connect();
			
			String custFromDate = (String) sdf.format(custFromDateChooser.getDate());
			String custToDate = (String) sdf.format(custToDateChooser.getDate());
			String query ="";
			
			if(custSearch != null)
			{
				query ="SELECT reserve.reserve_id AS 'ID', reserve_date AS 'DATE RESERVED', reserve_time AS 'TIME RESERVED', reserve_name AS 'CUSTOMER NAME', "
						+ "reserve_no_of_people AS 'NO. OF PEOPLE', reserve_contact_no AS 'CONTACT NO.', reserve_email_address AS 'EMAIL ADDRESS',"
						+ "reserve_type AS 'TYPE', reserve_status AS 'STATUS'"
						+ "  FROM reserve "
						+ "WHERE (reserve.reserve_id LIKE ? OR "
						+ "reserve_date LIKE ? OR "
						+ "reserve_no_of_people LIKE ? OR "
						+ "reserve_contact_no LIKE ? OR "
						+ "reserve_email_address LIKE ? OR "
						+ "reserve_time LIKE ? OR "
						+ "reserve_name LIKE ? OR "
						+ "reserve_type LIKE ? OR "
						+ "reserve_status LIKE ? ) "
						+ "AND (reserve_date BETWEEN ? AND ?);";

				c.pst = c.con.prepareStatement(query);
				c.pst.setString(1, "%" + custSearch.getText() + "%");
				c.pst.setString(2, "%" + custSearch.getText() + "%");
				c.pst.setString(3, "%" + custSearch.getText() + "%");
				c.pst.setString(4, "%" + custSearch.getText() + "%");
				c.pst.setString(5, "%" + custSearch.getText() + "%");
				c.pst.setString(6, "%" + custSearch.getText() + "%");
				c.pst.setString(7, "%" + custSearch.getText() + "%");
				c.pst.setString(8, "%" + custSearch.getText() + "%");
				c.pst.setString(9, "%" + custSearch.getText() + "%");
				c.pst.setString(10, custFromDate);
				c.pst.setString(11, custToDate);
				c.pst.execute();
				c.rs = c.pst.getResultSet();
				getCustRvTable().setModel(DbUtils.resultSetToTableModel(c.rs));
			}
			else
			{
				query ="SELECT reserve.reserve_id AS 'ID', desk_date AS 'DATE RESERVED', desk_time AS 'TIME RESERVED', reserve_name AS 'CUSTOMER NAME', "
						+ "reserve_no_of_people AS 'NO. OF PEOPLE', reserve_contact_no AS 'CONTACT NO.', reserve_email_address AS 'EMAIL ADDRESS',"
						+ "reserve_type AS 'TYPE', reserve_status AS 'STATUS'"
						+ "  FROM reserve INNER JOIN reserve_desks ON reserve.reserve_id = reserve_desks.reserve_id "
						+ "WHERE (desk_date BETWEEN ? AND ?);";
				
				c.pst = c.con.prepareStatement(query);
				c.pst.setString(1, custFromDate);
				c.pst.setString(2, custToDate);
				c.pst.execute();
				c.rs = c.pst.getResultSet();
				getCustRvTable().setModel(DbUtils.resultSetToTableModel(c.rs));
			}


			
			c.rs.close();
			c.pst.close();
			c.con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ViewCustTableWaiting()
	{
		
		try {
			c = new Connect();
			
			String custFromDate = (String) sdf.format(custFromDateChooser.getDate());
			String custToDate = (String) sdf.format(custToDateChooser.getDate());
			String query ="";
			
			if(custSearch != null)
			{
				query = "SELECT waiting_id AS 'ID', waiting_name AS 'NAME', waiting_no AS 'GUESTS', waiting_date AS 'DATE', waiting_time AS 'TIME' FROM waiting "
						+ "WHERE ("
						+ "waiting_id LIKE ? OR "
						+ "waiting_name LIKE ? OR "
						+ "waiting_no LIKE ? OR "
						+ "waiting_time LIKE ? OR "
						+ "waiting_date LIKE ? ) "
						+ "AND (waiting_date BETWEEN ? AND ?);";
				
				c.pst = c.con.prepareStatement(query);
				c.pst.setString(1, "%" + custSearch.getText() + "%");
				c.pst.setString(2, "%" + custSearch.getText() + "%");
				c.pst.setString(3, "%" + custSearch.getText() + "%");
				c.pst.setString(4, "%" + custSearch.getText() + "%");
				c.pst.setString(5, "%" + custSearch.getText() + "%");
				c.pst.setString(6, custFromDate);
				c.pst.setString(7, custToDate);
				c.pst.execute();
				c.rs = c.pst.getResultSet();
				//getCustWLTable().setModel(DbUtils.resultSetToTableModel(c.rs));
			}
			else
			{
				query = "SELECT waiting_id AS 'ID', waiting_name AS 'NAME', waiting_no AS 'GUESTS', waiting_time AS 'TIME' FROM waiting "
						+ "WHERE (waiting_date BETWEEN ? AND ?);";
				
				c.pst = c.con.prepareStatement(query);
				c.pst.setString(1, custFromDate);
				c.pst.setString(2, custToDate);
				c.pst.execute();
				c.rs = c.pst.getResultSet();
				//getCustWLTable().setModel(DbUtils.resultSetToTableModel(c.rs));
			}
			
			
			
			
			
			
			c.rs.close();
			c.pst.close();
			c.con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void ViewCustTableDinein()
	{
		
		try {
			c = new Connect();
			
			String custFromDate = (String) sdf.format(custFromDateChooser.getDate());
			String custToDate = (String) sdf.format(custToDateChooser.getDate());
			String query ="";
			
			if(custSearch != null)
			{
				query = "SELECT guest_dinein_id AS 'ID', guest_dinein_name AS 'NAME', guest_dinein_date AS 'DATE', guest_dinein_time AS 'TIME' FROM guest_dinein "
						+ "WHERE( "
						+ "guest_dinein_id LIKE ? OR "
						+ "guest_dinein_name LIKE ? OR "
						+ "guest_dinein_date LIKE ? OR "
						+ "guest_dinein_time LIKE ? ) "
						+ "AND (guest_dinein_date BETWEEN ? AND ?);";
				
				c.pst = c.con.prepareStatement(query);
				c.pst.setString(1, "%" + custSearch.getText() + "%");
				c.pst.setString(2, "%" + custSearch.getText() + "%");
				c.pst.setString(3, "%" + custSearch.getText() + "%");
				c.pst.setString(4, "%" + custSearch.getText() + "%");
				c.pst.setString(5, custFromDate);
				c.pst.setString(6, custToDate);
				c.pst.execute();
				c.rs = c.pst.getResultSet();
				getCustDITable().setModel(DbUtils.resultSetToTableModel(c.rs));
				
			}
			else
			{
				query = "SELECT guest_dinein_id AS 'ID', guest_dinein_name AS 'NAME', guest_dinein_date AS 'DATE', guest_dinein_time AS 'TIME' FROM guest_dinein "
						+ "WHERE (guest_dinein_date BETWEEN ? AND ?);";
				
				c.pst = c.con.prepareStatement(query);
				c.pst.setString(1, custFromDate);
				c.pst.setString(2, custToDate);
				c.pst.execute();
				c.rs = c.pst.getResultSet();
				getCustDITable().setModel(DbUtils.resultSetToTableModel(c.rs));
				
			}
			
			
			

			
			c.rs.close();
			c.pst.close();
			c.con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void ViewCustTableTakeout()
	{
		
		try {
			c = new Connect();
			
			String custFromDate = (String) sdf.format(custFromDateChooser.getDate());
			String custToDate = (String) sdf.format(custToDateChooser.getDate());
			String query ="";
			
			if(custSearch != null)
			{
				query = "SELECT guest_takeout_id AS 'ID', guest_takeout_name AS 'NAME', guest_takeout_date AS 'DATE', guest_takeout_time AS 'TIME' FROM guest_takeout "
						+ "WHERE ("
						+ "guest_takeout_id LIKE ? OR "
						+ "guest_takeout_name LIKE ? OR "
						+ "guest_takeout_date LIKE ? OR "
						+ "guest_takeout_time LIKE ? ) "
						+ "AND (guest_takeout_date BETWEEN ? AND ?);";
				
				c.pst = c.con.prepareStatement(query);
				c.pst.setString(1, "%" + custSearch.getText() + "%");
				c.pst.setString(2, "%" + custSearch.getText() + "%");
				c.pst.setString(3, "%" + custSearch.getText() + "%");
				c.pst.setString(4, "%" + custSearch.getText() + "%");
				c.pst.setString(5, custFromDate);
				c.pst.setString(6, custToDate);
				c.pst.execute();
				c.rs = c.pst.getResultSet();
				getCustTOTable().setModel(DbUtils.resultSetToTableModel(c.rs));
				
				
			}
			else
			{
				query = "SELECT guest_takeout_id AS 'ID', guest_takeout_name AS 'NAME', guest_takeout_date AS 'DATE', guest_takeout_time AS 'TIME' FROM guest_takeout "
						+ "WHERE (guest_takeout_date BETWEEN ? AND ?);";
				
				c.pst = c.con.prepareStatement(query);
				c.pst.setString(1, custFromDate);
				c.pst.setString(2, custToDate);
				c.pst.execute();
				c.rs = c.pst.getResultSet();
				getCustTOTable().setModel(DbUtils.resultSetToTableModel(c.rs));
			}
			
			
			

			
			c.rs.close();
			c.pst.close();
			c.con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void ViewAvailableTable()
	{
		
		try 
		{
			String custDate = (String) sdf.format(tableDateChooser.getDate());
			String custTime = (String) de_tableTimeSpinner.getFormat().format(tableTimeSpinner.getValue());
			String roomSelected = (String) roomSelect.getSelectedItem();
			String searchTable =  tableSearch.getText();
			String roomSelectedId = "";
			
			c = new Connect();
			String sql = "SELECT room_id FROM room WHERE room_name = ?;"; // ----------------------------------GET ROOM ID WHERE ROOM NAME = SELECTED ROOM NAME
			c.pst = c.con.prepareStatement(sql);
			c.pst.setString(1, roomSelected);
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			while(c.rs.next())
			{
				roomSelectedId = c.rs.getString(1);
			}
			
			c.pst.close();
			c.rs.close();
			c.con.close();
			
			
			if(floorSelect.getSelectedItem().equals("ALL"))
			{
				//System.out.println("floor "+ floorSelect.getSelectedItem());
				if(roomSelected == "ALL")
				{
					//System.out.println("room "+ roomSelect.getSelectedItem());
					if(tableStatus.getSelectedItem().equals("ALL"))
					{
						//System.out.println("tableStatus "+ tableStatus.getSelectedItem());
						c = new Connect();
						String query = "SELECT desks.desk_desc as 'TABLE NAME', desks.desk_capacity as 'CAPACITY', desks.desk_status as 'STATUS', "
								+ "tables.table_description as 'TABLE TYPE', room.room_name as 'ROOM' "
								+ "FROM desks, tables, room "
								+ "WHERE "
								+ "desks.room_id = room.room_id AND "
								+ "desks.table_id = tables.table_id AND "
								+ "("
								+ "desks.desk_desc LIKE ? OR "
								+ "desks.desk_capacity LIKE ? OR "
								+ "desks.desk_status LIKE ? OR "
								+ "tables.table_description LIKE ? OR "
								+ "room.room_name LIKE ? "
								+ ")"
								+ "AND (desks.desk_id NOT IN (SELECT desk_id FROM reserve_desks WHERE desk_date = ? AND ? BETWEEN desk_time_start AND desk_time_end)) "
								+ "AND (desks.desk_id NOT IN (SELECT desk_id FROM guest_dinein_desks WHERE desk_date = ? AND ? BETWEEN desk_time_start AND desk_time_end)) "
								+ "ORDER BY desks.desk_id;";
								
						c.pst = c.con.prepareStatement(query);
						c.pst.setString(1,"%"+ searchTable +"%");
						c.pst.setString(2,"%"+ searchTable+"%");
						c.pst.setString(3,"%"+ searchTable+"%");
						c.pst.setString(4,"%"+ searchTable+"%");
						c.pst.setString(5,"%"+ searchTable+"%");
						c.pst.setString(6, custDate);
						c.pst.setString(7, custTime);
						c.pst.setString(8, custDate);
						c.pst.setString(9, custTime);
						c.pst.execute();
						c.rs = c.pst.getResultSet();
						getAvailableTable().setModel(DbUtils.resultSetToTableModel(c.rs));
						
						c.rs.close();
						c.pst.close();
						c.con.close();
						
					}
					else
					{
						//System.out.println("tableStatus "+ tableStatus.getSelectedItem());
						c = new Connect();
						String query = "SELECT desks.desk_desc as 'TABLE NAME', desks.desk_capacity as 'CAPACITY', desks.desk_status as 'STATUS', "
								+ "tables.table_description as 'TABLE TYPE', room.room_name as 'ROOM' "
								+ "FROM desks, tables, room "
								+ "WHERE "
								+ "desks.room_id = room.room_id AND "
								+ "desks.table_id = tables.table_id AND "
								+ "desks.desk_status = ? AND "
								+ "("
								+ "desks.desk_desc LIKE ? OR "
								+ "desks.desk_capacity LIKE ? OR "
								+ "tables.table_description LIKE ? OR "
								+ "room.room_name LIKE ? "
								+ ")"
								+ "AND (desks.desk_id NOT IN (SELECT desk_id FROM reserve_desks WHERE desk_date = ? AND ? BETWEEN desk_time_start AND desk_time_end)) "
								+ "AND (desks.desk_id NOT IN (SELECT desk_id FROM guest_dinein_desks WHERE desk_date = ? AND ? BETWEEN desk_time_start AND desk_time_end)) "
								+ "ORDER BY desks.desk_id;";
						
						c.pst = c.con.prepareStatement(query);
						c.pst.setString(1, (String) tableStatus.getSelectedItem());
						c.pst.setString(2,"%"+ searchTable +"%");
						c.pst.setString(3,"%"+ searchTable +"%");
						c.pst.setString(4,"%"+ searchTable+"%");
						c.pst.setString(5,"%"+ searchTable+"%");
						c.pst.setString(6, custDate);
						c.pst.setString(7, custTime);
						c.pst.setString(8, custDate);
						c.pst.setString(9, custTime);

						c.pst.execute();
						c.rs = c.pst.getResultSet();
						getAvailableTable().setModel(DbUtils.resultSetToTableModel(c.rs));
						c.rs.close();
						c.pst.close();
						c.con.close();
					}
				}
				else// ------------------------------ Hindi ALL ang ROOM
				{
					//System.out.println("room "+ roomSelect.getSelectedItem());
					if(tableStatus.getSelectedItem().equals("ALL"))
					{
						//System.out.println("tableStatus "+ tableStatus.getSelectedItem());
						c = new Connect();
						String query = "SELECT desks.desk_desc as 'TABLE NAME', desks.desk_capacity as 'CAPACITY', desks.desk_status as 'STATUS', "
								+ "tables.table_description as 'TABLE TYPE', room.room_name as 'ROOM' "
								+ "FROM desks, tables, room "
								+ "WHERE "
								+ "desks.room_id = room.room_id AND "
								+ "desks.room_id = ? AND "
								+ "desks.table_id = tables.table_id AND "
								+ "("
								+ "desks.desk_desc LIKE ? OR "
								+ "desks.desk_capacity LIKE ? OR "
								+ "desks.desk_status LIKE ? OR "
								+ "tables.table_description LIKE ? "
								+ ")"
								+ "AND (desks.desk_id NOT IN (SELECT desk_id FROM reserve_desks WHERE desk_date = ? AND ? BETWEEN desk_time_start AND desk_time_end)) "
								+ "AND (desks.desk_id NOT IN (SELECT desk_id FROM guest_dinein_desks WHERE desk_date = ? AND ? BETWEEN desk_time_start AND desk_time_end)) "
								+ "ORDER BY desks.desk_id;";
						
						c.pst = c.con.prepareStatement(query);
						c.pst.setString(1,roomSelectedId);
						c.pst.setString(2,"%"+ searchTable +"%");
						c.pst.setString(3,"%"+ searchTable +"%");
						c.pst.setString(4,"%"+ searchTable +"%");
						c.pst.setString(5,"%"+ searchTable +"%");
						c.pst.setString(6, custDate);
						c.pst.setString(7, custTime);
						c.pst.setString(8, custDate);
						c.pst.setString(9, custTime);
						c.pst.execute();
						c.rs = c.pst.getResultSet();
						getAvailableTable().setModel(DbUtils.resultSetToTableModel(c.rs));
						c.rs.close();
						c.pst.close();
						c.con.close();
						
					}
					else
					{
						//System.out.println("tableStatus "+ tableStatus.getSelectedItem());
						c = new Connect();
						String query = "SELECT desks.desk_desc as 'TABLE NAME', desks.desk_capacity as 'CAPACITY', desks.desk_status as 'STATUS', "
								+ "tables.table_description as 'TABLE TYPE', room.room_name as 'ROOM' "
								+ "FROM desks, tables, room "
								+ "WHERE "
								+ "desks.room_id = room.room_id AND "
								+ "desks.room_id = ? AND "
								+ "desks.table_id = tables.table_id AND "
								+ "desks.desk_status = ? AND "
								+ "("
								+ "desks.desk_desc LIKE ? OR "
								+ "desks.desk_capacity LIKE ? OR "
								+ "tables.table_description LIKE ? "
								+ ")"
								+ "AND (desks.desk_id NOT IN (SELECT desk_id FROM reserve_desks WHERE desk_date = ? AND ? BETWEEN desk_time_start AND desk_time_end)) "
								+ "AND (desks.desk_id NOT IN (SELECT desk_id FROM guest_dinein_desks WHERE desk_date = ? AND ? BETWEEN desk_time_start AND desk_time_end)) "
								+ "ORDER BY desks.desk_id;";
						
						c.pst = c.con.prepareStatement(query);
						c.pst.setString(1,roomSelectedId);
						c.pst.setString(2, (String) tableStatus.getSelectedItem());
						c.pst.setString(3,"%"+ searchTable +"%");
						c.pst.setString(4, "%"+ searchTable +"%");
						c.pst.setString(5, "%"+ searchTable +"%");
						c.pst.setString(6, custDate);
						c.pst.setString(7, custTime);
						c.pst.setString(8, custDate);
						c.pst.setString(9, custTime);

						c.pst.execute();
						c.rs = c.pst.getResultSet();
						getAvailableTable().setModel(DbUtils.resultSetToTableModel(c.rs));
						c.rs.close();
						c.pst.close();
						c.con.close();
					}
				}
			}//-----------------------------if floorSelect is not "ALL"
			else
			{
				//System.out.println("floor PASOOOK "+ floorSelect.getSelectedItem());
				
				if(roomSelected.equals("ALL"))
				{
					//System.out.println("room PASOOOK "+ roomSelect.getSelectedItem());
					
					if(tableStatus.getSelectedItem().equals("ALL"))
					{

						//System.out.println();
						
						c = new Connect();
						String query = "SELECT desks.desk_desc as 'TABLE NAME', desks.desk_capacity as 'CAPACITY', desks.desk_status as 'STATUS', "
								+ "tables.table_description as 'TABLE TYPE', room.room_name as 'ROOM' "
								+ "FROM desks, tables, room "
								+ "WHERE "
								+ "desks.room_id = room.room_id AND "
								+ "desks.table_id = tables.table_id AND "
								+ "room.floor_id = ? AND "
								+ "("
								+ "desks.desk_desc LIKE ? OR "
								+ "desks.desk_capacity LIKE ? OR "
								+ "desks.desk_status LIKE ? OR "
								+ "tables.table_description LIKE ? OR "
								+ "room.room_name LIKE ? "
								+ ")"
								+ "AND (desks.desk_id NOT IN (SELECT desk_id FROM reserve_desks WHERE desk_date = ? AND ? BETWEEN desk_time_start AND desk_time_end)) "
								+ "AND (desks.desk_id NOT IN (SELECT desk_id FROM guest_dinein_desks WHERE desk_date = ? AND ? BETWEEN desk_time_start AND desk_time_end)) "
								+ "ORDER BY desks.desk_id;";
								
						c.pst = c.con.prepareStatement(query);
						c.pst.setString(1, (String) floorSelect.getSelectedItem());
						c.pst.setString(2,"%"+ searchTable +"%");
						c.pst.setString(3,"%"+ searchTable+"%");
						c.pst.setString(4,"%"+ searchTable+"%");
						c.pst.setString(5,"%"+ searchTable+"%");
						c.pst.setString(6,"%"+ searchTable+"%");
						c.pst.setString(7, custDate);
						c.pst.setString(8, custTime);
						c.pst.setString(9, custDate);
						c.pst.setString(10, custTime);
						c.pst.execute();
						c.rs = c.pst.getResultSet();
						getAvailableTable().setModel(DbUtils.resultSetToTableModel(c.rs));
						c.rs.close();
						c.pst.close();
						c.con.close();
						
					}
					else// ----------------------- TABLE ACTIVE OR INACTIVE
					{
						//System.out.println("tableStatus "+ tableStatus.getSelectedItem());
						c = new Connect();
						String query = "SELECT desks.desk_desc as 'TABLE NAME', desks.desk_capacity as 'CAPACITY', desks.desk_status as 'STATUS', "
								+ "tables.table_description as 'TABLE TYPE', room.room_name as 'ROOM' "
								+ "FROM desks, tables, room "
								+ "WHERE "
								+ "desks.room_id = room.room_id AND "
								+ "desks.table_id = tables.table_id AND "
								+ "desks.desk_status = ? AND "
								+ "room.floor_id = ? AND "
								+ "("
								+ "desks.desk_desc LIKE ? OR "
								+ "desks.desk_capacity LIKE ? OR "
								+ "tables.table_description LIKE ? OR "
								+ "room.room_name LIKE ? "
								+ ")"
								+ "AND (desks.desk_id NOT IN (SELECT desk_id FROM reserve_desks WHERE desk_date = ? AND ? BETWEEN desk_time_start AND desk_time_end)) "
								+ "AND (desks.desk_id NOT IN (SELECT desk_id FROM guest_dinein_desks WHERE desk_date = ? AND ? BETWEEN desk_time_start AND desk_time_end)) "
								+ "ORDER BY desks.desk_id;";
						
						c.pst = c.con.prepareStatement(query);
						c.pst.setString(1, (String) tableStatus.getSelectedItem());
						c.pst.setString(2, (String) floorSelect.getSelectedItem());
						c.pst.setString(3,"%"+ searchTable +"%");
						c.pst.setString(4,"%"+ searchTable +"%");
						c.pst.setString(5,"%"+ searchTable+"%");
						c.pst.setString(6,"%"+ searchTable+"%");
						c.pst.setString(7, custDate);
						c.pst.setString(8, custTime);
						c.pst.setString(9, custDate);
						c.pst.setString(10, custTime);
						c.pst.execute();
						c.rs = c.pst.getResultSet();
						getAvailableTable().setModel(DbUtils.resultSetToTableModel(c.rs));
						c.rs.close();
						c.pst.close();
						c.con.close();
					}
				}
				else // ------------------------------!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! room not selected as ALL
				{
					//System.out.println("room HINDIII DAPAT"+ roomSelect.getSelectedItem());
					if(tableStatus.getSelectedItem().equals("ALL"))
					{
						//System.out.println("tableStatus HINDIIII DAPAT"+ tableStatus.getSelectedItem());
						c = new Connect();
						String query = "SELECT desks.desk_desc as 'TABLE NAME', desks.desk_capacity as 'CAPACITY', desks.desk_status as 'STATUS', "
								+ "tables.table_description as 'TABLE TYPE', room.room_name as 'ROOM' "
								+ "FROM desks, tables, room "
								+ "WHERE "
								+ "desks.room_id = room.room_id AND "
								+ "desks.room_id = ? AND "
								+ "desks.table_id = tables.table_id AND "
								+ "room.floor_id = ? AND "
								+ "("
								+ "desks.desk_desc LIKE ? OR "
								+ "desks.desk_capacity LIKE ? OR "
								+ "desks.desk_status LIKE ? OR "
								+ "tables.table_description LIKE ? "
								+ ")"
								+ "AND (desks.desk_id NOT IN (SELECT desk_id FROM reserve_desks WHERE desk_date = ? AND ? BETWEEN desk_time_start AND desk_time_end)) "
								+ "AND (desks.desk_id NOT IN (SELECT desk_id FROM guest_dinein_desks WHERE desk_date = ? AND ? BETWEEN desk_time_start AND desk_time_end)) "
								+ "ORDER BY desks.desk_id;";
						
						c.pst = c.con.prepareStatement(query);
						c.pst.setString(1,roomSelectedId);
						c.pst.setString(2, (String) floorSelect.getSelectedItem());
						c.pst.setString(3,"%"+ searchTable +"%");
						c.pst.setString(4,"%"+ searchTable +"%");
						c.pst.setString(5,"%"+ searchTable +"%");
						c.pst.setString(6,"%"+ searchTable +"%");
						c.pst.setString(7, custDate);
						c.pst.setString(8, custTime);
						c.pst.setString(9, custDate);
						c.pst.setString(10, custTime);
						c.pst.execute();
						c.rs = c.pst.getResultSet();
						getAvailableTable().setModel(DbUtils.resultSetToTableModel(c.rs));
						c.rs.close();
						c.pst.close();
						c.con.close();
						
					}
					else
					{
						//System.out.println("tableStatus "+ tableStatus.getSelectedItem());
						c = new Connect();
						String query = "SELECT desks.desk_desc as 'TABLE NAME', desks.desk_capacity as 'CAPACITY', desks.desk_status as 'STATUS', "
								+ "tables.table_description as 'TABLE TYPE', room.room_name as 'ROOM' "
								+ "FROM desks, tables, room "
								+ "WHERE "
								+ "desks.room_id = room.room_id AND "
								+ "desks.room_id = ? AND "
								+ "desks.table_id = tables.table_id AND "
								+ "desks.desk_status = ? AND "
								+ "room.floor_id = ? AND "
								+ "("
								+ "desks.desk_desc LIKE ? OR "
								+ "desks.desk_capacity LIKE ? OR "
								+ "tables.table_description LIKE ? "
								+ ")"
								+ "AND (desks.desk_id NOT IN (SELECT desk_id FROM reserve_desks WHERE desk_date = ? AND ? BETWEEN desk_time_start AND desk_time_end)) "
								+ "AND (desks.desk_id NOT IN (SELECT desk_id FROM guest_dinein_desks WHERE desk_date = ? AND ? BETWEEN desk_time_start AND desk_time_end)) "
								+ "ORDER BY desks.desk_id;";
						
						c.pst = c.con.prepareStatement(query);
						c.pst.setString(1,roomSelectedId);
						c.pst.setString(2, (String) tableStatus.getSelectedItem());
						c.pst.setString(3, (String) floorSelect.getSelectedItem());
						c.pst.setString(4,"%"+ searchTable +"%");
						c.pst.setString(5, "%"+ searchTable +"%");
						c.pst.setString(6, "%"+ searchTable +"%");
						c.pst.setString(7, custDate);
						c.pst.setString(8, custTime);
						c.pst.setString(9, custDate);
						c.pst.setString(10, custTime);
						c.pst.execute();
						c.rs = c.pst.getResultSet();
						getAvailableTable().setModel(DbUtils.resultSetToTableModel(c.rs));
						c.rs.close();
						c.pst.close();
						c.con.close();
					}
				}
			}
			

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void ViewUnavailableTable()
	{
		try 
		{
			String custDate = (String) sdf.format(tableDateChooser.getDate());
			String custTime = (String) de_tableTimeSpinner.getFormat().format(tableTimeSpinner.getValue());
			String roomSelected = (String) roomSelect.getSelectedItem();
			String searchTable =  tableSearch.getText();
			String roomSelectedId = "";
			
			c = new Connect();
			String sql = "SELECT room_id FROM room WHERE room_name = ?;"; // ----------------------------------GET ROOM ID WHERE ROOM NAME = SELECTED ROOM NAME
			c.pst = c.con.prepareStatement(sql);
			c.pst.setString(1, roomSelected);
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			while(c.rs.next())
			{
				roomSelectedId = c.rs.getString(1);
			}
			
			c.pst.close();
			c.rs.close();
			c.con.close();
			
			
			if(floorSelect.getSelectedItem().equals("ALL"))
			{
				//System.out.println("floor "+ floorSelect.getSelectedItem());
				if(roomSelected == "ALL")
				{
					//System.out.println("room "+ roomSelect.getSelectedItem());
					if(tableStatus.getSelectedItem().equals("ALL"))
					{
						//System.out.println("tableStatus "+ tableStatus.getSelectedItem());
						c = new Connect();
						String query = "SELECT desks.desk_desc as 'TABLE NAME', desks.desk_capacity as 'CAPACITY', desks.desk_status as 'STATUS', "
								+ "tables.table_description as 'TABLE TYPE', room.room_name as 'ROOM' "
								+ "FROM desks, tables, room "
								+ "WHERE "
								+ "desks.room_id = room.room_id AND "
								+ "desks.table_id = tables.table_id AND "
								+ "("
								+ "desks.desk_desc LIKE ? OR "
								+ "desks.desk_capacity LIKE ? OR "
								+ "desks.desk_status LIKE ? OR "
								+ "tables.table_description LIKE ? OR "
								+ "room.room_name LIKE ? "
								+ ")"
								+ "AND (desks.desk_id IN (SELECT desk_id FROM reserve_desks WHERE desk_date = ? AND ? BETWEEN desk_time_start AND desk_time_end)) "
								+ "AND (desks.desk_id IN (SELECT desk_id FROM guest_dinein_desks WHERE desk_date = ? AND ? BETWEEN desk_time_start AND desk_time_end)) "
								+ "ORDER BY desks.desk_id;";
								
						c.pst = c.con.prepareStatement(query);
						c.pst.setString(1,"%"+ searchTable +"%");
						c.pst.setString(2,"%"+ searchTable+"%");
						c.pst.setString(3,"%"+ searchTable+"%");
						c.pst.setString(4,"%"+ searchTable+"%");
						c.pst.setString(5,"%"+ searchTable+"%");
						c.pst.setString(6, custDate);
						c.pst.setString(7, custTime);
						c.pst.setString(8, custDate);
						c.pst.setString(9, custTime);
						c.pst.execute();
						c.rs = c.pst.getResultSet();
						getUnavailableTable().setModel(DbUtils.resultSetToTableModel(c.rs));
						
						c.rs.close();
						c.pst.close();
						c.con.close();
						
					}
					else
					{
						//System.out.println("tableStatus "+ tableStatus.getSelectedItem());
						c = new Connect();
						String query = "SELECT desks.desk_desc as 'TABLE NAME', desks.desk_capacity as 'CAPACITY', desks.desk_status as 'STATUS', "
								+ "tables.table_description as 'TABLE TYPE', room.room_name as 'ROOM' "
								+ "FROM desks, tables, room "
								+ "WHERE "
								+ "desks.room_id = room.room_id AND "
								+ "desks.table_id = tables.table_id AND "
								+ "desks.desk_status = ? AND "
								+ "("
								+ "desks.desk_desc LIKE ? OR "
								+ "desks.desk_capacity LIKE ? OR "
								+ "tables.table_description LIKE ? OR "
								+ "room.room_name LIKE ? "
								+ ")"
								+ "AND (desks.desk_id IN (SELECT desk_id FROM reserve_desks WHERE desk_date = ? AND ? BETWEEN desk_time_start AND desk_time_end)) "
								+ "AND (desks.desk_id IN (SELECT desk_id FROM guest_dinein_desks WHERE desk_date = ? AND ? BETWEEN desk_time_start AND desk_time_end)) "
								+ "ORDER BY desks.desk_id;";
						
						c.pst = c.con.prepareStatement(query);
						c.pst.setString(1, (String) tableStatus.getSelectedItem());
						c.pst.setString(2,"%"+ searchTable +"%");
						c.pst.setString(3,"%"+ searchTable +"%");
						c.pst.setString(4,"%"+ searchTable+"%");
						c.pst.setString(5,"%"+ searchTable+"%");
						c.pst.setString(6, custDate);
						c.pst.setString(7, custTime);
						c.pst.setString(8, custDate);
						c.pst.setString(9, custTime);

						c.pst.execute();
						c.rs = c.pst.getResultSet();
						getUnavailableTable().setModel(DbUtils.resultSetToTableModel(c.rs));
						c.rs.close();
						c.pst.close();
						c.con.close();
					}
				}
				else// ------------------------------ Hindi ALL ang ROOM
				{
					//System.out.println("room "+ roomSelect.getSelectedItem());
					if(tableStatus.getSelectedItem().equals("ALL"))
					{
						//System.out.println("tableStatus "+ tableStatus.getSelectedItem());
						c = new Connect();
						String query = "SELECT desks.desk_desc as 'TABLE NAME', desks.desk_capacity as 'CAPACITY', desks.desk_status as 'STATUS', "
								+ "tables.table_description as 'TABLE TYPE', room.room_name as 'ROOM' "
								+ "FROM desks, tables, room "
								+ "WHERE "
								+ "desks.room_id = room.room_id AND "
								+ "desks.room_id = ? AND "
								+ "desks.table_id = tables.table_id AND "
								+ "("
								+ "desks.desk_desc LIKE ? OR "
								+ "desks.desk_capacity LIKE ? OR "
								+ "desks.desk_status LIKE ? OR "
								+ "tables.table_description LIKE ? "
								+ ")"
								+ "AND (desks.desk_id IN (SELECT desk_id FROM reserve_desks WHERE desk_date = ? AND ? BETWEEN desk_time_start AND desk_time_end)) "
								+ "AND (desks.desk_id IN (SELECT desk_id FROM guest_dinein_desks WHERE desk_date = ? AND ? BETWEEN desk_time_start AND desk_time_end)) "
								+ "ORDER BY desks.desk_id;";
						
						c.pst = c.con.prepareStatement(query);
						c.pst.setString(1,roomSelectedId);
						c.pst.setString(2,"%"+ searchTable +"%");
						c.pst.setString(3,"%"+ searchTable +"%");
						c.pst.setString(4,"%"+ searchTable +"%");
						c.pst.setString(5,"%"+ searchTable +"%");
						c.pst.setString(6, custDate);
						c.pst.setString(7, custTime);
						c.pst.setString(8, custDate);
						c.pst.setString(9, custTime);
						c.pst.execute();
						c.rs = c.pst.getResultSet();
						getUnavailableTable().setModel(DbUtils.resultSetToTableModel(c.rs));
						c.rs.close();
						c.pst.close();
						c.con.close();
						
					}
					else
					{
						//System.out.println("tableStatus "+ tableStatus.getSelectedItem());
						c = new Connect();
						String query = "SELECT desks.desk_desc as 'TABLE NAME', desks.desk_capacity as 'CAPACITY', desks.desk_status as 'STATUS', "
								+ "tables.table_description as 'TABLE TYPE', room.room_name as 'ROOM' "
								+ "FROM desks, tables, room "
								+ "WHERE "
								+ "desks.room_id = room.room_id AND "
								+ "desks.room_id = ? AND "
								+ "desks.table_id = tables.table_id AND "
								+ "desks.desk_status = ? AND "
								+ "("
								+ "desks.desk_desc LIKE ? OR "
								+ "desks.desk_capacity LIKE ? OR "
								+ "tables.table_description LIKE ? "
								+ ")"
								+ "AND (desks.desk_id IN (SELECT desk_id FROM reserve_desks WHERE desk_date = ? AND ? BETWEEN desk_time_start AND desk_time_end)) "
								+ "AND (desks.desk_id IN (SELECT desk_id FROM guest_dinein_desks WHERE desk_date = ? AND ? BETWEEN desk_time_start AND desk_time_end)) "
								+ "ORDER BY desks.desk_id;";
						
						c.pst = c.con.prepareStatement(query);
						c.pst.setString(1,roomSelectedId);
						c.pst.setString(2, (String) tableStatus.getSelectedItem());
						c.pst.setString(3,"%"+ searchTable +"%");
						c.pst.setString(4, "%"+ searchTable +"%");
						c.pst.setString(5, "%"+ searchTable +"%");
						c.pst.setString(6, custDate);
						c.pst.setString(7, custTime);
						c.pst.setString(8, custDate);
						c.pst.setString(9, custTime);

						c.pst.execute();
						c.rs = c.pst.getResultSet();
						getUnavailableTable().setModel(DbUtils.resultSetToTableModel(c.rs));
						c.rs.close();
						c.pst.close();
						c.con.close();
					}
				}
			}//-----------------------------if floorSelect is not "ALL"
			else
			{
				//System.out.println("floor PASOOOK "+ floorSelect.getSelectedItem());
				
				if(roomSelected.equals("ALL"))
				{
					//System.out.println("room PASOOOK "+ roomSelect.getSelectedItem());
					
					if(tableStatus.getSelectedItem().equals("ALL"))
					{

						//System.out.println();
						
						c = new Connect();
						String query = "SELECT desks.desk_desc as 'TABLE NAME', desks.desk_capacity as 'CAPACITY', desks.desk_status as 'STATUS', "
								+ "tables.table_description as 'TABLE TYPE', room.room_name as 'ROOM' "
								+ "FROM desks, tables, room "
								+ "WHERE "
								+ "desks.room_id = room.room_id AND "
								+ "desks.table_id = tables.table_id AND "
								+ "room.floor_id = ? AND "
								+ "("
								+ "desks.desk_desc LIKE ? OR "
								+ "desks.desk_capacity LIKE ? OR "
								+ "desks.desk_status LIKE ? OR "
								+ "tables.table_description LIKE ? OR "
								+ "room.room_name LIKE ? "
								+ ")"
								+ "AND (desks.desk_id IN (SELECT desk_id FROM reserve_desks WHERE desk_date = ? AND ? BETWEEN desk_time_start AND desk_time_end)) "
								+ "AND (desks.desk_id IN (SELECT desk_id FROM guest_dinein_desks WHERE desk_date = ? AND ? BETWEEN desk_time_start AND desk_time_end)) "
								+ "ORDER BY desks.desk_id;";
								
						c.pst = c.con.prepareStatement(query);
						c.pst.setString(1, (String) floorSelect.getSelectedItem());
						c.pst.setString(2,"%"+ searchTable +"%");
						c.pst.setString(3,"%"+ searchTable+"%");
						c.pst.setString(4,"%"+ searchTable+"%");
						c.pst.setString(5,"%"+ searchTable+"%");
						c.pst.setString(6,"%"+ searchTable+"%");
						c.pst.setString(7, custDate);
						c.pst.setString(8, custTime);
						c.pst.setString(9, custDate);
						c.pst.setString(10, custTime);
						c.pst.execute();
						c.rs = c.pst.getResultSet();
						getUnavailableTable().setModel(DbUtils.resultSetToTableModel(c.rs));
						c.rs.close();
						c.pst.close();
						c.con.close();
						
					}
					else// ----------------------- TABLE ACTIVE OR INACTIVE
					{
						//System.out.println("tableStatus "+ tableStatus.getSelectedItem());
						c = new Connect();
						String query = "SELECT desks.desk_desc as 'TABLE NAME', desks.desk_capacity as 'CAPACITY', desks.desk_status as 'STATUS', "
								+ "tables.table_description as 'TABLE TYPE', room.room_name as 'ROOM' "
								+ "FROM desks, tables, room "
								+ "WHERE "
								+ "desks.room_id = room.room_id AND "
								+ "desks.table_id = tables.table_id AND "
								+ "desks.desk_status = ? AND "
								+ "room.floor_id = ? AND "
								+ "("
								+ "desks.desk_desc LIKE ? OR "
								+ "desks.desk_capacity LIKE ? OR "
								+ "tables.table_description LIKE ? OR "
								+ "room.room_name LIKE ? "
								+ ")"
								+ "AND (desks.desk_id IN (SELECT desk_id FROM reserve_desks WHERE desk_date = ? AND ? BETWEEN desk_time_start AND desk_time_end)) "
								+ "AND (desks.desk_id IN (SELECT desk_id FROM guest_dinein_desks WHERE desk_date = ? AND ? BETWEEN desk_time_start AND desk_time_end)) "
								+ "ORDER BY desks.desk_id;";
						
						c.pst = c.con.prepareStatement(query);
						c.pst.setString(1, (String) tableStatus.getSelectedItem());
						c.pst.setString(2, (String) floorSelect.getSelectedItem());
						c.pst.setString(3,"%"+ searchTable +"%");
						c.pst.setString(4,"%"+ searchTable +"%");
						c.pst.setString(5,"%"+ searchTable+"%");
						c.pst.setString(6,"%"+ searchTable+"%");
						c.pst.setString(7, custDate);
						c.pst.setString(8, custTime);
						c.pst.setString(9, custDate);
						c.pst.setString(10, custTime);
						c.pst.execute();
						c.rs = c.pst.getResultSet();
						getUnavailableTable().setModel(DbUtils.resultSetToTableModel(c.rs));
						c.rs.close();
						c.pst.close();
						c.con.close();
					}
				}
				else // ------------------------------!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! room not selected as ALL
				{
					//System.out.println("room HINDIII DAPAT"+ roomSelect.getSelectedItem());
					if(tableStatus.getSelectedItem().equals("ALL"))
					{
						//System.out.println("tableStatus HINDIIII DAPAT"+ tableStatus.getSelectedItem());
						c = new Connect();
						String query = "SELECT desks.desk_desc as 'TABLE NAME', desks.desk_capacity as 'CAPACITY', desks.desk_status as 'STATUS', "
								+ "tables.table_description as 'TABLE TYPE', room.room_name as 'ROOM' "
								+ "FROM desks, tables, room "
								+ "WHERE "
								+ "desks.room_id = room.room_id AND "
								+ "desks.room_id = ? AND "
								+ "desks.table_id = tables.table_id AND "
								+ "room.floor_id = ? AND "
								+ "("
								+ "desks.desk_desc LIKE ? OR "
								+ "desks.desk_capacity LIKE ? OR "
								+ "desks.desk_status LIKE ? OR "
								+ "tables.table_description LIKE ? "
								+ ")"
								+ "AND (desks.desk_id IN (SELECT desk_id FROM reserve_desks WHERE desk_date = ? AND ? BETWEEN desk_time_start AND desk_time_end)) "
								+ "AND (desks.desk_id IN (SELECT desk_id FROM guest_dinein_desks WHERE desk_date = ? AND ? BETWEEN desk_time_start AND desk_time_end)) "
								+ "ORDER BY desks.desk_id;";
						
						c.pst = c.con.prepareStatement(query);
						c.pst.setString(1,roomSelectedId);
						c.pst.setString(2, (String) floorSelect.getSelectedItem());
						c.pst.setString(3,"%"+ searchTable +"%");
						c.pst.setString(4,"%"+ searchTable +"%");
						c.pst.setString(5,"%"+ searchTable +"%");
						c.pst.setString(6,"%"+ searchTable +"%");
						c.pst.setString(7, custDate);
						c.pst.setString(8, custTime);
						c.pst.setString(9, custDate);
						c.pst.setString(10, custTime);
						c.pst.execute();
						c.rs = c.pst.getResultSet();
						getUnavailableTable().setModel(DbUtils.resultSetToTableModel(c.rs));
						c.rs.close();
						c.pst.close();
						c.con.close();
						
					}
					else
					{
						//System.out.println("tableStatus "+ tableStatus.getSelectedItem());
						c = new Connect();
						String query = "SELECT desks.desk_desc as 'TABLE NAME', desks.desk_capacity as 'CAPACITY', desks.desk_status as 'STATUS', "
								+ "tables.table_description as 'TABLE TYPE', room.room_name as 'ROOM' "
								+ "FROM desks, tables, room "
								+ "WHERE "
								+ "desks.room_id = room.room_id AND "
								+ "desks.room_id = ? AND "
								+ "desks.table_id = tables.table_id AND "
								+ "desks.desk_status = ? AND "
								+ "room.floor_id = ? AND "
								+ "("
								+ "desks.desk_desc LIKE ? OR "
								+ "desks.desk_capacity LIKE ? OR "
								+ "tables.table_description LIKE ? "
								+ ")"
								+ "AND (desks.desk_id IN (SELECT desk_id FROM reserve_desks WHERE desk_date = ? AND ? BETWEEN desk_time_start AND desk_time_end)) "
								+ "AND (desks.desk_id IN (SELECT desk_id FROM guest_dinein_desks WHERE desk_date = ? AND ? BETWEEN desk_time_start AND desk_time_end)) "
								+ "ORDER BY desks.desk_id;";
						
						c.pst = c.con.prepareStatement(query);
						c.pst.setString(1,roomSelectedId);
						c.pst.setString(2, (String) tableStatus.getSelectedItem());
						c.pst.setString(3, (String) floorSelect.getSelectedItem());
						c.pst.setString(4,"%"+ searchTable +"%");
						c.pst.setString(5, "%"+ searchTable +"%");
						c.pst.setString(6, "%"+ searchTable +"%");
						c.pst.setString(7, custDate);
						c.pst.setString(8, custTime);
						c.pst.setString(9, custDate);
						c.pst.setString(10, custTime);
						c.pst.execute();
						c.rs = c.pst.getResultSet();
						getUnavailableTable().setModel(DbUtils.resultSetToTableModel(c.rs));
						c.rs.close();
						c.pst.close();
						c.con.close();
					}
				}
			}
			

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void AddFloors()
	{
		try {
			c = new Connect();
			ArrayList arrayFloors = new ArrayList<String>();
			String query = "SELECT * FROM floor_plan";
			c.pst = c.con.prepareStatement(query);
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			arrayFloors.add("ALL");
			while(c.rs.next())
			{
				arrayFloors.add(c.rs.getString("plan_id"));
				
			}
			DefaultComboBoxModel<String> floors = new DefaultComboBoxModel(arrayFloors.toArray());
			floorSelect.setModel(floors);
			
			c.rs.close();
			c.pst.close();
			c.con.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void AddRooms()
	{
		String chosenFloor = (String) floorSelect.getSelectedItem();
		ArrayList arrayRooms = new ArrayList<String>();
		try {
			
			c = new Connect();
			String query="";
			
			if(chosenFloor == "ALL")
			{
				query = "SELECT * FROM room;";
				c.pst = c.con.prepareStatement(query);
				c.pst.execute();
				c.rs = c.pst.getResultSet();
				arrayRooms.add("ALL");
				while(c.rs.next())
				{
					arrayRooms.add(c.rs.getString("room_name"));
				}
			}
			else
			{
				query = "SELECT * FROM room WHERE floor_id = ?";
				c.pst = c.con.prepareStatement(query);
				c.pst.setString(1, chosenFloor);
				c.pst.execute();
				c.rs = c.pst.getResultSet();
				arrayRooms.add("ALL");
				while(c.rs.next())
				{
					arrayRooms.add(c.rs.getString("room_name"));
				}
				
			}

			
			c.rs.close();
			c.pst.close();
			c.con.close();
			
			DefaultComboBoxModel<String> rooms = new DefaultComboBoxModel(arrayRooms.toArray());
			roomSelect.setModel(rooms);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void AddFoodCategory()
	{
		try {
			c = new Connect();
			ArrayList arrayCategory = new ArrayList<String>();
			String query = "SELECT * FROM categories";
			c.pst = c.con.prepareStatement(query);
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			arrayCategory.add("ALL");
			while(c.rs.next())
			{
				arrayCategory.add(c.rs.getObject("category_name"));
			}
			DefaultComboBoxModel<String> category = new DefaultComboBoxModel(arrayCategory.toArray());
			foodCategory.setModel(category);
			
			c.rs.close();
			c.pst.close();
			c.con.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ViewNormalFood()
	{
		try {
			
			if(foodStatus.getSelectedItem().equals("ACTIVE"))
			{
				if(foodCategory.getSelectedItem().equals("ALL"))
				{
					c = new Connect();
					String query = "SELECT normal_food_id AS 'ID', normal_food_name AS 'FOOD NAME', category_name AS 'CATEGORY', normal_food_status AS 'STATUS' "
							+ "FROM normal_foods JOIN categories ON normal_category_id = category_id "
							+ "WHERE (normal_food_id LIKE ? OR normal_food_name LIKE ? OR category_name LIKE ? OR normal_food_status LIKE ?) "
							+ "AND (normal_food_status = 'ACTIVE') AND (normal_food_type = 'Regular');";
					c.pst = c.con.prepareStatement(query);
					c.pst.setString(1, "%" + foodSearch.getText() + "%");
					c.pst.setString(2, "%" + foodSearch.getText() + "%");
					c.pst.setString(3, "%" + foodSearch.getText() + "%");
					c.pst.setString(4, "%" + foodSearch.getText() + "%");
					c.pst.execute();
					c.rs = c.pst.getResultSet();
					getNormalFoodTable().setModel(DbUtils.resultSetToTableModel(c.rs));
					
					
					c.rs.close();
					c.pst.close();
					c.con.close();
				}
				else
				{
					c = new Connect();
					String query = "SELECT normal_food_id AS 'ID', normal_food_name AS 'FOOD NAME', category_name AS 'CATEGORY', normal_food_status AS 'STATUS' "
							+ "FROM normal_foods JOIN categories ON normal_category_id = category_id "
							+ "WHERE (normal_food_id LIKE ? OR normal_food_name LIKE ? OR category_name LIKE ? OR normal_food_status LIKE ?) "
							+ "AND (normal_food_status = 'ACTIVE') "
							+ "AND (category_name = ?) AND (normal_food_type = 'Regular');";
					c.pst = c.con.prepareStatement(query);
					c.pst.setString(1, "%" + foodSearch.getText() + "%");
					c.pst.setString(2, "%" + foodSearch.getText() + "%");
					c.pst.setString(3, "%" + foodSearch.getText() + "%");
					c.pst.setString(4, "%" + foodSearch.getText() + "%");
					c.pst.setString(5, (String) foodCategory.getSelectedItem());
					c.pst.execute();
					c.rs = c.pst.getResultSet();
					getNormalFoodTable().setModel(DbUtils.resultSetToTableModel(c.rs));
					
					
					c.rs.close();
					c.pst.close();
					c.con.close();
				}
			}
			else if(foodStatus.getSelectedItem().equals("INACTIVE"))
			{
				if(foodCategory.getSelectedItem().equals("ALL"))
				{
					c = new Connect();
					String query = "SELECT normal_food_id AS 'ID', normal_food_name AS 'FOOD NAME', category_name AS 'CATEGORY', normal_food_status AS 'STATUS' "
							+ "FROM normal_foods JOIN categories ON normal_category_id = category_id "
							+ "WHERE (normal_food_id LIKE ? OR normal_food_name LIKE ? OR category_name LIKE ? OR normal_food_status LIKE ?) "
							+ "AND (normal_food_status = 'INACTIVE') AND (normal_food_type = 'Regular');";
					c.pst = c.con.prepareStatement(query);
					c.pst.setString(1, "%" + foodSearch.getText() + "%");
					c.pst.setString(2, "%" + foodSearch.getText() + "%");
					c.pst.setString(3, "%" + foodSearch.getText() + "%");
					c.pst.setString(4, "%" + foodSearch.getText() + "%");
					c.pst.execute();
					c.rs = c.pst.getResultSet();
					getNormalFoodTable().setModel(DbUtils.resultSetToTableModel(c.rs));
					
					
					c.rs.close();
					c.pst.close();
					c.con.close();
				}
				else
				{
					c = new Connect();
					String query = "SELECT normal_food_id AS 'ID', normal_food_name AS 'FOOD NAME', category_name AS 'CATEGORY', normal_food_status AS 'STATUS' "
							+ "FROM normal_foods JOIN categories ON normal_category_id = category_id "
							+ "WHERE (normal_food_id LIKE ? OR normal_food_name LIKE ? OR category_name LIKE ? OR normal_food_status LIKE ?) "
							+ "AND (normal_food_status = 'INACTIVE') AND (normal_food_type = 'Regular') "
							+ "AND (category_name = ?);";
					c.pst = c.con.prepareStatement(query);
					c.pst.setString(1, "%" + foodSearch.getText() + "%");
					c.pst.setString(2, "%" + foodSearch.getText() + "%");
					c.pst.setString(3, "%" + foodSearch.getText() + "%");
					c.pst.setString(4, "%" + foodSearch.getText() + "%");
					c.pst.setString(5, (String) foodCategory.getSelectedItem());
					c.pst.execute();
					c.rs = c.pst.getResultSet();
					getNormalFoodTable().setModel(DbUtils.resultSetToTableModel(c.rs));
					
					
					c.rs.close();
					c.pst.close();
					c.con.close();
				}
			}
			else
			{
				if(foodCategory.getSelectedItem().equals("ALL"))
				{
					c = new Connect();
					String query = "SELECT normal_food_id AS 'ID', normal_food_name AS 'FOOD NAME', category_name AS 'CATEGORY', normal_food_status AS 'STATUS' "
							+ "FROM normal_foods JOIN categories ON normal_category_id = category_id "
							+ "WHERE (normal_food_id LIKE ? OR normal_food_name LIKE ? OR category_name LIKE ? OR normal_food_status LIKE ?) AND (normal_food_type = 'Regular'); ";
					c.pst = c.con.prepareStatement(query);
					c.pst.setString(1, "%" + foodSearch.getText() + "%");
					c.pst.setString(2, "%" + foodSearch.getText() + "%");
					c.pst.setString(3, "%" + foodSearch.getText() + "%");
					c.pst.setString(4, "%" + foodSearch.getText() + "%");
					c.pst.execute();
					c.rs = c.pst.getResultSet();
					getNormalFoodTable().setModel(DbUtils.resultSetToTableModel(c.rs));
					
					
					c.rs.close();
					c.pst.close();
					c.con.close();
				}
				else
				{
					c = new Connect();
					String query = "SELECT normal_food_id AS 'ID', normal_food_name AS 'FOOD NAME', category_name AS 'CATEGORY', normal_food_status AS 'STATUS' "
							+ "FROM normal_foods JOIN categories ON normal_category_id = category_id "
							+ "WHERE (normal_food_id LIKE ? OR normal_food_name LIKE ? OR category_name LIKE ? OR normal_food_status LIKE ?) "
							+ "AND (category_name = ?) AND (normal_food_type = 'Regular');";
					c.pst = c.con.prepareStatement(query);
					c.pst.setString(1, "%" + foodSearch.getText() + "%");
					c.pst.setString(2, "%" + foodSearch.getText() + "%");
					c.pst.setString(3, "%" + foodSearch.getText() + "%");
					c.pst.setString(4, "%" + foodSearch.getText() + "%");
					c.pst.setString(5, (String) foodCategory.getSelectedItem());
					c.pst.execute();
					c.rs = c.pst.getResultSet();
					getNormalFoodTable().setModel(DbUtils.resultSetToTableModel(c.rs));
					
					
					c.rs.close();
					c.pst.close();
					c.con.close();
				}
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ViewEventFood()
	{
		try {
			
			if(foodStatus.getSelectedItem().equals("ACTIVE"))
			{
				if(foodCategory.getSelectedItem().equals("ALL"))
				{
					c = new Connect();
					String query = "SELECT normal_food_id AS 'ID', normal_food_name AS 'FOOD NAME', category_name AS 'CATEGORY', normal_food_status AS 'STATUS' "
							+ "FROM normal_foods JOIN categories ON normal_category_id = category_id "
							+ "WHERE (normal_food_id LIKE ? OR normal_food_name LIKE ? OR category_name LIKE ? OR normal_food_status LIKE ?) "
							+ "AND (normal_food_status = 'ACTIVE') AND (normal_food_type = 'Event');";
					c.pst = c.con.prepareStatement(query);
					c.pst.setString(1, "%" + foodSearch.getText() + "%");
					c.pst.setString(2, "%" + foodSearch.getText() + "%");
					c.pst.setString(3, "%" + foodSearch.getText() + "%");
					c.pst.setString(4, "%" + foodSearch.getText() + "%");
					c.pst.execute();
					c.rs = c.pst.getResultSet();
					getEventFoodTable().setModel(DbUtils.resultSetToTableModel(c.rs));
					
					
					c.rs.close();
					c.pst.close();
					c.con.close();
				}
				else
				{
					c = new Connect();
					String query = "SELECT normal_food_id AS 'ID', normal_food_name AS 'FOOD NAME', category_name AS 'CATEGORY', normal_food_status AS 'STATUS' "
							+ "FROM normal_foods JOIN categories ON normal_category_id = category_id "
							+ "WHERE (normal_food_id LIKE ? OR normal_food_name LIKE ? OR category_name LIKE ? OR normal_food_status LIKE ?) "
							+ "AND (normal_food_status = 'ACTIVE') "
							+ "AND (category_name = ?) AND (normal_food_type = 'Event');";
					c.pst = c.con.prepareStatement(query);
					c.pst.setString(1, "%" + foodSearch.getText() + "%");
					c.pst.setString(2, "%" + foodSearch.getText() + "%");
					c.pst.setString(3, "%" + foodSearch.getText() + "%");
					c.pst.setString(4, "%" + foodSearch.getText() + "%");
					c.pst.setString(5, (String) foodCategory.getSelectedItem());
					c.pst.execute();
					c.rs = c.pst.getResultSet();
					getEventFoodTable().setModel(DbUtils.resultSetToTableModel(c.rs));
					
					
					c.rs.close();
					c.pst.close();
					c.con.close();
				}
			}
			else if(foodStatus.getSelectedItem().equals("INACTIVE"))
			{
				if(foodCategory.getSelectedItem().equals("ALL"))
				{
					c = new Connect();
					String query = "SELECT normal_food_id AS 'ID', normal_food_name AS 'FOOD NAME', category_name AS 'CATEGORY', normal_food_status AS 'STATUS' "
							+ "FROM normal_foods JOIN categories ON normal_category_id = category_id "
							+ "WHERE (normal_food_id LIKE ? OR normal_food_name LIKE ? OR category_name LIKE ? OR normal_food_status LIKE ?) "
							+ "AND (normal_food_status = 'INACTIVE') AND (normal_food_type = 'Event');";
					c.pst = c.con.prepareStatement(query);
					c.pst.setString(1, "%" + foodSearch.getText() + "%");
					c.pst.setString(2, "%" + foodSearch.getText() + "%");
					c.pst.setString(3, "%" + foodSearch.getText() + "%");
					c.pst.setString(4, "%" + foodSearch.getText() + "%");
					c.pst.execute();
					c.rs = c.pst.getResultSet();
					getEventFoodTable().setModel(DbUtils.resultSetToTableModel(c.rs));
					
					
					c.rs.close();
					c.pst.close();
					c.con.close();
				}
				else
				{
					c = new Connect();
					String query = "SELECT normal_food_id AS 'ID', normal_food_name AS 'FOOD NAME', category_name AS 'CATEGORY', normal_food_status AS 'STATUS' "
							+ "FROM normal_foods JOIN categories ON normal_category_id = category_id "
							+ "WHERE (normal_food_id LIKE ? OR normal_food_name LIKE ? OR category_name LIKE ? OR normal_food_status LIKE ?) "
							+ "AND (normal_food_status = 'INACTIVE') AND (normal_food_type = 'Event') "
							+ "AND (category_name = ?);";
					c.pst = c.con.prepareStatement(query);
					c.pst.setString(1, "%" + foodSearch.getText() + "%");
					c.pst.setString(2, "%" + foodSearch.getText() + "%");
					c.pst.setString(3, "%" + foodSearch.getText() + "%");
					c.pst.setString(4, "%" + foodSearch.getText() + "%");
					c.pst.setString(5, (String) foodCategory.getSelectedItem());
					c.pst.execute();
					c.rs = c.pst.getResultSet();
					getEventFoodTable().setModel(DbUtils.resultSetToTableModel(c.rs));
					
					
					c.rs.close();
					c.pst.close();
					c.con.close();
				}
			}
			else
			{
				if(foodCategory.getSelectedItem().equals("ALL"))
				{
					c = new Connect();
					String query = "SELECT normal_food_id AS 'ID', normal_food_name AS 'FOOD NAME', category_name AS 'CATEGORY', normal_food_status AS 'STATUS' "
							+ "FROM normal_foods JOIN categories ON normal_category_id = category_id "
							+ "WHERE (normal_food_id LIKE ? OR normal_food_name LIKE ? OR category_name LIKE ? OR normal_food_status LIKE ?) AND (normal_food_type = 'Event'); ";
					c.pst = c.con.prepareStatement(query);
					c.pst.setString(1, "%" + foodSearch.getText() + "%");
					c.pst.setString(2, "%" + foodSearch.getText() + "%");
					c.pst.setString(3, "%" + foodSearch.getText() + "%");
					c.pst.setString(4, "%" + foodSearch.getText() + "%");
					c.pst.execute();
					c.rs = c.pst.getResultSet();
					getEventFoodTable().setModel(DbUtils.resultSetToTableModel(c.rs));
					
					
					c.rs.close();
					c.pst.close();
					c.con.close();
				}
				else
				{
					c = new Connect();
					String query = "SELECT normal_food_id AS 'ID', normal_food_name AS 'FOOD NAME', category_name AS 'CATEGORY', normal_food_status AS 'STATUS' "
							+ "FROM normal_foods JOIN categories ON normal_category_id = category_id "
							+ "WHERE (normal_food_id LIKE ? OR normal_food_name LIKE ? OR category_name LIKE ? OR normal_food_status LIKE ?) "
							+ "AND (category_name = ?) AND (normal_food_type = 'Event');";
					c.pst = c.con.prepareStatement(query);
					c.pst.setString(1, "%" + foodSearch.getText() + "%");
					c.pst.setString(2, "%" + foodSearch.getText() + "%");
					c.pst.setString(3, "%" + foodSearch.getText() + "%");
					c.pst.setString(4, "%" + foodSearch.getText() + "%");
					c.pst.setString(5, (String) foodCategory.getSelectedItem());
					c.pst.execute();
					c.rs = c.pst.getResultSet();
					getEventFoodTable().setModel(DbUtils.resultSetToTableModel(c.rs));
					
					
					c.rs.close();
					c.pst.close();
					c.con.close();
				}
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ViewNormalSizes()
	{
		try {
			c = new Connect();
			//System.out.println("NORMAL SIZES");
			String query = "SELECT normal_food_size AS 'FOOD SIZE', normal_food_description AS 'DESCRIPTION', normal_food_price AS 'PRICE' FROM normal_food_sizes "
					+ "WHERE normal_food_id = ?";
			c.pst = c.con.prepareStatement(query);
			c.pst.setString(1, (String) normalFoodId);
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			
			getNormalFoodSizesTable().setModel(DbUtils.resultSetToTableModel(c.rs));
			
			c.rs.close();
			c.pst.close();
			c.con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ViewEventSizes()
	{
		try {
			c = new Connect();
			//System.out.println("EVENT SIZES");
			String query = "SELECT normal_food_size AS 'FOOD SIZE', normal_food_description AS 'DESCRIPTION', normal_food_price AS 'PRICE' FROM normal_food_sizes "
					+ "WHERE normal_food_id = ?";
			c.pst = c.con.prepareStatement(query);
			c.pst.setString(1, (String) eventFoodId);
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			
			getEventFoodSizesTable().setModel(DbUtils.resultSetToTableModel(c.rs));
			
			c.rs.close();
			c.pst.close();
			c.con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ViewEventPackageItems()
	{
		try {
			c = new Connect();
			//System.out.println("EVENT ITEMS");
			String query = "SELECT normal_food_id AS 'FOOD ID', normal_food_name AS 'FOOD NAME', normal_food_size AS 'SIZE',"
					+ "normal_food_quantity AS 'QTY', normal_food_price AS 'PRICE' "
					+ "FROM normal_food_package "
					+ "WHERE normal_food_package_id = ? ";
			c.pst = c.con.prepareStatement(query);
			c.pst.setString(1, (String) eventPackageId);
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			
			getEventPackageItemsTable().setModel(DbUtils.resultSetToTableModel(c.rs));
			
			c.rs.close();
			c.pst.close();
			c.con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ViewNormalPackageItems()
	{
		try {
			c = new Connect();
			//System.out.println("NORMAL ITEMS");
			String query = "SELECT normal_food_id AS 'FOOD ID', normal_food_name AS 'FOOD NAME', normal_food_size AS 'SIZE',"
					+ "normal_food_quantity AS 'QTY', normal_food_price AS 'PRICE' "
					+ "FROM normal_food_package "
					+ "WHERE normal_food_package_id = ? ";
			c.pst = c.con.prepareStatement(query);
			c.pst.setString(1, (String) normalPackageId);
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			
			getNormalPackageItemsTable().setModel(DbUtils.resultSetToTableModel(c.rs));
			
			c.rs.close();
			c.pst.close();
			c.con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ViewNormalPackage()
	{
		try {
			
			c = new Connect();
			String query = "SELECT normal_package_id AS 'ID', normal_package_name AS 'PACKAGE NAME', normal_package_price_serving_size AS 'SERVING SIZE', "
					+ "normal_package_total AS 'TOTAL PRICE' FROM normal_packages "
					+ "WHERE (normal_package_id LIKE ? OR normal_package_name LIKE ? OR normal_package_price_serving_size LIKE ? OR normal_package_total LIKE ?) AND (normal_package_type = 'Regular');";
			c.pst = c.con.prepareStatement(query);
			c.pst.setString(1, "%" + packageSearch.getText() + "%");
			c.pst.setString(2, "%" + packageSearch.getText() + "%");
			c.pst.setString(3, "%" + packageSearch.getText() + "%");
			c.pst.setString(4, "%" + packageSearch.getText() + "%");
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			getNormalPackageTable().setModel(DbUtils.resultSetToTableModel(c.rs));
			
			c.rs.close();
			c.pst.close();
			c.con.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ViewEventPackage()
	{
		try {
			c = new Connect();
			String query = "SELECT normal_package_id AS 'ID', normal_package_name AS 'PACKAGE NAME', normal_package_price_serving_size AS 'SERVING SIZE', "
					+ "normal_package_total AS 'TOTAL PRICE' FROM normal_packages "
					+ "WHERE (normal_package_id LIKE ? OR normal_package_name LIKE ? OR normal_package_price_serving_size LIKE ? OR normal_package_total LIKE ?) AND (normal_package_type = 'Event');";
			c.pst = c.con.prepareStatement(query);
			c.pst.setString(1, "%" + packageSearch.getText() + "%");
			c.pst.setString(2, "%" + packageSearch.getText() + "%");
			c.pst.setString(3, "%" + packageSearch.getText() + "%");
			c.pst.setString(4, "%" + packageSearch.getText() + "%");
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			getEventPackageTable().setModel(DbUtils.resultSetToTableModel(c.rs));
			
			c.rs.close();
			c.pst.close();
			c.con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setCustOverviewLabel()
	{
		try {
			c = new Connect();
			String query = "SELECT (SELECT COUNT(*) FROM reserve WHERE reserve_date BETWEEN ? AND ?) AS 'Reserved', "
					+ "(SELECT COUNT(*) FROM guest_dinein WHERE guest_dinein_date BETWEEN ? AND ?) AS 'Dine-in', "
					+ "(SELECT COUNT(*) FROM guest_takeout WHERE guest_takeout_date BETWEEN ? AND ?) AS 'Take-out', "
					+ "(SELECT COUNT(*) FROM waiting WHERE waiting_date BETWEEN ? AND ?) AS 'Waiting';";
			c.pst = c.con.prepareStatement(query);
			c.pst.setString(1, overviewFromDate);
			c.pst.setString(2, overviewToDate);
			c.pst.setString(3, overviewFromDate);
			c.pst.setString(4, overviewToDate);
			c.pst.setString(5, overviewFromDate);
			c.pst.setString(6, overviewToDate);
			c.pst.setString(7, overviewFromDate);
			c.pst.setString(8, overviewToDate);
			
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			
			int x = 0;
			
			while(c.rs.next())
			{
					
					reservedNo.setText(c.rs.getString("Reserved"));
					dineinNo.setText(c.rs.getString("Dine-in"));
					takeoutNo.setText(c.rs.getString("Take-out"));
					//waitedNo.setText(c.rs.getString("Waiting"));
					x = c.rs.getInt("Reserved") + c.rs.getInt("Dine-in") + c.rs.getInt("Take-out") + c.rs.getInt("Waiting");
					totalNo.setText(Integer.toString(x));
				
				
			}
			
			c.rs.close();
			c.pst.close();
			c.con.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void setSalesOverviewLabel()
	{
		try {
			c = new Connect();
			String query = "SELECT "
					+ "(SELECT SUM(Bilang) AS 'Normal Reservations' FROM "
					+ "(SELECT order_total - order_balance AS 'Bilang' FROM normal_orders "
					+ "JOIN reserve ON reserve_id = id WHERE id LIKE 'R%' AND reserve_type = 'Regular' AND order_date BETWEEN ? AND ?) a) AS 'Normal Reservations' , "
					+ "(SELECT SUM(Bilang) AS 'Event Reservations' FROM "
					+ "(SELECT order_total - order_balance AS 'Bilang' FROM event_orders "
					+ "JOIN reserve ON reserve_id = id WHERE id LIKE 'R%' AND reserve_type = 'Event' AND order_date BETWEEN ? AND ?) b) AS 'Event Reservations' , "
					+ "(SELECT SUM(Bilang) AS 'Dine-in Sales' FROM "
					+ "(SELECT order_total - order_balance AS 'Bilang' FROM normal_orders WHERE id LIKE 'DI%' AND order_date BETWEEN ? AND ?) d) AS 'Dine-in Sales', "
					+ "(SELECT SUM(Bilang) AS 'Take-out Sales' FROM "
					+ "(SELECT order_total - order_balance AS 'Bilang' FROM normal_orders WHERE id LIKE 'TO%' AND order_date BETWEEN ? AND ?) c) AS 'Take-out Sales';";
			c.pst = c.con.prepareStatement(query);
			c.pst.setString(1, overviewFromDate);
			c.pst.setString(2, overviewToDate);
			c.pst.setString(3, overviewFromDate);
			c.pst.setString(4, overviewToDate);
			c.pst.setString(5, overviewFromDate);
			c.pst.setString(6, overviewToDate);
			c.pst.setString(7, overviewFromDate);
			c.pst.setString(8, overviewToDate);
			
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			double x = 0;
			
			while(c.rs.next())
			{
				if(c.rs.getString("Normal Reservations") == null)
				{
					incomeNormalReserve.setText("Php 0.00");
				}
				else
				{
					incomeNormalReserve.setText("Php " + c.rs.getString("Normal Reservations"));
				}
				
				if(c.rs.getString("Event Reservations") == null)
				{
					incomeEventReserve.setText("Php 0.00");
				}
				else
				{
					incomeEventReserve.setText("Php " + c.rs.getString("Event Reservations"));
				}
				
				if(c.rs.getString("Dine-in Sales") == null)
				{
					incomeDinein.setText("Php 0.00");
				}
				else
				{
					incomeDinein.setText("Php " + c.rs.getString("Dine-in Sales"));
				}
				
				if(c.rs.getString("Take-out Sales") == null)
				{
					incomeTakeout.setText("Php 0.00");
				}
				else
				{
					incomeTakeout.setText("Php " + c.rs.getString("Take-out Sales"));
				}
				
				
				
				
				
				x = c.rs.getDouble("Normal Reservations") + c.rs.getDouble("Event Reservations") + c.rs.getDouble("Dine-in Sales") + c.rs.getDouble("Take-out Sales");
				incomeTotal.setText("Php " + Double.toString(x));
				
				
			}
			
			c.rs.close();
			c.pst.close();
			c.con.close();
		
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void setFoodPackageOverviewLabel()
	{
		try {
			//-----------------------------------NORMAL TOP 5 FOOD
			c = new Connect();
			String query = "SELECT food_name as 'Regular Food Name', SUM(Profit) as 'Profit' FROM "
					+ "(SELECT food_name, COUNT(*)*food_quantity as 'Profit' "
					+ "FROM guest_takeout_orders, normal_foods, normal_orders "
					+ "WHERE guest_takeout_orders.food_id = normal_foods.normal_food_id AND "
					+ "normal_foods.normal_food_type = 'Regular' "
					+ "AND normal_orders.order_id = guest_takeout_orders.order_id "
					+ "AND normal_orders.order_date BETWEEN ? AND ? "
					+ "GROUP BY food_name HAVING Profit >=1 "
					+ "UNION ALL "
					+ "SELECT food_name, COUNT(*)*food_quantity as 'Profit' "
					+ "FROM guest_dinein_orders, normal_foods, normal_orders "
					+ "WHERE guest_dinein_orders.food_id = normal_foods.normal_food_id AND "
					+ "normal_foods.normal_food_type = 'Regular' "
					+ "AND normal_orders.order_id = guest_dinein_orders.order_id "
					+ "AND normal_orders.order_date BETWEEN ? AND ? "
					+ "GROUP BY food_name HAVING Profit >=1 "
					+ "UNION ALL "
					+ "SELECT food_name, COUNT(*)*food_quantity as 'Profit' "
					+ "FROM reserve_normal_orders, normal_foods, normal_orders "
					+ "WHERE reserve_normal_orders.food_id = normal_foods.normal_food_id AND "
					+ "normal_foods.normal_food_type = 'Regular' "
					+ "AND normal_orders.order_id = reserve_normal_orders.order_id "
					+ "AND normal_orders.order_date BETWEEN ? AND ? "
					+ "GROUP BY food_name HAVING Profit >=1 "
					+ "UNION ALL "
					+ "SELECT food_name, COUNT(*)*food_quantity as 'Profit' "
					+ "FROM reserve_event_orders, normal_foods, event_orders "
					+ "WHERE reserve_event_orders.food_id = normal_foods.normal_food_id AND "
					+ "normal_foods.normal_food_type = 'Regular' "
					+ "AND event_orders.order_id = reserve_event_orders.order_id "
					+ "AND event_orders.order_date BETWEEN ? AND ? "
					+ "GROUP BY food_name HAVING Profit >=1 "
					+ ")x GROUP BY food_name ORDER BY Profit DESC LIMIT 5;";
			c.pst = c.con.prepareStatement(query);
			c.pst.setString(1, overviewFromDate);
			c.pst.setString(2, overviewToDate);
			c.pst.setString(3, overviewFromDate);
			c.pst.setString(4, overviewToDate);
			c.pst.setString(5, overviewFromDate);
			c.pst.setString(6, overviewToDate);
			c.pst.setString(7, overviewFromDate);
			c.pst.setString(8, overviewToDate);
/*			c.pst.setString(9, overviewFromDate);
			c.pst.setString(10, overviewToDate);*/
			
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			
			int i = 0;
			
			
				while(i<5)
				{
					bestNormalfood[i].setText("n/a");
					//System.out.println(bestNormalfood[i].getText()); 
					bnf[i].setText("n/a");
					i++;
				}
				i = 0;
				while(c.rs.next())
				{
					bestNormalfood[i].setText(c.rs.getString("Regular Food Name"));
					bnf[i].setText(c.rs.getString("Profit"));
					//System.out.println(bestNormalfood[i].getText()); 
					i++;
				}
			
			
			
			
			
			
			c.rs.close();
			c.pst.close();
			c.con.close();
			
			
			//-----------------------------------EVENT TOP 5 FOOD
			c = new Connect();
			String query2 = "SELECT food_name as 'Event Food Name', SUM(Profit) as 'Profit' FROM "
					+ "(SELECT food_name, COUNT(*)*food_quantity as 'Profit' "
					+ "FROM reserve_event_orders, normal_foods, event_orders "
					+ "WHERE reserve_event_orders.food_id = normal_foods.normal_food_id AND "
					+ "normal_foods.normal_food_type = 'Event' "
					+ "AND event_orders.order_id = reserve_event_orders.order_id "
					+ "AND event_orders.order_date BETWEEN ? AND ? "
					+ "GROUP BY food_name HAVING Profit >=1 "
					+ ")x GROUP BY food_name ORDER BY Profit DESC LIMIT 5;";
			c.pst = c.con.prepareStatement(query2);
			c.pst.setString(1, overviewFromDate);
			c.pst.setString(2, overviewToDate);
			
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			
			int j = 0;
			
			
				while(j<5)
				{
					bestEventFood[j].setText("n/a");
					bef[j].setText("n/a");
					j++;
				}
				
				j=0;
			
				while(c.rs.next())
				{
					bestEventFood[j].setText(c.rs.getString("Event Food Name"));
					bef[j].setText(c.rs.getString("Profit"));
					j++;
				}
			
			
			
			
			c.rs.close();
			c.pst.close();
			c.con.close();
			
			//-----------------------------------NORMAL TOP 5 PACKAGES
			c = new Connect();
			String query3 = "SELECT food_name as 'Regular Package Name', SUM(Profit) as 'Profit' FROM "
					+ "(SELECT food_name, COUNT(*)*food_quantity as 'Profit' "
					+ "FROM guest_takeout_orders, normal_packages, normal_orders "
					+ "WHERE guest_takeout_orders.food_id = normal_packages.normal_package_id AND "
					+ "normal_packages.normal_package_type = 'Regular' "
					+ "AND normal_orders.order_id = guest_takeout_orders.order_id "
					+ "AND normal_orders.order_date BETWEEN ? AND ? "
					+ "GROUP BY food_name HAVING Profit >=1 "
					+ "UNION ALL "
					+ "SELECT food_name, COUNT(*)*food_quantity as 'Profit' "
					+ "FROM guest_dinein_orders, normal_packages, normal_orders "
					+ "WHERE guest_dinein_orders.food_id = normal_packages.normal_package_id AND "
					+ "normal_packages.normal_package_type = 'Regular' "
					+ "AND normal_orders.order_id = guest_dinein_orders.order_id "
					+ "AND normal_orders.order_date BETWEEN ? AND ? "
					+ "GROUP BY food_name HAVING Profit >=1 "
					+ "UNION ALL "
					+ "SELECT food_name, COUNT(*)*food_quantity as 'Profit' "
					+ "FROM reserve_normal_orders, normal_packages, normal_orders "
					+ "WHERE reserve_normal_orders.food_id = normal_packages.normal_package_id AND "
					+ "normal_packages.normal_package_type = 'Regular' "
					+ "AND normal_orders.order_id = reserve_normal_orders.order_id "
					+ "AND normal_orders.order_date BETWEEN ? AND ? "
					+ "GROUP BY food_name HAVING Profit >=1 "
					+ "UNION ALL "
					+ "SELECT food_name, COUNT(*)*food_quantity as 'Profit' "
					+ "FROM reserve_event_orders, normal_packages, event_orders "
					+ "WHERE reserve_event_orders.food_id = normal_packages.normal_package_id AND "
					+ "normal_packages.normal_package_type = 'Regular' "
					+ "AND event_orders.order_id = reserve_event_orders.order_id "
					+ "AND event_orders.order_date BETWEEN ? AND ? "
					+ "GROUP BY food_name HAVING Profit >=1 "
					+ ")x GROUP BY food_name ORDER BY Profit DESC LIMIT 5;";
			c.pst = c.con.prepareStatement(query3);
			c.pst.setString(1, overviewFromDate);
			c.pst.setString(2, overviewToDate);
			c.pst.setString(3, overviewFromDate);
			c.pst.setString(4, overviewToDate);
			c.pst.setString(5, overviewFromDate);
			c.pst.setString(6, overviewToDate);
			c.pst.setString(7, overviewFromDate);
			c.pst.setString(8, overviewToDate);
			
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			
			int k = 0;
			
			
				while(k<5)
				{
					bestPackageNormal[k].setText("n/a");
					bnp[k].setText("n/a");
					k++;
				}
				
				k = 0;
			
				while(c.rs.next())
				{
					bestPackageNormal[k].setText(c.rs.getString("Regular Package Name"));
					bnp[k].setText(c.rs.getString("Profit"));
					k++;
				}
			
			
			
			
			c.rs.close();
			c.pst.close();
			c.con.close();
			
			//-----------------------------------EVENT TOP 5 PACKAGE
			c = new Connect();
			String query4 = "SELECT food_name as 'Event Food Name', SUM(Profit) as 'Profit' FROM "
					+ "(SELECT food_name, COUNT(*)*food_quantity as 'Profit' "
					+ "FROM reserve_event_orders, normal_packages, event_orders "
					+ "WHERE reserve_event_orders.food_id = normal_packages.normal_package_id AND "
					+ "normal_packages.normal_package_type = 'Event' "
					+ "AND event_orders.order_id = reserve_event_orders.order_id "
					+ "AND event_orders.order_date BETWEEN ? AND ? "
					+ "GROUP BY food_name HAVING Profit >=1 "
					+ ")x GROUP BY food_name ORDER BY Profit DESC LIMIT 5;";
			c.pst = c.con.prepareStatement(query4);
			c.pst.setString(1, overviewFromDate);
			c.pst.setString(2, overviewToDate);
			
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			
			int l = 0;
			
			
				while(l<5)
				{
					bestPackageEvent[l].setText("n/a");
					bep[l].setText("n/a");
					l++;
				}
				l = 0;
			
			
				while(c.rs.next())
				{
					bestPackageEvent[l].setText(c.rs.getString("Event Food Name"));
					bep[l].setText(c.rs.getString("Profit"));
					l++;
				}
			
			
			
			
			c.rs.close();
			c.pst.close();
			c.con.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		if( arg0.getSource() == normalFoodTable.getSelectionModel() && arg0.getFirstIndex() >= 0 ){
			TableModel model = (TableModel)normalFoodTable.getModel();
			int row = normalFoodTable.getSelectedRow();
			if(row!=-1){
				Thread thr=new Thread(new Runnable(){
					  public void run()
					  {
					      try{

								normalFoodId = normalFoodTable.getValueAt(row, 0);
								ViewNormalImage();
								ViewNormalSizes();
								Thread.sleep(0);
						   }catch(Exception e){
							   
						   }
					   }
					 }); thr.start();
			}
		}
		
		if( arg0.getSource() == eventFoodTable.getSelectionModel() && arg0.getFirstIndex() >= 0 ){
			TableModel model = (TableModel)eventFoodTable.getModel();
			int row = eventFoodTable.getSelectedRow();
			if(row!=-1){
				Thread thr=new Thread(new Runnable(){
					  public void run()
					  {
					      try{

								eventFoodId = eventFoodTable.getValueAt(row, 0);
								ViewEventImage();
								ViewEventSizes();
								Thread.sleep(0);
						   }catch(Exception e){
							   
						   }
					   }
					 }); thr.start();
			}
		}
		
		if( arg0.getSource() == normalPackageTable.getSelectionModel() && arg0.getFirstIndex() >= 0 ){
			TableModel model = (TableModel)normalPackageTable.getModel();
			int row = normalPackageTable.getSelectedRow();
			if(row!=-1){
				Thread thr=new Thread(new Runnable(){
					  public void run()
					  {
					      try{

					    	  normalPackageId = normalPackageTable.getValueAt(row, 0);
								ViewNormalPackageImage();
								ViewNormalPackageItems();
								Thread.sleep(0);
						   }catch(Exception e){
							   
						   }
					   }
					 }); thr.start();
			}
		}
		
		if( arg0.getSource() == eventPackageTable.getSelectionModel() && arg0.getFirstIndex() >= 0 ){
			TableModel model = (TableModel)eventPackageTable.getModel();
			int row = eventPackageTable.getSelectedRow();
			if(row!=-1){
				Thread thr=new Thread(new Runnable(){
					  public void run()
					  {
					      try{

					    	  eventPackageId = eventPackageTable.getValueAt(row, 0);
								ViewEventPackageImage();
								ViewEventPackageItems();
								Thread.sleep(0);
						   }catch(Exception e){
							   
						   }
					   }
					 }); thr.start();
			}
		}
		
		if( arg0.getSource() == custRvTable.getSelectionModel() && arg0.getFirstIndex() >= 0 ){
			TableModel model = (TableModel)custRvTable.getModel();
			int row = custRvTable.getSelectedRow();
			if(row!=-1){
				Thread thr=new Thread(new Runnable(){
					  public void run()
					  {
					      try{

					    	  	reserve_id = custRvTable.getValueAt(row, 0);
					  			showRVDetails.setEnabled(true);
								Thread.sleep(0);
						   }catch(Exception e){
							   
						   }
					   }
					 }); thr.start();
			}
		}
		
		if( arg0.getSource() == custDITable.getSelectionModel() && arg0.getFirstIndex() >= 0 ){
			TableModel model = (TableModel)custDITable.getModel();
			int row = custDITable.getSelectedRow();
			if(row!=-1){
				Thread thr=new Thread(new Runnable(){
					  public void run()
					  {
					      try{

					    	  dinein_id = custDITable.getValueAt(row, 0);
					  		showDIDetails.setEnabled(true);
								Thread.sleep(0);
						   }catch(Exception e){
							   
						   }
					   }
					 }); thr.start();
			}
		}
		
		if( arg0.getSource() == custTOTable.getSelectionModel() && arg0.getFirstIndex() >= 0 ){
			TableModel model = (TableModel)custTOTable.getModel();
			int row = custTOTable.getSelectedRow();
			if(row!=-1){
				Thread thr=new Thread(new Runnable(){
					  public void run()
					  {
					      try{

					    	  takeout_id = custTOTable.getValueAt(row, 0);
					  		showTODetails.setEnabled(true);
								Thread.sleep(0);
						   }catch(Exception e){
							   
						   }
					   }
					 }); thr.start();
			}
		}
		
		/*if( arg0.getSource() == custWLTable.getSelectionModel() && arg0.getFirstIndex() >= 0 ){
			//TableModel model = (TableModel)custWLTable.getModel();
			int row = custWLTable.getSelectedRow();
			if(row!=-1){
				Thread thr=new Thread(new Runnable(){
					  public void run()
					  {
					      try{

					    	  //waiting_id = custWLTable.getValueAt(row, 0);
					  		//showWLDetails.setEnabled(true);
								Thread.sleep(0);
						   }catch(Exception e){
							   
						   }
					   }
					 }); thr.start();
			}*/
		
		
		if( arg0.getSource() == unavailableTable.getSelectionModel() && arg0.getFirstIndex() >= 0 ){
			TableModel model = (TableModel)unavailableTable.getModel();
			int row = unavailableTable.getSelectedRow();
			System.out.println("UNAVAILABLE SELECT 1");
			if(row!=-1){
				System.out.println("UNAVAILABLE SELECT 2");
				
				Thread thr=new Thread(new Runnable(){
					  public void run()
					  {
					      try{

					    	  tableReserveId = unavailableTable.getValueAt(row, 0);
					    	  btnShowMoreDetails.setEnabled(true);
					  		getReserveId((int) tableReserveId);
					  		System.out.println("UNAVAILABLE SELECT 3");
					  		
								Thread.sleep(0);
						   }catch(Exception e){
							   
						   }
					   }
					 }); thr.start();
			}
		}
		
		if( arg0.getSource() == floorTable.getSelectionModel() && arg0.getFirstIndex() >= 0 ){
			TableModel model = (TableModel)floorTable.getModel();
			int row = floorTable.getSelectedRow();
			if(row!=-1){
				Thread thr=new Thread(new Runnable(){
					  public void run()
					  {
					      try{

					    	  floorId = floorTable.getValueAt(row, 0);
					  		showRooms();
								Thread.sleep(0);
						   }catch(Exception e){
							   
						   }
					   }
					 }); thr.start();
			}
		}
		
		
		
		
		
	}
}
