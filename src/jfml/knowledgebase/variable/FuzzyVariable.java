package jfml.knowledgebase.variable;

import jfml.defuzzifier.Defuzzifier;

public abstract class FuzzyVariable extends KnowledgeBaseVariable {

	Defuzzifier defuzzifier;
	
	public void setDefuzzifier(Defuzzifier def){
		this.defuzzifier=def;
	}
	
	public void defuzzify(){
		if(this.isOutput() && this.getDefuzzifier()!=null && Float.isNaN(value))
    		value = getDefuzzifier().defuzzify();
	}
	
	protected abstract Defuzzifier getDefuzzifier();
}
