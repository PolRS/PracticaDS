package baseNoStates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OpenPropped extends DoorState{
    private static final Logger log1 = LoggerFactory.getLogger("firstMilestoneClasses");
    private static final Logger logAC = LoggerFactory.getLogger("allClasses");

    public OpenPropped(Door door) {
        super(door,false);
        this.name = "propped";
        log1.info("Door" + door.getId() + " entered propped state.");
        logAC.info("Door" + door.getId() + " entered propped state.");
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
