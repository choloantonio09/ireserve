package Capstone.View;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Capstone.Model.ModelFloor;
import Capstone.Model.ModelFunctionRoom;
import Capstone.Model.ModelStandardRoom;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import javax.swing.JProgressBar;

public class TableForm extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JTextField tfRoomCapacity;
	private JLabel lblRoomCapacity;
	protected int mainroom;
	protected int functionroom;
	protected int totalcapacity;
	public JButton btnSave;
	public JButton btnCancel;
	private String id;
	private String status;
	private ModelFloor mf;
	private ModelFunctionRoom mfr;
	private ModelStandardRoom msr;
	public JTextField tfRoomId;
	JTextField tfRoomName;
	JTextField tfRoomDesc;
	private JTextField tfRoomTable;
	private JTextField tfRoomChair;
	JTextField tfRoomTotalCapacity;
	private JLabel lblRoomTotalCapacity;
	private JLabel lblRoomChair;
	private JLabel lblRoomTable;
	private JLabel lblRoomDesc;
	private JLabel lblRoomName;
	private JLabel lblRoomId;
	private JPanel buttonPane;
	protected int standardchair;
	protected int standardtable;
	protected int functionchair;
	protected int functiontable;
	protected int functiontotalcapacity;
	protected int standardtotalcapacity;
	private String functionid;
	private String functionname;
	private String functiondesc;
	private int functioncapacity;
	private int functontotalcapacity;
	private String functiontype;
	private String functionstatus;
	private String standardid;
	private String standardname;
	private String standarddesc;
	private int standardcapacity;
	private String standardtype;
	private String standardstatus;
	private int remaningfloor;
	private int remainingcapacity;
	int totalfloor;
	private int remaining;
	private int total;
	private int main;
	private int function;
	public int mainfloor;
	public int functionfloor;
	public JProgressBar progressBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TableForm dialog = new TableForm();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TableForm() {
		setTitle("Manage table");
		setBounds(100, 100, 516, 414);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][][][][][][][]"));
		
		lblRoomId = new JLabel("ID:");
		contentPanel.add(lblRoomId, "cell 0 0");
		lblRoomId.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		tfRoomId = new JTextField();
		contentPanel.add(tfRoomId, "cell 1 0,growx");
		tfRoomId.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfRoomId.setEnabled(false);
		tfRoomId.setEditable(false);
		tfRoomId.setColumns(10);
		
		{
			lblRoomCapacity = new JLabel("Room capacity:");
			lblRoomCapacity.setFont(new Font("Monospaced", Font.PLAIN, 15));
			contentPanel.add(lblRoomCapacity, "cell 0 1");
		}
		
		{
			ShowPanelRoom();
		}
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnSave = new JButton("");
				btnSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnSave.setOpaque(false);
				btnSave.setContentAreaFilled(false);
				btnSave.setIcon(new ImageIcon(TableForm.class.getResource("/Images/Icon/save.png")));
				btnSave.setActionCommand("OK");
				buttonPane.add(btnSave);
				getRootPane().setDefaultButton(btnSave);
			}
			{
				btnCancel = new JButton("");
				btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnCancel.setOpaque(false);
				btnCancel.setContentAreaFilled(false);
				btnCancel.setIcon(new ImageIcon(TableForm.class.getResource("/Images/Icon/exit.png")));
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}
	}
	public void save(ActionListener save){
		btnSave.addActionListener(save);
	}

	public ModelStandardRoom getStandardField() {
		standardid = tfRoomId.getText();
		standardname = tfRoomName.getText();
		standarddesc = tfRoomDesc.getText();
		standardcapacity = Integer.parseInt(tfRoomCapacity.getText());
		standardtable = Integer.parseInt(tfRoomTable.getText());
		standardchair = Integer.parseInt(tfRoomChair.getText());;
		standardtotalcapacity = Integer.parseInt(tfRoomTotalCapacity.getText());
		standardtype ="Normal";
		standardstatus = "ACTIVE";

		msr = new ModelStandardRoom();
		msr.setId(standardid);
		msr.setFloorId(id);
		msr.setName(standardname);
		msr.setDesc(standarddesc);
		msr.setCapacity(standardcapacity);
		msr.setTable(standardtable);
		msr.setChair(standardchair);
		msr.setTotalCapacity(standardtotalcapacity);
		msr.setType(standardtype);
		msr.setStatus(standardstatus);
		return msr;
	}	
	
	public void ShowPanelRoom(){
		{
			tfRoomCapacity = new JTextField();
			tfRoomCapacity.setEnabled(false);
			tfRoomCapacity.setFont(new Font("Monospaced", Font.PLAIN, 15));
			tfRoomCapacity.setColumns(10);
			contentPanel.add(tfRoomCapacity, "cell 1 1,growx,aligny top");
		}
		
		lblRoomName = new JLabel("Name:");
		contentPanel.add(lblRoomName, "cell 0 2");
		lblRoomName.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		tfRoomName = new JTextField();
		tfRoomName.setEnabled(false);
		tfRoomName.setEditable(false);
		contentPanel.add(tfRoomName, "cell 1 2,growx");
		tfRoomName.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfRoomName.setColumns(10);
		
		lblRoomDesc = new JLabel("Description:");
		contentPanel.add(lblRoomDesc, "cell 0 3");
		lblRoomDesc.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		tfRoomDesc = new JTextField();
		tfRoomDesc.setEnabled(false);
		tfRoomDesc.setEditable(false);
		contentPanel.add(tfRoomDesc, "cell 1 3,growx");
		tfRoomDesc.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfRoomDesc.setColumns(10);
		
		lblRoomTable = new JLabel("Table/s:");
		contentPanel.add(lblRoomTable, "cell 0 4");
		lblRoomTable.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		tfRoomTable = new JTextField();
		contentPanel.add(tfRoomTable, "cell 1 4,growx");
		tfRoomTable.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(tfRoomTable.getText().equalsIgnoreCase("0")){
					tfRoomTable.setText("");
				}
			}@Override
			public void focusLost(FocusEvent arg0) {
				if(tfRoomTable.getText().equalsIgnoreCase("")){
					tfRoomTable.setText("0");					
					standardchair = Integer.parseInt(tfRoomChair.getText());
					standardtable = Integer.parseInt(tfRoomTable.getText());
					standardtotalcapacity = standardchair * standardtable;
					tfRoomTotalCapacity.setText(String.valueOf(standardtotalcapacity));		
				}
			}
		});
		tfRoomTable.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(!tfRoomTable.getText().isEmpty()){	
					standardchair = Integer.parseInt(tfRoomChair.getText());
					standardtable = Integer.parseInt(tfRoomTable.getText());
					standardtotalcapacity = standardchair * standardtable;
					tfRoomTotalCapacity.setText(String.valueOf(standardtotalcapacity));		
				}
			}
		});
		tfRoomTable.setText("0");
		tfRoomTable.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfRoomTable.setColumns(10);
		
		lblRoomChair = new JLabel("Chair/s per table:");
		contentPanel.add(lblRoomChair, "cell 0 5");
		lblRoomChair.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		tfRoomChair = new JTextField();
		contentPanel.add(tfRoomChair, "cell 1 5,growx");
		tfRoomChair.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(tfRoomChair.getText().equalsIgnoreCase("0")){
					tfRoomChair.setText("");
				}
			}@Override
			public void focusLost(FocusEvent arg0) {
				if(tfRoomChair.getText().equalsIgnoreCase("")){
					tfRoomChair.setText("0");					
					standardchair = Integer.parseInt(tfRoomChair.getText());
					standardtable = Integer.parseInt(tfRoomTable.getText());
					standardtotalcapacity = standardchair * standardtable;
					tfRoomTotalCapacity.setText(String.valueOf(standardtotalcapacity));		
				}
			}
		});
		tfRoomChair.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(!tfRoomChair.getText().isEmpty()){	
					standardchair = Integer.parseInt(tfRoomChair.getText());
					standardtable = Integer.parseInt(tfRoomTable.getText());
					standardtotalcapacity = standardchair * standardtable;
					tfRoomTotalCapacity.setText(String.valueOf(standardtotalcapacity));		
				}
			}
		});
		tfRoomChair.setText("0");
		tfRoomChair.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfRoomChair.setColumns(10);
		
		lblRoomTotalCapacity = new JLabel("Total capacity:");
		contentPanel.add(lblRoomTotalCapacity, "cell 0 6");
		lblRoomTotalCapacity.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
				tfRoomTotalCapacity = new JTextField();
				contentPanel.add(tfRoomTotalCapacity, "cell 1 6,growx");
				tfRoomTotalCapacity.setText("0");
				tfRoomTotalCapacity.setFont(new Font("Monospaced", Font.PLAIN, 15));
				tfRoomTotalCapacity.setEnabled(false);
				tfRoomTotalCapacity.setEditable(false);
				tfRoomTotalCapacity.setColumns(10);
				
				progressBar = new JProgressBar();
				progressBar.setStringPainted(true);
				contentPanel.add(progressBar, "cell 0 7 2 1,growx");
	}
	
}
