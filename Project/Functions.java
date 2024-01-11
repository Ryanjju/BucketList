import java.util.ArrayList;
import java.util.Scanner;

public class Functions {

    public void addElement(ArrayList<String> bucket) {
        System.out.println("\n\nIf you want to quit just type 'break'\n\n--- Add Items ---");
        Scanner reader = new Scanner(System.in);            
        while (true) { 
            System.out.print("Add: ");
            String input = reader.nextLine();
            if (input.equals("break")) {
                break;
            }
            bucket.add(input);                              // Add input to Array
        }
        System.out.println("\n---- Done! ----\n");

        // DO NOT CLOSE THE 'reader'!!! Else it woun't run on main.java too
    }

    public void deleteElement(ArrayList<String> bucket) {
        System.out.println("\n\nIf you want to quit just type 'break'\n\n--- Delete Items ---");
        Scanner reader = new Scanner(System.in);

        System.out.println("\nType in the number of the Item you want to delete\n\n---- Bucket List ----");
        for (int n = 0; n < bucket.size(); n++) {
            System.out.println("  (" + n + ") " + bucket.get(n));
        }
        System.err.println("\n");

        System.out.print("Delete: ");
        int input = reader.nextInt();
        reader.nextLine();  // Consume the newline character

        try {
            bucket.remove(input);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("This is not a valid index!");
        }
        System.out.println("\n---- Done! ----\n");

        // Same thing there DON'T close it!!!
    }

    public Functions() {
    }
}
