import java.io.*;
import java.util.*;

public class Ceres_Search1 {

    private static final String TARGET = "XMAS";
    private static final int[] DX = { 0, 1, 1, 1, 0, -1, -1, -1 };
    private static final int[] DY = { 1, 1, 0, -1, -1, -1, 0, 1 };

    public static void main(String[] args) throws IOException {
        List<String> grid = readFile("E:\\Hackathons\\Advent of Code\\Day 4\\input4.txt");
        int count = countOccurrences(grid);
        System.out.println("Total occurrences of XMAS: " + count);
    }

    private static List<String> readFile(String fileName) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    private static int countOccurrences(List<String> grid) {
        int rows = grid.size();
        int cols = grid.get(0).length();
        int count = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int dir = 0; dir < 8; dir++) {
                    if (checkWord(grid, i, j, dir, rows, cols)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private static boolean checkWord(List<String> grid, int x, int y, int dir, int rows, int cols) {
        for (int k = 0; k < TARGET.length(); k++) {
            int nx = x + k * DX[dir];
            int ny = y + k * DY[dir];

            if (nx < 0 || ny < 0 || nx >= rows || ny >= cols || grid.get(nx).charAt(ny) != TARGET.charAt(k)) {
                return false;
            }
        }
        return true;
    }
}