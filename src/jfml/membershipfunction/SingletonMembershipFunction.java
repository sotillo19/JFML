package jfml.membershipfunction;

import java.util.ArrayList;

import jfml.parameter.Parameter;

/**
 * Java class for representing singleton functions
 * @author sotillo19
 *
 */
public class SingletonMembershipFunction extends MembershipFunction implements Monotonical{

	float a;
	
	String name = "singleton";
	
	/**
	 * Default constructor
	 */
	public SingletonMembershipFunction() {
	
	}

	/**
	 * Constructor with a Parameter
	 * @param p parameter with a unique value
	 */
	public SingletonMembershipFunction(Parameter p) {
		super(p);
		if(p!=null){
			a = p.getParameter(1);
		}
	}

	/**
	 * Constructor with a parameter and left and right domain
	 * @param p parameter with a unique value
	 * @param domainLeft left domain
	 * @param domainRight right domain
	 */
	public SingletonMembershipFunction(Parameter p, float domainLeft, float domainRight) {
		this(p);
		this.domainLeft=domainLeft;
		this.domainRight=domainRight;
	}

	@Override
	public float getMembershipDegree(float x) {
		if (x == a)
			return 1;
		else
			return 0;
	}

	@Override
	public String toString() {
		return name + " [a: "+a+"]";
	}

	@Override
	public float getFi(float y) {
		return a;
	}

	@Override
	public ArrayList<Float> getXValuesDefuzzifier() {
		ArrayList<Float> v = new ArrayList<>();
		
		v.add(a);
		
		return v;
	}
}
