package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

import java.util.HashMap;

public abstract class Operator {

    private static HashMap<String, Operator> operators = new HashMap();

    static {
        operators.put("+", new AddOperator());
        operators.put("-", new SubtractOperator());
        operators.put("*", new MultiplyOperator());
        operators.put("/", new DivideOperator());
        operators.put("^", new PowerOperator());
        operators.put(")", new OpenParenOperator());
        operators.put("(", new ClosedParenOperator());
    }

    public abstract int priority();

    public abstract Operand execute(Operand op1, Operand op2);

    /**
     * NOTE: Iterates through hashmap. Only String keys
     *       of the map. This way, someone who were to
     *       add a new expression will just have to add
     *       it to the HashMap and not use cases.
     */
    public static boolean check(String token) {
        for (String key: operators.keySet()){
            if(token.equals(key)){
                return true;
            }
        }
        return false;
    }


    /**
     * used to retrieve an operator from our HashMap.
     * This will act as out publicly facing function,
     * granting access to the Operator HashMap.
     *
     * @param token key of the operator we want to retrieve
     * @return reference to a Operator instance.
     */
    public static Operator getOperator(String token) {
        return operators.get(token);
    }

}
