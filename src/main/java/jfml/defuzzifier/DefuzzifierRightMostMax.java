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
package jfml.defuzzifier;

import java.util.List;
import java.util.Map;

import jfml.term.FuzzyTermType;

public class DefuzzifierRightMostMax extends DefuzzifierContinuous {

	public DefuzzifierRightMostMax(float domainleft, float domainright, List<FuzzyTermType> terms) {
		super(domainleft,domainright,terms);
	}

	@Override
	public float defuzzify() {
		float max = 0, maxX = 0;
		float x,y;
		for (Map.Entry<Float, Float> entry : discreteValues.entrySet()){
			y = entry.getValue();
			x = entry.getKey();
			if(y >= max){
				max = y;
				maxX = x;
			}
		}

		// No max? => this variable has no active antecedent
		if( max <= 0 ) return Float.NaN;

		return maxX;

	}
}
