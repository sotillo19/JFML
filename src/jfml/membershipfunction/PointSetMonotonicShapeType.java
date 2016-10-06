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
 * &lt;complexType name="pointSetMonotonicShapeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="point" type="{http://www.ieee1855.org}pointType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="interpolationMethod" type="{http://www.ieee1855.org}monotonicInterpolationMethodType" default="linear" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
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
		MonotonicInterpolationMethodType interpolation = getInterpolationMethod();
		if(interpolation.value().equals(MonotonicInterpolationMethodType.LINEAR.value()) )
			return linearInterpolation(x);
		else if(interpolation.value().equals(MonotonicInterpolationMethodType.CUBIC.value()) )
			return cubicInterpolation(x);
		else
			return Float.NaN;

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
						throw new RuntimeException("The monotonocity is not possible with these input data points");
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
			if(getPoints().get(i).getX()>x)
				return i;
		return 0;
	}

	private int lower(float x) {
		for(int i=0;i<getPoints().size();i++)
			if(getPoints().get(i).getX()>x)
				return i-1;
		return 0;
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

	@Override
	public float getFi(float y) {
		MonotonicInterpolationMethodType interpolation = getInterpolationMethod();
		if(interpolation.value().equals(MonotonicInterpolationMethodType.LINEAR.value()) )
			return getFiLinear(y);
		else if(interpolation.value().equals(MonotonicInterpolationMethodType.CUBIC.value()) )
			return getFiCubic(y);
		else
			return Float.NaN;
	}

	private float getFiCubic(float y) {
		// TODO Auto-generated method stub
		return 0;
	}

	private float getFiLinear(float y) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Float> getXValuesDefuzzifier() {
		ArrayList<Float> v = new ArrayList<>();
		
		for(PointType p : getPoints())
			v.add(p.getX());
		
		return v;
	}

}
