package jfml.membershipfunction;

import java.util.ArrayList;

/**
 * Class for CircularDefinition membership
 * @author sotillo19
 *
 */
public class CircularMembershipFunction extends MembershipFunction {

	String name ="circular";
	CircularDefinitionType p;
	
	/**
	 * Default constructor
	 */
	public CircularMembershipFunction() {
		
	}

	/**
	 * Constructor with a CircularDefinitionType as param
	 * @param p a CircularDefinitionType
	 */
	public CircularMembershipFunction(CircularDefinitionType p) {
		super();
		this.p = p;
	}

	/**
	 * Constructor with a a CircularDefinitionType and the left and right domain
	 * @param p CircularDefinitionType
	 * @param domainLeft left domain
	 * @param domainRight right domain
	 */
	public CircularMembershipFunction(CircularDefinitionType p, float domainLeft, float domainRight) {
		super();
		this.p = p;
		this.domainLeft=domainLeft;
		this.domainRight=domainRight;
	}

	@Override
	public float getMembershipDegree(float x) {
		return p.getMembershipDegree(x);
	}

	@Override
	public String toString() {
		return p.toString();
	}

	@Override
	public ArrayList<Float> getXValuesDefuzzifier() {
		// TODO Auto-generated method stub
		return null;
	}

}
