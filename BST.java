public class BST <T> {
     BSTNode<T> root, current;

    public BST() {
        root = current = null;
    }

    public boolean empty() {
        return root == null;
    }

    public boolean full() {
        return false;
    }

    public T retrieve() {
        return current.data;
    }


    public boolean update(String key, T data) {
        remove_key(current.key);
        return insert(key, data);
    }
    // Visit action for a node
    private BSTNode<T> visit(BSTNode<T> node) {
        if (node != null) {
            current = node;
            System.out.println(current.key);
            return current;
        }
        else return null;
    }

    // Preorder traversal implementation
    private void preorder(BSTNode<T> p) {
        if (p == null) return;

        // Perform the "visit" action for position p
        visit(p);

        // For each child c in children(p) do preorder(c)
        // In BST, we have left and right children
        preorder(p.left);
        preorder(p.right);
    }

    // Postorder traversal implementation
    private void postorder(BSTNode<T> p) {
        if (p == null) return;

        // For each child c in children(p) do postorder(c)
        // In BST, we have left and right children
        postorder(p.left);
        postorder(p.right);

        // Perform the "visit" action for position p
        visit(p);
    }

    // Inorder traversal implementation
    private void inorder(BSTNode<T> p) {
        if (p == null) return;

        // If p has a left child lc then inorder(lc)
        if (p.left != null) {
            inorder(p.left);
        }

        // Perform the "visit" action for position p
        visit(p);

        // If p has a right child rc then inorder(rc)
        if (p.right != null) {
            inorder(p.right);
        }
    }

    // Public method to traverse the tree in a specified order
    public void traverse(Order order) {
        switch (order) {
            case PREORDER:
                preorder(root);
                break;
            case INORDER:
                inorder(root);
                break;
            case POSTORDER:
                postorder(root);
                break;
        }
    }

    public LinkedList<T> collectAllData() {
        LinkedList<T> collector = new LinkedList<>();
        collectInOrder(root, collector);
        return collector;
    }

    private void collectInOrder(BSTNode<T> node, LinkedList<T> collector) {
        if (node == null) return;

        // Process left subtree
        collectInOrder(node.left, collector);

        // Add current node's data to collector
        collector.insert(node.data);

        // Process right subtree
        collectInOrder(node.right, collector);
    }


    public boolean findkey(String tkey) {
        BSTNode<T> p = root, q = root;
        if (empty())
            return false;

        while (p != null) {
            q = p;
            if (tkey.compareTo(p.key) == 0) {
                current = p;
                return true;
            } else if (tkey.compareTo(p.key) < 0)
                p = p.left;
            else
                p = p.right;
        }
        current = q;
        return false;
    }

    public boolean insert(String key, T val) {
        BSTNode<T> p, q = current;

        if (findkey(key)) {
            current = q;
            return false;
        }

        p = new BSTNode<T>(key, val);
        if (empty()) {
            root = current = p;
            return true;
        } else {

            if (key.compareTo(current.key) < 0) {
                current.left = p;
            } else if (key.compareTo(current.key) > 0) {
                current.right = p;
            }
            current = p;
            return true;
        }


    }

    private BSTNode<T> remove_aux(String key, BSTNode<T> p, BooleanWrapper flag) {
        BSTNode<T> q, child = null;
        if (p == null)
            return null;
        if (key.compareTo(p.key) < 0)
            p.left = remove_aux(key, p.left, flag);
        if (key.compareTo(p.key) > 0) {
            p.right = remove_aux(key, p.right, flag);
        } else {
            flag.set(true);
            if (p.left != null && p.right != null) {
                q = find_min(p.right);
                p.key = q.key;
                p.data = q.data;
                p.right = remove_aux(q.key, p.right, flag);
            } else {
                if (p.right == null)
                    child = p.left;
                else if (p.left == null)
                    child = p.right;
                return child;
            }
        }
        return p;
    }

    private BSTNode<T> find_min(BSTNode<T> p) {
        if (p == null)
            return null;

        while (p.left != null) {
            p = p.left;
        }
        return p;
    }

    public boolean remove_key(String key) {
        BooleanWrapper removed = new BooleanWrapper(false);
        BSTNode<T> p;
        p = remove_aux(key, root, removed);
        current = root = p;
        return removed.get();
    }


}
