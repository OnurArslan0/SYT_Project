import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

import 'HomePage.dart';

class MoviePage extends StatefulWidget {
  final String userName;
  final Movie movie;

  MoviePage({required this.movie, required this.userName});

  @override
  _MoviePageState createState() => _MoviePageState();
}

class _MoviePageState extends State<MoviePage> {
  double averageRating=0 ;

  @override
  void initState() {
    super.initState();
    calculateAverageRating(widget.movie.movieId).then((rating) {
      averageRating = rating;
      print(rating);
      setState(() {
      });
    });
  }

  Future<double> calculateAverageRating(String movieId) async {
    final response = await http.get(Uri.parse('http://localhost:8080/rates/$movieId'));

    if (response.statusCode == 200) {
      List<dynamic> rateList = jsonDecode(response.body);

      double totalRating = 0;
      double ratingCount = 0;

      if(rateList.length==0){
        totalRating=0;
      }else{
        for (var rate in rateList) {
          totalRating += rate['rate'];
          ratingCount++;
        }
      }

      double averageRating = totalRating / ratingCount;
      return averageRating;
    } else {
      throw Exception('Failed to load ratings');
    }
  }

  Future<void> addOrUpdateRating(String movieId, int rating, String userName) async {
    final bool ratingExists = await getRatingByMovieUser(movieId, userName);
    if (ratingExists) {
      print("patching rate");
      await patchRate(movieId,userName, rating);
    } else {
      print("adding rate");
      await addRate(movieId,userName, rating);
    }
    averageRating = await calculateAverageRating(movieId);
    setState(() {
    });
  }

  Future<bool> getRatingByMovieUser(String movieId, String userName) async {
    final response = await http.get(Uri.parse('http://localhost:8080/rates/$movieId/$userName'));

    if (response.statusCode == 200) {
      final bool ratingExists = jsonDecode(response.body);
      return ratingExists;
    } else {
      throw Exception('Failed to get rating');
    }
  }

  Future<void> addRate(String movieId, String userName, int rating) async {
    final response = await http.post(
      Uri.parse('http://localhost:8080/rates/$movieId/addRate'),
      body: jsonEncode({'userName': userName, 'rate': rating}),
      headers: {'Content-Type': 'application/json'},
    );

    if (response.statusCode != 200) {
      throw Exception('Failed to add rating');
    }
  }

  Future<void> patchRate(String movieId, String userName, int rating) async {
    final response = await http.patch(
      Uri.parse('http://localhost:8080/rates/$movieId/patchRate'),
      body: jsonEncode({'userName': userName, 'rate': rating}),
      headers: {'Content-Type': 'application/json'},
    );

    if (response.statusCode != 200) {
      throw Exception('Failed to update rating');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.movie.name),
      ),
      body: SingleChildScrollView(
        padding: EdgeInsets.all(16),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Row(
              children: [
                Image.network(
                  widget.movie.photoURL,
                  height: 400,
                  width: 200,
                  fit: BoxFit.contain,
                ),
                Expanded(
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        for (int i = 5; i >= 1; i--)
                          Center(
                            child: IconButton(
                              highlightColor: Colors.redAccent,
                              splashColor: Colors.transparent,
                              hoverColor: Colors.transparent,
                              iconSize: 40,
                              icon: Icon(
                                Icons.star,
                                color: i-0.5 <= averageRating ? Colors.yellow : Colors.grey,
                              ),
                              onPressed: () {
                                addOrUpdateRating(widget.movie.movieId, i, widget.userName);
                              },
                            ),
                          )
                      ],
                    ),
                  ),
              ],
            ),
            Text(
              'Description:',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
            Text(widget.movie.description),
            SizedBox(height: 16),
            Text(
              'Director:',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
            Text(widget.movie.directorName),
            SizedBox(height: 16),
            Text(
              'Average Rating:',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
            Text(averageRating.toStringAsFixed(1))
          ],
        ),
      ),
    );
  }
}
