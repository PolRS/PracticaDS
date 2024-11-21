package code;

// Interface that implements the "Visitor" pattern
// Is used to avoid overload of functions in different classes
public interface AreaVisitor {
  void visit(Space space);

  void visit(Partition partition);

  void visit(Area area);
}
