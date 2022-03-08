/**************************************************************
      GNU GENERAL PUBLIC LICENSE - Version 3 

  JFML: A Java Library for the IEEE Standard for Fuzzy Markup Language
  (IEEE Std 1855-2016). Copyright (C) 2017

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

     You should have received a copy of the GNU General Public License
     along with this program.  If not, see <http://www.gnu.org/licenses/>.

  Contact information: <http://www.uco.es/JFML>

  J.M. Soto-Hidalgo & Jose M. Alonso & Jesus Alcala-Fdez
 **************************************************************/
package jfml.rulebase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import jfml.enumeration.StandardActivationMethodType;

/**
 * Abstract class for representing Fuzzy Systems RuleBase.
 * <p> Methods to evaluate rules, reset and get activation methods are included.
 * 
 * @author sotillo19
 *
 */
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
	
	protected float EPSILON = 0.00001f;

	/**
	 * Evaluates the rules
	 */
	public abstract void evaluate();
	
	/**
	 * Defines the method used for the implication process.
	 * @return the ActivationMethod according to {@link StandardActivationMethodType}
	 */
	public abstract String getActivationMethod();

	/**
	 * Gets the representation of the fuzzy system according to the static variables</br>
	 * <p>
	 * - TYPE_MAMDANI - Mamdani Rule Base </br>
	 * - TYPE_TSUKAMOTO - tsukamoto Rule Base </br>
	 * - TYPE_TSK - tsk Rule Base </br>
	 * - TYPE_ANYA - AnYa Rule Base </br>
	 * - TYPE_OTHER - other Rule Base </br>
	 * 
	 * @return the representation of the fuzzy system 
	 */
	public int getRuleBaseSystemType() {
		return this.ruleBaseSystemType;
	}

	/**
	 * Gets the name of the Rule Base fuzzy system </br>
	 * <p>
	 * - TYPE_MAMDANI - mamdani </br>
	 * - TYPE_TSUKAMOTO - tsukamoto </br>
	 * - TYPE_TSK - tsk  </br>
	 * - TYPE_ANYA - anYa  </br>
	 * - TYPE_OTHER - other  </br>
	 * @return the name of the Rule Base fuzzy system
	 */
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
	 * Sets the fuzzy system type according to static variables</br>
	 * <p>
	 * - TYPE_MAMDANI - Mamdani Rule Base </br>
	 * - TYPE_TSUKAMOTO - tsukamoto Rule Base </br>
	 * - TYPE_TSK - tsk Rule Base </br>
	 * - TYPE_ANYA - AnYa Rule Base </br>
	 * - TYPE_OTHER - other Rule Base </br>
	 * 
	 * @param type the type of the rule base
	 */
	public void setRuleBaseSystemType(int type) {
		this.ruleBaseSystemType = type;
	}

	/**
	 * - MIN for implementing the implication with the minimum as defined from Equation (A.28); </br>
	 * - PROD for implementing the implication with the product  as defined from Equation (A.29); </br>
	 * - BDIF for implementing the implication with bounded difference as defined from Equation (A.30); </br>
	 * - DRP for implementing the implication with the drastic product as defined from Equation (A.31); </br>
	 * - EPROD for implementing the implication with the Einstein product as defined from Equation (A.32); </br>
	 * - HPROD for implementing the implication with the Hamacher product as defined from Equation (A.33); </br>
	 * - NILMIN for implementing the implication with the Nilpotent minimum as defined from Equation (A.34); </br>
	 * - custom_\S* for a custom implication method.</br>
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
			return custom_activation(x, y, act);
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
	private float custom_activation(float x, float y, String act) {
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
	
	public abstract String getActivatedRules();

}
