package Capstone.View;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Capstone.Database.Connect;
import Capstone.Table.ReservationTable;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.SystemColor;

import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import net.miginfocom.swing.MigLayout;

public class Selection extends JPanel{

	private JButton btnMaintenance;
	private JButton btnTransactions;
	private JButton btnQueries;
	private JButton btnReports;
	private JButton btnUtilities;
	private static JLabel lblTime;
	private static JLabel lblDate;
	protected  static Query qr;
	private static JButton btnLogout;
	protected static Reports rp;
	private File file;
	public BackgroundImageApp pnlImage;
	protected static POSDesign r;
	public static ReservationTable ret;
	protected static Utilities u;
	Connect c;
	private JLabel systemName;
	private JPanel logoPanel;
	private String destination2;
	private String eventPath;

	public Selection() {
		initialize();
	}


	private void initialize() {
		setLayout(null);
		
		logoPanel = new JPanel();
		logoPanel.setBorder(null);
		logoPanel.setBounds(10, 11, 144, 114);
		add(logoPanel);
		logoPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		lblDate = new JLabel("Thurs, Aug 25, 2016 ");
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDate.setForeground(new Color(255, 255, 255));
		lblDate.setBounds(592, 11, 127, 22);
		add(lblDate);
		
		lblTime = new JLabel("10:43PM");
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setForeground(new Color(255, 255, 255));
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTime.setBounds(717, 11, 75, 22);
		add(lblTime);
		
		btnMaintenance = new JButton();
		btnMaintenance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main.displayPanel.removeAll();
				Main.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				Dashboard d = new Dashboard();
				d.btnCategories.doClick();
				Main.displayPanel.add(d, "cell 0 0 3 1,grow");
				Main.displayPanel.repaint();
				Main.displayPanel.revalidate();
			}
		});
		btnMaintenance.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ImageIcon img11 = new ImageIcon(this.getClass().getResource("/img/MaintenanceB.png"));
				btnMaintenance.setIcon(img11);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ImageIcon img1 = new ImageIcon(this.getClass().getResource("/img/MaintenanceA.png"));
				btnMaintenance.setIcon(img1);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				ImageIcon img111 = new ImageIcon(this.getClass().getResource("/img/MaintenanceC.png"));
				btnMaintenance.setIcon(img111);
			}
		});
		ImageIcon img1 = new ImageIcon(this.getClass().getResource("/img/MaintenanceA.png"));
		btnMaintenance.setIcon(img1);
		btnMaintenance.setBorderPainted(false);
		btnMaintenance.setFocusPainted(false);
		btnMaintenance.setContentAreaFilled(false);
		btnMaintenance.setBounds(82, 174, 200, 200);
		add(btnMaintenance);
		
		btnTransactions = new JButton();
		btnTransactions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.displayPanel.removeAll();
				Main.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				r = new POSDesign();
				ret = new ReservationTable(r);
