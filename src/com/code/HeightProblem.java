package com.code;

public class HeightProblem {
	public static void main(String[] args) {
		int n = 4;
		int left[] = { 2, 1, 1, 0 };
		System.out.println("Output:"+uniqueValue(n, left));
	}

	public static int[] uniqueValue(int count, int[] tallerCountArray) {
		int heightArray[] = new int[count];
		for (int heightIndex = 0;heightIndex <count;heightIndex++) {
			int tallCount = tallerCountArray[heightIndex];
			//
		}
		return heightArray;
	}

}
