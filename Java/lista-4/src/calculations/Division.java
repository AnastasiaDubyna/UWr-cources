package calculations;

public class Division extends Operator2Arg{

    public Division(Expression first, Expression second) {
        super(first, second);
    }

    @Override
    public int calculate() {
        return operandOne.calculate() / operandTwo.calculate();
    }

    @Override
    public String toString() {
        return operandOne + " / " + operandTwo;
    }
}
