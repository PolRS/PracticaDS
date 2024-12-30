import 'package:milestone3/assets/MyColors.dart';
import 'package:milestone3/Data/data.dart' as data;
import 'package:flutter/material.dart';

import 'the_drawer.dart';

class GroupActions extends StatefulWidget {
  data.UserGroup userGroup;

  GroupActions({super.key, required this.userGroup});

  @override
  State<GroupActions> createState() => _GroupActions();
}

class _GroupActions extends State<GroupActions> {
  late data.UserGroup userGroup;
  late List<String> temporaryActions;

  @override
  void initState() {
    super.initState();
    userGroup = widget.userGroup; // the userGroups of ScreenListGroups
    temporaryActions = userGroup.actions;
  }

  @override
  Widget build(BuildContext context) {

    temporaryActions = userGroup.actions;

    return Scaffold(
      drawer: TheDrawer(context).drawer,
      appBar: AppBar(
        backgroundColor: myColors.Grey_Dark,
        foregroundColor: myColors.Grey_Light,
        title: Text("Group ${userGroup.name}"),
      ),
      body: Center(
        child: Container(
          padding: const EdgeInsets.all(10.0),
            child: Form(
            child: Container(
              child: Column(
                children: [
                  CheckboxListTile(
                    title: const Text("Opens unlocked door"),
                    subtitle: const Text("Open"),
                    value: temporaryActions.contains(data.Actions.open), 
                    onChanged: (bool? value) => _OnUpdate(data.Actions.open),
                    ),
                  const Divider(),

                  CheckboxListTile(
                    title: const Text("Closes an open door"),
                    subtitle: const Text("Close"),
                    value: temporaryActions.contains(data.Actions.close), 
                    onChanged: (bool? value) => _OnUpdate(data.Actions.close),
                    ),
                  const Divider(),

                  CheckboxListTile(
                    title: const Text("Locks unlocked door"),
                    subtitle: const Text("Lock"),
                    value: temporaryActions.contains(data.Actions.lock), 
                    onChanged: (bool? value) => _OnUpdate(data.Actions.lock),
                    ),
                  const Divider(),

                  CheckboxListTile(
                    title: const Text("Unlocks locked door"),
                    subtitle: const Text("Unlock"),
                    value: temporaryActions.contains(data.Actions.unlock), 
                    onChanged: (bool? value) => _OnUpdate(data.Actions.unlock),
                    ),
                  const Divider(),

                  CheckboxListTile(
                    title: const Text("Shortly unlocks door"),
                    subtitle: const Text("Unlock Shortly"),
                    value: temporaryActions.contains(data.Actions.unlockShortly), 
                    onChanged: (bool? value) => _OnUpdate(data.Actions.unlockShortly),
                    ),


                  
                  const SizedBox(height: 40),

                  ElevatedButton(onPressed: (){_SaveData();}, child: const Text("Submit")),

                ],
              ) 
              ) 
          ),
        ),
      )
      

      
    );
  }

   _OnUpdate(String action){
    setState(() {
      if(temporaryActions.contains(action)){
        temporaryActions.remove(action);
      }else{
        temporaryActions.add(action);
      } 
    });

  }

  _SaveData(){
    setState(() {
      userGroup.actions = temporaryActions;
    });
  }


    

}