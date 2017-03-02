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
package jfml.enumeration;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for standardModifierType.
 * 
 * <pre>
 * &lt;simpleType name="standardModifierType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="above"/&gt;
 *     &lt;enumeration value="any"/&gt;
 *     &lt;enumeration value="below"/&gt;
 *     &lt;enumeration value="extremely"/&gt;
 *     &lt;enumeration value="intensify"/&gt;
 *     &lt;enumeration value="more_or_less"/&gt;
 *     &lt;enumeration value="norm"/&gt;
 *     &lt;enumeration value="not"/&gt;
 *     &lt;enumeration value="plus"/&gt;
 *     &lt;enumeration value="seldom"/&gt;
 *     &lt;enumeration value="slightly"/&gt;
 *     &lt;enumeration value="somewhat"/&gt;
 *     &lt;enumeration value="very"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * @author sotillo19
 */
@XmlType(name = "standardModifierType")
@XmlEnum
public enum StandardModifierType {

    @XmlEnumValue("above")
    ABOVE("above"),
    @XmlEnumValue("any")
    ANY("any"),
    @XmlEnumValue("below")
    BELOW("below"),
    @XmlEnumValue("extremely")
    EXTREMELY("extremely"),
    @XmlEnumValue("intensify")
    INTENSIFY("intensify"),
    @XmlEnumValue("more_or_less")
    MORE_OR_LESS("more_or_less"),
    @XmlEnumValue("norm")
    NORM("norm"),
    @XmlEnumValue("not")
    NOT("not"),
    @XmlEnumValue("plus")
    PLUS("plus"),
    @XmlEnumValue("seldom")
    SELDOM("seldom"),
    @XmlEnumValue("slightly")
    SLIGHTLY("slightly"),
    @XmlEnumValue("somewhat")
    SOMEWHAT("somewhat"),
    @XmlEnumValue("very")
    VERY("very");
    private final String value;

    StandardModifierType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static StandardModifierType fromValue(String v) {
        for (StandardModifierType c: StandardModifierType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
