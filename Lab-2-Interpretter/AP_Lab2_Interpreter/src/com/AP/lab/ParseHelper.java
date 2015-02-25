/*
 * Advanced Programming
 * Lab # 2
 * Interpreter
 * 
 * by Shumail Mohy-ud-Din
 * BSCS-2B - 01947
 * 
 */

/*
 * Assumptions:
 *  - Variable must be declared before use
 */

package com.AP.lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Hashtable;

public class ParseHelper {
	
	
	public int VARIABLE_TYPE_INTEGER = 1;
	public int VARIABLE_TYPE_FLOAT = 2;
	public int VARIABLE_TYPE_STRING = 3;

	//Hash table to store variables - Generic
	Hashtable<String, Variable> VARIABLES_HASHTABLE = new Hashtable<String, Variable> ();
	
	public void parse(BufferedReader br) {
		// TODO Auto-generated method stub
		
		String currLine;
		int lineNo = 0;
		//reading matrix from file
		try {
			while((currLine = br.readLine()) != null) {
				System.out.println(">> " + currLine);
				lineNo++;
				
				//case 1 - LET
				if(currLine.toLowerCase().contains("let")) {
					System.out.println("# case 1 : LET");
					
					if(currLine.toLowerCase().contains("=")) {
					
						String[] parsedStrElements = currLine.split(" ");
						
						System.out.println("# " + Arrays.toString(parsedStrElements));
						
						// extracting variables and values
						
						//if float
						if(parsedStrElements[3].contains(".")) {
							VARIABLES_HASHTABLE.put(parsedStrElements[1],new Variable<Float>(parsedStrElements[1], Float.parseFloat(parsedStrElements[3]), VARIABLE_TYPE_FLOAT  ));
						
						} 
						//if string
						else if (parsedStrElements[3].contains("\"") || parsedStrElements[3].contains("\'") ) {
							VARIABLES_HASHTABLE.put(parsedStrElements[1], new Variable<String>(parsedStrElements[1], parsedStrElements[3], VARIABLE_TYPE_STRING  ));
						} 
						//if integer
						else {
							VARIABLES_HASHTABLE.put(parsedStrElements[1], new Variable<Integer>(parsedStrElements[1], Integer.parseInt(parsedStrElements[3]), VARIABLE_TYPE_INTEGER  ));
						}
							
						System.out.println("<< Variable declared: [" + parsedStrElements[1] + "] with value [" + VARIABLES_HASHTABLE.get(parsedStrElements[1]).getVariableValue() + "] >>");
					
					} else {
						System.out.println("# Error: Invalid expression at line: " + lineNo);
						Interpret.ASSERT_FLAG = -1;
					}
					
				} 
				//CASE 2 - PRINT
				else if (currLine.toLowerCase().contains("print")) {
					System.out.println("# case 2 : PRINT");
					//split from space
					String[] parsedStrElements = currLine.split(" ");
					System.out.println("# " + Arrays.toString(parsedStrElements));
					
					//check if not null
					if(VARIABLES_HASHTABLE.get(parsedStrElements[1]) != null) {
						System.out.println("<< Print: " + parsedStrElements[1] + " => " + VARIABLES_HASHTABLE.get(parsedStrElements[1]).getVariableValue() + " >>");
					} else {
						System.out.println("<< Print Error: " + parsedStrElements[1] + " is Undeclared at line" + lineNo + " >>");
						Interpret.ASSERT_FLAG = -1;
					}
					
				
				} 
				//CASE 3 - SOME OPERATION
				else {
					System.out.println("# case 3 : OPERATION");
					
					String[] parsedStrElements = currLine.split("=");
					
					//Variable on left side e.g 'y' in  [y = x + 2]
					String x = parsedStrElements[0].trim();
					//operations to perform on right side
					String eq = parsedStrElements[1];
					
					System.out.println("# before: " + eq);
					
					//iterating and replacing the variables with their values
					for(String key : VARIABLES_HASHTABLE.keySet()) {
						if(eq.contains(key)) {
							eq = eq.replace(key, VARIABLES_HASHTABLE.get(key).getVariableValue().toString());
						}
						
					}
					
					System.out.println("# after: " + eq);
					
					//check if expression is VALID 
					
					int validFlag = isExpressionValid(eq);
					System.out.println("# Valid: " + validFlag);
					
					if(validFlag == 0) {	//VALID expression
					
						// Check if 'eq' is FLOAT or INT
						
						if(eq.contains(".")) {	//expression is float
							
							ExpressionEvaluator expression = new ExpressionEvaluator(eq);
							
							//evaluate expression
							BigDecimal exp_ans = expression.eval();
							
							Float expression_ans = exp_ans.floatValue();
							
							//equate left hand side varibale to answer
							
							Variable<Float> toUpdate = new Variable<Float >(x, expression_ans, VARIABLE_TYPE_FLOAT); 
							
							VARIABLES_HASHTABLE.put(x, toUpdate);
							
							System.out.println("<< value of: " + currLine + " => " + VARIABLES_HASHTABLE.get(x).getVariableValue() + " >>");
							
						} else {	//expression is int
							
							ExpressionEvaluator expression = new ExpressionEvaluator(eq);
							
							//evaluate expression
							BigDecimal exp_ans = expression.eval();
							
							Integer expression_ans = exp_ans.intValueExact();
							
							//equate left hand side variable to answer
							
							Variable<Integer> toUpdate = new Variable<Integer>(x, expression_ans, VARIABLE_TYPE_INTEGER); 
							
							VARIABLES_HASHTABLE.put(x, toUpdate);
							
							System.out.println("<< value of: " + currLine + " => " + VARIABLES_HASHTABLE.get(x).getVariableValue() + " >>");
							
						}
						
					} else if (validFlag == -1)  {	// INVALID Expression
						System.out.println("<< Error: Invalid Expression at line: " + lineNo + " (undeclared variable)>>");
						Interpret.ASSERT_FLAG = -1;
					}
					
					/*
					 EXAMPLE Evaluation of Expression
					
					String tmp = "(1+((2+3)*(4*5)))";
					tmp = "((2+7*3))";

					ExpressionEvaluator evaluator = new ExpressionEvaluator("(" + eq + ")");
					evaluator.stringOperation();
					Integer expression_ans = evaluator.operands.pop();
					
					*/
					
				}	//end case 3
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	/*
	 * Funciton to check if an expression is valid - doesn't contains any undeclared variable
	 */
	
	private int isExpressionValid(String eq) {
		// TODO Auto-generated method stub
		
		char[] expressionArray = eq.toCharArray();
		
		for(int i=0; i<expressionArray.length; i++) {
			if(Character.isLetter(expressionArray[i])) { 
				//INVALID as there can't be a letter in expression
				return -1;
			}
		}
		
		return 0;
	}

}
