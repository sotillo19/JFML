JDK=
JFMLpath=.
JFML=$(JFMLpath)/lib/JFML-v1.2.1.jar
JFMLxmlfiles=$(JFMLpath)/XMLFiles/

default:
	java -jar $(JFML) Tipper Mamdani1 $(JFMLxmlfiles)test-data-Tipper1.txt

createIrisMamdani1:
	java -classpath $(JFML) jfml.test.CreateIrisMamdaniExampleXML1

createIrisMamdani2:
	java -classpath $(JFML) jfml.test.CreateIrisMamdaniExampleXML2

createIrisMamdani3:
	java -classpath $(JFML) jfml.test.CreateIrisMamdaniExampleXML3

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

createTipperMamdani3:
	java -classpath $(JFML) jfml.test.CreateTipperMamdaniExampleXML3

createTipperTSK:
	java -classpath $(JFML) jfml.test.CreateTipperTSKExampleXML
	
createTipperTsukamoto1:
	java -classpath $(JFML) jfml.test.CreateTipperTsukamotoExampleXML1

createTipperTsukamoto2:
	java -classpath $(JFML) jfml.test.CreateTipperTsukamotoExampleXML2

testIrisMamdani1a:
	java -jar $(JFML) Iris Mamdani1 $(JFMLxmlfiles)test-data-Iris1.txt

testIrisMamdani1b:
	java -jar $(JFML) Iris Mamdani1 PetalWidth 0.2
	
testIrisMamdani2a:
	java -jar $(JFML) Iris Mamdani2 $(JFMLxmlfiles)test-data-Iris2.txt

testIrisMamdani2b:
	java -jar $(JFML) Iris Mamdani2 SepalLength 5.1 SepalWidth 3.5 PetalLength 1.4 PetalWidth 0.2

testIrisMamdani3a:
	java -jar $(JFML) Iris Mamdani3 $(JFMLxmlfiles)test-data-Iris1.txt

testIrisMamdani3b:
	java -jar $(JFML) Iris Mamdani3 PetalWidth 0.5

testInvertedPendulumMamdani1:
	java -jar $(JFML) InvertedPendulum Mamdani1 $(JFMLxmlfiles)test-data-InvertedPendulum.txt
	
testInvertedPendulumMamdani2:
	java -jar $(JFML) InvertedPendulum Mamdani2 $(JFMLxmlfiles)test-data-InvertedPendulum.txt

testInvertedPendulumTSK1:
	java -jar $(JFML) InvertedPendulum TSK1 $(JFMLxmlfiles)test-data-InvertedPendulum.txt
	
testInvertedPendulumTSK2:
	java -jar $(JFML) InvertedPendulum TSK2 $(JFMLxmlfiles)test-data-InvertedPendulum.txt

testJapaneseDietAssessmentMamdani:
	java -jar $(JFML) JapaneseDietAssessment Mamdani $(JFMLxmlfiles)test-data-JapaneseDietAssessment.txt

testTipperMamdani1:
	java -jar $(JFML) Tipper Mamdani1 $(JFMLxmlfiles)test-data-Tipper1.txt

testTipperMamdani2:
	java -jar $(JFML) Tipper Mamdani2 $(JFMLxmlfiles)test-data-Tipper1.txt

testTipperMamdani3:
	java -jar $(JFML) Tipper Mamdani3 $(JFMLxmlfiles)test-data-Tipper2.txt

testTipperTSK:
	java -jar $(JFML) Tipper TSK $(JFMLxmlfiles)test-data-Tipper1.txt

testTipperTsukamoto1:
	java -jar $(JFML) Tipper Tsukamoto1 $(JFMLxmlfiles)test-data-Tipper1.txt

testTipperTsukamoto2:
	java -jar $(JFML) Tipper Tsukamoto2 $(JFMLxmlfiles)test-data-Tipper1.txt
	
testRobotMamdani:
	java -jar $(JFML) Robot Mamdani $(JFMLxmlfiles)test-data-Robot.txt

testRobot1:
	java -jar $(JFML) $(JFMLxmlfiles)RobotIEEEstd1855.xml $(JFMLxmlfiles)test-data-Robot.txt

testRobot2:
	java -jar $(JFML) $(JFMLxmlfiles)RobotIEEEstd1855.xml 2 la av rd 0.2 dq 0.25 o 20 v 0.25
	
exportIrisMamdani1MATLAB:
	java -classpath $(JFML) jfml.test.ExportExample $(JFMLxmlfiles)IrisMamdani1.xml $(JFMLxmlfiles)IrisMamdani1.fis

exportIrisMamdani1FCL:
	java -classpath $(JFML) jfml.test.ExportExample $(JFMLxmlfiles)IrisMamdani1.xml $(JFMLxmlfiles)IrisMamdani1.fcl
	
exportIrisMamdani2MATLAB:
	java -classpath $(JFML) jfml.test.ExportExample $(JFMLxmlfiles)IrisMamdani2.xml $(JFMLxmlfiles)IrisMamdani2.fis
	
exportTipperMamdani1MATLAB:
	java -classpath $(JFML) jfml.test.ExportExample $(JFMLxmlfiles)TipperMamdani1.xml $(JFMLxmlfiles)TipperMamdani1.fis

