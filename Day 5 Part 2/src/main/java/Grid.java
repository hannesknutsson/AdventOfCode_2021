public class Grid {

    private int side;
    private int[][] grid;

    public Grid(int side) {
        this.side = side;
        this.grid = new int[side][side];
    }

    public void drawVector(Vector toDraw) {

        //Draw a diagonal line
        Vector.Point start = toDraw.pointA;
        Vector.Point stop = toDraw.pointB;

        int x = start.x;
        int y = start.y;
        boolean continueDrawing = true;

        while (continueDrawing) {
            grid[x][y]++;
            if (x == stop.x && y == stop.y) {
                continueDrawing = false;
            }
            if (x < stop.x) {
                x++;
            } else if (x > stop.x) {
                x--;
            }
            if (y < stop.y) {
                y++;
            } else if (y > stop.y){
                y--;
            }
        }
    }

    public int calcVectorOverlapPoints() {
        int overlappingPoints = 0;
        for (int y = 0 ; y < side ; y++) {
            for (int x = 0 ; x < side ; x++) {
                if (grid[x][y] > 1) {
                    overlappingPoints++;
                }
            }
        }
        return overlappingPoints;
    }
}
