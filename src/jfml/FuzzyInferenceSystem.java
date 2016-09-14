package jfml;

import jfml.jaxb.FuzzySystemType;

public class FuzzyInferenceSystem extends FuzzySystemType {

	/**
	 * Default constructor
	 */
	public FuzzyInferenceSystem() {
		super();
	}

	/**
	 * Constructor using the name
	 * 
	 * @param name
	 */
	public FuzzyInferenceSystem(String name) {
		super(name);
	}
	
	/**
	 * Constructor using a FuzzySystemType instance
	 * @param fst
	 */
	public FuzzyInferenceSystem(FuzzySystemType fst){
		super(fst.getName(),fst.getKnowledgeBase(),fst.getRuleBase(),fst.getNetworkAddress());
	}
	
	
}
