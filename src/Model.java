import java.awt.Rectangle;
import java.util.ArrayList;

import Entities.BackpackSoldier;
import Entities.Bullet;
import Entities.Enemy;
import Entities.Entity;
import Entities.Explosion;
import Entities.Item;
import Entities.Player;
import Entities.Turret;

public class Model {

	public int mode = 0;
	public int pos = 0;
	private Level level;
	private Rectangle window;
	private int[] keyData;
	
	public Model() {
		window = new Rectangle(0, 0, 200, 200);
	}
	
	public void processMenu(int[] data) {
		if (pos == 0 && data[3] == 1 && data[1] == 0) pos = 1;
		else if (pos == 1 && data[1] == 1 && data[3] == 0) pos = 0;
		
		if (data[4] == 1) {
			System.out.println("LEVELONE");
			level = new Level(pos);
			mode = 1;
		}
		
	}
	
	public void processItems(int[] data) {
		
		level.processItems(data);
		
	}
	
	public void applyPhysics() {
		level.players[0].updatePosition(keyData, window);
		if (pos == 1) level.players[1].updatePosition(keyData, window);

		level.updateEnemyPositions();
	}
	
	public void processCollisions() {
		
		//Player and ground collisions
		
		level.players[0].setOnGround(false);
		if (pos == 1) level.players[1].setOnGround(false);
		if (level.boundaryCollision(level.players[0].getHitbox())) {
			//System.out.println("collision");
			if (level.players[0].getDy() > 0)
				level.players[0].setOnGround(true);
		}
		
		if (pos == 1) {
			if (level.boundaryCollision(level.players[1].getHitbox())) {
				//System.out.println("collision");
				if (level.players[1].getDy() > 0)
					level.players[1].setOnGround(true);
			}
		}
		
		for (Entity e : level.getEnemies()) {
			e.setOnGround(false);
			if (level.boundaryCollision(e.getHitbox()))
					e.setOnGround(true);
		}
		
		//Enemy and bullet collisions
		
		ArrayList<Item> additions = new ArrayList<Item>();
		
		for (Item bullet : level.getItems()) {
			if (bullet instanceof Bullet && bullet.isHostile()) {
				for (Enemy enemy : level.getEnemies()) {
					if (bullet.getHitbox().intersects(enemy.getHitbox())) {
						if (enemy instanceof BackpackSoldier)
							additions.add(new Explosion(enemy.getX(), enemy.getY(), "red_circle", 2));
						if (enemy instanceof Turret)
							additions.add(new Explosion(enemy.getX(), enemy.getY(), "red_cloud", 2));
						enemy.setDead(true);
						bullet.setRemoved(true);
					}
				}
			}
		}
		
		level.addItems(additions);
		
		//Remove dead enemies
		ArrayList<Enemy> _enemies = new ArrayList<Enemy>();
		for (Enemy e : level.getEnemies())
			if (!e.isDead()) _enemies.add(e);
		level.updateEnemies(_enemies);
		
		//Remove items
		ArrayList<Item> _items = new ArrayList<Item>();
		for (Item i : level.getItems())
			if (!i.isRemoved()) _items.add(i);
		level.updateItems(_items);
		
	}
	
	public void setKeyData(int[] a) {
		this.keyData = a;
		if (mode == 1)
		updatePlayerAnimation();
		updateTimedAnimations();
	}
	
	public void updatePlayerAnimation() {
		level.players[0].updateAnimations(keyData);
		if (pos == 1) level.players[1].updateAnimations(keyData);

	}
	
	public void updateTimedAnimations() {
		if (mode == 0) return;
		for (Item i : level.getItems()) {
			if (i instanceof Explosion)
				((Explosion) i).count();
		}
	}
	
	public Player[] getPlayers() { return level.players; }
	public Item[] getItems() { return level.getItems().toArray(new Item[level.getItems().size()]); }
	public Enemy[] getEnemies() {  return level.getEnemies().toArray(new Enemy[level.getEnemies().size()]);  }
	public int getPos() { return pos; }

}
