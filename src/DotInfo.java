import java.util.ArrayList;

/**
 * The class <b>DotInfo</b> is a simple helper class to store 
 * the state (e.g. clicked, mined, number of neighbooring mines...) 
 * at the dot position (x,y)
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class DotInfo {

     // ADD YOUR INSTANCE VARIABLES HERE
     public enum TYPE2 {
         normal,mine,one,two,three,four,five,six,seven,eight,falaggedmine,uncoverednormal,minselected,coveredMine,flaggednormal;
     }

    /**
     * Constructor, used to initialize the instance variables
     * 
     * @param x
     *            the x coordinate
     * @param y
     *            the y coordinate
     */

    int x,y;
    boolean mine;
    boolean covered,flagged;
    TYPE2 t;
    ArrayList<DotInfo> neighbours;
    ArrayList<DotInfo> neighboursMine;
    public DotInfo(int x1, int y1){
            x = x1;
            y = y1;
            t = TYPE2.normal;
            mine = false;
            neighbours = new ArrayList<DotInfo>(8);
            neighboursMine = new ArrayList<DotInfo>();
    }

    /**
     * Getter method for the attribute x.
     * 
     * @return the value of the attribute x
     */


    public int getX(){

        return x;

    }
    
    /**
     * Getter method for the attribute y.
     * 
     * @return the value of the attribute y
     */
    public int getY(){

        return y;

    }
    
 
    /**
     * Setter for mined
     */
    public void setMined() {

        mine  = true;

    }

    /**
     * Getter for mined
     *
     * @return mined
     */
    public boolean isMined() {

        return mine;

    }


    /**
     * Setter for covered
     */
    public void uncover() {

        covered = false;

    }

    /**
     * Getter for covered
     *
     * @return covered
     */
    public boolean isCovered(){

    // ADD YOU CODE HERE
        return covered;

    }



    /**
     * Setter for wasClicked
     */
    public void click() {

    // ADD YOU CODE HERE

    }


    /**
     * Getter for wasClicked
     *
     * @return wasClicked
     */
    public boolean hasBeenClicked() {

        if(mine){
            //lose the game;
        }
        else if(covered){
            uncover();
        }
        else{// no mine and clicked already uncovered dots so nothing happens.
            //
            return false;

        }
        return true;

    }


    /**
     * Setter for neighbooringMines
     *

     */
    public void setNeighbooringMines(DotInfo n) {
        neighboursMine.add(n);
    }
    public void setNeighbors(DotInfo n) {
        neighbours.add(n);
    }


    /**
     * Get for neighbooringMines
     *
     * @return neighbooringMines
     */
    public ArrayList<DotInfo> getNeighbooringMines() {
        return neighboursMine;
    }
    public ArrayList<DotInfo> getNeighbours(){
        return neighbours;

    }
    public String print(){
        return ( "x = " + x + "y + " + y + " " + mine);
    }

 }
