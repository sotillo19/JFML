package jfml.defuzzifier;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import jfml.term.FuzzyTermType;

public abstract class DefuzzifierContinuous extends Defuzzifier implements Iterable<Float>{

	public static int DEFAULT_NUMBER_OF_POINTS = 1000;

	protected float stepSize;
	
	protected TreeMap<Float, Float> discreteValues; 

	public DefuzzifierContinuous(float domainleft, float domainright, List<FuzzyTermType> terms) {
		super();
		discrete = false;
		min=domainleft;
		max=domainright;
		init(domainleft, domainright, DEFAULT_NUMBER_OF_POINTS,terms);
	}

	public float getArea() {
		float sumX = getMin(), area=0;
		
		for (Map.Entry<Float, Float> entry : discreteValues.entrySet()){
			area += entry.getValue() * (entry.getKey()-sumX);
			sumX = entry.getKey();
		}
		return area;
	}

	public int getLength() {
		return discreteValues.size();
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

	public float getValueY(float x) {
		Float y = discreteValues.get(x);
		if( y == null ) return 0;
		return y.floatValue();
	}

	/**
	 * Initialize defuzzifier
	 * @param min : Minimum
	 * @param max : Maximum
	 * @param numberOfPoints
	 */
	private void init(float min, float max, int numberOfPoints, List<FuzzyTermType> terms) {
		discreteValues = new TreeMap<Float, Float>();

		// Go on only if min & max are setted
		if (Float.isNaN(min) || Float.isNaN(max)) return;

		// Check parameters
		if (min > max) throw new RuntimeException("Parameter max is out of range (should satisfy: min < max). min: " + min + "\tmax: " + max);

		// Initialize
		this.min = min;
		this.max = max;
		stepSize = (max - min) / numberOfPoints;
		
		
		float x = getMin();
		float step = getStepSize();

		//adding x values from the variable x domain
		while(x<getMax()){
			discreteValues.put(x, 0f);
			x += step;
		}
		
		//adding x values from membership functions
		for(FuzzyTermType t : terms){
			for(Float xDef : t.getXValuesDefuzzifier())
				discreteValues.put(xDef, 0f);
		}
		
		reset();
	}

	@Override
	public boolean isDiscrete() {
		return discrete;
	}
	
	/** Reset values */
	@Override
	public void reset() {
		if( discreteValues != null ) { // Set all values to 0
			for( Float key : discreteValues.keySet() )
				discreteValues.put(key, 0.0f);
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
		discreteValues.put(valueX, valueY);
	}

	public Iterator<Float> iterator() {
		return discreteValues.keySet().iterator();
	}

	public void setPoint(float x, float y) {
		discreteValues.put(x, y);
	}
	
	public int size() {
		return discreteValues.size();
	}
	
	@Override
	public abstract float defuzzify();
}
