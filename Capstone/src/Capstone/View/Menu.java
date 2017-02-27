package Capstone.View;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Capstone.Table.ReservationTable;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu {

	private JFrame frame;
	private JButton btnMaintenance;
	private JButton btnTransactions;
	private JButton btnQueries;
	private JButton btnReports;
	private JButton btnUtilities;
	private JLabel lblTime;
	private JLabel lblDate;
	protected Query qr;
	private JButton btnLogout;
	protected Reports rp;
	protected POSDesign r;
	protected ReservationTable ret;

	public Menu() {
		initialize();
	}


	private void initialize() {
		frame = new JFrame("Menu");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 450, 300);
		frame.setSize(850,650);
		frame.getContentPane().setLayout(null);
		
		lblDate = new JLabel("Thurs, Aug 25, 2016 ");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDate.setForeground(new Color(255, 255, 255));
		lblDate.setBounds(372, 77, 170, 22);
		frame.getContentPane().add(lblDate);
		
		lblTime = new JLabel("10:43PM");
		lblTime.setForeground(new Color(255, 255, 255));
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTime.setBounds(362, 29, 200, 50);
		frame.getContentPane().add(lblTime);
		
		btnMaintenance = new JButton();
		btnMaintenance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main.displayPanel.removeAll();
				Dashboard d = new Dashboard();
				d.btnCategories.doClick();
				Main.displayPanel.add(d, "cell 0 1 3 1,grow");
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
		frame.getContentPane().add(btnMaintenance);
		
		btnTransactions = new JButton();
		btnTransactions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.displayPanel.removeAll();
				r = new POSDesign();
				ret = new ReservationTable(r);
//				ret.ViewEventReservation();
//				ret.ViewNormalReservation();
				ret.ViewDinein();
				
				Main.displayPanel.add(r, "cell 0 1 3 1,grow");
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
		frame.getContentPane().add(btnTransactions);
		
		btnUtilities = new JButton();
		btnUtilities.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		frame.getContentPane().add(btnUtilities);
		
		btnQueries = new JButton();
		btnQueries.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.displayPanel.removeAll();
				qr = new Query();
				Main.displayPanel.add(qr, "cell 0 1 3 1,grow");
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
		frame.getContentPane().add(btnQueries);
		
		btnReports = new JButton();
		btnReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.displayPanel.removeAll();
				rp= new Reports();
				Main.displayPanel.add(rp, "cell 0 1 3 1,grow");
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
		frame.getContentPane().add(btnReports);
		
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
		frame.getContentPane().add(btnLogout);
		
		JLabel lblBanner = new JLabel("");
		ImageIcon img7 = new ImageIcon(this.getClass().getResource("/img/Time.png"));
		lblBanner.setIcon(img7);
		lblBanner.setBounds(-44, -25, 964, 163);
		frame.getContentPane().add(lblBanner);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Menu();

	}
}



