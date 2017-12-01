import javax.swing.JFrame;

import Entities.Enemy;
import Entities.Entity;
import Entities.Item;
import Entities.Player;

public class View extends JFrame{
	
	private GamePanel gp;
	private MenuPanel mp;
	
	private String currPanel = "menu";
	
	public View() {
		super("Contra");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		gp = new GamePanel(3);
		mp = new MenuPanel(3);
		add(mp);
		pack();
	}
	
	public void setGameState(Player player, Item[] items, Enemy[] enemies) {
		gp.updateState(player, items, enemies);
	}
	
	public void setGameState(int p) {
		mp.updateState(p);
	}
	
	public void refresh(int mode) {
		if (mode == 1 && currPanel.equals("menu")) {
			currPanel.equals("game");
			this.remove(mp);
			this.add(gp);
			pack();
		}
		
		if (mode == 0) mp.repaint();
		else if (mode == 1) gp.repaint();
	}
	
}
