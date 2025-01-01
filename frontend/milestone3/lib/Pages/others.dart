
import 'package:flutter/material.dart';
import 'package:milestone3/Data/area_loader.dart';
import 'package:milestone3/Data/data.dart';
import 'package:milestone3/Data/request.dart';
import 'package:milestone3/assets/MyColors.dart';
import 'package:milestone3/assets/custom_bottom_nav_bar.dart';
import 'package:milestone3/assets/custom_button.dart';
import 'package:milestone3/assets/door_item.dart';




class OthersPage extends StatefulWidget {
  const OthersPage({super.key});


  @override
  State<OthersPage> createState() => _OthersPageState();
}



class _OthersPageState extends State<OthersPage> {
  final int _currentIndex = 1;
  

  loadProppedDoors(){
    List<String> list = getProppedDoors();
    List<Widget> buttons = [];
    for (String door in list){
      AreaLoader areaLoader = AreaLoader();
      buttons.add(DoorItem(doorItem: Door(door, DoorState.openPropped)));
      }
    return buttons;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(

      bottomNavigationBar: CustomBottomNavBar(
        currentIndex: _currentIndex,
      ),
      appBar: AppBar(
        centerTitle: true,
        leading:Container(),
        backgroundColor: myColors.Grey_Dark,
        foregroundColor: myColors.Grey_Light,
        title: Text("Propped Doors"),
      ),

      body: 
        Padding(padding: const EdgeInsets.all(15.0), child: 
          Center(
            child: Column( children: loadProppedDoors()
            ),
          ),
        ),
      
      
      
      
    );
  }
}