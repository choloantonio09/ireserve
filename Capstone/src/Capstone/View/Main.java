package Capstone.View;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;

public class Main  {
	static JPanel displayPanel;
	static JFrame frame;
	/**
	 * Create the panel.
	 */
	public static void main(String[] args) {
		new Main();
	}
	public Main() {
		frame = new JFrame("Home");
		System.setProperty("Quaqua.tabLayoutPolicy","wrap");
        // set the Quaqua Look and Feel in the UIManager
		try {
//			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			UIManager.setLookAndFeel(ch.randelshofer.quaqua.QuaquaManager.getLookAndFeel());
		} catch (Exception e) {
			e.printStackTrace();
		}
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        System.out.println( sdf.format(cal.getTime()) );
        frame.setSize(850,650);
        frame.setTitle("Home");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));
		displayPanel = new JPanel();
		frame.getContentPane().add(displayPanel, "cell 0 0,grow");
		displayPanel.setLayout(new MigLayout("", "[850px,grow]", "[650px,grow]"));
		Selection s = new Selection();
		displayPanel.add(s, "cell 0 0,grow");
		frame.	repaint();
		frame.revalidate();
		frame.setLocationRelativeTo(null);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	

}
