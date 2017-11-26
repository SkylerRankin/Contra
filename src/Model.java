import java.awt.Rectangle;

import Entities.Player;

public class Model {

	private Rectangle window;
	private Player player;
	private int[] keyData;
	
	public Model() {
		
		player = new Player(0, 0, 32, 42);
		window = new Rectangle(0, 0, 200, 200);
	}
	
	public void applyPhysics() {
		player.updatePosition(keyData, window);
	}
	
	public void processCollisions() {
		
	}
	
	public void setKeyData(int[] a) {
		this.keyData = a;
		updatePlayerAnimation();
	}
	
	public void updatePlayerAnimation() {
		player.updateAnimations(this.keyData);
	}
	
	public Player getPlayer() { return this.player; }

}
