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
 * Java class for representing singleton functions
 * @author sotillo19
 *
 */
public class SingletonMembershipFunction extends MembershipFunction implements Monotonical{

	float a;
	
	String name = "singleton";
	
	/**
	 * Default constructor
	 */
	public SingletonMembershipFunction() {
	
	}

	/**
	 * Constructor with a Parameter
	 * @param p parameter with a unique value
	 */
	public SingletonMembershipFunction(Parameter p) {
		super(p);
		if(p!=null){
			a = p.getParameter(1);
		}
	}

	/**
	 * Constructor with a parameter and left and right domain
	 * @param p parameter with a unique value
	 * @param domainLeft left domain
	 * @param domainRight right domain
	 */
	public SingletonMembershipFunction(Parameter p, float domainLeft, float domainRight) {
		this(p);
		this.domainLeft=domainLeft;
		this.domainRight=domainRight;
	}

	@Override
	public float getMembershipDegree(float x) {
		if (x == a)
			return 1;
		else
			return 0;
	}

	@Override
	public String toString() {
		return name + " [a: "+a+"]";
	}

	@Override
	public float getFi(float y) {
		return a;
	}

	@Override
	public ArrayList<Float> getXValuesDefuzzifier() {
		ArrayList<Float> v = new ArrayList<>();
		
		v.add(a);
		
		return v;
	}
}
