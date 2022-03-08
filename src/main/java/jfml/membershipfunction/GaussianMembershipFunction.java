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
 * Class for representing Gaussian Membership functions
 * @author sotillo19
 *
 */
public class GaussianMembershipFunction extends MembershipFunction {

	float sigma, c;
	String name = "gaussian";
	
	/**
	 * Default constructor
	 */
	public GaussianMembershipFunction() {
	
	}

	/**
	 * Constructor with Parameter instance with parameters of the function
	 * @param p param1 = c; param2 = sigma
	 */
	public GaussianMembershipFunction(Parameter p) {
		super(p);
		if(p!=null){
			sigma = p.getParameter(2);
			c = p.getParameter(1);
		}
	}

	/**
	 * Constructor with Parameter instance with parameters of the function and the left and right domain
	 * @param p param1 = c; param2 = sigma
	 * @param domainLeft left domain
	 * @param domainRight right domain
	 */
	public GaussianMembershipFunction(Parameter p, float domainLeft, float domainRight) {
		this(p);
		this.domainLeft=domainLeft;
		this.domainRight=domainRight;
	}

	@Override
	public float getMembershipDegree(float x) {
		return (float) Math.exp(-Math.pow(x-c, 2) / (2 * Math.pow(sigma, 2)));
	}

	@Override
	public String toString() {
		return name + " [c: " + c + ", sigma: "+sigma+"]";
	}

	@Override
	public ArrayList<Float> getXValuesDefuzzifier() {
		ArrayList<Float> v = new ArrayList<>();
		
		v.add(c);
		
		return v;
	}

}
