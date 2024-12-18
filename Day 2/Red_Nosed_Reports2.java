import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Red_Nosed_Reports2 {

    public static boolean isIncreasing(int[] levels) {
        for (int i = 0; i < levels.length - 1; i++) {
            int diff = levels[i + 1] - levels[i];
            if (diff <= 0 || diff > 3) {
                return false;
            }
        }
        return true;
    }

    public static boolean isDecreasing(int[] levels) {
        for (int i = 0; i < levels.length - 1; i++) {
            int diff = levels[i] - levels[i + 1];
            if (diff <= 0 || diff > 3) {
                return false;
            }
        }
        return true;
    }

    public static boolean isSafeWithDampener(int[] levels) {
        int n = levels.length;

        for (int i = 0; i < n; i++) {
            int[] newLevels = new int[n - 1];

            for (int j = 0, k = 0; j < n; j++) {
                if (j != i) {
                    newLevels[k++] = levels[j];
                }
            }

            if (isIncreasing(newLevels) || isDecreasing(newLevels)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        try {
            // Read input from file
            List<String> lines = Files.readAllLines(Paths.get("E:\\Hackathons\\Advent of Code\\Day 2\\input1.txt"));
            int safeCount = 0;

            for (String line : lines) {
                String[] parts = line.trim().split("\\s+");
                int[] levels = new int[parts.length];

                for (int i = 0; i < parts.length; i++) {
                    levels[i] = Integer.parseInt(parts[i]);
                }

                if (isIncreasing(levels) || isDecreasing(levels) || isSafeWithDampener(levels)) {
                    safeCount++;
                }
            }

            System.out.println("Number of safe reports: " + safeCount);

        } catch (IOException e) {
            e.printStackTrace(); // Print detailed error message
        }
    }
}
