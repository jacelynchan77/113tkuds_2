import java.util.*;

class Solution {
    public void solveSudoku(char[][] board) {
        int[] row = new int[9], col = new int[9], box = new int[9];
        List<int[]> blanks = new ArrayList<>();

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char ch = board[r][c];
                if (ch == '.') {
                    blanks.add(new int[]{r, c});
                } else {
                    int bit = 1 << (ch - '1');
                    row[r] |= bit;
                    col[c] |= bit;
                    box[idx(r, c)] |= bit;
                }
            }
        }

        // 可選：按候選數量少的優先（簡單的預排序，減少回溯深度）
        blanks.sort((a, b) -> Integer.compare(
                Integer.bitCount(~(row[a[0]] | col[a[1]] | box[idx(a[0], a[1])]) & 0x1FF),
                Integer.bitCount(~(row[b[0]] | col[b[1]] | box[idx(b[0], b[1])]) & 0x1FF)
        ));

        dfs(0, blanks, board, row, col, box);
    }

    private boolean dfs(int k, List<int[]> blanks, char[][] board,
                        int[] row, int[] col, int[] box) {
        if (k == blanks.size()) return true;
        int r = blanks.get(k)[0], c = blanks.get(k)[1];
        int used = row[r] | col[c] | box[idx(r, c)];
        int candidates = (~used) & 0x1FF; // 九個位元代表 1..9

        while (candidates != 0) {
            int pick = candidates & -candidates;                // 取最低位的 1
            int d = Integer.numberOfTrailingZeros(pick) + 1;    // 對應數字 1..9
            int bit = 1 << (d - 1);

            board[r][c] = (char) ('0' + d);
            row[r] |= bit; col[c] |= bit; box[idx(r, c)] |= bit;

            if (dfs(k + 1, blanks, board, row, col, box)) return true;

            row[r] ^= bit; col[c] ^= bit; box[idx(r, c)] ^= bit;
            board[r][c] = '.';
            candidates ^= pick; // 移除剛嘗試的候選
        }
        return false;
    }

    private int idx(int r, int c) { return (r / 3) * 3 + (c / 3); }
}

public class lt_37_SudokuSolver {
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
        new Solution().solveSudoku(board);
        for (int r = 0; r < 9; r++) {
            System.out.println(Arrays.toString(rowToStringArray(board[r])));
        }
    }

    private static String[] rowToStringArray(char[] row) {
        String[] out = new String[9];
        for (int i = 0; i < 9; i++) out[i] = String.valueOf(row[i]);
        return out;
    }
}

/*
解題邏輯與思路
1) 狀態表達：用三個位元遮罩（row[9]、col[9]、box[9]）記錄每列、每欄、每個 3x3 宮已使用的數字。
   - 第 k 位（0..8）代表數字 (k+1) 是否已出現。
2) 候選計算：對於空格 (r,c)，可用數字的集合為 (~(row[r] | col[c] | box[idx(r,c)]) & 0x1FF)。
3) 回溯填格：依序挑選空格，對其候選數字逐一嘗試；放入後更新三個遮罩並遞迴。
   - 若之後無法完成，回溯復原該格與遮罩。
   - 可先將所有空格按「候選數量少」排序（MRV），能有效減少搜尋樹分支。
4) 正確性：每次放數皆同時滿足列 / 欄 / 宮不重複；唯一解一定能被回溯探索到。
時間複雜度：最壞指數級（回溯），但位元運算加上 MRV 能大幅剪枝；空間為 O(1)（固定大小狀態）+ O(空格數) 的遞迴棧。
*/
