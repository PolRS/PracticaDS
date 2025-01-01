import 'package:http/http.dart' as http;
import 'package:milestone3/Data/data.dart' as Data; 
import 'dart:convert' as convert;

import 'tree.dart';

/*
! - Fix getParentArea() to give the correct parent area and not a hardcoded value
! - make isSpace() check in java with "instanceof" 
! - get all the doors in the space with getSpaceDoors(String space)
! - Requests to operate doors (open, close, lock, unlock)
! - Request to Lock/Unlock a Partition
! - getProppedDoors() to get all the propped doors
*/

List<String> getProppedDoors() {
  //TODO
  return ["Door7"];
}

getUserGroups(){
  //TODO
  return ["Users", "Admins", "Managers"];
}

unlockArea(String areaId) {
  //TODO
}

lockArea(String areaId) {
  //TODO
}

String getParentArea() {
  //TODO
  return 'Building';
}

setDoorState(Data.Door door, String command) {
  //TODO
}


List<String> getSubAreas(String parentArea) {
  if(parentArea=="Floor1") { return ["Room1"]; }
  return ["Floor1", "Floor2", "Floor3"];
}

bool isSpace(String partition) {
  return partition == "Room1";
}

List<Data.Door> getSpaceDoors(String space) {
  return [
    Data.Door("Door1", Data.DoorState.openLocked),
    Data.Door("Door2", Data.DoorState.closedLocked),
    Data.Door("Door3", Data.DoorState.openUnlocked),
  ];
}

Future<Tree> getTree(String areaId) async {
  const String baseUrl = "http://localhost:8080";


  Uri uri = Uri.parse("$baseUrl/get_children?$areaId");
  final response = await http.get(uri);
  // response is NOT a Future because of await
  if (response.statusCode == 200) {
    // TODO: change prints by logs, use package Logger for instance
    // which is the most popular, see https://pub.dev/packages/logger
    print("statusCode=$response.statusCode");
    print(response.body);
    // If the server did return a 200 OK response, then parse the JSON.
    Map<String, dynamic> decoded = convert.jsonDecode(response.body);

    return Tree(decoded);
  } else {
    // If the server did not return a 200 OK response, then throw an exception.
    print("statusCode=$response.statusCode");
    throw Exception('failed to get answer to request $uri');
  }
}

Future<bool> openRequest (String credential, String action, String time,String doorId) async {
  const String baseUrl = "http://localhost:8080";
  //-/reader?credential=12345&action=close&datetime=2024-12-24T11:30&doorId=D3
  Uri uri = Uri.parse(
      "$baseUrl/reader?credential=$credential&action=$action&datetime=$time&doorId=$doorId");
  final response = await http.get(uri);

  if (response.statusCode == 200) {
    // TODO: change prints by logs, use package Logger for instance
    // which is the most popular, see https://pub.dev/packages/logger
    print("statusCode=$response.statusCode");
    print(response.body);

    return true;
  } else{
    print("statusCode=$response.statusCode");
    throw Exception('failed to get answer to request $uri');
  }

}


