import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.awt.*;
import java.util.List;
import java.util.Timer;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.*;


/**
 * The class <b>GameView</b> provides the current view of the entire Game. It extends
 * <b>JFrame</b> and lays out a matrix of <b>DotButton</b> (the actual game) and 
 * two instances of JButton. The action listener for the buttons is the controller.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class GameView extends JFrame {

     // ADD YOUR INSTANCE VARIABLES HERE
    ArrayList<JButton> jButtonArrayList;

    public GameController gameController;
    public GameModel gameModel;
    public ButtonGrid buttonGrid;
    public playbackButtonsPanel panel;
    public class JIconButton extends JButton implements MouseListener
    {
        private int x;
        private int y;
        public JIconButton(String file, String text,int x1, int y1)
        {
            super(text, new ImageIcon(file));
            x = x1;
            y = y1;

            addMouseListener(this);
            this.setOpaque(true);

        }

        public void mousePressed(MouseEvent e) {

        }

        public void mouseReleased(MouseEvent e) {

        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {

        }

        public void mouseClicked(MouseEvent e) {
            if (SwingUtilities.isRightMouseButton(e)){//flagged
                gameModel.rightclick(x,y);
            }
            else{
                gameModel.click(x,y);
            }
        }
    }


     public class ButtonGrid extends JPanel {
         int width;
         int height;
         public ButtonGrid(int w, int h) {
             this.setSize(600, 600);
             width = w;
             height = h;
             setLayout(new GridLayout(height,width));
             for(int i = 0; i < width*height; ++i){
                 JIconButton currentButton;
                 if(gameModel.get(i%width,i/width).t == DotInfo.TYPE2.normal || gameModel.get(i%width,i/width).t == DotInfo.TYPE2.coveredMine){
                     currentButton = new JIconButton("icons/Minesweeper_unopened_square.png","",(i)%gameModel.width,i/gameModel.width);

                 }
                 else if(gameModel.get(i%width,i/width).t == DotInfo.TYPE2.mine){
                     currentButton = new JIconButton("icons/Minesweeper_mine.png","",(i)%gameModel.width,i/gameModel.width);

                 }
                 else if(gameModel.get(i%width,i/width).t == DotInfo.TYPE2.uncoverednormal){
                     currentButton = new JIconButton("icons/Minesweeper_0.png","",(i)%gameModel.width,i/gameModel.width);
                 }
                 else if(gameModel.get(i%width,i/width).t == DotInfo.TYPE2.one){
                     currentButton = new JIconButton("icons/Minesweeper_1.png","",(i)%gameModel.width,i/gameModel.width);
                 }
                 else if(gameModel.get(i%width,i/width).t == DotInfo.TYPE2.two){
                     currentButton = new JIconButton("icons/Minesweeper_2.png","",(i)%gameModel.width,i/gameModel.width);
                 }
                 else if(gameModel.get(i%width,i/width).t == DotInfo.TYPE2.three){
                     currentButton = new JIconButton("icons/Minesweeper_3.png","",(i)%gameModel.width,i/gameModel.width);
                 }
                 else if(gameModel.get(i%width,i/width).t == DotInfo.TYPE2.four){
                     currentButton = new JIconButton("icons/Minesweeper_4.png","",(i)%gameModel.width,i/gameModel.width);
                 }
                 else if(gameModel.get(i%width,i/width).t == DotInfo.TYPE2.five){
                     currentButton = new JIconButton("icons/Minesweeper_5.png","",(i)%gameModel.width,i/gameModel.width);
                 }
                 else if(gameModel.get(i%width,i/width).t == DotInfo.TYPE2.six){
                     currentButton = new JIconButton("icons/Minesweeper_6.png","",(i)%gameModel.width,i/gameModel.width);
                 }
                 else if(gameModel.get(i%width,i/width).t == DotInfo.TYPE2.seven){
                     currentButton = new JIconButton("icons/Minesweeper_7.png","",(i)%gameModel.width,i/gameModel.width);
                 }
                 else if(gameModel.get(i%width,i/width).t == DotInfo.TYPE2.eight){
                     currentButton = new JIconButton("icons/Minesweeper_8.png","",(i)%gameModel.width,i/gameModel.width);
                 }
                 else if(gameModel.get(i%width,i/width).t == DotInfo.TYPE2.flaggednormal || gameModel.get(i%width,i/width).t == DotInfo.TYPE2.falaggedmine){
                     currentButton = new JIconButton("icons/Minesweeper_flag.png","",(i)%gameModel.width,i/gameModel.width);

                 }

                 else{
                     currentButton = new JIconButton("icons/Minesweeper_0.png","",(i)%gameModel.width,i/gameModel.width);
                 }
                 currentButton.setEnabled(true);

                 add(currentButton);
                 jButtonArrayList.add(currentButton);


             }

         }



     }



    class playbackButtonsPanel extends JPanel implements ActionListener{
        private JTextArea textField;
        private JButton ResetButton,QuitButton;

        public playbackButtonsPanel(){
            textField = new JTextArea("0 Step");
            ResetButton = new JButton("Reset");
            QuitButton = new JButton("Quit");
            this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
            add(textField);
            add(ResetButton);
            add(QuitButton);
            ResetButton.addActionListener(this);
            QuitButton.addActionListener(this);
        }

        public void updateView(){

            this.remove(textField);
            this.remove(ResetButton);
            this.remove(QuitButton);
            textField = new JTextArea(gameModel.steps+"   Step(s)");
            add(textField);
            add(ResetButton);
            add(QuitButton);

        }
        public void actionPerformed(ActionEvent e){
            if(e.getActionCommand() == "Reset"){
                System.out.print("Resetting!");
                gameModel = new GameModel(gameModel.width,gameModel.height,gameModel.numberOfMines);
                gameModel.view = GameView.this;
                reFactor();
            }
            else if(e.getActionCommand() == "Quit"){
                System.out.print("Quitting!");
                int dialogResult = JOptionPane.showConfirmDialog(null,"Quit?");
                if(dialogResult == JOptionPane.YES_OPTION){
                    System.exit(0);


                }
            }
        }

    }

    /**
     * Constructor used for initializing the Frame
     * 

     */

    public GameView(GameModel gameModel1, GameController gameController1) {
        jButtonArrayList = new ArrayList<>();
        gameController = gameController1;
        gameModel = gameModel1;
        panel = new playbackButtonsPanel();
        this.setLayout(new BorderLayout());
        buttonGrid = new ButtonGrid(gameModel.width,gameModel.height);
        this.setLayout(new BorderLayout());
        this.setSize(30*gameModel.width, 30*gameModel.height);
        this.getContentPane().add(buttonGrid,BorderLayout.CENTER);
        this.getContentPane().add(panel,BorderLayout.SOUTH);

        setVisible(true);
    }




    /**
     * update the status of the board's DotButton instances based 
     * on the current game model, then redraws the view
     */

    public void update(){
        


    }
    public void reFactor(){
        this.getContentPane().remove(buttonGrid);
        buttonGrid = new ButtonGrid(gameModel.width, gameModel.height);
        this.getContentPane().add(buttonGrid, BorderLayout.CENTER);
        this.getContentPane().remove(panel);
        panel.updateView();
        this.getContentPane().add(panel,BorderLayout.SOUTH);

        repaint();

        this.invalidate();
        this.validate();
        this.repaint();

        if(gameModel.gameover){

            int dialogResult = JOptionPane.showConfirmDialog(null,"you lose , another game?");
            if(dialogResult == JOptionPane.YES_OPTION){
                gameModel = new GameModel(gameModel.width,gameModel.height,gameModel.numberOfMines);
                gameModel.view = GameView.this;
                reFactor();

            }
            else if(dialogResult == JOptionPane.NO_OPTION){
                //quit
                System.exit(0);


            }
        }

        if(gameModel.isFinished()){
            int dialogResult = JOptionPane.showConfirmDialog(null,"you win , another game?");
            if(dialogResult == JOptionPane.YES_OPTION){
                gameModel = new GameModel(gameModel.width,gameModel.height,gameModel.numberOfMines);
                gameModel.view = GameView.this;
                reFactor();

            }
            else if(dialogResult == JOptionPane.NO_OPTION){
                //quit
                System.exit(0);


            }
        }


    }



}
