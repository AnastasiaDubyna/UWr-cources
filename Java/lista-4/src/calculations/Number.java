package calculations;

public class Number extends Expression{
    private final int val;

    public Number(int val) {
        this.val = val;
    }
    @Override
    public int calculate() {
        return this.val;
    }

    @Override
    public String toString() {
        return "" + val;
    }
}
