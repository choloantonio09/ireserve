package Capstone.View;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

public class BackgroundImageApp extends JPanel {
static JFrame window;
static JPanel mainPanel;

BufferedImage originalImage;
BufferedImage scaledImage;

BackgroundImageApp() {
    setPreferredSize(new Dimension(320, 200));
    try {
        originalImage = ImageIO.read(new File("images\\food.jpg"));
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

public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run(){
            window = new JFrame("Scale Test");
            window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            mainPanel = new BackgroundImageApp();
            window.getContentPane().add(mainPanel);

            window.pack();
            window.setLocationRelativeTo(null);  // positions window in center of screen
            window.setVisible(true);
        }
    });
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