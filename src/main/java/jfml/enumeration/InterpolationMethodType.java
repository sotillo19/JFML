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
 * <p>Java class for interpolationMethodType.
 * 
 * <pre>
 * &lt;simpleType name="interpolationMethodType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="linear"/&gt;
 *     &lt;enumeration value="lagrange"/&gt;
 *     &lt;enumeration value="spline"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * @author sotillo19
 */
@XmlType(name = "interpolationMethodType")
@XmlEnum
public enum InterpolationMethodType {

    @XmlEnumValue("linear")
    LINEAR("linear"),
    @XmlEnumValue("lagrange")
    LAGRANGE("lagrange"),
    @XmlEnumValue("spline")
    SPLINE("spline");
    private final String value;

    InterpolationMethodType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static InterpolationMethodType fromValue(String v) {
        for (InterpolationMethodType c: InterpolationMethodType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
