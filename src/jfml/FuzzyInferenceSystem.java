package jfml;

import jfml.jaxb.FuzzySystemType;

/**
 * <p>Java class for FuzzyInferenceSystem.
 * @author sotillo19
 */
public class FuzzyInferenceSystem extends FuzzySystemType {

	/**
	 * Default constructor
	 */
	public FuzzyInferenceSystem() {
		super();
	}

	/**
	 * Constructor using the name of the FuzzySystem
	 * 
	 * @param name the name of the fuzzy system
	 */
	public FuzzyInferenceSystem(String name) {
		super(name);
	}
	
	/**
	 * Constructor using another FuzzySystemType instance
	 * @param fst {@link FuzzySystemType }
	 */
	public FuzzyInferenceSystem(FuzzySystemType fst){
		super(fst.getName(),fst.getKnowledgeBase(),fst.getRuleBase(),fst.getNetworkAddress());
	}
	
	
}
