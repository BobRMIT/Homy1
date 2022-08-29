class LoggedUserInfo {
  final int userId;
  final String name;

  const LoggedUserInfo({
    required this.userId,
    required this.name,
  });

  factory LoggedUserInfo.fromJson(Map<String, dynamic> json) {
    return LoggedUserInfo(
      userId: json['id'],
      name: json['name'],
    );
  }
}