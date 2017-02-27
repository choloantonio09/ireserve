package Capstone.View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Capstone.Database.Connect;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import net.miginfocom.swing.MigLayout;

public class ShowMoreTakeout extends JFrame {

	private JPanel contentPane;
	Connect c;
	String reservetype ="";
	private JTextField takeoutid;
	private JTextField date;
	private JTextField time;
	private JTextField name;
	private JTextField orderid;
	private JTable orderDetailsTable;
	private JLabel lblWaitingDetails;
	private JLabel lblWaitingId;
	private JLabel lblDateReserved;
	private JLabel lblCustomerName;
	private JLabel lblTimeReserved;
	private JLabel lblOrderDetails;
	private JLabel lblOrderId;
	private JTextField totalamount;
	private JLabel lblStatus_1;
	private JTextField orderstatus;
	private JScrollPane scrollPane;

	/**
	 * Create the frame.
	 */
	public ShowMoreTakeout(Object takeout_id) {
		setResizable(false);
		setTitle("TAKE-OUT CUSTOMER DETAILS");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 617, 473);
		contentPane = new JPanel();
		//contentPane.setUI(new AquaTabbedPaneUI());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblWaitingId = new JLabel("TAKE-OUT ID:");
		lblWaitingId.setBounds(12, 54, 111, 34);
		contentPane.add(lblWaitingId);
		
		takeoutid = new JTextField(takeout_id.toString());
		takeoutid.setBounds(131, 54, 140, 34);
		//reservationid.setText("n/a");
		takeoutid.setEditable(false);
		contentPane.add(takeoutid);
		takeoutid.setColumns(10);
		
		lblDateReserved = new JLabel("DATE:");
		lblDateReserved.setBounds(351, 54, 82, 34);
		contentPane.add(lblDateReserved);
		
		date = new JTextField();
		date.setBounds(443, 54, 150, 34);
		date.setText("n/a");
		date.setEditable(false);
		date.setColumns(10);
		contentPane.add(date);
		
		lblTimeReserved = new JLabel("TIME:");
		lblTimeReserved.setBounds(351, 102, 82, 33);
		contentPane.add(lblTimeReserved);
		
		time = new JTextField();
		time.setBounds(443, 101, 150, 34);
		time.setText("n/a");
		time.setEditable(false);
		time.setColumns(10);
		contentPane.add(time);
		
		lblCustomerName = new JLabel("NAME:");
		lblCustomerName.setBounds(12, 102, 111, 34);
		contentPane.add(lblCustomerName);
		
		name = new JTextField();
		name.setBounds(131, 102, 140, 34);
		name.setText("n/a");
		name.setEditable(false);
		name.setColumns(10);
		contentPane.add(name);
		
		lblWaitingDetails = new JLabel("TAKE-OUT CUSTOMER DETAILS:");
		lblWaitingDetails.setBounds(12, 12, 259, 31);
		lblWaitingDetails.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblWaitingDetails);
		
		lblOrderDetails = new JLabel("ORDER DETAILS:");
		lblOrderDetails.setBounds(12, 146, 259, 31);
		lblOrderDetails.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblOrderDetails);
		
		lblOrderId = new JLabel("ORDER ID:");
		lblOrderId.setBounds(12, 188, 111, 34);
		contentPane.add(lblOrderId);
		
		orderid = new JTextField();
		orderid.setBounds(131, 188, 140, 34);
		orderid.setText("n/a");
		orderid.setEditable(false);
		orderid.setColumns(10);
		contentPane.add(orderid);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 233, 581, 147);
		contentPane.add(scrollPane);
		
		orderDetailsTable = new JTable(){
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        // This is how we disable editing:
		        return false;
		    }
		};
		orderDetailsTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 11));
		scrollPane.setViewportView(orderDetailsTable);
		
		JLabel lblTotalAmount = new JLabel("TOTAL AMOUNT:");
		lblTotalAmount.setBounds(297, 391, 136, 34);
		lblTotalAmount.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTotalAmount.setForeground(Color.RED);
		contentPane.add(lblTotalAmount);
		
		totalamount = new JTextField();
		totalamount.setBounds(443, 391, 150, 34);
		totalamount.setText("n/a");
		totalamount.setEditable(false);
		totalamount.setColumns(10);
		contentPane.add(totalamount);
		
		lblStatus_1 = new JLabel("STATUS:");
		lblStatus_1.setBounds(351, 188, 82, 34);
		contentPane.add(lblStatus_1);
		
		orderstatus = new JTextField();
		orderstatus.setBounds(443, 188, 150, 34);
		orderstatus.setText("n/a");
		orderstatus.setEditable(false);
		orderstatus.setColumns(10);
		contentPane.add(orderstatus);
		
		ViewReservationDetails();
		ViewOrderDetails();
	}
	
	public JTable getOrderDetailsTable() {
		return orderDetailsTable;
	}


	public void ViewReservationDetails()
	{
		try {
			c = new Connect();
			String query = "SELECT * FROM guest_takeout WHERE guest_takeout_id = ?";
			c.pst = c.con.prepareStatement(query);
			c.pst.setString(1, takeoutid.getText());
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			while(c.rs.next())
			{
				name.setText(c.rs.getString("guest_takeout_name"));
				date.setText(c.rs.getString("guest_takeout_date"));
				time.setText(c.rs.getString("guest_takeout_time"));

			}
			c.rs.close();
			c.pst.close();
			c.con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void ViewOrderDetails()
	{
		try {
			c = new Connect();
			String query2 = "SELECT * "
					+ "FROM normal_orders JOIN guest_takeout_orders "
					+ "ON normal_orders.order_id = guest_takeout_orders.order_id "
					+ "WHERE normal_orders.id = ?";
			c.pst = c.con.prepareStatement(query2);
			c.pst.setString(1, takeoutid.getText());
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			while(c.rs.next())
			{
				System.out.println(c.rs.getString("normal_orders.order_id"));
				orderid.setText(c.rs.getString("normal_orders.order_id"));
				orderstatus.setText(c.rs.getString("normal_orders.order_status"));
				totalamount.setText(c.rs.getString("normal_orders.order_total"));
			}
			
			c.rs.close();
			c.pst.close();
			c.con.close(); 
			
			c = new Connect();
			String query3 = "SELECT food_id AS 'FOOD ID', food_name AS 'FOOD NAME', food_size AS 'SIZE', food_quantity AS 'QTY', food_price AS 'PRICE' "
					+ "FROM guest_takeout_orders "
					+ "WHERE order_id = ?";
			c.pst = c.con.prepareStatement(query3);
			c.pst.setString(1, orderid.getText());
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			getOrderDetailsTable().setModel(DbUtils.resultSetToTableModel(c.rs));
			c.rs.close();
			c.pst.close();
			c.con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
