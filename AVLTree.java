import java.util.*;

/**
 * AVLTree class contains tree structure that is used to store words
 * 	found in the dictionary as AVL tree structure. The tree balances itself
 * 	when the balance factor becomes either -2 or 2. This makes sure that 
 * 	remove, add and contains methods performed on the tree has O(logN) efficiency. 
 * @author Adisa Narula
 * @param <T> 
 */

public class AVLTree <T extends Comparable <T>> implements Iterable <T> {
	
	// instantiate attributes for BST class 
	
	protected AVLNode <T> root;
	
	/**
	 * Constructor for AVL tree creates an empty tree object 
	 */
	AVLTree() {
		
		this.root = null;
	}
	
	
	/**
	 * Add method that can be accessed by the user in order 
	 * 	to add elements to AVL tree. Add method calls recursion add so that user 
	 * 	cannot access nodes and do not have to know the underlying structure
	 *  of how data is stored in the tree. 
	 * @param data that is inserted into the tree 
	 */
	public void add(T data) {
		
		// if data not null
		if (data != null) {
			this.root = recAdd(root, data);	
		}		
	}
	
	/**
	 * Recursion add method compares data with code and recursively goes through the tree structure
	 *  until the correct position is found where the new data gets added. The method automatically
	 *  update height of nodes in the tree after every time a new node is added and calls balance factor 
	 *  to check whether tree has to be re balanced. 
	 * @param node tree that new node is added to 
	 * @param data that is inserted to the tree 
	 * @return
	 */
	private AVLNode<T> recAdd(AVLNode<T> node, T data) {
	
		if (node == null) {	
			// where node is being added 
			node = new AVLNode<T> (data);
			updateHeight(node);
			return node;	
		}
		
		// if data is less than node data 
		else if (data.compareTo(node.getData()) <= 0) {
			node.setLeft(recAdd(node.getLeft(), data));
		}
		
		// data greater than node 
		else {
			node.setRight(recAdd(node.getRight(), data));
		}
		
		// update height
		updateHeight(node);
		int factor = balanceFactor(node);
		
		// check balance here 
		if (factor == 2 || factor == -2) {
			
			node = fixTree(node, factor);
		}
		
		//System.out.println("Balance factor: " + factor);
		
		return node;
	}
	
	
	// remove method goes here 
	
	/**
	 * Remove method accessed by user in order to remove specified data from the tree
	 * 	This makes sure that user does not have access to node or have to understand
	 * 	underlying structure of the tree. Remove method calls recursion remove. 
	 * @param data that is removed from the tree. 
	 */
	public void remove(T data) {
		
		// if data is not null
		if (data != null) {
			this.root = recRemove(this.root, data);
	
		}
		// reset root to reference new tree 
	}
	
	/**
	 * Recursively searchers through the tree to find node that needs 
	 * 	to be removed and calls remove method when data is found. 
	 * @param node that data is being removed from. 
	 * @param data that is removed from the tree structure. 
	 * @return The new tree structure after data has been removed. 
	 */
	private AVLNode<T> recRemove(AVLNode<T> node, T data) {
		
		if (node == null) {
			; // do nothing 
		}
		else if (data.compareTo(node.getData()) < 0) {
			// search left subtree
			node.setLeft(recRemove(node.getLeft(), data));
			}
		else if (data.compareTo(node.getData()) > 0) {
			// search right subtree
			node.setRight(recRemove(node.getRight(), data));
			}
		
		else {
			// reassign node 
			node = remove(node);
			
			// update height 
			updateHeight(node);
			
			int factor = balanceFactor(node);
			// check balance here 
			if (factor == 2 || factor == -2) {
				
				node = fixTree(node, factor);
			}
		}
		// return node
		return node;
		
	}
	
