package jfml.membershipfunction;

import jfml.parameter.Parameter;

public class ZShapeMembershipFunction extends MembershipFunction {

	float a,b;
	
	String name="z-Shaped";
	
	public ZShapeMembershipFunction() {
		// TODO Auto-generated constructor stub
	}

	public ZShapeMembershipFunction(Parameter p) {
		super(p);
		if(p!=null){
			a = p.getParameter(1);
			b = p.getParameter(2);
		}
	}

	public ZShapeMembershipFunction(Parameter p, float domainLeft, float domainRight) {
		this(p);
		this.domainLeft=domainLeft;
		this.domainRight=domainRight;
	}
	@Override
	public float getMembershipDegree(float x) {
		if (x <= a)
			return 1;
		if (a <= x && x <= ((a + b) / 2))
			return (float) (1 - 2 * (Math.pow((x - a) / (b - a), 2)));
		if (((a + b) / 2) <= x && x <= b)
			return (float) (2 * (Math.pow((x - b) / (b - a), 2)));
		if (x >= b)
			return 0;

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
