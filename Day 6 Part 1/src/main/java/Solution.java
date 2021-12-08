import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) {

        InputStream is = Solution.class.getClassLoader().getResourceAsStream("input");
        BufferedReader input = new BufferedReader(new InputStreamReader(is));

        List<String> inputLines = input.lines().collect(Collectors.toList());

        List<Integer> fishies = new ArrayList<>();
        Arrays.stream(inputLines.get(0).split(",")).forEach(fishie -> fishies.add(Integer.parseInt(fishie)));

        final int daysToSimulate = 80;

        for (int iteration = 0 ; iteration < daysToSimulate ; iteration++) {
            int fishieCountAtDayStart = fishies.size();
            for (int index = 0 ; index < fishieCountAtDayStart ; index++) {
                if (fishies.get(index) > 0) {
                    fishies.set(index, fishies.get(index) - 1);
                } else {
                    fishies.set(index, 6);
                    fishies.add(8);
                }
            }
        }

        System.out.println("After " + daysToSimulate + " days there are " + fishies.size() + " fishies.");
    }
}
