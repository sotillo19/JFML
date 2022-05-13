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

/**
 * Class for CircularDefinition membership
 * @author sotillo19
 *
 */
public class CircularMembershipFunction extends MembershipFunction {

	String name ="circular";
	CircularDefinitionType p;
	
	/**
	 * Default constructor
	 */
	public CircularMembershipFunction() {
		
	}

	/**
	 * Constructor with a CircularDefinitionType as param
	 * @param p a CircularDefinitionType
	 */
	public CircularMembershipFunction(CircularDefinitionType p) {
		super();
		this.p = p;
	}

	/**
	 * Constructor with a a CircularDefinitionType and the left and right domain
	 * @param p CircularDefinitionType
	 * @param domainLeft left domain
	 * @param domainRight right domain
	 */
	public CircularMembershipFunction(CircularDefinitionType p, float domainLeft, float domainRight) {
		super();
		this.p = p;
		this.domainLeft=domainLeft;
		this.domainRight=domainRight;
	}

	@Override
	public float getMembershipDegree(float x) {
		return p.getMembershipDegree(x);
	}

	@Override
	public String toString() {
		return p.toString();
	}

	@Override
	public ArrayList<Float> getXValuesDefuzzifier() {
		// TODO Auto-generated method stub
		return null;
	}

}
