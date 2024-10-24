package baseNoStates;

import java.util.ArrayList;
import java.util.Arrays;

public class Space extends Area {
  private final ArrayList<Door> doors = new ArrayList<>();

  public Space(String id, Area parentArea) {
    super(id, parentArea);
  }
}
