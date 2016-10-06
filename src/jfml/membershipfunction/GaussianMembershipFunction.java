package jfml.membershipfunction;

import java.util.ArrayList;

import jfml.parameter.Parameter;

public class GaussianMembershipFunction extends MembershipFunction {

	float sigma, c;
	String name = "gaussian";
	
	public GaussianMembershipFunction() {
	
	}

	/**
	 * 
	 * @param p param1 = c; param2 = sigma
	 */
	public GaussianMembershipFunction(Parameter p) {
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
	public GaussianMembershipFunction(Parameter p, float domainLeft, float domainRight) {
		this(p);
		this.domainLeft=domainLeft;
		this.domainRight=domainRight;
	}

	@Override
	public float getMembershipDegree(float x) {
		return (float) Math.exp(-Math.pow(x-c, 2) / (2 * Math.pow(sigma, 2)));
	}

	@Override
	public String toString() {
		return name + " [c: " + c + ", sigma: "+sigma+"]";
	}

	@Override
	public ArrayList<Float> getXValuesDefuzzifier() {
		ArrayList<Float> v = new ArrayList<>();
		
		v.add(c);
		
		return v;
	}

}
