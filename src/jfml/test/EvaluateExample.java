package jfml.test;

import java.io.File;

import jfml.FuzzyInferenceSystem;
import jfml.JFML;
import jfml.knowledgebase.variable.KnowledgeBaseVariable;

/**
 * This class represents several cases of inference with the Tipper System Example and the Japanese Diet Assessment System Example
 * @author joseAlonso
 *
 */
public class EvaluateExample {
    private String xml= null;
	private FuzzyInferenceSystem fs= null;
	private String exOpt= null;

	public EvaluateExample() {
	      System.out.println("Prueba R");
          this.xml= "jfmlTest/XMLFiles/GeneratedTipperExampleOUT_Mamdani.xml";
          
          new EvaluateExample("JapaneseDietAssessment", "Mamdani");
	}

    public EvaluateExample(String example, String infOpt) {
          String mainf=null;
	      if (example.equals("Tipper")) {
		      mainf= "./XMLFiles/GeneratedTipperExampleOUT_";
		  } else if (example.equals("JapaneseDietAssessment")) {
		      mainf= "./XMLFiles/GeneratedJapaneseDietAssessmentExampleOUT_";
		  }
		  if (mainf!=null) {
		    this.exOpt= example;
	        if ( (infOpt.equals("Mamdani")) || (infOpt.equals("Mamdani2")) || (infOpt.equals("TSK")) || (infOpt.equals("Tsukamoto")) || (infOpt.equals("Tsukamoto2")) ) {
		      this.xml = mainf+infOpt+".xml";
		    }
		  }
          if (xml!=null) {
		      boolean res= this.loadFIS();
			  if (res) {
                  this.makeInference();
			  } else { 
			      System.out.println("Error loading FIS from XML");
			  }
          } else {
		      System.out.println("Options: [Tipper | JapaneseDietAssessment] [Mamdani | Mamdani2 | TSK | Tsukamoto | Tsukamoto2]");
			  System.out.println("Notice that the program has 2 arguments but brackets are not required");
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
	
    public float makeInference() {
	        float value= -1; // no inference
		    // set inputs values
			if (this.exOpt != null) {
			  boolean warning= false;
			  KnowledgeBaseVariable[] kbv= null;
			  if (this.exOpt.equals("Tipper")) {
		          System.out.println("2) Setting input variables: food=6 and service=8");
				  kbv = new KnowledgeBaseVariable[2];
		          kbv[0] = this.fs.getVariable("food");
		          kbv[1] = this.fs.getVariable("service");
		          kbv[0].setValue(6f);
		          kbv[1].setValue(8f);
			  } else if (this.exOpt.equals("JapaneseDietAssessment")) {
		          System.out.println("2) Setting input variables: PCC=50.0%, PCP=18.2%, PCF=28.6%, PCR=91.4, and FGB=3.83");
				  kbv = new KnowledgeBaseVariable[5];
		          kbv[0] = this.fs.getVariable("PCC");
		          kbv[1] = this.fs.getVariable("PCP");
		          kbv[2] = this.fs.getVariable("PCF");
		          kbv[3] = this.fs.getVariable("PCR");
		          kbv[4] = this.fs.getVariable("FGB");
				  // sample 1 in TableVIII (ref Paper)
				  // expected output = 5.0 (with all 243 rules)
		           kbv[0].setValue(50.0f);
		           kbv[1].setValue(12.2f);
		           kbv[2].setValue(18.6f);
		           kbv[3].setValue(91.4f);
		           kbv[4].setValue(1.83f);
				  // sample 3 in TableVIII (ref Paper)
				  // expected output = 1.02 (with all 243 rules)
		          // kbv[0].setValue(45.0f);
		          // kbv[1].setValue(21.6f);
		          // kbv[2].setValue(31.1f);
		          // kbv[3].setValue(82.9f);
		          // kbv[4].setValue(3.95f);
                  // low values in all inputs (expected output = very low)
		           //kbv[0].setValue(5.5f);
		           //kbv[1].setValue(5.5f);
		           //kbv[2].setValue(5.5f);
		           //kbv[3].setValue(5.5f);
		           //kbv[4].setValue(0.5f);
                  // high values in all inputs (expected output = low)
		          // kbv[0].setValue(95.5f);
		          // kbv[1].setValue(95.5f);
		          // kbv[2].setValue(95.5f);
		          // kbv[3].setValue(150.5f);
		          // kbv[4].setValue(6.1f);
		          // kbv[0].setValue(50);
		          // kbv[1].setValue(50);
		          // kbv[2].setValue(50);
		          // kbv[3].setValue(100);
		          // kbv[4].setValue(3);
			  } else {
			      System.out.println("Unknown example option");
				  warning= true;
			  }
			  if (!warning) {
		        System.out.println();
		    
		        // inference
		        System.out.println("3) Making fuzzy inference");
		        this.fs.evaluate();
		
		        // get output
				KnowledgeBaseVariable output= null;
			    if (this.exOpt.equals("Tipper")) {
		            output =  this.fs.getVariable("tip");
				} else if (this.exOpt.equals("JapaneseDietAssessment")) {
		            output =  this.fs.getVariable("DHL");
				}
				if (output != null)
	                value = output.getValue();
		
		        //printing results
		        System.out.println("RESULTS");
				int lim=-1;
			    if (this.exOpt.equals("Tipper")) {
				    lim=2;
				} else if (this.exOpt.equals("JapaneseDietAssessment")) {
				    lim=5;
				}
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
		        System.out.println("4) Fuzzy System Description");
		        System.out.println(fs.toString());
			  }
			}
			return value;
	}
	
	public static void main(String[] args) {

		if (args!=null && args.length == 2) {
              new EvaluateExample(args[0], args[1]);
		} else {
		      System.out.println("Options: [Tipper | JapaneseDietAssessment] [Mamdani | Mamdani2 | TSK | Tsukamoto | Tsukamoto2]");
			  System.out.println("Notice that the program has 2 arguments but brackets are not required");
        }

	}

}
