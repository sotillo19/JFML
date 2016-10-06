package jfml.defuzzifier;

import java.util.List;
import java.util.Map;

import jfml.term.FuzzyTermType;

public class DefuzzifierCenterOfGravity extends DefuzzifierContinuous {

	public DefuzzifierCenterOfGravity(float domainleft, float domainright, List<FuzzyTermType> terms) {
		super(domainleft,domainright,terms);
	}

	@Override
	public float defuzzify() {
		float x,y, sum = 0, weightedSum = 0;

		for (Map.Entry<Float, Float> entry : discreteValues.entrySet()){
			x = entry.getKey();
			y = entry.getValue();
			
			sum += y;
			weightedSum += x * y;
		}

		// No sum? => this variable has no active antecedent
		if (sum <= 0) return Float.NaN;

		return weightedSum / sum;
	}
	
	/*public float defuzzify1() {
		float x = min, sum = 0, weightedSum = 0;

		// Calculate integrals (approximated as sums)
		for (int i = 0; i < values.length; i++, x += stepSize) {
			sum += values[i];
			weightedSum += x * values[i];
		}

		// No sum? => this variable has no active antecedent
		if (sum <= 0) return Float.NaN;

		// Calculate center of gravity
		float cog = weightedSum / sum;
		return cog;
	}*/

}
