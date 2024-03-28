import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day7 {
    public static void main(String[] args) throws IOException {
        FileReader file = new FileReader("Puzzle_7/input.txt");
        BufferedReader bReader = new BufferedReader(file);
        HashMap<String, Integer> wires = new HashMap<>();
        HashMap<String[], String> instruction = new HashMap<>();

        String currentLine;
        while ((currentLine = bReader.readLine()) != null) {
            String[] splitLine = currentLine.split(" -> ");
            instruction.put(splitLine[0].split(" "), splitLine[1]);
            wires.put(splitLine[1], null);

        }
        bReader.close();
        file.close();

        while (wires.containsValue(null)) {
            provideSignal(wires, instruction);
        }
        System.out.println(wires);
    }

    static void provideSignal(HashMap<String, Integer> wires, HashMap<String[], String> instruction) {
        for (Map.Entry<String, Integer> entryW : wires.entrySet()) {
            // wires.forEach((wire, signal) -> {...
            String wire = entryW.getKey();
            Integer signal = entryW.getValue();

            // Skips signals we already have
            if (signal != null)
                continue;

            for (Map.Entry<String[], String> entryI : instruction.entrySet()) {
                // Gets operation for wire in wires
                if (Objects.equals(wire, entryI.getValue())) {
                    Integer newSignal = operate(entryI, entryW, wires);
                    if (newSignal != null)
                        wires.replace(wire, newSignal);
                    break;
                }
            }
        }
    }

    // Handle operations
    static Integer operate(Map.Entry<String[], String> entryI, Map.Entry<String, Integer> entryW, HashMap<String, Integer> wires) {
        String w1 = entryI.getKey()[entryI.getKey().length - 1];
        Integer s1;
        Integer signal = null;

        if (StringIsNum(w1))
            s1 = Integer.parseInt(w1);
        else
            s1 = wires.get(w1);

        if (s1 != null) {
            switch (entryI.getKey().length) {
                case 1:
                    signal = s1;
                    break;
                case 2:
                    signal = NOT(s1);
                    break;
                case 3:
                    String w2 = entryI.getKey()[0];
                    String operation = entryI.getKey()[1];
                    Integer s2;

                    if (StringIsNum(w2))
                        s2 = Integer.parseInt(w2);
                    else
                        s2 = wires.get(w2);

                    if (s2 != null)
                        signal = Case3(s2, s1, operation);
            }
        }
        return signal;
    }

    // Check if string is a number
    static boolean StringIsNum(String str) {
        boolean isDigit = true;

        // Check if signal is a number
        for (char c:str.toCharArray()) {
            if (!Character.isDigit(c)) {
                isDigit = false;
                break;
            }
        }
        return isDigit;
    }

    static int Case3(int s1, int s2, String operator) {
        int signal = 0;
        switch (operator) {
            case "OR":
                signal = OR(s1, s2);
                break;
            case "AND":
                signal = AND(s1, s2);
                break;
            case "LSHIFT":
                signal = LSHIFT(s1, s2);
                break;
            case "RSHIFT":
                signal = RSHIFT(s1, s2);
        }
        return signal;
    }

    // Operations
    static int AND(int x, int y) {
        return x & y;
    }
    static int OR(int x, int y) {
        return x | y;
    }
    static int NOT(int x) {
      return ~x + 65536;
    }
    static int RSHIFT(int x, int shift) {
        return x >> shift;
    }
    static int LSHIFT(int x, int shift) {
        int newInt = x << shift;
        if (newInt > 65535) {
            int factor = newInt>>16;
            newInt -= factor*65536;
        }
        return newInt;
    }
}
