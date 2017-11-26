import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import Entities.Player;
import Utilities.Frame;

public class GamePanel extends JPanel{
	
	private Player player;
	private Frame frame;
	private BufferedImage background;
	
	public GamePanel() {
		setPreferredSize(new Dimension(200, 200));
		frame = new Frame(200, 200, 0, 0);
		player = new Player(0, 0, 32, 42);
	}
	
	public void updatePlayer(Player p) {
		player = p;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (!player.isFlipped()) frame.drawImage(g, player.getImage(), player.getX(), player.getY());
		else frame.drawImage(g, player.getImage(), player.getX(), player.getY(), true);
	}
	
}
