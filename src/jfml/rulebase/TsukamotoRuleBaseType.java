package jfml.rulebase;

/**
 * Java class for implementing Tsukamoto rule base fuzzy systems
 * @author sotillo19
 *
 */
public class TsukamotoRuleBaseType extends RuleBaseType {

	/**
	 * Default constructor
	 */
	public TsukamotoRuleBaseType() {
		
	}

	/**
     * Constructor using name. Rest elements by default
     * @param name name of the Tsukamoto rule base
     */
	public TsukamotoRuleBaseType(String name) {
		super(name, FuzzySystemRuleBase.TYPE_TSUKAMOTO);
	}

	/**
	 * Constructor using the name, the activation method, the and, the or and the type
	 * @param name name of the TSK rule base
	 * @param activation the method used for the implication process according to {@link StandardActivationMethodType }
	 * @param and the and algorithm to be used
	 * @param or the or algorithm to be used
	 * @param type the ruleBaseSystemType
	 */
	public TsukamotoRuleBaseType(String name, String activation, String and, String or, int type) {
		super(name, activation, and, or, type);
	}

}
