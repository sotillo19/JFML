package jfml.compatibility;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import jfml.FuzzyInferenceSystem;
import jfml.enumeration.InterpolationMethodType;
import jfml.knowledgebase.KnowledgeBaseType;
import jfml.knowledgebase.variable.FuzzyVariableType;
import jfml.knowledgebase.variable.KnowledgeBaseVariable;
import jfml.membershipfunction.PointType;
import jfml.rule.AntecedentType;
import jfml.rule.ConsequentType;
import jfml.rule.FuzzyRuleType;
import jfml.rulebase.MamdaniRuleBaseType;
import jfml.term.FuzzyTermType;

/**
 * This class allows to import a fuzzy system in format FCL (IEC 1131).
 * 
 * @author Jesus Alcala-Fdez
 *
 */
public class ImportFCL extends Import {

	/**
	 * Constructor by default
	 */
	public ImportFCL() {
	}

	@Override
	public FuzzyInferenceSystem importFuzzySystem(String path) throws java.io.IOException {
		String fuzzySystemFCL, word, nameVariable, nameTerm, defMethod, acc, defaultValue;
		String andMethod, orMethod, actMethod;
		List<String> basicRules;
		StringTokenizer line, lines;
		int nRuleBase;
		float domainLeft, domainRight;
		float[] oneParam;
		PointType point;
		FuzzyInferenceSystem fuzzySystemIEEE;
		KnowledgeBaseType kb;
		FuzzyVariableType var;
		FuzzyTermType term;
		MamdaniRuleBaseType rb;
		FuzzyRuleType rule;
		AntecedentType antecedent;
		ConsequentType consequent;

		nRuleBase = 0;
		oneParam = new float[1];

		fuzzySystemFCL = readFile(path);
		fuzzySystemFCL = cleanFCL(fuzzySystemFCL);
		if (fuzzySystemFCL == null) {
			return null;
		}

		lines = new StringTokenizer(fuzzySystemFCL, "\n\r");
		line = new StringTokenizer(lines.nextToken(), "\t ");

		while (!line.nextToken().equalsIgnoreCase("FUNCTION_BLOCK")) {
			line = new StringTokenizer(lines.nextToken(), "\t ");
		}

		fuzzySystemIEEE = new FuzzyInferenceSystem(line.nextToken());

		// KNOWLEDGE BASE
		kb = new KnowledgeBaseType();
		fuzzySystemIEEE.setKnowledgeBase(kb);

		while (lines.hasMoreTokens()) {
			line = new StringTokenizer(lines.nextToken(), "\t ");
			word = line.nextToken();

			// Read the VAR_INPUT block
			if (word.equalsIgnoreCase("VAR_INPUT")) {
				line = new StringTokenizer(lines.nextToken(), ":\t ");
				word = line.nextToken();
				while (!word.equalsIgnoreCase("END_VAR")) {
					var = new FuzzyVariableType(word, Float.MIN_VALUE, Float.MAX_VALUE);
					var.setType("input");
					kb.addVariable(var);

					line = new StringTokenizer(lines.nextToken(), ":\t ");
					word = line.nextToken();
				}
			}
			// Read the VAR_OUTPUT block
			else if (word.equalsIgnoreCase("VAR_OUTPUT")) {
				line = new StringTokenizer(lines.nextToken(), ":\t ");
				word = line.nextToken();
				while (!word.equalsIgnoreCase("END_VAR")) {
					var = new FuzzyVariableType(word, Float.MIN_VALUE, Float.MAX_VALUE);
					var.setType("output");
					kb.addVariable(var);

					line = new StringTokenizer(lines.nextToken(), ":\t ");
					word = line.nextToken();
				}
			}
			// Read the terms of a input variable
			else if (word.equalsIgnoreCase("FUZZIFY")) {
				nameVariable = line.nextToken(); // Variable's name
				var = (FuzzyVariableType) kb.getVariable(nameVariable);

				line = new StringTokenizer(lines.nextToken(), ", ;()\t");
				word = line.nextToken();

				while (!word.equalsIgnoreCase("END_FUZZIFY")) {
					if (word.equalsIgnoreCase("TERM")) {
						nameTerm = line.nextToken(); // Term's name
						line.nextToken(); // :=

						List<PointType> points = new ArrayList<PointType>();

						while (line.hasMoreTokens()) {
							point = new PointType(Float.parseFloat(line.nextToken()),
									Float.parseFloat(line.nextToken()));
							points.add(point);
						}

						term = new FuzzyTermType(nameTerm, FuzzyTermType.TYPE_pointSetShape, points);
						term.getPointSetShape().setInterpolationMethod(InterpolationMethodType.LINEAR);
						term.getPointSetShape().setDegree(1);
						var.addFuzzyTerm(term);
					}

					line = new StringTokenizer(lines.nextToken(), ", ;()\t");
					word = line.nextToken();
				}
			}
			// Read the terms of a output variable
			else if (word.equalsIgnoreCase("DEFUZZIFY")) {
				nameVariable = line.nextToken(); // Variable's name
				var = (FuzzyVariableType) kb.getVariable(nameVariable);

				line = new StringTokenizer(lines.nextToken(), ", ;()\t");
				word = line.nextToken();

				while (!word.equalsIgnoreCase("END_DEFUZZIFY")) {
					if (word.equalsIgnoreCase("TERM")) {
						nameTerm = line.nextToken(); // Term's name
						line.nextToken(); // :=

						oneParam[0] = Float.parseFloat(line.nextToken());

						term = new FuzzyTermType(nameTerm, FuzzyTermType.TYPE_singletonShape, oneParam);
						var.addFuzzyTerm(term);

					} else if (word.equalsIgnoreCase("METHOD")) {
						line.nextToken(); // :=
						defMethod = line.nextToken(); // Defuzzification method
						if (defMethod.equalsIgnoreCase("COGS"))
							defMethod = "COG";
						var.setDefuzzifierName(defMethod);
					} else if (word.equalsIgnoreCase("DEFAULT")) {
						line.nextToken(); // :=
						defaultValue = line.nextToken(); // Default value

						var.setDefaultValue(Float.parseFloat(defaultValue));
					} else if (word.equalsIgnoreCase("RANGE")) {
						line.nextToken(); // :=
						domainLeft = (Float.parseFloat(line.nextToken())); // Minimum
																			// value
						line.nextToken(); // ..
						domainRight = (Float.parseFloat(line.nextToken())); // Maximum
																			// value
						var.setDomainleft(domainLeft);
						var.setDomainright(domainRight);
					}

					line = new StringTokenizer(lines.nextToken(), ", ;()\t");
					word = line.nextToken();
				}

			}
			// Read a rule block
			else if (word.equalsIgnoreCase("RULEBLOCK")) {
				nRuleBase++;
				rb = new MamdaniRuleBaseType("rulebase" + nRuleBase);
				fuzzySystemIEEE.addRuleBase(rb);

				line = new StringTokenizer(lines.nextToken(), ", ;\t");
				word = line.nextToken();

				while (!word.equalsIgnoreCase("END_RULEBLOCK")) {
					// And method
					if (word.equalsIgnoreCase("AND")) {
						line.nextToken(); // :
						andMethod = line.nextToken();
						rb.setAndMethod(andMethod);
					}
					// Or method
					else if (word.equalsIgnoreCase("OR")) {
						line.nextToken(); // :
						orMethod = line.nextToken();
						if (orMethod.equalsIgnoreCase("ASUM"))
							orMethod = "PROBOR";
						rb.setAndMethod(orMethod);
					}
					// Activation method
					else if (word.equalsIgnoreCase("ACT")) {
						line.nextToken(); // :
						actMethod = line.nextToken();
						rb.setActivationMethod(actMethod);
					}
					// Accumulation method
					else if (word.equalsIgnoreCase("ACCU")) {
						line.nextToken(); // :
						acc = line.nextToken();
						List<KnowledgeBaseVariable> variables = kb.getKnowledgeBaseVariables();
						for (int i = 0; i < variables.size(); i++) {
							FuzzyVariableType v = (FuzzyVariableType) variables.get(i);
							v.setAccumulation(acc);
						}
					}
					// Rule
					else if (word.equalsIgnoreCase("RULE")) {
						basicRules = splitRules(line, kb);
						for (int i = 0; i < basicRules.size(); i++) {
							line = new StringTokenizer(basicRules.get(i), ", ;\t");
							rule = new FuzzyRuleType(line.nextToken());

							rule.setAndMethod(rb.getAndMethod());
							rule.setOrMethod(rb.getOrMethod());

							// Read the antecedent
							line.nextToken(); // :
							line.nextToken(); // IF

							antecedent = new AntecedentType();

							nameVariable = line.nextToken(); // varible's name
							line.nextToken(); // IS
							nameTerm = line.nextToken(); // Term's name

							var = (FuzzyVariableType) kb.getVariable(nameVariable);
							term = (FuzzyTermType) var.getTerm(nameTerm);
							antecedent.addClause(var, term);

							word = line.nextToken();
							while (!word.equalsIgnoreCase("THEN")) {
								if (word.equalsIgnoreCase("AND")) {
									rule.setConnector("AND");
								} else {
									rule.setConnector("OR");
								}

								nameVariable = line.nextToken(); // varible's
																	// name
								line.nextToken(); // IS
								nameTerm = line.nextToken(); // varible's
																// term

								var = (FuzzyVariableType) kb.getVariable(nameVariable);
								term = (FuzzyTermType) var.getTerm(nameTerm);
								antecedent.addClause(var, term);

								word = line.nextToken();
							}

							rule.setAntecedent(antecedent);

							// Read the consequent
							consequent = new ConsequentType();

							while (line.hasMoreTokens()) {
								word = line.nextToken();
								if (word.equalsIgnoreCase("WITH")) {
									rule.setWeight(Float.parseFloat(line.nextToken())); // weighting
																						// factor
								} else {
									nameVariable = word; // Varible's name
									line.nextToken(); // IS
									nameTerm = line.nextToken(); // Term's name
									var = (FuzzyVariableType) kb.getVariable(nameVariable);
									term = (FuzzyTermType) var.getTerm(nameTerm);
									consequent.addThenClause(var, term);
								}
							}

							rule.setConsequent(consequent);
							rb.addRule(rule);
						}
					}

					line = new StringTokenizer(lines.nextToken(), ", ;\t");
					word = line.nextToken();
				}
			}
		}

		return fuzzySystemIEEE;
	}

