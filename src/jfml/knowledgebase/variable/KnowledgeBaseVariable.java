package jfml.knowledgebase.variable;

import javax.xml.bind.annotation.XmlAccessorType;

import jfml.defuzzifier.Defuzzifier;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;

@XmlAccessorType(XmlAccessType.NONE) //This is needed to ignore class attributes as xml tags in XML files
public abstract class KnowledgeBaseVariable {

	float x=-1;
	
	Defuzzifier defuzzifier;
	
	public void setDefuzzifier(Defuzzifier def){
		this.defuzzifier=def;
	}
	
	protected abstract Defuzzifier getDefuzzifier();
	
	public abstract float getValue();
	
	public abstract void setValue(float x);
	
	public abstract float getDefuzzifierValue();
	
	public abstract List<?> getTerms();
	
	public abstract boolean isOutput();

	public void defuzzify(){
		if(this.isOutput() && this.getDefuzzifier()!=null && x==-1)
    		x = getDefuzzifier().defuzzify();
	}

	public void reset() {
		this.x = -1;
	}
	
	@Override
	public abstract String toString();

	public abstract String getName(); 
}
