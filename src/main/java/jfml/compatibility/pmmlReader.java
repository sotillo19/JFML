/*
*  This file is a part of the package "frbsJpmml".
*  
*  Author: Lala Septem Riza
*  Co-author: Christoph Bergmeir
*  Supervisors: Francisco Herrera Triguero and Jose Manuel Benitez
*  Copyright (c) DiCITS Lab, Sci2s group, DECSAI, University of Granada.
*  
*  This package is free software: you can redistribute it and/or modify it under
*  the terms of the GNU General Public License as published by the Free Software
*  Foundation, either version 2 of the License, or (at your option) any later version.
*
*  This package is distributed in the hope that it will be useful, but WITHOUT
*  ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
*  A PARTICULAR PURPOSE. See the GNU General Public License for more details.
*/

package jfml.compatibility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * It is a class for reading pmml.
 * @author Lala
 */
public class pmmlReader extends DefaultHandler{
    private static final String TAG_PMML = "frbsPMML";
    private static final String TAG_HEADER = "Header";
    private static final String TAG_EXTENSION = "Extension";
    private static final String TAG_APPLICATION = "Application";
    private static final String TAG_TIMESTAMP = "Timestamp";
    private static final String TAG_DATADICTIONARY = "DataDictionary";
    private static final String TAG_DATAFIELD = "DataField";
    private static final String TAG_CLASSVALUE ="Value";
    private static final String TAG_INTERVAL = "Interval";
    private static final String TAG_FRBS = "FrbsModel";
    private static final String TAG_MININGSCHEMA = "MiningSchema";
    private static final String TAG_MININGFIELD = "MiningField";
    private static final String TAG_OUTPUTFIELD = "OutputField";
    private static final String TAG_CONJUNCTIONOPERATOR = "ConjunctionOperator";
    private static final String TAG_DISJUNCTIONOPERATOR = "DisjunctionOperator";
    private static final String TAG_IMPLICATIONOPERATOR = "ImplicationOperator";
    private static final String TAG_AGGREGATIONOPERATOR = "AggregationOperator";  
    private static final String TAG_MEMBERSHIPFUNCTIONATTRS = "MembershipFunction";
    private static final String TAG_FUZZYTERM = "FuzzyTerm";
    private static final String TAG_MEAN = "mean";
    private static final String TAG_VARIANCE = "Variance";
    private static final String TAG_LEFT = "Left";
    private static final String TAG_LEFTMIDDLE = "LeftMiddle";
    private static final String TAG_RIGHTMIDDLE = "RightMiddle";
    private static final String TAG_MIDDLE = "Middle";
    private static final String TAG_RIGHT = "Right";
    private static final String TAG_RULE = "Rule";
    private static final String TAG_COMPOUNDPREDICATE = "CompoundPredicate";
    private static final String TAG_SIMPLEPREDICATE = "SimplePredicate";
    private static final String TAG_THENPART = "Then";
    private static final String TAG_COEFFICIENT = "Coefficient";
    private static final String TAG_CONSTANT = "Constant";
    private static final String TAG_GRADE = "Grade";

    private List<HeaderPMML> headerList = null;
    private List<Extension> extensionList = null;
    private List<Application> applicationList = null;
    private List<DataDictAttr> datadictionaryList = null;
    private List<DataField> datafieldList = null;
    private List<ClassValue> classvalueList = null;
    private List<Interval> intervalList = null;
    private List<frbsPMML> frbsList = null;
    private List<MiningField> miningfieldList = null;
    private List<OutputField> outputfieldList = null;
    private List<ConjunctionOperator> conjunctionoperatorList = null;
    private List<DisjunctionOperator> disjunctionoperatorList = null;
    private List<ImplicationOperator> implicationoperatorList = null;
    private List<AggregationOperator> aggregationoperatorList = null;
    private List<MembershipFunctionAttrs> membershipfunctionattrsList = null; 
    private List<MembershipFunction> membershipfunctionList = null;
    private List<Rule> ruleList = null;
    private List<TSKThenPart> tskthenpartList = null;
    private List<Grade> gradeList = null;

