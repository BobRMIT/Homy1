import 'dart:convert';
import 'package:syncfusion_flutter_calendar/calendar.dart';

import 'package:flutter/material.dart';
import 'LogInForm.dart' as LoginPage;
import './page/ChatPage.dart' as ChatPage;
import './page/HomePage.dart' as HomePage;
import './page/NotificationsPage.dart' as NotificationsPage;
import './page/SettingsPage.dart' as SettingsPage;
import './page/BookingPage.dart' as BookingPage;
import 'package:http/http.dart' as http;

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
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

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {

  int pageIndex = 0;

  Future<void> BookingSetup() async { //seting up booking database, and check if functioning
    final Data = await http.get(
        Uri.parse("http://localhost:8080/booking/"));
    //print("Booking Setup");
}


  @override
  Widget build(BuildContext context) {

    var FrameWidth = MediaQuery.of(context).size.width;
    var FrameHeight = MediaQuery.of(context).size.width;

    final screens = [
      HomePage.HomePage(),
      ChatPage.ChatPage(),
      NotificationsPage.NotificationsPage(),
      SettingsPage.SettingsPage(),
      BookingPage.BookingPage(),
    ];
    return Scaffold(


      body: screens[pageIndex],
      bottomNavigationBar: NavigationBar(
        onDestinationSelected: (int index) {
          setState(() {
            //print(index);
            pageIndex = index;
            if (index == 4){
              BookingSetup(); //checking if booking has been setup
            }
          });
        },
        backgroundColor: Colors.blue, //blue colour for bottom
        selectedIndex: pageIndex,
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
          NavigationDestination(
            selectedIcon: Icon(Icons.settings),
            icon: Icon(Icons.settings),
            label: 'Settings',
          ),
          NavigationDestination(
            selectedIcon: Icon(Icons.health_and_safety),
            icon: Icon(Icons.health_and_safety),
            label: 'Booking',
          ),
        ],
      ),
    );
  }

}
