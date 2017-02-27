package Capstone.View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.plaf.metal.MetalButtonUI;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Capstone.Controller.AddController;
import Capstone.Controller.RemoveController;
import Capstone.Database.Connect;
import Capstone.Model.ModelSales;
import Capstone.Option.StringUtil;
import Capstone.Table.NormalSalesTable;
import net.miginfocom.swing.MigLayout;

public class NormalBillingDinein extends JPanel {
	public JTable tblOrders;
	public JTextField tfOrderId;
	JTextField tfId;
	private JScrollPane spOrders;
	private JPanel panel;
	public String packageNormalName;
	protected String packageName;
	protected String packageId;
	public JTextField tfTotal;
	private JLabel lblTotal;
	private JLabel lblCharge;
	public JTextField tfCharge;
	private JButton btnClose;
	private JButton btnCashOut;
	private JLabel lblOrder;
	private JLabel lblId;
	JTextField tfType;
	
	private JLabel lblType;
	protected Double cash;
	public double fixed_total;
	protected double total;
	private JLabel lblChange;
	public JTextField tfChange;
	private JButton btnEditOrder;
	String type;
	private JLabel lblName;
	JTextField tfName;
	private JPanel panel_2;
	protected PrintReport pr;
	private Connect con;
	private String otot;
	/**
	 * Create the panel.
	 */
	public NormalBillingDinein(String type) {
		this.type = type;
		setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[][grow]", "[][][][][][][]"));
		
		lblOrder = new JLabel("ORDER#:");
		lblOrder.setFont(new Font("Monospaced", Font.PLAIN, 15));
		panel.add(lblOrder, "flowx,cell 0 0");
		
		tfOrderId = new JTextField();
		tfOrderId.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfOrderId.setEditable(false);
		panel.add(tfOrderId, "cell 1 0,growx");
		tfOrderId.setColumns(10);
		
		lblType = new JLabel("Type:");
		lblType.setFont(new Font("Monospaced", Font.PLAIN, 15));
		panel.add(lblType, "cell 0 2");
		
		tfType = new JTextField();
		tfType.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfType.setEditable(false);
		tfType.setColumns(10);
		panel.add(tfType, "cell 1 2,growx");
		
		lblId = new JLabel("ID:");
		lblId.setFont(new Font("Monospaced", Font.PLAIN, 15));
		panel.add(lblId, "flowx,cell 0 1");
		
		spOrders = new JScrollPane();
		spOrders.getVerticalScrollBar().setUnitIncrement(20);
		
		tfId = new JTextField();
		tfId.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfId.setEditable(false);
		panel.add(tfId, "cell 1 1,growx");
		tfId.setColumns(10);
		
		lblName = new JLabel("Name:");
		lblName.setFont(new Font("Monospaced", Font.PLAIN, 15));
		panel.add(lblName, "cell 0 3");
		
		tfName = new JTextField();
		tfName.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfName.setEditable(false);
		tfName.setColumns(10);
		panel.add(tfName, "cell 1 3,growx");
		panel.add(spOrders, "cell 0 4 2 1,grow");
		
		tblOrders = new JTable();
		tblOrders.setRowHeight(30);
		tblOrders.setFont(new Font("Monospaced", Font.PLAIN, 15));
		spOrders.setViewportView(tblOrders);
		
		panel_2 = new JPanel();
		panel.add(panel_2, "cell 0 6 2 1,grow");
		panel_2.setLayout(new MigLayout("", "[][][grow]", "[][][][]"));
		
		lblTotal = new JLabel("Total:");
		panel_2.add(lblTotal, "cell 0 0");
		lblTotal.setFont(new Font("Monospaced", Font.PLAIN, 20));
		
		tfTotal = new JTextField();
		panel_2.add(tfTotal, "cell 1 0 2 1,growx");
		tfTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		tfTotal.setText("0.0");
		tfTotal.setEditable(false);
		tfTotal.setFont(new Font("Monospaced", Font.PLAIN, 20));
		tfTotal.setColumns(10);
				
				lblCharge = new JLabel("Cash:");
				panel_2.add(lblCharge, "cell 0 1");
				lblCharge.setFont(new Font("Monospaced", Font.PLAIN, 20));
		
				tfCharge = new JTextField();
				panel_2.add(tfCharge, "cell 1 1 2 1,growx");
				tfCharge.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent arg0) {
						if(tfCharge.getText().equalsIgnoreCase("0.0")){
							tfCharge.setText("");
						}
					}@Override
					public void focusLost(FocusEvent arg0) {
						if(tfCharge.getText().equalsIgnoreCase("")){
							tfCharge.setText("0.0");					
							tfChange.setText(String.format("%.2f", fixed_total));
							if(total > 0){
								lblChange.setText("Balance:");
							}else{
								lblChange.setText("Change:");
							}
						}
					}
				});
				tfCharge.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent evt) {
						if(!tfCharge.getText().isEmpty()){	
							cash = Double.parseDouble(tfCharge.getText());
							total = fixed_total - cash;
							tfChange.setText(String.format("%.2f", total));
							if(total > 0){
								lblChange.setText("Balance:");
							}else{
								lblChange.setText("Change:");
							}
						}
				}
				});
				tfCharge.setText("0.0");
				tfCharge.setHorizontalAlignment(SwingConstants.RIGHT);
				tfCharge.setFont(new Font("Monospaced", Font.PLAIN, 20));
				tfCharge.setColumns(10);
		
		lblChange = new JLabel("Balance:");
		panel_2.add(lblChange, "cell 0 2");
		lblChange.setFont(new Font("Monospaced", Font.PLAIN, 20));
		
		tfChange = new JTextField();
		panel_2.add(tfChange, "cell 1 2 2 1,growx");
		tfChange.setEditable(false);
		tfChange.setText("0.0");
		tfChange.setHorizontalAlignment(SwingConstants.RIGHT);
		tfChange.setFont(new Font("Monospaced", Font.PLAIN, 20));
		tfChange.setColumns(10);
		
		btnEditOrder = new JButton("EDIT ORDER");
		panel_2.add(btnEditOrder, "cell 0 3");
		btnEditOrder.setIcon(new ImageIcon(NormalBillingDinein.class.getResource("/Images/Icon/update.png")));
		btnEditOrder.setOpaque(false);
		btnEditOrder.setContentAreaFilled(false);
		btnEditOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NormalSales ns = new NormalSales();
				NormalSalesTable nst = new NormalSalesTable(ns);
				ModelSales ms = new ModelSales();
				new RemoveController(ns, ms, nst);
				new AddController(ns, ms, nst);
				nst.CounterOrder();
				nst.ViewTableOrders(tfId.getText(), "Dine-in");
				ns.tfId.setText(tfId.getText());
				ns.tfName.setText(tfName.getText());
				ns.tfType.setText(tfType.getText());
				ns.lblTakeDinein.setText("Dine-in");
				ns.tabbedPane.remove(ns.pnlTables);
				ns.checkTable = false;
				ns.tabbedPane.repaint();
				ns.setModal(true);
				ns.setSize(1000,666);
				ns.setVisible(true);
				WindowNormalBillingDinein.jf.dispose();
			}
		});
		btnEditOrder.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		btnCashOut = new JButton("Bill out");
		btnCashOut.setIcon(new ImageIcon(NormalBillingDinein.class.getResource("/Images/Icon/bill.png")));
		panel_2.add(btnCashOut, "flowx,cell 1 3");
		btnCashOut.setOpaque(false);
		btnCashOut.setContentAreaFilled(false);
		btnCashOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connect c;		
				if(Double.parseDouble(tfCharge.getText()) == 0){
					JOptionPane.showMessageDialog(null, "Please check your payment.");
				}else{
				try {					
					c = new Connect();
					c.pst = c.con.prepareCall("call update_normal_order_balance_status(?,?,?)");
					c.pst.setString(1, tfOrderId.getText());
					Double tempChange =  Double.parseDouble(tfChange.getText());
					Double tempTotal = Double.parseDouble( tfTotal.getText());
					if(tempChange > 0 && tempChange < tempTotal){
						c.pst.setString(2, tfChange.getText());
						c.pst.setString(3, "Balance");					
						JOptionPane.showMessageDialog(null, "Balance: " + tfChange.getText());
					}else if(tempChange == tempTotal){
						c.pst.setString(2, tfTotal.getText());
						c.pst.setString(3, "Unpaid");
						JOptionPane.showMessageDialog(null, "Unpaid");
					}else{
						c.pst.setString(2, "0.00");
						c.pst.setString(3, "Paid");
						JOptionPane.showMessageDialog(null, "Fully paid");
						ViewPdf();
					}
					c.pst.execute();
					WindowNormalBillingDinein.nbt.ViewTableOrders(WindowNormalBillingDinein.rid, tfType.getText());
					WindowNormalBillingDinein.jf.dispose();
					c.pst.close();
					c.con.close();
					}catch (SQLException e) {
						// TODO: handle exception
						e.printStackTrace();
				}
				}
			}
		});
		btnCashOut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCashOut.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		btnClose = new JButton("Close");
		btnClose.setIcon(new ImageIcon(NormalBillingDinein.class.getResource("/Images/Icon/exit.png")));
		panel_2.add(btnClose, "cell 2 3");
		btnClose.setOpaque(false);
		btnClose.setContentAreaFilled(false);
		btnClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WindowNormalBillingDinein.jf.dispose();
			}
		});
		btnClose.setFont(new Font("Monospaced", Font.PLAIN, 15));
		DefaultButtonColor();
	}
	public void DefaultButtonColor(){

		btnClose.setUI(new MetalButtonUI());
		btnCashOut.setUI(new MetalButtonUI());
		btnEditOrder.setUI(new MetalButtonUI());
	}
	
	public void ViewPdf(){
		//-------------------------------------------------------------------------------------------------------------------------------------------------------------
		try{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss"); 
			String curDate=df.format(new Date());
		 	Document doc = new Document();
		 	String filename = "Billing"+curDate+".pdf";
            PdfWriter.getInstance(doc, new FileOutputStream(filename));
            Paragraph preface = new Paragraph();
            preface.setAlignment(Paragraph.ALIGN_LEFT);
            preface.add("Customer: " + tfName.getText());
            preface.add("\n");
            preface.add("Receipt no: " + tfOrderId.getText());
            preface.setIndentationLeft(50);
            Paragraph preface1 = new Paragraph();
            preface1.setAlignment(Paragraph.ALIGN_RIGHT);
            preface1.setIndentationRight(50);
            doc.open();
            pr = new PrintReport();
            pr.Caller(doc, filename);
            PdfPTable pdfTable = new PdfPTable(tblOrders.getColumnCount());
            pdfTable.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfTable.setTotalWidth(2500);
            //adding table headers
            for (int i = 0; i < tblOrders.getColumnCount(); i++) {
                pdfTable.addCell(tblOrders.getColumnName(i));
            }
            //extracting data from the JTable and inserting it to PdfPTable
            for (int rows = 0; rows < tblOrders.getRowCount(); rows++) {
                for (int cols = 0; cols < tblOrders.getColumnCount(); cols++) {
                    pdfTable.addCell(tblOrders.getModel().getValueAt(rows, cols).toString());
                }
            }
            preface.add(new Paragraph(" "));
            preface.add(new Paragraph(" "));

			try {
					con = new Connect();
					con.pst = con.con.prepareCall("{call select_total_total(?)}");
					con.pst.setString(1, tfOrderId.getText());
					con.pst.execute();
					con.rs = con.pst.getResultSet();
					while(con.rs.next()){
						otot = con.rs.getString("order_total");
					}
					con.rs.close();
					con.pst.close();
					con.con.close();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "Category name already exists.");
			}
            preface1.add("Total Amount : " +String.format("%.2f", otot));
            doc.add(preface);
            doc.add(pdfTable);
            doc.add(preface1);
            doc.close();
            try {
				Desktop.getDesktop().open(new File("Billing"+curDate+".pdf"));
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
}
