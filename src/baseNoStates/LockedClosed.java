package baseNoStates;

public class LockedClosed extends DoorState {
  public LockedClosed(Door door) {
    super(door,true);
    this.name="locked";
  }

  @Override
  public void open() {
    assert false;
  }

  @Override
  public void close() {
    assert false;
  }

  @Override
  public void lock() {
    assert false;
  }

  @Override
  public void unlock() {
    this.door.setState(new UnlockedClosed(this.door));
  }

  @Override
  public void unlockShortly() {
    this.door.setState(new UnlockedShortly(this.door));
  }

}
