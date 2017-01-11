package jfml.enumeration;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for standardDefuzzifierType.
 * 
 * <pre>
 * &lt;simpleType name="standardDefuzzifierType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="MOM"/&gt;
 *     &lt;enumeration value="LM"/&gt;
 *     &lt;enumeration value="RM"/&gt;
 *     &lt;enumeration value="COG"/&gt;
 *     &lt;enumeration value="COA"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * @author sotillo19
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
