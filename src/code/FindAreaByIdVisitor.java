package code;

public class FindAreaByIdVisitor implements AreaVisitor {

  private Area areaLookingFor;
  private String id;

  public FindAreaByIdVisitor(String id) {
    areaLookingFor = null;
    this.id = id;
  }

  @Override
  public void visit(Space space) {
    if (space.getId().equals(id)) {
      this.areaLookingFor = space;
    }
  }

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

  public Area getArea() {
    return this.areaLookingFor;
  }
}
