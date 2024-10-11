package baseNoStates;

import java.util.ArrayList;
import java.util.Arrays;

public class Space extends Area {
  private final ArrayList<Door> doors = new ArrayList<>();

  public Space(String id) {
    super(id);
  }

  public void addDoor(Door door) { doors.add(door); }

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

  @Override
  public ArrayList<Space> getSpaces() {
    ArrayList<Space> spaces = new ArrayList<>(Arrays.asList(this));
    return spaces;
  }
}
