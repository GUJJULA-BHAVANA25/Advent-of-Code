import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mull_It_Over2 {
    public static void main(String[] args) {
        String filePath = "E:\\Hackathons\\Advent of Code\\Day 3\\input.txt"; // Path to the input file
        String memory = readFile(filePath);

        if (memory != null) {
            int result = calculateEnabledMultiplications(memory);
            System.out.println("The sum of the results of enabled multiplications is: " + result);
        } else {
            System.out.println("Error reading the file.");
        }
    }

    public static String readFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append(" "); // Append each line to the content
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Return null if there's an error
        }
        return content.toString().trim(); // Return the complete content as a single string
    }

    public static int calculateEnabledMultiplications(String memory) {
        // Regular expression to match valid mul instructions
        Pattern mulPattern = Pattern.compile("mul\\s*\\(\\s*(\\d{1,3})\\s*,\\s*(\\d{1,3})\\s*\\)");
        // Flags to track if multiplications are enabled or disabled
        boolean isEnabled = true;
        int sum = 0;

        // Split the input string into tokens based on the instructions
        String[] tokens = memory.split("(?<=\\))|(?=\\()|(?<=\\()|(?=\\))|(?=\\s|\\W)");

        for (String token : tokens) {
            token = token.trim(); // Clean up whitespace

            // Check for do() and don't() instructions
            if (token.equals("do()")) {
                isEnabled = true; // Enable multiplications
            } else if (token.equals("don't()")) {
                isEnabled = false; // Disable multiplications
            } else if (isEnabled) {
                // Match and process valid mul instructions
                Matcher matcher = mulPattern.matcher(token);
                while (matcher.find()) {
                    int x = Integer.parseInt(matcher.group(1));
                    int y = Integer.parseInt(matcher.group(2));
                    sum += x * y; // Add the result of the multiplication
                }
            }
        }

        return sum; // Return the total sum of enabled multiplications
    }
}