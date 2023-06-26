public class Main {
    public static void main(String[] args) {

        RBTree tree = new RBTree();
        for (int i = 1; i <= 15; i++) {
            tree.insert(i);
            System.out.println(tree);
        }
    }
}