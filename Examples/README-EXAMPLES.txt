The JFML library can be run with 3 main arguments (ProblemName InferenceExample DataFile) but brackets are not required
  Usage: java -jar JFML-vx.x.jar [options]
  Options: Tipper [Mamdani1 | Mamdani2 | Mamdani3 | TSK | Tsukamoto1 | Tsukamoto2 | AnYa] test-data-file
  Options: JapaneseDietAssessment Mamdani test-data-file
  Options: Iris [Mamdani1 | Mamdani2 | Mamdani3] test-data-file
  Options: InvertedPendulum [Mamdani1 | Mamdani2 | TSK1 | TSK2] test-data-file
  Options: Robot Mamdani test-data-file

You can also run the library with a specific instance as follows:
  Options: ProblemName InferenceExample V1 D1 V2 D2 ... 
  ProblemName: Tipper, JapaneseDietAssessment, etc.
  InferenceExample; Mamdani, Mamdani1, Mamdani2, TSK, etc.

Notice that the combination of ProblemName and InferenceExample must be in accordance with the name of an XML file in the folder ./XMLFiles
You must be also sure of providing the entire list of pairs variable name (Vi, as it is in the XML file) and numerical value (Di) for evaluation
Example:
> Iris Mamdani2 SepalLength 5.1 SepalWidth 3.5 PetalLength 1.4 PetalWidth 0.2

You can also run the library with a specific instance, for your own XML file, as follows:
  Options: XMLfilePath NbOutputs ON1 ON2 ... V1 D1 V2 D2 ...

Before running the examples, please be sure the XMLFiles folder contains the required XML files.
Otherwise, you can create some of them from the command line:
Examples:
> java -classpath ./lib/JFML-v1.0.jar jfml.test.CreateIrisMamdaniExampleXML1
> java -classpath ./lib/JFML-v1.0.jar jfml.test.CreateIrisMamdaniExampleXML2
> java -classpath ./lib/JFML-v1.0.jar jfml.test.CreateIrisMamdaniExampleXML3
> java -classpath ./lib/JFML-v1.0.jar jfml.test.CreateInvertedPendulumMamdaniExampleXML1
> java -classpath ./lib/JFML-v1.0.jar jfml.test.CreateInvertedPendulumMamdaniExampleXML2
> java -classpath ./lib/JFML-v1.0.jar jfml.test.CreateInvertedPendulumTSKExampleXML1
> java -classpath ./lib/JFML-v1.0.jar jfml.test.CreateInvertedPendulumTSKExampleXML2
> java -classpath ./lib/JFML-v1.0.jar jfml.test.CreateJapaneseDietAssessmentMamdaniExampleXML
> java -classpath ./lib/JFML-v1.0.jar jfml.test.CreateTipperMamdaniExampleXML1
> java -classpath ./lib/JFML-v1.0.jar jfml.test.CreateTipperMamdaniExampleXML2
> java -classpath ./lib/JFML-v1.0.jar jfml.test.CreateTipperMamdaniExampleXML3
> java -classpath ./lib/JFML-v1.0.jar jfml.test.CreateTipperTSKExampleXML
> java -classpath ./lib/JFML-v1.0.jar jfml.test.CreateTipperTsukamotoExampleXML1
> java -classpath ./lib/JFML-v1.0.jar jfml.test.CreateTipperTsukamotoExampleXML2
> java -classpath ./lib/JFML-v1.0.jar jfml.test.CreateTipperAnYaExampleXML

