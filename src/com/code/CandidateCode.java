package com.code;

import java.io.*;

import java.util.Arrays;

public class CandidateCode {
	public static void main(String[] args) {
		String[] position = { "1#1", "2#5", "3#3", "6#3" };
		String[] temperature = { "2#6#8#6#-7", "2#5#-5#-5#0", "-1#3#-8#8#7", "3#2#0#6#9", "2#1#-4#5#8", "-5#6#7#4#7" };
		int steps = 3;
		System.out.println("Number Of Steps :" + steps);
		System.out.println("Input Rat Position:" + Arrays.asList(position));
		String[] finalPosition = RatsPostions(position, temperature, steps);
		System.out.println("Final Rat Position:" + Arrays.asList(finalPosition));
	}

	public static class Cell implements Comparable<Cell> {
		private int row, column;
		private int temperature;
		private int difference;
		private Direction direction;

		public Cell(int row, int column) {
			this.row = row - 1;
			this.column = column - 1;
		}

		public Cell(int row, int column, int temp, int difference, Direction dir) {
			this.row = row;
			this.column = column;
			this.temperature = temp;
			this.difference = (difference < 0) ? -1 * difference : difference;
			this.direction = dir;
		}

		public Cell() {
			difference = Integer.MAX_VALUE;
			direction = Direction.NO_DIRECTION;
		}

		public int getRow() {
			return row;
		}

		public int getColumn() {
			return column;
		}

		public void updatePosition(int row, int column) {
			this.column = column;
			this.row = row;
		}

		public String getPosition() {
			return (row + 1) + "#" + (column + 1);
		}

		public int getTemperature() {
			return temperature;
		}

		public void setTemperature(int temperature) {
			this.temperature = temperature;
		}

		public int getDifference() {
			return difference;
		}

		@Override
		public int compareTo(Cell cell) {
			if (this.difference == cell.getDifference()) {
				return (this.direction.priority() > cell.getDirection().priority()) ? -1 : 1;
			} else {
				return (this.difference < cell.getDifference()) ? -1 : 1;
			}
		}

		public Direction getDirection() {
			return direction;
		}

	}

	/**
	 * @param input1
	 * @param input2
	 * @param input3
	 * @return
	 */
	public static String[] RatsPostions(String[] input1, String[] input2, final int input3) {
		try {
			int columnSize = input2[0].split("#").length;
			// Create Matrix of Temperature
			int matrix[][] = new int[input2.length][columnSize];

			for (int i = 0; i < matrix.length; i++) {
				String tokens[] = input2[i].split("#");

				for (int k = 0; k < columnSize; k++) {
					matrix[i][k] = Integer.parseInt(tokens[k]);
				}
			}

			// Create Initial Rat Position
			Cell ratPosition[] = new Cell[input1.length];
			for (int i = 0; i < input1.length; i++) {
				ratPosition[i] = createCell(input1[i], matrix);
			}

			// Update Position for n times
			for (int j = 0; j < input3; j++) {
				for (int k = 0; k < ratPosition.length; k++) {
					positionUpdater(ratPosition[k], matrix);
				}
			}

			// Create Output
			String newPosition[] = new String[ratPosition.length];
			int i = 0;
			for (Cell c : ratPosition) {
				newPosition[i++] = c.getPosition();
			}
			return newPosition;
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid input format, Please check ..");
		}
	}

	/**
	 * @param rowColumn
	 * @param rectangle
	 * @return
	 */
	private static Cell createCell(String rowColumn, int[][] rectangle) {
		String tokens[] = rowColumn.split("#");
		if (tokens.length == 2) {
			Cell cell = new Cell(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
			cell.setTemperature(rectangle[cell.getRow()][cell.getColumn()]);
			return cell;
		} else {
			throw new IllegalArgumentException("Invalid format - It should be (E.g) 5#7, Please check ..");
		}
	}

	enum Direction {
		UPPER(4), LOWER(2), LEFT(1), RIGHT(3), NO_DIRECTION(0);
		int priority;

		Direction(int p) {
			this.priority = p;
		}

		int priority() {
			return this.priority;
		}
	}

	/**
	 * @param cell
	 * @param rectangle
	 */
	private static void positionUpdater(final Cell cell, final int[][] rectangle) {
		Cell[] list = new Cell[4];
		list[0] = nearByCell(rectangle, cell, Direction.UPPER);
		list[1] = nearByCell(rectangle, cell, Direction.RIGHT);
		list[2] = nearByCell(rectangle, cell, Direction.LOWER);
		list[3] = nearByCell(rectangle, cell, Direction.LEFT);
		Arrays.sort(list);
		cell.updatePosition(list[0].getRow(), list[0].getColumn());
	}

	/**
	 * @param rectangle
	 * @param cell
	 * @param dir
	 * @return
	 */
	private static Cell nearByCell(int[][] rectangle, Cell cell, Direction dir) {
		int rowSize = rectangle.length;
		int columnSize = rectangle[0].length;
		int i = cell.getRow();
		int j = cell.getColumn();
		switch (dir) {
		case LEFT:
			j = (cell.getColumn() > 0) ? (cell.getColumn() - 1) : Integer.MAX_VALUE;
			break;
		case LOWER:
			i = (cell.getRow() < rowSize - 1) ? (cell.getRow() + 1) : Integer.MAX_VALUE;
			break;
		case RIGHT:
			j = (cell.getColumn() < columnSize - 1) ? (cell.getColumn() + 1) : Integer.MAX_VALUE;
			break;
		case UPPER:
			i = (cell.getRow() > 0) ? (cell.getRow() - 1) : Integer.MAX_VALUE;
			break;
		default:
			throw new IllegalArgumentException("Invalid Direction, Please check");
		}
		return i == Integer.MAX_VALUE || j == Integer.MAX_VALUE ? new Cell()
				: new Cell(i, j, rectangle[i][j], cell.getTemperature() - rectangle[i][j], dir);
	}

}