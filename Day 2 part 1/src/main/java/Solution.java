import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) {

        InputStream is = Solution.class.getClassLoader().getResourceAsStream("input");
        BufferedReader input = new BufferedReader(new InputStreamReader(is));

        List<String> inputList = input.lines().collect(Collectors.toList());

        int horizontalPos = 0;
        int depth = 0;

        for (String command : inputList) {

            String[] splitCommand = command.split(" ");

            String direction = splitCommand[0];
            int distance = Integer.parseInt(splitCommand[1]);

            switch (direction) {
                case "forward":
                    horizontalPos += distance;
                    break;
                case "down":
                    depth += distance;
                    break;
                case "up":
                    depth -= distance;
                    break;
            }
        }

        System.out.println("The submarine is located at horizontal position " + horizontalPos + " and depth " + depth + ".");
        System.out.println(horizontalPos + " x " + depth + " = " + (horizontalPos * depth));
    }

}