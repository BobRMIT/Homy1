import 'package:flutter/material.dart';
import 'package:syncfusion_flutter_calendar/calendar.dart';

// class HomePage extends StatelessWidget{
//   @override
//   Widget build(BuildContext context) => Scaffold(
//     appBar: AppBar(
//       title: Text('Homepage'),
//     ),
//     body: Center(child: Text('Home', style: TextStyle(fontSize: 60),)),
//   );
// }

class HomePage extends StatelessWidget{

  TextEditingController _appointmentController = TextEditingController();

  // @override
  // void dispose(){ // it means when the page is killed the event controller should stop
  //   _appointmentController.dispose();
  //   super.dispose();
  // }

  @override
  Widget build(BuildContext context) => Scaffold(
    body: SfCalendar(
      view: CalendarView.month, //show monthly
      dataSource: BookingSource(_getBookingSource()),
      monthViewSettings: MonthViewSettings( appointmentDisplayMode: MonthAppointmentDisplayMode.appointment),
    ),
    floatingActionButton: FloatingActionButton.extended(
        onPressed:() => showDialog(
            context: context, builder: (context)=> AlertDialog(
         title: Text("Add Appointment:"),
         content: TextFormField(
           controller: _appointmentController,
         ),
         actions: [
          TextButton(
            child: Text("Cancel"),
            onPressed: ()=>Navigator.pop(context),
          ),
          TextButton(
            child: Text("Save"),
            onPressed: ()=>Navigator.pop(context), // change this to go onto actual calendar
          ),
         ],
        ),
        ),
        label: Text("Add Booking"),
        icon: Icon(Icons.add)
        ),
  );
}


// BOOKING INFORMATION HERE (GET DATA FROM BOOKINGS PAGE TO DISPLAY ON CALENDAR NOW WILL DEVELOP A APPOINTMENT) (shows appointment on calendar
List<Meeting> _getBookingSource() {
  final List<Meeting> meetings = <Meeting>[];
  final DateTime today = DateTime.now();
  final DateTime startTime =
  DateTime(today.year, today.month, today.day, 9, 0, 0);
  final DateTime endTime = startTime.add(const Duration(hours: 2));
  meetings.add(Meeting(
      'DR X Appointment', startTime, endTime, const Color(0xFF0F8644), false));
  return meetings;
}

//alter to change from what is needed for booking
class BookingSource extends CalendarDataSource {

  BookingSource(List<Meeting> source) {
    appointments = source; //list
  }

  @override
  DateTime getStartTime(int index) {
    return appointments![index].from;
  }

  @override
  DateTime getEndTime(int index) {
    return appointments![index].to;
  }

  @override
  String getSubject(int index) {
    return appointments![index].eventName;
  }

  @override
  Color getColor(int index) {
    return appointments![index].background;
  }


  @override
  bool isAllDay(int index) {
    return appointments![index].isAllDay;
  }
}

//alter to change from what is needed for booking
class Meeting {
  Meeting(this.eventName, this.from, this.to, this.background, this.isAllDay);

  String eventName;
  DateTime from;
  DateTime to;
  Color background;
  bool isAllDay;
}

