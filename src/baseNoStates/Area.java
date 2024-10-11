package baseNoStates;

import java.util.ArrayList;

/**
 * Abstract class that stores information about areas,
 * serves to find the doors that give access to each area
 * and get the spaces that are part of a partition,
 * is part of the Composite pattern.
 */
public abstract class Area {
  String id;

  public Area(String id) {
    this.id = id;
  }

  public abstract ArrayList<Door> getDoorsGivingAccess();

  public abstract  Area findAreaById(String id);

  public abstract ArrayList<Space> getSpaces();
}
