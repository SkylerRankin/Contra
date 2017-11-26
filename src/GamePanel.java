import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import Entities.Player;
import Utilities.Frame;
import Utilities.SpriteSheetManager;

public class GamePanel extends JPanel{
	
	private Player player;
	private Frame frame;
	private BufferedImage background;
	
	public GamePanel() {
		setPreferredSize(new Dimension(250, 240));
		frame = new Frame(240, 250);
		player = new Player(0, 0, 32, 42);
		background = new SpriteSheetManager().getSprites(3328, 240, "files/bg_plain.png")[0];
	}
	
	public void updatePlayer(Player p) {
		player = p;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		frame.setFocus(player.getX(), player.getY(), 0);
		frame.drawImage(g, background, 0, 0);
		if (!player.isFlipped()) frame.drawImage(g, player.getImage(), player.getX(), player.getY());
		else frame.drawImage(g, player.getImage(), player.getX(), player.getY(), true);
	}
	
}
