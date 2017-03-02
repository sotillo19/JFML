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
package jfml.compatibility;

import java.io.*;
import jfml.FuzzyInferenceSystem;

/**
 * Abstract class to create parsers to export fuzzy systems to other formats.
 * @author Jesus Alcala-Fdez
 *
 */
public abstract class Export {
	/**
	 * Constructor by default
	 */
	protected Export() {
	}

	/**
	 * Write contents in a file  
	 * @param file : Path of the output file
	 * @param contents
	 * @throws java.io.IOException
	 */
    protected static void writeFile(String file, String contents) {
        try {
            FileOutputStream f = new FileOutputStream(file);
            DataOutputStream fis = new DataOutputStream(f);
            fis.writeBytes(contents);
            fis.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit( -1);
        }
    }
    
	/** 
	 * Export the fuzzy system from IEEE Standard to another format. Write it in file.
	 * @param file : A String with the path of the File
	 * @param fuzzySystem : An object FuzzyInferenceSystem
	 * @return 
	 */
	public abstract void exportFuzzySystem(FuzzyInferenceSystem fuzzySystem, String file);
}
