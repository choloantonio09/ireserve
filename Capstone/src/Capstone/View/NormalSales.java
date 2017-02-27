package Capstone.View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.metal.MetalButtonUI;
import javax.swing.table.DefaultTableModel;

import Capstone.Database.Connect;
import Capstone.Model.CountDinein;
import Capstone.Model.CountTakeout;
import Capstone.Model.ModelSales;
import net.miginfocom.swing.MigLayout;
import net.proteanit.sql.DbUtils;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NormalSales extends JDialog implements  ListSelectionListener{
	public JTable tblOrders;
	public JTextField tfOrder;
	JTextField tfId;
	public JButton btnCancel;
	public JButton btnSettle;
	private JScrollPane scrollPane_1;
	private JPanel panel;
	private JPanel panel_1;
	private JScrollPane scrollPane;
	public JList<String> listCategories;
	private JPanel pnlItems;
	private JPanel pnlFoods;
	public String packageNormalName;
	private Connect c;
	private int iFood;
	private JButton[] btnFoods;
	private String foodName;
	private String foodId;
	private String foodPhotos;
	private String foodPackageType;
	
	protected String packageName;
	protected String packageId;
	int row = 0;
	int column = 0;
	int grid = 0;
	private JSpinner spnGrid;
	private JLabel lblViewColumnsBy;
	private JLabel lblOrder;
	private JLabel lblId;
	private JPanel[] pnlDynamic;
	private BackgroundImageApp lblFoodImage;
	private JScrollPane spFood;
	public JList listPackage;
	private JScrollPane scrollPane_3;
	File file;
	private JPanel pnlFoodSizes;
	private JLabel lblId_1;
	JTextField tfType;
	private JButton[] btnFoodSizes;
	private JSpinner[] spnFoodSizes;
	private JScrollPane spFoodSizes;
	private String sizeName;
	private String sizePackagePrice;
	protected double packagePrice;
	protected double packageTotalTemp;
	public JTextField tfTotal;
	private JLabel lblTotal;
	protected double packageTotal;
	protected int packageQuantity;
	protected String packageSize;
	String type;
	Double Total;
	private JTable tblPackage;
	private JButton btnAdd;
	private JScrollPane scrollPane_2;
	private JSpinner spinner;
	private JLabel lblQuantity;
	private JTextField tfTotalPackage;
	private JButton btnClose;
	public JLabel lblTakeDinein;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public JTextField tfName;
	private JLabel lblName;
	JTabbedPane tabbedPane;
	JPanel pnlTables;
	private JLabel lblStatus;
	private JTextField txtTable;
	private JTextField txtQuantity;
	private JPanel panel_2;
	private JTextField textField;
	private JTextField textField_1;
	public  boolean checkTable = false;
	/**
	 * Create the panel.
	 */
	public NormalSales() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				POSDesign.tblEventReserve.clearSelection();
				POSDesign.tblNormalReserve.clearSelection();
			}
		});
		setTitle("Order");
		getContentPane().setLayout(new MigLayout("", "[258.00][][grow]", "[grow]"));
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[][]", "[][][][][][grow][][][]"));
		
		lblOrder = new JLabel("ORDER#:");
		lblOrder.setFont(new Font("Monospaced", Font.PLAIN, 15));
		panel.add(lblOrder, "flowx,cell 0 0");
		
		tfOrder = new JTextField();
		tfOrder.setEditable(false);
		tfOrder.setFont(new Font("Monospaced", Font.PLAIN, 15));
		panel.add(tfOrder, "cell 1 0,growx");
		tfOrder.setColumns(10);
		
		tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setFont(new Font("Monospaced", Font.PLAIN, 15));
		panel.add(tfId, "cell 1 1,growx");
		tfId.setColumns(10);
		
		lblId_1 = new JLabel("TYPE:");
		lblId_1.setFont(new Font("Monospaced", Font.PLAIN, 15));
		panel.add(lblId_1, "flowx,cell 0 2");
		
		tfType = new JTextField();
		tfType.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfType.setColumns(10);
		panel.add(tfType, "cell 1 2,growx");
		
		lblName = new JLabel("NAME:");
		lblName.setFont(new Font("Monospaced", Font.PLAIN, 15));
		panel.add(lblName, "flowx,cell 0 3");
		
		tfName = new JTextField();
		tfName.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfName.setColumns(10);
		panel.add(tfName, "cell 1 3,growx");
		
		lblStatus = new JLabel("STATUS:");
		lblStatus.setFont(new Font("Monospaced", Font.PLAIN, 15));
		panel.add(lblStatus, "flowx,cell 0 4");
		
		lblId = new JLabel("ID:");
		lblId.setFont(new Font("Monospaced", Font.PLAIN, 15));
		panel.add(lblId, "flowx,cell 0 1");
		
		lblTakeDinein = new JLabel("DINE-IN");
		lblTakeDinein.setFont(new Font("Monospaced", Font.PLAIN, 15));
		panel.add(lblTakeDinein, "cell 1 4,alignx left,growy");
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setUI(new AquaTabbedPaneUI());
		panel.add(tabbedPane, "cell 0 5 2 1,grow");
		
		pnlTables = new JPanel();
		tabbedPane.addTab("Tables", null, pnlTables, null);
		pnlTables.setLayout(new MigLayout("", "[grow][grow,fill]", "[]"));
		
		txtTable = new JTextField();
		txtTable.setEnabled(false);
		pnlTables.add(txtTable, "cell 0 0,growx");
		txtTable.setHorizontalAlignment(SwingConstants.CENTER);
		txtTable.setText("TABLE");
		txtTable.setFont(new Font("Monospaced", Font.PLAIN, 15));
		txtTable.setEditable(false);
		txtTable.setColumns(10);
		
		txtQuantity = new JTextField();
		txtQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		txtQuantity.setEnabled(false);
		pnlTables.add(txtQuantity, "cell 1 0,alignx center");
		txtQuantity.setText("CHAIR");
		txtQuantity.setFont(new Font("Monospaced", Font.PLAIN, 15));
		txtQuantity.setEditable(false);
		txtQuantity.setColumns(10);
		
		scrollPane_1 = new JScrollPane();
		tabbedPane.addTab("Orders", null, scrollPane_1, null);
		scrollPane_1.getVerticalScrollBar().setUnitIncrement(20);
		
		tblOrders = new JTable();
		tblOrders.setRowHeight(30);
		tblOrders.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tblOrders.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Size", "Quantity", "Price"
			}
		));
		scrollPane_1.setViewportView(tblOrders);
		
		
		lblTotal = new JLabel("TOTAL:");
		lblTotal.setFont(new Font("Monospaced", Font.PLAIN, 25));
		panel.add(lblTotal, "flowx,cell 0 6");
		
		tfTotal = new JTextField();
		tfTotal.setEditable(false);
		tfTotal.setText("0.0");
		tfTotal.setFont(new Font("Monospaced", Font.PLAIN, 25));
		panel.add(tfTotal, "cell 1 6,growx");
		tfTotal.setColumns(10);
		
		panel_2 = new JPanel();
		panel.add(panel_2, "flowx,cell 0 7 2 1,growx");
		panel_2.setLayout(new MigLayout("", "[][][]", "[]"));
		
		btnSettle = new JButton("Add");
		btnSettle.setOpaque(false);
		btnSettle.setContentAreaFilled(false);
		panel_2.add(btnSettle, "cell 0 0");
		btnSettle.setIcon(new ImageIcon(NormalSales.class.getResource("/Images/Icon/plus.png")));
		btnSettle.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSettle.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		btnCancel = new JButton("Back");
		btnCancel.setOpaque(false);
		btnCancel.setContentAreaFilled(false);
		panel_2.add(btnCancel, "cell 1 0");
		btnCancel.setEnabled(false);
		btnCancel.setIcon(new ImageIcon(NormalSales.class.getResource("/Images/Icon/cancel.png")));
		btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnSettle.getText().equalsIgnoreCase("Add") || btnSettle.getText().equalsIgnoreCase("Remove")){
						tblOrders.clearSelection();
						btnSettle.setText(btnSettle.getName());
						btnCancel.setEnabled(false);						
				}else if(btnSettle.getText().equalsIgnoreCase("Update")){
						tblOrders.clearSelection();
						btnSettle.setText(btnSettle.getName());
						btnCancel.setEnabled(false);	
				}
				POSDesign.tblEventReserve.clearSelection();
				POSDesign.tblNormalReserve.clearSelection();
			}
		});
		btnCancel.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		btnClose = new JButton("Close");
		btnClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClose.setOpaque(false);
		btnClose.setContentAreaFilled(false);
		panel_2.add(btnClose, "cell 2 0");
		btnClose.setIcon(new ImageIcon(NormalSales.class.getResource("/Images/Icon/exit.png")));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnClose.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(panel_1, "cell 1 0,grow");
		panel_1.setLayout(new MigLayout("", "[grow]", "[grow][]"));
		
		scrollPane = new JScrollPane();
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		panel_1.add(scrollPane, "cell 0 0,grow");
		
		listCategories = new JList();
		listCategories.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				Thread thr=new Thread(new Runnable(){
					  public void run()
					  {
					      try{
						    	  if (listCategories.getFirstVisibleIndex() == listCategories.getSelectedIndex()) {
						    		  listPackage.clearSelection();
						    		  packageNormalName = (String) listCategories.getSelectedValue();	
						    		  pnlFoodSizes.removeAll();
						    		  ViewFoods("","Categories");
						    		  
						    	  }else{
						    		  listPackage.clearSelection();
						    		  packageNormalName = (String) listCategories.getSelectedValue();	
						    		  pnlFoodSizes.removeAll();
						    		  ViewFoods(packageNormalName,"Categories");
						    	  }
								Thread.sleep(0);
						   }catch(Exception e){
							   
						   }
					   }
					 }); thr.start();
			}
		});
		listCategories.setFont(new Font("Monospaced", Font.PLAIN, 15));
		scrollPane.setViewportView(listCategories);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.getVerticalScrollBar().setUnitIncrement(20);
		panel_1.add(scrollPane_3, "cell 0 1,grow");
		
		listPackage = new JList();
		listPackage.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				Thread thr=new Thread(new Runnable(){
					  public void run()
					  {
					      try{
					    	  listCategories.clearSelection();
					    	  packageNormalName = (String) listPackage.getSelectedValue();	
					    	  pnlFoodSizes.removeAll();
					    	  ViewFoods(packageNormalName,"Package");
								Thread.sleep(0);
						   }catch(Exception e){
							   
						   }
					   }
					 }); thr.start();
			}
		});
		scrollPane_3.setViewportView(listPackage);
		listPackage.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		pnlItems = new JPanel();
		pnlItems.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(pnlItems, "cell 2 0,grow");
		pnlItems.setLayout(new MigLayout("", "[grow][grow]", "[][300px:n:500px][grow]"));
		
		lblViewColumnsBy = new JLabel("VIEW COLUMNS BY:");
		lblViewColumnsBy.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlItems.add(lblViewColumnsBy, "flowx,cell 0 0,alignx left");
		
		spnGrid = new JSpinner();
		spnGrid.setFont(new Font("Monospaced", Font.PLAIN, 15));
		spnGrid.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				try {
					c = new Connect();
					c.pst = c.con.prepareCall("call update_grid(?)");
					c.pst.setInt(1, (int) spnGrid.getValue());
					c.pst.execute();
					c.pst.close();
					c.con.close();
					ViewFoods("", "Categories");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		ViewColums();
		((DefaultEditor) spnGrid.getEditor()).getTextField().setEditable(false);
		spnGrid.setEditor(new JSpinner.DefaultEditor(spnGrid));
		pnlItems.add(spnGrid, "cell 0 0,alignx left");
		
		spFood = new JScrollPane();
		spFood.getVerticalScrollBar().setUnitIncrement(20);
		spFood.setAutoscrolls(true);
		pnlItems.add(spFood, "cell 0 1 2 1,grow");
		
		pnlFoods = new JPanel();
		spFood.setViewportView(pnlFoods);
		pnlFoods.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlFoods.setLayout(new MigLayout("", "[grow]", "[]"));
		
		spFoodSizes = new JScrollPane();
		spFoodSizes.getVerticalScrollBar().setUnitIncrement(20);
		pnlItems.add(spFoodSizes, "cell 0 2 2 1,grow");
		
		pnlFoodSizes = new JPanel();
		spFoodSizes.setViewportView(pnlFoodSizes);
		pnlFoodSizes.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlFoodSizes.setLayout(new MigLayout("", "[grow][grow]", "[]"));
		
		ViewFoods("","Categories");
		DefaultButtonColor();
		ListSelectionModel selectionModel = tblOrders.getSelectionModel();
		selectionModel.addListSelectionListener( this );
	}

	//Moving Category Values to ModelCategory
	public ModelSales getFieldData(){
		ModelSales ms = new ModelSales();
		ms.setId(tfId.getText());
		ms.setOrderId(tfOrder.getText());
		ms.setType(tfType.getText());
		ms.setName(tfName.getText());
		ms.setTotal(Double.parseDouble(tfTotal.getText()));
		ms.setStatus(lblTakeDinein.getText());
		return ms;
	}
	
	public void DefaultButtonColor(){

		btnCancel.setUI(new MetalButtonUI());
		btnSettle.setUI(new MetalButtonUI());
		btnClose.setUI(new MetalButtonUI());

	}	
	 public void ViewColums(){
		 int grid_value = 1;
		 try {
				c = new Connect();
				c.pst = c.con.prepareCall("call select_grid()");
				c.pst.execute();
				c.rs = c.pst.getResultSet();
				while(c.rs.next()){
					grid_value = c.rs.getInt("grid_value");
				}
				spnGrid.setModel(new SpinnerNumberModel(grid_value, 1, 5, 1));	
				c.pst.close();
				c.con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	 
	 }
	 public void ViewFoods(String categoryName, String listSelect){
		 	column = 0;
	    	iFood = 1;
			try {
				c = new Connect();
				c.pst = c.con.prepareCall("{call count_normal_foods()}");
			c.pst.execute();	
			c.rs = c.pst.getResultSet();
			int count = 0;
			while(c.rs.next()){
				count = c.rs.getInt("COUNT");				
			}
			btnFoods = new JButton[count+1];
			pnlDynamic = new JPanel[count+1];
			
			c= new Connect();
			if(listSelect.equalsIgnoreCase("Categories")){
				c.pst = c.con.prepareCall("{call select_normal_food_via_category(?)}");				
			}else if(listSelect.equalsIgnoreCase("Package")){
				c.pst = c.con.prepareCall("{call select_normal_package_food_via_category(?)}");						
			}
			c.pst.setString(1, categoryName);
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			pnlFoods.removeAll();
			while(c.rs.next()){

				if(listSelect.equalsIgnoreCase("Categories")){
					foodName = c.rs.getString("normal_food_name");
					foodId = c.rs.getString("normal_food_id");
					file = new File(c.rs.getString("normal_food_photos"));	
					foodPackageType = c.rs.getString("normal_food_type");	
					btnFoods[iFood] = new JButton(foodName);
				}else if(listSelect.equalsIgnoreCase("Package")){
					foodName = c.rs.getString("Name");
					foodId = c.rs.getString("ID");
					packageName = c.rs.getString("Name");
					packageId = c.rs.getString("ID");
					packageSize = c.rs.getString("Serving size");
					file = new File(c.rs.getString("Photos"));			
					foodPackageType = c.rs.getString("Type");			
					Total = c.rs.getDouble("Total");	
					btnFoods[iFood] = new JButton(foodName +" [ "+ Total +" ]");
				}
				
				//-------------------------------------------------

				//Set Icon
				
				foodPhotos = file.getAbsoluteFile().getAbsolutePath();
				lblFoodImage = new BackgroundImageApp(file);
				
				pnlDynamic[iFood] = new JPanel();
				pnlDynamic[iFood].setBorder(new LineBorder(new Color(0, 0, 0)));
				pnlDynamic[iFood].setPreferredSize(new Dimension(100,100));
				pnlDynamic[iFood].setLayout(new MigLayout("", "[]", "[]"));
				pnlDynamic[iFood].add(lblFoodImage, "cell 0 0 3 1,alignx center");
				
				//--------------------------------------------------
				
				btnFoods[iFood].setText(foodName);
				btnFoods[iFood].setFont(new Font("Monospaced", Font.PLAIN, 12));
				btnFoods[iFood].setActionCommand(foodId);
				if(listSelect.equalsIgnoreCase("Categories")){
					btnFoods[iFood].setName(foodName);;
				}else if(listSelect.equalsIgnoreCase("Package")){
					btnFoods[iFood].setName(packageName);;					
				}
				btnFoods[iFood].setToolTipText(String.valueOf(Total));
				btnFoods[iFood].setUI(new MetalButtonUI());
				btnFoods[iFood].setOpaque(false);
				btnFoods[iFood].setContentAreaFilled(false);
				btnFoods[iFood].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnFoods[iFood].setIcon(new ImageIcon(NormalSales.class.getResource("/Images/Icon/plus.png")));

				
				pnlDynamic[iFood].add(btnFoods[iFood], "cell 0 1 3 1");
				
				if(iFood % (int)spnGrid.getValue() == 0){
					pnlFoods.add(pnlDynamic[iFood], "cell 0 "+column+++"");
					
				}else{
					pnlFoods.add(pnlDynamic[iFood], "cell 0 "+column+" ");
				}
				
				//Category Button Action Listener
				btnFoods[iFood].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
		            	for(int j = 1; j < iFood; j++){
							if(e.getActionCommand().equalsIgnoreCase(btnFoods[j].getActionCommand())){	
								if(listSelect.equalsIgnoreCase("Categories")){
									foodName = btnFoods[j].getName();
									foodId = btnFoods[j].getActionCommand();
									ViewFoodSizes(foodId);
									
								}else if(listSelect.equalsIgnoreCase("Package")){
									packageName = btnFoods[j].getName();	
									packageId = btnFoods[j].getActionCommand();
									ViewPackageData(packageId, packageName, btnFoods[j].getToolTipText());
								}
							}
		            	}
					}
				  });
				//End of Category Button Action Listener			
				pnlDynamic[iFood].updateUI();
				pnlDynamic[iFood].repaint();
				pnlDynamic[iFood].revalidate();		
				iFood++;
			}
			pnlItems.repaint();
			pnlItems.revalidate();
			pnlFoods.repaint();
			pnlFoods.revalidate();
			c.rs.close();
			c.pst.close();
			c.con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}	
	 public void ViewPackageData(String pid, String pname, String total){
		 pnlFoodSizes.removeAll();
			pnlFoodSizes.setLayout(new MigLayout("", "[][grow,fill][]", "[][grow]"));
			
			scrollPane_2 = new JScrollPane();
			scrollPane_2.getVerticalScrollBar().setUnitIncrement(20);
			pnlFoodSizes.add(scrollPane_2, "cell 0 1 3 1,grow");
			
			tblPackage = new JTable();
			scrollPane_2.setViewportView(tblPackage);
			
			try {
				Connect c = new Connect();
				c.pst = c.con.prepareCall("call select_all_normal_food_package(?)");
				c.pst.setString(1, pname);
				c.pst.execute();
				c.rs = c.pst.getResultSet();
				tblPackage.setModel(DbUtils.resultSetToTableModel(c.rs));
				c.rs.close();
				c.pst.close();
				c.con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			lblQuantity = new JLabel("Quantity:");
			pnlFoodSizes.add(lblQuantity, "flowx,cell 1 1,grow");
			
			spinner = new JSpinner();			
			spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			((DefaultEditor) spinner.getEditor()).getTextField().setEditable(false);
			spinner.setEditor(new JSpinner.DefaultEditor(spinner));
			pnlFoodSizes.add(spinner, "cell 1 0,grow");
			
			btnAdd = new JButton("Add");
			btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnAdd.setIcon(new ImageIcon(NormalSales.class.getResource("/Images/Icon/plus.png")));
			btnAdd.setOpaque(false);
			btnAdd.setContentAreaFilled(false);
			btnAdd.setUI(new MetalButtonUI());
			btnAdd.setActionCommand(pid);
			pnlFoodSizes.add(btnAdd, "cell 0 0,grow");

			for(int j = 1; j < iFood; j++){
				if(btnFoods[j].getToolTipText().equalsIgnoreCase(total)){
					tfTotalPackage = new JTextField(String.valueOf(total));
				}
        	}
			tfTotalPackage.setEditable(false);
			tfTotalPackage.setFont(new Font("Monospaced", Font.PLAIN, 15));
			tfTotalPackage.setColumns(10);
			pnlFoodSizes.add(tfTotalPackage, "cell 2 0,grow");
			
			spinner.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
					if(e.getSource() == spinner){	
						packagePrice = (Double.parseDouble(total) * (int) spinner.getValue());
						tfTotalPackage.setText(String.valueOf(packagePrice));
					}
            	}
			});
			
			//Category Button Action Listener
			btnAdd.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					packageTotalTemp = Double.parseDouble(tfTotal.getText());
					packageTotal = 0.00;
						if(e.getActionCommand().equalsIgnoreCase(btnAdd.getActionCommand())){	
							if (checkTable == true) {
								tabbedPane.setSelectedIndex(1);
							}
							DefaultTableModel model = (DefaultTableModel) tblOrders.getModel();
							packagePrice = (Double.parseDouble(total) * (int) spinner.getValue());	
							packageQuantity = (int) spinner.getValue();
							Object[] row = { packageId, packageName, packageSize, packageQuantity, packagePrice };
							model.addRow(row);
							packageTotal = packageTotalTemp + packagePrice;
							
							tfTotal.setText(String.valueOf(packageTotal));
						}
				}
			  });
			//End of Category Button Action Listener			
			
			pnlFoodSizes.updateUI();
			pnlFoodSizes.repaint();
			pnlFoodSizes.revalidate();
			
	 }
	 
	 
	 public void ViewFoodSizes(String foodId){
		 int cellY = 1;
	 	row = 0;

		pnlFoodSizes.setLayout(new MigLayout("", "[grow]", "[]"));
		try {
			c = new Connect();
			c.pst = c.con.prepareCall("{call count_sizes()}");
			c.pst.execute();	
			c.rs = c.pst.getResultSet();
			int count = 0;
			while(c.rs.next()){
				count = c.rs.getInt("COUNT");				
			}
			btnFoodSizes = new JButton[count+1];
			spnFoodSizes = new JSpinner[count+1];
			c.rs.close();
			c.pst.close();
			c.con.close();
			
			c= new Connect();
			c.pst = c.con.prepareCall("{call select_size_via_normal_food(?)}");
			c.pst.setString(1, foodId);
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			pnlFoodSizes.removeAll();
			textField = new JTextField();
			textField.setText("TABLE");
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			textField.setFont(new Font("Monospaced", Font.PLAIN, 15));
			textField.setEnabled(false);
			textField.setEditable(false);
			textField.setColumns(10);
			pnlFoodSizes.add(textField, "cell 0 0,growx");
			
			textField_1 = new JTextField();
			textField_1.setText("TABLE");
			textField_1.setHorizontalAlignment(SwingConstants.CENTER);
			textField_1.setFont(new Font("Monospaced", Font.PLAIN, 15));
			textField_1.setEnabled(false);
			textField_1.setEditable(false);
			textField_1.setColumns(10);
			pnlFoodSizes.add(textField_1, "cell 1 0,growx");
			while(c.rs.next()){
				sizeName = c.rs.getString("normal_food_size");
				sizePackagePrice = c.rs.getString("normal_package_price");
				btnFoodSizes[row] = new JButton(sizeName + " " + sizePackagePrice);
				btnFoodSizes[row].setName(sizePackagePrice);
				btnFoodSizes[row].setFont(new Font("Monospaced", Font.PLAIN, 15));
				btnFoodSizes[row].setActionCommand(sizeName);
				btnFoodSizes[row].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnFoodSizes[row].setIcon(new ImageIcon(NormalSales.class.getResource("/Images/Icon/plus.png")));

				btnFoodSizes[row].setOpaque(false);
				btnFoodSizes[row].setContentAreaFilled(false);
				btnFoodSizes[row].setUI(new MetalButtonUI());
				pnlFoodSizes.add(btnFoodSizes[row], "cell 0 "+cellY+"");
				
				spnFoodSizes[row] = new JSpinner();
				spnFoodSizes[row].setName(sizeName);
				spnFoodSizes[row].setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
				((DefaultEditor) spnFoodSizes[row].getEditor()).getTextField().setEditable(false);
				spnFoodSizes[row].setEditor(new JSpinner.DefaultEditor(spnFoodSizes[row]));
				spnFoodSizes[row].setFont(new Font("Monospaced", Font.PLAIN, 15));
				spnFoodSizes[row].setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				pnlFoodSizes.add(spnFoodSizes[row], "cell 1 "+cellY+",grow");
				
				spnFoodSizes[row].addChangeListener(new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent e) {
						// TODO Auto-generated method stub
		            	for(int j = 0; j < row; j++){
							if(e.getSource() == spnFoodSizes[j]){	
								packagePrice = (Double.parseDouble(btnFoodSizes[j].getName()) * (int) spnFoodSizes[j].getValue());
								btnFoodSizes[j].setText(btnFoodSizes[j].getActionCommand() + " " + packagePrice);
							}
		            	}
					}
				});
				
				//Category Button Action Listener
				btnFoodSizes[row].addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						packageTotalTemp = Double.parseDouble(tfTotal.getText());
						packageTotal = 0.00;
		            	for(int j = 0; j < row; j++){
							if(e.getActionCommand().equalsIgnoreCase(btnFoodSizes[j].getActionCommand())){
								if (checkTable == true) {
									tabbedPane.setSelectedIndex(1);
								}
								DefaultTableModel model = (DefaultTableModel) tblOrders.getModel();
								btnFoodSizes[j].getActionCommand();
								packagePrice = (Double.parseDouble(btnFoodSizes[j].getName()) * (int) spnFoodSizes[j].getValue());	
								packageQuantity = (int) spnFoodSizes[j].getValue();
								packageSize = btnFoodSizes[j].getActionCommand();
								Object[] row = { foodId, foodName, packageSize, packageQuantity, packagePrice };
								model.addRow(row);
								packageTotal = packageTotalTemp + packagePrice;
								
								tfTotal.setText(String.valueOf(packageTotal));
							}
		            	}
					}
				  });
				//End of Category Button Action Listener				
				row++;
				cellY++;
			}
			pnlFoodSizes.repaint();
			pnlFoodSizes.revalidate();
			c.rs.close();
			c.pst.close();
			c.con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		}
		
	
		public void remove(ActionListener remove){
			btnSettle.addActionListener(remove);
		}	
	
		public void add(ActionListener add){
			btnSettle.addActionListener(add);
		}	
		
		//Image scale to fit on Panel class
		public class BackgroundImageApp extends JPanel {
		
				BufferedImage originalImage;
				BufferedImage scaledImage;
		
				BackgroundImageApp(File file) {
				    setPreferredSize(new Dimension(100,100));
				    try {
				        originalImage = ImageIO.read(file);
				    } catch(Exception e){}
		
				    addComponentListener(new ResizerListener());
				}
		
				public void resize() {
				    double widthScaleFactor = getWidth() / (double)originalImage.getWidth();
				    double heightScaleFactor = getHeight() / (double)originalImage.getHeight();
				    double scaleFactor = (widthScaleFactor > heightScaleFactor)? heightScaleFactor : widthScaleFactor;
		
				    AffineTransform at = new AffineTransform();
				    at.scale(scaleFactor, scaleFactor);
		
				    AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
				    scaledImage = scaleOp.filter(originalImage, scaledImage);
				    revalidate();
				    updateUI();
				    repaint();
				}
		
				@Override
				public void paintComponent(Graphics g) {
					super.paintComponent(g);
		
					double widthScaleFactor = getWidth() / (double)getWidth();
					double heightScaleFactor = getHeight() / (double)getHeight();
					double scaleFactor = (widthScaleFactor > heightScaleFactor)? heightScaleFactor : widthScaleFactor;
		
					int width = (int)(getWidth() * scaleFactor);
					int height = (int)(getHeight() * scaleFactor);
		
					g.drawImage(originalImage, 0, 0, width, height, null);
				}
		
		
				class ResizerListener implements ComponentListener {
				    @Override
				    public void componentResized(ComponentEvent e) {
				        resize();
				    }
		
				    @Override public void componentHidden(ComponentEvent e) {}
				    @Override public void componentMoved(ComponentEvent e) {}
				    @Override public void componentShown(ComponentEvent e) {}
			}
		}
		//end of Image scale to fit on Panel class
		@Override
		public void valueChanged(ListSelectionEvent event) {
			// TODO Auto-generated method stub
			if( event.getSource() == tblOrders.getSelectionModel() && event.getFirstIndex() >= 0 ){
				tblOrders.getModel();
				int row = tblOrders.getSelectedRow();
				if(row!=-1){
					btnSettle.setText("Remove");
					btnCancel.setEnabled(true);					
				}
			}
		}
}
