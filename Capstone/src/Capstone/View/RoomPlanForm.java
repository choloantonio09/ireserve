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
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class RoomPlanForm extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfStandardRoomCapacity;
	private JTextField tfFunctionRoomCapacity;
	JTextField tfRemainingCapacity;
	private JLabel lblId;
	private JLabel lblRemainingCapacity;
	protected int mainroom;
	protected int functionroom;
	protected int totalcapacity;
	public JButton btnSave;
	private JButton btnCancel;
	private String id;
	private String status;
	private ModelFloor mf;
	private ModelFunctionRoom mfr;
	private ModelStandardRoom msr;
	public JTextField tfFunctionRoomId;
	private JTextField tfFunctionRoomName;
	private JTextField tfFunctionRoomDesc;
	private JTextField tfFunctionRoomTotalCapacity;
	private JTextField tfFunctionRoomTable;
	private JTextField tfFunctionRoomChair;
	public JTextField tfStandardRoomId;
	private JTextField tfStandardRoomName;
	private JTextField tfStandardRoomDesc;
	private JTextField tfStandardRoomTable;
	private JTextField tfStandardRoomChair;
	private JTextField tfStandardRoomTotalCapacity;
	public JCheckBox chckbxStandardRoomCapacity;
	public JCheckBox chckbxFunctionRoomCapacity;
	public JTextField tfId;
	private JPanel pnlStandardRoom;
	private JPanel pnlFunctionRoom;
	private JLabel lblFunctionRoomTotalCapacity;
	private JLabel lblFunctionRoomChair;
	private JLabel lblFunctionRoomTable;
	private JLabel lblFunctionRoomDesc;
	private JLabel lblFunctionRoomName;
	private JLabel lblFunctionRoomId;
	private JLabel lblStandardRoomTotalCapacity;
	private JLabel lblStandardRoomChair;
	private JLabel lblStandardRoomTable;
	private JLabel lblStandardRoomDesc;
	private JLabel lblStandardRoomName;
	private JLabel lblStandardRoomId;
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RoomPlanForm dialog = new RoomPlanForm();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RoomPlanForm() {
		setTitle("Additional Room");
		setBounds(100, 100, 930, 499);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow][][grow]", "[][][][][grow]"));
		{
			lblId = new JLabel("ID:");
			lblId.setFont(new Font("Monospaced", Font.PLAIN, 15));
			contentPanel.add(lblId, "cell 0 0");
		}
		
		tfId = new JTextField();
		tfId.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfId.setEnabled(false);
		tfId.setEditable(false);
		tfId.setColumns(10);
		contentPanel.add(tfId, "cell 1 0 3 1,growx");
		
		chckbxStandardRoomCapacity = new JCheckBox("Standard room capacity:");
		chckbxStandardRoomCapacity.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(chckbxStandardRoomCapacity.isSelected()){
					pnlStandardRoom.show();
					tfStandardRoomCapacity.setEnabled(true);
				}else{
					pnlStandardRoom.hide();
					tfStandardRoomCapacity.setEnabled(false);
					tfStandardRoomCapacity.setText("0");
					mainroom = Integer.parseInt(tfFunctionRoomCapacity.getText());
					functionroom = Integer.parseInt(tfStandardRoomCapacity.getText());
					totalcapacity = mainroom + functionroom;
				}
			}
		});
		
		{
			lblRemainingCapacity = new JLabel("Remaining capacity:");
			lblRemainingCapacity.setFont(new Font("Monospaced", Font.PLAIN, 15));
			contentPanel.add(lblRemainingCapacity, "cell 0 1");
		}
		{
			tfRemainingCapacity = new JTextField();
			tfRemainingCapacity.setEnabled(false);
			tfRemainingCapacity.setEditable(false);
			tfRemainingCapacity.setFont(new Font("Monospaced", Font.PLAIN, 15));
			tfRemainingCapacity.setColumns(10);
			contentPanel.add(tfRemainingCapacity, "cell 1 1 3 1,growx");
		}
		chckbxStandardRoomCapacity.setFont(new Font("Monospaced", Font.PLAIN, 15));
		contentPanel.add(chckbxStandardRoomCapacity, "flowx,cell 0 2 2 1");
		
		chckbxFunctionRoomCapacity = new JCheckBox("Function room capacity:");
		chckbxFunctionRoomCapacity.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(chckbxFunctionRoomCapacity.isSelected()){
					pnlFunctionRoom.show();
					tfFunctionRoomCapacity.setEnabled(true);
				}else{
					pnlFunctionRoom.hide();
					tfFunctionRoomCapacity.setEnabled(false);
					tfFunctionRoomCapacity.setText("0");
					mainroom = Integer.parseInt(tfFunctionRoomCapacity.getText());
					functionroom = Integer.parseInt(tfStandardRoomCapacity.getText());
					totalcapacity = mainroom + functionroom;
				}
			}
		});
		chckbxFunctionRoomCapacity.setFont(new Font("Monospaced", Font.PLAIN, 15));
		contentPanel.add(chckbxFunctionRoomCapacity, "flowx,cell 2 2 2 1");
		{
			tfStandardRoomCapacity = new JTextField("0");
			tfStandardRoomCapacity.setEnabled(false);
			tfStandardRoomCapacity.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent arg0) {
					if(tfStandardRoomCapacity.getText().equalsIgnoreCase("0")){
						tfStandardRoomCapacity.setText("");
					}
				}@Override
				public void focusLost(FocusEvent arg0) {
					if(tfStandardRoomCapacity.getText().equalsIgnoreCase("")){
						tfStandardRoomCapacity.setText("0");					
						mainroom = Integer.parseInt(tfFunctionRoomCapacity.getText());
						functionroom = Integer.parseInt(tfStandardRoomCapacity.getText());
						totalcapacity = mainroom + functionroom;
					}
				}
			});
			tfStandardRoomCapacity.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {
					if(!tfStandardRoomCapacity.getText().isEmpty()){	
						mainroom = Integer.parseInt(tfFunctionRoomCapacity.getText());
						functionroom = Integer.parseInt(tfStandardRoomCapacity.getText());
						totalcapacity = mainroom + functionroom;
					}
				}
			});
			tfStandardRoomCapacity.setText("0");
			tfStandardRoomCapacity.setFont(new Font("Monospaced", Font.PLAIN, 15));
			tfStandardRoomCapacity.setColumns(10);
			contentPanel.add(tfStandardRoomCapacity, "cell 0 3 2 1,growx,aligny top");
		}
		tfFunctionRoomCapacity = new JTextField();
		tfFunctionRoomCapacity.setEnabled(false);
		tfFunctionRoomCapacity.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(tfFunctionRoomCapacity.getText().equalsIgnoreCase("0")){
					tfFunctionRoomCapacity.setText("");
				}
			}@Override
			public void focusLost(FocusEvent arg0) {
				if(tfFunctionRoomCapacity.getText().equalsIgnoreCase("")){
					tfFunctionRoomCapacity.setText("0");					
					mainroom = Integer.parseInt(tfFunctionRoomCapacity.getText());
					functionroom = Integer.parseInt(tfStandardRoomCapacity.getText());
					totalcapacity = mainroom + functionroom;
				}
			}
		});
		tfFunctionRoomCapacity.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(!tfFunctionRoomCapacity.getText().isEmpty()){	
					mainroom = Integer.parseInt(tfFunctionRoomCapacity.getText());
					functionroom = Integer.parseInt(tfStandardRoomCapacity.getText());
					totalcapacity = mainroom + functionroom;
				}
			}
		});
		tfFunctionRoomCapacity.setText("0");
		tfFunctionRoomCapacity.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfFunctionRoomCapacity.setColumns(10);
		contentPanel.add(tfFunctionRoomCapacity, "cell 2 3 2 1,growx,aligny top");
		
		{
			ShowPanelStandardRoom();
			ShowPanelFunctionRoom();
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
				btnSave.setIcon(new ImageIcon(RoomPlanForm.class.getResource("/Images/Icon/save.png")));
				btnSave.setActionCommand("OK");
				buttonPane.add(btnSave);
				getRootPane().setDefaultButton(btnSave);
			}
			{
				btnCancel = new JButton("");
				btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnCancel.setOpaque(false);
				btnCancel.setContentAreaFilled(false);
				btnCancel.setIcon(new ImageIcon(RoomPlanForm.class.getResource("/Images/Icon/exit.png")));
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

	public ModelFloor getFloorField() {
		id = tfId.getText();
		mainroom = Integer.parseInt(tfStandardRoomCapacity.getText());
		functionroom = Integer.parseInt(tfFunctionRoomCapacity.getText());
		remaningfloor = Integer.parseInt(tfRemainingCapacity.getText());
		remainingcapacity = remaningfloor - totalcapacity;
		
//		total = totalfloor + totalcapacity; //HERE
		remaining = remaningfloor - totalcapacity;
		main = mainfloor + mainroom;
		function = functionfloor + functionroom;
		
		if(id.equalsIgnoreCase("FLR01")){
			status = "Default";			
		}else{
			status = "Extended";
		}
		mf = new ModelFloor();
		mf.setId(id);
		mf.setMainRoom(mainroom);
		mf.setFunctionRoom(functionroom);
		mf.setTotalFloorCapacity(remaningfloor);
		mf.setRemainingCapacity(remainingcapacity);
		mf.setTotalCapacity(totalcapacity);
		mf.setStatus(status);
		
		mf.setRemaining(remaining);
		mf.setMain(main);
		mf.setFunction(function);
		
		return mf;
	}	

	public ModelFunctionRoom getFunctionField() {
		id = tfId.getText();
		functionid = tfFunctionRoomId.getText();
		functionname = tfFunctionRoomName.getText();
		functiondesc = tfFunctionRoomDesc.getText();
		functioncapacity = Integer.parseInt(tfFunctionRoomCapacity.getText());
		functiontable = Integer.parseInt(tfFunctionRoomTable.getText());
		functionchair = Integer.parseInt(tfFunctionRoomChair.getText());;
		functontotalcapacity = Integer.parseInt(tfFunctionRoomTotalCapacity.getText());
		functiontype ="Event";
		functionstatus = "ACTIVE";

		mfr = new ModelFunctionRoom();
		mfr.setFloorId(id);
		mfr.setId(functionid);
		mfr.setName(functionname);
		mfr.setDesc(functiondesc);
		mfr.setCapacity(functioncapacity);
		mfr.setTable(functiontable);
		mfr.setChair(functionchair);
		mfr.setTotalCapacity(functiontotalcapacity);
		mfr.setType(functiontype);
		mfr.setStatus(functionstatus);
		return mfr;
	}	

	public ModelStandardRoom getStandardField() {
		id = tfId.getText();
		standardid = tfStandardRoomId.getText();
		standardname = tfStandardRoomName.getText();
		standarddesc = tfStandardRoomDesc.getText();
		standardcapacity = Integer.parseInt(tfStandardRoomCapacity.getText());
		standardtable = Integer.parseInt(tfStandardRoomTable.getText());
		standardchair = Integer.parseInt(tfStandardRoomChair.getText());;
		standardtotalcapacity = Integer.parseInt(tfStandardRoomTotalCapacity.getText());
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
	
	public void ShowPanelStandardRoom(){

		pnlStandardRoom = new JPanel();
		pnlStandardRoom.hide();
		contentPanel.add(pnlStandardRoom, "cell 0 4 2 1,grow");
		pnlStandardRoom.setLayout(new MigLayout("", "[][grow]", "[][][][][][]"));
		
		lblStandardRoomId = new JLabel("ID:");
		pnlStandardRoom.add(lblStandardRoomId, "cell 0 0,alignx left,aligny center");
		lblStandardRoomId.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		tfStandardRoomId = new JTextField();
		pnlStandardRoom.add(tfStandardRoomId, "cell 1 0,growx");
		tfStandardRoomId.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfStandardRoomId.setEnabled(false);
		tfStandardRoomId.setEditable(false);
		tfStandardRoomId.setColumns(10);
		
		lblStandardRoomName = new JLabel("Name:");
		pnlStandardRoom.add(lblStandardRoomName, "cell 0 1");
		lblStandardRoomName.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		tfStandardRoomName = new JTextField();
		pnlStandardRoom.add(tfStandardRoomName, "cell 1 1,growx");
		tfStandardRoomName.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfStandardRoomName.setColumns(10);
		
		lblStandardRoomDesc = new JLabel("Description:");
		pnlStandardRoom.add(lblStandardRoomDesc, "cell 0 2");
		lblStandardRoomDesc.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		tfStandardRoomDesc = new JTextField();
		pnlStandardRoom.add(tfStandardRoomDesc, "cell 1 2,growx");
		tfStandardRoomDesc.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfStandardRoomDesc.setColumns(10);
		
		lblStandardRoomTable = new JLabel("Table/s:");
		pnlStandardRoom.add(lblStandardRoomTable, "cell 0 3");
		lblStandardRoomTable.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		tfStandardRoomTable = new JTextField();
		tfStandardRoomTable.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(tfStandardRoomTable.getText().equalsIgnoreCase("0")){
					tfStandardRoomTable.setText("");
				}
			}@Override
			public void focusLost(FocusEvent arg0) {
				if(tfStandardRoomTable.getText().equalsIgnoreCase("")){
					tfStandardRoomTable.setText("0");					
					standardchair = Integer.parseInt(tfStandardRoomChair.getText());
					standardtable = Integer.parseInt(tfStandardRoomTable.getText());
					standardtotalcapacity = standardchair * standardtable;
					tfStandardRoomTotalCapacity.setText(String.valueOf(standardtotalcapacity));		
				}
			}
		});
		tfStandardRoomTable.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(!tfStandardRoomTable.getText().isEmpty()){	
					standardchair = Integer.parseInt(tfStandardRoomChair.getText());
					standardtable = Integer.parseInt(tfStandardRoomTable.getText());
					standardtotalcapacity = standardchair * standardtable;
					tfStandardRoomTotalCapacity.setText(String.valueOf(standardtotalcapacity));		
				}
			}
		});
		pnlStandardRoom.add(tfStandardRoomTable, "cell 1 3,growx");
		tfStandardRoomTable.setText("0");
		tfStandardRoomTable.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfStandardRoomTable.setColumns(10);
		
		lblStandardRoomChair = new JLabel("Chair/s per table:");
		pnlStandardRoom.add(lblStandardRoomChair, "cell 0 4");
		lblStandardRoomChair.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		tfStandardRoomChair = new JTextField();
		tfStandardRoomChair.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(tfStandardRoomChair.getText().equalsIgnoreCase("0")){
					tfStandardRoomChair.setText("");
				}
			}@Override
			public void focusLost(FocusEvent arg0) {
				if(tfStandardRoomChair.getText().equalsIgnoreCase("")){
					tfStandardRoomChair.setText("0");					
					standardchair = Integer.parseInt(tfStandardRoomChair.getText());
					standardtable = Integer.parseInt(tfStandardRoomTable.getText());
					standardtotalcapacity = standardchair * standardtable;
					tfStandardRoomTotalCapacity.setText(String.valueOf(standardtotalcapacity));		
				}
			}
		});
		tfStandardRoomChair.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(!tfStandardRoomChair.getText().isEmpty()){	
					standardchair = Integer.parseInt(tfStandardRoomChair.getText());
					standardtable = Integer.parseInt(tfStandardRoomTable.getText());
					standardtotalcapacity = standardchair * standardtable;
					tfStandardRoomTotalCapacity.setText(String.valueOf(standardtotalcapacity));		
				}
			}
		});
		pnlStandardRoom.add(tfStandardRoomChair, "cell 1 4,growx");
		tfStandardRoomChair.setText("0");
		tfStandardRoomChair.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfStandardRoomChair.setColumns(10);
		
		lblStandardRoomTotalCapacity = new JLabel("Total capacity:");
		pnlStandardRoom.add(lblStandardRoomTotalCapacity, "cell 0 5");
		lblStandardRoomTotalCapacity.setFont(new Font("Monospaced", Font.PLAIN, 15));

		tfStandardRoomTotalCapacity = new JTextField();
		pnlStandardRoom.add(tfStandardRoomTotalCapacity, "cell 1 5,growx");
		tfStandardRoomTotalCapacity.setText("0");
		tfStandardRoomTotalCapacity.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfStandardRoomTotalCapacity.setEnabled(false);
		tfStandardRoomTotalCapacity.setEditable(false);
		tfStandardRoomTotalCapacity.setColumns(10);
	}
	
	public void ShowPanelFunctionRoom(){

		pnlFunctionRoom = new JPanel();
		pnlFunctionRoom.hide();
		contentPanel.add(pnlFunctionRoom, "cell 2 4 2 1,grow");
		pnlFunctionRoom.setLayout(new MigLayout("", "[][grow]", "[][][][][][]"));
		
		lblFunctionRoomId = new JLabel("ID:");
		pnlFunctionRoom.add(lblFunctionRoomId, "cell 0 0,alignx left,aligny center");
		lblFunctionRoomId.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		tfFunctionRoomId = new JTextField();
		pnlFunctionRoom.add(tfFunctionRoomId, "cell 1 0,growx");
		tfFunctionRoomId.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfFunctionRoomId.setEnabled(false);
		tfFunctionRoomId.setEditable(false);
		tfFunctionRoomId.setColumns(10);
		
		lblFunctionRoomName = new JLabel("Name:");
		pnlFunctionRoom.add(lblFunctionRoomName, "cell 0 1");
		lblFunctionRoomName.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		tfFunctionRoomName = new JTextField();
		pnlFunctionRoom.add(tfFunctionRoomName, "cell 1 1,growx");
		tfFunctionRoomName.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfFunctionRoomName.setColumns(10);
		
		lblFunctionRoomDesc = new JLabel("Description:");
		pnlFunctionRoom.add(lblFunctionRoomDesc, "cell 0 2");
		lblFunctionRoomDesc.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		tfFunctionRoomDesc = new JTextField();
		pnlFunctionRoom.add(tfFunctionRoomDesc, "cell 1 2,growx");
		tfFunctionRoomDesc.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfFunctionRoomDesc.setColumns(10);
		
		lblFunctionRoomTable = new JLabel("Table/s:");
		pnlFunctionRoom.add(lblFunctionRoomTable, "cell 0 3");
		lblFunctionRoomTable.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		tfFunctionRoomTable = new JTextField();
		tfFunctionRoomTable.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(tfFunctionRoomTable.getText().equalsIgnoreCase("0")){
					tfFunctionRoomTable.setText("");
				}
			}@Override
			public void focusLost(FocusEvent arg0) {
				if(tfFunctionRoomTable.getText().equalsIgnoreCase("")){
					tfFunctionRoomTable.setText("0");					
					functionchair = Integer.parseInt(tfFunctionRoomChair.getText());
					functiontable = Integer.parseInt(tfFunctionRoomTable.getText());
					functiontotalcapacity = functionchair * functiontable;
					tfFunctionRoomTotalCapacity.setText(String.valueOf(functiontotalcapacity));		
				}
			}
		});
		tfFunctionRoomTable.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(!tfFunctionRoomTable.getText().isEmpty()){	
					functionchair = Integer.parseInt(tfFunctionRoomChair.getText());
					functiontable = Integer.parseInt(tfFunctionRoomTable.getText());
					functiontotalcapacity = functionchair * functiontable;
					tfFunctionRoomTotalCapacity.setText(String.valueOf(functiontotalcapacity));		
				}
			}
		});
		pnlFunctionRoom.add(tfFunctionRoomTable, "cell 1 3,growx");
		tfFunctionRoomTable.setText("0");
		tfFunctionRoomTable.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfFunctionRoomTable.setColumns(10);
		
		lblFunctionRoomChair = new JLabel("Chair/s per table:");
		pnlFunctionRoom.add(lblFunctionRoomChair, "cell 0 4");
		lblFunctionRoomChair.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		tfFunctionRoomChair = new JTextField();
		tfFunctionRoomChair.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(tfFunctionRoomChair.getText().equalsIgnoreCase("0")){
					tfFunctionRoomChair.setText("");
				}
			}@Override
			public void focusLost(FocusEvent arg0) {
				if(tfFunctionRoomChair.getText().equalsIgnoreCase("")){
					tfFunctionRoomChair.setText("0");					
					functionchair = Integer.parseInt(tfFunctionRoomChair.getText());
					functiontable = Integer.parseInt(tfFunctionRoomTable.getText());
					functiontotalcapacity = functionchair * functiontable;
					tfFunctionRoomTotalCapacity.setText(String.valueOf(functiontotalcapacity));		
				}
			}
		});
		tfFunctionRoomChair.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(!tfFunctionRoomChair.getText().isEmpty()){	
					functionchair = Integer.parseInt(tfFunctionRoomChair.getText());
					functiontable = Integer.parseInt(tfFunctionRoomTable.getText());
					functiontotalcapacity = functionchair * functiontable;
					tfFunctionRoomTotalCapacity.setText(String.valueOf(functiontotalcapacity));		
				}
			}
		});
		pnlFunctionRoom.add(tfFunctionRoomChair, "cell 1 4,growx");
		tfFunctionRoomChair.setText("0");
		tfFunctionRoomChair.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfFunctionRoomChair.setColumns(10);
		
		lblFunctionRoomTotalCapacity = new JLabel("Total capacity:");
		pnlFunctionRoom.add(lblFunctionRoomTotalCapacity, "cell 0 5");
		lblFunctionRoomTotalCapacity.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		tfFunctionRoomTotalCapacity = new JTextField();
		pnlFunctionRoom.add(tfFunctionRoomTotalCapacity, "cell 1 5,growx");
		tfFunctionRoomTotalCapacity.setText("0");
		tfFunctionRoomTotalCapacity.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfFunctionRoomTotalCapacity.setEnabled(false);
		tfFunctionRoomTotalCapacity.setEditable(false);
		tfFunctionRoomTotalCapacity.setColumns(10);
	}
	
}
