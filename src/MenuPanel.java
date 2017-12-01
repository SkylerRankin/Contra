import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Utilities.Animator;

public class MenuPanel extends JPanel{
	
	private Animator animator;
	private double scale;
	
	public MenuPanel(double scale) {
		this.scale = scale;
		setPreferredSize(new Dimension((int)(250*scale), (int)(240*scale)));
		animator = new Animator("files/menu_anim.txt", "files/menus.png", 250, 240, 0);
		animator.setAnimation("one");
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.scale(scale, scale);
		g2d.drawImage(animator.currentAnimation.getFrame(), 0, 0, null);
	}
	
	public void updateState(int p) {
		if (p == 0) animator.setAnimation("one");
		else animator.setAnimation("two");
	}

}
