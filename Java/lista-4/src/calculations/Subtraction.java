package calculations;

public class Subtraction extends Operator2Arg{

    public Subtraction(Expression first, Expression second) {
        super(first, second);
    }

    @Override
    public int calculate() {
        return operandOne.calculate() - operandTwo.calculate();
    }

    @Override
    public String toString() {
        return "(" + operandOne + " - " + operandTwo + ")";
    }
}
