package edu.csc413.calculator.evaluator;


import edu.csc413.calculator.operators.OpenParenOperator;
import edu.csc413.calculator.operators.Operator;
import edu.csc413.calculator.evaluator.Operand;
import java.util.Stack;
import java.util.StringTokenizer;

public class Evaluator {
    private Stack<Operand> operandStack;
    private Stack<Operator> operatorStack;
    private StringTokenizer tokenizer;
    private static final String DELIMITERS = "+-*^/()";

    public Evaluator() {
        operandStack = new Stack<>();
        operatorStack = new Stack<>();
    }

    public int eval(String expression) {

        String token;

        this.tokenizer = new StringTokenizer(expression, DELIMITERS, true);

        /**
         * CITATIONS: Code below influenced by:
         *            https://www.geeksforgeeks.org/expression-evaluation/
         */

        while (this.tokenizer.hasMoreTokens()) {
            // filter out spaces
            if (!(token = this.tokenizer.nextToken()).equals(" ")) {
                // check if token is an operand
                if (Operand.check(token)) {
                    operandStack.push(new Operand(token));
                } else {
                    if (!Operator.check(token)) {
                        System.out.println("*****invalid token******");
                        System.exit(1);
                    }
                    Operator newOperator = Operator.getOperator(token);

                    if(newOperator.equals(Operator.getOperator("("))) {
                        operatorStack.push(newOperator);
                        continue;
                    }
                    if(newOperator.equals(Operator.getOperator(")"))) {
                        while (!(operatorStack.peek().equals(Operator.getOperator("(")))) {
                            evaluation();
                        }
                        operatorStack.pop();
                    }else{
                        while(!(operatorStack.isEmpty()) && operatorStack.peek().priority() >= newOperator.priority()){
                            evaluation();
                        }
                        operatorStack.push(newOperator);
                    }
                }
            }
        }
        while(!operatorStack.isEmpty()){
            evaluation();
        }
        return operandStack.pop().getValue();
    }
    /**
     * NOTE: This is a method that does the whole process of
     *       pushing and popping the operator and operand
     *       stacks so long as the priority is greater than
     *       1. Made this to encapsulate a process.
     */
    public void evaluation(){
        Operand op1 = operandStack.pop();
        Operand op2 = operandStack.pop();
        Operator oldOpr = operatorStack.pop();
        operandStack.push(oldOpr.execute(op1, op2));
    }
}