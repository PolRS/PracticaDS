package baseNoStates;

import java.util.ArrayList;

public class Space extends Area {
  private final ArrayList<Door> doors = new ArrayList<>();

  public Space(String id) {
    super(id);
  }

  @Override
  public ArrayList<Door> getDoorsGivingAccess() {
    return doors;
  }

  @Override
  public Area findAreaById(String id) {
    if (id.equals(this.id)) {
      return this;
    }
    return null;
  }
}
