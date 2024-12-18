import java.io.*;
import java.util.*;

public class ResonantCollinearity1 {
    static class Antenna {
        int x, y;
        char freq;

        Antenna(int x, int y, char freq) {
            this.x = x;
            this.y = y;
            this.freq = freq;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("E:\\Hackathons\\Advent of Code\\Day 8\\input8.txt"));
        List<Antenna> antennas = new ArrayList<>();
        String line;
        int y = 0;

        // Parse the input
        while ((line = reader.readLine()) != null) {
            for (int x = 0; x < line.length(); x++) {
                char c = line.charAt(x);
                if (c != '.') {
                    antennas.add(new Antenna(x, y, c));
                }
            }
            y++;
        }
        reader.close();

        Set<String> antinodes = new HashSet<>();
        int n = antennas.size();

        // Find antinodes
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Antenna a1 = antennas.get(i);
                Antenna a2 = antennas.get(j);

                if (a1.freq == a2.freq) {
                    // Calculate midpoint
                    int midX = (a1.x + a2.x) / 2;
                    int midY = (a1.y + a2.y) / 2;

                    // Check if midpoint is integer
                    if ((a1.x + a2.x) % 2 == 0 && (a1.y + a2.y) % 2 == 0) {
                        antinodes.add(midX + "," + midY);
                    }

                    // Calculate antinodes beyond the antennas
                    int dx = a2.x - a1.x;
                    int dy = a2.y - a1.y;

                    // Extend antinodes on both sides
                    antinodes.add((a1.x - dx) + "," + (a1.y - dy));
                    antinodes.add((a2.x + dx) + "," + (a2.y + dy));
                }
            }
        }

        System.out.println("Total unique antinode locations: " + antinodes.size());
    }
}
