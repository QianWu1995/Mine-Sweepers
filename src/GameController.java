import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.*;


/**
 * The class <b>GameController</b> is the controller of the game. It is a listener
 * of the view, and has a method <b>play</b> which computes the next
 * step of the game, and  updates model and view.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */


public class GameController implements ActionListener {

    // ADD YOUR INSTANCE VARIABLES HERE

    /**
     * Constructor used for initializing the controller. It creates the game's view 
     * and the game's model instances
     * 
     * @param width
     *            the width of the board on which the game will be played
     * @param height
     *            the height of the board on which the game will be played
     * @param numberOfMines
     *            the number of mines hidden in the board
     */
    int width,height,numberOfMines;


    public GameController(int width1, int height1, int numberOfMines1) {

            width = width1;
            height = height1;
            numberOfMines = numberOfMines1;
    }


    /**
     * Callback used when the user clicks a button (reset or quit)
     *
     * @param e
     *            the ActionEvent
     */

    public void actionPerformed(ActionEvent e) {
            String x = e.getActionCommand();

    }

    /**
     * resets the game
     */
    private void reset(){



    }

    /**
     * <b>play</b> is the method called when the user clicks on a square.
     * If that square is not already clicked, then it applies the logic
     * of the game to uncover that square, and possibly end the game if
     * that square was mined, or possibly uncover some other squares. 
     * It then checks if the game
     * is finished, and if so, congratulates the player, showing the number of
     * moves, and gives to options: start a new game, or exit
     * @param width
     *            the selected column
     * @param heigth
     *            the selected line
     */
    private void play(int width, int heigth,DotInfo d ){
        //DotInfo d = DotInfo.class;
        if (d.t == DotInfo.TYPE2.coveredMine) {
            d.t = DotInfo.TYPE2.mine;
            //gameover = true;
            System.out.println("MINE!!");

        }
        else if (d.t == DotInfo.TYPE2.normal) {
            System.out.println("NORMAL!!" + "neighbor mines  " + d.neighboursMine.size());
            if(d.neighboursMine.size() == 1){
                d.t = DotInfo.TYPE2.one;
            }
            else if(d.neighboursMine.size() == 2){
                d.t = DotInfo.TYPE2.two;
            }
            else if(d.neighboursMine.size() == 3){
                d.t = DotInfo.TYPE2.three;
            }
            else if(d.neighboursMine.size() == 4){
                d.t = DotInfo.TYPE2.four;
            }
            else if(d.neighboursMine.size() == 5){
                d.t = DotInfo.TYPE2.five;
            }
            else if(d.neighboursMine.size() == 6){
                d.t = DotInfo.TYPE2.six;
            }
            else if(d.neighboursMine.size() == 7){
                d.t = DotInfo.TYPE2.seven;
            }
            else if(d.neighboursMine.size() == 8){
                d.t = DotInfo.TYPE2.eight;
            }
            else{
                d.t = DotInfo.TYPE2.uncoverednormal;
            }
            System.out.println("NORMAL!!" + "neighbor mines  " + d.t.toString());
        }

        //steps += 1;
        //reFactor();
        //view.repaint();
    }


    public void click(DotInfo d) {
        if (d.t == DotInfo.TYPE2.normal) {
            System.out.println("NORMAL!!" + "neighbor mines  " + d.neighboursMine.size());
            if(d.neighboursMine.size() == 1){
                d.t = DotInfo.TYPE2.one;
            }
            else if(d.neighboursMine.size() == 2){
                d.t = DotInfo.TYPE2.two;
            }
            else if(d.neighboursMine.size() == 3){
                d.t = DotInfo.TYPE2.three;
            }
            else if(d.neighboursMine.size() == 4){
                d.t = DotInfo.TYPE2.four;
            }
            else if(d.neighboursMine.size() == 5){
                d.t = DotInfo.TYPE2.five;
            }
            else if(d.neighboursMine.size() == 6){
                d.t = DotInfo.TYPE2.six;
            }
            else if(d.neighboursMine.size() == 7){
                d.t = DotInfo.TYPE2.seven;
            }
            else if(d.neighboursMine.size() == 8){
                d.t = DotInfo.TYPE2.eight;
            }
            else{
                d.t = DotInfo.TYPE2.uncoverednormal;
            }
            System.out.println("NORMAL!!" + "neighbor mines  " + d.t.toString());
        }

    }
   /**
     * <b>clearZone</b> is the method that computes which new dots should be ``uncovered'' 
     * when a new square with no mine in its neighborood has been selected
     * @param initialDot
     *      the DotInfo object corresponding to the selected DotButton that
     * had zero neighbouring mines
     */
    private void clearZone(DotInfo initialDot) {
            Stack stack = new Stack(initialDot);
            while(stack.isEmpty() == false){ // there are something in the stack.
                DotInfo top;
                top = stack.peek();
                click(top);
                stack.pop();
                for(int i = 0; i < top.neighbours.size(); ++i){
                    if(top.neighbours.get(i).t == DotInfo.TYPE2.normal){
                        stack.push(top.neighbours.get(i));
                    }
                }
            }
    }
}
