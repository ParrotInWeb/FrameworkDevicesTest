# DeTeFra- Devices Tests Framework

## What is it?
DeTeFra is a framework by which we quickly run automatic tests of mobile devices on Android and IoS. This framework is based on Appium.

DeTeFra has to places with configuration:
1. First, in src/main/java/core/config, where we change constant parameters for the entire project, such as application name, default internet connection parameters or notification.
2. The second is application.properties. This we will change through Maven. Thanks to this, we can set our environment (paths, mobile parameters).

DeTeFra assumes work based on Page Object Pattern and Fluent Interface, including internationalization tests.

To test implementation, DeTeFra used additional libraries:
* Ocular
* Allure 
* log4J

## Configuration
### Constant parameters
We change statitc parameters in src/main/java/core/config.

#### AppProperties.java
* STATE_OF_NOTIFICATIONS - if _true_ then notifications will be cleared
* STATE_OF_WI_FI_CONNECTION - we can turn on or turn off WiFi Connection by methods from AndroidFunctions
* STATE_OF_DATA_CONNECTION  - if _true_ then data connection will be enabled for whole project
* APP_PACKAGE - application name package like _com.finalwire.aida64_
* APP_ACTIVITY - activity for application like _HHMainActivity_

#### OcularConfig.java
In this case we can change basic configuration for Ocular library (paths, similarity and save snapshot)

### Dynamical parameters
#### application.properties
* system.port - system port, default 8200
* appium.appiumJsPath - path to main.js of Appium
* appium.nodeJsPath - path to executable file of NodeJS 
* device.name - is not used in project but it is very helpful with maven commands to easy choice mobile to test
* aspectj.version - version of aspectj, default 1.9.1
* platform.defaultLanguage - language, default _pl_
* platform.Name - platform name, default _Android_
* platform.defaultLocale - locale, default _PL_
* platform.Version - platform version, default _9_
* suite - path to test suite, default _suites/allTest.xml_
* appium.AutomationName - automation name, default _UiAutomator2_
* project.build.sourceEncoding=UTF-8 - it should be UTF-8
* device.serial - serial name of device or IP of device
* bootstrap.port - bootstrap port, default 2250

#### Maven command - examples
##### Samsung A51
mvn test -DskipTests -Ddevice.name=Samsung_A51 -Ddevice.serial=R58N138DRRM -Dplatform.Version=10 -Dappium.nodeJsPath=C:\Programy\nodejs\node.exe -Dappium.appiumJsPath=C:\Users\socia\node_modules\appium\build\lib\main.js
##### Maxcom MS553
mvn test -DskipTests -Ddevice.name=Maxcom_MS553 -Ddevice.serial=0123456789ABCDEF -Dplatform.Version=7 -Dappium.nodeJsPath=C:\Programy\nodejs\node.exe -Dappium.appiumJsPath=C:\Users\socia\node_modules\appium\build\lib\main.js

