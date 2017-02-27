package Capstone.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.metal.MetalButtonUI;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;

import java.awt.SystemColor;

import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.Color;

import net.miginfocom.swing.MigLayout;

import javax.swing.ImageIcon;

import Capstone.Model.ModelNormalFood;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Cursor;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;

public class NormalFoodForm extends JDialog {

	private JPanel contentPane;
	public JTextField tfId;
	public JTextField tfName;
	public BackgroundImageApp pnlImage;
	private JButton btnUpload;
	protected JFileChooser jfBrowse;
	String destination = "src\\Images\\Default\\food.jpg";
	File file;
	protected String normalPath;
	private JButton btnSave;
	private JButton btnExit;
	public JComboBox cbCategory;
	private JPanel pnlDecor;
	JRadioButton rdbtnRegular;
	JRadioButton rdbtnEvent;
	private JLabel lblType;
	private final ButtonGroup bgType = new ButtonGroup();
	private JComboBox cbStatus;
	private JLabel lblStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NormalFoodForm frame = new NormalFoodForm();
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
	public NormalFoodForm() {
		System.setProperty("Quaqua.tabLayoutPolicy","wrap");
        // set the Quaqua Look and Feel in the UIManager
		try {
			UIManager.setLookAndFeel(ch.randelshofer.quaqua.QuaquaManager.getLookAndFeel());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 420, 466);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[126px,grow][4px][66px,grow][336px][174px]", "[17px][339px,grow][29px][27px][27px][][27px][][75px]"));
		
		JLabel label_3 = new JLabel("Fields with (*) are required");
		label_3.setFont(new Font("Monospaced", Font.PLAIN, 12));
		contentPane.add(label_3, "cell 0 0 3 1,alignx left,aligny top");
		btnUpload = new JButton("UPLOAD IMAGE");
		btnUpload.setUI(new MetalButtonUI());
		btnUpload.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpload.setContentAreaFilled(false);
		btnUpload.setOpaque(false);
		btnUpload.setIcon(new ImageIcon(NormalFoodForm.class.getResource("/Images/Icon/upload_image.png")));
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jfBrowse = new JFileChooser();
				jfBrowse.setAccessory(new ImagePreview(jfBrowse));
				jfBrowse.addChoosableFileFilter(new FileNameExtensionFilter("JPEG Files", "jpg"));
				jfBrowse.addChoosableFileFilter(new FileNameExtensionFilter("PNG Files","png"));
				jfBrowse.addChoosableFileFilter(new FileNameExtensionFilter("GIF Files","gif"));
				jfBrowse.addChoosableFileFilter(new FileNameExtensionFilter("BMP Files","bmp"));
				jfBrowse.addChoosableFileFilter(new FileNameExtensionFilter("All Images", "jpg", "png", "gif", "bmp"));
				int returnBrowse = jfBrowse.showOpenDialog(null);
				if(returnBrowse == JFileChooser.APPROVE_OPTION){
					normalPath = jfBrowse.getSelectedFile().getPath();
					File file = new File(normalPath);
					pnlDecor.removeAll();
					pnlDecor.updateUI();
					pnlImage = new BackgroundImageApp(file);
					pnlDecor.add(pnlImage, "cell 0 0,grow");
					pnlDecor.revalidate();
					pnlDecor.repaint();
					
				}else{
					JOptionPane.showMessageDialog(null, "No image selected");
				}
			}
		});
		
		pnlDecor = new JPanel();
		contentPane.add(pnlDecor, "cell 0 1 5 1,grow");
		pnlDecor.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		btnUpload.setFont(new Font("Monospaced", Font.PLAIN, 15));
		contentPane.add(btnUpload, "cell 0 2 5 1,alignx center,aligny top");
		
		JLabel label = new JLabel("Food ID:");
		label.setFont(new Font("Monospaced", Font.PLAIN, 15));
//		contentPane.add(label, "cell 0 3,growx,aligny center");
		
		tfId = new JTextField();
		tfId.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfId.setEditable(false);
		tfId.setColumns(10);
		tfId.setBackground(SystemColor.scrollbar);
