package Capstone.View;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;
import net.proteanit.sql.DbUtils;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.metal.MetalButtonUI;
import javax.swing.table.DefaultTableModel;

import Capstone.Database.Connect;
import Capstone.Model.ModelEventPackage;
import Capstone.Model.ModelNormalPackage;

import javax.swing.JTabbedPane;
import javax.swing.border.MatteBorder;

import java.awt.Font;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;

public class Packages extends JPanel implements ListSelectionListener, MouseListener, FocusListener{
	boolean hey = true;
	static boolean boolCheck = true;

	public String path;
    public ImageIcon imageIcon;
    JFileChooser jfBrowse;
    public String foodType;
    public String foodId;
    private JPanel pnlNormal;
//    private JPanel pnlEvent;
    private JTabbedPane tpPackage;
    private JPanel pnlNormalCategory;
    private JButton[] btnNormalCategories;
    private JButton[] btnNormalFoods;
    private JButton[] btnNormalFoodSizes;
    private JButton btnNormalViewAll;
	public JButton btnNormalCreate;
	public JButton btnNormalRemove;
	public JButton btnNormalDelete;
	public JButton btnEventRemove;
	public JButton btnEventCreate;
	public JButton btnEventDelete;
	private JButton btnNormalAttachImage;
	private JSpinner[] spnNormalFoodSizes;
    String categoryNormalName;
    String countCategory;
    String foodNormalName;
    String foodNormalId;
    String sizeNormalName;
    String sizeNormalPackagePrice;
    String foodPackageType;
    
    double packageNormalPrice = 0.00;
    int normalViewAll;
    int iNormalCategory;
    int iNormalFood;
    int iNormalFoodSizes;

    int iEventCategory;
    int iEventFood;
    int iEventFoodSizes;
    
    int[] food;
    private JPanel pnlNormalFood;
    Connect c;
	private JPanel pnlNormalFoodSizes;
	private JPanel pnlNormalTable;
	public JTable tblNormalFoods;
	private JScrollPane spNormalTable;
	private JTextField tfNormalCategories;
	private JTextField tfNormalFood;
	private JTextField tfNormalSizes;
	public JList listNormalPackage;
	private JScrollPane spNormalPackageList;
	private JLabel lblNewLabel;
	public JTextField tfNormalPackageName;
	private JLabel lblServingSize;
	private JLabel lblNormalTotalPrice;
	public JTextField tfNormalTotal;
	public JSpinner spnNormalServingSize;

	private String packageNormalId;
	private String packageNormalName;
	private String packageNormalSize;
	private int packageNormalQuantity;
	private Double packageNormalTotal = 0.00;
	private Double packageNormalTotalTemp = 0.00;

	private String npi;
	private String npn;
	private int nppss;
	private String npt;
	private String np;
	private String npd;
	private String npop;
	private String npdper;
	private String npdpri;
	
	

	ModelNormalPackage mnp;
	ModelEventPackage mep;
	private JLabel lblNormalPackageId;
	public JTextField tfNormalPackageId;
	private JTextField tfNormalPackageList;
	private JPanel pnlPackageList;
	public JTextField tfEventPackageId;
	public JTextField tfEventPackageName;
	public JSpinner spnEventServingSize;
	public JTable tblEventFoods;
	public JTextField tfEventTotal;
	private BackgroundImageApp pnlNormalImage;
	/**Create the panel.**/
	File file = new File("src\\Images\\Default\\food.jpg");
	protected JFileChooser normalJfBrowse;
	protected String normalPath;
	protected JFileChooser eventJfBrowse;
	protected String eventPath;
	private JTextField tfNormalPackageForm;
	private JLabel label;
	private JTextField tfQuantity;
	private JLabel lblType;
	private JLabel lblDiscount;
	private JRadioButton rdbtnRegular;
	private JRadioButton rdbtnEvent;
	private final ButtonGroup bgType = new ButtonGroup();
	public JList listEventPackage;
	private JScrollPane spEventPackage;
	public JButton btnNormalBack;
	protected String npty;
	private String foodNormalType;
	private JPanel panel;
	private JRadioButton rdbtnPercent;
	private JRadioButton rdbtnPrice;
	private JTextField tfDiscount;
	private final ButtonGroup bgDiscount = new ButtonGroup();
	public Double originalprice;
	public Double total = 0.00;
	public Double holdTotal = 0.00;
	private double discount;
	public JTextField tfOriginalPrice;
	private JLabel lblOriginalPrice;
	private int  discountbypercent;
	private double discountbyprice;
	private String discounttext;
	private String listValue;
	
