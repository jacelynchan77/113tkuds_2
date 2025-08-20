import java.io.*;
import java.util.*;

public class M08_BSTRangedSum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] values = new int[n];
        for (int i = 0; i < n; i++) values[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        TreeNode root = buildTree(values);
        long sum = rangeSumBST(root, L, R);

        System.out.println("Sum: " + sum);
    }

    // Build binary tree from level-order array (-1 means null)
    /*
     * 時間複雜度: O(n)
     * 說明：
     * 1. 每個輸入值都只處理一次，用來建立樹節點並連結子節點。
     * 2. 佇列操作為 O(1)（均攤）。
     * 3. 因此總體時間複雜度為 O(n)。
     */
    private static TreeNode buildTree(int[] a) {
        if (a.length == 0 || a[0] == -1) return null;

        TreeNode root = new TreeNode(a[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        int i = 1;
        while (i < a.length) {
            TreeNode cur = q.poll();

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

    // DFS with BST pruning:
    // If node.val < L → only explore right; if node.val > R → only explore left.
    /*
     * 時間複雜度: O(m)（最壞情況 O(n)）
     * 說明：
     * 1. 在最佳/平均情況下，利用 BST 性質剪枝，可以略過整個區間外的子樹，
     *    只拜訪 m 個與區間 [L, R] 相交的節點。
     * 2. 在最壞情況（無法剪枝或樹退化成鏈表）下，可能需要拜訪全部 n 個節點。
     * 3. 因此時間複雜度為 O(m)，且不超過 O(n)。遞迴空間複雜度為 O(h)，h 為樹高。
     */
    private static long rangeSumBST(TreeNode node, int L, int R) {
        if (node == null) return 0;

        if (node.val < L) {
            // 整個左子樹都小於 L → 略過左子樹
            return rangeSumBST(node.right, L, R);
        } else if (node.val > R) {
            // 整個右子樹都大於 R → 略過右子樹
            return rangeSumBST(node.left, L, R);
        } else {
            // 當前節點在區間內 → 納入並繼續探索左右子樹
            return node.val
                 + rangeSumBST(node.left, L, R)
                 + rangeSumBST(node.right, L, R);
        }
    }
}

// Basic tree node
class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int v) { this.val = v; }
}
