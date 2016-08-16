package jfml.defuzzifier;

import java.util.HashMap;


/**
 * Center of gravity for functions defuzzyfier
 * 
 * @author pcingola@users.sourceforge.net
 */
public class DefuzzifierCenterOfGravityFunctions extends DefuzzifierDiscrete {

	public DefuzzifierCenterOfGravityFunctions(float leftDomain, float rightDomain) {
		super(leftDomain,rightDomain);
	}

	/** Deffuzification function */
	@Override
	public float defuzzify() {
		Double x;
		float y, sum = 0, sumWeight = 0;
		for( Double xD : this ) {
			y = (float) getDiscreteValue(xD);
			x = xD;
			sumWeight += x * y;
			sum += y;
		}

		if( sum != 0 ) return sumWeight / sum;
		return Float.NaN;
	}

	/** Reset values */
	@Override
	public void reset() {
		discreteValues = new HashMap<Double, Double>();
	}

}
