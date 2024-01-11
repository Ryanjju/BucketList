import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> bucket = new ArrayList<>();
        Scanner reader = new Scanner(System.in);

        /*
         * I've choosen the Hash-Map systhem because a If-Condition for every comment
         * would get
         * slow. And it would be a liniar system. So verry inefficent. Now we can create
         * a Hash-key
         * to finde the necessary action for the given command.
         */
        Map<String, Runnable> commandActions = new HashMap<>();

        /* quit the Programm */
        commandActions.put("quit()", () -> {
            while (true) {
                System.out.print("Do you really want to quit? (y|n) ");
                String ask = reader.nextLine();
                if (ask.equalsIgnoreCase("y")) {
                    System.out.println("Exit with Exitcode 0");
                    reader.close(); // close input reader
                    System.exit(0);
                }
                if (ask.equalsIgnoreCase("n")) {
                    System.out.println("Okay you won't quit!");
                    break;
                }
            }
        });

        /* print the help */
        commandActions.put("help", () -> {
            System.out.println("Valid Commands are: ");
            for (String key : commandActions.keySet()) {
                System.out.println("   * " + key);          // just print all keys (better than writing
                                                            // all Commands in a print.)
            }
        });

        /* Add an Element to the list */
        commandActions.put("add", () -> {
            Functions read = new Functions();
            read.addElement(bucket);                        // Only add the new Elements to the List
        });

        /* Show the List */
        commandActions.put("show", () -> {
            System.out.println("\n\n---- Bucket List ----");
            for (int i = 0; i < bucket.size(); i++) {
                System.out.println("   * " + bucket.get(i));    // for each Elemt in the List 
            }
            System.out.println("\n---- Done! ----\n");
        });

        /* Delete an Item from the List */
        commandActions.put("delete", () -> {
            Functions delete = new Functions();
            delete.deleteElement(bucket);           // Directly remove the input from the Array at the 
                                                    // main.java file
        });

        /* This is the Input, so the command line, if you want to say so */
        while (true) {
            System.out.print("Input: ");
            String input = reader.nextLine();
            if (commandActions.containsKey(input)) {    // Hash request
                commandActions.get(input).run();        // if found run the requested action
            } else {
                System.out.println("Invalid command! Type 'help' for valid commands.");
            }
        }
    }
}
