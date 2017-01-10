package jfml.knowledgebase.variable;

import jfml.defuzzifier.Defuzzifier;

/**
 * Abstract class for representing the FuzzyVariables
 * @author sotillo19
 *
 */
public abstract class FuzzyVariable extends KnowledgeBaseVariable {

	Defuzzifier defuzzifier;
	
	/**
	 * Gets the defuzzifier
	 * @return the defuzzifier
	 */
	protected abstract Defuzzifier getDefuzzifier();
	
	/**
	 * Sets the defuzzifier
	 * @param def defuzzifier 
	 */
	public void setDefuzzifier(Defuzzifier def){
		this.defuzzifier=def;
	}
	
	/**
	 * Method to defuzzify
	 */
	public void defuzzify(){
		if(this.isOutput() && this.getDefuzzifier()!=null && Float.isNaN(value))
    		value = getDefuzzifier().defuzzify();
	}
	
	
}
