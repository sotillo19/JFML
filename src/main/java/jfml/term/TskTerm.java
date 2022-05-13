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

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import jfml.knowledgebase.variable.FuzzyVariable;

@XmlAccessorType(XmlAccessType.NONE)
/**
 * Abstract class for representing the Tsk terms
 * @author sotillo19
 *
 */
public abstract class TskTerm extends Term{
	
	public static final int _ORDER_0 = 0;
	public static final int _ORDER_1 = 1;
	
	/**
	 * a value with the term evaluation
	 */
	protected float z=Float.NaN;
	
	/**
	 * Gets the evaluation value
	 * @return the evaluation value
	 */
	public float getEvaluation(){
		return this.z;
	}
	
	
	/**
	 * Sets the evaluation value
	 * @param z the value z
	 */
	public void setEvaluation(float z){
		this.z=z;
	}
	
	/**
	 * Reset the evaluation value
	 */
	public void reset(){
		this.z=Float.NaN;
	}
	
	/**
	 * Evalutates the TSKterm considering the list of variables of the KnowledgeBase
	 * @param kbv list of variables of the KnowledgeBase
	 */
	public abstract void evaluateTskTerm(List<FuzzyVariable> kbv);
	
	/**
	 * Gets the name of the TSK term
	 */
	public abstract String getName();

}
