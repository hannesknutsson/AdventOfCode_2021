public class Vector {

    public static class Point {
        public int x;
        public int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public Point pointA;
    public Point pointB;

    public Vector(Point a, Point b) {
        this.pointA = a;
        this.pointB = b;
    }
}
