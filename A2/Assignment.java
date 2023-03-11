import java.util.*;

class Assignment implements Comparator<Assignment>{
	int number;
	int weight;
	int deadline;
	
	protected Assignment() {
	}
	
	protected Assignment(int number, int weight, int deadline) {
		this.number = number;
		this.weight = weight;
		this.deadline = deadline;
	}
	
	/**
	 * This method is used to sort to compare assignment objects for sorting. 
	 * Sort by weight
	 */
	@Override
	public int compare(Assignment a1, Assignment a2) {
		if (a1.number == a2.number) {
			return 0; //if the assignment numbers are the same => they are the same object
		}
		if (a1.weight >= a2.weight) {
			return -1; //if a1 weight greater than or equal to a2 weight then assign it priority
		} else {
			return 1; //if a1 wight less than a2 weight then assign it priority.
		}
	}
}
