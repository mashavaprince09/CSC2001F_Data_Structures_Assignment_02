// Hussein's Binary Tree
// 26 March 2017
// Hussein Suleman
// modified by Prince Mashava

public class BinaryTreeNode
{
   Kb data;
   BinaryTreeNode left;
   BinaryTreeNode right;
   int height;

   /**
   * Creates a binary tree node with a KB object and a left and right child node
   * 
   * 
   * @param d a KB object that's going to be linked to this node 
   * 
   * @param l the node's left child node
   * 
   * @param r the node's right child node
   *    
   */   
   public BinaryTreeNode ( Kb d, BinaryTreeNode l, BinaryTreeNode r )
   {
      data = d;
      left = l;
      right = r;
      height = 0;
   }
   
   /**
   * Returns the left child of this node. This node is not part of the tree and can be used to access other nodes.
   * 
   * 
   * @return the left child of this node or null if this node is the leaf of the tree
   */
   public BinaryTreeNode getLeft () { return left; }
   /**
   * Returns the right child of this node. This node is not changed by this method. The value returned by this method is null if this node has no right child.
   * 
   * 
   * @return the right child of this node or null if this node is the leaf of the tree
   */
   public BinaryTreeNode getRight () { return right; }
   /**
   * Returns Kb object of this node
   * 
   * 
   * @return Kb object 
   */
   public Kb getData(){return data;}
   /**
   * Returns the height of the component. This value is computed by examining the properties of the component and if they are non - null then the height of the component is returned.
   * 
   * 
   * @return the height of the component or - 1 if it is not possible to determine the height of the component
   */
   public int getHeight(){return height;}
   /**
   * Sets the data from a Kb object. This is useful for testing and to avoid having to re - use the data from an already existing knowledge base
   * 
   * @param kb - the knowledge base object
   */
   public void setData(Kb kb){
      data.setTerm(kb.getTerm());
      data.setSentence(kb.getSentence());      
      data.setConfidence(kb.getConfidence());      
   }  
}

