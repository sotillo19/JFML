package jfml.membershipfunction;

import jfml.parameter.Parameter;
import jfml.parameter.TwoParamType;

public class RightLinearMembershipFunction extends MembershipFunction implements MonotonicalMembershipFunction {

	float a,b;
	
	String name = "rightLinear";
	
	public RightLinearMembershipFunction() {
		// TODO Auto-generated constructor stub
	}

	public RightLinearMembershipFunction(Parameter p) {
		super(p);
		if(p!=null){
			a = p.getParameter(1);
			b = p.getParameter(2);
		}
	}

	public RightLinearMembershipFunction(TwoParamType p, float domainLeft, float domainRight) {
		this(p);
		this.domainLeft=domainLeft;
		this.domainRight=domainRight;
	}

	@Override
	public float getMembershipDegree(float x) {
		if (x <= a)
			return 0;
		if (x >= b)
			return 1;

		return (float) (((x - a) / (b - a)));
	}
	
	@Override
	public String toString() {
		return name + " [a: "+a+ ", b: "+b+"]";
	}

	@Override
	public float getFi(float y) {
		return (a + y*(b-a));
	}

}
