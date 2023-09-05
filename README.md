# JFML: An open source Java Library for the IEEE Standard for Fuzzy Markup Language (IEEE Std 1855-2016)

JFML is an open source Java library which is aimed at facilitating interoperability and usability of fuzzy systems. 
Notice that JFML implements the new IEEE Std 1855 published and sponsored by the Standards Committee of the IEEE Computational Intelligence Society.

JFML has a web page associated with a complete documentation, a good variety of examples for both users and developers, etc., The web page is hosted at:
http://www.uco.es/JFML

## JFML-Core

From v1.3, JFML —now renamed as JFML-Core— is compiled with Java 11 and uses Maven to do so, whilst previous versions use Ant and Java 8. This module has two main purposes:
- It contains the definition of the main classes, such as FuzzyVariable.
- It applies an already built Fuzzy Logic System to a data set to predict the data output value.

### How to use

To build, you can clone the .git repository or download the library into a .zip file from the `Clone or download` option. You can also download the source code from the `releases` in GitHub.

We recommend following the next steps to use it if no modification is needed:
- Download the library into a .zip file from the _Clone or Download_ option or from the <a href="https://github.com/sotillo19/JFML/releases">releases</a>
- Unzip it in a local folder.
- At the root of the project, go (`cd`) to _Examples_.
- Run the following command:
```
java -jar ./lib/<jar-file> <problem> <fls> ./XMLFiles/<test-data-file>
```
This command is thoroughly explained in _Examples/README-EXAMPLES.txt_.

In case of changing the source files:
- Make the necessary changes.
- Do `mvn clean install`.
- A new artifact, as a JAR file, will be generated at the _target_ folder.
- Copy and paste that JAR to _Examples/lib_.
- At the root of the project, go (`cd`) to _Examples_.
- Run the main command.


## JFML IoT: A Java library for IoT using JFML for fuzzy control. 

JFML IoT is an open source Java library designed to facilitate the integration of Fuzzy-Base Systems (FRBS) into Internet of Things (IoT) enviroments. Using Java Fuzzy Markup Language (JFML) as engine. JFML is built on the IEEE 1855-2016 standard, simplifying the representation of fuzzy rule-based systems with a standard XML-based FML language. It's available in [github.com/JfMRes/JFML_IoT](https://github.com/JfMRes/JFML_IoT).

The entire project has been created with extensibility in mind. The abstract classes represent the basic functionality of all objects, making it easier to extend the project and add new classes to expand its capabilities.

You can download the full project or download the .jar file to import in your project [from releases](https://github.com/JfMRes/JFML_IoT/releases)

### Implemented components

This section provides an overview of the components implemented in the project.

#### Sensors

The following sensors have been integrated into the system:

|  Name       | Variable    | Type      | Pin Count | Variable/Id Count |
|-------------------|---------------|-----------|-----------|-------------------|
| ArduinoNoiseSensor | Sound         | Analog    | 1         | 1                 |
| ArduinoPotentiometer | Rotation  | Analog    | 1         | 1                 |
| ArduinoFireSensor | Fire          | Digital   | 1         | 1                 |
| ArduinoDHT11      | Temperature and Humidity | Analog | 1 | 2                 |
| ArduinoSR04       | Distance      | Analog    | 2         | 1                 |
| ArduinoLDR        | Luminosity    | Analog    | 1         | 1                 |
| ArduinoPIR        | Presence      | Digital   | 1         | 1                 |
| ArduinoPushButton | Interaction   | Digital   | 1         | 1                 |


#### Actuators

The following actuators have been implemented in the system:

|  Name     | Phenomenon    | Type      | Pin Count | Variable/Id Count |
|-------------------|---------------|-----------|-----------|-------------------|
| ArduinoLed        | Light         | Digital   | 1         | 1                 |
| ArduinoLedPWM     | Light         | Analog    | 1         | 1                 |
| ArduinoLedRGB     | Light         | Digital   | 3         | 3                 |
| ArduinoBuzzer     | Sound         | Digital   | 1         | 1                 |
| ArduinoServo      | Mechanical    | Analog    | 1         | 1                 |


#### Embedded systems

The following embedded systems have been implemented in the system:

- Arduino Mega using OSOYOO MEGA-IoT Shield.
- NodeMCU v0.9

#### Communication protocol

In the system, only the Message Queuing Telemetry Transport (MQTT) protocol has been implemented for communication, as it provides highly efficient and secure communication.
