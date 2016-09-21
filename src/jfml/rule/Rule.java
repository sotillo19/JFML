package jfml.rule;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import jfml.enumeration.StandardAndMethodType;
import jfml.enumeration.StandardOrMethodType;
import jfml.enumeration.StandardTnormType;

@XmlAccessorType(XmlAccessType.NONE) //This is needed to ignore class attributes as xml tags in XML files
public abstract class Rule {
	public static String defaultAndMethod = "MIN";
	public static String defaultOrMethod = "MAX";
	
	private float evaluation=0;
	
	public void reset(){
		this.setEvaluation(0);
	}
	
	/**
	 * 
	 * @return The and algorithm by default: MIN
	 */
	public float and(float[] degrees){
		return and(defaultAndMethod,degrees);
	}
	
	
	/**
	 * 
	 * @param andMethod
	 * 	- MIN for implementing the operator and with the minimum as defined from Equation (A.14);
		- PROD for implementing the operator and with the product as defined from Equation (A.15);
		- BDIF for implementing the operator and with bounded difference as defined from Equation (A.16);
		- DRP for implementing the operator and with the drastic product as defined from Equation (A.17);
		- EPROD for implementing the operator and with the Einstein product as defined from Equation (A.18);
		- HPROD for implementing the operator and with the Hamacher product as defined from Equation (A.19);
		- NILMIN for implementing the operator and with the Nilpotent minimum as defined from Equation (A.20); 
		- custom_\S* for a custom method for operator and.
	 * @return
	 */
	public float and(String andMethod, float[] degrees){
		if(andMethod.equals(StandardAndMethodType.MIN.value()))
			return min(degrees);
		else if(andMethod.equals(StandardAndMethodType.PROD.value()))
			return prod(degrees);
		else if(andMethod.equals(StandardAndMethodType.BDIF.value()))
			return bdif(degrees);
		else if(andMethod.equals(StandardAndMethodType.DRP.value()))
			return drp(degrees);
		else if(andMethod.equals(StandardAndMethodType.EPROD.value()))
			return eprod(degrees);
		else if(andMethod.equals(StandardAndMethodType.HPROD.value()))
			return hprod(degrees);
		else if(andMethod.equals(StandardAndMethodType.NILMIN.value()))
			return nilmin(degrees);
		else if(andMethod.contains("custom"))
			return custom_and(degrees, andMethod);
		else
			return and(degrees);
	}
	
	
	/**
	 * custom_\S* for a custom method for operator and.
	 * @param degrees
	 * @param andMethod 
	 * @return
	 */
	private float custom_and(float[] degrees, String andMethod) {
		// TODO Auto-generated method stub
		return 0;
	}


	/**
	 * NILMIN for implementing the operator and with the Nilpotent minimum as defined from Equation (A.20); 
	 * @param degrees
	 * @return
	 */
	private float nilmin(float[] degrees) {
		float res=degrees[0];
		
		for(int i=1;i<degrees.length;i++)
			res=nilmin(res,degrees[i]);
		
		return res;
	}


	private float nilmin(float a, float b) {
		if(a+b>1)
			return Math.min(a, b);
		else
			return 0;
	}


	/**
	 * HPROD for implementing the operator and with the Hamacher product as defined from Equation (A.19);
	 * @param degrees
	 * @return
	 */
	private float hprod(float[] degrees) {
		float res=degrees[0];
		
		for(int i=1;i<degrees.length;i++)
			res=hprod(res,degrees[i]);
		
		return res;
	}


	private float hprod(float a, float b) {
		return (a+b)/(a+b-(a*b));
	}


	/**
	 * EPROD for implementing the operator and with the Einstein product as defined from Equation (A.18);
	 * @param degrees
	 * @return
	 */
	private float eprod(float[] degrees) {
		float res=degrees[0];
		
		for(int i=1;i<degrees.length;i++)
			res=eprod(res,degrees[i]);
		
		return res;
	}


	private float eprod(float a, float b) {
		return (a*b)/(2-(a+b-(a*b)));
	}


	/**
	 *  DRP for implementing the operator and with the drastic product as defined from Equation (A.17);
	 * @param degrees
	 * @return
	 */
	private float drp(float[] degrees) {
		float res=degrees[0];
		
		for(int i=1;i<degrees.length;i++)
			res=drp(res,degrees[i]);
		
		return res;
	}


	private float drp(float a, float b) {
		if(a==1)
			return b;
		if(b==1)
			return a;
		else 
			return 0;
	}


	/**
	 * BDIF for implementing the operator and with bounded difference as defined from Equation (A.16);
	 * @param degrees
	 * @return
	 */
	private float bdif(float[] degrees) {
		float res=degrees[0];
		
		for(int i=1;i<degrees.length;i++)
			res=bdif(res,degrees[i]);
		
		return res;
	}
	
	private float bdif(float a, float b){
		return Math.max(0, a+b-1);
	}


