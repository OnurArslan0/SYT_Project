import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:flutter/material.dart';

import 'HomePage.dart';
import 'RegisterPage.dart';


class LoginPage extends StatefulWidget {
  @override
  _LoginPageState createState() => _LoginPageState();
}
class _LoginPageState extends State<LoginPage> {
  TextEditingController emailController = TextEditingController();
  TextEditingController passwordController = TextEditingController();

  Future<User> fetchUser(String mail) async {
    final response = await http.get(Uri.parse('http://localhost:8080/aut/${mail}'));

    if (response.statusCode == 200) {
      final jsonData = jsonDecode(response.body);
      return User.fromJson(jsonData);
    } else {
      throw Exception('Failed to load user');
    }
  }

  Future<void> loginUser(String mail) async {
    String url = 'http://localhost:8080/aut/login';
    Map<String, String> headers = {'Content-Type': 'application/json'};
    Map<String, String> body = {
      'mail': emailController.text,
      'password': passwordController.text,
    };

    http.Response response = await http.post(
      Uri.parse(url),
      headers: headers,
      body: jsonEncode(body),
    );

    if (response.statusCode == 200) {
      Map<String, dynamic> responseData = jsonDecode(response.body);
      String token = responseData['token'];
      print('Token: $token');
      User user= await fetchUser(mail);
      Navigator.push(
        context,
        MaterialPageRoute(builder: (context) => HomePage(user: user)),
      );
    } else {
      print('Error: ${response.statusCode}');
      showDialog(
        context: context,
        builder: (context) => AlertDialog(
          title: Text('Incorrect Login'),
          content: Text('Invalid username or password.'),
          actions: [
            TextButton(
              onPressed: () => Navigator.pop(context),
              child: Text('OK'),
            ),
          ],
        ),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Login'),
      ),
      body: Padding(
        padding: EdgeInsets.all(16.0),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            TextFormField(
              controller: emailController,
              decoration: InputDecoration(
                labelText: 'Email',
              ),
            ),
            SizedBox(height: 16.0),
            TextFormField(
              controller: passwordController,
              decoration: InputDecoration(
                labelText: 'Password',
              ),
              obscureText: true,
            ),
            SizedBox(height: 16.0),
            ElevatedButton(
              onPressed: (){
                loginUser(emailController.text);
              },
              child: Text('Login'),
            ),
            SizedBox(height: 16.0),
            Text("Don't you have a membership yet?"),
            SizedBox(height: 8.0),
            ElevatedButton(
              onPressed: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) => UserRegisterPage()),
                );
              },
              child: Text('Become a Member'),
            ),
          ],
        ),
      ),
    );
  }
}

class User {
  final String userName;
  final String mail;
  final String password;
  final String userId;

  User({
    required this.userName,
    required this.mail,
    required this.password,
    required this.userId,
  });

  factory User.fromJson(Map<String, dynamic> json) {
    return User(
      userName: json['userName'],
      mail: json['mail'],
      password: json['password'],
      userId: json['userId'],
    );
  }
}