	public Packages() {
		System.setProperty("Quaqua.tabLayoutPolicy","wrap");
        // set the Quaqua Look and Feel in the UIManager
		try {
			UIManager.setLookAndFeel(ch.randelshofer.quaqua.QuaquaManager.getLookAndFeel());
		} catch (Exception e) {
			e.printStackTrace();
		}
		normalPath = file.getAbsolutePath();
		eventPath = file.getAbsolutePath();
		setBackground(SystemColor.menu);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setVisible(true);
		setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		tpPackage = new JTabbedPane(JTabbedPane.TOP);
		tpPackage.setBorder(new LineBorder(new Color(0, 0, 0)));
		tpPackage.setUI(new AquaTabbedPaneUI());
		add(tpPackage, "cell 0 0,grow");
		
		pnlNormal = new JPanel();
		pnlNormal.setBackground(getPanelColor());
		tpPackage.addTab("Package", null, pnlNormal, null);
		pnlNormal.setLayout(new MigLayout("", "[grow][grow][grow][grow]", "[][grow][][grow]"));
		
		tfNormalCategories = new JTextField();
		tfNormalCategories.setEnabled(false);
		tfNormalCategories.setEditable(false);
		tfNormalCategories.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfNormalCategories.setHorizontalAlignment(SwingConstants.CENTER);
		tfNormalCategories.setText("CATEGORIES");
		pnlNormal.add(tfNormalCategories, "cell 0 0,growx");
		tfNormalCategories.setColumns(10);
		
		tfNormalFood = new JTextField();
		tfNormalFood.setEnabled(false);
		tfNormalFood.setEditable(false);
		tfNormalFood.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfNormalFood.setText("FOOD");
		tfNormalFood.setHorizontalAlignment(SwingConstants.CENTER);
		tfNormalFood.setColumns(10);
		pnlNormal.add(tfNormalFood, "cell 1 0,growx");
		
		tfNormalPackageForm = new JTextField();
		tfNormalPackageForm.setText("PACKAGE FORM");
		tfNormalPackageForm.setHorizontalAlignment(SwingConstants.CENTER);
		tfNormalPackageForm.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfNormalPackageForm.setEnabled(false);
		tfNormalPackageForm.setEditable(false);
		tfNormalPackageForm.setColumns(10);
		pnlNormal.add(tfNormalPackageForm, "cell 2 0,growx");
		
		tfNormalPackageList = new JTextField();
		tfNormalPackageList.setText("PACKAGE LIST");
		tfNormalPackageList.setHorizontalAlignment(SwingConstants.CENTER);
		tfNormalPackageList.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfNormalPackageList.setEnabled(false);
		tfNormalPackageList.setEditable(false);
		tfNormalPackageList.setColumns(10);
		pnlNormal.add(tfNormalPackageList, "cell 3 0,growx");
				
		pnlNormalCategory = new JPanel();
		pnlNormalCategory.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlNormalCategory.setLayout(new MigLayout("", "[grow]", "[]"));
		pnlNormal.add(pnlNormalCategory, "cell 0 1,grow");
				
		pnlNormalFood = new JPanel();
		pnlNormalFood.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlNormalFood.setLayout(new MigLayout("", "[100px,grow]", "[]"));
		pnlNormal.add(pnlNormalFood, "cell 1 1,grow");
		
		pnlNormalTable = new JPanel();
		pnlNormalTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlNormal.add(pnlNormalTable, "cell 2 1 1 3,grow");
		pnlNormalTable.setLayout(new MigLayout("", "[200px,grow][][grow]", "[][][][grow][][][][]"));
		
		label = new JLabel("Fields with (*) are required");
		label.setFont(new Font("Monospaced", Font.PLAIN, 12));
		pnlNormalTable.add(label, "cell 1 0 2 1,alignx center,growy");
		
		lblNormalPackageId = new JLabel("Package ID:");
		lblNormalPackageId.setFont(new Font("Monospaced", Font.PLAIN, 15));
//		pnlNormalTable.add(lblNormalPackageId, "cell 1 1");
		
		tfNormalPackageId = new JTextField();
		tfNormalPackageId.setEditable(false);
		tfNormalPackageId.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfNormalPackageId.setColumns(10);
//		pnlNormalTable.add(tfNormalPackageId, "cell 2 1,growx");
		
		lblNewLabel = new JLabel("Package Name*:");
		lblNewLabel.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlNormalTable.add(lblNewLabel, "cell 1 1");
		
		tfNormalPackageName = new JTextField();
		tfNormalPackageName.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlNormalTable.add(tfNormalPackageName, "cell 2 1,growx");
		tfNormalPackageName.setColumns(10);
		
		lblType = new JLabel("Type:");
		lblType.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlNormalTable.add(lblType, "cell 1 2");
		
		rdbtnRegular = new JRadioButton("Regular");
		rdbtnRegular.setFont(new Font("Monospaced", Font.PLAIN, 15));
		rdbtnRegular.setSelected(true);
		bgType.add(rdbtnRegular);
		pnlNormalTable.add(rdbtnRegular, "flowx,cell 2 2");
		
		btnNormalAttachImage = new JButton("UPLOAD IMAGE");
		btnNormalAttachImage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNormalAttachImage.setIcon(new ImageIcon(Packages.class.getResource("/Images/Icon/calendar.png")));
		btnNormalAttachImage.setContentAreaFilled(false);
		btnNormalAttachImage.setFont(new Font("Monospaced", Font.PLAIN, 15));
		btnNormalAttachImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				normalJfBrowse = new JFileChooser();
				normalJfBrowse.setAccessory(new ImagePreview(normalJfBrowse));
				normalJfBrowse.addChoosableFileFilter(new FileNameExtensionFilter("JPEG Files", "jpg"));
				normalJfBrowse.addChoosableFileFilter(new FileNameExtensionFilter("PNG Files","png"));
				normalJfBrowse.addChoosableFileFilter(new FileNameExtensionFilter("GIF Files","gif"));
				normalJfBrowse.addChoosableFileFilter(new FileNameExtensionFilter("BMP Files","bmp"));
				normalJfBrowse.addChoosableFileFilter(new FileNameExtensionFilter("All Images", "jpg", "png", "gif", "bmp"));
				int returnBrowse = normalJfBrowse.showOpenDialog(null);
				if(returnBrowse == JFileChooser.APPROVE_OPTION){
					normalPath = normalJfBrowse.getSelectedFile().getPath();
					File file = new File(normalPath);
					pnlNormalTable.remove(pnlNormalImage);
					pnlNormalImage.removeAll();
					pnlNormalImage.updateUI();
					//Set Icon		
					pnlNormalImage = new BackgroundImageApp(file);
					pnlNormalImage.setBackground(SystemColor.menu);
					pnlNormalImage.setBorder(new LineBorder(Color.DARK_GRAY));
					pnlNormalImage.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
					pnlNormalTable.add(pnlNormalImage, "cell 0 0 1 4,grow");
					pnlNormalImage.setLayout(null);
					pnlNormalTable.updateUI();
					pnlNormalTable.revalidate();
					pnlNormalTable.repaint();
				}else{
					JOptionPane.showMessageDialog(null, "No image selected");
				}
			}
		});
		pnlNormalTable.add(btnNormalAttachImage, "cell 0 4,alignx center,growy");
		
		lblServingSize = new JLabel("Serving size:");
		lblServingSize.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlNormalTable.add(lblServingSize, "cell 1 3");
		
		spnNormalServingSize = new JSpinner();
		spnNormalServingSize.setFont(new Font("Monospaced", Font.PLAIN, 15));
		spnNormalServingSize.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		((DefaultEditor) spnNormalServingSize.getEditor()).getTextField().setEditable(false);
		spnNormalServingSize.setEditor(new JSpinner.DefaultEditor(spnNormalServingSize));
		pnlNormalTable.add(spnNormalServingSize, "cell 2 3");
		
		rdbtnPercent = new JRadioButton("Percent");
		rdbtnPercent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnPercent.isSelected()){
					tfDiscount.setText("0");
					RetrieveOriginalTotal();
					CalculateDiscountTotal();
				}	
			}
		});
		rdbtnPercent.setSelected(true);
		bgDiscount.add(rdbtnPercent);
		rdbtnPercent.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlNormalTable.add(rdbtnPercent, "flowx,cell 2 4,aligny top");
		
		lblOriginalPrice = new JLabel("Original Price:");
		lblOriginalPrice.setFont(new Font("Monospaced", Font.PLAIN, 17));
		pnlNormalTable.add(lblOriginalPrice, "cell 0 6");
		
		tfOriginalPrice = new JTextField("0.0");
		tfOriginalPrice.setEnabled(false);
		tfOriginalPrice.setEditable(false);
		tfOriginalPrice.setFont(new Font("Monospaced", Font.PLAIN, 17));
		tfOriginalPrice.setColumns(10);
		pnlNormalTable.add(tfOriginalPrice, "cell 1 6,growx");
		
		spNormalTable = new JScrollPane();
		pnlNormalTable.add(spNormalTable, "cell 0 5 3 1,grow");
		spNormalTable.getVerticalScrollBar().setUnitIncrement(20);
		
		lblDiscount = new JLabel("Discount by:");
		lblDiscount.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlNormalTable.add(lblDiscount, "cell 1 4,aligny top");
		
		tblNormalFoods = new JTable();
		tblNormalFoods.setShowGrid(false);
		tblNormalFoods.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tblNormalFoods.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tblNormalFoods.setRowHeight(30);
		tblNormalFoods.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tblNormalFoods.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Food ID", "Food Name", "Size", "Quantity", "Price"
			}
		));
		spNormalTable.setViewportView(tblNormalFoods);
		
		lblNormalTotalPrice = new JLabel("Total Price:");
		lblNormalTotalPrice.setFont(new Font("Monospaced", Font.PLAIN, 17));
		pnlNormalTable.add(lblNormalTotalPrice, "cell 0 7");
		
		tfNormalTotal = new JTextField("0.0");
		tfNormalTotal.setFont(new Font("Monospaced", Font.PLAIN, 17));
		tfNormalTotal.setColumns(10);
		pnlNormalTable.add(tfNormalTotal, "cell 1 7,growx");
		
		btnNormalDelete = new JButton("");
		btnNormalDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNormalDelete.setIcon(new ImageIcon(Packages.class.getResource("/Images/Icon/remove.png")));
		btnNormalDelete.setContentAreaFilled(false);
		btnNormalDelete.setEnabled(false);
		btnNormalDelete.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlNormalTable.add(btnNormalDelete, "flowx,cell 2 7,alignx right");
		
		btnNormalRemove = new JButton("");
		btnNormalRemove.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNormalRemove.setIcon(new ImageIcon(Packages.class.getResource("/Images/Icon/minus.png")));
		btnNormalRemove.setContentAreaFilled(false);
		btnNormalRemove.setEnabled(false);
		btnNormalRemove.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlNormalTable.add(btnNormalRemove, "cell 2 7,alignx right");

		btnNormalBack = new JButton("");
		btnNormalBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RefreshPackage();
			}
		});
		btnNormalBack.setName("BACK");
		btnNormalBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNormalBack.setIcon(new ImageIcon(Packages.class.getResource("/Images/Icon/cancel.png")));
		btnNormalBack.setContentAreaFilled(false);
		btnNormalBack.setEnabled(false);
		btnNormalBack.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlNormalTable.add(btnNormalBack, "cell 2 7,alignx right");
		
		btnNormalCreate = new JButton("");
		btnNormalCreate.setName("CREATE");
		btnNormalCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNormalCreate.setIcon(new ImageIcon(Packages.class.getResource("/Images/Icon/save.png")));
		btnNormalCreate.setContentAreaFilled(false);
		btnNormalCreate.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlNormalTable.add(btnNormalCreate, "cell 2 7,alignx right");
		
		rdbtnEvent = new JRadioButton("Event");
		rdbtnEvent.setFont(new Font("Monospaced", Font.PLAIN, 15));
		bgType.add(rdbtnEvent);
		pnlNormalTable.add(rdbtnEvent, "cell 2 2");
		
		rdbtnPrice = new JRadioButton("Price");
		rdbtnPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnPrice.isSelected()){
					tfDiscount.setText("0");
					RetrieveOriginalTotal();
					CalculateDiscountTotal();
				}
			}
		});
		bgDiscount.add(rdbtnPrice);
		rdbtnPrice.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlNormalTable.add(rdbtnPrice, "cell 2 4,aligny top");
		
		tfDiscount = new JTextField("0");
		tfDiscount.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(tfDiscount.getText().equalsIgnoreCase("0")){
					tfDiscount.setText("");
				}
			}
		});
		tfDiscount.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(tfDiscount.getText().equalsIgnoreCase("0")){
					tfDiscount.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(tfDiscount.getText().equalsIgnoreCase("")){
					tfDiscount.setText("0");					
					RetrieveOriginalTotal();
					CalculateDiscountTotal();
				}
			}
		});
		tfDiscount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (!tfNormalFood.getText().isEmpty()) {
					RetrieveOriginalTotal();
					CalculateDiscountTotal();
				}
			}
		});
		tfDiscount.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfDiscount.setColumns(10);
		pnlNormalTable.add(tfDiscount, "cell 2 4,aligny top");
		
		pnlPackageList = new JPanel();
		pnlPackageList.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlNormal.add(pnlPackageList, "cell 3 1 1 3,grow");
		pnlPackageList.setLayout(new MigLayout("", "[grow]", "[grow,fill][grow]"));
		
		spNormalPackageList = new JScrollPane();
		pnlPackageList.add(spNormalPackageList, "cell 0 0,grow");

		spNormalPackageList.getVerticalScrollBar().setUnitIncrement(20);
		
		listNormalPackage = new JList();
		listNormalPackage.setFont(new Font("Monospaced", Font.PLAIN, 15));
		listNormalPackage.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				listValue = String.valueOf(listNormalPackage.getSelectedValue());
				RefreshSelectList();
				listEventPackage.clearSelection();
			}
		});
		spNormalPackageList.setViewportView(listNormalPackage);
		spEventPackage = new JScrollPane();
		pnlPackageList.add(spEventPackage, "cell 0 1,grow");
		listEventPackage = new JList();
		spEventPackage.setViewportView(listEventPackage);
		listEventPackage.setFont(new Font("Monospaced", Font.PLAIN, 15));
		listEventPackage.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent arg0) {
					// TODO Auto-generated method stub
					listValue = String.valueOf(listEventPackage.getSelectedValue());
					RefreshSelectList();
					listNormalPackage.clearSelection();
				}
			});
		panel = new JPanel();
		pnlNormal.add(panel, "cell 0 2 2 1,grow");
		panel.setLayout(new MigLayout("", "[grow][grow]", "[]"));
		tfNormalSizes = new JTextField();
		panel.add(tfNormalSizes, "cell 0 0,growx");
		tfNormalSizes.setEnabled(false);
		tfNormalSizes.setEditable(false);
		tfNormalSizes.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfNormalSizes.setText("SIZES");
		tfNormalSizes.setHorizontalAlignment(SwingConstants.CENTER);
		tfNormalSizes.setColumns(10);
		tfQuantity = new JTextField();
		panel.add(tfQuantity, "cell 1 0,growx");
		tfQuantity.setText("QUANTITY");
		tfQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		tfQuantity.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfQuantity.setEnabled(false);
		tfQuantity.setEditable(false);
		tfQuantity.setColumns(10);
		pnlNormalFoodSizes = new JPanel();
		pnlNormalFoodSizes.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlNormalFoodSizes.setLayout(new MigLayout("", "[150px,grow]", "[]"));
		pnlNormal.add(pnlNormalFoodSizes, "cell 0 3 2 1,grow");		
		
