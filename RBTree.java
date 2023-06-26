public class RBTree {
    private Node root;
    class Node{
        int value;
        Node left;
        Node right;
        Color color;
    }

    enum Color{
        BLACK,
        RED
    }

    public void insert(int value){
        if(root == null){
            root = new Node();
            root.value = value;
        }else{
            insert(root, value);
        }
        root.color = BLACK;
    }

    public void insert (Node node, int value){
        if(node.value != value){
            if(node.value < value){
                if(node.right == null){
                    node.right = new Node();
                    node.right.value = value;
                    node.right.color = RED;
                }else{
                    insert(node.right, value);
                }
            }
            else{
                if(node.left == null){
                    node.left = new Node();
                    node.left.value = value;
                    node.left.color = RED;
                }else{
                    insert(node.left, value);
                }
            }
        }
    }

    public Node find(int value){
        return find(root, value);
    }

    private  Node find (Node node, int value){
        if(node == null){
            return null;
        }
        if(node.value == value){
            return node;
        }

        if(node.value < value){
            return find(node.right, value);
        }else{
            return find(node.left, value);
        }


    }

    public Node rightSwitch(Node node){
        Node right = node.right;
        Node rightChild = right.left;
        right.left = node;
        node.right = rightChild;
        right.color = node.color;
        node.color = Color.RED;
        return right;
    }

    public Node leftSwitch(Node node){
        Node left = node.left;
        Node leftChild = left.right;
        left.right = node;
        node.left = leftChild;
        left.color = node.color;
        node.color = Color.RED;
        return left;
    }

    public void switchColor(Node node){
        node.right.color = Color.BLACK;
        node.left.color = Color.BLACK;
        node.color = Color.RED;
    }

    public Node balance(Node node){
        Node res = node;
        boolean flag;
        do {
            flag = false;

            if (res.right != null && res.right.color == Color.RED &&
                    (res.left == null || res.left.color == Color.BLACK)) {
                flag = true;
                res = rightSwitch (res);
            }
            if (res.left != null && res.left.color == Color.RED &&
                    res.left.left != null && res.left.left.color == Color.RED) {
                flag = true;
                res = leftSwitch(res);
            }
            if (res.left != null && res.left.color == Color.RED &&
                    res.right != null && res.right.color == Color.RED) {
               flag = true;
               switchColor(res);
            }
        } while (flag);

        return res;

    }

}
