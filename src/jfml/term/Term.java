package jfml.term;

/**
 * Abstract class for representing the terms.
 * 
 * MembershipValue is implemented
 * 
 * @author sotillo19
 *
 */
public abstract class Term {
	
	/**
	 * Gets the name of the term
	 * @return the name of the term
	 */
	public abstract String getName();
	
	/**
	 * Gets the membership degree by calculating the membership value of the parameter x to this term
	 * @param x the value x
	 * @return the membership value of the parameter x to this term
	 */
	public abstract float getMembershipValue(float x);
	
	/**
	 * Creates a copy of the term
	 * @return a copy of the term
	 */
	public abstract Term copy();
}
