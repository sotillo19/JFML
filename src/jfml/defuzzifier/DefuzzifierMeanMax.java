package jfml.defuzzifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jfml.term.FuzzyTermType;

public class DefuzzifierMeanMax extends DefuzzifierContinuous {

	public DefuzzifierMeanMax(float domainleft, float domainright, List<FuzzyTermType> terms) {
		super(domainleft,domainright,terms);
	}
	
	@Override
	public float defuzzify() {
		float max = 0;
		float x,y;
		
		for (Map.Entry<Float, Float> entry : discreteValues.entrySet()){
			y = entry.getValue();
			if(y >= max)
				max = y;
		}

		// No max? => this variable has no active antecedent
		if( max <= 0 ) return Float.NaN;
		
		// Calculate mean of max
		ArrayList<Float> mom = new ArrayList<>();
		for (Map.Entry<Float, Float> entry : discreteValues.entrySet()){
			y = entry.getValue();
			x = entry.getKey();
			if(y == max)
				mom.add(x);
		}

		// Return mean of max
		float sum = 0;
		for(int i=0;i<mom.size()-1;i++)
			sum += mom.get(i+1)-mom.get(i);
		
		return mom.get(0)+(sum / 2);

	}
	
	/*public float defuzzify1() {
		float max = 0, maxX = 0;
		int count = 0;

		// Calculate max
		for( int i = 0; i < values.length; i++ ) {
			if( values[i] >= max ) max = values[i];
		}

		// No max? => this variable has no active antecedent
		if( max <= 0 ) return Float.NaN;

		// Calculate mean of max
		for( int i = 0; i < values.length; i++ ) {
			if( values[i] == max ) {
				maxX = min + stepSize * i;
				count++;
			}
		}

		// Return mean of max
		return maxX / count;

	}*/
}
