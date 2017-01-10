package jfml.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import jfml.aggregated.AndAggregatedType;
import jfml.aggregated.OrAggregatedType;
import jfml.knowledgebase.KnowledgeBaseType;
import jfml.knowledgebase.variable.AggregatedFuzzyVariableType;
import jfml.knowledgebase.variable.AnYaDataCloudType;
import jfml.knowledgebase.variable.FuzzyVariableType;
import jfml.knowledgebase.variable.TskVariableType;
import jfml.knowledgebase.variable.TsukamotoVariableType;
import jfml.membershipfunction.CircularDefinitionType;
import jfml.membershipfunction.CustomShapeType;
import jfml.membershipfunction.ParameterType;
import jfml.membershipfunction.PointSetMonotonicShapeType;
import jfml.membershipfunction.PointSetShapeType;
import jfml.membershipfunction.PointType;
import jfml.operator.AndLogicalType;
import jfml.operator.OrLogicalType;
import jfml.parameter.FourParamType;
import jfml.parameter.OneParamType;
import jfml.parameter.ThreeParamType;
import jfml.parameter.TwoParamType;
import jfml.rule.AnYaAntecedentType;
import jfml.rule.AnYaRuleType;
import jfml.rule.AntecedentType;
import jfml.rule.ClauseType;
import jfml.rule.ConsequentClausesType;
import jfml.rule.ConsequentType;
import jfml.rule.FuzzyRuleType;
import jfml.rule.TskClauseType;
import jfml.rule.TskConsequentClausesType;
import jfml.rule.TskConsequentType;
import jfml.rule.TskFuzzyRuleType;
import jfml.rulebase.AnYaRuleBaseType;
import jfml.rulebase.FuzzySystemRuleBase;
import jfml.rulebase.RuleBaseType;
import jfml.rulebase.TskRuleBaseType;
import jfml.term.AggregatedFuzzyTermType;
import jfml.term.CircularTermType;
import jfml.term.FuzzyTermType;
import jfml.term.TskTermType;
import jfml.term.TsukamotoTermType;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the jfml package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 * @author sotillo19
 */
