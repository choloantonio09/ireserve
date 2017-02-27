package Capstone.Login;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 414, 604);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][80px][80px][80px][-30,grow]", "[grow][40px][80px][80px][80px][80px][grow]"));
		
		passwordField = new JPasswordField();
		passwordField.setEchoChar('o');
		contentPane.add(passwordField, "cell 1 1 2 1,grow");
		
		JButton button = new JButton("<");
		button.setContentAreaFilled(false);
		contentPane.add(button, "cell 3 1,grow");
		
		JButton b1 = new JButton("1");
		b1.setContentAreaFilled(false);
		contentPane.add(b1, "cell 1 2,grow");
		
		JButton b2 = new JButton("2");
		b2.setContentAreaFilled(false);
		contentPane.add(b2, "cell 2 2,grow");
		
		JButton b3 = new JButton("3");
		b3.setContentAreaFilled(false);
		contentPane.add(b3, "cell 3 2,grow");
		
		JButton b4 = new JButton("4");
		b4.setContentAreaFilled(false);
		contentPane.add(b4, "cell 1 3,grow");
		
		JButton b5 = new JButton("5");
		b5.setContentAreaFilled(false);
		contentPane.add(b5, "cell 2 3,grow");
		
		JButton b6 = new JButton("6");
		b6.setContentAreaFilled(false);
		contentPane.add(b6, "cell 3 3,grow");
		
		JButton b7 = new JButton("7");
		b7.setContentAreaFilled(false);
		contentPane.add(b7, "cell 1 4,grow");
		
		JButton b8 = new JButton("8");
		b8.setContentAreaFilled(false);
		contentPane.add(b8, "cell 2 4,grow");
		
		JButton b9 = new JButton("9");
		b9.setContentAreaFilled(false);
		contentPane.add(b9, "cell 3 4,grow");
		
		JButton btnClear = new JButton("x");
		btnClear.setContentAreaFilled(false);
		contentPane.add(btnClear, "cell 1 5,grow");
		
		JButton b0 = new JButton("0");
		b0.setContentAreaFilled(false);
		contentPane.add(b0, "cell 2 5,grow");
		
		JButton btnLogin = new JButton(">");
		btnLogin.setContentAreaFilled(false);
		contentPane.add(btnLogin, "cell 3 5,grow");
	}

}
