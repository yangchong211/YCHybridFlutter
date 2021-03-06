import 'dart:ui';
import 'package:flutter/material.dart';
import 'package:flutter_lib/router/router_helper.dart';
import 'package:flutter_lib/utils/screen/screen_utils.dart';

void main() => runApp(new MyApp());


class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'flutter和Android混编项目',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      // onGenerateRoute: (RouteSettings settings) {
      //   return AnimationPageRoute(builder: (context) {
      //     return Router.parseRouter(settings.name, settings.arguments);
      //   });
      // },
      home: Router.widgetForRoute(window),
      // home: _widgetForRoute(window),
    );
  }

}




