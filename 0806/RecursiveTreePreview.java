
import java.util.*;

public class RecursiveTreePreview {

    static class Node {

        String name;
        List<Node> children = new ArrayList<>();

        public Node(String name) {
            this.name = name;
        }
    }

    public static int countFiles(Node dir) {
        if (dir.children.isEmpty()) {
            return 1;
        }
        int count = 0;
        for (Node child : dir.children) {
            count += countFiles(child);
        }
        return count;
    }

    public static void printMenu(Node menu, String indent) {
        System.out.println(indent + menu.name);
        for (Node child : menu.children) {
            printMenu(child, indent + "  ");
        }
    }

    public static List<Object> flattenList(List<Object> nested) {
        List<Object> flat = new ArrayList<>();
        for (Object obj : nested) {
            if (obj instanceof List) {
                flat.addAll(flattenList((List<Object>) obj));
            } else {
                flat.add(obj);
            }
        }
        return flat;
    }

    public static int maxDepth(List<Object> nested) {
        int depth = 1;
        for (Object obj : nested) {
            if (obj instanceof List) {
                depth = Math.max(depth, 1 + maxDepth((List<Object>) obj));
            }
        }
        return depth;
    }

    public static void main(String[] args) {
        Node root = new Node("root");
        Node folder1 = new Node("folder1");
        folder1.children.add(new Node("file1"));
        folder1.children.add(new Node("file2"));
        Node folder2 = new Node("folder2");
        folder2.children.add(new Node("file3"));
        root.children.add(folder1);
        root.children.add(folder2);

        System.out.println("Total files: " + countFiles(root));

        Node menu = new Node("Menu");
        Node submenu1 = new Node("Submenu 1");
        submenu1.children.add(new Node("Option 1"));
        submenu1.children.add(new Node("Option 2"));
        menu.children.add(submenu1);
        printMenu(menu, "");

        List<Object> nested = Arrays.asList(1, Arrays.asList(2, Arrays.asList(3, 4)), 5);
        System.out.println("Flatten: " + flattenList(nested));
        System.out.println("Max depth: " + maxDepth(nested));
    }
}
