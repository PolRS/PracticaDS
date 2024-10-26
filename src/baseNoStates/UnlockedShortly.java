package baseNoStates;


import java.util.Observable;
import java.util.Observer;

/**
 * Represents the state of the door when it is unlocked and will be locked shortly
 */
public class UnlockedShortly extends DoorState implements Observer {
    private int timeLeftOpen;
    private static final int DEFAULT_TIME_OPEN = 10;

    private int tickCounter;

    public UnlockedShortly(Door door) {
        super(door,false);

        // Since the door is unlocked, it will be locked shortly
        this.timeLeftOpen = DEFAULT_TIME_OPEN;
        this.name="unlocked_shortly";
        this.door.getClock().addObserver(this);
        this.door.getClock().startClock();
    }

    // updates the remaining time the door will be open
    @Override
    public void update(Observable o, Object arg) {
        tickCounter++;
        if (tickCounter == DEFAULT_TIME_OPEN) {
            ((Clock) o).deleteObserver(this);  // Remove this door from the observer list
            this.door.setState(new OpenPropped(this.door));
        }
    }

    //cannot open a door that is already open
    @Override
    public void open() {
        assert false;
    }

    // closes the door
    @Override
    public void close() {
        this.door.setState(new LockedClosed(this.door));
    }

    //cannot lock an open door
    @Override
    public void lock() {
        assert false;
    }

    //cannot unlock a door that is already unlocked
    @Override
    public void unlock() {
        assert false;
    }

    //cannot unlock a door that is already unlocked
    @Override
    public void unlockShortly() {
        assert false;
    }

    // refreshes the timer
    // if the timer reaches 0, the door will be propped
    public void refreshTimer(){
        if(this.timeLeftOpen > 1){
            this.timeLeftOpen--;
        }else{
            this.door.setState(new OpenPropped(this.door));
        }
    }
}