//		//--------------------------------------------------------------------------------------------------------------------------------------------------------
//		
//		pnlEvent = new JPanel();
//		pnlEvent.setBackground(getPanelColor());
//		tpPackage.addTab("Event", null, pnlEvent, null);
//		pnlEvent.setLayout(new MigLayout("", "[grow][grow][grow][grow]", "[][grow][][grow]"));
//		
//		tfEventCategories = new JTextField();
//		tfEventCategories.setEnabled(false);
//		tfEventCategories.setEditable(false);
//		tfEventCategories.setFont(new Font("Monospaced", Font.PLAIN, 15));
//		tfEventCategories.setHorizontalAlignment(SwingConstants.CENTER);
//		tfEventCategories.setText("CATEGORIES");
//		pnlEvent.add(tfEventCategories, "cell 0 0,growx");
//		tfEventCategories.setColumns(10);
//		
//		tfEventFood = new JTextField();
//		tfEventFood.setEnabled(false);
//		tfEventFood.setEditable(false);
//		tfEventFood.setFont(new Font("Monospaced", Font.PLAIN, 15));
//		tfEventFood.setText("FOOD");
//		tfEventFood.setHorizontalAlignment(SwingConstants.CENTER);
//		tfEventFood.setColumns(10);
//		pnlEvent.add(tfEventFood, "cell 1 0,growx");
//		
//		tfEventPackageForm = new JTextField();
//		tfEventPackageForm.setText("PACKAGE FORM");
//		tfEventPackageForm.setHorizontalAlignment(SwingConstants.CENTER);
//		tfEventPackageForm.setFont(new Font("Monospaced", Font.PLAIN, 15));
//		tfEventPackageForm.setEnabled(false);
//		tfEventPackageForm.setEditable(false);
//		tfEventPackageForm.setColumns(10);
//		pnlEvent.add(tfEventPackageForm, "cell 2 0,growx");
//		
//		tfEventPackageList = new JTextField();
//		tfEventPackageList.setText("PACKAGE LIST");
//		tfEventPackageList.setHorizontalAlignment(SwingConstants.CENTER);
//		tfEventPackageList.setFont(new Font("Monospaced", Font.PLAIN, 15));
//		tfEventPackageList.setEnabled(false);
//		tfEventPackageList.setEditable(false);
//		tfEventPackageList.setColumns(10);
//		pnlEvent.add(tfEventPackageList, "cell 3 0,growx");
//		
//		scrollPane = new JScrollPane();
//		pnlEvent.add(scrollPane, "cell 0 1,grow");
//				
//		pnlEventCategory = new JPanel();
//		scrollPane.setViewportView(pnlEventCategory);
//		pnlEventCategory.setBorder(new LineBorder(new Color(0, 0, 0)));
//		pnlEventCategory.setLayout(new MigLayout("", "[grow]", "[][]"));
//		btnEventViewAll = new JButton("View All"  + " (" + eventViewAll + ")");
//		btnEventViewAll.setName("");
//		btnEventViewAll.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		btnEventViewAll.setForeground(new Color(255, 255, 255));
//		btnEventViewAll.setBackground(new Color(51, 122, 152));
//		btnEventViewAll.setActionCommand("");
//		btnEventViewAll.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				pnlEventFoodSizes.removeAll();	
//				pnlEventFoodSizes.repaint();
//				pnlEventFoodSizes.revalidate();
//				ViewEventFoods(btnEventViewAll.getActionCommand());
//				for(int j = 1; j<iNormalCategory;j++){
//					btnEventCategories[j].setBackground(new Color(51, 122, 152));
//				}
//				btnEventViewAll.setBackground(new Color(255, 99, 71));
//			}
//		});
//		btnEventViewAll.setFont(new Font("Monospaced", Font.PLAIN, 15));
//		pnlEventCategory.add(btnEventViewAll, "cell 0 0, grow");
//		
//		scrollPane_1 = new JScrollPane();
//		pnlEvent.add(scrollPane_1, "cell 1 1,grow");
//				
//		pnlEventFood = new JPanel();
//		scrollPane_1.setViewportView(pnlEventFood);
//		pnlEventFood.setBorder(new LineBorder(new Color(0, 0, 0)));
//		pnlEventFood.setLayout(new MigLayout("", "[100px,grow]", "[]"));
//		
//		pnlEventTable = new JPanel();
//		pnlEventTable.setBorder(new LineBorder(new Color(0, 0, 0)));
//		pnlEvent.add(pnlEventTable, "cell 2 1 1 3,grow");
//		pnlEventTable.setLayout(new MigLayout("", "[200px][][grow]", "[][][][][][][][grow][]"));
//		
//		label_1 = new JLabel("Fields with (*) are required");
//		label_1.setFont(new Font("Monospaced", Font.PLAIN, 12));
//		pnlEventTable.add(label_1, "cell 1 0 2 1,alignx center,growy");
//		
//		lblEventPackageId = new JLabel("Event Package ID:");
//		lblEventPackageId.setFont(new Font("Monospaced", Font.PLAIN, 15));
//		pnlEventTable.add(lblEventPackageId, "cell 1 1");
//		
//		tfEventPackageId = new JTextField();
//		tfEventPackageId.setEditable(false);
//		tfEventPackageId.setFont(new Font("Monospaced", Font.PLAIN, 15));
//		tfEventPackageId.setColumns(10);
//		pnlEventTable.add(tfEventPackageId, "cell 2 1,growx");
//		
//		lblNewLabel = new JLabel("Package Name*:");
//		lblNewLabel.setFont(new Font("Monospaced", Font.PLAIN, 15));
//		pnlEventTable.add(lblNewLabel, "cell 1 2");
//		
//		tfEventPackageName = new JTextField();
//		tfEventPackageName.setFont(new Font("Monospaced", Font.PLAIN, 15));
//		pnlEventTable.add(tfEventPackageName, "cell 2 2,growx");
//		tfEventPackageName.setColumns(10);
//		
//		lblServingSize = new JLabel("Serving size:");
//		lblServingSize.setFont(new Font("Monospaced", Font.PLAIN, 15));
//		pnlEventTable.add(lblServingSize, "cell 1 3");
//		
//		spnEventServingSize = new JSpinner();
//		spnEventServingSize.setFont(new Font("Monospaced", Font.PLAIN, 15));
//		spnEventServingSize.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
//		((DefaultEditor) spnEventServingSize.getEditor()).getTextField().setEditable(false);
//		spnEventServingSize.setEditor(new JSpinner.DefaultEditor(spnEventServingSize));
//		pnlEventTable.add(spnEventServingSize, "cell 2 3");
//		
//		btnEventAttachImage = new JButton("UPLOAD IMAGE");
//		btnEventAttachImage.setIcon(new ImageIcon(Packages.class.getResource("/Images/Icon/upload_image.png")));
//		btnEventAttachImage.setContentAreaFilled(false);
//		btnEventAttachImage.setFont(new Font("Monospaced", Font.PLAIN, 15));
//		btnEventAttachImage.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				eventJfBrowse = new JFileChooser();
//				eventJfBrowse.setAccessory(new ImagePreview(eventJfBrowse));
//				eventJfBrowse.addChoosableFileFilter(new FileNameExtensionFilter("JPEG Files", "jpg"));
//				eventJfBrowse.addChoosableFileFilter(new FileNameExtensionFilter("PNG Files","png"));
//				eventJfBrowse.addChoosableFileFilter(new FileNameExtensionFilter("GIF Files","gif"));
//				eventJfBrowse.addChoosableFileFilter(new FileNameExtensionFilter("BMP Files","bmp"));
//				eventJfBrowse.addChoosableFileFilter(new FileNameExtensionFilter("All Images", "jpg", "png", "gif", "bmp"));
//				int returnBrowse = eventJfBrowse.showOpenDialog(null);
//				if(returnBrowse == JFileChooser.APPROVE_OPTION){	
//					eventPath = eventJfBrowse.getSelectedFile().getPath();
//					File file = new File(eventPath);
//					pnlEventTable.remove(pnlEventImage);
//					pnlEventImage.removeAll();
//					pnlEventImage.updateUI();
//					//Set Icon		
//					pnlEventImage = new BackgroundImageApp(file);
//					pnlEventImage.setBackground(SystemColor.menu);
//					pnlEventImage.setBorder(new LineBorder(Color.DARK_GRAY));
//					pnlEventImage.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
//					pnlEventTable.add(pnlEventImage, "cell 0 0 1 4,grow");
//					pnlEventImage.setLayout(null);
//					pnlEventTable.updateUI();
//					pnlEventTable.revalidate();
//					pnlEventTable.repaint();
//				}else{
//					JOptionPane.showMessageDialog(null, "No image selected");
//				}
//			}
//		});
//		pnlEventTable.add(btnEventAttachImage, "cell 0 6,grow");
//		
//		spEventTable = new JScrollPane();
//		pnlEventTable.add(spEventTable, "cell 0 7 3 1,grow");
//		spEventTable.getVerticalScrollBar().setUnitIncrement(20);
//		
//		tblEventFoods = new JTable();
//		tblEventFoods.setShowGrid(false);
//		tblEventFoods.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
//		tblEventFoods.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		tblEventFoods.setRowHeight(30);
//		tblEventFoods.setFont(new Font("Monospaced", Font.PLAIN, 15));
//		tblEventFoods.setModel(new DefaultTableModel(
//			new Object[][] {
//			},
//			new String[] {
//				"Food ID", "Food Name", "Size", "Quantity", "Price"
//			}
//		));
//		spEventTable.setViewportView(tblEventFoods);
//		
//		lblEventTotalPrice = new JLabel("Total Price:");
//		lblEventTotalPrice.setFont(new Font("Monospaced", Font.PLAIN, 25));
//		pnlEventTable.add(lblEventTotalPrice, "cell 0 8");
//		
//		tfEventTotal = new JTextField("0.0");
//		tfEventTotal.setFont(new Font("Monospaced", Font.PLAIN, 30));
//		tfEventTotal.setColumns(10);
//		pnlEventTable.add(tfEventTotal, "cell 1 8,growx");
//		
//		btnEventDelete = new JButton("");
//		btnEventDelete.setContentAreaFilled(false);
//		btnEventDelete.setIcon(new ImageIcon(Packages.class.getResource("/Images/Icon/remove.png")));
//		btnEventDelete.setEnabled(false);
//		btnEventDelete.setFont(new Font("Monospaced", Font.PLAIN, 15));
//		pnlEventTable.add(btnEventDelete, "flowx,cell 2 8,alignx right");
//		
//		btnEventRemove = new JButton("");
//		btnEventRemove.setContentAreaFilled(false);
//		btnEventRemove.setIcon(new ImageIcon(Packages.class.getResource("/Images/Icon/minus.png")));
//		btnEventRemove.setEnabled(false);
//		btnEventRemove.setFont(new Font("Monospaced", Font.PLAIN, 15));
//		pnlEventTable.add(btnEventRemove, "cell 2 8,alignx right");
//		
//		btnEventCreate = new JButton("");
//		btnEventCreate.setContentAreaFilled(false);
//		btnEventCreate.setName("CREATE");
//		btnEventCreate.setIcon(new ImageIcon(Packages.class.getResource("/Images/Icon/save.png")));
//		btnEventCreate.setFont(new Font("Monospaced", Font.PLAIN, 15));
//		pnlEventTable.add(btnEventCreate, "cell 2 8,alignx right");
//		
//		pnlPackageList_1 = new JPanel();
//		pnlPackageList_1.setBorder(new LineBorder(new Color(0, 0, 0)));
//		pnlEvent.add(pnlPackageList_1, "cell 3 1 1 3,grow");
//		pnlPackageList_1.setLayout(new MigLayout("", "[grow]", "[grow,fill]"));
//		
//		spEventPackageList = new JScrollPane();
//		pnlPackageList_1.add(spEventPackageList, "cell 0 0,grow");
//		spEventPackageList.getVerticalScrollBar().setUnitIncrement(20);
//		
//		listEventPackage = new JList();
//		listEventPackage.setFont(new Font("Monospaced", Font.PLAIN, 15));
//		listEventPackage.addListSelectionListener(new ListSelectionListener() {
//			
//			@Override
//			public void valueChanged(ListSelectionEvent arg0) {
//				// TODO Auto-generated method stub
//				if (listEventPackage.getFirstVisibleIndex() == listEventPackage.getSelectedIndex()) {
//					tfEventTotal.setText("0.0");
//					spnEventServingSize.setValue(1);
//					tfEventPackageName.setText("");
//					btnEventCreate.setName("CREATE");
//					btnEventCreate.setEnabled(true);
//					btnEventDelete.setEnabled(false);
//					btnEventRemove.setEnabled(false);
//					DefaultTableModel dm = (DefaultTableModel) tblEventFoods.getModel();
//					int rowCount = dm.getRowCount();
//					//Remove rows one by one from the end of the table
//					for (int i = rowCount - 1; i >= 0; i--) {
//					    dm.removeRow(i);
//					}
//					//Set Icon		
//					file = new File("src\\Images\\Default\\food.jpg");
//					eventPath = file.getAbsolutePath();
//
//					pnlEventTable.remove(pnlEventImage);
//					pnlEventImage.removeAll();
//					pnlEventImage.updateUI();
//					
//					pnlEventImage = new BackgroundImageApp(file);
//					pnlEventImage.setBackground(SystemColor.menu);
//					pnlEventImage.setBorder(new LineBorder(Color.DARK_GRAY));
//					pnlEventImage.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
//					pnlEventTable.add(pnlEventImage, "cell 0 0 1 4,grow");
//					pnlEventImage.setLayout(null);
//					pnlEventTable.updateUI();
//					pnlEventTable.revalidate();
//					pnlEventTable.repaint();
////					Dashboard.ept.CounterEventPackage();
//				}else{
//					btnEventCreate.setName("UPDATE");
//					btnEventCreate.setEnabled(true);
//					btnEventDelete.setEnabled(true);
//					btnEventRemove.setEnabled(false);
//					Connect c;					
//					try {
//						c = new Connect();
//						c.pst = c.con.prepareCall("call select_all_event_food_package(?)");
//						c.pst.setString(1, String.valueOf(listEventPackage.getSelectedValue()));
//						c.pst.execute();
//						c.rs = c.pst.getResultSet();
//						tblEventFoods.setModel(DbUtils.resultSetToTableModel(c.rs));
//						c.rs.close();
//						c.pst.close();
//						c.con.close();
//						c = new Connect();
//						c.pst = c.con.prepareCall("call select_all_event_package(?)");
//						c.pst.setString(1, String.valueOf(listEventPackage.getSelectedValue()));
//						c.pst.execute();
//						c.rs = c.pst.getResultSet();
//						while(c.rs.next()){
//							epi = c.rs.getString("event_package_id");
//							epn = c.rs.getString("event_package_name");
//							eppss = c.rs.getInt("event_package_price_serving_size");
//							ept = c.rs.getString("event_package_total");
//							ep = c.rs.getString("event_package_photos");
//						}
//						//Set Icon		
//						file = new File(ep);
//						eventPath = file.getAbsolutePath();
//
//						pnlEventTable.remove(pnlEventImage);
//						pnlEventImage.removeAll();
//						pnlEventImage.updateUI();
//						
//						pnlEventImage = new BackgroundImageApp(file);
//						pnlEventImage.setBackground(SystemColor.menu);
//						pnlEventImage.setBorder(new LineBorder(Color.DARK_GRAY));
//						pnlEventImage.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
//						pnlEventTable.add(pnlEventImage, "cell 0 0 1 4,grow");
//						pnlEventImage.setLayout(null);
//						pnlEventTable.updateUI();
//						pnlEventTable.revalidate();
//						pnlEventTable.repaint();
//						
//						tfEventPackageId.setText(epi);
//						tfEventPackageName.setText(epn);
//						spnEventServingSize.setValue(eppss);
//						tfEventTotal.setText(ept);
//						c.rs.close();
//						c.pst.close();
//						c.con.close();
//					} catch (SQLException e) {
//						// TODO: handle exception
//						e.printStackTrace();
//					}
//				}
//				
//			}
//		});
//		spEventPackageList.setViewportView(listEventPackage);
//		
//		tfEventSizes = new JTextField();
//		tfEventSizes.setEnabled(false);
//		tfEventSizes.setEditable(false);
//		tfEventSizes.setFont(new Font("Monospaced", Font.PLAIN, 15));
//		tfEventSizes.setText("SIZES");
//		tfEventSizes.setHorizontalAlignment(SwingConstants.CENTER);
//		tfEventSizes.setColumns(10);
//		pnlEvent.add(tfEventSizes, "flowx,cell 0 2,growx");
//		
//		scrollPane_2 = new JScrollPane();
//		pnlEvent.add(scrollPane_2, "cell 0 3 2 1,grow");
//		
//		pnlEventFoodSizes = new JPanel();
//		scrollPane_2.setViewportView(pnlEventFoodSizes);
//		pnlEventFoodSizes.setBorder(new LineBorder(new Color(0, 0, 0)));
//		pnlEventFoodSizes.setLayout(new MigLayout("", "[150px,grow]", "[]"));
//		
//		txtQuantity = new JTextField();
//		txtQuantity.setText("QUANTITY");
//		txtQuantity.setHorizontalAlignment(SwingConstants.CENTER);
//		txtQuantity.setFont(new Font("Monospaced", Font.PLAIN, 15));
//		txtQuantity.setEnabled(false);
//		txtQuantity.setEditable(false);
//		txtQuantity.setColumns(10);
//		pnlEvent.add(txtQuantity, "cell 1 2,grow");

		Thread thr=new Thread(new Runnable(){
			  public void run()
			  {
			      try{
				  		//Set Icon		
				  		pnlNormalImage = new BackgroundImageApp(file);
				  		pnlNormalImage.setBackground(SystemColor.menu);
				  		pnlNormalImage.setBorder(new LineBorder(Color.DARK_GRAY));
				  		pnlNormalImage.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				  		pnlNormalTable.add(pnlNormalImage, "cell 0 0 1 4,grow");
				  		pnlNormalImage.setLayout(null);
				  		pnlNormalTable.updateUI();
				  		pnlNormalTable.revalidate();
				  		pnlNormalTable.repaint();
						//Set Icon		
//						pnlEventImage = new BackgroundImageApp(file);
//						pnlEventImage.setBackground(SystemColor.menu);
//						pnlEventImage.setBorder(new LineBorder(Color.DARK_GRAY));
//						pnlEventImage.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
//						pnlEventTable.add(pnlEventImage, "cell 0 0 1 4,grow");
//						pnlEventImage.setLayout(null);
//						pnlEventTable.updateUI();
//						pnlEventTable.revalidate();
//						pnlEventTable.repaint();
						ViewNormalViewAll();
//						ViewEventViewAll();
						ViewNormalCategories();
//						ViewEventCategories();
						DefaultButtonColor();
						Thread.sleep(0);
				   }catch(Exception e){
					   
				   }
			   }
			 }); thr.start();
		ListSelectionModel normalPackageFood = tblNormalFoods.getSelectionModel();		
		normalPackageFood.addListSelectionListener( this );	
		
