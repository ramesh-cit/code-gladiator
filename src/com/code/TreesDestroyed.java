package com.code;

public class TreesDestroyed {

	public static void main(String[] args) {

		int input1 = 6 ; // row
		int input2 = 7 ; // column
		int input3 = 14; // destroyed plants
		String input4 = "((2,1),(6,6),(4,2),(2,5),(2,6),(2,7),(3,4),(6,1),(6,2),(2,3),(6,3),(6,4),(6,5),(6,7))";

		// Get the max trees destroyed
		int maxcount = TreesDestroyed.maxTreeDetroyed(input1, input2, input3, input4);
	}

	private static int maxTreeDetroyed(int input1, int input2,int input3,String input4) {

		try {
			// Validate the given inputs
			validateInput(input1,input2,input3,input4);
			int[][] treeMatrix = constructMatrix(input1, input2, input3 ,input4);

			int destroyedCount = pathCount(input1,input2,treeMatrix);
			//int destroyedCount = pathCount(treeMatrix);
			System.out.println("Output : "+destroyedCount);
			return destroyedCount;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	private static void validateInput(int input1, int input2, int input3,
			String input4) {
		// TODO Auto-generated method stub
		try {
			// Row  
			if (!(input1 > 0 && input1 <= 100 )) {
				throw new IllegalArgumentException("Invalid Row ,Please check");
			}
			// Column  
			if (!(input2 > 0 && input2 <= 100 )) {
				throw new IllegalArgumentException("Invalid Column ,Please check");
			}
			// Destroyed trees
			if (!(input3 >= 3 && input3 <= (input1*input2) )) {
				throw new IllegalArgumentException("Invalid Input");
			}
			// String null check
			if(input4 == null || input4.length() <= 0 )
				throw new IllegalArgumentException("Invalid Input");

		}catch(IllegalArgumentException ex){
			ex.printStackTrace();
			throw new IllegalArgumentException("Invalid Input");
		}
	}

	/*
	 * Find the number of paths
	 */
	private static int pathCount(int input1, int input2, int[][] treeMatrix) {
		// diagonal
		int diagonalCount = 0;
		int maxDestroyed = 0;
		int[] colSum =new int[input2];
		int[] rowSum =new int[input1];
		int[] diagnolSum =new int[input1];
		int maxRowDestroyed = 0;
		int maxDiagnolDestroyed = 0;
		int maxColDestroyed = 0;

		for (int row = 0; row < treeMatrix.length; row++) {
			if(treeMatrix[row][row] == 1)
				diagonalCount++;
		}
		for (int i = 0; i < input1; i++){   
			for (int j = 0; j < input2; j++){ 
				if(i==j && treeMatrix[i][j] == 1){
					diagnolSum[i] += treeMatrix[i][j];
				}
				if(maxDiagnolDestroyed <  diagnolSum[i])
					maxDiagnolDestroyed = diagnolSum[i];
			}
			System.out.println("DDD =" + diagnolSum[i]);
		} 

		// Row destroyed Count
		for (int i = 0; i < input1; i++){   
			for (int j = 0; j < input2; j++){ 
				if(treeMatrix[i][j] == 1){
					rowSum[i] += treeMatrix[i][j];
				}
				if(maxRowDestroyed <  rowSum[i])
					maxRowDestroyed = rowSum[i];
			}
			System.out.println("RRR =" + rowSum[i]);
		} 
		// Column destroyed Count
		for (int i = 0; i < input1; i++){   
			for (int j = 0; j < input2; j++){ 
				if(treeMatrix[i][j] == 1){
					colSum[j] += treeMatrix[i][j];
				}
				if(maxColDestroyed <  colSum[j])
					maxColDestroyed = colSum[j];  

			}
			System.out.println("CCC =" + colSum[i]);
		} 
		System.out.println(maxRowDestroyed + "******" + maxColDestroyed +"*******" + maxDiagnolDestroyed);

		if ( maxRowDestroyed > maxColDestroyed && maxRowDestroyed >maxDiagnolDestroyed )
			maxDestroyed = maxRowDestroyed;
		else if ( maxColDestroyed > maxRowDestroyed && maxColDestroyed > maxDiagnolDestroyed )
			maxDestroyed = maxColDestroyed;
		else
			maxDestroyed = maxDiagnolDestroyed;

		return maxDestroyed;
	}

	/*
	 * Convert the String into matrix and mark the destroyed place as 1
	 */
	private static int[][] constructMatrix(int input1, int input2,int input3, String input4) {
		//Convert the String into matrix
		int[][] treeMatrix = new int[input1][input2];
		try {
			input4 = "," + input4.substring(1, input4.length()-1);
			input4 = input4.replace(",(", "");

			String tempTokens[] = input4.split("\\)");
			System.out.println(tempTokens.length);

			if (tempTokens.length != input3) {
				throw new IllegalArgumentException("Invalid Input");
			}
			for (int k = 0; k < tempTokens.length; k++) {
				String tokens[] = tempTokens[k].split(",");
				for (int i = 0; i < input1; i++) {
					for (int j = 0; j < input2; j++) {
						// if the plant destroyed mark 1 
						if ((Integer.parseInt(tokens[0]) - 1) == i
								&& (Integer.parseInt(tokens[1]) - 1) == j) {
							treeMatrix[i][j] = 1;
						}
					}
				}
			}
			for (int i = 0; i < input1; i++) {
				for (int j = 0; j < input2; j++) {
					System.out.print(treeMatrix[i][j]+" ");
				}
				System.out.println();
			}
		} catch (NumberFormatException e) {
			throw e;
		}
		return treeMatrix;
	}


}
