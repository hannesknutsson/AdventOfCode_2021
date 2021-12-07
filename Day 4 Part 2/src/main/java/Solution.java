import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

        //Loop over the numbers until only one board remains
        int currentDrawNumber = -1;
        while (boards.size() > 1 && numberDraws.size() > 0) {
            currentDrawNumber = numberDraws.remove(0);
            int finalCurrentDrawNumber = currentDrawNumber; //Stupid lambda requires effectively final variable so here it is I guess
            boards.forEach(board -> board.markNumber(finalCurrentDrawNumber));
            List<Board> bingoBoards = boards.stream().filter(Board::hasBingo).collect(Collectors.toList());
            boards.removeAll(bingoBoards);
        }

        //Draw numbers until last board has won as well
        Board lastBoard = boards.get(0);
        while (!lastBoard.hasBingo()) {
            currentDrawNumber = numberDraws.remove(0);
            lastBoard.markNumber(currentDrawNumber);
        }

        //Present the score
        int lastBoardScore = boards.get(0).calculateScore(currentDrawNumber);
        System.out.println("The last board's score is: " + lastBoardScore);
    }
}
