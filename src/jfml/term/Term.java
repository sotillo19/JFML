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
package jfml.term;

/**
 * Abstract class for representing the terms.
 * 
 * MembershipValue is implemented
 * 
 * @author sotillo19
 *
 */
public abstract class Term {
	
	/**
	 * Gets the name of the term
	 * @return the name of the term
	 */
	public abstract String getName();
	
	/**
	 * Gets the membership degree by calculating the membership value of the parameter x to this term
	 * @param x the value x
	 * @return the membership value of the parameter x to this term
	 */
	public abstract float getMembershipValue(float x);
	
	/**
	 * Creates a copy of the term
	 * @return a copy of the term
	 */
	public abstract Term copy();
}
