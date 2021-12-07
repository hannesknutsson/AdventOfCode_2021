public class Grid {

    private int side;
    private int[][] grid;

    public Grid(int side) {
        this.side = side;
        this.grid = new int[side][side];
    }

    public void drawVector(Vector toDraw) {
        if (toDraw.pointA.x == toDraw.pointB.x) {
            //Draw a vertical line
            int y1 = Math.min(toDraw.pointA.y, toDraw.pointB.y);
            int y2 = Math.max(toDraw.pointA.y, toDraw.pointB.y);
            int x = toDraw.pointA.x;    //Both points have same X
            for (int i = y1 ; i <= y2 ; i++) {
                grid[i][x]++;
            }
        } else {
            //Draw a horizontal line
            int x1 = Math.min(toDraw.pointA.x, toDraw.pointB.x);
            int x2 = Math.max(toDraw.pointA.x, toDraw.pointB.x);
            int y = toDraw.pointA.y;    //Both points have same Y
            for (int i = x1 ; i <= x2 ; i++) {
                grid[y][i]++;
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
