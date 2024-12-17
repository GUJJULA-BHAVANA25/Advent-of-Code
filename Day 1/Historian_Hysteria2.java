import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Historian_Hysteria2 {
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

            // Calculate the similarity score
            int similarityScore = 0;

            for (int leftNum : leftList) {
                int count = 0;

                // Count occurrences of leftNum in rightList
                for (int rightNum : rightList) {
                    if (leftNum == rightNum) {
                        count++;
                    }
                }

                // Update similarity score
                similarityScore += leftNum * count;
            }

            // Print the result
            System.out.println("Similarity Score: " + similarityScore);

        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
        }
    }
}
