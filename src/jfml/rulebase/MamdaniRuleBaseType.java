package jfml.rulebase;

/**
 * Java class for representing Mamdani rule base fuzzy systems
 * @author sotillo19
 *
 */
public class MamdaniRuleBaseType extends RuleBaseType {

	/**
	 * Default constructor
	 */
	public MamdaniRuleBaseType() {
		
	}

	/**
	 * Constructor using the name of Mamdani the rule base
	 * @param name
	 */
	public MamdaniRuleBaseType(String name) {
		super(name, FuzzySystemRuleBase.TYPE_MAMDANI);
	}

	/**
	 * Constructor using the name, the activation method, the and, the or and the type
	 * @param name name of the Mamdani rule base
	 * @param activation the method used for the implication process according to {@link StandardActivationMethodType }
	 * @param and the and algorithm to be used
	 * @param or the or algorithm to be used
	 * @param type the ruleBaseSystemType
	 */
	public MamdaniRuleBaseType(String name, String activation, String and, String or, int type) {
		super(name, activation, and, or, type);
	}

}
