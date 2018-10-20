import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Random;

public class EfficientWordMarkov extends BaseWordMarkov {
	private HashMap<WordGram,ArrayList<String>> myMap;
	
	public EfficientWordMarkov() {
		this(2);
	}
	
	public EfficientWordMarkov(int order){
		super(order); //
		myMap = new HashMap<WordGram, ArrayList<String>>();
	}

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
	
	@Override
		public ArrayList<String> getFollows(WordGram key) {
			if (!myMap.containsKey(key)) {
				throw new NoSuchElementException();
				
			} else {
				return myMap.get(key); 
			}
		}
	
	
}