    private HeaderPMML header;
    private Extension extension;
    private Application application;
    private DataDictAttr datadictattr;
    private DataField datafield;
    private ClassValue classvalue;
    private Interval interval;
    private frbsPMML frbs;
    private MiningField miningfield;
    private OutputField outputfield;
    private ConjunctionOperator conjunctionoperator;
    private DisjunctionOperator disjunctionoperator;
    private ImplicationOperator implicationoperator;
    private AggregationOperator aggregationoperator;
    private MembershipFunctionAttrs membershipfunctionattrs;   
    private MembershipFunction membershipfunction;
    private Rule rule;
    private TSKThenPart thenpart;
    private Grade grade;

    private final Stack<String> tagsStack = new Stack<String>();
    private final StringBuilder tempVal = new StringBuilder();
    
    /**
     * It is a constructor. 
     * @param filename a file name.
     */
    public pmmlReader(String filename){
        SAXParserFactory pmml = SAXParserFactory.newInstance();
        try {
            SAXParser fpmml = pmml.newSAXParser();
            fpmml.parse(filename, this);          
        } catch (SAXException se){
            se.printStackTrace();
        } catch (ParserConfigurationException pce){
            pce.printStackTrace();
        } catch (IOException ie){
            ie.printStackTrace();
        }
    }
    
    /**
     * It is used to get a list of header.
     * @return headerList.
     */
    public List<HeaderPMML> getHeaderList(){
            return headerList;
    }

    /**
     * It is used to get a list of extension.
     * @return extensionList.
     */
    public List<Extension> getExtensionList(){
            return extensionList;
    }
    
    /**
     * It is used to get a list of application.
     * @return applicationList.
     */
    public List<Application> getApplicationList(){
            return applicationList;
    }

    /**
     * It is used to get a list of data dictionary.
     * @return datadictionaryList.
     */
    public List<DataDictAttr> getDataDictionaryList(){
            return datadictionaryList;
    }

    /**
     * It is used to get a list of data field.
     * @return datafieldList.
     */
    public List<DataField> getDataFieldList(){
            return datafieldList;
    }
    
    /**
     * It is used to get a list of class value.
     * @return classvalueList.
     */
    public List<ClassValue> getClassValueList(){
            return classvalueList;
    }
    
    /**
     * It is used to get a list of interval.
     * @return intervalList.
     */
    public List<Interval> getIntervalList(){
            return intervalList;
    }

    /**
     * It is used to get a list of frbs 
     * @return frbsList.
     */
    public List<frbsPMML> getfrbsList(){
            return frbsList;
    }

    /**
     * It is used to get a list of mining field.
     * @return miningfieldList.
     */
    public List<MiningField> getMiningFieldList(){
            return miningfieldList;
    }

    /**
     * It is used to get a list of output field.
     * @return outputfieldList.
     */
    public List<OutputField> getOutputFieldList(){
            return outputfieldList;
    }

    /**
     * It is used to get a list of conjunction operator
     * @return conjunctionoperatorList.
     */
    public List<ConjunctionOperator> getConjunctionOperator(){
            return conjunctionoperatorList;
    }
    
    /**
     * It is used to get a list of disjunction operator.
     * @return disjunctionoperatorList.
     */
    public List<DisjunctionOperator> getDisjunctionOperator(){
            return disjunctionoperatorList;
    }
    
    /**
     * It is used to get a list of implication operator.
     * @return implicationoperatorList.
     */
    public List<ImplicationOperator> getImplicationOperator(){
            return implicationoperatorList;
    }
    
    /**
     * It is used to get a list of aggregation.
     * @return aggregationoperatorList.
     */
    public List<AggregationOperator> getAggregationOperator(){
            return aggregationoperatorList;
    }
    
     /**
     * It is used to get a list of attributes of membership function .
     * @return membershipfunctionattrsList.
     */
    public List<MembershipFunctionAttrs> getMembershipFunctionAttrs(){
            return membershipfunctionattrsList;
    }
    
