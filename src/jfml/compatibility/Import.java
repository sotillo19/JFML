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
 * Abstract class to create parsers to import fuzzy systems from other formats.
 * @author Jesus Alcala-Fdez
 *
 */
public abstract class Import {

	/**
	 * Constructor by default
	 */
	protected Import() {
	}

	/**
	 * Read a file and return it in a String  
	 * @param path
	 * Path of the file
	 * @return
     *     possible object is
     *     {@link String }
	 * @throws java.io.IOException
	 */
	protected static String readFile(String path) throws java.io.IOException {
		String contents = "";

		FileInputStream fis = new FileInputStream(path);
		byte[] size = new byte[4096];
		int bytesRead = 0;
		while (bytesRead != -1) {
			bytesRead = fis.read(size);
			if (bytesRead != -1) {
				contents += new String(size, 0, bytesRead);
			}
		}
		fis.close();

		return contents;
	}

	/**
	 * Import a fuzzy system from another format.
	 * 
	 * @param path
	 * Path of the file with the fuzzy system in another format
	 * @return An object FuzzyInferenceSystem
	 * @throws IOException
	 */
	public abstract FuzzyInferenceSystem importFuzzySystem(String path) throws IOException;
}
