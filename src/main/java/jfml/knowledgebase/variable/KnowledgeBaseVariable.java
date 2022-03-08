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
package jfml.knowledgebase.variable;

import javax.xml.bind.annotation.XmlAccessorType;

import jfml.term.Term;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;

/**
 * Abstract class that cannot be instantiated directly for representing the KnowledgeVariable concept
 * @author sotillo19
 *
 */
@XmlAccessorType(XmlAccessType.NONE) //This is needed to ignore class attributes as xml tags in XML files
public abstract class KnowledgeBaseVariable {

	float value=Float.NaN;
	
	/**
	 * Gets the value of the variable
	 * @return the value of the variable
	 */
	public abstract float getValue();
	
	/**
	 * Sets the value of the variable
	 * @param x the value of the variable
	 */
	public abstract void setValue(float x);
	
	/**
	 * Gets a list of terms
	 * @return a list of terms
	 */
	public abstract List<?> getTerms();
	
	/**
	 * Gets a Term instance by name or null otherwise
	 * @param name term name
	 * @return a Term instance by name or null otherwise
	 */
	public abstract Term getTerm(String name);
	
	/**
	 * Tests if the variable is output type
	 * @return true if the variable is output type; false otherwise
	 */
	public abstract boolean isOutput();
	
	/**
	 * Tests if the variable is input type
	 * @return true if the variable is input type; false otherwise
	 */
	public boolean isInput(){
		return !isOutput();
	}

	/**
	 * Resets the value of the variable
	 */
	public abstract void reset();
	
	/**
	 * Returns a String object representing this variable. The name a the list of terms
	 * @return a String object representing this variable.
	 */
	public abstract String toString();

	/**
	 * Returns the name of the variable
	 * @return the name of the variable
	 */
	public abstract String getName(); 
	
	/**
	 * Returns true if the variable contains a Term with the name as param
	 * @param name the name of the Term
	 * @return rue if the variable contains a Term with the name; false otherwise
	 */
	public abstract boolean hasTerm(String name);
	
	/**
	 * Returns a new instance of the variable
	 * @return a new instance of the variable
	 */
	public abstract KnowledgeBaseVariable copy();
}
