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

  public Partition(String id, String type, ArrayList<Area> areas) {
    super(id, type);
    this.areas = areas;
  }

  @Override
  public ArrayList<Space> getSpaces() {
    ArrayList<Space> spaces = new ArrayList<>();
    for (Area area : areas) {
      if (area.getType().equals("space")) {
        if (!spaces.contains((Space) area))
          spaces.add((Space) area);
      }
      else {
        spaces.addAll(area.getSpaces());
      }
    }

    return spaces;
  }

  @Override
  public ArrayList<Door> getDoorsGivingAccess() {
    ArrayList<Door> doors = new ArrayList<>();
    for (Area area : areas) {
      for (Door d : area.getDoorsGivingAccess()) {
        if (!doors.contains(d)) {
          doors.add(d);
        }
      }
    }
    return doors;
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
