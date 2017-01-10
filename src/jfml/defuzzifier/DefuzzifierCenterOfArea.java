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
