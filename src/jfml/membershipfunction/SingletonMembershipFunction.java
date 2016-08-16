package jfml.membershipfunction;

import jfml.parameter.Parameter;

public class SingletonMembershipFunction extends MembershipFunction {

	float a;
	
	String name = "singleton";
	
	public SingletonMembershipFunction() {
		// TODO Auto-generated constructor stub
	}

	public SingletonMembershipFunction(Parameter p) {
		super(p);
		if(p!=null){
			a = p.getParameter(1);
		}
	}

	public SingletonMembershipFunction(Parameter p, float domainLeft, float domainRight) {
		this(p);
		this.domainLeft=domainLeft;
		this.domainRight=domainRight;
	}

	@Override
	public float getMembershipDegree(float x) {
		if (x == a)
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
		return name + " [a: "+a+"]";
	}
}
