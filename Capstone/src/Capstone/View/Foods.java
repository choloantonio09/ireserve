package Capstone.View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.DebugGraphics;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.metal.MetalButtonUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Capstone.Controller.AddController;
import Capstone.Controller.RemoveController;
import Capstone.Controller.UpdateController;
import Capstone.Database.Connect;
import Capstone.Model.ModelEventFood;
import Capstone.Model.ModelNormalFood;
import Capstone.Model.ModelSize;
//import Capstone.Table.EventFoodTable;
import Capstone.Table.NormalFoodTable;
import Capstone.Table.SizeTable;
import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;

public class Foods extends JPanel implements ListSelectionListener, ActionListener, ItemListener, MouseListener, FocusListener{
	public JTextField tfNormalFoodPrice;
	
	public JTable tblNormalFoodSizes;
	public JTable tblNormalFoods;
	
	public JButton btnNormalAddFood;
	public JButton btnNormalAddSize;
	public JButton btnNormalRemoveSize;
	
	public JPanel pnlNormalButtons;
	public JPanel pnlNormalButtonSizes;
	public JPanel pnlNormalFoodDetails;
	public JPanel pnlFoodSizesDetails;
	public static JPanel pnlNormalFoodSizes;
    public JPanel pnlNormal;
    public static JButton btnNormalCreateNewSizes;
	
	public JLabel lblNormalFoodPrice;
    public JLabel lblNormalFoodNameSize;
	public static JComboBox cbNormalSizes;
	
	boolean hey = true;
    boolean pick;
	static boolean boolCheck = true;
	
    public JTabbedPane tpPackage;


	public String normalPath;
    public String normalFoodId;
    public String normalFoodType;
    public String normalsize;
    
	public String eventPath;
    public String eventFoodId;
    public String eventFoodType;
    public String eventsize;
	
    public ImageIcon normalImageIcon;
    public ImageIcon eventImageIcon;

	
    JFileChooser normalJfBrowse;
    JFileChooser eventJfBrowse;
    
	Sizes s;
	ModelSize ms;
	SizeTable st;
	
	ModelNormalFood mnf;
	ModelEventFood mef;
	File file = new File("src\\Images\\Default\\food.jpg");
	DefaultListCellRenderer dlcr = new DefaultListCellRenderer(); 
	private JButton btnNormalUpdateSize;
	public JButton btnNormalCancelSize;
	private JScrollPane scrollPane;
	
	Double normalPrice;
	Double discount;
	Double packagePrice;
	protected NormalFoodTable nft;
	private String normalFoodName;
	private String normalFoodCategory;
	private String normalFoodPhotos;
	private String normalFoodStatus;
	private JButton btnNormalUpdate;
	private JButton btnNormalCancel;
	private String eventFoodName;
	private String eventFoodCategory;
	private String eventFoodPhotos;
	private String eventFoodStatus;
//	protected EventFoodTable eft;
	private JLabel lblDescription;
	private JTextField tfNormalDescSize;
	private JLabel tfEventDesc;
	private JLabel tfNormalDesc;
	private JLabel lblNameSize;
	
	/**Create the panel.**/
	
