<!DOCTYPE html>
<html>
<head>
    <title>Sudoku Game</title>
    <style>
        table { border-collapse: collapse; }
        td { width: 30px; height: 30px; text-align: center; border: 1px solid black; }
    </style>
</head>
<body>
    <h1>Sudoku Game</h1>
    <form action="SudokuServlet" method="post">
        <label for="difficulty">Select Difficulty:</label>
        <select name="difficulty" id="difficulty">
            <option value="0">Expert</option>
            <option value="1">Hard</option>
            <option value="2">Normal</option>
            <option value="3">Easy</option>
            <option value="4">Very Easy</option>
        </select>
        <input type="submit" value="Start Game">
    </form>

    <table>
        <tbody>
            <% 
                String[][] puzzle = (String[][]) request.getAttribute("puzzle");
                if (puzzle != null) {
                    for (int i = 0; i < 9; i++) {
                        out.print("<tr>");
                        for (int j = 0; j < 9; j++) {
                            out.print("<td><input type='text' value='" + puzzle[i][j] + "' " + 
                                      (puzzle[i][j].equals("0") ? "" : "disabled") + "></td>");
                        }
                        out.print("</tr>");
                    }
                }
            %>
        </tbody>
    </table>
</body>
</html>
