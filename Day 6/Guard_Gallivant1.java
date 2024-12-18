import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class Guard_Gallivant1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("E:\\Hackathons\\Advent of Code\\Day 6\\input6.txt"));

        // Initialize the grid and guard's position and direction
        char[][] grid = new char[10][10];
        int x = 0, y = 0, dir = 0; // 0: up, 1: right, 2: down, 3: left

        // Populate the grid from the input file
        for (int i = 0; i < 10; i++) {
            grid[i] = scanner.nextLine().toCharArray();
            for (int j = 0; j < 10; j++) {
                if (grid[i][j] == '^') {
                    x = i;
                    y = j;
                }
            }
        }

        // HashSet to track visited positions
        HashSet<String> visited = new HashSet<>();
        visited.add(x + "," + y);

        // Simulate the guard's patrol
        while (true) {
            // Check if there's an obstacle ahead
            int newX = (x + dx(dir) + 10) % 10; // Wrap around the grid
            int newY = (y + dy(dir) + 10) % 10; // Wrap around the grid
            if (grid[newX][newY] == '#') {
                // Turn right
                dir = (dir + 1) % 4;
            } else {
                // Move forward
                x = newX;
                y = newY;
                visited.add(x + "," + y);
            }
        }

        System.out.println(visited.size());
    }

    // Helper function to get the change in x and y coordinates based on direction
    private static int dx(int dir) {
        return switch (dir) {
            case 0 -> -1;
            case 1 -> 0;
            case 2 -> 1;
            case 3 -> 0;
            default -> 0;
        };
    }

    private static int dy(int dir) {
        return switch (dir) {
            case 0 -> 0;
            case 1 -> 1;
            case 2 -> 0;
            case 3 -> -1;
            default -> 0;
        };
    }
}