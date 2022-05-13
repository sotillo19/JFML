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
package jfml.aggregated;

/**
 * Abstract class for representing the AggregatedType
 * @author sotillo19
 *
 */
public abstract class AggregatedType {

	/**
	 * Default constructor
	 */
	public AggregatedType() {
		
	}

	/**
	 * Get the method for operator AND / OR
	 * @return the method for operator AND / OR
	 */
	public abstract String getOperator();
	
	/**
	 * Gets the i-th element of the Aggregation
	 * Both the element AND and the element OR contain two elements, clause or an element 
	 * AND followed by an element clause or an element OR followed by an element clause. 
	 * The element clause represents the clause to be used in the fuzzy expression of the 
	 * aggregated fuzzy term under definition.
	 * @param i 0=first element, 1=last element
	 * @return the content of the i-th part i of the Aggregation
	 */
	public abstract Object getContent(int i);

	/**
	 * Gets the aggregation depending on the operator AND / OR
	 * @param degree1 the degree1
	 * @param degree2 the degree2
	 * @return the aggregation result
	 */
	public abstract float operate(float degree1, float degree2);
}
