import 'package:flutter/material.dart';
import 'package:milestone3/Data/data.dart';
import 'package:milestone3/Data/requests.dart';
import 'package:milestone3/Pages/parent_area_page.dart';
import 'package:milestone3/Pages/space_page.dart';

class AreaLoader {

loadArea(context, String name){
  Requests requests = Requests();
  if(requests.isSpace(name)){
    loadSpace(context, name);
  } else {
    loadPartition(context, name);
  }
}

loadSpace(context, String space) {
  print("Loading space $space");

  Requests requests = Requests();
  List<Door> doors = requests.getSpaceDoors(space);

  Widget newPage = SpacePage(title: space, doors: doors);

  Navigator.of(context).pop();
  Navigator.of(context).push(MaterialPageRoute<void>(
    builder: (context) {
      return newPage;
    },
  ));
 }


  loadPartition(context, String partition) {

    final requests = Requests();
    List<String> subAreas = requests.getSubAreas(partition);

    Widget newPage =  ParentAreaPage(
      title: partition,
      subAreas: subAreas,
    );

    Navigator.of(context).pop();
    Navigator.of(context).push(MaterialPageRoute<void>(
      builder: (context) {
        return newPage;
      },
    ));

 }

}