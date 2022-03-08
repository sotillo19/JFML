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
package jfml.membershipfunction;

import java.util.ArrayList;

import jfml.parameter.Parameter;

/**
 * The abstract base class for all membership functions
 * @author sotillo19
 *
 */
public abstract class MembershipFunction {

	//boolean discrete;
	/** Function's parameters */
	Parameter parameter;
	/** Domain right (range max) */
	float domainRight;
	/** Domain left (range min) */
	float domainLeft;

	/** Default Constructor */
	protected MembershipFunction() {
		domainLeft = domainRight = Float.NaN;
	}
	
	/**
	 * Constructor with parameters
	 * @param p parameter 
	 */
	protected MembershipFunction(Parameter p){
		super();
		this.parameter=p;
	}
	
	/**
	 * Constructor with parameter and right and left domain
	 * @param p parameter
	 * @param domainLeft left domain
	 * @param domainRight right domain
	 */
	protected MembershipFunction(Parameter p, float domainLeft, float domainRight){
		super();
		this.parameter=p;
		this.domainLeft = domainLeft;
		this.domainRight = domainRight;
	}

	/** 
	 * Get membership degree value.
	 * @param x : Variable's 'x' value
	 * @return 
	 * Note: Output must be in range [0,1] 
	 */
	public abstract float getMembershipDegree(float x);


	/**
	 * Gets the name of the function
	 * @return the name of the function
	 */
	public String getName() {
		String str = this.getClass().getName();
		String mfStr = "MembershipFunction";
		int ind = str.lastIndexOf('.');
		if (ind >= 0) {
			str = str.substring(ind + 1);
			if (str.startsWith(mfStr)) str = str.substring(mfStr.length());
		}
		return str;
	}

	/**
	 * Gets the Parameter associated to this function
	 * @return the Parameter associated to this function
	 */
	public Parameter getParameter() {
		return parameter;
	}

	/**
	 * Gets the right domain
	 * @return the right domain
	 */
	public double getDomainRight() {
		return domainRight;
	}

	/**
	 * Gets the left domain
	 * @return the left domain
	 */
	public double getDomainLeft() {
		return domainLeft;
	}

	/**
	 * Sets the parameter
	 * @param p the parameter
	 */
	public void setParameter(Parameter p) {
		parameter = p;
	}

	/**
	 * Sets the right domain value
	 * @param domainRight the right domain value
	 */
	public void setDomainRight(float domainRight) {
		this.domainRight = domainRight;
	}

	/**
	 * Sets the left domain value
	 * @param domainLeft the left domain value
	 */
	public void setDomainLeft(float domainLeft) {
		this.domainLeft = domainLeft;
	}

	@Override
	public abstract String toString();

	/**
	 * This function returns an array with values [x1, x2, x3, ...] which represents points in the x domain of the function needed by defuzzifer
	 * @return an ArrayList with floats
	 */
	public abstract ArrayList<Float> getXValuesDefuzzifier();
}
