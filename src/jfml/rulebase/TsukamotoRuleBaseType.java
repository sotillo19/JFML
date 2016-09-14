package jfml.rulebase;

public class TsukamotoRuleBaseType extends RuleBaseType {

	public TsukamotoRuleBaseType() {
		// TODO Auto-generated constructor stub
	}

	public TsukamotoRuleBaseType(String name) {
		super(name, FuzzySystemRuleBase.TYPE_TSUKAMOTO);
	}

	public TsukamotoRuleBaseType(String name, String activation, String and, String or, int type) {
		super(name, activation, and, or, type);
	}

}
