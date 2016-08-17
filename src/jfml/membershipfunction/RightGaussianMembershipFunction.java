package jfml.membershipfunction;

import jfml.parameter.Parameter;

public class RightGaussianMembershipFunction extends MembershipFunction {

	float sigma, c;
	
	String name="rightGaussian";
	
	public RightGaussianMembershipFunction() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param p param1 = c; param2 = sigma
	 */
	public RightGaussianMembershipFunction(Parameter p) {
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
	public RightGaussianMembershipFunction(Parameter p, float domainLeft, float domainRight) {
		this(p);
		this.domainLeft=domainLeft;
		this.domainRight=domainRight;
	}

	@Override
	public float getMembershipDegree(float x) {
		if (x >= c)
			return 1;
		else
			return (float) Math.exp(-Math.pow(x-c, 2) / (2 * Math.pow(sigma, 2))); 
	}

	@Override
	public boolean checkParamters(StringBuffer errors) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void estimateUniverse() {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		return name + " [c: " + c + ", sigma: "+sigma+"]";
	}
	
}