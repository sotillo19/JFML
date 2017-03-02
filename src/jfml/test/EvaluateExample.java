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
 * 3) IRIS classification problem Example
 * 4) InvertedPendulum control problem Example
 * 5) Robot problem Example
 *
 * Each example can be tested from command line and also with the test-data files provided in the XMLFiles folder:
 * 1) test-data-Tipper.txt
 * 2) test-data-JapaneseDietAssessment.txt
 * 3) test-data-Iris1.txt (irisMamdani1.xml and irisMamdani3.xml) and test-data-Iris2.txt (irisMamdani2.xml)
 * 4) test-data-InvertedPendulum.txt
 * 5) test-data-Robot.txt
 *
 * @author Jose Alonso
 */
public class EvaluateExample {
    private String xml= null;
	private FuzzyInferenceSystem fs= null;
	private String exOpt= null;
	private int NbData= -1;
	private int NbInputs= -1;
	private int NbOutputs= -1;
    private String[] inputNames= null;
    private String[] outputNames= null;
	private float[][] data= null;

	public EvaluateExample() {
	      System.out.println("Prueba R");
          this.xml= "jfmlTest/XMLFiles/TipperMamdani1.xml";
	}

	public EvaluateExample(String[] aa) {
	      boolean warning= false;
          String example= aa[0];
		  String infOpt= aa[1];
		  this.NbOutputs=0;
		  boolean end= false;
		  if (example.endsWith(".xml")) {
			  File f= new File(example);
			  if (!f.exists()) {
			      warning= true;
			      System.out.println("WRONG XML FILE PATH. FILE DOES NOT EXIST");
			  } else {
		          end=true;
			      //System.out.println("XML FILE PATH EXISTS");
				  this.exOpt= "USERXML";
				  this.NbOutputs= (new Integer(infOpt)).intValue();
				  this.outputNames= new String[this.NbOutputs];
				  for (int i=0; i<this.NbOutputs; i++) {
				       this.outputNames[i]= aa[2+i];
				  }
			  }
		  }
		  this.NbData= 1;
		  int lim= aa.length;
		  this.NbInputs= (lim - 2 - this.NbOutputs)/2;
		  this.inputNames= new String[this.NbInputs];
		  this.data= new float[this.NbData][this.NbInputs];
		  int i=2 + this.NbOutputs;
          //System.out.println("NbInputs="+this.NbInputs);
          //System.out.println("NbOutputs="+this.NbOutputs);
	      for (int m= 0; m<this.NbInputs; m++) {
			   this.inputNames[m]= aa[i+m];
			   Float ff= null;
			   try {
			       ff= new Float(aa[i+m+1]);
			   } catch (Exception ex) {
			       System.out.println("WRONG NUMBER OF OUTPUTS");
				   warning = true;
				   break;
			   }
			   if (ff!=null) {
			       this.data[0][m]= ff.floatValue();
			   }
			   i++;
		  }
	        /*for (int m= 0; m<this.NbInputs; m++) {
               System.out.println("V"+m+": "+this.inputNames[m]);
               System.out.println("D"+m+": "+this.data[0][m]);
		    }*/
		  if (!end && !warning) {
		    String mainf=null;
	        if ( (example.equals("Tipper")) || (example.equals("JapaneseDietAssessment")) || (example.equals("Iris")) || (example.equals("InvertedPendulum")) ) {
		      this.NbOutputs=1;
		    } else if (example.equals("Robot")) {
		      this.NbOutputs=2;
		    }
			if (this.NbOutputs > 0) {
	          mainf= "./XMLFiles/"+example;
			}
		    if (mainf!=null) {
		      this.exOpt= example;
	          if ( (infOpt.equals("Mamdani")) || (infOpt.equals("Mamdani1")) || (infOpt.equals("Mamdani2")) || (infOpt.equals("Mamdani3")) || (infOpt.equals("TSK")) || (infOpt.equals("TSK1")) || (infOpt.equals("TSK2")) || (infOpt.equals("Tsukamoto")) || (infOpt.equals("Tsukamoto1")) || (infOpt.equals("Tsukamoto2")) || (infOpt.equals("AnYa")) || (infOpt.equals("AnYa1")) ) {
		        this.xml = mainf+infOpt+".xml";
		      }
		    }
            //System.out.println("XML="+this.xml);
            if ( (this.xml!=null) && (this.xml.endsWith(".xml")) ) {
		      boolean res= this.loadFIS();
			  if (res) {
			      for (int n=0; n<this.NbData; n++) {
                       float[] v= this.makeInference(n);
					   for (int k=0; k<this.NbOutputs; k++) {
					     if (v[k] == -1) {
					       warning= true;
  		                   System.out.println("ERROR: WRONG INFERENCE");
                           break;						   
					     }
					   }
				  }
				  //printing the FuzzySystem
		          System.out.println("4) Fuzzy System Description");
		          System.out.println(fs.toString());
			  } else { 
		          warning=true;
			      System.out.println("ERROR loading FIS from XML");
			  }
          } else {
		      warning=true;
              System.out.println("ERROR: WRONG XML FILE");
		      //System.out.println("O1");
		  }
		}
		if (end && !warning) {
		    this.xml= example;
		    boolean res= this.loadFIS();
			//System.out.println("res="+res);
	        this.exOpt= "USERXML";
			if (res) {
			      for (int n=0; n<this.NbData; n++) {
                       float[] v= this.makeInference(n);
					   for (int k=0; k<this.NbOutputs; k++) {
					     if (v[k] == -1) {
					       warning= true;
  		                   System.out.println("ERROR: WRONG INFERENCE");
                           break;						   
					     }
					   }
				  }
				  if (!warning) {
				    //printing the FuzzySystem
		            System.out.println("4) Fuzzy System Description");
		            System.out.println(fs.toString());
				  }
			} else { 
			    System.out.println("ERROR loading FIS from XML");
			    warning= true;
			}
		}
		if (warning) {
		      System.out.println("Please, check the arguments given to the program");
			  System.out.println("Notice that the program has 3 main arguments (ProblemName InferenceExample DataFile) but brackets are not required");
		      System.out.println("  Options: Tipper [Mamdani1 | Mamdani2 | Mamdani3 | TSK | Tsukamoto1 | Tsukamoto2 | AnYa] test-data-file");
		      System.out.println("  Options: JapaneseDietAssessment Mamdani test-data-file");
		      System.out.println("  Options: Iris [Mamdani1 | Mamdani2 | Mamdani3] test-data-file");
		      System.out.println("  Options: InvertedPendulum [Mamdani1 | Mamdani2 | TSK1 | TSK2] test-data-file");
		      System.out.println("  Options: Robot Mamdani test-data-file");
			  System.out.println("You can also call the program with a specific instance as follows:");
			  System.out.println("  Options: ProblemName InferenceExample V1 D1 V2 D2 ... ");
			  System.out.println("  ProblemName: Tipper, JapaneseDietAssessment, etc.");
			  System.out.println("  InferenceExample; Mamdani, Mamdani1, Mamdani2, TSK, etc.");
			  System.out.println("  Notice that the combination of ProblemName and InferenceExample must be in accordance with the name of an XML file in the folder ./XMLFiles");
			  System.out.println("  You must be also sure of providing the entire list of pairs variable name (Vi, as it is in the XML file) and numerical value (Di) for evaluation");
			  System.out.println("  Example:");
			  System.out.println("> Iris Mamdani2 SepalLength 5.1 SepalWidth 3.5 PetalLength 1.4 PetalWidth 0.2");
			  System.out.println("You can also call the program with a specific instance, for your own XML file, as follows:");
			  System.out.println("  Options: XMLfilePath NbOutputs ON1 ON2 ... V1 D1 V2 D2 ... ");
		}
	}

