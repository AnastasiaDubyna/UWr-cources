package calculations;

public class Factorial extends Operator1Arg{

    public Factorial(Expression operand) {
        super(operand);
    }

    @Override
    public int calculate() {
        int N = operand.calculate();
        int result = 1;

        for (int i = 1; i <= N; i++) {
            result *= i;
        }
        return result;
    }

    @Override
    public String toString() {
        return "!" + operand;
    }
}