	/**
	 * PROD for implementing the operator and with the product as defined from Equation (A.15);
	 * @param degrees
	 * @return
	 */
	private float prod(float[] degrees) {
		float res=0;
		
		for(int i=0;i<degrees.length;i++)
			res*=degrees[i];
		
		return res;
	}


	/**
	 * 	  MIN for implementing the operator and with the minimum as defined from Equation (A.14);
	 * @param degrees
	 * @return
	 */
	private float min(float[] degrees) {
		float res=Float.MAX_VALUE;
		
		for(int i=0;i<degrees.length;i++)
			if(res>degrees[i])
				res=degrees[i];
		
		return res;
	}


	/**
	 * 
	 * @return The or algorithm by default: MAX
	 */
	public float or(float[] degrees){
		return and(defaultOrMethod,degrees);
	}
	
	
	/**
	 * 
	 * @param orMethod
	 * 	- MAX for implementing the connector or with the maximum as defined from Equation (A.21);
		- PROBOR for implementing the connector or with the probabilistic sum as defined from Equation (A.22);
		- BSUM for implementing the operator or with the bounded sum as defined from Equation (A.23);
		- DRS for implementing the operator or with the drastic sum as defined from Equation (A.24);
		- ESUM for implementing the operator or with the Einstein sum as defined from Equation (A.25);
		- HSUM for implementing the operator or with the Hamacher sum as defined from Equation (A.26);
		- NILMAX for implementing the operator or with the Nilpotent maximum as defined from Equation (A.27);
		- custom_\S* for a custom method for implementing the connector or.
	 * @return
	 */
	public float or(String orMethod, float[] degrees){
		if(orMethod.equals(StandardOrMethodType.MAX.value()))
			return max(degrees);
		else if(orMethod.equals(StandardOrMethodType.PROBOR.value()))
			return probor(degrees);
		else if(orMethod.equals(StandardOrMethodType.BSUM.value()))
			return bsum(degrees);
		else if(orMethod.equals(StandardOrMethodType.DRS.value()))
			return drs(degrees);
		else if(orMethod.equals(StandardOrMethodType.ESUM.value()))
			return esum(degrees);
		else if(orMethod.equals(StandardOrMethodType.HSUM.value()))
			return hsum(degrees);
		else if(orMethod.equals(StandardOrMethodType.NILMAX.value()))
			return nilmax(degrees);
		else if(orMethod.contains("custom"))
			return custom_or(degrees,orMethod);
		else 
			return or(degrees);
	
	}
	
	private float custom_or(float[] degrees, String orMethod) {
		// TODO Auto-generated method stub
		return 0;
	}


	private float nilmax(float[] degrees) {
		float res=degrees[0];
		
		for(int i=1;i<degrees.length;i++)
			res=nilmax(res,degrees[i]);
		
		return res;
	}


	private float nilmax(float a, float b) {
		if(a+b<1)
			return Math.max(a, b);
		else
			return 1;
	}


	private float hsum(float[] degrees) {
		float res=degrees[0];
		
		for(int i=1;i<degrees.length;i++)
			res=hsum(res,degrees[i]);
		
		return res;
	}


	private float hsum(float a, float b) {
		return (a+b-(2*a*b))/(1-(a*b));
	}


	private float esum(float[] degrees) {
		float res=degrees[0];
		
		for(int i=1;i<degrees.length;i++)
			res=esum(res,degrees[i]);
		
		return res;
	}


	private float esum(float a, float b) {
		return (a+b)/(1+a*b);
	}


	private float drs(float[] degrees) {
		float res=degrees[0];
		
		for(int i=1;i<degrees.length;i++)
			res=drs(res,degrees[i]);
		
		return res;
	}


	private float drs(float a, float b) {
		if(a==0)
			return b;
		if(b==0)
			return a;
		else
			return 1;
	}


	private float bsum(float[] degrees) {
		float res=degrees[0];
		
		for(int i=1;i<degrees.length;i++)
			res=bsum(res,degrees[i]);
		
		return res;
	}


	private float bsum(float a, float b) {
		return Math.min(1, a+b);
	}


	private float probor(float[] degrees) {
		float res=degrees[0];
		
		for(int i=1;i<degrees.length;i++)
			res=probor(res,degrees[i]);
		
		return res;
	}


	private float probor(float a, float b) {
		return a+b-(a*b);
	}


	private float max(float[] degrees) {
		float res=-Float.MAX_VALUE;
		
		for(int i=0;i<degrees.length;i++)
			if(res<=degrees[i])
				res=degrees[i];
		
		return res;
	}

	/**
	 * Performs the combination of the multiple clauses contained in the antecedent part of a rule by means two connectors, and or or
	 * @param degrees
	 * @return aggregation value
	 */
	public abstract float aggregation(float[] degrees);
	
	@Override
	public abstract String toString();

	public float getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(float evaluation) {
		this.evaluation = evaluation;
	}
}
