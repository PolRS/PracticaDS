package code;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

/**
 * Class that represents a specific Space.
 * is part of the Composite pattern.
 * It is a leaf in the composite pattern, which means it has no subSpaces.
 */
public class Space extends Area {
  private final ArrayList<Door> doors = new ArrayList<>();

  public Space(String id, Area parentArea) {
    super(id, parentArea);
  }

  public ArrayList<Door> getDoorsGivingAccess() {
    return doors;
  }
}
