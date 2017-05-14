package com.code;

import java.util.Arrays;

class ThirstyCrow {
	public static void main(String[] args) throws java.lang.Exception {
		// your code goes here
		int[] integerArray = { 23, 12, 34, 45, 67, 12, 7 };
		// int[] integerArray = { 1, 2, 3, 4, 5, 5, 6 };
		System.out.println(ThirstyCrowProblem(integerArray, 7, 3));
	}

	public static int ThirstyCrowProblem(int[] input1, int input2, int input3) {

		if ((input1.length < input3) || (input1.length != input2)) {
			return -1;
		}
		Arrays.sort(input1);
		// Get Last Pot required stones
 		int last = input1[input3 - 1];
 
		int i, numberOfStones = 0, minimumStones;
		// number of stones to required to fill N(input3) pots
		for (i = 0; i < input3; i++) {
			numberOfStones += input1[input1.length - 1 - i];
		}

		int count, stoneCount = 0;
		for (i = 0, count = 0; count < input3; i++) {
			if ((input1[input1.length - i - 1] > last)) {
				stoneCount += last;
			} else {
				stoneCount += input1[input1.length - i - 1];
				count++;
			}
		}
		minimumStones = Math.min(numberOfStones, stoneCount);
		return minimumStones;
	}

}