     /**
     * It is used to get a list of membership function.
     * @return membershipfunctionList.
     */
    public List<MembershipFunction> getMembershipFunction(){
            return membershipfunctionList;
    }
    
    /**
     * It is used to get a list of rules.
     * @return ruleList.
     */    
    public List<Rule> getRule(){
            return ruleList;
    }
    
    /**
     * It is used to get a list of then part.
     * @return tskthenpartList.
     */    
    public List<TSKThenPart> getTSKThenPart(){
            return tskthenpartList;
    }

    /**
     * It is used to get a list of grade of certainty.
     * @return gradeList.
     */
    public List<Grade> getGrade(){
            return gradeList;
    }
    
    /**
     * It is used to start parsing.
     */
    @Override
    public void startDocument(){
            pushTag("");
    }

    private void pushTag(String tag){
            tagsStack.push(tag);
    }

    private String popTag(){
            return tagsStack.pop();
    }

    private String peekTag(){
            return tagsStack.peek();
    }

    boolean btimestamp = false;
    boolean bparam = false;
    boolean barray = false;
    boolean bgrade = false;
    
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            pushTag(qName);
            tempVal.setLength(0);
            if (TAG_HEADER.equalsIgnoreCase(qName)){
                    String copyright = attributes.getValue("copyright");
                    String description = attributes.getValue("description");
                    header = new HeaderPMML();
                    header.setCopyright(copyright);
                    header.setDescription(description);
                    if (headerList == null)
                            headerList = new ArrayList<>();				
            }

            else if (TAG_EXTENSION.equalsIgnoreCase(qName)){
                    String name = attributes.getValue("name");
                    String value = attributes.getValue("value");
                    String extender = attributes.getValue("extender");
                    extension = new Extension();			
                    extension.setName(name);			
                    extension.setValue(value);		
                    extension.setExtender(extender);
                    if (extensionList == null){
                            extensionList = new ArrayList<>();
                    }
            }
            else if (TAG_APPLICATION.equalsIgnoreCase(qName)){		
                    String name = attributes.getValue("name");
                    String version = attributes.getValue("version");
                    application = new Application();
                    application.setName(name);
                    application.setVersion(version);
                    if (applicationList == null){
                            applicationList = new ArrayList<>();
                    }
            }
            else if (TAG_TIMESTAMP.equalsIgnoreCase(qName)){
                    btimestamp = true;
            }
            else if (TAG_DATADICTIONARY.equalsIgnoreCase(qName)){
                    String numberoffields = attributes.getValue("numberOfFields");
                    datadictattr = new DataDictAttr(Integer.parseInt(numberoffields));
                    if (datadictionaryList == null){
                            datadictionaryList = new ArrayList<>();
                    }
            }
            else if (TAG_DATAFIELD.equalsIgnoreCase(qName)){
                    String name = attributes.getValue("name");
                    String optype = attributes.getValue("optype");
                    String datatype = attributes.getValue("dataType");
                    datafield = new DataField();
                    datafield.setName(name);
                    datafield.setOptype(optype);
                    datafield.setDatatype(datatype);
                    if (datafieldList== null){
                            datafieldList = new ArrayList<>();
                    }
            }
            else if (TAG_CLASSVALUE.equalsIgnoreCase(qName)){
                    String value = attributes.getValue("value");
                    classvalue = new ClassValue(value);
                     if (classvalueList== null){
                            classvalueList = new ArrayList<>();
                    }                  
            }
            else if (TAG_INTERVAL.equalsIgnoreCase(qName)){
                    String closure = attributes.getValue("closure");
                    String leftmargin = attributes.getValue("leftMargin");
                    String rightmargin = attributes.getValue("rightMargin");
                    interval = new Interval();
                    interval.setClosure(closure);
                    interval.setLeftMargin(Double.parseDouble(leftmargin));
                    interval.setRightMargin(Double.parseDouble(rightmargin));
                    if (intervalList == null){
                            intervalList = new ArrayList<>();
                    }
            }
            else if (TAG_FRBS.equalsIgnoreCase(qName)){
                    String modelname = attributes.getValue("modelName");
                    String functionname = attributes.getValue("functionName");
                    String algorithmname = attributes.getValue("algorithmName");
                    String targetfieldname = attributes.getValue("targetFieldName");
                    frbs = new frbsPMML();
                    frbs.setModelName(modelname);
                    frbs.setFunctionName(functionname);
                    frbs.setAlgorithmName(algorithmname);
                    frbs.setTargetFieldName(targetfieldname);
                    if (frbsList == null){
                            frbsList = new ArrayList<>();
                    }
            }
            else if (TAG_MININGFIELD.equalsIgnoreCase(qName)){
                    String name = attributes.getValue("name");
                    String usagetype = attributes.getValue("usageType");
                    miningfield = new MiningField();
                    miningfield.setName(name);
                    miningfield.setUsageType(usagetype);
                    if (miningfieldList == null){
                            miningfieldList = new ArrayList<>();
                    }
            }

