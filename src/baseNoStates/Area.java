package baseNoStates;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class that stores information about areas,
 * serves to find the doors that give access to each area
 * and get the spaces that are part of a partition,
 * is part of the Composite pattern.
 */
public abstract class Area {
  protected final String id;

  protected Area parentArea;

  protected List<Area> subAreas;

  public Area(String id, Area pA) {
    this.id = id;
    this.parentArea = pA;
    this.subAreas = new ArrayList<>();

    if(pA!=null){
      parentArea.addSubArea(this);
    }
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


  protected void addSubArea(Area a){
    this.subAreas.add(a);
  }

  // gets all the doors that give access to the Area
  public List<Door> getDoorsGivingAccess(){
    List<Door> doors = new ArrayList<>();
    for (Area a: subAreas) {
      doors.addAll(a.getDoorsGivingAccess());
    }
    return doors;
  }

}
