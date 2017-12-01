package Entities;

import java.awt.Rectangle;

import Utilities.Animator;

public class BackpackSoldier extends Enemy{
	
	public BackpackSoldier(double x, double y, int w, int h, double dx) {
		super(x, y, w, h);
		animator = new Animator("files/enemy1_anim.txt", "files/jumpenemy.png", 32, 32, 10);
		animator.setAnimation("run");
		this.dx = dx;
		this.flipped = (this.dx < 0) ? false : true;
	}
	
	public void updatePosition() {
		
		if (!onGround) {
			dy += 0.1;
			y += dy;
		}
		
		x += dx;
		
	}

}
