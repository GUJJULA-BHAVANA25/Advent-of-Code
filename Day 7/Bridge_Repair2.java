import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Bridge_Repair2 {

    // Method to concatenate two numbers using BigInteger
    public static BigInteger concatenate(BigInteger a, BigInteger b) {
        return new BigInteger(a.toString() + b.toString()); // Concatenate as strings and convert back to BigInteger
    }

    // Method to evaluate expressions with +, *, and || operators
    public static BigInteger evaluateExpression(List<BigInteger> numbers, List<String> operators) {
        BigInteger result = numbers.get(0); // Start with the first number
        for (int i = 1; i < numbers.size(); i++) {
            switch (operators.get(i - 1)) {
                case "+":
                    result = result.add(numbers.get(i));
                    break;
                case "*":
                    result = result.multiply(numbers.get(i));
                    break;
                case "||":
                    result = concatenate(result, numbers.get(i));
                    break;
            }
        }
        return result;
    }

    // Method to try all combinations of operators (+, *, ||) and return the sum if
    // valid
    public static BigInteger calculateCalibrationResult(List<BigInteger> numbers, BigInteger targetValue) {
        int n = numbers.size();
        BigInteger totalResult = BigInteger.ZERO;

        // Try all combinations of operators between numbers
        for (int i = 0; i < Math.pow(3, n - 1); i++) {
            List<String> operators = new ArrayList<>();
            int temp = i;
            for (int j = 0; j < n - 1; j++) {
                operators.add(getOperator(temp % 3));
                temp /= 3;
            }

            // Evaluate the expression with the current operator combination
            BigInteger result = evaluateExpression(numbers, operators);
            if (result.equals(targetValue)) {
                totalResult = totalResult.add(targetValue);
            }
        }

        return totalResult;
    }

    // Method to map a number to an operator: 0 -> "+", 1 -> "*", 2 -> "||"
    private static String getOperator(int index) {
        switch (index) {
            case 0:
                return "+";
            case 1:
                return "*";
            case 2:
                return "||";
            default:
                return "";
        }
    }

    public static void main(String[] args) throws IOException {
        // Read input from a file
        BufferedReader reader = new BufferedReader(new FileReader("E:\\Hackathons\\Advent of Code\\Day 7\\input.txt"));
        String line;
        BigInteger totalCalibrationResult = BigInteger.ZERO;

        // Process each line in the input file
        while ((line = reader.readLine()) != null) {
            // Clean the line by removing non-numeric characters (except spaces and ':')
            line = line.replaceAll("[^0-9 :]", "").trim();

            // Split the cleaned line into parts
            String[] parts = line.split(":");
            BigInteger targetValue = new BigInteger(parts[0].trim());

            // Get the list of numbers
            String[] numberStrings = parts[1].trim().split(" ");
            List<BigInteger> numbers = new ArrayList<>();
            for (String num : numberStrings) {
                numbers.add(new BigInteger(num));
            }

            // Calculate the calibration result for this set of numbers
            totalCalibrationResult = totalCalibrationResult.add(calculateCalibrationResult(numbers, targetValue));
        }

        // Close the reader
        reader.close();

        // Output the total calibration result
        System.out.println("Total Calibration Result: " + totalCalibrationResult);
    }
}
