// Hussein's Binary Tree
// 26 March 2017
// Hussein Suleman

// Sets the right - hand - side of the BTQueueNode. This is used to determine the order of the nodes
public class BTQueueNode
{
   BinaryTreeNode node;
   BTQueueNode next;
   
   public BTQueueNode ( BinaryTreeNode n, BTQueueNode nxt )
   {
      node = n;
      next = nxt;
   }
}
