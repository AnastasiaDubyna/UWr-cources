package calculations;

public class GreaterThanOrEqual extends Operator2Arg{

    public GreaterThanOrEqual(Expression first, Expression second) {
        super(first, second);
    }

    @Override
    public int calculate() {
        return (operandOne.calculate() >= operandTwo.calculate()) ? 1 : 0;
    }

    @Override
    public String toString() {
        return operandOne + " >= " + operandTwo;
    }
}
