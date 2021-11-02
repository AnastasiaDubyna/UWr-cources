import java.math.BigInteger;
import java.util.Scanner;

public class Factorial {
    private static boolean isInputValid(int n) {
        if (n <= 0) {
            System.err.println("Please enter value bigger than 0");
            return false;
        }
        else if (n > 100) {
            System.err.println("Please enter smaller value");
            return false;
        }
        return true;
    }

    private static BigInteger getFactorial(int n) {
        BigInteger factorialValue = new BigInteger("1");
        for (int i = 1; i <= n; ++i) {
            factorialValue = factorialValue.multiply(BigInteger.valueOf(i));
        }
        return  factorialValue;
    }

    public static void main(String[] args) {
        System.out.println("Enter factorial: ");
        int factorialOf = new Scanner(System.in).nextInt();

        if (isInputValid(factorialOf)) {
            System.out.printf("Factorial of %d: %d", factorialOf, getFactorial(factorialOf));
        }
    }
}
