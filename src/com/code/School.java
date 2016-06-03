package com.code;

import java.io.*;

public class School {

	public static void main(String[] args) {
		int m = 5, n = 6, p = 3, q = 4;

		int numberOfChildrens = School.distributeCadbury(m, n, p, q);
		System.out.println("Number Of Childrens:" + numberOfChildrens);
	}

	public static int distributeCadbury(int input1, int input2, int input3,
			int input4) {
		int count = 0;
		for (int length = input1; length <= input2; length++) {
			for (int breadth = input3; breadth <= input4; breadth++) {
				count = count + numberOfPieces(length, breadth);
			}
		}
		return count;
	}

	private static int numberOfPieces(int length, int breadth) {
		if (length == breadth)
			return 1;
		return (length < breadth) ? (1 + numberOfPieces(length, breadth
				- length)) : (1 + numberOfPieces(length - breadth, breadth));
	}
}