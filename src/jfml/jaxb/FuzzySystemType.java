/**************************************************************
      GNU GENERAL PUBLIC LICENSE - Version 3 

  JFML: A Java Library for the IEEE Standard for Fuzzy Markup Language
  (IEEE Std 1855-2016). Copyright (C) 2017

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

     You should have received a copy of the GNU General Public License
     along with this program.  If not, see <http://www.gnu.org/licenses/>.

  Contact information: <http://www.uco.es/JFML>

  J.M. Soto-Hidalgo & Jose M. Alonso & Jesus Alcala-Fdez
 **************************************************************/
package jfml.jaxb;

import java.util.ArrayList;
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
 * &lt;complexType name="fuzzySystemType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="knowledgeBase" type="{http://www.ieee1855.org}knowledgeBaseType"/&gt;
 *         &lt;choice maxOccurs="unbounded"&gt;
 *           &lt;element name="mamdaniRuleBase" type="{http://www.ieee1855.org}ruleBaseType"/&gt;
 *           &lt;element name="tsukamotoRuleBase" type="{http://www.ieee1855.org}ruleBaseType"/&gt;
 *           &lt;element name="tskRuleBase" type="{http://www.ieee1855.org}tskRuleBaseType"/&gt;
 *           &lt;element name="anYaRuleBase" type="{http://www.ieee1855.org}anYaRuleBaseType"/&gt;
 *           &lt;any processContents='lax' namespace='##other' minOccurs="0"/&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" /&gt;
 *       &lt;attribute name="networkAddress" type="{http://www.ieee1855.org}networkAddressType" default="127.0.0.1" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * @author sotillo19
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
	 * @param name the fuzzy System name
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
	 * @return a list of RuleBase
	 */
	public List<Object> getRuleBase() {
		if (ruleBase == null) {
			ruleBase = new ArrayList<Object>();
		}
		return this.ruleBase;
	}

	/**
	 * Adds a new RuleBase to the fuzzySystem
	 * 
	 * @param r
	 *            allowed object is {@link FuzzySystemRuleBase }
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
		KnowledgeBaseVariable v = getKnowledgeBase().getVariable(var);
		if (v != null)
			v.setValue(value);
	}

	/**
	 * Return a variable instance identifies by its name
	 * 
	 * @param name
	 *            allowed object is {@link String }
	 * @return allowed object is {@link KnowledgeBaseVariable }
	 */
	public KnowledgeBaseVariable getVariable(String name) {
		return getKnowledgeBase().getVariable(name);
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
	protected void reset(KnowledgeBaseType knowledgeBase, List<Object> ruleBase) {
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
