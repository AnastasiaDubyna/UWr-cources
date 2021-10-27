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
        this.x = x + v.dx;
        this.y = y + v.dy;
    }

    public void turn(Point p, double angleDeg) {
        double startX = this.x;
        double startY = this.y;
        double angle = Math.toRadians(angleDeg);

        this.x = (startX - p.x) * Math.cos(angle) - (startY - p.y) * Math.sin(angle) + p.x;
        this.y = (startX - p.x) * Math.sin(angle) + (startY - p.y) * Math.cos(angle) + p.y;
    }

    public void reflection(Line p) {
        System.out.println("Placeholder");
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

}
