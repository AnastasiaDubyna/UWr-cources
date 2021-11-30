package calculations;

public abstract class Expression implements Calculable{
    public static int sum(Expression ...expr) {
        int result = 0;
        for (Expression e : expr) {
            result += e.calculate();
        }
        return result;
    }

    public static int product(Expression ...expr) {
        int result = 1;
        for (Expression e : expr) {
            result *= e.calculate();
        }
        return result;
    }

    public boolean equals(Object o) {
        return o instanceof Expression s && this.calculate() == s.calculate();
    }
}
