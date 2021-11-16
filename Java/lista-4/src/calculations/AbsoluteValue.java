package calculations;

public class AbsoluteValue extends Operator1Arg{

    public AbsoluteValue(Expression operand) {
        super(operand);
    }

    @Override
    public int calculate() {
        int operandCalc = operand.calculate();

        if (operandCalc < 0) {
            return (-1) * operandCalc;
        }
        return operandCalc;
    }

    @Override
    public String toString() {
        return "|" + operand + "|";
    }
}
