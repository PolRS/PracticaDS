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

  protected Area parentArea;

  public Area(String id, Area pA) {
    this.id = id;
    this.parentArea = pA;
  }

  public String getId() { return id; }

  // checks if the @area is a child of this area
  // or if it is the same area recursively
  public boolean belongsInside(Area a){
    if(this.getId().equals(a.getId())){
      return true;
    }else if(a.parentArea == null){
      return false;
    }else{
      return this.belongsInside(a.parentArea);
    }
  }
}
