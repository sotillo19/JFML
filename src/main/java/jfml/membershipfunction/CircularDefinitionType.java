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
package jfml.membershipfunction;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import jfml.knowledgebase.variable.KnowledgeBaseVariable;
import jfml.operator.AndLogicalType;
import jfml.operator.LogicalType;
import jfml.operator.OrLogicalType;
import jfml.term.CircularTermType;
import jfml.term.Term;


/**
 * <p>Java class for circularDefinitionType complex type.
 * 
 * <pre>
 * &lt;complexType name="circularDefinitionType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="and" type="{http://www.ieee1855.org}andLogicalType"/&gt;
 *         &lt;element name="or" type="{http://www.ieee1855.org}orLogicalType"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * @author sotillo19
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "circularDefinitionType", propOrder = {
    "and",
    "or"
})
public class CircularDefinitionType extends MembershipFunction{

    protected AndLogicalType and;
    protected OrLogicalType or;
    
    @XmlTransient
    private KnowledgeBaseVariable var;
    
    
    /**
     * Default constructor
     */
    public CircularDefinitionType(){
    	super();
    }
    
    
    /**
     * 
     * @param and
     * @param or
     * @param var
     */
    public CircularDefinitionType(AndLogicalType and, OrLogicalType or, KnowledgeBaseVariable var){
    	super();
    	this.and=and;
    	this.or=or;
    	this.var=var;
    }
    
    public CircularDefinitionType(LogicalType log, KnowledgeBaseVariable var){
    	super();
    	if(log instanceof AndLogicalType)
    		this.and=(AndLogicalType) log;
    	else if(log instanceof OrLogicalType)
    		this.or=(OrLogicalType) log;
    	
    	this.var=var;
    }

    /**
     * Gets the value of the property and.
     * 
     * @return
     *     possible object is
     *     {@link AndLogicalType }
     *     
     */
    public AndLogicalType getAnd() {
        return and;
    }

    /**
     * Sets the value of the property and.
     * 
     * @param value
     *     allowed object is
     *     {@link AndLogicalType }
     *     
     */
    public void setAnd(AndLogicalType and) {
        this.and = and;
    }

    /**
     * Gets the value of the property or.
     * 
     * @return
     *     possible object is
     *     {@link OrLogicalType }
     *     
     */
    public OrLogicalType getOr() {
        return or;
    }

    /**
     * Sets the value of the property or.
     * 
     * @param value
     *     allowed object is
     *     {@link OrLogicalType }
     *     
     */
    public void setOr(OrLogicalType or) {
        this.or = or;
    }

    /**
     * Copy method
     * @return a new instance of CircularDefinitionType
     */
	public CircularDefinitionType copy() {
		return new CircularDefinitionType(getAnd(), getOr(), getVariable());
	}

	/**
	 * Get the FuzzyVariableType which contains the terms
	 * @return
	 */
	public KnowledgeBaseVariable getVariable() {
		return var;
	}
	
	/**
	 * Set the FuzzyVariableType which contains the terms
	 * @param var
	 */
	public void setVariable(KnowledgeBaseVariable var){
		this.var=var;
	}

	@Override
	public float getMembershipDegree(float x) {
		if(getAnd()!=null)
			return evaluateCircular(x,getAnd());
		else if(getOr()!=null)
			return evaluateCircular(x,getOr());
		
		return Float.NaN;
	}

	private float evaluateCircular(float x, LogicalType log) {
		float degree1, degree2;
		
		if(var == null)
			throw new RuntimeException("A variable with the terms is needed");
		
		Object o1 = log.getContent(0);
		Object o2 = log.getContent(1);

		if(o1 instanceof CircularTermType){
			Term t1 = var.getTerm(((CircularTermType) o1).getValue());
			if(t1==null)
				throw new RuntimeException("The term " + ((CircularTermType) o1).getValue() + "is not found in the variable "+var.getName());
			degree1 = t1.getMembershipValue(x);
		}
		else
			degree1 = evaluateCircular(x,(LogicalType) o1);
		
		if(o2 instanceof CircularTermType){
			Term t2 = var.getTerm(((CircularTermType) o2).getValue());
			if(t2==null)
				throw new RuntimeException("The term " + ((CircularTermType) o2).getValue() + "is not found in the variable "+var.getName());
			degree2 = t2.getMembershipValue(x);
		}
		else
			degree2 = evaluateCircular(x,(LogicalType) o2);
		
		return log.operate(degree1,degree2);
	}

	@Override
	public String toString() {
		String s = "";
		if(getAnd()!=null)
			s += printCircular(getAnd());
		else if(getOr()!=null)
			s += printCircular(getOr());
		return s;
	}

	private String printCircular(LogicalType log) {
		String s="";
		String s1, s2;
		
		if(log instanceof AndLogicalType)
			s = " AND ";
		else
			s = " OR ";
		
		Object o1 = log.getContent(0);
		Object o2 = log.getContent(1);

		if(o1 instanceof CircularTermType){
			Term t1 = var.getTerm(((CircularTermType) o1).getValue());
			s1 = t1.getName();
		}
		else
			s1 = printCircular((LogicalType) o1);
		
		if(o2 instanceof CircularTermType){
			Term t2 = var.getTerm(((CircularTermType) o2).getValue());
			s2 = t2.getName();
		}
		else
			s2 = printCircular((LogicalType) o2);
		
		return s1 + s + s2;
	}

	@Override
	public ArrayList<Float> getXValuesDefuzzifier() {
		// TODO Auto-generated method stub
		return null;
	}

}
