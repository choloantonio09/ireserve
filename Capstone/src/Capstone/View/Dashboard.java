package Capstone.View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.plaf.metal.MetalButtonUI;

import Capstone.Controller.AddController;
import Capstone.Controller.RemoveController;
import Capstone.Controller.UpdateController;
import Capstone.Model.ModelCategory;
import Capstone.Model.ModelEventFood;
import Capstone.Model.ModelEventPackage;
import Capstone.Model.ModelNormalFood;
import Capstone.Model.ModelNormalPackage;
import Capstone.Model.ModelTable;
import Capstone.Table.CategoryTable;
//import Capstone.Table.EventFoodTable;
import Capstone.Table.EventPackageTable;
import Capstone.Table.NormalFoodTable;
import Capstone.Table.NormalPackageTable;
import Capstone.Table.RoomTable;
import net.miginfocom.swing.MigLayout;

public class Dashboard extends JPanel implements MouseListener, FocusListener{

	static JButton btnCategories;
	static JButton btnFoods;
	static JButton btnPackages;
	private JPanel buttonPanel;
//	public static JButton btnTables;
	private JPanel pnlSplit;
	

	Categories c;
	ModelCategory mc;
	public static CategoryTable ct;
	

	public static Foods f;
	static ModelNormalFood mnf;
//	static ModelEventFood mef;
	public static NormalFoodTable nft;
//	public static EventFoodTable eft;

	Packages p;
	ModelNormalPackage mnp;
//	ModelEventPackage mep;
	static NormalPackageTable npt;
//	static EventPackageTable ept;
	
	public static Tables t;
	ManageTIme mt;
	

	Utilities u;
	private JButton btnGoBack;
	public static JButton btnFloor;
	public static FloorPlan fp;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//				Dashboard frame = new Dashboard();
//				frame.setVisible(true);
//	}
	public static RoomTable rpt;
	private JScrollPane scrollerRight;
	private JButton btnTime;

	/**
	 * Create the frame.
	 */
	public Dashboard() {
		System.setProperty("Quaqua.tabLayoutPolicy","wrap");
        // set the Quaqua Look and Feel in the UIManager
		try {
//			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			UIManager.setLookAndFeel(ch.randelshofer.quaqua.QuaquaManager.getLookAndFeel());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setBounds(100, 100, 871, 667);
		setLayout(new MigLayout("", "[1080px,grow]", "[][grow]"));
		
		buttonPanel = new JPanel();
		add(buttonPanel, "cell 0 0");
		
		btnCategories = new JButton("Category");
		btnCategories.setOpaque(false);
		btnCategories.setContentAreaFilled(false);
		btnCategories.setIcon(new ImageIcon(Dashboard.class.getResource("/Images/Icon/category.png")));
		btnCategories.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCategories.setFont(new Font("Monospaced", Font.PLAIN, 15));
		btnCategories.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnCategories.setIcon(new ImageIcon(Dashboard.class.getResource("/Images/Icon/category2.png")));
				btnFoods.setIcon(new ImageIcon(Dashboard.class.getResource("/Images/Icon/food.png")));
				btnPackages.setIcon(new ImageIcon(Dashboard.class.getResource("/Images/Icon/package.png")));
				btnFloor.setIcon(new ImageIcon(Dashboard.class.getResource("/Images/Icon/floor.png")));
				btnTime.setIcon(new ImageIcon(Dashboard.class.getResource("/Images/Icon/table.png")));
				
				//removing all panels
				pnlSplit.removeAll();

				// instantiate category pnlSplit
				c = new Categories();
				ct = new CategoryTable(c);
				ct.ViewCategory();
//				new UpdateController(c, ct);
				
				// adding category pnlSplit
				c.setBorder(null);
				pnlSplit.add(c, "cell 0 0,grow");
				repaint();
				revalidate();
			}
		});
		
		btnGoBack = new JButton("Home");
		btnGoBack.setIcon(new ImageIcon(Dashboard.class.getResource("/Images/Icon/home.png")));
		btnGoBack.setContentAreaFilled(false);
		btnGoBack.setOpaque(false);
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.displayPanel.removeAll();
				Selection s = new Selection();
				Main.displayPanel.add(s, "cell 0 0 3 1,grow");
				Main.displayPanel.repaint();
				Main.displayPanel.revalidate();
				Main.frame.setSize(850,650);
				Main.frame.setLocationRelativeTo(null);
			}
		});
		buttonPanel.setLayout(new MigLayout("", "[102px][138px][102px][129px][156px][]", "[38px]"));
		btnGoBack.setFont(new Font("Monospaced", Font.PLAIN, 15));
		btnGoBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonPanel.add(btnGoBack, "cell 0 0,grow");
		buttonPanel.add(btnCategories, "cell 1 0,grow");
		
		btnFoods = new JButton("Food");
		btnFoods.setOpaque(false);
		btnFoods.setContentAreaFilled(false);
		btnFoods.setIcon(new ImageIcon(Dashboard.class.getResource("/Images/Icon/food.png")));
		btnFoods.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFoods.setFont(new Font("Monospaced", Font.PLAIN, 15));
		btnFoods.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				btnCategories.setIcon(new ImageIcon(Dashboard.class.getResource("/Images/Icon/category.png")));
				btnFoods.setIcon(new ImageIcon(Dashboard.class.getResource("/Images/Icon/food2.png")));
				btnPackages.setIcon(new ImageIcon(Dashboard.class.getResource("/Images/Icon/package.png")));
				btnFloor.setIcon(new ImageIcon(Dashboard.class.getResource("/Images/Icon/floor.png")));
				btnTime.setIcon(new ImageIcon(Dashboard.class.getResource("/Images/Icon/table.png")));

				//removing all panels
				pnlSplit.removeAll();
				
				// instantiate food pnlSplit
				f = new Foods();
				nft = new NormalFoodTable(f);
				mnf = new ModelNormalFood();
