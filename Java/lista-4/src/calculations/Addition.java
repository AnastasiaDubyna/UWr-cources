package calculations;

public class Addition extends Operator2Arg{

    public Addition(Expression first, Expression second) {
        super(first, second);
    }

    @Override
    public int calculate() {
        return operandOne.calculate() + operandTwo.calculate();
    }

    @Override
    public String toString() {
        return "(" + operandOne + " + " + operandTwo + ")";
    }
}

