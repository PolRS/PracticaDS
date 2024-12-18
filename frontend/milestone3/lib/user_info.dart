import 'package:flutter/material.dart';

import 'data.dart';
import 'the_drawer.dart';

class UserInfo extends StatefulWidget {
  User user;

  UserInfo({super.key, required this.user});

  @override
  State<UserInfo> createState() => _UserInfo();
}

class _UserInfo extends State<UserInfo> {
  late User user;
  final TextEditingController _controller_name = TextEditingController(); // Create a controller
  final TextEditingController _controller_cred = TextEditingController(); // Create a controller

  @override
  void initState() {
    super.initState();
    user = widget.user; // the userGroups of ScreenListGroups
  }

  @override
  Widget build(BuildContext context) {
    _controller_name.text = user.name;
    _controller_cred.text = user.credential;
    return Scaffold(
      drawer: TheDrawer(context).drawer,
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.primary,
        foregroundColor: Theme.of(context).colorScheme.onPrimary,
        title: Text("Group ${user.name}"),
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
                      labelText: "Name",
                    ),
                  ),

                  const SizedBox(height: 20.0),

                  TextFormField(
                    controller: _controller_cred, // Bind the controller to the text field
                    decoration: const InputDecoration(
                      border: OutlineInputBorder(),
                      labelText: "Credential",
                    ),
                  ),

                  Padding(
                    padding: const EdgeInsets.all(40.0),
                    child: ElevatedButton(
                      onPressed: () {
                        String name = _controller_name.text;
                        String cred = _controller_cred.text;
                        if(name == "" || cred == "") {

                           ScaffoldMessenger.of(context).showSnackBar(
                            const SnackBar(
                              content: Text("Empty Name or Credential"),
                            ),
                          );
                        }else if(cred.length != 5){
                          ScaffoldMessenger.of(context).showSnackBar(
                            const SnackBar(
                              content: Text("Credential must be 5 characters long"),
                            ),
                          );

                        }{
                          user.name = name;
                          user.credential = cred;
                          ScaffoldMessenger.of(context).showSnackBar(
                            const SnackBar(
                              content: Text("Saved"),
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