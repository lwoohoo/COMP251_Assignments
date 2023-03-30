import java.util.*;

public class HW_Sched {
	ArrayList<Assignment> Assignments = new ArrayList<Assignment>();
	int m;
	int lastDeadline = 0;
	
	protected HW_Sched(int[] weights, int[] deadlines, int size) {
		for (int i=0; i<size; i++) {
			Assignment homework = new Assignment(i, weights[i], deadlines[i]);
			this.Assignments.add(homework);
			if (homework.deadline > lastDeadline) {
				lastDeadline = homework.deadline;
			}
		}
		m =size;
	}
	
	
	/**
	 * 
	 * @return Array where output[i] corresponds to the assignment 
	 * that will be done at time i.
	 */
	public int[] SelectAssignments() {
		//TODO Implement this
		
		//Sort assignments
		//Order will depend on how compare function is implemented
		Collections.sort(Assignments, new Assignment()); //after this point the list is sorted by weight
		//remove all elements that won't be inclulded in the schedule
		for (int i = Assignments.size() - 1; i >= lastDeadline; i--) {
			Assignments.remove(i); //remove all such elements until size = lastDeadline
		}
		
		// If homeworkPlan[i] has a value -1, it indicates that the 
		// i'th timeslot in the homeworkPlan is empty
		//homeworkPlan contains the homework schedule between now and the last deadline
		int[] homeworkPlan = new int[lastDeadline];
		for (int i=0; i < homeworkPlan.length; ++i) {
			homeworkPlan[i] = -1;
		}
		
		//loops selects the best option and places it in the startning of the schedule
		//note that if #hw > #hours,
		ArrayList<Assignment> workingList = new ArrayList(Assignments);
		for (int index = 0; index < homeworkPlan.length; index++){ //iterate over the schedule
			//select item with soonest deadline
			Assignment min = workingList.get(0); //initialize min as first item in arraylist
			for (Assignment item: workingList) {
				if (item.deadline <= min.deadline) {
					min = item;
				}
			}
			homeworkPlan[index] = min.number;
			workingList.remove(min);
		}
		
		return homeworkPlan;
	}
}
	



