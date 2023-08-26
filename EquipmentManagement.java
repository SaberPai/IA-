import java.io.*;
import java.util.ArrayList;
import java.util.Date;
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
                    def.ViewStorage();
                    borrowEquipment(input);
                    break;

                case 2:
                    System.out.println("Enter the name of the equipment you want to return:");
                    String equipmentName = input.nextLine();
            
                    int rowIndex = -1;
                    int colIndex = -1;
            
                    // Find the equipment in the equipmentSpec array
                    for (int rows = 0; rows < equipmentSpec.length; rows++) {
                        for (int cols = 0; cols < equipmentSpec[rows].length; cols++) {
                            if (equipmentSpec[rows][cols].equalsIgnoreCase(equipmentName)) {
                                rowIndex = rows;
                                colIndex = cols;
                                break;
                            }
                        }
                    }
            
                    if (rowIndex != -1 && colIndex != -1) {
                        System.out.println("Equipment returned. Thank you!");
            
                        // Update equipment quantity
                        equipmentQuantity[rowIndex][colIndex]++;
            
                        // Remove the due date information (if stored in a separate data structure)
            
                    } else {
                        System.out.println("Equipment not found.");
                    }
                    break;

                case 3:
                    addOrMarkEquipment(input);
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
        System.out.println("Enter the name of the equipment you want to borrow:");
        String equipmentName = input.nextLine();

        int rowIndex = -1;
        int colIndex = -1;

        // Find the equipment in the equipmentSpec array
         for (int rows = 0; rows < equipmentSpec.length; rows++) {
            for (int cols = 0; cols < equipmentSpec[rows].length; cols++) {
                if (equipmentSpec[rows][cols].equalsIgnoreCase(equipmentName)) {
                    rowIndex = rows;
                    colIndex = cols;
                    break;
                }
            }
        }

        if (rowIndex != -1 && colIndex != -1) {
            if (equipmentQuantity[rowIndex][colIndex] > 0) {
                System.out.println("Equipment available. Borrowing...");
                equipmentQuantity[rowIndex][colIndex]--;

                // Calculate due date (e.g., 7 days from today)
                long currentTime = System.currentTimeMillis();
                long dueDate = currentTime + 7 * 24 * 60 * 60 * 1000; 

                System.out.println("Due date for returning: " + new Date(dueDate));

            } else {
                System.out.println("Equipment not available at the moment.");
            }
        } else {
            System.out.println("Equipment not found.");
        }
        }

    private static void checkMissingEquipment() {
        // Check for missing equipment and send reminders
    }

    private static void addOrMarkEquipment(Scanner input) {
        System.out.println("Do you want to add new equipment or mark existing equipment as broken? (add/broken)");
        String choice = input.nextLine().toLowerCase();
    
        if (choice.equals("add")) {
            System.out.println("Enter the name of the new equipment:");
            String newEquipment = input.nextLine();
    
            System.out.println("Enter the quantity of the new equipment:");
            int newQuantity = input.nextInt();
            input.nextLine(); // Consume newline
    
            // Find an empty spot in the equipment matrix
            int rowIndex = -1;
            int colIndex = -1;
            for (int rows = 0; rows < equipmentQuantity.length; rows++) {
                for (int cols = 0; cols < equipmentQuantity[rows].length; cols++) {
                    if (equipmentQuantity[rows][cols] == 0) {
                        rowIndex = rows;
                        colIndex = cols;
                        break;
                    }
                }
            }
    
            if (rowIndex != -1 && colIndex != -1) {
                equipmentSpec[rowIndex][colIndex] = newEquipment;
                equipmentQuantity[rowIndex][colIndex] = newQuantity;
                System.out.println("New equipment added successfully.");
            } else {
                System.out.println("No space available for new equipment.");
            }
    
        } else if (choice.equals("broken")) {
            System.out.println("Enter the name of the equipment to mark as broken:");
            String brokenEquipment = input.nextLine();
    
            int rowIndex = -1;
            int colIndex = -1;
    
            // Find the equipment in the equipmentSpec array
            for (int rows = 0; rows < equipmentSpec.length; rows++) {
                for (int cols = 0; cols < equipmentSpec[rows].length; cols++) {
                    if (equipmentSpec[rows][cols].equalsIgnoreCase(brokenEquipment)) {
                        rowIndex = rows;
                        colIndex = cols;
                        break;
                    }
                }
            }
    
            if (rowIndex != -1 && colIndex != -1) {
                equipmentQuantity[rowIndex][colIndex] = 0;
                System.out.println("Equipment marked as broken and removed from availability.");
            } else {
                System.out.println("Equipment not found.");
            }
    
        } else {
            System.out.println("Invalid choice.");
        }
    }
}

