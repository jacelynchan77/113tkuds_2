import java.io.*;
import java.util.*;

public class M07_BinaryTreeLeftView {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = Integer.parseInt(st.nextToken());
        }

        TreeNode root = buildTree(values);

        List<Integer> leftView = getLeftView(root);

        System.out.print("LeftView:");
        for (int val : leftView) {
            System.out.print(" " + val);
        }
        System.out.println();
    }

    // Build tree from level-order array (-1 = null)
    /*
     * 時間複雜度: O(n)
     * 說明：
     * 1. 每個輸入值都只處理一次。
     * 2. 對於每個節點，最多建立兩個子節點。
     * 3. 因此總體時間複雜度為 O(n)。
     */
    private static TreeNode buildTree(int[] values) {
        if (values.length == 0 || values[0] == -1) return null;

        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (i < values.length) {
            TreeNode current = queue.poll();

            // Left child
            if (i < values.length && values[i] != -1) {
                current.left = new TreeNode(values[i]);
                queue.offer(current.left);
            }
            i++;

            // Right child
            if (i < values.length && values[i] != -1) {
                current.right = new TreeNode(values[i]);
                queue.offer(current.right);
            }
            i++;
        }

        return root;
    }

    // BFS to find left view
    /*
     * 時間複雜度: O(n)
     * 說明：
     * 1. BFS 會完整拜訪所有節點各一次。
     * 2. 每一層只需紀錄第一個節點即可。
     * 3. 因此總體時間複雜度為 O(n)。
     */
    private static List<Integer> getLeftView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                // First node of this level → left view
                if (i == 0) {
                    result.add(node.val);
                }

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }

        return result;
    }
}

// TreeNode definition
class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int v) {
        this.val = v;
        this.left = null;
        this.right = null;
    }
}
