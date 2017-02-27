package Capstone.View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Capstone.Controller.AddController;
import Capstone.Controller.RemoveController;
import Capstone.Model.ModelSales;
import Capstone.Table.NormalSalesTable;
import net.miginfocom.swing.MigLayout;

public class DineinOrder extends JDialog {

	public final JPanel contentPanel = new JPanel();
	public static JTable tblOrderList;
	public JScrollPane spOrderList;
	public static JTextField tfTables;
	JTextField tfName;
	public JButton cancelButton;
	public JPanel buttonPane;
	public JLabel lblName;
	public JLabel lblTable;
	public JButton btnOrder;
	public static JTextField tfOrderId;
	public static JTextField tfTotal;
	private JPanel panel;
	private JLabel lblStatus;
	public static  String reserveid;
	public static  String rtype;
	public static  String rname;
	public static  String rid;
	public static JTextField tfStatus;
	private JButton btnBill;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DineinOrder dialog = new DineinOrder();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DineinOrder() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Select customer");
		setBounds(100, 100, 654, 435);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow]", "[][grow]"));
		{
			panel = new JPanel();
			contentPanel.add(panel, "flowx,cell 0 0,grow");
			panel.setLayout(new MigLayout("", "[][grow][][grow][][grow]", "[][]"));
			{
				JLabel lblOrderId = new JLabel("Order ID:");
				panel.add(lblOrderId, "cell 0 0");
				lblOrderId.setFont(new Font("Monospaced", Font.PLAIN, 15));
			}
			{
				tfOrderId = new JTextField();
				panel.add(tfOrderId, "cell 1 0,grow");
				tfOrderId.setFont(new Font("Monospaced", Font.PLAIN, 15));
				tfOrderId.setEditable(false);
				tfOrderId.setColumns(10);
			}
			{
				lblName = new JLabel("Name:");
				panel.add(lblName, "cell 2 0");
				lblName.setFont(new Font("Monospaced", Font.PLAIN, 15));
			}
			{
				tfName = new JTextField();
				panel.add(tfName, "cell 3 0,grow");
				tfName.setFont(new Font("Monospaced", Font.PLAIN, 15));
				tfName.setEditable(false);
				tfName.setColumns(10);
			}
			{
				JLabel lblTotal = new JLabel("Total:");
				panel.add(lblTotal, "cell 4 0");
				lblTotal.setFont(new Font("Monospaced", Font.PLAIN, 15));
			}
			{
				tfTotal = new JTextField();
				panel.add(tfTotal, "cell 5 0,grow");
				tfTotal.setFont(new Font("Monospaced", Font.PLAIN, 15));
				tfTotal.setEditable(false);
				tfTotal.setColumns(10);
			}
			{
				lblTable = new JLabel("Table:");
				panel.add(lblTable, "cell 0 1");
				lblTable.setFont(new Font("Monospaced", Font.PLAIN, 15));
			}
			{
				tfTables = new JTextField();
				panel.add(tfTables, "cell 1 1,grow");
				tfTables.setEditable(false);
				tfTables.setFont(new Font("Monospaced", Font.PLAIN, 15));
				tfTables.setColumns(10);
			}
			{
				lblStatus = new JLabel("Status");
				lblStatus.setFont(new Font("Monospaced", Font.PLAIN, 15));
				panel.add(lblStatus, "cell 2 1,alignx trailing");
			}
			{
				tfStatus = new JTextField();
				tfStatus.setFont(new Font("Monospaced", Font.PLAIN, 15));
				tfStatus.setEditable(false);
				tfStatus.setColumns(10);
				panel.add(tfStatus, "cell 3 1,growx");
			}
		}
		{
			spOrderList = new JScrollPane();
			contentPanel.add(spOrderList, "cell 0 1,grow");
			{
				tblOrderList = new JTable();
				tblOrderList.setFont(new Font("Monospaced", Font.PLAIN, 15));
				spOrderList.setViewportView(tblOrderList);
			}
		}
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				{
					btnOrder = new JButton("Order");
					btnOrder.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(tfStatus.getText().equalsIgnoreCase("Paid")){
								
							}else{
								NormalSales ns = new NormalSales();
								NormalSalesTable nst = new NormalSalesTable(ns);
								ModelSales ms = new ModelSales();
								new RemoveController(ns, ms, nst);
								new AddController(ns, ms, nst);
								nst.CounterOrder();
								nst.ViewTableOrders(reserveid, rtype);
								ns.tfId.setText(reserveid);
								ns.tfName.setText(rname);
								ns.tfType.setText(rtype);
								ns.lblTakeDinein.setText("Dine-in");
								ns.tabbedPane.remove(ns.pnlTables);
								ns.checkTable = false;
								ns.tabbedPane.repaint();
								dispose();
								ns.setModal(true);
								ns.setSize(1000,666);
								ns.setVisible(true);
							}
						}
					});
					{
						btnBill = new JButton("Bill");
						btnBill.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								dispose();
								WindowNormalBillingDinein ab = new WindowNormalBillingDinein(reserveid, rtype, "Dinein", rname);
								ab.jf.setModal(true);
								ab.jf.setVisible(true);
							}
						});
						btnBill.setActionCommand("OK");
						buttonPane.add(btnBill);
					}
					btnOrder.setActionCommand("OK");
					buttonPane.add(btnOrder);
				}
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
