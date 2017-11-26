import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import Utilities.InputManager;

//import utilities.InputManager;

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
        	model.applyPhysics();
        	model.processCollisions();
        	view.setGameState(model.getPlayer());
        	view.refresh();
            
        }
    }
    
}