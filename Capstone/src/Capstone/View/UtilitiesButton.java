package Capstone.View;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.UIManager;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;

import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.metal.MetalButtonUI;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.SystemColor;

import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JButton;

import ch.randelshofer.quaqua.colorchooser.JColorWheel;

import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UtilitiesButton extends JPanel implements MouseListener, FocusListener, ChangeListener{
	private JPanel pnlButton;
	private JTabbedPane tpUtilities;
	private JColorChooser ccButton;
	private JPanel pnlButtonColorChooser;
	private JPanel pnlButtonBack;
	private JLabel lblButtonBackground;
	private JPanel pnlButtonFore;
	private JLabel lblButtonForeground;
	private JPanel pnlButtonColorSample;
	private JRadioButton rbButtonBackground;
	private JRadioButton rbButtonForeground;
	private final ButtonGroup bgButtonColor = new ButtonGroup();
	private JPanel pnlButtonMixed;
	private JLabel lblButtonMixed;
	int backButtonRed;
	int backButtonGreen;
	int backButtonBlue;
	int foreButtonRed;
	int foreButtonGreen;
	int foreButtonBlue;
	private JButton btnButtonSave;
	private JButton btnButtonCancel;
	int backBackgroundBlue;
	int backBackgroundGreen;
	int backBackgroundRed;
	private JRadioButton rbButtonHover;
	private JPanel pnlButtonHoverColor;
	private JLabel lblButtonHoverColor;
	private JRadioButton rbButtonHoverText;
	private JPanel pnbButtonHoverText;
	private JLabel lblButtonHoverText;
	int backHoverButtonRed;
	int backHoverButtonGreen;
	int backHoverButtonBlue;
	int foreHoverButtonRed;
	int foreHoverButtonGreen;
	int foreHoverButtonBlue;
	/**Create the panel.**/
	
	public UtilitiesButton() {
		System.setProperty("Quaqua.tabLayoutPolicy","wrap");
        // set the Quaqua Look and Feel in the UIManager
		try {
			UIManager.setLookAndFeel(ch.randelshofer.quaqua.QuaquaManager.getLookAndFeel());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setVisible(true);
		setLayout(new MigLayout("", "[][grow]", "[][][grow]"));
		pnlButtonColorChooser = new JPanel();
		add(pnlButtonColorChooser, "cell 0 0 2 4,grow");
		pnlButtonColorChooser.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		ccButton = new JColorChooser();
		pnlButtonColorChooser.add(ccButton, "cell 0 0,growx");
		
		pnlButtonColorSample = new JPanel();
		add(pnlButtonColorSample, "cell 0 4 2 1,alignx center,growy");
		pnlButtonColorSample.setLayout(new MigLayout("", "[][500px,grow]", "[70px][grow][50px][grow][70px]"));
		
		rbButtonBackground = new JRadioButton("");
		bgButtonColor.add(rbButtonBackground);
		rbButtonBackground.setSelected(true);
		pnlButtonColorSample.add(rbButtonBackground, "cell 0 0");
		
		pnlButtonBack = new JPanel();
		pnlButtonColorSample.add(pnlButtonBack, "cell 1 0,grow");
		pnlButtonBack.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlButtonBack.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		lblButtonBackground = new JLabel("BACKGROUND COLOR");
		lblButtonBackground.setHorizontalAlignment(SwingConstants.CENTER);
		lblButtonBackground.setFont(new Font("Sylfaen", Font.BOLD, 30));
		lblButtonBackground.setOpaque(true);
		pnlButtonBack.add(lblButtonBackground, "cell 0 0,grow");
		
		rbButtonHover = new JRadioButton("");
		bgButtonColor.add(rbButtonHover);
		pnlButtonColorSample.add(rbButtonHover, "cell 0 1");
		
		pnlButtonHoverColor = new JPanel();
		pnlButtonHoverColor.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlButtonColorSample.add(pnlButtonHoverColor, "cell 1 1,grow");
		pnlButtonHoverColor.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		lblButtonHoverColor = new JLabel("HOVER COLOR");
		lblButtonHoverColor.setOpaque(true);
		lblButtonHoverColor.setHorizontalAlignment(SwingConstants.CENTER);
		lblButtonHoverColor.setFont(new Font("Sylfaen", Font.BOLD, 30));
		pnlButtonHoverColor.add(lblButtonHoverColor, "cell 0 0,grow");
		
		rbButtonForeground = new JRadioButton("");
		bgButtonColor.add(rbButtonForeground);
		pnlButtonColorSample.add(rbButtonForeground, "cell 0 2");
		
		pnlButtonFore = new JPanel();
		pnlButtonColorSample.add(pnlButtonFore, "cell 1 2,grow");
		pnlButtonFore.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlButtonFore.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		lblButtonForeground = new JLabel("BACKGROUND TEXT");
		lblButtonForeground.setHorizontalAlignment(SwingConstants.CENTER);
		lblButtonForeground.setFont(new Font("Sylfaen", Font.BOLD, 30));
		pnlButtonFore.add(lblButtonForeground, "cell 0 0,grow");
		
		rbButtonHoverText = new JRadioButton("");
		bgButtonColor.add(rbButtonHoverText);
		pnlButtonColorSample.add(rbButtonHoverText, "cell 0 3");
		
		pnbButtonHoverText = new JPanel();
		pnbButtonHoverText.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlButtonColorSample.add(pnbButtonHoverText, "cell 1 3,grow");
		pnbButtonHoverText.setLayout(new MigLayout("", "[grow,fill]", "[grow]"));
		
		lblButtonHoverText = new JLabel("HOVER TEXT");
		lblButtonHoverText.setHorizontalAlignment(SwingConstants.CENTER);
		lblButtonHoverText.setFont(new Font("Sylfaen", Font.BOLD, 30));
		pnbButtonHoverText.add(lblButtonHoverText, "cell 0 0,grow");
		
		pnlButtonMixed = new JPanel();
		pnlButtonMixed.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlButtonColorSample.add(pnlButtonMixed, "cell 1 4,grow");
		pnlButtonMixed.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		lblButtonMixed = new JLabel("MIXED COLOR");
		lblButtonMixed.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent arg0) {

				lblButtonMixed.setBackground(getBackgroundColor());
				lblButtonMixed.setForeground(getForegroundColor());
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblButtonMixed.setBackground(getHoverBackgroundColor());
				lblButtonMixed.setForeground(getHoverForegroundColor());					
			}
		});
		lblButtonMixed.setHorizontalAlignment(SwingConstants.CENTER);
		lblButtonMixed.setFont(new Font("Sylfaen", Font.BOLD, 30));
		lblButtonMixed.setOpaque(true);
		pnlButtonMixed.add(lblButtonMixed, "cell 0 0,grow");
		
		btnButtonSave = new JButton("SAVE");
		btnButtonSave.setFont(new Font("Sylfaen", Font.PLAIN, 15));
		add(btnButtonSave, "flowx,cell 1 5,alignx right,aligny bottom");
		
		btnButtonCancel = new JButton("CANCEL");
		btnButtonCancel.setFont(new Font("Sylfaen", Font.PLAIN, 15));
		add(btnButtonCancel, "cell 1 5,alignx right,aligny bottom");
	
	DefaultPanelColor();
	DefaultButtonColor();
	DefaultTextColor();
    ccButton.getSelectionModel().addChangeListener(this);
}

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
private Color getTextFieldColor(){
	return SystemColor.controlHighlight;
}        
private Color getTextFieldIdColor(){
	return SystemColor.scrollbar;
}        
public void DefaultPanelColor(){
	setBackground(getPanelColor());
}	
public void DefaultTextColor(){
	
}		

