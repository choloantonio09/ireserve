package Capstone.View;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;

import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;

import Capstone.Database.Connect;
import Capstone.Model.ModelLogin;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;

public class Login extends JFrame{
	private JPasswordField passwordField;
	private JButton btnLogin;
	private JButton btnExit;
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JButton b6;
	private JButton b5;
	private JButton b4;
	private JButton b0;
	private JButton btnX;
	private JButton b8;
	private JButton b9;
	private JButton b7;
	String temporary = "";
	private JLabel lblErrorMessage;

    public static void main(String[] args) {    	
 				try {
 					Login log = new Login();
 					log.setVisible(true);
 					log.setLocationRelativeTo(null);
 				} catch (Exception e) {
 					e.printStackTrace();
 				} 
    }

    public Login() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/Images/background.jpg")));
		System.setProperty("Quaqua.tabLayoutPolicy","wrap");
        // set the Quaqua Look and Feel in the UIManager
		try {
//			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			UIManager.setLookAndFeel(ch.randelshofer.quaqua.QuaquaManager.getLookAndFeel());
		} catch (Exception e) {
			e.printStackTrace();
		}
    	try {
                    BackgroundPane background = new BackgroundPane();
                    background.setBackground(ImageIO.read(getClass().getResource("/Images/background.jpg")));

                    
                    setExtendedState(JFrame.MAXIMIZED_BOTH);
//                    setUndecorated(true);
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    setContentPane(background);
//                    pack();
                    setLocationRelativeTo(null);
                    getContentPane().setLayout(new MigLayout("", "[grow][85px][8.00px][85px][8.00px][85px][grow]", "[12.00,grow][81.00px][][][75px][][75px][][75px][][75px][][52px][grow]"));
                    
                    JPanel panel_3 = new JPanel();
                    panel_3.setVisible(false);
                    getContentPane().add(panel_3, "cell 0 0 7 1,grow");
                    
                    JPanel panel = new JPanel();
                    panel.setVisible(false);
                    getContentPane().add(panel, "cell 0 1 1 12,grow");
                    
                    passwordField = new JPasswordField();
                    passwordField.addKeyListener(new KeyAdapter() {
                    	@Override
                    	public void keyTyped(KeyEvent evt) {
                    		if(evt.getKeyChar() == KeyEvent.VK_ENTER){
                    			btnLogin.doClick();
                    		}
                    		if (!Character.isDigit(evt.getKeyChar()) && (evt.getKeyChar() != KeyEvent.VK_BACK_SPACE)&& (evt.getKeyChar() != KeyEvent.VK_ENTER)) {
                    	        evt.consume();
                    	        lblErrorMessage.setText("Only numbers are allowed");
                    	    }else{
                    			temporary = temporary.concat(String.valueOf(evt.getKeyChar()));
                    			lblErrorMessage.setText(null);
                    		}
                    	}
                    });
                    passwordField.setFont(new Font("Tahoma", Font.PLAIN, 30));
                    passwordField.setForeground(Color.WHITE);
                    passwordField.setOpaque(false);
                    passwordField.setBackground(new Color(0,0,0,0));
                    getContentPane().add(passwordField, "cell 1 1 5 1,grow");
                    passwordField.setBorder(new LineBorder(new Color(171, 173, 179)));
                    
                    JPanel panel_2 = new JPanel();
                    panel_2.setVisible(false);
                    getContentPane().add(panel_2, "cell 6 1 1 13,grow");
                    
                    lblErrorMessage = new JLabel("");
                    lblErrorMessage.setForeground(Color.RED);
                    lblErrorMessage.setFont(new Font("Tahoma", Font.PLAIN, 15));
                    getContentPane().add(lblErrorMessage, "cell 1 2 5 1,grow");
                    
                    btnLogin = new JButton(">");
                    btnLogin.addActionListener(new ActionListener() {
                    	public void actionPerformed(ActionEvent arg0) {
                    		try {
        						Connect c = new Connect();
        						String account_type_id = null;
        						ModelLogin logMod = new ModelLogin();
        						logMod = getFieldData();
        						c.pst = c.con.prepareCall("{call select_accounts(?)}");
        						c.pst.setString(1, logMod.getPin());
        						c.pst.execute();
        						c.rs = c.pst.getResultSet();
        						while(c.rs.next()){
        							account_type_id = c.rs.getString("user_type");
        						}
        						c.rs.close();
        						c.pst.close();
        						c.con.close();
        						if("Administrator".equals(account_type_id)){
        							dispose();
        							Main frame = new Main();
        							frame.frame.setVisible(true);
        						}
//        						else if(AccountType.RECEPTIONIST == account_type_id){
//        							log.dispose();
//        							Receptionist frame = new Receptionist();
//        							AddButton abView = new AddButton();
//        							CalendarModel cdModel = new CalendarModel();
//        							ReservationModel rvModel = new ReservationModel();
//        							new ReceptionistController(frame,cdModel, rvModel);
//        							frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
//        							frame.setVisible(true);
//        						}
        						else{
        							JOptionPane.showMessageDialog(null, "Incorrect pin");
        						}
        						
        					} catch (Exception e) {
        						e.printStackTrace();
        					}
                    	}
                    });
                    btnLogin.setContentAreaFilled(false);
                    btnLogin.setFont(new Font("Serif", Font.BOLD, 30));
                    btnLogin.setForeground(Color.WHITE);
                    getContentPane().add(btnLogin, "cell 5 10,grow");
                    btnLogin.setBorder(new LineBorder(new Color(192, 192, 192)));
                    btnLogin.setBackground(new Color(17, 24, 42));
                    
                    btnExit = new JButton("EXIT");
                    btnExit.setContentAreaFilled(false);
                    btnExit.setFont(new Font("Serif", Font.BOLD, 30));
                    btnExit.setForeground(Color.WHITE);
                    btnExit.addActionListener(new ActionListener() {
                    	public void actionPerformed(ActionEvent arg0) {
                    		System.exit(0);
                    	}
                    });
                    getContentPane().add(btnExit, "cell 3 12,grow");
                    btnExit.setBorder(new LineBorder(new Color(192, 192, 192)));
                    btnExit.setBackground(new Color(17, 24, 42));
                    
                    b1 = new JButton("1");
                    b1.addKeyListener(new KeyAdapter() {
                    	@Override
                    	public void keyTyped(KeyEvent evt) {
                    		if (!Character.isDigit(evt.getKeyChar()) && !(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
                    	        evt.consume();
                    	        lblErrorMessage.setText("Only numbers are allowed");
                    	    }else{
                    			temporary = temporary.concat(String.valueOf(evt.getKeyChar()));
               	    		 	passwordField.setText(temporary);
                    			lblErrorMessage.setText(null);
                    		}
                    	}
                    });
                    b1.setContentAreaFilled(false);
                    b1.setFont(new Font("Serif", Font.BOLD, 30));
                    b1.setForeground(Color.WHITE);
                    getContentPane().add(b1, "cell 1 4,grow");
                    b1.setBorder(new LineBorder(new Color(192, 192, 192)));
                    b1.setBackground(new Color(17, 24, 42));
                    
                    b2 = new JButton("2");
                    b2.setContentAreaFilled(false);
                    b2.setFont(new Font("Serif", Font.BOLD, 30));
                    b2.setForeground(Color.WHITE);
                    getContentPane().add(b2, "cell 3 4,grow");
                    b2.setBorder(new LineBorder(new Color(192, 192, 192)));
                    b2.setBackground(new Color(17, 24, 42));
                    b2.addKeyListener(new KeyAdapter() {
                    	@Override
                    	public void keyTyped(KeyEvent evt) {
                    		if (!Character.isDigit(evt.getKeyChar()) && !(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
                    	        evt.consume();
                    	        lblErrorMessage.setText("Only numbers are allowed");
                    	    }else{
                    			temporary = temporary.concat(String.valueOf(evt.getKeyChar()));
               	    		 	passwordField.setText(temporary);
                    			lblErrorMessage.setText(null);
                    		}
                    	}
                    });
                    
                    b3 = new JButton("3");
                    b3.setContentAreaFilled(false);
                    b3.setFont(new Font("Serif", Font.BOLD, 30));
                    b3.setForeground(Color.WHITE);
                    getContentPane().add(b3, "cell 5 4,grow");
                    b3.setBorder(new LineBorder(new Color(192, 192, 192)));
                    b3.setBackground(new Color(17, 24, 42));
                    b3.addKeyListener(new KeyAdapter() {
                    	@Override
                    	public void keyTyped(KeyEvent evt) {
                    		if (!Character.isDigit(evt.getKeyChar()) && !(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
                    	        evt.consume();
                    	        lblErrorMessage.setText("Only numbers are allowed");
                    	    }else{
                    			temporary = temporary.concat(String.valueOf(evt.getKeyChar()));
               	    		 	passwordField.setText(temporary);
                    			lblErrorMessage.setText(null);
                    		}
                    	}
                    });
                    
                    b6 = new JButton("6");
                    b6.setContentAreaFilled(false);
                    b6.setFont(new Font("Serif", Font.BOLD, 30));
                    b6.setForeground(Color.WHITE);
                    getContentPane().add(b6, "cell 5 6,grow");
                    b6.setBorder(new LineBorder(new Color(192, 192, 192)));
                    b6.setBackground(new Color(17, 24, 42));
                    b6.addKeyListener(new KeyAdapter() {
                    	@Override
                    	public void keyTyped(KeyEvent evt) {
                    		if (!Character.isDigit(evt.getKeyChar()) && !(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
                    	        evt.consume();
                    	        lblErrorMessage.setText("Only numbers are allowed");
                    	    }else{
                    			temporary = temporary.concat(String.valueOf(evt.getKeyChar()));
               	    		 	passwordField.setText(temporary);
                    			lblErrorMessage.setText(null);
                    		}
                    	}
                    });
                    
                    b5 = new JButton("5");
                    b5.setContentAreaFilled(false);
                    b5.setFont(new Font("Serif", Font.BOLD, 30));
                    b5.setForeground(Color.WHITE);
                    getContentPane().add(b5, "cell 3 6,grow");
                    b5.setBorder(new LineBorder(new Color(192, 192, 192)));
                    b5.setBackground(new Color(17, 24, 42));
                    b5.addKeyListener(new KeyAdapter() {
                    	@Override
                    	public void keyTyped(KeyEvent evt) {
                    		if (!Character.isDigit(evt.getKeyChar()) && !(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
                    	        evt.consume();
                    	        lblErrorMessage.setText("Only numbers are allowed");
                    	    }else{
                    			temporary = temporary.concat(String.valueOf(evt.getKeyChar()));
               	    		 	passwordField.setText(temporary);
                    			lblErrorMessage.setText(null);
                    		}
                    	}
                    });
                    
                    b4 = new JButton("4");
                    b4.setContentAreaFilled(false);
                    b4.setFont(new Font("Serif", Font.BOLD, 30));
                    b4.setForeground(Color.WHITE);
                    getContentPane().add(b4, "cell 1 6,grow");
                    b4.setBorder(new LineBorder(new Color(192, 192, 192)));
                    b4.setBackground(new Color(17, 24, 42));
                    b4.addKeyListener(new KeyAdapter() {
                    	@Override
                    	public void keyTyped(KeyEvent evt) {
                    		if (!Character.isDigit(evt.getKeyChar()) && !(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
                    	        evt.consume();
                    	        lblErrorMessage.setText("Only numbers are allowed");
                    	    }else{
                    			temporary = temporary.concat(String.valueOf(evt.getKeyChar()));
               	    		 	passwordField.setText(temporary);
                    			lblErrorMessage.setText(null);
                    		}
                    	}
                    });
                    
                    b0 = new JButton("0");
                    b0.setContentAreaFilled(false);
                    b0.setFont(new Font("Serif", Font.BOLD, 30));
                    b0.setForeground(Color.WHITE);
                    getContentPane().add(b0, "cell 3 10,grow");
                    b0.setBorder(new LineBorder(new Color(192, 192, 192)));
                    b0.setBackground(new Color(17, 24, 42));
                    b0.addKeyListener(new KeyAdapter() {
                    	@Override
                    	public void keyTyped(KeyEvent evt) {
                    		if (!Character.isDigit(evt.getKeyChar()) && !(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
                    	        evt.consume();
                    	        lblErrorMessage.setText("Only numbers are allowed");
                    	    }else{
                    			temporary = temporary.concat(String.valueOf(evt.getKeyChar()));
               	    		 	passwordField.setText(temporary);
                    			lblErrorMessage.setText(null);
                    		}
                    	}
                    });
                    
                    btnX = new JButton("x");
                    btnX.setContentAreaFilled(false);
                    btnX.setFont(new Font("Serif", Font.BOLD, 30));
                    btnX.setForeground(Color.WHITE);
                    getContentPane().add(btnX, "cell 1 10,grow");
                    btnX.setBorder(new LineBorder(new Color(192, 192, 192)));
                    btnX.setBackground(new Color(17, 24, 42));
                    
                    b8 = new JButton("8");
                    b8.setContentAreaFilled(false);
                    b8.setFont(new Font("Serif", Font.BOLD, 30));
                    b8.setForeground(Color.WHITE);
                    getContentPane().add(b8, "cell 3 8,grow");
                    b8.setBorder(new LineBorder(new Color(192, 192, 192)));
                    b8.setBackground(new Color(17, 24, 42));
                    b8.addKeyListener(new KeyAdapter() {
                    	@Override
                    	public void keyTyped(KeyEvent evt) {
                    		if (!Character.isDigit(evt.getKeyChar()) && !(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
                    	        evt.consume();
                    	        lblErrorMessage.setText("Only numbers are allowed");
                    	    }else{
                    			temporary = temporary.concat(String.valueOf(evt.getKeyChar()));
               	    		 	passwordField.setText(temporary);
                    			lblErrorMessage.setText(null);
                    		}
                    	}
                    });
                    
                    b9 = new JButton("9");
                    b9.setContentAreaFilled(false);
                    b9.setFont(new Font("Serif", Font.BOLD, 30));
                    b9.setForeground(Color.WHITE);
                    getContentPane().add(b9, "cell 5 8,grow");
                    b9.setBorder(new LineBorder(new Color(192, 192, 192)));
                    b9.setBackground(new Color(17, 24, 42));
                    b9.addKeyListener(new KeyAdapter() {
                    	@Override
                    	public void keyTyped(KeyEvent evt) {
                    		if (!Character.isDigit(evt.getKeyChar()) && !(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
                    	        evt.consume();
                    	        lblErrorMessage.setText("Only numbers are allowed");
                    	    }else{
                    			temporary = temporary.concat(String.valueOf(evt.getKeyChar()));
               	    		 	passwordField.setText(temporary);
                    			lblErrorMessage.setText(null);
                    		}
                    	}
                    });
                    
                    b7 = new JButton("7");
                    b7.setContentAreaFilled(false);
                    b7.setFont(new Font("Serif", Font.BOLD, 30));
                    b7.setForeground(Color.WHITE);
                    getContentPane().add(b7, "cell 1 8,grow");
                    b7.setBorder(new LineBorder(new Color(192, 192, 192)));
                    b7.setBackground(new Color(17, 24, 42));
                    b7.addKeyListener(new KeyAdapter() {
                    	@Override
                    	public void keyTyped(KeyEvent evt) {
                    		if (!Character.isDigit(evt.getKeyChar()) && !(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
                    	        evt.consume();
                    	        lblErrorMessage.setText("Only numbers are allowed");
                    	    }else{
                    	    	passwordField.setText(String.valueOf(evt.getKeyChar()));
                    			temporary = temporary.concat(String.valueOf(evt.getKeyChar()));
                    			lblErrorMessage.setText(null);
                    		}
                    	}
                    });
                    
                    JPanel panel_1 = new JPanel();
                    panel_1.setVisible(false);
                    getContentPane().add(panel_1, "cell 0 13 6 1,grow");

                    ActionClass actionEvent = new ActionClass();

                    b1.addActionListener(actionEvent);b1.setActionCommand("1");
                    b2.addActionListener(actionEvent);b2.setActionCommand("2");
                    b3.addActionListener(actionEvent);b3.setActionCommand("3");
                    b4.addActionListener(actionEvent);b4.setActionCommand("4");
                    b5.addActionListener(actionEvent);b5.setActionCommand("5");
                    b6.addActionListener(actionEvent);b6.setActionCommand("6");
                    b7.addActionListener(actionEvent);b7.setActionCommand("7");
                    b8.addActionListener(actionEvent);b8.setActionCommand("8");
                    b9.addActionListener(actionEvent);b9.setActionCommand("9");
                    b0.addActionListener(actionEvent);b0.setActionCommand("0");
                    btnX.addActionListener(actionEvent);btnX.setActionCommand("10");
                    
                } catch (IOException exp) {
                    exp.printStackTrace();
                }
    }
    class ActionClass implements ActionListener {
    public void actionPerformed(ActionEvent e) {
    	 int action = Integer.parseInt(e.getActionCommand());

    	 switch(action) {
	    	 case 1:
	    		 temporary = temporary.concat("1");
	    		 passwordField.setText(temporary);
     			lblErrorMessage.setText(null);
    	         break;
	    	 case 2: 
	    		 temporary = temporary.concat("2");
			    	passwordField.setText(temporary);
        			lblErrorMessage.setText(null);
    	         break;
	    	 case 3: 
	    		 temporary = temporary.concat("3");
			    	passwordField.setText(temporary);
        			lblErrorMessage.setText(null);
		         break;
	    	 case 4: 
	    		 temporary = temporary.concat("4");
			    	passwordField.setText(temporary);
        			lblErrorMessage.setText(null);
		         break;
	    	 case 5: 
	    		 temporary = temporary.concat("5");
			    	passwordField.setText(temporary);
        			lblErrorMessage.setText(null);
		         break;
	    	 case 6: 
	    		 temporary = temporary.concat("6");
			    	passwordField.setText(temporary);
        			lblErrorMessage.setText(null);
		         break;
	    	 case 7: 
	    		 temporary = temporary.concat("7");
			    	passwordField.setText(temporary);
        			lblErrorMessage.setText(null);
		         break;
	    	 case 8: 
	    		 temporary = temporary.concat("8");
			    	passwordField.setText(temporary);
        			lblErrorMessage.setText(null);
		         break;
	    	 case 9: 
	    		 temporary = temporary.concat("9");
			    	passwordField.setText(temporary);
        			lblErrorMessage.setText(null);
		         break;
		    case 0: 
	    		 temporary = temporary.concat("0");
		    	passwordField.setText(temporary);
    			lblErrorMessage.setText(null);
		        break;
		    case 10:
		    	temporary = "";
		    	passwordField.setText(null);
    			lblErrorMessage.setText(null);
		    	break;
    	 }
    }
    }
    public void addActionListener(ActionListener login){
		btnLogin.addActionListener(login);
	}

	public ModelLogin getFieldData() {
		ModelLogin logMod = new ModelLogin();
		logMod.setPin(passwordField.getText());
		return logMod;
	}
    public class BackgroundPane extends JPanel {

        private BufferedImage img;
        private BufferedImage scaled;

        public BackgroundPane() {
        }

        @Override
        public Dimension getPreferredSize() {
            return img == null ? super.getPreferredSize() : new Dimension(img.getWidth(), img.getHeight());
        }

        public void setBackground(BufferedImage value) {
            if (value != img) {
                this.img = value;
                repaint();
            }
        }

        @Override
        public void invalidate() {
            super.invalidate();
            if (getWidth() > img.getWidth() || getHeight() > img.getHeight()) {
                scaled = getScaledInstanceToFill(img, getSize());
            } else {
                scaled = img;
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (scaled != null) {
                int x = (getWidth() - scaled.getWidth()) / 2;
                int y = (getHeight() - scaled.getHeight()) / 2;
                g.drawImage(scaled, x, y, this);
            }
        }

    }

    public static BufferedImage getScaledInstanceToFill(BufferedImage img, Dimension size) {

        double scaleFactor = getScaleFactorToFill(img, size);

        return getScaledInstance(img, scaleFactor);

    }

    public static double getScaleFactorToFill(BufferedImage img, Dimension size) {

        double dScale = 1;

        if (img != null) {

            int imageWidth = img.getWidth();
            int imageHeight = img.getHeight();

            double dScaleWidth = getScaleFactor(imageWidth, size.width);
            double dScaleHeight = getScaleFactor(imageHeight, size.height);

            dScale = Math.max(dScaleHeight, dScaleWidth);

        }

        return dScale;

    }

    public static double getScaleFactor(int iMasterSize, int iTargetSize) {

        double dScale = (double) iTargetSize / (double) iMasterSize;

        return dScale;

    }

    public static BufferedImage getScaledInstance(BufferedImage img, double dScaleFactor) {

        return getScaledInstance(img, dScaleFactor, RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);

    }

    protected static BufferedImage getScaledInstance(BufferedImage img, double dScaleFactor, Object hint, boolean bHighQuality) {

        BufferedImage imgScale = img;

        int iImageWidth = (int) Math.round(img.getWidth() * dScaleFactor);
        int iImageHeight = (int) Math.round(img.getHeight() * dScaleFactor);

//        System.out.println("Scale Size = " + iImageWidth + "x" + iImageHeight);
        if (dScaleFactor <= 1.0d) {

            imgScale = getScaledDownInstance(img, iImageWidth, iImageHeight, hint, bHighQuality);

        } else {

            imgScale = getScaledUpInstance(img, iImageWidth, iImageHeight, hint, bHighQuality);

        }

        return imgScale;

    }

    protected static BufferedImage getScaledDownInstance(BufferedImage img,
            int targetWidth,
            int targetHeight,
            Object hint,
            boolean higherQuality) {

        int type = (img.getTransparency() == Transparency.OPAQUE)
                ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;

        BufferedImage ret = (BufferedImage) img;
        if (targetHeight > 0 || targetWidth > 0) {
            int w, h;
            if (higherQuality) {
                // Use multi-step technique: start with original size, then
                // scale down in multiple passes with drawImage()
                // until the target size is reached
                w = img.getWidth();
                h = img.getHeight();
            } else {
                // Use one-step technique: scale directly from original
                // size to target size with a single drawImage() call
                w = targetWidth;
                h = targetHeight;
            }

            do {
                if (higherQuality && w > targetWidth) {
                    w /= 2;
                    if (w < targetWidth) {
                        w = targetWidth;
                    }
                }

                if (higherQuality && h > targetHeight) {
                    h /= 2;
                    if (h < targetHeight) {
                        h = targetHeight;
                    }
                }

                BufferedImage tmp = new BufferedImage(Math.max(w, 1), Math.max(h, 1), type);
                Graphics2D g2 = tmp.createGraphics();
                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
                g2.drawImage(ret, 0, 0, w, h, null);
                g2.dispose();

                ret = tmp;
            } while (w != targetWidth || h != targetHeight);
        } else {
            ret = new BufferedImage(1, 1, type);
        }
        return ret;
    }
    
    protected static BufferedImage getScaledUpInstance(BufferedImage img,
            int targetWidth,
            int targetHeight,
            Object hint,
            boolean higherQuality) {

        int type = BufferedImage.TYPE_INT_ARGB;

        BufferedImage ret = (BufferedImage) img;
        int w, h;
        if (higherQuality) {
            // Use multi-step technique: start with original size, then
            // scale down in multiple passes with drawImage()
            // until the target size is reached
            w = img.getWidth();
            h = img.getHeight();
        } else {
            // Use one-step technique: scale directly from original
            // size to target size with a single drawImage() call
            w = targetWidth;
            h = targetHeight;
        }

        do {
            if (higherQuality && w < targetWidth) {
                w *= 2;
                if (w > targetWidth) {
                    w = targetWidth;
                }
            }

            if (higherQuality && h < targetHeight) {
                h *= 2;
                if (h > targetHeight) {
                    h = targetHeight;
                }
            }

            BufferedImage tmp = new BufferedImage(w, h, type);
            Graphics2D g2 = tmp.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
            g2.drawImage(ret, 0, 0, w, h, null);
            g2.dispose();

            ret = tmp;
            tmp = null;

        } while (w != targetWidth || h != targetHeight);
        return ret;
    }

}