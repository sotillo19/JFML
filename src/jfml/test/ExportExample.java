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
 * This class presents some examples to export a fuzzy system to either MATLAB format or FCL (IEC 1131).  
 * 
 * @author Jose Alonso
 *
 */
public class ExportExample {
	
	public static void main(String[] args)  throws IOException {
        if (args.length!=2) {
		    System.out.println("Please, check the arguments given to the program");
			System.out.println("Notice that the program has 2 main arguments (Language ProblemName DataFile) but brackets are not required");
		    System.out.println("  Options: [FCL | MATLAB] [IrisMamdani1 | TipperMamdani1 | TipperTsukamoto1 | TipperAnYa1 | TipperTSK]");
        } else {
		    Export es=null;
		    String Lang=args[0];
		    boolean goon= true;
		    if (Lang.equals("FCL")) {
		        es= new ExportFCL();
		    } else if (Lang.equals("MATLAB")) {
		        es= new ExportMatlab();
		    } else {
		        goon= false;
		        System.out.println("Please, check the arguments given to the program");
			    System.out.println("Unfortunately, you select an unknown Language");
				System.out.println("Notice that the program has 2 main arguments (Language ProblemName DataFile) but brackets are not required");
			    System.out.println("  Options: [FCL | MATLAB] [IrisMamdani1 | TipperMamdani1 | TipperTsukamoto1 | TipperAnYa1 | TipperTSK]");
		    }
		    if (goon && es!=null) {
		        String ifs="";	
		        File xmlFile= null;
		    	if (args[1].equals("IrisMamdani1")) {
				    if (Lang.equals("FCL")) {
				    	ifs= "./XMLFiles/IrisMamdani1.fcl";
				    } else if (Lang.equals("MATLAB")) {
				    	ifs= "./XMLFiles/IrisMamdani1.fis";
				    } 
		    		xmlFile = new File("./XMLFiles/IrisMamdani1.xml");
		    	} else if (args[1].equals("TipperMamdani1")) {
				    if (Lang.equals("FCL")) {
				    	ifs= "./XMLFiles/TipperMamdani1.fcl";
				    } else if (Lang.equals("MATLAB")) {
				    	ifs= "./XMLFiles/TipperMamdani1.fis";
				    } 
		    		xmlFile = new File("./XMLFiles/TipperMamdani1.xml");
		    	} else if (args[1].equals("TipperTsukamoto1")) {
				    if (Lang.equals("FCL")) {
				    	ifs= "./XMLFiles/TipperTsukamoto1.fcl";
				    } else if (Lang.equals("MATLAB")) {
				    	ifs= "./XMLFiles/TipperTsukamoto1.fis";
				    } 
		    		xmlFile = new File("./XMLFiles/TipperTsukamoto1.xml");
		    	} else if (args[1].equals("TipperAnYa1")) {
				    if (Lang.equals("FCL")) {
				    	ifs= "./XMLFiles/TipperAnYa1.fcl";
				    } else if (Lang.equals("MATLAB")) {
				    	ifs= "./XMLFiles/TipperAnYa1.fis";
				    } 
		    		xmlFile = new File("./XMLFiles/TipperAnYa1.xml");
		    	} else if (args[1].equals("TipperTSK")) {
				    if (Lang.equals("FCL")) {
				    	ifs= "./XMLFiles/TipperTSK.fcl";
				    } else if (Lang.equals("MATLAB")) {
				    	ifs= "./XMLFiles/TipperTSK.fis";
				    } 
		    		xmlFile = new File("./XMLFiles/TipperTSK.xml");
		    	} else {
			        goon= false;
			        System.out.println("Please, check the arguments given to the program");
				    System.out.println("Unfortunately, you select an unknown ProblemName");
					System.out.println("Notice that the program has 2 main arguments (Language ProblemName DataFile) but brackets are not required");
				    System.out.println("  Options: [FCL | MATLAB] [IrisMamdani1 | TipperMamdani1 | TipperTsukamoto1 | TipperAnYa1 | TipperTSK]");
		    	}
		    	if (goon && !ifs.equals("")) {
			    	//loading Fuzzy System from an file according the standard FCL (IEC 1131)
					if (xmlFile.exists()) {
		    		    System.out.println("Input file: "+xmlFile.getCanonicalPath());
						FuzzyInferenceSystem fs = JFML.load(xmlFile);
						// without calling to evaluate it is considered always as Mamdani
		    		    System.out.println(fs.toString());
		    		    es.exportFuzzySystem(fs,ifs);
		    		    File fout= new File(ifs);
		    		    if (fout.exists()) {
		    		        System.out.println("New file: "+fout.getCanonicalPath());
		    		    } else {
		    		        //System.out.println("Something went wrong in the EXPORT process.\n No file ("+fout.getCanonicalPath()+") was created.");
		    		        System.out.println("WARNING: No output file was created.");
		    		    }
					} else {
				        System.out.println("Please, check the arguments given to the program");
					    System.out.println("There is no XML file for the selected fuzzy system");
						System.out.println("Notice that the program has 2 main arguments (Language ProblemName DataFile) but brackets are not required");
					    System.out.println("  Options: [FCL | MATLAB] [IrisMamdani1 | TipperMamdani1 | TipperTsukamoto1 | TipperAnYa1 | TipperTSK]");
					}
		    	}
		    }
        }
	}
}
