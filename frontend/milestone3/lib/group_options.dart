import 'package:milestone3/group_actions.dart';
import 'package:milestone3/group_info.dart';
import 'package:milestone3/group_schedule.dart';
import 'package:milestone3/list_group.dart';
import 'package:flutter/material.dart';

import 'data.dart';
import 'the_drawer.dart';

class GroupOptions extends StatefulWidget {
  UserGroup userGroup;

  GroupOptions({super.key, required this.userGroup});

  @override
  State<GroupOptions> createState() => _GroupOptions();
}

class _GroupOptions extends State<GroupOptions> {
  late UserGroup userGroup;

  @override
  void initState() {
    super.initState();
    userGroup = widget.userGroup; // the userGroups of ScreenListGroups
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      drawer: TheDrawer(context).drawer,
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.primary,
        foregroundColor: Theme.of(context).colorScheme.onPrimary,
        title: Text("Group ${userGroup.name}"),
      ),
      body: Center(
        child: GridView.count(
          padding: const EdgeInsets.all(20.0),
          mainAxisSpacing: 10.0,
          crossAxisSpacing: 10.0,
          crossAxisCount: 2,
          children: [
                Container(
                            padding: const EdgeInsets.all(20.0),
                  color: Colors.grey,
                  child: Center(
                    child: Column(
                      children: [
                        IconButton(color: const Color.fromARGB(255, 255, 255, 255), iconSize: 90.0, icon: const Icon(Icons.feed), onPressed: () {
                          Navigator.of(context).pop(); // close drawer
                          Navigator.of(context).push(MaterialPageRoute<void>(
                            builder: (context) => GroupInfo(userGroup: userGroup),
                          ));
                        }),
                        const Text("Info", style: TextStyle( color: Color.fromARGB(255, 255, 255, 255),),),
                      ],
                    )
                ),
              ),
            
                Container(
                            padding: const EdgeInsets.all(20.0),

                  color: Colors.grey,
                  child: Center(  
                    child: Column(
                      children: [
                        IconButton(color: const Color.fromARGB(255, 255, 255, 255), iconSize: 90.0, icon: const Icon(Icons.calendar_month), onPressed: () {
                           Navigator.of(context).pop(); // close drawer
                          Navigator.of(context).push(MaterialPageRoute<void>(
                            builder: (context) => GroupSchedule(userGroup: userGroup),
                          ));
                        }),
                        const Text("Schedule", style: TextStyle( color: Color.fromARGB(255, 255, 255, 255),),)
                      ],
                    )

                  ),
                ),
             
                Container(
                  color: Colors.grey,
                  child: Center(
                    child: Column(
                      children: [
                        IconButton(color: const Color.fromARGB(255, 255, 255, 255), iconSize: 100.0, icon: const Icon(Icons.room_preferences), onPressed: () {
                          Navigator.of(context).pop(); // close drawer
                          Navigator.of(context).push(MaterialPageRoute<void>(
                            builder: (context) => GroupActions(userGroup: userGroup),
                          ));

                        }),
                        const Text("Actions", style: TextStyle(color: Color.fromARGB(255, 255, 255, 255),),)
                      ],
                    )

                  ),
                ),
             

              Container(
                  color: Colors.grey,
                  child: Center(
                    child: Column(
                      children: [
                        IconButton(color: const Color.fromARGB(255, 255, 255, 255), iconSize: 100.0, icon: const Icon(Icons.holiday_village), onPressed: () {}),
                        const Text("Places", style: TextStyle( color: Color.fromARGB(255, 255, 255, 255),),)
                      ],
                    )

                  ),
                ),
             
                Container(
                  color: Colors.grey,
                  child: Center(
                    child: Column(
                      children: [
                        IconButton(color: const Color.fromARGB(255, 255, 255, 255), iconSize: 100.0, icon: const Icon(Icons.manage_accounts), onPressed: () {
                          Navigator.of(context).pop(); // close drawer
                          Navigator.of(context).push(MaterialPageRoute<void>(
                            builder: (context) => ListGroup(userGroup: userGroup),
                          ));
                        }),
                        const Text("Users", style: TextStyle( color: Color.fromARGB(255, 255, 255, 255),),)
                      ],
                    )

                  ),
                ),
          ],
        )
      )
      
      
    );
  }


}