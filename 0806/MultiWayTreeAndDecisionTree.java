
import java.util.*;

public class MultiWayTreeAndDecisionTree {

    static class Node {

        String name;
        List<Node> children = new ArrayList<>();

        Node(String n) {
            name = n;
        }
    }

    // 1. create sample multiway tree
    public static Node sample() {
        Node root = new Node("root");
        Node a = new Node("A"), b = new Node("B"), c = new Node("C");
        root.children.add(a);
        root.children.add(b);
        root.children.add(c);
        a.children.add(new Node("A1"));
        a.children.add(new Node("A2"));
        b.children.add(new Node("B1"));
        return root;
    }

    // 2. DFS & BFS
    public static void dfs(Node root) {
        if (root == null) {
            return;
        }
        System.out.println(root.name);
        for (Node ch : root.children) {
            dfs(ch);
        }
    }

    public static void bfs(Node root) {
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node cur = q.poll();
            System.out.println(cur.name);
            for (Node ch : cur.children) {
                q.add(ch);
            }
        }
    }

    // 3. simple decision tree: guess number (1..10) using yes/no questions simulated
    // We'll simulate a tiny decision tree (<=5 -> left else right)
    static class DecisionNode {

        String question;
        DecisionNode yes, no;
        Integer guess;

        DecisionNode(String q) {
            question = q;
        }

        DecisionNode(Integer g) {
            guess = g;
        }
    }

    public static DecisionNode buildDecision() {
        DecisionNode root = new DecisionNode("Is number <= 5?");
        root.yes = new DecisionNode("Is number <= 3?");
        root.no = new DecisionNode("Is number <= 8?");
        root.yes.yes = new DecisionNode(2);
        root.yes.no = new DecisionNode(4);
        root.no.yes = new DecisionNode(7);
        root.no.no = new DecisionNode(9);
        return root;
    }

    // simulate with given number
    public static int decide(DecisionNode root, int number) {
        DecisionNode cur = root;
        while (cur.guess == null) {
            if (cur.question.contains("<= 5")) {
                if (number <= 5) {
                    cur = cur.yes;
                } else {
                    cur = cur.no;
                }
            } else if (cur.question.contains("<= 3")) {
                if (number <= 3) {
                    cur = cur.yes;
                } else {
                    cur = cur.no;
                }
            } else if (cur.question.contains("<= 8")) {
                if (number <= 8) {
                    cur = cur.yes;
                } else {
                    cur = cur.no;
                }
            } else {
                return -1;
            }
        }
        return cur.guess;
    }

    // 4. height and degree (max children)
    public static int height(Node root) {
        if (root == null) {
            return 0;
        }
        int h = 0;
        for (Node ch : root.children) {
            h = Math.max(h, height(ch));
        }
        return h + 1;
    }

    public static int maxDegree(Node root) {
        if (root == null) {
            return 0;
        }
        int m = root.children.size();
        for (Node ch : root.children) {
            m = Math.max(m, maxDegree(ch));
        }
        return m;
    }

    public static void main(String[] args) {
        Node root = sample();
        System.out.println("DFS:");
        dfs(root);
        System.out.println("BFS:");
        bfs(root);

        DecisionNode droot = buildDecision();
        System.out.println("Decision guess for 2: " + decide(droot, 2));
        System.out.println("Decision guess for 7: " + decide(droot, 7));

        System.out.println("Multiway tree height: " + height(root));
        System.out.println("Multiway tree max degree: " + maxDegree(root));
    }
}
