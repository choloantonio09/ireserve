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

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

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
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Cursor;

public class Sizes extends JDialog implements ListSelectionListener {

	private final JPanel contentPanel = new JPanel();
	public JTextField tfSizeName;
	public JTable tblSizes;
	private JButton btnBack;
	private JPanel buttonPane;
	private JButton btnAdd;
	public JButton btnRemove;
	String normal_food_id;
	String name;
	ModelSize ms = new ModelSize();

	public Sizes(String normalfoodid, String name) {
		setTitle("Manage sizes");
		System.setProperty("Quaqua.tabLayoutPolicy","wrap");
        // set the Quaqua Look and Feel in the UIManager
		try {
			UIManager.setLookAndFeel(ch.randelshofer.quaqua.QuaquaManager.getLookAndFeel());
		} catch (Exception e) {
			e.printStackTrace();
		}
		normal_food_id = normalfoodid;
		this.name = name;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				Foods.boolCheck = true;
				if(name.equalsIgnoreCase("Normal")){
					Dashboard.nft.ViewNormalNotInSize(normal_food_id, false, null);
					Foods.cbNormalSizes.setSelectedIndex(0);
					
				}
//				else if(name.equalsIgnoreCase("Event")){
//					Dashboard.eft.ViewEventNotInSize(normal_food_id, false, null);
//					Foods.cbEventSizes.setSelectedIndex(0);
//					
//				}
			}
		});
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 299, 418);
		setModal(true);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.menu);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[59.00,grow][grow]", "[][grow]"));
		
		JLabel lblSizeName = new JLabel("Size name:");
		lblSizeName.setFont(new Font("Monospaced", Font.PLAIN, 15));
		contentPanel.add(lblSizeName, "cell 0 0");
	
		tfSizeName = new JTextField();
		tfSizeName.setFont(new Font("Monospaced", Font.PLAIN, 15));
		contentPanel.add(tfSizeName, "cell 1 0,growx");
		tfSizeName.setColumns(10);
	
		JScrollPane scrollPane = new JScrollPane();
		contentPanel.add(scrollPane, "cell 0 1 2 1,grow");
		
		tblSizes = new JTable();
		tblSizes.setRowHeight(30);
		tblSizes.setFont(new Font("Monospaced", Font.PLAIN, 15));
		scrollPane.setViewportView(tblSizes);
		tblSizes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		buttonPane = new JPanel();
		buttonPane.setBackground(SystemColor.menu);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		btnAdd = new JButton("");
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdd.setOpaque(false);
		btnAdd.setContentAreaFilled(false);
		btnAdd.setIcon(new ImageIcon(Sizes.class.getResource("/Images/Icon/save.png")));
		btnAdd.setFont(new Font("Monospaced", Font.PLAIN, 15));
		btnAdd.setActionCommand("OK");
		buttonPane.add(btnAdd);
	
		btnRemove = new JButton("");
		btnRemove.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRemove.setOpaque(false);
		btnRemove.setContentAreaFilled(false);
		btnRemove.setIcon(new ImageIcon(Sizes.class.getResource("/Images/Icon/remove.png")));
		btnRemove.setEnabled(false);			
		btnRemove.setFont(new Font("Monospaced", Font.PLAIN, 15));
		btnRemove.setActionCommand("OK");
		buttonPane.add(btnRemove);
	
		btnBack = new JButton("");
		btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBack.setOpaque(false);
		btnBack.setContentAreaFilled(false);
		btnBack.setIcon(new ImageIcon(Sizes.class.getResource("/Images/Icon/exit.png")));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnBack.setFont(new Font("Monospaced", Font.PLAIN, 15));
		btnBack.setActionCommand("OK");
		buttonPane.add(btnBack);
		getRootPane().setDefaultButton(btnBack);
		DefaultButtonColor();
		// Handle the listener
		ListSelectionModel selectionModel = tblSizes.getSelectionModel();
		selectionModel.addListSelectionListener( this );
		
	}
	
	//Handler for list selection changes
 	public void valueChanged(ListSelectionEvent event){
		if( event.getSource() == tblSizes.getSelectionModel() && event.getFirstIndex() >= 0 ){
			TableModel model = (TableModel)tblSizes.getModel();
			int row = tblSizes.getSelectedRow();
			if(row!=-1){
				tfSizeName.setText(model.getValueAt(row, 0).toString());
				btnRemove.setEnabled(true);
			}
		}
 	}//end of Handler for list selection changes
	
	public ModelSize getSizesField(){
		ms = new ModelSize();
		ms.setName(tfSizeName.getText());
		return ms;
	}

	public void add(ActionListener addSizes) {
		btnAdd.addActionListener(addSizes);		
	}
	public void remove(ActionListener addSizes) {
		btnRemove.addActionListener(addSizes);		
	}
	public void DefaultButtonColor(){
		btnAdd.setUI(new MetalButtonUI());
		btnRemove.setUI(new MetalButtonUI());
		btnBack.setUI(new MetalButtonUI());
	}
}
