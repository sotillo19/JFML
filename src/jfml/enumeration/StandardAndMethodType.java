package jfml.enumeration;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for standardTnormType.
 * 
 * <pre>
 * &lt;simpleType name="standardTnormType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="MIN"/&gt;
 *     &lt;enumeration value="PROD"/&gt;
 *     &lt;enumeration value="BDIF"/&gt;
 *     &lt;enumeration value="DRP"/&gt;
 *     &lt;enumeration value="EPROD"/&gt;
 *     &lt;enumeration value="HPROD"/&gt;
 *     &lt;enumeration value="NILMIN"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * @author sotillo19
 */
@XmlType(name = "standardAndMethodType")
@XmlEnum
public enum StandardAndMethodType {

    MIN,
    PROD,
    BDIF,
    DRP,
    EPROD,
    HPROD,
    NILMIN;

    public String value() {
        return name();
    }

    public static StandardAndMethodType fromValue(String v) {
        return valueOf(v);
    }

}
