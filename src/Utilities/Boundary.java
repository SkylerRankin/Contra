package Utilities;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Boundary {
	
	//0 = black, 1 = white, 2 = red, 3 = green
	
	private int[][] pixels;
	
	public Boundary(String path) {
		pixels = readImage(path);
	}
	
	public boolean collision(Rectangle r, int a) {
		if (r.x < 0 || r.y < 0 || r.x+r.width > pixels.length || r.y+r.height > pixels[0].length) return false;
		for (int i=r.x; i<r.x+r.width; ++i)
			for (int j=r.y; j<r.y+r.height; ++j)
				if (pixels[i][j] == a)
					return true;
		return false;
	}
	
	public boolean collision(Rectangle r, int a, int b) {
		if (r.x < 0 || r.y < 0 || r.x+r.width > pixels.length || r.y+r.height > pixels[0].length) return false;
		for (int i=r.x; i<r.x+r.width; ++i)
			for (int j=r.y; j<r.y+r.height; ++j)
				if (pixels[i][j] == a || pixels[i][j] == b)
					return true;
		return false;
	}
	
	private int[][] readImage(String path) {
		File f = new File(path);
		BufferedImage img = null;
		try {
			img = ImageIO.read(f);
		} catch (IOException e) {
			System.out.println(e);
		}
		if (img == null) { System.out.println("No image found at "+path);return null; }
		int[][] pixels = new int[img.getWidth()][img.getHeight()];
		for (int i=0; i<img.getWidth(); ++i)
			for (int j=0; j<img.getHeight(); ++j) {
					Color c = new Color(img.getRGB(i, j));
					if (c.getRed() >100 && c.getGreen() <200 && c.getBlue() <200) pixels[i][j] = 2;
					else if (c.getRed() < 200 && c.getGreen() > 100 && c.getBlue() < 200) pixels[i][j] = 3;
					else if (c.getRed() <100 && c.getGreen() <100 && c.getBlue() <100) pixels[i][j] = 0;
					else pixels[i][j] = 1;
			}
		return pixels;
	}

}