    public EvaluateExample(String example, String infOpt, String dataFile) {
          String mainf=null;
	      if ( (example.equals("Tipper")) || (example.equals("JapaneseDietAssessment")) || (example.equals("Iris")) || (example.equals("InvertedPendulum")) ){
		      this.NbOutputs=1;
		  } else if (example.equals("Robot")) {
			  this.NbOutputs=2;
		  }
		  if (this.NbOutputs > 0) {
              mainf= "./XMLFiles/"+example;
		  }
		  if (mainf!=null) {
		      this.exOpt= example;
	          if ( (infOpt.equals("Mamdani")) || (infOpt.equals("Mamdani1")) || (infOpt.equals("Mamdani2")) || (infOpt.equals("Mamdani3")) || (infOpt.equals("TSK")) || (infOpt.equals("TSK1")) || (infOpt.equals("TSK2")) || (infOpt.equals("Tsukamoto")) || (infOpt.equals("Tsukamoto1")) || (infOpt.equals("Tsukamoto2")) || (infOpt.equals("AnYa")) || (infOpt.equals("AnYa1")) ) {
		        this.xml = mainf+infOpt+".xml";
		      }
		  }
		  this.Evaluate(this.xml,dataFile);
	}

    public EvaluateExample(String xmlfile, String dataFile) {
       this.Evaluate(xmlfile, dataFile);
    }
    
