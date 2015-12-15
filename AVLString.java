
/**
 * String AVL class extends AVL tree and provides extra method 
 * 	that allows user to search for prefixes in AVL String objects. 
 * @author Adisa Narula 
 */
public class AVLString extends AVLTree<String>{
	
	/**
	 * Constructor calls super() constructor of parent AVL class. 
	 */
	public AVLString() {
		super();
	}
	
	/**
	 * Contains prefix helper method accessed by the user in order
	 * 	to check whether AVL tree contains a certain prefix. 
	 * @param data prefix to search for in AVL tree. 
	 * @return tree if prefix exists. 
	 */
	public boolean containsPrefix (String data) {
		
		// tree if prefix exists. 
		return recContainsPrefix(super.root, data);
	}
	
	
	/**
	 * Recursive contains prefix method recursively searchers through the tree structure
	 * 	in order to find whether prefix exists in the tree. Uses compareTo method to
	 * 	check whether the method should go left or right to find the data. 
	 * @param node tree that is being searched. 
	 * @param data prefix that is searched for in the tree. 
	 * @return true if prefix is found. 
	 */
	private boolean recContainsPrefix(AVLNode<String> node, String data) {
		
		// empty tree 
		if (node == null) {
			
			return false;
		}
		// return true if data in node starts with the prefix 
		else if (node.getData().startsWith(data)) {
			
			return true;
		}
		
		// keep comparing 
		else if (data.compareTo(node.getData()) < 0) {
			
			// go left 
			return recContainsPrefix(node.getLeft(), data);
		}
		else {
			
			// go right 
			return recContainsPrefix(node.getRight(), data);
		}	
	}
}
