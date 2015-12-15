
import java.io.*;

import java.util.*;

/**
 * @author Adisa Narula
 * collaborated with Adithep Narula and Avika Narula
 * @version 1.0
 * The program produces all possible words given a set of letters and a dictionary and prints them to the console
 */

public class FindWord {

	/**
	 * Main method takes file name and user input, validates them and exits the program if an error is found 
	 * This method is where the setLetters object is instantiated which takes user input and dictionary as argument  
	 * @param args The name of the dictionary text file given in the command line 
	 * @throws IOException Throws an exception when file in the command line does not exist 
	 */
	
public static void main(String[] args) throws IOException{
	
			
		// exits the program if file name not included in command line

		if (args.length <= 0) {
			System.err.println("File name not included in the command line");
			System.exit(0);
		}
		
		// store dictionary content in string array 
		String[] dictionaryWord = readFile(args[0]);
		
		// store user input in string array 
		String[] letters = validateInput();
		
		// create CreateLetters object to load letters and dictionary into the class
		SetLetters words = new SetLetters(letters, dictionaryWord);
		
		// Collection of words found in dictionary
		
		Collection <String> wordsFound = words.getArray();

		Iterator<String> iterator = wordsFound.iterator();
		
		// Print array list 
		
		System.out.println();
		
		System.out.println("Words found in the dictionary");
		System.out.println("-----------------------------");
		
		while(iterator.hasNext()) {
			
			System.out.println(iterator.next() + " ");
		}
		
	}
		
	/**
	 * Reads file that user input and returns string array with words from the dictionary
	 * catches IOExpcetion when file is not found and exits the program
	 * @param name of the file from command line 
	 * @return String array that stores words from dictionary file
	 */
	
	public static String[] readFile (String name) {
		
		String[] dictionary = null;
		String line = null;

		try {
			
			FileReader file = new FileReader(name);
			BufferedReader reader = new BufferedReader(file);
			
			// create string array list to store lines from the program 
			ArrayList <String> lines = new ArrayList <String>();
			
			// while there are more lines add it to the array
			while ((line = reader.readLine()) != null) {
				
				lines.add(line);
			}
			
			reader.close();
			
			// convert array list into array 
			dictionary = lines.toArray(new String[lines.size()]); 	
		}
		
		catch (IOException e) {
			
			System.err.println("File was not found");
			System.exit(0);
			
		}
		return dictionary;	
	}
	
	
	/**
	 * Validates the string of characters that user enters and exits the program if an error is found.
	 * Errors include input with invalid characters such as "1!-" and length of characters than are smaller than two or greater than ten.
	 * @return String array of letters that have been validated 
	 */
		
		public static String[] validateInput() {

			System.out.println("Enter a string of letters (2-10 characters):");
			
			// take user input and makes the necessary changes  
			
			Scanner inputLetters = new Scanner(System.in);
			String letters = inputLetters.nextLine();
			
			// create char array to check whether input is all letters 
			char[] newLetters = letters.toCharArray();
			
			// loop through char array to validate input 
			
			if ((2 <= letters.length()) && (letters.length() <= 10)) {
				
				for (int i = 0; i < newLetters.length; i++) {
					
					if (Character.isLetter(newLetters[i])) {
						continue;
					}
					
					else {
						System.err.println("The input contains invalid characters");
						System.exit(0);
					}
				}
			}
			
			else {
				System.err.println("Size of input must be between 2 to 10 letters");
				System.exit(0);
			}
			
			// convert letters to lower case 
			String lowerLetters = letters.toLowerCase();
			
			// split user input into array 
			String[] splitLetters = lowerLetters.split("");
			String[] finalLetters = new String[(splitLetters.length) -1];
			
			// make a copy of splitLetters array 
			for (int i = 0; i < finalLetters.length; i++) {	
				finalLetters[i] = splitLetters[i+1];
			}
			return finalLetters;	
		}
}
