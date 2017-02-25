JDK=
JFMLpath=.
JFML=$(JFMLpath)/lib/JFML-v1.0.jar

default:
	java -jar $(JFML) Iris Mamdani1 ./XMLFiles/test-data-Iris1.txt

createIrisMamdani1:
	java -classpath $(JFML) jfml.test.CreateIrisMamdaniExampleXML1

createIrisMamdani2:
	java -classpath $(JFML) jfml.test.CreateIrisMamdaniExampleXML2

createInvertedPendulumMamdani1:
	java -classpath $(JFML) jfml.test.CreateInvertedPendulumMamdaniExampleXML1
	
createInvertedPendulumMamdani2:
	java -classpath $(JFML) jfml.test.CreateInvertedPendulumMamdaniExampleXML2

createInvertedPendulumTSK1:
	java -classpath $(JFML) jfml.test.CreateInvertedPendulumTSKExampleXML1

createInvertedPendulumTSK2:
	java -classpath $(JFML) jfml.test.CreateInvertedPendulumTSKExampleXML2

createJapaneseDietAssessmentMamdani:
	java -classpath $(JFML) jfml.test.CreateJapaneseDietAssessmentMamdaniExampleXML

createTipperMamdani1:
	java -classpath $(JFML) jfml.test.CreateTipperMamdaniExampleXML1

createTipperMamdani2:
	java -classpath $(JFML) jfml.test.CreateTipperMamdaniExampleXML2

createTipperTSK:
	java -classpath $(JFML) jfml.test.CreateTipperTSKExampleXML
	
createTipperTsukamoto1:
	java -classpath $(JFML) jfml.test.CreateTipperTsukamotoExampleXML1

createTipperTsukamoto2:
	java -classpath $(JFML) jfml.test.CreateTipperTsukamotoExampleXML2

testIrisMamdani1a:
	java -jar $(JFML) Iris Mamdani1 ./XMLFiles/test-data-Iris1.txt

testIrisMamdani1b:
	java -jar $(JFML) Iris Mamdani1 PetalWidth 0.2
	
testIrisMamdani2a:
	java -jar $(JFML) Iris Mamdani2 ./XMLFiles/test-data-Iris2.txt

testIrisMamdani2b:
	java -jar $(JFML) Iris Mamdani2 SepalLength 5.1 SepalWidth 3.5 PetalLength 1.4 PetalWidth 0.2

testIrisMamdani3a:
	java -jar $(JFML) Iris Mamdani3 ./XMLFiles/test-data-Iris1.txt

testIrisMamdani3b:
	java -jar $(JFML) Iris Mamdani3 PetalWidth 0.5

testInvertedPendulumMamdani1:
	java -jar $(JFML) InvertedPendulum Mamdani1 ./XMLFiles/test-data-InvertedPendulum.txt
	
testInvertedPendulumMamdani2:
	java -jar $(JFML) InvertedPendulum Mamdani2 ./XMLFiles/test-data-InvertedPendulum.txt

testInvertedPendulumTSK1:
	java -jar $(JFML) InvertedPendulum TSK1 ./XMLFiles/test-data-InvertedPendulum.txt
	
testInvertedPendulumTSK2:
	java -jar $(JFML) InvertedPendulum TSK2 ./XMLFiles/test-data-InvertedPendulum.txt

testJapaneseDietAssessmentMamdani:
	java -jar $(JFML) JapaneseDietAssessment Mamdani ./XMLFiles/test-data-JapaneseDietAssessment.txt

testTipperMamdani1:
	java -jar $(JFML) Tipper Mamdani1 ./XMLFiles/test-data-Tipper1.txt

testTipperMamdani2:
	java -jar $(JFML) Tipper Mamdani2 ./XMLFiles/test-data-Tipper1.txt

testTipperMamdani3:
	java -jar $(JFML) Tipper Mamdani3 ./XMLFiles/test-data-Tipper2.txt

testTipperTSK:
	java -jar $(JFML) Tipper TSK ./XMLFiles/test-data-Tipper1.txt

testTipperTsukamoto1:
	java -jar $(JFML) Tipper Tsukamoto1 ./XMLFiles/test-data-Tipper1.txt

testTipperTsukamoto2:
	java -jar $(JFML) Tipper Tsukamoto2 ./XMLFiles/test-data-Tipper1.txt
	
testRobotMamdani:
	java -jar $(JFML) Robot Mamdani ./XMLFiles/test-data-Robot.txt

testRobot1:
	java -jar $(JFML) $(JFMLpath)/XMLFiles/RobotIEEEstd1855.xml ./XMLFiles/test-data-Robot.txt

testRobot2:
	java -jar $(JFML) $(JFMLpath)/XMLFiles/RobotIEEEstd1855.xml 2 la av rd 0.2 dq 0.25 o 20 v 0.25
	