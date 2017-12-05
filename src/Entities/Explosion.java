package Entities;

import Utilities.Animator;

public class Explosion extends Item {
	
	private int delay = 10;
	private int temp;
	private int count;
	private int end;

	public Explosion(double x, double y, String type, int end) {
		super(x, y, 32, 32);
		this.end = end;
		animator = new Animator("files/explosion_anim.txt", "files/explosion.png", 32, 32, delay);
		animator.setAnimation(type);
	}
	
	public void count() {
		temp++;
		if (temp == delay) {
			count++;
			temp = 0;
		}
		if (count == end) this.removed = true;
	}
	
}
