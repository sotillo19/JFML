package jfml.knowledgebase.variable;

import javax.xml.bind.annotation.XmlAccessorType;

import jfml.term.Term;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;

@XmlAccessorType(XmlAccessType.NONE) //This is needed to ignore class attributes as xml tags in XML files
public abstract class KnowledgeBaseVariable {

	float value=Float.NaN;
	
	public abstract float getValue();
	
	public abstract void setValue(float x);
	
	public abstract List<?> getTerms();
	
	public abstract Term getTerm(String name);
	
	public abstract boolean isOutput();
	
	public boolean isInput(){
		return !isOutput();
	}

	public abstract void reset();
	
	public abstract String toString();

	public abstract String getName(); 
	
	public abstract boolean hasTerm(String name);
	
	public abstract KnowledgeBaseVariable copy();
}
