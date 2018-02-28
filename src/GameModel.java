import java.util.ArrayList;
import java.util.Random;

/**
 * The class <b>GameModel</b> holds the model, the state of the systems. 
 * It stores the following information:
 * - the state of all the ``dots'' on the board (mined or not, clicked
 * or not, number of neighbooring mines...)
 * - the size of the board
 * - the number of steps since the last reset
 *
 * The model provides all of this informations to the other classes trough 
 *  appropriate Getters. 
 * The controller can also update the model through Setters.
 * Finally, the model is also in charge of initializing the game
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
public class GameModel {


     // ADD YOUR INSTANCE VARIABLES HERE

    /**
     * Constructor to initialize the model to a given size of board.
     * 
     * @param width
     *            the width of the board
     * 
     * @param heigth
     *            the heigth of the board
     * 
     * @param numberOfMines
     *            the number of mines to hide in the board
     */
    ArrayList<GameView.JIconButton> buttonsArray;
    public int width, height, numberOfMines;
    public ArrayList<DotInfo> allDots; // array of all the blocks
    public ArrayList<DotInfo> allMine; // array of the mines
    public int steps;
    public int totalDots;
    public GameView view;
    public boolean gameover;
    public GameModel(int width1, int heigth1, int numberOfMines1) {
        totalDots = width1*heigth1;
        width = width1;
        height = heigth1;
        numberOfMines = numberOfMines1;
        gameover = false;
        allDots = new ArrayList<DotInfo>(width*height);
        allMine = new ArrayList<DotInfo>(numberOfMines);

        settingUp();
        print();
    }


    void print(){
        for(int j = 0; j < height; ++j){
            for(int i = 0; i < width; ++i){
                DotInfo dotInfo = get(i,j);
                System.out.println ( dotInfo.print() + dotInfo.t.toString() + " " + dotInfo.neighboursMine.size());

            }

        }

    }
    public boolean outofRange(int i , int j){
        if(i < 0 || i > width-1){
            return true;
        }
        if(j < 0 || j > height - 1){
            return true;
        }
        return false;
    }

    void settingUp(){
        int mineleft = numberOfMines;
        int temp = 0;
        double prob = numberOfMines/(width*height);
        for(int i = 0; i < width*height - numberOfMines; ++i){
            temp = (Math.random() <= 0.13) ? 1 : 2;
            System.out.println("temp is "+temp);
            System.out.println("mineleft "+mineleft);
            if(temp == 1 && mineleft>0){// mine
                DotInfo dotInfo = new DotInfo(i%width,i/width);
                dotInfo.t = DotInfo.TYPE2.coveredMine;
                allDots.add(dotInfo);
                allMine.add(dotInfo);
                --mineleft;
            }
            else{// no mine
                DotInfo dotInfo = new DotInfo(i%width,i/width);
                allDots.add(dotInfo);

            }
        }

        for(int i = width*height - numberOfMines ; i < width*height; ++i){
            if(mineleft > 0){
                DotInfo dotInfo = new DotInfo(i%width,i/width);
                dotInfo.t = DotInfo.TYPE2.coveredMine;
                allDots.add(dotInfo);
                allMine.add(dotInfo);
                --mineleft;
            }
            else{
                DotInfo dotInfo = new DotInfo(i%width,i/width);
                allDots.add(dotInfo);
            }
        }
        // all of the dots have been initialized successfully.
        // setting up their neighbours.
        DotInfo temp2;
        for(int i = 0; i < width; ++i){
            for(int j = 0; j < height; ++j) {
                temp2 = get(i,j);
                if(!outofRange(i+1,j)){//right
                    if(get(i+1,j).t == DotInfo.TYPE2.coveredMine){
                        temp2.setNeighbooringMines(get(i+1,j));
                    }
                    temp2.setNeighbors(get(i+1,j));
                }
                if(!outofRange(i,j+1)){//bottom
                    if(get(i,j+1).t == DotInfo.TYPE2.coveredMine){
                        temp2.setNeighbooringMines(get(i,j+1));
                    }
                    temp2.setNeighbors(get(i,j+1));
                }
                if(!outofRange(i+1,j+1)){//right bottom
                    if(get(i+1,j+1).t == DotInfo.TYPE2.coveredMine){
                        temp2.setNeighbooringMines(get(i+1,j+1));
                    }
                    temp2.setNeighbors(get(i+1,j+1));
                }
                if(!outofRange(i+1,j-1)){//top right
                    if(get(i+1,j-1).t == DotInfo.TYPE2.coveredMine){
                        temp2.setNeighbooringMines(get(i+1,j-1));
                    }
                    temp2.setNeighbors(get(i+1,j-1));
                }
                if(!outofRange(i,j-1)){//top
                    if(get(i,j-1).t == DotInfo.TYPE2.coveredMine){
                        temp2.setNeighbooringMines(get(i,j-1));
                    }


                    temp2.setNeighbors(get(i,j-1));
                }
                if(!outofRange(i-1,j)){//left
                    if(get(i-1,j).t == DotInfo.TYPE2.coveredMine){
                        temp2.setNeighbooringMines(get(i-1,j));
                    }
                    temp2.setNeighbors(get(i-1,j));
                }
                if(!outofRange(i-1,j+1)){//left bottom
                    if(get(i-1,j+1).t == DotInfo.TYPE2.coveredMine){
                        temp2.setNeighbooringMines(get(i-1,j+1));
                    }
                    temp2.setNeighbors(get(i-1,j+1));
                }
                if(!outofRange(i-1,j-1)){//left top
                    if(get(i-1,j-1).t == DotInfo.TYPE2.coveredMine){
                        temp2.setNeighbooringMines(get(i-1,j-1));
                    }
                    temp2.setNeighbors(get(i-1,j-1));
                }
            }
        }
    }


 
    /**
     * Resets the model to (re)start a game. The previous game (if there is one)
     * is cleared up . 
     */
    public void reset(){

        settingUp();

    }


    /**
     * Getter method for the heigth of the game
     * 
     * @return the value of the attribute heigthOfGame
     */   
    public int getHeigth(){
        return height;
    }

    /**
     * Getter method for the width of the game
     * 
     * @return the value of the attribute widthOfGame
     */   
    public int getWidth(){
        
        return width;

    }



    /**
     * returns true if the dot at location (i,j) is mined, false otherwise
    * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isMined(int i, int j){

        DotInfo d = get(i,j);
        return d.isMined();

    }

    /**
     * returns true if the dot  at location (i,j) has 
     * been clicked, false otherwise
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean hasBeenClicked(int i, int j){

        DotInfo d = get(i,j);
        return  d.hasBeenClicked();

    }

  /**
     * returns true if the dot  at location (i,j) has zero mined 
     * neighboor, false otherwise
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isBlank(int i, int j){

        DotInfo d = get(i,j);
        DotInfo temp;
        for(int ii = 0; ii <d.neighbours.size(); ++ii){
            temp = d.neighbours.get(ii);
            if(temp.isMined()){
                return false;
            }
        }
        return true;

    }
    /**
     * returns true if the dot is covered, false otherwise
    *
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */
    public boolean isCovered(int i, int j){

        DotInfo d = get(i,j);
        return d.isCovered();

    }

    /**
     * returns the number of neighbooring mines os the dot  
     * at location (i,j)
     *
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the number of neighbooring mines at location (i,j)
     */   
    public int getNeighbooringMines(int i, int j){
        DotInfo d = get(i,j);
        int numberOfMine = 0;
        for(int ii = 0; ii < d.getNeighbooringMines().size(); ++ii){
            if(d.getNeighbooringMines().get(ii).isMined()){

                ++numberOfMine;
            }

        }
        return numberOfMine;

    }


    /**
     * Sets the status of the dot at location (i,j) to uncovered
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     */   
    public void uncover(int i, int j){

        DotInfo d = get(i,j);
        d.uncover();

    }

    /**
     * Sets the status of the dot at location (i,j) to clicked
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     */   
    public void click(int i, int j) {

        DotInfo d = get(i, j);
        if (d.t == DotInfo.TYPE2.coveredMine) {
            d.t = DotInfo.TYPE2.mine;
            gameover = true;
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
            clearZone(d);
        }

        steps += 1;
        view.reFactor();
        view.repaint();


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
        view.reFactor();
        view.repaint();
    }
    public void rightclick(int i, int j) {

        DotInfo d = get(i, j);
        if (d.t == DotInfo.TYPE2.coveredMine) {
            d.t = DotInfo.TYPE2.falaggedmine;
        }
        else if(d.t == DotInfo.TYPE2.normal) {
            d.t = DotInfo.TYPE2.flaggednormal;
        }
        else if(d.t == DotInfo.TYPE2.flaggednormal){
            d.t = DotInfo.TYPE2.normal;
        }
        else if(d.t == DotInfo.TYPE2.falaggedmine){
            d.t = DotInfo.TYPE2.coveredMine;
        }


        //steps += 1;
        view.reFactor();
        view.repaint();


    }

    private void clearZone(DotInfo initialDot) {
        Stack stack = new Stack(initialDot);
        while(stack.isEmpty() == false){ // there are something in the stack.
            DotInfo top;
            top = stack.peek();
            click(top);
            stack.pop();
            for(int i = 0; i < top.neighbours.size(); ++i){
                if(top.neighbours.get(i).t == DotInfo.TYPE2.normal ||top.neighbours.get(i).t == DotInfo.TYPE2.flaggednormal ){
                    click(top.neighbours.get(i));
                    if(top.neighbours.get(i).neighboursMine.size() == 0){
                        stack.push(top.neighbours.get(i));
                    }
                }
            }
        }
    }

     /**
     * Uncover all remaining covered dot
     */   
    public void uncoverAll(){
        DotInfo temp;
        for(int i = 0; i < allDots.size(); ++i){
                temp = allDots.get(i);
                temp.uncover();

        }

    }

 

    /**
     * Getter method for the current number of steps
     * 
     * @return the current number of steps
     */   
    public int getNumberOfSteps(){
        
        return steps;

    }

  

    /**
     * Getter method for the model's dotInfo reference
     * at location (i,j)
     *
      * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     *
     * @return model[i][j]
     */   
    public DotInfo get(int i, int j) {// i is x and j is y
        return allDots.get((j) * width + i);
    }


   /**
     * The metod <b>step</b> updates the number of steps. It must be called 
     * once the model has been updated after the payer selected a new square.
     */
     public void step(){
        
        ++steps;

    }
 
   /**
     * The metod <b>isFinished</b> returns true iff the game is finished, that
     * is, all the nonmined dots are uncovered.
     *
     * @return true if the game is finished, false otherwise
     */
    public boolean isFinished(){
        DotInfo temp;
        for(int i = 0; i < allDots.size(); ++i){
            temp = allDots.get(i);
            if(temp.t == DotInfo.TYPE2.normal || temp.t == DotInfo.TYPE2.flaggednormal){

                return false;
            }

        }
        return true;



    }

    void openNeighbour(int x, int y){
        DotInfo temp = get(x,y);
        DotInfo temp2;
        for(int i = 0; i < temp.neighbours.size();++i){
            temp2 = temp.getNeighbours().get(i);
            if(temp2.t == DotInfo.TYPE2.normal && temp2.neighboursMine.size() == 0){
                click(temp2.x, temp2.y);
            }
        }
    }
}
