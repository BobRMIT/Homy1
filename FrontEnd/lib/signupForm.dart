import 'dart:convert';

import 'package:flutter/material.dart';
import 'main.dart' as MainPage;
import 'package:http/http.dart' as http;
import 'HomePage.dart' as HomePage;

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

        // This is the theme of your application.
        //
        // Try running your application with "flutter run". You'll see the
        // application has a blue toolbar. Then, without quitting the app, try
        // changing the primarySwatch below to Colors.green and then invoke
        // "hot reload" (press "r" in the console where you ran "flutter run",
        // or simply save your changes to "hot reload" in a Flutter IDE).
        // Notice that the counter didn't reset back to zero; the application
        // is not restarted.
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
  final usernameController = TextEditingController();
  final firstNameController = TextEditingController();
  final lastNameController = TextEditingController();
  final passwordController = TextEditingController();
  final errorTxtController = TextEditingController();
  Color selectionColor = Colors.red;




  String getUsername() {
    return usernameController.text;
  }

  String getPassword() {
    return passwordController.text;
  }

  String getFirstName() {
    return firstNameController.text;
  }

  String getLastName() {
    return lastNameController.text;
  }

  void _setTextStateError(String error) {
    setState(() {
      selectionColor = Colors.red;
      errorTxtController.text = error;
    });
  }

  void _setTextStateSuc(String error) {
    setState(() {
      selectionColor = Colors.green;
      errorTxtController.text = error;

    });
  }

  Color getTextColour() {
    return selectionColor;
  }


  _BackToMenu(){
    print("Back to menu screen");
    MainPage.main(); //Going back to menu
  }

  Future<void> _SigningUp() async {

    if ((getUsername().isNotEmpty) && (getPassword().isNotEmpty) && (getFirstName().isNotEmpty) && (getLastName().isNotEmpty)){
      if (!getUsername().contains(' ')){
        if (!getPassword().contains(' ')){
          final response = await
          http.post(
            Uri.parse('http://localhost:8080/create/'),
            headers: <String, String>{
              'Content-Type': 'application/json; charset=UTF-8',
            },
            body: jsonEncode(<String, String>{
              'username': getUsername(),
              'password': getPassword(),
              'firstName': getFirstName(),
              'lastName': getLastName(),
              'permission': 'User'
            }),
          );
          if (response.body == 'Success') {
            _setTextStateSuc(response.body);
          }else {
            _setTextStateError(response.body);
          }

        }else{
          _setTextStateError("Password Contains Whitespace");
        }
      }else{
        _setTextStateError("Username Contains Whitespace");
      }

    }else{
      _setTextStateError("Empty Fields");
    }
  }


  _HomeScreen(){
    print("To home page");
    HomePage.main();
  }




  @override
  Widget build(BuildContext context) {

    var FrameWidth = MediaQuery.of(context).size.width;
    var FrameHeight = MediaQuery.of(context).size.width;


    // This method is rerun every time setState is called, for instance as done
    // by the _incrementCounter method above.
    //
    // The Flutter framework has been optimized to make rerunning build methods
    // fast, so that you can just rebuild anything that needs updating rather
    // than having to individually change instances of widgets.

    return Scaffold(


      appBar: AppBar(
        // Here we take the value from the MyHomePage object that was created by
        // the App.build method, and use it to set our appbar title.
        title: const Text("Hello"),
      ),
      body: Center(


        // Center is a layout widget. It takes a single child and positions it
        // in the middle of the parent.
        child: Column(



          // Column is also a layout widget. It takes a list of children and
          // arranges them vertically. By default, it sizes itself to fit its
          // children horizontally, and tries to be as tall as its parent.
          //
          // Invoke "debug painting" (press "p" in the console, choose the
          // "Toggle Debug Paint" action from the Flutter Inspector in Android
          // Studio, or the "Toggle Debug Paint" command in Visual Studio Code)
          // to see the wireframe for each widget.
          //
          // Column has various properties to control how it sizes itself and
          // how it positions its children. Here we use mainAxisAlignment to
          // center the children vertically; the main axis here is the vertical
          // axis because Columns are vertical (the cross axis would be
          // horizontal).
          mainAxisAlignment: MainAxisAlignment.start,
          children: <Widget>[

            //SizedBox(height: FrameHeight*0.2),
            SizedBox(height: FrameHeight*0.1),

            // TextFormField(
            //   controller: usernameController,
            //   keyboardType: TextInputType.emailAddress, // Use email input type for emails.
            //   decoration: const InputDecoration(
            //       contentPadding: EdgeInsets.symmetric(horizontal: 40.0),
            //       hintText: 'Username',
            //       labelText: 'Username'
            //   ),
            // ),

            Container(
              padding: const EdgeInsets.all(10),
              child: TextField(
                controller: firstNameController,
                decoration: const InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: 'First name',
                ),
              ),
            ),
            Container(
              padding: const EdgeInsets.all(10),
              child: TextField(
                controller: lastNameController,
                decoration: const InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: 'Last name',
                ),
              ),
            ),
            Container(
              padding: const EdgeInsets.all(10),
              child: TextField(
                controller: usernameController,
                decoration: const InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: 'Username',
                ),
              ),
            ),
            // TextFormField(
            //   controller: passwordController,
            //   keyboardType: TextInputType.emailAddress, // Use email input type for emails.
            //   decoration: const InputDecoration(
            //       contentPadding: EdgeInsets.symmetric(horizontal: 40.0),
            //       hintText: 'Password',
            //       labelText: 'Password',
            //   ),
            // ),

            Container(
              padding: const EdgeInsets.fromLTRB(10, 10, 10, 0),
              child: TextField(
                obscureText: true,
                controller: passwordController,
                decoration: const InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: 'Password',
                  hintText: 'Password',
                ),
              ),
            ),


            SizedBox(height: FrameHeight*0.05),


            Align( alignment: const Alignment(-0.9,-1),
              child:
              SizedBox(

                child: Text(errorTxtController.text,
                  style: TextStyle(fontWeight: FontWeight.bold, color: getTextColour()),
                ),
              ),
            ),

            SizedBox(height: FrameHeight*0.05),

            ElevatedButton(

              onPressed: _SigningUp,
              style: ElevatedButton.styleFrom(
                  fixedSize: Size(FrameWidth * 0.6, FrameHeight*0.1),
                  primary: Colors.blue,
                  onPrimary: Colors.black,
                  padding: const EdgeInsets.symmetric(horizontal: 50, vertical: 20),
                  textStyle: const TextStyle(
                      fontSize: 20,
                      fontWeight: FontWeight.bold)
              ),
              child: const Text('Sign Up'),
            ),

            SizedBox(height: FrameHeight*0.05),


            ElevatedButton(

              onPressed: _BackToMenu,
              style: ElevatedButton.styleFrom(
                  fixedSize: Size(FrameWidth * 0.6, FrameHeight*0.1),
                  primary: Colors.blue,
                  onPrimary: Colors.black,
                  padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 20),
                  textStyle: const TextStyle(
                      fontSize: 20,
                      fontWeight: FontWeight.bold)
              ),
              child: const Text('Back'),
            ),

          ],
        ),
      ),
    );
  }
}