//		ListSelectionModel eventPackageFood = tblEventFoods.getSelectionModel();		
//		eventPackageFood.addListSelectionListener( this );	
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
	public void DefaultButtonColor(){
		
		btnNormalViewAll.setUI(new MetalButtonUI());
		btnNormalCreate.setUI(new MetalButtonUI());
		btnNormalBack.setUI(new MetalButtonUI());
		btnNormalRemove.setUI(new MetalButtonUI());
//		btnEventRemove.setUI(new MetalButtonUI());
//		btnEventCreate.setUI(new MetalButtonUI());
//		btnEventDelete.setUI(new MetalButtonUI());
		btnNormalAttachImage.setUI(new MetalButtonUI());
//		btnEventAttachImage.setUI(new MetalButtonUI());
//		btnEventViewAll.setUI(new MetalButtonUI());
		btnNormalDelete.setUI(new MetalButtonUI());
//		btnEventDelete.setUI(new MetalButtonUI());
		
	}	
    
	public void normalcreateAndUpdate(ActionListener createAndUpdate){
		btnNormalCreate.addActionListener(createAndUpdate);
	}	
	public void normaldelete(ActionListener delete){
		btnNormalDelete.addActionListener(delete);
	}	
	
	public void normalremove(ActionListener remove){
		btnNormalRemove.addActionListener(remove);
	}	

