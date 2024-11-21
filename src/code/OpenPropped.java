package code;

public class OpenPropped extends DoorState{
    public OpenPropped(Door door) {
        super(door,false);
        this.name = "propped";
    }

    //cannot open a door already open
    @Override
    public void open() {
        assert false;
    }

    //close the door
    @Override
    public void close() {
        this.door.setState(new LockedClosed(this.door));
    }

    //cannot lock an open door
    @Override
    public void lock() {
        assert false;
    }

    //cannot unlock an open door
    @Override
    public void unlock() {
        assert false;
    }

    //cannot unlock an open door
    @Override
    public void unlockShortly() {
        assert false;
    }
}
