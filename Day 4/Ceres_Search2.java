import java.io.*;
import java.util.*;

public class Ceres_Search2 {

    private static final char[][][] PATTERNS = {
            { { 'M', '.', 'S' }, { '.', 'A', '.' }, { 'M', '.', 'S' } },
            { { 'S', '.', 'M' }, { '.', 'A', '.' }, { 'S', '.', 'M' } },
            { { 'M', '.', 'M' }, { '.', 'A', '.' }, { 'S', '.', 'S' } },
            { { 'S', '.', 'S' }, { '.', 'A', '.' }, { 'M', '.', 'M' } }
    };

    public static void main(String[] args) throws IOException {
        List<String> grid = readFile("E:\\Hackathons\\Advent of Code\\Day 4\\input4.txt");
        int count = countXMasOccurrences(grid);
        System.out.println("Total occurrences of X-MAS: " + count);
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

    private static int countXMasOccurrences(List<String> grid) {
        int rows = grid.size();
        int cols = grid.get(0).length();
        int count = 0;

        for (int i = 0; i <= rows - 3; i++) {
            for (int j = 0; j <= cols - 3; j++) {
                for (char[][] pattern : PATTERNS) {
                    if (checkPattern(grid, i, j, pattern)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private static boolean checkPattern(List<String> grid, int x, int y, char[][] pattern) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (pattern[i][j] != '.' && grid.get(x + i).charAt(y + j) != pattern[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}