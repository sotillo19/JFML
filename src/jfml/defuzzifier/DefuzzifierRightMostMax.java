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
	
	/*@Override
	public float defuzzify() {
		float max = 0, maxX = 0;

		// Find last max
		for( int i = 0; i < values.length; i++ ) {
			if( values[i] >= max ) {
				max = values[i];
				maxX = min + stepSize * i;
			}
		}

		// No max? => this variable has no active antecedent
		if( max <= 0 ) return Float.NaN;

		return maxX;

	}*/
}