//	public void eventcreateAndUpdate(ActionListener createAndUpdate){
//		btnEventCreate.addActionListener(createAndUpdate);
//	}	
//	public void eventdelete(ActionListener delete){
//		btnEventDelete.addActionListener(delete);
//	}	
//	
//	public void eventremove(ActionListener remove){
//		btnEventRemove.addActionListener(remove);
//	}	

    public ModelNormalPackage getModelNormalPackage(){
    	mnp = new ModelNormalPackage();
    	mnp.setId(tfNormalPackageId.getText());
    	mnp.setName(tfNormalPackageName.getText());
    	mnp.setServingSize(String.valueOf(spnNormalServingSize.getValue()));
    	mnp.setTotal(Double.parseDouble(tfNormalTotal.getText()));
    	mnp.setPhotos(normalPath);
    	if(rdbtnEvent.isSelected()){
    		mnp.setType(rdbtnEvent.getText());
    	}else if(rdbtnRegular.isSelected()){
    		mnp.setType(rdbtnRegular.getText());
    	}
    	if(rdbtnPercent.isSelected()){
    		discountbypercent = Integer.parseInt(tfDiscount.getText());
    		discounttext = rdbtnPercent.getText();    		
    		discountbyprice = 0.00;
    	}else if (rdbtnPrice.isSelected()){
    		discountbyprice = Double.parseDouble(tfDiscount.getText());
    		discounttext = rdbtnPrice.getText();
    		discountbypercent = 0;
    	}
    	originalprice = Double.parseDouble(tfOriginalPrice.getText());
    	mnp.setDiscountbypercent(discountbypercent);
    	mnp.setDiscountbyprice(discountbyprice);
    	mnp.setDiscount(discounttext);
    	mnp.setOriginalprice(originalprice);
    	return mnp;
    }
    
//    public ModelEventPackage getModelEventPackage(){
//    	mep = new ModelEventPackage();
//    	mep.setId(tfEventPackageId.getText());
//    	mep.setName(tfEventPackageName.getText());
//    	mep.setServingSize(String.valueOf(spnEventServingSize.getValue()));
//    	mep.setTotal(Double.parseDouble(tfEventTotal.getText()));
//    	mep.setPhotos(eventPath);
//    	return mep;
//    }


    public void ViewNormalViewAll(){
    	try {
			c = new Connect();
			ResultSet rs;
			c.pst = c.con.prepareCall("call count_normal_foods_category(?)");
			c.pst.setString(1, "");
			c.pst.execute();
			rs = c.pst.getResultSet();
			while(rs.next()){
				normalViewAll = rs.getInt("count");
			}
			rs.close();
			c.pst.close();
			c.con.close();
		} catch (SQLException e) {
			// TODO: handle exception
		}
		btnNormalViewAll = new JButton("[" + normalViewAll + "]" + "View All");
		btnNormalViewAll.setForeground(new Color(255, 255, 255));
		btnNormalViewAll.setBackground(new Color(51, 122, 152));
		btnNormalViewAll.setName("");
		btnNormalViewAll.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNormalViewAll.setActionCommand("");
		btnNormalViewAll.setHorizontalAlignment(SwingConstants.LEADING);
		btnNormalViewAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlNormalFoodSizes.removeAll();	
				pnlNormalFoodSizes.repaint();
				pnlNormalFoodSizes.revalidate();
				ViewNormalFoods(btnNormalViewAll.getActionCommand());
				for(int j = 1; j<iNormalCategory;j++){
					btnNormalCategories[j].setBackground(new Color(51, 122, 152));
				}
				btnNormalViewAll.setBackground(new Color(255, 99, 71));
			}
		});
		btnNormalViewAll.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlNormalCategory.add(btnNormalViewAll, "cell 0 0, grow");
    }
    
