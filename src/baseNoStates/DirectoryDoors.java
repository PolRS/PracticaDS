package baseNoStates;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class responsible for initializing doors, spaces and partitions,
 * it is also responsible for finding a door or an area
 * from an id.
 */
public final class DirectoryDoors {
  private static ArrayList<Door> allDoors;
  private static Area rootArea;

  public static void makeDoors() {
    // basement
    Space parking = new Space("parking", "space");
    Space exterior = new Space("exterior", "space");
    Space stairs = new Space("stairs", "space");
    Partition basement = new Partition("exterior", "partition", new ArrayList<>(Arrays.asList(parking)));

    Door d1 = new Door("D1"); // exterior, parking
    Door d2 = new Door("D2"); // stairs, parking

    // ground floor
    Space hall = new Space("hall", "space");
    Space room1 = new Space("room1", "space");
    Space room2 = new Space("room2", "space");
    Partition groundFloor = new Partition("ground floor", "partition", new ArrayList<>(Arrays.asList(hall, room1, room2)));
    Door d3 = new Door("D3"); // exterior, hall
    Door d4 = new Door("D4"); // stairs, hall
    Door d5 = new Door("D5"); // hall, room1
    Door d6 = new Door("D6"); // hall, room2

    // first floor
    Space corridor = new Space("corridor", "space");
    Space room3 = new Space("room3", "space");
    Space it = new Space("IT", "space");
    Partition firstFloor = new Partition("floor1", "partition", new ArrayList<>(Arrays.asList(room3, corridor, it)));
    Door d7 = new Door("D7"); // stairs, corridor
    Door d8 = new Door("D8"); // corridor, room3
    Door d9 = new Door("D9"); // corridor, IT

    allDoors = new ArrayList<>(Arrays.asList(d1, d2, d3, d4, d5, d6, d7, d8, d9));
    rootArea = new Partition("building", "partition", new ArrayList<>(Arrays.asList(basement, groundFloor, firstFloor)));
  }

  public static Door findDoorById(String id) {
    for (Door door : allDoors) {
      if (door.getId().equals(id)) {
        return door;
      }
    }
    System.out.println("door with id " + id + " not found");
    return null; // otherwise we get a Java error
  }

  // this is needed by RequestRefresh
  public static ArrayList<Door> getAllDoors() {
    System.out.println(allDoors);
    return allDoors;
  }

}
