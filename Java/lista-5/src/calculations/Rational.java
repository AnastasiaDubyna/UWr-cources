package calculations;

public class Rational implements Comparable<Rational>{
    private int numerator, denominator = 1;

    public Rational() {
        numerator = 0;
    }

    public Rational(int n) {
        this(n, 1);
    }

    public Rational(int k, int m) {
        if (m == 0) {
            throw new IllegalArgumentException("Denominator can't be 0");
        }
        int gcd = gcd(Math.abs(k), Math.abs(m));
        numerator = (m < 0 ? (-1) * k : k) / gcd;
        denominator = Math.abs(m) / gcd;
    }

    public int getDenominator() {
        return denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    private static int gcd(int a, int b) {
        if (a == 0) {
            return b;
        }

        return gcd(b % a, a);
    }

    private static int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    public static boolean equals(Rational a, Rational b) {
        return a.numerator == b.numerator && a.denominator == b.denominator;
    }

    @Override
    public int compareTo(Rational o) {
        return equals(this, o) ? 1 : 0;
    }

    public static Rational multiply(Rational a, Rational b) {
        return new Rational(a.numerator * b.numerator, a.denominator * b.denominator);
    }

    public static Rational add(Rational a, Rational b) {
        int lcm = lcm(a.denominator, b.denominator);
        int newNumerator = a.numerator * lcm / a.denominator + b.numerator * lcm / b.denominator;
//        System.out.println(lcm);
//        System.out.println(newNumerator);
        return new Rational(newNumerator, lcm);
    }

    public static Rational subtract(Rational a, Rational b) {
        return add(a, new Rational(-1 * b.numerator, b.denominator));
    }

    public static Rational divide(Rational a, Rational b) {
        if (b.numerator == 0) {
            throw new ArithmeticException("You can't divide by 0");
        }
        return multiply(a, new Rational(b.denominator, b.numerator));
    }
}