	public Foods() {
		System.setProperty("Quaqua.tabLayoutPolicy","wrap");
         // set the Quaqua Look and Feel in the UIManager
		try {
			UIManager.setLookAndFeel(ch.randelshofer.quaqua.QuaquaManager.getLookAndFeel());
		} catch (Exception e) {
			e.printStackTrace();
		}
		normalPath = file.getAbsoluteFile().getAbsolutePath();
		eventPath = file.getAbsoluteFile().getAbsolutePath();
		dlcr.setHorizontalAlignment(DefaultListCellRenderer.CENTER); 
		setBackground(SystemColor.menu);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setVisible(true);
		setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		scrollPane = new JScrollPane();
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		add(scrollPane, "cell 0 0,grow");
		
		tpPackage = new JTabbedPane(JTabbedPane.TOP);
		tpPackage.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(tpPackage);
		
		pnlNormal = new JPanel();
		pnlNormal.setBackground(getPanelColor());
		tpPackage.addTab("Food", null, pnlNormal, "Food");
		tpPackage.setUI(new AquaTabbedPaneUI());
		pnlNormal.setLayout(new MigLayout("", "[][grow][][193.00,grow]", "[][][83.00,grow]"));
		
		pnlNormalButtons = new JPanel();
		pnlNormalButtons.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		pnlNormalButtons.setBackground(SystemColor.menu);
		pnlNormal.add(pnlNormalButtons, "cell 0 0 4 1,growx");
		pnlNormalButtons.setLayout(new MigLayout("", "[][][]", "[]"));
		
		btnNormalAddFood = new JButton("Add food");
		btnNormalAddFood.setContentAreaFilled(false);
		btnNormalAddFood.setOpaque(false);
		btnNormalAddFood.setIcon(new ImageIcon(Foods.class.getResource("/Images/Icon/plus.PNG")));
		btnNormalAddFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NormalFoodForm nff = new NormalFoodForm();
				nff.setTitle("Add food");
				nft = new NormalFoodTable(nff);
				nft.CounterNormalFood();
				nft.ViewCategory();
				new AddController(nff, nft);
				nff.setModal(true);
				nff.setVisible(true);
			}
		});
		btnNormalAddFood.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnlNormalButtons.add(btnNormalAddFood, "cell 0 0");
		btnNormalAddFood.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		btnNormalUpdate = new JButton("Update food");
		btnNormalUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNormalUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NormalFoodForm nff = new NormalFoodForm();
				nff.setTitle("Update food");
				nft = new NormalFoodTable(nff);
				nft.ViewCategory();
				nff.tfId.setText(normalFoodId);
				nff.tfName.setText(normalFoodName);
				nff.cbCategory.setSelectedItem(normalFoodCategory);
				if(normalFoodType.equalsIgnoreCase("Regular")){
					nff.rdbtnRegular.setSelected(true);
				}else if(normalFoodType.equalsIgnoreCase("Event")){
					nff.rdbtnEvent.setSelected(true);
				}
				nff.destination = normalFoodPhotos;
				nff.setIcon();
				new UpdateController(nff, nft);
				nff.setModal(true);
				nff.setVisible(true);
			}
		});
		btnNormalUpdate.setIcon(new ImageIcon(Foods.class.getResource("/Images/Icon/update.png")));
		btnNormalUpdate.setOpaque(false);
		btnNormalUpdate.setFont(new Font("Monospaced", Font.PLAIN, 15));
		btnNormalUpdate.setEnabled(false);
		btnNormalUpdate.setContentAreaFilled(false);
		pnlNormalButtons.add(btnNormalUpdate, "cell 1 0");
		
		btnNormalCancel = new JButton("Back");
		btnNormalCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNormalCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tblNormalFoods.clearSelection();
				Dashboard.mnf = new ModelNormalFood();
				Dashboard.nft = new NormalFoodTable(Dashboard.f);
				btnNormalAddFood.setEnabled(true);
				btnNormalUpdate.setEnabled(false);
				btnNormalCancel.setEnabled(false);
				
				DefaultTableModel dm = (DefaultTableModel) tblNormalFoodSizes.getModel();

			    for (int i = 0; i < dm.getRowCount(); i++) {
			        for (int j = 0; j < dm.getColumnCount(); j++) {
			            dm.setValueAt("", i, j);
			        }
			    }
				lblNormalFoodNameSize.setText(null);;
				btnNormalCancelSize.setEnabled(false);
				btnNormalUpdateSize.setEnabled(false);
				btnNormalRemoveSize.setEnabled(false);
				btnNormalAddSize.setEnabled(false);
				ViewNormalSize();
				Dashboard.nft.ViewNormalFoodSizes(lblNormalFoodNameSize.getText());
				Dashboard.nft.ViewNormalNotInSize(lblNormalFoodNameSize.getText(), false, null);
				tfNormalFoodPrice.setText("0.00");
				tfNormalDescSize.setText(null);
				lblNameSize.setText(null);
			}
		});
		btnNormalCancel.setIcon(new ImageIcon(Foods.class.getResource("/Images/Icon/cancel.png")));
		btnNormalCancel.setOpaque(false);
		btnNormalCancel.setFont(new Font("Monospaced", Font.PLAIN, 15));
		btnNormalCancel.setEnabled(false);
		btnNormalCancel.setContentAreaFilled(false);
		pnlNormalButtons.add(btnNormalCancel, "cell 2 0");
		
		pnlNormalFoodSizes = new JPanel();
		pnlNormalFoodSizes.setBackground(SystemColor.menu);
		pnlNormal.add(pnlNormalFoodSizes, "cell 2 1,growx,aligny top");
		pnlNormalFoodSizes.setLayout(new MigLayout("", "[grow]", "[][][][][][]"));
		
		//Normal Create new Sizes
		btnNormalCreateNewSizes = new JButton("Add size");
		btnNormalCreateNewSizes.setOpaque(false);
		btnNormalCreateNewSizes.setContentAreaFilled(false);
		btnNormalCreateNewSizes.setIcon(new ImageIcon(Foods.class.getResource("/Images/Icon/plus.PNG")));
		btnNormalCreateNewSizes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNormalCreateNewSizes.addActionListener(this);
		btnNormalCreateNewSizes.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlNormalFoodSizes.add(btnNormalCreateNewSizes, "flowx,cell 0 1,grow");
		
		cbNormalSizes = new JComboBox();
		cbNormalSizes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pnlNormalFoodSizes.add(cbNormalSizes, "cell 0 1,grow");
		cbNormalSizes.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		//Combobox Size Handler
		cbNormalSizes.addItemListener(this);
		cbNormalSizes.setRenderer(dlcr); 
		
		lblNameSize = new JLabel("");
		lblNameSize.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlNormalFoodSizes.add(lblNameSize, "flowx,cell 0 0");
		
		lblNormalFoodNameSize = new JLabel("");
		lblNormalFoodNameSize.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlNormalFoodSizes.add(lblNormalFoodNameSize, "cell 0 0");
		
		lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlNormalFoodSizes.add(lblDescription, "cell 0 2,alignx center");
		
		tfNormalDescSize = new JTextField();
		tfNormalDescSize.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlNormalFoodSizes.add(tfNormalDescSize, "cell 0 3,growx");
		tfNormalDescSize.setColumns(10);
		
		lblNormalFoodPrice = new JLabel("Food Price*:");
		pnlNormalFoodSizes.add(lblNormalFoodPrice, "cell 0 4,alignx center");
		lblNormalFoodPrice.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		tfNormalFoodPrice = new JTextField();
		tfNormalFoodPrice.setHorizontalAlignment(SwingConstants.CENTER);
		tfNormalFoodPrice.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(tfNormalFoodPrice.getText().equalsIgnoreCase("0.00")){
					tfNormalFoodPrice.setText("");
				}
			}@Override
			public void focusLost(FocusEvent arg0) {
				if(tfNormalFoodPrice.getText().equalsIgnoreCase("")){
					tfNormalFoodPrice.setText("0.00");					
				}
			}
		});
		pnlNormalFoodSizes.add(tfNormalFoodPrice, "cell 0 5,growx");
		tfNormalFoodPrice.setText("0.00");
		tfNormalFoodPrice.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfNormalFoodPrice.setColumns(10);
		tfNormalFoodPrice.setBackground(getTextFieldColor());
		
		
		pnlNormalFoodDetails = new JPanel();
		pnlNormalFoodDetails.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlNormalFoodDetails.setBackground(SystemColor.menu);
		pnlNormalFoodDetails.setLayout(new MigLayout("", "[grow]", "[grow]"));

		pnlNormal.add(pnlNormalFoodDetails, "cell 0 1 2 2,grow");
		JScrollPane spNormalFoods =  new JScrollPane();
		spNormalFoods.getVerticalScrollBar().setUnitIncrement(20);
		pnlNormalFoodDetails.add(spNormalFoods, "cell 0 0,grow");
		spNormalFoods.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tblNormalFoods = new JTable();
		tblNormalFoods.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tblNormalFoods.setRowHeight(30);
		spNormalFoods.setViewportView(tblNormalFoods);
		
		pnlNormalButtonSizes = new JPanel();
		pnlNormalButtonSizes.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		pnlNormalButtonSizes.setBackground(SystemColor.menu);
		pnlNormal.add(pnlNormalButtonSizes, "cell 2 2,grow");
		pnlNormalButtonSizes.setLayout(new MigLayout("", "[grow][grow]", "[][]"));
		
		btnNormalAddSize = new JButton("");
		btnNormalAddSize.setContentAreaFilled(false);
		btnNormalAddSize.setOpaque(false);
		btnNormalAddSize.setIcon(new ImageIcon(Foods.class.getResource("/Images/Icon/plus.PNG")));
		btnNormalAddSize.setEnabled(false);
		btnNormalAddSize.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNormalAddSize.setFont(new Font("Monospaced", Font.PLAIN, 15));
		pnlNormalButtonSizes.add(btnNormalAddSize, "cell 0 0,alignx left");
		
		btnNormalUpdateSize = new JButton("");
		btnNormalUpdateSize.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
		btnNormalUpdateSize.setIcon(new ImageIcon(Foods.class.getResource("/Images/Icon/update.png")));
		btnNormalUpdateSize.setContentAreaFilled(false);
		btnNormalUpdateSize.setOpaque(false);
		btnNormalUpdateSize.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNormalUpdateSize.setFont(new Font("Monospaced", Font.PLAIN, 15));
		btnNormalUpdateSize.setEnabled(false);
		pnlNormalButtonSizes.add(btnNormalUpdateSize, "cell 1 0,alignx right");
		
		btnNormalRemoveSize = new JButton("");
		btnNormalRemoveSize.setIcon(new ImageIcon(Foods.class.getResource("/Images/Icon/remove.png")));
		btnNormalRemoveSize.setContentAreaFilled(false);
		btnNormalRemoveSize.setOpaque(false);
		btnNormalRemoveSize.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNormalRemoveSize.setFont(new Font("Monospaced", Font.PLAIN, 15));
		btnNormalRemoveSize.setEnabled(false);
		pnlNormalButtonSizes.add(btnNormalRemoveSize, "cell 0 1,alignx left");
		
		btnNormalCancelSize = new JButton("");
		btnNormalCancelSize.setContentAreaFilled(false);
		btnNormalCancelSize.setOpaque(false);
		btnNormalCancelSize.setIcon(new ImageIcon(Foods.class.getResource("/Images/Icon/cancel.png")));
		btnNormalCancelSize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnNormalCancelSize.setEnabled(false);
				btnNormalUpdateSize.setEnabled(false);
				btnNormalRemoveSize.setEnabled(false);
				btnNormalAddSize.setEnabled(true);
				ViewNormalSize();
				Dashboard.nft.ViewNormalFoodSizes(lblNormalFoodNameSize.getText());
				Dashboard.nft.ViewNormalNotInSize(lblNormalFoodNameSize.getText(), false, null);
				tfNormalFoodPrice.setText("0.00");
				tfNormalDescSize.setText(null);
			}
		});
		btnNormalCancelSize.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNormalCancelSize.setFont(new Font("Monospaced", Font.PLAIN, 15));
		btnNormalCancelSize.setEnabled(false);
		pnlNormalButtonSizes.add(btnNormalCancelSize, "cell 1 1,alignx right");
		
		
		pnlFoodSizesDetails = new JPanel();
		pnlFoodSizesDetails.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlFoodSizesDetails.setBackground(SystemColor.menu);
		pnlFoodSizesDetails.setLayout(new MigLayout("", "[grow]", "[grow]"));
		pnlNormal.add(pnlFoodSizesDetails, "cell 3 1 1 2,grow"); 
		
		JScrollPane spNormalFoodSizes = new JScrollPane();
		spNormalFoodSizes.getVerticalScrollBar().setUnitIncrement(20);
		pnlFoodSizesDetails.add(spNormalFoodSizes, "cell 0 0,grow");
		spNormalFoodSizes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		tblNormalFoodSizes = new JTable();
		tblNormalFoodSizes.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tblNormalFoodSizes.setRowHeight(30);
		spNormalFoodSizes.setViewportView(tblNormalFoodSizes);
		ListSelectionModel normalSelectionSizeModel = tblNormalFoodSizes.getSelectionModel();
		
		// Handle the listener
		ListSelectionModel normalSelectionModel = tblNormalFoods.getSelectionModel();
		
		ViewNormalSize();
