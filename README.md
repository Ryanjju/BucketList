# ReadMe

## Deutsch / German

### Installation 

* **Downloads:**
  * JDK: [Java Development Kit](https://www.oracle.com/java/technologies/downloads/#javasejdk)
  * Visual Studio Code: [Visual Studio Code Download](https://code.visualstudio.com/)

* **Installation:**
  * Folgen Sie den Anweisungen des JDK-Installers.
  * In VS Code unter _Extensions_ laden Sie das Paket _Extension Pack for Java_ von Microsoft herunter, indem Sie `⇧` + `⌘` + `X` oder `STRG` + `⇧` + `X` drücken und danach suchen.

### Beschreibung des Codes

* **Importieren der Bibliotheken:**
  ```java
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.Map;
    import java.util.Scanner;
  ```
  Die Bibliotheken:
  * _ArrayList_ ist für die Listenerstellung erforderlich.
  * _HashMap_ & _Map_ ermöglichen die Erstellung einer Hash-Map zur effizienten und schnellen Ausführung der Commands.
  * _Scanner_ wird für die Eingabeüberwachung verwendet.

* **Array und Hash-Map:**
  ```java
    ArrayList<String> bucket = new ArrayList<>();
    Scanner reader = new Scanner(System.in);
  
    Map<String, Runnable> commandActions = new HashMap<>();
  ```
  Das Programm verwendet Arrays zur Speicherung der Benutzereingaben. Diese Eingaben werden als Strings gespeichert. Das Array-Objekt _bucket_ speichert alle Eingaben des Benutzers, die als Tasks ausgegeben werden können. 
  Die Hash-Map ermöglicht die schnelle Zuordnung von Werten und verknüpften Aktionen, im Gegensatz zu linearen Suchsystemen, die bei vielen Objekten ineffizient sein können.

* **While-Schleife zur stetigen Abfrage von Commands:**
  ```java
    while (true) {
      System.out.print("Input: ");
      String input = reader.nextLine();
      if (commandActions.containsKey(input)) {  
        commandActions.get(input).run();        
      } else {
        System.out.println("Invalid command! Type 'help' for valid commands.");
      }
    }
  ```
  Bei jeder Ausführung der Schleife wird die Eingabe eines Commands erwartet. Das Programm überprüft, ob der eingegebene Command in der zuvor angelegten Hash-Map vorhanden ist. Andernfalls wird eine Fehlermeldung ausgegeben.

* **quit():**
  ```java
    commandActions.put("quit()", () -> {
      while (true) {
        System.out.print("Do you really want to quit? (y|n) ");
        String ask = reader.nextLine();
        if (ask.equalsIgnoreCase("y")) {
          System.out.println("Exit with Exitcode 0");
          reader.close(); 
          System.exit(0);
        }

        if (ask.equalsIgnoreCase("n")) {
          System.out.println("Okay, you won't quit!");
          break;
        }
      }
    });
  ```
  Die Quit-Funktion verwendet eine _While-Loop_, um eine Bestätigung für das Beenden des Programms vom Benutzer zu erhalten. Abhängig von der Eingabe des Benutzers wird das Programm beendet oder fortgesetzt.

* **help:**
  ```java
    commandActions.put("help", () -> {
      System.out.println("Valid Commands are: ");
      for (String key : commandActions.keySet()) {
        System.out.println("   * " + key);          
      }
    });
  ```
  Die help-Funktion gibt die verfügbaren Befehle aus. Dies wird dynamisch durch die Abfrage der in der Hash-Map gespeicherten Befehle ermöglicht.

* **add:**
  ```java
    commandActions.put("add", () -> {
      Functions read = new Functions();
      read.addElement(bucket);  
    });
  ```
  Der Add-Command fügt Elemente zur Bucket-List hinzu. Die zugehörige Funktion befindet sich in der Klasse [Functions.java](/Project/Functions.java).

  ```java
    public void addElement(ArrayList<String> bucket) {
      System.out.println("\n\nTo quit, type 'break'.\n\n--- Add Items ---");
      Scanner reader = new Scanner(System.in);           
      while (true) { 
        System.out.print("Add: ");
        String input = reader.nextLine();
        if (input.equals("break")) {
          break;
        }

        bucket.add(input);                             
      }
      System.out.println("\n---- Done! ----\n");
    }
  ```
  Bei Aufruf dieser Funktion wird das String-Array übergeben. Der Benutzer wird aufgefordert, Elemente hinzuzufügen, bis er "break" eingibt.

* **show:**
  ```java
    commandActions.put("show", () -> {
      System.out.println("\n\n---- Bucket List ----");
      for (String item : bucket) {
        System.out.println("   * " + item); 
      }
      System.out.println("\n---- Done! ----\n");
    });
  ```
  Die Show-Funktion gibt alle Elemente in der Bucket-Liste aus.


* **delete:**
  ```java
    commandActions.put("delete", () -> {
      Functions delete = new Functions();
      delete.deleteElement(bucket);
    });
  ```
  Der `delete`-Befehl ermöglicht es dem Benutzer, spezifische Elemente aus der Bucket-Liste zu entfernen. Die entsprechende Funktion für das Löschen befindet sich ebenfalls in der Klasse [Functions.java](/Project/Functions.java).

  ```java
    public void deleteElement(ArrayList<String> bucket) {
      System.out.println("\n\nTo exit, type 'break'.\n\n--- Delete Items ---");
      Scanner reader = new Scanner(System.in);
      while (true) {
        System.out.println("Current items in the list:");
        for (int i = 0; i < bucket.size(); i++) {
          System.out.println("   " + i + ". " + bucket.get(i));
        }
        System.out.print("Enter the index of the item to delete (or 'break' to exit): ");
        String input = reader.nextLine();
        if (input.equals("break")) {
          break;
        }
        try {
          int index = Integer.parseInt(input);
          if (index >= 0 && index < bucket.size()) {
            bucket.remove(index);
            System.out.println("Item removed successfully!");
          } else {
            System.out.println("Invalid index. Please enter a valid index.");
          }
        } catch (NumberFormatException e) {
          System.out.println("Invalid input. Please enter a valid index.");
        }
      }
      System.out.println("\n---- Done! ----\n");
    }
  ```
  Diese Funktion bietet dem Benutzer die Möglichkeit, durch Eingabe des Indexes ein Element aus der Liste zu löschen. Bei Eingabe eines ungültigen Indexes oder des Befehls "break" wird die Funktion beendet.
