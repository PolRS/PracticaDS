package baseNoStates;

public class LockedClosed extends DoorState {
  public LockedClosed(Door door) {
    super(door,true);
    this.name="locked";
  }

  //cannot open a locked door
  @Override
  public void open() {
    assert false;
  }

  //cannot close a closed door
  @Override
  public void close() {
    assert false;
  }

  //cannot lock a locked door
  @Override
  public void lock() {
    assert false;
  }

  //unlock the door
  @Override
  public void unlock() {
    this.door.setState(new UnlockedClosed(this.door));
  }

  //unlock the door shortly
  @Override
  public void unlockShortly() {
    this.door.setState(new UnlockedShortly(this.door));
  }

}
