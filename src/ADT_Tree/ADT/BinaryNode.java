package ADT_Tree.ADT;

public class BinaryNode <T> {

    private T data;
    private BinaryNode<T> left;
    private BinaryNode<T> right;

    public BinaryNode(){
        this(null);
    }

    public BinaryNode(T data){
        this(data,null,null);
    }

    public BinaryNode(T data,BinaryNode<T> left,BinaryNode<T> right){
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public T getData() {

        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BinaryNode<T> getLeft() {
        return left;
    }

    public void setLeft(BinaryNode<T> left) {
        this.left = left;
    }

    public BinaryNode<T> getRight() {
        return right;
    }

    public void setRight(BinaryNode<T> right) {
        this.right = right;
    }

    public boolean hasLeftChild(){
        return left != null;
    }

    public boolean hasRightChild(){
        return right != null;
    }

    public boolean isLeaf(){
        return left == null && right == null;
    }

    public int getNumberOfNodes(){
       int leftNumber = 0;
       int rightNumber = 0;

      if(left != null)
          leftNumber = left.getNumberOfNodes();
      if(right != null)
          rightNumber = right.getNumberOfNodes();

      return 1 + leftNumber + rightNumber;
    }

    public int getHeight(){
       return getHeight(this);
    }

    private int getHeight(BinaryNode<T> node){
        int height = 0;
        if(node != null)
            height = 1 + Math.max(getHeight(node.getLeft()),getHeight(node.getRight()));

        return height;
    }

    public BinaryNode<T> copy(){
        BinaryNode<T> newRoot = new BinaryNode<>(data);
        if (left != null)
            newRoot.setLeft(left.copy());
        if (right != null)
            newRoot.setRight(right.copy());

        return newRoot;
    }
}

