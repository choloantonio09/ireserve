package PrintComponent;

import java.awt.Font;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;

public class Component extends JFrame{
	
	private JTextArea txtPrintArea = new JTextArea();
	private JPanel contentPane;
	private JScrollPane scrollPane;
	
	public static void main(String args[]) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Component frame = new Component();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	


	public Component()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 429, 746);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[575px]", "[669px][73px]"));
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 0,grow");
		scrollPane.setViewportView(txtPrintArea);
		
		txtPrintArea.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrintSupport.printComponent(txtPrintArea);
			}
		});
		btnPrint.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnPrint.setForeground(new Color(255, 255, 255));
		btnPrint.setBackground(new Color(128, 0, 0));
		contentPane.add(btnPrint, "cell 0 1,alignx center,growy");
		
		
	}
}
