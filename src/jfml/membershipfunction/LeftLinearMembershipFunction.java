package jfml.membershipfunction;

import java.util.ArrayList;

import jfml.parameter.Parameter;

public class LeftLinearMembershipFunction extends MembershipFunction implements Monotonical{

	float a,b;
	
	String name="leftLinear";
	
	public LeftLinearMembershipFunction() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param parameter p -> a and b. a<b
	 */
	public LeftLinearMembershipFunction(Parameter p) {
		super(p);
		if(p!=null){
			a = p.getParameter(1);
			b = p.getParameter(2);
		}
		if(a>b)
			throw new RuntimeException("Parameter ERROR: parameters must satisfy a<=b");

	}

	public LeftLinearMembershipFunction(Parameter p, float domainLeft, float domainRight) {
		this(p);
		this.domainLeft=domainLeft;
		this.domainRight=domainRight;
	}

	@Override
	public float getMembershipDegree(float x) {
		if (x <= a)
			return 1;
		if (x >= b)
			return 0;

		return (float) (((b - x) / (b - a)));
	}

	
	@Override
	public String toString() {
		return name + " [a: "+a+ ", b: "+b+"]";
	}

	@Override
	public float getFi(float y) {
		return (b - y*(b-a));
	}

	@Override
	public ArrayList<Float> getXValuesDefuzzifier() {
		ArrayList<Float> v = new ArrayList<>();
		
		v.add(a);
		v.add(b);
		
		return v;
	}

}
