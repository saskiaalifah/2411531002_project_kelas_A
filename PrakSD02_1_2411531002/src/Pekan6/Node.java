package Pekan6;

//Node pada Double Linked List
public class Node {
 ItemBelanja data;
 Node prev;
 Node next;

 public Node(ItemBelanja data) {
     this.data = data;
     this.prev = null;
     this.next = null;
 }
}

