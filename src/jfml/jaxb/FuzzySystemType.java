package jfml.jaxb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.w3c.dom.Element;

import jfml.knowledgebase.KnowledgeBaseType;
import jfml.knowledgebase.variable.FuzzyVariableType;
import jfml.knowledgebase.variable.KnowledgeBaseVariable;
import jfml.knowledgebase.variable.TskVariableType;
import jfml.knowledgebase.variable.TsukamotoVariableType;
import jfml.rulebase.AnYaRuleBaseType;
import jfml.rulebase.FuzzySystemRuleBase;
import jfml.rulebase.RuleBaseType;
import jfml.rulebase.TskRuleBaseType;

/**
 * <p>
 * Java class for the fuzzySystemType complex type.
 * 
 * <pre>
 * &lt;complexType name="fuzzySystemType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="knowledgeBase" type="{http://www.ieee1855.org}knowledgeBaseType"/>
 *         &lt;choice maxOccurs="unbounded">
 *           &lt;element name="mamdaniRuleBase" type="{http://www.ieee1855.org}ruleBaseType"/>
 *           &lt;element name="tsukamotoRuleBase" type="{http://www.ieee1855.org}ruleBaseType"/>
 *           &lt;element name="tskRuleBase" type="{http://www.ieee1855.org}tskRuleBaseType"/>
 *           &lt;element name="anYaRuleBase" type="{http://www.ieee1855.org}anYaRuleBaseType"/>
 *           &lt;any processContents='lax' namespace='##other' minOccurs="0"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="networkAddress" type="{http://www.ieee1855.org}networkAddressType" default="127.0.0.1" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fuzzySystemType", propOrder = { "knowledgeBase", "ruleBase" })
public class FuzzySystemType {

	@XmlElement(required = true)
	protected KnowledgeBaseType knowledgeBase;
	@XmlElementRefs({
			@XmlElementRef(name = "tskRuleBase", namespace = "http://www.ieee1855.org", type = JAXBElement.class, required = false),
			@XmlElementRef(name = "anYaRuleBase", namespace = "http://www.ieee1855.org", type = JAXBElement.class, required = false),
			@XmlElementRef(name = "tsukamotoRuleBase", namespace = "http://www.ieee1855.org", type = JAXBElement.class, required = false),
			@XmlElementRef(name = "mamdaniRuleBase", namespace = "http://www.ieee1855.org", type = JAXBElement.class, required = false) })
	@XmlAnyElement(lax = true)
	protected List<Object> ruleBase;
	@XmlAttribute(name = "name", required = true)
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlID
	@XmlSchemaType(name = "ID")
	protected String name;
	@XmlAttribute(name = "networkAddress")
	protected String networkAddress;

	/**
	 * Default constructor
	 */
	public FuzzySystemType() {
		super();
	}

	/**
	 * Constructor using the FuzzySystem name
	 * 
	 * @param name
	 */
	public FuzzySystemType(String name) {
		super();
		this.setName(name);
	}
	
	public FuzzySystemType(String name, KnowledgeBaseType knowledgeBase, List<Object> ruleBase, String networkAddress){
		super();
		this.name=name;
		this.knowledgeBase=knowledgeBase;
		this.ruleBase=ruleBase;
		this.networkAddress=networkAddress;
	}

	/**
	 * Gets the value of the knowledgeBase property.
	 * 
	 * @return possible object is {@link KnowledgeBaseType }
	 * 
	 */
	public KnowledgeBaseType getKnowledgeBase() {
		return knowledgeBase;
	}

	/**
	 * Sets the value of the knowledgeBase property.
	 * 
	 * @param value
	 *            allowed object is {@link KnowledgeBaseType }
	 * 
	 */
	public void setKnowledgeBase(KnowledgeBaseType value) {
		this.knowledgeBase = value;
	}

	/**
	 * Gets the value of the ruleBase property.
	 * 
	 * Objects of the following type(s) are allowed in the list {@link Object }
	 * {@link JAXBElement }{@code <}{@link RuleBaseType }{@code >}
	 * {@link JAXBElement }{@code <}{@link TskRuleBaseType }{@code >}
	 * {@link JAXBElement }{@code <}{@link AnYaRuleBaseType }{@code >}
	 * {@link JAXBElement }{@code <}{@link RuleBaseType }{@code >}
	 * {@link Element }
	 * 
	 * 
	 */
	public List<Object> getRuleBase() {
		if (ruleBase == null) {
			ruleBase = new ArrayList<Object>();
		}
		return this.ruleBase;
	}

	/**
	 * Add a new RuleBase to the fuzzySystem
	 * 
	 * @param r
	 *            allowed object is {@link FuzzySystemRuleBaseType }
	 */
	public void addRuleBase(FuzzySystemRuleBase r) {
		if (ruleBase == null) {
			ruleBase = new ArrayList<Object>();
		}
		ObjectFactory of = new ObjectFactory();
		JAXBElement<?> e = null;

		if (r instanceof RuleBaseType) {
			if (r.getRuleBaseSystemType() == FuzzySystemRuleBase.TYPE_MAMDANI)
				e = of.createFuzzySystemTypeMamdaniRuleBase((RuleBaseType) r);
			else
				e = of.createFuzzySystemTypeTsukamotoRuleBase((RuleBaseType) r);
		} else if (r instanceof TskRuleBaseType)
			e = of.createFuzzySystemTypeTskRuleBase((TskRuleBaseType) r);

		else if (r instanceof AnYaRuleBaseType)
			e = of.createFuzzySystemTypeAnYaRuleBase((AnYaRuleBaseType) r);

		if (e != null)
			this.ruleBase.add(e);
	}

	/**
	 * Gets the value of the name property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the value of the name property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setName(String value) {
		this.name = value;
	}

	/**
	 * Gets the value of the networkAddress property.
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
	 * Sets the value of the networkAddress property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setNetworkAddress(String value) {
		this.networkAddress = value;
	}

	/**
	 * 
	 * @return the JAXBE element
	 */
	public JAXBElement<FuzzySystemType> getJAXBElement() {
		jfml.jaxb.ObjectFactory of = new jfml.jaxb.ObjectFactory();

		return of.createFuzzySystem(this);
	}

	/**
	 * Set a value to a variable identifies by its name
	 * 
	 * @param var
	 *            allowed object is {@link String }
	 * @param value
	 *            allowed object is {@link float }
	 */
	public void setVariableValue(String var, float value) {
		KnowledgeBaseVariable v = getVariable(getKnowledgeBase().getVariables(), var);
		if (v != null)
			v.setValue(value);
	}

	/**
	 * Return a variable instance identifies by its name
	 * 
	 * @param var
	 *            allowed object is {@link String }
	 * @return allowed object is {@link KnowledgeBaseVariable }
	 */
	public KnowledgeBaseVariable getVariable(String var) {
		return getVariable(getKnowledgeBase().getVariables(), var);
	}

	@SuppressWarnings("rawtypes")
	private KnowledgeBaseVariable getVariable(List<Object> var, String cad) {
		Iterator<Object> it = var.iterator();

		while (it.hasNext()) {
			Object v = it.next();
			if (((JAXBElement) v).getValue() instanceof KnowledgeBaseVariable) {
				KnowledgeBaseVariable kbvar = (KnowledgeBaseVariable) ((JAXBElement) v).getValue();
				if (kbvar.getName().equals(cad))
					return kbvar;
			}
		}
		return null;

	}

	/**
	 * Evaluate the fuzzy system
	 */
	public void evaluate() {
		// Reset defuzzifiers, variables, rules, etc.
		reset(getKnowledgeBase(), getRuleBase());
		
		// Evaluate each rule
		evaluateRules();

		// Defuzzify each consequent variable: fuzzy variable call defuzzify() and other variable call getValue()
		//defuzzify(getKnowledgeBase());

	}

	@SuppressWarnings("rawtypes")
	private void reset(KnowledgeBaseType knowledgeBase, List<Object> ruleBase) {
		// RESETTING VARIABLES
		boolean tsukamoto = false;
		for (Object v : knowledgeBase.getVariables()) {
			KnowledgeBaseVariable var = null;
			if (((JAXBElement) v).getValue() instanceof KnowledgeBaseVariable) {
				var = (KnowledgeBaseVariable) ((JAXBElement) v).getValue();
				if (var != null && var.isOutput()){
					var.reset();
					//SETTING INPUT VARIABLES INTO TSKVARIABLES
					if(var instanceof TskVariableType)
						((TskVariableType) var).setInputVariables(knowledgeBase.getKnowledgeBaseVariables());
					if(var instanceof TsukamotoVariableType)
						tsukamoto=true;
				}
			}
		}

		// RESETTING RULES
		for (Object rb : ruleBase) {
			FuzzySystemRuleBase r = null;
			if (((JAXBElement) rb).getValue() instanceof FuzzySystemRuleBase) {
				r = (FuzzySystemRuleBase) ((JAXBElement) rb).getValue();
				
				if (r != null)
					r.reset();
				
				if(r instanceof RuleBaseType && tsukamoto)
					r.setRuleBaseSystemType(FuzzySystemRuleBase.TYPE_TSUKAMOTO);
			}
		}
	}

	/*@SuppressWarnings("rawtypes")
	private void defuzzify(KnowledgeBaseType kb) {
		for (Object v : kb.getVariables()) {
			KnowledgeBaseVariable var = null;
			if (((JAXBElement) v).getValue() instanceof KnowledgeBaseVariable) {
				var = (KnowledgeBaseVariable) ((JAXBElement) v).getValue();
				if (var != null)
					var.defuzzify();
			}
		}

	}*/

	@SuppressWarnings("rawtypes")
	private void evaluateRules() {
		for (Object rb : getRuleBase()) {
			FuzzySystemRuleBase r = null;
			if (((JAXBElement) rb).getValue() instanceof FuzzySystemRuleBase)
				r = (FuzzySystemRuleBase) ((JAXBElement) rb).getValue();

			if (r != null && r instanceof RuleBaseType) {
				if (((RuleBaseType) r).getRuleBaseSystemType() == RuleBaseType.TYPE_MAMDANI)
					evaluateMamdani((RuleBaseType) r);
				else if (((RuleBaseType) r).getRuleBaseSystemType() == RuleBaseType.TYPE_TSUKAMOTO)
					evaluateTsukamoto((RuleBaseType) r);
			} else if (r != null && r instanceof TskRuleBaseType) {
				evaluateTsk((TskRuleBaseType) r);
			} else if (r != null && r instanceof AnYaRuleBaseType) {
				evaluateAnYa((AnYaRuleBaseType) r);
			} else
				evaluateAny(r);
		}
	}

	private void evaluateAny(Object r) {
		// TODO Auto-generated method stub

	}

	private void evaluateAnYa(AnYaRuleBaseType r) {
		r.evaluate();
	}

	private void evaluateTsk(TskRuleBaseType r) {
		r.evaluate();
	}

	private void evaluateTsukamoto(RuleBaseType r) {
		evaluateMamdani(r);
	}

	private void evaluateMamdani(RuleBaseType r) {
		r.evaluate();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String toString() {
		StringBuffer b = new StringBuffer("\n");

		b.append("FUZZY SYSTEM: " + getName() + "\n");

		// KNOWLEDGEBASE
		b.append(getKnowledgeBase().toString());

		// RULES
		b.append("RULEBASE:\n");
		for (Object rb : getRuleBase()) {
			FuzzySystemRuleBase rbi = null;
			if (((JAXBElement) rb).getValue() instanceof FuzzySystemRuleBase) {
				rbi = (FuzzySystemRuleBase) ((JAXBElement) rb).getValue();
				if (rbi != null)
					b.append(rbi.toString());
			}
		}
		return b.toString();
	}

}
