package code;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a subdivision of an area.
 * is part of the Composite pattern.
 */
public class Partition extends Area {

  public Partition(String id, Area parentArea) {
    super(id, parentArea);
  }

  public List<Area> getSubAreas() {
    return this.subAreas;
  }

}