private Color getBackgroundColor(){
    return new Color(backButtonRed, backButtonGreen, backButtonBlue);
}
private Color getHoverBackgroundColor(){
	return new Color(backHoverButtonRed, backHoverButtonGreen, backHoverButtonBlue);
}
private Color getForegroundColor(){
	return new Color(foreButtonRed, foreButtonGreen, foreButtonBlue);
}
private Color getHoverForegroundColor(){
	return new Color(foreHoverButtonRed, foreHoverButtonGreen, foreHoverButtonBlue);
}

public void DefaultButtonColor(){
	btnButtonCancel.setForeground(getButtonForegroundColor());
	btnButtonCancel.setBackground(getButtonBackgroundColor());
	btnButtonCancel.setUI(new MetalButtonUI());
	
	btnButtonSave.setForeground(getButtonForegroundColor());
	btnButtonSave.setBackground(getButtonBackgroundColor());
	btnButtonSave.setUI(new MetalButtonUI());
	
	lblButtonMixed.setForeground(getButtonForegroundColor());
	lblButtonMixed.setBackground(getButtonBackgroundColor());
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
public void mouseClicked(MouseEvent e) {
	// TODO Auto-generated method stub
}

@Override
public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub
	e.getComponent().setBackground(getButtonHoverBackgroundColor());
	e.getComponent().setForeground(getButtonHoverForegroundColor());
}