//				eft = new EventFoodTable(f);
//				mef = new ModelEventFood();
				nft.ViewNormalFood();
//				eft.ViewEventFood();
				new AddController(f, mnf, nft);
//				new AddController(f, mef, eft);
				new UpdateController(f, mnf, nft);
				
				// adding food pnlSplit				
				f.setBorder(null);		
				pnlSplit.add(f, "cell 0 0,grow");
				repaint();
				revalidate();
			}
		});
		buttonPanel.add(btnFoods, "cell 2 0,grow");
		
		btnPackages = new JButton("Package");
		btnPackages.setIcon(new ImageIcon(Dashboard.class.getResource("/Images/Icon/package.png")));
		btnPackages.setOpaque(false);
		btnPackages.setContentAreaFilled(false);
		btnPackages.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPackages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnCategories.setIcon(new ImageIcon(Dashboard.class.getResource("/Images/Icon/category.png")));
				btnFoods.setIcon(new ImageIcon(Dashboard.class.getResource("/Images/Icon/food.png")));
				btnPackages.setIcon(new ImageIcon(Dashboard.class.getResource("/Images/Icon/package2.png")));
				btnFloor.setIcon(new ImageIcon(Dashboard.class.getResource("/Images/Icon/floor.png")));
				btnTime.setIcon(new ImageIcon(Dashboard.class.getResource("/Images/Icon/table.png")));
				
				//removing all panels
				pnlSplit.removeAll();
				
				// instantiate food pnlSplit
				p = new Packages();
				mnp = new ModelNormalPackage();
//				mep = new ModelEventPackage();
				npt = new NormalPackageTable(p);
//				ept = new EventPackageTable(p);
				new AddController(p, mnp, npt);
				new RemoveController(p, mnp, npt);
//				new UpdateController(t, mt, dt);
				
				// adding food pnlSplit				
				p.setBorder(null);		
				pnlSplit.add(p, "cell 0 0,grow");
				repaint();
				revalidate();
			}
		});
		btnPackages.setFont(new Font("Monospaced", Font.PLAIN, 15));
		buttonPanel.add(btnPackages, "cell 3 0,grow");
		
