import java.io.*;
import java.util.*;

public class Bridge_Repair1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("E:\\Hackathons\\Advent of Code\\Day 7\\input.txt"));
        String line;
        long sum = 0;

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(":");
            long target = Long.parseLong(parts[0].trim());
            String[] numbers = parts[1].trim().split(" ");
            long[] nums = Arrays.stream(numbers).mapToLong(Long::parseLong).toArray();

            if (isPossible(nums, target)) {
                sum += target;
            }
        }
        System.out.println("Total calibration result: " + sum);
    }

    static boolean isPossible(long[] nums, long target) {
        return check(nums, target, 1, nums[0]);
    }

    static boolean check(long[] nums, long target, int idx, long current) {
        if (idx == nums.length) {
            return current == target;
        }
        return check(nums, target, idx + 1, current + nums[idx]) ||
                check(nums, target, idx + 1, current * nums[idx]);
    }
}