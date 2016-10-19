package jfml.test;

import java.io.File;
import java.io.LineNumberReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.util.Vector;

import jfml.FuzzyInferenceSystem;
import jfml.JFML;
import jfml.knowledgebase.variable.KnowledgeBaseVariable;

/**
 * This class represents several cases of inference with several examples:
 * 1) the Tipper System Example
 * 2) the Japanese Diet Assessment System Example
 * 3) IRIS classifiction problem Example
 * 4) InvertedPendulum control problem Example
 * @author joseAlonso
 */
public class EvaluateExample {
    private String xml= null;
	private FuzzyInferenceSystem fs= null;
	private String exOpt= null;
	private int NbData= -1;
	private int NbInputs= -1;
    private String[] inputNames= null;
	private float[][] data= null;

	public EvaluateExample() {
	      System.out.println("Prueba R");
          this.xml= "jfmlTest/XMLFiles/GeneratedTipperExampleOUT_Mamdani.xml";
	}

    public EvaluateExample(String example, String infOpt, String dataFile) {
	      boolean warning= false;
	      if (dataFile != null) {
	        try{
	          LineNumberReader lnr= new LineNumberReader(new InputStreamReader(new FileInputStream(dataFile)));
			  Vector<String> v= new Vector<String>();
              String l= lnr.readLine();
			  this.inputNames= l.split(";");
              while ((l= lnr.readLine())!=null) {
		        //System.out.println(l);
			    v.add(l);
              }
			  Object[] obj= v.toArray();
			  //System.out.println("rows="+obj.length+"  cols="+ins.length);
			  this.NbData= obj.length;
			  this.NbInputs= this.inputNames.length;
			  data= new float[this.NbData][this.NbInputs];
			  for (int n= 0; n<this.NbData; n++) {
			    String[] aux= ((String)obj[n]).split(";");
			    for (int m= 0; m<this.NbInputs; m++) {
			        data[n][m]= (new Float(aux[m])).floatValue();
			    }
			  }
		      lnr.close();
		    } catch (Exception ex) {
		        System.out.println("ERROR reading data file");
				warning= true;
		    }
		  }
		  if (!warning) {
            String mainf=null;
	        if (example.equals("Tipper")) {
		      mainf= "./XMLFiles/GeneratedTipperExampleOUT_";
		    } else if (example.equals("JapaneseDietAssessment")) {
		      mainf= "./XMLFiles/GeneratedJapaneseDietAssessmentExampleOUT_";
		    } else if (example.equals("Iris")) {
		      mainf= "./XMLFiles/GeneratedIrisExampleOUT_";
		    } else if (example.equals("InvertedPendulum")) {
		      mainf= "./XMLFiles/GeneratedInvertedPendulumExampleOUT_";
		    }
		    if (mainf!=null) {
		      this.exOpt= example;
	          if ( (infOpt.equals("Mamdani")) || (infOpt.equals("Mamdani2")) || (infOpt.equals("TSK")) || (infOpt.equals("Tsukamoto")) || (infOpt.equals("Tsukamoto2")) ) {
		        this.xml = mainf+infOpt+".xml";
		      }
		    }
            if ( (xml!=null) && (this.xml.endsWith(".xml")) ) {
		      boolean res= this.loadFIS();
			  if (res) {
			      for (int n=0; n<this.NbData; n++) {
                       this.makeInference(n);
				  }
				  //printing the FuzzySystem
		          System.out.println("4) Fuzzy System Description");
		          System.out.println(fs.toString());
			  } else { 
			      System.out.println("Error loading FIS from XML");
			  }
            } else {
		      System.out.println("Options: Tipper [Mamdani | Mamdani2 | TSK | Tsukamoto | Tsukamoto2] test-data-file");
		      System.out.println("Options: JapaneseDietAssessment Mamdani test-data-file");
		      System.out.println("Options: Iris [Mamdani | Mamdani2] test-data-file");
		      System.out.println("Options: InvertedPendulum [Mamdani | Mamdani2 | TSK] test-data-file");
			  System.out.println("Notice that the program has 3 arguments (ProblemName InferenceExample DataFile) but brackets are not required");
		    }
		  }
	}

