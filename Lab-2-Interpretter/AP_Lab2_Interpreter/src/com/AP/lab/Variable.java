/*
 * Advanced Programming
 * Lab # 2
 * Interpreter
 * 
 * by Shumail Mohy-ud-Din
 * BSCS-2B - 01947
 * 
 */

package com.AP.lab;

public class Variable<T> {
	
	public int VARIABLE_TYPE_INTEGER = 1;
	public int VARIABLE_TYPE_FLOAT = 2;
	public int VARIABLE_TYPE_STRING = 3;
	
	public String variableSymbol;
	public T variableValue;
	public int init_flag = 0;
	public int variable_type;
	
	
	public Variable(String variableSymbol, T variableValue, int variable_type) {
		super();
		this.variableSymbol = variableSymbol;
		this.variableValue = variableValue;
		this.variable_type = variable_type;
	}
	public int getVariable_type() {
		return variable_type;
	}
	public void setVariable_type(int variable_type) {
		this.variable_type = variable_type;
	}
	public String getVariableSymbol() {
		return variableSymbol;
	}
	public void setVariableSymbol(String variableSymbol) {
		this.variableSymbol = variableSymbol;
	}
	public T getVariableValue() {
		return variableValue;
	}
	public void setVariableValue(T variableValue) {
		this.variableValue = variableValue;
	}
	public int getInit_flag() {
		return init_flag;
	}
	public void setInit_flag(int init_flag) {
		this.init_flag = init_flag;
	}
	
	
	
}
