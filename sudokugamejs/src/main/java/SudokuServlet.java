import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@WebServlet("/SudokuServlet")
public class SudokuServlet extends HttpServlet {

    
    private String[][] puzzle;
    private String[][] solution;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        int difficulty = Integer.parseInt(request.getParameter("difficulty"));

        
        newGame(difficulty);

      
        request.setAttribute("puzzle", puzzle);

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    
    private void newGame(int difficulty) {
      
        String[][] grid = getGridInit();

       
        solution = solveGrid(grid);

    
        puzzle = makeItPuzzle(solution, difficulty);
    }

    
    private String[][] getGridInit() {
        String[][] result = new String[9][9];
        Random rand = new Random();

        for (int i = 1; i <= 9; i++) {
            int row = rand.nextInt(9);
            int col = rand.nextInt(9);
            if (result[row][col] == null) {
                result[row][col] = String.valueOf(i);
            } else {
                i--; // retry if position already taken
            }
        }

        // Initialize empty cells
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (result[i][j] == null) {
                    result[i][j] = "0";
                }
            }
        }

        return result;
    }

    // Placeholder for solving grid - will use backtracking
    private String[][] solveGrid(String[][] grid) {
        // Implement a backtracking algorithm here to solve the puzzle
        // For now, returning the grid as-is
        return grid;
    }

    // Method to make the puzzle from the solved grid based on difficulty
    private String[][] makeItPuzzle(String[][] grid, int difficulty) {
        String[][] puzzle = new String[9][9];
        int emptyCells = 5 * difficulty + 20;

        for (int i = 0; i < 9; i++) {
            System.arraycopy(grid[i], 0, puzzle[i], 0, 9);
        }

        Random rand = new Random();
        while (emptyCells > 0) {
            int row = rand.nextInt(9);
            int col = rand.nextInt(9);
            if (!puzzle[row][col].equals("0")) {
                puzzle[row][col] = "0";
                emptyCells--;
            }
        }

        return puzzle;
    }
}
