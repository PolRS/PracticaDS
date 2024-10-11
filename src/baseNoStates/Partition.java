package baseNoStates;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class that stores which areas are part of a partition,
 * can get both areas and doors that are part of it,
 * is part of the Composite pattern.
 */
public class Partition extends Area {
  ArrayList<Area> areas = new ArrayList<>();

  public Partition(String id, ArrayList<Area> areas) {
    super(id);
    this.areas = areas;
  }

  @Override
  public ArrayList<Space> getSpaces() {
    return null;
  }

  @Override
  public ArrayList<Door> getDoorsGivingAccess() {
    return null;
  }

  @Override
  public Area findAreaById(String id) {
    for (Area area : areas) {
      if (area.getId().equals(id)) {
        return area;
      } else {
        area.findAreaById(id);
      }
    }
    return null;
  }
}
