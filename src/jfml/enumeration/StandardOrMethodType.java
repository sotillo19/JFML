package jfml.enumeration;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for standardTconormType.
 * 
 * <pre>
 * &lt;simpleType name="standardTconormType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="MAX"/>
 *     &lt;enumeration value="PROBOR"/>
 *     &lt;enumeration value="BSUM"/>
 *     &lt;enumeration value="DRS"/>
 *     &lt;enumeration value="ESUM"/>
 *     &lt;enumeration value="HSUM"/>
 *     &lt;enumeration value="NILMAX"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 * @author sotillo19
 */
@XmlType(name = "standardTconormType")
@XmlEnum
public enum StandardOrMethodType {

    MAX,
    PROBOR,
    BSUM,
    DRS,
    ESUM,
    HSUM,
    NILMAX;

    public String value() {
        return name();
    }

    public static StandardOrMethodType fromValue(String v) {
        return valueOf(v);
    }

}
