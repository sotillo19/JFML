package jfml.defuzzifier;

/**
 * Generic continuous defuzzifier (a defuzzifier for continuous membership functions)
 * @author pcingola@users.sourceforge.net
 */
public abstract class DefuzzifierContinuous extends Defuzzifier {

	/** Default number of points for 'values[]' */
	public static int DEFAULT_NUMBER_OF_POINTS = 1000;

	/** 
	 * Step size between each element in 'values[]'
	 * 			stepSize = (max - min) / values.length 
	 */
	protected float stepSize;
	/** 
	 * Funcion values: A generic continuous function
	 * 			y = f(x)
	 * where x : [min, max] 
	 * Values are stored in 'values[]' array.
	 * Array's index is calculated as: 
	 * 			index = (x - min) / (max - min) * (values.length)
	 */
	protected float values[];

	public DefuzzifierContinuous(float leftDomain, float rightDomain) {
		super();
		discrete = false;
		min=leftDomain;
		max=rightDomain;
		init(leftDomain, rightDomain, DEFAULT_NUMBER_OF_POINTS);
	}

	public void addValue(int index, float value) {
		values[index] += value;
	}


	/** Deffuzification function */
	@Override
	public abstract float defuzzify();

	/** Calculate function's area */
	public float getArea() {
		float sum = 0;
		for (int i = 0; i < values.length; i++)
			sum += values[i];
		return sum * stepSize;
	}

	/** Get 'values[]' index */
	public int getIndex(float d) {
		if ((d < min) || (d > max)) throw new RuntimeException("Value out of range: " + d);
		return (int) ((d - min) / stepSize);
	}

	public int getLength() {
		if (values != null) return values.length;
		return 0;
	}

	public float getMax() {
		return max;
	}

	public float getMin() {
		return min;
	}

	public float getStepSize() {
		return stepSize;
	}

	/** Get a value from 'values[]' using a float as index */
	public float getValue(float x) {
		return values[getIndex(x)];
	}

	public float getValue(int index) {
		return values[index];
	}

	public float[] getValues() {
		return values;
	}

	/**
	 * Initialize
	 * @param min : Minimum
	 * @param max : Maximum
	 * @param numberOfPoints
	 */
	private void init(float min, float max, int numberOfPoints) {
		values = new float[numberOfPoints];

		// Go on only if min & max are setted
		if (Float.isNaN(min) || Float.isNaN(max)) return;

		// Check parameters
		if (min > max) throw new RuntimeException("Parameter max is out of range (should satisfy: min < max). min: " + min + "\tmax: " + max);

		// Initialize
		this.min = min;
		this.max = max;
		stepSize = (max - min) / numberOfPoints;
		reset();
	}

	@Override
	public boolean isDiscrete() {
		return discrete;
	}

	/** Reset values (in 'values[] array) */
	@Override
	public void reset() {
		if (values != null) {
			for (int i = 0; i < values.length; i++)
				values[i] = 0;
		}
	}

	@Override
	public void setDiscrete(boolean discrete) {
		this.discrete = discrete;
	}

	public void setMax(float max) {
		this.max = max;
	}

	public void setMin(float min) {
		this.min = min;
	}

	public void setStepSize(float stepSize) {
		this.stepSize = stepSize;
	}

	public void setValue(float valueX, float valueY) {
		values[getIndex(valueX)] = valueY;
	}

	public void setValue(int index, float value) {
		values[index] = value;
	}

	public void setValues(float[] values) {
		this.values = values;
	}
}
