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
    		    System.out.println("WARNING: The target input file does not exist.");
        	} else {
        		if ( (!args[0].endsWith(".fcl")) && (!args[0].endsWith(".fis")) && (!args[0].endsWith(".frbsPMML")) ) {
        		    System.out.println("WARNING: Only FCL (\'*.fcl\'), MATLAB (\'*.fis\'), or frbs PMML (\'*.frbsPMML\') files are admitted.");
        		} else {
        		    Import is=null;
        		    String xmlf= args[0];
        		    String Lang;
        		    if (args[0].endsWith(".fcl")) {
        		        is= new ImportFCL();
        		        xmlf= xmlf.replace(".fcl", ".xml");
        		        Lang= "FCL";
        		    } else if (args[0].endsWith(".frbsPMML")) {
        		        is= new ImportPMML();
        		        xmlf= xmlf.replace(".frbsPMML", ".xml");
        		        Lang= "PMML";
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
        } else if (args.length==2) {
        	File fauxIn= new File(args[0]);
        	File fauxOut= new File(args[1]);
        	boolean warning= false;
        	if (!fauxIn.exists()) {
    		    System.out.println("WARNING: The input file does not exist.");
    		    warning=true;
        	} 
        	if (!warning && fauxOut.exists()) {
    		    System.out.println("WARNING: The output file already exists.");
		        System.out.println("Please, launch the program again with another output file name.");
    		    warning=true;
        	}
    		if (!warning && (!args[0].endsWith(".fcl")) && (!args[0].endsWith(".fis")) && (!args[0].endsWith(".frbsPMML")) ) {
    		    System.out.println("WARNING: Only FCL (\'*.fcl\'), MATLAB (\'*.fis\'), or frbs PMML (\'*.frbsPMML\') files are admitted.");
    		    warning=true;
        	}
        	if (!warning && (!args[1].endsWith(".xml"))) {
    		    System.out.println("WARNING: The output file name must ends with .xml");
    		    warning=true;
        	}
        	if (!warning) {
       		    Import is=null;
       		    String Lang;
       		    if (args[0].endsWith(".fcl")) {
       		        is= new ImportFCL();
       		        Lang= "FCL";
       		    } else if (args[0].endsWith(".frbsPMML")) {
       		        is= new ImportPMML();
       		        Lang= "PMML";
       		    } else {
       		        is= new ImportMatlab();
       		        Lang= "MATLAB";
       		    }
	    		FuzzyInferenceSystem fs = is.importFuzzySystem(args[0]);
	    		if (fs!=null) {
	    		  File dirXMLFiles = new File("./XMLFiles/");
	    		  if (!dirXMLFiles.exists()) {
	    			dirXMLFiles.mkdir();
	    		  }
	    		  JFML.writeFSTtoXML(fs, fauxOut);
	    		  System.out.println("Imported fuzzy system: from "+Lang+ " to JFML");
	    		  System.out.println("New file: "+fauxOut);
	    		  //printing the FuzzySystem
	    		  System.out.println(fs.toString());
	    		}
        	}
        } else if (args.length!=3) {
		    System.out.println("Please, check the arguments given to the program");
			System.out.println("Notice that the program has 2 main arguments (Language ProblemName DataFile) but brackets are not required");
		    System.out.println("  Options: MATLAB [Tipper | Iris] output.xml");
		    System.out.println("  Options: FCL [Tipper | Robot] output.xml");
		    System.out.println("  Options: PMML [Tipper | Iris] output.xml");
        } else {
		    Import is=null;
		    String Lang=args[0];
        	File fauxOut= new File(args[2]);
		    boolean goon= true;
		    if (Lang.equals("FCL")) {
		        is= new ImportFCL();
		    } else if (Lang.equals("PMML")) {
		        is= new ImportPMML();
		    } else if (Lang.equals("MATLAB")) {
		        is= new ImportMatlab();
		    } else {
		        goon= false;
		        System.out.println("Please, check the arguments given to the program");
			    System.out.println("Unfortunately, you select an unknown Language");
				System.out.println("Notice that the program has 3 main arguments (Language ProblemName DataFile) but brackets are not required");
			    System.out.println("  Options: MATLAB [Tipper | Iris] output.xml");
			    System.out.println("  Options: FCL [Tipper | Robot] output.xml");
			    System.out.println("  Options: PMML [Tipper | Iris] output.xml");
		    }
        	if (goon && fauxOut.exists()) {
    		    System.out.println("WARNING: The output file already exists.");
		        System.out.println("Please, launch the program again with another output file name.");
    		    goon=false;
        	}
        	if (goon && (!args[2].endsWith(".xml"))) {
    		    System.out.println("WARNING: The output file name must ends with .xml");
    		    goon=false;
        	}
		    if (goon && is!=null) {
		        String ifs="";	
		    	if (args[1].equals("Tipper")) {
				    if (Lang.equals("FCL")) {
				    	ifs= "./XMLFiles/Tipper.fcl";
				    } else if (Lang.equals("MATLAB")) {
				    	ifs= "./XMLFiles/Tipper.fis";
				    } else if (Lang.equals("PMML")) {
				    	ifs= "./XMLFiles/TipperTSK.frbsPMML";
				    } 
		    	} else if (args[1].equals("Robot")) {
				    if (Lang.equals("FCL")) {
			    		ifs= "./XMLFiles/robot.fcl";
				    } else {
				        goon= false;
				        System.out.println("Please, check the arguments given to the program");
					    System.out.println("Unfortunately, you select an unknown Language");
						System.out.println("Notice that the program has 3 main arguments (Language ProblemName DataFile) but brackets are not required");
					    System.out.println("  Options: MATLAB [Tipper | Iris] output.xml");
					    System.out.println("  Options: FCL [Tipper | Robot] output.xml");
					    System.out.println("  Options: PMML [Tipper | Iris] output.xml");
				    }
		    	} else if (args[1].equals("Iris")) {
				    if (Lang.equals("FCL")) {
				        goon= false;
				        System.out.println("Please, check the arguments given to the program");
					    System.out.println("Unfortunately, you select an unknown Language");
						System.out.println("Notice that the program has 3 main arguments (Language ProblemName DataFile) but brackets are not required");
					    System.out.println("  Options: MATLAB [Tipper | Iris] output.xml");
					    System.out.println("  Options: FCL [Tipper | Robot] output.xml");
					    System.out.println("  Options: PMML [Tipper | Iris] output.xml");
				    } else if (Lang.equals("MATLAB")) {
			    		ifs= "./XMLFiles/iris.fis"; 
				    } else if (Lang.equals("PMML")) {
			    		ifs= "./XMLFiles/IrisMamdani2.frbsPMML"; 
		    	    }
		    	} else {
			        goon= false;
			        System.out.println("Please, check the arguments given to the program");
				    System.out.println("Unfortunately, you select an unknown ProblemName");
					System.out.println("Notice that the program has 3 main arguments (Language ProblemName DataFile) but brackets are not required");
				    System.out.println("  Options: MATLAB [Tipper | Iris] output.xml");
				    System.out.println("  Options: FCL [Tipper | Robot] output.xml");
				    System.out.println("  Options: PMML [Tipper | Iris] output.xml");
		    	}
		    	if (goon && !ifs.equals("")) {
			    	//loading Fuzzy System from an file according the standard FCL (IEC 1131)
		    		FuzzyInferenceSystem fs = is.importFuzzySystem(ifs);
		    		if (fs!=null) {
		    		  File dirXMLFiles = new File("./XMLFiles/");
		    		  if (!dirXMLFiles.exists())
		    			dirXMLFiles.mkdir();

		    		  JFML.writeFSTtoXML(fs, fauxOut);
		    		  System.out.println("Imported fuzzy system: from "+Lang+ " to JFML");
		    		  System.out.println("New file: "+fauxOut);
		    		  //printing the FuzzySystem
		    		  System.out.println(fs.toString());
		    		}
		    	}
		    }
        }
	}
}