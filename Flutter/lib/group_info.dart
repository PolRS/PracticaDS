import 'package:flutter/material.dart';

import 'data.dart';
import 'the_drawer.dart';

class GroupInfo extends StatefulWidget {
  UserGroup userGroup;

  GroupInfo({super.key, required this.userGroup});

  @override
  State<GroupInfo> createState() => _GroupInfo();
}

class _GroupInfo extends State<GroupInfo> {
  late UserGroup userGroup;
  final TextEditingController _controller_name = TextEditingController(); // Create a controller
  final TextEditingController _controller_desc = TextEditingController(); // Create a controller

  @override
  void initState() {
    super.initState();
    userGroup = widget.userGroup; // the userGroups of ScreenListGroups
  }

  @override
  Widget build(BuildContext context) {
    _controller_name.text = userGroup.name;
    _controller_desc.text = userGroup.description;
    return Scaffold(
      drawer: TheDrawer(context).drawer,
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.primary,
        foregroundColor: Theme.of(context).colorScheme.onPrimary,
        title: Text("Group ${userGroup.name}"),
      ),
      body: Center(
        child: Container(
          padding: const EdgeInsets.all(40.0),
            child: Form(
            child: Container(
              child: Column(
                children: [
                  const SizedBox(height: 20.0),

                  TextFormField(
                    controller: _controller_name, // Bind the controller to the text field
                    decoration: const InputDecoration(
                      border: OutlineInputBorder(),
                      labelText: "Group Name",
                    ),
                  ),

                  const SizedBox(height: 20.0),

                  TextFormField(
                      maxLines: 5,

                    controller: _controller_desc, // Bind the controller to the text field
                    decoration: const InputDecoration(
                      border: OutlineInputBorder(),
                      labelText: "Description",
                    ),
                  ),

                  Padding(
                    padding: const EdgeInsets.all(40.0),
                    child: ElevatedButton(
                      onPressed: () {
                        String name = _controller_name.text;
                        String desc = _controller_desc.text;
                        if(name != "") {
                          userGroup.name = name;
                          userGroup.description = desc;
                          ScaffoldMessenger.of(context).showSnackBar(
                            const SnackBar(
                              content: Text("Saved"),
                            ),
                          );
                        }else{
                          ScaffoldMessenger.of(context).showSnackBar(
                            const SnackBar(
                              content: Text("Name cannot be empty"),
                            ),
                          );
                        }
                      },
                      child: const Text("Save"),
                    ),
                  )
                ],
              ) 
              ) 
          ),
        ),
      )
      
      
    );
  }
}