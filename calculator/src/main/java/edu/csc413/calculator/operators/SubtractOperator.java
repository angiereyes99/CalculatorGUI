package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

public class SubtractOperator extends Operator{
    @Override
    public int priority() {
        return 1;
    }
    @Override
    /**
     * NOTE: Odd results in this operator. When running
     *       the test, fails all cases. When i were to
     *       switch the op1 and 2 the test cases passed,
     *       but all evaluator classes gave wrong answer.
     * */
    public Operand execute(Operand op1, Operand op2) {
        Operand diff = new Operand(op2.getValue()-op1.getValue());
        return diff;
    }
}
