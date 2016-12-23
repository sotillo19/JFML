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
