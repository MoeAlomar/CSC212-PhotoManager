public class BSTNode<T> {
    public String key;
    public T data;
    public BSTNode<T> left;
    public BSTNode<T> right;

    public BSTNode(String key, T data){
        this.key = key;
        this.data = data;
        left = right = null;
    }

    public BSTNode(String k, T val, BSTNode<T> l, BSTNode<T> r){
        key = k;
        data = val;
        left = l;
        right = r;
    }
}
