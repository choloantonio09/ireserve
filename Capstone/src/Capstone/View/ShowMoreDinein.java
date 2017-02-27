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

public class ShowMoreDinein extends JFrame {

	private JPanel contentPane;
	Connect c;
	String reservetype ="";
	private JTextField dineinid;
	private JTextField date;
	private JTextField time;
	private JTextField name;
	private JTextField orderid;
	private JTable orderDetailsTable;
	private JTable tableDetailsTable;
	private JLabel lblWaitingDetails;
	private JLabel lblWaitingId;
	private JLabel lblDateReserved;
	private JLabel lblCustomerName;
	private JLabel lblTimeReserved;
	private JLabel lblTableDetails;
	private JLabel lblOrderDetails;
	private JLabel lblOrderId;
	private JTextField totalamount;
	private JLabel lblStatus_1;
	private JTextField orderstatus;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;

	/**
	 * Create the frame.
	 */
	public ShowMoreDinein(Object dinein_id) {
		setTitle("DINE-IN CUSTOMER DETAILS");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1035, 605);
		contentPane = new JPanel();
		//contentPane.setUI(new AquaTabbedPaneUI());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[95px][20px][144px][18px][114px][13px][144px][71px][18px][162px][47px][20px][103px]", "[34px][37px][43px][22px][36px][11px][35px][16px][241px][35px]"));
		
		lblWaitingId = new JLabel("DINE-IN ID:");
		contentPane.add(lblWaitingId, "cell 0 1,grow");
		
		dineinid = new JTextField(dinein_id.toString());
		//reservationid.setText("n/a");
		dineinid.setEditable(false);
		contentPane.add(dineinid, "cell 2 1,grow");
		dineinid.setColumns(10);
		
		lblDateReserved = new JLabel("DATE:");
		contentPane.add(lblDateReserved, "cell 0 2,grow");
		
		date = new JTextField();
		date.setText("n/a");
		date.setEditable(false);
		date.setColumns(10);
		contentPane.add(date, "cell 2 2,grow");
		
		lblTimeReserved = new JLabel("TIME:");
		contentPane.add(lblTimeReserved, "cell 4 2,grow");
		
		time = new JTextField();
		time.setText("n/a");
		time.setEditable(false);
		time.setColumns(10);
		contentPane.add(time, "cell 6 2,grow");
		
		lblCustomerName = new JLabel("NAME:");
		contentPane.add(lblCustomerName, "cell 0 4,grow");
		
		name = new JTextField();
		name.setText("n/a");
		name.setEditable(false);
		name.setColumns(10);
		contentPane.add(name, "cell 2 4,grow");
		
		lblWaitingDetails = new JLabel("DINE-IN CUSTOMER DETAILS:");
		lblWaitingDetails.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblWaitingDetails, "cell 0 0 3 1,grow");
		
		lblOrderDetails = new JLabel("ORDER DETAILS:");
		lblOrderDetails.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblOrderDetails, "cell 7 0 3 1,grow");
		
		lblOrderId = new JLabel("ORDER ID:");
		contentPane.add(lblOrderId, "cell 7 1,grow");
		
		orderid = new JTextField();
		orderid.setText("n/a");
		orderid.setEditable(false);
		orderid.setColumns(10);
		contentPane.add(orderid, "cell 9 1,grow");
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 7 2 6 7,grow");
		
		orderDetailsTable = new JTable(){
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        // This is how we disable editing:
		        return false;
		    }
		};
		orderDetailsTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 11));
		scrollPane.setViewportView(orderDetailsTable);
		
		scrollPane_1 = new JScrollPane();
		contentPane.add(scrollPane_1, "cell 0 8 7 1,grow");
		
		tableDetailsTable = new JTable(){
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        // This is how we disable editing:
		        return false;
		    }
		};
		tableDetailsTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 11));
		scrollPane_1.setViewportView(tableDetailsTable);
		
		lblTableDetails = new JLabel("TABLE DETAILS:");
		lblTableDetails.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblTableDetails, "cell 0 6 3 1,grow");
		
		JLabel lblTotalAmount = new JLabel("TOTAL AMOUNT:");
		lblTotalAmount.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTotalAmount.setForeground(Color.RED);
		contentPane.add(lblTotalAmount, "cell 9 9,alignx right,growy");
		
		totalamount = new JTextField();
		totalamount.setText("n/a");
		totalamount.setEditable(false);
		totalamount.setColumns(10);
		contentPane.add(totalamount, "cell 10 9 3 1,grow");
		
		lblStatus_1 = new JLabel("STATUS:");
		contentPane.add(lblStatus_1, "cell 10 1,grow");
		
		orderstatus = new JTextField();
		orderstatus.setText("n/a");
		orderstatus.setEditable(false);
		orderstatus.setColumns(10);
		contentPane.add(orderstatus, "cell 12 1,grow");
		
		ViewReservationDetails();
		ViewTableDetails();
		ViewOrderDetails();
	}
	
	public JTable getOrderDetailsTable() {
		return orderDetailsTable;
	}

	public JTable getTableDetailsTable() {
		return tableDetailsTable;
	}


	public void ViewReservationDetails()
	{
		try {
			c = new Connect();
			String query = "SELECT * FROM guest_dinein WHERE guest_dinein_id = ?";
			c.pst = c.con.prepareStatement(query);
			c.pst.setString(1, dineinid.getText());
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			while(c.rs.next())
			{
				
				name.setText(c.rs.getString("guest_dinein_name"));
				date.setText(c.rs.getString("guest_dinein_date"));
				time.setText(c.rs.getString("guest_dinein_time"));

			}
			c.rs.close();
			c.pst.close();
			c.con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ViewTableDetails()
	{
		
		try {
			c = new Connect();
			String query = "SELECT desks.desk_id AS 'DESK ID', desk_desc AS 'NAME', guest_dinein_desks.desk_capacity AS 'CAPACITY', "
					+ "desk_time_start AS 'TIME IN', desk_time_end AS 'TIME OUT', guest_dinein_desks.desk_status AS 'STATUS' FROM desks JOIN guest_dinein_desks ON desks.desk_id = guest_dinein_desks.desk_id "
					+ "WHERE guest_dinein_desks.guest_dinein_id = ?";
			c.pst = c.con.prepareStatement(query);
			c.pst.setString(1, dineinid.getText());
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			getTableDetailsTable().setModel(DbUtils.resultSetToTableModel(c.rs));
			
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
					+ "FROM normal_orders JOIN guest_dinein_orders "
					+ "ON normal_orders.order_id = guest_dinein_orders.order_id "
					+ "WHERE normal_orders.id = ?";
			c.pst = c.con.prepareStatement(query2);
			c.pst.setString(1, dineinid.getText());
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
					+ "FROM guest_dinein_orders "
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
