package game;
import calculations.Rational;


public class Game {
    private int range;
    private Rational number;
    private int maxAttempts;
    private int attemptsCounter;
    private boolean isGuessed;

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public Rational getNumber() {
        return number;
    }

    public void setGuessed(boolean guessed) {
        isGuessed = guessed;
    }

    public boolean getGuessed() {
        return isGuessed;
    }

    public int getAttemptsCounter() {
        return attemptsCounter;
    }

    public void incrementAttemptsCounter() {
        attemptsCounter += 1;
    }

    public void start(int z) {
        if (z < 4) throw new IllegalArgumentException("range too small");
        range = z;

        int numerator = (int) (Math.random() * range) + 1;
        int denominator = (int) (Math.random() * range) + 1;

        number = new Rational(numerator, denominator);
        maxAttempts = (int) Math.ceil(3 * Math.log(range));
        attemptsCounter = 0;
        isGuessed = false;

        try {
            assert (denominator >= numerator);
        } catch (AssertionError e) {
            this.start(range);
        }

    }

}
