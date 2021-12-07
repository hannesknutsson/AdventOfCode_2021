import java.util.*;
import java.util.stream.Collectors;

public class Board {

    static class Number {
        int value;
        boolean marked;

        Number(int value) {
            this.value = value;
            marked = false;
        }
    }

    Number[][] numberGrid = new Number[5][5];
    Map<Integer, List<Number>> numberMap; //Just makes marking numbers more efficient

    Board(List<String> boardRows) {
        numberMap = new HashMap<>();
        for (int y = 0 ; y < 5 ; y++) {
            //Because of how I split stuff I get empty strings on single digit numbers, therefore I filter those values out..
            List<String> numberList = Arrays.stream(boardRows.get(y).split(" ")).filter(string -> !string.equals("")).collect(Collectors.toList());
            for (int x = 0 ; x < 5 ; x++) {
                int number = Integer.parseInt(numberList.get(x));
                numberMap.putIfAbsent(number, new ArrayList<>());
                Number newCell = new Number(number);
                numberGrid[x][y] = newCell;
                numberMap.get(number).add(newCell);
            }
        }
    }

    public void markNumber(int cellNumber) {
        for (Number cellToCircle : numberMap.getOrDefault(cellNumber, new ArrayList<>())) {
            cellToCircle.marked = true;
        }
    }

    public boolean hasBingo() {

        boolean hasBingo = false;

        //Scan for horizontal bingo's
        for (int y = 0; y < 5 && !hasBingo; y++) {
            int circlesInARow = 0;
            for (int x = 0 ; x < 5 ; x++) {
                if (numberGrid[x][y].marked) {
                    circlesInARow++;
                } else {
                    break;
                }
            }
            if (circlesInARow == 5) {
                hasBingo = true;
            }
        }

        //Scan for vertical bingo's
        for (int x = 0; x < 5 && !hasBingo; x++) {
            int circlesInARow = 0;
            for (int y = 0 ; y < 5 ; y++) {
                if (numberGrid[x][y].marked) {
                    circlesInARow++;
                } else {
                    break;
                }
            }
            if (circlesInARow == 5) {
                hasBingo = true;
            }
        }

        return hasBingo;
    }

    public int calculateScore(int finalNumber) {
        int unmarkedSum = 0;
        for (int y = 0 ; y < 5 ; y++) {
            for (int x = 0 ; x < 5 ; x++) {
                if (!numberGrid[x][y].marked) {
                    unmarkedSum += numberGrid[x][y].value;
                }
            }
        }
        return unmarkedSum * finalNumber;
    }
}
