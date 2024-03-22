import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Day7 {
    public static void main(String[] args) throws IOException {
        FileReader file = new FileReader("Puzzle_7/input.txt");
        BufferedReader bReader = new BufferedReader(file);
        HashMap<String, Integer> wire = new HashMap<>();
        HashSet<String> instruction = new HashSet<>();  // maybe use ArrayList

        String currentLine;
        while ((currentLine = bReader.readLine()) != null) {
            instruction.add(currentLine);
            initWires(wire, currentLine);
        }
        bReader.close();
        file.close();

        System.out.println(wire);
        System.out.println(instruction);
    }

    // Assign signals to wires
    static void initWires(HashMap<String, Integer> wire, String line) {
        String[] split = line.split(" -> ");
        boolean isDigit = true;

        // Check if signal is a number
        for (char c:split[0].toCharArray()) {
            if (!Character.isDigit(c)) {
                isDigit = false;
                break;
            }
        }

        // Assign values to wire
        if (isDigit){
            wire.put(split[1], Integer.parseInt(split[0]));
        } else
            wire.put(split[1], null);
    }
}
