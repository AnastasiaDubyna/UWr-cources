package calculations;

public class DiscreteLogarithm extends Operator2Arg {

    public DiscreteLogarithm(Expression base, Expression argument) throws Exception {
        super(base, argument);
        if (base.calculate() < 0 || base.calculate() == 1 || argument.calculate() < 0) {
            throw new Exception("Invalid logarithm");
        }
    }

    @Override
    public int calculate() {
        return (int) (Math.log(operandTwo.calculate()) / Math.log(operandOne.calculate()));
    }

    @Override
    public String toString() {
        return "log(" + operandOne + ", " + operandTwo + ")";
    }
}


