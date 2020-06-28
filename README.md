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
* Base configuration
  * Node path
  * Appium parth, url, port
  * Bootstrap port
  * System port
* Phone parameters
  * Android version
  * Android serial number
  * Phone number
* Phone settings
  * Language and locale

#### Examples
##### Samsung A51
mvn test -DskipTests -Ddevice.name=Samsung_A51 -Ddevice.phoneNumber=+48660401755 -Ddevice.serial=R58N138DRRM -Dplatform.Version=10 -Dappium.nodeJsPath=C:\Programy\nodejs\node.exe -Dappium.appiumJsPath=C:\Users\socia\node_modules\appium\build\lib\main.js
##### Maxcom MS553
mvn test -DskipTests -Ddevice.name=Maxcom_MS553 -Ddevice.phoneNumber=+48784981829 -Ddevice.serial=0123456789ABCDEF -Dplatform.Version=7 -Dappium.nodeJsPath=C:\Programy\nodejs\node.exe -Dappium.appiumJsPath=C:\Users\socia\node_modules\appium\build\lib\main.js

### Constants
* Application package and activity _core.config.AppConfig_
* DateTimeFormat _core.config.DateTimeFormat_
* Paths _core.config.project.Paths_
* Ocular _core.config.project.OcularConfig_



