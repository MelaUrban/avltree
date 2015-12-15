import java.util.Iterator;

public class AVLTrial {

	public static void main(String[] args) {
		
		
		String[] words = {"5", "7" , "8" , "10", "31", "59" , "69"};
		
		AVLTree<String> wordTree = new AVLTree<String>();
		
		for (int i=0; i < words.length; i++) {
			wordTree.add(words[i]);	
		}
		
		System.out.println("Adding another word: "); 
		
		wordTree.add("42");
		System.out.println(wordTree.contains("42"));
		
		
		//System.out.println(wordTree.toString());
		
//		System.out.println("Words in the dictionary");
//		
		//Iterator<String> iter = wordTree.iterator();
//		
//		while (iter.hasNext()) {
//			System.out.println(iter.next() + " ");
//		}
		
//		wordTree.remove("cat");
//		
//		System.out.println("After removing banana");
		
//		Iterator<String> iter2 = wordTree.iterator();
//
//		while (iter2.hasNext()) {
//			System.out.println(iter2.next() + " ");
//		}	
		
//		System.out.println(wordTree.toString());
//		System.out.println("contains word: "  + wordTree.contains("sat"));
		
		
		
		

		
	}
}
