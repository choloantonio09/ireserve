package Capstone.View;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.metal.MetalButtonUI;
import javax.swing.table.TableModel;

import Capstone.Database.Connect;
import Capstone.Model.CounterRoom;

import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JTextField;

public class TableType extends JPanel implements ListSelectionListener {
	private static JTable tblTable;
	private JButton btnAdd;
	private JButton btnRemove;
	private JScrollPane spTable;
	private JPanel pnlTables;
	private JPanel pnlButtons;
	private JPanel pnlForms;
	protected static Connect con;
	public static boolean boolCheck;
	private JLabel lblTableFigure;
	private JTextField tfFigure;
	protected String figure;

	/**
	 * Create the panel.
	 */
	public TableType() {
		setLayout(new MigLayout("", "[grow]", "[][][grow]"));
		
		pnlForms = new JPanel();
		add(pnlForms, "cell 0 0,grow");
		pnlForms.setLayout(new MigLayout("", "[][300px,grow]", "[]"));
		
		lblTableFigure = new JLabel("Table type:");
		lblTableFigure.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlForms.add(lblTableFigure, "cell 0 0");
		
		tfFigure = new JTextField();
		tfFigure.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfFigure.setColumns(10);
		pnlForms.add(tfFigure, "cell 1 0,growx");
		
		pnlButtons = new JPanel();
		add(pnlButtons, "cell 0 1");
		pnlButtons.setLayout(new MigLayout("", "[grow][grow]", "[23px,fill]"));
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					figure = tfFigure.getText();
					if(validateName(figure) == true && validateEmptyString(figure) == true) {
						con = new Connect();
						con.pst = con.con.prepareCall("{call insert_table_type(?)}");
						con.pst.setString(1, figure);
						con.pst.execute();
						RefreshForm();
						con.pst.close();
						con.con.close();
		        	}else if(validateEmptyString(figure) == false){
						JOptionPane.showMessageDialog(null, "Empty field");						
					}else{
						JOptionPane.showMessageDialog(null, "Invalid character");
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Duplicate type.");
				}
			}
		});
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdd.setContentAreaFilled(false);
		btnAdd.setOpaque(false);
		btnAdd.setFont(new Font("Monospaced", Font.PLAIN, 15));
		btnAdd.setIcon(new ImageIcon(TableType.class.getResource("/Images/Icon/plus.png")));
		pnlButtons.add(btnAdd, "cell 0 0,alignx center");
		
		btnRemove = new JButton("Remove");
		btnRemove.setEnabled(false);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					figure = tfFigure.getText();
					con = new Connect();
					con.pst = con.con.prepareCall("{call delete_table_type(?)}");
					con.pst.setString(1, figure);
					con.pst.execute();
					RefreshForm();
					con.pst.close();
					con.con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnRemove.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRemove.setContentAreaFilled(false);
		btnRemove.setOpaque(false);
		btnRemove.setFont(new Font("Monospaced", Font.PLAIN, 15));
		btnRemove.setIcon(new ImageIcon(TableType.class.getResource("/Images/Icon/remove.png")));
		pnlButtons.add(btnRemove, "cell 1 0,alignx center");
		
		pnlTables = new JPanel();
		add(pnlTables, "cell 0 2,grow");
		pnlTables.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		spTable = new JScrollPane();
		pnlTables.add(spTable, "cell 0 0,grow");
		
		tblTable = new JTable();
		spTable.setViewportView(tblTable);
		ViewTableFigure(); 
		DefaultButtonUI();
		ListSelectionModel selectionModel = tblTable.getSelectionModel();		
		selectionModel.addListSelectionListener( this );
	}


	//Handler for list selection changes
 	public void valueChanged(ListSelectionEvent event){
		if( event.getSource() == tblTable.getSelectionModel() && event.getFirstIndex() >= 0 ){
			TableModel model = (TableModel) tblTable.getModel();
			int row = tblTable.getSelectedRow();
			if(row!=-1){
				figure  = model.getValueAt(row, 1).toString();
				tfFigure.setText(figure); 
				btnRemove.setEnabled(true);
			}
		}
 	}//end of Handler for list selection changes
	/**
	 * Default UI
	 */
	public void DefaultButtonUI(){
		btnAdd.setUI(new MetalButtonUI());
		btnRemove.setUI(new MetalButtonUI());
	}
	
	/**
	 * Refresh
	 */
	public void RefreshForm(){
		ViewTableFigure(); 
		tfFigure.setText(null);
	}
		/**
		 * View Table Floor
		 */
		public static void ViewTableFigure(){
				try {
					con = new Connect();
					con.pst = con.con.prepareCall("{call select_table_type()}");
					con.pst.execute();
					con.rs = con.pst.getResultSet();
					tblTable.setModel(DbUtils.resultSetToTableModel(con.rs));
					con.rs.close();
					con.pst.close();
					con.con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}

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
}
