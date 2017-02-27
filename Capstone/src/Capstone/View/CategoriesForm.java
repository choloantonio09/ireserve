package Capstone.View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.MetalButtonUI;
import javax.swing.JTextField;

import java.awt.SystemColor;
import java.awt.Font;

import javax.swing.JLabel;

import Capstone.Model.ModelCategory;
import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class CategoriesForm extends JDialog {

	private JPanel contentPane;
	public JTextField tfId;
	JTextField tfName;
	private JPanel pnlButtons;
	public JButton btnSave;
	private JButton btnExit;
	private JLabel lblStatus;
	JComboBox cbStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CategoriesForm frame = new CategoriesForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CategoriesForm() {
		System.setProperty("Quaqua.tabLayoutPolicy","wrap");
        // set the Quaqua Look and Feel in the UIManager
		try {
			UIManager.setLookAndFeel(ch.randelshofer.quaqua.QuaquaManager.getLookAndFeel());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setModal(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				Categories.tblCategories.clearSelection();
			}
		});
		setSize(500, 274);
		setTitle("Categories");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[162px][727px,grow]", "[17px][27px][27px][][grow][]"));
		
		tfId = new JTextField();
		tfId.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfId.setEditable(false);
		tfId.setColumns(10);
		tfId.setBackground(SystemColor.scrollbar);
//		contentPane.add(tfId, "cell 1 1,growx,aligny top");
		
		tfName = new JTextField();
		tfName.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfName.setColumns(10);
		tfName.setBackground(SystemColor.controlHighlight);
		contentPane.add(tfName, "cell 1 2,growx,aligny top");
		
		JLabel label = new JLabel("Name of Category*:");
		label.setFont(new Font("Monospaced", Font.PLAIN, 15));
		contentPane.add(label, "cell 0 2,alignx left,aligny center");
		
		JLabel label_1 = new JLabel("Category ID:");
		label_1.setFont(new Font("Monospaced", Font.PLAIN, 15));
//		contentPane.add(label_1, "cell 0 1,grow");
		
		JLabel label_2 = new JLabel("Fields with (*) are required");
		label_2.setFont(new Font("Monospaced", Font.PLAIN, 12));
		contentPane.add(label_2, "cell 0 0 2 1,alignx center,aligny top");
		
		lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Monospaced", Font.PLAIN, 15));
		contentPane.add(lblStatus, "cell 0 3");
		
		cbStatus = new JComboBox();
		cbStatus.setModel(new DefaultComboBoxModel(new String[] {"ACTIVE", "INACTIVE"}));
		cbStatus.setFont(new Font("Monospaced", Font.PLAIN, 15));
		contentPane.add(cbStatus, "cell 1 3,growx");
		
		pnlButtons = new JPanel();
		contentPane.add(pnlButtons, "cell 0 5 2 1,alignx right,growy");
		pnlButtons.setLayout(new MigLayout("", "[][]", "[]"));
		
		btnSave = new JButton("");
		btnSave.setActionCommand("ADD");
		btnSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSave.setContentAreaFilled(false);
		btnSave.setUI(new MetalButtonUI());
		btnSave.setIcon(new ImageIcon(CategoriesForm.class.getResource("/Images/Icon/save.png")));
		pnlButtons.add(btnSave, "cell 0 0");
		
		btnExit = new JButton("");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExit.setContentAreaFilled(false);
		btnExit.setUI(new MetalButtonUI());
		btnExit.setIcon(new ImageIcon(CategoriesForm.class.getResource("/Images/Icon/exit.png")));
		pnlButtons.add(btnExit, "cell 1 0");
	}
	public void save(ActionListener save){
		btnSave.addActionListener(save);
	}	
	public ModelCategory getCategoryField(){
		ModelCategory mc = new ModelCategory();
		mc.setId(tfId.getText());
		mc.setName(tfName.getText());
		mc.setStatus((String) cbStatus.getSelectedItem());
		return mc;
	}
}
