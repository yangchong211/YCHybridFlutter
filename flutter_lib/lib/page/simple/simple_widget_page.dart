import 'package:flutter/material.dart';
import 'package:flutter_lib/page/basic/button_page.dart';
import 'package:flutter_lib/page/event/gesture_detector_page.dart';
import 'package:flutter_lib/page/basic/image_page.dart';
import 'package:flutter_lib/page/simple/index_and_chose_page.dart';
import 'package:flutter_lib/page/simple/layout_page.dart';
import 'package:flutter_lib/page/use/scaffold_page.dart';
import 'package:flutter_lib/page/simple/sliverWidget/sliver_page.dart';
import 'package:flutter_lib/page/basic/text_field_page.dart';
import 'package:flutter_lib/page/basic/text_page.dart';
import 'package:flutter_lib/page/dialog/toast_and_dialog_page.dart';
import 'package:flutter_lib/widget/custom_raised_button.dart';



void main() {
  runApp(new MaterialApp(home: new SimpleWidgetMainPage()));
}

class SimpleWidgetMainPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(
        title: new Text("基础组件"),
      ),
      body: new Center(
        child: new ListView(
          children: <Widget>[
            CustomRaisedButton(new LayoutPage(), "layout布局"),
            CustomRaisedButton(new ToastAndDialogPage(), "ToastAndDialogPage"),
            CustomRaisedButton(new IndexAndChosePage(), "IndexAndChose 控件"),
            CustomRaisedButton(new SliverPage(), "Sliver Widget"),
          ],
        ),
      ),
    );
  }
}
