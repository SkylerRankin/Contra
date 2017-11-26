import java.awt.EventQueue;

public class Game {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable()
       {
           @Override
           public void run()
           {
               View v = new View();
               Model m = new Model();
               Control c = new Control(m, v);
               v.setVisible(true);
           }
       }
       );
    }   
}