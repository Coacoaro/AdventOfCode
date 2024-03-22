import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day6 {
    public static void main(String[] args) throws IOException {
        FileReader file = new FileReader("Puzzle_6/input.txt");
        BufferedReader bReader = new BufferedReader(file);
        ArrayList<String> input = new ArrayList<>();
        ArrayList<String> toggle = new ArrayList<>();

        String currentLine;
        while ((currentLine = bReader.readLine()) != null) {
            String[] line = currentLine.split(" ");
            input.add(line[line.length - 3]);
            input.add(line[line.length - 1]);
            toggle.add(line[line.length - 4]);
        }
        bReader.close();
        file.close();

        System.out.println(toggle);

        // Getting coordinates
        ArrayList<Integer> coor = new ArrayList<>();
        for (String str:input) {
            String[] num = String.valueOf(str).split(",");
            coor.add(Integer.parseInt(num[0]));
            coor.add(Integer.parseInt(num[1]));
        }

        // Follow instructions
        boolean[][] grid = new boolean[1000][1000];
        int[][] grid2 = new int[1000][1000];
        for(int t = 0; t < 3; t++) {
            instructions1(grid, coor.get(t*4), coor.get(t*4 + 1), coor.get(t*4 + 2), coor.get(t*4 + 3), toggle.get(t));
            instructions2(grid2, coor.get(t*4), coor.get(t*4 + 1), coor.get(t*4 + 2), coor.get(t*4 + 3), toggle.get(t));
        }

        // Count lights on
        int on = 0;
        int off = 0;
        for (boolean[] x:grid) {
            for (boolean y:x) {
                if (y)
                    on++;
                else
                    off++;
            }
        }
        System.out.println(on + " lights on");
        System.out.println(off + " lights off");

        // Count brightness
        int brightness = 0;
        for (int[] x:grid2) {
            for (int y:x) {
                brightness += y;
            }
        }

        System.out.println("Brightness is " + brightness);
    }

    // Toggle lights based on instructions pt 2
    public static void instructions2(int[][] grid2, Integer x1, Integer y1, Integer x2, Integer y2, String toggle) {
        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                switch (toggle) {
                    case "on":
                        grid2[x][y] += 1;
                        break;
                    case "off":
                        grid2[x][y] -= 1;
                        if (grid2[x][y] < 0)
                            grid2[x][y] = 0;
                        break;
                    default:
                        grid2[x][y] += 2;
                        break;
                }
            }
        }
    }

    // Toggle lights based on instructions pt 1
    public static void instructions1(boolean[][] grid, Integer x1, Integer y1, Integer x2, Integer y2, String toggle) {
        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                switch (toggle) {
                    case "on":
                        grid[x][y] = true;
                        break;
                    case "off":
                        grid[x][y] = false;
                        break;
                    default:
                        grid[x][y] = !grid[x][y];
                }
            }
        }
    }
}
