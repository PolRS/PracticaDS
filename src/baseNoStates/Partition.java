package baseNoStates;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class that represents a subdivision of an area.
 * is part of the Composite pattern.
 */
public class Partition extends Area {
  ArrayList<Area> areas = new ArrayList<>();

  public Partition(String id, Area parentArea) {
    super(id, parentArea);
    this.areas = areas;
  }

  public ArrayList<Door> getDoorsGivingAccess() {
    ArrayList<Door> doors = new ArrayList<>();
    for (Area area : areas) {
      if (area instanceof Partition) {
        area.getDoorsGivingAccess();
      } else {
        doors.addAll(area.getDoorsGivingAccess());
      }
    }

    return doors;
  }
}
