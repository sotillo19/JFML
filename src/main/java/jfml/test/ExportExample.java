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
 * This class presents some examples to export a fuzzy system to either MATLAB format or FCL (IEC 1131) and PMML.  
 * 
 * @author Jose Alonso
 *
 */
public class ExportExample {
	
	public static void main(String[] args)  throws IOException {
        if (args.length==2) {
		    Export es=null;
		    File fIn= new File(args[0]);
		    File fOut= new File(args[1]);
		    boolean goon= true;
        	if (!fIn.exists()) {
    		    System.out.println("WARNING: The input file does not exist.");
    		    goon=false;
        	} 
        	if (goon && fOut.exists()) {
    		    System.out.println("WARNING: The output file already exists.");
		        System.out.println("Please, launch the program again with another output file name.");
    		    goon=false;
        	}
        	if (goon && (!args[0].endsWith(".xml"))) {
    		    System.out.println("WARNING: The input file name must ends with .xml");
    		    goon=false;
        	}
        	if (goon && (!args[1].endsWith(".fcl")) && (!args[1].endsWith(".fis")) && (!args[1].endsWith(".frbsPMML"))) {
    		    System.out.println("WARNING: Only FCL (\'*.fcl\'), MATLAB (\'*.fis\') and PMML (\'*.frbsPMML\') files are admitted as output files.");
    		    goon=false;
        	}
            if (goon) {
	    		System.out.println("Input file: "+fIn.getCanonicalPath());
				FuzzyInferenceSystem fs = JFML.load(fIn);
				// without calling to evaluate it is considered always as Mamdani
    		    System.out.println(fs.toString());
    		    if (args[1].endsWith(".fcl")) {
    		        es= new ExportFCL();
    		    } else if (args[1].endsWith(".fis")) {
    		        es= new ExportMatlab();
    		    } else if (args[1].endsWith(".frbsPMML")) {
    		        es= new ExportPMML();
    		    }
    		    if (es!=null) {
   		          es.exportFuzzySystem(fs,args[1]);
   		          if (fOut.exists()) {
   		            System.out.println("New file: "+fOut.getCanonicalPath());
   		          } else {
   		            System.out.println("WARNING: No output file was created.");
   		          }
    		    } else {
    			    System.out.println("Please, check the arguments given to the program");
    				System.out.println("Notice that the program has 2 main arguments");
    			    System.out.println("  Options: inputFile outputFile");
    		    }
            }
        } else if (args.length!=3) {
		    System.out.println("Please, check the arguments given to the program");
			System.out.println("Notice that the program has 3 main arguments (Language ProblemName OutputFile) but brackets are not required");
		    System.out.println("  Options: [FCL | MATLAB | PMML] [IrisMamdani1 | TipperMamdani1 | TipperTsukamoto1 | TipperAnYa1 | TipperTSK] outputFile");
        } else {
		    Export es=null;
		    String Lang=args[0];
		    boolean goon= true;
		    if (Lang.equals("FCL")) {
		        es= new ExportFCL();
		        if (!args[2].endsWith(".fcl")) {
	    		    System.out.println("WARNING: The output file name must ends with .fcl");
	    		    goon=false;
		        }
		    } else if (Lang.equals("MATLAB")) {
		        es= new ExportMatlab();
		        if (!args[2].endsWith(".fis")) {
	    		    System.out.println("WARNING: The output file name must ends with .fis");
	    		    goon=false;
		        }
		    } else if (Lang.equals("PMML")) {
		        es= new ExportMatlab();
		        if (!args[2].endsWith(".frbsPMML")) {
	    		    System.out.println("WARNING: The output file name must ends with .frbsPMML");
	    		    goon=false;
		        }
		    } else {
		        goon= false;
		        System.out.println("Please, check the arguments given to the program");
			    System.out.println("Unfortunately, you select an unknown Language");
				System.out.println("Notice that the program has 3 main arguments (Language ProblemName OutputFile) but brackets are not required");
			    System.out.println("  Options: [FCL | MATLAB | PMML] [IrisMamdani1 | TipperMamdani1 | TipperTsukamoto1 | TipperAnYa1 | TipperTSK] outputFile");
		    }
		    if (goon && es!=null) {
		        File fOut= new File(args[2]);
		        File xmlFile= null;
		    	if (args[1].equals("IrisMamdani1")) {
		    		xmlFile = new File("./XMLFiles/IrisMamdani1.xml");
		    	} else if (args[1].equals("TipperMamdani1")) {
		    		xmlFile = new File("./XMLFiles/TipperMamdani1.xml");
		    	} else if (args[1].equals("TipperTsukamoto1")) {
		    		xmlFile = new File("./XMLFiles/TipperTsukamoto1.xml");
		    	} else if (args[1].equals("TipperAnYa1")) {
		    		xmlFile = new File("./XMLFiles/TipperAnYa1.xml");
		    	} else if (args[1].equals("TipperTSK")) {
		    		xmlFile = new File("./XMLFiles/TipperTSK.xml");
		    	} else {
			        goon= false;
			        System.out.println("Please, check the arguments given to the program");
				    System.out.println("Unfortunately, you select an unknown ProblemName");
					System.out.println("Notice that the program has 3 main arguments (Language ProblemName DataFile) but brackets are not required");
				    System.out.println("  Options: [FCL | MATLAB | PMML] [IrisMamdani1 | TipperMamdani1 | TipperTsukamoto1 | TipperAnYa1 | TipperTSK] outputFile");
		    	}
		    	if (goon) {
			    	//loading Fuzzy System from an file according the standard FCL (IEC 1131)
					if (xmlFile.exists()) {
		    		    System.out.println("Input file: "+xmlFile.getCanonicalPath());
						FuzzyInferenceSystem fs = JFML.load(xmlFile);
						// without calling to evaluate it is considered always as Mamdani
		    		    System.out.println(fs.toString());
		    		    if (fOut.exists()) {
		    		        System.out.println("WARNING: Output file already exists.");
		    		        System.out.println("Please, launch the program again with another output file name.");
		    		    } else {
		    		        es.exportFuzzySystem(fs,args[2]);
		    		        if (fOut.exists()) {
		    		            System.out.println("New file: "+fOut.getCanonicalPath());
		    		        } else {
		    		            //System.out.println("Something went wrong in the EXPORT process.\n No file ("+fout.getCanonicalPath()+") was created.");
		    		            System.out.println("WARNING: No output file was created.");
		    		        }
		    		    }
					} else {
				        System.out.println("Please, check the arguments given to the program");
					    System.out.println("There is no XML file for the selected fuzzy system");
						System.out.println("Notice that the program has 3 main arguments (Language ProblemName DataFile) but brackets are not required");
					    System.out.println("  Options: [FCL | MATLAB | PMML] [IrisMamdani1 | TipperMamdani1 | TipperTsukamoto1 | TipperAnYa1 | TipperTSK] outputFile");
					}
		    	}
		    }
        }
	}
}