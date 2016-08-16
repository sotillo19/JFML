//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2016.07.12 a las 06:51:57 PM CEST 
//


package jfml.parameter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para fourParamType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="fourParamType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="param1" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="param2" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="param3" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="param4" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fourParamType")
public class FourParamType extends Parameter{

    @XmlAttribute(name = "param1", required = true)
    protected float param1;
    @XmlAttribute(name = "param2", required = true)
    protected float param2;
    @XmlAttribute(name = "param3", required = true)
    protected float param3;
    @XmlAttribute(name = "param4", required = true)
    protected float param4;

    /**
     * Obtiene el valor de la propiedad param1.
     * 
     */
    public float getParam1() {
        return param1;
    }

    /**
     * Define el valor de la propiedad param1.
     * 
     */
    public void setParam1(float value) {
        this.param1 = value;
    }

    /**
     * Obtiene el valor de la propiedad param2.
     * 
     */
    public float getParam2() {
        return param2;
    }

    /**
     * Define el valor de la propiedad param2.
     * 
     */
    public void setParam2(float value) {
        this.param2 = value;
    }

    /**
     * Obtiene el valor de la propiedad param3.
     * 
     */
    public float getParam3() {
        return param3;
    }

    /**
     * Define el valor de la propiedad param3.
     * 
     */
    public void setParam3(float value) {
        this.param3 = value;
    }

    /**
     * Obtiene el valor de la propiedad param4.
     * 
     */
    public float getParam4() {
        return param4;
    }

    /**
     * Define el valor de la propiedad param4.
     * 
     */
    public void setParam4(float value) {
        this.param4 = value;
    }

	@Override
	public int getParameterLength() {
		return 4;
	}

	@Override
	public float getParameter(int i) {
		if(i>0 && i<=getParameterLength()){
			if(i==1)
				return getParam1();
			else if(i==2)
				return getParam2();
			else if(i==3)
				return getParam3();
			else if(i==4)
				return getParam4();
		}
		return -1;	
	}

}