            else if (TAG_OUTPUTFIELD.equalsIgnoreCase(qName)){
                    String name = attributes.getValue("name");
                    String optype = attributes.getValue("optype");
                    String datatype = attributes.getValue("dataType");
                    String feature = attributes.getValue("feature");
                    outputfield = new OutputField();
                    outputfield.setName(name);
                    outputfield.setOpType(optype);
                    outputfield.setDataType(datatype);
                    outputfield.setFeature(feature);
                    if (outputfieldList == null){
                            outputfieldList = new ArrayList<>();
                    }
            }
            else if (TAG_CONJUNCTIONOPERATOR.equalsIgnoreCase(qName)){
                    String value = attributes.getValue("value");
                    conjunctionoperator = new ConjunctionOperator(value);
                    if (conjunctionoperatorList == null){
                        conjunctionoperatorList = new ArrayList<>();
                    }
            }
            else if (TAG_DISJUNCTIONOPERATOR.equalsIgnoreCase(qName)){
                    String value = attributes.getValue("value");
                    disjunctionoperator = new DisjunctionOperator(value);
                    if (disjunctionoperatorList == null){
                        disjunctionoperatorList = new ArrayList<>();
                    }
            }
            else if (TAG_IMPLICATIONOPERATOR.equalsIgnoreCase(qName)){
                    String value = attributes.getValue("value");
                    implicationoperator = new ImplicationOperator(value);
                    if (implicationoperatorList == null){
                        implicationoperatorList = new ArrayList<>();
                    }
            }
            else if (TAG_AGGREGATIONOPERATOR.equalsIgnoreCase(qName)){
                    String value = attributes.getValue("value");
                    aggregationoperator = new AggregationOperator(value);
                    if (aggregationoperatorList == null){
                        aggregationoperatorList = new ArrayList<>();
                    }
            }
            else if (TAG_MEMBERSHIPFUNCTIONATTRS.equalsIgnoreCase(qName)){
                    String name = attributes.getValue("name");
                    String numberoflabels = attributes.getValue("numberOfLabels");
                    membershipfunctionattrs = new MembershipFunctionAttrs(name, numberoflabels);
                    if (membershipfunctionattrsList == null){
                        membershipfunctionattrsList = new ArrayList<>();
                    }
            }
            else if (TAG_FUZZYTERM.equalsIgnoreCase(qName)){
                    String name = attributes.getValue("name");
                    String type = attributes.getValue("type");
                    membershipfunction = new MembershipFunction(name, type);
                    if (membershipfunctionList == null){
                        membershipfunctionList = new ArrayList<>();
                    }
            }
            else if (TAG_MEAN.equalsIgnoreCase(qName)){
                    bparam = true;
            }
            else if (TAG_VARIANCE.equalsIgnoreCase(qName)){
                    bparam = true;
            }
            else if (TAG_LEFT.equalsIgnoreCase(qName)){
                    bparam = true;
            }
            else if (TAG_MIDDLE.equalsIgnoreCase(qName)){
                    bparam = true;
            }
            else if (TAG_LEFTMIDDLE.equalsIgnoreCase(qName)){
                    bparam = true;
            }
            else if (TAG_RIGHTMIDDLE.equalsIgnoreCase(qName)){
                    bparam = true;
            }
            else if (TAG_RIGHT.equalsIgnoreCase(qName)){
                    bparam = true;
            }
            else if (TAG_RULE.equalsIgnoreCase(qName)){
                    String id = attributes.getValue("id");
                    rule = new Rule(id);
                    if (ruleList == null){
                        ruleList = new ArrayList<>();
                    }
            }
            else if (TAG_COMPOUNDPREDICATE.equalsIgnoreCase(qName)){
                    String operator = attributes.getValue("booleanOperator");
                    rule.addOperator(operator);
            }
            else if (TAG_SIMPLEPREDICATE.equalsIgnoreCase(qName)){
                    String field = attributes.getValue("field");
                    String value = attributes.getValue("value");
                    rule.addVarRule(field);
                    rule.addValueRule(value);
            }
            else if (TAG_THENPART.equalsIgnoreCase(qName)){
                    String type = attributes.getValue("type");
                    thenpart = new TSKThenPart(type);
                    if (tskthenpartList == null){
                        tskthenpartList = new ArrayList<>();
                    }
            }
            else if (TAG_COEFFICIENT.equalsIgnoreCase(qName)){
                    String field = attributes.getValue("field");
                    String value = attributes.getValue("value");
                    thenpart.addField(field);
                    thenpart.addValue(value);
            }
            else if (TAG_CONSTANT.equalsIgnoreCase(qName)){
                    String field = "constant";
                    String value = attributes.getValue("value");
                    thenpart.addField(field);
                    thenpart.addValue(value);
            }
            else if (TAG_GRADE.equalsIgnoreCase(qName)){
                    grade = new Grade();
                    if (gradeList == null){
                        gradeList = new ArrayList<>();
                    }
            }             
    }


    public void endElement(String uri, String localName, String qName) throws SAXException {
            String tag = peekTag();
            if (!qName.equals(tag)){
                    throw new InternalError();
            }

            popTag();
            String parentTag = peekTag();

            if (TAG_HEADER.equalsIgnoreCase(qName)){
                    headerList.add(header);
            }
            else if (TAG_EXTENSION.equalsIgnoreCase(qName)){
                    extensionList.add(extension);
            }
            else if (TAG_APPLICATION.equalsIgnoreCase(qName)){
                    applicationList.add(application);
            }
            else if (TAG_DATADICTIONARY.equalsIgnoreCase(qName)){
                    datadictionaryList.add(datadictattr);
            }
            else if (TAG_DATAFIELD.equalsIgnoreCase(qName)){
                    datafieldList.add(datafield);
            }
            else if (TAG_CLASSVALUE.equalsIgnoreCase(qName)){
                    classvalueList.add(classvalue);
            }
            else if (TAG_INTERVAL.equalsIgnoreCase(qName)){
                    intervalList.add(interval);
            }
            else if (TAG_FRBS.equalsIgnoreCase(qName)){
                    frbsList.add(frbs);
            }
            else if (TAG_MININGFIELD.equalsIgnoreCase(qName)){
                    miningfieldList.add(miningfield);
            }
            else if (TAG_OUTPUTFIELD.equalsIgnoreCase(qName)){
                    outputfieldList.add(outputfield);
            }
            else if (TAG_CONJUNCTIONOPERATOR.equalsIgnoreCase(qName)){
                    conjunctionoperatorList.add(conjunctionoperator);
            }
            else if (TAG_DISJUNCTIONOPERATOR.equalsIgnoreCase(qName)){
                    disjunctionoperatorList.add(disjunctionoperator);
            }
            else if (TAG_IMPLICATIONOPERATOR.equalsIgnoreCase(qName)){
                    implicationoperatorList.add(implicationoperator);
            }
            else if (TAG_AGGREGATIONOPERATOR.equalsIgnoreCase(qName)){
                    aggregationoperatorList.add(aggregationoperator);
            }
            else if (TAG_MEMBERSHIPFUNCTIONATTRS.equalsIgnoreCase(qName)){
                    membershipfunctionattrsList.add(membershipfunctionattrs);
            }
            else if (TAG_FUZZYTERM.equalsIgnoreCase(qName)){
                    membershipfunctionList.add(membershipfunction);
            }
            else if (TAG_RULE.equalsIgnoreCase(qName)){
                    ruleList.add(rule);
            }
            else if (TAG_THENPART.equalsIgnoreCase(qName)){
                    tskthenpartList.add(thenpart);
            }
            else if (TAG_GRADE.equalsIgnoreCase(qName)){
                    String gradeVal = tempVal.toString().trim();
                    grade.addGrade(gradeVal);
                    gradeList.add(grade);
            }    
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException{
        tempVal.append(ch, start, length);
        
        if (btimestamp){
                header.setTimestamp(new String(ch, start, length));
                btimestamp = false;
        }
        else if (bparam){
                membershipfunction.addValue(new String(ch, start, length));
                bparam = false;
        }
        else if (bgrade){
                grade.addGrade(new String(ch, start, length));
                bgrade = false;
        }
    }
}

