package Capstone.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.metal.MetalButtonUI;
import javax.swing.table.TableModel;

import net.miginfocom.swing.MigLayout;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

import Capstone.Database.Connect;
import Capstone.Model.ModelLogin;
import Capstone.Model.ModelSize;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Cursor;

public class SectionForm extends JDialog implements ListSelectionListener {

	private final JPanel contentPanel = new JPanel();
	public JTextField tfSectionName;
	public JTable tblSection;
	private JButton btnBack;
	private JPanel buttonPane;
	private JButton btnAdd;
	public JButton btnRemove;
	ModelSize ms = new ModelSize();
	private Connect con;
	protected String name;

	public SectionForm() {
		setTitle("Manage Section");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				Room.boolCheck = false;
				Room.cbSection.setSelectedIndex(0);
			}
		});
		System.setProperty("Quaqua.tabLayoutPolicy","wrap");
        // set the Quaqua Look and Feel in the UIManager
		try {
			UIManager.setLookAndFeel(ch.randelshofer.quaqua.QuaquaManager.getLookAndFeel());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.menu);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][grow]"));
		
		JLabel lblSectionName = new JLabel("Section name:");
		lblSectionName.setFont(new Font("Monospaced", Font.PLAIN, 15));
		contentPanel.add(lblSectionName, "cell 0 0");
	
		tfSectionName = new JTextField();
		tfSectionName.setFont(new Font("Monospaced", Font.PLAIN, 15));
		contentPanel.add(tfSectionName, "cell 1 0,growx");
		tfSectionName.setColumns(10);
	
		JScrollPane spSection = new JScrollPane();
		contentPanel.add(spSection, "cell 0 1 2 1,grow");
		
		tblSection = new JTable();
		tblSection.setRowHeight(30);
		tblSection.setFont(new Font("Monospaced", Font.PLAIN, 15));
		spSection.setViewportView(tblSection);
		tblSection.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		buttonPane = new JPanel();
		buttonPane.setBackground(SystemColor.menu);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		btnAdd = new JButton("");
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdd.setOpaque(false);
		btnAdd.setContentAreaFilled(false);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name = tfSectionName.getText();
				try {
					con = new Connect();
					con.pst = con.con.prepareCall("{call insert_section(?,?,?)}");
					con.pst.setString(1, name);
					con.pst.setString(2, "Regular");
					con.pst.setString(3, "Can be deleted");
					con.pst.execute();
					RefreshForm();
					con.pst.close();
					con.con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Duplicate section.");
				}
			}
		});
		btnAdd.setIcon(new ImageIcon(SectionForm.class.getResource("/Images/Icon/save.png")));
		btnAdd.setFont(new Font("Monospaced", Font.PLAIN, 15));
		btnAdd.setActionCommand("OK");
		buttonPane.add(btnAdd);
	
		btnRemove = new JButton("");
		btnRemove.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRemove.setOpaque(false);
		btnRemove.setContentAreaFilled(false);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					con = new Connect();
					con.pst = con.con.prepareCall("{call delete_section(?,?)}");
					con.pst.setString(1, name);
					con.pst.setString(2, "Can be deleted");
					con.pst.execute();
					RefreshForm();
					con.pst.close();
					con.con.close();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Please remove all " + name +  " in the section.");
				}
			}
		});
		btnRemove.setIcon(new ImageIcon(SectionForm.class.getResource("/Images/Icon/remove.png")));
		btnRemove.setEnabled(false);			
		btnRemove.setFont(new Font("Monospaced", Font.PLAIN, 15));
		btnRemove.setActionCommand("OK");
		buttonPane.add(btnRemove);
	
		btnBack = new JButton("");
		btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBack.setOpaque(false);
		btnBack.setContentAreaFilled(false);
		btnBack.setIcon(new ImageIcon(SectionForm.class.getResource("/Images/Icon/exit.png")));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnBack.setFont(new Font("Monospaced", Font.PLAIN, 15));
		btnBack.setActionCommand("OK");
		buttonPane.add(btnBack);
		ListSelectionModel selectionModel = tblSection.getSelectionModel();		
		selectionModel.addListSelectionListener( this );
		getRootPane().setDefaultButton(btnAdd);
		ViewTableSection();
		DefaultButtonColor();
	}
	
	public void RefreshForm() {
		Room.ViewComboboxSection();
		ViewTableSection();
		tfSectionName.setText(null);
		btnRemove.setEnabled(false);
	}

	//Handler for list selection changes
 	public void valueChanged(ListSelectionEvent event){
		if( event.getSource() == tblSection.getSelectionModel() && event.getFirstIndex() >= 0 ){
			TableModel model = (TableModel)tblSection.getModel();
			int row = tblSection.getSelectedRow();
			if(row!=-1){
				name = model.getValueAt(row, 0).toString();
				tfSectionName.setText(model.getValueAt(row, 0).toString());
				btnRemove.setEnabled(true);
			}
		}
 	}//end of Handler for list selection changes
 	
	/**
	 * View Table Floor
	 */
	public void ViewTableSection(){
			try {
				con = new Connect();
				con.pst = con.con.prepareCall("{call select_table_section()}");
				con.pst.execute();
				con.rs = con.pst.getResultSet();
				tblSection.setModel(DbUtils.resultSetToTableModel(con.rs));
				con.rs.close();
				con.pst.close();
				con.con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
	}
	public void DefaultButtonColor(){
		btnAdd.setUI(new MetalButtonUI());
		btnRemove.setUI(new MetalButtonUI());
		btnBack.setUI(new MetalButtonUI());
	}
}
