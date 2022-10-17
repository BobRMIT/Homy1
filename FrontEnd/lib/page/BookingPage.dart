import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:dropdown_button2/dropdown_button2.dart';
import 'package:intl/intl.dart';
import 'package:http/http.dart' as http;
import 'package:localstorage/localstorage.dart';


class BookingPage extends StatelessWidget {
  const BookingPage({Key? key}) : super(key: key);

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
  TextEditingController dateController = TextEditingController();
  TextEditingController timeController = TextEditingController();
  final errorTxtController = TextEditingController();
  Color selectionColor = Colors.red;
  late bool buttonDisabled;
  List<String> items = []; // list of all doctors
  String? selectedValue;    // selected value of doctor from list


  String getDate() {
    return  dateController.text;
  }

  ///Setting error text to error
  void _setTextState(String error) { // red error
    setState(() {
      selectionColor = Colors.red;
      errorTxtController.text = error;
    });
  }

  ///Setting error text to success
  void _setTextStateSuc(String success) { // green success
    setState(() {
      selectionColor = Colors.green;
      errorTxtController.text = success;
    });
  }

  ///getting list of doctors from API
  Future<void> DoctorListF() async {

    final Data = await http.get(
        Uri.parse("http://localhost:8080/booking/getDoctors/"));

    String info = Data.body;
    info = info.replaceAll(']','').replaceAll('[','').replaceAll(',',''); // splitting json into usable list

    if (info.isEmpty){
      _setTextState("No Doctors Available");
      setState(() {
        buttonDisabled = true;
      });
    }else {
      info = info.substring(1, info.length - 1);
      setState(() {
        items = info.split('""'); // for initial loading, makes list load with all doctors
      });
    }
  }


  @override
  void initState() {
    super.initState();
    dateController.text = ""; //set the initial value of text field
    timeController.text = "";
    errorTxtController.text = "";
    WidgetsBinding.instance.addPostFrameCallback((_) {DoctorListF();});           // This line is magical, calling the future to create the present, absolutely beautiful
    buttonDisabled = false;
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


    ///creates new booking from given data
    Future<void> ChecksCompleteBook() async {

      LocalStorage storage = LocalStorage('key');
      Map<String, dynamic> userID = jsonDecode(storage.getItem('getID')); //getting user ID from cache

      try{
        final response = await http.post(                     //sending data to API
          Uri.parse('http://localhost:8080/booking/create/'),
          headers: <String, String>{
            'Content-Type': 'application/json; charset=UTF-8',
          },
          body: jsonEncode(<String, String>{
            'eventID:' : "",
            'eventName': "Booking",
            'eventStart': getDate(),
            'eventEnd': timeController.text,
            'eventDetails': "Desc",
            'userID': userID['id'].toString(),
            'doctorID': "0",
            'doctorName': selectedValue.toString()
          }),
        );

        if (response.statusCode == 201) {
          _setTextStateSuc('Success');
        } else {
          _setTextState("error: ${response.statusCode}");
        }
      } on Exception catch (_) {
        _setTextState("Failed to connect to API");
        //errorTxtController.text = "Failed to connect to API";
        //throw Exception('Failed to connect to API');
      }
    }

    ///Booking function checking
    Future<void> BookingInit()  async {
      if ((dateController.text.isNotEmpty)
          && (timeController.text.isNotEmpty)
          && (selectedValue != null)){ //(selectedValue.toString() != 'null')){

        ChecksCompleteBook();

        } else {
          _setTextState("Blank Fields");
        }
    }



    return Scaffold(

      appBar: AppBar(
        title: const Text("Booking"),
      ),
      body: Center(

      child:Column(
        mainAxisAlignment: MainAxisAlignment.start,
        children: <Widget>[

          SizedBox(height: FrameHeight*0.1),

        DropdownButtonHideUnderline(
          child: DropdownButton2(
            hint: Text('Select a Doctor',
              style: TextStyle(
                fontSize: 14,
                color: Theme
                    .of(context)
                    .hintColor,
              ),
            ),
            items: items
                .map((item) =>
                DropdownMenuItem<String>(
                  value: item,
                  child: Text(
                    item,
                    style: const TextStyle(
                      fontSize: 14,
                    ),
                  ),
                ))
                .toList(),
            value: selectedValue,

            onChanged: (value) {setState(() {
                selectedValue = value as String; // changing drop down box value
              });
            },
            buttonHeight: 40,
            buttonWidth: 400,
            itemHeight: 40,
          ),
        ),
        Container(
          // padding:const EdgeInsets.all(15),
          padding: const EdgeInsets.fromLTRB(10, 10, 10, 0),
          // height:150,
          child:Center(
          child: TextField(

              controller: dateController, //editing controller of this TextField
              decoration: const InputDecoration(

              icon: Icon(Icons.calendar_today), //icon of text field
              labelText: "Enter Date" //label text of field
            ),
            readOnly: true,
            onTap: () async {
              DateTime? pickedDate = await showDatePicker(
                  context: context,
                  initialDate: DateTime.now(),
                  //get today's date
                  firstDate: DateTime(2000),
                  //DateTime.now() - not to allow to choose before today.
                  lastDate: DateTime(2101)
              );

              if (pickedDate != null) {
                String formattedDate = DateFormat('dd-MM-yyyy').format(pickedDate); // format date in required form here we use yyyy-MM-dd that means time is removed
                setState(() {
                  dateController.text = formattedDate;//set formatted date to TextField value.
                });
              }
            },
        ),

        ),
        ),
          Container(
            // padding:const EdgeInsets.all(15),
            padding: const EdgeInsets.fromLTRB(10, 10, 10, 0),
            // height:150,
            child:Center(
              child: TextField(

                controller: timeController, //editing controller of this TextField
                decoration: const InputDecoration(

                    icon: Icon(Icons.timer_rounded), //icon of text field
                    labelText: "Enter Time" //label text of field
                ),
                readOnly: true,
                onTap: () async {
                  var time = await showTimePicker(
                    context: context,
                    initialTime: TimeOfDay.now(),
                  );
                  if (time != null) {
                    setState(() {
                      // String formattedTime = DateFormat('hh:mm:ss').format(context); // format date in required form here we use yyyy-MM-dd that means time is removed
                      timeController.text = time.format(context);
                      // formattedTime; //set foratted date to TextField value.
                    });
                  }
                },

              ),
            ),
          ),
          SizedBox(height: FrameHeight*0.1),

        ElevatedButton(

          onPressed: buttonDisabled ? null : BookingInit, // add action for save later to add to calendar
          style: ElevatedButton.styleFrom(
              fixedSize: Size(FrameWidth * 0.6, FrameHeight*0.1),
              primary: Colors.blue,
              onPrimary: Colors.black,
              padding: const EdgeInsets.symmetric(horizontal: 50, vertical: 20),
              textStyle: const TextStyle(
                  fontSize: 20,
                  fontWeight: FontWeight.bold)
          ),
          child: const Text('Book'),),

          SizedBox(height: FrameHeight*0.1),

          Align( alignment: const Alignment(-0.6,-1),
            child:
            SizedBox(

              child: Text(errorTxtController.text,
                style: TextStyle(fontWeight: FontWeight.bold, color: selectionColor),
              ),
            ),
          ),
    ],
    ),
      ),
    );
  }
}
