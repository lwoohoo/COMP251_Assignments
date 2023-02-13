import java.util.*;

public class A1_Q3 {

	public static int elements1(int[] sizes) { //brute force - not sufficient
		ArrayList<Integer> working_list = new ArrayList<Integer>();
		int maxLength = 0;
		for (int item: sizes) {
			boolean contains = working_list.contains(item);
			if (!contains) {
				working_list.add(item);
			} else { //if in list => seq of unique sizes ends
				if (working_list.size() > maxLength) maxLength = working_list.size(); //reset the size of longest unique sequence
				working_list.clear(); //clear the list
			}

		}
		return maxLength;
	}

	public static int elements(int[] sizes) {
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		int maxLength = 0;
		for (int i = 0; i < sizes.length; i++) {
			Integer itemIndex = hm.get(sizes[i]);
			if (itemIndex == (null)) { //if not in hm
				hm.put(sizes[i], i);
			} else {
				if (hm.size() > maxLength) maxLength = hm.size();
				i = itemIndex; //the +1 will be added at the end of the loop iteration
				hm.clear();
			}
		}
		return maxLength;
	}
}
