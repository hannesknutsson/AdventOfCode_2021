import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) {

        InputStream is = Solution.class.getClassLoader().getResourceAsStream("input");
        BufferedReader input = new BufferedReader(new InputStreamReader(is));

        List<String> inputLines = input.lines().collect(Collectors.toList());

        //Remove the first line to get all sequence of numbers to be drawn before parsing the boards
        List<Integer> numberDraws = Arrays.stream(inputLines.remove(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());

        //Remove empty lines from the input
        inputLines = inputLines.stream().filter(line -> !line.equals("")).collect(Collectors.toList());


        boolean continueReading = true;
        int linesRead = 0;
        List<Board> boards = new ArrayList<>();

        //Parse all boards
        while (continueReading) {
            List<String> boardLines = new ArrayList<>();
            for (int i = 0 ; i < 5 ; i++) {
                boardLines.add(inputLines.get(linesRead++));

                if (linesRead == inputLines.size()){
                    continueReading = false;
                }
            }
            boards.add(new Board(boardLines));
        }

        int winningScore = -1;

        //Loop over the numbers until a board wins
        for (int currentNumber : numberDraws) {
            boards.forEach(board -> board.markNumber(currentNumber));
            Optional<Board> winningBoard = boards.stream().filter(Board::hasBingo).findAny();
            if (winningBoard.isPresent()) {
                winningScore = winningBoard.get().calculateScore(currentNumber);
                break;
            }
        }

        System.out.println("The winning score is: " + winningScore);
    }
}
