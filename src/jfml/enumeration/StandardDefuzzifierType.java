package jfml.enumeration;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for standardDefuzzifierType.
 * 
 * <pre>
 * &lt;simpleType name="standardDefuzzifierType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="MOM"/>
 *     &lt;enumeration value="LM"/>
 *     &lt;enumeration value="RM"/>
 *     &lt;enumeration value="COG"/>
 *     &lt;enumeration value="COA"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "standardDefuzzifierType")
@XmlEnum
public enum StandardDefuzzifierType {

    MOM,
    LM,
    RM,
    COG,
    COA;

    public String value() {
        return name();
    }

    public static StandardDefuzzifierType fromValue(String v) {
        return valueOf(v);
    }

}
