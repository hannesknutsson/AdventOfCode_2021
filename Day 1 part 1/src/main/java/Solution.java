import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) {

        InputStream is = Solution.class.getClassLoader().getResourceAsStream("input");
        BufferedReader input = new BufferedReader(new InputStreamReader(is));

        List<Integer> inputList = input.lines().map(Integer::parseInt).collect(Collectors.toList());

        int depthIncreasedCounter = 0;
        int lastDepth = Integer.MAX_VALUE;

        for (Integer currentDepth : inputList) {
            if (currentDepth > lastDepth) {
                depthIncreasedCounter++;
            }
            lastDepth = currentDepth;
        }

        System.out.println("The depth increased " + depthIncreasedCounter + " times.");
    }

}
