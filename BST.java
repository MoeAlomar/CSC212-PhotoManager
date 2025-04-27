public class BST <T>{
    BSTNode<T> root, current;
public BST(){
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

    public boolean findkey(String tkey){
    BSTNode<T>  p = root, q = root;
    if(empty())
        return false;

    while(p != null){
        q = p;
        if (tkey.compareTo(p.key) < 0)
            p = p.left;
        else if(tkey.compareTo(p.key) > 0)
            p = p.right;
        else
            current = p;
        return true;
    }
    current = q;
    return false;
    }

    public boolean insert(String key, T val){
    BSTNode<T> p, q = current;

    if (findkey(key)){
        current = q;
        return false;
    }

    p = new BSTNode<T>(key, val);
        if (empty()) {
            root = current = p;
            return true;
        }
        else {

            if ( key.compareTo(current.key) < 0 ) {
            current.left = p;
            }
            else if ( key.compareTo(current.key) > 0 ) {
            current.right = p;
            }
            current = p;
            return true;
        }


    }

    private BSTNode<T> remove_aux(String key, BSTNode<T> p, BooleanWrapper flag) {
    BSTNode<T> q, child = null;
    if(p == null)
        return null;
    if (key.compareTo(p.key) < 0)
        p.left = remove_aux(key, p.left, flag);
        if (key.compareTo(p.key) > 0)
            p.right = remove_aux(key, p.right, flag);
        else {
            flag.set(true);
            if (p.left != null && p.right != null){
                q = find_min(p.right);
                p.key = q.key;
                p.data = q.data;
                p.right = remove_aux(q.key, p.right, flag);
            }
            else {
                if (p.right == null)
                    child = p.left;
                else if (p.left == null)
                    child = p.right;
                return child;
            }
        }
         return p;
    }

    private BSTNode<T> find_min(BSTNode<T> p){
    if (p == null)
            return null;

    while(p.left != null) {
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