    private void Evaluate(String xmlfile, String dataFile) {
	      boolean warning= false;
		  File faux= new File(dataFile);
	      if (faux.exists()) {
	        try{
	          LineNumberReader lnr= new LineNumberReader(new InputStreamReader(new FileInputStream(dataFile)));
			  Vector<String> v= new Vector<String>();
              String l= lnr.readLine();
			  this.outputNames= l.split(";");
              l= lnr.readLine();
              this.inputNames= l.split(";");
              while ((l= lnr.readLine())!=null) {
		        //System.out.println(l);
			    v.add(l);
              }
			  Object[] obj= v.toArray();
			  //System.out.println("rows="+obj.length+"  cols="+ins.length);
			  this.NbData= obj.length;
			  this.NbInputs= this.inputNames.length;
			  this.data= new float[this.NbData][this.NbInputs];
			  for (int n= 0; n<this.NbData; n++) {
			    String[] aux= ((String)obj[n]).split(";");
			    for (int m= 0; m<this.NbInputs; m++) {
			        this.data[n][m]= (new Float(aux[m])).floatValue();
			    }
			  }
		      lnr.close();
		    } catch (Exception ex) {
		        System.out.println("ERROR reading DATA file");
				warning= true;
		    }
		  } else {
		      System.out.println("ERROR: DATA file DOES NOT exist");
		      warning=true;
		  }
		  if (!warning) {
		    this.xml= xmlfile;
		    //System.out.println(this.xml);
            if ( (this.xml!=null) && (this.xml.endsWith(".xml")) ) {
		      boolean res= this.loadFIS();
			  if (res) {
			      for (int n=0; n<this.NbData; n++) {
		               //System.out.println("n="+n);
                       float[] v= this.makeInference(n);
					   for (int k=0; k<this.NbOutputs; k++) {
			             //System.out.println("k="+k);
					     if (v[k] == -1) {
					       warning= true;
  		                   System.out.println("ERROR: WRONG INFERENCE");
                           break;						   
					     }
					   }
				  }
				  if (!warning) {
				    //printing the FuzzySystem
		            System.out.println("4) Fuzzy System Description");
		            System.out.println(fs.toString());
				  }
			  } else { 
			      System.out.println("ERROR loading FIS from XML");
			      warning= true;
			  }
            } else {
  		        System.out.println("ERROR: WRONG XML FILE");
  		        System.out.println("O2");
			    warning= true;
		    }
		  }
		  if (warning) {
		      System.out.println("Please, check the arguments given to the program");
			  System.out.println("Notice that the program has 3 main arguments (ProblemName InferenceExample DataFile) but brackets are not required");
		      System.out.println("  Options: Tipper [Mamdani1 | Mamdani2 | Mamdani3 | TSK | Tsukamoto1 | Tsukamoto2 | AnYa] test-data-file");
		      System.out.println("  Options: JapaneseDietAssessment Mamdani test-data-file");
		      System.out.println("  Options: Iris [Mamdani1 | Mamdani2 | Mamdani3] test-data-file");
		      System.out.println("  Options: InvertedPendulum [Mamdani1 | Mamdani2 | TSK1 | TSK2] test-data-file");
		      System.out.println("  Options: Robot Mamdani test-data-file");
			  System.out.println("You can also call the program with a specific instance as follows:");
			  System.out.println("  Options: ProblemName InferenceExample V1 D1 V2 D2 ... ");
			  System.out.println("  ProblemName: Tipper, JapaneseDietAssessment, etc.");
			  System.out.println("  InferenceExample; Mamdani, Mamdani1, Mamdani2, TSK, etc.");
			  System.out.println("  Notice that the combination of ProblemName and InferenceExample must be in accordance with the name of an XML file in the folder ./XMLFiles");
			  System.out.println("  You must be also sure of providing the entire list of pairs variable name (Vi, as it is in the XML file) and numerical value (Di) for evaluation");
			  System.out.println("  Example:");
			  System.out.println("> Iris Mamdani2 SepalLength 5.1 SepalWidth 3.5 PetalLength 1.4 PetalWidth 0.2");
			  System.out.println("You can also call the program with a specific instance, for your own XML file, as follows:");
			  System.out.println("  Options: XMLfilePath NbOutputs ON1 ON2 ... V1 D1 V2 D2 ... ");
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
            System.out.println();
			File faux= new File(x);
			if (faux.exists()) {
		      this.fs = JFML.load(faux);
              if (this.fs!=null) {
			    int nbv= this.fs.getKnowledgeBase().getVariables().size();
			    if (this.NbOutputs==-1) {
			    	this.NbOutputs= nbv - this.NbInputs;
			    	this.exOpt= "USERXML";
			    }
				//System.out.println("nbv="+nbv);
				//System.out.println("NbInputs="+NbInputs);
				//System.out.println("NbOutputs="+NbOutputs);
				if (this.NbInputs+this.NbOutputs!=nbv) {
				    System.out.println("ERROR: WRONG NUMBER OF INPUTS AND/OR OUTPUTS");
				    return false;
				} else {
			        return true;
				}
			  } else {
				  System.out.println("ERROR: NULL FIS OBJECT");
			      return false;
              }
			} else {
				System.out.println("ERROR: XML file DOES NOT exist");
			    return false;
            }
  		    //printing the FuzzySystem
		    //System.out.println(fs.toString());
	}

    public float[] makeInference(int d) {
	        float[] value= new float[this.NbOutputs];
			for (int k=0; k<this.NbOutputs; k++) {
			     value[k]=-1; // no inference
			}
		    // set inputs values
			if (this.exOpt != null) {
			  boolean warning= false;
			  KnowledgeBaseVariable[] kbv= null;
			  if (d>=0) {
		          System.out.print("2."+d+") Setting input variables: ");
			  } else {
		          System.out.print("2) Setting input variables: ");
			  }
			  if ( (this.exOpt.equals("USERXML")) || (this.exOpt.equals("Tipper")) || (this.exOpt.equals("JapaneseDietAssessment")) || (this.exOpt.equals("Iris")) || (this.exOpt.equals("InvertedPendulum")) || (this.exOpt.equals("Robot")) ) {
				  kbv = new KnowledgeBaseVariable[this.NbInputs];
				  for (int m=0; m<this.NbInputs; m++) {
		            kbv[m] = this.fs.getVariable(this.inputNames[m]);
					if (kbv[m]!=null) {
		              kbv[m].setValue(data[d][m]);
		              System.out.print(this.inputNames[m]+"="+data[d][m]);
					  if (m<this.NbInputs-1)
					    System.out.print(", ");
					  else
					    System.out.println();
					} else {
					    System.out.println();
			            System.out.println("ERROR: WRONG INPUT NAME");
				        warning= true;
					    break;
					}
				  }
			    System.out.println();
			  } else {
			      System.out.println("UNKNOWN EXAMPLE OPTION");
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
		
                //System.out.println("GET OUT");
			    // get output
				KnowledgeBaseVariable[] output= new KnowledgeBaseVariable[this.NbOutputs];
			    if (this.exOpt.equals("Tipper")) {
		            output[0] =  this.fs.getVariable("tip");
				} else if (this.exOpt.equals("JapaneseDietAssessment")) {
		            output[0] =  this.fs.getVariable("DHL");
				} else if (this.exOpt.equals("Iris")) {
		            output[0] =  this.fs.getVariable("irisClass");
				} else if (this.exOpt.equals("InvertedPendulum")) {
		            output[0] =  this.fs.getVariable("Force");
				} else if (this.exOpt.equals("Robot")) {
		            output[0] =  this.fs.getVariable("la");
		            output[1] =  this.fs.getVariable("av");
				} else if (this.exOpt.equals("USERXML")) {
				    for (int k=0; k<this.NbOutputs; k++) {
		                 output[k] =  this.fs.getVariable(this.outputNames[k]);
					}
				}
				boolean end= false;
				if (output != null) {
				    for (int k=0; k<this.NbOutputs; k++) {
					  if (output[k]!=null) {
	                      value[k] = output[k].getValue();
					  } else {
				          System.out.println("WRONG output NAME");
					      end= true;
						  break;
					  }
					}
				}
				if (!end) {
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
				  System.out.print(" (OUTPUT): ");
				  for (int k=0; k<this.NbOutputs; k++) {
		            System.out.print(output[k].getName()+"="+ value[k] +" ");
				  }
		          System.out.println();
			      System.out.println();
		          //printing the FuzzySystem
		          //System.out.println("4) Fuzzy System Description");
		          //System.out.println(fs.toString());
				}
			  }
			}
			return value;
    }

	public static void main(String[] args) {

	    boolean warning= false;
		if (args!=null) {
		  if (args.length == 2) {
              new EvaluateExample(args[0], args[1]);
		  } else if (args.length == 3) {
              new EvaluateExample(args[0], args[1], args[2]);
		  } else if (args.length >= 4) {
			  new EvaluateExample(args);
              /*if ((args.length % 2) == 0) {
			    //System.out.println("EVEN NUMBER of args");
				new EvaluateExample(args);
 			  } else {
			    //System.out.println("ODD NUMBER of args");
				warning= true;
			  }*/
		  } else {
		      warning= true;
		  }
		} else {
		    warning= true;
		}
		if (warning) {
		      System.out.println("ERROR: WRONG ARGUMENTS");
		      System.out.println("Please, check the arguments given to the program");
			  System.out.println("Notice that the program has 3 main arguments (ProblemName InferenceExample DataFile) but brackets are not required");
		      System.out.println("  Options: Tipper [Mamdani1 | Mamdani2 | Mamdani3 | TSK | Tsukamoto1 | Tsukamoto2 | AnYa] test-data-file");
		      System.out.println("  Options: JapaneseDietAssessment Mamdani test-data-file");
		      System.out.println("  Options: Iris [Mamdani1 | Mamdani2 | Mamdani3] test-data-file");
		      System.out.println("  Options: InvertedPendulum [Mamdani1 | Mamdani2 | TSK1 | TSK2] test-data-file");
		      System.out.println("  Options: Robot Mamdani test-data-file");
			  System.out.println("You can also call the program with a specific instance as follows:");
			  System.out.println("  Options: ProblemName InferenceExample V1 D1 V2 D2 ... ");
			  System.out.println("  ProblemName: Tipper, JapaneseDietAssessment, etc.");
			  System.out.println("  InferenceExample; Mamdani, Mamdani1, Mamdani2, TSK, etc.");
			  System.out.println("  Notice that the combination of ProblemName and InferenceExample must be in accordance with the name of an XML file in the folder ./XMLFiles");
			  System.out.println("  You must be also sure of providing the entire list of pairs variable name (Vi, as it is in the XML file) and numerical value (Di) for evaluation");
			  System.out.println("  Example:");
			  System.out.println("> Iris Mamdani2 SepalLength 5.1 SepalWidth 3.5 PetalLength 1.4 PetalWidth 0.2");
			  System.out.println("You can also call the program with a specific instance, for your own XML file, as follows:");
			  System.out.println("  Options: XMLfilePath NbOutputs ON1 ON2 ... V1 D1 V2 D2 ... ");
        }

	}

}