class Grade {
    private List<String> value = new ArrayList<String>();
    
    public Grade(){
        
    }
    public void addGrade(String value){
        this.value.add(value);
    }
    
    public List<String> getValue(){
        return value;
    }
    
    @Override
    public String toString(){
            return "Grade:: value= "+this.value;
    }
}

class TSKThenPart {
    private String type;
    private List<String> value = new ArrayList<String>();
    private List<String> field = new ArrayList<String>();
    public TSKThenPart(String type){
        this.type = type;
    }
    public void addType(String type){
        this.type = type;
    }
    public void addValue(String value){
        this.value.add(value);
    }
    public void addField(String field){
        this.field.add(field);
    }
    public List<String> getValue(){
        return value;
    }
    public List<String> getField(){
        return field;
    }
    public String getType(){
        return type;
    }
     @Override
    public String toString(){
            return "Then:: type= "+this.type + " field " + this.field + " value: " + this.value;
    }
}

class Rule{
    private String id;
    private String predicate;
    private List<String> operator = new ArrayList<String>();
    private List<String> varRule = new ArrayList<String>();
    private List<String> valueRule = new ArrayList<String>();
    
    public Rule(String id){
        this.id = id;
    }

    public void addVarRule(String varName){
        this.varRule.add(varName);
    }
    public void addValueRule(String valueTerm){
        this.valueRule.add(valueTerm);
    } 
    public void addOperator(String operator){
        this.operator.add(operator);
    }
    public List<String> getVarRule(){
        return varRule;
    }
    public List<String> getValueRule(){
        return valueRule;
    }
    public List<String> getOperator(){
        return operator;
    }
    @Override
    public String toString(){
            return "Rule:: id= "+this.id + " varRule: " + this.varRule + " valueRule " + this.valueRule + " operator " + this.operator;
    }
}
        
