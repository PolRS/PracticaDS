package code;

import java.util.ArrayList;
import java.util.List;

public class VisitorDoorsGivingAccess implements AreaVisitor {

  private final List<Door> doors = new ArrayList<>();


  @Override
  public void visit(Space space) {
    doors.addAll(space.getDoorsGivingAccess());
  }

  @Override
  public void visit(Partition partition) {
    for (Area area : partition.getSubAreas()) {
      this.visit(area);
    }
  }

  @Override
  public void visit(Area area) {
    if (area instanceof Space) {
      this.visit(((Space) area));
    } else {
      this.visit(((Partition) area));
    }
  }

  public List<Door> getDoors() {
    return doors;
  }
}
