package jfml.defuzzifier;

public class DefuzzifierMeanMax extends DefuzzifierContinuous {

	public DefuzzifierMeanMax(float leftDomain, float rightDomain) {
		super(leftDomain,rightDomain);
	}

	/** Deffuzification function */
	@Override
	public float defuzzify() {
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

	}
}
