package calculations;

public class Maximum extends Operator2Arg{

    public Maximum(Expression first, Expression second) {
        super(first, second);
    }

    @Override
    public int calculate() {
        return Math.max(operandOne.calculate(), operandTwo.calculate());
    }

    @Override
    public String toString() {
        return "max(" + operandOne + ", " + operandTwo + ")";
    }
}
