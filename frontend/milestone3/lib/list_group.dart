

import 'package:milestone3/data.dart';
import 'package:milestone3/user_info.dart';
import 'package:flutter/material.dart';

import 'data.dart' as Data;
import 'the_drawer.dart';

class ListGroup extends StatefulWidget {
  Data.UserGroup userGroup;

  ListGroup({super.key, required this.userGroup});

  @override
  State<ListGroup> createState() => _ListGroupState();
}

class _ListGroupState extends State<ListGroup> {
  late Data.UserGroup userGroup;

  @override
  void initState() {
    super.initState();
    userGroup = widget.userGroup; // the userGroups of ScreenListGroups
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      floatingActionButton: FloatingActionButton(
        child: const Icon(Icons.add),
        onPressed: () {
  
          User u = User("New User", "New Credential");
          userGroup.users.add(u);
          setState(() {});

              setState(() {});
        },
      ),
      drawer: TheDrawer(context).drawer,
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.primary,
        foregroundColor: Theme.of(context).colorScheme.onPrimary,
        title: const Text("User groups"),
      ),
      body: ListView.separated(
        // it's like ListView.builder() but better
        // because it includes a separator between items
        padding: const EdgeInsets.all(16.0),
        itemCount: userGroup.users.length,
        itemBuilder: (BuildContext context, int index) =>
            _buildRow(userGroup.users[index], index),
        separatorBuilder: (BuildContext context, int index) => const Divider(),
      ),
    );
  }

  Widget _buildRow(Data.User user, int index) {

    return ListTile(

      title: Text(user.name),

      trailing: Text(user.credential),
      onTap: () {
        Navigator.of(context).push(MaterialPageRoute<void>(
          builder: (context) => UserInfo(user: user),
        ));
      },
    );
  }
}