class MembershipFunction{
    private String name;
    private String type;
    private String value;
    
    public MembershipFunction(String name, String type){
        this.name = name;
        this.type = type;
    }
    
    public void addValue(String newValue){
        this.value = this.value + " " + newValue;
    }   
    public String getName(){
        return name;
    }   
    public String getType(){
        return type;
    }
    public String getValue(){
        return value;
    }
    @Override
    public String toString(){
            return "MembershipFunction:: name= "+this.name + " type: " + this.type + " value: " + this.value;
    }
}

class MembershipFunctionAttrs{
    private String name;
    private String numberoflabels;
    
    public MembershipFunctionAttrs(String name, String numberoflabels){
        this.name = name;
        this.numberoflabels = numberoflabels;
    }
    public String getName(){
        return name;
    }
    public String getNumberOfLabels(){
        return numberoflabels;
    }
    
    @Override
    public String toString(){
            return "MembershipFunction:: name= "+this.name + " numberOfLabels: " + this.numberoflabels;
    }
}

class OutputField {
    private String name;
    private String optype;
    private String datatype;
    private String feature;

    public OutputField(){

    }
    public OutputField(String name, String optype, String datatype, String feature){
            this.name = name;
            this.optype = optype;
            this.datatype = datatype;
            this.feature = feature;
    }

