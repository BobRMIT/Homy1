import 'dart:convert';
import 'dart:io';

import 'package:flutter/material.dart';
import 'main.dart' as MainPage;
import 'package:http/http.dart' as http;
import 'HomePage.dart' as HomePage;
import 'package:localstorage/localstorage.dart';


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

  @override
  State<MyHomePage> createState() => _MyHomePageState();

  final String title;
}


class _MyHomePageState extends State<MyHomePage> {
  final usernameController = TextEditingController();
  final passwordController = TextEditingController();
  final errorTxtController = TextEditingController();


  String getUsername() {
      return usernameController.text;
  }

  String getPassword() {
      return passwordController.text;
  }

  void _setTextState(String error) {
    setState(() {
      errorTxtController.text = error;
    });
  }

/// exit login screen
   _BackToMenu(){
    MainPage.main(); //Going back to menu
  }

  ///Login method
  Future<void> _HomeScreen() async {
    String username = getUsername();
    String password = getPassword();

    if ((username.isNotEmpty) && (password.isNotEmpty)){
      if ((!username.contains(' ')) && (!password.contains(' '))) {
        try {
          final Data = await http.get(
              Uri.parse("http://localhost:8080/users/$username/$password/")); //sending login details to API

          if (Data.body.isEmpty) {          //invalid credentials given
            _setTextState("Invalid Credentials");

          } else if(Data.statusCode == 200) { //login successful
            //print(Data.body);
            LocalStorage storage = LocalStorage('key');
            storage.setItem('getID', Data.body);          // saves user ID to Cache when logging in
            HomePage.main();

          }else { //error within API
            _setTextState("Fatal error, statusCode: ${Data.statusCode}");
          }

        } on Exception catch (_) { // error connecting with API
          _setTextState("Failed to connect to API");
          //throw Exception('Failed to connect to API');
        }
      }else {
        //print("Whitespace");
        _setTextState("Fields Contain Whitespace");
      }
    } else {
      //print("Blank");
      _setTextState("Blank Fields");
    }

  }


  @override
  Widget build(BuildContext context) {

    var FrameWidth = MediaQuery.of(context).size.width;
    var FrameHeight = MediaQuery.of(context).size.width;

    bool _isHidden = true;

    return Scaffold(

      appBar: AppBar(
        title: const Text("Log In"),
      ),
      body: Center(

        child: Column(

          mainAxisAlignment: MainAxisAlignment.start,
          children: <Widget>[

            SizedBox(height: FrameHeight*0.1),

            SizedBox(
              width: FrameWidth * 0.7,

              child: TextFormField(
                controller: usernameController,
                keyboardType: TextInputType.emailAddress, // Use email input type for emails.
                decoration: InputDecoration(
                    border: OutlineInputBorder(borderRadius: BorderRadius.circular(10)),
                    contentPadding: const EdgeInsets.symmetric(horizontal: 20.0),
                    hintText: 'Username',
                    labelText: 'Username',
                  prefixIcon: const Icon(Icons.person),
                ),
              ),
            ),

            SizedBox(height: FrameHeight*0.03),

            SizedBox(
              width: FrameWidth * 0.7,
              child: TextFormField(
                obscureText: true,
                controller: passwordController,
                keyboardType: TextInputType.emailAddress, // Use email input type for emails.
                decoration: InputDecoration(
                    border: OutlineInputBorder(borderRadius: BorderRadius.circular(10)),
                    contentPadding: EdgeInsets.symmetric(horizontal: 20.0),
                    hintText: 'Password',
                    labelText: 'Password',
                  prefixIcon: const Icon(Icons.lock),
                  suffixIcon: IconButton(
                      icon: Icon(
                          _isHidden
                              ? Icons.visibility
                              : Icons.visibility_off ),
                      onPressed: () {
                        setState(() {
                          _isHidden = !_isHidden;
                        });
                      }
                  ),
                ),
              ),

            ),

            SizedBox(height: FrameHeight*0.03),

            Align( alignment: const Alignment(-0.6,-1),
              child:
              SizedBox(

                child: Text(errorTxtController.text,
                  style: const TextStyle(fontWeight: FontWeight.bold, color: Colors.red),
                ),
              ),
            ),

            SizedBox(height: FrameHeight*0.1),

            ElevatedButton(
              onPressed: _HomeScreen, //login method
              style: ElevatedButton.styleFrom(
                  fixedSize: Size(FrameWidth * 0.6, FrameHeight*0.1),
                  primary: Colors.blue,
                  onPrimary: Colors.black,
                  padding: const EdgeInsets.symmetric(horizontal: 50, vertical: 20),
                  textStyle: const TextStyle(
                      fontSize: 20,
                      fontWeight: FontWeight.bold)
              ),
              child: const Text('Log In'),
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