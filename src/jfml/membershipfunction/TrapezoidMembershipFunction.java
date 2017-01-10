package jfml.membershipfunction;

import java.util.ArrayList;

import jfml.parameter.Parameter;

/**
 * Java class for representing Trapezoid functions
 * @author sotillo19
 *
 */
public class TrapezoidMembershipFunction extends MembershipFunction {

	float a,b,c,d;
	
	String name = "trapezoid";
	
	/**
	 * Default constructor
	 */
	public TrapezoidMembershipFunction() {
		
	}

	/**
	 * Constructor with Parameter instance with the parameters of the function
	 * @param p parameter -> a, b, c and d. Parameters must satisfy a <= b <= c <= d
	 */
	public TrapezoidMembershipFunction(Parameter p) {
		super(p);
		if(p!=null){
			a = p.getParameter(1);
			b = p.getParameter(2);
			c = p.getParameter(3);
			d = p.getParameter(4);
		}
		if(a>b || b>c || c>d)
			throw new RuntimeException("Parameter ERROR: parameters must satisfy a <= b <= c <= d");
		
	}

	/**
	 * Constructor with Parameter instance with the parameters of the function
	 * @param p parameter -> a, b, c and d. Parameters must satisfy a <= b <= c <= d
	 * @param domainLeft left domain
	 * @param domainRight right domain
	 */
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
