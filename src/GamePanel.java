import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import Entities.Bullet;
import Entities.Enemy;
import Entities.Item;
import Entities.Player;
import Utilities.Frame;
import Utilities.SpriteSheetManager;

public class GamePanel extends JPanel{
	
	private boolean twoplayer;
	private Player[] players;
	private Item[] items;
	private Enemy[] enemies;
	
	private Frame frame;
	private BufferedImage background;
	
	public GamePanel(double scale, int two) {
		setPreferredSize(new Dimension((int)(250*scale), (int)(240*scale)));
		frame = new Frame(240, 250, scale);
		background = new SpriteSheetManager().getSprites(3328, 240, "files/bg_plain.png")[0];
		twoplayer = two == 0 ? false : true;
		System.out.println("Two Player = " + twoplayer);
	}
	
	public void updateState(Player[] p, Item[] i, Enemy[] e) {
		players = p;
		items = i;
		enemies = e;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (players == null || items == null || enemies == null) return;
		frame.setFocus((int)players[0].getX(), (int)players[0].getY(), (int)players[0].getDx());
		frame.initImage();
		//Draw background
		frame.drawImage(g, background, 0, 0);
		
		//Draw player
		frame.drawImage(g, players[0].getImage(), (int)players[0].getX(), (int)players[0].getY(), players[0].isFlipped());
		if (twoplayer) frame.drawImage(g, players[1].getImage(), (int)players[1].getX(), (int)players[1].getY(), players[1].isFlipped());
		
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