exportTipperMamdani1FCL:
	java -classpath $(JFML) jfml.test.ExportExample $(JFMLxmlfiles)TipperMamdani1.xml $(JFMLxmlfiles)TipperMamdani1.fcl

exportTipperMamdani2MATLAB:
	java -classpath $(JFML) jfml.test.ExportExample $(JFMLxmlfiles)TipperMamdani2.xml $(JFMLxmlfiles)TipperMamdani2.fis

exportTipperMamdani2FCL:
	java -classpath $(JFML) jfml.test.ExportExample $(JFMLxmlfiles)TipperMamdani2.xml $(JFMLxmlfiles)TipperMamdani2.fcl
	
exportTipperTSKMATLAB:
	java -classpath $(JFML) jfml.test.ExportExample $(JFMLxmlfiles)TipperTSK.xml $(JFMLxmlfiles)TipperTSK.fis

exportTipperTSKFCL:
	java -classpath $(JFML) jfml.test.ExportExample $(JFMLxmlfiles)TipperTSK.xml $(JFMLxmlfiles)TipperTSK.fcl
	
exportIrisMamdaniPMML:
	java -classpath $(JFML) jfml.test.ExportExample $(JFMLxmlfiles)IrisMamdani2.xml $(JFMLxmlfiles)IrisMamdani2.frbsPMML

exportTipperTSKPMML:
	java -classpath $(JFML) jfml.test.ExportExample $(JFMLxmlfiles)TipperTSK.xml $(JFMLxmlfiles)TipperTSK.frbsPMML
	
importIrisMamdani1MATLAB:
	java -classpath $(JFML) jfml.test.ImportExample $(JFMLxmlfiles)IrisMamdani1.fis $(JFMLxmlfiles)IrisMamdani1.xml

importIrisMamdani1FCL:
	java -classpath $(JFML) jfml.test.ImportExample $(JFMLxmlfiles)IrisMamdani1.fcl $(JFMLxmlfiles)IrisMamdani1.xml
	
importIrisMamdani2MATLAB:
	java -classpath $(JFML) jfml.test.ImportExample $(JFMLxmlfiles)IrisMamdani2.fis $(JFMLxmlfiles)IrisMamdani2.xml
	
importTipperMamdani1MATLAB:
	java -classpath $(JFML) jfml.test.ImportExample $(JFMLxmlfiles)TipperMamdani1.fis $(JFMLxmlfiles)TipperMamdani1.xml

importTipperMamdani1FCL:
	java -classpath $(JFML) jfml.test.ImportExample $(JFMLxmlfiles)TipperMamdani1.fcl $(JFMLxmlfiles)TipperMamdani1.xml

importTipperMamdani2MATLAB:
	java -classpath $(JFML) jfml.test.ImportExample $(JFMLxmlfiles)TipperMamdani2.fis $(JFMLxmlfiles)TipperMamdani2.xml

importTipperMamdani2FCL:
	java -classpath $(JFML) jfml.test.ImportExample $(JFMLxmlfiles)TipperMamdani2.fcl $(JFMLxmlfiles)TipperMamdani2.xml
	
importTipperTSKMATLAB:
	java -classpath $(JFML) jfml.test.ImportExample $(JFMLxmlfiles)TipperTSK.fis $(JFMLxmlfiles)TipperTSK.xml

importTipperTSKFCL:
	java -classpath $(JFML) jfml.test.ImportExample $(JFMLxmlfiles)TipperTSK.fcl $(JFMLxmlfiles)TipperTSK.xml
	
importIrisMamdaniPMML:
	java -classpath $(JFML) jfml.test.ImportExample $(JFMLxmlfiles)IrisMamdani2.frbsPMML $(JFMLxmlfiles)IrisMamdani2.frbsPMML.xml

importTipperTSKPMML:
	java -classpath $(JFML) jfml.test.ImportExample $(JFMLxmlfiles)TipperTSK.frbsPMML $(JFMLxmlfiles)TipperTSK.frbsPMML.xml
	

testIrisMamdani1Import:
	java -jar $(JFML) $(JFMLxmlfiles)IrisMamdani1.xml $(JFMLxmlfiles)test-data-Iris1.txt

testTipperMamdani1Import:
	java -jar $(JFML) $(JFMLxmlfiles)TipperMamdani1.xml $(JFMLxmlfiles)test-data-Tipper1.txt

testTipperTSKImport:
	java -jar $(JFML) $(JFMLxmlfiles)TipperTSK.xml $(JFMLxmlfiles)test-data-Tipper1.txt

testTao:
	java -jar $(JFML) $(JFMLxmlfiles)taoFML.xml 1 Class x1 90 x2 60 Sumx1x2 150
	
testIrisMamdaniPMML:
	java -jar $(JFML) $(JFMLxmlfiles)irisMamdani2.xml 1 irisClass SepalLength 5.5 SepalWidth 2.3 PetalLength 4 PetalWidth 1.3
	java -jar $(JFML) $(JFMLxmlfiles)IrisMamdani2.frbsPMML.xml 1 irisClass SepalLength 5.5 SepalWidth 2.3 PetalLength 4 PetalWidth 1.3

testTipperTSKPMML:
	java -jar $(JFML) $(JFMLxmlfiles)TipperTSK.xml 1 tip food 2 service 3
	java -jar $(JFML) $(JFMLxmlfiles)TipperTSK.frbsPMML.xml 1 tip food 2 service 3
