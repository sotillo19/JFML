package jfml;

import jfml.jaxb.FuzzySystemType;

public class FuzzySystem extends FuzzySystemType {

	/**
	 * Default constructor
	 */
	public FuzzySystem() {
		super();
	}

	/**
	 * Constructor using the name
	 * 
	 * @param name
	 */
	public FuzzySystem(String name) {
		super(name);
	}
	
	/**
	 * Constructor using a FuzzySystemType instance
	 * @param fst
	 */
	public FuzzySystem(FuzzySystemType fst){
		super(fst.getName(),fst.getKnowledgeBase(),fst.getRuleBase(),fst.getNetworkAddress());
	}
	
	
}
