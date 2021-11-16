package calculations;

public class Constant extends Expression{
    private final int val;

    public Constant(int val) throws Exception {
        if (val != 0 && val != 1 && val != -1) {
            throw new Exception("Invalid constant value");
        }
        this.val = val;
    }

    @Override
    public int calculate() {
        return val;
    }

    @Override
    public String toString() {
        return "" + val;
    }
}
