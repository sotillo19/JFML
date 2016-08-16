package jfml.defuzzifier;


public class DefuzzifierRightMostMax extends DefuzzifierContinuous {

	public DefuzzifierRightMostMax(float leftDomain, float rightDomain) {
		super(leftDomain,rightDomain);
	}

	/** Deffuzification function */
	@Override
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

	}
}
