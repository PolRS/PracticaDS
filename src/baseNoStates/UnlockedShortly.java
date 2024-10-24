package baseNoStates;


import java.util.Observable;
import java.util.Observer;

public class UnlockedShortly extends DoorState implements Observer {
    private int timeLeftOpen;
    private static final int DEFAULT_TIME_OPEN = 10;

    private int tickCounter;
    public UnlockedShortly(Door door) {
        super(door,false);
        this.timeLeftOpen = DEFAULT_TIME_OPEN;
        this.name="unlocked_shortly";
        this.door.getClock().addObserver(this);
        this.door.getClock().startClock();
    }

    @Override
    public void update(Observable o, Object arg) {
        tickCounter++;
        if (tickCounter == 10) {
            ((Clock) o).deleteObserver(this);  // Remove this door from the observer list
            this.door.setState(new OpenPropped(this.door));
        }
    }
    @Override
    public void open() {
        assert false;
    }

    @Override
    public void close() {
        this.door.setState(new LockedClosed(this.door));
    }

    @Override
    public void lock() {
        assert false;
    }

    @Override
    public void unlock() {
        assert false;
    }

    @Override
    public void unlockShortly() {
        assert false;
    }

    public void refreshTimer(){
        if(this.timeLeftOpen > 1){
            this.timeLeftOpen--;
        }else{
            this.door.setState(new OpenPropped(this.door));
        }
    }
}
