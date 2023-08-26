import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class EquipmentManagement {
    private static int[][] equipmentMatrix = {
        {10, 4, 6, 30},
        {2, 6, 2, 2},
        {10, 2, 21, 12},
        {23, 0, 0, 12}
    };

    private static String[][] equipmentSpec = {
        {"Stapler", "Plastic Bottles (Larger)", "Glue gun", "Stationery"},
        {"Arduino box", "Scissors", "Toolbox", "Gloveboxes"},
        {"Plastic Bottles (Small)", "Plastic Bottles (Medium)", "Goggles", "Apron (Large)"},
        {"Scrap Paper", "Empty", "Empty", "Apron (Small)"}
    };

    private static int[][] equipmentQuantity = new int[equipmentMatrix.length][equipmentMatrix[0].length];

    public static class Tuple {
        private String element1;
        private int element2;

        public Tuple(String element1, int element2) {
            this.element1 = element1;
            this.element2 = element2;
        }

        @Override
        public String toString() {
            return "(" + element1 + ", " + element2 + ")";
        }
    }

    public static void main(String[] args) {
        loadEquipmentQuantities();

        Timer timer = new Timer();
        TimerTask reminderTask = new TimerTask() {
            @Override
            public void run() {
                checkMissingEquipment();
            }
        };
        timer.schedule(reminderTask, 0, 24 * 60 * 60 * 1000); // Check every 24 hours

        Scanner input = new Scanner(System.in);
        int choice;
        do {
            showMenu();
            System.out.println("What would you like to do?");
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    borrowEquipment(input);
                    break;

                case 2:
                    // Return equipment
                    break;

                case 3:
                    // Add new equipment or mark equipment as broken
                    break;

                case 4:
                    System.out.println("Thank you for using the system");
                    saveEquipmentQuantities();
                    System.exit(0);

                default:
                    System.out.println("Sorry, that is not a valid option. Please try again.");
                    break;
            }
        } while (choice > 0 && choice < 5);
    }

    private static void loadEquipmentQuantities() {
        // Load equipment quantities from a file (e.g., equipment_quantities.txt)
        // Update the equipmentQuantity array
    }

    private static void saveEquipmentQuantities() {
        // Save equipment quantities to a file (e.g., equipment_quantities.txt)
    }

    private static void showMenu() {
        System.out.println("===============================================================");
        System.out.println("  Welcome to the MakerSpace SMP (Storage Management Program)! ");
        System.out.println("===============================================================");
        System.out.println("What would you like to do? ");
        System.out.println("1. Borrow Equipment  ");
        System.out.println("2. Return Equipment ");
        System.out.println("3. View Storage");
        System.out.println("4. Quit");
    }

    private static void borrowEquipment(Scanner input) {
        // Implement the equipment borrowing functionality
    }

    private static void checkMissingEquipment() {
        // Check for missing equipment and send reminders
    }

    // Other methods for returning equipment, adding new equipment, etc.
}