Tests:
> java -jar ./lib/JFML-v1.0.jar Iris Mamdani1 ./XMLFiles/test-data-Iris1.txt
> java -jar ./lib/JFML-v1.0.jar Iris Mamdani1 PetalWidth 0.2
> java -jar ./lib/JFML-v1.0.jar Iris Mamdani2 ./XMLFiles/test-data-Iris2.txt
> java -jar ./lib/JFML-v1.0.jar Iris Mamdani2 SepalLength 5.1 SepalWidth 3.5 PetalLength 1.4 PetalWidth 0.2
> java -jar ./lib/JFML-v1.0.jar Iris Mamdani3 ./XMLFiles/test-data-Iris1.txt
> java -jar ./lib/JFML-v1.0.jar Iris Mamdani3 PetalWidth 0.5
> java -jar ./lib/JFML-v1.0.jar InvertedPendulum Mamdani1 ./XMLFiles/test-data-InvertedPendulum.txt
> java -jar ./lib/JFML-v1.0.jar InvertedPendulum Mamdani2 ./XMLFiles/test-data-InvertedPendulum.txt
> java -jar ./lib/JFML-v1.0.jar InvertedPendulum TSK1 ./XMLFiles/test-data-InvertedPendulum.txt
> java -jar ./lib/JFML-v1.0.jar InvertedPendulum TSK2 ./XMLFiles/test-data-InvertedPendulum.txt
> java -jar ./lib/JFML-v1.0.jar JapaneseDietAssessment Mamdani ./XMLFiles/test-data-JapaneseDietAssessment.txt
> java -jar ./lib/JFML-v1.0.jar Tipper Mamdani1 ./XMLFiles/test-data-Tipper1.txt
> java -jar ./lib/JFML-v1.0.jar Tipper Mamdani2 ./XMLFiles/test-data-Tipper1.txt
> java -jar ./lib/JFML-v1.0.jar Tipper Mamdani3 ./XMLFiles/test-data-Tipper2.txt
> java -jar ./lib/JFML-v1.0.jar Tipper TSK ./XMLFiles/test-data-Tipper1.txt
> java -jar ./lib/JFML-v1.0.jar Tipper Tsukamoto1 ./XMLFiles/test-data-Tipper1.txt
> java -jar ./lib/JFML-v1.0.jar Tipper Tsukamoto2 ./XMLFiles/test-data-Tipper1.txt
> java -jar ./lib/JFML-v1.0.jar Tipper AnYa ./XMLFiles/test-data-Tipper2.txt
> java -jar ./lib/JFML-v1.0.jar Robot Mamdani ./XMLFiles/test-data-Robot.txt
> java -jar ./lib/JFML-v1.0.jar ./XMLFiles/RobotIEEEstd1855.xml ./XMLFiles/test-data-Robot.txt
> java -jar ./lib/JFML-v1.0.jar ./XMLFiles/RobotIEEEstd1855.xml 2 la av rd 0.2 dq 0.25 o 20 v 0.25
> java -jar ./lib/JFML-v1.0.jar ./XMLFiles/TipperTsukamoto1.xml 1 tip food 5 service 4
> java -jar ./lib/JFML-v1.0.jar ./XMLFiles/TipperTsukamoto1.xml /XMLFiles/test-data-Tipper1.txt

> java -classpath ./lib/JFML-v1.0.jar jfml.test.ExportExample MATLAB IrisMamdani1
> java -classpath ./lib/JFML-v1.0.jar jfml.test.ExportExample MATLAB TipperMamdani1
> java -classpath ./lib/JFML-v1.0.jar jfml.test.ExportExample MATLAB TipperTsukamoto1
> java -classpath ./lib/JFML-v1.0.jar jfml.test.ExportExample MATLAB TipperAnYa1
> java -classpath ./lib/JFML-v1.0.jar jfml.test.ExportExample MATLAB TipperTSK

> java -classpath ./lib/JFML-v1.0.jar jfml.test.ImportExample MATLAB Tipper
> java -jar ./lib/JFML-v1.0.jar ./XMLFiles/Tipper.xml ./XMLFiles/test-data-Tipper1.txt
> java -classpath ./lib/JFML-v1.0.jar jfml.test.ImportExample MATLAB Iris
> java -jar ./lib/JFML-v1.0.jar ./XMLFiles/Iris.xml ./XMLFiles/test-data-iris3.txt

> java -classpath ./lib/JFML-v1.0.jar jfml.test.ImportExample ./XMLFiles/TipperMamdani2.fis
> java -classpath ./lib/JFML-v1.0.jar jfml.test.ImportExample ./XMLFiles/TipperTSK2.fis

> java -classpath ./lib/JFML-v1.0.jar jfml.test.ExportExample FCL IrisMamdani1
> java -classpath ./lib/JFML-v1.0.jar jfml.test.ExportExample FCL TipperMamdani1
> java -classpath ./lib/JFML-v1.0.jar jfml.test.ExportExample FCL TipperTsukamoto1
> java -classpath ./lib/JFML-v1.0.jar jfml.test.ExportExample FCL TipperAnYa1
> java -classpath ./lib/JFML-v1.0.jar jfml.test.ExportExample FCL TipperTSK

> java -classpath ./lib/JFML-v1.0.jar jfml.test.ImportExample FCL Tipper
> java -jar ./lib/JFML-v1.0.jar ./XMLFiles/Tipper.xml ./XMLFiles/test-data-Tipper1.txt
> java -classpath ./lib/JFML-v1.0.jar jfml.test.ImportExample FCL Robot
> java -jar ./lib/JFML-v1.0.jar ./XMLFiles/Robot.xml ./XMLFiles/test-data-Robot.txt

> java -classpath ./lib/JFML-v1.0.jar jfml.test.ImportExample ./XMLFiles/TipperTSK2.fcl
> java -classpath ./lib/JFML-v1.0.jar jfml.test.ImportExample ./XMLFiles/TipperMamdani2.fcl

In addition, notice that you can run some of the examples above by calling to the related bat files in Windows OS or by running the Makefile otherwise.