//		ViewEventSize();
		normalSelectionModel.addListSelectionListener( this );	
		normalSelectionSizeModel.addListSelectionListener( this );
//		eventlSelectionModel.addListSelectionListener( this );
//		eventSelectionSizeModel.addListSelectionListener( this );
		DefaultButtonColor();
	}

    public Color getPanelColor(){
		return SystemColor.menu;
	}
	public Color getButtonBackgroundColor(){
        return new Color(27, 12, 95);
    }
	public Color getButtonHoverBackgroundColor(){
		return new Color(194, 253, 249);
	}
	private Color getButtonForegroundColor(){
		return new Color(215, 203, 0);
	}
	public Color getButtonHoverForegroundColor(){
		return Color.BLACK;
	}
	
    public Color getTextFieldColor(){
		return SystemColor.controlHighlight;
    }    
    
	public void normaladdsize(ActionListener add){
		btnNormalAddSize.addActionListener(add);
	}
	
	public void normalremovesize(ActionListener remove){
		btnNormalRemoveSize.addActionListener(remove);
	}
	
	public void normalupdatesize(ActionListener update){
		btnNormalUpdateSize.addActionListener(update);
	}
	//end of normal listeners
	

    //normal listeners
//	public void eventaddsize(ActionListener add){
//		btnEventAddSize.addActionListener(add);
//	}
//	
//	public void eventremovesize(ActionListener remove){
//		btnEventRemoveSize.addActionListener(remove);
//	}
//	
//	public void eventupdatesize(ActionListener update){
//		btnEventUpdateSize.addActionListener(update);
//	}
	//end of normal listeners
	
	public void DefaultButtonColor(){
		btnNormalAddFood.setUI(new MetalButtonUI());
		btnNormalUpdate.setUI(new MetalButtonUI());
		btnNormalCancel.setUI(new MetalButtonUI());
		btnNormalAddSize.setUI(new MetalButtonUI());
		btnNormalUpdateSize.setUI(new MetalButtonUI());
		btnNormalRemoveSize.setUI(new MetalButtonUI());
		btnNormalCancelSize.setUI(new MetalButtonUI());
		btnNormalCreateNewSizes.setUI(new MetalButtonUI());
	}
	
	//Moving Normal Food Values to ModelNormalFood
	public ModelNormalFood getNormalFoodField(){

			mnf = new ModelNormalFood();
			mnf.setId(lblNormalFoodNameSize.getText());
			mnf.setType("Normal");
			mnf.setPhotos(normalPath);
//			mnf.setName(tfNormalFoodName.getText());
//			mnf.setCategory((String) cbNormalCategory.getSelectedItem());
//			mnf.setStatus(btnNormalStatus.getText());
			
			mnf.setSize((String) cbNormalSizes.getSelectedItem());
			mnf.setDesc(tfNormalDescSize.getText());
//			try {
				mnf.setFoodPrice(Double.parseDouble(tfNormalFoodPrice.getText()));
//			} catch (Exception e) {
//				JOptionPane.showMessageDialog(null, "Invalid input.");
//			}
			mnf.setSizeTable(normalsize); 
		
		return mnf;
	}
	//end of Moving Normal Food Values to ModelNormalFood
	
	//Moving Event Food Values to ModelEventFood
