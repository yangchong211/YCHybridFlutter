
import 'package:flutter/material.dart';
import 'package:flutter_lib/page/main/home_page.dart';

class MainApp extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new MainAppState();
  }

}

class MainAppState extends State<MainApp>{

  //在构建页面时，会调用组件的build方法
  //widget的主要工作是提供一个build()方法来描述如何构建UI界面
  //通常是通过组合、拼装其它基础widget
  @override
  Widget build(BuildContext context) {
    return getHome(context);
  }

  Widget getHome(BuildContext context) {
    return new MaterialApp(
      title: 'Flutter Demo',
      theme: new ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: new HomePage(title: 'Flutter进阶之旅'),
    );
  }

}

