package jfml.membershipfunction;

import java.util.ArrayList;

import jfml.parameter.Parameter;

public class TrapezoidMembershipFunction extends MembershipFunction {

	float a,b,c,d;
	
	String name = "trapezoid";
	
	public TrapezoidMembershipFunction() {
		// TODO Auto-generated constructor stub
	}

	public TrapezoidMembershipFunction(Parameter p) {
		super(p);
		if(p!=null){
			a = p.getParameter(1);
			b = p.getParameter(2);
			c = p.getParameter(3);
			d = p.getParameter(4);
		}
	}

	public TrapezoidMembershipFunction(Parameter p, float domainLeft, float domainRight) {
		this(p);
		this.domainLeft=domainLeft;
		this.domainRight=domainRight;
	}

	@Override
	public float getMembershipDegree(float x) {
		if ((x >= b) && (x <= c)) return 1.0f;
		else if (x <= a) return 0f;
		else if (x >= d) return 0f;
		else if (x < b)  return (float) (x - a) / (b - a);
		else  return (float) (d - x) / (d - c);
	}
	
	@Override
	public String toString() {
		return name + " [a: "+a+ ", b: "+b+", c: "+c+ ", d: "+d+"]";
	}

	@Override
	public ArrayList<Float> getXValuesDefuzzifier() {
		ArrayList<Float> v = new ArrayList<>();
		
		v.add(a);
		v.add(b);
		v.add(c);
		v.add(d);
		
		return v;
	}

}