	/**
	 * Performs remove operation on tree after node that is going to be removed
	 * 	has been found. Method checks whether node being removed has one child
	 * 	or two children and performs remove method accordingly. Remove method automatically
	 * 	calls update height method after a node has been removed and re balances the tree
	 * 	when necessary. 
	 * @param node that data is being removed from. 
	 * @return node after data has been removed. 
	 */
	private AVLNode<T> remove(AVLNode<T> node) {
		
		// conditions for node with one child 
		if (node.getLeft() == null) {
			return node.getRight();
		}
		
		// no right child return left child 
		else if (node.getRight() == null) {
			return node.getLeft();
		}
		else {
			
			// special case for node with two children 
			// get data stored in node that is going to replace removed node
			T data = getPredecessor(node);
			node.setData(data);
			node.setLeft(recRemove(node.getLeft(), data));
			
			// implement AVL 
			
			
			return node;
		}
	}
	
	/**
	 * Retrieves the node that will replaced removed node. Gets the rightmost 
	 * 	node in the left subtree of the given node. 
	 * @param node that data is being removed from 
	 * @return data of rightmost node in the left subtree. 
	 */
	private T getPredecessor(AVLNode<T> node) {
		
		if (node.getLeft() == null) {
			// there should be a left child 
			throw new NoSuchElementException("Error: there should be a left chlid");
		}
		
		else {
			// keep current node 
			AVLNode<T> current = node.getLeft();
			// while there is still more right children 
			while(current.getRight() != null) {
				current = current.getRight();
			}
			return current.getData();
		}
	}
	
	
	/**
	 * Fix tree method is called when the tree needs to be balanced (when balanced factor
	 * 	is either -2 or 2). Calls specific re-balance method depending on which node 
	 * 	has caused the imbalance. 
	 * @param node tree that needs to be re-balanced.  
	 * @param bf balanced factor that triggered rotation. 
	 * @return tree after re-balancing has been performed 
	 */
	
	private AVLNode<T> fixTree(AVLNode<T> node, int bf) {
		
		// imbalance on the left side 
		
		if (bf == -2) {
			// get left child 
			AVLNode<T> leftChild = node.getLeft();
			
			// left left node caused imbalance 
			if (balanceFactor(leftChild) == -1) {
				return balanceLL(node);
			}
			
			else if (balanceFactor(leftChild) == 1) {
				return balanceLR(node);
			}	
		}
		
		// imbalance on the right side
		else if (bf == 2) {
			
			//get right child 
			AVLNode<T> rightChild = node.getRight();
			
			// imbalance left subtree
			if (balanceFactor(rightChild) == -1) {
				return balanceRL(node);
			}
			
			else if(balanceFactor(rightChild) == 1) {
				return balanceRR(node);
			}
		}
		return node;	
	}
	
	
	/**
	 * Balance method for left-left imbalance. Automatically 
	 * update height of each node that is rotated. 
	 * @param A tree that needs to be re-balanced. 
	 * @return re-balanced tree
	 */
	private AVLNode<T> balanceLL (AVLNode<T> A) {
		
		// store left child of node 
		AVLNode<T> B = A.getLeft();
		// reset left child to right subtree of B 
		A.setLeft(B.getRight());
		// set right subtree of B to be the original node 
		B.setRight(A);
		
		// update height 
		updateHeight(A);
		updateHeight(B);
		
		return B;	
	}
	
	/**
	 * Balance method for left right imbalance when balance factor
	 * 	is -2 and 1. Updates height automatically for nodes that 
	 * 	undergoes a rotation. 
	 * @param A tree that needs to be re-balanced. 
	 * @return re-balanced tree 
	 */
	private AVLNode<T> balanceLR (AVLNode<T> A) {
		
		// store left subtree of A
		AVLNode<T> B = A.getLeft();
		// store right subtree of B 
		AVLNode<T> C = B.getRight();
		
		// reset left child of node A 
		A.setLeft(C.getRight());
		// reset right child of node B 
		B.setRight(C.getLeft());
		
		// set left and right for C to get the final re-balanced tree 
		C.setLeft(B);
		C.setRight(A);
		
		// update height 
		updateHeight(A);
		updateHeight(B);
		updateHeight(C);
		
		// balanced tree
		return C;	
	}
	
	/**
	 * Balance method for right-left imbalance. Automatically 
	 * update height of each node that is rotated. 
	 * @param A tree that needs to be re-balanced. 
	 * @return re-balanced tree
	 */
	private AVLNode<T> balanceRL (AVLNode<T> A) {
		
		// store right subtree of A 
		AVLNode<T> B = A.getRight();
		// store left subtree of B 
		AVLNode<T> C = B.getLeft();
		
		// reset right subtree of A 
		A.setRight(C.getLeft());
		// reset left subtree of B 
		B.setLeft(C.getRight());
		C.setRight(B);
		C.setLeft(A);
		
		// update height 
		updateHeight(A);
		updateHeight(B);
		updateHeight(C);
		
		return C;	
	}
	
