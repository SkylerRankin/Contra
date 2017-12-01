package Entities;

import Utilities.Animator;

public class Explosion extends Item {

	public Explosion(double x, double y, String type) {
		super(x, y, 32, 32);
		animator = new Animator("files/explosion_anim.txt", "files/explosion.png", 32, 32, 5);
		animator.setAnimation(type);
	}
	
}
