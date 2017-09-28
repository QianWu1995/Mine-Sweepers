
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by qianwu on 2017-03-22.
 */
public class View extends JFrame implements Observer {

    public Model model;


    class TopButtonsPanel extends JPanel implements ActionListener {

        JToggleButton toggleButton;

        TopButtonsPanel(){

            toggleButton = new JToggleButton("1test");

            this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        }
        public void actionPerformed(ActionEvent e) {


        }

    }

    public View(Model model){
        this.model = model;

        setVisible(true);

    }
    public void update(Object observable) {
        // XXX Fill this in with the logic for updating the view when the model
        // changes.
        //reFactor();
        repaint();


        System.out.println("Model changed!");

    }
}
