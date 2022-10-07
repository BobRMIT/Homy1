import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:localstorage/localstorage.dart';

class NotificationsPage extends StatelessWidget{



  @override
  Widget build(BuildContext context) {

    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(

        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);

  @override
  State<MyHomePage> createState() => _MyHomePageState();
  final String title;
}

class _MyHomePageState extends State<MyHomePage> {
  List<String> entries = ['A', 'B', 'C'];


  Future<void> BookingsList() async {
    LocalStorage storage = LocalStorage('key');
    Map<String, dynamic> userInfo = jsonDecode(storage.getItem('getID'));
    print(userInfo['id']);

    final Data = await http.get(
        Uri.parse("http://localhost:8080/booking/GetBookingList/${userInfo['id']}/")

    );

    String info = Data.body.toString();
    print(info);
    info = info.replaceAll(']','').replaceAll('[','').replaceAll(',','');
    print(info);
    info = info.substring(1, info.length-1);
    print(info);

    setState(() {
      entries = info.split('""');
    });
  }


  @override
  void initState() {
    super.initState();

    WidgetsBinding.instance.addPostFrameCallback((
        _) {BookingsList();}); // This line is magical, calling the future to create the present, absolutely beautiful

  }


  @override
  Widget build(BuildContext context) {
    var FrameWidth = MediaQuery
        .of(context)
        .size
        .width;
    var FrameHeight = MediaQuery
        .of(context)
        .size
        .width;


    return Scaffold(
        appBar: AppBar(
          title: Text('Notifications'),
        ),
        body: Center(
            child: ListView.builder(
                padding: const EdgeInsets.all(8),
                itemCount: entries.length,
                itemBuilder: (BuildContext context, int index) {
                  return Container(
                    height: 50,

                    child: Center(child: Text('Booking: ${entries[index]}')),
                  );
                }
            )
        )
    );
  }
}