    public String getName(){
            return name;
    }

    public void setName(String name){
            this.name = name;
    }
    public String getOpType(){
            return optype;
    }

    public void setOpType(String optype){
            this.optype = optype;
    }
    public String getDataType(){
            return datatype;
    }

    public void setDataType(String datatype){
            this.datatype = datatype;
    }
    public String getFeature(){
            return feature;
    }

    public void setFeature(String feature){
            this.feature = feature;
    }

    @Override
    public String toString(){
            return "Output Field:: name= "+this.name + " optype: " + this.optype + " dataType: " + this.datatype + " feature: " + this.feature;

    }
}

class MiningField {
    private String name;
    private String usagetype;

    public MiningField(){

    }
    public MiningField(String name, String usagetype){
            this.name = name;
            this.usagetype = usagetype;
    }

    public String getName(){
            return name;
    }

    public void setName(String name){
            this.name = name;
    }
    public String getUsageType(){
            return usagetype;
    }

    public void setUsageType(String usagetype){
            this.usagetype = usagetype;
    }


    @Override
    public String toString(){
            return "Mining Field:: name= "+this.name + " usageType: " + this.usagetype;

    }
}

class Interval {
    private String closure;
    private double leftMargin;
    private double rightMargin;

    public Interval(){

    }
    public Interval(String closure, double leftMargin, double rightMargin){
            this.closure = closure;
            this.leftMargin = leftMargin;
            this.rightMargin = rightMargin;
    }

    public String getClosure(){
            return closure;
    }
    public void setClosure(String closure){
            this.closure = closure;
    }
    public double getLeftMargin(){
            return leftMargin;
    }
    public void setLeftMargin(double leftMargin){
            this.leftMargin = leftMargin;
    }
    public double getRightMargin(){
            return rightMargin;
    }
    public void setRightMargin(double rightMargin){
            this.rightMargin = rightMargin;
    }

    @Override
    public String toString(){
            return "Interval:: closure= "+this.closure+" leftMargin "+this.leftMargin +" rightMargin= "+this.rightMargin;

    }
}

class ConjunctionOperator{
    private String value;
    
    public ConjunctionOperator(String value){
        this.value = value;
    }
    
    public String getValue(){
        return value;
    }
    
    @Override
    public String toString(){
          return "Conjunction Operator::" + this.value;
    }
}

class DisjunctionOperator{
    private String value;
    
    public DisjunctionOperator(String value){
        this.value = value;
    }
    
    public String getValue(){
        return value;
    }
    
    @Override
    public String toString(){
          return "Disjunction Operator::" + this.value;
    }
}

class ImplicationOperator{
    private String value;
    
    public ImplicationOperator(String value){
        this.value = value;
    }
    
    public String getValue(){
        return value;
    }
    
    @Override
    public String toString(){
          return "Implication Operator::" + this.value;
    }
}
    
class AggregationOperator{
    private String value;
    
    public AggregationOperator(String value){
        this.value = value;
    }
    
    public String getValue(){
        return value;
    }
    
    @Override
    public String toString(){
          return "Aggregation Operator::" + this.value;
    }
}


class HeaderPMML {
    private String copyright;
    private String description;
    private String timestamp;

    public HeaderPMML(){
    } 
    public HeaderPMML(String copyright, String description, String timestamp){
            this.copyright = copyright;
            this.description = description;
            this.timestamp = timestamp;
    }
    public String getCopyright(){
            return copyright;
    }

    public void setCopyright(String copyright){
            this.copyright = copyright;
    }
    public String getDescription(){
            return description;
    }

