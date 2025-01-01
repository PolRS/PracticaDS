import 'package:flutter/material.dart';
import 'package:milestone3/Data/data.dart';
import 'package:milestone3/Data/request.dart' as Requests;
import 'package:milestone3/Pages/area_page.dart';
import 'package:milestone3/Pages/parent_area_page.dart';
import 'package:milestone3/Pages/space_page.dart';

class AreaLoader {

loadArea(context, String name){
  if(Requests.isSpace(name)){
    loadSpace(context, name);
  } else {
    loadPartition(context, name);
  }
}

loadSpace(context, String space) {
  print("Loading space $space");

  List<Door> doors = Requests.getSpaceDoors(space);

  Widget newPage = SpacePage(title: space, doors: doors);

  Navigator.of(context).push(MaterialPageRoute<void>(
    builder: (context) {
      return newPage;
    },
  ));
 }


  loadPartition(context, String partition) {

    List<String> subAreas = Requests.getSubAreas(partition);

    Widget newPage =  AreaPage(
      title: partition,
      subAreas: subAreas,
    );

    Navigator.of(context).push(MaterialPageRoute<void>(
      builder: (context) {
        return newPage;
      },
    ));

 }

}