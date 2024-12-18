import java.io.*;
import java.util.*;

public class Print_Queue1 {

    public static void main(String[] args) throws IOException {
        List<String> rules = new ArrayList<>();
        List<List<Integer>> updates = new ArrayList<>();

        readInput("E:\\Hackathons\\Advent of Code\\Day 5\\input5.txt", rules, updates);
        int totalMiddlePageSum = validateUpdates(rules, updates);
        System.out.println("Total Middle Page Sum: " + totalMiddlePageSum);
    }

    private static void readInput(String fileName, List<String> rules, List<List<Integer>> updates) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean readingRules = true;

            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    readingRules = false;
                    continue;
                }
                if (readingRules) {
                    rules.add(line);
                } else {
                    List<Integer> update = new ArrayList<>();
                    for (String page : line.split(",")) {
                        update.add(Integer.parseInt(page));
                    }
                    updates.add(update);
                }
            }
        }
    }

    private static int validateUpdates(List<String> rules, List<List<Integer>> updates) {
        int totalSum = 0;
        Map<Integer, Set<Integer>> precedenceMap = buildPrecedenceMap(rules);

        for (List<Integer> update : updates) {
            if (isValidOrder(update, precedenceMap)) {
                int middlePage = update.get(update.size() / 2);
                totalSum += middlePage;
            }
        }
        return totalSum;
    }

    private static Map<Integer, Set<Integer>> buildPrecedenceMap(List<String> rules) {
        Map<Integer, Set<Integer>> precedenceMap = new HashMap<>();
        for (String rule : rules) {
            String[] pages = rule.split("\\|");
            int before = Integer.parseInt(pages[0]);
            int after = Integer.parseInt(pages[1]);

            precedenceMap.putIfAbsent(before, new HashSet<>());
            precedenceMap.get(before).add(after);
        }
        return precedenceMap;
    }

    private static boolean isValidOrder(List<Integer> update, Map<Integer, Set<Integer>> precedenceMap) {
        Map<Integer, Integer> positionMap = new HashMap<>();
        for (int i = 0; i < update.size(); i++) {
            positionMap.put(update.get(i), i);
        }

        for (Map.Entry<Integer, Set<Integer>> entry : precedenceMap.entrySet()) {
            int before = entry.getKey();
            for (int after : entry.getValue()) {
                if (positionMap.containsKey(before) && positionMap.containsKey(after)) {
                    if (positionMap.get(before) > positionMap.get(after)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}