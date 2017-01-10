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

import jfml.membershipfunction.CustomMembershipFunction;
import jfml.membershipfunction.CustomShapeType;
import jfml.membershipfunction.LeftGaussianMembershipFunction;
import jfml.membershipfunction.LeftLinearMembershipFunction;
import jfml.membershipfunction.PointSetMonotonicShapeType;
import jfml.membershipfunction.PointType;
import jfml.membershipfunction.RightGaussianMembershipFunction;
import jfml.membershipfunction.RightLinearMembershipFunction;
import jfml.membershipfunction.SShapeMembershipFunction;
import jfml.membershipfunction.ZShapeMembershipFunction;
import jfml.parameter.TwoParamType;


/**
 * <p>Java class for tsukamotoTermType complex type.
 * 
 * <pre>
 * &lt;complexType name="tsukamotoTermType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="rightLinearShape" type="{http://www.ieee1855.org}twoParamType"/>
 *         &lt;element name="leftLinearShape" type="{http://www.ieee1855.org}twoParamType"/>
 *         &lt;element name="rightGaussianShape" type="{http://www.ieee1855.org}twoParamType"/>
 *         &lt;element name="leftGaussianShape" type="{http://www.ieee1855.org}twoParamType"/>
 *         &lt;element name="zShape" type="{http://www.ieee1855.org}twoParamType"/>
 *         &lt;element name="sShape" type="{http://www.ieee1855.org}twoParamType"/>
 *         &lt;element name="pointSetMonotonicShape" type="{http://www.ieee1855.org}pointSetMonotonicShapeType"/>
 *         &lt;element name="customMonotonicShape" type="{http://www.ieee1855.org}customShapeType"/>
 *       &lt;/choice>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="complement" default="false">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;pattern value="true|false|TRUE|FALSE|True|False"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * @author sotillo19
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tsukamotoTermType", propOrder = {
    "rightLinearShape",
    "leftLinearShape",
    "rightGaussianShape",
    "leftGaussianShape",
    "zShape",
    "sShape",
    "pointSetMonotonicShape",
    "customMonotonicShape"
})
public class TsukamotoTermType extends FuzzyTerm{

    protected TwoParamType rightLinearShape;
    protected TwoParamType leftLinearShape;
    protected TwoParamType rightGaussianShape;
    protected TwoParamType leftGaussianShape;
    protected TwoParamType zShape;
    protected TwoParamType sShape;
    protected PointSetMonotonicShapeType pointSetMonotonicShape;
    protected CustomShapeType customMonotonicShape;
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
    public TsukamotoTermType(){
    	super();
    }
    
    
    /**
     * Constructor using the name, the type of fuzzy term and an array of parameters
     * @param name the name of the fuzzy term
     * @param type the type (see static variables in {@link FuzzyTerm})
     * @param param an array of parameters
     */
    public TsukamotoTermType(String name, int type, float[] param) {
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
    public TsukamotoTermType(String name, int type, List<PointType> param) {
    	super();
    	this.setName(name);
    	this.type=type;
    	
    	switch (type) {
		case FuzzyTerm.TYPE_pointSetMonotonicShape:
			PointSetMonotonicShapeType psm = new PointSetMonotonicShapeType();
			psm.setPoints(param);
			setPointSetMonotonicShape(psm);
			break;
		case FuzzyTerm.TYPE_customMonotonicShape:
			CustomShapeType cs = new CustomShapeType();
			setCustomMonotonicShape(cs);
			break;		
			
		default:
			break;
		}
	}
    
    /**
     * Constructor using the name and an instance of PointSetMonotonicShapeType
     * @param name the name of the Tsukamoto term
     * @param psm an instance of PointSetMonotonicShapeType
     */
    public TsukamotoTermType(String name, PointSetMonotonicShapeType psm) {
    	super();
    	this.setName(name);
    	this.type=FuzzyTerm.TYPE_pointSetMonotonicShape;
    	setPointSetMonotonicShape(psm);
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
     * Gets the value of the property pointSetMonotonicShape.
     * 
     * @return
     *     possible object is
     *     {@link PointSetMonotonicShapeType }
     *     
     */
    public PointSetMonotonicShapeType getPointSetMonotonicShape() {
        return pointSetMonotonicShape;
    }

    /**
     * Sets the value of the property pointSetMonotonicShape.
     * 
     * @param value
     *     allowed object is
     *     {@link PointSetMonotonicShapeType }
     *     
     */
    public void setPointSetMonotonicShape(PointSetMonotonicShapeType value) {
        this.pointSetMonotonicShape = value;
        //TODO create monotonic 
        //this.type=FuzzyTerm.TYPE_MONOTONIC;
        //this.mf = new MonotonicMembershipFunction(value);
    }

    /**
     * Gets the value of the property customMonotonicShape.
     * 
     * @return
     *     possible object is
     *     {@link CustomShapeType }
     *     
     */
    public CustomShapeType getCustomMonotonicShape() {
        return customMonotonicShape;
    }

    /**
     * Sets the value of the property customMonotonicShape.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomShapeType }
     *     
     */
    public void setCustomMonotonicShape(CustomShapeType value) {
        this.customMonotonicShape = value;
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
		FuzzyTerm t;
		
		if(this.type == TYPE_pointSetMonotonicShape)
			t = new TsukamotoTermType(new String(getName()), this.type, this.getPointSetMonotonicShape().getPoints());
		else
			t = new TsukamotoTermType(new String(getName()), this.type, this.getParam());
		
		t.initializeMembershipFunction(leftDomain, rightDomain);
		((TsukamotoTermType) t).setComplement(new String(getComplement()));
		
		return t;
	}
	
	/**
	 * Gets an array of floats with the parameters of this fuzzy term
	 * @return an array of floats with the parameters of this fuzzy term
	 */
	public float[] getParam(){
    	float[] param = null;
    	TwoParamType two;
    	
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

}
