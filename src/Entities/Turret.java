package Entities;

import java.awt.Point;

import Utilities.Animator;

public class Turret extends Enemy {
	
	private boolean active;
	private double direction;
	private int health = 5;
	private boolean fire;
	private int wait_time = 70;
	private int delay;
	
	public Turret(int x, int y, int w, int h) {
		
		super(x, y, w, h);
		animator = new Animator("files/turret_anim.txt", "files/turret.png", 32, 32, 5);
		animator.setAnimation("idle_0");
		delay = 10;
		fire = false;
		active = false;
	}
	
	public void setTarget(Point p) {
		int _x = p.x - (int) this.x;
		int _y = p.y - (int) this.y;
		
		this.active = false;
		if (Math.sqrt(Math.pow(_x, 2) + Math.pow(_y, 2)) < 300) this.active = true;
		
		double radians = Math.atan2(_y, _x);
		double angle = radians*(180 / Math.PI);
		
		double upper = 30*Math.ceil(angle / 30);
		double lower = 30*Math.floor(angle / 30);
		
		int closest = Math.abs(upper - angle) < Math.abs(lower - angle) ? (int)upper : (int)lower;
		
		double r2 = closest*Math.PI/180;
		//System.out.println("dx="+_x+", dy="+_y+", closest="+closest+", r2="+r2);
		this.direction = r2;
		switch (closest) {
			case 0:
				animator.setAnimation("idle_0");
				break;
			case -30:
				animator.setAnimation("idle_1");
				break;
			case -60:
				animator.setAnimation("idle_2");
				break;
			case -90:
				animator.setAnimation("idle_3");
				break;
			case -120:
				animator.setAnimation("idle_4");
				break;
			case -150:
				animator.setAnimation("idle_5");
				break;
			case -180:
				animator.setAnimation("idle_6");
				break;
		}
		
	}
	
	public Point getShotOffset() {
		
		switch (this.animator.getAnimation()) {
		case "idle_0":
			return new Point(31, 17);
		case "idle_1":
			return new Point(30, 10);
		case "idle_2":
			return new Point(25, 3);
		case "idle_3":
			return new Point(17, 2);
		case "idle_4":
			return new Point(10, 3);
		case "idle_5":
			return new Point(4, 12);
		case "idle_6":
			return new Point(1, 18);
		case "idle_7":
			return new Point(3, 11);
		case "idle_8":
			return new Point(9, 6);
		}
		return new Point(0, 0);
	}
	
	public boolean isActive() { return this.active; }
	public double getDir() { return direction; }
	public boolean getFire() { return fire; }
	public void increment() { 
		if (delay >= wait_time) {
			delay = 0;
			fire = true;
		} else {
			delay++;
			fire = false;
		}
	}

}
