import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

import 'LoginPage.dart';
import 'MoviePage.dart';
class HomePage extends StatefulWidget {
  final User user;

  HomePage({ required this.user});

  @override
  _HomePageState createState() => _HomePageState();
}


class _HomePageState extends State<HomePage> {
  List<Movie> movies = [];

  @override
  void initState() {
    super.initState();
    fetchMovieList();
  }

  Future<void> fetchMovieList() async {
    String url = 'http://localhost:8080/movies/allMovies';
    http.Response response = await http.get(Uri.parse(url));

    if (response.statusCode == 200) {
      List<dynamic> responseData = jsonDecode(response.body);
      List<Movie> movieList = responseData.map((data) => Movie.fromJson(data)).toList();

      setState(() {
        movies = movieList;
      });
    } else {
      print("error");
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Ana Sayfa'),
      ),
      body: ListView.builder(
        itemCount: movies.length,
        itemBuilder: (context, index) {
          Movie movie = movies[index];
          return ListTile(
            leading: Image.network(movie.photoURL),
            title: Text(movie.name),
            subtitle: Text(movie.directorName),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(
                  builder: (context) => MoviePage(movie: movie, userName: widget.user.userName),
                ),
              );
            },
          );
        },
      ),
    );
  }
}

class Movie {
  final String name;
  final String photoURL;
  final String description;
  final String directorName;
  final String movieId;

  Movie({
    required this.name,
    required this.photoURL,
    required this.description,
    required this.directorName,
    required this.movieId,
  });

  factory Movie.fromJson(Map<String, dynamic> json) {
    return Movie(
      name: json['name'],
      photoURL: json['photoURL'],
      description: json['description'],
      directorName: json['directorName'],
      movieId: json['movieId'],
    );
  }
}
