package code;

public class UnlockedClosed extends DoorState {
  public UnlockedClosed(Door door) {
      super(door, true);
      this.name = "unlocked";
  }

  // opens the door
  @Override
  public void open() {
      this.door.setState(new UnlockedOpen(this.door));
  }

  //cannot close a door that is already closed
  @Override
  public void close() {
      assert false;
  }

  // locks the door
  @Override
  public void lock() {
      this.door.setState(new LockedClosed(this.door));
  }

  // cannot unlock a door that is already unlocked
  @Override
  public void unlock() {
      assert false;
  }

  // cannot lock a door that is already unlocked
  @Override
  public void unlockShortly() {
      assert false;
  }
}
