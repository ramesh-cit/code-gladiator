package com.code;

import java.io.*;
import java.util.*;

public class CandidateCode {
	 public static class Cell {
         private int row, column;

		public int getRow() {
			return row;
		}

		public void setRow(int row) {
			this.row = row;
		}

		public int getColumn() {
			return column;
		}

		public void setColumn(int column) {
			this.column = column;
		}
          
         
	 }
	 
	public static void main(String[] args) {
		String[] position = {"1#1","2#5","3#3","6#3"};
		String[] temperature = {"2#6#8#6#-7","2#5#-5#-5#0","-1#3#-8#8#7","3#2#0#6#9","2#1#-4#5#8","-5#6#7#4#7"};
		int steps = 3;
		String[] finalPosition = RatsPostions(position, temperature, steps);
		for(String p: finalPosition)
		System.out.print(p + " ");
	}

	public static String[] RatsPostions(String[] input1, String[] input2, int input3) {
		int pos[][];
		String tokens[];
	
		pos = new int[input1.length][2];
		
		for (int i = 0; i < pos.length; i++) {
			tokens = input1[i].split("#");
			if (tokens.length == 2) {
				
				pos[i][0] = (Integer.parseInt(tokens[0]) - 1) < 0 ? 0 : Integer.parseInt(tokens[0]) - 1;
				pos[i][1] = (Integer.parseInt(tokens[1]) - 1) < 0 ? 0 : Integer.parseInt(tokens[1]) - 1;
			} else {
				throw new IllegalArgumentException("Invalid format - It should be (E.g) 5#7, Please check ..");
			}
		}
		int rectangle[][];
		rectangle = new int[input2.length][input2[0].split("#").length];
		for (int i = 0; i < rectangle.length; i++) {
			tokens = input2[i].split("#");
			for (int k = 0; k < tokens.length; k++) {
				rectangle[i][k] = Integer.parseInt(tokens[k]);
			}
		}
		
		
		////////////
		int ss[];
		for (int j = 0; j < input3; j++) {
			for (int k = 0; k < input1.length; k++) {
				ss = positionFinder(pos[k][0], pos[k][1], rectangle);
				pos[k][0] = ss[0];
				pos[k][1] = ss[1];
			}
		}
		StringBuilder finalOP = new StringBuilder();
		for (int k = 0; k < pos.length; k++) {
			for (int l = 0; l < pos[k].length; l++) {
				finalOP.append(Integer.valueOf(pos[k][l] + 1).toString());
				if (l == 0)
					finalOP.append("#");
				else {
					if (k < pos.length - 1)
						finalOP.append(",");
				}
			}
		}
		return finalOP.toString().split(",");
	}

	private static int[] positionFinder(int i, int j, int[][] rectangle) {
		int currTemp = rectangle[i][j];
		int ex, ey, wx, wy, sx, sy, nx, ny;
		int veast, vwest, vsouth, vnorth;
		veast = vwest = vsouth = vnorth = -1;
		ex = ey = wx = wy = sx = sy = nx = ny = 0;
		int east, west, north, south;
		if (i < rectangle.length - 1)
			ex = i + 1;
		ey = j;
		if (i > 0)
			wx = i - 1;
		wy = j;
		if (j > 0)
			sy = j - 1;
		sx = i;
		if (j < rectangle[0].length - 1)
			ny = j + 1;
		nx = i;
		try {
			east = rectangle[ex][ey];
			west = rectangle[wx][wy];
			south = rectangle[sx][sy];
			north = rectangle[nx][ny];
			east = east - currTemp;
			east = (east < 0) ? (-1) * east : east;
			west = west - currTemp;
			west = (west < 0) ? (-1) * west : west;
			south = south - currTemp;
			south = (south < 0) ? (-1) * south : south;
			north = north - currTemp;
			north = (north < 0) ? (-1) * north : north;
			int arr[];
			if (i == 0 && j == 0) {
				arr = new int[] { east, north };
				veast = 0;
				vnorth = 0;
			} else if (i == 0 && j == rectangle[0].length - 1) {
				// arr=new int[]{west,east};
				arr = new int[] { east, south };
				vwest = 0;
				veast = 0;
			}

			else if (i == rectangle.length - 1 && j == rectangle[0].length - 1) {
				arr = new int[] { south, west };
				vsouth = vwest = 0;
			} else if (i == rectangle.length - 1 && j == 0) {
				arr = new int[] { north, west };
				vnorth = vwest = 0;
			} else if (i == 0 && j != 0 && j != rectangle[0].length - 1) {
				arr = new int[] { north, south, east };
				vnorth = vsouth = veast = 0;
			} else if (j == 0 && i != 0 && i != rectangle.length - 1) {
				arr = new int[] { north, west, east };
				vnorth = vwest = veast = 0;
			} else if (i == rectangle.length - 1 && j != 0 && j != rectangle[0].length - 1) {
				arr = new int[] { north, south, west };
				vnorth = vsouth = vwest = 0;
			} else if (j == rectangle[0].length - 1 && i != 0 && i != rectangle.length - 1) {
				arr = new int[] { west, south, east };
				vwest = vsouth = veast = 0;
			} else {
				arr = new int[] { east, west, south, north };
				veast = vwest = vsouth = vnorth = 0;
			}

			Arrays.sort(arr);
			if (i != 0)
				if (arr[0] == west && vwest == 0) {
					return new int[] { wx, wy };
				}

			if (j != rectangle[0].length - 1)
				if (arr[0] == north && vnorth == 0) {
					return new int[] { nx, ny };
				}

			if (j != rectangle.length - 1)
				if (arr[0] == east && veast == 0) {

					return new int[] { ex, ey };
				}

			if (j != 0)
				if (arr[0] == south && vsouth == 0) {
					return new int[] { sx, sy };
				}
		} catch (Exception e) {
		}
		return new int[] { i, j };
	}
}