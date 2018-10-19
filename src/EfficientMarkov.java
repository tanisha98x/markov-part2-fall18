import java.util.*;
public class EfficientMarkov extends BaseMarkov{
	protected HashMap<String,ArrayList<String>> myMap;

	public EfficientMarkov(int order) {
		super(order);//
		myMap = new HashMap<String, ArrayList<String>>();
	}
	public EfficientMarkov() {
		this(3);
	}

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
	
	@Override
	public ArrayList<String> getFollows(String MyMapKey) {
		if (!myMap.containsKey(MyMapKey)) {
			throw new NoSuchElementException(MyMapKey + " not in map");
		} else {
			return myMap.get(MyMapKey);
		}
	}

}
