package jfml.membershipfunction;

import java.util.ArrayList;

import jfml.parameter.Parameter;

/**
 * Java class for representing Rectangular membership functions
 * @author sotillo19
 *
 */
public class RectangularMembershipFunction extends MembershipFunction {

	float a,b;
	
	String name="rectangular";
	
	/**
	 * Default constructor
	 */
	public RectangularMembershipFunction() {
		
	}

	/**
	 * Constructor with a Parameter instance with the parameters a and b 
	 * @param p Parameter -> a and b. Parameters a and b must satisfy a<=b
	 */
	public RectangularMembershipFunction(Parameter p) {
		super(p);
		if(p!=null){
			a = p.getParameter(1);
			b = p.getParameter(2);
		}
		if(a>b)
			throw new RuntimeException("Parameter ERROR: parameters must satisfy a<=b");
	}

	/**
	 * Constructor with a Parameter and the left and right domain
	 * @param p Parameter -> a and b. Parameters must satisfy a<=b
	 * @param domainLeft left domain
	 * @param domainRight right domain
	 */
	public RectangularMembershipFunction(Parameter p, float domainLeft, float domainRight) {
		this(p);
		this.domainLeft=domainLeft;
		this.domainRight=domainRight;
	}
	@Override
	public float getMembershipDegree(float x) {
		if (x < a)
			return 0;
		if (x >= a && x <= b)
			return 1;
		else
			return 0;
	}

	@Override
	public String toString() {
		return name + " [a: "+a+ ", b: "+b+"]";
	}

	@Override
	public ArrayList<Float> getXValuesDefuzzifier() {
		ArrayList<Float> v = new ArrayList<>();
		
		v.add(a);
		v.add(b);
		
		return v;
	}
}
