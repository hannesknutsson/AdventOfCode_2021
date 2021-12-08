import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) {

        InputStream is = Solution.class.getClassLoader().getResourceAsStream("input");
        BufferedReader input = new BufferedReader(new InputStreamReader(is));

        List<String> inputLines = input.lines().collect(Collectors.toList());

        //HydroThermal Ventures will from here just be called vectors
        List<Vector> vectors = new ArrayList<>();

        //Parse all vectors
        for (String vectorString : inputLines) {
            String[] rawPoints = vectorString.split(" -> ");
            String[] rawPointA = rawPoints[0].split(",");
            String[] rawPointB = rawPoints[1].split(",");
            Vector.Point pointA = new Vector.Point(Integer.parseInt(rawPointA[0]), Integer.parseInt(rawPointA[1]));
            Vector.Point pointB = new Vector.Point(Integer.parseInt(rawPointB[0]), Integer.parseInt(rawPointB[1]));
            vectors.add(new Vector(pointA, pointB));
        }

        Grid seaBottom = new Grid(1000);
        vectors.forEach(seaBottom::drawVector);

        System.out.println("There are " + seaBottom.calcVectorOverlapPoints() + " overlapping points.");
    }
}
