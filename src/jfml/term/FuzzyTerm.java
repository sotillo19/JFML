package jfml.term;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import jfml.membershipfunction.*;

/**
 * Abstract class for representing the FuzzyTerm types
 * Each fuzzy term has associated a {@link MembershipFunction} and a left and right domain from the variable
 * 
 * @author sotillo19
 *
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class FuzzyTerm extends Term{
	public static final int TYPE_rightLinearShape = 0;
	public static final int TYPE_leftLinearShape = 1;
	public static final int TYPE_piShape = 2;
	public static final int TYPE_triangularShape = 3;
	public static final int TYPE_gaussianShape = 4;
	public static final int TYPE_rightGaussianShape = 5;
	public static final int TYPE_leftGaussianShape = 6;
	public static final int TYPE_trapezoidShape = 7;
	public static final int TYPE_singletonShape = 8;
	public static final int TYPE_rectangularShape = 9;
	public static final int TYPE_zShape = 10;
	public static final int TYPE_sShape = 11;
	public static final int TYPE_pointSetShape = 12;
	public static final int TYPE_pointSetMonotonicShape = 13;
	public static final int TYPE_circularDefinition = 14;
	public static final int TYPE_customShape = 15;
	public static final int TYPE_customMonotonicShape = 16;
	
	protected float leftDomain, rightDomain;

	protected int type = -1;

	protected float evaluation = -1;
	
	protected MembershipFunction mf;
	
	/**
	 * Gets the complement (true or false)
	 * @return the complement
	 */
	public abstract String getComplement();
	
	/**
	 * Sets the value for the type according to the static variables
	 * @param type the value for the type. Possible values:
	 * - TYPE_rightLinearShape </br>
	 * - TYPE_leftLinearShape </br>
	 * -TYPE_piShape</br>
	 * -TYPE_triangularShape</br>
	 * -TYPE_gaussianShape</br>
	 * 	-TYPE_rightGaussianShape</br>
	 * 	-TYPE_leftGaussianShape</br>
	 * 	-TYPE_trapezoidShape</br>
	 * 	-TYPE_singletonShape</br>
	 * 	-TYPE_rectangularShape</br>
	 * 	-TYPE_zShape </br>
	 * 	-TYPE_sShape</br>
	 * 	-TYPE_pointSetShape</br> 
	 * 	-TYPE_pointSetMonotonicShape</br>
	 * 	-TYPE_circularDefinition </br>
	 * 	-TYPE_customShape</br>
	 * 	-TYPE_customMonotonicShape</br>
	 *  
	 */
	public void setType(int type){
		this.type=type;
	}
	
	/**
	 * Gets the {@link } MembershipFunction associated to this term
	 * @return the {@link } MembershipFunction associated to this term
	 */
	public MembershipFunction getMembershipFunction(){
		return this.mf;
	}
	
	/**
	 * This function returns an array with values [x1, x2, x3, ...] which represents points in the x domain of the function needed by defuzzifer
	 * @return an ArrayList with floats
	 */
	public ArrayList<Float> getXValuesDefuzzifier(){
		if(mf !=null)
			return mf.getXValuesDefuzzifier();
		else
			return new ArrayList<>();
	}

	/**
	 * This function initializes the membership function associated to this term
	 * @param domainLeft the left domain
	 * @param domainRight the right domain
	 */
	public void initializeMembershipFunction(float domainLeft, float domainRight){
		leftDomain = domainLeft;
		rightDomain = domainRight;
		if (this instanceof FuzzyTermType) {
			FuzzyTermType t = (FuzzyTermType) this;
			
			if (t.getRightLinearShape() != null){
				mf = new RightLinearMembershipFunction(t.getRightLinearShape(),domainLeft,domainRight);
				t.setType(TYPE_rightLinearShape);
			}
			else if (t.getLeftLinearShape() != null){
				mf = new LeftLinearMembershipFunction(t.getLeftLinearShape(),domainLeft,domainRight);
				t.setType(TYPE_leftLinearShape);
			}
			else if (t.getPiShape() != null){
				mf = new PiShapedMembershipFunction(t.getPiShape(),domainLeft,domainRight);
				t.setType(TYPE_piShape);
			}
			else if (t.getTriangularShape() != null){
				mf = new TriangularMembershipFunction(t.getTriangularShape(),domainLeft,domainRight);
				t.setType(TYPE_triangularShape);
			}
			else if (t.getGaussianShape() != null){
				mf = new GaussianMembershipFunction(t.getGaussianShape(),domainLeft,domainRight);
				t.setType(TYPE_gaussianShape);
			}
			else if (t.getRightGaussianShape() != null){
				mf = new RightGaussianMembershipFunction(t.getRightGaussianShape(),domainLeft,domainRight);
				t.setType(TYPE_rightGaussianShape);
			}
			else if (t.getLeftGaussianShape() != null){
				mf = new LeftGaussianMembershipFunction(t.getLeftGaussianShape(),domainLeft,domainRight);
				t.setType(TYPE_leftGaussianShape);
			}
			else if (t.getTrapezoidShape() != null){
				mf = new TrapezoidMembershipFunction(t.getTrapezoidShape(),domainLeft,domainRight);
				t.setType(TYPE_trapezoidShape);
			}
			else if (t.getSingletonShape() != null){
				mf = new SingletonMembershipFunction(t.getSingletonShape(),domainLeft,domainRight);
				t.setType(TYPE_singletonShape);
			}
			else if (t.getRectangularShape() != null){
				mf = new RectangularMembershipFunction(t.getRectangularShape(),domainLeft,domainRight);
				t.setType(TYPE_rectangularShape);
			}
			else if (t.getZShape() != null){
				mf = new ZShapeMembershipFunction(t.getZShape(),domainLeft,domainRight);
				t.setType(TYPE_zShape);
			}
			else if (t.getSShape() != null){
				mf = new SShapeMembershipFunction(t.getSShape(),domainLeft,domainRight);
				t.setType(TYPE_sShape);
			}
			else if (t.getPointSetShape() != null){
				mf = new PointSetShapeType(domainLeft,domainRight,t.getPointSetShape().getPoints());
				t.setType(TYPE_pointSetShape);
			}
			else if (t.getCircularDefinition() != null){
				mf = new CircularMembershipFunction(t.getCircularDefinition());
				t.setType(TYPE_circularDefinition);
			}
			else if (t.getCustomShape() != null){
				mf = new CustomMembershipFunction(t.getCustomShape(),domainLeft,domainRight);
				t.setType(TYPE_customShape);
			}
		}
		else if(this instanceof TsukamotoTermType){
			TsukamotoTermType t = (TsukamotoTermType) this;
			
			if (t.getRightLinearShape() != null){
				mf = new RightLinearMembershipFunction(t.getRightLinearShape(),domainLeft,domainRight);
				t.setType(TYPE_rightLinearShape);
			}
			else if (t.getLeftLinearShape() != null){
				mf = new LeftLinearMembershipFunction(t.getLeftLinearShape(),domainLeft,domainRight);
				t.setType(TYPE_leftLinearShape);
			}
			else if (t.getRightGaussianShape() != null){
				mf = new RightGaussianMembershipFunction(t.getRightGaussianShape(),domainLeft,domainRight);
				t.setType(TYPE_rightGaussianShape);
			}
			else if (t.getLeftGaussianShape() != null){
				mf = new LeftGaussianMembershipFunction(t.getLeftGaussianShape(),domainLeft,domainRight);
				t.setType(TYPE_leftGaussianShape);
			}
			else if (t.getZShape() != null){
				mf = new ZShapeMembershipFunction(t.getZShape(),domainLeft,domainRight);
				t.setType(TYPE_zShape);
			}
			else if (t.getSShape() != null){
				mf = new SShapeMembershipFunction(t.getSShape(),domainLeft,domainRight);
				t.setType(TYPE_sShape);
			}
			else if (t.getPointSetMonotonicShape() != null){
				mf = new PointSetMonotonicShapeType(domainLeft,domainRight,t.getPointSetMonotonicShape().getPoints());
				t.setType(TYPE_pointSetMonotonicShape);
			}
			else if (t.getCustomMonotonicShape() != null){
				mf = new CustomMembershipFunction(t.getCustomMonotonicShape(),domainLeft,domainRight);
				t.setType(TYPE_customMonotonicShape);
			}
		}
	}
	
	@Override
	public float getMembershipValue(float x) {
		float d = 0;
		MembershipFunction mf = getMembershipFunction();
		
		if(mf!=null)
			d = mf.getMembershipDegree(x);
		
		if (getComplement().equals("true") || getComplement().equals("TRUE") || getComplement().equals("True"))
			d = 1 - d;
		
		return d;
	}
	
	/**
	 * Gets the x value from y
	 * @param y the y value
	 * @return the x value from y
	 */
	public float getFi(float y){
		float d = 0;
		MembershipFunction mf = getMembershipFunction();
		
		if(mf!=null && mf instanceof Monotonical)
			d = ((Monotonical) mf).getFi(y);
		
		if (getComplement().equals("true") || getComplement().equals("TRUE") || getComplement().equals("True"))
			d = 1 - d;
		
		return d;
	}

	/**
	 * Gets the name of the fuzzy term
	 */
	public abstract String getName();
	
	/**
	 * Creates a copy of the fuzzy term
	 */
	public abstract FuzzyTerm copy();

}
