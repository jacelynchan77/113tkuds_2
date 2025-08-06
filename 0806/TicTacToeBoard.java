
public class TicTacToeBoard {

    static char[][] board = new char[3][3];

    public static void initBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    public static boolean placeMark(int row, int col, char mark) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != '-') {
            return false;
        }
        board[row][col] = mark;
        return true;
    }

    public static boolean checkWin(char mark) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == mark && board[i][1] == mark && board[i][2] == mark) {
                return true;
            }
        }
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == mark && board[1][j] == mark && board[2][j] == mark) {
                return true;
            }
        }
        if (board[0][0] == mark && board[1][1] == mark && board[2][2] == mark) {
            return true;
        }
        if (board[0][2] == mark && board[1][1] == mark && board[2][0] == mark) {
            return true;
        }
        return false;
    }

    public static boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        initBoard();
        placeMark(0, 0, 'X');
        placeMark(1, 1, 'O');
        placeMark(0, 1, 'X');
        placeMark(2, 2, 'O');
        placeMark(0, 2, 'X');
        printBoard();

        System.out.println("X wins? " + checkWin('X'));
        System.out.println("O wins? " + checkWin('O'));
        System.out.println("Draw? " + (isFull() && !checkWin('X') && !checkWin('O')));
    }
}
