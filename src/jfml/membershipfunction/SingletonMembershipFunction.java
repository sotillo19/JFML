package jfml.membershipfunction;

import java.util.ArrayList;

import jfml.parameter.Parameter;

public class SingletonMembershipFunction extends MembershipFunction implements Monotonical{

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
	public String toString() {
		return name + " [a: "+a+"]";
	}

	@Override
	public float getFi(float y) {
		return a;
	}

	@Override
	public ArrayList<Float> getXValuesDefuzzifier() {
		ArrayList<Float> v = new ArrayList<>();
		
		v.add(a);
		
		return v;
	}
}
