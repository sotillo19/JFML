package jfml.defuzzifier;

import java.util.HashMap;
import java.util.Iterator;

public abstract class DefuzzifierDiscrete extends Defuzzifier implements Iterable<Double> {

	/** 
	 * Function values: A generic discrete function 
	 * 			x = [x_1, x_2, .... , x_n]
	 * 			y = [y_1, y_2, .... , y_n]
	 * 			y_i = f[x_i]
	 * Values are stored in 'discreteValues' hash
	 */
	protected HashMap<Double, Double> discreteValues;

	public DefuzzifierDiscrete(float leftDomain, float rightDomain) {
		super();
		discrete = true;
		min=leftDomain;
		max=rightDomain;
		discreteValues = new HashMap<Double, Double>();
	}


	/** Deffuzification function */
	@Override
	public abstract float defuzzify();

	/** Get a point's 'y' value */
	public double getDiscreteValue(double x) {
		Double y = discreteValues.get(x);
		if( y == null ) return 0;
		return y;
	}

	/** Get an iterator (on discreteValues' keys) */
	@Override
	public Iterator<Double> iterator() {
		return discreteValues.keySet().iterator();
	}

	/** Reset values */
	@Override
	public void reset() {
		if( discreteValues != null ) { // Set all values to 0
			for( Double key : this )
				discreteValues.put(key, 0.0);
		}
	}

	/** Set a point */
	public void setPoint(double x, double y) {
		discreteValues.put(x, y);
	}

	/** How many points are there in this defuzzifier */
	public int size() {
		return discreteValues.size();
	}

}
