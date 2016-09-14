package jfml.membershipfunction;

public interface MonotonicalMembershipFunction {
	/**
	 * This function returns the inverse value. Given y, return x where f(x)=y
	 * @param y
	 * @return
	 */
	public float getFi(float y);
}