    public void setDescription(String description){
            this.description = description;
    }
    public String getTimestamp(){
            return timestamp;
    }

    public void setTimestamp(String timestamp){
            this.timestamp = timestamp;
    }


    @Override
    public String toString(){
            return "Header:: copyright= "+this.copyright + " description: "+ this.description + " timestamp : " + this.timestamp;

    }
}

class frbsPMML {
    private String modelname;
    private String functionname;
    private String algorithmname;
    private String targetfieldname;

    public frbsPMML(){

    }
    public frbsPMML(String modelname, String functionname, String algorithmname, String targetfieldname){
            this.modelname = modelname;
            this.functionname = functionname;
            this.algorithmname = algorithmname;
            this.targetfieldname = targetfieldname;
    }
    public String getModelName(){
            return modelname;
    }

    public void setModelName(String modelname){
            this.modelname = modelname;
    }
    public String getFunctionName(){
            return functionname;
    }

    public void setFunctionName(String functionname){
            this.functionname = functionname;
    }
    public String getAlgorithmName(){
            return algorithmname;
    }

    public void setAlgorithmName(String algorithmname){
            this.algorithmname = algorithmname;
    }
    public String getTargetFieldName(){
            return targetfieldname;
    }

    public void setTargetFieldName(String targetfieldname){
            this.targetfieldname = targetfieldname;
    }

    @Override
    public String toString(){
            return "frbs:: modelname= "+this.modelname + " function name: " + this.functionname + " algorithm name: "+this.algorithmname;

    }
}

class Extension {
    private String name;
    private String value;
    private String extender;

    public Extension(){

    }
    public Extension(String name, String value, String extender){
            this.name = name;
            this.value = value;
            this.extender = extender;
    }
    public String getName(){
            return name;
    }

    public void setName(String name){
            this.name = name;
    }
    public String getValue(){
            return value;
    }

    public void setValue(String value){
            this.value = value;
    }
    public String getExtender(){
            return extender;
    }

    public void setExtender(String extender){
            this.extender = extender;
    }

    @Override
    public String toString(){
            return "Extension:: name= "+this.name + " value: "+ this.value + " extender: " + this.extender;

    }
}

class ClassValue{
    private String value;
    
    public ClassValue(String value){
        this.value = value;
    }
    public String getValue(){
            return value;
    }
    @Override
    public String toString(){
            return " Class Values:: " + " value " + this.value;

    }
}

class DataField {
    private String name;
    private String optype;
    private String dataType;

    public DataField(){

    }
    public DataField(String name, String optype, String dataType){
            this.name = name;
            this.optype = optype;
            this.dataType = dataType;
    }

    public String getName(){
            return name;
    }
    public void setName(String name){
            this.name = name;
    }
    public String getOptype(){
            return optype;
    }
    public void setOptype(String optype){
            this.optype = optype;
    }
    public String getDataType(){
            return dataType;
    }
    public void setDatatype(String dataType){
            this.dataType = dataType;
    }

    @Override
    public String toString(){
            return " DataField:: " + " Name " + this.name +" optype= " + this.optype +
                            " dataType = "+ this.dataType;

    }
}

class DataDictAttr {
    private int numberOfFields;
    public DataDictAttr(){        
    }   
    public DataDictAttr(int numberOfFields){
        this.numberOfFields = numberOfFields;
    }
    public int getNumberOfFields(){
        return numberOfFields;
    }
    @Override
    public String toString(){
            return "DataDictionaryAttribute:: numberOfFields= "+this.numberOfFields;
    }
}

class Application {
    private String name;
    private String version;


    public Application(){

    }
    public Application(String name, String version){
            this.name = name;
            this.version = version;
    }
    public String getName(){
            return name;
    }

    public void setName(String name){
            this.name = name;
    }
    public String getVersion(){
            return version;
    }

    public void setVersion(String version){
            this.version = version;
    }


    @Override
    public String toString(){
            return "Application:: name= "+this.name + "v ersion: "+ this.version;

    }
}

