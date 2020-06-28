# Framework Devices Tests

Version 1.0.0

Author: Tomasz Kozik

## Properties and capabilities
* Page object pattern 
* Tests android application with Fluent Interface
* Take screenshots on tests failure or skipped
* Visualisation assertions
* Languages tests

## Used technology
* Java with Maven
* TestNG
* Ocular
* Allure 
* sourceEncoding: UTF-8

## Configuration

### Maven by pom.xml
* Project configuration:
  * Test suites
* Appium configuration
  * Node path
  * Appium parth
  * Bootstrap port
  * System port
* App configuration
  * AutomationName
  * Platform name
  * Platform version
  * Default language and locale
* Device parameters
  * Device serial number
  * Device name

#### Examples
##### Samsung A51
mvn test -DskipTests -Ddevice.name=Samsung_A51 -Ddevice.serial=R58N138DRRM -Dplatform.Version=10 -Dappium.nodeJsPath=C:\Programy\nodejs\node.exe -Dappium.appiumJsPath=C:\Users\socia\node_modules\appium\build\lib\main.js
##### Maxcom MS553
mvn test -DskipTests -Ddevice.name=Maxcom_MS553 -Ddevice.serial=0123456789ABCDEF -Dplatform.Version=7 -Dappium.nodeJsPath=C:\Programy\nodejs\node.exe -Dappium.appiumJsPath=C:\Users\socia\node_modules\appium\build\lib\main.js

### Constants
* _core.config.project.ProjectProperties_:
  * cleanNotification - true if always clean and close notification before tests
  * changeWiFiConnection - true if changed state before tests
  * changeDataConnection - true if changed state before tests
* _core.config.AppConfig_
  * Application package and activity 
* _core.config.DateTimeFormat_ - DateTimeFormat 
* _core.config.project.Paths_ - paths for project
* _core.config.project.OcularConfig_ - ocular configuration



