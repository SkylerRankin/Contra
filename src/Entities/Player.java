package Entities;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Entities.Entity;
import Utilities.Animator;

public class Player extends Entity {
	
	private boolean player2;
	private boolean shot = false;
	
	public Player(double x, double y, int w, int h, boolean player2) {
		super(x, y, w, h);
		this.player2 = player2;
		if (player2) this.animator = new Animator("files/player_anim.txt", "files/red_player.png", 32, 42, 10);
		else this.animator = new Animator("files/player_anim.txt", "files/blue_player.png", 32, 42, 10);
		this.animator.setAnimation("idle");
	}
	
	 public void updateAnimations(int[] data) {
		
		//Straight forward positions
		
		if ((player2 ? data[6] : data[0]) == 1) {
			flipped = true;
			if (!animator.getAnimation().equals("run"))
				animator.setAnimation("run");
		} else if ((player2 ? data[10] : data[4]) == 1) {
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
		} else if (data[1] == 1) {
			if (!animator.getAnimation().equals("idle_up"))
				animator.setAnimation("idle_up");
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
	public double getDir() {
		System.out.println(this.animator.getAnimation() + ", " + this.flipped);
		switch(this.animator.getAnimation()) {
		
		case "down":
		case "jump":
		case "idle":
		case "run":
		case "idle_shot":
			if (flipped) return Math.toRadians(-180);
			else return 0;
		case "idle_up":
			return Math.toRadians(-90);
		case "up_angle":
			if (flipped) return Math.toRadians(-135);
			else return Math.toRadians(-45);
		case "down_angle":
			if (flipped) return Math.toRadians(-225);
			else return Math.toRadians(-315);
		}
		return 0;
	}
	public Point getShotOffset() {
		
		switch (this.animator.getAnimation()) {
		case "idle":
			if (flipped) return new Point(-3, -18);
			return new Point(-27, -18);
		case "down":
			if (flipped) return new Point(0, -32);
			return new Point(-32, -32);
		case "up_angle":
			if (flipped) return new Point(-6, -8);
			return new Point(-25, -8);
		case "down_angle":
			if (flipped) return new Point(-4, -26);
			return new Point(-28, -26);
		case "idle_up":
			if (flipped) return new Point(-12, 0);
			return new Point(-19, 0);
		}
		
		
		return new Point(-15, -15);
	}

}
