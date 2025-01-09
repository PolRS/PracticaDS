import 'package:milestone3/Data/request.dart';

abstract class Area{
  late String id;
  late List<dynamic> children;
  Area(this.id, this.children);

  bool validateUnlock();
  bool validateLocked();


}

class Partition extends Area {
  Partition(super.id, List<Area>super.children);

  bool validateUnlock(){

    for(Area a in children){
      if(a.validateUnlock() == false){
        return false;
      }
    }
    return true;
  }
  bool validateLocked(){

    for(Area a in children){
      if(a.validateLocked() == false){
        return false;
      }
    }
    return true;
  }
}

class Space extends Area {
  Space(super.id, List<Door> super.children);

  bool validateUnlock(){

    for(Door d in children){
      if(d.isUnlocked() == false){
        return false;
      }
    }
    return true;
  }
  bool validateLocked(){

    for(Door d in children){
      if(d.isLocked() == false){
        return false;
      }
    }
    return true;
  }
}

class Door {
  late String id;
  late bool closed;
  late String state;
  Door({required this.id, this.state="unlocked", this.closed=true});

  bool isLocked(){
    return ("locked" == state);
  }
  bool isUnlocked(){
    return ("unlocked" == state);
  }
}

// at the moment this class seems unnecessary but later we will extend it
class Tree {
  late Area root;

  Tree(Map<String, dynamic> dec) {
    // Initialize the root node
    if (dec['class'] == "partition") {
      root = Partition(dec['id'], <Area>[]);
      addChildren(root, dec['areas']);
    } else if (dec['class'] == "space") {
      List<Door> doors = <Door>[];
      for (Map<String, dynamic> d in dec['access_doors']) {
        doors.add(Door(id: d['id'], state: d['state'], closed: d['closed']));
      }
      root = Space(dec['id'], doors);
    } else {
      assert(false, "Invalid root class type in JSON");
    }
  }

  void addChildren(Area parent, List<dynamic> childData) {
    for (Map<String, dynamic> item in childData) {
      if (item['class'] == "partition") {
        // Create a Partition and add it to the parent's children
        Partition childPartition = Partition(item['id'], <Area>[]);
        if (parent is Partition) {
          parent.children.add(childPartition);
          addChildren(childPartition, item['areas']);
        } else {
          assert(false, "Only a Partition can have partitions as children");
        }
      } else if (item['class'] == "space") {
        // Create a Space and add it to the parent's children
        List<Door> doors = <Door>[];
        for (Map<String, dynamic> d in item['access_doors']) {
          doors.add(Door(id: d['id'], state: d['state'], closed: d['closed']));
        }
        Space childSpace = Space(item['id'], doors);
        if (parent is Partition) {
          parent.children.add(childSpace);
        } else {
          assert(false, "Only a Partition can have spaces as children");
        }
      } else {
        assert(false, "Unknown class type: ${item['class']}");
      }
    }
  }
}