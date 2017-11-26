import java.awt.Graphics;
import javax.swing.JFrame;

import Entities.Player;
import Utilities.Frame;

public class View extends JFrame{
	
	private Frame frame;
	private Player player;
	private GamePanel p;
	
	public View() {
		super("Contra");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		p = new GamePanel();
		add(p);
		pack();
		
	}
	
	public void setGameState(Player player) {
		p.updatePlayer(player);
	}
	
	public void refresh() {
		p.repaint();
	}
	
}
