package Utilities;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputManager{
    //left up right down Z X -> 0 1 2 3 4 5
    private int[] keys = new int[6];
    
    public KeyPressListener getKeyListener() { return new KeyPressListener(); }
    public int[] getKeyData() { return keys; }
    
    public class KeyPressListener implements KeyListener {
        public void keyTyped(KeyEvent ke) {}
        public void keyPressed(KeyEvent ke) {
            int key = ke.getKeyCode();
            if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
                keys[2] = 1;
            } else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
                keys[0] = 1;
            } else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
                keys[1] = 1;
            } else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
                keys[3] = 1;
            }  else if (key == KeyEvent.VK_Z) {
                keys[4] = 1;
            } else if (key == KeyEvent.VK_X) {
            	keys[5] = 1;
            }
        }
        public void keyReleased(KeyEvent ke) {
            int key = ke.getKeyCode();
            if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
                keys[2] = 0;
            } else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
                keys[0] = 0;
            } else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
                keys[1] = 0;
            } else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
                keys[3] = 0;
            } else if (key == KeyEvent.VK_Z) {
                keys[4] = 0;
            } else if (key == KeyEvent.VK_X) {
            	keys[5] = 0;
            }
        }
    }
}