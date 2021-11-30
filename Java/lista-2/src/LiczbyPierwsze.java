import java.util.ArrayList;
import java.lang.Math;

public final class LiczbyPierwsze {
    private final static  int POTEGA2 = 21;
    private final static boolean[] SITO = new boolean[1 << POTEGA2];

    public static ArrayList<Long> naCzynnikiPierwsze(long x) {
        boolean isNegative = false;

        if (x < 0) {
            x = x * (-1L);
            isNegative = true;
        }

        long numHalf = x / 2;
        long limit = (long) Math.sqrt(numHalf) + 1;
        long lowLimit = limit;
        long highLimit = 2 * limit;
        long dividedNum = x;

        boolean[] firstSegmentSieve = new boolean[(int) limit + 1];
        ArrayList<Long> firstSegmentPrimes = new ArrayList<>();
        ArrayList<Long> primeFactors = new ArrayList<>();

        if (isNegative) {
            primeFactors.add(-1L);
        }

        for (int i = 2; i < limit + 1; i++) {
            firstSegmentSieve[i] = true;
        }
        for (int i = 2; i < limit / 2; i++) {
            if (firstSegmentSieve[i]) {
                for (int j = i + i; j <= limit; j += i) {
                    firstSegmentSieve[j] = false;
                }
            }
        }

        for (int i = 0; i < firstSegmentSieve.length; i++) {
            if (firstSegmentSieve[i]) {
                firstSegmentPrimes.add((long) i);
            }
        }

        for (long prime : firstSegmentPrimes) {
            while (dividedNum % prime == 0){
                primeFactors.add(prime);
                dividedNum /= prime;
            }
//            System.out.println(firstSegmentPrimes);
        }
//
        while (lowLimit < numHalf) {
            if (highLimit >= numHalf) {
                highLimit = (int) numHalf;
            }

            for (int i = 0; i < limit + 1; i++) {
                SITO[i] = true;
            }

            for (int i = 0; i < firstSegmentPrimes.size(); i++) {
                int start = (int) (Math.floor(lowLimit / firstSegmentPrimes.get(i)) * firstSegmentPrimes.get(i));
                if (start < lowLimit) {
                    start += firstSegmentPrimes.get(i);
                }
                for (int j = start; j < highLimit; j += firstSegmentPrimes.get(i)) {
                    SITO[j - (int) lowLimit] = false;
                }
            }

            for (int i = 0; i < limit + 1; i++) {
                if (SITO[i]) {
                    while (dividedNum % (i + lowLimit) == 0) {
                        primeFactors.add((long) i + lowLimit);
                        dividedNum /= i + lowLimit;
                    }
                }
            }
            lowLimit += limit;
            highLimit += limit;

        }

    return primeFactors;
    }

    public static void main(String[] args) {
        long[] numArray = {
                98L, -25L, 296L
        };
        for (long num : numArray) {
            ArrayList<Long> primeFactors = naCzynnikiPierwsze(num);
            System.out.printf("%d: %s\n", num, primeFactors.toString());
        }
    }
}
