package baseNoStates;

public class UnlockedShortly extends DoorState{

    private int timeLeftOpen;
    private static final int DEFAULT_TIME_OPEN = 10;
    public UnlockedShortly(Door door) {
        super(door,false);
        this.timeLeftOpen = DEFAULT_TIME_OPEN;
        this.name="unlocked_shortly";
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
