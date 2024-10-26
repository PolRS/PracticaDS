package baseNoStates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that represensts a specific Space.
 * is part of the Composite pattern.
 * It is a leaf in the composite pattern, wich means it has no subSpaces.
 */
public class Space extends Area {
  private final ArrayList<Door> doors = new ArrayList<>();

  public Space(String id, Area parentArea) {
    super(id, parentArea);
  }

  @Override
  public List<Door> getDoorsGivingAccess(){
    return this.doors;
  }

  public void addDoor(Door d){
    this.doors.add(d);
  }
}
