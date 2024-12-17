import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Historian_Hysteria1 {
    public static void main(String[] args) {
        try {
            // Read input from file
            List<String> lines = Files.readAllLines(Paths.get("input.txt"));

            int[] leftList = new int[lines.size()];
            int[] rightList = new int[lines.size()];

            // Parse input into two lists
            for (int i = 0; i < lines.size(); i++) {
                String[] parts = lines.get(i).trim().split("\\s+");
                leftList[i] = Integer.parseInt(parts[0]);
                rightList[i] = Integer.parseInt(parts[1]);
            }

            // Sort both lists
            Arrays.sort(leftList);
            Arrays.sort(rightList);

            // Calculate total distance
            int totalDistance = 0;
            for (int i = 0; i < lines.size(); i++) {
                totalDistance += Math.abs(leftList[i] - rightList[i]);
            }

            // Output result
            System.out.println("Total Distance: " + totalDistance);

        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
        }
    }
}
