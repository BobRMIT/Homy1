import 'dart:convert';


import 'package:flutter/material.dart';
import 'LogInForm.dart' as LoginPage;
import 'package:http/http.dart' as http;

void main(List<String> userInfoList) {
  final String ID = userInfoList[0];

  runApp(MyApp());
}


class MyApp extends StatelessWidget {
  //final ID;
  const MyApp({Key? key}) : super(key: key);
  // This widget is the root of your application.
  //const List<String> UserInfo = ["a"];
  //UserInfo[1];

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(

        primarySwatch: Colors.blue,

      ),
      home: const MyHomePage(title: "Title"),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {

  int currentPageIndex = 0;
  String dropdownvalue = '1';

  late List<RenderObjectWidget> pages = [
    Column(
        children: <Widget>[
          //Text(UserInfo[1], style: TextStyle(fontSize: 40)),
          const Text('Bookings', style: TextStyle(fontSize: 40)),

          DropdownButton(
            value: dropdownvalue,
            items: const [DropdownMenuItem(value: "1", child: Text("1")),
                          DropdownMenuItem(value: "2", child: Text("2")),
                          DropdownMenuItem(value: "3", child: Text("3"))],

            onChanged: (String? value) {
              setState(() {
                dropdownvalue = value!;
              });
            }
          ),
    ]),
    const Center(child: Text('Page 2', style: TextStyle(fontSize: 40))),

    const Center(
        child: ElevatedButton(
          onPressed: LoginPage.main,
          child: Text('B'),
          ),
      ),
  ];

  void _BackToLogin() {
    print("Back to Login screen");
    LoginPage.main(); //Going back to menu
  }

  @override
  Widget build(BuildContext context) {

    var FrameWidth = MediaQuery.of(context).size.width;
    var FrameHeight = MediaQuery.of(context).size.width;


    return Scaffold(
      body: pages[currentPageIndex],

      bottomNavigationBar: NavigationBar(
          selectedIndex: currentPageIndex,
          onDestinationSelected: (int index) {
            setState(() {
              currentPageIndex = index;
            });
        },

        destinations: const <Widget>[
          NavigationDestination(
            icon: Icon(Icons.home),
            label: 'Home',
          ),
          NavigationDestination(
            icon: Icon(Icons.chat_bubble),
            label: 'Chat',
          ),
          NavigationDestination(
            selectedIcon: Icon(Icons.notifications),
            icon: Icon(Icons.notification_add),
            label: 'Notifications',
          ),
        ],
      ),

      appBar: AppBar(
        // Here we take the value from the MyHomePage object that was created by
        // the App.build method, and use it to set our appbar title.
        title: const Text('Hello'),
      ),
    );
  }
}
