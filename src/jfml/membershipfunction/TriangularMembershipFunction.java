package jfml.membershipfunction;

import java.util.ArrayList;

import jfml.parameter.Parameter;

public class TriangularMembershipFunction extends MembershipFunction {

	float a,b,c;
	
	String name="triangular";
	
	public TriangularMembershipFunction() {
		// TODO Auto-generated constructor stub
	}

	public TriangularMembershipFunction(Parameter p) {
		super(p);
		if(p!=null){
			a = p.getParameter(1);
			b = p.getParameter(2);
			c = p.getParameter(3);
		}
	}

	public TriangularMembershipFunction(Parameter p, float domainLeft, float domainRight) {
		this(p);
		this.domainLeft=domainLeft;
		this.domainRight=domainRight;
	}

	@Override
	public float getMembershipDegree(float x) {
		if (a == b && b <= c) {
			if (a < x)
				return 0f;
			if (a == x)
				return 1f;
			if (b <= x && x <= c)
				return (float) (c - x) / (c - b);
			if (x > c)
				return 0f;
		}

		if (b == c && a <= b) {
			if (a < x)
				return 0f;
			if (b == x)
				return 1f;
			if (a <= x && x <= b)
				return (float) (x - a) / (b - a);
			if (x > c)
				return 0f;
		}

		if (x <= a)
			return 0f;
		if (a < x && x <= b)
			return (float) (x - a) / (b - a);
		if (b < x && x < c)
			return (float) (c - x) / (c - b);
		if (c <= x)
			return 0f;

		return 0f;
	}
	
	@Override
	public String toString() {
		return name + " [a: "+a+ ", b: "+b+", c: "+c+"]";
	}

	@Override
	public ArrayList<Float> getXValuesDefuzzifier() {
		ArrayList<Float> v = new ArrayList<>();
		
		v.add(a);
		v.add(b);
		v.add(c);
		
		return v;
	}

}
