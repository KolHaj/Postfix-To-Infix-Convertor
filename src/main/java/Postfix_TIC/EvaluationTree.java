package Postfix_TIC;

/**
* File: EvaluationTree.java
* Author: Kolger Hajati
* Date: February 6, 2019
* Purpose: holds node tree and file creation for assembly
*/

import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class EvaluationTree {
	// Node, Stack, Variables
    private Node root;
    private Stack<Node> stackValue;
    private String instructOrder;
    private static char CHARERROR;
    private static char c;
    
    
    // Constructor that initializes stack of nodes
    public EvaluationTree() {
    	stackValue = new Stack<Node>();
        instructOrder = "";
    }
    
    /**
    *Creates a tree data structure that checks operand or operator
    *Also creates either the OperandNode or OperatorNode
    *Also updates assembly
    */
    public void create(String expression) throws Exception {
    	
        int operatorCount = 0;
        for(int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            
            if(isOperator(c)) {
            	
            	setChar(c);
            	Operator operator = new Operator();
                String op = String.valueOf(operator.toString());
                Node right = stackValue.pop();
                Node left = stackValue.pop();
                stackValue.push(new OperatorNode(op, left, right));
                updateAssembly(op, left, right, operatorCount);
                operatorCount++;
            }
            else if(!Character.isWhitespace(c)) {
            	stackValue.push(new OperandNode(Integer.parseInt(Character.toString(c))));
            } 
        }
        root = stackValue.lastElement();
        fileCreator(instructOrder, "AssemblyFile.txt");
    }

    //Checks if character value is a operator
    private boolean isOperator(char c) throws Exception {
		if (c == '+' || c == '-' || c == '*' || c == '/')
			return true;
		else if (c >= '0' && c <= '9')
			return false;
			setError(c);
			throw new Exception ("Error: " + c);
	}

    //Setter for OperatorInput file
    public static void setChar(char ch) {
    	c = ch;
    }
    //Getter for OperatorInput file
    public static char getChar() {
    	return c;
    }
    //Returns the infix expression
    public String infix() {
        if(root == null) {
            return "";
        }
        return root.currentV();
    }
    //Returns the postfix expression
    public String postfix() {
        if(root == null) {
            return "";
        }
        return root.postV();
    }
    
    //Attach new instructions to instructOrder
    private void updateAssembly(String op, Node left, Node right, int opCount) {
    	Operator operator = new Operator();
    	instructOrder += operator.toInstruction() + " R" + opCount + " ";
        
        if(left instanceof OperandNode) {
        	instructOrder += left.currentV() + " \n";
            if(right instanceof OperandNode) {
            	instructOrder += right.currentV() + " ";
            }
            else {
            	instructOrder += "R" + (opCount-1) + " \n";
            }
        }
        else if(left instanceof OperatorNode) {
            opCount += (opCount <= 2) ? 1 : 0;
            instructOrder += "R" + (opCount-2) + " ";
            
            if(right instanceof OperatorNode) {
            	instructOrder += "R" + (opCount-1) + " \n";
            }
            else {
            	instructOrder += right.currentV() + " \n";
            }
        }
    }
    
    //Write to the file
    private void fileCreator(String content, String fileName) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(content);
            writer.write(System.getProperty( "line.separator" ));
        }
        catch(IOException e){
        	e.printStackTrace();
        }
    }
    //Sets value of token error
    public void setError(char c) {
    	CHARERROR = c;
    }
    //Gets value of error token
    public static char getError() {
		return CHARERROR;
    }
}
