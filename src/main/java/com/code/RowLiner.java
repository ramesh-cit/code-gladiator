package com.code;
import java.util.ArrayList;
import java.util.List;


public class RowLiner {
	public static void main(String[] args) {
		String[] input = { "4#11", "6#0","5#2","6#1","7#1","5#11","5#11","5#10"};
		int value = RowLiner.getHeight(input, 4);
		System.out.println("Output:"+value);
	}

	public static int getHeight(String[] input1, int input2) {
		int output = 0;
		try {
			List<Integer> heightList = validate(input1, input2);
			for (int i = 0; i < heightList.size() - 1; i++)
				for (int j = i + 1; j < heightList.size(); j++) {
					if (heightList.get(i) > heightList.get(j)) {
						output++;
					}
				}
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
			return -1;
		}
		return output;
	}

	public static List<Integer> validate(String[] input, int input2) throws IllegalArgumentException {
		try {
			if (input == null) {
				throw new IllegalArgumentException("Input Array is Null, Please check");
			}
			List<Integer> heightList = new ArrayList<Integer>(input2);
			for (String height : input) {
				if (height == null) {
					throw new IllegalArgumentException("Array Value is Null, Please check");
				}
				String tokens[] = height.split("#");
				int feet, inch = 0;
				if (tokens.length == 2) {
					feet = Integer.parseInt(tokens[0]);
					inch = Integer.parseInt(tokens[1]);
					if (feet < 4 || feet > 7) {
						throw new IllegalArgumentException("Feet should be within range 4-7, Please check ..");
					}
					if (inch < 0 || inch > 11) {
						throw new IllegalArgumentException("Inch should be within range 0-11, Please check ..");
					}
				} else {
					throw new IllegalArgumentException("Invalid format - It should be (E.g) 5#7, Please check ..");
				}
				heightList.add(feet * 12 + inch);
			}
			return heightList;
		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException("Invalid format - It should be (E.g) 5#7, Please check ..");
		}

	}

}