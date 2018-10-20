import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Random;

public class EfficientWordMarkov extends BaseWordMarkov {
	private HashMap<WordGram,ArrayList<String>> myMap;
	
	/**
	 * Default constructor has order 3
	 */
	public EfficientWordMarkov() {
		this(2);
	}
	/**
	 * Construct a EfficientWordMarkov object with
	 * the specified order
	 * @param order size of this Markov generator
	 */
	public EfficientWordMarkov(int order){
		super(order); //
		myMap = new HashMap<WordGram, ArrayList<String>>();
	}
	/**
	 * Creates a map with keys of that are wordgrams of length my order from the given text and values that are array lists of 
	 * strings of words that follow the key. 
	 * @param a given string
	 */
	@Override
	public void setTraining(String text){
		myMap.clear();
		myWords = text.split("\\s+");
		int len=myWords.length;
		for (int k=0; k< len - myOrder; k++) {
			WordGram myMapKey=new WordGram(myWords,k,myOrder);
			if (!myMap.containsKey(myMapKey)) {
				ArrayList <String> MyMapValue= new ArrayList <String>();
				MyMapValue.add(myWords[k+ myOrder]);
				myMap.put(myMapKey, MyMapValue);
			}else 
				{myMap.get(myMapKey).add(myWords[k+ myOrder]);}	
		}
		
		WordGram MyMapKey = new WordGram(myWords, len - myOrder, myOrder);
		if (!myMap.containsKey(MyMapKey)) {
			ArrayList<String> MyMapValue = new ArrayList<String>();
			MyMapValue.add(PSEUDO_EOS);
			myMap.put(MyMapKey, MyMapValue);
			
		} else {
			myMap.get(MyMapKey).add(PSEUDO_EOS);
		}
		
	}
	/**
	 * Returns the value of a given key in myMap and throws a No Such Element Exception if the key isn't found
	 * @param a given Wordgram
	 * @return An array list of strings
	 */
	@Override
		public ArrayList<String> getFollows(WordGram key) {
			if (!myMap.containsKey(key)) {
				throw new NoSuchElementException();
				
			} else {
				return myMap.get(key); 
			}
		}
	
	
}
