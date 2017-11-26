import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Entities.Player;
import Utilities.Boundary;
import Utilities.SpriteSheetManager;

public class Level {

	private BufferedImage background;
	public Player player;
	private Boundary boundary;
	
	public Level() {
		boundary = new Boundary("files/boundary.png");
		background = new SpriteSheetManager().getSprites(3328, 240, "files/bg_plain.png")[0];
		player = new Player(100, 10, 32, 42);
	}
	
	public boolean boundaryCollision(Rectangle r) {
		return boundary.collision(r);
	}
	
	public BufferedImage getBackground() { return this.background; }
	
}
