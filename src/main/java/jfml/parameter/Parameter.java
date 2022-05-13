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
package jfml.parameter;

/**
 * Abstract class for the Parameter representation
 * 
 * @author sotillo19
 *
 */
public abstract class Parameter {

	/**
	 * Return the number of parameters
	 * @return
	 */
	public abstract int getParameterLength();
	
	/**
	 * return the i-th parameter.
	 * @param i the i-th parameter. Starts with 1. Parameter 1 --> i=1
	 * @return the value of the i-th parameter. If i is not in the range, returns -1
	 */
	public abstract float getParameter(int i);
}