//    public void ViewEventViewAll(){
//    	try {
//			c = new Connect();
//			ResultSet rs;
//			c.pst = c.con.prepareCall("call count_event_foods_category(?)");
//			c.pst.setString(1, "");
//			c.pst.execute();
//			rs = c.pst.getResultSet();
//			while(rs.next()){
//				eventViewAll = rs.getInt("count");
//			}
//			rs.close();
//			c.pst.close();
//			c.con.close();
//		} catch (SQLException e) {
//			// TODO: handle exception
//		}
//    }
//    //------------------------------------------------------------------------------------------------------------------
    
    public void ViewNormalCategories(){
    	iNormalCategory = 1;
		try {
			c = new Connect();
			c.pst = c.con.prepareCall("{call count_categories()}");
			c.pst.execute();	
			c.rs = c.pst.getResultSet();
			int count = 0;
			while(c.rs.next()){
				count = c.rs.getInt("COUNT");				
			}
			btnNormalCategories = new JButton[count+1];
			food = new int[count+1];
			c.rs.close();
			c.pst.close();
			c.con.close();
			//--------------------------------------------------------
			
			c= new Connect();
			c.pst = c.con.prepareCall("{call select_all_categories()}");
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			while(c.rs.next()){
				
				categoryNormalName = c.rs.getString("Category Name");
				ResultSet rs;
    			c.pst = c.con.prepareCall("call count_normal_foods_category(?)");
    			c.pst.setString(1, categoryNormalName);
    			c.pst.execute();
    			rs = c.pst.getResultSet();
    			while(rs.next()){
    				food[iNormalCategory] = rs.getInt("count");
    			}
				if(food[iNormalCategory] != 0){
				btnNormalCategories[iNormalCategory] = new JButton("[" + food[iNormalCategory] + "]" + categoryNormalName);
				btnNormalCategories[iNormalCategory].setName(categoryNormalName);
				btnNormalCategories[iNormalCategory].setFont(new Font("Monospaced", Font.PLAIN, 15));
				btnNormalCategories[iNormalCategory].setActionCommand(categoryNormalName);
				btnNormalCategories[iNormalCategory].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnNormalCategories[iNormalCategory].setBackground(new Color(51, 122, 152));
				btnNormalCategories[iNormalCategory].setForeground(new Color(255, 255, 255));
				btnNormalCategories[iNormalCategory].setHorizontalAlignment(SwingConstants.LEADING);
				pnlNormalCategory.add(btnNormalCategories[iNormalCategory], "cell 0 "+iNormalCategory+",grow");

				btnNormalCategories[iNormalCategory].setUI(new MetalButtonUI());
				
				//Category Button Action Listener
				btnNormalCategories[iNormalCategory].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
		            	for(int j = 1; j < iNormalCategory; j++){
		    				btnNormalCategories[j].setBackground(new Color(51, 122, 152));
		    				btnNormalViewAll.setBackground(new Color(51, 122, 152));
							if(e.getActionCommand().equalsIgnoreCase(btnNormalCategories[j].getActionCommand())){
								pnlNormalFoodSizes.removeAll();	
								pnlNormalFoodSizes.repaint();
								pnlNormalFoodSizes.revalidate();
								btnNormalCategories[j].setBackground(new Color(255, 99, 71));
								ViewNormalFoods(btnNormalCategories[j].getActionCommand());
								
							}
		            	}
					}
				  });
				//End of Category Button Action Listener
				iNormalCategory++;
				}
			}
	
			c.rs.close();
			c.pst.close();
			c.con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
    }

//    //------------------------------------------------------------------------------------------------------------------
//   
//    public void ViewEventCategories(){
//    	iEventCategory = 1;
//		try {
//			c = new Connect();
//			c.pst = c.con.prepareCall("{call count_categories()}");
//			c.pst.execute();	
//			c.rs = c.pst.getResultSet();
//			int count = 0;
//			while(c.rs.next()){
//				count = c.rs.getInt("COUNT");				
//			}
//			btnEventCategories = new JButton[count+1];
//			food = new int[count+1];
//			c.rs.close();
//			c.pst.close();
//			c.con.close();
//			//--------------------------------------------------------
//			
//			c= new Connect();
//			c.pst = c.con.prepareCall("{call select_all_categories()}");
//			c.pst.execute();
//			c.rs = c.pst.getResultSet();
//			while(c.rs.next()){
//				
//				categoryEventName = c.rs.getString("Category Name");
//				ResultSet rs;
//    			c.pst = c.con.prepareCall("call count_event_foods_category(?)");
//    			c.pst.setString(1, categoryEventName);
//    			c.pst.execute();
//    			rs = c.pst.getResultSet();
//    			while(rs.next()){
//    				food[iEventCategory] = rs.getInt("count");
//    			}
//				if(food[iEventCategory] != 0){
//				
//				btnEventCategories[iEventCategory] = new JButton(categoryEventName + " (" + food[iEventCategory] + ")");
//				btnEventCategories[iEventCategory].setName(categoryEventName);
//				btnEventCategories[iEventCategory].setFont(new Font("Monospaced", Font.PLAIN, 15));
//				btnEventCategories[iEventCategory].setActionCommand(categoryEventName);
//				btnEventCategories[iEventCategory].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//				btnEventCategories[iEventCategory].setBackground(new Color(51, 122, 152));
//				btnEventCategories[iEventCategory].setForeground(new Color(255, 255, 255));
//				pnlEventCategory.add(btnEventCategories[iEventCategory], "cell 0 "+iEventCategory+",grow");
//
//				btnEventCategories[iEventCategory].setUI(new MetalButtonUI());
//				//Category Button Action Listener
//				btnEventCategories[iEventCategory].addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent e) {
//		            	for(int j = 1; j < iEventCategory; j++){
//		    				btnEventCategories[j].setBackground(new Color(51, 122, 152));
//		    				btnEventViewAll.setBackground(new Color(51, 122, 152));
//							if(e.getActionCommand().equalsIgnoreCase(btnEventCategories[j].getActionCommand())){
//								pnlEventFoodSizes.removeAll();	
//								pnlEventFoodSizes.repaint();
//								pnlEventFoodSizes.revalidate();
//								btnEventCategories[j].setBackground(new Color(255, 99, 71));
//								ViewEventFoods(btnEventCategories[j].getActionCommand());
//							}
//		            	}
//					}
//				  });
//				//End of Category Button Action Listener
//				
//				
//				iEventCategory++;
//				}
//			}
//	
//			c.rs.close();
//			c.pst.close();
//			c.con.close();
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
//    }
//
//    //------------------------------------------------------------------------------------------------------------------
    
    public void ViewNormalFoods(String categoryNormalName){
    	iNormalFood = 1;
		try {
			c = new Connect();
			c.pst = c.con.prepareCall("{call count_normal_foods()}");
			c.pst.execute();	
			c.rs = c.pst.getResultSet();
			int count = 0;
			while(c.rs.next()){
				count = c.rs.getInt("COUNT");				
			}
			btnNormalFoods = new JButton[count+1];
			c.rs.close();
			c.pst.close();
			c.con.close();
			
			c= new Connect();
			c.pst = c.con.prepareCall("{call select_normal_food_via_category(?)}");
			c.pst.setString(1, categoryNormalName);
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			pnlNormalFood.removeAll();
			while(c.rs.next()){
				foodNormalName = c.rs.getString("normal_food_name");
				foodNormalId = c.rs.getString("normal_food_id");
				foodNormalType = c.rs.getString("normal_food_type");
				if(foodNormalType.equals("Regular")){
					foodNormalType = "R";
				}else if (foodNormalType.equals("Event")){
					foodNormalType = "E";
				}
				btnNormalFoods[iNormalFood] = new JButton("(" + foodNormalType + ")" + foodNormalName);
				btnNormalFoods[iNormalFood].setName(foodNormalName);
				btnNormalFoods[iNormalFood].setFont(new Font("Monospaced", Font.PLAIN, 15));
				btnNormalFoods[iNormalFood].setActionCommand(foodNormalId);
				btnNormalFoods[iNormalFood].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnNormalFoods[iNormalFood].setBackground(new Color(51, 122, 152));
				btnNormalFoods[iNormalFood].setForeground(new Color(255, 255, 255));
				btnNormalFoods[iNormalFood].setHorizontalAlignment(SwingConstants.LEADING);
				pnlNormalFood.add(btnNormalFoods[iNormalFood], "cell 0 "+iNormalFood+",grow");

				btnNormalFoods[iNormalFood].setUI(new MetalButtonUI());
				//Category Button Action Listener
				btnNormalFoods[iNormalFood].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
		            	for(int j = 1; j < iNormalFood; j++){
		            		btnNormalFoods[j].setBackground(new Color(51, 122, 152));
							if(e.getActionCommand().equalsIgnoreCase(btnNormalFoods[j].getActionCommand())){	
								packageNormalName = btnNormalFoods[j].getName();	
								packageNormalId = btnNormalFoods[j].getActionCommand();
								ViewNormalFoodSizes(btnNormalFoods[j].getActionCommand());
								btnNormalFoods[j].setBackground(new Color(255, 99, 71));
							}
		            	}
					}
				  });
				//End of Category Button Action Listener				
				iNormalFood++;
			}
			pnlNormalFood.repaint();
			pnlNormalFood.revalidate();
			c.rs.close();
			c.pst.close();
			c.con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
    }
    
    //------------------------------------------------------------------------------------------------------------------
