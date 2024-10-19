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

  public Area getParentArea(){return parentArea;}

  public boolean belongsInside(Area a){
    if(this.equals(a)){
      return true;
    }else if(this.parentArea == null){
      return false;
    }else{
      return this.parentArea.belongsInside(a);
    }
  }
}
