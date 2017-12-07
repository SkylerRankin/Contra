package Entities;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Utilities.Animator;

public abstract class Entity {
	
	protected Animator animator;
	protected double x = 0;
	protected double y = 0;
	protected double prevX;
	protected double prevY;
	protected double dx;
	protected double dy;
	protected boolean onGround = false;
	protected boolean flipped = false;
	protected int width;
	protected int height;
	
	public Entity(double x, double y, int w, int h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
	}
	
	public double getX() { return this.x; }
	public double getY() { return this.y; }
	public double getDx() { return this.dx; }
	public double getDy() { return this.dy; }
	public void setOnGround(boolean a) { this.onGround = a; }
	public boolean isFlipped() { return flipped; }
	
	public Rectangle getHitbox() {
		return new Rectangle((int)x, (int)y, width, height);
	}
	
	public BufferedImage getImage() {
		if (animator != null) return animator.currentAnimation.getFrame();
		return null;
	}

}
