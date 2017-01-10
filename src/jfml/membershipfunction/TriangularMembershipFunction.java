package jfml.membershipfunction;

import java.util.ArrayList;

import jfml.parameter.Parameter;

/**
 * Java class for representing Triangular functions
 * @author sotillo19
 *
 */
public class TriangularMembershipFunction extends MembershipFunction {

	float a,b,c;
	
	String name="triangular";
	
	/**
	 * Default constructor
	 */
	public TriangularMembershipFunction() {
		
	}

	/**
	 * Constructor with Parameter instance with the parameters of the function
	 * @param p parameter -> a, b and c. Parameters must satisfy a <= b <= c
	 */
	public TriangularMembershipFunction(Parameter p) {
		super(p);
		if(p!=null){
			a = p.getParameter(1);
			b = p.getParameter(2);
			c = p.getParameter(3);
		}
		if(a>b || b>c)
			throw new RuntimeException("Parameter ERROR: parameters must satisfy a <= b <= c");
	}

	/**
	 * Constructor with Parameter instance with the parameters of the function
	 * @param p parameter -> a, b and c. Parameters must satisfy a <= b <= c
	 * @param domainLeft left domain
	 * @param domainRight right domain
	 */
	public TriangularMembershipFunction(Parameter p, float domainLeft, float domainRight) {
		this(p);
		this.domainLeft=domainLeft;
		this.domainRight=domainRight;
	}

	@Override
	public float getMembershipDegree(float x) {
		if (x == b) return 1.0f;
		else if (x <= a) return 0f;
		else if (x >= c) return 0f;
		else if (x < b)  return (float) (x - a) / (b - a);
		else  return (float) (c - x) / (c - b);
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
