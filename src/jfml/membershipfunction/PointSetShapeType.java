package jfml.membershipfunction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import jfml.enumeration.InterpolationMethodType;
import jfml.enumeration.MonotonicInterpolationMethodType;


/**
 * <p>Java class for pointSetShapeType complex type.
 * 
 * <pre>
 * &lt;complexType name="pointSetShapeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="point" type="{http://www.ieee1855.org}pointType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="interpolationMethod" type="{http://www.ieee1855.org}interpolationMethodType" default="linear" />
 *       &lt;attribute name="degree" type="{http://www.w3.org/2001/XMLSchema}int" default="3" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pointSetShapeType", propOrder = {
    "point"
})
public class PointSetShapeType extends MembershipFunction{

    @XmlElement(required = true)
    protected List<PointType> point;
    @XmlAttribute(name = "interpolationMethod")
    protected InterpolationMethodType interpolationMethod;
    @XmlAttribute(name = "degree")
    protected Integer degree;
    
    @XmlTransient
    String name = "PointSet";
    
    @XmlTransient
    float[] mM;
    
    public PointSetShapeType() {
    	
	}
	
	public PointSetShapeType(float domainLeft, float domainRight) {
	    super();
	    this.domainLeft=domainLeft;
		this.domainRight=domainRight;
	}
	
	public PointSetShapeType(float domainLeft, float domainRight, List<PointType> points) {
	    super();
	    this.domainLeft=domainLeft;
		this.domainRight=domainRight;
		this.point = points;
	}
	
	public PointSetShapeType(List<PointType> points) {
	    super();
		this.point = points;
	}

    /**
     * Gets the value of the point property.
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PointType }
     * 
     * 
     */
    public List<PointType> getPoints() {
        if (point == null) {
            point = new ArrayList<PointType>();
        }
        return this.point;
    }

    /**
     * Gets the value of the property interpolationMethod.
     * 
     * @return
     *     possible object is
     *     {@link InterpolationMethodType }
     *     
     */
    public InterpolationMethodType getInterpolationMethod() {
        if (interpolationMethod == null) {
            return InterpolationMethodType.LINEAR;
        } else {
            return interpolationMethod;
        }
    }

    /**
     * Sets the value of the property interpolationMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link InterpolationMethodType }
     *     
     */
    public void setInterpolationMethod(InterpolationMethodType value) {
        this.interpolationMethod = value;
    }

    /**
     * Gets the value of the property degree.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getDegree() {
        if (degree == null) {
            return  3;
        } else {
            return degree;
        }
    }

    /**
     * Sets the value of the property degree.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDegree(Integer value) {
        this.degree = value;
    }

	@Override
	public float getMembershipDegree(float x) {
		InterpolationMethodType interpolation = getInterpolationMethod();
		if(interpolation.value().equals(InterpolationMethodType.LINEAR.value()) )
			return linearInterpolation(x);
		else if(interpolation.value().equals(InterpolationMethodType.LAGRANGE.value()) )
			return lagrangeInterpolation(x);
		else if(interpolation.value().equals(InterpolationMethodType.SPLINE.value()) )
			return splineInterpolation(x);
		else
			return Float.NaN;
	}
	
	private float splineInterpolation(float x) {
		final int n = getPoints().size();
        
		if(mM == null){
			mM= new float[n-1];
	        for (int i = 0; i < n-1; i++) 
	            mM[i] = (getPoints().get(i+1).getY() - getPoints().get(i).getY()) / (getPoints().get(i+1).getX() - getPoints().get(i).getX());
		}
        
        if (Float.isNaN(x)) {
            return x;
        }
        if (x <= getPoints().get(0).getX()) {
            return getPoints().get(0).getY();
        }
        if (x >= getPoints().get(n-1).getX()) {
            return getPoints().get(n-1).getY();
        }
        // Find the index 'i' of the last point with smaller X.
        // We know this will be within the spline due to the boundary tests.
        int i = 0;
        while (x >= getPoints().get(i+1).getX()) {
            i += 1;
            if (x == getPoints().get(i).getX()) {
                return getPoints().get(i).getY();
            }
        }
        return getPoints().get(i).getY() + mM[i] * (x - getPoints().get(i).getX());
	}

	private float lagrangeInterpolation(float x) {
		int n = getPoints().size();
		float y =0;
		for(int count = 0; count<n; count++)
        {
             //Initialisation of variable
            float numerator = 1; 
            float denominator = 1;
             
            //second Loop for the polynomial calculation
            for(int count2 = 0; count2<n; count2++)
            {
                if (count2 != count)
                {
                  numerator = numerator * (x - getPoints().get(count2).getX());
                  denominator = denominator * (getPoints().get(count).getX() - getPoints().get(count2).getX());
                } 
            }
            y = y + (numerator/denominator) * getPoints().get(count).getY();
        }
		return y;
	}

	private float linearInterpolation(float x) {
		Collections.sort(getPoints(),new PointType());
		
		for(int i=0;i<getPoints().size();i++){
			PointType p0 = getPoints().get(i);
			if(p0.getX()>x && i+1<getPoints().size()){
				PointType p1 = getPoints().get(i+1);
				return p0.getY() + (p1.getY()-p0.getY())*((x-p0.getX())/(p1.getX()-p0.getX()));
			}	
		}
		return 0;
	}

	@Override
	public String toString() {
		String b = name + " [";
		
		for(PointType p : getPoints())
			b += "("+p.getX() + " ," + p.getY()+") ,";
		
		return b.substring(0, b.length()-2) + "]";
	}

}
