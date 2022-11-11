package Node;

public class DLNode<T>{
   private T data;
   private DLNode prev;
   private DLNode next;

   public DLNode(T data) {
      this(data,null,null);
   }

   public DLNode(T data, DLNode prev, DLNode next) {
      this.data = data;
      this.prev = prev;
      this.next = next;
   }
}
