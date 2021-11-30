package calculations;

public abstract class Operator1Arg extends Expression{
    Expression operand;

    Operator1Arg(Expression operand) {
        this.operand = operand;
    }
}
