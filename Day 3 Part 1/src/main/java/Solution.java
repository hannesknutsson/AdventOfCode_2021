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

        final int bitsPerLine = 12;

        int[] zeroes = new int[bitsPerLine];
        int[] ones = new int[bitsPerLine];

        for (String binary : inputList) {
            char[] bits = binary.toCharArray();
            for (int bitPos = 0 ; bitPos < bitsPerLine ; bitPos++) {
                if (bits[bitPos] == '0') {
                    zeroes[bitPos]++;
                } else {
                    ones[bitPos]++;
                }
            }
        }

        StringBuilder gammaBuilder = new StringBuilder();
        StringBuilder epsilonBuilder = new StringBuilder();

        for (int bitPos = 0 ; bitPos < bitsPerLine ; bitPos++) {
            if (zeroes[bitPos] > ones[bitPos]) {
                gammaBuilder.append("0");
                epsilonBuilder.append("1");
            } else {
                gammaBuilder.append("1");
                epsilonBuilder.append("0");
            }
        }

        int decimalGamma = Integer.parseInt(gammaBuilder.toString(), 2);
        int decimalEpsilon = Integer.parseInt(epsilonBuilder.toString(), 2);

        int powerConsumption = decimalGamma * decimalEpsilon;

        System.out.println("Gamma is " + decimalGamma + " Epsilon is " + decimalEpsilon + " Power consumption is " + powerConsumption);
    }

}