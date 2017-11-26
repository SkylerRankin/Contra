package Utilities;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Boundary {
	
	private int[][] pixels;
	
	public Boundary(String path) {
		pixels = readImage(path);
	}
	
	public boolean collision(Rectangle r) {
		if (r.x < 0 || r.y < 0 || r.x+r.width > pixels.length || r.y+r.height > pixels[0].length) return false;
		for (int i=r.x; i<r.x+r.width; ++i)
			for (int j=r.y; j<r.y+r.height; ++j)
				if (pixels[i][j] == 1)
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
					pixels[i][j] = Math.abs(img.getRGB(i, j)) < 1000 ? 0 : 1;
			}
		return pixels;
	}

}
