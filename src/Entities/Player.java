package Entities;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Entities.Entity;
import Utilities.Animator;

public class Player extends Entity {
	
	public Player(int x, int y, int w, int h) {
		super(x, y, w, h);
		this.animator = new Animator("files/player_anim.txt", "files/contra_player.png", 32, 42, 10);
		this.animator.setAnimation("idle");
	}
	
	public void updateAnimations(int[] data) {
		
		if (data[0] == 1) {
			flipped = true;
			if (!animator.getAnimation().equals("run"))
				animator.setAnimation("run");
			
		} else if (data[1] == 1) {
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
		
		if (data[0]+data[1]+data[2]+data[3] == 0)
			animator.setAnimation("idle");
		
	}
	
	public void updatePosition(int[] data, Rectangle _w) {
		
		if (data[0] == 1) dx = -3;
		if (data[2] == 1) dx = 3;
		if (data[0]+data[2]==0) dx = 0;
		
		prevX = x;
		prevY = y;
		
		x += dx;
		y += dy;
		
	}
	
	public BufferedImage getImage() {
		return this.animator.currentAnimation.getFrame();
	}
	
	public int getX() { return this.x; }
	public int getY() { return this.y; }
	public boolean isFlipped() { return this.flipped; }

}
