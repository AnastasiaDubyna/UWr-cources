package calculations;

public class Modulo extends Operator2Arg{

    public Modulo(Expression operandOne, Expression operandTwo) {
        super(operandOne, operandTwo);
    }

    @Override
    public int calculate() {
        return operandOne.calculate() % operandTwo.calculate();
    }

    @Override
    public String toString() {
        return operandOne + " mod " + operandTwo;
    }
}
