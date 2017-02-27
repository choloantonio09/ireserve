package Capstone.View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import net.proteanit.sql.DbUtils;
import Capstone.Database.Connect;
import  Capstone.Model.*;

import com.toedter.calendar.IDateEditor;

import javax.swing.border.LineBorder;
import javax.swing.plaf.metal.MetalButtonUI;

import org.apache.commons.validator.routines.EmailValidator;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Building extends JPanel {
	public static IDateEditor dateEditor;
	public JButton btnSave;
	public JLabel lblBuildingArea;
	JTextField tfBuildingArea;
	public JPanel pnlForm;
	public JPanel pnlBuilding;
	JPanel frame;
	public JLabel lblTable;
	public JSpinner spnTable;
	protected Connect con;
	private JTable tblBuilding;
	private JScrollPane spBuilding;
	private JPanel pnlButton;
	protected String id;
	protected String name;
	protected String area;
	/**
	 * Create the panel.
	 */
	/**
	 * 
	 */
	public Building() {

		setLayout(new MigLayout("", "[grow]", "[][][grow]"));
		
		pnlForm = new JPanel();
		add(pnlForm, "cell 0 0,grow");
		pnlForm.setLayout(new MigLayout("", "[][300px]", "[]"));
		
		lblBuildingArea = new JLabel("Area of the restaurant (Irreversible):");
		pnlForm.add(lblBuildingArea, "cell 0 0");
		lblBuildingArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		tfBuildingArea = new JTextField();
		pnlForm.add(tfBuildingArea, "cell 1 0,growx");
		tfBuildingArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfBuildingArea.setColumns(10);
		
		pnlButton = new JPanel();
		add(pnlButton, "cell 0 1");
		pnlButton.setLayout(new MigLayout("", "[grow]", "[]"));
		
		btnSave = new JButton();
		btnSave.setText("Add fixed area");
		pnlButton.add(btnSave, "cell 0 0,alignx center");
		btnSave.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				area = tfBuildingArea.getText();
				try {
					if(isNumber(area) == true){
						con = new Connect();
						con.pst = con.con.prepareCall("{call insert_building(?)}");
						con.pst.setString(1, area);
						con.pst.execute();
						RefreshForm();
						con.pst.close();
						con.con.close();
					}else{
						JOptionPane.showMessageDialog(null, "Invalid number");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSave.setContentAreaFilled(false);
		btnSave.setOpaque(false);
		btnSave.setIcon(new ImageIcon(Building.class.getResource("/Images/Icon/plus.PNG")));
		btnSave.setName("ADD");
		btnSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSave.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		pnlBuilding = new JPanel();
		add(pnlBuilding, "cell 0 2,grow");
		pnlBuilding.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		spBuilding = new JScrollPane();
		pnlBuilding.add(spBuilding, "cell 0 0,grow");
		
		tblBuilding = new JTable();
		spBuilding.setViewportView(tblBuilding);
		DefaultButtonUI();
		RefreshForm();
	}	

	/**
	 * Default UI
	 */
	public void DefaultButtonUI(){
		btnSave.setUI(new MetalButtonUI());
	}
	
	/**
	 * Refresh
	 */
	public void RefreshForm(){
		Thread thr=new Thread(new Runnable(){
			  public void run()
			  {
			      try{			    	  
			    	  ViewTableBuilding();
						Thread.sleep(0);
				   }catch(Exception e){
					   
				   }
			   }
			 }); thr.start();
	}
	
	/**
	 * View Table Floor
	 */
	public void ViewTableBuilding(){
		boolean found = false;
			try {
				con = new Connect();
				con.pst = con.con.prepareCall("{call select_table_building()}");
				con.pst.execute();
				con.rs = con.pst.getResultSet();
				while(con.rs.next()){
					btnSave.setEnabled(false);
					tfBuildingArea.setEnabled(false);
					found = true;
					area = con.rs.getString("Final area of the Restaurant");
					tfBuildingArea.setText(area);
				}
				if(!found){
					btnSave.setEnabled(true);
					tfBuildingArea.setEnabled(true);
				}
				con = new Connect();
				con.pst = con.con.prepareCall("{call select_table_building()}");
				con.pst.execute();
				con.rs = con.pst.getResultSet();
				tblBuilding.setModel(DbUtils.resultSetToTableModel(con.rs));
				con.rs.close();
				con.pst.close();
				con.con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
	}

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
}
