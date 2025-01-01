import 'package:flutter/material.dart';
import 'package:milestone3/Data/request.dart' as Requests;
import 'package:milestone3/Pages/others.dart';
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
    Navigator.of(context).popUntil((route) => route.isFirst);
    Navigator.of(context).pop();
    Navigator.of(context).push(MaterialPageRoute<void>(
      builder: (context) {
        String area = Requests.getParentArea();
        return ParentAreaPage(
          title: area,
          subAreas: Requests.getSubAreas(area),
        );
      },
    ));
  }

    onTapOthers(){
    Navigator.of(context).popUntil((route) => route.isFirst);
    Navigator.of(context).pop();
    Navigator.of(context).push(MaterialPageRoute<void>(
      builder: (context) {
        return OthersPage();
      },
    ));
  }


  onTap(int i){
    if (i == 0){
      onTapHome();
    } else {
      onTapOthers();
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
          icon: Icon(Icons.error),
          label: 'Others',
        ),
      ],
    );
  }
}