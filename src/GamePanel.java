import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import Entities.Enemy;
import Entities.Item;
import Entities.Player;
import Utilities.Frame;
import Utilities.SpriteSheetManager;

public class GamePanel extends JPanel{
	
	private Player[] player;
	private Item[] items;
	private Enemy[] enemies;
	
	private Frame frame;
	private BufferedImage background;
	
	public GamePanel(double scale) {
		setPreferredSize(new Dimension((int)(250*scale), (int)(240*scale)));
		frame = new Frame(240, 250, scale);
		player = new Player(0, 0, 32, 42, false);
		background = new SpriteSheetManager().getSprites(3328, 240, "files/bg_plain.png")[0];
	}
	
	public void updateState(Player p, Item[] i, Enemy[] e) {
		player = p;
		items = i;
		enemies = e;
	}
	
	public void paintComponent(Graphics g) {
		
		//Utilities
		super.paintComponent(g);
		if (player == null || items == null || enemies == null) return;
		frame.setFocus((int)player.getX(), (int)player.getY(), (int)player.getDx());
		frame.initImage();
		//Draw background
		frame.drawImage(g, background, 0, 0);
		
		//Draw player
		frame.drawImage(g, player.getImage(), (int)player.getX(), (int)player.getY(), player.isFlipped());
		
		//Draw items
		for (Item i : items) {
			frame.drawImage(g, i.getImage(), (int)i.getX(), (int)i.getY());
		}
		
		//Draw enemies
		for (Enemy e : enemies) {
			frame.drawImage(g, e.getImage(), (int)e.getX(), (int)e.getY(), e.isFlipped());
		}
		
		frame.draw(g);
			
		
	}
	
}
