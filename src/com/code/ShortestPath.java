package com.code;
class Route {
	private int cost;
	private String route;
	
	public Route(int cost, String route) {
		super();
		this.cost = cost;
		this.route = route;
	}
	public int getCost() {
		return cost;
	}
	public String getRoute() {
		return route;
	}
	@Override
	public String toString() {
		return cost+","+route;
	}
	
}
public class ShortestPath {
    public static void main(String[] args) {
           String[] input = { "5#7#2#4", "1#8#1#3", "6#2#9#5", "1#6#2#8" };
           String value = minimumCost(input, 4);
           System.out.println("Shortest Path:" + value);
    }
    enum Position {
           DOWN ("B"), DIAGONAL("D"), RIGHT("R"), INVALID("I");
           private String code;
           Position(String code) {
                  this.code = code;
           }
           public String code() {
                  return code;
           }
    }
    public static class Cell {
           private int i, j;
           private int cost;
           private Position position;

           @Override
           public boolean equals(Object o) {
                  // If the object is compared with itself then return true 
            if (o == this) {
                return true;
            }
            /* Check if o is an instance of Complex or not
              "null instanceof [type]" also returns false */
            if (!(o instanceof Cell)) {
                return false;
            }
            // typecast o to Cell so that we can compare data members
            Cell c = (Cell) o;
                  return (c.getI() == i && c.getJ() == j) ? true : false;
           }
           public Cell(int i, int j, int cost) {
                  this.i = i;
                  this.j = j;
                  this.cost = cost;
           }
           public Cell(int i, int j, int cost, Position pos) {
                  this.i = i;
                  this.j = j;
                  this.cost = cost;
                  this.position = pos;
           }

           public Cell() {
                  //Undefined Cell
                  position = Position.INVALID;
           }

           public int getI() {
                  return i;
           }

           public int getJ() {
                  return j;
           }

           public int getCost() {
                  return cost;
           }

           public void setPosition(Position position) {
                  this.position = position;
           }
           public Position getPosition() {
                  return position;
           }
    }

    public static String minimumCost(String[] input1, int input2) {
           int[][] matrix = parseMatrix(input1, input2);
           System.out.println("Matrix\n");
           for (int i = 0; i < matrix.length; i++) {
                  for (int j = 0; j < matrix.length; j++) {
                        System.out.print(matrix[i][j] + "\t");
                  }
                  System.out.println("\n");
           }

           Cell start = new Cell(0, 0, matrix[0][0]);
           
           Cell dest = new Cell(input2 - 1, input2 - 1,
                        matrix[input2 - 1][input2 - 1]);
           
           Route route = findRoute(start, dest, matrix, input2);
          
           return route.toString();
    }
 
    private static Route findRoute(Cell start, Cell dest, int[][] matrix, int size) {
        String route = "";
        boolean destReached = false;
        Cell downCell, rightCell, diagonalCell, currentCell;
        currentCell = start;
        int cost = currentCell.getCost();
        do {
               downCell = nextCell(currentCell, Position.DOWN, matrix, size);
               rightCell = nextCell(currentCell, Position.RIGHT, matrix, size);
               diagonalCell = nextCell(currentCell, Position.DIAGONAL, matrix, size);
              
               // Find Next least Cost Cell nearby
               Cell minimumCostCell = shortest(downCell, rightCell, diagonalCell);
              
               //Information
               cost = cost + minimumCostCell.getCost();
               route = route + minimumCostCell.getPosition().code();
               currentCell = minimumCostCell;
               //Destination Reached ??
               destReached = (minimumCostCell.equals(dest)) ? true : false;
        } while (destReached == false);
       
        
        
        System.out.println("Total Cost:"+cost);
        System.out.println("Route:"+route);
        return new Route(cost, route);
    }
    
    private static Cell nextCell(Cell currentCell, Position position, int[][] matrix, int size) {
           int row, column;
           switch (position) {
           case DIAGONAL:
                  row = currentCell.getI() + 1;
                  column = currentCell.getJ() + 1;
                  return (row < size && column < size) ? new Cell(row, column,
                               matrix[row][column]) : new Cell();
           case DOWN:
                  row = currentCell.getI() + 1;
                  column = currentCell.getJ();
                  return (row < size) ? new Cell(row, column,
                               matrix[row][column]) : new Cell();
           case RIGHT:
                  column = currentCell.getJ() + 1;
                  row = currentCell.getI();
                  return (column < size) ? new Cell(row,column,
                               matrix[row][column]) : new Cell();
           default:
                  throw new IllegalArgumentException("Invalid position");
           }

    }

    private static Cell shortest(Cell downCell, Cell rightCell,
                  Cell diagonalCell) {
           Cell shortestCell = new Cell();
           int bCost = (downCell.getPosition() != Position.INVALID) ? downCell
                        .getCost() : Integer.MAX_VALUE;
           int rCost = (rightCell.getPosition() != Position.INVALID) ? rightCell
                        .getCost() : Integer.MAX_VALUE;         
           int dCost = (diagonalCell.getPosition() != Position.INVALID) ? diagonalCell
                        .getCost() : Integer.MAX_VALUE;
           if (bCost < rCost) {
                  shortestCell = downCell;
                  shortestCell.setPosition(Position.DOWN);
                  if (dCost < bCost) {
                        shortestCell = diagonalCell;
                        shortestCell.setPosition(Position.DIAGONAL);
                  }
           } else {
                  shortestCell = rightCell;
                  shortestCell.setPosition(Position.RIGHT);
                  if (dCost < rCost) {
                        shortestCell = diagonalCell;
                        shortestCell.setPosition(Position.DIAGONAL);
                  }
           }
           return shortestCell;
    }

    private static int[][] parseMatrix(String[] input, int size) {
           if (input == null) {
                  throw new IllegalArgumentException("Input Array is Null, Please check");
           }
           if (input.length < size) {
                  throw new IllegalArgumentException("Invalid input, Please check ..");
           }
           int[][] matrix = new int[size][size];
           try {
                  for (int i = 0; i < size; i++) {
                        String tokens[] = input[i].split("#");
                        if (tokens.length < size) {
                               throw new IllegalArgumentException("Invalid input, Please check ..");
                        }
                        int j = 0;
                        for (String token : tokens) {
                               matrix[i][j++] = Integer.parseInt(token);
                        }
                  }
           } catch (NumberFormatException e) {
                  throw new IllegalArgumentException("Invalid format - It should be (E.g) 5#7#5#1, Please check ..");
           }
           return matrix;
    }
}