import 'dart:async';
import 'package:flutter/material.dart';
import 'package:flutter_lib/page/event/provider/business_state_service.dart';
import 'package:flutter_lib/page/event/provider/business_state_service_impl.dart';
import 'package:flutter_lib/utils/getIt/get_it.dart';

GetIt serviceLocator = GetIt.instance;

class ServiceLocator extends StatefulWidget {

  final Widget child;

  ServiceLocator(this.child);

  @override
  _ServiceLocator createState() => _ServiceLocator();

}

class _ServiceLocator extends State<ServiceLocator> {

  @override
  void initState() {
    super.initState();
  }

  Future fetchContext(BuildContext context) async {
    setupServiceLocator(context);
  }

  @override
  Widget build(BuildContext context) {
    return FutureBuilder(
        future: fetchContext(context),
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.done) {
            return widget.child;
          } else {
            return Container();
          }
        });
  }

  @override
  void dispose() {
    super.dispose();
    //解绑操作
    serviceLocator.resetLazySingleton<BusinessPatternService>();
  }

  void setupServiceLocator(BuildContext context) {
    //注册模式状态管理service
    serviceLocator.registerLazySingleton<BusinessPatternService>(
        () => BusinessPatternServiceImpl(context));
  }
}
