import 'package:flutter/material.dart';
import 'package:milestone3/assets/custom_button.dart';
import 'Data/tree.dart';
import 'screen_space.dart';
import 'Data/request.dart' as rq;

class ScreenPartition extends StatefulWidget {
  final String id;
  const ScreenPartition({super.key, required this.id});

  @override
  State<ScreenPartition> createState() => _ScreenPartitionState();
}

class _ScreenPartitionState extends State<ScreenPartition> {
  late Future<Tree> futureTree;
  @override
  void initState() {
    super.initState();
    futureTree = rq.getTree(widget.id);
  }

  // future with listview
  // https://medium.com/nonstopio/flutter-future-builder-with-list-view-builder-d7212314e8c9
  @override
  Widget build(BuildContext context) {
    return FutureBuilder<Tree>(
      future: futureTree,
      builder: (context, snapshot) {
        // anonymous function
        if (snapshot.hasData) {
          return Scaffold(
            appBar: AppBar(
              backgroundColor: Theme.of(context).colorScheme.primary,
              foregroundColor: Theme.of(context).colorScheme.onPrimary,
              title: Text(snapshot.data!.root.id),
              actions: <Widget>[
                IconButton(icon: const Icon(Icons.home), onPressed: () {Navigator.popUntil(context, (route) => route.isFirst);}//Navigator.pushAndRemoveUntil( context, MaterialPageRoute(builder: (context) => HomePage()), (route) => false, // Remove all previous routes  );

                ),
                //TODO other actions
              ],
            ),
            body: ListView.separated(
              // it's like ListView.builder() but better because it includes a separator between items
              padding: const EdgeInsets.all(16.0),
              itemCount: snapshot.data!.root.children.length,
              itemBuilder: (BuildContext context, int i) =>
                  _buildRow(snapshot.data!.root.children[i], i),
              separatorBuilder: (BuildContext context, int index) =>
              const Divider(),
            ),
          );
        } else if (snapshot.hasError) {
          return Text("${snapshot.error}");
        }
        // By default, show a progress indicator
        return Container(
            height: MediaQuery.of(context).size.height,
            color: Colors.white,
            child: const Center(
              child: CircularProgressIndicator(),
            ));
      },
    );
  }

  Widget _buildRow(Area area, int index) {
    assert (area is Partition || area is Space);
    if (area is Partition) {
      return ListTile(
        title: Text('P ${area.id}'),
        onTap: () => _navigateDownPartition(area.id) ,
        trailing: Row(
          mainAxisSize: MainAxisSize.min ,
            children: [
              if( area.validateUnlock() )
                Icon(Icons.room_preferences)  ,
              if(area.validateLocked() )
                Icon(Icons.lock),
              IconButton(
              icon: const Icon(Icons.lock_open),
              onPressed: () async {
                bool successArea = await rq.AreaRequest("11343", "unlock", "2024-12-24T11:30", '${area.id}' );

                setState(() {
                  futureTree = rq.getTree(widget.id);
                  area.validateLocked();
                  area.validateUnlock();
                });


              },
            ),
              IconButton(
                icon: const Icon(Icons.lock),
                onPressed: () async {
                  await rq.AreaRequest("11343", "lock", "2024-12-24T11:30", '${area.id}' );
                  setState(()  {
                    futureTree = rq.getTree(widget.id);
                    area.validateLocked();
                    area.validateUnlock();
                  });
                  // Action for the button

                },
              ),

            ],
          
        ),

      );
    } else {
      return ListTile(
        title: Text('S ${area.id}'),
        onTap: () => _navigateDownSpace(area.id),
        trailing: Row(
          mainAxisSize: MainAxisSize.min ,
          children: [
            if( area.validateUnlock() )
              Icon(Icons.room_preferences)  ,
            if(area.validateLocked() )
              Icon(Icons.lock),
            IconButton(
              icon: const Icon(Icons.lock_open),
              onPressed: () async {
                await rq.AreaRequest("11343", "unlock", "2024-12-24T11:30", '${area.id}' );

                setState(() {
                  futureTree = rq.getTree(widget.id);
                  area.validateLocked();
                  area.validateUnlock();
                });


              },
            ),
            IconButton(
              icon: const Icon(Icons.lock),
              onPressed: () async {
                await rq.AreaRequest("11343", "lock", "2024-12-24T11:30", '${area.id}' );

                setState(() {
                  futureTree = rq.getTree(widget.id);
                  area.validateLocked();
                  area.validateUnlock();
                });
                // Action for the button

              },
            )],
        ),
      );
    }
  }
  void _refresh() async {
    futureTree = rq.getTree(widget.id);
    setState(() {});
  }
  void _navigateDownPartition(String childId) {

    Navigator.of(context)
        .push(MaterialPageRoute<void>(builder: (context) => ScreenPartition(id: childId,))
    );
  }
  void _navigateDownSpace(String childId) {
    Navigator.of(context)
        .push(MaterialPageRoute<void>(builder: (context) => ScreenSpace(id: childId,))
    );
  }


}
