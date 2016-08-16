package jfml.parameter;

public abstract class Parameter {

	/**
	 * Return the number of parameters
	 * @return
	 */
	public abstract int getParameterLength();
	
	/**
	 * return the i-th parameter.
	 * @param i the i-th parameter. Starts with 1. Parameter 1 --> i=1
	 * @return the value of the i-th parameter. If i is not in the range, returns -1
	 */
	public abstract float getParameter(int i);
}
