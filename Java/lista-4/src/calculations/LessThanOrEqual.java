package calculations;

public class LessThanOrEqual extends Operator2Arg{

    public LessThanOrEqual(Expression first, Expression second) {
        super(first, second);
    }

    @Override
    public int calculate() {
        return (operandOne.calculate() <= operandTwo.calculate()) ? 1 : 0;
    }

    @Override
    public String toString() {
        return operandOne + " <= " + operandTwo;
    }
}

