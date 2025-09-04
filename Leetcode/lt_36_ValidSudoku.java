import java.util.*;

class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];
        boolean[][] box = new boolean[9][9];
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char ch = board[r][c];
                if (ch == '.') continue;
                int d = ch - '1';
                int b = (r / 3) * 3 + (c / 3);
                if (row[r][d] || col[c][d] || box[b][d]) return false;
                row[r][d] = col[c][d] = box[b][d] = true;
            }
        }
        return true;
    }
}

public class lt_36_ValidSudoku {
    public static void main(String[] args) {
        char[][] board = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        System.out.println(new Solution().isValidSudoku(board));
    }
}

/*
解題邏輯與思路
以三個 9x9 布林表追蹤是否出現過某數字：
1) row[r][d]：第 r 列是否已出現數字 d+1
2) col[c][d]：第 c 欄是否已出現數字 d+1
3) box[b][d]：第 b 個 3x3 子宮格是否已出現數字 d+1，其中 b = (r/3)*3 + (c/3)
逐格掃描，遇到 '.' 跳過；若任一表已為 true 則違規回傳 false，否則標記為 true。掃描完皆無衝突則為有效。
時間複雜度 O(81) ≈ O(1)，空間複雜度 O(1)（固定大小的 3 個 9x9 布林表）。
*/
