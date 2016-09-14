package jfml;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import jfml.jaxb.FuzzySystemType;
import jfml.knowledgebase.variable.FuzzyVariableType;
import jfml.knowledgebase.variable.KnowledgeBaseVariable;
import jfml.knowledgebase.variable.TsukamotoVariableType;
import jfml.term.FuzzyTerm;

public class JFML {

	/**
	 * Static method to create a fuzzySystem from a xml file according to the IEEE1855 standard 
	 * @param xml allowed object is
     *     {@link File }
	 * @return a fuzzy System instance
	 */
	public static FuzzyInferenceSystem load(File xml) {
		return new FuzzyInferenceSystem(readFSTfromXML(xml, false).getValue());
	}

	@SuppressWarnings({ "unchecked" })
	private static JAXBElement<FuzzySystemType> readFSTfromXML(File input, boolean print) {
		JAXBElement<FuzzySystemType> fst = null;
		try {
			JAXBContext jc = JAXBContext.newInstance(FuzzySystemType.class);
			Unmarshaller u = jc.createUnmarshaller();

			fst = (JAXBElement<FuzzySystemType>) u.unmarshal(input);

			/**
			 * initialize membership functions -- reading from XML file they are not
			 * initialized
			 */
			initializeMembershipFunctions(fst.getValue());

			if (print) {
				Marshaller m = jc.createMarshaller();

				// output pretty printed
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				m.marshal(fst, System.out);
			}

		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return fst;
	}

	@SuppressWarnings("rawtypes")
	private static void initializeMembershipFunctions(FuzzySystemType fst) {
		for (Object v : fst.getKnowledgeBase().getVariables()) {
			KnowledgeBaseVariable var = null;
			if (((JAXBElement) v).getValue() instanceof KnowledgeBaseVariable)
				var = (KnowledgeBaseVariable) ((JAXBElement) v).getValue();
			if (var != null) {
				for (Object t : var.getTerms()) {
					float d_left = 0, d_right = 0;
					if (t instanceof FuzzyTerm) {
						if (var instanceof FuzzyVariableType) {
							d_left = ((FuzzyVariableType) var).getDomainleft();
							d_right = ((FuzzyVariableType) var).getDomainright();
						} else if (var instanceof TsukamotoVariableType) {
							d_left = ((TsukamotoVariableType) var).getDomainleft();
							d_right = ((TsukamotoVariableType) var).getDomainright();
						}

						((FuzzyTerm) t).initializeMembershipFunction(d_left, d_right);
					}
					// TODO initialize other terms
				}
			}
		}

	}

	private static void writeFSTtoXML(JAXBElement<?> element, File output) {
		try {
			@SuppressWarnings("rawtypes")
			Class type = element.getDeclaredType();
			JAXBContext jc = JAXBContext.newInstance(type);
			Marshaller m = jc.createMarshaller();
			
			// output pretty printed
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			m.marshal(element, output);
			// m.marshal(element, System.out);
			
			//removePrefixNS(output, "ns2");

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Static method to write a FuzzySystem in a xml file
	 * @param fst allowed object is
     *     {@link FuzzySystemType }
	 * 
	 * @param output
	 * allowed object is
     *     {@link File }
	 * 
	 */
	public static void writeFSTtoXML(FuzzySystemType fst, File output) {
		writeFSTtoXML(fst.getJAXBElement(), output);
	}

	@SuppressWarnings("rawtypes")
	public void writeFSTtoXML(JAXBElement<?> element, Class type, File output) {
		try {
			JAXBContext jc = JAXBContext.newInstance(type);
			Marshaller m = jc.createMarshaller();
			

			// output pretty printed
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			m.marshal(element, output);
			// m.marshal(element, System.out);
			
			//removePrefixNS(output, "ns2");

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	private static void removePrefixNS(File file, String prefix){
		BufferedReader bf = null;
		String s;
		String b="";

		try {
			bf = new BufferedReader(new FileReader(file));
			do{
				s = bf.readLine();
				if(s!=null)
					b += s.replaceAll(prefix+":", "").replaceAll(":"+prefix, "") + "\r\n";
			}
			while (s != null);
			
			bf.close();
			
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			out.write(b);
			out.close();
		} 
		catch (IOException io) {
			System.err.println(io.getMessage());
		}
	}
}
