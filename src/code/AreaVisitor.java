package code;

public interface AreaVisitor {
  void visit(Space space);

  void visit(Partition partition);

  void visit(Area area);
}