@Override
public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub
	e.getComponent().setBackground(getButtonBackgroundColor());
	e.getComponent().setForeground(getButtonForegroundColor());
}

@Override
public void mousePressed(MouseEvent e) {
	// TODO Auto-generated method stub
}

@Override
public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub
}

@Override
public void stateChanged(ChangeEvent e) {
	// TODO Auto-generated method stub
        Color ccBack = ccButton.getColor();
        Color ccFore = ccButton.getColor();

        Color ccHoverBack = ccButton.getColor();
        Color ccHoverFore = ccButton.getColor();
		if(rbButtonBackground.isSelected()){
	        backButtonRed = ccBack.getRed();
	        backButtonGreen = ccBack.getGreen();
	        backButtonBlue = ccBack.getBlue();
	        lblButtonBackground.setBackground(new Color(backButtonRed, backButtonGreen, backButtonBlue));
	        lblButtonMixed.setBackground(new Color(backButtonRed, backButtonGreen, backButtonBlue));
		}else if(rbButtonForeground.isSelected()){
	        foreButtonRed = ccFore.getRed();
	        foreButtonGreen = ccFore.getGreen();
	        foreButtonBlue = ccFore.getBlue();
	        lblButtonForeground.setForeground(new Color(foreButtonRed, foreButtonGreen, foreButtonBlue));
	        lblButtonMixed.setForeground(new Color(foreButtonRed, foreButtonGreen, foreButtonBlue));
		}else if(rbButtonHover.isSelected()){
	        backHoverButtonRed = ccHoverBack.getRed();
	        backHoverButtonGreen = ccHoverBack.getGreen();
	        backHoverButtonBlue = ccHoverBack.getBlue();
	        lblButtonHoverColor.setBackground(new Color(backHoverButtonRed, backHoverButtonGreen, backHoverButtonBlue));
		}else if(rbButtonHoverText.isSelected()){
	        foreHoverButtonRed = ccHoverFore.getRed();
	        foreHoverButtonGreen = ccHoverFore.getGreen();
	        foreHoverButtonBlue = ccHoverFore.getBlue();
	        lblButtonHoverText.setForeground(new Color(foreHoverButtonRed, foreHoverButtonGreen, foreHoverButtonBlue));
			
		} 
}

public void DefaultColors(){
	//BUTTON
	backButtonRed = lblButtonMixed.getBackground().getRed();
	backButtonGreen = lblButtonMixed.getBackground().getGreen();
	backButtonBlue = lblButtonMixed.getBackground().getBlue();
	
	foreButtonRed = lblButtonMixed.getForeground().getRed();
	foreButtonGreen = lblButtonMixed.getForeground().getGreen();
	foreButtonBlue = lblButtonMixed.getForeground().getBlue();
	

}
//	public static void main(String[] args) {
//        //Schedule a job for the event-dispatching thread:
//        //creating and showing this application's GUI.
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//            	JFrame f = new JFrame();
//            	Utilities u = new Utilities();
//            	f.setVisible(true);
//            	f.getContentPane().add(u);
//            	f.setSize(500,500);
//            	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            }
//        });
//    }
}
