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

public class PiShapedMembershipFunction extends MembershipFunction {

	float a,b,c,d;
	
	String name="pi-Shaped";
	
	/**
	 * Default constructor
	 */
	public PiShapedMembershipFunction() {
		
	}
	
	/**
	 * Constructor with a Parameter instance with the parameters
	 * @param p a Parameter
	 */
	public PiShapedMembershipFunction(Parameter p) {
		super(p);
		if(p!=null){
			a = p.getParameter(1);
			b = p.getParameter(2);
			//TODO revise this
			c = b;
			d = b + Math.abs(a - b);
		}
	}

	/**
	 * Constructor with a Parameter instance with the parameters and the right and left domain
	 * @param p a Parameter
	 * @param domainLeft left domain
	 * @param domainRight right domain
	 */
	public PiShapedMembershipFunction(Parameter p, float domainLeft, float domainRight) {
		this(p);
		this.domainLeft=domainLeft;
		this.domainRight=domainRight;
	}

	@Override
	public float getMembershipDegree(float x) {
		if (x <= a) {
			return 0.0f;
		} else if (a <= x && x <= ((a + b) / 2)) {
			return (float) (2 * (Math.pow((x - a) / (b - a), 2)));
		} else if (((a + b) / 2) <= x && x <= b) {
			return (float) (1 - (2 * Math.pow((x - b) / (b - a), 2)));
		} else if (b <= x && x <= c) {
			return 1.0f;
		} else if (c <= x && x <= (c + d) / 2) {
			return (float) (1 - (2 * (Math.pow((x - c) / (d - c), 2))));
		} else if ((c + d) / 2 <= x && x <= d) {
			return (float) (2 * (Math.pow((x - d) / (d - c), 2)));
		} else if (x >= d) {
			return 0.0f;
		}
		return 0.0f;
	}
	
	@Override
	public String toString() {
		return name + " [a: "+a+ ", b: "+b+"]";
	}

	@Override
	public ArrayList<Float> getXValuesDefuzzifier() {
		return new ArrayList<>();
	}

}
