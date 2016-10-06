package jfml.membershipfunction;

import java.util.ArrayList;

import jfml.parameter.Parameter;

public class PiShapedMembershipFunction extends MembershipFunction {

	float a,b,c,d;
	
	String name="pi-Shaped";
	
	public PiShapedMembershipFunction() {
		// TODO Auto-generated constructor stub
	}
	
	public PiShapedMembershipFunction(Parameter p) {
		super(p);
		if(p!=null){
			a = p.getParameter(1);
			b = p.getParameter(2);
			//TODO revise this
			c = b;
			d = b + Math.abs(a - b);
		}
	}

	public PiShapedMembershipFunction(Parameter p, float domainLeft, float domainRight) {
		this(p);
		this.domainLeft=domainLeft;
		this.domainRight=domainRight;
	}

	@Override
	public float getMembershipDegree(float x) {
		if (x <= a) {
			return 0.0f;
		} else if (a <= x && x <= ((a + b) / 2)) {
			return (float) (2 * (Math.pow((x - a) / (b - a), 2)));
		} else if (((a + b) / 2) <= x && x <= b) {
			return (float) (1 - (2 * Math.pow((x - b) / (b - a), 2)));
		} else if (b <= x && x <= c) {
			return 1.0f;
		} else if (c <= x && x <= (c + d) / 2) {
			return (float) (1 - (2 * (Math.pow((x - c) / (d - c), 2))));
		} else if ((c + d) / 2 <= x && x <= d) {
			return (float) (2 * (Math.pow((x - d) / (d - c), 2)));
		} else if (x >= d) {
			return 0.0f;
		}
		return 0.0f;
	}
	
	@Override
	public String toString() {
		return name + " [a: "+a+ ", b: "+b+"]";
	}

	@Override
	public ArrayList<Float> getXValuesDefuzzifier() {
		return new ArrayList<>();
	}

}
