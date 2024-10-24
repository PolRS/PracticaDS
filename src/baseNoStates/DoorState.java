package baseNoStates;

// DoorState is the base class for all the states of the Door
public abstract class DoorState {
  protected Door door;
  protected String name;
  protected boolean isClosed;

  public DoorState(Door door, boolean isClosed) {
    this.door = door;
    this.isClosed = isClosed;
  }

  // getName returns the name of the state
  // must match the name in the json declaration
  public String getName() {
    return name;
  }

  // all subclasses must implement the following methods
  // even if they do nothing
  public abstract void open();
  public abstract void close();
  public abstract void lock();
  public abstract void unlock();
  public abstract void unlockShortly();

  // isClosed returns if specidfic state is closed
  public boolean isClosed(){ return isClosed; };

}
