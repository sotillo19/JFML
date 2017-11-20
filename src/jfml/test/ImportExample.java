/**************************************************************
      GNU GENERAL PUBLIC LICENSE - Version 3 

  JFML: A Java Library for the IEEE Standard for Fuzzy Markup Language
  (IEEE Std 1855-2016). Copyright (C) 2017

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

     You should have received a copy of the GNU General Public License
     along with this program.  If not, see <http://www.gnu.org/licenses/>.

  Contact information: <http://www.uco.es/JFML>

  J.M. Soto-Hidalgo & Jose M. Alonso & Jesus Alcala-Fdez
 **************************************************************/
package jfml.test;

import java.io.File;
import java.io.IOException;

import jfml.FuzzyInferenceSystem;
import jfml.JFML;
import jfml.compatibility.*;

/**
 * This class presents some examples to import a fuzzy system from either MATLAB format or FCL (IEC 1131).  
 * 
 * @author Jose Alonso
 *
 */
public class ImportExample {
	
	public static void main(String[] args)  throws IOException {
        if (args.length==1) {
        	File faux= new File(args[0]);
        	if (!faux.exists()) {
    		    System.out.println("WARNING: The target file does not exist.");
        	} else {
        		if ( (!args[0].endsWith(".fcl")) && (!args[0].endsWith(".fis")) ) {
        		    System.out.println("WARNING: Only FCL (\'*.fcl\') and MATLAB (\'*.fis\') files are admitted.");
        		} else {
        		    Import is=null;
        		    String xmlf= args[0];
        		    String Lang;
        		    if (args[0].endsWith(".fcl")) {
        		        is= new ImportFCL();
        		        xmlf= xmlf.replace(".fcl", ".xml");
        		        Lang= "FCL";
        		    } else {
        		        is= new ImportMatlab();
        		        xmlf= xmlf.replace(".fis", ".xml");
        		        Lang= "MATLAB";
        		    }
        		    File xmlFile= new File(xmlf);
		    		FuzzyInferenceSystem fs = is.importFuzzySystem(args[0]);
		    		if (fs!=null) {
		    		  File dirXMLFiles = new File("./XMLFiles/");
		    		  if (!dirXMLFiles.exists()) {
		    			dirXMLFiles.mkdir();
		    		  }
		    		  JFML.writeFSTtoXML(fs, xmlFile);
		    		  System.out.println("Imported fuzzy system: from "+Lang+ " to JFML");
		    		  System.out.println("New file: "+xmlFile);
		    		  //printing the FuzzySystem
		    		  System.out.println(fs.toString());
		    		}
        		}
        	}
        } else if (args.length!=2) {
		    System.out.println("Please, check the arguments given to the program");
			System.out.println("Notice that the program has 2 main arguments (Language ProblemName DataFile) but brackets are not required");
		    System.out.println("  Options: MATLAB [Tipper | Iris]");
		    System.out.println("  Options: FCL [Tipper | Robot]");
        } else {
		    Import is=null;
		    String Lang=args[0];
		    boolean goon= true;
		    if (Lang.equals("FCL")) {
		        is= new ImportFCL();
		    } else if (Lang.equals("MATLAB")) {
		        is= new ImportMatlab();
		    } else {
		        goon= false;
		        System.out.println("Please, check the arguments given to the program");
			    System.out.println("Unfortunately, you select an unknown Language");
				System.out.println("Notice that the program has 2 main arguments (Language ProblemName DataFile) but brackets are not required");
			    System.out.println("  Options: MATLAB [Tipper | Iris]");
			    System.out.println("  Options: FCL [Tipper | Robot]");
		    }
		    if (goon && is!=null) {
		        String ifs="";	
		        File xmlFile= null;
		    	if (args[1].equals("Tipper")) {
				    if (Lang.equals("FCL")) {
				    	ifs= "./XMLFiles/Tipper.fcl";
				    } else if (Lang.equals("MATLAB")) {
				    	ifs= "./XMLFiles/Tipper.fis";
				    } 
		    		xmlFile = new File("./XMLFiles/Tipper.xml");
		    	} else if (args[1].equals("Robot")) {
				    if (Lang.equals("FCL")) {
			    		ifs= "./XMLFiles/robot.fcl";
				    } else {
				        goon= false;
				        System.out.println("Please, check the arguments given to the program");
					    System.out.println("Unfortunately, you select an unknown Language");
						System.out.println("Notice that the program has 2 main arguments (Language ProblemName DataFile) but brackets are not required");
					    System.out.println("  Options: MATLAB [Tipper | Iris]");
					    System.out.println("  Options: FCL [Tipper | Robot]");
				    }
		    		xmlFile = new File("./XMLFiles/Robot.xml");
		    	} else if (args[1].equals("Iris")) {
				    if (Lang.equals("FCL")) {
				        goon= false;
				        System.out.println("Please, check the arguments given to the program");
					    System.out.println("Unfortunately, you select an unknown Language");
						System.out.println("Notice that the program has 2 main arguments (Language ProblemName DataFile) but brackets are not required");
					    System.out.println("  Options: MATLAB [Tipper | Iris]");
					    System.out.println("  Options: FCL [Tipper | Robot]");
				    } else if (Lang.equals("MATLAB")) {
			    		ifs= "./XMLFiles/iris.fis"; 
		    	    }
		    		xmlFile = new File("./XMLFiles/iris.xml");
		    	} else {
			        goon= false;
			        System.out.println("Please, check the arguments given to the program");
				    System.out.println("Unfortunately, you select an unknown ProblemName");
					System.out.println("Notice that the program has 2 main arguments (Language ProblemName DataFile) but brackets are not required");
				    System.out.println("  Options: MATLAB [Tipper | Iris]");
				    System.out.println("  Options: FCL [Tipper | Robot]");
		    	}
		    	if (goon && !ifs.equals("")) {
			    	//loading Fuzzy System from an file according the standard FCL (IEC 1131)
		    		FuzzyInferenceSystem fs = is.importFuzzySystem(ifs);
		    		if (fs!=null) {
		    		  File dirXMLFiles = new File("./XMLFiles/");
		    		  if (!dirXMLFiles.exists())
		    			dirXMLFiles.mkdir();

		    		  JFML.writeFSTtoXML(fs, xmlFile);
		    		  System.out.println("Imported fuzzy system: from "+Lang+ " to JFML");
		    		  System.out.println("New file: "+xmlFile);
		    		  //printing the FuzzySystem
		    		  System.out.println(fs.toString());
		    		}
		    	}
		    }
        }
	}
}