import java.util.*;
public class EfficientMarkov extends BaseMarkov{
	private HashMap<String,ArrayList<String>> myMap;

	/**
	 * Construct a EfficientMarkov object with
	 * the specified order
	 * @param order size of this markov generator
	 */
	public EfficientMarkov(int order) {
		super(order);//
		myMap = new HashMap<String, ArrayList<String>>();
	}

	/**
	 * Default constructor has order 3
	 */
	public EfficientMarkov() {
		this(3);
	}
	
	/**
	 * Creates a map with keys of that are strings of length my order from the given text and values that are array lists of 
	 * strings of characters that follow the key. At the end of the text, a Pseudo Eos tag is added that breaks the loop
	 * @param a given string
	 */
	@Override
	public void setTraining(String text) {
		myMap.clear();
		myText=text;
		int len=text.length();
		
		for (int k=0; k< len - myOrder; k++) {
			String MyMapKey= text.substring(k, k + myOrder);
			if (!myMap.containsKey(MyMapKey)) {
				ArrayList <String> MyMapValue= new ArrayList <String>();
				MyMapValue.add(text.substring(k+myOrder, k+myOrder +1));
				myMap.put(MyMapKey, MyMapValue);
			}else 
				{myMap.get(MyMapKey).add(text.substring(k+myOrder ,k+myOrder +1));}
		}
		String MyMapKey= text.substring(len-myOrder, len);
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
	 * @param a given string
	 * @return An array list of strings
	 */
	
	@Override
	public ArrayList<String> getFollows(String MyMapKey) {
		if (!myMap.containsKey(MyMapKey)) {
			throw new NoSuchElementException(MyMapKey + " not in map");
		} else {
			return myMap.get(MyMapKey);
		}
	}

}
