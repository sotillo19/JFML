package jfml.term;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import jfml.knowledgebase.variable.FuzzyVariable;
import jfml.knowledgebase.variable.KnowledgeBaseVariable;

@XmlAccessorType(XmlAccessType.NONE)
public abstract class TskTerm {
	
	public static final int _ORDER_0 = 0;
	public static final int _ORDER_1 = 1;
	
	/**
	 * a value with the term evaluation
	 */
	protected float z=Float.NaN;
	
	/**
	 * 
	 * @return the evaluation value
	 */
	public float getEvaluation(){
		return this.z;
	}
	
	
	/**
	 * Sets the evaluation value
	 * @param v
	 */
	public void setEvaluation(float z){
		this.z=z;
	}
	
	/**
	 * reset the evaluation value
	 */
	public void reset(){
		this.z=Float.NaN;
	}
	
	
	public abstract void evaluateTskTerm(List<FuzzyVariable> kbv);

}
