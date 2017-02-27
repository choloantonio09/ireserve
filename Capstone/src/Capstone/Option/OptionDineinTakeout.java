package Capstone.Option;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.MetalButtonUI;

import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Dialog.ModalExclusionType;

public class OptionDineinTakeout extends JFrame implements MouseListener, FocusListener {

	private JPanel contentPane;
	private JPanel panel;
	private JButton btnDinein;
	private JButton btnTakeout;
	private JButton btnCancel;
	private JPanel panel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OptionDineinTakeout frame = new OptionDineinTakeout();
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
	public OptionDineinTakeout() {
		System.setProperty("Quaqua.tabLayoutPolicy","wrap");
        // set the Quaqua Look and Feel in the UIManager
		try {
			UIManager.setLookAndFeel(ch.randelshofer.quaqua.QuaquaManager.getLookAndFeel());
		} catch (Exception e) {
			e.printStackTrace();
		}		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setBackground(new Color(255, 255, 255, 180));
		getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setOpaque(true);
		getContentPane().add(panel_1, "cell 0 0,growx,aligny center");
		panel_1.setLayout(new MigLayout("", "[grow][][][][grow]", "[100px]"));
		
		btnDinein = new JButton("DINE-IN");
		btnDinein.setFont(new Font("Monospaced", Font.PLAIN, 50));
		panel_1.add(btnDinein, "cell 1 0");
		
		btnTakeout = new JButton("TAKE-OUT");
		btnTakeout.setFont(new Font("Monospaced", Font.PLAIN, 50));
		panel_1.add(btnTakeout, "cell 2 0");
		
		btnCancel = new JButton("CANCEL");
		btnCancel.setFont(new Font("Monospaced", Font.PLAIN, 50));
		panel_1.add(btnCancel, "cell 3 0");

		Listener();
		setVisible(true);
	}
	
	//COLORS
	public Color getPanelColor(){
		return SystemColor.menu;
	}
	public static Color getButtonBackgroundColor(){
        return new Color(27, 12, 95);
    }
	public Color getButtonHoverBackgroundColor(){
		return new Color(194, 253, 249);
	}
	public static Color getButtonForegroundColor(){
		return new Color(215, 203, 0);
	}
	public Color getButtonHoverForegroundColor(){
		return Color.BLACK;
	}

    public Color getTextFieldColor(){
		return SystemColor.controlHighlight;
    }    
	 public void Listener(){

	    	btnDinein.addMouseListener(this);
	    	btnDinein.addFocusListener(this);

	    	btnTakeout.addMouseListener(this);
	    	btnTakeout.addFocusListener(this);

	    	btnCancel.addMouseListener(this);
	    	btnCancel.addFocusListener(this);
	    	
	    	btnTakeout.setUI(new MetalButtonUI());
	    	btnTakeout.setBackground(getButtonBackgroundColor());
	    	btnTakeout.setForeground(getButtonForegroundColor());
			
	    	btnDinein.setUI(new MetalButtonUI());
	    	btnDinein.setBackground(getButtonBackgroundColor());
	    	btnDinein.setForeground(getButtonForegroundColor());

	    	btnCancel.setUI(new MetalButtonUI());
	    	btnCancel.setBackground(getButtonBackgroundColor());
	    	btnCancel.setForeground(getButtonForegroundColor());

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

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
}
