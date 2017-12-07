package Entities;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Entities.Entity;
import Utilities.Animator;

public class Player extends Entity {
	
	private boolean[] hit_data = new boolean[]{false, false, false};
	// 0=player is hit, 1=knockback, 2=
	
	private boolean hit = false;
	private boolean knockback = false;
	
	private int health = 3;
	private boolean player2;
	private boolean shot = false;
	private boolean falling;
	private double jump_speed = 3;
	private double speed = 1;
	private double gravity = 0.1;
	
	public Player(double x, double y, int w, int h, boolean player2) {
		super(x, y, w, h);
		this.player2 = player2;
		if (player2) this.animator = new Animator("files/player_anim.txt", "files/blue_player.png", 32, 42, 10);
		else this.animator = new Animator("files/player_anim.txt", "files/red_player.png", 32, 42, 10);
		this.animator.setAnimation("idle");
	}	
	
	 public void updateAnimations(int[] data) {
		 int i = player2 ? 6 : 0;
		 
		 if (hit_data[0]) {
			 if  (!animator.getAnimation().equals("hit"))
					 animator.setAnimation("hit");
			 return;
		 }
		 
		//Straight forward positions
		
		if (data[i] == 1) {
			flipped = true;
			if (!animator.getAnimation().equals("run"))
				animator.setAnimation("run");
		} else if (data[4+i] == 1) {
			if (!animator.getAnimation().equals("jump")) {
				animator.setAnimation("jump");
			}
		} else if (data[2+i] == 1) {
			flipped = false;
			if (!animator.getAnimation().equals("run"))
				animator.setAnimation("run");
		} else if (data[3+i] == 1) {
			if (!animator.getAnimation().equals("down"))
				animator.setAnimation("down");
		} else if (data[1+i] == 1) {
			if (!animator.getAnimation().equals("idle_up"))
				animator.setAnimation("idle_up");
		}
		
		//Angled aiming
		
		if (data[i]+data[1+i] == 2) {
			flipped = true;
			if (!animator.getAnimation().equals("up_angle"))
				animator.setAnimation("up_angle");
		} else if (data[2+i]+data[1+i]==2) {
			if (!animator.getAnimation().equals("up_angle"))
				animator.setAnimation("up_angle");
		} else if (data[i]+data[3+i]==2) {
			flipped = true;
			if (!animator.getAnimation().equals("down_angle"))
				animator.setAnimation("down_angle");
		} else if (data[2+i]+data[3+i]==2) {
			if (!animator.getAnimation().equals("down_angle"))
				animator.setAnimation("down_angle");
		}
		
		if (data[i]+data[1+i]+data[2+i]+data[3+i] == 0)
			animator.setAnimation("idle");
		
		if (data[4+i] ==1)
			animator.setAnimation("jump");
		
		if (!onGround && !animator.getAnimation().equals("jump"))
			animator.setAnimation("jump");
		
	}
	
	public void updatePosition(int[] data, Rectangle _w) {
		
		if (hit_data[0]) {
			if (hit_data[2]) { dy = -jump_speed; hit_data[2] = false; }
			if (hit_data[1]) dx = speed/2*(this.flipped ? 1 : -1);
			prevX = x;
			prevY = y;
			if (!onGround) { dy += gravity; y += dy; }
			if (onGround) { hit_data[0] = false; hit_data[1] = false; hit_data[2] = false;}
			x += dx;
			return;
		}
		
		if ((player2 ? data[9] + data[10] : data[3] + data[4]) == 2) falling = true;
		if ((player2 ? data[6] : data[0]) == 1) dx = -speed;
		if ((player2 ? data[8] : data[2]) == 1) dx = speed;
		if ((player2 ? data[10] : data[4]) == 1 && onGround && !falling) dy = -jump_speed;
		if ((player2 ? data[6] + data[8] : data[0] + data[2])==0) dx = 0;
		
		prevX = x;
		prevY = y;
		
		if (!onGround) {
			dy += gravity;
			y += dy;
		}
		
		x += dx;
		
	}
	
	public boolean isHit() { return hit_data[0]; }
	public void hit() { 
		this.hit_data[0] = true;
		this.hit_data[1] = true;
		this.hit_data[2] = true;
		this.health--;
		System.out.println(this.health);
		if (!animator.getAnimation().equals("hit"))
			 animator.setAnimation("hit");
	}
	public Rectangle getGroundHitbox() { return new Rectangle((int)x, (int)y + this.height - 1, this.width, 1); }
	public boolean isFalling() { return falling; }
	public void setFalling(boolean f) { falling = f; }
	public boolean hasShot() { return shot; }
	public void setShot(boolean a) { shot = a; }
	public boolean isFlipped() { return this.flipped; }
	public boolean onGround() { return onGround; }
	public void setOnGround(boolean b) { onGround = b; }
	public double getDy() { return dy; }
	public double getDir() {
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