//    
//    public void ViewEventFoods(String categoryEventName){
//    	iEventFood = 1;
//		try {
//			c = new Connect();
//			c.pst = c.con.prepareCall("{call count_event_foods()}");
//			c.pst.execute();	
//			c.rs = c.pst.getResultSet();
//			int count = 0;
//			while(c.rs.next()){
//				count = c.rs.getInt("COUNT");				
//			}
//			btnEventFoods = new JButton[count+1];
//			c.rs.close();
//			c.pst.close();
//			c.con.close();
//			
//			c= new Connect();
//			c.pst = c.con.prepareCall("{call select_event_food_via_category(?)}");
//			c.pst.setString(1, categoryEventName);
//			c.pst.execute();
//			c.rs = c.pst.getResultSet();
//			pnlEventFood.removeAll();
//			while(c.rs.next()){
//				foodEventName = c.rs.getString("event_food_name");
//				foodEventId = c.rs.getString("event_food_id");
//				foodPackageType = c.rs.getString("event_food_type");
//				btnEventFoods[iEventFood] = new JButton(foodEventName + "[" + foodPackageType + "]");
//				btnEventFoods[iEventFood].setName(foodEventName);
//				btnEventFoods[iEventFood].setFont(new Font("Monospaced", Font.PLAIN, 15));
//				btnEventFoods[iEventFood].setActionCommand(foodEventId);
//				btnEventFoods[iEventFood].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//				btnEventFoods[iEventFood].setBackground(new Color(51, 122, 152));
//				btnEventFoods[iEventFood].setForeground(new Color(255, 255, 255));
//				pnlEventFood.add(btnEventFoods[iEventFood], "cell 0 "+iEventFood+",grow");
//
//				btnEventFoods[iEventFood].setUI(new MetalButtonUI());
//				//Category Button Action Listener
//				btnEventFoods[iEventFood].addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent e) {
//		            	for(int j = 1; j < iEventFood; j++){
//		            		btnEventFoods[j].setBackground(new Color(51, 122, 152));
//							if(e.getActionCommand().equalsIgnoreCase(btnEventFoods[j].getActionCommand())){	
//								packageEventName = btnEventFoods[j].getName();	
//								packageEventId = btnEventFoods[j].getActionCommand();
//								ViewEventFoodSizes(btnEventFoods[j].getActionCommand());
//								btnEventFoods[j].setBackground(new Color(255, 99, 71));
//							}
//		            	}
//					}
//				  });
//				//End of Category Button Action Listener				
//				iEventFood++;
//			}
//			pnlEventFood.repaint();
//			pnlEventFood.revalidate();
//			c.rs.close();
//			c.pst.close();
//			c.con.close();
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
//    }
//    
    //------------------------------------------------------------------------------------------------------------------
    
    
    public void ViewNormalFoodSizes(String foodNormalId){
    	iNormalFoodSizes = 1;
		try {
			c = new Connect();
			c.pst = c.con.prepareCall("{call count_sizes()}");
			c.pst.execute();	
			c.rs = c.pst.getResultSet();
			int count = 0;
			while(c.rs.next()){
				count = c.rs.getInt("COUNT");				
			}
			btnNormalFoodSizes = new JButton[count+1];
			spnNormalFoodSizes = new JSpinner[count+1];
			c.rs.close();
			c.pst.close();
			c.con.close();
			
			c= new Connect();
			c.pst = c.con.prepareCall("{call select_size_via_normal_food(?)}");
			c.pst.setString(1, foodNormalId);
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			pnlNormalFoodSizes.removeAll();
			while(c.rs.next()){
				sizeNormalName = c.rs.getString("normal_food_size");
				sizeNormalPackagePrice = c.rs.getString("normal_package_price");
				btnNormalFoodSizes[iNormalFoodSizes] = new JButton(sizeNormalName + " " + sizeNormalPackagePrice);
				btnNormalFoodSizes[iNormalFoodSizes].setName(sizeNormalPackagePrice);
				btnNormalFoodSizes[iNormalFoodSizes].setFont(new Font("Monospaced", Font.PLAIN, 15));
				btnNormalFoodSizes[iNormalFoodSizes].setActionCommand(sizeNormalName);
				btnNormalFoodSizes[iNormalFoodSizes].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				pnlNormalFoodSizes.add(btnNormalFoodSizes[iNormalFoodSizes], "cell 0 "+iNormalFoodSizes+",grow");

				btnNormalFoodSizes[iNormalFoodSizes].setUI(new MetalButtonUI());
				
				spnNormalFoodSizes[iNormalFoodSizes] = new JSpinner();
				spnNormalFoodSizes[iNormalFoodSizes].setName(sizeNormalName);
				spnNormalFoodSizes[iNormalFoodSizes].setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
				((DefaultEditor) spnNormalFoodSizes[iNormalFoodSizes].getEditor()).getTextField().setEditable(false);
				spnNormalFoodSizes[iNormalFoodSizes].setEditor(new JSpinner.DefaultEditor(spnNormalFoodSizes[iNormalFoodSizes]));
				spnNormalFoodSizes[iNormalFoodSizes].setFont(new Font("Monospaced", Font.PLAIN, 15));
				spnNormalFoodSizes[iNormalFoodSizes].setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				pnlNormalFoodSizes.add(spnNormalFoodSizes[iNormalFoodSizes], "cell 1 "+iNormalFoodSizes+",grow");
				btnNormalFoodSizes[iNormalFoodSizes].setBackground(new Color(51, 122, 152));
				btnNormalFoodSizes[iNormalFoodSizes].setForeground(new Color(255, 255, 255));
				
				spnNormalFoodSizes[iNormalFoodSizes].addChangeListener(new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent e) {
						// TODO Auto-generated method stub
		            	for(int j = 1; j < iNormalFoodSizes; j++){
							if(e.getSource() == spnNormalFoodSizes[j]){	
								packageNormalPrice = (Double.parseDouble(btnNormalFoodSizes[j].getName()) * (int) spnNormalFoodSizes[j].getValue());
								btnNormalFoodSizes[j].setText(btnNormalFoodSizes[j].getActionCommand() + " " + packageNormalPrice);
							}
		            	}
					}
				});
				
				//Category Button Action Listener
				btnNormalFoodSizes[iNormalFoodSizes].addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						packageNormalTotalTemp = Double.parseDouble(tfNormalTotal.getText());
						packageNormalTotal = 0.00;
		            	for(int j = 1; j < iNormalFoodSizes; j++){
		            		btnNormalFoodSizes[j].setBackground(new Color(51, 122, 152));
							if(e.getActionCommand().equalsIgnoreCase(btnNormalFoodSizes[j].getActionCommand())){	
								DefaultTableModel model = (DefaultTableModel) tblNormalFoods.getModel();
								btnNormalFoodSizes[j].getActionCommand();
								packageNormalPrice = (Double.parseDouble(btnNormalFoodSizes[j].getName()) * (int) spnNormalFoodSizes[j].getValue());	
								packageNormalQuantity = (int) spnNormalFoodSizes[j].getValue();
								packageNormalSize = btnNormalFoodSizes[j].getActionCommand();
								Object[] row = { packageNormalId, packageNormalName, packageNormalSize, packageNormalQuantity, packageNormalPrice };
								model.addRow(row);
								packageNormalTotal = packageNormalTotalTemp + packageNormalPrice;
								btnNormalFoodSizes[j].setBackground(new Color(255, 99, 71));
								tfNormalTotal.setText(String.valueOf(packageNormalTotal));
								originalprice = packageNormalTotal;
								RetrieveOriginalTotal();
								CalculateDiscountTotal();
							}
		            	}
					}
				  });
				//End of Category Button Action Listener				
				iNormalFoodSizes++;
			}
			pnlNormalFoodSizes.repaint();
			pnlNormalFoodSizes.revalidate();
			c.rs.close();
			c.pst.close();
			c.con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
    }
    
    //------------------------------------------------------------------------------------------------------------------
