public class Point {
    private double x;
    private double y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void move(Vector v) {
        this.x += v.dx;
        this.y += v.dy;
    }

    public void turn(Point p, double angleDeg) {
        double startX = this.x;
        double startY = this.y;
        double angle = Math.toRadians(angleDeg);

        this.x = (startX - p.x) * Math.cos(angle) - (startY - p.y) * Math.sin(angle) + p.x;
        this.y = (startX - p.x) * Math.sin(angle) + (startY - p.y) * Math.cos(angle) + p.y;
    }

    public void reflection(Line p) {
        double d = ((p.a * p.a * y) - (2 * p.a * p.b * x) - (2 * p.c * p.b) - (p.b * p.b * y)) / (p.a * p.a + p.b * p.b);

        this.x = (p.a * (d - y)) / p.b + x;
        this.y = d;
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

}
