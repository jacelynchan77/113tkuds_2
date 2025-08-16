// File: AVLLeaderboardSystem.java

import java.util.*;

public class AVLLeaderboardSystem {

    static class PlayerNode {

        String playerName;
        int score;
        PlayerNode left, right;
        int height;
        int size; // number of nodes in subtree including self

        PlayerNode(String playerName, int score) {
            this.playerName = playerName;
            this.score = score;
            this.height = 1;
            this.size = 1;
        }

        void updateHeightAndSize() {
            int leftHeight = (left != null) ? left.height : 0;
            int rightHeight = (right != null) ? right.height : 0;
            this.height = Math.max(leftHeight, rightHeight) + 1;

            int leftSize = (left != null) ? left.size : 0;
            int rightSize = (right != null) ? right.size : 0;
            this.size = leftSize + rightSize + 1;
        }

        int getBalance() {
            int leftHeight = (left != null) ? left.height : 0;
            int rightHeight = (right != null) ? right.height : 0;
            return leftHeight - rightHeight;
        }
    }

    static class AVLLeaderboard {

        private PlayerNode root;
        private Map<String, PlayerNode> playerMap = new HashMap<>();

        // Add or update player score
        public void addOrUpdatePlayer(String name, int score) {
            if (playerMap.containsKey(name)) {
                // Remove old score first
                root = delete(root, playerMap.get(name).score, name);
            }
            root = insert(root, name, score);
        }

        // Insert node
        private PlayerNode insert(PlayerNode node, String name, int score) {
            if (node == null) {
                PlayerNode newNode = new PlayerNode(name, score);
                playerMap.put(name, newNode);
                return newNode;
            }

            if (score > node.score || (score == node.score && name.compareTo(node.playerName) < 0)) {
                node.left = insert(node.left, name, score);
            } else {
                node.right = insert(node.right, name, score);
            }

            node.updateHeightAndSize();
            return balance(node);
        }

        // Delete node by score and name
        private PlayerNode delete(PlayerNode node, int score, String name) {
            if (node == null) {
                return null;
            }

            if (score > node.score || (score == node.score && name.compareTo(node.playerName) < 0)) {
                node.left = delete(node.left, score, name);
            } else if (score < node.score || (score == node.score && name.compareTo(node.playerName) > 0)) {
                node.right = delete(node.right, score, name);
            } else {
                // Found node
                playerMap.remove(name);
                if (node.left == null) {
                    return node.right;
                }
                if (node.right == null) {
                    return node.left;
                }

                // Two children: find inorder predecessor
                PlayerNode maxLeft = findMax(node.left);
                node.playerName = maxLeft.playerName;
                node.score = maxLeft.score;
                node.left = delete(node.left, maxLeft.score, maxLeft.playerName);
            }

            node.updateHeightAndSize();
            return balance(node);
        }

        private PlayerNode findMax(PlayerNode node) {
            while (node.right != null) {
                node = node.right;
            }
            return node;
        }

        // AVL rotations
        private PlayerNode rightRotate(PlayerNode y) {
            PlayerNode x = y.left;
            PlayerNode T2 = x.right;

            x.right = y;
            y.left = T2;

            y.updateHeightAndSize();
            x.updateHeightAndSize();

            return x;
        }

        private PlayerNode leftRotate(PlayerNode x) {
            PlayerNode y = x.right;
            PlayerNode T2 = y.left;

            y.left = x;
            x.right = T2;

            x.updateHeightAndSize();
            y.updateHeightAndSize();

            return y;
        }

        private PlayerNode balance(PlayerNode node) {
            int balance = node.getBalance();

            if (balance > 1 && node.left.getBalance() >= 0) {
                return rightRotate(node);
            }
            if (balance > 1 && node.left.getBalance() < 0) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
            if (balance < -1 && node.right.getBalance() <= 0) {
                return leftRotate(node);
            }
            if (balance < -1 && node.right.getBalance() > 0) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
            return node;
        }

        // Get rank of player (1 = highest score)
        public int getPlayerRank(String name) {
            PlayerNode player = playerMap.get(name);
            if (player == null) {
                return -1;
            }
            return 1 + countHigher(root, player.score, name);
        }

        private int countHigher(PlayerNode node, int score, String name) {
            if (node == null) {
                return 0;
            }

            if (score < node.score || (score == node.score && name.compareTo(node.playerName) > 0)) {
                // Node is higher score
                int rightSize = (node.right != null) ? node.right.size : 0;
                return rightSize + 1 + countHigher(node.left, score, name);
            } else {
                return countHigher(node.right, score, name);
            }
        }

        // Get top K players
        public List<String> getTopKPlayers(int k) {
            List<String> result = new ArrayList<>();
            collectTopK(root, k, result);
            return result;
        }

        private void collectTopK(PlayerNode node, int k, List<String> result) {
            if (node == null || result.size() >= k) {
                return;
            }
            collectTopK(node.left, k, result);
            if (result.size() < k) {
                result.add(node.playerName + "(" + node.score + ")");
            }
            collectTopK(node.right, k, result);
        }

        // Print leaderboard
        public void printLeaderboard() {
            System.out.println("Leaderboard (Player(score)):");
            printInOrder(root);
            System.out.println();
        }

        private void printInOrder(PlayerNode node) {
            if (node != null) {
                printInOrder(node.left);
                System.out.print(node.playerName + "(" + node.score + ") ");
                printInOrder(node.right);
            }
        }
    }

    // Main method: demo
    public static void main(String[] args) {
        AVLLeaderboard leaderboard = new AVLLeaderboard();

        leaderboard.addOrUpdatePlayer("Alice", 50);
        leaderboard.addOrUpdatePlayer("Bob", 70);
        leaderboard.addOrUpdatePlayer("Charlie", 60);
        leaderboard.addOrUpdatePlayer("David", 80);

        System.out.println("Initial leaderboard:");
        leaderboard.printLeaderboard();

        System.out.println("\nTop 3 players: " + leaderboard.getTopKPlayers(3));
        System.out.println("Rank of Charlie: " + leaderboard.getPlayerRank("Charlie"));

        // Update score
        System.out.println("\nUpdating Alice's score to 90...");
        leaderboard.addOrUpdatePlayer("Alice", 90);

        leaderboard.printLeaderboard();
        System.out.println("Rank of Alice: " + leaderboard.getPlayerRank("Alice"));
        System.out.println("Top 3 players: " + leaderboard.getTopKPlayers(3));
    }
}
