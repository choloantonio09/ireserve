import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class Menu {

	private JFrame frame;
	private JButton btnMaintenance;
	private JButton btnTransactions;
	private JButton btnQueries;
	private JButton btnReports;
	private JButton btnUtilities;

	public Menu() {
		initialize();
	}


	private void initialize() {
		frame = new JFrame("Menu");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 450, 300);
		frame.setSize(850,650);
		frame.getContentPane().setLayout(null);
		
		JLabel lblDate = new JLabel("Thurs, Aug 25, 2016 ");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDate.setForeground(new Color(255, 255, 255));
		lblDate.setBounds(372, 77, 170, 22);
		frame.getContentPane().add(lblDate);
		
		JLabel lblTime = new JLabel("10:43PM");
		lblTime.setForeground(new Color(255, 255, 255));
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTime.setBounds(362, 29, 200, 50);
		frame.getContentPane().add(lblTime);
		
		btnMaintenance = new JButton();
		btnMaintenance.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Image img11 = new ImageIcon(this.getClass().getResource("/MaintenanceB.png")).getImage();
				btnMaintenance.setIcon(new ImageIcon(img11));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Image img1 = new ImageIcon(this.getClass().getResource("/MaintenanceA.png")).getImage();
				btnMaintenance.setIcon(new ImageIcon(img1));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Image img111 = new ImageIcon(this.getClass().getResource("/MaintenanceC.png")).getImage();
				btnMaintenance.setIcon(new ImageIcon(img111));
			}
		});
		Image img1 = new ImageIcon(this.getClass().getResource("/MaintenanceA.png")).getImage();
		btnMaintenance.setIcon(new ImageIcon(img1));
		btnMaintenance.setBorderPainted(false);
		btnMaintenance.setFocusPainted(false);
		btnMaintenance.setContentAreaFilled(false);
		btnMaintenance.setBounds(82, 174, 200, 200);
		frame.getContentPane().add(btnMaintenance);
		
		btnTransactions = new JButton();
		btnTransactions.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Image img22 = new ImageIcon(this.getClass().getResource("/TransactionsB.png")).getImage();
				btnTransactions.setIcon(new ImageIcon(img22));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Image img2 = new ImageIcon(this.getClass().getResource("/TransactionsA.png")).getImage();
				btnTransactions.setIcon(new ImageIcon(img2));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Image img222 = new ImageIcon(this.getClass().getResource("/TransactionsC.png")).getImage();
				btnTransactions.setIcon(new ImageIcon(img222));
			}
		});
		Image img2 = new ImageIcon(this.getClass().getResource("/TransactionsA.png")).getImage();
		btnTransactions.setIcon(new ImageIcon(img2));
		btnTransactions.setBorderPainted(false);
		btnTransactions.setFocusPainted(false);
		btnTransactions.setContentAreaFilled(false);
		btnTransactions.setBounds(326, 174, 200, 200);
		frame.getContentPane().add(btnTransactions);
		
		btnUtilities = new JButton();
		btnUtilities.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Image img33 = new ImageIcon(this.getClass().getResource("/UtilitiesB.png")).getImage();
				btnUtilities.setIcon(new ImageIcon(img33));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Image img3 = new ImageIcon(this.getClass().getResource("/UtilitiesA.png")).getImage();
				btnUtilities.setIcon(new ImageIcon(img3));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Image img333 = new ImageIcon(this.getClass().getResource("/UtilitiesC.png")).getImage();
				btnUtilities.setIcon(new ImageIcon(img333));
			}
		});
		Image img3 = new ImageIcon(this.getClass().getResource("/UtilitiesA.png")).getImage();
		btnUtilities.setIcon(new ImageIcon(img3));
		btnUtilities.setBorderPainted(false);
		btnUtilities.setFocusPainted(false);
		btnUtilities.setContentAreaFilled(false);
		btnUtilities.setBounds(575, 174, 200, 200);
		frame.getContentPane().add(btnUtilities);
		
		btnQueries = new JButton();
		btnQueries.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Image img44 = new ImageIcon(this.getClass().getResource("/QueriesB.png")).getImage();
				btnQueries.setIcon(new ImageIcon(img44));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Image img4 = new ImageIcon(this.getClass().getResource("/QueriesA.png")).getImage();
				btnQueries.setIcon(new ImageIcon(img4));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Image img444 = new ImageIcon(this.getClass().getResource("/QueriesC.png")).getImage();
				btnQueries.setIcon(new ImageIcon(img444));
			}
		});
		Image img4 = new ImageIcon(this.getClass().getResource("/QueriesA.png")).getImage();
		btnQueries.setIcon(new ImageIcon(img4));
		btnQueries.setBorderPainted(false);
		btnQueries.setFocusPainted(false);
		btnQueries.setContentAreaFilled(false);
		btnQueries.setBounds(82, 374, 200, 200);
		frame.getContentPane().add(btnQueries);
		
		btnReports = new JButton();
		btnReports.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Image img55 = new ImageIcon(this.getClass().getResource("/ReportsB.png")).getImage();
				btnReports.setIcon(new ImageIcon(img55));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Image img5 = new ImageIcon(this.getClass().getResource("/ReportsA.png")).getImage();
				btnReports.setIcon(new ImageIcon(img5));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Image img555 = new ImageIcon(this.getClass().getResource("/ReportsC.png")).getImage();
				btnReports.setIcon(new ImageIcon(img555));
			}
		});
		Image img5 = new ImageIcon(this.getClass().getResource("/ReportsA.png")).getImage();
		btnReports.setIcon(new ImageIcon(img5));
		btnReports.setBorderPainted(false);
		btnReports.setFocusPainted(false);
		btnReports.setContentAreaFilled(false);
		btnReports.setBounds(326, 374, 200, 200);
		frame.getContentPane().add(btnReports);
		
		JButton btnLogout = new JButton();
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Image img66 = new ImageIcon(this.getClass().getResource("/logout2.png")).getImage();
				btnLogout.setIcon(new ImageIcon(img66));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Image img6 = new ImageIcon(this.getClass().getResource("/logout1.png")).getImage();
				btnLogout.setIcon(new ImageIcon(img6));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Image img666 = new ImageIcon(this.getClass().getResource("/logout3.png")).getImage();
				btnLogout.setIcon(new ImageIcon(img666));
			}
		});
		Image img6 = new ImageIcon(this.getClass().getResource("/logout1.png")).getImage();
		btnLogout.setIcon(new ImageIcon(img6));
		btnLogout.setBorderPainted(false);
		btnLogout.setFocusPainted(false);
		btnLogout.setContentAreaFilled(false);
		btnLogout.setBounds(700, 486, 100, 100);
		frame.getContentPane().add(btnLogout);
		
		JLabel lblBanner = new JLabel("");
		Image img7 = new ImageIcon(this.getClass().getResource("/Time.png")).getImage();
		lblBanner.setIcon(new ImageIcon(img7));
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



