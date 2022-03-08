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
package jfml;

import jfml.jaxb.FuzzySystemType;

/**
 * <p>Java class for FuzzyInferenceSystem.
 * @author sotillo19
 */
public class FuzzyInferenceSystem extends FuzzySystemType {

	/**
	 * Default constructor
	 */
	public FuzzyInferenceSystem() {
		super();
	}

	/**
	 * Constructor using the name of the FuzzySystem
	 * 
	 * @param name the name of the fuzzy system
	 */
	public FuzzyInferenceSystem(String name) {
		super(name);
	}
	
	/**
	 * Constructor using another FuzzySystemType instance
	 * @param fst {@link FuzzySystemType }
	 */
	public FuzzyInferenceSystem(FuzzySystemType fst){
		super(fst.getName(),fst.getKnowledgeBase(),fst.getRuleBase(),fst.getNetworkAddress());
		this.reset(this.getKnowledgeBase(), this.getRuleBase());
	}
	
	
}