@XmlRegistry
public class ObjectFactory {
    private final static QName _FuzzySystem_QNAME = new QName("http://www.ieee1855.org", "fuzzySystem");
    private final static QName _AndLogicalTypeTermName_QNAME = new QName("http://www.ieee1855.org", "termName");
    private final static QName _AndLogicalTypeOr_QNAME = new QName("http://www.ieee1855.org", "or");
    private final static QName _AndLogicalTypeAnd_QNAME = new QName("http://www.ieee1855.org", "and");
    private final static QName _KnowledgeBaseTypeTsukamotoVariable_QNAME = new QName("http://www.ieee1855.org", "tsukamotoVariable");
    private final static QName _KnowledgeBaseTypeFuzzyVariable_QNAME = new QName("http://www.ieee1855.org", "fuzzyVariable");
    private final static QName _KnowledgeBaseTypeAnYaDataCloud_QNAME = new QName("http://www.ieee1855.org", "anYaDataCloud");
    private final static QName _KnowledgeBaseTypeAggregatedFuzzyVariable_QNAME = new QName("http://www.ieee1855.org", "aggregatedFuzzyVariable");
    private final static QName _KnowledgeBaseTypeTskVariable_QNAME = new QName("http://www.ieee1855.org", "tskVariable");
    private final static QName _OrAggregatedTypeClause_QNAME = new QName("http://www.ieee1855.org", "clause");
    private final static QName _FuzzySystemTypeAnYaRuleBase_QNAME = new QName("http://www.ieee1855.org", "anYaRuleBase");
    private final static QName _FuzzySystemTypeMamdaniRuleBase_QNAME = new QName("http://www.ieee1855.org", "mamdaniRuleBase");
    private final static QName _FuzzySystemTypeTsukamotoRuleBase_QNAME = new QName("http://www.ieee1855.org", "tsukamotoRuleBase");
    private final static QName _FuzzySystemTypeTskRuleBase_QNAME = new QName("http://www.ieee1855.org", "tskRuleBase");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: jfml
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FuzzySystemType }
     * 
     */
    public FuzzySystemType createFuzzySystemType() {
        return new FuzzySystemType();
    }

    /**
     * Create an instance of {@link TskFuzzyRuleType }
     * 
     */
    public TskFuzzyRuleType createTskFuzzyRuleType() {
        return new TskFuzzyRuleType();
    }

    /**
     * Create an instance of {@link AndLogicalType }
     * 
     */
    public AndLogicalType createAndLogicalType() {
        return new AndLogicalType();
    }

    /**
     * Create an instance of {@link AnYaRuleBaseType }
     * 
     */
    public AnYaRuleBaseType createAnYaRuleBaseType() {
        return new AnYaRuleBaseType();
    }

    /**
     * Create an instance of {@link AnYaDataCloudType }
     * 
     */
    public AnYaDataCloudType createAnYaDataCloudType() {
        return new AnYaDataCloudType();
    }

    /**
     * Create an instance of {@link CircularDefinitionType }
     * 
     */
    public CircularDefinitionType createCircularDefinitionType() {
        return new CircularDefinitionType();
    }

    /**
     * Create an instance of {@link ClauseType }
     * 
     */
    public ClauseType createClauseType() {
        return new ClauseType();
    }

    /**
     * Create an instance of {@link OneParamType }
     * 
     */
    public OneParamType createOneParamType() {
        return new OneParamType();
    }

    /**
     * Create an instance of {@link TskTermType }
     * 
     */
    public TskTermType createTskTermType() {
        return new TskTermType();
    }

    /**
     * Create an instance of {@link FuzzyRuleType }
     * 
     */
    public FuzzyRuleType createFuzzyRuleType() {
        return new FuzzyRuleType();
    }

    /**
     * Create an instance of {@link ThreeParamType }
     * 
     */
    public ThreeParamType createThreeParamType() {
        return new ThreeParamType();
    }

    /**
     * Create an instance of {@link FuzzyVariableType }
     * 
     */
    public FuzzyVariableType createFuzzyVariableType() {
        return new FuzzyVariableType();
    }

    /**
     * Create an instance of {@link RuleBaseType }
     * 
     */
    public RuleBaseType createRuleBaseType() {
        return new RuleBaseType();
    }

    /**
     * Create an instance of {@link ConsequentType }
     * 
     */
    public ConsequentType createConsequentType() {
        return new ConsequentType();
    }

    /**
     * Create an instance of {@link TskConsequentClausesType }
     * 
     */
    public TskConsequentClausesType createTskConsequentClausesType() {
        return new TskConsequentClausesType();
    }

    /**
     * Create an instance of {@link TskVariableType }
     * 
     */
    public TskVariableType createTskVariableType() {
        return new TskVariableType();
    }

    /**
     * Create an instance of {@link AntecedentType }
     * 
     */
    public AntecedentType createAntecedentType() {
        return new AntecedentType();
    }

    /**
     * Create an instance of {@link PointType }
     * 
     */
    public PointType createPointType() {
        return new PointType();
    }

    /**
     * Create an instance of {@link KnowledgeBaseType }
     * 
     */
    public KnowledgeBaseType createKnowledgeBaseType() {
        return new KnowledgeBaseType();
    }

    /**
     * Create an instance of {@link OrLogicalType }
     * 
     */
    public OrLogicalType createOrLogicalType() {
        return new OrLogicalType();
    }

    /**
     * Create an instance of {@link ConsequentClausesType }
     * 
     */
    public ConsequentClausesType createConsequentClausesType() {
        return new ConsequentClausesType();
    }

    /**
     * Create an instance of {@link CustomShapeType }
     * 
     */
    public CustomShapeType createCustomShapeType() {
        return new CustomShapeType();
    }

    /**
     * Create an instance of {@link CircularTermType }
     * 
     */
    public CircularTermType createCircularTermType() {
        return new CircularTermType();
    }

    /**
     * Create an instance of {@link FourParamType }
     * 
     */
    public FourParamType createFourParamType() {
        return new FourParamType();
    }

    /**
     * Create an instance of {@link TskRuleBaseType }
     * 
     */
    public TskRuleBaseType createTskRuleBaseType() {
        return new TskRuleBaseType();
    }

    /**
     * Create an instance of {@link FuzzyTermType }
     * 
     */
    public FuzzyTermType createFuzzyTermType() {
        return new FuzzyTermType();
    }

    /**
     * Create an instance of {@link AndAggregatedType }
     * 
     */
    public AndAggregatedType createAndAggregatedType() {
        return new AndAggregatedType();
    }

    /**
     * Create an instance of {@link OrAggregatedType }
     * 
     */
    public OrAggregatedType createOrAggregatedType() {
        return new OrAggregatedType();
    }

    /**
     * Create an instance of {@link AggregatedFuzzyTermType }
     * 
     */
    public AggregatedFuzzyTermType createAggregatedFuzzyTermType() {
        return new AggregatedFuzzyTermType();
    }

    /**
     * Create an instance of {@link ParameterType }
     * 
     */
    public ParameterType createParameterType() {
        return new ParameterType();
    }

    /**
     * Create an instance of {@link TsukamotoVariableType }
     * 
     */
    public TsukamotoVariableType createTsukamotoVariableType() {
        return new TsukamotoVariableType();
    }

    /**
     * Create an instance of {@link TsukamotoTermType }
     * 
     */
    public TsukamotoTermType createTsukamotoTermType() {
        return new TsukamotoTermType();
    }

    /**
     * Create an instance of {@link PointSetMonotonicShapeType }
     * 
     */
    public PointSetMonotonicShapeType createPointSetMonotonicShapeType() {
        return new PointSetMonotonicShapeType();
    }

    /**
     * Create an instance of {@link AnYaRuleType }
     * 
     */
    public AnYaRuleType createAnYaRuleType() {
        return new AnYaRuleType();
    }

    /**
     * Create an instance of {@link TskClauseType }
     * 
     */
    public TskClauseType createTskClauseType() {
        return new TskClauseType();
    }

    /**
     * Create an instance of {@link AnYaAntecedentType }
     * 
     */
    public AnYaAntecedentType createAnYaAntecedentType() {
        return new AnYaAntecedentType();
    }

    /**
     * Create an instance of {@link TwoParamType }
     * 
     */
    public TwoParamType createTwoParamType() {
        return new TwoParamType();
    }

    /**
     * Create an instance of {@link TskConsequentType }
     * 
     */
    public TskConsequentType createTskConsequentType() {
        return new TskConsequentType();
    }

    /**
     * Create an instance of {@link PointSetShapeType }
     * 
     */
    public PointSetShapeType createPointSetShapeType() {
        return new PointSetShapeType();
    }

    /**
     * Create an instance of {@link AggregatedFuzzyVariableType }
     * 
     */
    public AggregatedFuzzyVariableType createAggregatedFuzzyVariableType() {
        return new AggregatedFuzzyVariableType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FuzzySystemType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ieee1855.org", name = "fuzzySystem")
    public JAXBElement<FuzzySystemType> createFuzzySystem(FuzzySystemType value) {
        return new JAXBElement<FuzzySystemType>(_FuzzySystem_QNAME, FuzzySystemType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CircularTermType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ieee1855.org", name = "termName", scope = AndLogicalType.class)
    public JAXBElement<CircularTermType> createAndLogicalTypeTermName(CircularTermType value) {
        return new JAXBElement<CircularTermType>(_AndLogicalTypeTermName_QNAME, CircularTermType.class, AndLogicalType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrLogicalType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ieee1855.org", name = "or", scope = AndLogicalType.class)
    public JAXBElement<OrLogicalType> createAndLogicalTypeOr(OrLogicalType value) {
        return new JAXBElement<OrLogicalType>(_AndLogicalTypeOr_QNAME, OrLogicalType.class, AndLogicalType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AndLogicalType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ieee1855.org", name = "and", scope = AndLogicalType.class)
    public JAXBElement<AndLogicalType> createAndLogicalTypeAnd(AndLogicalType value) {
        return new JAXBElement<AndLogicalType>(_AndLogicalTypeAnd_QNAME, AndLogicalType.class, AndLogicalType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CircularTermType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ieee1855.org", name = "termName", scope = OrLogicalType.class)
    public JAXBElement<CircularTermType> createOrLogicalTypeTermName(CircularTermType value) {
        return new JAXBElement<CircularTermType>(_AndLogicalTypeTermName_QNAME, CircularTermType.class, OrLogicalType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrLogicalType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ieee1855.org", name = "or", scope = OrLogicalType.class)
    public JAXBElement<OrLogicalType> createOrLogicalTypeOr(OrLogicalType value) {
        return new JAXBElement<OrLogicalType>(_AndLogicalTypeOr_QNAME, OrLogicalType.class, OrLogicalType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AndLogicalType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ieee1855.org", name = "and", scope = OrLogicalType.class)
    public JAXBElement<AndLogicalType> createOrLogicalTypeAnd(AndLogicalType value) {
        return new JAXBElement<AndLogicalType>(_AndLogicalTypeAnd_QNAME, AndLogicalType.class, OrLogicalType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TsukamotoVariableType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ieee1855.org", name = "tsukamotoVariable", scope = KnowledgeBaseType.class)
    public JAXBElement<TsukamotoVariableType> createKnowledgeBaseTypeTsukamotoVariable(TsukamotoVariableType value) {
        return new JAXBElement<TsukamotoVariableType>(_KnowledgeBaseTypeTsukamotoVariable_QNAME, TsukamotoVariableType.class, KnowledgeBaseType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FuzzyVariableType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ieee1855.org", name = "fuzzyVariable", scope = KnowledgeBaseType.class)
    public JAXBElement<FuzzyVariableType> createKnowledgeBaseTypeFuzzyVariable(FuzzyVariableType value) {
        return new JAXBElement<FuzzyVariableType>(_KnowledgeBaseTypeFuzzyVariable_QNAME, FuzzyVariableType.class, KnowledgeBaseType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnYaDataCloudType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ieee1855.org", name = "anYaDataCloud", scope = KnowledgeBaseType.class)
    public JAXBElement<AnYaDataCloudType> createKnowledgeBaseTypeAnYaDataCloud(AnYaDataCloudType value) {
        return new JAXBElement<AnYaDataCloudType>(_KnowledgeBaseTypeAnYaDataCloud_QNAME, AnYaDataCloudType.class, KnowledgeBaseType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AggregatedFuzzyVariableType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ieee1855.org", name = "aggregatedFuzzyVariable", scope = KnowledgeBaseType.class)
    public JAXBElement<AggregatedFuzzyVariableType> createKnowledgeBaseTypeAggregatedFuzzyVariable(AggregatedFuzzyVariableType value) {
        return new JAXBElement<AggregatedFuzzyVariableType>(_KnowledgeBaseTypeAggregatedFuzzyVariable_QNAME, AggregatedFuzzyVariableType.class, KnowledgeBaseType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TskVariableType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ieee1855.org", name = "tskVariable", scope = KnowledgeBaseType.class)
    public JAXBElement<TskVariableType> createKnowledgeBaseTypeTskVariable(TskVariableType value) {
        return new JAXBElement<TskVariableType>(_KnowledgeBaseTypeTskVariable_QNAME, TskVariableType.class, KnowledgeBaseType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrAggregatedType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ieee1855.org", name = "or", scope = OrAggregatedType.class)
    public JAXBElement<OrAggregatedType> createOrAggregatedTypeOr(OrAggregatedType value) {
        return new JAXBElement<OrAggregatedType>(_AndLogicalTypeOr_QNAME, OrAggregatedType.class, OrAggregatedType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClauseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ieee1855.org", name = "clause", scope = OrAggregatedType.class)
    public JAXBElement<ClauseType> createOrAggregatedTypeClause(ClauseType value) {
        return new JAXBElement<ClauseType>(_OrAggregatedTypeClause_QNAME, ClauseType.class, OrAggregatedType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AndAggregatedType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ieee1855.org", name = "and", scope = OrAggregatedType.class)
    public JAXBElement<AndAggregatedType> createOrAggregatedTypeAnd(AndAggregatedType value) {
        return new JAXBElement<AndAggregatedType>(_AndLogicalTypeAnd_QNAME, AndAggregatedType.class, OrAggregatedType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnYaRuleBaseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ieee1855.org", name = "anYaRuleBase", scope = FuzzySystemType.class)
    public JAXBElement<AnYaRuleBaseType> createFuzzySystemTypeAnYaRuleBase(AnYaRuleBaseType value) {
        return new JAXBElement<AnYaRuleBaseType>(_FuzzySystemTypeAnYaRuleBase_QNAME, AnYaRuleBaseType.class, FuzzySystemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RuleBaseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ieee1855.org", name = "mamdaniRuleBase", scope = FuzzySystemType.class)
    public JAXBElement<RuleBaseType> createFuzzySystemTypeMamdaniRuleBase(RuleBaseType value) {
    	value.setRuleBaseSystemType(FuzzySystemRuleBase.TYPE_MAMDANI);
        return new JAXBElement<RuleBaseType>(_FuzzySystemTypeMamdaniRuleBase_QNAME, RuleBaseType.class, FuzzySystemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RuleBaseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ieee1855.org", name = "tsukamotoRuleBase", scope = FuzzySystemType.class)
    public JAXBElement<RuleBaseType> createFuzzySystemTypeTsukamotoRuleBase(RuleBaseType value) {
    	value.setRuleBaseSystemType(FuzzySystemRuleBase.TYPE_TSUKAMOTO);
        return new JAXBElement<RuleBaseType>(_FuzzySystemTypeTsukamotoRuleBase_QNAME, RuleBaseType.class, FuzzySystemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TskRuleBaseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ieee1855.org", name = "tskRuleBase", scope = FuzzySystemType.class)
    public JAXBElement<TskRuleBaseType> createFuzzySystemTypeTskRuleBase(TskRuleBaseType value) {
        return new JAXBElement<TskRuleBaseType>(_FuzzySystemTypeTskRuleBase_QNAME, TskRuleBaseType.class, FuzzySystemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrAggregatedType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ieee1855.org", name = "or", scope = AndAggregatedType.class)
    public JAXBElement<OrAggregatedType> createAndAggregatedTypeOr(OrAggregatedType value) {
        return new JAXBElement<OrAggregatedType>(_AndLogicalTypeOr_QNAME, OrAggregatedType.class, AndAggregatedType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClauseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ieee1855.org", name = "clause", scope = AndAggregatedType.class)
    public JAXBElement<ClauseType> createAndAggregatedTypeClause(ClauseType value) {
        return new JAXBElement<ClauseType>(_OrAggregatedTypeClause_QNAME, ClauseType.class, AndAggregatedType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AndAggregatedType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ieee1855.org", name = "and", scope = AndAggregatedType.class)
    public JAXBElement<AndAggregatedType> createAndAggregatedTypeAnd(AndAggregatedType value) {
        return new JAXBElement<AndAggregatedType>(_AndLogicalTypeAnd_QNAME, AndAggregatedType.class, AndAggregatedType.class, value);
    }

}
