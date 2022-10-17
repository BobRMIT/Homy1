import 'dart:convert';
import 'dart:io';


import 'package:flutter/material.dart';
import '/main.dart' as MainPage;
import 'package:localstorage/localstorage.dart';

// add      --no-sound-null-safety

///Logout function
_BackToMenu(){
  MainPage.main(); //Going back to menu
}


class SettingsPage extends StatelessWidget{

  static LocalStorage storage = LocalStorage('key');
  Map<String, dynamic> userInfo = jsonDecode(storage.getItem('getID'));

  @override
  Widget build(BuildContext context) => Scaffold(
    appBar: AppBar(
      title: Text('Settings User: ${userInfo['id']}'),
    ),


    body: Center(

       //child: Text('Settings', style: TextStyle(fontSize: 60)),
      child: ElevatedButton(

          onPressed: _BackToMenu, //logging out
              style: ElevatedButton.styleFrom(
              fixedSize: Size(120,40)),
          child: const Text('Logout'),
      )
    )
  );
}