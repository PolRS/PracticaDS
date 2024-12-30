import 'package:flutter/material.dart';
import 'package:milestone3/Data/requests.dart';
import 'package:milestone3/Pages/parent_area_page.dart';
import 'package:milestone3/assets/MyColors.dart';

class CustomBottomNavBar extends StatefulWidget {
  final int currentIndex;

  const CustomBottomNavBar({
    super.key,
    required this.currentIndex,
  });


  @override
  _CustomBottomNavBarState createState() => _CustomBottomNavBarState();
}

class _CustomBottomNavBarState extends State<CustomBottomNavBar> {

  onTapHome(){
    Navigator.of(context).pop();
    Navigator.of(context).push(MaterialPageRoute<void>(
      builder: (context) {
        final requests = Requests();
        String area = requests.getParentArea();
        return ParentAreaPage(
          title: area,
          subAreas: requests.getSubAreas(area),
        );
      },
    ));
  }

  onTap(int i){
    if (i == 0){
      onTapHome();
    } else {
      print("nothing to do");
    }
  }

  @override
  Widget build(BuildContext context) {
    return BottomNavigationBar(
      backgroundColor: myColors.Grey_Dark,
      unselectedItemColor: myColors.Grey_Medium,
      selectedItemColor: myColors.Grey_Light,
      currentIndex: widget.currentIndex,
      onTap: onTap,
      items: const [
        BottomNavigationBarItem(
          icon: Icon(Icons.home),
          label: 'Navigate',
        ),
        BottomNavigationBarItem(
          icon: Icon(Icons.group),
          label: 'Groups',
        ),
        BottomNavigationBarItem(
          icon: Icon(Icons.star),
          label: 'Others',
        ),
      ],
    );
  }
}