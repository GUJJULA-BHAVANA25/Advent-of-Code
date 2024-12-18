import java.io.*;
import java.util.*;

public class Print_Queue2 {

    public static void main(String[] args) throws IOException {
        List<String> rules = new ArrayList<>();
        List<List<Integer>> updates = new ArrayList<>();

        readInput("E:\\Hackathons\\Advent of Code\\Day 5\\input5.txt", rules, updates);
        int totalMiddlePageSum = correctAndSumMiddlePages(rules, updates);
        System.out.println("Total Middle Page Sum after Correction: " + totalMiddlePageSum);
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

    private static int correctAndSumMiddlePages(List<String> rules, List<List<Integer>> updates) {
        int totalSum = 0;
        Map<Integer, Set<Integer>> precedenceMap = buildPrecedenceMap(rules);

        for (List<Integer> update : updates) {
            if (!isValidOrder(update, precedenceMap)) {
                List<Integer> correctedOrder = topologicalSort(update, precedenceMap);
                int middlePage = correctedOrder.get(correctedOrder.size() / 2);
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
        for (Map.Entry<Integer, Set<Integer>> entry : precedenceMap.entrySet()) {
            int before = entry.getKey();
            for (int after : entry.getValue()) {
                if (update.contains(before) && update.contains(after)) {
                    if (update.indexOf(before) > update.indexOf(after)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static List<Integer> topologicalSort(List<Integer> update, Map<Integer, Set<Integer>> precedenceMap) {
        Map<Integer, Integer> inDegree = new HashMap<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int page : update) {
            inDegree.put(page, 0);
            graph.put(page, new ArrayList<>());
        }

        for (Map.Entry<Integer, Set<Integer>> entry : precedenceMap.entrySet()) {
            int before = entry.getKey();
            for (int after : entry.getValue()) {
                if (update.contains(before) && update.contains(after)) {
                    graph.get(before).add(after);
                    inDegree.put(after, inDegree.get(after) + 1);
                }
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }

        List<Integer> sortedOrder = new ArrayList<>();
        while (!queue.isEmpty()) {
            int current = queue.poll();
            sortedOrder.add(current);
            for (int neighbor : graph.get(current)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }
        return sortedOrder;
    }
}