package jfml.term;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import jfml.knowledgebase.variable.FuzzyVariable;

@XmlAccessorType(XmlAccessType.NONE)
/**
 * Abstract class for representing the Tsk terms
 * @author sotillo19
 *
 */
public abstract class TskTerm extends Term{
	
	public static final int _ORDER_0 = 0;
	public static final int _ORDER_1 = 1;
	
	/**
	 * a value with the term evaluation
	 */
	protected float z=Float.NaN;
	
	/**
	 * Gets the evaluation value
	 * @return the evaluation value
	 */
	public float getEvaluation(){
		return this.z;
	}
	
	
	/**
	 * Sets the evaluation value
	 * @param z the value z
	 */
	public void setEvaluation(float z){
		this.z=z;
	}
	
	/**
	 * Reset the evaluation value
	 */
	public void reset(){
		this.z=Float.NaN;
	}
	
	/**
	 * Evalutates the TSKterm considering the list of variables of the KnowledgeBase
	 * @param kbv list of variables of the KnowledgeBase
	 */
	public abstract void evaluateTskTerm(List<FuzzyVariable> kbv);
	
	/**
	 * Gets the name of the TSK term
	 */
	public abstract String getName();

}
