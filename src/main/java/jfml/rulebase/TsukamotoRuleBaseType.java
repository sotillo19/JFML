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
package jfml.rulebase;

/**
 * Java class for implementing Tsukamoto rule base fuzzy systems
 * @author sotillo19
 *
 */
public class TsukamotoRuleBaseType extends RuleBaseType {

	/**
	 * Default constructor
	 */
	public TsukamotoRuleBaseType() {
		
	}

	/**
     * Constructor using name. Rest elements by default
     * @param name name of the Tsukamoto rule base
     */
	public TsukamotoRuleBaseType(String name) {
		super(name, FuzzySystemRuleBase.TYPE_TSUKAMOTO);
	}

	/**
	 * Constructor using the name, the activation method, the and, the or and the type
	 * @param name name of the TSK rule base
	 * @param activation the method used for the implication process according to {@link StandardActivationMethodType }
	 * @param and the and algorithm to be used
	 * @param or the or algorithm to be used
	 * @param type the ruleBaseSystemType
	 */
	public TsukamotoRuleBaseType(String name, String activation, String and, String or, int type) {
		super(name, activation, and, or, type);
	}

}
