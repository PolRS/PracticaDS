package baseNoStates;

public abstract class DoorState {
  protected Door door;
  protected String name;
  protected boolean isClosed;

  public DoorState(Door door, boolean isClosed) {
    this.door = door;
    this.isClosed = isClosed;
  }

  public String getName() {
    return name;
  }

  public abstract void open();
  public abstract void close();
  public abstract void lock();
  public abstract void unlock();

  public abstract void unlockShortly();

  public boolean isClosed(){return isClosed;};

}
