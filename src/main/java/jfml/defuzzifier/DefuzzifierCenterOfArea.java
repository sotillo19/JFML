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

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import jfml.term.FuzzyTermType;

public class DefuzzifierCenterOfArea extends DefuzzifierContinuous {

	
	public DefuzzifierCenterOfArea(float domainleft, float domainright, List<FuzzyTermType> terms) {
		super(domainleft,domainright,terms);
	}
	
	@Override
	public float defuzzify() {
		float sumLow = 0, sumHigh = 0;
		Iterator<Entry<Float, Float>> itLow = discreteValues.entrySet().iterator();
		Iterator<Entry<Float, Float>> itHi = getReverseOrderIterator();
		float low = 0, hi = Float.MAX_VALUE;
		
		// Calculate lower and upper sums (approximating integrals). 
		// Center of area is where integrals (sums) are equal
		while(itLow.hasNext() && itHi.hasNext() && low < hi){
			if( sumLow <= sumHigh ) {
				Map.Entry<Float, Float> entry = (Entry<Float, Float>) itLow.next();
				low = entry.getKey();
				sumLow += entry.getValue();
			} else {
				Map.Entry<Float, Float> entry = (Entry<Float, Float>) itHi.next();
				hi = entry.getKey();
				sumHigh += entry.getValue();
			}
		}

		// No max? => this variable has no active antecedent
		if( max <= 0 ) return Float.NaN;

		//(either 'low' or 'high' should be the same)
		return low;

	}

	private Iterator<Entry<Float, Float>> getReverseOrderIterator() {
		TreeMap<Float,Float> reverse = new TreeMap<Float,Float>(Collections.reverseOrder());
		
		for (Map.Entry<Float, Float> entry : discreteValues.entrySet())
			reverse.put(entry.getKey(), entry.getValue());
		
		return reverse.entrySet().iterator();
	}
}
