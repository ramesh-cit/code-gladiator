package com.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tree {
	public static void main(String[] args) {
		int input1 = 6; // row
		int input2 = 7; // column
		int input3 = 14; // destroyed plants
		String input4 = "((2,1),(6,6),(4,2),(2,5),(2,6),(2,7),(3,4),(6,1),(6,2),(2,3),(6,3),(6,4),(6,5),(6,7))";

		// Get the max trees destroyed
		int maxCount = Tree.maxTreeDetroyed(input1, input2, input3, input4);
		System.out.println("Max Trees Destroyed : " + maxCount);
	}

	private static void validateInput(int input1, int input2, int input3, String input4) {
		try {
			// Row
			if (!(input1 > 0 && input1 <= 100)) {
				throw new IllegalArgumentException("Invalid Row ,Please check");
			}
			// Column
			if (!(input2 > 0 && input2 <= 100)) {
				throw new IllegalArgumentException("Invalid Column ,Please check");
			}
			// Destroyed trees
			if (!(input3 >= 3 && input3 <= (input1 * input2))) {
				throw new IllegalArgumentException("Invalid Input");
			}
			// String null check
			if (input4 == null || input4.length() <= 0)
				throw new IllegalArgumentException("Invalid Input");

		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException("Invalid Input");
		}
	}

	private static int maxTreeDetroyed(int input1, int input2, int input3, String input4) {
		// Validate the given inputs
		validateInput(input1, input2, input3, input4);
		int[][] treeMatrix = constructMatrix(input1, input2, input3 ,input4);
		printTreeMatrix(treeMatrix);
		int maxHTrees = checkPath(treeMatrix, Direction.HORIZONTAL);
		int maxVTrees = checkPath(treeMatrix, Direction.VERTICAL);
		if (maxHTrees > maxVTrees) {
			return maxHTrees;
		}
		return maxVTrees;
	}
	
	enum Direction {
		VERTICAL, HORIZONTAL;
	}
 
	private static int checkPath(int[][] treeMatrix, Direction dir) {
		int colSize = (dir == Direction.HORIZONTAL) ? treeMatrix[0].length: treeMatrix.length;
		int rowSize  = (dir == Direction.HORIZONTAL) ? treeMatrix.length:treeMatrix[0].length;
		
		int[] pathList = new int[rowSize];
		for (int i = 0; i < rowSize; i++) {
			int count = 0; 
			List<Integer> list = new ArrayList<Integer>();  
			for (int j = 0; j < colSize; j++) {
				int value = (dir == Direction.HORIZONTAL)  ?treeMatrix[i][j] : treeMatrix[j][i];
				if (value == 1) {
					count++;
					list.add(j);
				}
			}
			if (count == colSize) {
				pathList[i] = count;
			}
			if (count >= 3) {
				if (checkSameDifference(list))  {
					pathList[i] = count;
				}
			}  else {
				pathList[i] = 0;
			}
			System.out.println("Number Of Trees:"+pathList[i]);
		}
		Arrays.sort(pathList);
		return pathList[pathList.length - 1];
	}

	private static boolean checkSameDifference(List<Integer> pathList) {
		int diff = pathList.get(0) - pathList.get(1); 
		for (int i=1;i<pathList.size() - 1;i++) {
			if ((pathList.get(i + 1) - pathList.get(i)) != diff) return false; 
		}
		return true;
	}

	private static void printTreeMatrix(int[][] treeMatrix) {
		for (int i = 0; i < treeMatrix.length; i++) {
			for (int j = 0; j < treeMatrix[0].length; j++) {
				System.out.print(treeMatrix[i][j]+" ");
			}
			System.out.println();
		}
	}

	private static int[][] constructMatrix(int rowCount, int columnCount, int count, String position) {
		//Convert the String into matrix
		int[][] treeMatrix = new int[rowCount][columnCount];
		position = position.replace("((", "");
		position = position.replace("))", "");
		position = position.replace("),(", ";");
		System.out.println(position);
		String[] tokens = position.split(";");
		if (tokens.length != count) {
			throw new IllegalArgumentException("Invalid Input");
		}
		for(String location:tokens) {
			int row = Integer.valueOf((String) location.substring(0, 1));
			int column = Integer.valueOf((String) location.substring(2, 3));
			treeMatrix[row - 1][column-1] = 1;
		}
		return treeMatrix;
	}
}
