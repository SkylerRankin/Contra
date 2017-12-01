package Entities;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Entities.Entity;
import Utilities.Animator;

public class Player extends Entity {
	
	private boolean shot = false;
	
	public Player(double x, double y, int w, int h) {
		super(x, y, w, h);
		this.animator = new Animator("files/player_anim.txt", "files/player.png", 32, 42, 10);
		this.animator.setAnimation("idle");
	}
	
	public void updateAnimations(int[] data) {
		
		//Straight forward positions
		
		if (data[0] == 1) {
			flipped = true;
			if (!animator.getAnimation().equals("run"))
				animator.setAnimation("run");
		} else if (data[4] == 1) {
			if (!animator.getAnimation().equals("jump")) {
				animator.setAnimation("jump");
			}
		} else if (data[2] == 1) {
			flipped = false;
			if (!animator.getAnimation().equals("run"))
				animator.setAnimation("run");
		} else if (data[3] == 1) {
			if (!animator.getAnimation().equals("down"))
				animator.setAnimation("down");
		}
		
		//Angled aiming
		
		if (data[0]+data[1]==2) {
			flipped = true;
			if (!animator.getAnimation().equals("up_angle"))
				animator.setAnimation("up_angle");
		} else if (data[2]+data[1]==2) {
			if (!animator.getAnimation().equals("up_angle"))
				animator.setAnimation("up_angle");
		} else if (data[0]+data[3]==2) {
			flipped = true;
			if (!animator.getAnimation().equals("down_angle"))
				animator.setAnimation("down_angle");
		} else if (data[2]+data[3]==2) {
			if (!animator.getAnimation().equals("down_angle"))
				animator.setAnimation("down_angle");
		}
		
		if (data[0]+data[1]+data[2]+data[3] == 0)
			animator.setAnimation("idle");
		
		if (data[4]==1)
			animator.setAnimation("jump");
		
		if (!onGround && !animator.getAnimation().equals("jump"))
			animator.setAnimation("jump");
		
	}
	
	public void updatePosition(int[] data, Rectangle _w) {
		
		if (data[0] == 1) dx = -1;
		if (data[2] == 1) dx = 1;
		if (data[4] == 1 && onGround) dy = -3;
		if (data[0]+data[2]==0) dx = 0;
		
		prevX = x;
		prevY = y;
		
		if (!onGround) {
			dy += 0.1;
			y += dy;
		}
		
		x += dx;
		
	}
	
	public boolean hasShot() { return shot; }
	public void setShot(boolean a) { shot = a; }
	public boolean isFlipped() { return this.flipped; }
	public boolean onGround() { return onGround; }
	public void setOnGround(boolean b) { onGround = b; }
	public double getDy() { return dy; }
	public int getDir() {
		switch(this.animator.getAnimation()) {
		case "idle":
		case "idle_shot":
			if (flipped) return 4;
			else return 0;
		case "up_angle":
			if (flipped) return 3;
			else return 1;
		case "up":
			return 2;
		case "down_angle":
			if (flipped) return 5;
			else return 7;
		case "down":
			return 6;
		}
		return 0;
	}

}
