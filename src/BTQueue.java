// Hussein's Binary Tree
// 26 March 2017
// Hussein Suleman

public class BTQueue
{   
   BTQueueNode head;
   BTQueueNode tail;
      
   public BTQueue ()
   {
      head = null;
      tail = null;
   }
   
   /**
   * Gets the next node in the queue. This is an O ( 1 ) operation. If there are no more nodes in the queue null is returned.
   * 
   * 
   * @return the next node in the queue or null if there are no more nodes in the queue ( in which case the queue is empty
   */
   public BinaryTreeNode getNext ()
   {
      // Returns the head of the head.
      if (head == null)
         return null;
      BTQueueNode qnode = head;
      head = head.next;
      // Resets the head and tail of the list.
      if ( head == null )
         tail = null;
      return qnode.node;
   }
   
   /**
   * Adds a node to the end of the queue. This is used to create a linked list of nodes that are added to the queue.
   * 
   * @param node - the node to be added to the queue. It must not be null
   */
   public void enQueue ( BinaryTreeNode node )
   {
      // Add a new node to the tail of the queue.
      if (tail == null)
      {   
         tail = new BTQueueNode (node, null);
         head = tail;
      }   
      else
      {
         tail.next = new BTQueueNode (node, null);
         tail = tail.next;
      }   
   }   
}
