import java.util.*;


/**
 * SetLetters class creates different combinations of letters and checks whether the word exists in the dictionary
 * Stores the word found in the dictionary in the wordFound ArrayList 
 * @author Adisa Narula
 * @version 1.0
 *
 */
public class SetLetters {
	
	// Instance of dictionary class to access the content of dictionary file
	
	private Dictionary d; 
	
	// initialize array to store words found
	ArrayList<String> wordFound = new ArrayList<String>(); 

		
	/**
	 * Creates a new instance of dictionary object and creates different words that are searched for in the dictionary 
	 * This constructor calls the createLettersLoop method 
	 * @param letters string array of letters 
	 * @param dictionaryList string array of words in the dictionary 
	 */
	public SetLetters(String[] letters, String[] dictionaryList) {

		d = new Dictionary(dictionaryList);
		createLettersLoop(letters);

	}
		
	/**
	 * Makes different combinations of word from array of letters and checks whether the word exists in the dictionary 
	 * This method calls the method in the helperDictionary and checkEquals method in the dictionary class   
	 * @param word that is checked for in the dictionary 
	 * @param letters array of letters 
	 */
	public void createLetters(String word, String[] letters) {
		
		// initialize string for new word 
		String[] tempArray = new String[letters.length];
		
		// loop through letters array 
		for (int i = 0; i < letters.length; i++) { 
			
			// make a copy of letters array 
			
			for (int j = 0; j < letters.length; j++) {
				tempArray[j] = letters[j];
			}
			
			// if the letter in array has already been used, use null as flag 
			
			if (tempArray[i] == null) {
				continue;
			}
			
			// otherwise concatenate to word and flag the letter that was used 
			String newWord = word + tempArray[i];
			tempArray[i] = null;
			
			
			// check whether prefix of the word is found in dictionary if true call equals function  
			if (d.prefixDictionary(newWord)) {
								
				if (d.checkEquals(newWord)) {
					
					wordFound.add(newWord); 
				
				}
				
				createLetters(newWord, tempArray);
	
			} 		
		} 
	} 
	
	// calls createLetter method with different letters as parameter 
	
	/**
	 * Iterates through every letter in the array and creates different combinations of words starting with that letter  
	 * This method calls createLetters method 
	 * @param lettersInput string array of letters 
	 */
	
	private void createLettersLoop(String[] lettersInput) {
		
		// make copy of the array 
			
		for (int j = 0; j < lettersInput.length; j++) {
			
			String[] temp = new String[lettersInput.length];			
			for (int i = 0; i < temp.length; i++) {
				temp[i] = lettersInput[i];
			}
			
			temp[j] = null;
			createLetters(lettersInput[j], temp);
			
		}	
	}
	
	/**
	 * Removes duplicates and sorts the arrays of words found in the dictionary 
	 * @return words found that are unique and sorted
	 */
	public Collection <String> getArray() {
		
		// sort the array  
		Collections.sort(wordFound);
		
		// remove duplicates
		for (int i = wordFound.size()-1; i > 0; i--) {
			
			if (wordFound.get(i).equals(wordFound.get(i-1))) {
				wordFound.remove(i);
			}
		}
		return wordFound;
	}
}
