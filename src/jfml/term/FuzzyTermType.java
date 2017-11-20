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
package jfml.term;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import jfml.membershipfunction.*;
import jfml.parameter.FourParamType;
import jfml.parameter.OneParamType;
import jfml.parameter.ThreeParamType;
import jfml.parameter.TwoParamType;


/**
 * <p>Java class for fuzzyTermType complex type.
 * 
 * <pre>
 * &lt;complexType name="fuzzyTermType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="rightLinearShape" type="{http://www.ieee1855.org}twoParamType"/&gt;
 *         &lt;element name="leftLinearShape" type="{http://www.ieee1855.org}twoParamType"/&gt;
 *         &lt;element name="piShape" type="{http://www.ieee1855.org}twoParamType"/&gt;
 *         &lt;element name="triangularShape" type="{http://www.ieee1855.org}threeParamType"/&gt;
 *         &lt;element name="gaussianShape" type="{http://www.ieee1855.org}twoParamType"/&gt;
 *         &lt;element name="rightGaussianShape" type="{http://www.ieee1855.org}twoParamType"/&gt;
 *         &lt;element name="leftGaussianShape" type="{http://www.ieee1855.org}twoParamType"/&gt;
 *         &lt;element name="trapezoidShape" type="{http://www.ieee1855.org}fourParamType"/&gt;
 *         &lt;element name="singletonShape" type="{http://www.ieee1855.org}oneParamType"/&gt;
 *         &lt;element name="rectangularShape" type="{http://www.ieee1855.org}twoParamType"/&gt;
 *         &lt;element name="zShape" type="{http://www.ieee1855.org}twoParamType"/&gt;
 *         &lt;element name="sShape" type="{http://www.ieee1855.org}twoParamType"/&gt;
 *         &lt;element name="pointSetShape" type="{http://www.ieee1855.org}pointSetShapeType"/&gt;
 *         &lt;element name="circularDefinition" type="{http://www.ieee1855.org}circularDefinitionType"/&gt;
 *         &lt;element name="customShape" type="{http://www.ieee1855.org}customShapeType"/&gt;
 *       &lt;/choice&gt;
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" /&gt;
 *       &lt;attribute name="complement" default="false"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;pattern value="true|false|TRUE|FALSE|True|False"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * @author sotillo19
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fuzzyTermType", propOrder = {
    "rightLinearShape",
    "leftLinearShape",
    "piShape",
    "triangularShape",
    "gaussianShape",
    "rightGaussianShape",
    "leftGaussianShape",
    "trapezoidShape",
    "singletonShape",
    "rectangularShape",
    "zShape",
    "sShape",
    "pointSetShape",
    "circularDefinition",
    "customShape"
})
public class FuzzyTermType extends FuzzyTerm{
	
    protected TwoParamType rightLinearShape;
    protected TwoParamType leftLinearShape;
    protected TwoParamType piShape;
    protected ThreeParamType triangularShape;
    protected TwoParamType gaussianShape;
    protected TwoParamType rightGaussianShape;
    protected TwoParamType leftGaussianShape;
    protected FourParamType trapezoidShape;
    protected OneParamType singletonShape;
    protected TwoParamType rectangularShape;
    protected TwoParamType zShape;
    protected TwoParamType sShape;
    protected PointSetShapeType pointSetShape;
    protected CircularDefinitionType circularDefinition;
    protected CustomShapeType customShape;
    @XmlAttribute(name = "name", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String name;
    @XmlAttribute(name = "complement")
    protected String complement;
    
    /**
     * Default constructor
     */
    public FuzzyTermType(){
    	
    }
    
    /**
     * Constructor using the name, the type of fuzzy term and an array of parameters
     * @param name the name of the fuzzy term
     * @param type the type (see static variables in {@link FuzzyTerm})
     * @param param an array of parameters
     */
    public FuzzyTermType(String name, int type, float[] param) {
    	super();
    	this.setName(name);
    	this.setComplement("false");
    	this.type=type;
    	
    	int numParam=0;
    	if(param!=null)
    		numParam=param.length;
    	
    	switch (type) {
		case FuzzyTerm.TYPE_rightLinearShape:
			if(numParam==2){
				TwoParamType two = new TwoParamType();
				two.setParam1(param[0]);
				two.setParam2(param[1]);
				setRightLinearShape(two);
			}
			break;
		case FuzzyTerm.TYPE_leftLinearShape:
			if(numParam==2){
				TwoParamType two = new TwoParamType();
				two.setParam1(param[0]);
				two.setParam2(param[1]);
				setLeftLinearShape(two);
			}
			break;	
		case FuzzyTerm.TYPE_piShape:
			if(numParam==2){
				TwoParamType two = new TwoParamType();
				two.setParam1(param[0]);
				two.setParam2(param[1]);
				setPiShape(two);
			}
			break;
		case FuzzyTerm.TYPE_triangularShape:
			if(numParam==3){
				ThreeParamType three = new ThreeParamType();
				three.setParam1(param[0]);
				three.setParam2(param[1]);
				three.setParam3(param[2]);
				setTriangularShape(three);
			}
			break;	
		case FuzzyTerm.TYPE_gaussianShape:
			if(numParam==2){
				TwoParamType two = new TwoParamType();
				two.setParam1(param[0]);
				two.setParam2(param[1]);
				setGaussianShape(two);
			}
			break;
		case FuzzyTerm.TYPE_rightGaussianShape:
			if(numParam==2){
				TwoParamType two = new TwoParamType();
				two.setParam1(param[0]);
				two.setParam2(param[1]);
				setRightGaussianShape(two);
			}
			break;	
		case FuzzyTerm.TYPE_leftGaussianShape:
			if(numParam==2){
				TwoParamType two = new TwoParamType();
				two.setParam1(param[0]);
				two.setParam2(param[1]);
				setLeftGaussianShape(two);
			}
			break;
		case FuzzyTerm.TYPE_trapezoidShape:
			if(numParam==4){
				FourParamType four = new FourParamType();
				four.setParam1(param[0]);
				four.setParam2(param[1]);
				four.setParam3(param[2]);
				four.setParam4(param[3]);
				setTrapezoidShape(four);
			}
			break;
		case FuzzyTerm.TYPE_singletonShape:
			if(numParam==1){
				OneParamType one = new OneParamType();
				one.setParam1(param[0]);
				setSingletonShape(one);
			}
			break;	
		case FuzzyTerm.TYPE_rectangularShape:
			if(numParam==2){
				TwoParamType two = new TwoParamType();
				two.setParam1(param[0]);
				two.setParam2(param[1]);
				setRectangularShape(two);
			}
			break;
		case FuzzyTerm.TYPE_zShape:
			if(numParam==2){
				TwoParamType two = new TwoParamType();
				two.setParam1(param[0]);
				two.setParam2(param[1]);
				setZShape(two);
			}
			break;	
		case FuzzyTerm.TYPE_sShape:
			if(numParam==2){
				TwoParamType two = new TwoParamType();
				two.setParam1(param[0]);
				two.setParam2(param[1]);
				setSShape(two);
			}
			break;				
			
		default:
			break;
		}
	}
    
    /**
     * Constructor of a PointSet term using the name and a list of PointType
     * @param name the name of the Fuzzy Term
     * @param type type must be the static value FuzzyTerm.TYPE_pointSetShape
     * @param points the list of PointType
     */
    public FuzzyTermType(String name, int type, List<PointType> points) {
    	super();
    	this.setName(name);
    	this.setComplement("false");
    	this.type=type;
    	
    	switch (type) {
		case FuzzyTerm.TYPE_pointSetShape:
			setPointSetShape(new PointSetShapeType(points));
			default:
			break;
    	}
    }
    
    /**
     * Constructor using the name and an instance of PointSetShapeType
     * @param name the name of the fuzzy term
     * @param p an instance of PointSetShapeType
     */
    public FuzzyTermType(String name, PointSetShapeType p) {
    	super();
    	setName(name);
    	setComplement("false");
    	this.type=FuzzyTerm.TYPE_pointSetShape;
    	setPointSetShape(p);
    }
    
    /**
     * Constructor using the name and an instance of CircularDefinitionType
     * @param name the name of the fuzzy term
     * @param c an instance of CircularDefinitionType
     */
    public FuzzyTermType(String name, CircularDefinitionType c) {
    	super();
    	setName(name);
    	setComplement("false");
    	this.type=FuzzyTerm.TYPE_circularDefinition;
    	setCircularDefinition(c);
    }
    
    /**
     * Constructor using the name and an instance of CustomShapeType
     * @param name the name of the fuzzy term
     * @param c an instance of CustomShapeType
     */
    public FuzzyTermType(String name, CustomShapeType c) {
    	super();
    	setName(name);
    	setComplement("false");
    	this.type=FuzzyTerm.TYPE_customShape;
    	setCustomShape(c);
    }
    
    /**
     * Constructor using the name, the complement and a CircularDefinitionType
     * @param name the name of the fuzzy term
     * @param complement the complement (true or false)
     * @param circular an instance of CircularDefinitionType
     */
    public FuzzyTermType(String name, String complement, CircularDefinitionType circular) {
		this(name, circular);
		this.setComplement(complement);
	}

    /**
     * Constructor using the name, the complement and a PointSetShapeType
     * @param name the name of the fuzzy term
     * @param complement the complement (true or false)
     * @param circular an instance of PointSetShapeType
     */
	public FuzzyTermType(String name, String complement, PointSetShapeType point) {
		this(name, point);
		this.setComplement(complement);
	}

	/**
	 * Gets an array of floats with the parameters of this fuzzy term
	 * @return an array of floats with the parameters of this fuzzy term
	 */
	public float[] getParam(){
    	float[] param = null;
    	TwoParamType two;
    	ThreeParamType three;
    	FourParamType four;

    	switch (type) {
		case FuzzyTerm.TYPE_rightLinearShape:
			two =getRightLinearShape();
			if(two!=null){
				param = new float[2];
				param[0] = two.getParam1();
				param[1] = two.getParam2();
			}
			break;
		case FuzzyTerm.TYPE_leftLinearShape:
			two =getLeftLinearShape();
			if(two!=null){
				param = new float[2];
				param[0] = two.getParam1();
				param[1] = two.getParam2();
			}
			break;
		case FuzzyTerm.TYPE_piShape:
			two =getPiShape();
			if(two!=null){
				param = new float[2];
				param[0] = two.getParam1();
				param[1] = two.getParam2();
			}
			break;
		case FuzzyTerm.TYPE_triangularShape:
			three =getTriangularShape();
			if(three!=null){
				param = new float[3];
				param[0] = three.getParam1();
				param[1] = three.getParam2();
				param[2] = three.getParam3();
			}
			break;	
		case FuzzyTerm.TYPE_gaussianShape:
			two =getGaussianShape();
			if(two!=null){
				param = new float[2];
				param[0] = two.getParam1();
				param[1] = two.getParam2();
			}
			break;
		case FuzzyTerm.TYPE_rightGaussianShape:
			two =getRightGaussianShape();
			if(two!=null){
				param = new float[2];
				param[0] = two.getParam1();
				param[1] = two.getParam2();
			}
			break;
		case FuzzyTerm.TYPE_leftGaussianShape:
			two =getLeftGaussianShape();
			if(two!=null){
				param = new float[2];
				param[0] = two.getParam1();
				param[1] = two.getParam2();
			}
			break;
		case FuzzyTerm.TYPE_trapezoidShape:
			four =getTrapezoidShape();
			if(four!=null){
				param = new float[4];
				param[0] = four.getParam1();
				param[1] = four.getParam2();
				param[2] = four.getParam3();
				param[3] = four.getParam4();
			}
			break;
		case FuzzyTerm.TYPE_singletonShape:
			OneParamType one = getSingletonShape();
			if(one!=null){
				param = new float[1];
				param[0] = one.getParam1();
			}
			break;	
		case FuzzyTerm.TYPE_rectangularShape:
			two =getRectangularShape();
			if(two!=null){
				param = new float[2];
				param[0] = two.getParam1();
				param[1] = two.getParam2();
			}
			break;
		case FuzzyTerm.TYPE_zShape:
			two =getZShape();
			if(two!=null){
				param = new float[2];
				param[0] = two.getParam1();
				param[1] = two.getParam2();
			}
			break;
		case FuzzyTerm.TYPE_sShape:
			two =getSShape();
			if(two!=null){
				param = new float[2];
				param[0] = two.getParam1();
				param[1] = two.getParam2();
			}
			break;			
			
		default:
			break;
		}
    	
    	return param;
    }

	/**
     * Gets the value of the property rightLinearShape.
     * 
     * @return
     *     possible object is
     *     {@link TwoParamType }
     *     
     */
    public TwoParamType getRightLinearShape() {
        return rightLinearShape;
    }

    /**
     * Sets the value of the property rightLinearShape.
     * 
     * @param value
     *     allowed object is
     *     {@link TwoParamType }
     *     
     */
    public void setRightLinearShape(TwoParamType value) {
        this.rightLinearShape = value;
        this.type=FuzzyTerm.TYPE_rightLinearShape;
        this.mf = new RightLinearMembershipFunction(value);
    }

    /**
     * Gets the value of the property leftLinearShape.
     * 
     * @return
     *     possible object is
     *     {@link TwoParamType }
     *     
     */
    public TwoParamType getLeftLinearShape() {
        return leftLinearShape;
    }

    /**
     * Sets the value of the property leftLinearShape.
     * 
     * @param value
     *     allowed object is
     *     {@link TwoParamType }
     *     
     */
    public void setLeftLinearShape(TwoParamType value) {
        this.leftLinearShape = value;
        this.type=FuzzyTerm.TYPE_leftLinearShape;
        this.mf = new LeftLinearMembershipFunction(value);
    }

    /**
     * Gets the value of the property piShape.
     * 
     * @return
     *     possible object is
     *     {@link TwoParamType }
     *     
     */
    public TwoParamType getPiShape() {
        return piShape;
    }

    /**
     * Sets the value of the property piShape.
     * 
     * @param value
     *     allowed object is
     *     {@link TwoParamType }
     *     
     */
    public void setPiShape(TwoParamType value) {
        this.piShape = value;
        this.type=FuzzyTerm.TYPE_piShape;
        this.mf = new PiShapedMembershipFunction(value);
    }

    /**
     * Gets the value of the property triangularShape.
     * 
     * @return
     *     possible object is
     *     {@link ThreeParamType }
     *     
     */
    public ThreeParamType getTriangularShape() {
        return triangularShape;
    }

    /**
     * Sets the value of the property triangularShape.
     * 
     * @param value
     *     allowed object is
     *     {@link ThreeParamType }
     *     
     */
    public void setTriangularShape(ThreeParamType value) {
        this.triangularShape = value;
        this.type=FuzzyTerm.TYPE_triangularShape;
        this.mf = new TriangularMembershipFunction(value);
    }   

    /**
     * Gets the value of the property gaussianShape.
     * 
     * @return
     *     possible object is
     *     {@link TwoParamType }
     *     
     */
    public TwoParamType getGaussianShape() {
        return gaussianShape;
    }

    /**
     * Sets the value of the property gaussianShape.
     * 
     * @param value
     *     allowed object is
     *     {@link TwoParamType }
     *     
     */
    public void setGaussianShape(TwoParamType value) {
        this.gaussianShape = value;
        this.type=FuzzyTerm.TYPE_gaussianShape;
        this.mf = new GaussianMembershipFunction(value);
    }

    /**
     * Gets the value of the property rightGaussianShape.
     * 
     * @return
     *     possible object is
     *     {@link TwoParamType }
     *     
     */
    public TwoParamType getRightGaussianShape() {
        return rightGaussianShape;
    }

    /**
     * Sets the value of the property rightGaussianShape.
     * 
     * @param value
     *     allowed object is
     *     {@link TwoParamType }
     *     
     */
    public void setRightGaussianShape(TwoParamType value) {
        this.rightGaussianShape = value;
        this.type=FuzzyTerm.TYPE_rightGaussianShape;
        this.mf = new RightGaussianMembershipFunction(value);
    }

    /**
     * Gets the value of the property leftGaussianShape.
     * 
     * @return
     *     possible object is
     *     {@link TwoParamType }
     *     
     */
    public TwoParamType getLeftGaussianShape() {
        return leftGaussianShape;
    }

    /**
     * Sets the value of the property leftGaussianShape.
     * 
     * @param value
     *     allowed object is
     *     {@link TwoParamType }
     *     
     */
    public void setLeftGaussianShape(TwoParamType value) {
        this.leftGaussianShape = value;
        this.type=FuzzyTerm.TYPE_leftGaussianShape;
        this.mf = new LeftGaussianMembershipFunction(value);
    }

    /**
     * Gets the value of the property trapezoidShape.
     * 
     * @return
     *     possible object is
     *     {@link FourParamType }
     *     
     */
    public FourParamType getTrapezoidShape() {
        return trapezoidShape;
    }

    /**
     * Sets the value of the property trapezoidShape.
     * 
     * @param value
     *     allowed object is
     *     {@link FourParamType }
     *     
     */
    public void setTrapezoidShape(FourParamType value) {
        this.trapezoidShape = value;
        this.type=FuzzyTerm.TYPE_trapezoidShape;
        this.mf = new TrapezoidMembershipFunction(value);
    }

    /**
     * Gets the value of the property singletonShape.
     * 
     * @return
     *     possible object is
     *     {@link OneParamType }
     *     
     */
    public OneParamType getSingletonShape() {
        return singletonShape;
    }

    /**
     * Sets the value of the property singletonShape.
     * 
     * @param value
     *     allowed object is
     *     {@link OneParamType }
     *     
     */
    public void setSingletonShape(OneParamType value) {
        this.singletonShape = value;
        this.type=FuzzyTerm.TYPE_singletonShape;
        this.mf = new SingletonMembershipFunction(value);
    }

    /**
     * Gets the value of the property rectangularShape.
     * 
     * @return
     *     possible object is
     *     {@link TwoParamType }
     *     
     */
    public TwoParamType getRectangularShape() {
        return rectangularShape;
    }

    /**
     * Sets the value of the property rectangularShape.
     * 
     * @param value
     *     allowed object is
     *     {@link TwoParamType }
     *     
     */
    public void setRectangularShape(TwoParamType value) {
        this.rectangularShape = value;
        this.type=FuzzyTerm.TYPE_rectangularShape;
        this.mf = new RectangularMembershipFunction(value);
    }

    /**
     * Gets the value of the property zShape.
     * 
     * @return
     *     possible object is
     *     {@link TwoParamType }
     *     
     */
    public TwoParamType getZShape() {
        return zShape;
    }

    /**
     * Sets the value of the property zShape.
     * 
     * @param value
     *     allowed object is
     *     {@link TwoParamType }
     *     
     */
    public void setZShape(TwoParamType value) {
        this.zShape = value;
        this.type=FuzzyTerm.TYPE_zShape;
        this.mf = new ZShapeMembershipFunction(value);
    }

    /**
     * Gets the value of the property sShape.
     * 
     * @return
     *     possible object is
     *     {@link TwoParamType }
     *     
     */
    public TwoParamType getSShape() {
        return sShape;
    }

    /**
     * Sets the value of the property sShape.
     * 
     * @param value
     *     allowed object is
     *     {@link TwoParamType }
     *     
     */
    public void setSShape(TwoParamType value) {
        this.sShape = value;
        this.type=FuzzyTerm.TYPE_sShape;
        this.mf = new SShapeMembershipFunction(value);
    }

    /**
     * Gets the value of the property pointSetShape.
     * 
     * @return
     *     possible object is
     *     {@link PointSetShapeType }
     *     
     */
    public PointSetShapeType getPointSetShape() {
        return pointSetShape;
    }

    /**
     * Sets the value of the property pointSetShape.
     * 
     * @param value
     *     allowed object is
     *     {@link PointSetShapeType }
     *     
     */
    public void setPointSetShape(PointSetShapeType value) {
        this.pointSetShape = value;
        this.type=FuzzyTerm.TYPE_pointSetShape;
        this.mf = value;
    }
    

    /**
     * Gets the value of the property circularDefinition.
     * 
     * @return
     *     possible object is
     *     {@link CircularDefinitionType }
     *     
     */
    public CircularDefinitionType getCircularDefinition() {
        return circularDefinition;
    }

    /**
     * Sets the value of the property circularDefinition.
     * 
     * @param value
     *     allowed object is
     *     {@link CircularDefinitionType }
     *     
     */
    public void setCircularDefinition(CircularDefinitionType value) {
        this.circularDefinition = value;
        this.type=FuzzyTerm.TYPE_circularDefinition;
        this.mf = new CircularMembershipFunction(value);
    }

    /**
     * Gets the value of the property customShape.
     * 
     * @return
     *     possible object is
     *     {@link CustomShapeType }
     *     
     */
    public CustomShapeType getCustomShape() {
        return customShape;
    }

    /**
     * Sets the value of the property customShape.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomShapeType }
     *     
     */
    public void setCustomShape(CustomShapeType value) {
        this.customShape = value;
        this.type=FuzzyTerm.TYPE_customShape;
        this.mf = new CustomMembershipFunction(value);
    }

    /**
     * Gets the value of the property name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the property name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the property complement.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComplement() {
        if (complement == null) {
            return "false";
        } else {
            return complement;
        }
    }

    /**
     * Sets the value of the property complement.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComplement(String value) {
        this.complement = value;
    }

    @Override
    public String toString(){
    	String b = name;
    
    	if(mf!=null){
    		if(getComplement().toLowerCase().equals("true"))
        		b += " - NOT "+ mf.toString();
    		else    			
    			b += " -  "+ mf.toString();
    	}
		
    	return b;
    }


	@Override
	public FuzzyTerm copy() {
		FuzzyTerm t = null;
		if(this.type == TYPE_circularDefinition)
			t = new FuzzyTermType(new String(getName()), getCircularDefinition().copy());
		else if(this.type == TYPE_pointSetShape)
			t = new FuzzyTermType(new String(getName()), getPointSetShape().copy());
		else if(this.type == TYPE_customShape)
			t = new FuzzyTermType(new String(getName()), getCustomShape().copy());
		else
			t = new FuzzyTermType(new String(getName()), this.type, this.getParam());
		
		t.initializeMembershipFunction(leftDomain, rightDomain);
		((FuzzyTermType) t).setComplement(new String(getComplement()));
		
		return t;
	}
}
