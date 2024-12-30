import 'dart:ffi';

import 'package:flutter/material.dart';
import 'package:milestone3/Data/data.dart';
import 'package:milestone3/assets/MyColors.dart';
import 'package:milestone3/assets/custom_bottom_nav_bar.dart';
import 'package:milestone3/assets/custom_button.dart';
import 'package:milestone3/assets/door_item.dart';
import '../the_drawer.dart';




class SpacePage extends StatefulWidget {
  const SpacePage({super.key, required this.title, required this.doors});

  final String title;
  final List<Door> doors;

  @override
  State<SpacePage> createState() => _SpacePageState();
}



class _SpacePageState extends State<SpacePage> {
  final int _currentIndex = 0;

  generateDoors(List<Door> doors){
    List<Widget> doorItems = [];
    for (Door door in doors){
      doorItems.add(DoorItem(doorItem: door));
    }
    return doorItems;
  }


  @override
  Widget build(BuildContext context) {
    return Scaffold(

      bottomNavigationBar: CustomBottomNavBar(
        currentIndex: _currentIndex,
      ),
      appBar: AppBar(
        centerTitle: true,
        backgroundColor: myColors.Grey_Dark,
        foregroundColor: myColors.Grey_Light,
        actions: [
          IconButton(
            icon: const Icon(Icons.lock),
            onPressed: () {
              // Add functionality here, like opening a drawer
              ScaffoldMessenger.of(context).showSnackBar(
                const SnackBar(content: Text('Menu button pressed')),
              );
            },
          ),
        ],
        title: Text(widget.title),
      ),

      body: 
        Padding(padding: const EdgeInsets.all(15.0), child: 
          Center(
            child: Column( children: generateDoors(widget.doors)
            ),
          ),
        ),
      
      
      
      
    );
  }
}