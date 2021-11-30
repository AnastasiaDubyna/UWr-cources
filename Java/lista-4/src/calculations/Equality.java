package calculations;

public class Equality extends Operator2Arg{

    public Equality(Expression first, Expression second) {
        super(first, second);
    }

    @Override
    public int calculate() {
        return (operandOne.calculate() == operandTwo.calculate()) ? 1 : 0;
    }

    @Override
    public String toString() {
        return operandOne + " == " + operandTwo;
    }
}
