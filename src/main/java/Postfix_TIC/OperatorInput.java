package Postfix_TIC;

/**
* File: OperatorInput.java
* Author: Kolger Hajati
* Date: February 6, 2019
* Purpose: holds infix expression evaluation
*/

public abstract class OperatorInput  {
    abstract public String toInstruction();
}
	class Operator extends OperatorInput {
		//Char value that is being assigned with getter
		char c = EvaluationTree.getChar();
		
		//If statement to check operator to assembly instructions
		public String toInstruction() {
			
			String instruction = "";
			if (c == '+') {
				instruction = "Add";
			}
			else if (c == '-') {
				instruction = "Sub";
			} 
			else if (c == '*') {
				instruction = "Mul";
			} 
			else if (c == '/') {
				instruction = "Div";
			}
			return instruction;
		}
		//If statement to check operator to toString
		public String toString(){
			
			//variable
			String str = "";
			
			if (c == '+') {
				str = "+";
			} 
			else if (c == '-') {
				str = "-";
			}
			else if (c == '*') {
				str = "*";
			} 
			else if (c == '/') {
				str = "/";
			}
			return str;
		}
}