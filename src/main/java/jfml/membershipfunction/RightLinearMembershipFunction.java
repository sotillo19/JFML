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
import jfml.parameter.TwoParamType;

/**
 * Java class for representing RightLinear membership functions
 * @author sotillo19
 *
 */
public class RightLinearMembershipFunction extends MembershipFunction implements Monotonical {

	float a,b;
	
	String name = "rightLinear";
	
	/**
	 * Default constructor
	 */
	public RightLinearMembershipFunction() {
		
	}

	/**
	 * Constructor with Parameter instance with the parameters of the function
	 * @param p parameter -> a and b. Parameters a and b must satisfy a<=b
	 */
	public RightLinearMembershipFunction(Parameter p) {
		super(p);
		if(p!=null){
			a = p.getParameter(1);
			b = p.getParameter(2);
		}
		if(a>b)
			throw new RuntimeException("Parameter ERROR: parameters must satisfy a<=b");
	}

	/**
	 * Constructor with TwoParamType with a and b values
	 * @param p Parameters a and b must satisfy a<=b
	 * @param domainLeft left domain
	 * @param domainRight right domain
	 */
	public RightLinearMembershipFunction(TwoParamType p, float domainLeft, float domainRight) {
		this(p);
		this.domainLeft=domainLeft;
		this.domainRight=domainRight;
	}

	@Override
	public float getMembershipDegree(float x) {
		if (x <= a)
			return 0;
		if (x >= b)
			return 1;

		return (float) (((x - a) / (b - a)));
	}
	
	@Override
	public String toString() {
		return name + " [a: "+a+ ", b: "+b+"]";
	}

	@Override
	public float getFi(float y) {
		return (a + y*(b-a));
	}

	@Override
	public ArrayList<Float> getXValuesDefuzzifier() {
		ArrayList<Float> v = new ArrayList<>();
		
		v.add(a);
		v.add(b);
		
		return v;
	}

}
