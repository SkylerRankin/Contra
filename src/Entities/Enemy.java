package Entities;

public class Enemy extends Entity {

	private boolean dead;
	
	public Enemy(double x, double y, int w, int h) {
		super(x, y, w, h);
	}
	
	public boolean isDead() { return dead; }
	public void setDead(boolean a) { dead = a; }
	
}
