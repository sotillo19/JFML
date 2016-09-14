package jfml.membershipfunction;

import jfml.parameter.Parameter;

public class LeftGaussianMembershipFunction extends MembershipFunction implements MonotonicalMembershipFunction{

	float sigma, c;
	
	String name = "leftGaussian";
	
	public LeftGaussianMembershipFunction() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param p param1 = c; param2 = sigma
	 * 
	 */
	public LeftGaussianMembershipFunction(Parameter p) {
		super(p);
		if(p!=null){
			sigma = p.getParameter(2);
			c = p.getParameter(1);
		}
	}

	/**
	 * 
	 * @param p param1 = c; param2 = sigma
	 * @param domainLeft
	 * @param domainRight
	 */
	public LeftGaussianMembershipFunction(Parameter p, float domainLeft, float domainRight) {
		this(p);
		this.domainLeft=domainLeft;
		this.domainRight=domainRight;
	}

	@Override
	public float getMembershipDegree(float x) {
		if (x <= c)
			return 1;
		else
			return (float) Math.exp(-Math.pow(x-c, 2) / (2 * Math.pow(sigma, 2)));
	}

	@Override
	public String toString() {
		return name + " [c: " + c + ", sigma: "+sigma+"]";
	}

	@Override
	public float getFi(float y) {
		if(y==1)
			return c;
		else
			return (float) (c + Math.sqrt(-2*Math.pow(sigma, 2)*Math.log(y)));
	}

}
