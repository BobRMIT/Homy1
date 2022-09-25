import 'dart:convert';

import 'package:flutter/material.dart';
import 'LogInForm.dart' as loginForm;
import 'package:http/http.dart' as http;
import 'signupForm.dart' as signupForm;


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

  int _counter = 0;

  _ToSignup(){
    print("To signup form");
    signupForm.main(); //Going back to menu
  }

  void _incrementCounter() {
    setState(() {
      // This call to setState tells the Flutter framework that something has
      // changed in this State, which causes it to rerun the build method below
      // so that the display can reflect the updated values. If we changed
      // _counter without calling setState(), then the build method would not be
      // called again, and so nothing would appear to happen.
      _counter++;
      _counter = _counter + 2;
    });
  }

  void _setTextState(String error){
    setState((){

      errorTxtController.text = error;
    });
  }

  //Mode 1 - Login, Mode 0 - Signup
  Future<void> _loginSignup(int mode) async {
    //final Data = await http.get(Uri.parse('http://localhost:8080/TestUser/1'));
    try {
      final Data = await http.get(
          Uri.parse("http://localhost:8080/users/"));
      if (Data.statusCode == 200) {
        print("BackEnd Active");
        if (mode == 1){
          loginForm.main();
          //Next Window
        }
        else if (mode == 0){
          signupForm.main();
        }


      } else {
        _setTextState("Failed to connect to API, status: ${Data.statusCode}");
        print("Failed to connect to API, status: ${Data.statusCode}");
      }
    } on Exception catch (_) {
      _setTextState("Failed to connect to API");
      //errorTxtController.text = "Failed to connect to API";
      throw Exception('Failed to connect to API');
    }
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
        //title: const Text("Hello"),
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

              // onPressed: () {},
              onPressed: () => _loginSignup(0),
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

              onPressed: () => _loginSignup(1),
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
