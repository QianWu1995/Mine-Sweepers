import java.util.ArrayList;

/**
 * Stack Abstract Data Type. A Stack is a linear data structure
 * following last-in-first-out protocol, i.e. the last element
 * that has been added onto the Stack, is the first one to
 * be removed.
 *
 * @author Marcel Turcotte
 */

public class  Stack{

    ArrayList<DotInfo> dotInfos;

    /**
     * Tests if this Stack is empty.
     *
     * @return true if this Stack is empty; and false otherwise.
     */

    public Stack(){
        dotInfos = new ArrayList<DotInfo>();

    }
    public Stack(DotInfo d){
        dotInfos = new ArrayList<DotInfo>();
        dotInfos.add(d);
    }

    public  boolean isEmpty(){
        if(dotInfos.size() == 0){
            return true;
        }
        return false;
    }


    /**
     * Returns a reference to the top element; does not change
     * the state of this Stack.
     *
     * @return The top element of this stack without removing it.
     */

    public  DotInfo peek(){
        return dotInfos.get(dotInfos.size()-1);
    }

    /**
     * Removes and returns the element at the top of this stack.
     *
     * @return The top element of this stack.
     */

    public  void pop(){
        dotInfos.remove(dotInfos.size()-1);
    }

    /**
     * Puts an element onto the top of this stack.
     *
     */

    public  void push(DotInfo d){
        dotInfos.add(d);
    }

}
