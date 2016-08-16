package jfml.rulebase;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.NONE) //This is needed to ignore class attributes as xml tags in XML files
public abstract class FuzzySystemRuleBaseType {
    
	public static String defaultActivationMethod = "MIN";
	public static String defaultAndMethod = "MIN";
	public static String defaultOrMethod = "MAX";
	public static String defaultNetworkAddress = "127.0.0.1";
	
    static final public int TYPE_MAMDANI=0;
    static final public int TYPE_TSUKAMOTO=1;
    static final public int TYPE_TSK=2;
    static final public int TYPE_ANYA=3;
    static final public int TYPE_OTHER=4;
	
	protected int ruleBaseSystemType;
	
	public abstract void evaluate();
	
	/**
     * 
     * @return
     */
    public int getRuleBaseSystemType(){
    	return this.ruleBaseSystemType;
    }
    
    public String getRuleBaseSystemTypeName(){
    	switch (ruleBaseSystemType) {
		case TYPE_MAMDANI:
			return "mamdani";
		case TYPE_TSUKAMOTO:
			return "tsukamoto";
		case TYPE_TSK:
			return "tsk";
		case TYPE_ANYA:
			return "anYa";
		default:
			return "other";
		}
    }
    
    /**
     * Sets the fuzzy system type according to static variables
     * <p>
     * - TYPE_MAMDANI - Mamdani Rule Base 
     * - TYPE_TSUKAMOTO - tsukamoto Rule Base
     * - TYPE_TSK - tsk Rule Base
     * - TYPE_ANYA - AnYa Rule Base
     * - TYPE_OTHER - other Rule Base
     * 
     * @param type 
     */
    public void setRuleBaseSystemType(int type){
    	this.ruleBaseSystemType=type;
    }

	public abstract void reset();
    
	@Override
    public abstract String toString();

}
