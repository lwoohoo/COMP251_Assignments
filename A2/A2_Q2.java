import java.util.*;

public class A2_Q2 {

	public static int change(int[] denominations) {
		int finalIndex = denominations.length - 1;
		int bound = denominations[finalIndex] + denominations[finalIndex -1];
		for (int i = 2; i < bound; i++) {
			int dynamic = dynamic(denominations, i);
			int greedy = greedy(denominations, i, finalIndex);
			if (dynamic != greedy) return greedy; //counterexample found => greedy fails
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
			if (value == 0) return total; //exit case
		}

		
		return 0;
	}


	public static int dynamic(int[] denominations, int value) {
		

		return -1;
	}

}
