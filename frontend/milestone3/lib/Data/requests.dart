import 'dart:math';

import 'package:milestone3/Data/data.dart';

class Requests {


  // Get the main area in the Java code
  String getParentArea() {
    return 'Building';
  }

  // Get the sub areas in the Java code
  // based on parentArea
  List<String> getSubAreas(String parentArea) {
    if(parentArea=="Floor1") { return ["Room1"]; }
    return ["Floor1", "Floor2", "Floor3"];
  }

  bool isSpace(String partition) {
    return partition == "Room1";
  }

  List<Door> getSpaceDoors(String space) {
    return [
      Door("Door1", DoorState.openLocked),
      Door("Door2", DoorState.closedLocked),
      Door("Door3", DoorState.openUnlocked),
    ];
  }

  setDoorState(Door door, String command) {
    if(command == Actions.open && door.state == DoorState.closedLocked){
      door.state = DoorState.openLocked;
    }
    else if(command == Actions.open && door.state == DoorState.closedUnlocked){
      door.state = DoorState.openUnlocked;
    }
    else if (command == Actions.close && door.state == DoorState.openLocked){
      door.state = DoorState.closedLocked;
    }
    else if (command == Actions.close && door.state == DoorState.openUnlocked){
      door.state = DoorState.closedUnlocked;
    }
    else if (command == Actions.lock && door.state == DoorState.openUnlocked){
      door.state = DoorState.openLocked;
    }
    else if (command == Actions.lock && door.state == DoorState.closedUnlocked){
      door.state = DoorState.closedLocked;
    }
    else if (command == Actions.unlock && door.state == DoorState.closedLocked){
      door.state = DoorState.closedUnlocked;
    }
    else if (command == Actions.unlock && door.state == DoorState.openLocked){
      door.state = DoorState.openUnlocked;
    }
    
  }


}