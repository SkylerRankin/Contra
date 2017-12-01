package Entities;

public class Item extends Entity {

	protected boolean hostile;
	protected boolean removed = false;
	
	public Item(double x, double y, int w, int h) {
		super(x, y, w, h);
	}
	
	public boolean isRemoved() { return removed; }
	public void setRemoved(boolean r) { removed = r; }
	public boolean isHostile() { return hostile; }
}
