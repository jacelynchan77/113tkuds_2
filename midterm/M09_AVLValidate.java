import java.io.*;
import java.util.*;

public class M09_AVLValidate {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] vals = new int[n];
        for (int i = 0; i < n; i++) vals[i] = Integer.parseInt(st.nextToken());

        TreeNode root = buildTree(vals);

        // First: check BST property (strict: min < val < max)
        boolean isBST = isBST(root, Long.MIN_VALUE, Long.MAX_VALUE);

        if (!isBST) {
            System.out.println("Invalid BST");
            return;
        }

        // Second: check AVL balance (|BF| <= 1 everywhere)
        boolean isAVL = isAVL(root);

        if (!isAVL) {
            System.out.println("Invalid AVL");
        } else {
            System.out.println("Valid");
        }
    }

    // ---------- Build Tree from level-order where -1 represents null ----------
    /*
     * 時間複雜度: O(n)
     * 說明：
     * 1. 每個輸入元素都只處理一次。
     * 2. 每個非空節點最多掛兩個子節點，佇列操作為 O(1)。
     * 3. 總體成本 = O(n)。
     */
    private static TreeNode buildTree(int[] a) {
        if (a.length == 0 || a[0] == -1) return null;

        TreeNode root = new TreeNode(a[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        int i = 1;
        while (i < a.length) {
            TreeNode cur = q.poll();
            if (cur == null) break; // safety

            // left child
            if (i < a.length && a[i] != -1) {
                cur.left = new TreeNode(a[i]);
                q.offer(cur.left);
            }
            i++;

            // right child
            if (i < a.length && a[i] != -1) {
                cur.right = new TreeNode(a[i]);
                q.offer(cur.right);
            }
            i++;
        }
        return root;
    }

    // ---------- Check BST with min/max bounds (strict inequalities) ----------
    /*
     * 時間複雜度: O(n)
     * 說明：
     * 1. 每個節點只會被拜訪一次，並進行常數時間的上下界檢查。
     * 2. 遞迴傳遞 (min, max) 不會額外增加迴圈。
     * 3. 總體時間複雜度 = O(n)。
     */
    private static boolean isBST(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (!(min < node.val && node.val < max)) return false;
        return isBST(node.left, min, node.val) && isBST(node.right, node.val, max);
    }

    // ---------- Check AVL: post-order returns height or sentinel on failure ----------
    /*
     * 時間複雜度: O(n)
     * 說明：
     * 1. 後序 DFS 每個節點只拜訪一次。
     * 2. 每個節點只需 O(1) 工作（高度計算與差值檢查）。
     * 3. 總體時間複雜度 = O(n)。（額外空間 O(h)，h 為樹高）
     */
    private static boolean isAVL(TreeNode root) {
        return heightOrFail(root) != SENTINEL_FAIL;
    }

    private static final int SENTINEL_FAIL = Integer.MIN_VALUE / 2;

    // returns height if balanced, otherwise SENTINEL_FAIL
    private static int heightOrFail(TreeNode node) {
        if (node == null) return 0;

        int hl = heightOrFail(node.left);
        if (hl == SENTINEL_FAIL) return SENTINEL_FAIL;

        int hr = heightOrFail(node.right);
        if (hr == SENTINEL_FAIL) return SENTINEL_FAIL;

        if (Math.abs(hl - hr) > 1) return SENTINEL_FAIL;

        return Math.max(hl, hr) + 1;
    }
}

// ---------- TreeNode ----------
class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int v) { this.val = v; }
}
