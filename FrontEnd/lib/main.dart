import 'dart:convert';

import 'package:flutter/material.dart';
import 'LogInForm.dart' as loginForm;
import 'package:http/http.dart' as http;
import 'signupForm.dart' as signupForm;
import 'AdminSignupForm.dart' as adminSignupForm;


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

  // This widget is the home page of your application. It is stateful, meaning
  // that it has a State object (defined below) that contains fields that affect
  // how it looks.

  // This class is the configuration for the state. It holds the values (in this
  // case the title) provided by the parent (in this case the App widget) and
  // used by the build method of the State. Fields in a Widget subclass are
  // always marked "final".

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {

  final errorTxtController = TextEditingController();

  void _setTextState(String error){
    setState((){

      errorTxtController.text = error;
    });
  }

  /*  Mode 1 - Login,
      Mode 0 - Signup
      moving to Login or Signup Page
  */
  Future<void> _loginSignup(int mode) async {
    //final Data = await http.get(Uri.parse('http://localhost:8080/TestUser/1'));
    try {
      final Data = await http.get(
          Uri.parse("http://localhost:8080/users/")); //checks if backend is active before progressing
      if (Data.statusCode == 200) {
        print("BackEnd Active");
        if (mode == 1){
          loginForm.main();
          //Next Window
        }
        else if (mode == 0){
          signupForm.main();
        }


      } else {// showing user error code
        _setTextState("Failed to connect to API, status: ${Data.statusCode}");
        //print("Failed to connect to API, status: ${Data.statusCode}");
      }
    } on Exception catch (_) { // catching any unexpected error
      _setTextState("Failed to connect to API");
      //throw Exception('Failed to connect to API');
    }
  }

  @override
  Widget build(BuildContext context) {

    var FrameWidth = MediaQuery.of(context).size.width;
    var FrameHeight = MediaQuery.of(context).size.width;

    return Scaffold(


      appBar: AppBar(
        // Here we take the value from the MyHomePage object that was created by
        // the App.build method, and use it to set our appbar title.
        //title: const Text("Hello"),
      ),
      body: Center(

        // Center is a layout widget. It takes a single child and positions it
        // in the middle of the parent.
        child: Column(

          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[

            const Text(
              "Lorem ipsum Temp Image\n"
                  "Logo here in the future?"
            ),
            Image.asset("images/LogoTemp.jpg",height: 200,
              width: 400,),


            SizedBox(height: FrameHeight*0.1),

            ElevatedButton(

              onPressed: () => _loginSignup(0), //to signup page
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


            SizedBox(height: FrameHeight*0.1),


            ElevatedButton(

              onPressed: () => _loginSignup(1), //to login page
              style: ElevatedButton.styleFrom(
                  fixedSize: Size(FrameWidth * 0.6, FrameHeight*0.1),
                  primary: Colors.blue,
                  onPrimary: Colors.black,
                  padding: const EdgeInsets.symmetric(horizontal: 50, vertical: 20),
                  textStyle: const TextStyle(
                      fontSize: 20,
                      fontWeight: FontWeight.bold)
              ),
              child: const Text('Login'),
            ),
            SizedBox(height: FrameHeight*0.05),

            Text(
              errorTxtController.text,
              style: const TextStyle(fontWeight: FontWeight.bold, color: Colors.red),
            ),
          ],
        ),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}
