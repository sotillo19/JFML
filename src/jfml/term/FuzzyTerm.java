package jfml.term;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import jfml.membershipfunction.*;

@XmlAccessorType(XmlAccessType.NONE)
public class FuzzyTerm {
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
	public static final int TYPE_circularDefinition = 13;
	public static final int TYPE_customShape = 14;

	protected int type = -1;

	protected float evaluation = -1;
	
	protected MembershipFunction mf;
	
	public void setType(int type){
		this.type=type;
	}
	
	public MembershipFunction getMembershipFunction(){
		return this.mf;
	}

	public void initializeMembershipFunction(float domainLeft, float domainRight){
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
				mf = new PointSetShapeMembershipFunction(t.getPointSetShape(),domainLeft,domainRight);
				t.setType(TYPE_pointSetShape);
			}
			else if (t.getCircularDefinition() != null){
				mf = new CircularMembershipFunction(t.getCircularDefinition(),domainLeft,domainRight);
				t.setType(TYPE_circularDefinition);
			}
			else if (t.getCustomShape() != null){
				mf = new CustomMembershipFunction(t.getCustomShape(),domainLeft,domainRight);
				t.setType(TYPE_customShape);
			}
		}
		else{
			//TODO for other fuzzyTerms
		}
	}
	
	public float getMembershipValue(float x) {
		float d = 0;
		MembershipFunction mf = getMembershipFunction();
		
		if(mf!=null)
			d = mf.getMembershipDegree(x);
		
		if (this instanceof FuzzyTermType) {
			FuzzyTermType t = (FuzzyTermType) this;

			if (t.getComplement().equals("true") || t.getComplement().equals("TRUE")
					|| t.getComplement().equals("True"))
				d = 1 - d;
		}
		return d;
	}

}
