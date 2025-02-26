import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Day9 {
    public static void main(String[] args) throws IOException {
        BufferedReader bReader = new BufferedReader(new FileReader("Puzzle_9/input.txt"));
        ArrayList<String> cities = new ArrayList<>();
        ArrayList<Integer> distances = new ArrayList<>();

        String currentLine;
        while ((currentLine = bReader.readLine()) != null) {
            String[] splitLine = currentLine.split(" ");
            String city1 = splitLine[0];
            String city2 = splitLine[2];
            distances.add(Integer.parseInt(splitLine[4]));

            if (!cities.contains(city1))
                cities.add(city1);

            if (!cities.contains(city2))
                cities.add(city2);
        }
        bReader.close();

        int [][] m = matrixCreate(cities, distances);

        // Part 1 & 2
        int small = Integer.MAX_VALUE;
        int large = 0;
        for(int start = 0; start < m.length; start++) {
            int s = shortPath(m, start);
            int l = longPath(m, start);

            if (small > s)
                small = s;

            if (large < l)
                large = l;
        }
        System.out.println("min val. = "+small);
        System.out.println("max val. = "+large);
    }


    // Adding given values to matrix
    public static int[][] matrixCreate(ArrayList<String> vertex, ArrayList<Integer> edges) {
        int[][] m = new int[vertex.size()][vertex.size()];
        int index = 0;

        for (int c1 = 0; c1 < vertex.size(); c1++) {
            for (int c2 = c1 + 1; c2 < vertex.size(); c2++) {
                m[c1][c2] = edges.get(index);
                m[c2][c1] = m[c1][c2];  // mirrors across the main diagonal
                index++;
            }
        }
        return m;
    }


    // Finds shortest path through all nodes without revisiting
    public static int shortPath(int[][] m, int start){
        HashSet<Integer> visited = new HashSet<>();
        int min_path = 0;
        int row = start;

        while (visited.size() < m.length) {
            visited.add(row);
            int min_dist = Integer.MAX_VALUE;
            int at_idx = 0;

            for (int i = 0; i < m.length; i++){
                if (row == i || visited.contains(i)) {
                    continue;
                }

                if (min_dist > m[row][i]) {
                    min_dist = m[row][i];
                    at_idx = i;
                }
            }
            visited.add(at_idx);
            min_path += min_dist;
            row = at_idx;
        }
        return min_path;
    }

    // Finds longest path through all nodes without revisiting
    public static int longPath(int[][] m, int start){
        HashSet<Integer> visited = new HashSet<>();
        int max_path = 0;
        int row = start;

        while (visited.size() < m.length) {
            visited.add(row);
            int max_dist = 0;
            int at_idx = 0;

            for (int i = 0; i < m.length; i++){
                if (row == i || visited.contains(i)) {
                    continue;
                }

                if (max_dist < m[row][i]) {
                    max_dist = m[row][i];
                    at_idx = i;
                }
            }
            visited.add(at_idx);
            max_path += max_dist;
            row = at_idx;
        }
        return max_path;
    }

//    // Not needed but is here to play around with
//    // Finds shortest path between all node pairs
//    public static void floydWarshall(int[][] m) {
//        for (int i = 0; i < m.length; i++) {
//            for (int j = 0; j < m.length; j++) {
//                for (int k = 0; k < m.length; k++) {
//
//                    if (i == j)
//                        break;
//
//                    if (m[i][j] > m[i][k] + m[k][j])
//                        m[i][j] = m[i][k] + m[k][j];
//                }
//            }
//        }
//    }
}
