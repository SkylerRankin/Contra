import java.awt.Rectangle;

import Entities.Player;

public class Model {

	private Level level;
	private Rectangle window;
	private int[] keyData;
	
	public Model() {
		level = new Level();
		window = new Rectangle(0, 0, 200, 200);
	}
	
	public void applyPhysics() {
		level.player.updatePosition(keyData, window);
	}
	
	public void processCollisions() {
		level.player.setOnGround(false);
		if (level.boundaryCollision(level.player.getHitbox())) {
			System.out.println("collision");
			if (level.player.getDy() > 0)
				level.player.setOnGround(true);
		}
	}
	
	public void setKeyData(int[] a) {
		this.keyData = a;
		updatePlayerAnimation();
	}
	
	public void updatePlayerAnimation() {
		level.player.updateAnimations(keyData);
	}
	
	public Player getPlayer() { return level.player; }

}