//	public ModelEventFood getEventFoodField(){
//		mef = new ModelEventFood();
//		mef.setType("Event");
//		mef.setPhotos(eventPath);
//		mef.setId(lblEventFoodNameSize.getText());
//		mef.setName(tfEventFoodName.getText());
//		mef.setCategory((String) cbEventCategory.getSelectedItem());
//		mef.setStatus(btnEventStatus.getText());
		
//		mef.setSize((String) cbEventSizes.getSelectedItem());
//		mef.setDesc(tfEventDescSize.getText());
//		mef.setFoodPrice(Double.parseDouble(tfEventFoodPrice.getText()));
//		mef.setDiscount(Integer.parseInt(tfEventDiscount.getText()));
//		mef.setPackagePrice(Double.parseDouble(tfEventPackagePrice.getText()));
//		mef.setSizeTable(eventsize); 
//		return mef;
//	}
	//end of Moving Event Food Values to ModelEventFood
		
	//Handler for list selection changes
 	public void valueChanged(ListSelectionEvent event){
		if( event.getSource() == tblNormalFoods.getSelectionModel() && event.getFirstIndex() >= 0 ){
			TableModel model = tblNormalFoods.getModel();
			int row = tblNormalFoods.getSelectedRow();
			if(row!=-1){
				normalFoodId = model.getValueAt(row, 0).toString();
				normalFoodName= model.getValueAt(row, 1).toString();
				normalFoodCategory = model.getValueAt(row, 2).toString();
				normalFoodPhotos = model.getValueAt(row, 3).toString();
				normalFoodType = model.getValueAt(row, 4).toString();
				normalFoodStatus = model.getValueAt(row, 5).toString();
				
				lblNameSize.setText("<html>Selected ID:<br></html>");
				
				lblNormalFoodNameSize.setText(normalFoodId);
				btnNormalUpdate.setEnabled(true);
				btnNormalCancel.setEnabled(true);
				btnNormalAddFood.setEnabled(false);				
				btnNormalAddSize.setEnabled(true);
				Dashboard.nft.ViewNormalFoodSizes(normalFoodId);
				Dashboard.nft.ViewNormalNotInSize(normalFoodId, false, null);
			}
//		}else if( event.getSource() == tblEventFoods.getSelectionModel() && event.getFirstIndex() >= 0 ){
//			TableModel model = tblEventFoods.getModel();
//			int row = tblEventFoods.getSelectedRow();
//			if(row!=-1){
//				eventFoodId = model.getValueAt(row, 0).toString();
//				eventFoodName= model.getValueAt(row, 1).toString();
//				eventFoodCategory = model.getValueAt(row, 2).toString();
//				eventFoodPhotos = model.getValueAt(row, 3).toString();
//				eventFoodStatus = model.getValueAt(row, 6).toString();
//				
//				lblEventFoodNameSize.setText(eventFoodId);
//				btnEventUpdate.setEnabled(true);
//				btnEventCancel.setEnabled(true);
//				btnEventAddFood.setEnabled(false);				
//				btnEventAddSize.setEnabled(true);
//				Dashboard.eft.ViewEventFoodSizes(eventFoodId);
//				Dashboard.eft.ViewEventNotInSize(eventFoodId, false, null);
//			}
		}else if( event.getSource() == tblNormalFoodSizes.getSelectionModel() && event.getFirstIndex() >= 0 ){
			TableModel model = tblNormalFoodSizes.getModel();
			int row = tblNormalFoodSizes.getSelectedRow();
			if(row!=-1){
				normalsize = model.getValueAt(row, 0).toString();
				tfNormalDescSize.setText(model.getValueAt(row, 1).toString());
				tfNormalFoodPrice.setText(model.getValueAt(row, 2).toString());
				btnNormalCancelSize.setEnabled(true);
				btnNormalUpdateSize.setEnabled(true);
				btnNormalRemoveSize.setEnabled(true);
				btnNormalAddSize.setEnabled(false);
					
				Dashboard.nft.ViewNormalNotInSize(lblNormalFoodNameSize.getText(), true, model.getValueAt(row, 0).toString());
				cbNormalSizes.setSelectedItem(model.getValueAt(row, 0).toString());
			}
//		}else if( event.getSource() == tblEventFoodSizes.getSelectionModel() && event.getFirstIndex() >= 0 ){
//			TableModel model = tblEventFoodSizes.getModel();
//			int row = tblEventFoodSizes.getSelectedRow();
//			if(row!=-1){
//				eventsize = model.getValueAt(row, 0).toString();
//				tfEventDescSize.setText(model.getValueAt(row, 1).toString());
//				tfEventFoodPrice.setText(model.getValueAt(row, 2).toString());
//				tfEventDiscount.setText(model.getValueAt(row, 3).toString());
//				tfEventPackagePrice.setText(model.getValueAt(row, 4).toString());
//					btnEventCancelSize.setEnabled(true);
//					btnEventUpdateSize.setEnabled(true);
//					btnEventRemoveSize.setEnabled(true);
//					btnEventAddSize.setEnabled(false);
//					
//				Dashboard.eft.ViewEventNotInSize(lblEventFoodNameSize.getText(), true, model.getValueAt(row, 0).toString());
//				cbEventSizes.setSelectedItem(model.getValueAt(row, 0).toString());
//			}
		}
 	}
 	//end of Handler for list selection changes

 	//Viewing of combobox size method
	public static void ViewNormalSize(){
		ArrayList arraySize = new ArrayList<String>();
		boolean pick;
		pick = false;
		try{
			Connect c = new Connect();
			c.pst = c.con.prepareCall("{call select_cb_sizes()}");
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			while (c.rs.next()) { 
				String size_name = c.rs.getString("Size");
				arraySize.add(size_name);
			}
			arraySize.add("[Edit Sizes]");
			if(arraySize.size() == 1){
				pick = false;
			}else{
				pick = true;
			}
			c.rs.close();
			c.pst.close();
			c.con.close();
			DefaultComboBoxModel<String> mpSizeNormal = new DefaultComboBoxModel(arraySize.toArray());
			cbNormalSizes.setModel(mpSizeNormal);
		}catch(Exception ed){
			ed.printStackTrace();
		}
		if(pick == false){
			pickNormalFalse();
		}else{
			pickNormalTrue();
		}
	}
	//end of Viewing of combobox size method
 		
	//Viewing of combobox size method
