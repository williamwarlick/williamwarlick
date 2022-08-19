/**
 * An object representing a single digit placement while solving a Sudoku puzzle to be used by the solver.
 * @author William Warlick and Avery Ellis
 * @version Final October 26th, 2021
 */

public class SudokuMove {

    /**
     * The row where the value is placed.
     */
    private final int rowPlaced;

    /**
     * The column where the value is placed.
     */
    private final int colPlaced;

    /**
     * The value to be placed at a given row and col.
     */
    private final int valPlaced;

    /**
     * Constructs a sudoku move.
     * @param puzzle the sudoku board to make the move on.
     * @param val the value to be placed at a given row and col.
     * @param row the row to place the value in.
     * @param col the col to place the value in.
     */
    public SudokuMove(SudokuPuzzle puzzle, int val, int row, int col) {
        //Place a given value in the puzzle at the specified row and column.
        rowPlaced = row;
        colPlaced = col;
        valPlaced = val;

        puzzle.place(val, row, col);
    }

    /**
     * Gets row which value is placed in.
     * @return row which value is placed in.
     */
    public int getRowPlaced() {
        return rowPlaced;
    }

    /**
     * Gets col which value is placed in.
     * @return col which value is placed in.
     */
    public int getColPlaced() {
        return colPlaced;
    }

    /**
     * Gets value to be placed at a given row and col.
     * @return Value to be placed at a given row and col.
     */
    public int getValPlaced() {
        return valPlaced;
    }
}
