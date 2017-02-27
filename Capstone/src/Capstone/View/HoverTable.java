package Capstone.View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class HoverTable  {

	private JPanel contentPane;
	public static JTable tblTables;
	static JFrame jf;
	private JScrollPane scrollPane;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public HoverTable() {
		jf = new JFrame();
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		jf.setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 0,grow");
		
		tblTables = new JTable();
		scrollPane.setViewportView(tblTables);
	}

}
