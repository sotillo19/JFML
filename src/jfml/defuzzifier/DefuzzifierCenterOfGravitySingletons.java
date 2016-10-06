package jfml.defuzzifier;

public class DefuzzifierCenterOfGravitySingletons extends DefuzzifierDiscrete {

	public DefuzzifierCenterOfGravitySingletons(float leftDomain, float rightDomain) {
		super(leftDomain,rightDomain);
	}

	@Override
	public float defuzzify() {
		Float x;
		float y, sum = 0, sumWeight = 0;
		for( Float xD : this ) {
			y = (float) getDiscreteValue(xD);
			x = xD;
			sumWeight += x * y;
			sum += y;
		}

		if( sum != 0 ) return sumWeight / sum;
		return Float.NaN;
	}
}
