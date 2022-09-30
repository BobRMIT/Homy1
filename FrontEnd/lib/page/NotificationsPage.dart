import 'package:flutter/material.dart';

class NotificationsPage extends StatelessWidget{
  @override
  Widget build(BuildContext context) => Scaffold(
    appBar: AppBar(
      title: Text('Notifications'),
    ),
    body: Center(child: Text('Notifications', style: TextStyle(fontSize: 60),)),
  );
}