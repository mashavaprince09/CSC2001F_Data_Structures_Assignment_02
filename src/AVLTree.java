// Hussein's AVL Tree
// 2 April 2017
// Hussein Suleman
// reference: kukuruku.co/post/avl-trees/
// modified by Prince Mashava

public class AVLTree extends BinaryTree
{
   /*
   * an instance variable used to count searches   
   */   
   public int search;
   /*
   * an instance variable used to count insertions   
   */   
   public int insert;
   
   /**
   * Returns the height of the node. If the node is null - 1 is returned. This method is used to implement Tree.
   * 
   * @param node - the node to get the height of. Null is allowed.
   * 
   * @return the height of the node or - 1 if not found in the tree ( in which case the height is undefined
   */
   public int height ( BinaryTreeNode node )
   {
      // The height of the node.
      if (node != null)
         return node.height;
      return -1;
   }
   
   /**
   * Returns the balance factor of the subtree rooted at node. Balancing factors are defined as the difference between the heights of the two subtrees.
   * 
   * @param node - the node to check. Must not be null.
   * 
   * @return the balance factor of the subtree rooted at node or 0 if there is no balance factor in the
   */
   public int balanceFactor ( BinaryTreeNode node )
   {
      return height (node.right) - height (node.left);
   }
   
   /**
   * Fixes the height of a node. This involves adjusting the height of the left and right subtrees to account for the fact that they are in the same level.
   * 
   * @param node - the node to fix the height of. Must not be null
   */
   public void fixHeight ( BinaryTreeNode node )
   {
      node.height = Math.max (height (node.left), height (node.right)) + 1;
   }
   
   /**
   * Rotates the subtree rooted at p right. This method does not check for duplicates. If there is a collision the method returns null.
   * 
   * @param p - the node to be rotated. This is assumed to be a leaf node.
   * 
   * @return the node that was rotated to the right of p. This is returned to allow chaining of calls to this method
   */
   public BinaryTreeNode rotateRight ( BinaryTreeNode p )
   {
      BinaryTreeNode q = p.left;
      p.left = q.right;
      q.right = p;
      fixHeight (p);
      fixHeight (q);
      return q;
   }

   /**
   * Rotates the subtree rooted at q to the left. This is equivalent to rotating the right child of q ( and its children ) to the left child of q.
   * 
   * @param q - the subtree to be rotated. This must be a leaf node of this BST.
   * 
   * @return the root of the rotated subtree rooted at q or null if there is no such root in the
   */
   public BinaryTreeNode rotateLeft ( BinaryTreeNode q )
   {
      BinaryTreeNode p = q.right;
      q.right = p.left;
      p.left = q;
      fixHeight (q);
      fixHeight (p);
      return p;
   }
   
   /**
   * Balance the subtree rooted at p. This is the core of balancing algorithm. If p is a leaf the balanced subtree is returned.
   * 
   * @param p - the root of the subtree to be balanced
   * 
   * @return the root of the balanced subtree or p if it is not a leaf ( in which case the caller should check the return value
   */
   public BinaryTreeNode balance ( BinaryTreeNode p )
   {
      fixHeight (p);
      // Rotate the point p by the balance factor 2.
      if (balanceFactor (p) == 2)
      {
         // If balanceFactor is negative rotate the right of the p.
         if (balanceFactor (p.right) < 0)
            p.right = rotateRight (p.right);
         return rotateLeft (p);
      }
      // Rotate the point p by the balance factor.
      if (balanceFactor (p) == -2)
      {
         // Rotate the left of the p.
         if (balanceFactor (p.left) > 0)
            p.left = rotateLeft (p.left);
         return rotateRight (p);
      }
      return p;
   }

   /**
   * Inserts a Kb into the tree. This is equivalent to calling insert ( d root ). Note that you have to make sure that d is not null before calling this method.
   * 
   * @param d - the Kb to be inserted into the tree
   */
   public void insert ( Kb d )
   {
      root = insert (d, root);
   }
   /**
   * Inserts a Kb into the tree. This method is used for balancing the tree to find the root of the tree that corresponds to the given data.
   * 
   * @param d - The data to insert. This value must be greater than or equal to the data stored in the node's data field.
   * @param node - The node in which to insert the data.
   * 
   * @return The new root of the tree after insertion has been balanced or null if the data was already in the
   */
   public BinaryTreeNode insert ( Kb d, BinaryTreeNode node )
   {
      // Returns a new binary tree node.
      if (node == null){
        //insert counter
         //insert++;
         return new BinaryTreeNode (d, null, null); }
      // inserts the node at the given position
      if (d.compareTo (node.data) <= 0){
	 //insert counter
	 insert++;
         node.left = insert (d, node.left);
      }
      else {
	 //insert counter
	 insert++;
         node.right = insert (d, node.right);
      }
      return balance (node);
   }
   
