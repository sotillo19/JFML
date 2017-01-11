package jfml.parameter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for the oneParamType complex type.
 * 
 * <pre>
 * &lt;complexType name="oneParamType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="param1" use="required" type="{http://www.w3.org/2001/XMLSchema}float" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * @author sotillo19
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "oneParamType")
public class OneParamType extends Parameter {

    @XmlAttribute(name = "param1", required = true)
    protected float param1;

    /**
     * Gets the value of the attribute param1.
     * @return the value of the attribute param1.
     */
    public float getParam1() {
        return param1;
    }

    /**
     * Sets the value for the attribute param1.
     * @param value value
     */
    public void setParam1(float value) {
        this.param1 = value;
    }

	@Override
	public int getParameterLength() {
		return 1;
	}

	@Override
	public float getParameter(int i) {
		if(i>0 && i<=getParameterLength())
			return getParam1();
		else
			return -1;
	}

}
