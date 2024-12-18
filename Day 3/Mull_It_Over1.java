import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mull_It_Over1 {
    public static void main(String[] args) {

        try {
            // Read input from file
            List<String> lines = Files.readAllLines(Paths.get("E:\\Hackathons\\Advent of Code\\Day 3\\input.txt"));
            int totalSum = 0;

            // Regex pattern to match valid mul(X,Y) expressions
            Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");

            for (String line : lines) {
                Matcher matcher = pattern.matcher(line);

                // Process all matches in the current line
                while (matcher.find()) {
                    int x = Integer.parseInt(matcher.group(1));
                    int y = Integer.parseInt(matcher.group(2));
                    totalSum += x * y;
                }
            }

            System.out.println("Total sum of valid multiplications: " + totalSum);

        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
        }
    }
}
