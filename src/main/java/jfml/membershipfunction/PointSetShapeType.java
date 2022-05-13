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


/**
 * <p>Java class for pointSetShapeType complex type.
 * 
 * <pre>
 * &lt;complexType name="pointSetShapeType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="point" type="{http://www.ieee1855.org}pointType" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="interpolationMethod" type="{http://www.ieee1855.org}interpolationMethodType" default="linear" /&gt;
 *       &lt;attribute name="degree" type="{http://www.w3.org/2001/XMLSchema}int" default="3" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * @author sotillo19
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
    
    /**
     * Default constructor
     */
    public PointSetShapeType() {
    	
	}
	
    /**
     * Constructor with left and right domain
     * @param domainLeft left domain
     * @param domainRight right domain
     */
	public PointSetShapeType(float domainLeft, float domainRight) {
	    super();
	    this.domainLeft=domainLeft;
		this.domainRight=domainRight;
	}
	
	/**
	 * Constructor with left and right domain and a list of PointType
	 * @param domainLeft left domain
	 * @param domainRight right domain
	 * @param points list of PointType
	 */
	public PointSetShapeType(float domainLeft, float domainRight, List<PointType> points) {
	    super();
	    this.domainLeft=domainLeft;
		this.domainRight=domainRight;
		this.point = points;
	}
	
	/**
	 * Constructor with a list of PointType
	 * @param points a list of PointType
	 */
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
			PointType p1 = getPoints().get(i);
			if(x<=p1.getX() && i==0)
				return p1.getY();
			else if(x>=p1.getX() && i==getPoints().size()-1)
				return p1.getY();
			else if(p1.getX()>=x){
				PointType p0 = getPoints().get(i-1);
				return p0.getY() + (p1.getY()-p0.getY())*((x-p0.getX())/(p1.getX()-p0.getX()));
			}	
		}
		return 0;
	}

	@Override
	public String toString() {
		String b = name + " [";
		
		for(PointType p : getPoints())
			b += "("+p.getX() + ", " + p.getY()+"), ";
		
		return b.substring(0, b.length()-2) + "]";
	}

	@Override
	public ArrayList<Float> getXValuesDefuzzifier() {
		ArrayList<Float> v = new ArrayList<>();
		
		for(PointType p : getPoints())
			v.add(p.getX());
		
		return v;
	}


	/**
	 * Copy a PointSetShapeType
	 * @return
	 */
	public PointSetShapeType copy() {
		PointSetShapeType copy = null;
		List<PointType> points = new ArrayList<>();
		
		for(PointType p : getPoints())
			points.add(new PointType(p.getX(), p.getY()));
		
		copy = new PointSetShapeType(domainLeft, domainRight, points);
		copy.setDegree(getDegree());
		copy.setInterpolationMethod(getInterpolationMethod());
		
		return copy;
		
	}

}
