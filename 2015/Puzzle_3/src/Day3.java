import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Day3 {
    public static void main(String[] args) throws IOException {
        FileReader file = new FileReader("Puzzle_3/input.txt");
        BufferedReader bReader = new BufferedReader(file);
        String directions = bReader.readLine();

        System.out.println(Part1(directions) + " different houses");
        System.out.println(Part2(directions) + " different houses");
    }

    // Santa and Robo-Santa taking turns following directions
    public static int Part2(String directions) {
        ArrayList<String> hasPresent = new ArrayList<>();
        ArrayList<Character> santaDir = new ArrayList<>();
        ArrayList<Character> roboSantaDir = new ArrayList<>();

        // Split directions in half
        final char[] dir = directions.toCharArray();
        for (int i = 0; i < dir.length; i++) {
            if (i % 2 == 0) {
                 santaDir.add(dir[i]);
            } else {
                roboSantaDir.add(dir[i]);
            }
        }

        // Santa following directions
        x = 0;
        y = 0;
        for (char s : santaDir) {
            String house = Arrays.toString(getsPresent(s));
            if (!hasPresent.contains(house)) {
                hasPresent.add(house);
            }
        }

        // Robo-Santa following directions
        x = 0;
        y = 0;
        for (char rs : roboSantaDir) {
            String house = Arrays.toString(getsPresent(rs));
            if (!hasPresent.contains(house)) {
                hasPresent.add(house);
            }
        }
        return hasPresent.size();
    }


    // Santa following directions
    public static int Part1(String directions){
        x = 0;
        y = 0;
        // Puts houses visited in ArrayList
        ArrayList<String> hasPresent = new ArrayList<>();
        final char[] dir = directions.toCharArray();
        for (char d : dir) {
            String house = Arrays.toString(getsPresent(d));
            if (!hasPresent.contains(house)) {
                hasPresent.add(house);
            }
        }
        return hasPresent.size();
    }


    // Gets coordinates into int[]
    static int x;
    static int y;
    public static int[] getsPresent(char c) {
            switch (c) {
                case '>':
                    x++;
                    break;
                case '<':
                    x--;
                    break;
                case '^':
                    y++;
                    break;
                case 'v':
                    y--;
                    break;
            }
        return new int[]{x, y};
    }
}
