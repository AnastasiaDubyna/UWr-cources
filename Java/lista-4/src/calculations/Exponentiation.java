package calculations;

public class Exponentiation extends Operator2Arg{
    public Exponentiation(Expression first, Expression second) {
        super(first, second);
    }

    @Override
    public int calculate() {
        return (int) Math.pow(operandOne.calculate(), operandTwo.calculate());
    }

    @Override
    public String toString() {
        return operandOne + "^" + operandTwo;
    }
}
