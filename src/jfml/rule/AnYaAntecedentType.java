package jfml.rule;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anYaAntecedentType complex type.
 * 
 * <pre>
 * &lt;complexType name="anYaAntecedentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dataCloudName" type="{http://www.w3.org/2001/XMLSchema}IDREF"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * @author sotillo19
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "anYaAntecedentType", propOrder = {
    "dataCloudName"
})
public class AnYaAntecedentType {

    @XmlElement(required = true)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object dataCloudName;

    /**
     * Gets the value of the property dataCloudName.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getDataCloudName() {
        return dataCloudName;
    }

    /**
     * Sets the value of the property dataCloudName.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setDataCloudName(Object value) {
        this.dataCloudName = value;
    }

}
