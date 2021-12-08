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

        List<String> inputList = input.lines().collect(Collectors.toList());

        final int bitsPerLine = inputList.get(0).toCharArray().length;

        List<String> oxygenCandidates = new ArrayList<>(inputList);
        List<String> co2Candidates = new ArrayList<>(inputList);

        for (int bitPos = 0 ; bitPos < bitsPerLine ; bitPos++) {

            int oxygenZeroes = 0;
            int co2Zeroes = 0;

            for (String binary : oxygenCandidates) {
                char currentBit = binary.charAt(bitPos);
                if (currentBit == '0') {
                    oxygenZeroes++;
                }
            }

            for (String binary : co2Candidates) {
                char currentBit = binary.charAt(bitPos);
                if (currentBit == '0') {
                    co2Zeroes++;
                }
            }

            char oxygenFilter;
            char co2Filter;
            int finalBitPos = bitPos;   //Stupid stream requirement

            if (oxygenZeroes > oxygenCandidates.size() / 2) {
                oxygenFilter = '0';
            } else if (oxygenZeroes < oxygenCandidates.size() / 2) {
                oxygenFilter = '1';
            } else {
                oxygenFilter = '1';
            }

            if (co2Zeroes > co2Candidates.size() / 2) {
                co2Filter = '1';
            } else if (co2Zeroes < co2Candidates.size() / 2) {
                co2Filter = '0';
            } else {
                co2Filter = '0';
            }

            if (oxygenCandidates.size() > 1) {
                oxygenCandidates = oxygenCandidates.stream().filter(bits -> bits.toCharArray()[finalBitPos] == oxygenFilter).collect(Collectors.toList());
            }

            if (co2Candidates.size() > 1) {
                co2Candidates = co2Candidates.stream().filter(bits -> bits.toCharArray()[finalBitPos] == co2Filter).collect(Collectors.toList());
            }
        }

        int oxygenGeneratorRating = Integer.parseInt(oxygenCandidates.get(0), 2);
        int co2ScrubberRating = Integer.parseInt(co2Candidates.get(0), 2);

        int lifeSupportRating = oxygenGeneratorRating * co2ScrubberRating;

        System.out.println("O2  rating: " + oxygenGeneratorRating);
        System.out.println("CO2 rating: " + co2ScrubberRating);
        System.out.println("Life support rating: " + lifeSupportRating);
    }
}