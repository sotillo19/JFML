package jfml.term;

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

import jfml.knowledgebase.variable.FuzzyVariable;
import jfml.knowledgebase.variable.KnowledgeBaseVariable;


/**
 * <p>Java class for tskTermType complex type.
 * 
 * <pre>
 * &lt;complexType name="tskTermType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tskValue" type="{http://www.w3.org/2001/XMLSchema}float" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="order" use="required" type="{http://www.ieee1855.org}tskOrderType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tskTermType", propOrder = {
    "tskValue"
})
public class TskTermType extends TskTerm{

    @XmlElement(type = Float.class)
    protected List<Float> tskValue;
    @XmlAttribute(name = "name", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String name;
    @XmlAttribute(name = "order", required = true)
    protected int order;
    
    public TskTermType(){
    	
    }

    public TskTermType(String name, int order, float[] coeff) {
		super();
		this.name=name;
		this.order=order;
		this.tskValue = new ArrayList<>();
		for(int i=0;i<coeff.length;i++)
			tskValue.add(new Float(coeff[i]));
	}

	/**
     * Gets the value of the tskValue property. Coefficients
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Float }
     * 
     * 
     */
    public List<Float> getTskValue() {
        if (tskValue == null) {
            tskValue = new ArrayList<Float>();
        }
        return this.tskValue;
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
     * Gets the value of the property order.
     * 
     */
    public int getOrder() {
        return order;
    }

    /**
     * Sets the value of the property order.
     * 
     */
    public void setOrder(int value) {
        this.order = value;
    }
    
    @Override
    public String toString(){
    	String b = name;
    	
    	List<Float> coeff = getTskValue();
    	
    	b += " - z = "+coeff.get(0).toString() + " + ";

    	for(int i=1;i<coeff.size();i++)
    		b += coeff.get(i).toString() + "v"+i + " + ";
		
    	return b.substring(0, b.length()-3);
    }

	@Override
	public void evaluateTskTerm(List<FuzzyVariable> list) {
		if(getOrder() == 0)
			setEvaluation(getTskValue().get(0));
		else if(getOrder() == 1)
			setEvaluation(evaluateLinearFunction(getTskValue(), list));
	}

	private float evaluateLinearFunction(List<Float> coefficients, List<FuzzyVariable> kbv) {
		// initialize coefficient "c"
		float res = coefficients.get(0);
		
		//calculate a*v1 + b*v2 + ...
		for(int i=1;i<coefficients.size();i++)
			res += coefficients.get(i)* (kbv.get(i-1).getValue());
		
		return res;
	}

	public TskTermType copy() {
		float[] coeff = new float[getTskValue().size()];
		for(int i=0;i<coeff.length;i++)
			coeff[i] = getTskValue().get(i);
			
		return new TskTermType(new String(name),order,coeff);
	}

}
