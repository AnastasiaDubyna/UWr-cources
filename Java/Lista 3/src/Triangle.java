import java.util.Arrays;

public class Triangle {
    private Point a;
    private Point b;
    private Point c;

    Triangle(Point a, Point b, Point c) throws IllegalAccessException {
        double abLength = new LineSegment(a, b).length();
        double bcLength = new LineSegment(b, c).length();
        double acLength = new LineSegment(a, c).length();


        double[] lineSegLengthArray = {abLength, bcLength, acLength};
        Arrays.sort(lineSegLengthArray);
        if (lineSegLengthArray[2] >= lineSegLengthArray[1] + lineSegLengthArray[0]) {
            throw new IllegalAccessException("Invalid triangle");
        }

        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Point getA() {
        return a;
    }

    public Point getB() {
        return b;
    }

    public Point getC() {
        return c;
    }

    public void move(Vector v) {
        this.a.move(v);
        this.b.move(v);
        this.c.move(v);
    }

    public void turn(Point p, double angle) {
        this.a.turn(p, angle);
        this.b.turn(p, angle);
        this.c.turn(p, angle);
    }

    public void reflection(Line p) {
        this.a.reflection(p);
        this.b.reflection(p);
        this.c.reflection(p);
    }
}
