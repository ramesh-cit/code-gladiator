package com.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
 

public class JoinedPipes {

	public static void main(String[] args) {
		// initialize Input 1 : Length of pipes
		// initialize Input 2 : No. of pipes
		int input1[] = new int[] { 4, 7, 2, 5, 6, 3, 8, 12, 3 };
		//int input1[] = new int[] { 4, 3, 2};
		int input2 = 9;
		// Get the minimum length of joined pipes in ascending order
		int[] value = JoinedPipes.getJoinedPipes(input1, input2);
		for (int v:value) {
			System.out.print(v + " ");
		}

	}

	private static int[] getJoinedPipes(int[] input1, int input2) {
		try {
			validateInput(input1, input2);
			return minPipeCost(input1);
		} catch (IllegalArgumentException ex) {
			throw ex;
		}
	}

	/**
	 * Validate the Given inputs
	 * 
	 * @param input1
	 * @param input2
	 */
	private static void validateInput(int[] input1, int input2) {
		if (input2 <= 0) {
			throw new IllegalArgumentException("The input cannot be zero");
		}
		if (input1 == null) {
			throw new IllegalArgumentException("Input Array is Null, Please check");
		}
		if (input1.length != input2) {
			throw new IllegalArgumentException("Invalid Input for the array 1 Please check");
		}
	}

	/**
	 * To find the minimum length of the Pipes in the given input
	 * 
	 * @param input1
	 * @param input2
	 * @return output - array(minimum length of joined pipes in ascending
	 *         order)
	 */
	public static int[] minPipeCost(int[] input1) {
		if (input1.length == 1) {
			return new int[] { 0 };
		}
		if (input1.length == 2) {
			return new int[] { input1[0] + input1[1] };
		}
		// sort input array
		Arrays.sort(input1);
		
		List<Integer> list = new ArrayList<Integer>();
		
		for (int i = 0; i < input1.length; i++) {
			list.add(Integer.valueOf(input1[i]));
		}
		// Output array
		int[] output = new int[input1.length - 1];
		 
		int j = 0;
		do {
			int value = list.get(0) + list.get(1);
			list.remove(0);
			list.remove(0);
			addItemToSortedList(value, list);
			output[j++] = value;
			if (list.size()<3) {
				output[j] = list.get(0) + list.get(1);
				break;
			} 
		} while (true);
		return output;
	}
	
	private static void addItemToSortedList(int item, List<Integer> list) {
		for (int i = 0; i < list.size(); i++) {
			if (item <= list.get(i)) {
				list.add(i, item);
				return;
			} else {
				continue;
			}
		}
		list.add(item);
	}
}