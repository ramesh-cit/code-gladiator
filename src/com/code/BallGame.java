package com.code;

public class BallGame {
	public static void main(String[] args) {
		int n = 5, m = 3, l = 2;
		int output = passCount(n, m, l);
		System.out.println("Output:"+output);
	}

	enum Direction {
		CLOCK, ANTI_CLOCK;
	}

	public static int passCount(int input1, int input2, int input3) {
		
		if (!valid(input1, input2)) {
			return -1;
		}
		int passCount = 0;
		int playerIndex = 0; // first player
		boolean pass = true;
		int player[] = new int[input1 + 1];
		do {
			player[playerIndex]++;
			if (player[playerIndex] == input2)
				break;
			if (player[playerIndex] % 2 == 0) {
				// even
				playerIndex = nextNumber(input1 , playerIndex, input3, Direction.ANTI_CLOCK);
			} else {
				// odd
				playerIndex = nextNumber(input1, playerIndex, input3, Direction.CLOCK);
			}
			passCount++;
			// game over

		} while (pass == true);
		return passCount;
	}

	private static boolean valid(int input1, int input2) {
		if ((input1 >=3 && input1<=1000) && (input2>=3 && input2<=1000)) return true;  
		return false;
	}

	private static int nextNumber(int max, int current, int count, Direction dir) {
		int nextPlayer;
		if (dir == Direction.CLOCK) {
			nextPlayer = (current + count) % max;
		} else {
			nextPlayer = current - count;
			if (nextPlayer >= 0) {
				return nextPlayer;  
			} else {
				nextPlayer = ((nextPlayer * -1) % max);
				nextPlayer = max - nextPlayer; 
			}
		}
		return nextPlayer;
	}
}
