//import java.util.*;

public class A2_Q2 {

	public static int change(int[] denominations) {
		int finalIndex = denominations.length - 1;
		int bound = denominations[finalIndex] + denominations[finalIndex -1];
		int[] dynamic = dynamic(denominations, bound, finalIndex);
		for (int i = 2; i < bound; i++) {
			int greedy = greedy(denominations, i, finalIndex);
			if (dynamic[i] != greedy) {
				return i; //counterexample found => greedy fails
			}
		}
		return -1; //if loops ends => no counterexample exists
		
	}

	public static int greedy(int[] denominations, int value, int finalIndex) {
		int total = 0;
		while (finalIndex >= 0) { //loop through potentially all coins
			int coin = denominations[finalIndex]; //calculate coin for that loop
			while (value >= coin) { //subtracts as many of coin as possible
				value = value - coin;
				total++;
			}
			if (value == 0) {
				return total; //exit case
			}
			finalIndex --;
		}

		
		return 0;
	}


	public static int[] dynamic(int[] denominations, int value, int finalIndex) {
		int refTable[] = new int[value + 1];

		//format table
		refTable[0] = 0;
		for (int i = 1; i <= value; i++) {
			refTable[i] = Integer.MAX_VALUE;
		}

		for (int i = 1; i <= value; i++) {

			for (int j = finalIndex; j >= 0; j--) {
				if (denominations[j] <= i) { //if coin_j fits in i then
					int workingTotal = refTable[i - denominations[j]]; //store realtive total value in table (allows use of previously calculated values) 
					if (workingTotal != Integer.MAX_VALUE && workingTotal + 1 < refTable[i]){
						 refTable[i] = workingTotal + 1;
					}
				}
				//otherwise move to next coin until one does fit
			}
		}
		return refTable;
	}
}
