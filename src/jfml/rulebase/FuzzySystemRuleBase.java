package jfml.rulebase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import jfml.enumeration.StandardActivationMethodType;

@XmlAccessorType(XmlAccessType.NONE) // This is needed to ignore class
										// attributes as xml tags in XML files
public abstract class FuzzySystemRuleBase {

	public static String defaultActivationMethod = "MIN";
	public static String defaultAndMethod = "MIN";
	public static String defaultOrMethod = "MAX";
	public static String defaultNetworkAddress = "127.0.0.1";

	static final public int TYPE_MAMDANI = 0;
	static final public int TYPE_TSUKAMOTO = 1;
	static final public int TYPE_TSK = 2;
	static final public int TYPE_ANYA = 3;
	static final public int TYPE_OTHER = 4;

	protected int ruleBaseSystemType;

	public abstract void evaluate();
	
	public abstract String getActivationMethod();

	/**
	 * 
	 * @return
	 */
	public int getRuleBaseSystemType() {
		return this.ruleBaseSystemType;
	}

	public String getRuleBaseSystemTypeName() {
		switch (ruleBaseSystemType) {
		case TYPE_MAMDANI:
			return "mamdani";
		case TYPE_TSUKAMOTO:
			return "tsukamoto";
		case TYPE_TSK:
			return "tsk";
		case TYPE_ANYA:
			return "anYa";
		default:
			return "other";
		}
	}

	/**
	 * Sets the fuzzy system type according to static variables
	 * <p>
	 * - TYPE_MAMDANI - Mamdani Rule Base - TYPE_TSUKAMOTO - tsukamoto Rule Base
	 * - TYPE_TSK - tsk Rule Base - TYPE_ANYA - AnYa Rule Base - TYPE_OTHER -
	 * other Rule Base
	 * 
	 * @param type
	 */
	public void setRuleBaseSystemType(int type) {
		this.ruleBaseSystemType = type;
	}

	/**
	 * - MIN for implementing the implication with the minimum as defined from Equation (A.28); 
	 * - PROD for implementing the implication with the product  as defined from Equation (A.29); 
	 * - BDIF for implementing the implication with bounded difference as defined from Equation (A.30); 
	 * - DRP for implementing the implication with the drastic product as defined from Equation (A.31); 
	 * - EPROD for implementing the implication with the Einstein product as defined from Equation (A.32); 
	 * - HPROD for implementing the implication with the Hamacher product as defined from Equation (A.33); 
	 * - NILMIN for implementing the implication with the Nilpotent minimum as defined from Equation (A.34); 
	 * - custom_\S* for a custom implication method.
	 * 
	 * @param x
	 *            degree of antecedent
	 * @param y
	 *            degree of consequent
	 * @return The degree of membership of the consequent part according to the
	 *         activation method {@link getActivationMethod()}
	 */
	protected float activation(float x, float y) {
		String act = getActivationMethod();
		if (act.equals(StandardActivationMethodType.MIN.value()))
			return min(x, y);
		else if (act.equals(StandardActivationMethodType.PROD.value()))
			return prod(x, y);
		else if (act.equals(StandardActivationMethodType.BDIF.value()))
			return bdif(x, y);
		else if (act.equals(StandardActivationMethodType.DRP.value()))
			return drp(x, y);
		else if (act.equals(StandardActivationMethodType.EPROD.value()))
			return eprod(x, y);
		else if (act.equals(StandardActivationMethodType.HPROD.value()))
			return hprod(x, y);
		else if (act.equals(StandardActivationMethodType.NILMIN.value()))
			return nilmin(x, y);
		else if (act.contains("custom"))
			return custom(x, y);
		else
			return min(x, y);
	}

	/**
	 * - custom_\S* for a custom implication method.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private float custom(float x, float y) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * - NILMIN for implementing the implication with the Nilpotent minimum as
	 * defined from Equation (A.34);
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private float nilmin(float x, float y) {
		if (x + y > 1)
			return Math.min(x, y);
		else
			return 0;
	}

	/**
	 * - HPROD for implementing the implication with the Hamacher product as
	 * defined from Equation (A.33);
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private float hprod(float x, float y) {
		return (x + y) / (x + y - (x * y));
	}

	/**
	 * - EPROD for implementing the implication with the Einstein product as
	 * defined from Equation (A.32);
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private float eprod(float x, float y) {
		return (x + y) / (2 - (x + y - (x * y)));
	}

	/**
	 * - DRP for implementing the implication with the drastic product as
	 * defined from Equation (A.31);
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private float drp(float x, float y) {
		if (y == 1)
			return x;
		if (x == 1)
			return y;
		else
			return 0;
	}

	/**
	 * - BDIF for implementing the implication with bounded difference as
	 * defined from Equation (A.30);
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private float bdif(float x, float y) {
		return Math.max(0, (x + y - 1));
	}

	/**
	 * - PROD for implementing the implication with the product as defined from
	 * Equation (A.29);
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private float prod(float x, float y) {
		return x * y;
	}

	/**
	 * - MIN for implementing the implication with the minimum as defined from
	 * Equation (A.28);
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private float min(float x, float y) {
		return Math.min(x, y);
	}

	public abstract void reset();

	@Override
	public abstract String toString();

}
