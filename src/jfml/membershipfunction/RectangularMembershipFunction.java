package jfml.membershipfunction;

import jfml.parameter.Parameter;

public class RectangularMembershipFunction extends MembershipFunction {

	float a,b;
	
	String name="rectangular";
	
	public RectangularMembershipFunction() {
		// TODO Auto-generated constructor stub
	}

	public RectangularMembershipFunction(Parameter p) {
		super(p);
		if(p!=null){
			a = p.getParameter(1);
			b = p.getParameter(2);
		}
	}

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
		return name + " [a: "+a+ ", b: "+b+"]";
	}
}