	/**
	 * Remove the comments, unnecessary spaces, rewrite the definitions of
	 * the rules in one line, and check the format FCL.
	 * 
	 * @param fcl
	 *            String with the fuzzy system in format FCL
	 * @return possible object is {@link String }
	 */
	private String cleanFCL(String fcl) {
		StringTokenizer lines, line;
		String cleanedFcl, sentence, word;
		int varInput, varOutput, varEnd, fuzzify, defuzzify, ruleblock, functionBlock;

		fcl = fcl.replace("(", " ( ");
		fcl = fcl.replace(")", " ) ");
		fcl = fcl.replace(",", " , ");
		fcl = fcl.replace(":", " : ");
		fcl = fcl.replace("=", " = ");
		fcl = fcl.replace(":  =", ":=");

		// Remove spaces
		lines = new StringTokenizer(fcl, "\n\r");
		cleanedFcl = "";
		while (lines.hasMoreTokens()) {
			line = new StringTokenizer(lines.nextToken(), "\t ");
			if (line.hasMoreTokens()) {
				cleanedFcl += line.nextToken();
				while (line.hasMoreTokens())
					cleanedFcl += " " + line.nextToken();
				cleanedFcl += "\n";
			}
		}

		// Remove comments
		lines = new StringTokenizer(cleanedFcl, "\n\r");
		cleanedFcl = "";
		while (lines.hasMoreTokens()) {
			sentence = lines.nextToken();
			if (!sentence.startsWith("//")) {
				if (sentence.startsWith("/*")) {
					while (!sentence.startsWith("*/"))
						sentence = lines.nextToken();
				} else if (sentence.indexOf("//") > -1) {
					line = new StringTokenizer(sentence.replaceAll("//", "|"), "|");
					cleanedFcl += line.nextToken();
					cleanedFcl += "\n";
				} else {
					cleanedFcl += sentence;
					cleanedFcl += "\n";
				}
			}
		}

		// Join the definition of the rules
		lines = new StringTokenizer(cleanedFcl, "\n\r");
		cleanedFcl = "";
		while (lines.hasMoreTokens()) {
			sentence = lines.nextToken();
			if (sentence.startsWith("RULE ")) {
				cleanedFcl += sentence;
				while (!sentence.endsWith(";")) {
					sentence = lines.nextToken();
					cleanedFcl += " " + sentence;
				}
				cleanedFcl += "\n";
			} else {
				cleanedFcl += sentence + "\n";
			}
		}

		// Check the format FCL
		varInput = varOutput = varEnd = fuzzify = defuzzify = ruleblock = functionBlock = 0;
		lines = new StringTokenizer(cleanedFcl, "\n\r");
		while (lines.hasMoreTokens()) {
			line = new StringTokenizer(lines.nextToken(), " \t;, :=()");
			if (line.hasMoreTokens()) {
				word = line.nextToken();
				if (word.equalsIgnoreCase("FUNCTION_BLOCK"))
					functionBlock++;
				if (word.equalsIgnoreCase("END_FUNCTION_BLOCK"))
					functionBlock++;
				if (word.equalsIgnoreCase("VAR_INPUT"))
					varInput++;
				if (word.equalsIgnoreCase("VAR_OUTPUT"))
					varOutput++;
				if (word.equalsIgnoreCase("END_VAR"))
					varEnd++;
				if (word.equalsIgnoreCase("FUZZIFY"))
					fuzzify++;
				if (word.equalsIgnoreCase("END_FUZZIFY"))
					fuzzify++;
				if (word.equalsIgnoreCase("DEFUZZIFY"))
					defuzzify++;
				if (word.equalsIgnoreCase("END_DEFUZZIFY"))
					defuzzify++;
				if (word.equalsIgnoreCase("RULEBLOCK"))
					ruleblock++;
				if (word.equalsIgnoreCase("END_RULEBLOCK"))
					ruleblock++;
			}
		}

		if (functionBlock != 2) {
			System.err.println(
					"There was a problem with the format FCL. The file should contain FUNCTION_BLOCK and END_FUNCTION_BLOCK.");
			return null;
		}
		if ((varInput < 1) || (varOutput < 1) || (varInput + varOutput != varEnd)) {
			System.err.println("There was a problem with the format FCL. Review the blocks VAR_INPUT and VAR_OUTPUT.");
			return null;
		}
		if ((fuzzify < 2) || ((fuzzify % 2) > 0)) {
			System.err.println(
					"There was a problem with the format FCL. The file should contain FUZZIFY and END_FUZZIFY for each input variable.");
			return null;
		}
		if ((defuzzify < 2) || ((defuzzify % 2) > 0)) {
			System.err.println(
					"There was a problem with the format FCL. The file should contain DEFUZZIFY and END_DEFUZZIFY for each output variable.");
			return null;
		}
		if ((ruleblock < 2) || ((ruleblock % 2) > 0)) {
			System.err.println(
					"There was a problem with the format FCL. The file should contain at least one token RULEBLOCK and END_RULEBLOCK.");
			return null;
		}

		return cleanedFcl;
	}

