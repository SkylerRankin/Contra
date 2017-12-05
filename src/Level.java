import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Entities.BackpackSoldier;
import Entities.Bullet;
import Entities.Enemy;
import Entities.Entity;
import Entities.Item;
import Entities.Player;
import Entities.Turret;
import Utilities.Boundary;
import Utilities.SpriteSheetManager;

public class Level {

	private BufferedImage background;
	public Player player;
	public Player player2;
	private Boundary boundary;
	private ArrayList<Item> items;
	private ArrayList<Enemy> enemies;
	
	public Level(int pos) {
		System.out.println(pos);
		boundary = new Boundary("files/boundary.png");
		background = new SpriteSheetManager().getSprites(3328, 240, "files/bg_plain.png")[0];
		player = new Player(100, 10, 32, 42, false);
		if (pos == 1) player2 = new Player(100, 10, 32, 42, true);
		items = new ArrayList<Item>();
		enemies = new ArrayList<Enemy>();
		enemies.add(new BackpackSoldier(100, 20, 32, 32, 0.5));
		enemies.add(new Turret(200, 180, 32, 32));
	}
	
	public boolean boundaryCollision(Rectangle r) {
		return boundary.collision(r);
	}
	
	public void processItems(int[] data) {
		if (data[5] == 1 && !player.hasShot()) {
			player.setShot(true);
			items.add(new Bullet(player.getX() - player.getShotOffset().x, player.getY() - player.getShotOffset().y, player.getDir(), true));
		}
		
		if (data[5] == 0)
			player.setShot(false);
		
		for (Enemy e : enemies)
			if (e instanceof Turret) {
				Turret t = (Turret) e;
				if (((Turret) e).getFire() && ((Turret) e).isActive()) {
					items.add(new Bullet(e.getX() + t.getShotOffset().x, e.getY() + t.getShotOffset().y, ((Turret) e).getDir(), false));
					((Turret) e).increment();
				} else {
					((Turret) e).increment();
				}
			}
		
		for (Entity e : items) {
			if (e instanceof Bullet) {
				((Bullet) e).updatePosition();
			}
		}
	}
	
	public void updateEnemyPositions() {
		for (Entity _e : enemies) {
			if (_e instanceof BackpackSoldier)
				((BackpackSoldier) _e).updatePosition();
			if (_e instanceof Turret) {
				((Turret) _e).setTarget(new Point((int)player.getX(), (int)player.getY()));
			}
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
