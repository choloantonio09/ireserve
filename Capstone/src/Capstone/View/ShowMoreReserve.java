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

public class ShowMoreReserve extends JFrame {

	private JPanel contentPane;
	Connect c;
	String reservetype ="";
	private JTextField reservationid;
	private JTextField date;
	private JTextField time;
	private JTextField name;
	private JTextField contactno;
	private JTextField guests;
	private JTextField email;
	private JTextField type;
	private JTextField status;
	private JTextField orderid;
	private JTable orderDetailsTable;
	private JTable tableDetailsTable;
	private JLabel lblReservationDetails;
	private JLabel lblReservationId;
	private JLabel lblType;
	private JLabel lblDateReserved;
	private JLabel lblCustomerName;
	private JLabel lblContactNo;
	private JLabel lblEmailAddress;
	private JLabel lblNoOfGuests;
	private JLabel lblTimeReserved;
	private JLabel lblStatus;
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
	public ShowMoreReserve(Object reserve_id) {
		setTitle("RESERVATION DETAILS");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1019, 641);
		contentPane = new JPanel();
		//contentPane.setUI(new AquaTabbedPaneUI());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[103px][10px][152px][10px][121px][10px][152px][78px][10px][152px][9px][4px][10px][37px][10px][105px]", "[39px][40px][40px][11px][40px][10px][41px][10px][40px][24px][39px][11px][164px][39px]"));
		
		lblReservationId = new JLabel("RESERVATION ID:");
		contentPane.add(lblReservationId, "cell 0 1,grow");
		
		reservationid = new JTextField(reserve_id.toString());
		//reservationid.setText("n/a");
		reservationid.setEditable(false);
		contentPane.add(reservationid, "cell 2 1,grow");
		reservationid.setColumns(10);
		
		lblDateReserved = new JLabel("DATE RESERVED:");
		contentPane.add(lblDateReserved, "cell 0 4,grow");
		
		date = new JTextField();
		date.setText("n/a");
		date.setEditable(false);
		date.setColumns(10);
		contentPane.add(date, "cell 2 4,grow");
		
		lblTimeReserved = new JLabel("TIME RESERVED:");
		contentPane.add(lblTimeReserved, "cell 4 4,grow");
		
		time = new JTextField();
		time.setText("n/a");
		time.setEditable(false);
		time.setColumns(10);
		contentPane.add(time, "cell 6 4,grow");
		
		lblCustomerName = new JLabel("NAME:");
		contentPane.add(lblCustomerName, "cell 0 6,grow");
		
		name = new JTextField();
		name.setText("n/a");
		name.setEditable(false);
		name.setColumns(10);
		contentPane.add(name, "cell 2 6,grow");
		
		lblContactNo = new JLabel("CONTACT NO:");
		contentPane.add(lblContactNo, "cell 0 8,grow");
		
		contactno = new JTextField();
		contactno.setText("n/a");
		contactno.setEditable(false);
		contactno.setColumns(10);
		contentPane.add(contactno, "cell 2 8,grow");
		
		lblNoOfGuests = new JLabel("NO. OF GUESTS:");
		contentPane.add(lblNoOfGuests, "cell 4 6,grow");
		
		guests = new JTextField();
		guests.setText("n/a");
		guests.setEditable(false);
		guests.setColumns(10);
		contentPane.add(guests, "cell 6 6,grow");
		
		lblEmailAddress = new JLabel("EMAIL ADDRESS:");
		contentPane.add(lblEmailAddress, "cell 4 8,grow");
		
		email = new JTextField();
		email.setText("n/a");
		email.setEditable(false);
		email.setColumns(10);
		contentPane.add(email, "cell 6 8,grow");
		
		type = new JTextField();
		type.setText("n/a");
		type.setEditable(false);
		type.setColumns(10);
		contentPane.add(type, "cell 2 2,grow");
		
		lblType = new JLabel("TYPE:");
		contentPane.add(lblType, "cell 0 2,grow");
		
		status = new JTextField();
		status.setText("n/a");
		status.setEditable(false);
		status.setColumns(10);
		contentPane.add(status, "cell 6 2,grow");
		
		lblStatus = new JLabel("STATUS:");
		contentPane.add(lblStatus, "cell 4 2,grow");
		
		lblReservationDetails = new JLabel("RESERVATION DETAILS:");
		lblReservationDetails.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(lblReservationDetails, "cell 0 0 3 1,grow");
		
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
		contentPane.add(scrollPane, "cell 7 2 9 11,grow");
		
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
		contentPane.add(scrollPane_1, "cell 0 12 7 1,grow");
		
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
		contentPane.add(lblTableDetails, "cell 0 10 3 1,grow");
		
		JLabel lblTotalAmount = new JLabel("TOTAL AMOUNT:");
		lblTotalAmount.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTotalAmount.setForeground(Color.RED);
		contentPane.add(lblTotalAmount, "cell 9 13 3 1,alignx right,growy");
		
