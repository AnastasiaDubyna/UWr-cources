package calculations;

public class SignChange extends Operator1Arg{

    public SignChange(Expression operand) {
        super(operand);
    }

    @Override
    public int calculate() {
        return (-1) * operand.calculate();
    }

    @Override
    public String toString() {
        return "-(" + operand + ")";
    }
}