	/**
	 * Balance method for right right imbalance. Automatically 
	 * update height of each node that is rotated. 
	 * @param A tree that needs to be re-balanced. 
	 * @return re-balanced tree
	 */
	private AVLNode<T> balanceRR (AVLNode<T> A) {
		
		// store right subtree of A 
		AVLNode<T> B = A.getRight();
		// reset right subtree of A to left subtree of B 
		A.setRight(B.getLeft());
		// reset left subtree of B
		B.setLeft(A);
		
		// update height 
		updateHeight(A);
		updateHeight(B);
		
		// re-balanced tree 
		return B;	
	}
	
	
	/**
	 * Contains method accessed by user in order to check whether 
	 * 	a piece of data exists in the tree structure. Calls recursive 
	 * 	contains method. 
	 * @param data that is searched for in the tree. 
	 * @return true if data is found in the tree. 
	 */
	public boolean contains(T data) {
		
		// tree if found 
		return recContains(root, data);
	}
	
	/**
	 * Recursive contains method recursively searchers through the tree structure
	 * 	in order to find whether data exists in the tree. Uses compareTo method to
	 * 	check whether the method should go left or right to find the data. 
	 * @param node tree that is being searched. 
	 * @param data that is searched for in the tree. 
	 * @return true if data is found. 
	 */
	private boolean recContains (AVLNode<T> node, T data) {
		
		// if tree is empty
		if (node == null) {
			
			return false;
		}
		// if data is less than node data go left
		else if (data.compareTo(node.getData()) < 0) {
			
			return recContains(node.getLeft(), data);
		}
		// greater than node data; go right 
		else if (data.compareTo(node.getData()) > 0) {
			
			return recContains(node.getRight(), data);
		}
		else {
			// true if found 
			return true;
		}
	}

	/**
	 * Calculates balance factor of node to determine whether
	 * 	the tree needs to be re-balanced. If balance factor returns -2 or
	 * 	2, the tree automatically triggers re-balancing. 
	 * @param node that is being searched. 
	 * @return -2 or 2 if re-balancing is necessary. 
	 */
	private int balanceFactor(AVLNode<T> node) {
		

		// no right element return negative height 
		
		if (node.getRight() == null) {
			
			return - (node.getHeight());
		}
		
		// no left element return height 
		
		else if (node.getLeft() == null) {
			
			return node.getHeight();
		}
		
		// store left and right height as variables
		
		int rightHeight = node.getRight().getHeight();
		int leftHeight = node.getLeft().getHeight();
		
		// return balance factor 
		return rightHeight - leftHeight;
		
	}

	/**
	 * Resets height value for each node when a node
	 * 	is either added or removed from the tree. This allows
	 * 	balance factor method to determine whether a rotation needs 
	 * 	to occur in order to keep the tree balanced. 
	 * @param node tree that has had a node either removed or added 
	 */
	private void updateHeight(AVLNode<T> node) {
		
		// if node is a leaf 
		if (node.getLeft() == null && node.getRight() == null) {
			node.setHeight(0);
		}
		
		// no left child 
		else if (node.getLeft() == null) {
			
			// set height 
			node.setHeight(node.getRight().getHeight() + 1);
		}
		
		else if (node.getRight() == null) {
			
			// set height 
			node.setHeight(node.getLeft().getHeight() + 1);
		}
		
		else {
			
			// find maximum between height of subtrees 
			int maximum = max(node.getRight().getHeight(), node.getLeft().getHeight()); 
			node.setHeight(maximum + 1);	
		}	
	}
	
	
	/**
	 * Determines the maximum height between left and right subtree 
	 * @param right height of right subtree
	 * @param left height of left subtree 
	 * @return maximum integer 
	 */
	private int max(int right, int left) {
		
		// if right is greater return right 
		if (right >= left) {
			
			return right;
		}
		else {
			return left;
		}
	}
	
