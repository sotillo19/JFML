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
 * Class for implementing custom membership functions
 * @author sotillo19
 *
 */
public class CustomMembershipFunction extends MembershipFunction {

	String name = "custom";
	
	public CustomMembershipFunction(CustomShapeType c) {
		// TODO Auto-generated constructor stub
	} 
	
	public CustomMembershipFunction() {
		// TODO Auto-generated constructor stub
	}

	public CustomMembershipFunction(Parameter p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	public CustomMembershipFunction(CustomShapeType customShape, float domainLeft, float domainRight) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public float getMembershipDegree(float x) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Float> getXValuesDefuzzifier() {
		// TODO Auto-generated method stub
		return null;
	}

}
