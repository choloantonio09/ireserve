package Capstone.View;

import net.miginfocom.swing.MigLayout;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.plaf.metal.MetalButtonUI;

import Capstone.Model.ModelWaiting;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.text.ParseException;
import javax.swing.ImageIcon;

public class WaitingForm extends JDialog {
	public JTextField tfWaitingNo;
	public JTextField tfWaitingId;
	JTextField tfName;
	public JButton btnSave;
	private JButton btnCancel;
	private JLabel lblName;
	private JLabel lblWaiting;
	private JLabel lblWaitingId;
	private JLabel lblFieldWith;
	/**
	 * Create the panel.
	 */
	public WaitingForm() {
		getContentPane().setLayout(new MigLayout("", "[][grow]", "[][][][][grow][]"));
		
		lblFieldWith = new JLabel("Fields with (*) are required");
		lblFieldWith.setFont(new Font("Monospaced", Font.PLAIN, 12));
		getContentPane().add(lblFieldWith, "cell 0 0 2 1,alignx center,growy");
		
		lblWaitingId = new JLabel("Waiting ID:");
		lblWaitingId.setFont(new Font("Monospaced", Font.PLAIN, 20));
		getContentPane().add(lblWaitingId, "cell 0 1,alignx trailing");
		
		tfWaitingId = new JTextField();
		tfWaitingId.setFont(new Font("Monospaced", Font.PLAIN, 20));
		tfWaitingId.setColumns(10);
		getContentPane().add(tfWaitingId, "cell 1 1,growx");
		
		lblWaiting = new JLabel("Waiting #:");
		lblWaiting.setFont(new Font("Monospaced", Font.PLAIN, 20));
		getContentPane().add(lblWaiting, "cell 0 2");
		
		tfWaitingNo = new JTextField();
		tfWaitingNo.setFont(new Font("Monospaced", Font.PLAIN, 20));
		getContentPane().add(tfWaitingNo, "cell 1 2,growx");
		tfWaitingNo.setColumns(10);
		
		lblName = new JLabel("Name:");
		lblName.setFont(new Font("Monospaced", Font.PLAIN, 20));
		getContentPane().add(lblName, "cell 0 3");
		
		tfName = new JTextField();
		tfName.setFont(new Font("Monospaced", Font.PLAIN, 20));
		tfName.setColumns(10);
		getContentPane().add(tfName, "cell 1 3,growx");
		
		btnSave = new JButton("");
		btnSave.setName("ADD");
		btnSave.setIcon(new ImageIcon(WaitingForm.class.getResource("/Images/Icon/save.png")));
		btnSave.setContentAreaFilled(false);
		btnSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSave.setFont(new Font("Monospaced", Font.PLAIN, 15));
		getContentPane().add(btnSave, "flowx,cell 0 5 2 1,alignx right");
		
		btnCancel = new JButton("");
		btnCancel.setIcon(new ImageIcon(WaitingForm.class.getResource("/Images/Icon/exit.png")));
		btnCancel.setOpaque(false);
		btnCancel.setContentAreaFilled(false);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setFont(new Font("Monospaced", Font.PLAIN, 15));
		getContentPane().add(btnCancel, "cell 0 5 2 1,alignx right");
		DefaultButtonColor();

	}	//COLORS
	public Color getPanelColor(){
		return SystemColor.menu;
	}
	public Color getButtonBackgroundColor(){
        return new Color(27, 12, 95);
    }
	public Color getButtonHoverBackgroundColor(){
		return new Color(194, 253, 249);
	}
	public Color getButtonForegroundColor(){
		return new Color(215, 203, 0);
	}
	public Color getButtonHoverForegroundColor(){
		return Color.BLACK;
	}

    public Color getTextFieldColor(){
		return SystemColor.controlHighlight;
    }    
    //COLORS
	public void DefaultButtonColor(){

		btnSave.setUI(new MetalButtonUI());
		btnCancel.setUI(new MetalButtonUI());

	}	

	
	public ModelWaiting getFieldData() throws ParseException{
		ModelWaiting mw = new ModelWaiting();
		mw.setId(tfWaitingId.getText());
		mw.setName(tfName.getText());
		mw.setNo(tfWaitingNo.getText());
		return mw;
	}
	
	public void save(ActionListener save){
		btnSave.addActionListener(save);
	}

}
