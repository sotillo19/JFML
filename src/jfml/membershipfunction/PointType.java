package jfml.membershipfunction;

import java.util.Comparator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;



/**
 * <p>Java class for pointType complex type.
 * 
 * <pre>
 * &lt;complexType name="pointType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="x" use="required" type="{http://www.w3.org/2001/XMLSchema}float" /&gt;
 *       &lt;attribute name="y" use="required" type="{http://www.w3.org/2001/XMLSchema}float" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * @author sotillo19
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pointType")
public class PointType implements Comparator<PointType>{

    @XmlAttribute(name = "x", required = true)
    protected float x;
    @XmlAttribute(name = "y", required = true)
    protected float y;
    
    /**
     * Default constructor
     */
    public PointType(){
    	
    }
    
    /**
     * Constructor with the two parameters x and y
     * @param x parameter x
     * @param y parameter y
     */
    public PointType(float x, float y){
    	super();
    	setX(x);
    	setY(y);
    }

    /**
     * Gets the value of the property x.
     * 
     */
    public float getX() {
        return x;
    }

    /**
     * Sets the value of the property x.
     * 
     */
    public void setX(float value) {
        this.x = value;
    }

    /**
     * Gets the value of the property y.
     * 
     */
    public float getY() {
        return y;
    }

    /**
     * Sets the value of the property y.
     * 
     */
    public void setY(float value) {
        this.y = value;
    }
    
    @Override
    public String toString(){
    	return "["+x + ", "+y+"]";
    }

	@Override
	public int compare(PointType o1, PointType o2) {
		int r=0;
		int sorting=1;
		if (o1.getX() == o2.getX())
            return 0;

        if (o1.getX() < o2.getX())
            r=-1;
        else
        	r=1;
        
        return r*sorting;
	}

}
