package calculations;

public class Minimum extends Operator2Arg{

    public Minimum(Expression first, Expression second) {
        super(first, second);
    }

    @Override
    public int calculate() {
        return Math.min(operandOne.calculate(), operandTwo.calculate());
    }

    @Override
    public String toString() {
        return "min(" + operandOne + ", " + operandTwo + ")";
    }
}
