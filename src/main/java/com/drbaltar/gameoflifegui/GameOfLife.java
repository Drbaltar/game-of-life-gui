package com.drbaltar.gameoflifegui;

import java.util.Random;
import java.util.stream.IntStream;

public class GameOfLife {

    public static int[][] grid;
    static int x = 50, y = 50;

    public static void main(String[] args) {
        generateRandomGrid();
        System.out.println("Original Generation");

        visualizer();
        IntStream.range(0,20).forEach(x->{
                nextGeneration();
                System.out.println("Generation: " + (x + 1));
                visualizer();
        });
    }


    /* Implement this method */
    public static void nextGeneration() {
        int[][] nextGenGrid = new int[x][y];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (isAliveInNextGen(i, j))
                    nextGenGrid[i][j] = 1;
            }
        }
        GameOfLife.grid = nextGenGrid;
    }

    public static void generateRandomGrid() {
        Random random = new Random();
        int[][] randomGrid = new int[x][y];

        for (int i = 0; i < randomGrid.length; i++) {
            for (int j = 0; j < randomGrid[i].length; j++) {
                randomGrid[i][j] = random.nextInt(2);
            }
        }
        grid = randomGrid;
    }

    private static boolean isAliveInNextGen(int xLoc, int yLoc){
        int aliveNeighborCount = 0;
        for (int i = xLoc-1; i <xLoc+2 ; i++) {
            for (int j = yLoc-1;j < yLoc+2; j++) {
                if (isValidPoint(i, j) && grid[i][j] == 1 && !(i == xLoc && j == yLoc))
                    aliveNeighborCount++;
            }
        }

        if (grid[xLoc][yLoc] == 0) {
            if (aliveNeighborCount == 3) {
                return true;
            } else
                return false;
        }
        if (aliveNeighborCount < 2 || aliveNeighborCount > 3)
            return false;
        else
            return true;
    }

    private static boolean isValidPoint(int xLoc, int yLoc) {
        boolean isXValid = xLoc >= 0 && xLoc < x;
        boolean isYValid = yLoc >= 0 && yLoc < y;

        return isXValid && isYValid;
    }


    /* Use visualizer for a string representation of your grid */
    public static void visualizer() {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (grid[i][j] == 0)
                    System.out.print("-  ");
                else
                    System.out.print("ϛ  ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
