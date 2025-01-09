import 'package:milestone3/Data/request.dart' as Requests;
import 'package:milestone3/Pages/parent_area_page.dart';

import 'package:flutter/material.dart';
import 'screen_partition.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {


    return MaterialApp(
      debugShowCheckedModeBanner: false,
      // removes the debug banner that hides the home button
      title: "ROOT",
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.grey), // deepPurple
        useMaterial3: true,
        textTheme: const TextTheme(
          bodyMedium: TextStyle(fontSize: 20), // size of hello
        ),
      ),
      home: ScreenPartition(id: "ROOT",), // MyHomePage(),
    );
  }
}
