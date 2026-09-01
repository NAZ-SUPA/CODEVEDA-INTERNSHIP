import java.util.Scanner;

public class BINARY_SEARCH_TREE {
    public static TreeNode root;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        input();
    }

    public static void input() {
        System.out.println("\nWELCOME TO BINARY SEARCH TREE PROGRAM\n");

        while (true) {
            System.out.println(
                    "\nCHOOSE FUNCTIONALITY (INSERTION, DELETION, SEARCH, IN-ORDER, PRE-ORDER, POST-ORDER, EXIT):");
            String function = scanner.nextLine().trim().toUpperCase();

            if (function.equals("EXIT")) {
                System.out.println("Exiting program.");
                break;
            }

            int data = 0;
            if (function.equals("INSERTION") || function.equals("DELETION") || function.equals("SEARCH")) {
                System.out.print("ENTER INTEGER: ");
                while (!scanner.hasNextInt()) {
                    System.out.println("PLEASE ENTER A VALID INTEGER: ");
                    scanner.next();
                }
                data = scanner.nextInt();
                scanner.nextLine(); // Clear newline buffer
            }

            switch (function) {
                case "INSERTION":
                    insertion(data, root);
                    break;
                case "DELETION":
                    delete(search(data, root));
                    break;
                case "SEARCH":
                    TreeNode result = search(data, root);
                    if (result != null) {
                        System.out.println("FOUND NODE: " + result.data);
                    } else {
                        System.out.println("THE NODE DOES NOT EXIST IN THE TREE");
                    }
                    break;
                case "IN-ORDER":
                    in_order(root);
                    System.out.println("NULL");
                    break;
                case "PRE-ORDER":
                    pre_order(root);
                    System.out.println("NULL");
                    break;
                case "POST-ORDER":
                    post_order(root);
                    System.out.println("NULL");
                    break;
                default:
                    System.out.println("INVALID COMMAND. Please choose from the list.");
                    break;
            }
        }
    }

    // Insertion
    public static void insertion(int data, TreeNode current) {
        TreeNode newNode = new TreeNode(data);
        if (root == null) {
            root = newNode;
            System.out.println("NODE ADDED AS ROOT.");
            return;
        }

        if (data < current.data) {
            if (current.left_child == null) {
                current.left_child = newNode;
                newNode.parent = current;
                System.out.println("NODE INSERTED SUCCESSFULLY.");
            } else {
                insertion(data, current.left_child);
            }
        } else if (data > current.data) {
            if (current.right_child == null) {
                current.right_child = newNode;
                newNode.parent = current;
                System.out.println("NODE INSERTED SUCCESSFULLY.");
            } else {
                insertion(data, current.right_child);
            }
        } else {
            System.out.println("DUPLICATE VALUE: Node already exists.");
        }
    }

    // Search
    public static TreeNode search(int data, TreeNode current) {
        if (current == null || current.data == data) {
            return current;
        } else if (data < current.data) {
            return search(data, current.left_child);
        } else {
            return search(data, current.right_child);
        }
    }

    // Deletion
    public static void delete(TreeNode current) {
        if (current == null) {
            System.out.println("NODE DOES NOT EXIST IN THE TREE.");
            return;
        }

        // Case 1: Leaf Node
        if (current.left_child == null && current.right_child == null) {
            if (current == root) {
                root = null;
            } else if (current.parent.left_child == current) {
                current.parent.left_child = null;
            } else {
                current.parent.right_child = null;
            }
        }
        // Case 3: Two Children (Replace data with successor and delete successor)
        else if (current.left_child != null && current.right_child != null) {
            TreeNode successor = find_successor(current.right_child);
            int successorVal = successor.data;
            delete(successor);
            current.data = successorVal;
            return;
        }
        // Case 2: One Child
        else {
            TreeNode child = (current.left_child != null) ? current.left_child : current.right_child;
            if (current == root) {
                root = child;
                child.parent = null;
            } else if (current.parent.left_child == current) {
                current.parent.left_child = child;
                child.parent = current.parent;
            } else {
                current.parent.right_child = child;
                child.parent = current.parent;
            }
        }
        System.out.println("NODE DELETED SUCCESSFULLY.");
    }

    // Successor Finder
    public static TreeNode find_successor(TreeNode current) {
        while (current.left_child != null) {
            current = current.left_child;
        }
        return current;
    }

    // Traversals
    public static void in_order(TreeNode current) {
        if (current == null)
            return;
        in_order(current.left_child);
        System.out.print(current.data + " -> ");
        in_order(current.right_child);
    }

    public static void pre_order(TreeNode current) {
        if (current == null)
            return;
        System.out.print(current.data + " -> ");
        pre_order(current.left_child);
        pre_order(current.right_child);
    }

    public static void post_order(TreeNode current) {
        if (current == null)
            return;
        post_order(current.left_child);
        post_order(current.right_child);
        System.out.print(current.data + " -> ");
    }
}

class TreeNode {
    int data;
    TreeNode left_child;
    TreeNode right_child;
    TreeNode parent;

    public TreeNode(int data) {
        this.data = data;
    }
}