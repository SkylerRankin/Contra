import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Entities.BackpackSoldier;
import Entities.Bullet;
import Entities.Enemy;
import Entities.Entity;
import Entities.Item;
import Entities.Player;
import Utilities.Boundary;
import Utilities.SpriteSheetManager;

public class Level {

	private BufferedImage background;
	public Player player;
	private Boundary boundary;
	private ArrayList<Item> items;
	private ArrayList<Enemy> enemies;
	
	public Level() {
		boundary = new Boundary("files/boundary.png");
		background = new SpriteSheetManager().getSprites(3328, 240, "files/bg_plain.png")[0];
		player = new Player(100, 10, 32, 42);
		items = new ArrayList<Item>();
		enemies = new ArrayList<Enemy>();
		enemies.add(new BackpackSoldier(100, 20, 32, 32, 0.5));
	}
	
	public boolean boundaryCollision(Rectangle r) {
		return boundary.collision(r);
	}
	
	public void processItems(int[] data) {
		if (data[5] == 1 && !player.hasShot()) {
			player.setShot(true);
			System.out.println("fire");
			
			int ox = -15;
			int oy = -15;
			
			items.add(new Bullet(player.getX() - ox, player.getY() - oy, player.getDir(), true));
		}
		
		if (data[5] == 0)
			player.setShot(false);
		
		for (Entity e : items) {
			if (e instanceof Bullet) {
				((Bullet) e).updatePosition();
			}
		}
		
	}
	
	public void updateEnemyPositions() {
		for (Entity _e : enemies) {
			((BackpackSoldier) _e).updatePosition();
		}
	}
	
	public BufferedImage getBackground() { return this.background; }
	public ArrayList<Item> getItems() { return items; }
	public ArrayList<Enemy> getEnemies() { return enemies; }
	public void updateEnemies(ArrayList<Enemy> e) { this.enemies = e; }
	public void updateItems(ArrayList<Item> i) { this.items = i; }
	public void addItem(Item i) { this.items.add(i); }
	public void addItems(ArrayList<Item> i) { this.items.addAll(i); }
}
