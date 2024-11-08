// Hussein's Binary Tree
// 26 March 2017
// Hussein Suleman

// Sets the root node of the tree to null. This is useful for tests
public class BinaryTree
{
   BinaryTreeNode root;
   
   public BinaryTree ()
   {
      root = null;
   }
   
   /**
   * Returns the number of nodes in the tree. This is the number of nodes that are connected to the root node by a path from the root node to the node that has the largest distance to the root node.
   * 
   * 
   * @return the number of nodes in the tree. A negative value indicates that the tree is empty ; a positive value indicates that the tree has no nodes
   */
   public int getHeight ()
   {
      return getHeight (root);
   }   
   /**
   * Returns the height of the subtree rooted at node. This is the sum of the height of the children of the node's left and right subtrees
   * 
   * @param node - the node to start the search from
   * 
   * @return the height of the subtree rooted at node or - 1 if node is null or has no children
   */
   public int getHeight ( BinaryTreeNode node )
   {
      // Returns the height of the node.
      if (node == null)
         return -1;
      else
         return 1 + Math.max (getHeight (node.getLeft ()), getHeight (node.getRight ()));
   }
   
   /**
   * Returns the number of elements in the tree. This is the same as #getSize ( Node ) but for the root node.
   * 
   * 
   * @return the number of elements in the tree ; negative if the tree is empty or contains no elements ; positive
   */
   public int getSize ()
   {
      return getSize (root);
   }   
   /**
   * Returns the size of the subtree rooted at node. It is used to determine the number of nodes in the tree
   * 
   * @param node - the node to start from
   * 
   * @return the size of the subtree rooted at node or 0 if node is null or not a binary tree
   */
   public int getSize ( BinaryTreeNode node )
   {
      // Returns the number of nodes in the tree.
      if (node == null)
         return 0;
      else
         return 1 + getSize (node.getLeft ()) + getSize (node.getRight ());
   }
   
   /**
   * Prints the data of a binary tree node. This method is called for each node in the parse tree.
   * 
   * @param node - the node to visit. This is an instance of BinaryTreeNode
   */
   public void visit ( BinaryTreeNode node )
   {
      System.out.println (node.data);
   }
   
   /**
   * Performs a pre - order traversal of the tree starting from the root node. This is equivalent to calling #preOrder ( Node )
   */
   public void preOrder ()
   {
      preOrder (root);
   }
   /**
   * Traverses the tree in pre - order. This is used to determine the order of the subtrees that are to be visited.
   * 
   * @param node - the node to start traversing from null if
   */
   public void preOrder ( BinaryTreeNode node )
   {
      // Visit the node and pre order the nodes.
      if (node != null)
      {
         visit (node);
         preOrder (node.getLeft ());
         preOrder (node.getRight ());
      }   
   }

   /**
   * Performs a post order traversal of the tree starting from the root node. This is equivalent to calling #postOrder ( Node )
   */
   public void postOrder ()
   {
      postOrder (root);
   }
   /**
   * Visits a binary tree in post order. This is equivalent to visiting each node in the left subtree followed by visiting each node in the right subtree.
   * 
   * @param node - the node to visit in post order ( may be null
   */
   public void postOrder ( BinaryTreeNode node )
   {
      // Visit the node and post order.
      if (node != null)
      {
         postOrder (node.getLeft ());
         postOrder (node.getRight ());
         visit (node);
      }   
   }

   /**
   * This method traverses the tree in in order. The root is visited first then the subtrees are visited
   */
   public void inOrder ()
   {
      inOrder (root);
   }
   /**
   * Traverses the tree in inorder. This is used to find the root of the tree that is the most common ancestor of all nodes in the tree.
   * 
   * @param node - the node to start traversing from ( may be null
   */
   public void inOrder ( BinaryTreeNode node )
   {
      // Visit the node and visit it.
      if (node != null)
      {
         inOrder (node.getLeft ());
         visit (node);
         inOrder (node.getRight ());
      }   
   }

   /**
   * Level order traversal of the BST. The root is visited first then the children of each node in the tree
   */
   public void levelOrder ()
   {
      // Returns the root node.
      if (root == null)
         return;
      BTQueue q = new BTQueue ();
      q.enQueue (root);
      BinaryTreeNode node;
      // Walks the queue and enqueues all nodes in the queue.
      while ((node = q.getNext ()) != null)
      {
         visit (node);
         // Queue the left node to the queue
         if (node.getLeft () != null)
            q.enQueue (node.getLeft ());
         // Queue the right node to the right of the node.
         if (node.getRight () != null)
            q.enQueue (node.getRight ());
      }
   }
}
