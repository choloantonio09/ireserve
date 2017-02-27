package Capstone.View;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import net.proteanit.sql.DbUtils;

import javax.swing.JTabbedPane;
import javax.swing.JButton;

import Capstone.Database.Connect;
import com.toedter.calendar.IDateEditor;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import java.awt.Cursor;
import java.awt.Desktop;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionEvent;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.metal.MetalButtonUI;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.MatteBorder;

import com.toedter.calendar.JYearChooser;

import javax.swing.table.DefaultTableModel;


import net.proteanit.sql.DbUtils;
import Capstone.Database.Connect;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;



import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font.*;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JCalendar;





public class Reports extends JPanel implements ListSelectionListener{
	PrintReport pr;
	
	public JTabbedPane tpReport;
	public JPanel pnlMonthly;
	public JPanel pblDaily;
	public IDateEditor dateSalesEditorStart;
	public IDateEditor dateSalesEditorEnd;
	public IDateEditor dateSalesEditorStart1;
	public IDateEditor dateSalesEditorEnd1;
	public String sid;
	private JButton btnGoBack;
	public static String datefrom;
	//public static String dateto;
	private JTable DailyTable;
	private JTextField textFieldtotaldaily;
	Connect c;
	private JLabel lblYear;
	private JTable MonthlyTable;
	SimpleDateFormat sdf;
	SimpleDateFormat monthformat;
	SimpleDateFormat yearformat;
	JDateChooser dateChooser;
	
	private JTextField textFieldtotalmonthly;
	public JYearChooser yearChooser;
	private String totaldaily;
	private String totalmonthly;
	private String datefdow;
	private String dateldow;
	
	public int totalsum;
	JButton btnmonthprint;
	public JComboBox comboBoxMonthly;
	 
	 
		public JPanel pnlWeek;
		
		public JPanel pnlReceipt;
		public JPanel pnlReceiptButton;
		public JPanel pnlReceiptArea;
		public JLabel lblReceiptFrom;
		public JLabel lnlReceiptTo;
		public JDateChooser dateReceiptStart;
		public JButton btnReceiptGenerate;
		public JDateChooser dateReceiptEnd;
		public JScrollPane spReceipt;
		public JTextArea taReceipt;
		public JTable table;
		public JPanel panel_3;
		public JPanel panel_2;
		public JPanel pblOverView;
		public JButton btnPrint;
		
		private JPanel pnlAnnualHead;
		JTable tblAnnual;
		private JTextField textFieldAnnual;
		//private JLabel lblTo;
		private JPanel pnlWeekHead;
		private JLabel lblMonth;
		private JButton btnGenerateReport;
		private JButton btnPrintReport_1;
		private JTextField textFieldWeek;
		private JTable tblWeek;
		public String totalyear;
		public String totalweek;
		public String header;
		public Boolean error;

		private int MonthlyFilteryear;
		private int monthValue;
		private int yearValue;
		private int startValue;
		private int endValue;
		private String monthName;
		private String custType;
		private String custType2;
		private JPanel panel_1;
		private JPanel panel_4;
		private JLabel lblTo_1;
		private JScrollPane scrollPanelWeek;
		private JComboBox<?> comboBoxWeek;
		private JLabel lblType;
		private JComboBox<?> comboBoxAnnual;
		private JComboBox<?> comboBoxDaily;
		private JComboBox<?> comboBoxMonth;
		private JLabel lblNewLabel;
		private JLabel lblSalesReportDaily;

		private JMonthChooser monthChooser;

		private int MonthlyFiltermonth;
		private JDateChooser dateChooserWeek;
		private JLabel lblSalesReportWeekly;
		private JLabel lblSalesReportAnnual;
		private JLabel label;
		private JLabel label_1;

		protected String Ordertypename;
		private JTextField textField;

