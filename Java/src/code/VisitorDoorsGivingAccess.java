package code;

import java.util.ArrayList;
import java.util.List;

// Visitor that stores the Doors that give
// access to an Area and all
public class VisitorDoorsGivingAccess implements AreaVisitor {


  private final List<Door> doors = new ArrayList<>();


  // From a space, stores all the doors associated with it
  @Override
  public void visit(Space space) {
    doors.addAll(space.getDoorsGivingAccess());
  }

  // Gets all the doors inside the spaces inside the partition
  @Override
  public void visit(Partition partition) {
    for (Area area : partition.getSubAreas()) {
      this.visit(area);
    }
  }

  // uses the correct method to filter individual cases
  @Override
  public void visit(Area area) {
    if (area instanceof Space) {
      this.visit(((Space) area));
    } else {
      this.visit(((Partition) area));
    }
  }

  // Returns the final list of doors
  public List<Door> getDoors() {
    return doors;
  }
}
