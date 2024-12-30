import 'dart:ffi';

import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:milestone3/Data/data.dart';
import 'package:milestone3/Data/data.dart' as Data;
import 'package:milestone3/Data/requests.dart';
import 'package:milestone3/assets/MyColors.dart';

class DoorItem extends StatefulWidget {

  final Door doorItem;

  const DoorItem({
    super.key,
    required this.doorItem,
  });



  
  @override
    State<StatefulWidget> createState() => _DoorItemState();
  }
  
  class _DoorItemState extends State<DoorItem> {

    setDoorState(String command){
      Requests requests = Requests();
      requests.setDoorState(widget.doorItem, command);

      setState(() {
      });
    }
    open(){
      setDoorState(Data.Actions.open);
    }
    close(){
      setDoorState(Data.Actions.close);
    }
    lock(){
      setDoorState(Data.Actions.lock);
    }
    unlock(){
      setDoorState(Data.Actions.unlock);
    }

    @override
    Widget build(BuildContext context) {
      return ListTile(
        contentPadding: const EdgeInsets.symmetric(horizontal: 16.0),
        title: Text(
          widget.doorItem.name,
          style: const TextStyle(fontSize: 25),
        ),
        trailing: Row(
          mainAxisSize: MainAxisSize.min,
          children: getDoorStates(widget.doorItem, open, close, lock, unlock),
        ),
        
      );
    }
  }



  List<Widget> getDoorStates(Door d, open, close, lock, unlock){
    if(d.state == DoorState.closedLocked){
      return [ButtonClosed(func:open), ButtonLocked(func:unlock)];
    }else if(d.state == DoorState.openLocked){
      return [ButtonOpen(func:close), ButtonLocked(func:unlock)];
    }else if(d.state == DoorState.closedUnlocked){
      return [ButtonClosed(func:open), ButtonUnlocked(func:lock)];
    }else if(d.state == DoorState.openUnlocked){
      return [ButtonOpen(func:close), ButtonUnlocked(func:lock)];
    }

    return [];
  }

  class ButtonLocked extends StatelessWidget{

    final func;

  const ButtonLocked({super.key, required this.func});
    @override
    Widget build(BuildContext context) {
      return IconButton(
        icon: const Icon(Icons.lock_outline),
        color: myColors.Grey_Medium,
        style: ButtonStyle(
          backgroundColor: WidgetStateProperty.all<Color>(myColors.Red),
        ),
        onPressed: func
      );
            
          
    }
  }

  class ButtonUnlocked extends StatelessWidget{
    final func;

  const ButtonUnlocked({super.key, required this.func});
    @override
    Widget build(BuildContext context) {
      return IconButton(
        icon: const Icon(Icons.lock_open),
        color: myColors.Grey_Medium,
        style: ButtonStyle(
          backgroundColor: WidgetStateProperty.all<Color>(myColors.Green),
        ),
        onPressed: func
      );
    }
  }

  class ButtonOpen extends StatelessWidget{
        final func;

  const ButtonOpen({super.key, required this.func});

    @override
    Widget build(BuildContext context) {
      return IconButton(
        icon: const Icon(Icons.meeting_room),
        color: myColors.Grey_Medium,
        style: ButtonStyle(
          backgroundColor: WidgetStateProperty.all<Color>(myColors.Green),
        ),
        onPressed: func
      );
    }
  }

  class ButtonClosed extends StatelessWidget{
        final func;

  const ButtonClosed({super.key, required this.func});

    @override
    Widget build(BuildContext context) {
      return IconButton(
        icon: const Icon(Icons.door_back_door),
        color: myColors.Grey_Medium,
        style: ButtonStyle(
          backgroundColor: WidgetStateProperty.all<Color>(myColors.Red),
        ),
        onPressed: func
      );
    }
  }
