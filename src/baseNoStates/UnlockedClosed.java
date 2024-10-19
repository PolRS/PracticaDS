package baseNoStates;

public class UnlockedClosed extends DoorState{
    public UnlockedClosed(Door door) {
        super(door,true);
        this.name="unlocked";
    }

    @Override
    public void open() {
        this.door.setState(new UnlockedOpen(this.door));
    }

    @Override
    public void close() {
        assert false;
    }

    @Override
    public void lock() {
        this.door.setState(new LockedClosed(this.door));
    }

    @Override
    public void unlock() {
        assert false;
    }

    @Override
    public void unlockShortly() {
        assert false;
    }
}
