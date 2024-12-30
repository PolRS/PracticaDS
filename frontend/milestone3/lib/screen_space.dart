import 'package:flutter/material.dart';
import 'Data/tree.dart';
import 'Data/request.dart' as rq;
import 'package:milestone3/Data/data.dart';
import 'user_info.dart';



class ScreenSpace extends StatefulWidget {
  final String id;
  const ScreenSpace({super.key, required this.id});

  @override
  State<ScreenSpace> createState() => _ScreenSpaceState();
}

class _ScreenSpaceState extends State<ScreenSpace> {
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
                IconButton(icon: const Icon(Icons.home), onPressed: () {}
                  // TODO go home page = root
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

  Widget _buildRow(Door door, int index) {
    return ListTile(
      title: Text('D ${door.id}'),
      trailing: Row(
        mainAxisSize: MainAxisSize.min, // Ensures the Row takes up minimal space
        children: [
          Text('${door.state}, closed=${door.closed}'),
          const SizedBox(width: 8), // Optional spacing between text and button
          IconButton(
            icon: const Icon(Icons.lock_open),
            onPressed: ()  {
              rq.openRequest("12345", "open", "2024-12-24T11:30", '${door.id}' );
              setState(() {
                futureTree = rq.getTree(widget.id);
              });
              // Action for the button
              print('Unlock button pressed for Door ${door.id}');
            },
          ),
          // Add more buttons if needed
          IconButton(
            icon: const Icon(Icons.lock),
            onPressed: () {
              rq.openRequest("12345", "close", "2024-12-24T11:30", '${door.id}' ).then;
              setState(() {
                futureTree = rq.getTree(widget.id);
              });
              // Action for the button
              print('Lock button pressed for Door ${door.id}');
            },
          ),
        ],
      ),
    );
  }


}
