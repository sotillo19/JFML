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
