package baseNoStates;

import java.util.ArrayList;

/**
 * Abstract class that stores information about areas,
 * serves to find the doors that give access to each area
 * and get the spaces that are part of a partition,
 * is part of the Composite pattern.
 */
public abstract class Area {
  protected final String id;
  protected final String type;

  public Area(String id, String type) {
    this.id = id;
    this.type = type;
  }

  public String getId() { return id; }

  public String getType() { return type; }

  public abstract ArrayList<Door> getDoorsGivingAccess();

  public abstract  Area findAreaById(String id);

  public abstract ArrayList<Space> getSpaces();
}
