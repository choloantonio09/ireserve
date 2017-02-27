package Capstone.View;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import net.miginfocom.swing.MigLayout;

import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.awt.Font;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import javax.swing.plaf.metal.MetalButtonUI;

import Capstone.Database.Connect;
import Capstone.Table.ReservationTable;
import Capstone.View.Query.BackgroundImageApp;
import Capstone.View.Query.BackgroundImageApp.ResizerListener;
import Images.GetImageResource;
import Images.GetImageResourceLogo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.SystemColor;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Utilities extends JPanel {
	
	Connect c;
	JFileChooser jfBrowse;
	
	static ReservationTable rt;
	private JButton btnGoBack;
	private JPanel panel;
	private JLabel lblUtilities;
	private JPanel panel_1;
	private JTextField systemName;
	private JTextField contactNumber;
	private JTextField restaurantAddress;
	public BackgroundImageApp pnlImage;
	private String destination2;
	private JPanel logoPanel;
	private String normalPath;
	private String eventPath;
	private JButton btnUpload;
	File file = new File("src/img/logo.png");
	
	
	
	
	public Utilities() {
		normalPath = file.getAbsolutePath();
		
		System.setProperty("Quaqua.tabLayoutPolicy","wrap");
        // set the Quaqua Look and Feel in the UIManager
		try {
//			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			UIManager.setLookAndFeel(ch.randelshofer.quaqua.QuaquaManager.getLookAndFeel());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setBounds(100, 100, 1033, 671);
		setLayout(new MigLayout("", "[38.00][1080px,grow]", "[855px,grow]"));
		
		btnGoBack = new JButton("");
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
		btnGoBack.setUI(new MetalButtonUI());
		btnGoBack.setIcon(new ImageIcon(Reports.class.getResource("/Images/Icon/home.PNG")));
		btnGoBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGoBack.setContentAreaFilled(false);
		btnGoBack.setOpaque(false);
		add(btnGoBack, "cell 0 0,growx,aligny top");
		
		panel = new JPanel();
		add(panel, "cell 1 0,grow");
		panel.setLayout(new MigLayout("", "[661.00px][63px][205.00px,grow]", "[61px][45.00][35px][11px][45.00px][45.00px][35px][11px][45.00px][45.00][44px][11px][45.00px][67.00][34.00px,grow]"));
		
		panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.textHighlight);
		panel.add(panel_1, "cell 0 0 3 1,grow");
		panel_1.setLayout(new MigLayout("", "[912px,grow]", "[40px,grow]"));
		
		lblUtilities = new JLabel("SYSTEM UTILITIES");
		panel_1.add(lblUtilities, "cell 0 0,grow");
		lblUtilities.setForeground(new Color(0, 0, 51));
		lblUtilities.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		
		JLabel lblChangeSystemLogo = new JLabel("CHANGE RESTAURANT'S NAME:");
		lblChangeSystemLogo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		panel.add(lblChangeSystemLogo, "cell 0 2,grow");
		
		systemName = new JTextField();
		systemName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(systemName, "cell 0 4,grow");
		systemName.setColumns(10);
		
		contactNumber = new JTextField();
		contactNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contactNumber.setColumns(10);
		panel.add(contactNumber, "cell 0 8,grow");
		
		JLabel lblChangeRestaurantsContact = new JLabel("CHANGE RESTAURANT'S CONTACT NUMBER:");
		lblChangeRestaurantsContact.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		panel.add(lblChangeRestaurantsContact, "cell 0 6,grow");
		
		JLabel lblChangeRestaurantsMain = new JLabel("CHANGE RESTAURANT'S ADDRESS:");
		lblChangeRestaurantsMain.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		panel.add(lblChangeRestaurantsMain, "cell 0 10,grow");
		
		restaurantAddress = new JTextField();
		restaurantAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		restaurantAddress.setColumns(10);
		panel.add(restaurantAddress, "cell 0 12,grow");
		
		logoPanel = new JPanel();
		logoPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(logoPanel, "cell 2 2 1 9,grow");
		logoPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		btnUpload = new JButton("UPLOAD IMAGE");
		btnUpload.setUI(new MetalButtonUI());
		btnUpload.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpload.setContentAreaFilled(false);
		btnUpload.setOpaque(false);
		btnUpload.setIcon(new ImageIcon(NormalFoodForm.class.getResource("/Images/Icon/upload_image.png")));
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				jfBrowse = new JFileChooser();
				jfBrowse.setAccessory(new ImagePreview(jfBrowse));
				jfBrowse.addChoosableFileFilter(new FileNameExtensionFilter("JPEG Files", "jpg"));
				jfBrowse.addChoosableFileFilter(new FileNameExtensionFilter("PNG Files","png"));
				jfBrowse.addChoosableFileFilter(new FileNameExtensionFilter("GIF Files","gif"));
				jfBrowse.addChoosableFileFilter(new FileNameExtensionFilter("BMP Files","bmp"));
				jfBrowse.addChoosableFileFilter(new FileNameExtensionFilter("All Images", "jpg", "png", "gif", "bmp"));
				int returnBrowse = jfBrowse.showOpenDialog(null);
				if(returnBrowse == JFileChooser.APPROVE_OPTION){
					
					normalPath = jfBrowse.getSelectedFile().getPath();
					
					File file = new File(normalPath);
					logoPanel.removeAll();
					logoPanel.updateUI();
					pnlImage = new BackgroundImageApp(file);
					logoPanel.add(pnlImage, "cell 0 0,grow");
					logoPanel.revalidate();
					logoPanel.repaint();
					
				}else{
					JOptionPane.showMessageDialog(null, "No image selected");
				}
			}
		});
		
		panel.add(btnUpload, "cell 2 11 1 3,grow");
		
		JButton saveButton = new JButton("SAVE CHANGES");
		saveButton.setBackground(SystemColor.textHighlight);
		saveButton.setForeground(new Color(0, 0, 51));
		saveButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					GetImageResourceLogo gir = new GetImageResourceLogo();
					String destination = gir.GetImage(normalPath, "1" + ".jpg", "add");
					UpdateUtility(destination);
					
					//System.out.println(destination);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panel.add(saveButton, "cell 2 14,grow");
		
		ViewSystemInfo();
		

	}
	
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


	public void ViewSystemInfo(){
		//SET ICON
		
		
		try {
			c = new Connect();
			String query = "SELECT * FROM utilities WHERE utility_id = '1'";
			//System.out.println(roomId);
			c.pst = c.con.prepareStatement(query);
			c.pst.execute();
			c.rs = c.pst.getResultSet();
			if(c.rs.next())
			{
				systemName.setText(c.rs.getString("restaurant_name"));
				restaurantAddress.setText(c.rs.getString("restaurant_address"));
				contactNumber.setText(c.rs.getString("restaurant_contact_no"));
				destination2 = c.rs.getString("system_logo");
			}
			logoPanel.removeAll();
			file = new File(destination2);
			eventPath = file.getAbsoluteFile().getAbsolutePath();
			pnlImage = new BackgroundImageApp(file);
			pnlImage.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			pnlImage.setBorder(new LineBorder(Color.DARK_GRAY));
			pnlImage.setBackground(SystemColor.menu);
			pnlImage.setLayout(new BorderLayout(0, 0));
			logoPanel.add(pnlImage, "cell 0 0, grow");
			logoPanel.repaint();
			
			c.pst.close();
			c.rs.close();
			c.con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void UpdateUtility(String logo)
	{
		try {
			
			c = new Connect();
			String query = "UPDATE utilities SET system_logo = ?, restaurant_name = ?, restaurant_address = ?, restaurant_contact_no = ? WHERE utility_id = '1';";
			c.pst = c.con.prepareStatement(query);
			c.pst.setString(1, logo);
			c.pst.setString(2, systemName.getText());
			c.pst.setString(3, restaurantAddress.getText());
			c.pst.setString(4, contactNumber.getText());
			c.pst.execute();
			
			JOptionPane.showMessageDialog(null, "Successfully Update System Utilities!");
			
			c.pst.close();
			c.con.close();
		} catch (SQLException  e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	

	
}