//	public static void ViewEventSize(){
//		ArrayList arraySize = new ArrayList<String>();
//		boolean pick;
//		pick = false;
//		try{
//			Connect c = new Connect();
//			c.pst = c.con.prepareCall("{call select_cb_sizes()}");
//			c.pst.execute();
//			c.rs = c.pst.getResultSet();
//			while (c.rs.next()) { 
//				String size_name = c.rs.getString("Size");
//				arraySize.add(size_name);
//			}
//			arraySize.add("[Edit Sizes]");
//			if(arraySize.size() == 1){
//				pick = false;
//			}else{
//				pick = true;
//			}
//			c.rs.close();
//			c.pst.close();
//			c.con.close();
//			DefaultComboBoxModel<String> mpSizeEvent = new DefaultComboBoxModel(arraySize.toArray());
//		}catch(Exception ed){
//			ed.printStackTrace();
//		}
//		if(pick == false){
//			pickEventFalse();
//		}else{
//			pickEventTrue();
//		}
//	}
	//end of Viewing of combobox size method
 		
	//if empty method
	public static void pickNormalFalse(){
		//NORMAL
		pnlNormalFoodSizes.remove(btnNormalCreateNewSizes);
		pnlNormalFoodSizes.remove(cbNormalSizes);

		pnlNormalFoodSizes.add(btnNormalCreateNewSizes, "flowx,cell 0 1,grow");
		pnlNormalFoodSizes.repaint();
		pnlNormalFoodSizes.revalidate();
	}
	//if not method
	public static void pickNormalTrue(){
		//EVENT
		pnlNormalFoodSizes.remove(btnNormalCreateNewSizes);
		pnlNormalFoodSizes.remove(cbNormalSizes);
		
		pnlNormalFoodSizes.add(cbNormalSizes, "cell 0 1,grow");
		pnlNormalFoodSizes.repaint();
		pnlNormalFoodSizes.revalidate();
	}
	//if not method