	/**
	 * Converts the tree into string post order traversal of the tree. 
	 * 	Calls post traversal print that prints the data in post 
	 * 	traversal manner.  
	 * 	Credits: Joanna Klukowska 
	 * @return string representation of the tree. 
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		//inOrderPrint(root, s);
		postOrderPrint(root, 0, s);
		return s.toString();
	}
	
	/**
	 * Prints the data in the tree in a post traversal order. 
	 * 	Shows the structure of the root and its subtree. 
	 * 	Credits: Joanna Klukowska 
	 * @param tree that is being printed. 
	 * @param level (depth) of the current recursive call in the tree
	 * @param output string that concatenates data from the tree.   
	 */
	private void postOrderPrint(AVLNode<T> tree, int level, StringBuilder output )
	
	{
		// if tree is not null 
		if (tree != null) {
			String spaces = "\n";
			if (level > 0) {
				for (int i = 0; i < level - 1; i++)
					spaces += "   ";
				spaces += "|--";
			}
			// append spaces and data to the tree 
			output.append(spaces);
			output.append("Data: " + tree.getData());
			postOrderPrint(tree.getLeft(), level + 1, output);
			postOrderPrint(tree.getRight(), level + 1, output);
		}
	}
	
	/**
	 * Iterator method allows user to traverse and print out
	 * 	elements in the AVL tree without having to know 
	 * 	its underlying structure. 
	 * @return iterator<T> object  
	 */
	@Override
	public Iterator<T> iterator() {
		
		// return new custom tree iterator object 
		return (new TreeIterator());
	}
		
	/**
	 * Private iterator class that implements methods hasNext() and next()
	 * 	that is required in order for user to traverse through the AVl tree
	 * 	in an in order traversal manner. The inner iterator class allows it to access
	 * 	the tree and maintain object oriented principles. 
	 * @author Adisa Narula 
	 */
	private class TreeIterator implements Iterator<T> {
		
		// instantiate data feel or iterator 
		private AVLNode<T> tree;
		private ArrayList<T> inOrderArray;
		private T dataReturned;
		
		// keep at counter 
		int counter;
		int lastElement;
		
		/**
		 * Constructor sets tree to root of the AVL tree. 
		 * 	It also calls inOrder method to add data to the array list
		 * 	in an in order traversal way. 
		 */
		public TreeIterator() {
			
			this.tree = root;
			inOrderArray = new ArrayList<T>();
			
			// add elements to array 
			
			inOrder(tree);
			
			// set counter to zero 
			counter = 0;
			lastElement = inOrderArray.size();		

		}
		
		/**
		 * Allows user to check whether the tree contains another element.
		 * 	The method must be called before next method is called.  
		 * @return true if there is another element in the tree. 
		 */
		@Override
		public boolean hasNext() {
			
			// check size of array to see whether there are any more elements 
			return (counter != lastElement);
		}
		
		/**
		 * The next method accesses and returns the next data found in the tree.
		 * 	The method must be called after the hasNext() method every time
		 * 	it is used. 
		 * @return the next data in the tree. 
		 */
		@Override
		public T next() {
			
			// if no more element throw exception 
			if (!hasNext()) {
				throw new NoSuchElementException("No more elements");
			}
			
			// keep data 
			dataReturned = inOrderArray.get(counter);
			counter++;
			// return the next data 
			return dataReturned;
		}
		
		/**
		 * Optional remove method not implemented for AVL iterator. 
		 *  @throws UnsupportedOperationException remove method is not supported in tree iterator. 
		 */
		@Override
		public void remove() {
			
			throw new UnsupportedOperationException("remove() is not supported.");
		}
		
		// in order traversal of data in BST 
		
		/**
		 * Recursively performs in order traversal of data in the AVL tree. 
		 * 	Adds data to array list in order.  
		 * @param node tree that the method traverse through. 
		 */
		private void inOrder(AVLNode<T> node) {
						
			// if the node is not empty 
			if (node != null) {
				
				// go all the way to the left
				inOrder(node.getLeft());
				// add to array 
				inOrderArray.add(node.getData());
				// go right 
				inOrder(node.getRight());

			}	
		}
	}
}