    public void setExOption(String eo) {
	        this.exOpt= eo;
	}

    public String getExOption() {
	        return this.exOpt;
	}

    public boolean loadFIS() {
	        return this.loadFIS(this.xml);
	}

    public boolean loadFIS(String x) {
		    //loading Fuzzy System from an XML file according the standard IEEE 1855
		    System.out.println("1) Loading Fuzzy System from an XML file according the standard IEEE 1855");
		    this.fs = JFML.load(new File(x));
		    System.out.println();
            if (this.fs!=null) {
			    return true;
			} else {
			    return false;
            }			
  		    //printing the FuzzySystem
		    //System.out.println(fs.toString());
	}

    public float makeInference(int d) {
	        float value= -1; // no inference
			//System.out.println("OUT="+value);
		    // set inputs values
			if (this.exOpt != null) {
			  boolean warning= false;
			  KnowledgeBaseVariable[] kbv= null;
			  if (d>=0)
		          System.out.print("2."+d+") Setting input variables: ");
			  else
		          System.out.print("2) Setting input variables: ");
				  
			  if ( (this.exOpt.equals("Tipper")) || (this.exOpt.equals("JapaneseDietAssessment")) || (this.exOpt.equals("Iris")) || (this.exOpt.equals("InvertedPendulum")) ) {
				  kbv = new KnowledgeBaseVariable[this.NbInputs];
				  for (int m=0; m<this.NbInputs; m++) {
		            kbv[m] = this.fs.getVariable(this.inputNames[m]);
		            kbv[m].setValue(data[d][m]);
		            System.out.print(this.inputNames[m]+"="+data[d][m]);
					if (m<this.NbInputs-1)
					    System.out.print(", ");
					else
					    System.out.println();
				  }
			  } else {
			      System.out.println("Unknown example option");
				  warning= true;
			  }
			  if (!warning) {
		        //System.out.println();
		        // inference
			    if (d>=0)
		            System.out.println("3."+d+") Making fuzzy inference");
				else
		            System.out.println("3) Making fuzzy inference");

			    this.fs.evaluate();
		
		        // get output
				KnowledgeBaseVariable output= null;
			    if (this.exOpt.equals("Tipper")) {
		            output =  this.fs.getVariable("tip");
				} else if (this.exOpt.equals("JapaneseDietAssessment")) {
		            output =  this.fs.getVariable("DHL");
				} else if (this.exOpt.equals("Iris")) {
		            output =  this.fs.getVariable("irisClass");
				} else if (this.exOpt.equals("InvertedPendulum")) {
		            output =  this.fs.getVariable("Force");
				}
				if (output != null) {
	                value = output.getValue();
		        }
		        //printing results
		        System.out.println("RESULTS");
				int lim= this.NbInputs;
				if (lim > 0) {
                    System.out.print(" (INPUT): ");
				    for (int n=0; n<lim; n++) {
			             System.out.print(kbv[n].getName()+"="+kbv[n].getValue());
						 if (n<lim-1)
						     System.out.print(", ");
					}
					System.out.println();
				}
		        System.out.println(" (OUTPUT): "+output.getName()+"="+ value);
		        System.out.println();
		
		        //printing the FuzzySystem
		        //System.out.println("4) Fuzzy System Description");
		        //System.out.println(fs.toString());
			  }
			}
			return value;
    }

	public static void main(String[] args) {

		if (args!=null && args.length == 3) {
              new EvaluateExample(args[0], args[1], args[2]);
		} else {
		      System.out.println("Options: Tipper [Mamdani | Mamdani2 | TSK | Tsukamoto | Tsukamoto2] test-data-file");
		      System.out.println("Options: JapaneseDietAssessment Mamdani test-data-file");
		      System.out.println("Options: Iris [Mamdani | Mamdani2] test-data-file");
		      System.out.println("Options: InvertedPendulum [Mamdani | Mamdani2 | TSK] test-data-file");
			  System.out.println("Notice that the program has 3 arguments (ProblemName InferenceExample DataFile) but brackets are not required");
        }

	}

}
