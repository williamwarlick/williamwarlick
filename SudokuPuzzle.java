/**
 * SudokuPuzzle class represents a Sudoku puzzle (either an unsolved, partially solved or solved puzzle).
 *
 * @author William Warlick and Avery Ellis
 * @version Final October 26th, 2021
 */

public class SudokuPuzzle {

    /**
     * A 2d array to represent the sudoku board.
     */
    private final int[][] sudokuBoard;

    /**
     * A boolean to represent if said sudoku board is solved.
     */
    private boolean isSolved = false;

    /**
     * Sets isSolved boolean to true.
     */
    public void isSolved() {
        isSolved = true;
    }

    /**
     * Gets isSolved
     * @return if the puzzle is solved
     */
    public boolean solveCheck() {
        return isSolved;
    }

    /**
     * Instantiates SudokuPuzzle
     * @param sudoku is a String of Sudoku Board.
     */
    public SudokuPuzzle(String sudoku){
        int[][] board = new int[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                board[row][col] = Character.getNumericValue(sudoku.charAt((9 * row) + col));
            }
        }
        sudokuBoard = board;
    }

    /**
     * Getter for value at specific board position.
     * @param col col of value to be got is in.
     * @param row row of value to be got is in
     * @return value from indicated board position
     */
    public int getValue(int row, int col) { return sudokuBoard[row][col]; }

    /**
     * Places chosen value at specific board position.
     * @param val value to be placed.
     * @param row row where to place val.
     * @param col col where to place val.
     */
    public void place(int val, int row, int col) {
        sudokuBoard[row][col] = val;
    }

    /**
     * Checks to see if the move is lega.
     * @param row row the digit will be placed in.
     * @param col col the digit will be placed in.
     * @param digit digit to be placed.
     * @return false if any of the rules are broken, true if none are.
     */
    public boolean isValidMove(int row, int col, int digit) {
        int boolCheck = 0;
        //Check row rule
        int[] testRow = sudokuBoard[row];
        for (int k : testRow) {
            if (k == digit) {
                boolCheck++;
            }
        }
        //Check col rule
        int[] column = new int[9];
        for (int j = 0; j < 9; j++) {
            column[j] = sudokuBoard[j][col];
        }
        for (int j = 0; j < 9; j++) {
            if (column[j] == digit) {
                boolCheck++;
            }
        }
        //Check Box rule
        int firstRow = 3 * (row / 3);
        int firstCol = 3 * (col / 3);

        for (row = firstRow; row < firstRow + 3; row++) {
            for (col = firstCol; col < firstCol + 3; col++) {
                if (sudokuBoard[row][col] == digit) {
                    boolCheck++;
                }
            }
        }
        return boolCheck == 0;
    }

    /**
     * Checks to see if two puzzles are equal. In our case will be used to compare out solution with one provided.
     * @param obj Sudoku Puzzle
     * @return true if puzzles are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SudokuPuzzle) {
            //We just checked that obj is a SudokuPuzzle, so we can safely cast it.
            SudokuPuzzle comparePuzzle = (SudokuPuzzle)obj;
            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {
                    if (sudokuBoard[row][col] != comparePuzzle.getValue(row, col)) {
                        return false;
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * To String that formats the given array into a sudoku board
     * @return String Sudoku board lines for splitting the into columns of 3.
     */
    public String toString() {
        String boardString = "";
        for (int row = 0; row < 9; row++) {
            if (row % 3 == 0) {
               boardString += ("-------------------------");
               boardString += "\n";
            }
            for (int col = 0; col < 9; col++) {
                if (col % 3 == 0) {
                    boardString += "| ";
                }
                boardString += sudokuBoard[row][col];
                boardString += " ";
            }
            boardString += "|";
            boardString += "\n";
        }
        boardString += ("-------------------------");
        return boardString;
    }
}
