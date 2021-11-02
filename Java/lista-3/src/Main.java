public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        //Point implementation
        System.out.println("Point:");
        Point point1 = new Point(2, 2);
        System.out.printf("Coord before moving (%f, %f)\n", point1.getX(), point1.getY());
        point1.move(new Vector(-5, 0));
        System.out.printf("Coord after moving (%f, %f)\n", point1.getX(), point1.getY());
        point1.turn(new Point(4, -2), 270);
        System.out.printf("Coord after turning (%f, %f)\n", point1.getX(), point1.getY());

        //LineSegment implementation
        System.out.println("LineSegment:");
        LineSegment lineSegment1 = new LineSegment(point1, new Point(1, 2));
        LineSegment lineSegment2 = new LineSegment(point1, new Point(1, 1));
        System.out.printf("Coord before moving: %s, %s\n", lineSegment1.getBeg(), lineSegment1.getEnd());
        lineSegment1.move(new Vector(-5, 0));
        System.out.printf("Coord after moving: %s, %s\n", lineSegment1.getBeg(), lineSegment1.getEnd());
        lineSegment1.turn(new Point(4, -2), 270);
        System.out.printf("Coord after moving: %s, %s\n", lineSegment1.getBeg(), lineSegment1.getEnd());

        //Triangle implementation
        System.out.println("Triangle:");
        Triangle triangle1 = new Triangle(new Point(1, 1), new Point(1, 3), new Point(4,1));
        System.out.printf("Coord before moving: %s, %s, %s\n", triangle1.getA(), triangle1.getB(), triangle1.getC());
        triangle1.move(new Vector(-5, 0));
        System.out.printf("Coord after moving: %s, %s, %s\n", triangle1.getA(), triangle1.getB(), triangle1.getC());
        triangle1.turn(new Point(4, -2), 270);
        System.out.printf("Coord after turning: %s, %s, %s\n", triangle1.getA(), triangle1.getB(), triangle1.getC());


    }
}
