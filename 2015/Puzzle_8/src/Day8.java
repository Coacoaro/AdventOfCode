import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day8 {
    public static void main(String[] args) throws IOException {
        FileReader file = new FileReader("Puzzle_8/input.txt");
        BufferedReader bReader = new BufferedReader(file);
        int literal = 0;
        int memory = 0;
        int encoded = 0;
        boolean escape;

        String currentLine;
        while ((currentLine = bReader.readLine()) != null) {
            escape = false;
            char[] character = currentLine.toCharArray();

            for (char c:character) {
                literal++;

                // Part 1
                if (escape) {
                    if (c == '\\' || c == '"')
                        memory++;
                    else if (c == 'x')
                        memory--;

                    escape = false;
                } else {
                    if (c == '\\')
                        escape = true;
                    else if (c != '"')
                        memory++;
                }

                // Part 2
                if (c == '\\' || c == '"') {
                    encoded++;
                }
            }
            encoded += 2;
        }
        bReader.close();
        file.close();

        System.out.println(literal - memory);
        System.out.println(encoded);
    }
}
