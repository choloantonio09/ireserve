package Capstone.View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.metal.MetalButtonUI;
import javax.swing.table.TableModel;

import Capstone.Controller.AddController;
import Capstone.Controller.UpdateController;
import Capstone.Model.ModelCategory;
import Capstone.Table.CategoryTable;
import net.miginfocom.swing.MigLayout;

public class Categories extends JPanel implements ListSelectionListener {
	public static JTable tblCategories;
	private JButton btnAdd;
	private JPanel pnlButtons;
	ModelCategory mc;
	private JPanel panel;
	private String id;
	private String name;
	private String status;
	protected CategoryTable ct;
	private JPanel pnlMain;
	private JTabbedPane tabbedPane;
	/**Create the panel.**/
	
	public Categories() {
		System.setProperty("Quaqua.tabLayoutPolicy","wrap");
        // set the Quaqua Look and Feel in the UIManager
		try {
			UIManager.setLookAndFeel(ch.randelshofer.quaqua.QuaquaManager.getLookAndFeel());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setVisible(true);
		setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabbedPane.setUI(new AquaTabbedPaneUI());
		add(tabbedPane, "cell 0 0,grow");
		
		pnlMain = new JPanel();
		tabbedPane.addTab("Category", null, pnlMain, null);
		pnlMain.setLayout(new MigLayout("", "[88px][grow]", "[][4.00,grow]"));
		
		pnlButtons = new JPanel();
		pnlMain.add(pnlButtons, "cell 0 0 2 1,alignx left,growy");
		pnlButtons.setLayout(new MigLayout("", "[]", "[]"));
		
		btnAdd = new JButton("Add category");
		btnAdd.setContentAreaFilled(false);
		btnAdd.setUI(new MetalButtonUI());
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CategoriesForm cf = new CategoriesForm();
				ct = new CategoryTable(cf);
				ct.CounterCategory();
				new AddController(cf, ct);
				cf.setVisible(true);
			}
		});
		btnAdd.setOpaque(false);
		btnAdd.setIcon(new ImageIcon(Categories.class.getResource("/Images/Icon/plus.PNG")));
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnlButtons.add(btnAdd, "cell 0 0");
		btnAdd.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		panel = new JPanel();
		panel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		pnlMain.add(panel, "cell 0 1 2 1,grow");
		
		JScrollPane spCategories =  new JScrollPane();
		panel.add(spCategories, "cell 0 0,grow");
		spCategories.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tblCategories = new JTable();
		tblCategories.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tblCategories.setRowHeight(30);
		spCategories.setViewportView(tblCategories);
		tblCategories.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		
		// Handle the listener
		ListSelectionModel selectionModel = tblCategories.getSelectionModel();		
		selectionModel.addListSelectionListener( this );
	}
	// Handler for list selection changes
 	public void valueChanged(ListSelectionEvent event){
		if( event.getSource() == tblCategories.getSelectionModel() && event.getFirstIndex() >= 0 ){
			TableModel model = (TableModel)tblCategories.getModel();
			int row = tblCategories.getSelectedRow();
			tblCategories.getSelectedColumn();
			if(row!=-1){
				id = model.getValueAt(row, 0).toString();
				name = model.getValueAt(row, 1).toString();
				status = model.getValueAt(row, 4).toString();
				CategoriesForm cf = new CategoriesForm();
				cf.tfId.setText(id);
				cf.tfName.setText(name);
				cf.cbStatus.setSelectedItem(status);
				new UpdateController(cf, ct);
				cf.setVisible(true);
			}
		}
 	}
}
