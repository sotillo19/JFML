package jfml.membershipfunction;

import java.util.ArrayList;

public class CircularMembershipFunction extends MembershipFunction {

	String name ="circular";
	CircularDefinitionType p;
	
	public CircularMembershipFunction() {
		
	}

	public CircularMembershipFunction(CircularDefinitionType p) {
		super();
		this.p = p;
	}

	public CircularMembershipFunction(CircularDefinitionType p, float domainLeft, float domainRight) {
		super();
		this.p = p;
		this.domainLeft=domainLeft;
		this.domainRight=domainRight;
	}

	@Override
	public float getMembershipDegree(float x) {
		return p.getMembershipDegree(x);
	}

	@Override
	public String toString() {
		return p.toString();
	}

	@Override
	public ArrayList<Float> getXValuesDefuzzifier() {
		// TODO Auto-generated method stub
		return null;
	}

}
