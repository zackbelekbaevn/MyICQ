import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Controller implements ActionListener, KeyListener{
    private Model model;

    public Controller(Viewer viewer) {
        model = new Model(viewer);

    }

    public void actionPerformed(ActionEvent event){
        String command = event.getActionCommand();

        if(command.equals("Send Message")){
            model.send();
            model.messageTextClear();
        }else if(command.equals("Clear")){
            model.clear();
        }
    }

    public void keyTyped(KeyEvent e){

    }
    public void keyPressed(KeyEvent e){
    }
    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            System.out.println("dasdasd");
            model.send();
        }
    }


}