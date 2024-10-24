package baseNoStates;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class that represensts a subdivision of an area.
 * is part of the Composite pattern.
 */
public class Partition extends Area {
  ArrayList<Area> areas = new ArrayList<>();

  public Partition(String id, Area parentArea) {
    super(id, parentArea);
    this.areas = areas;
  }



}