//				ret.ViewEventReservation();
//				ret.ViewNormalReservation();
				ret.ViewDinein();
				
				Main.displayPanel.add(r, "cell 0 0 3 1,grow");
				Main.displayPanel.repaint();
				Main.displayPanel.revalidate();
			}
		});
		btnTransactions.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ImageIcon img22 = new ImageIcon(this.getClass().getResource("/img/TransactionsB.png"));
				btnTransactions.setIcon(img22);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ImageIcon img2 = new ImageIcon(this.getClass().getResource("/img/TransactionsA.png"));
				btnTransactions.setIcon(img2);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				ImageIcon img222 = new ImageIcon(this.getClass().getResource("/img/TransactionsC.png"));
				btnTransactions.setIcon(img222);
			}
		});
		ImageIcon img2 = new ImageIcon(this.getClass().getResource("/img/TransactionsA.png"));
		btnTransactions.setIcon(img2);
		btnTransactions.setBorderPainted(false);
		btnTransactions.setFocusPainted(false);
		btnTransactions.setContentAreaFilled(false);
		btnTransactions.setBounds(326, 174, 200, 200);
		add(btnTransactions);
		
		btnUtilities = new JButton();
		btnUtilities.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				Main.displayPanel.removeAll();
				Main.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				u = new Utilities();
				Main.displayPanel.add(u, "cell 0 0 3 1,grow");
				Main.displayPanel.repaint();
				Main.displayPanel.revalidate();
			}
		});
		btnUtilities.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ImageIcon img33 = new ImageIcon(this.getClass().getResource("/img/UtilitiesB.png"));
				btnUtilities.setIcon(img33);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ImageIcon img3 = new ImageIcon(this.getClass().getResource("/img/UtilitiesA.png"));
				btnUtilities.setIcon(img3);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				ImageIcon img333 = new ImageIcon(this.getClass().getResource("/img/UtilitiesC.png"));
				btnUtilities.setIcon(img333);
			}
		});
		ImageIcon img3 = new ImageIcon(this.getClass().getResource("/img/UtilitiesA.png"));
		btnUtilities.setIcon(img3);
		btnUtilities.setBorderPainted(false);
		btnUtilities.setFocusPainted(false);
		btnUtilities.setContentAreaFilled(false);
		btnUtilities.setBounds(575, 174, 200, 200);
		add(btnUtilities);
		
		btnQueries = new JButton();
		btnQueries.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.displayPanel.removeAll();
				Main.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				qr = new Query();
				Main.displayPanel.add(qr, "cell 0 0 3 1,grow");
				Main.displayPanel.repaint();
				Main.displayPanel.revalidate();
			}
		});
		btnQueries.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ImageIcon img44 = new ImageIcon(this.getClass().getResource("/img/QueriesB.png"));
				btnQueries.setIcon(img44);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ImageIcon img4 = new ImageIcon(this.getClass().getResource("/img/QueriesA.png"));
				btnQueries.setIcon(img4);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				ImageIcon img444 = new ImageIcon(this.getClass().getResource("/img/QueriesC.png"));
				btnQueries.setIcon(img444);
			}
		});
		ImageIcon img4 = new ImageIcon(this.getClass().getResource("/img/QueriesA.png"));
		btnQueries.setIcon(img4);
		btnQueries.setBorderPainted(false);
		btnQueries.setFocusPainted(false);
		btnQueries.setContentAreaFilled(false);
		btnQueries.setBounds(82, 374, 200, 200);
		add(btnQueries);
		
		btnReports = new JButton();
		btnReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.displayPanel.removeAll();
				Main.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				rp= new Reports();
				Main.displayPanel.add(rp, "cell 0 0 3 1,grow");
				Main.displayPanel.repaint();
				Main.displayPanel.revalidate();
			}
		});
		btnReports.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ImageIcon img55 = new ImageIcon(this.getClass().getResource("/img/ReportsB.png"));
				btnReports.setIcon(img55);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ImageIcon img5 = new ImageIcon(this.getClass().getResource("/img/ReportsA.png"));
				btnReports.setIcon(img5);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				ImageIcon img555 = new ImageIcon(this.getClass().getResource("/img/ReportsC.png"));
				btnReports.setIcon(img555);
			}
		});
		ImageIcon img5 = new ImageIcon(this.getClass().getResource("/img/ReportsA.png"));
		btnReports.setIcon(img5);
		btnReports.setBorderPainted(false);
		btnReports.setFocusPainted(false);
		btnReports.setContentAreaFilled(false);
		btnReports.setBounds(326, 374, 200, 200);
		add(btnReports);
		
		btnLogout = new JButton();
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login log = new Login();
				log.setVisible(true);
				log.setLocationRelativeTo(null);
			}
		});
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ImageIcon img66 = new ImageIcon(this.getClass().getResource("/img/logout2.png"));
				btnLogout.setIcon(img66);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ImageIcon img6 = new ImageIcon(this.getClass().getResource("/img/logout1.png"));
				btnLogout.setIcon(img6);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				ImageIcon img666 = new ImageIcon(this.getClass().getResource("/img/logout3.png"));
				btnLogout.setIcon(img666);
			}
		});
		ImageIcon img6 = new ImageIcon(this.getClass().getResource("/img/logout1.png"));
		btnLogout.setIcon(img6);
		btnLogout.setBorderPainted(false);
		btnLogout.setFocusPainted(false);
		btnLogout.setContentAreaFilled(false);
		btnLogout.setBounds(700, 486, 100, 100);
		add(btnLogout);
		
		JLabel lblBanner = new JLabel("");
		ImageIcon img7 = new ImageIcon(this.getClass().getResource("/img/Time.png"));
		
		systemName = new JLabel("Restaurant Reservation System");
		systemName.setForeground(new Color(255, 255, 255));
		systemName.setFont(new Font("Tahoma", Font.PLAIN, 35));
		systemName.setHorizontalAlignment(SwingConstants.CENTER);
		systemName.setBounds(164, 44, 531, 58);
		add(systemName);
		lblBanner.setIcon(img7);
		lblBanner.setBounds(-44, -25, 964, 163);
		add(lblBanner);
		RunningTime();
		
		ViewSystemInfo();
		
	}

	public static void RunningTime(){
    		Thread clock = new Thread(){
    			public void run(){
    				try {
    					for(;;){
    				        lblTime.setText(new SimpleDateFormat("hh:mm:ss a").format(new Date()));    			
    				        lblDate.setText(new SimpleDateFormat("MMMM dd, yyyy").format(new Date()));    						
    						sleep(1000);
    					}
    				} catch (InterruptedException e) {
    					// TODO: handle exception
    					e.printStackTrace();
    				}
    			}
    		};
    		clock.start();
	}
	
	
private class BackgroundImageApp extends JPanel {
		
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
			pnlImage.setBorder(null);
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
	
	
}



