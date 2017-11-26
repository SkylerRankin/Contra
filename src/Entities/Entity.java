package Entities;

import java.awt.Rectangle;

import Utilities.Animator;

public abstract class Entity {
	
	protected Animator animator;
	protected int x = 0;
	protected int y = 0;
	protected int prevX;
	protected int prevY;
	protected double dx;
	protected double dy;
	protected boolean flipped = false;
	private int width;
	private int height;
	
	public Entity(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
	}
	
	public Rectangle getHitbox() {
		return new Rectangle(x, y, width, height);
	}

}
