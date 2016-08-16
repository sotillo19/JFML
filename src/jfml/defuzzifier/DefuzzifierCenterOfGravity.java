package jfml.defuzzifier;


public class DefuzzifierCenterOfGravity extends DefuzzifierContinuous {

	public DefuzzifierCenterOfGravity(float leftDomain, float rightDomain) {
		super(leftDomain,rightDomain);
	}

	/** Deffuzification function */
	@Override
	public float defuzzify() {
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
	}

}
