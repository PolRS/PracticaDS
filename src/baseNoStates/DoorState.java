package baseNoStates;

public abstract class DoorState {
  protected Door door;
  protected String name;

  public DoorState(Door door) {
    this.door = door;
    name = "locked";
  }

  public String getName() {
    return name;
  }

  public abstract void open(Door door);
  public abstract void close(Door door);
  public abstract void lock(Door door);
  public abstract void unlock(Door door);
}
