package calculations;

public abstract class Operator2Arg extends Expression {
    Expression operandOne;
    Expression operandTwo;

    protected Operator2Arg(Expression first, Expression second) {
        this.operandOne = first;
        this.operandTwo = second;
    }



}
