import java.io.*;

public class Day2 {
    public static void main(String[] args) throws IOException {

        FileReader file = new FileReader("Puzzle_2/input.txt");
        BufferedReader bReader = new BufferedReader(file);
        int[][] dimLWH = new int[1000][3];

        String currentLine;
        String[] splitLine;
        int i = 0;
        while ((currentLine = bReader.readLine()) != null) {
            splitLine = currentLine.split("x",0);
            int j = 0;

            for (String n : splitLine) {
                dimLWH[i][j] = Integer.parseInt(n);
                j++;
            }
            i++;
        }

        int paper = 0;
        int ribbon = 0;
        for (int[] box : dimLWH) {
            paper += getPaper(box);
            ribbon += getRibbon(box);
        }
        System.out.println(paper + " square feet of paper");
        System.out.println(ribbon + " feet of ribbon");
    }
    
    public static int getPaper(int[] box) {
        int l = box[0];         
        int w = box[1];         
        int h = box[2];
        int smallFace = Math.min(l*w, w*h);
        smallFace = Math.min(smallFace,h*l);

        return 2*(l*w + w*h + l*h) + smallFace;
    }
    
    public static int getRibbon(int[] box) {
        int l = box[0];
        int w = box[1];
        int h = box[2];

        int shortSide = Math.min(l+w, w+h);
        shortSide = Math.min(shortSide, l+h);

        return l * w * h + 2*shortSide;
    }
}
