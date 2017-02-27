package Capstone.View;

import javax.swing.JPanel;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.Color;

import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.metal.MetalButtonUI;
import javax.swing.table.TableModel;

import Capstone.Database.Connect;
import Capstone.Model.ModelCategory;

import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.util.ArrayList;

import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;

public class UserAccounts extends JPanel implements ListSelectionListener, MouseListener, FocusListener {
	public JTextField tfCategoryId;
	public JTable tblCategories;
	public JButton btnStatus;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnCancel;
	private JPanel pnlButtons;
	ModelCategory mc;
	private JLabel label;
	private JTextField tfSearch;
	private JLabel lblSearch;
	private JPanel panel;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	private JTextField tfCategoryName;
	/**Create the panel.**/
	
	public UserAccounts() {
		System.setProperty("Quaqua.tabLayoutPolicy","wrap");
        // set the Quaqua Look and Feel in the UIManager
		try {
			UIManager.setLookAndFeel(ch.randelshofer.quaqua.QuaquaManager.getLookAndFeel());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setVisible(true);
		setLayout(new MigLayout("", "[88px][grow]", "[24px][][][4.00,grow]"));
		
		tfCategoryId = new JTextField();
		tfCategoryId.setEditable(false);
		tfCategoryId.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		add(tfCategoryId, "cell 1 0,growx,aligny bottom");
		tfCategoryId.setColumns(10);
		
		JLabel lblCategoryId = new JLabel("Category ID:");
		lblCategoryId.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		add(lblCategoryId, "cell 0 0,grow");
		
		JLabel lblCategoryName = new JLabel("Name of Category:");
		lblCategoryName.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		add(lblCategoryName, "cell 0 1,alignx trailing");
		
		tfCategoryName = new JTextField();
		tfCategoryName.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		tfCategoryName.setColumns(10);
		add(tfCategoryName, "cell 1 1,growx");
		
		pnlButtons = new JPanel();
		add(pnlButtons, "cell 0 2 2 1,grow");
		pnlButtons.setLayout(new MigLayout("", "[][][][]", "[]"));
		
		btnAdd = new JButton("ADD");
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnlButtons.add(btnAdd, "cell 0 0");
		btnAdd.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		
		btnUpdate = new JButton("UPDATE");
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setEnabled(false);
		pnlButtons.add(btnUpdate, "cell 1 0");
		btnUpdate.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		
		btnCancel = new JButton("CANCEL");
		btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dashboard.btnCategories.doClick();
			}
		});
		btnCancel.setEnabled(false);
		pnlButtons.add(btnCancel, "cell 2 0");
		btnCancel.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		
		btnStatus = new JButton("ACTIVE");
		btnStatus.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnStatus.hide();
		pnlButtons.add(btnStatus, "cell 3 0");
		btnStatus.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Category Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setLayout(new MigLayout("", "[grow]", "[][grow]"));
		add(panel, "cell 0 3 2 1,grow");
		
		lblSearch = new JLabel("Search:");
		panel.add(lblSearch, "flowx,cell 0 0,alignx right");
		lblSearch.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		
		JScrollPane spCategories =  new JScrollPane();
		panel.add(spCategories, "cell 0 1,grow");
		spCategories.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tblCategories = new JTable();
		tblCategories.setRowHeight(30);
		spCategories.setViewportView(tblCategories);
		tblCategories.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		label = new JLabel("New label");
		spCategories.setColumnHeaderView(label);
		
		tfSearch = new JTextField();
		panel.add(tfSearch, "cell 0 0,alignx right");
		tfSearch.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		tfSearch.setColumns(10);
		
		// Handle the listener
		ListSelectionModel selectionModel = tblCategories.getSelectionModel();		
		selectionModel.addListSelectionListener( this );
		btnAdd.addMouseListener(this);
		btnAdd.addFocusListener(this);
		btnUpdate.addMouseListener(this);
		btnUpdate.addFocusListener(this);
		btnCancel.addMouseListener(this);
		btnCancel.addFocusListener(this);
		btnStatus.addMouseListener(this);
		btnStatus.addFocusListener(this);
		DefaultPanelColor();
		DefaultButtonColor();
		DefaultTextColor();
	}
	
	//Moving Category Values to ModelCategory
	public ModelCategory getCategoryField(){
		mc = new ModelCategory();
		mc.setId(tfCategoryId.getText());
		mc.setName(tfCategoryName.getText());
		mc.setStatus(btnStatus.getText());
		return mc;
	}

	private Color getPanelColor(){
        return SystemColor.menu;
	}
	private Color getButtonBackgroundColor(){
        return new Color(27, 12, 95);
    }
	private Color getButtonHoverBackgroundColor(){
		return new Color(194, 253, 249);
	}
	private Color getButtonForegroundColor(){
		return new Color(215, 203, 0);
	}
	private Color getButtonHoverForegroundColor(){
		return Color.BLACK;
	}
    private Color getTextFieldColor(){
		return SystemColor.controlHighlight;
    }        
    private Color getTextFieldIdColor(){
		return SystemColor.scrollbar;
    }        
	public void add(ActionListener add){
		btnAdd.addActionListener(add);
	}	
	public void update(ActionListener update){
		btnUpdate.addActionListener(update);
	}	
	public void status(ActionListener status){
		btnStatus.addActionListener(status);
	}	
	public void DefaultPanelColor(){
		setBackground(getPanelColor());
		pnlButtons.setBackground(getPanelColor());
		panel.setBackground(getPanelColor());
	}	
	public void DefaultTextColor(){
		tfCategoryId.setBackground(getTextFieldIdColor());
		tfCategoryName.setBackground(getTextFieldColor());
		tfSearch.setBackground(getTextFieldColor());
	}	
	public void DefaultButtonColor(){
		btnAdd.setBackground(getButtonBackgroundColor());
		btnAdd.setForeground(getButtonForegroundColor());
		btnAdd.setUI(new MetalButtonUI());
		
		btnUpdate.setBackground(getButtonBackgroundColor());
		btnUpdate.setForeground(getButtonForegroundColor());
		btnUpdate.setUI(new MetalButtonUI());
		
		btnCancel.setBackground(getButtonBackgroundColor());
		btnCancel.setForeground(getButtonForegroundColor());	
		btnCancel.setUI(new MetalButtonUI());
		
		btnStatus.setBackground(getButtonBackgroundColor());
		btnStatus.setForeground(getButtonForegroundColor());
		btnStatus.setUI(new MetalButtonUI());
	}	
	// Handler for list selection changes
 	public void valueChanged(ListSelectionEvent event){
		if( event.getSource() == tblCategories.getSelectionModel() && event.getFirstIndex() >= 0 ){
			TableModel model = (TableModel)tblCategories.getModel();
			int row = tblCategories.getSelectedRow();
			if(row!=-1){
				tfCategoryId.setText(model.getValueAt(row, 0).toString());
				tfCategoryName.setText(model.getValueAt(row, 1).toString());
				String status = model.getValueAt(row, 4).toString();
				btnUpdate.setEnabled(true);
				btnCancel.setEnabled(true);
				btnStatus.show();
				btnAdd.setEnabled(false);
				if(status.equals("ACTIVE"))
					btnStatus.setText("INACTIVE");
				else
					btnStatus.setText("ACTIVE");
			}
		}
 	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		e.getComponent().setBackground(getButtonHoverBackgroundColor());
		e.getComponent().setForeground(getButtonHoverForegroundColor());
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		e.getComponent().setBackground(getButtonBackgroundColor());
		e.getComponent().setForeground(getButtonForegroundColor());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		e.getComponent().setBackground(getButtonHoverBackgroundColor());
		e.getComponent().setForeground(getButtonHoverForegroundColor());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		e.getComponent().setBackground(getButtonBackgroundColor());
		e.getComponent().setForeground(getButtonForegroundColor());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

}
