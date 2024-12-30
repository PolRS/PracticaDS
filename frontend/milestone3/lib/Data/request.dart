import 'package:http/http.dart' as http;
import 'dart:convert' as convert;

import 'tree.dart';

Future<Tree> getTree(String areaId) async {
  const String baseUrl = "http://localhost:8080";


  Uri uri = Uri.parse("$baseUrl/get_children?$areaId");
  final response = await http.get(uri);
  // response is NOT a Future because of await
  if (response.statusCode == 200) {
    // TODO: change prints by logs, use package Logger for instance
    // which is the most popular, see https://pub.dev/packages/logger
    print("statusCode=$response.statusCode");
    print(response.body);
    // If the server did return a 200 OK response, then parse the JSON.
    Map<String, dynamic> decoded = convert.jsonDecode(response.body);

    return Tree(decoded);
  } else {
    // If the server did not return a 200 OK response, then throw an exception.
    print("statusCode=$response.statusCode");
    throw Exception('failed to get answer to request $uri');
  }
}

Future<bool> openRequest (String credential, String action, String time,String doorId) async {
  const String baseUrl = "http://localhost:8080";
  //-/reader?credential=12345&action=close&datetime=2024-12-24T11:30&doorId=D3
  Uri uri = Uri.parse(
      "$baseUrl/reader?credential=$credential&action=$action&datetime=$time&doorId=$doorId");
  final response = await http.get(uri);

  if (response.statusCode == 200) {
    // TODO: change prints by logs, use package Logger for instance
    // which is the most popular, see https://pub.dev/packages/logger
    print("statusCode=$response.statusCode");
    print(response.body);

    return true;
  } else{
    print("statusCode=$response.statusCode");
    throw Exception('failed to get answer to request $uri');
  }

}


