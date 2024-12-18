import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class q {
    public static int processMemory(String memory) {
        int totalSum = 0;
        boolean mulEnabled = true;

        for (int i = 0; i < memory.length(); i++) {
            if (memory.startsWith("mul", i)) {
                // Reset mulEnabled for each mul instruction
                mulEnabled = true;
                i += 3;
                int operand1 = 0;
                while (i < memory.length() && Character.isDigit(memory.charAt(i))) {
                    operand1 = operand1 * 10 + (memory.charAt(i) - '0');
                    i++;
                }
                i++;
                int operand2 = 0;
                while (i < memory.length() && Character.isDigit(memory.charAt(i))) {
                    operand2 = operand2 * 10 + (memory.charAt(i) - '0');
                    i++;
                }
                if (mulEnabled) {
                    totalSum += operand1 * operand2;
                }
            } else if (memory.startsWith("don't", i)) {
                mulEnabled = false;
                i += 5;
            } else if (memory.startsWith("do", i)) {
                mulEnabled = true;
                i += 2;
            }
        }

        return totalSum;
    }

    public static void main(String[] args) {
        try {
            File inputFile = new File("E:\\Hackathons\\Advent of Code\\Day 3\\input.txt"); // Replace with your actual
                                                                                           // file name
            Scanner scanner = new Scanner(inputFile);
            String inputMemory = scanner.nextLine();
            scanner.close();

            int result = processMemory(inputMemory);
            System.out.println(result);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }
}