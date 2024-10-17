package baseNoStates;

public class Locked extends DoorState {
  public Locked(Door door) {
    super(door);
  }

  @Override
  public void open(Door door) {
    if (door.isClosed() && name.equals("unlocked")) {
      door.setClosed(false);
    }
  }

  @Override
  public void close(Door door) {
    if (!door.isClosed()) {
      door.setClosed(true);
    }
  }

  @Override
  public void lock(Door door) {
    if (door.isClosed() && door.getStateName().equals("unlocked")) {
      name = "locked";
    }
  }

  @Override
  public void unlock(Door door) {
    if (door.isClosed() && door.getStateName().equals("locked")) {
      name = "unlocked";
    }
  }
}
