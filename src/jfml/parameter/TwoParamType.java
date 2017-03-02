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
package jfml.parameter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for the twoParamType complex type.
 * 
 * <pre>
 * &lt;complexType name="twoParamType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="param1" use="required" type="{http://www.w3.org/2001/XMLSchema}float" /&gt;
 *       &lt;attribute name="param2" use="required" type="{http://www.w3.org/2001/XMLSchema}float" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * @author sotillo19
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "twoParamType")
public class TwoParamType extends Parameter{

    @XmlAttribute(name = "param1", required = true)
    protected float param1;
    @XmlAttribute(name = "param2", required = true)
    protected float param2;

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

    /**
     * Gets the value of the attribute param2.
     * @return the value of the attribute param2.
     */
    public float getParam2() {
        return param2;
    }

    /**
     * Sets the value for the attribute param2.
     * @param value value
     */
    public void setParam2(float value) {
        this.param2 = value;
    }

	@Override
	public int getParameterLength() {
		return 2;
	}

	@Override
	public float getParameter(int i) {
		if(i>0 && i<=getParameterLength()){
			if(i==1)
				return getParam1();
			else if(i==2)
				return getParam2();
		}
		return -1;		
	}

}