//    
//    public void ViewEventFoodSizes(String foodEventId){
//    	iEventFoodSizes = 1;
//		try {
//			c = new Connect();
//			c.pst = c.con.prepareCall("{call count_sizes()}");
//			c.pst.execute();	
//			c.rs = c.pst.getResultSet();
//			int count = 0;
//			while(c.rs.next()){
//				count = c.rs.getInt("COUNT");				
//			}
//			btnEventFoodSizes = new JButton[count+1];
//			spnEventFoodSizes = new JSpinner[count+1];
//			c.rs.close();
//			c.pst.close();
//			c.con.close();
//			
//			c= new Connect();
//			c.pst = c.con.prepareCall("{call select_size_via_event_food(?)}");
//			c.pst.setString(1, foodEventId);
//			c.pst.execute();
//			c.rs = c.pst.getResultSet();
//			pnlEventFoodSizes.removeAll();
//			while(c.rs.next()){
//				sizeEventName = c.rs.getString("event_food_size");
//				sizeEventPackagePrice = c.rs.getString("event_package_price");
//				btnEventFoodSizes[iEventFoodSizes] = new JButton(sizeEventName + " " + sizeEventPackagePrice);
//				btnEventFoodSizes[iEventFoodSizes].setName(sizeEventPackagePrice);
//				btnEventFoodSizes[iEventFoodSizes].setFont(new Font("Monospaced", Font.PLAIN, 15));
//				btnEventFoodSizes[iEventFoodSizes].setActionCommand(sizeEventName);
//				btnEventFoodSizes[iEventFoodSizes].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//				pnlEventFoodSizes.add(btnEventFoodSizes[iEventFoodSizes], "cell 0 "+iEventFoodSizes+",grow");
//
//				btnEventFoodSizes[iEventFoodSizes].setUI(new MetalButtonUI());
//				
//				spnEventFoodSizes[iEventFoodSizes] = new JSpinner();
//				spnEventFoodSizes[iEventFoodSizes].setName(sizeEventName);
//				spnEventFoodSizes[iEventFoodSizes].setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
//				((DefaultEditor) spnEventFoodSizes[iEventFoodSizes].getEditor()).getTextField().setEditable(false);
//				spnEventFoodSizes[iEventFoodSizes].setEditor(new JSpinner.DefaultEditor(spnEventFoodSizes[iEventFoodSizes]));
//				spnEventFoodSizes[iEventFoodSizes].setFont(new Font("Monospaced", Font.PLAIN, 15));
//				spnEventFoodSizes[iEventFoodSizes].setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
//				pnlEventFoodSizes.add(spnEventFoodSizes[iEventFoodSizes], "cell 1 "+iEventFoodSizes+",grow");
//
//				btnEventFoodSizes[iEventFoodSizes].setBackground(new Color(51, 122, 152));
//				btnEventFoodSizes[iEventFoodSizes].setForeground(new Color(255, 255, 255));
//				spnEventFoodSizes[iEventFoodSizes].addChangeListener(new ChangeListener() {
//					@Override
//					public void stateChanged(ChangeEvent e) {
//						// TODO Auto-generated method stub
//		            	for(int j = 1; j < iEventFoodSizes; j++){
//							if(e.getSource() == spnEventFoodSizes[j]){	
//								packageEventPrice = (Double.parseDouble(btnEventFoodSizes[j].getName()) * (int) spnEventFoodSizes[j].getValue());
//								btnEventFoodSizes[j].setText(btnEventFoodSizes[j].getActionCommand() + " " + packageEventPrice);
//							}
//		            	}
//					}
//				});
//				
//				//Category Button Action Listener
//				btnEventFoodSizes[iEventFoodSizes].addActionListener(new ActionListener() {
//
//					public void actionPerformed(ActionEvent e) {
//						packageEventTotalTemp = Double.parseDouble(tfEventTotal.getText());
//						packageEventTotal = 0.00;
//		            	for(int j = 1; j < iEventFoodSizes; j++){
//		            		btnEventFoodSizes[j].setBackground(new Color(51, 122, 152));
//							if(e.getActionCommand().equalsIgnoreCase(btnEventFoodSizes[j].getActionCommand())){	
//								DefaultTableModel model = (DefaultTableModel) tblEventFoods.getModel();
//								btnEventFoodSizes[j].getActionCommand();
//								packageEventPrice = (Double.parseDouble(btnEventFoodSizes[j].getName()) * (int) spnEventFoodSizes[j].getValue());	
//								packageEventQuantity = (int) spnEventFoodSizes[j].getValue();
//								packageEventSize = btnEventFoodSizes[j].getActionCommand();
//								Object[] row = { packageEventId, packageEventName, packageEventSize, packageEventQuantity, packageEventPrice };
//								model.addRow(row);
//								packageEventTotal = packageEventTotalTemp + packageEventPrice;
//								
//								tfEventTotal.setText(String.valueOf(packageEventTotal));
//								btnEventFoodSizes[j].setBackground(new Color(255, 99, 71));
//							}
//		            	}
//					}
//				  });
//				//End of Category Button Action Listener				
//				iEventFoodSizes++;
//			}
//			pnlEventFoodSizes.repaint();
//			pnlEventFoodSizes.revalidate();
//			c.rs.close();
//			c.pst.close();
//			c.con.close();
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
//    }
//    
    //------------------------------------------------------------------------------------------------------------------
    
	@Override
	public void valueChanged(ListSelectionEvent event) {
		// TODO Auto-generated method stub
		 if( event.getSource() == tblNormalFoods.getSelectionModel() && event.getFirstIndex() >= 0 ){
			btnNormalRemove.setEnabled(true);
			
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

	//Image scale to fit on Panel class
		public class BackgroundImageApp extends JPanel {
		
				BufferedImage originalImage;
				BufferedImage scaledImage;
		
				BackgroundImageApp(File file) {
				    setPreferredSize(new Dimension(320, 200));
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
		
		public void RetrieveOriginalTotal(){

			total = 0.00;
			DefaultTableModel dtm = (DefaultTableModel) tblNormalFoods.getModel();
		    int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
		    Object[][] tableData = new Object[nRow][nCol];

		    for (int i = 0 ; i < nRow ; i++){
		        for (int j = 0 ; j < nCol ; j++){
		            tableData[i][j] = dtm.getValueAt(i,j);
		        }
				holdTotal = Double.parseDouble(tableData[i][4].toString());
				total = total + holdTotal;
		    }
		}
		
		public void CalculateDiscountTotal(){
			try {
				discount = Double.parseDouble(tfDiscount.getText());
				if(rdbtnPercent.isSelected()){
					discount = discount / 100;
					discount = discount * total;
					discount = total - discount;
					tfOriginalPrice.setText(Math.round(total) + ".00");
					tfNormalTotal.setText(Math.round(discount) + ".00");
					
				}else if(rdbtnPrice.isSelected()){
					discount = total - discount;
					tfOriginalPrice.setText(Math.round(total) + ".00");
					tfNormalTotal.setText(Math.round(discount) + ".00");
				}
			} catch (Exception e) {
			}
		}
		public void RefreshPackage(){
			DefaultTableModel dm = (DefaultTableModel) tblNormalFoods.getModel();
			int rowCount = dm.getRowCount();
			//Remove rows one by one from the end of the table
			for (int i = rowCount - 1; i >= 0; i--) {
			    dm.removeRow(i);
			}
			Dashboard.npt.ViewEventPackage();
			Dashboard.npt.ViewNormalPackage();
			Dashboard.npt.CounterNormalPackage();
			tfNormalTotal.setText("0.0");
			tfOriginalPrice.setText("0.0");
			spnNormalServingSize.setValue(1);
			tfNormalPackageName.setText("");
			btnNormalCreate.setName("CREATE");
			btnNormalCreate.setEnabled(true);
			btnNormalBack.setEnabled(false);
			btnNormalDelete.setEnabled(false);
			btnNormalRemove.setEnabled(false);
			//Set Icon		
			file = new File("src\\Images\\Default\\food.jpg");
			normalPath = file.getAbsolutePath();

			pnlNormalTable.remove(pnlNormalImage);
			pnlNormalImage.removeAll();
			pnlNormalImage.updateUI();
			
			pnlNormalImage = new BackgroundImageApp(file);
			pnlNormalImage.setBackground(SystemColor.menu);
			pnlNormalImage.setBorder(new LineBorder(Color.DARK_GRAY));
			pnlNormalImage.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			pnlNormalTable.add(pnlNormalImage, "cell 0 0 1 4,grow");
			pnlNormalImage.setLayout(null);
			pnlNormalImage.revalidate();
			pnlNormalImage.repaint();
		}
		public void RefreshSelectList(){
			Connect c;					
			try {
				c = new Connect();
				c.pst = c.con.prepareCall("call select_all_normal_food_package(?)");
				c.pst.setString(1, listValue);
				System.out.println(listValue);
				c.pst.execute();
				c.rs = c.pst.getResultSet();
				tblNormalFoods.setModel(DbUtils.resultSetToTableModel(c.rs));
				c.rs.close();
				c.pst.close();
				c.con.close();
				c = new Connect();
				c.pst = c.con.prepareCall("call select_all_normal_package(?)");
				c.pst.setString(1, listValue);
				c.pst.execute();
				c.rs = c.pst.getResultSet();
				while(c.rs.next()){
					npi = c.rs.getString("normal_package_id");
					npn = c.rs.getString("normal_package_name");
					nppss = c.rs.getInt("normal_package_price_serving_size");
					npt = c.rs.getString("normal_package_total");
					npty = c.rs.getString("normal_package_type");
					np = c.rs.getString("normal_package_photos");
					npd = c.rs.getString("normal_package_discount");
					npop = c.rs.getString("normal_package_original_price");
					npdper = c.rs.getString("normal_package_discount_by_percent");
					npdpri = c.rs.getString("normal_package_discount_by_price");
				}

				//Set Icon		
				file = new File(np);
				normalPath = file.getAbsolutePath();

				pnlNormalTable.remove(pnlNormalImage);
				pnlNormalImage.removeAll();
				pnlNormalImage.updateUI();
				
				pnlNormalImage = new BackgroundImageApp(file);
				pnlNormalImage.setBackground(SystemColor.menu);
				pnlNormalImage.setBorder(new LineBorder(Color.DARK_GRAY));
				pnlNormalImage.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				pnlNormalTable.add(pnlNormalImage, "cell 0 0 1 4,grow");
				pnlNormalImage.setLayout(null);
				pnlNormalTable.updateUI();
				pnlNormalTable.revalidate();
				pnlNormalTable.repaint();

				if(npty.equalsIgnoreCase("Regular")){
					rdbtnRegular.setSelected(true);
				}else if(npty.equalsIgnoreCase("Event")){
					rdbtnEvent.setSelected(true);
				}
				if(npd.equalsIgnoreCase("Percent")){
					rdbtnPercent.setSelected(true);
					tfDiscount.setText(npdper);
				}else if(npd.equalsIgnoreCase("Price")){
					rdbtnPrice.setSelected(true);
					tfDiscount.setText(npdpri);
				}
				tfOriginalPrice.setText((Math.round(Double.parseDouble(npop)) + ".00"));
				tfNormalPackageId.setText(npi);
				tfNormalPackageName.setText(npn);
				spnNormalServingSize.setValue(nppss);
				tfNormalTotal.setText((Math.round(Double.parseDouble(npt)) + ".00"));
				btnNormalCreate.setName("UPDATE");
				btnNormalCreate.setEnabled(true);
				btnNormalDelete.setEnabled(true);
				btnNormalBack.setEnabled(true);
				btnNormalRemove.setEnabled(false);	
				c.rs.close();
				c.pst.close();
				c.con.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
}
