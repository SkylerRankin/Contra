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

	private boolean twoplayer;
	private BufferedImage background;
	public Player[] players;
	private Boundary boundary;
	private ArrayList<Item> items;
	private ArrayList<Enemy> enemies;
	
	public Level(int pos) {
		boundary = new Boundary("files/boundary.png");
		background = new SpriteSheetManager().getSprites(3328, 240, "files/bg_plain.png")[0];
		if (pos == 0) {
			twoplayer = false;
			players = new Player[1];
			players[0] = new Player(100, 10, 32, 42, false);
		} else {
			twoplayer = true;
			players = new Player[2];
			players[0] = new Player(100, 10, 32, 42, false);
			players[1] = new Player(120, 10, 32, 42, true);
		}
		items = new ArrayList<Item>();
		enemies = new ArrayList<Enemy>();
		enemies.add(new BackpackSoldier(100, 20, 32, 32, 0.5));
		enemies.add(new Turret(200, 180, 32, 32));
		enemies.add(new Turret(250, 180, 32, 32));
		enemies.add(new Turret(300, 180, 32, 32));
		enemies.add(new Turret(310, 50, 32, 32));
		enemies.add(new Turret(310, 100, 32, 32));
		enemies.add(new Turret(440, 180, 32, 32));
	}
	
	public boolean boundaryCollision(Rectangle r) {
		return boundary.collision(r);
	}
	
	public void processItems(int[] data) {
		if (data[5] == 1 && !players[0].hasShot()) {
			players[0].setShot(true);
			items.add(new Bullet(players[0].getX() - players[0].getShotOffset().x, players[0].getY() - players[0].getShotOffset().y, players[0].getDir(), true));
		}
		
		if (twoplayer && data[11] == 1 && !players[1].hasShot()) {
			System.out.println("shot");
			players[1].setShot(true);
			items.add(new Bullet(players[1].getX() - players[1].getShotOffset().x, players[1].getY() - players[1].getShotOffset().y, players[1].getDir(), true));
		}
		
		if (data[5] == 0) players[0].setShot(false);
		if (twoplayer && data[11] == 0) players[1].setShot(false);
		
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
				((Turret) _e).setTarget(new Point((int)players[0].getX(), (int)players[0].getY()));
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