//		btnTables = new JButton("TABLES");
//		btnTables.setIcon(new ImageIcon(Dashboard.class.getResource("/Images/Icon/table.png")));
//		btnTables.setOpaque(false);
//		btnTables.setContentAreaFilled(false);
//		btnTables.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		btnTables.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				btnCategories.setIcon(new ImageIcon(Dashboard.class.getResource("/Images/Icon/category.png")));
//				btnFoods.setIcon(new ImageIcon(Dashboard.class.getResource("/Images/Icon/food.png")));
//				btnPackages.setIcon(new ImageIcon(Dashboard.class.getResource("/Images/Icon/package.png")));
//				btnFloor.setIcon(new ImageIcon(Dashboard.class.getResource("/Images/Icon/floor.png")));
//				btnTables.setIcon(new ImageIcon(Dashboard.class.getResource("/Images/Icon/table2.png"))); 
//				
//				//removing all panels
//				pnlSplit.removeAll();
//				
//				// instantiate food pnlSplit
//				t = new Tables();
//				mt = new ModelTable();
//				dt = new DeskTable(t);
////				dt.ViewRoomPlanDesign();
//				dt.ViewRooms();
////				new UpdateController(t, mt, dt);
//				
//				// adding food pnlSplit				
//				t.setBorder(null);		
//				pnlSplit.add(t, "cell 0 0,grow");
//				repaint();
//				revalidate();
//			}
//		});
		
		btnFloor = new JButton("Floor plan");
		btnFloor.setUI(new MetalButtonUI());
		btnFloor.setIcon(new ImageIcon(Dashboard.class.getResource("/Images/Icon/floor.png")));
		btnFloor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnCategories.setIcon(new ImageIcon(Dashboard.class.getResource("/Images/Icon/category.png")));
				btnFoods.setIcon(new ImageIcon(Dashboard.class.getResource("/Images/Icon/food.png")));
				btnPackages.setIcon(new ImageIcon(Dashboard.class.getResource("/Images/Icon/package.png")));
				btnFloor.setIcon(new ImageIcon(Dashboard.class.getResource("/Images/Icon/floor2.png")));
				btnTime.setIcon(new ImageIcon(Dashboard.class.getResource("/Images/Icon/table.png")));
				
				//removing all panels
				pnlSplit.removeAll();
				
				// instantiate food pnlSplit
				fp = new FloorPlan();
				// adding food pnlSplit				
				fp.setBorder(null);		
				pnlSplit.add(fp, "cell 0 0,grow");
				repaint();
				revalidate();
			}
		});
		btnFloor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFloor.setOpaque(false);
		btnFloor.setFont(new Font("Monospaced", Font.PLAIN, 15));
		btnFloor.setContentAreaFilled(false);
		buttonPanel.add(btnFloor, "cell 4 0,alignx left,aligny top");
		
		btnTime = new JButton("Time");
		btnTime.setUI(new MetalButtonUI());
		btnTime.setIcon(new ImageIcon(Dashboard.class.getResource("/Images/Icon/table.png")));
		btnTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				btnCategories.setIcon(new ImageIcon(Dashboard.class.getResource("/Images/Icon/category.png")));
				btnFoods.setIcon(new ImageIcon(Dashboard.class.getResource("/Images/Icon/food.png")));
				btnPackages.setIcon(new ImageIcon(Dashboard.class.getResource("/Images/Icon/package.png")));
				btnFloor.setIcon(new ImageIcon(Dashboard.class.getResource("/Images/Icon/floor.png")));
				btnTime.setIcon(new ImageIcon(Dashboard.class.getResource("/Images/Icon/table2.png")));
				
				//removing all panels
				pnlSplit.removeAll();
				
				// instantiate food pnlSplit
				mt = new ManageTIme();
				// adding food pnlSplit				
				mt.setBorder(null);		
				pnlSplit.add(mt, "cell 0 0,grow");
				repaint();
				revalidate();
			}
		});
		btnTime.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTime.setOpaque(false);
		btnTime.setFont(new Font("Monospaced", Font.PLAIN, 15));
		btnTime.setContentAreaFilled(false);
		buttonPanel.add(btnTime, "cell 5 0");
		//		btnTables.setFont(new Font("Monospaced", Font.PLAIN, 15));
		//		buttonPanel.add(btnTables, "cell 5 0,grow");
				
				pnlSplit = new JPanel();
				add(pnlSplit, "cell 0 1,grow");
				pnlSplit.setLayout(new MigLayout("", "[grow]", "[grow]"));

//		SplitPaneUI();
		
		// Handle the listener
		DefaultButtonColor();
		DefaultPanelColor();
	}
	
	//COLORS
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
	//end of COLORS
	
	public void DefaultPanelColor(){
		setBackground(getPanelColor());
		setBackground(getPanelColor());
		buttonPanel.setBackground(getPanelColor());
		pnlSplit.setBackground(getPanelColor());		
		buttonPanel.setBorder(new LineBorder(getPanelColor(), 1, true));
	}	
	public void DefaultButtonColor(){
		btnCategories.setUI(new MetalButtonUI());
		btnFoods.setUI(new MetalButtonUI());
		btnPackages.setUI(new MetalButtonUI());
//		btnTables.setUI(new MetalButtonUI());
		btnGoBack.setUI(new MetalButtonUI());
		
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
	
}
