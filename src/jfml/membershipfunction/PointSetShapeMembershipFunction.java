package jfml.membershipfunction;

public class PointSetShapeMembershipFunction extends MembershipFunction {

	String name="pointSet";
	
	public PointSetShapeMembershipFunction() {
		// TODO Auto-generated constructor stub
	}

	public PointSetShapeMembershipFunction(PointSetShapeType p) {
		// TODO Auto-generated constructor stub
	}

	public PointSetShapeMembershipFunction(PointSetShapeType p, float domainLeft, float domainRight) {
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
		return name;
	}

}
