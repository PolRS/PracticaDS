import 'dart:ffi';

import 'package:flutter/material.dart';
import 'package:milestone3/Data/area_loader.dart';
import 'package:milestone3/assets/MyColors.dart';
import 'package:milestone3/assets/custom_bottom_nav_bar.dart';
import 'package:milestone3/assets/custom_button.dart';
import '../the_drawer.dart';




class AreaPage extends StatefulWidget {
  const AreaPage({super.key, required this.title, required this.subAreas});

  final String title;
  final List<String> subAreas;

  @override
  State<AreaPage> createState() => _AreaPageState();
}



class _AreaPageState extends State<AreaPage> {
  final int _currentIndex = 0;

  generateSubAreaButtons(List<String> subAreas){
    List<Widget> buttons = [];
    for (String subArea in subAreas){
      AreaLoader areaLoader = AreaLoader();
      buttons.add(CustomButton(label: subArea, onPressed: () => {
        areaLoader.loadArea(context, subArea)}));
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
            child: Column( children: generateSubAreaButtons(widget.subAreas)
            ),
          ),
        ),
      
      
      
      
    );
  }
}