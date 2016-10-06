package jfml.rulebase;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import jfml.defuzzifier.DefuzzifierContinuous;
import jfml.knowledgebase.variable.AnYaDataCloudType;
import jfml.knowledgebase.variable.FuzzyVariableType;
import jfml.knowledgebase.variable.TsukamotoVariableType;
import jfml.rule.AnYaRuleType;
import jfml.rule.ClauseType;
import jfml.rule.ConsequentClausesType;
import jfml.rule.FuzzyRuleType;
import jfml.term.FuzzyTerm;
import jfml.term.FuzzyTermType;

/**
 * <p>
 * Java class for anYaRuleBaseType complex type.
 * 
 * <pre>
 * &lt;complexType name="anYaRuleBaseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="anYaRule" type="{http://www.ieee1855.org}anYaRuleType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="activationMethod" type="{http://www.ieee1855.org}activationMethodType" default="MIN" />
 *       &lt;attribute name="networkAddress" type="{http://www.ieee1855.org}networkAddressType" default="127.0.0.1" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "anYaRuleBaseType", propOrder = { "anYaRule" })
public class AnYaRuleBaseType extends FuzzySystemRuleBase {

	@XmlElement(required = true)
	protected List<AnYaRuleType> anYaRule;
	@XmlAttribute(name = "name", required = true)
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlID
	@XmlSchemaType(name = "ID")
	protected String name;
	@XmlAttribute(name = "activationMethod")
	protected String activationMethod;
	@XmlAttribute(name = "networkAddress")
	protected String networkAddress;

	public AnYaRuleBaseType() {
		setRuleBaseSystemType(FuzzySystemRuleBase.TYPE_ANYA);
	}

	/**
	 * Gets the value of the anYaRule property.
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link AnYaRuleType }
	 * 
	 * 
	 */
	public List<AnYaRuleType> getAnYaRules() {
		if (anYaRule == null) {
			anYaRule = new ArrayList<AnYaRuleType>();
		}
		return this.anYaRule;
	}

	/**
	 * Gets the value of the property name.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the value of the property name.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setName(String value) {
		this.name = value;
	}

	/**
	 * Gets the value of the property activationMethod.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getActivationMethod() {
		if (activationMethod == null) {
			return "MIN";
		} else {
			return activationMethod;
		}
	}

	/**
	 * Sets the value of the property activationMethod.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setActivationMethod(String value) {
		this.activationMethod = value;
	}

	/**
	 * Gets the value of the property networkAddress.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getNetworkAddress() {
		if (networkAddress == null) {
			return "127.0.0.1";
		} else {
			return networkAddress;
		}
	}

	/**
	 * Sets the value of the property networkAddress.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setNetworkAddress(String value) {
		this.networkAddress = value;
	}

	@Override
	public void evaluate() {
		float sumN = 0;
		// evaluate the antecedent of each rule
		for (AnYaRuleType r : getAnYaRules()) 
			sumN += evaluateAntecedents(r); // evaluate antecedent -- r.setEvaluation(value)
			
		for (AnYaRuleType r : getAnYaRules()) {
			float li = r.getEvaluation() / sumN;
			// Apply weight - the importance of the rule to be used by the inference engine
			li *= r.getWeight();

			// Activation rule consequents: Apply evaluation to consequent linguisticTerms
			implyConsequents(r, li);
		}
	}
	
	

	private float evaluateAntecedents(AnYaRuleType r){	
		AnYaDataCloudType cloud = (AnYaDataCloudType) r.getAnYaAntecedent().getDataCloudName();
		float x = cloud.getValue();
		
		float dist=0;
		int m = cloud.getTerms().size();
		for(Double d : cloud.getTerms())
			dist += euclidean(d.floatValue(),x);
				
		float value=m/(float)(m+dist);
		
		// sets the evaluation value to the rule r
		r.setEvaluation(value);
		
		return value;
	}
	
	private float euclidean(float x_j, float x_k) {
		return Math.abs(x_k-x_j);
	}

	private void implyConsequents(AnYaRuleType r, float ant_evaluation) {
		ConsequentClausesType then = r.getConsequent().getThen();
		ConsequentClausesType _else = r.getConsequent().getElse();
		if(then!=null){
			for(ClauseType c : then.getClause()){				
				if(c!=null && c.getVariable() instanceof FuzzyVariableType)
					activationMamdani((FuzzyVariableType)c.getVariable(),(FuzzyTerm)c.getTerm(),ant_evaluation);
				else if(c!=null && c.getVariable() instanceof TsukamotoVariableType)
					activationTsukamoto((TsukamotoVariableType)c.getVariable(),(FuzzyTerm)c.getTerm(),ant_evaluation);
			}
			
			if(_else!=null){
				for(ClauseType c : _else.getClause()){
					if(c!=null && c.getVariable() instanceof FuzzyVariableType)
						activationMamdani((FuzzyVariableType)c.getVariable(),(FuzzyTerm)c.getTerm(),(1-ant_evaluation));
					else if(c!=null && c.getVariable() instanceof TsukamotoVariableType)
						activationTsukamoto((TsukamotoVariableType)c.getVariable(),(FuzzyTerm)c.getTerm(),(1-ant_evaluation));
				}
			}
		}
	}

	private void activationMamdani(FuzzyVariableType v, FuzzyTerm t, float ant_evaluation) {
		DefuzzifierContinuous defuzzifier = (DefuzzifierContinuous) v.getDefuzzifier();
		float membership, y, x, aggregated = 0;

		x = defuzzifier.getMin();
		double step = defuzzifier.getStepSize();

		int i, length = defuzzifier.getLength();

		// Add membership degrees function to deffuzifier
		for (i = 0; i < length; i++, x += step) {
			membership = t.getMembershipValue(x);

			//IMPLICATION (activation process)
			y = activation(ant_evaluation, membership);

			// ACCUMULATION
			aggregated = v.accumulation(defuzzifier.getValueY(i), y);
			defuzzifier.setValue(i, aggregated);
		}
	}
	
	
	private void activationTsukamoto(TsukamotoVariableType v, FuzzyTerm t, float ant_evaluation) {
		v.addEvaluation(ant_evaluation,t.getFi(ant_evaluation));
	}
	

	private float evaluateAntecedents(FuzzyRuleType r){
		List<ClauseType> clauses = r.getAntecedent().getClauses();
		float[] degrees = new float[clauses.size()];
		for(int i=0;i<clauses.size();i++){
			ClauseType c= clauses.get(i);
			FuzzyTermType t=null;
			FuzzyVariableType v=null;
			if(c!=null && c.getTerm() instanceof FuzzyTermType)
				t = (FuzzyTermType) c.getTerm();
			if(c.getVariable() instanceof FuzzyVariableType)
				v = (FuzzyVariableType) c.getVariable();
				
			if(t!=null && v!=null)
				degrees[i] = c.modifierMembershipDegree(t.getMembershipValue(v.getValue()));
		}
		
		//aggregate degrees (connector operator)
		return r.aggregation(degrees);
	}

	@Override
	public void reset() {
		for(AnYaRuleType r : getAnYaRules())
			r.reset();
	}

	@Override
	public String toString() {
		String b= "  *"+ getRuleBaseSystemTypeName() + " - " + getName() + ": ACTIVATION=" + getActivationMethod() + "\n";
		int numRule=1;
		for(AnYaRuleType r : getAnYaRules())
			b += "\tRULE "+(numRule++) + ": " +r.toString() +"\n";
			
		return b;
	}

}
