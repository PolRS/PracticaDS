package baseNoStates;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class that stores which areas are part of a partition,
 * can get both areas and doors that are part of it,
 * is part of the Composite pattern.
 */
public class Partition extends Area {
  ArrayList<Area> areas = new ArrayList<>();

  public Partition(String id, Area parentArea) {
    super(id, parentArea);
    this.areas = areas;
  }



}