//	public static void pickEventTrue(){
//		//EVENT
//		pnlEventFoodSizes.remove(btnEventCreateNewSizes);
//		pnlEventFoodSizes.remove(cbEventSizes);
//		
//		pnlEventFoodSizes.add(cbEventSizes, "cell 0 1,grow");
//		pnlEventFoodSizes.repaint();
//		pnlEventFoodSizes.revalidate();
//	}
//	
//	public static void pickEventFalse(){
//		//NORMAL
//		pnlEventFoodSizes.remove(btnEventCreateNewSizes);
//		pnlEventFoodSizes.remove(cbEventSizes);
//
//		pnlEventFoodSizes.add(btnEventCreateNewSizes, "flowx,cell 0 1,grow");
//		pnlEventFoodSizes.repaint();
//		pnlEventFoodSizes.revalidate();
//	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(btnNormalCreateNewSizes == arg0.getSource()){
			boolCheck = false;
			s = new Sizes(lblNormalFoodNameSize.getText(), "Normal");
			ms = new ModelSize();
			st = new SizeTable(s);
			new AddController(s, ms, st);
			new RemoveController(s, ms, st);
			s.setVisible(true);
			
//		}else if(btnEventCreateNewSizes == arg0.getSource()){			
//			boolCheck = false;
//			s = new Sizes(lblNormalFoodNameSize.getText(), "Event");
//			ms = new ModelSize();
//			st = new SizeTable(s);
//			new AddController(s, ms, st);
//			new RemoveController(s, ms, st);
//			s.setVisible(true);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		if(cbNormalSizes == arg0.getSource()){
			if (cbNormalSizes.getItemCount()-1 == cbNormalSizes.getSelectedIndex() && boolCheck == true) {
				boolCheck = false;
				s = new Sizes(lblNormalFoodNameSize.getText(), "Normal");
				ms = new ModelSize();
				st = new SizeTable(s);
				new AddController(s, ms, st);
				new RemoveController(s, ms, st);
				s.setVisible(true);
			}
//		}else if(cbEventSizes == arg0.getSource()){
//			if (cbEventSizes.getItemCount()-1 == cbEventSizes.getSelectedIndex() && boolCheck == true) {
//				boolCheck = false;
//				s = new Sizes(lblEventFoodNameSize.getText(), "Event");
//				ms = new ModelSize();
//				st = new SizeTable(s);
//				new AddController(s, ms, st);
//				new RemoveController(s, ms, st);
//				s.setVisible(true);
//			}
		}
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		arg0.getComponent().setBackground(getButtonHoverBackgroundColor());
		arg0.getComponent().setForeground(getButtonHoverForegroundColor());
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		arg0.getComponent().setBackground(getButtonBackgroundColor());
		arg0.getComponent().setForeground(getButtonForegroundColor());
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		arg0.getComponent().setBackground(getButtonHoverBackgroundColor());
		arg0.getComponent().setForeground(getButtonHoverForegroundColor());
		
	}


	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		arg0.getComponent().setBackground(getButtonBackgroundColor());
		arg0.getComponent().setForeground(getButtonForegroundColor());
		
	}
}
