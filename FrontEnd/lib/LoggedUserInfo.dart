class LoggedUserInfo {

  static final LoggedUserInfo _instance = LoggedUserInfo._internal();

  factory LoggedUserInfo() => _instance;

  late List<String> _myVariable;

  LoggedUserInfo._internal() {
    _myVariable = ["1"];
  }

  List<String> get myVariable => _myVariable;

  set myVariable(List<String> Info) => myVariable = Info;

  void incrementMyVariable() => myVariable = ["a"];



}