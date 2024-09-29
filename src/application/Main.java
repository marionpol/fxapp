package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int size = 3;
    private static final String playerX = "X";
    private static final String playerO = "O";
    private Label textLabel;
    private Button[][] gameButtons;
    private boolean playerXTurn = true;
    private boolean gameEnded = false;

    @Override
    public void start(Stage game) {
        BorderPane layout = new BorderPane();
        textLabel = new Label("Turn: X ");
        layout.setTop(textLabel);

        GridPane gameGrid = new GridPane();
        gameButtons = new Button[size][size];

        for (int row = 0; row < size; row++){
            for (int col = 0; col < size; col++){
                Button button = new Button();
                button.setOnAction(actionEvent -> {
                    if(gameEnded){
                        return;
                    }
                    if (!button.getText().isEmpty()){
                        return;
                    }
                    String currentPlayer = playerXTurn ? playerX : playerO;
                    button.setText(currentPlayer);

                    if(checkWin(currentPlayer)){
                        gameEnded = true;
                        textLabel.setText("The end!" + currentPlayer + " has won!");
                    } else if (isBoardFull()) {
                        gameEnded = true;
                        textLabel.setText("The end! It's a tie!");
                    } else {
                        playerXTurn = !playerXTurn;

                        if (playerXTurn) {
                            textLabel.setText("Turn: X");
                        } else {
                            textLabel.setText("Turn: O");
                        }
                    }
                });
                gameButtons[row][col] = button;
                gameGrid.add(button, col, row);
        }
    }
       layout.setCenter(gameGrid);

        Scene scene = new Scene(layout);
        game.setScene(scene);
        game.setTitle("Tic-Tac-Toe");
        game.show();
    }

    private boolean checkWin(String player) {
        // Check rows
        for (int row = 0; row < size; row++) {
            if (checkRow(row, player)) {
                gameEnded = true;
                return true;
            }
        }

        // Check columns
        for (int col = 0; col < size; col++) {
            if (checkCol(col, player)) {
                gameEnded = true;
                return true;
            }
        }

        // Check diagonals
        if (checkDiagonal(0, 0, player) || checkDiagonal(0, size - 1, player)) {
            gameEnded = true;
            return true;
        }

        // Check for potential win (blocking opponent) only after the second player has made their move
        if (playerXTurn) {
            String opponent = player == playerX ? playerO : playerX;
            for (int row = 0; row < size; row++) {
                if (checkRow(row, opponent) && countEmptyCellsInRow(row) == 1) {
                    // Check if the empty cell is still empty
                    int emptyCol = findEmptyCellInRow(row);
                    if (emptyCol != -1 && gameButtons[row][emptyCol].getText().isEmpty()) {
                        // Check if the empty cell can lead to a win
                        if (checkRow(row, player) && countEmptyCellsInRow(row) == 0) {
                            gameEnded = true;
                            return true;
                        }
                    }
                }
            }
            for (int col = 0; col < size; col++) {
                if (checkCol(col, opponent) && countEmptyCellsInCol(col) == 1) {
                    // Check if the empty cell is still empty
                    int emptyRow = findEmptyCellInCol(col);
                    if (emptyRow != -1 && gameButtons[emptyRow][col].getText().isEmpty()) {
                        // Check if the empty cell can lead to a win
                        if (checkCol(col, player) && countEmptyCellsInCol(col) == 0) {
                            gameEnded = true;
                            return true;
                        }
                    }
                }
            }
            if (checkDiagonal(0, 0, opponent) && countEmptyCellsInDiagonal(0, 0) == 1) {
                // Check if the empty cell is still empty
                int emptyRow = findEmptyCellInDiagonal(0, 0);
                int emptyCol = findEmptyCellInDiagonal(0, 0);
                if (emptyRow != -1 && emptyCol != -1 && gameButtons[emptyRow][emptyCol].getText().isEmpty()) {
                    // Check if the empty cell can lead to a win
                    if (checkDiagonal(0, 0, player) && countEmptyCellsInDiagonal(0, 0) == 0) {
                        gameEnded = true;
                        return true;
                    }
                }
            }
            if (checkDiagonal(0, size - 1, opponent) && countEmptyCellsInDiagonal(0, size - 1) == 1) {
                // Check if the empty cell is still empty
                int emptyRow = findEmptyCellInDiagonal(0, size - 1);
                int emptyCol = findEmptyCellInDiagonal(0, size - 1);
                if (emptyRow != -1 && emptyCol != -1 && gameButtons[emptyRow][emptyCol].getText().isEmpty()) {
                    // Check if the empty cell can lead to a win
                    if (checkDiagonal(0, size - 1, player) && countEmptyCellsInDiagonal(0, size - 1) == 0) {
                        gameEnded = true;
                        return true;
                    }
                }
            }
        }

        // Check if the board is full (for a tie)
        return isBoardFull();
    }


    private int countEmptyCellsInRow(int row) {
        int count = 0;
        for (int col = 0; col < size; col++) {
            if (gameButtons[row][col].getText().isEmpty()) {
                count++;
            }
        }
        return count;
    }

    private int countEmptyCellsInCol(int col) {
        int count = 0;
        for (int row = 0; row < size; row++) {
            if (gameButtons[row][col].getText().isEmpty()) {
                count++;
            }
        }
        return count;
    }

    private int countEmptyCellsInDiagonal(int startRow, int startCol) {
        int count = 0;
        int row = startRow;
        int col = startCol;
        while (row < size && col < size) {
            if (gameButtons[row][col].getText().isEmpty()) {
                count++;
            }
            row++;
            col++;
        }
        return count;
    }

    private int findEmptyCellInRow(int row) {
        for (int col = 0; col < size; col++) {
            if (gameButtons[row][col].getText().isEmpty()) {
                return col;
            }
        }
        return -1;
    }

    private int findEmptyCellInCol(int col) {
        for (int row = 0; row < size; row++) {
            if (gameButtons[row][col].getText().isEmpty()) {
                return row;
            }
        }
        return -1;
    }

    private int findEmptyCellInDiagonal(int startRow, int startCol) {
        int row = startRow;
        int col = startCol;
        while (row < size && col < size) {
            if (gameButtons[row][col].getText().isEmpty()) {
                return row;
            }
            row++;
            col++;
        }
        return -1;
    }

    private boolean checkRow(int row, String player) {
        boolean allCellsMatch = true;
        for (int col = 0; col < size; col++) {
            if (!gameButtons[row][col].getText().equals(player)) {
                allCellsMatch = false;
                break; // Exit the loop early if a mismatch is found
            }
        }
        return allCellsMatch;
    }

    private boolean checkCol(int col, String player) {
        boolean allCellsMatch = true;
        for (int row = 0; row < size; row++) {
            if (!gameButtons[row][col].getText().equals(player)) {
                allCellsMatch = false;
                break; // Exit the loop early if a mismatch is found
            }
        }
        return allCellsMatch;
    }

    private boolean checkDiagonal(int startRow, int startCol, String player) {
        boolean allCellsMatch = true;

        // Main diagonal (top-left to bottom-right)
        if (startRow == 0 && startCol == 0) {
            for (int i = 0; i < size; i++) {
                if (!gameButtons[i][i].getText().equals(player)) {
                    allCellsMatch = false;
                    break;
                }
            }
        }

        // Anti-diagonal (top-right to bottom-left)
        else if (startRow == 0 && startCol == size - 1) {
            for (int i = 0; i < size; i++) {
                if (!gameButtons[i][size - 1 - i].getText().equals(player)) {
                    allCellsMatch = false;
                    break;
                }
            }
        }

        return allCellsMatch;
    }

    private boolean isBoardFull() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (gameButtons[row][col].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        launch(Main.class);
    }
}