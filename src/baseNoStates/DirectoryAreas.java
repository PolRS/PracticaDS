package baseNoStates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Class responsible for initializing doors, spaces and partitions,
 * it is also responsible for finding a door or an area
 * from an id.
 */
public final class DirectoryAreas {
  private static ArrayList<Door> allDoors;
  private static ArrayList<Area> allAreas;


  //creates all areas and doors
  public static void makeAreas() {
    allAreas = new ArrayList<>();

    // building
    Area building = new Partition("building", null);
    allAreas.add(building);

    // basement
    Partition basement = new Partition("basement", building);
    Space parking = new Space("parking", basement);
    Space exterior = new Space("exterior", basement);
    Space stairs = new Space("stairs", basement);
    allAreas.add(basement);
    allAreas.add(parking);
    allAreas.add(exterior);
    allAreas.add(stairs);

    Door d1 = new Door("D1",exterior,parking); // exterior, parking
    Door d2 = new Door("D2",stairs,parking); // stairs, parking

    // ground floor
    Partition groundFloor = new Partition("ground floor", building);
    Space hall = new Space("hall", groundFloor);
    Space room1 = new Space("room1", groundFloor);
    Space room2 = new Space("room2", groundFloor);
    allAreas.add(groundFloor);
    allAreas.add(hall);
    allAreas.add(room1);
    allAreas.add(room2);

    Door d3 = new Door("D3",exterior,hall); // exterior, hall
    Door d4 = new Door("D4",stairs,hall); // stairs, hall
    Door d5 = new Door("D5",hall,room1); // hall, room1
    Door d6 = new Door("D6",hall,room2); // hall, room2

    // first floor
    Partition firstFloor = new Partition("floor1", building);
    Space corridor = new Space("corridor", firstFloor);
    Space room3 = new Space("room3", firstFloor);
    Space it = new Space("IT", firstFloor);
    allAreas.add(firstFloor);
    allAreas.add(corridor);
    allAreas.add(room3);
    allAreas.add(it);

    Door d7 = new Door("D7",stairs,corridor); // stairs, corridor
    Door d8 = new Door("D8",corridor,room3); // corridor, room3
    Door d9 = new Door("D9",corridor,it); // corridor, IT

    allDoors = new ArrayList<>(Arrays.asList(d1, d2, d3, d4, d5, d6, d7, d8, d9));
  }

  //finds a door by its id
  public static Door findDoorById(String id) {
    for (Door door : allDoors) {
      if (door.getId().equals(id)) {
        return door;
      }
    }
    System.out.println("door with id " + id + " not found");
    return null; // otherwise we get a Java error
  }

  //finds an area by its id
  public static Area findAreaById(String id){
    for (Area area : allAreas) {
      if (area.getId().equals(id)) {
        return area;
      }
    }
    assert false : ("area with id " + id + " not found");
    return null; // otherwise we get a Java error
  }

  // returns an iterator of all areas
  public static Iterator<Door> getAllDoors() {
    System.out.println(allDoors);
    return allDoors.iterator();
  }

}
