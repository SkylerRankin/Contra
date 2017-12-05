package Utilities;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputManager{
    //left up right down . / a w d s 1 2 -> 0 1 2 3 4 5 6 7 8 9 10 11
	//0    1  2     3    4 5 6 7 8 9 1011
    private int[] keys = new int[12];
    
    public KeyPressListener getKeyListener() { return new KeyPressListener(); }
    public int[] getKeyData() { return keys; }
    
    public class KeyPressListener implements KeyListener {
        public void keyTyped(KeyEvent ke) {}
        public void keyPressed(KeyEvent ke) {
            int key = ke.getKeyCode();
            if (key == KeyEvent.VK_RIGHT) {
                keys[2] = 1;
            } else if (key == KeyEvent.VK_LEFT) {
                keys[0] = 1;
            } else if (key == KeyEvent.VK_UP) {
                keys[1] = 1;
            } else if (key == KeyEvent.VK_DOWN) {
                keys[3] = 1;
            }  else if (key == KeyEvent.VK_PERIOD) {
                keys[4] = 1;
            } else if (key == KeyEvent.VK_SLASH) {
            	keys[5] = 1;
            } else if (key == KeyEvent.VK_A) {
                keys[6] = 1;
            } else if (key == KeyEvent.VK_W) {
                keys[7] = 1;
            } else if (key == KeyEvent.VK_D) {
                keys[8] = 1;
            } else if (key == KeyEvent.VK_S) {
                keys[9] = 1;
            } else if (key == KeyEvent.VK_1) {
            	keys[10] = 1;
            } else if (key == KeyEvent.VK_2) {
            	keys[11] = 1;
            }
        }
        public void keyReleased(KeyEvent ke) {
            int key = ke.getKeyCode();
            if (key == KeyEvent.VK_RIGHT) {
                keys[2] = 0;
            } else if (key == KeyEvent.VK_LEFT) {
                keys[0] = 0;
            } else if (key == KeyEvent.VK_UP) {
                keys[1] = 0;
            } else if (key == KeyEvent.VK_DOWN) {
                keys[3] = 0;
            } else if (key == KeyEvent.VK_PERIOD) {
                keys[4] = 0;
            } else if (key == KeyEvent.VK_SLASH) {
            	keys[5] = 0;
            }  else if (key == KeyEvent.VK_A) {
                keys[6] = 0;
            } else if (key == KeyEvent.VK_W) {
                keys[7] = 0;
            } else if (key == KeyEvent.VK_D) {
                keys[8] = 0;
            }  else if (key == KeyEvent.VK_S) {
                keys[9] = 0;
            } else if (key == KeyEvent.VK_1) {
            	keys[10] = 0;
            } else if (key == KeyEvent.VK_2) {
            	keys[11] = 0;
            }
        }
    }
}