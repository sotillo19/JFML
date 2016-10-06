package jfml.membershipfunction;

import java.util.ArrayList;

import jfml.parameter.Parameter;

public class SShapeMembershipFunction extends MembershipFunction implements Monotonical{

	float a,b;
	
	String name="s-Shape";
	
	public SShapeMembershipFunction() {
		// TODO Auto-generated constructor stub
	}

	public SShapeMembershipFunction(Parameter p) {
		super(p);
		if(p!=null){
			a = p.getParameter(1);
			b = p.getParameter(2);
		}
	}

	public SShapeMembershipFunction(Parameter p, float domainLeft, float domainRight) {
		this(p);
		this.domainLeft=domainLeft;
		this.domainRight=domainRight;
	}

	@Override
	public float getMembershipDegree(float x) {
		if (x <= a)
			return 0;
		if (a <= x && x <= ((a + b) / 2))
			return (float) (2 * (Math.pow((x - a) / (b - a), 2)));
		if (((a + b) / 2) <= x && x <= b)
			return (float) (1 - (2 * (Math.pow((x - b) / (b - a), 2))));
		if (x >= b)
			return 1;

		return 0;
	}

	@Override
	public String toString() {
		return name + " [a: "+a+ ", b: "+b+"]";
	}

	@Override
	public float getFi(float y) {
		if (y == 0)  return a;
	       else if (y == 1) return b;
	       else  if (y >= 0.5)
	           return (float) (b - (Math.sqrt((1.0 - y) / 2.0) * (b - a)));
	       else
	           return (float) ((Math.sqrt(y / 2.0) * (b - a)) + a);
	}

	@Override
	public ArrayList<Float> getXValuesDefuzzifier() {
		return new ArrayList<>();
	}

}
