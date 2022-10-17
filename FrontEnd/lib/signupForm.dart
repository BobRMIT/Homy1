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
  //final BoolCheck = BooleanController()
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
    //print("Back to menu screen");
    MainPage.main(); //Going back to menu
  }

  bool VisChecking(status){
    return !status;
    //_isHidden = !_isHidden
  }


  /*
  * Creating user function
  * Error checks box inputs:
  *         No text box can be empty
  *         Username and Password cannot contain whitespace
  *         Username sent to API to check if it already exists
  * */
  Future<void> _SigningUp() async {
    try {
      if ((getUsername().isNotEmpty) && (getPassword().isNotEmpty) &&
          (getFirstName().isNotEmpty) && (getLastName().isNotEmpty)) {
        if (!getUsername().contains(' ')) {
          if (!getPassword().contains(' ')) {
            String username = getUsername();
            final CheckUsername = await http.get(
                Uri.parse("http://localhost:8080/users/check/$username/")); // checking if username already exists
                //print(CheckUsername.body);

            if (CheckUsername.body == "false") {
                  //print("adding new user");
              final response = await
              http.post(
                Uri.parse('http://localhost:8080/users/create/'),       // Creating user once if username is available
                headers: <String, String>{                              // creating json to send to API
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

              if (response.statusCode == 201) {
                _setTextStateSuc('Success');    //Created user if all clear
              } else {
                _setTextStateError("error: ${response.statusCode}"); //API functionality failed
              }
            } else {
              _setTextStateError("Username Already Taken");
            }
          } else {
            _setTextStateError("Password Contains Whitespace");
          }
        } else {
          _setTextStateError("Username Contains Whitespace");
        }
      } else {
        _setTextStateError("Empty Fields");
      }
    } on Exception catch (_) {
      _setTextStateError("Failed to connect to API"); //API connection failed
      //errorTxtController.text = "Failed to connect to API";
      //throw Exception('Failed to connect to API');
    }
  }



  @override
  Widget build(BuildContext context) {

    var FrameWidth = MediaQuery.of(context).size.width;
    var FrameHeight = MediaQuery.of(context).size.width;

    bool _isHidden = true;


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
        title: const Text("Sign Up"),
      ),
      body: Center(


        // Center is a layout widget. It takes a single child and positions it
        // in the middle of the parent.
        child: Column(

          mainAxisAlignment: MainAxisAlignment.start,
          children: <Widget>[

            //SizedBox(height: FrameHeight*0.2),
            SizedBox(height: FrameHeight*0.1),


            Container(
              padding: const EdgeInsets.all(10),
              child: TextField(
                controller: firstNameController,
                decoration: const InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: 'First name',
                  prefixIcon: Icon(Icons.person),
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
                  prefixIcon: Icon(Icons.person),
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
                  prefixIcon: Icon(Icons.person),
                ),
              ),
            ),

            Container(
              padding: const EdgeInsets.fromLTRB(10, 10, 10, 0),
              child: TextField(
                obscureText: _isHidden,
                controller: passwordController,
                decoration: InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: 'Password',
                  hintText: 'Password',
                  prefixIcon: const Icon(Icons.lock),
                  suffixIcon: IconButton(
                      icon: Icon(
                          VisChecking(_isHidden) ?
                          Icons.visibility_off : Icons.visibility  ),
                      onPressed: () {
                        VisChecking(_isHidden);
                        setState(() {

                          _isHidden = !VisChecking(_isHidden);

                        });
                      }
                  ),
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

              onPressed: _BackToMenu, // going back to menu
              style: ElevatedButton.styleFrom(
                  fixedSize: Size(FrameWidth * 0.6, FrameHeight * 0.1),
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

