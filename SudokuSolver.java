import java.util.ArrayDeque;

/**
 * Class SudokuSolver solves a Sudoku puzzle using bruteforce backtracking.
 * @author William Warlick and Avery Ellis
 * @version Final October 26th, 2021
 */

public class SudokuSolver {

    /**
     * Creates a Deque of previous sudoku moves to be backtracked on if a new digit cannot be placed.
     */
    private final ArrayDeque<SudokuMove> moveStack = new ArrayDeque<SudokuMove>();

    /**
     * Creates a Sudoku board puzzle.
     */
    private final SudokuPuzzle puzzle;

    /**
     * Constructs a Sudoku Puzzle.
     *
     * @param puzzle is a SudokuPuzzle object which represents some form of a sudoku puzzle.
     */
    public SudokuSolver(SudokuPuzzle puzzle) {
        this.puzzle = puzzle;
    }

    /**
     * Finds the next move that is valid.
     * @param minVal minimum value for next move.
     * @param row row where next move will be.
     * @param col col where next move will be.
     * @return SudokuMove that instructs where and what to place.
     */
    private SudokuMove findNextMove (SudokuPuzzle puzzle, int minVal, int row, int col){
        for (int startingValue = minVal; startingValue < 10; startingValue++) {
            if (puzzle.isValidMove(row, col, startingValue)) {
                return new SudokuMove(puzzle, startingValue, row, col);
            }
        }
        return null;
    }

    /**
     * Solves and unsolved sudoku puzzle by backtracking.
     */
    public void solve() {
        int cell = 0;
        //Minimum value of digit to be placed. Will be increased so in backtracking pushed digits will not repeat.
        int minVal = 1;

        while (!puzzle.solveCheck()) {
            int row = cell / 9;
            int col = cell - (row * 9);

            if (puzzle.getValue(row, col) == 0) { //if the value is zero, finds value to place.
                SudokuMove move = findNextMove(puzzle, minVal, row, col);
                if (move != null) { //if there is a possible move, make it.
                    moveStack.push(move);
                } else {
                    boolean beginBackTracking = true;

                    while (beginBackTracking) {
                        //Pop the most recent move off the stack, grab the cell where we made
                        // that move and the value we used there, reset that cell's value to 0, and try to place a
                        // number higher than the previous value.
                        SudokuMove prevMove = moveStack.pop();
                        int prevCol = prevMove.getColPlaced();
                        int prevRow = prevMove.getRowPlaced();

                        SudokuMove backTrack = findNextMove(puzzle, prevMove.getValPlaced(), prevRow, prevCol);

                        if (backTrack != null) {
                            //if possible move is found, make it.
                            puzzle.place(backTrack.getValPlaced(), backTrack.getRowPlaced(), backTrack.getColPlaced());
                            moveStack.push(backTrack);
                            beginBackTracking = false;

                        } else {
                            //No move is found, place zero and keeps backtracking.
                            puzzle.place(0, prevMove.getRowPlaced(), prevMove.getColPlaced());
                        }
                        //Calculates the cell
                        cell = (9 * (prevMove.getRowPlaced()) + prevMove.getColPlaced()) - 1;
                    }
                }
            }
            if (cell == 80) {
                //We do 80 because we start at 0 and that totals 81 cells.
                puzzle.isSolved();
            } else {
                //If we haven't gotten to 80 yet, then we can still progress onwards.
                cell++;
            }
        }
    }
}

