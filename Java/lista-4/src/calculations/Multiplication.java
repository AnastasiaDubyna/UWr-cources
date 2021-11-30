package calculations;

public class Multiplication extends Operator2Arg{

    public Multiplication(Expression first, Expression second) {
        super(first, second);
    }

    @Override
    public int calculate() {
        return operandOne.calculate() * operandTwo.calculate();
    }

    @Override
    public String toString() {
        return operandOne + " * " + operandTwo;
    }
}
