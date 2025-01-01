import 'package:flutter/material.dart';

import 'Data/data.dart';

class TheDrawer {
  late Drawer drawer;

  TheDrawer(BuildContext context) {
    drawer = Drawer(
      // we want the "hamburger" button that unfolds the navigation drawer only
      // if we are at the root, if not we want the up button, like in page of space
      // how to make a drawer https://flutter.dev/docs/cookbook/design/drawer
      child: ListView(
        // Important: Remove any padding from the ListView.
        padding: EdgeInsets.zero,
        children: <Widget>[
          const DrawerHeader(
            child: Text('ACS'),
          ),
          ListTile(
            leading: const Icon(Icons.holiday_village),
            // https://material.io/resources/icons
            title: const Text('Places'),
            onTap: () {},
          ),

          ListTile(
            leading: const Icon(Icons.favorite),
            title: const Text('Recent'),
            onTap: () {},
          ),
        ],
      ),
    );
  }
}