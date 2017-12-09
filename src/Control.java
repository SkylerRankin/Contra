import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import Utilities.InputManager;

public class Control {
    
    private Model model;
    private View view;
    private Timer timer;
    private InputManager im;
    
    public Control(Model m, View v) {
        model = m;
        view = v;
        im = new InputManager();
        timer = new Timer(10, new TimerListener());
        view.addKeyListener(im.getKeyListener());
        timer.start();
    }
    
    public class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
        	model.setKeyData(im.getKeyData());
        	if (model.mode == 0) {
        		model.processMenu(im.getKeyData());
        		view.setGameState(model.getPos());
        		view.refresh(model.mode, model.pos);
        	} else if (model.mode == 1) {
        		if (model.checkDeath()) {
        			model.applyPhysics();
                	model.processCollisions();
                	view.setGameState(model.getPlayers(), model.getItems(), model.getEnemies());
                	view.refresh(model.mode, model.pos);
                	if (model.game_over) {
                		model.mode = 0;
                		model.restart();
                		System.out.println("game over");
                	}
        		} else {
            		model.processItems(im.getKeyData());
                	model.applyPhysics();
                	model.processCollisions();
                	view.setGameState(model.getPlayers(), model.getItems(), model.getEnemies());
                	view.refresh(model.mode, model.pos);
        		}
        	}	
        }
    }
    
}