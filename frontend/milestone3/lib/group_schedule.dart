import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:weekday_selector/weekday_selector.dart';

import 'Data/data.dart';
import 'the_drawer.dart';

class GroupSchedule extends StatefulWidget {
  UserGroup userGroup;

  GroupSchedule({super.key, required this.userGroup});

  @override
  State<GroupSchedule> createState() => _GroupSchedule();
}

class _GroupSchedule extends State<GroupSchedule> {
  late UserGroup userGroup;
  late Schedule temporarySchedule;
  late List<bool> selectedWeekdays;

  @override
  void initState() {
    super.initState();
    userGroup = widget.userGroup; // the userGroups of ScreenListGroups
    temporarySchedule = userGroup.schedule;
  }

  @override
  Widget build(BuildContext context) {

    final DateFormat dateFormatter = DateFormat('dd-MM-yyyy');

    temporarySchedule ??= userGroup.schedule;
    
    selectedWeekdays = List.filled(7, false);
    temporarySchedule.weekdays ??= List.empty();
    for(int i in temporarySchedule.weekdays){
      selectedWeekdays[i%7] = true;
    }


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
                  Row(
                    children: [
                      Text("From    ${dateFormatter.format(temporarySchedule.fromDate)}"),
                      IconButton(
                        icon: const Icon(Icons.calendar_today),
                        onPressed: () async {
                          await _PickStartDate();
                        },
                      ),
                    ],
                  ),

                  Row(  
                    children: [
                      Text("To         ${dateFormatter.format(temporarySchedule.toDate)}"),
                      IconButton(
                        icon: const Icon(Icons.calendar_today),
                        onPressed: () async {
                          await _PickEndDate();
                        },
                      ),
                    ],
                  ),

                  const SizedBox(height: 40),


                  const Row(children: [Text("Weekdays", textAlign: TextAlign.left,)]),
                  Container(
                    child: Column(
                      children: [
                      WeekdaySelector(
                        onChanged: (int day) async => await _PickWeekDays(day),
                        values: selectedWeekdays,
                      ),
                      ]
                    )
                  ),

                  const SizedBox(height: 40),

                  Row(
                    children: [
                      Text("From    ${temporarySchedule.fromTime.format(context)}"),
                      IconButton(
                        icon: const Icon(Icons.query_builder),
                        onPressed: () async {
                          await _PickStartTime();
                        },
                      ),
                    ],
                  ),

                   Row(
                    children: [
                      Text("From    ${temporarySchedule.toTime.format(context)}"),
                      IconButton(
                        icon: const Icon(Icons.query_builder),
                        onPressed: () async {
                          await _PickEndTime();
                        },
                      ),
                    ],
                  ),
                  
                  const SizedBox(height: 40),

                  ElevatedButton(onPressed: (){_SaveData(context);}, child: const Text("Submit")),

                ],
              ) 
              ) 
          ),
        ),
      )
      

      
    );
  }

    Future<void> _PickStartDate() async {
      DateTime? picked = await showDatePicker(
      context: context,
      initialDate: DateTime.now(),
      firstDate: DateTime(DateTime.now().year),
      lastDate: DateTime(DateTime.now().year+5),
      );
      if (picked != null) {
        setState(() {
          temporarySchedule.fromDate = picked;
        });
      }else{
        print("Date not selected");
      }
    }

    Future<void> _PickEndDate() async {
      DateTime? picked = await showDatePicker(
      context: context,
      initialDate: DateTime.now(),
      firstDate: DateTime(DateTime.now().year),
      lastDate: DateTime(DateTime.now().year+5),
      );
      if (picked != null) {
        setState(() {
          temporarySchedule.toDate = picked;
        });
      }else{
        print("Date not selected");
      }
    }

    _PickWeekDays(int day) async {
      setState(() {

        if(temporarySchedule.weekdays.contains(day)){
          temporarySchedule.weekdays.remove(day);
        }else{
          temporarySchedule.weekdays.add(day);
        }
        for(int i in temporarySchedule.weekdays){
          selectedWeekdays[i%7] = true;
        }
      });
     
    }

    Future<void> _PickStartTime() async {
      TimeOfDay? picked = await showTimePicker(
      context: context,
      initialTime: temporarySchedule.fromTime,
      );
      if (picked != null) {
        setState(() {
          temporarySchedule.fromTime = picked;
        });
      }else{
        print("Date not selected");
      }
    }

    Future<void> _PickEndTime() async {
      TimeOfDay? picked = await showTimePicker(
      context: context,
      initialTime: temporarySchedule.toTime,
      );
      if (picked != null) {
        setState(() {
          temporarySchedule.toTime = picked;
        });
      }else{
        print("Date not selected");
      }
    }

    _SaveData(context) {

      if(temporarySchedule.fromDate.isAfter(temporarySchedule.toDate)){
        _ShowAlert();
        return;
      }else{

        userGroup.schedule = temporarySchedule;
        

        ScaffoldMessenger.of(context).showSnackBar(
          const SnackBar(
            content: Text("Saved"),
          ),
        );
      }



    }

    _ShowAlert() {
      showDialog(
        context: context,
        builder: (BuildContext context) {
          return AlertDialog(
            title: const Text("Range Dates"),
            content: const Text("The From date is after the To date.\nPlease select a new data range."),
            actions: [
              TextButton(
                onPressed: () {
                  Navigator.of(context).pop();
                },
                child: const Text("Accept"),
              ),
            ],
          );
        },
      );
    }

}