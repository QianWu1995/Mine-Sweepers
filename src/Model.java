import java.util.List;

/**
 * Created by qianwu on 2017-03-22.
 */
public class Model {


    private List<Observer> observers;

    public void addObserver(Observer observer) {

        this.observers.add(observer);

    }
}
