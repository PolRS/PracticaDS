package baseNoStates;

public class UnlockedOpen extends DoorState{
  public UnlockedOpen(Door door) {
    super(door,false);
    this.name="unlocked";
  }

  @Override
  public void open() {
    assert false;
  }

  @Override
  public void close() {
    this.door.setState(new UnlockedClosed(this.door));
  }

  @Override
  public void lock() {
    assert false;
  }

  @Override
  public void unlock() {
    assert false;
  }

  public void unlockShortly(){
    assert false;
  }
}
