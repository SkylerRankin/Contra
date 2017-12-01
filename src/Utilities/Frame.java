package Utilities;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Frame {
    
    Point absoluteFocus;
    Point prevAbsoluteFocus;
    
    int dx;
    int offset;
    int width;
    int height;
    private double scale;
    private BufferedImage image;  
    
    public Frame(int _width, int _height, double scale) {
    	this.scale = scale;
        width = _width;
        height = _height;
        absoluteFocus = new Point(_width/2, _height/2);
        prevAbsoluteFocus = new Point(_width/2, _height/2);
        offset = width/2;
    }
    
    public void setFocus(int x, int y, int _dx) {
        if (_dx >= 0) {
            //player moved right, follow player
            if (x > absoluteFocus.x) {
            	//System.out.println("x>absfoc.x");
                if (x + offset <= 3328) { //do not shift if at the end of the level
                    prevAbsoluteFocus = absoluteFocus;
                    absoluteFocus = new Point(x-1, y); //the minus 1 removes that weird jitter
                }
            }
            else absoluteFocus = prevAbsoluteFocus;
            
        } else if (_dx < 0) {
            //player moved left, do not follow player
            absoluteFocus = prevAbsoluteFocus;
        }
        dx = _dx;
    }
    
    public void printFocus() {
        System.out.println(absoluteFocus.x+", "+absoluteFocus.y);
    }
    
    public void drawImage(Graphics g, BufferedImage bf, int _x, int _y) {
    	
    	Graphics2D g2d = image.createGraphics();
    	g2d.scale(scale, scale);
    	g2d.drawImage(bf, _x - absoluteFocus.x + offset, _y, null);
    	//g2d.drawImage(bf, _x, _y, bf.getWidth(), bf.getHeight(), null);
    	
    	/*
    	Graphics2D g2d = (Graphics2D) g;
        g2d.scale(scale, scale);
        g2d.drawImage(bf, _x - absoluteFocus.x + offset, _y, null);
        //g2d.drawImage(bf, _x - absoluteFocus.x + offset, _y, null);
    	//g.drawImage(bf, _x - absoluteFocus.x + offset, _y, null);
    	 
    	 */
    }
    
    public void drawImage(Graphics g, BufferedImage bf, int _x, int _y, boolean flip) {
    	if (flip) {
        	int x = _x - absoluteFocus.x + offset;
        	Graphics2D g2d = image.createGraphics();
        	g2d.scale(scale, scale);
        	g2d.drawImage(bf, x + bf.getWidth(), _y, -bf.getWidth(), bf.getHeight(), null);
        	//g2d.drawImage(bf, _x, _y, bf.getWidth(), bf.getHeight(), null);
        	
        	/*
            int x = _x - absoluteFocus.x + offset;
            Graphics2D g2d = (Graphics2D) g;
            g2d.scale(scale, scale);
            
            //g.drawImage(bf, x + bf.getWidth(), _y, -bf.getWidth(), bf.getHeight(), null);
             */
    	} else {
        	Graphics2D g2d = image.createGraphics();
        	g2d.scale(scale, scale);
        	g2d.drawImage(bf, _x - absoluteFocus.x + offset, _y, null);
        	//g2d.drawImage(bf, _x, _y, bf.getWidth(), bf.getHeight(), null);
        	
        	/*
        	Graphics2D g2d = (Graphics2D) g;
            g2d.scale(scale, scale);
            g2d.drawImage(bf, _x - absoluteFocus.x + offset, _y, null);
            //g2d.drawImage(bf, _x - absoluteFocus.x + offset, _y, null);
        	//g.drawImage(bf, _x - absoluteFocus.x + offset, _y, null);
        	 
        	 */
    	}

    }
    
    public void initImage() {
    	image = new BufferedImage((int)(250*scale), (int)(240*scale), BufferedImage.TYPE_INT_RGB);
    }
    
    public void draw(Graphics g) {
    	
    	g.drawImage(image, 0, 0, null);
    	
    }
    
}