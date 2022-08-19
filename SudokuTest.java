import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *  Read filename and (potentially) solution file name to Solve 9 X 9 sudoku puzzles. Checks to see if Solution file
 *  is correct.
 *  @author William Warlick and Avery Ellis
 *  @version Final October 26th, 2021
 */

public class SudokuTest {

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the filename of the puzzle: ");
        String puzzleFilename = scan.nextLine();
        System.out.println("Enter the filename of the solution (optional): ");
        String solutionFilename = scan.nextLine();
        try {
            scan = new Scanner(new File(puzzleFilename));
            String sudoku = "";
            while (scan.hasNext()) {
                sudoku += scan.next();
            }
            //Finds every white space and deletes, creating a string of digits from the sudoku puzzle.
            sudoku = sudoku.replaceAll("\\s+","");
            SudokuPuzzle puzl = new SudokuPuzzle(sudoku);
            System.out.println("Starting puzzle: ");
            System.out.println(puzl);

            try {
                scan = new Scanner(new File(solutionFilename));
                String sudokuSolution = "";
                while (scan.hasNext()) {
                    sudokuSolution += scan.next();
                }
                sudokuSolution = sudokuSolution.replaceAll("\\s+", "");
                SudokuPuzzle solution = new SudokuPuzzle(sudokuSolution);
                SudokuSolver solved = new SudokuSolver(puzl);
                solved.solve();

                System.out.println("Solved Puzzle: ");
                System.out.println(puzl);

                if (puzl.equals(solution)) {
                    System.out.println("Solution is correct!");
                } else {
                    System.out.println("Solution is NOT correct!");
                }
            } catch (FileNotFoundException e) {
                System.out.println("Solution file not found.");
                SudokuSolver solved = new SudokuSolver(puzl);
                solved.solve();
                System.out.println("Solved Puzzle: ");
                System.out.println(puzl);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(1);
        }
    }
}