	/**
	 * Generate a list of subrules from the rule when it contains AND and OR
	 * operators in its antecedent, and remove the operators NOT of the rules
	 * adding new terms to the variables
	 * 
	 * @param rule
	 * @param kb
	 * @return possible object is {@link List<String> }
	 */
	private List<String> splitRules(StringTokenizer rule, KnowledgeBaseType kb) {
		String word, antecedent, consequent, subRule, nameRule, iniRule;
		StringTokenizer conditions;
		List<String> rules;
		int nAND, nOR, nRules;
		FuzzyVariableType var;
		FuzzyTermType term;

		nAND = nOR = 0;
		rules = new ArrayList<String>();

		nameRule = rule.nextToken(); // name
		iniRule = " " + rule.nextToken(); // :
		iniRule += " " + rule.nextToken() + " "; // IF
		antecedent = "";
		consequent = "";

		// Remove NOTs
		word = rule.nextToken();

		while (!word.equalsIgnoreCase("THEN")) {
			if (word.equalsIgnoreCase("AND")) {
				nAND++;
				antecedent += " " + word; // AND
				word = rule.nextToken();
			}
			if (word.equalsIgnoreCase("OR")) {
				nOR++;
				antecedent += " " + word; // OR
				word = rule.nextToken();
			}
			if (word.equalsIgnoreCase("NOT")) {
				rule.nextToken(); // (

				word = rule.nextToken(); // Variable's name
				antecedent += " " + word;
				var = (FuzzyVariableType) kb.getVariable(word);

				word = rule.nextToken(); // IS
				antecedent += " " + word;

				word = rule.nextToken();

				if (word.equalsIgnoreCase("NOT")) {
					word = rule.nextToken(); // Term's name
					antecedent += " " + word;
				} else {
					if (!var.hasTerm("NOT" + word)) {
						term = (FuzzyTermType) var.getTerm(word).copy();
						term.setName("NOT" + word);
						if (term.getComplement().equalsIgnoreCase("FALSE"))
							term.setComplement("TRUE");
						else
							term.setComplement("FALSE");

						var.addFuzzyTerm(term);
					}

					antecedent += " NOT" + word; // Term's name
				}
				rule.nextToken(); // )
			} else {

				antecedent += " " + word; // Variable's name
				var = (FuzzyVariableType) kb.getVariable(word);

				word = rule.nextToken(); // IS
				antecedent += " " + word;

				word = rule.nextToken();

				if (word.equalsIgnoreCase("NOT")) {
					word = rule.nextToken(); // Variable's term

					if (!var.hasTerm("NOT" + word)) {
						term = (FuzzyTermType) var.getTerm(word).copy();
						term.setName("NOT" + word);
						if (term.getComplement().equalsIgnoreCase("FALSE"))
							term.setComplement("TRUE");
						else
							term.setComplement("FALSE");

						var.addFuzzyTerm(term);
					}

					antecedent += " NOT" + word; // Term's name

				} else {
					antecedent += " " + word; // Term's name
				}
			}
			word = rule.nextToken();
		}

		consequent = word; // THEN
		while (rule.hasMoreTokens()) {
			word = rule.nextToken();
			consequent += " " + word;
		}

		// Generate the subrules
		if ((nAND > 0) && (nOR > 0)) {
			conditions = new StringTokenizer(antecedent.replaceAll(" OR ", "|"), "|");
			nRules = 1;
			while (conditions.hasMoreTokens()) {
				subRule = nameRule + "_" + nRules + iniRule + conditions.nextToken() + " " + consequent;
				rules.add(subRule);
				nRules++;
			}
		} else {
			rules.add(nameRule + iniRule + antecedent + " " + consequent);
		}

		return rules;
	}
}
