public class Line {
    public final double a;
    public final double b;
    public final double c;

    Line(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    private double countLineSlope() {
        return ((- this.a) / this.b);
    }

    private double countLineIntercept() {
        return ((- this.c) / this.b);
    }

    static boolean areParallel(Line firstLine, Line secondLine) {
        return (firstLine.countLineSlope() == secondLine.countLineSlope());
    }

    static boolean arePerpendicular(Line firstLine, Line secondLine) {
        return (firstLine.countLineSlope() * secondLine.countLineSlope() == -1);
    }

    static void move(Vector v, Line l) {
        System.out.println("Placeholder");

    }

    static Point findCrossPoint(Line firstLine, Line secondLine) {
        double w = firstLine.a * secondLine.b - secondLine.a * firstLine.b;
        double wx = -firstLine.c * secondLine.b - (-secondLine.c) * firstLine.b;
        double wy = firstLine.a * (secondLine.c) - secondLine.a * (-firstLine.c);

        return new Point(wx/w , wy/w);
    }

}
