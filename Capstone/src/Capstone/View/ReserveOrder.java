package Capstone.View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Capstone.Database.Connect;
import Capstone.Table.ReservationTable;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.SwingConstants;

public class ReserveOrder extends JDialog {

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
	public JButton btnSeat;
	public static JTextField tfOrderId;
	public static JTextField tfTotal;
	private JPanel panel;
	private JLabel lblStatus;
	public static  String reserveid;
	public static  String rtype;
	public static  String rname;
	public static  String rid;
	public static JTextField tfStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ReserveOrder dialog = new ReserveOrder();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ReserveOrder() {
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
					btnSeat = new JButton("Seat");
					btnSeat.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
						        Calendar cal = Calendar.getInstance();
						        cal .add(Calendar.HOUR, 1);
						        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
								Connect con = new Connect();
								con.pst = con.con.prepareCall("{call seat_reserve(?,?)}");
								con.pst.setString(1, reserveid);
								con.pst.setString(2, sdf.format(cal.getTime()));	
								con.pst.execute();
								ReservationTable rt = new ReservationTable(Selection.r);
								rt.ViewNormalReservation();
								rt.ViewEventReservation();
								rt.ViewDinein();
								con.pst.close();
								con.con.close();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}	
						}
					});
					btnSeat.setActionCommand("OK");
					buttonPane.add(btnSeat);
				}
				{
					btnOrder = new JButton("Order");
					btnOrder.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
								if(rtype.equalsIgnoreCase("Normal")){
//									WindowNormalOrderReserve ao = new WindowNormalOrderReserve(rtype, rid, "Reservation", rname);
//									ao.jf.setVisible(true);
//									ao.pnlSales.repaint();
//									ao.pnlSales.revalidate();
								}else if(rtype.equalsIgnoreCase("Event")){
									WindowEventOrderReserve ao = new WindowEventOrderReserve(rtype, rid, "Reservation", rname);
									ao.jf.setVisible(true);
									ao.pnlSales.repaint();
									ao.pnlSales.revalidate();	
								}
								dispose();
						}
					});
					btnOrder.setActionCommand("OK");
					buttonPane.add(btnOrder);
				}
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
