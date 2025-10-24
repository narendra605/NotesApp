import java.io.*;
import java.util.Scanner;

public class NotesApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filePath = "notes.txt";  // File to store notes
        int choice;

        try {
            while (true) {
                System.out.println("\n=== Notes Manager ===");
                System.out.println("1. Write a new note");
                System.out.println("2. View all notes");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        writeNoteToFile(scanner, filePath);
                        break;
                    case 2:
                        readNotesFromFile(filePath);
                        break;
                    case 3:
                        System.out.println("Goodbye! Have a great day! 📝");
                        return; // exit main
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            }
        } finally {
            scanner.close(); // ✅ close scanner before exiting program
        }
    }

    // Method to write a note to file
    public static void writeNoteToFile(Scanner scanner, String filePath) {
        // try-with-resources automatically closes FileWriter, BufferedWriter, PrintWriter
        try (FileWriter fw = new FileWriter(filePath, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            System.out.print("Enter your note: ");
            String note = scanner.nextLine();
            out.println(note);
            System.out.println("Note saved successfully! ✅");

        } catch (IOException e) {
            System.out.println("Error while writing to file: " + e.getMessage());
        }
    }

    // Method to read all notes
    public static void readNotesFromFile(String filePath) {
        // try-with-resources automatically closes FileReader, BufferedReader
        try (FileReader fr = new FileReader(filePath);
             BufferedReader br = new BufferedReader(fr)) {

            System.out.println("\n--- Your Notes ---");
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("- " + line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No notes found yet! Try adding one first.");
        } catch (IOException e) {
            System.out.println("Error while reading file: " + e.getMessage());
        }
    }
}
