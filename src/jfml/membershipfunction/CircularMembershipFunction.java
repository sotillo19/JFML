package jfml.membershipfunction;

public class CircularMembershipFunction extends MembershipFunction {

	String name ="circular";
	
	public CircularMembershipFunction() {
		
	}

	public CircularMembershipFunction(CircularDefinitionType p) {
		// TODO Auto-generated constructor stub
	}

	public CircularMembershipFunction(CircularDefinitionType p, float domainLeft, float domainRight) {
		this(p);
		this.domainLeft=domainLeft;
		this.domainRight=domainRight;
	}

	@Override
	public float getMembershipDegree(float x) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
