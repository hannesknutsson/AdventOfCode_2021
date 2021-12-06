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
        int lastTotalDepth = Integer.MAX_VALUE;
        final int windowSize = 3;

        for (int windowStart = 0 ; windowStart <= inputList.size() - windowSize ; windowStart++) {
            int currentTotalDepth = inputList.get(windowStart) + inputList.get(windowStart + 1) + inputList.get(windowStart + 2);
            if (currentTotalDepth > lastTotalDepth) {
                depthIncreasedCounter++;
            }
            lastTotalDepth = currentTotalDepth;
        }

        System.out.println("The depth increased " + depthIncreasedCounter + " times.");
    }

}