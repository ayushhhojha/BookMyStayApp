/**
 * Book My Stay App
 * Use Case 3: Centralized Room Inventory Management
 *
 * Demonstrates the use of HashMap for managing room availability
 * as a single source of truth.
 *
 * @author Ayush
 * @version 3.1
 */

import java.util.HashMap;
import java.util.Map;

// Inventory Class
class RoomInventory {

    private HashMap<String, Integer> inventory;

    // Constructor - initialize inventory
    public RoomInventory() {
        inventory = new HashMap<>();

        // Adding room types and availability
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    // Get availability of a room type
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability
    public void updateAvailability(String roomType, int count) {
        if (inventory.containsKey(roomType)) {
            inventory.put(roomType, count);
        } else {
            System.out.println("Room type not found!");
        }
    }

    // Display all inventory
    public void displayInventory() {
        System.out.println("\n--- Current Room Inventory ---");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

// Main Class
public class UseCase3InventorySetup {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("    BOOK MY STAY - VERSION 3.1         ");
        System.out.println("=======================================");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Display initial inventory
        inventory.displayInventory();

        // Example: Check availability
        System.out.println("\nChecking availability of Single Room:");
        System.out.println("Available: " + inventory.getAvailability("Single Room"));

        // Example: Update availability
        System.out.println("\nUpdating availability...");
        inventory.updateAvailability("Single Room", 4);

        // Display updated inventory
        inventory.displayInventory();

        System.out.println("\nInventory Management Completed Successfully!");
    }
}