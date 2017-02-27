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

public class ShowMoreWaiting extends JFrame {

	private JPanel contentPane;
	Connect c;
	String reservetype ="";
	private JTextField waitingid;
	private JTextField date;
	private JTextField time;
	private JTextField name;
	private JTextField guests;
	private JTextField status;
	private JTextField orderid;
	private JTable orderDetailsTable;
	private JTable tableDetailsTable;
	private JLabel lblWaitingDetails;
	private JLabel lblWaitingId;
	private JLabel lblDateReserved;
	private JLabel lblCustomerName;
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
	public ShowMoreWaiting(Object waiting_id) {
		setTitle("WAITING CUSTOMER DETAILS");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1019, 641);
		contentPane = new JPanel();
		//contentPane.setUI(new AquaTabbedPaneUI());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[99px][15px][148px][14px][118px][14px][148px][75px][14px][148px][13px][1px][14px][33px][15px][106px]", "[37px][39px][45px][11px][38px][17px][39px][11px][38px][11px][243px][37px]"));
		
		lblWaitingId = new JLabel("WAITING ID:");
		contentPane.add(lblWaitingId, "cell 0 1,grow");
		
		waitingid = new JTextField(waiting_id.toString());
		//reservationid.setText("n/a");
		waitingid.setEditable(false);
		contentPane.add(waitingid, "cell 2 1,grow");
		waitingid.setColumns(10);
		
		lblDateReserved = new JLabel("DATE WAITED:");
		contentPane.add(lblDateReserved, "cell 0 4,grow");
		
		date = new JTextField();
		date.setText("n/a");
		date.setEditable(false);
		date.setColumns(10);
		contentPane.add(date, "cell 2 4,grow");
		
		lblTimeReserved = new JLabel("TIME WAITED:");
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
		
		lblNoOfGuests = new JLabel("NO. OF GUESTS:");
		contentPane.add(lblNoOfGuests, "cell 4 6,grow");
		
		guests = new JTextField();
		guests.setText("n/a");
		guests.setEditable(false);
		guests.setColumns(10);
		contentPane.add(guests, "cell 6 6,grow");
		
		status = new JTextField();
		status.setText("n/a");
		status.setEditable(false);
		status.setColumns(10);
		contentPane.add(status, "cell 2 2,grow");
		
		lblStatus = new JLabel("STATUS:");
		contentPane.add(lblStatus, "cell 0 2,grow");
		
		lblWaitingDetails = new JLabel("WAITING CUSTOMER DETAILS:");
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
		contentPane.add(scrollPane, "cell 7 2 9 9,grow");
		
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
		contentPane.add(scrollPane_1, "cell 0 10 7 1,grow");
		
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
		contentPane.add(lblTableDetails, "cell 0 8 3 1,grow");
		
		JLabel lblTotalAmount = new JLabel("TOTAL AMOUNT:");
		lblTotalAmount.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTotalAmount.setForeground(Color.RED);
		contentPane.add(lblTotalAmount, "cell 9 11 3 1,alignx right,growy");
		
		totalamount = new JTextField();
		totalamount.setText("n/a");
		totalamount.setEditable(false);
		totalamount.setColumns(10);
		contentPane.add(totalamount, "cell 13 11 3 1,grow");
		
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
			String query = "SELECT * FROM waiting WHERE waiting_id = ?";
			c.pst = c.con.prepareStatement(query);
			c.pst.setString(1, waitingid.getText());
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			while(c.rs.next())
			{
				status.setText(c.rs.getString("waiting_status"));
				name.setText(c.rs.getString("waiting_name"));
				date.setText(c.rs.getString("waiting_date"));
				time.setText(c.rs.getString("waiting_time"));
				guests.setText(c.rs.getString("waiting_no"));

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
			String query = "SELECT desks.desk_id AS 'DESK ID', desk_desc AS 'NAME', waiting_desks.desk_capacity AS 'CAPACITY', "
					+ "desk_time_start AS 'TIME IN', desk_time_end AS 'TIME OUT', waiting_desks.desk_status AS 'STATUS' FROM desks JOIN waiting_desks ON desks.desk_id = waiting_desks.desk_id "
					+ "WHERE waiting_desks.waiting_id = ?";
			c.pst = c.con.prepareStatement(query);
			c.pst.setString(1, waitingid.getText());
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
					+ "FROM event_orders JOIN waiting_orders "
					+ "ON event_orders.order_id = waiting_orders.order_id "
					+ "WHERE event_orders.id = ?";
			c.pst = c.con.prepareStatement(query2);
			c.pst.setString(1, waitingid.getText());
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
					+ "FROM waiting_orders "
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
