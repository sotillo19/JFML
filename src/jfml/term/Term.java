package jfml.term;

public abstract class Term {
	
	public abstract String getName();
	
	public abstract float getMembershipValue(float x);
	
	public abstract Term copy();
}