		totalamount = new JTextField();
		totalamount.setText("n/a");
		totalamount.setEditable(false);
		totalamount.setColumns(10);
		contentPane.add(totalamount, "cell 13 13 3 1,grow");
		
		lblStatus_1 = new JLabel("STATUS:");
		contentPane.add(lblStatus_1, "cell 11 1 3 1,grow");
		
		orderstatus = new JTextField();
		orderstatus.setText("n/a");
		orderstatus.setEditable(false);
		orderstatus.setColumns(10);
		contentPane.add(orderstatus, "cell 15 1,grow");
		
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
			String query = "SELECT * FROM reserve WHERE reserve.reserve_id = ?";
			c.pst = c.con.prepareStatement(query);
			c.pst.setString(1, reservationid.getText());
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			while(c.rs.next())
			{
				status.setText(c.rs.getString("reserve_status"));
				type.setText(c.rs.getString("reserve_type"));
				name.setText(c.rs.getString("reserve_name"));
				date.setText(c.rs.getString("reserve_date"));
				time.setText(c.rs.getString("reserve_time"));
				guests.setText(c.rs.getString("reserve_no_of_people"));
				contactno.setText(c.rs.getString("reserve_contact_no"));
				email.setText(c.rs.getString("reserve_email_address"));
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
			String query = "SELECT desks.desk_id AS 'DESK ID', desk_desc AS 'NAME', reserve_desks.desk_capacity AS 'CAPACITY', "
					+ "desk_time_start AS 'TIME IN', desk_time_end AS 'TIME OUT', reserve_desks.desk_status AS 'STATUS' FROM desks JOIN reserve_desks ON desks.desk_id = reserve_desks.desk_id "
					+ "WHERE reserve_desks.reserve_id = ?";
			c.pst = c.con.prepareStatement(query);
			c.pst.setString(1, reservationid.getText());
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
			String query = "SELECT * FROM reserve WHERE reserve_id = ?";
			c.pst = c.con.prepareStatement(query);
			c.pst.setString(1, reservationid.getText());
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			while(c.rs.next())
			{
				reservetype = c.rs.getString("reserve_type");
			}
			
			c.rs.close();
			c.pst.close();
			c.con.close();
			
			if(reservetype.equals("Regular"))
			{
				c = new Connect();
				String query2 = "SELECT * "
						+ "FROM normal_orders JOIN reserve_normal_orders "
						+ "ON normal_orders.order_id = reserve_normal_orders.order_id "
						+ "WHERE normal_orders.id = ?";
				c.pst = c.con.prepareStatement(query2);
				c.pst.setString(1, reservationid.getText());
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
				c.con.close(); //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -- AYAW NG ORDER DETAILS _!!!!!!!
				
				c = new Connect();
				String query3 = "SELECT food_id AS 'FOOD ID', food_name AS 'FOOD NAME', food_size AS 'SIZE', food_quantity AS 'QTY', food_price AS 'PRICE' "
						+ "FROM reserve_normal_orders "
						+ "WHERE order_id = ?";
				c.pst = c.con.prepareStatement(query3);
				c.pst.setString(1, orderid.getText());
				c.pst.execute();
				c.rs = c.pst.getResultSet();
				getOrderDetailsTable().setModel(DbUtils.resultSetToTableModel(c.rs));
				c.rs.close();
				c.pst.close();
				c.con.close();
				
				
			}
			else
			{
				c = new Connect();
				String query2 = "SELECT * "
						+ "FROM event_orders JOIN reserve_event_orders "
						+ "ON event_orders.order_id = reserve_event_orders.order_id "
						+ "WHERE event_orders.id = ?";
				c.pst = c.con.prepareStatement(query2);
				c.pst.setString(1, reservationid.getText());
				c.pst.execute();
				c.rs = c.pst.getResultSet();
				while(c.rs.next())
				{
					System.out.println(c.rs.getString("event_orders.order_id"));
					orderid.setText(c.rs.getString("event_orders.order_id"));
					orderstatus.setText(c.rs.getString("event_orders.order_status"));
					totalamount.setText(c.rs.getString("event_orders.order_total"));
				}
				
				c.rs.close();
				c.pst.close();
				c.con.close(); //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -- AYAW NG ORDER DETAILS _!!!!!!!
				
				c = new Connect();
				String query3 = "SELECT food_id AS 'FOOD ID', food_name AS 'FOOD NAME', food_size AS 'SIZE', food_quantity AS 'QTY', food_price AS 'PRICE' "
						+ "FROM reserve_event_orders "
						+ "WHERE order_id = ?";
				c.pst = c.con.prepareStatement(query3);
				c.pst.setString(1, orderid.getText());
				c.pst.execute();
				c.rs = c.pst.getResultSet();
				getOrderDetailsTable().setModel(DbUtils.resultSetToTableModel(c.rs));
				c.rs.close();
				c.pst.close();
				c.con.close();
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
