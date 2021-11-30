public class LineSegment {
    private Point beg;
    private Point end;

    LineSegment(Point a, Point b) throws IllegalAccessException {
        if (a.getX() == b.getX() && a.getY() == b.getY()) {
            throw new IllegalAccessException("It's a point, not a line segment");
        }
        this.beg = new Point(a.getX(), a.getY());
        this.end = new Point(b.getX(), b.getY());
    }

    public Point getBeg() {
        return this.beg;
    }

    public Point getEnd() {
        return end;
    }

    public double length() {
        double x1 = this.beg.getX();
        double y1 = this.beg.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();

        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }

    public void move(Vector v) {
        this.beg.move(v);
        this.end.move(v);
    }

    public void turn(Point p, double angle) {
        this.beg.turn(p, angle);
        this.end.turn(p, angle);
    }

    public void reflection(Line p) {
        this.beg.reflection(p);
        this.end.reflection(p);
    }
}
