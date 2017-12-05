package Utilities;

import java.awt.Point;

public class Angles {
	
	public static int getAngle(Point p1, Point p2) {
		//angle where p1 is the center, p2 is somewhere around p1
		int dx = p1.x > p2.x ? 1 : -1;
		int dy = p1.y > p2.y ? 1 : -1;
		System.out.println("dx="+dx+", dy="+dy);
		double a = Math.atan(Math.abs(p2.y - p1.y) / Math.abs(p2.x - p1.x));
		System.out.println(a);
		//if (dx == -1) a+=Math.PI/2;
		//if (dy == -1) a+=Math.PI/2;
		
		return (int) (a * 180 / Math.PI);
		
	}

}
