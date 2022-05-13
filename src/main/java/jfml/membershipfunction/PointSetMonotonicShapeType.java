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

import jfml.enumeration.MonotonicInterpolationMethodType;


/**
 * <p>Java class for pointSetMonotonicShapeType complex type.
 * 
 * <pre>
 * &lt;complexType name="pointSetMonotonicShapeType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="point" type="{http://www.ieee1855.org}pointType" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="interpolationMethod" type="{http://www.ieee1855.org}monotonicInterpolationMethodType" default="linear" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * @author sotillo19
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pointSetMonotonicShapeType", propOrder = {
    "point"
})
public class PointSetMonotonicShapeType extends MembershipFunction implements Monotonical{

    @XmlElement(required = true)
    protected List<PointType> point;
    @XmlAttribute(name = "interpolationMethod")
    protected MonotonicInterpolationMethodType interpolationMethod;
    
    @XmlTransient
    String name = "Monotonic-pointSet";
    
    @XmlTransient
    float[] d;
    
    @XmlTransient
    float[] m;

	public PointSetMonotonicShapeType() {
	
	}
	
	public PointSetMonotonicShapeType(float domainLeft, float domainRight) {
	    super();
	    this.domainLeft=domainLeft;
		this.domainRight=domainRight;
	}
	
	public PointSetMonotonicShapeType(float domainLeft, float domainRight, List<PointType> points) {
	    super();
	    this.domainLeft=domainLeft;
		this.domainRight=domainRight;
		this.point = points;
	}
	
	public PointSetMonotonicShapeType(List<PointType> points) {
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
     * Set points
     * @param points
     */
    public void setPoints(List<PointType> points){
    	this.point = points;
    }
    
    /**
     * Add a point to the list
     * @param p
     */
    public void addPoint(PointType p){
    	if (point == null) {
            point = new ArrayList<PointType>();
        }
    	point.add(p);
    }
    
    /**
     * Add a point to the list
     * @param p
     */
    public void addPoint(float x, float y){
    	if (point == null) {
            point = new ArrayList<PointType>();
        }
    	point.add(new PointType(x, y));
    }

    /**
     * Gets the value of the property interpolationMethod.
     * 
     * @return
     *     possible object is
     *     {@link MonotonicInterpolationMethodType }
     *     
     */
    public MonotonicInterpolationMethodType getInterpolationMethod() {
        if (interpolationMethod == null) {
            return MonotonicInterpolationMethodType.LINEAR;
        } else {
            return interpolationMethod;
        }
    }

    /**
     * Sets the value of the property interpolationMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link MonotonicInterpolationMethodType }
     *     
     */
    public void setInterpolationMethod(MonotonicInterpolationMethodType value) {
        this.interpolationMethod = value;
    }

	@Override
	public float getMembershipDegree(float x) {
		if(!isMonotonic())
			throw new RuntimeException("Points: "+point.toString()+" are not monotonic");
		
		MonotonicInterpolationMethodType interpolation = getInterpolationMethod();
		if(interpolation.value().equals(MonotonicInterpolationMethodType.LINEAR.value()) )
			return linearInterpolation(x);
		else if(interpolation.value().equals(MonotonicInterpolationMethodType.CUBIC.value()) )
			return cubicInterpolation(x);
		else
			return Float.NaN;

	}
	
	private boolean isMonotonic() {
		Collections.sort(getPoints(),new PointType());
		int count = 0;
		for(int i=1;i<getPoints().size();i++)
			if(getPoints().get(i-1).getY()<getPoints().get(i).getY())
				count++;
			else
				count--;
		
		if(Math.abs(count)==getPoints().size()-1)
			return true;
		else
			return false;
	}

	private boolean preprocesingDataCubicInterpolation(){
		Collections.sort(getPoints(),new PointType());
		int n=getPoints().size();
		//Step 1
		if(d==null){
			d = new float[n];
			for(int i=0;i<n-1;i++){
				PointType p0 = getPoints().get(i);
				PointType p1 = getPoints().get(i+1);
				d[i]=(p1.getY()-p0.getY())/(p1.getX()-p0.getX());
			}
		}
		
		//Step 2
		if(m==null){
			m = new float[n];
			for(int i=1;i<n-1;i++){
				m[i]=(d[i-1]+d[i])/2;
				
				//if d[i-1] and d[i] have different sign --> m[i]=0
				if(d[i-1]*d[i]<0)
					m[i]=0;
				
				//step 3
				if(d[i]==0){
					m[i]=0;
					m[i+1]=0;
				}
				//step 4
				else{
					float a=m[i]/d[i];
					float b=m[i+1]/d[i];
					if(a<0 || b<0)
						m[i]=0;
					if(!checkMonotonicity(a,b))
						throw new RuntimeException("The monotonocity is not possible with these input data points: "+point.toString());
				}
			}
			m[0]=d[0];
			m[n-1]=d[n-2];		
			
			return true;
		}
		return true;
	}

	private float cubicInterpolation(float x) {
		preprocesingDataCubicInterpolation();
		
		return f_interpolated(x);
	}

	private float f_interpolated(float x) {
		int i_lower = lower(x);
		int i_upper = upper(x);
		
		if(i_lower == i_upper)
			return getPoints().get(i_lower).getY();
		
		PointType p_lower = getPoints().get(i_lower);
		PointType p_upper = getPoints().get(i_upper);
		float m_lower = m[i_lower];
		float m_upper = m[i_upper];
		
		float h = p_upper.getX() - p_lower.getX();
		float t = (x-p_lower.getX())/h;
		
		return p_lower.getY()*h_00(t) + h*m_lower*h_10(t) + p_upper.getY()*h_01(t) + h*m_upper*h_11(t);
	}

	private float h_11(float t) {
		return (-1/3f)*B_2(t);
	}

	private float h_01(float t) {
		return B_3(t) + B_2(t);
	}

	private float h_10(float t) {
		return (1/3f)*B_1(t);
	}

	private float h_00(float t) {
		return B_0(t) + B_1(t);
	}
	

	private float B_3(float t) {
		return (float) Math.pow(t, 3);
	}
	
	private float B_2(float t) {
		return (float) (3 * Math.pow(t, 2) * (1 - t));
	}

	private float B_1(float t) {
		return (float) (3 * Math.pow(1-t, 2) * (t));
	}

	private float B_0(float t) {
		return (float) Math.pow(1-t, 3);
	}

	private int upper(float x) {
		for(int i=0;i<getPoints().size();i++)
			if(getPoints().get(i).getX()>=x)
				return i;
		return getPoints().size()-1;
	}

	private int lower(float x) {
		for(int i=0;i<getPoints().size();i++)
			if(getPoints().get(i).getX()>=x)
				if(i==0)
					return i;
				else
					return i-1;
		
		return getPoints().size()-1;
	}

	private boolean checkMonotonicity(float a, float b) {
		if(a-(Math.pow((2*a+b-2),2)/(3*(a+b-2))) >= 0)
			return true;
		else if(a+2*b-3 <= 0)
			return true;
		else if(2*a+b-3 <= 0)
			return true;
		else
			return false;
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
	public float getFi(float y) {
		if(!isMonotonic())
			throw new RuntimeException("Points: "+point.toString()+" are not monotonic");
		
		MonotonicInterpolationMethodType interpolation = getInterpolationMethod();
		if(interpolation.value().equals(MonotonicInterpolationMethodType.LINEAR.value()) )
			return getFiLinear(y);
		else if(interpolation.value().equals(MonotonicInterpolationMethodType.CUBIC.value()) )
			return getFiCubic(y);
		else
			return Float.NaN;
	}

	private float getfi_interpolated(float y, int i_lower, int i_upper) {
		PointType p_lower = getPoints().get(i_lower);
		PointType p_upper = getPoints().get(i_upper);
		float m_lower = m[i_lower];
		float m_upper = m[i_upper];
		
		float h = p_upper.getX() - p_lower.getX();

		float y_lower = p_lower.getY();
		float y_upper = p_upper.getY();

		float b_lower = 0;
		float b_upper = 1.0f;
			
		float t=0;
		float err = 1.0f;
		
		while (Math.abs(err) > 0.0001f){
			t = b_lower + ((b_upper - b_lower) / 2.0f);
			err = (float) ((Math.pow(t, 3)*((2*y_lower) - (2*y_upper) + (h*m_lower) + (h*m_upper))) + ((Math.pow(t, 2)*((3*y_upper) - (3*y_lower) - (h*m_upper) - (2*h*m_lower))) + (t*h*m_lower) + y_lower - y));

			if (err > 0) b_upper = t;
			else  b_lower = t;
		}
		
		return ((t * h) + p_lower.getX());
	}

	private float getFiCubic(float y) {
		preprocesingDataCubicInterpolation();
		
		Collections.sort(getPoints(),new PointType());
		PointType p0 = getPoints().get(0);
		PointType pf = getPoints().get(getPoints().size()-1);
		if(y==p0.getY())
			return p0.getX();
		else if(y==pf.getY())
			return pf.getX();
		else{
			if (p0.getY() < pf.getY()) {
				if(y<p0.getY())
					return p0.getX();
				if(y>pf.getY())
					return pf.getX();
				for(int i=1;i<getPoints().size();i++){
					PointType p1 = getPoints().get(i);
					if(y==p1.getY())  return p1.getX(); 
					else if(y<p1.getY()){
						return (getfi_interpolated(y, i-1, i));
					}	
				}
			}
			else {
				if(p0.getY()<y)
					return p0.getX();
				if(pf.getY()>y)
					return pf.getX();
				for(int i=1;i<getPoints().size();i++){
					PointType p1 = getPoints().get(i);
					if(p1.getY()==y)  return p1.getX();
					else if(p1.getY()<y){
						return (getfi_interpolated(y, i, i-1));
					}	
				}
			}
		}
		return 0;
	}


	private float getFiLinear(float y) {
		Collections.sort(getPoints(),new PointType());
		PointType p0 = getPoints().get(0);
		PointType pf = getPoints().get(getPoints().size()-1);
		if(y==p0.getY())
			return p0.getX();
		else if(y==pf.getY())
			return pf.getX();
		else{
			if (p0.getY() < pf.getY()) {
				if(y<p0.getY())
					return p0.getX();
				if(y>pf.getY())
					return pf.getX();
				for(int i=1;i<getPoints().size();i++){
					PointType p1 = getPoints().get(i);
					if(y==p1.getY())  return p1.getX(); 
					else if(y<p1.getY()){
						p0 = getPoints().get(i-1);
						return p0.getX() + (p1.getX()-p0.getX())*(y-p0.getY())/(p1.getY()-p0.getY());
					}	
				}
			}
			else {
				if(p0.getY()<y)
					return p0.getX();
				if(pf.getY()>y)
					return pf.getX();
				for(int i=1;i<getPoints().size();i++){
					PointType p1 = getPoints().get(i);
					if(p1.getY()==y)  return p1.getX();
					else if(p1.getY()<y){
						p0 = getPoints().get(i-1);
						return p0.getX() + (p1.getX()-p0.getX())*(y-p0.getY())/(p1.getY()-p0.getY());
					}	
				}
			}
		}
		return 0;
	}


	@Override
	public ArrayList<Float> getXValuesDefuzzifier() {
		ArrayList<Float> v = new ArrayList<>();
		
		for(PointType p : getPoints())
			v.add(p.getX());
		
		return v;
	}

	
	/**
	 * For testing
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<PointType> p = new ArrayList<>();
		
		//Increasing function
		p.add(new PointType(1, 0.3f));
		p.add(new PointType(2, 0.6f));
		p.add(new PointType(3, 0.7f));
		p.add(new PointType(4, 1.3f));
		p.add(new PointType(5, 1.6f));
		
		//Decreasing function
		/*p.add(new PointType(5, 0.3f));
		p.add(new PointType(4, 0.6f));
		p.add(new PointType(3, 0.7f));
		p.add(new PointType(2, 1.3f));
		p.add(new PointType(1, 1.6f));*/
		
		PointSetMonotonicShapeType ps = new PointSetMonotonicShapeType(p);
		ps.setInterpolationMethod(MonotonicInterpolationMethodType.CUBIC);
		
		float x = 2.6f;
		float y = ps.getMembershipDegree(x);
		System.out.println(x +"->" + y);
		
		float x1 = ps.getFi(y);
		System.out.println(y +"->" + x1);
	}
}
