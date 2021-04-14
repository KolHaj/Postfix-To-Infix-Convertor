package Postfix_TIC;

/**
* File: NodeData.java
* Author: Kolger Hajati
* Date: February 6, 2019
* Purpose: This holds classes for Operand Infix
*/

abstract class NodeData {
    abstract public String toInstruction();
}

interface Node {
    public String currentV();
    public String postV();
}

//Holds the operand methods
class OperandNode implements Node {
    private int value;

    //Constructor that sets value
    public OperandNode(int v) {
        this.value = v;
    }
    //Operand infix
    public String currentV() {
        return String.valueOf(value);
    }
    //Operand postfix
    public String postV() {
        return String.valueOf(value);
    }
}

//Holds the methods of infix and postfix
class OperatorNode implements Node {
    
    private Node left, right;
    private String operator;
    
    //Constructor
    public OperatorNode(String op, Node l, Node r) {
        this.operator = op;
        this.left = l;
        this.right = r;
    }
    //Recursion for infix expression
    public String currentV() {
        String leftVal = left.currentV();
        String rightVal = right.currentV();
        return "(" + leftVal + " " + operator + " " + rightVal + ")";
    }
    //Recursion for postfix expression
    public String postV() {
        String leftVal = left.postV();
        String rightVal = right.postV();
        return leftVal + " " + rightVal + " " + operator;
    }
}