   /**
   * Delete a Kb from the tree. This is equivalent to calling delete ( d root ) but does not change the tree in place.
   * 
   * @param d - the Kb to delete from the tree ; may not be
   */
   public void delete ( Kb d )
   {
      root = delete (d, root);
   }   
   /**
   * Deletes the node with the given data from the tree. This method is O ( log n ) in the number of nodes
   * 
   * @param d - the data to be deleted
   * @param node - the node where the data is to be deleted from
   * 
   * @return the node where the data was deleted from or null if it wasn't in the tree before deletion
   */
   public BinaryTreeNode delete ( Kb d, BinaryTreeNode node )
   {
      // Returns the node or null if the node is null.
      if (node == null) return null;
      // Remove the lowest node from the tree.
      if (d.compareTo (node.data) < 0)
         node.left = delete (d, node.left);
      // Remove the node from the tree.
      else if (d.compareTo (node.data) > 0)
         node.right = delete (d, node.right);
      else
      {
         BinaryTreeNode q = node.left;
         BinaryTreeNode r = node.right;
         // Returns the current query.
         if (r == null)
            return q;
         BinaryTreeNode min = findMin (r);
         min.right = removeMin (r);
         min.left = q;
         return balance (min);
      }
      return balance (node);
   }
   
   /**
   * Finds the minimum value in a subtree. Used for binary search. This is the same as find ( node ) except that it does not traverse the tree to find the minimum value.
   * 
   * @param node - the node to start the search from. Must not be null.
   * 
   * @return the minimum value in the subtree rooted at node or null if no such value exists in the tree
   */
   public BinaryTreeNode findMin ( BinaryTreeNode node )
   {
      // Find the minimum node in the tree.
      if (node.left != null)
         return findMin (node.left);
      else
         return node;
   }

   /**
   * Removes the minimum value from the subtree rooted at node. This is done by balancing the tree to the left and recursivly removing the node with the smallest value
   * 
   * @param node - the node to start from
   * 
   * @return the node with the smallest value in the subtree rooted at node ; if there is no such node the return value is
   */
   public BinaryTreeNode removeMin ( BinaryTreeNode node )
   {
      // Returns the left node.
      if (node.left == null)
         return node.right;
      node.left = removeMin (node.left);
      return balance (node);
   }

   /**
   * Find the node that contains the Kb. If there is no such node return null. This is a non - recursive version of #find ( Kb )
   * 
   * @param d - the Kb to look for
   * 
   * @return the BinaryTreeNode that contains the Kb or null if not found in the tree or if the tree is
   */
   public BinaryTreeNode find ( Kb d )
   {
      // Find the root node in the tree.
      if (root == null)
         return null;
      else
         return find (d, root);
   }
   /**
   * Finds the node that contains the key. This is a recursive method. It traverses the tree until it finds the node that contains the key.
   * 
   * @param d - Key to look for. This is the key we are looking for.
   * @param node - Node where we are looking for the key.
   * 
   * @return BinaryTreeNode The node that contains the key or null if not found. Note that the root node is the same as the node passed in
   */
   public BinaryTreeNode find ( Kb d, BinaryTreeNode node )
   {
      // find the node that is less than or equal to the data
      if (d.compareTo (node.data) == 0) 
         return node;
      // Find the node in the tree.
      else if (d.compareTo (node.data) < 0)
         return (node.left == null) ? null : find (d, node.left);
      else
         return (node.right == null) ? null : find (d, node.right);
   }
   
   /**
   * Order the nodes in the tree according to order. This is useful for debugging and to ensure that the tree is in ascending order
   */
   public void treeOrder ()
   {
      treeOrder (root, 0);
   }
   /**
   * Prints the order of the tree. Used for debugging purposes. This is a recursive method to print the data in a binary tree
   * 
   * @param node - node that is the root of the tree
   * @param level - level of the node in the tree ( starting at 0
   */
   public void treeOrder ( BinaryTreeNode node, int level )
   {
      // This method is called by the tree.
      if (node != null)
      {
         // Prints out the level levels.
         for ( int i=0; i<level; i++ )
            System.out.print (" ");
         System.out.println (node.data);
         treeOrder (node.left, level+1);
         treeOrder (node.right, level+1);
      }
   }

   /**
   * Updates the data associated with a key. This is called when one of the keys changes in the tree
   * 
   * @param kb - the key that has changed
   * @param btn - the node to update with the new data ( must be a child
   */
   public void update(Kb kb, BinaryTreeNode btn){
      btn.setData(kb);
   }    
   
   /**
   * Finds and returns the BinaryTreeNode that contains the term. If no term is found null is returned.
   * 
   * @param term - The term to search for. Must be non - null.
   * 
   * @return The BinaryTreeNode that contains the term or null if no term is found. If no term is found
   */
   public BinaryTreeNode findTerm(String term){
      // Returns the term or null if no term is found.
      // Returns the term or null if no term is found.
      if(root == null){
        return null;
      }
      else {
         return findTerm(term,root);
      }
   }
   /**
   * Finds and returns the node that contains the term. This method is recursive in that it traverses the tree until it finds the node that contains the term.
   * 
   * @param term - The term to search for. Must be non - null.
   * @param node - The node to start the search from. Must not be null.
   * 
   * @return The node that contains the term or null if not found. Note that the return value is a BinaryTreeNode
   */
   public BinaryTreeNode findTerm ( String term, BinaryTreeNode node )
   {
      // find the node with the term
      if (term.compareTo (node.getData().getTerm()) == 0) {
	 //search counter
	 search++;
         return node;}
      //insert counter
      // Returns the term of the node.
      else if (term.compareTo (node.getData().getTerm())  < 0){
	 //search counter
	 search++;
         return (node.getLeft() == null) ? null : findTerm (term, node.getLeft());}
      else{
	 //search counter
	 search++;
         return (node.getRight() == null) ? null : findTerm (term, node.getRight());}
   }   
}

