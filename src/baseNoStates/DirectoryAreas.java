package baseNoStates;

import java.util.ArrayList;

//falta implementacion, para preparar un poco la semana que viene
public class DirectoryAreas {
  private Area rootArea;
  private ArrayList<Door> allDoors;

  public static void makeAreas() {

  }

  public Area findAreaById(String id) {
    return rootArea.findAreaById(id);
  }

  public Door findDoorById(String id) {
    return null;
  }

  public ArrayList<Door> getAllDoors() { return allDoors; }
}
