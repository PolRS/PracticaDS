package code;

public class FindAreaByIdVisitor implements AreaVisitor {

  private Area areaLookingFor;
  private String id;

  // Visitor that finds and area by ID.
  // It searches for all the subspaces inside
  // the first provided Area
  public FindAreaByIdVisitor(String id) {
    areaLookingFor = null;
    this.id = id;
  }

  // For spaces, we only check if the ID matches
  @Override
  public void visit(Space space) {
    if (space.getId().equals(id)) {
      this.areaLookingFor = space;
    }
  }

  // For partitions, we need to check if
  // one of the subareas contains the area
  // we are looking for.
  @Override
  public void visit(Partition partition) {
    if (partition.getId().equals(id)) {
      this.areaLookingFor = partition;
    } else {
      for (Area a : partition.subAreas) {
        visit(a);
      }
    }
  }

  // uses the correct method to filter individual cases
  @Override
  public void visit(Area area) {
    if (areaLookingFor == null) {

      if (area instanceof Space) {
        visit(((Space) area));
      } else {
        visit(((Partition) area));
      }
    }

  }

  // Returns the final area we are looking for,
  // or Null if not found
  public Area getArea() {
    return this.areaLookingFor;
  }
}
