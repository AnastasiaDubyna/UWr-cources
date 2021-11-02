public class Vector {
    final public double dx;
    final public double dy;

    Vector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    static Vector add(Vector a, Vector b) {
        return new Vector(a.dx + b.dx, a.dy + b.dy);
    }
}