		private JTextField textFieldRange;
		
		  
	/**
	 * Create the panel.
	 */
	public Reports() {
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		yearformat = new SimpleDateFormat("yyyy");
		monthformat = new SimpleDateFormat("MM");
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
		tpReport = new JTabbedPane(JTabbedPane.TOP);
		tpReport.setUI(new AquaTabbedPaneUI());
		add(tpReport, "cell 1 0,grow");
		
/*DAILY*/
		
		pblDaily = new JPanel();
		tpReport.addTab("Daily", null, pblDaily, null);
		pblDaily.setLayout(new MigLayout("", "[730.00px][0.00px][334px,grow]", "[][49px][182px,grow]"));
		
		lblSalesReportDaily = new JLabel("DAILY SALES REPORT");
		lblSalesReportDaily.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalesReportDaily.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 20));
		pblDaily.add(lblSalesReportDaily, "cell 0 0,alignx left");
		
		JPanel DailyPanelButtons = new JPanel();
		pblDaily.add(DailyPanelButtons, "cell 0 1 3 1,growx,aligny top");
		DailyPanelButtons.setLayout(new MigLayout("", "[81px][185px][183.00px][75.00px][189px][200.00px][177px,grow]", "[35px,grow]"));
		
		JLabel lblFrom = new JLabel("DATE:");
		lblFrom.setForeground(Color.DARK_GRAY);
		lblFrom.setFont(new Font("Monospaced", Font.PLAIN, 20));
		DailyPanelButtons.add(lblFrom, "cell 0 0,grow");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setFont(new Font("Monospaced", Font.PLAIN, 18));
		scrollPane.setViewportBorder(new EmptyBorder(0, 0, 0, 0));
		pblDaily.add(scrollPane, "cell 0 2,grow");
		
		DailyTable = new JTable();
		DailyTable.setEnabled(false);
		scrollPane.setViewportView(DailyTable);
		
		DailyTable.setFont(new Font("Calibri", Font.PLAIN, 18)); 

		DailyTable.getTableHeader().setFont( new Font( "Calibri" , Font.PLAIN, 18 ));
		
		dateChooser = new JDateChooser(new Date());
		dateChooser.setFont(new Font("Monospaced", Font.PLAIN, 15));
		dateChooser.setDateFormatString("MMM dd, yyyy");
		DailyPanelButtons.add(dateChooser, "cell 1 0,grow");
		
		JButton btnPrintReport = new JButton("PRINT REPORT");
		btnPrintReport.setFont(new Font("Monospaced", Font.PLAIN, 20));
		btnPrintReport.setEnabled(false);
		btnPrintReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
					 DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss"); 
					 String curDate=df.format(new Date());
			            Document doc = new Document();
			            String filename = "ReportDaily"+curDate+".pdf";
			            PdfWriter.getInstance(doc, new FileOutputStream(filename));
			            
			            Paragraph prefacedate = new Paragraph();
			            prefacedate.setAlignment(Element.ALIGN_CENTER);
			            prefacedate.add(datefrom);
			            
			            Paragraph preface = new Paragraph();
			            preface.setAlignment(Element.ALIGN_CENTER);
			            preface.add("\n");
			            preface.add("Daily Sales Report");
			            preface.add("\n");
			            preface.add(Ordertypename);
			            preface.add("\n");
			            preface.add("\n");
			            //preface.setIndentationLeft(220);
			            
			           
			            Paragraph preface1 = new Paragraph();
			            preface1.setAlignment(Paragraph.ALIGN_RIGHT);
			            preface1.setIndentationRight(50);
			            preface1.add("Total Amount : " + totaldaily);
			            doc.open();
			            
			            pr = new PrintReport();
			            pr.Caller(doc, filename);
			            
			           
			           
			           
			            PdfPTable pdfTable = new PdfPTable(DailyTable.getColumnCount());
		                pdfTable.setHorizontalAlignment(Element.ALIGN_CENTER);
			            pdfTable.setTotalWidth(2500);
			            //adding table headers
			           
			            for (int i = 0; i < DailyTable.getColumnCount(); i++) {
			                pdfTable.addCell(DailyTable.getColumnName(i));
			            }
			            //extracting data from the JTable and inserting it to PdfPTable
			            for (int rows = 0; rows < DailyTable.getRowCount(); rows++) {
			                for (int cols = 0; cols < DailyTable.getColumnCount(); cols++) {
			                    pdfTable.addCell(DailyTable.getModel().getValueAt(rows, cols).toString());

			                }
			            }
			            
			            
			            doc.add(prefacedate);
			            doc.add(preface);
			            doc.add(pdfTable);
			            doc.add(preface1);
			            doc.close();;
			            try {
			            	//System.out.println("print daily");
							Desktop.getDesktop().open(new File("ReportDaily"+curDate+".pdf"));
						
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			        } catch (DocumentException ex) {
			            //Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
			        } catch (FileNotFoundException ex) {
			           // Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
			        }


			    
			}
		});
		 
		 label = new JLabel("TYPE :");
		 label.setFont(new Font("Monospaced", Font.PLAIN, 20));
		 DailyPanelButtons.add(label, "cell 3 0,alignx right,growy");
		
		 comboBoxDaily = new JComboBox();
		 comboBoxDaily.setFont(new Font("Monospaced", Font.PLAIN, 15));
		 comboBoxDaily.setModel(new DefaultComboBoxModel(new String[] {"All", "Regular", "Event"}));
		 DailyPanelButtons.add(comboBoxDaily, "cell 4 0,grow");
		
		 dateChooser.setMaxSelectableDate(new Date());
		JButton btnNewButton = new JButton("GENERATE REPORT");
		btnNewButton.setFont(new Font("Monospaced", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			boolean error = false;
			
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{	
					
					datefrom =(String) sdf.format(dateChooser.getDate());
					
					
				}
				catch (Exception e)
				{	error=true;
					  // create our jframe
				    //JFrame frame = new JFrame("JOptionPane showMessageDialog example");
				    
				    // show a joptionpane dialog using showMessageDialog
					JOptionPane.showMessageDialog(null, "Please enter Valid date in MMM DD, YYYY format. \nThe date wil be returned to current date.", "Error", JOptionPane.ERROR_MESSAGE);
					dateChooser.setCalendar(Calendar.getInstance());
					System.out.println("pasok");
					error=false;
				    
				    
				}
					 
					  System.out.println(error);
				 
				
				if(comboBoxDaily.getSelectedItem().equals("All")){
					Ordertypename = "";
					ViewTableDaily();
					TotalDaily();
				}else if(comboBoxDaily.getSelectedItem().equals("Regular")){
					Ordertypename = "Regular customers and reservations";
					ViewTableDailyNormal();
					TotalDailyNormal();
				}else if(comboBoxDaily.getSelectedItem().equals("Event")){
					Ordertypename = "Event reservations";
					ViewTableDailyEvent();
					TotalDailyEvent();
				}
				//ViewTableDaily();
				int rowcount = DailyTable.getRowCount();
			//	System.out.println("daily "+rowcount);
				if(rowcount == 0 && error != true)
				{
					JOptionPane.showMessageDialog(null, "No results found.", "", JOptionPane.INFORMATION_MESSAGE); 
					btnPrintReport.setEnabled(false);
					System.out.println("pasok no result "+ rowcount);
				}
				else
					btnPrintReport.setEnabled(true);
				//TotalDaily();
			}
		});
		DailyPanelButtons.add(btnNewButton, "cell 5 0,alignx right,growy");
		btnPrintReport.setBackground(Color.WHITE);
		DailyPanelButtons.add(btnPrintReport, "cell 6 0,grow");
		
		JPanel DailyPanelTotal = new JPanel();
		pblDaily.add(DailyPanelTotal, "cell 2 2,growx,aligny bottom");
		DailyPanelTotal.setLayout(new MigLayout("", "[64.00][grow]", "[]"));
		
		JLabel lblTotalDaily = new JLabel("TOTAL");
		lblTotalDaily.setFont(new Font("Monospaced", Font.PLAIN, 20));
		DailyPanelTotal.add(lblTotalDaily, "flowx,cell 0 0,grow");
		
		textFieldtotaldaily = new JTextField();
		textFieldtotaldaily.setEnabled(false);
		textFieldtotaldaily.setFont(new Font("Monospaced", Font.PLAIN, 23));
		textFieldtotaldaily.setEditable(false);
		DailyPanelTotal.add(textFieldtotaldaily, "cell 1 0,grow");
		textFieldtotaldaily.setColumns(10);
		JPanel pnlChart = new JPanel();
		pnlChart.setBounds(451, 90, 323, 253);
		
		/*ENd Daily*/
		
		
		/*WEEKLY*/
		pnlWeek = new JPanel();
		tpReport.addTab("Weekly", null, pnlWeek, null);
		pnlWeek.setLayout(new MigLayout("", "[948px,grow]", "[][49px][359px,grow]"));
		
		lblSalesReportWeekly = new JLabel("WEEKLY SALES REPORT");
		lblSalesReportWeekly.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalesReportWeekly.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 20));
		pnlWeek.add(lblSalesReportWeekly, "cell 0 0");
		
		pnlWeekHead = new JPanel();
		pnlWeek.add(pnlWeekHead, "cell 0 1,grow");
		pnlWeekHead.setLayout(new MigLayout("", "[70.00px][163.00px][233.00px,grow][][155.00][194.00px][142.00px,grow]", "[35px,grow]"));
		
		lblMonth = new JLabel("DATE:");
		lblMonth.setHorizontalAlignment(SwingConstants.LEFT);
		lblMonth.setFont(new Font("Monospaced", Font.PLAIN, 20));
		pnlWeekHead.add(lblMonth, "cell 0 0,grow");
		
		dateChooserWeek = new JDateChooser(new Date());
		dateChooserWeek.setFont(new Font("Monospaced", Font.PLAIN, 15));
		dateChooserWeek.setDateFormatString("MMM dd, yyyy");
		dateChooserWeek.setMaxSelectableDate(new Date());
		pnlWeekHead.add(dateChooserWeek, "cell 1 0,grow");
		
		textFieldRange = new JTextField();
		textFieldRange.setEnabled(false);
		textFieldRange.setText("Range will appear here.");
		textFieldRange.setEditable(false);
		pnlWeekHead.add(textFieldRange, "cell 2 0,grow");
		textFieldRange.setColumns(10);
		
		lblType = new JLabel("TYPE :");
		lblType.setFont(new Font("Monospaced", Font.PLAIN, 20));
		pnlWeekHead.add(lblType, "cell 3 0,alignx right,growy");
		
		comboBoxWeek = new JComboBox();
		comboBoxWeek.setModel(new DefaultComboBoxModel(new String[] {"All", "Regular", "Event"}));
		
		pnlWeekHead.add(comboBoxWeek, "cell 4 0,grow");
		
		btnGenerateReport = new JButton("GENERATE REPORT");
		btnGenerateReport.setFont(new Font("Monospaced", Font.PLAIN, 20));
		pnlWeekHead.add(btnGenerateReport, "cell 5 0,alignx right,growy");
		
		btnPrintReport_1 = new JButton("PRINT REPORT");
		btnPrintReport_1.setEnabled(false);
		btnPrintReport_1.setFont(new Font("Monospaced", Font.PLAIN, 20));
		pnlWeekHead.add(btnPrintReport_1, "cell 6 0,grow");
		
		JPanel panel = new JPanel();
		pnlWeek.add(panel, "cell 0 2,grow");
		panel.setLayout(new MigLayout("", "[724.00px][224px,grow]", "[345px,grow]"));
		
		panel_1 = new JPanel();
		panel.add(panel_1, "cell 1 0,growx,aligny bottom");
		panel_1.setLayout(new MigLayout("", "[72px][131px,grow]", "[33px,grow]"));
		
		JLabel lblTotal = new JLabel("TOTAL:");
		panel_1.add(lblTotal, "cell 0 0,alignx left,growy");
		lblTotal.setFont(new Font("Monospaced", Font.PLAIN, 20));
		
		textFieldWeek = new JTextField();
		panel_1.add(textFieldWeek, "cell 1 0,growx,aligny top");
		textFieldWeek.setFont(new Font("Monospaced", Font.PLAIN, 20));
		textFieldWeek.setEnabled(false);
		textFieldWeek.setColumns(10);
		
		scrollPanelWeek = new JScrollPane();
		scrollPanelWeek.setFont(new Font("Arial", Font.PLAIN, 18));
		panel.add(scrollPanelWeek, "cell 0 0,grow");
		
		tblWeek = new JTable();
		tblWeek.setEnabled(false);
		tblWeek.setFont(new Font("Calibri", Font.PLAIN, 18));
		scrollPanelWeek.setViewportView(tblWeek);

		tblWeek.getTableHeader().setFont( new Font( "Calibri" , Font.PLAIN, 18 ));
		
		btnGenerateReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				datefrom =(String) sdf.format(dateChooserWeek.getDate());
				
				String[] arrDate = datefrom.split("-");
				String type = null;
				error = false;
				
				int lYear = Integer.parseInt(arrDate[0]);
				int lMonth = Integer.parseInt(arrDate[1]) - 1;			
				int lDay = Integer.parseInt(arrDate[2]);			
				
				Calendar cal = Calendar.getInstance ();
				cal.set(lYear, lMonth, lDay);
				int dayofWeek = cal.get (Calendar.DAY_OF_WEEK);

				cal.add (Calendar.DATE, -1 * (dayofWeek - Calendar.SUNDAY));
				Date fdow = cal.getTime ();
				datefdow =(String) sdf.format(fdow);
				
				cal.add (Calendar.DATE, 6);
				Date ldow = cal.getTime ();
				dateldow =(String) sdf.format(ldow);
				
				textFieldRange.setText("FROM: "+datefdow +" TO: "+dateldow);
				
				if (comboBoxWeek.getSelectedItem().equals("All")){
					type = "normal_orders, event_orders";
					//header = "Regular and Event customer sales report";
				}
				else if (comboBoxWeek.getSelectedItem().equals("Regular")){
					type = "normal_orders";
					header = "Regular customers and reservations";
				}
				else if (comboBoxWeek.getSelectedItem().equals("Event")){
					type = "event_orders";
					header = "Event reservations";
				};
					viewtblWeek(datefdow, dateldow, type);
					viewtotalWeek(datefdow, dateldow, type);	
				} catch (Exception e){
					error = true;
					JOptionPane.showMessageDialog(null, "Please enter Valid date in MMM DD, YYYY format. \nThe date wil be returned to current date.", "Error", JOptionPane.ERROR_MESSAGE);
					dateChooserWeek.setCalendar(Calendar.getInstance());
				}	
					
				int rowcount = tblWeek.getRowCount();
				//	System.out.println("daily "+rowcount);
					if(rowcount == 0  && error != true)
					{
						btnPrintReport_1.setEnabled(false);
						JOptionPane.showMessageDialog(null, "No Results Found.", "Error", JOptionPane.INFORMATION_MESSAGE);
					}
					else
						btnPrintReport_1.setEnabled(true);
			;}
		});
		
		btnPrintReport_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			try {				
				
				Document doc = new Document();
				DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss"); df.format(new Date());
				String current = df.format(new Date());
				String filename = "ReportWeekly"+current+".pdf";
				PdfWriter.getInstance(doc, new FileOutputStream(filename));
	            
	            Paragraph prefacedate = new Paragraph();
	            prefacedate.setAlignment(Element.ALIGN_CENTER);
	            
	            Paragraph preface = new Paragraph();
	            preface.setAlignment(Element.ALIGN_CENTER);
	            preface.add("\n");
	            preface.add("Weekly Sales Report");
	            preface.add("\n");
	            preface.add(header);
	            preface.add("\n");
	            prefacedate.add("FROM : "+datefdow+" TO : "+dateldow);
	            preface.add("\n");
	            preface.add("\n");
	            //preface.setIndentationLeft(220);
	           
	            Paragraph preface1 = new Paragraph();
	            preface1.setAlignment(Paragraph.ALIGN_RIGHT);
	            preface1.setIndentationRight(50);
	            preface1.add("Total Amount : " + totalweek);
	            doc.open();
	            
	            pr = new PrintReport();
	            pr.Caller(doc, filename);
	            PdfPTable pdfTable = new PdfPTable(tblWeek.getColumnCount());
                pdfTable.setHorizontalAlignment(Element.ALIGN_CENTER);
	            pdfTable.setTotalWidth(2500);
	           
	            //adding table headers
	            for (int i = 0; i < tblWeek.getColumnCount(); i++) {
	                pdfTable.addCell(tblWeek.getColumnName(i));
	            }
	            //extracting data from the JTable and inserting it to PdfPTable
	            for (int rows = 0; rows < tblWeek.getRowCount() ; rows++) {
	                for (int cols = 0; cols < tblWeek.getColumnCount(); cols++) {
	                    pdfTable.addCell(tblWeek.getModel().getValueAt(rows, cols).toString());

	                }
	            }
	            doc.add(prefacedate);
	            doc.add(preface);
	            doc.add(pdfTable);
	            doc.add(preface1);
	            doc.close();
	            System.out.println("done");
	            
	            try {
	            	Desktop.getDesktop().open(new File("ReportWeekly"+current+".pdf"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        } catch (DocumentException ex) {
	        	ex.printStackTrace();
	        } catch (FileNotFoundException ex) {
	        	ex.printStackTrace();
	        } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    }
		});
		/*END WEEKLY*/
		
		/*MONTHLY*/
		
		pnlMonthly = new JPanel();
		tpReport.addTab("Monthly", null, pnlMonthly, null);
		pnlMonthly.setLayout(new MigLayout("", "[724.00px][14px][383px,grow]", "[29.00px][44.00px][384px,grow]"));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(new EmptyBorder(0, 0, 0, 0));
		pnlMonthly.add(scrollPane_1, "cell 0 2,grow");
		
		MonthlyTable = new JTable();
		MonthlyTable.setEnabled(false);
		MonthlyTable.setFont(new Font("Calibri", Font.PLAIN, 18));
		scrollPane_1.setViewportView(MonthlyTable);

		MonthlyTable.getTableHeader().setFont( new Font( "Calibri" , Font.PLAIN, 18 ));
		
		JPanel panel1 = new JPanel();
		pnlMonthly.add(panel1, "cell 0 1 3 1,grow");
		panel1.setLayout(new MigLayout("", "[84px][92.00px][216.00px][][][191.00px][222.00px,right][177px,grow]", "[37px,grow]"));
		
		lblYear = new JLabel("YEAR : ");
		lblYear.setFont(new Font("Monospaced", Font.PLAIN, 20));
		panel1.add(lblYear, "cell 0 0,alignx left,growy");
		
		yearChooser = new JYearChooser();
		JSpinner sampleSpinner = (JSpinner) yearChooser.getSpinner();
		((javax.swing.JTextField) sampleSpinner.getEditor()).setEditable(false);
		yearChooser.getSpinner().setBounds(0, 0, 56, 35);
		yearChooser.getSpinner().setFont(new Font("Monospaced", Font.PLAIN, 15));
		yearChooser.getSpinner().setBackground(Color.WHITE);
		panel1.add(yearChooser, "cell 1 0,grow");
		yearChooser.setLayout(null);
		
		JLabel lblMonth_1 = new JLabel("MONTH :");
		lblMonth_1.setFont(new Font("Monospaced", Font.PLAIN, 20));
		panel1.add(lblMonth_1, "cell 2 0,alignx left,growy");
		
		monthChooser = new JMonthChooser();
		monthChooser.getComboBox().setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel1.add(monthChooser, "cell 2 0,grow");
		
		 Date date = new Date();
	     LocalDate localDatey = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	     int monthlyyear = localDatey.getYear();
	     yearChooser.setEndYear(monthlyyear);
		
		JButton btnmonthsubmit = new JButton("GENERATE REPORT");
		btnmonthsubmit.setFont(new Font("Monospaced", Font.PLAIN, 20));
		btnmonthsubmit.addActionListener(new ActionListener() {
			boolean error = false;
			public void actionPerformed(ActionEvent e) {
				
				Ordertypename="";
				if(comboBoxMonthly.getSelectedItem().equals("All")){
					Ordertypename="";
					ViewTableMonthly();
					TotalMonthly();
				}else if(comboBoxMonthly.getSelectedItem().equals("Regular")){
					Ordertypename="Regular customers and reservations";
					ViewTableMonthlyNormal();
					TotalMonthlyNormal();
				}else if(comboBoxMonthly.getSelectedItem().equals("Event")){
					Ordertypename="Event reservations";
					ViewTableMonthlyEvent();
					TotalMonthlyEvent();
				}
				//ViewTableMonthly();
				int monthcount = MonthlyTable.getRowCount();
				//System.out.println("monthly" +monthcount);
				if(monthcount == 0 && error == false)
				{
					JOptionPane.showMessageDialog(null, "No results found.", "", JOptionPane.INFORMATION_MESSAGE); 
					btnmonthprint.setEnabled(false); 
				}
				else
					btnmonthprint.setEnabled(true);

				//TotalMonthly();
			}
		});
		
		label_1 = new JLabel("TYPE : ");
		label_1.setFont(new Font("Monospaced", Font.PLAIN, 20));
		panel1.add(label_1, "cell 4 0,alignx right,growy");
		panel1.add(btnmonthsubmit, "cell 6 0,alignx right,growy");
		
		btnmonthprint = new JButton("PRINT REPORT");
		btnmonthprint.setFont(new Font("Monospaced", Font.PLAIN, 20));
		btnmonthprint.setEnabled(false);
		btnmonthprint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
					 DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss"); 
			         
					 String curDate=df.format(new Date());
					 	Document doc = new Document();

						 String filename = "ReportMonthly"+curDate+".pdf";
			            PdfWriter.getInstance(doc, new FileOutputStream(filename));
			            
			           Paragraph preface = new Paragraph();
			            preface.setAlignment(Paragraph.ALIGN_CENTER);
			            preface.add("\n");
			            preface.add("Monthly Sales Report");
			            preface.add("\n");
			            preface.add(Ordertypename);
			            preface.add("\n");
			            preface.add("\n");
			            
			            Paragraph preface1 = new Paragraph();
			            preface1.setAlignment(Paragraph.ALIGN_RIGHT);
			            preface1.setIndentationRight(50);
			            doc.open();
			            
			            pr = new PrintReport();
			            pr.Caller(doc, filename);
			            
			            Paragraph prefacedate = new Paragraph();
			            
			            String monthString;
			            switch (monthChooser.getMonth()+1) {
			                case 1:  monthString = "January";       break;
			                case 2:  monthString = "February";      break;
			                case 3:  monthString = "March";         break;
			                case 4:  monthString = "April";         break;
			                case 5:  monthString = "May";           break;
			                case 6:  monthString = "June";          break;
			                case 7:  monthString = "July";          break;
			                case 8:  monthString = "August";        break;
			                case 9:  monthString = "September";     break;
			                case 10: monthString = "October";       break;
			                case 11: monthString = "November";      break;
			                case 12: monthString = "December";      break;
			                default: monthString = "Invalid month"; break;
			            }
			           
			            
			            prefacedate.add(monthString+" "+MonthlyFilteryear);

			            prefacedate.setAlignment(Element.ALIGN_CENTER);
			            
			           
			            PdfPTable pdfTable = new PdfPTable(MonthlyTable.getColumnCount());
		                pdfTable.setHorizontalAlignment(Element.ALIGN_CENTER);
			            pdfTable.setTotalWidth(2500);
			            //adding table headers
			           
			            for (int i = 0; i < MonthlyTable.getColumnCount(); i++) {
			                pdfTable.addCell(MonthlyTable.getColumnName(i));
			            }
			            //extracting data from the JTable and inserting it to PdfPTable
			            for (int rows = 0; rows < MonthlyTable.getRowCount(); rows++) {
			                for (int cols = 0; cols < MonthlyTable.getColumnCount(); cols++) {
			                    pdfTable.addCell(MonthlyTable.getModel().getValueAt(rows, cols).toString());

			                }
			            }
			            
			            preface.add(new Paragraph(" "));
			            preface.add(new Paragraph(" "));
			            preface1.add("Total Amount : " + totalmonthly);
			            
			            doc.add(prefacedate);
			            doc.add(preface);
			            doc.add(pdfTable);
			            doc.add(preface1);
			            doc.close();;
			            try {
							Desktop.getDesktop().open(new File("ReportMonthly"+curDate+".pdf"));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			        } catch (DocumentException ex) {
			            //Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
			        } catch (FileNotFoundException ex) {
			           // Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
			        }


			    
			}
		});
		panel1.add(btnmonthprint, "cell 7 0,grow");
		
		 comboBoxMonthly = new JComboBox();
		comboBoxMonthly.setFont(new Font("Monospaced", Font.PLAIN, 15));
		comboBoxMonthly.setModel(new DefaultComboBoxModel(new String[] {"All", "Regular", "Event"}));
		panel1.add(comboBoxMonthly, "cell 5 0,grow");
		
		
		
		JPanel panel_2 = new JPanel();
		pnlMonthly.add(panel_2, "cell 2 2,growx,aligny bottom");
		panel_2.setLayout(new MigLayout("", "[][grow]", "[]"));
		
		JLabel lblTotalMonth = new JLabel("TOTAL:");
		lblTotalMonth.setFont(new Font("Monospaced", Font.PLAIN, 20));
		panel_2.add(lblTotalMonth, "cell 0 0,alignx left,growy");
		
		textFieldtotalmonthly = new JTextField();
		textFieldtotalmonthly.setEnabled(false);
		textFieldtotalmonthly.setFont(new Font("Monospaced", Font.PLAIN, 23));
		textFieldtotalmonthly.setEditable(false);
		panel_2.add(textFieldtotalmonthly, "cell 1 0,grow");
		textFieldtotalmonthly.setColumns(10);
		
		JLabel lblSalesReportMonthly = new JLabel("MONTHLY SALES REPORT");
		lblSalesReportMonthly.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 20));
		pnlMonthly.add(lblSalesReportMonthly, "cell 0 0,alignx left,growy");
		
		/*END MONTHLY*/
		
		
		/*ANNUAL*/
		JPanel pnlAnnual = new JPanel();
		tpReport.addTab("Annual", null, pnlAnnual, null);
		pnlAnnual.setLayout(new MigLayout("", "[948px,grow]", "[][49px][359px,grow]"));
		
		lblSalesReportAnnual = new JLabel("ANNUAL SALES REPORT");
		lblSalesReportAnnual.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalesReportAnnual.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 20));
		pnlAnnual.add(lblSalesReportAnnual, "cell 0 0");
		
		pnlAnnualHead = new JPanel();
		pnlAnnual.add(pnlAnnualHead, "cell 0 1,grow");
		pnlAnnualHead.setLayout(new MigLayout("", "[144px][59.00px][41.00][48px][58.00px][][grow][212.00px][177px,grow]", "[35px]"));
		
		JLabel lblYear = new JLabel("YEAR : ");
		lblYear.setFont(new Font("Monospaced", Font.PLAIN, 20));
		pnlAnnualHead.add(lblYear, "flowx,cell 0 0,alignx left,growy");
		
		lblNewLabel = new JLabel("TYPE : ");
		lblNewLabel.setFont(new Font("Monospaced", Font.PLAIN, 20));
		pnlAnnualHead.add(lblNewLabel, "cell 5 0,alignx trailing");
		
		comboBoxAnnual = new JComboBox();
		comboBoxAnnual.setModel(new DefaultComboBoxModel(new String[] {"All", "Regular", "Event"}));
		pnlAnnualHead.add(comboBoxAnnual, "cell 6 0,grow");
		
		JButton btnNewButton1 = new JButton("GENERATE REPORT");
		btnNewButton1.setFont(new Font("Monospaced", Font.PLAIN, 20));
		pnlAnnualHead.add(btnNewButton1, "cell 7 0,alignx right,growy");
		
		JButton btnNewButton_1 = new JButton("PRINT REPORT");
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.setFont(new Font("Monospaced", Font.PLAIN, 20));
		pnlAnnualHead.add(btnNewButton_1, "cell 8 0,grow");
		
		Date date1 = new Date();
		LocalDate localDate = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int year  = localDate.getYear();
		
		JYearChooser yearChooserStart = new JYearChooser();
		JSpinner sampleSpinner1 = (JSpinner) yearChooserStart.getSpinner();
		((javax.swing.JTextField) sampleSpinner1.getEditor()).setEditable(false);
		yearChooserStart.getSpinner().setBounds(0, 0, 56, 35);
		yearChooserStart.setEndYear(year);
		yearChooserStart.getSpinner().setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlAnnualHead.add(yearChooserStart, "cell 0 0,alignx left,growy");

		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String type = null;
				startValue = (Integer) yearChooserStart.getValue();
				
				if (comboBoxAnnual.getSelectedItem().equals("All")){
					type = "normal_orders, event_orders";
					//header = "Regular and Event customer sales report";
				}
				else if (comboBoxAnnual.getSelectedItem().equals("Regular")){
					type = "normal_orders";
					header = "Regular customers and reservations";
				}
				else if (comboBoxAnnual.getSelectedItem().equals("Event")){
					type = "event_orders";
					header = "Event reservations";
				};
				
				viewtblAnnual(startValue, type);
				viewtotalAnnual(startValue, type);

				int rowcount = tblAnnual.getRowCount();
				//	System.out.println("daily "+rowcount);
					if(rowcount == 0)
					{
						btnNewButton_1.setEnabled(false);
						JOptionPane.showMessageDialog(null, "No Results Found.", "Error", JOptionPane.INFORMATION_MESSAGE);
					}
					else
						btnNewButton_1.setEnabled(true);
