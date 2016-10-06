package jfml.defuzzifier;

public abstract class Defuzzifier{

	protected boolean discrete;
	
	protected float max; // Where function ends 
	protected float min; // Where function begins

	/** 
	 * @return NaN if no rule inferred this variable 
	 */
	public abstract float defuzzify();

	public String getName() {
		String str = this.getClass().getName();
		String dfStr = "Defuzzifier";
		int ind = str.lastIndexOf('.');
		if (ind >= 0) {
			str = str.substring(ind + 1);
			if (str.startsWith(dfStr)) str = str.substring(dfStr.length());
		}
		return str;
	}

	public boolean isDiscrete() {
		return discrete;
	}
	
	public void setDiscrete(boolean discrete) {
		this.discrete = discrete;
	}

	/** Reset defuzzifier values, this method is invoked on every rule */
	public abstract void reset();


	@Override
	public String toString() {
		return getName();
	}
}
