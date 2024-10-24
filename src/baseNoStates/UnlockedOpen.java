package baseNoStates;

public class UnlockedOpen extends DoorState{
  public UnlockedOpen(Door door) {
    super(door,false);
    this.name="unlocked";
  }

  //cannot open a door that is already open
  @Override
  public void open() {
    assert false;
  }

  // closes the door
  @Override
  public void close() {
    this.door.setState(new UnlockedClosed(this.door));
  }

  //cannot lock an open door
  @Override
  public void lock() {
    assert false;
  }

  //cannot unlock a door that is already unlocked
  @Override
  public void unlock() {
    assert false;
  }

  //cannot unlock a door that is already unlocked
  public void unlockShortly(){
    assert false;
  }
}
