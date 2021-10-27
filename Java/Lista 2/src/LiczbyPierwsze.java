import java.util.ArrayList;
import java.lang.Math;

public final class LiczbyPierwsze {
    private final static  int POTEGA2 = 21;
    private final static boolean[] SITO = new boolean[1 << POTEGA2];
//    public static ArrayList<Long> czynnikiPierwsze = new ArrayList<Long>();
//    public static boolean czyPierwsza(long x) {
//        return SITO[(int) x];
//    }

    public static ArrayList<Long> naCzynnikiPierwsze(long x) {
        long numHalf = x / 2;
        long limit = (long) Math.sqrt(numHalf) + 1;
        long lowLimit = limit;
        long highLimit = 2 * limit;
        long dividedNum = x;

        boolean[] firstSegmentSieve = new boolean[(int) limit + 1];
        ArrayList<Long> firstSegmentPrimes = new ArrayList<>();
        ArrayList<Long> primeFactors = new ArrayList<>();

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
//                System.out.println(lowLimit);
//                System.out.printf("HighLimit: %d\n", highLimit);
//                System.out.println(firstSegmentPrimes.get(i));
//                System.out.printf("Start for i = %d\n", i);
//                System.out.printf("Start value: %d\n", start);
                for (int j = start; j < highLimit; j += firstSegmentPrimes.get(i)) {
//                    System.out.printf("j: %d\n", j);
//                    System.out.printf("j - lowLimit: %d\n", j - lowLimit);
                    SITO[j - (int) lowLimit] = false;
                }
            }

            for (int i = 0; i < limit + 1; i++) {
                if (SITO[i]) {
                    //System.out.println(i + lowLimit);
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
                9223372036854775783L
        };
        for (long num : numArray) {
            System.out.println(naCzynnikiPierwsze(num));
        }
    }
}
