  import 'package:flutter/material.dart';
import 'package:milestone3/Data/request.dart' as Request;
import 'package:milestone3/Data/tree.dart';

class LockArea {
  void _showPopup(BuildContext context, Area area) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: Text('Area Options'),
          content: Text('What do you want to do with area "${area.id}?"'),
          actions: [
            TextButton(
              child: Text('LOCK'),
              onPressed: () {
                Request.lockArea(area.id);
                Navigator.of(context).pop(); // Close the popup
              },
            ),
            TextButton(
              child: Text('UNLOCK'),
              onPressed: () {
                Request.unlockArea(area.id);
                Navigator.of(context).pop(); // Close the popup
              },
            ),
            TextButton(
              child: Text('Cancel'),
              onPressed: () {
                Navigator.of(context).pop(); // Close the popup
              },
            ),

          ],
        );
      },
    );
  }
  

  

}