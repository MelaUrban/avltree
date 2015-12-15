
/**
 * AVLNode class creates the nodes used to store data in AVL tree. 
 * 	Contains data for left, right, data and height of each node. 
 * 	Provides all the necessary getters and setters methods for AVL node. 
 * 	Extends comparable to make sure that generic type used in AVL node object
 * 	implements the compare to method. 
 * @author Adisa Narula
 * @param <T>
 */
public class AVLNode <T extends Comparable <T>> implements Comparable <AVLNode <T>>  {

	// initiate node data field 
	
	private T data; 
	private AVLNode <T> left;
	private AVLNode <T> right;
	int height;
	
	// constructors 
	
	/**
	 * Constructor creates new AVLNode and sets data and height of node. 
	 * @param data that is stored in the node.
	 */
	public AVLNode(T data) {
		
		// set data field for node object 
		this.data = data;
		this.left = null;
		this.right = null;
		this.height = 0;
	}
	
	/**
	 * Overloaded constructor sets data, left child, right child and height of the node. 
	 * @param data that is stored in the node. 
	 * @param left left subtree of the node. 
	 * @param right right subtree of the node. 
	 */
	public AVLNode(T data, AVLNode <T> left, AVLNode <T> right) {
		
		// set data field for node object 
		this.data = data;
		this.left = left;
		this.right = right;
		this.height = 0;
	}
	
	/**
	 * Setter method for data in the node. Allows user to 
	 * 	change data stored in the node. 
	 * @param data that is store in the node object. 
	 */
	public void setData(T data) {
		
		this.data = data;
	}
	
	/**
	 * Setter method to set left subtree of node. 
	 * @param left subtree of the node. 
	 */
	public void setLeft (AVLNode <T> left) {
		
		this.left = left;
	}
	
	/**
	 * Setter method for right subtree of the node. 
	 * @param right subtree of the node. 
	 */
	public void setRight (AVLNode <T> right) {
		
		this.right = right;
	}
	
	/**
	 * Getter method for user to be able to access
	 * 	data stored in the node. 
	 * @return data store in the node. 
	 */
	public T getData () {
		
		return this.data;
	}
	
	/**
	 * Setter method for the height field of the node. 
	 * @param height of node. 
	 */
	public void setHeight(int height) {
		
		this.height = height;
	}
	
	/**
	 * Getter method for user to be able to access height of node. 
	 * @return integer representing height of node. 
	 */
	public int getHeight() {
		
		return this.height;
	}
	
	/**
	 * Getter method for user to be able to access left subtree of the node.
	 * @return left subtree of the node. 
	 */
	public AVLNode <T> getLeft() {
		
		return this.left;
	}
	
	/**
	 * Getter method for user to be able to access right subtree of the node.
	 * @return right subtree of the node. 
	 */
	public AVLNode <T> getRight() {
		
		return this.right;
	}

	/**
	 * Compares this object with the specified object for order. 
	 * 	Returns a negative integer, zero, or a positive integer as this object
	 * 	is less than, equal to, or greater than the specified object (Javadoc). 
	 * @return integer that represents whether data is less than (-1), greater than (1) or equal to (0) 
	 */
	@Override
	public int compareTo(AVLNode<T> other) {
		
		return this.data.compareTo(other.data);
		
	}

	

}
