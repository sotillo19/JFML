package jfml.rulebase;

public class MamdaniRuleBaseType extends RuleBaseType {

	public MamdaniRuleBaseType() {
		// TODO Auto-generated constructor stub
	}

	public MamdaniRuleBaseType(String name) {
		super(name, FuzzySystemRuleBase.TYPE_MAMDANI);
	}

	public MamdaniRuleBaseType(String name, String activation, String and, String or, int type) {
		super(name, activation, and, or, type);
		// TODO Auto-generated constructor stub
	}

}
