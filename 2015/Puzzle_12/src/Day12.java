import java.io.*;
import static java.lang.Character.isDigit;

public class Day12 {
    public static void main(String[] args) throws IOException{
        char[] input = new BufferedReader(new FileReader("Puzzle_12/input.txt")).readLine().toCharArray();
        System.out.println("sum is = "+sumOfNums(input));   // Part 1
    }

    public static int sumOfNums(char[] input) {
        int sum = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length - 1; i++) {
            sb.setLength(0);
            while (isDigit(input[i + 1])) {
                if (input[i] == '-')
                    sb.append('-');

                sb.append(input[i + 1]);
                i++;
            }

            if (isDigit(input[i]))
                sum += Integer.parseInt(sb.toString());
        }
        return sum;
    }
}
