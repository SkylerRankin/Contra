package Entities;

import Utilities.Animator;

public class Bullet extends Item {
	
	private double angle;
	private boolean fromPlayer;
	
	public Bullet(double x, double y, double angle, boolean from) {
		
		super(x, y, 2, 2);
		this.angle = angle;
		dx = 2;
		dy = 2;
		animator = new Animator("files/item_anim.txt", "files/bullet.png", 3, 3, 1);
		animator.setAnimation("bullet");
		this.hostile = from;
	}
	
	public boolean fromPlayer() { return fromPlayer; }
	
	public void updatePosition() {
		
		x+=dx*Math.cos(angle);
		y+=dy*Math.sin(angle);
		/*
		switch (dir) {
			case 0:
				x+=dx;
				break;
			case 1: 
				x+=dx;
				y-=dy;
				break;
			case 2:
				y-=dy;
				break;
			case 3:
				x-=dx;
				y-=dy;
				break;
			case 4:
				x-=dx;
				break;
			case 5:
				x-=dx;
				y+=dy;
				break;
			case 6:
				y+=dy;
				break;
			case 7:
				y+=dy;
				x+=dx;
				break;
		}*/
	}

	
}
