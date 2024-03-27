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
            initWires(wires, currentLine);
        }
        bReader.close();
        file.close();

        // Operation
//        HashSet<String> operations = new HashSet<>();
//        instruction.forEach((dog, key) -> {
//            if (dog.length != 1)
//                operations.add(dog[dog.length-2]);
////            else
////                operations.add(dog[0]);
//        } );

//        for (Map.Entry<String, Integer> entry : wire.entrySet()) {
//            if (entry.getValue() != null) {
//                System.out.println(entry.getKey());
//            }
//            if (Objects.equals(entry.getKey(), "a")) {
//                System.out.println(entry);
//            }
//        };

        for (Map.Entry<String, Integer> entryW : wires.entrySet()) {
            String wire = entryW.getKey();
            Integer signal = entryW.getValue();

            if (signal != null) {
//                System.out.println(entryW);
                continue;
            }

            for (Map.Entry<String[], String> entryI : instruction.entrySet()) {
                // instruction.forEach((logic, wire) -> {...
                String[] logic = entryI.getKey();
                String assWire = entryI.getValue();

                // Gets operation for wire in wires
                if (Objects.equals(entryW.getKey(), assWire)) {
//                    operate(entryW, logic, assWire);
                    break;
                }
            }
        }
        int x = 123;
        int y = 456;
        System.out.println(AND(x, y));
        System.out.println(OR(x, y));
        System.out.println(NOT(x));
        System.out.println(NOT(y));
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

    // Handle operations
    static void operate(Map.Entry<String, Integer> entryW, String[] logic, String assWire) {
        String w1 = logic[0];
        String w2;
        String operation;

        switch (logic.length) {
            case 1:
                System.out.println(w1 + " -> " + assWire);
                break;
            case 2:
                w1 = logic[1];
                System.out.println("~" + w1 + " -> " + assWire);
                break;
            case 3:
                operation = logic[logic.length - 2];
                w2 = logic[2];
                switch (operation) {
                    case "OR":
                        System.out.println(w1 + "|" + w2 + " -> " + assWire);
                        break;
                    case "AND":
                        System.out.println(w1 + "&" + w2 + " -> " + assWire);
                        break;
                    case "LSHIFT":
                        System.out.println(w1 + "<<" + w2 + " -> " + assWire);
                        break;
                    case "RSHIFT":
                        System.out.println(w1 + ">>" + w2 + " -> " + assWire);
                        break;
            }
        }
    }

    // Operations
    static Integer AND(Integer x, Integer y) {
        return x & y;
    }
    static Integer OR(Integer x, Integer y) {
        return x | y;
    }
    static Integer NOT(Integer x) {
      return ~x + 65536;
    }
//    static Integer LSHIFT(Integer x, Integer y) {
//    }
//
//    static Integer RSHIFT(Integer x, Integer y) {
//    }
}
