package jfml.membershipfunction;

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
		if (a <= b && a <= c) {
			if (x < a)
				return 0;
			else {
				if (a <= x && x <= b) {
					if (a == b)
						return 1;
					else
						return ((x - a) / (b - a));
				} else {
					if (b <= x && x <= c)
						return 1;
					else {
						if (c <= x && x <= d) {
							if (c == d)
								return 1;
							else
								return ((d - x) / (d - c));
						} else
							return 0;
					}
				}

			}
		} else {
			if (a <= x && x <= b) {
				return ((x - a) / (b - a));
			} else {
				if (x > b || x <= c)
					return 1;
				else {
					if (x > c && x < d)
						return ((d - x) / (d - c));
					else
						return 0;
				}
			}
		}
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
		return name + " [a: "+a+ ", b: "+b+", c: "+c+ ", d: "+d+"]";
	}

}
