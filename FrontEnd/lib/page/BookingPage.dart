import 'package:flutter/material.dart';
import 'package:dropdown_button2/dropdown_button2.dart';
import 'package:intl/intl.dart';

// class BookingPage extends StatelessWidget{
//   @override
//   Widget build(BuildContext context) => Scaffold(
//     appBar: AppBar(
//       title: Text('Booking'),
//     ),
//     body: Center(child: Text('Booking', style: TextStyle(fontSize: 60),)),
//   );
// }



class BookingPage extends StatelessWidget {
  const BookingPage({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    // _GetUserData();
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
  TextEditingController dateController = TextEditingController();
  TextEditingController timeController = TextEditingController();

  @override
  void initState() {
    dateController.text = ""; //set the initial value of text field
    timeController.text = "";
    super.initState();
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

    final List<String> items = [
      'DR 1',
      'DR 2',
      'DR 3',
      'DR 4',
    ];
    String? selectedValue;
    final bool readOnly = false;
    final f = new DateFormat('yyyy-MM-dd hh:mm');




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
        title: const Text("Booking"),
      ),
      body: Center(

      child:Column(
        mainAxisAlignment: MainAxisAlignment.start,
        children: <Widget>[

        // Center is a layout widget. It takes a single child and positions it
        // in the middle of the parent.
        // child: Column(

          SizedBox(height: FrameHeight*0.1),
        DropdownButtonHideUnderline(
          child: DropdownButton2(
            hint: Text(
              'Select a Doctor',
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
            onChanged: (value) {
              setState(() {
                selectedValue = value as String;
              });
            },
            buttonHeight: 40,
            buttonWidth: 140,
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
                String formattedDate = DateFormat('yyyy-MM-dd').format(
                    pickedDate); // format date in required form here we use yyyy-MM-dd that means time is removed
                setState(() {
                  dateController.text = formattedDate;//set foratted date to TextField value.
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
              ),
            ),
          ),
          SizedBox(height: FrameHeight*0.1),

          ElevatedButton(

            onPressed: (){}, // add action for save later to add to calendar
            style: ElevatedButton.styleFrom(
                fixedSize: Size(FrameWidth * 0.6, FrameHeight*0.1),
                primary: Colors.blue,
                onPrimary: Colors.black,
                padding: const EdgeInsets.symmetric(horizontal: 50, vertical: 20),
                textStyle: const TextStyle(
                    fontSize: 20,
                    fontWeight: FontWeight.bold)
            ),
            child: const Text('Save'),
          ),
    ],
    ),
      ),
    );
  }
}