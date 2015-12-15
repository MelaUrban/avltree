

/**
 * Searches for words in the dictionary through recursive implementation of binary search 
 * 	loads the dictionary that user enters in the command line and into binary search tree
 *	checks whether a word exists in the dictionary 
 * @author Adisa Narula
 * @version 1.0
 *
 */

public class Dictionary {
	
	private AVLString dictionaryWordTree; 
	

	/**
	 * Constructor creates a new string array and stores the list of 
	 * 	words from the dictionary file into AVL tree structure 
	 * @param dictionary string array of words in the dictionary file 
	 */
	public Dictionary(String[] dictionary) {
	
		dictionaryWordTree = new AVLString();
				
		// add all the words to binary search tree 
		// in balance tree 
	    
//		for(int i = 0; i <dictionary.length; i++){
//			
//			dictionaryWordTree.add(dictionary[i]);
//		}
		
		insertBalanceWords(dictionary, 0, dictionary.length-1);

		//System.out.println(dictionaryWordTree.toString());
	}
	
	/**
	 * Inserts the words in the dictionary into tree structure and makes sure that
	 * 	the tree is not stored as a linked list because the words in the dictionary
	 * 	are passed in in alphabetical order. 
	 * @param dictionary array list containing words found the in dictionary 
	 * @param first position of the first word in the dictionary 
	 * @param last position of the last word in the dictionary 
	 */
	private void insertBalanceWords(String[] dictionary, int first, int last) {
		
		// set middle element to one
		
		// method is similar to binary search method 
		int mid = 0;
		// one element to add 
		if (first == last) {
			
			dictionaryWordTree.add(dictionary[first]);
		}
		
		// two elements 
		else if (first + 1 == last) {
			dictionaryWordTree.add(dictionary[first]);
			dictionaryWordTree.add(dictionary[last]);
		}
		
		else {
			
			// keep updating mid and recursively add data to the tree 
			mid = (first + last) / 2;
			dictionaryWordTree.add(dictionary[mid]);
			insertBalanceWords(dictionary, first, mid - 1);
			insertBalanceWords(dictionary, mid + 1 , last);
		}
	}
	
	/**
	 * Searches the AVL string tree structure that contains the dictionary
	 * 	to see whether the tree contains specific prefix that is passed in to the method. 
	 * @param wordToSearch prefix that is searched for in the dictionary. 
	 * @return true if dictionary contains the prefix.  
	 */
	public boolean prefixDictionary(String wordToSearch) {
		
		// true if word is in the dictionary 
		return(dictionaryWordTree.containsPrefix(wordToSearch));
		
	}
	
	/**
	 * Searches the AVL string tree structure that contains the dictionary
	 * 	to see whether the tree contains specific word that is passed in to the method. 
	 * @param wordToSearch word that is searched for in the dictionary. 
	 * @return true if dictionary contains the word.  
	 */
	public boolean checkEquals(String wordToSearch) {
		
		// true if word is in the dictionary 
		return (dictionaryWordTree.contains(wordToSearch));
	
	}
	
}
