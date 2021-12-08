import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    final static int daysToSimulate = 2;
    final static int normalDuplicationTime = 6;
    final static int firstDuplicationTime = 8;

    static Map<Long, Map<Long,Long>> calculationMap = new HashMap<>();

    public static void main(String[] args) {

        InputStream is = Solution.class.getClassLoader().getResourceAsStream("input");
        BufferedReader input = new BufferedReader(new InputStreamReader(is));

        List<String> inputLines = input.lines().collect(Collectors.toList());

        List<Long> fishies = new ArrayList<>();
        //Arrays.stream(inputLines.get(0).split(",")).forEach(fishie -> fishies.add(Integer.parseInt(fishie)));
        Arrays.stream("3,4,3,1,2".split(",")).forEach(fishie -> fishies.add((long)Integer.parseInt(fishie)));

        long fishieCount = fishies.size();

        //Prepare result caching data structure
        for (long i = 0 ; i <= 8 ; i++) {
            calculationMap.putIfAbsent(i, new HashMap<>());
        }

        for (long fishie : fishies) {
            fishieCount += fishieForecast(fishie, daysToSimulate);
        }

        System.out.println("After " + daysToSimulate + " days there are " + fishieCount + " fishies.");
    }

    public static long fishieForecast(final long fishieTimer, final long daysLeft) {

        //Try keeping track of the number of fishies this fishie produces
        long totalFishiesProduced = 0;

        if (calculationMap.get(fishieTimer).get(daysLeft) != null) {
            //Calculation for this scenario already done and cached. Don't calculate that again.
            totalFishiesProduced = calculationMap.get(fishieTimer).get(daysLeft);
        } else {
            //This fishie will first have to wait until it is a 0. After that, it will create daysLeft / normalDuplicationTime fishies
            final long timesDuplicated = Math.max(0, daysLeft - fishieTimer) / normalDuplicationTime;

            //We add these new fishies to the total count
            totalFishiesProduced += timesDuplicated;

            //Calculate recursively how many children the new fishies will create
            long childrenDuplicates = 0;
            for (int childFish = 0 ; childFish < timesDuplicated ; childFish++) {
                //Since the new fishies needs extra time to grow up, we have to enter firstDuplicationTime as their fishieTimer
                //Since the new fishies timers wont start counting until next iteration, we have to subtract 1 from daysleft
                //Since the new fishies will be created with an even interval of normalDuplicationTime, we have to subtract normalDuplicationTime extra for every next fishie
                childrenDuplicates += fishieForecast(firstDuplicationTime, Math.max(0, daysLeft - 1 - (childFish * normalDuplicationTime)));
            }
            totalFishiesProduced += childrenDuplicates;
            calculationMap.get(fishieTimer).put(daysLeft, totalFishiesProduced);
        }

        return totalFishiesProduced;
    }
}
