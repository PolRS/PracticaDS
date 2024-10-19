package baseNoStates;

public class OpenPropped extends DoorState{
    public OpenPropped(Door door) {
        super(door,false);
        this.name="propped";
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
}