;			}
		});
			
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			try {
				Document doc = new Document();
				DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss"); df.format(new Date());
				String current = df.format(new Date());

				String filename = "ReportDaily"+current+".pdf";
				PdfWriter.getInstance(doc, new FileOutputStream("ReportAnnual"+current+".pdf"));
	            
	            Paragraph prefacedate = new Paragraph();
	            prefacedate.setAlignment(Element.ALIGN_CENTER);
	            
	            Paragraph preface = new Paragraph();
	            preface.setAlignment(Element.ALIGN_CENTER);
	            preface.add("\n");
	            preface.add("Annual Sales Report");
	            preface.add("\n");
	            preface.add(header);
	            preface.add("\n");
	            prefacedate.add("YEAR : "+startValue);
	            preface.add("\n");
	            preface.add("\n");
	            //preface.setIndentationLeft(220);
	            
	           
	            Paragraph preface1 = new Paragraph();
	            preface1.setAlignment(Paragraph.ALIGN_RIGHT);
	            preface1.setIndentationRight(50);
	            preface1.add("Total Amount : " + totalyear);
	            doc.open();
	            
	            pr = new PrintReport();
	            pr.Caller(doc, current);
	            PdfPTable pdfTable = new PdfPTable(tblAnnual.getColumnCount());
                pdfTable.setHorizontalAlignment(Element.ALIGN_CENTER);
	            pdfTable.setTotalWidth(2500);
	            //adding table headers
	            for (int i = 0; i < tblAnnual.getColumnCount(); i++) {
	                pdfTable.addCell(tblAnnual.getColumnName(i));
	            }
	            //extracting data from the JTable and inserting it to PdfPTable
	            for (int rows = 0; rows < tblAnnual.getRowCount() ; rows++) {
	                for (int cols = 0; cols < tblAnnual.getColumnCount(); cols++) {
	                    pdfTable.addCell(tblAnnual.getModel().getValueAt(rows, cols).toString());

	                }
	            }
	            
	            doc.add(prefacedate);
	            doc.add(preface);
	            doc.add(pdfTable);
	            doc.add(preface1);
	            doc.close();
	            System.out.println("done");
	            
	            try {
	            	Desktop.getDesktop().open(new File("ReportAnnual"+current+".pdf"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        } catch (DocumentException ex) {
	        	ex.printStackTrace();
	        } catch (FileNotFoundException ex) {
	        	ex.printStackTrace();
	        }

	    }
		});
		
		
		JPanel pnlAnnualBody = new JPanel();
		pnlAnnual.add(pnlAnnualBody, "cell 0 2,grow");
		pnlAnnualBody.setLayout(new MigLayout("", "[934px,grow]", "[345px,grow]"));
		
		JPanel panel_6 = new JPanel();
		pnlAnnualBody.add(panel_6, "cell 0 0,grow");
		panel_6.setLayout(new MigLayout("", "[700px][216px,grow]", "[331px,grow]"));
		
		JScrollPane scrollPanelAnnual = new JScrollPane();
		panel_6.add(scrollPanelAnnual, "cell 0 0,grow");
		
		tblAnnual = new JTable();
		tblAnnual.setEnabled(false);
		tblAnnual.setFont(new Font("Calibri", Font.PLAIN, 18));
		scrollPanelAnnual.setViewportView(tblAnnual);
		tblAnnual.getTableHeader().setFont( new Font( "Calibri" , Font.PLAIN, 18 ));
		
		panel_4 = new JPanel();
		panel_6.add(panel_4, "cell 1 0,growx,aligny bottom");
		panel_4.setLayout(new MigLayout("", "[][grow]", "[]"));
		
		JLabel lblTotal_1 = new JLabel("TOTAL:");
		panel_4.add(lblTotal_1, "cell 0 0,grow");
		lblTotal_1.setFont(new Font("Monospaced", Font.PLAIN, 20));
		
		textFieldAnnual = new JTextField();
		panel_4.add(textFieldAnnual, "cell 1 0,grow");
		textFieldAnnual.setFont(new Font("Monospaced", Font.PLAIN, 20));
		textFieldAnnual.setEnabled(false);
		textFieldAnnual.setEditable(false);
		textFieldAnnual.setColumns(10);
		
		/*END ANNUAL*/
		
	
	}

	
	public JTable getTableDaily() {
		return DailyTable;
	}
	
	public void ViewTableDaily() {
		try {
			datefrom =(String) sdf.format(dateChooser.getDate());
			c = new Connect();
			String querydaily =  "SELECT Dateo AS 'Date', Order_type AS 'Order Type', Total_Amount As TotalAmount "
					+ "from (SELECT event_orders.order_date AS 'Dateo', CONCAT('Event ',event_orders.order_type) AS 'Order_type', "
					+ "SUM(event_orders.order_total - event_orders.order_balance) AS 'Total_Amount' "
					+ "from event_orders "
					+ "WHERE ((event_orders.order_total - event_orders.order_balance) != '0') AND (event_orders.order_date = '"+datefrom+"') "
					+ "GROUP BY event_orders.order_id "
					+ "UNION ALL "
					+ "SELECT normal_orders.order_date AS 'Dateo', CONCAT('Regular ',normal_orders.order_type) AS 'Order_type', "
					+ "SUM(normal_orders.order_total - normal_orders.order_balance) AS 'Total_Amount' "
					+ "from normal_orders "
					+ "WHERE ((normal_orders.order_total - normal_orders.order_balance) != '0') AND (normal_orders.order_date = '"+datefrom+"') "
					+ "GROUP BY normal_orders.order_id) x;";
			
			//System.out.println(datefrom + " "+dateto);
			
			c.pst = c.con.prepareStatement(querydaily);
	
			//c.pst.setString(1, "T00001");
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			getTableDaily().setModel(DbUtils.resultSetToTableModel(c.rs));
			c.rs.close();
			c.pst.close(); 	
			c.con.close();
	
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
		
	}
	public void TotalDaily() {
		try {
			
			c = new Connect();
			String datefrom =(String) sdf.format(dateChooser.getDate());
			//String dateto =(String) sdf.format(dateChooser_1.getDate());
			
			
			String querydailytotal = "SELECT SUM(Total_Amount) As 'Grand Total' "
					+ "from (SELECT event_orders.order_date AS 'Dateo', CONCAT('Event ',event_orders.order_type) AS 'Order_type', "
					+ "SUM(event_orders.order_total - event_orders.order_balance) AS 'Total_Amount' "
					+ "from event_orders "
					+ "WHERE ((event_orders.order_total - event_orders.order_balance) != '0') AND (event_orders.order_date = '"+datefrom+"') "
					+ "GROUP BY event_orders.order_id "
					+ "UNION ALL "
					+ "SELECT normal_orders.order_date AS 'Dateo', CONCAT('Regular ',normal_orders.order_type) AS 'Order_type', "
					+ "SUM(normal_orders.order_total - normal_orders.order_balance) AS 'Total_Amount' "
					+ "from normal_orders "
					+ "WHERE ((normal_orders.order_total - normal_orders.order_balance) != '0') AND (normal_orders.order_date = '"+datefrom+"') "
					+ "GROUP BY normal_orders.order_id) x;";
					
			c.pst = c.con.prepareStatement(querydailytotal);
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			while(c.rs.next())
			{

				 textFieldtotaldaily.setText(Integer.toString(c.rs.getInt("Grand Total")));
				  totaldaily = Integer.toString(c.rs.getInt("Grand Total"));
				// totalsum += c.rs.getInt("TotalAmount");
				//System.out.println(totalsum);
			}
			

			// textFieldtotaldaily.setText(Integer.toString(totalsum));
			
			c.rs.close();
			c.pst.close(); 	
			c.con.close();
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	
	public void ViewTableDailyNormal() {
		try {
			datefrom =(String) sdf.format(dateChooser.getDate());
			c = new Connect();
			String querydaily = "SELECT normal_orders.order_date AS 'Date', CONCAT('Regular ',normal_orders.order_type) AS 'Order Type', "
					+ "(normal_orders.order_total - normal_orders.order_balance) AS 'Total Amount' "
					+ "from normal_orders "
					+ "WHERE ((normal_orders.order_total - normal_orders.order_balance) != '0') AND (normal_orders.order_date = '"+datefrom+"');";

			
			//System.out.println(datefrom + " "+dateto);
			
			c.pst = c.con.prepareStatement(querydaily);
			

			//c.pst.setString(1, "T00001");
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			getTableDaily().setModel(DbUtils.resultSetToTableModel(c.rs));
			c.rs.close();
			c.pst.close(); 	
			c.con.close();
			
		
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
		
	}
	
	public void TotalDailyNormal() {
		try {
			
			c = new Connect();
			String datefrom =(String) sdf.format(dateChooser.getDate());
			//String dateto =(String) sdf.format(dateChooser_1.getDate());
			
			
			String querydailytotal = "SELECT SUM(normal_orders.order_total - normal_orders.order_balance) AS 'Total Amount' "
					+ "from normal_orders "
					+ "WHERE ((normal_orders.order_total - normal_orders.order_balance) != '0') AND (normal_orders.order_date = '"+datefrom+"');";
							
			c.pst = c.con.prepareStatement(querydailytotal);
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			while(c.rs.next())
			{

				 textFieldtotaldaily.setText(Integer.toString(c.rs.getInt("Total Amount")));
				  totaldaily = Integer.toString(c.rs.getInt("Total Amount"));
				// totalsum += c.rs.getInt("TotalAmount");
				//System.out.println(totalsum);
			}
			

			// textFieldtotaldaily.setText(Integer.toString(totalsum));
			
			c.rs.close();
			c.pst.close(); 	
			c.con.close();
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
	}	
		
	public void ViewTableDailyEvent() {
		try {
			datefrom =(String) sdf.format(dateChooser.getDate());
			//dateto =(String) sdf.format(dateChooser_1.getDate());
			c = new Connect();
			String querydaily = "SELECT event_orders.order_date AS 'Date', CONCAT('Event ',event_orders.order_type) AS 'Order Type', "
					+ "(event_orders.order_total - event_orders.order_balance) AS 'Total Amount' "
					+ "from event_orders "
					+ "WHERE ((event_orders.order_total - event_orders.order_balance) != '0') "
					+ "AND (event_orders.order_date = '"+datefrom+"') "
					+ "GROUP BY event_orders.order_id;";
		
			//System.out.println(datefrom + " "+dateto);
			
			c.pst = c.con.prepareStatement(querydaily);
			

			//c.pst.setString(1, "T00001");
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			getTableDaily().setModel(DbUtils.resultSetToTableModel(c.rs));
			c.rs.close();
			c.pst.close(); 	
			c.con.close();
			
		
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
		
	}
	public void TotalDailyEvent() {
		try {
			
			c = new Connect();
			String datefrom =(String) sdf.format(dateChooser.getDate());
			//String dateto =(String) sdf.format(dateChooser_1.getDate());
			
			
			String querydailytotal = "SELECT SUM(event_orders.order_total - event_orders.order_balance) AS 'TotalAmount' "
					+ "from event_orders "
					+ "WHERE ((event_orders.order_total - event_orders.order_balance) != '0') AND (event_orders.order_date = '"+datefrom+"')";
			
			c.pst = c.con.prepareStatement(querydailytotal);
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			while(c.rs.next())
			{

				 textFieldtotaldaily.setText(Integer.toString(c.rs.getInt("TotalAmount")));
				  totaldaily = Integer.toString(c.rs.getInt("TotalAmount"));
				// totalsum += c.rs.getInt("TotalAmount");
				//System.out.println(totaldaily);
			}
			

			// textFieldtotaldaily.setText(Integer.toString(totalsum));
			
			c.rs.close();
			c.pst.close(); 	
			c.con.close();
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
	}	
	
	public JTextField getTextFieldWeek() {
		return textFieldWeek;
	}

	public JTable getTblWeek() {
		return tblWeek;
	}
	public void viewtblWeek(String datefdow, String dateldow, String type) {
		String ctype = null;
		String union = "";
		if (type == "normal_orders"){
			ctype = "Regular";
		}
		else if (type == "event_orders"){
			ctype = "Event";
		}
		else {
			ctype = "Regular";
			type = "normal_orders";
			union = "UNION SELECT DAYNAME(order_date) AS 'Day', order_date AS 'Date' , "
				+"CONCAT('Event ' , order_type) AS 'Order Type', "
				+"SUM(order_total-order_balance) AS Sales FROM event_orders "
				+"WHERE(order_date >= ? AND order_date <= ?) " 
				+"GROUP BY concat(day(order_date), order_type)";
		}
		try {
			Connect c = new Connect();
			String query =
				"SELECT DAYNAME(order_date) AS 'Day', order_date AS 'Date' , "
				+"CONCAT('"+ctype+" "+"' , order_type) AS 'Order Type', "
				+"SUM(order_total-order_balance) AS Sales FROM "+ type +" "
				+"WHERE(order_date >= ? AND order_date <= ?) " 
				+"GROUP BY concat(day(order_date), order_type)\n"
				+ union ;
			c.pst = c.con.prepareStatement(query);
			c.pst.setString(1,  datefdow);
			c.pst.setString(2,  dateldow);
				if (union != ""){
					c.pst.setString(3,  datefdow);
					c.pst.setString(4,  dateldow);
				}
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			getTblWeek().setModel(DbUtils.resultSetToTableModel(c.rs));
			
			c.rs.close();
			c.pst.close();
			c.con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void viewtotalWeek(String datefdow, String dateldow, String type) {
		if (type == "normal_orders, event_orders"){
			type = 	"(SELECT * FROM normal_orders UNION SELECT * \n"
					+"FROM event_orders)" ;
		}
		try {
			Connect c = new Connect();
			String query ="SELECT SUM(order_total-order_balance) AS 'Sales Total'  \n"
					+ "FROM "+ type +" AS TOTAL WHERE (order_date >= ? AND order_date <= ?)\n";
					
			int totalsum=0;
			c.pst = c.con.prepareStatement(query);
			c.pst.setString(1, datefdow);
			c.pst.setString(2, dateldow);
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			while(c.rs.next()) {
			totalsum += c.rs.getInt("Sales Total");
		}
			textFieldWeek.setText(Integer.toString(totalsum));
			totalweek = Integer.toString(totalsum);
			c.rs.close();
			c.pst.close();
			c.con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public JTable getTableMonthly() {
		return MonthlyTable;
	}
	
	public void ViewTableMonthlyEvent() {
		try {
			c = new Connect();

			MonthlyFilteryear =yearChooser.getValue();
			MonthlyFiltermonth =monthChooser.getMonth() + 1;
			String querymonthly = "SELECT event_orders.order_date AS 'Date', CONCAT('Event ',event_orders.order_type) AS 'Order Type', "
					+ "SUM(event_orders.order_total - event_orders.order_balance) AS 'Total Amount' "
					+ "from event_orders "
					+ "WHERE ((event_orders.order_total - event_orders.order_balance) != '0') AND "
					+ "month(event_orders.order_date) = '"+MonthlyFiltermonth+"' AND year(event_orders.order_date) = '"+MonthlyFilteryear+"' "
					+ "Group by event_orders.order_date, event_orders.order_type ;";
			
			c.pst = c.con.prepareStatement(querymonthly);
			
			//c.pst.setString(1, "T00001");
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			getTableMonthly().setModel(DbUtils.resultSetToTableModel(c.rs));
			c.rs.close();
			c.pst.close(); 	
			c.con.close();
			
		
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void TotalMonthlyEvent() {
		try {
			c = new Connect();

			MonthlyFilteryear = yearChooser.getValue();
			MonthlyFiltermonth =monthChooser.getMonth() + 1;
			String querymonthlytotal = "SELECT SUM(event_orders.order_total - event_orders.order_balance) AS 'TotalAmount' "
					+ "from event_orders "
					+ "WHERE ((event_orders.order_total - event_orders.order_balance) != '0') AND "
					+ "month(event_orders.order_date) = '"+MonthlyFiltermonth+"' AND year(event_orders.order_date) = '"+MonthlyFilteryear+"';";
				
		//	System.out.println(MonthlyFiltermonth +" sjhkjdshf");
			c.pst = c.con.prepareStatement(querymonthlytotal);
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			while (c.rs.next())
			{
				//System.out.println(c.rs);
				textFieldtotalmonthly.setText(Integer.toString(c.rs.getInt("TotalAmount")));

				  totalmonthly = Integer.toString(c.rs.getInt("TotalAmount"));

				//System.out.println(c.rs.getString("TOTAL_AMOUNT"));
			}
				
			c.rs.close();
			c.pst.close(); 	
			c.con.close();
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void ViewTableMonthlyNormal() {
		try {
			c = new Connect();

			MonthlyFilteryear =yearChooser.getValue();
			MonthlyFiltermonth =monthChooser.getMonth() + 1;
			String querymonthly = "SELECT normal_orders.order_date AS 'Date', CONCAT('Regular ',normal_orders.order_type) AS 'Order Type', "
					+ "SUM(normal_orders.order_total - normal_orders.order_balance) AS 'Total Amount' "
					+ "from normal_orders "
					+ "WHERE ((normal_orders.order_total - normal_orders.order_balance) != '0') AND "
					+ "month(normal_orders.order_date) = '"+MonthlyFiltermonth+"' AND year(normal_orders.order_date) = '"+MonthlyFilteryear+"' "
							+ "Group by normal_orders.order_date, normal_orders.order_type ;";
				
			//System.out.println(MonthlyFiltermonth + " " + MonthlyFilteryear);
			c.pst = c.con.prepareStatement(querymonthly);
			
	
			//c.pst.setString(1, "T00001");
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			getTableMonthly().setModel(DbUtils.resultSetToTableModel(c.rs));
			c.rs.close();
			c.pst.close(); 	
			c.con.close();
			
		
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
	}
	public void TotalMonthlyNormal() {
		try {
			c = new Connect();

			MonthlyFilteryear = yearChooser.getValue();
			MonthlyFiltermonth =monthChooser.getMonth() + 1;
			String querymonthlytotal = "SELECT SUM(normal_orders.order_total - normal_orders.order_balance) AS 'Grand Amount' "
					+ "from normal_orders "
					+ "WHERE ((normal_orders.order_total - normal_orders.order_balance) != '0') AND "
					+ "month(normal_orders.order_date) = '"+MonthlyFiltermonth+"' AND year(normal_orders.order_date) = '"+MonthlyFilteryear+"';";
			
			c.pst = c.con.prepareStatement(querymonthlytotal);
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			while (c.rs.next())
			{
				//System.out.println(c.rs);
				textFieldtotalmonthly.setText(Integer.toString(c.rs.getInt("Grand Amount")));

				  totalmonthly = Integer.toString(c.rs.getInt("Grand Amount"));

				//System.out.println(c.rs.getString("TOTAL_AMOUNT"));
			}
			
			c.rs.close();
			c.pst.close(); 	
			c.con.close();
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	
	public void ViewTableMonthly() {
		try {
			c = new Connect();

			MonthlyFilteryear =yearChooser.getValue();
			MonthlyFiltermonth =monthChooser.getMonth() + 1;
			String querymonthly = "SELECT Dateo as 'Date', Order_Type AS 'Order Type', Total_Amount AS 'Total Amount' "
					+ "FROM (SELECT event_orders.order_date AS 'Dateo',  CONCAT('Event ',event_orders.order_type) AS 'Order_Type', "
					+ "SUM(event_orders.order_total - event_orders.order_balance) AS 'Total_Amount' "
					+ "from event_orders "
					+ "WHERE ((event_orders.order_total - event_orders.order_balance) != '0') AND "
					+ "month(event_orders.order_date) = '"+MonthlyFiltermonth+"' AND year(event_orders.order_date) = '"+MonthlyFilteryear+"' "
					+ "GROUP BY event_orders.order_date, event_orders.order_type "
					+ "UNION "
					+ "SELECT normal_orders.order_date AS 'Dateo',  CONCAT('Regular ',normal_orders.order_type) AS 'Order_Type', "
					+ "SUM(normal_orders.order_total - normal_orders.order_balance) AS 'Total_Amount' "
					+ "from normal_orders "
					+ "WHERE ((normal_orders.order_total - normal_orders.order_balance) != '0') AND "
					+ "month(normal_orders.order_date) = '"+MonthlyFiltermonth+"' AND year(normal_orders.order_date) = '"+MonthlyFilteryear+"' "
					+ "GROUP BY normal_orders.order_date, normal_orders.order_type ) x;";
			
			c.pst = c.con.prepareStatement(querymonthly);
			
			//c.pst.setString(1, "T00001");
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			getTableMonthly().setModel(DbUtils.resultSetToTableModel(c.rs));
			c.rs.close();
			c.pst.close(); 	
			c.con.close();
			
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
	}
	public void TotalMonthly() {
		try {
			c = new Connect();

			MonthlyFilteryear = yearChooser.getValue();
			MonthlyFiltermonth =monthChooser.getMonth() + 1;
			
			String querymonthlytotal = "SELECT SUM(Total_Amount) AS 'Grand Amount' "
					+ "FROM (SELECT event_orders.order_date AS 'Dateo', event_orders.order_type AS 'Order_Type', "
					+ "SUM(event_orders.order_total - event_orders.order_balance) AS 'Total_Amount' "
					+ "from event_orders "
					+ "WHERE ((event_orders.order_total - event_orders.order_balance) != '0') AND "
					+ "month(event_orders.order_date) = '"+MonthlyFiltermonth+"' AND year(event_orders.order_date) = '"+MonthlyFilteryear+"' "
					+ "GROUP BY event_orders.order_date "
					+ "UNION "
					+ "SELECT normal_orders.order_date AS 'Dateo', normal_orders.order_type AS 'Order_Type', "
					+ "SUM(normal_orders.order_total - normal_orders.order_balance) AS 'Total_Amount' "
					+ "from normal_orders "
					+ "WHERE ((normal_orders.order_total - normal_orders.order_balance) != '0') AND "
					+ "month(normal_orders.order_date) = '"+MonthlyFiltermonth+"' AND year(normal_orders.order_date) = '"+MonthlyFilteryear+"' "
					+ "GROUP BY normal_orders.order_date ) x;";
			
			c.pst = c.con.prepareStatement(querymonthlytotal);
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			while (c.rs.next())
			{
				//System.out.println(c.rs);
				textFieldtotalmonthly.setText(Integer.toString(c.rs.getInt("Grand Amount")));

				  totalmonthly = Integer.toString(c.rs.getInt("Grand Amount"));

				//System.out.println(c.rs.getString("TOTAL_AMOUNT"));
			}
				
			c.rs.close();
			c.pst.close(); 	
			c.con.close();
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public JTextField getTextField() {
		return textFieldAnnual;
	}

	public JTable getTable_1() {
		return tblAnnual;
	}
	
	public void viewtblAnnual(int startValue, String type) {
		String ctype = null;
		String union = "";
		if (type == "normal_orders"){
			ctype = "Regular";
		}
		else if (type == "event_orders"){
			ctype = "Event";
		}
		else {
			ctype = "Regular";
			type = "normal_orders";
			union = "UNION SELECT monthname(order_date) AS 'Month' ,year(order_date) AS 'Year' , "
					+"CONCAT('Event ',order_type) AS 'Order Type', SUM(order_total-order_balance) AS Sales \n"
					+"FROM event_orders \n"
					+"WHERE year(order_date) = ? GROUP BY month(order_date), order_type \n";
			
		}
		String year = Integer.toString(startValue);
		try {
			Connect c = new Connect();
			String query ="SELECT monthname(order_date) AS 'Month' ,year(order_date) AS 'Year' , "
						+"CONCAT('"+ctype+" "+"',order_type) AS 'Order Type', SUM(order_total-order_balance) AS Sales \n"
						+"FROM "+ type +" \n"
						+"WHERE year(order_date) = ? GROUP BY month(order_date), order_type \n"
						+ union ;
			c.pst = c.con.prepareStatement(query);
			c.pst.setString(1,  year);
				if (union != ""){
					c.pst.setString(2,  year);
				}
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			getTable_1().setModel(DbUtils.resultSetToTableModel(c.rs));
			
			c.rs.close();
			c.pst.close();
			c.con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void viewtotalAnnual(int startValue, String type) {
		String year = Integer.toString(startValue);
		if (type == "normal_orders, event_orders"){
			type = 	"(SELECT * FROM normal_orders UNION SELECT * \n"
					+"FROM event_orders)" ;
		}
		try {
			Connect c = new Connect();
			String query ="SELECT SUM(order_total-order_balance) AS 'Sales Total' \n"
					+ "FROM "+ type +" AS TOTAL WHERE year(order_date) = ?";
			int totalsum=0;
			c.pst = c.con.prepareStatement(query);
			c.pst.setString(1,  year);
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			while(c.rs.next()) {
			totalsum += c.rs.getInt("Sales Total");
		}
			textFieldAnnual.setText(Integer.toString(totalsum));
			totalyear = Integer.toString(totalsum);
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
		// TODO Auto-generated method stub
		
	}
}
