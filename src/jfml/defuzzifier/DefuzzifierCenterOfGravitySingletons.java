package jfml.defuzzifier;

public class DefuzzifierCenterOfGravitySingletons extends DefuzzifierDiscrete {

	public DefuzzifierCenterOfGravitySingletons(float leftDomain, float rightDomain) {
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
}
