package jfml.membershipfunction;

import jfml.parameter.Parameter;

public class CustomMembershipFunction extends MembershipFunction {

	String name = "custom";
	
	public CustomMembershipFunction(CustomShapeType c) {
		// TODO Auto-generated constructor stub
	} 
	
	public CustomMembershipFunction() {
		// TODO Auto-generated constructor stub
	}

	public CustomMembershipFunction(Parameter p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	public CustomMembershipFunction(CustomShapeType customShape, float domainLeft, float domainRight) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public float getMembershipDegree(float x) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return null;
	}

}