//		contentPane.add(tfId, "cell 2 3 3 1,growx,aligny top");
		
		tfName = new JTextField();
		tfName.setFont(new Font("Monospaced", Font.PLAIN, 15));
		tfName.setColumns(10);
		tfName.setBackground(SystemColor.controlHighlight);
		contentPane.add(tfName, "cell 2 4 3 1,growx,aligny top");
		
		JLabel label_1 = new JLabel("Name of Food*:");
		label_1.setFont(new Font("Monospaced", Font.PLAIN, 15));
		contentPane.add(label_1, "cell 0 4,alignx left,aligny center");
		
		lblType = new JLabel("Type:");
		lblType.setFont(new Font("Monospaced", Font.PLAIN, 15));
		contentPane.add(lblType, "cell 0 5");
		
		rdbtnRegular = new JRadioButton("Regular");
		rdbtnRegular.setSelected(true);
		rdbtnRegular.setFont(new Font("Monospaced", Font.PLAIN, 15));
		bgType.add(rdbtnRegular);
		contentPane.add(rdbtnRegular, "cell 2 5");
		
		rdbtnEvent = new JRadioButton("Event");
		rdbtnEvent.setFont(new Font("Monospaced", Font.PLAIN, 15));
		bgType.add(rdbtnEvent);
		contentPane.add(rdbtnEvent, "cell 3 5 2 1");
		
		JLabel label_2 = new JLabel("Category:");
		label_2.setFont(new Font("Monospaced", Font.PLAIN, 15));
		contentPane.add(label_2, "cell 0 6,growx,aligny center");
		
		cbCategory = new JComboBox();
		cbCategory.setFont(new Font("Monospaced", Font.PLAIN, 15));
		contentPane.add(cbCategory, "cell 2 6 3 1,growx,aligny top");
		
		lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Monospaced", Font.PLAIN, 15));
		contentPane.add(lblStatus, "cell 0 7");
		
		cbStatus = new JComboBox();
		cbStatus.setModel(new DefaultComboBoxModel(new String[] {"ACTIVE", "INACTIVE"}));
		cbStatus.setFont(new Font("Monospaced", Font.PLAIN, 15));
		contentPane.add(cbStatus, "cell 2 7 3 1,growx");
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, "cell 4 8,grow");
		panel_1.setLayout(new MigLayout("", "[][]", "[]"));
		
		btnSave = new JButton("");
		btnSave.setUI(new MetalButtonUI());
		btnSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSave.setContentAreaFilled(false);
		btnSave.setOpaque(false);
		btnSave.setIcon(new ImageIcon(NormalFoodForm.class.getResource("/Images/Icon/save.png")));
		btnSave.setActionCommand("ADD");
		panel_1.add(btnSave, "cell 0 0");
		
		btnExit = new JButton("");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnExit.setUI(new MetalButtonUI());
		btnExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExit.setContentAreaFilled(false);
		btnExit.setOpaque(false);
		btnExit.setIcon(new ImageIcon(NormalFoodForm.class.getResource("/Images/Icon/exit.png")));
		panel_1.add(btnExit, "cell 1 0");		
		
		setIcon();
	}
	//Image scale to fit on Panel class
	public class BackgroundImageApp extends JPanel {
		
		BufferedImage originalImage;
		BufferedImage scaledImage;

		BackgroundImageApp(File file) {
		    setPreferredSize(new Dimension(320, 200));
		    try {
		        originalImage = ImageIO.read(file);
		    } catch(Exception e){}

		    addComponentListener(new ResizerListener());
		}

		public void resize() {
		    double widthScaleFactor = getWidth() / (double)originalImage.getWidth();
		    double heightScaleFactor = getHeight() / (double)originalImage.getHeight();
		    double scaleFactor = (widthScaleFactor > heightScaleFactor)? heightScaleFactor : widthScaleFactor;

		    AffineTransform at = new AffineTransform();
		    at.scale(scaleFactor, scaleFactor);

		    AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		    scaledImage = scaleOp.filter(originalImage, scaledImage);
		    revalidate();
		    updateUI();
		    repaint();
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			double widthScaleFactor = getWidth() / (double)getWidth();
			double heightScaleFactor = getHeight() / (double)getHeight();
			double scaleFactor = (widthScaleFactor > heightScaleFactor)? heightScaleFactor : widthScaleFactor;

			int width = (int)(getWidth() * scaleFactor);
			int height = (int)(getHeight() * scaleFactor);

			g.drawImage(originalImage, 0, 0, width, height, null);
		}


		class ResizerListener implements ComponentListener {
		    @Override
		    public void componentResized(ComponentEvent e) {
		        resize();
		    }

		    @Override public void componentHidden(ComponentEvent e) {}
		    @Override public void componentMoved(ComponentEvent e) {}
		    @Override public void componentShown(ComponentEvent e) {}
	}
}

	public void setIcon(){
		//SET ICON
		pnlDecor.removeAll();
		file = new File(destination);
		normalPath = file.getAbsoluteFile().getAbsolutePath();
		pnlImage = new BackgroundImageApp(file);
		pnlImage.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		pnlImage.setBorder(new LineBorder(Color.DARK_GRAY));
		pnlImage.setBackground(SystemColor.menu);
		pnlImage.setLayout(new BorderLayout(0, 0));
		pnlDecor.add(pnlImage, "cell 0 0, grow");
	}
    
	public void save(ActionListener save){
		btnSave.addActionListener(save);
	}
	
	
	//Moving Normal Food Values to ModelNormalFood
	public ModelNormalFood getNormalFoodField(){
		ModelNormalFood mnf = new ModelNormalFood();
		mnf.setId(tfId.getText());
		mnf.setName(tfName.getText());
		mnf.setCategory((String) cbCategory.getSelectedItem());
		if(rdbtnEvent.isSelected()){
			mnf.setType(rdbtnEvent.getText());
		}else if(rdbtnRegular.isSelected()){
			mnf.setType(rdbtnRegular.getText());
		}
		mnf.setPhotos(normalPath);
		mnf.setStatus((String) cbStatus.getSelectedItem());
		
		
		return mnf;
	}
	//end of Moving Normal Food Values to ModelNormalFood
	
//end of Image scale to fit on Panel class
}
