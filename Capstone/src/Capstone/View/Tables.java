package Capstone.View;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.metal.MetalButtonUI;
import javax.swing.table.TableModel;

import Capstone.Database.Connect;
import Capstone.Model.CounterDesk;
import Capstone.Option.StringUtil;
import net.miginfocom.swing.MigLayout;
import net.proteanit.sql.DbUtils;

public class Tables extends JPanel implements ListSelectionListener {
	private static JTable tblFloorSection;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JScrollPane spFloorSection;
	private JPanel pnlTables;
	private JPanel pnlButtons;
	private JPanel pnlForms;
	protected static Connect con;
	protected String tabletypeid;
	protected String sectionid;
	private String sectionname;
	private String tabletype;
	public static boolean boolCheck;
	private JLabel lblTableName;
	private JTextField tfTableName;
	private JLabel lblChair;
	protected String tabledesc;
	protected String roomarea;
	protected String roomcapacity;
	private JLabel lblRoomId;
	private JTextField tfTableId;
	protected String tableid;
	private JSpinner spnChair;
	protected int chair;
	private JButton btnCancel;

	/**
	 * Create the panel.
	 */
	public Tables() {
		setLayout(new MigLayout("", "[grow]", "[][][grow]"));
		
		pnlForms = new JPanel();
		add(pnlForms, "cell 0 0,grow");
		pnlForms.setLayout(new MigLayout("", "[][300px]", "[][][]"));
		
		lblRoomId = new JLabel("Table ID:");
		lblRoomId.setFont(new Font("Monospaced", Font.PLAIN, 15));
//		pnlForms.add(lblRoomId, "cell 0 0");
		
		tfTableId = new JTextField();
		tfTableId.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfTableId.setEnabled(false);
		tfTableId.setEditable(false);
		tfTableId.setColumns(10);
//		pnlForms.add(tfTableId, "cell 1 0,growx");
		
		lblTableName = new JLabel("Type of table:");
		lblTableName.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlForms.add(lblTableName, "cell 0 1");
		
		tfTableName = new JTextField();
		tfTableName.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfTableName.setColumns(10);
		pnlForms.add(tfTableName, "cell 1 1,growx");
		
		lblChair = new JLabel("Chair:");
		lblChair.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlForms.add(lblChair, "cell 0 2");
		
		spnChair = new JSpinner();
		spnChair.setFont(new Font("Monospaced", Font.PLAIN, 15));
		spnChair.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		((DefaultEditor) spnChair.getEditor()).getTextField().setEditable(false);
		spnChair.setEditor(new JSpinner.DefaultEditor(spnChair));
		pnlForms.add(spnChair, "cell 1 2,growx");
		
		pnlButtons = new JPanel();
		add(pnlButtons, "cell 0 1");
		pnlButtons.setLayout(new MigLayout("", "[grow][grow][]", "[23px,fill]"));
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tableid = tfTableId.getText();
					tabledesc = tfTableName.getText();
					chair =(int) spnChair.getValue();
					if(StringUtil.isAlphanumericWithSpaceApostropheOrHyphen(tabledesc) == true && validateEmptyString(tabledesc) == true) {
						GetTypeIdViaName();
						con = new Connect();
						con.pst = con.con.prepareCall("{call insert_tables(?,?,?)}");
						con.pst.setString(1, tableid);
						con.pst.setString(2, tabledesc);
						con.pst.setInt(3, chair);
						con.pst.execute();
						RefreshForm();
						con.pst.close();
						con.con.close();
		        	}else if(validateEmptyString(tabledesc) == false){
						JOptionPane.showMessageDialog(null, "Empty field.");						
					}else{
						JOptionPane.showMessageDialog(null, "Invalid input.");
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Table already exists..");
				}
			}
		});	
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdd.setContentAreaFilled(false);
		btnAdd.setOpaque(false);
		btnAdd.setFont(new Font("Monospaced", Font.PLAIN, 15));
		btnAdd.setIcon(new ImageIcon(Tables.class.getResource("/Images/Icon/plus.png")));
		pnlButtons.add(btnAdd, "cell 0 0,alignx center");
		
		btnUpdate = new JButton("Update");
		btnUpdate.setEnabled(false);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tableid = tfTableId.getText();
					tabledesc = tfTableName.getText();
					chair =(int) spnChair.getValue();
					if(StringUtil.isAlphanumericWithSpaceApostropheOrHyphen(tabledesc) == true && validateEmptyString(tabledesc) == true) {
						GetTypeIdViaName();
						con = new Connect();
						con.pst = con.con.prepareCall("{call insert_tables(?,?,?)}");
						con.pst.setString(1, tableid);
						con.pst.setString(2, tabledesc);
						con.pst.setInt(3, chair);
						con.pst.execute();
						RefreshForm();
						con.pst.close();
						con.con.close();
		        	}else if(validateEmptyString(tabledesc) == false){
						JOptionPane.showMessageDialog(null, "Empty field.");						
					}else{
						JOptionPane.showMessageDialog(null, "Invalid input.");
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Table already exists..");
				}
			}
		});
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setContentAreaFilled(false);
		btnUpdate.setOpaque(false);
		btnUpdate.setFont(new Font("Monospaced", Font.PLAIN, 15));
		btnUpdate.setIcon(new ImageIcon(Tables.class.getResource("/Images/Icon/remove.png")));
		pnlButtons.add(btnUpdate, "cell 1 0,alignx center");
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RefreshForm();
			}
		});
		btnCancel.setIcon(new ImageIcon(Tables.class.getResource("/Images/Icon/cancel.png")));
		btnCancel.setOpaque(false);
		btnCancel.setFont(new Font("Monospaced", Font.PLAIN, 15));
		btnCancel.setEnabled(false);
		btnCancel.setContentAreaFilled(false);
		pnlButtons.add(btnCancel, "cell 2 0");
		
		pnlTables = new JPanel();
		add(pnlTables, "cell 0 2,grow");
		pnlTables.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		spFloorSection = new JScrollPane();
		pnlTables.add(spFloorSection, "cell 0 0,grow");
		
		tblFloorSection = new JTable();
		spFloorSection.setViewportView(tblFloorSection);
		CounterDesk();
		ViewTableTables();
		DefaultButtonUI();
		ListSelectionModel selectionModel = tblFloorSection.getSelectionModel();		
		selectionModel.addListSelectionListener( this );
	}


	//Handler for list selection changes
 	public void valueChanged(ListSelectionEvent event){
		if( event.getSource() == tblFloorSection.getSelectionModel() && event.getFirstIndex() >= 0 ){
			TableModel model = (TableModel) tblFloorSection.getModel();
			int row = tblFloorSection.getSelectedRow();
			if(row!=-1){
				tabletype = model.getValueAt(row, 0).toString();
				sectionname = model.getValueAt(row, 1).toString();
				btnAdd.setEnabled(false);
				btnUpdate.setEnabled(true);
				btnCancel.setEnabled(true);
			}
		}
 	}//end of Handler for list selection changes
	/**
	 * Default UI
	 */
	public void DefaultButtonUI(){
		btnAdd.setUI(new MetalButtonUI());
		btnUpdate.setUI(new MetalButtonUI());
	}
	
	/**
	 * Refresh
	 */
	public void RefreshForm(){
		CounterDesk();
		ViewTableTables();
		TableAssignment.ViewComboboxTables();
		tfTableName.setText(null);
		spnChair.setValue(1);
		btnUpdate.setEnabled(false);
		btnCancel.setEnabled(false);
		btnAdd.setEnabled(true);
	}
	
	public void GetTypeIdViaName(){
		try {
			con = new Connect();
			con.pst = con.con.prepareCall("{call get_type_id_via_name(?)}");
			con.pst.setString(1, tabletype);
			con.pst.execute();
			con.rs = con.pst.getResultSet();
			while(con.rs.next()){
				tabletypeid = con.rs.getString("table_type_id");
			}
			con.rs.close();
			con.pst.close();
			con.con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
		public void ViewTableTables(){
				try {
					con = new Connect();
					con.pst = con.con.prepareCall("{call select_table_tables()}");
					con.pst.execute();
					con.rs = con.pst.getResultSet();
					tblFloorSection.setModel(DbUtils.resultSetToTableModel(con.rs));
					con.rs.close();
					con.pst.close();
					con.con.close();
				}catch(Exception e){
						e.printStackTrace();
				}
		}
		/**
		 * Counter floor plan
		 */
		public void CounterDesk(){
			CounterDesk cp = new CounterDesk();
			cp.getAccountNumber();
			if(!cp.getAccountNumber().isEmpty()){
				tfTableId.setText(cp.getAccountNumber());
			}
		}//end of CounterFood Method
		
		// validate empty
	   public static boolean validateEmptyString( String name ){
		   if(name.isEmpty()){
			   return false;
		   }else{
			   return true;
		   }
	   } // end method validateEmptyString
	   
	   public boolean validateName(String strname){ //Method
				if (strname.matches("[a-zA-Z '-]+")){
					return true;
			}else {
					return false;
			}
		}//Method

		public boolean isNumber(String no) {   //no only
			String regex = "\\d+";
			try {
				if(no.matches(regex)) {  
				    return true;
				}else {
					return false;
				}
			}catch(Exception e){
				return false;
			}
		}
		//Mary End
}
