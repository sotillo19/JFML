package jfml.knowledgebase.variable;

import javax.xml.bind.annotation.XmlAccessorType;

import jfml.defuzzifier.Defuzzifier;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;

@XmlAccessorType(XmlAccessType.NONE) //This is needed to ignore class attributes as xml tags in XML files
public abstract class KnowledgeBaseVariable {

	float value=Float.NaN;
	
	public abstract float getValue();
	
	public abstract void setValue(float x);
	
	public abstract List<?> getTerms();
	
	public abstract boolean isOutput();
	
	public boolean isInput(){
		return !isOutput();
	}

	public abstract void reset();
	
	@Override
	public abstract String toString();

	public abstract String getName(); 
}
