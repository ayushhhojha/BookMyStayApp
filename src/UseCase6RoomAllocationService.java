/**
 * Book My Stay App
 * Use Case 6: Reservation Confirmation & Room Allocation
 *
 * Demonstrates safe room allocation using Queue, HashMap, and Set.
 *
 * @author Ayush
 * @version 6.0
 */

import java.util.*;

// Reservation Class
class UC6Reservation {
    private String guestName;
    private String roomType;

    public UC6Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

// Booking Queue (FIFO)
class UseCase6BookingRequestQueue {
    private Queue<UC6Reservation> queue = new LinkedList<>();

    public void addRequest(UC6Reservation r) {
        queue.offer(r);
    }

    public UC6Reservation getNextRequest() {
        return queue.poll(); // FIFO removal
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

// Inventory Service
class InventoryService {
    private HashMap<String, Integer> inventory = new HashMap<>();

    public InventoryService() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void reduceAvailability(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }
}

// Room Allocation Service
class RoomAllocationService {

    // Prevent duplicate room IDs
    private Set<String> allocatedRoomIds = new HashSet<>();

    // Track room type → allocated IDs
    private HashMap<String, Set<String>> roomAllocations = new HashMap<>();

    // Generate unique Room ID
    private String generateRoomId(String roomType) {
        return roomType.substring(0, 2).toUpperCase()
                + "-" + UUID.randomUUID().toString().substring(0, 5);
    }

    public void processBookings(UseCase6BookingRequestQueue queue, InventoryService inventory) {

        System.out.println("\n--- Processing Booking Requests ---");

        while (!queue.isEmpty()) {

            UC6Reservation request = queue.getNextRequest();
            String roomType = request.getRoomType();

            System.out.println("\nProcessing request for: " + request.getGuestName());

            // Check availability
            if (inventory.getAvailability(roomType) > 0) {

                String roomId;

                // Ensure unique room ID
                do {
                    roomId = generateRoomId(roomType);
                } while (allocatedRoomIds.contains(roomId));

                // Store allocated ID
                allocatedRoomIds.add(roomId);

                // Map room type → IDs
                roomAllocations.putIfAbsent(roomType, new HashSet<>());
                roomAllocations.get(roomType).add(roomId);

                // Update inventory immediately
                inventory.reduceAvailability(roomType);

                System.out.println("Booking CONFIRMED");
                System.out.println("Guest: " + request.getGuestName());
                System.out.println("Room Type: " + roomType);
                System.out.println("Room ID: " + roomId);

            } else {
                System.out.println("Booking FAILED (No availability for " + roomType + ")");
            }
        }
    }
}

// Main Class
public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("    BOOK MY STAY - VERSION 6.0         ");
        System.out.println("=======================================");

        // Step 1: Create queue
        UseCase6BookingRequestQueue queue = new UseCase6BookingRequestQueue();

        queue.addRequest(new UC6Reservation("Ayush", "Single Room"));
        queue.addRequest(new UC6Reservation("Rahul", "Single Room"));
        queue.addRequest(new UC6Reservation("Priya", "Single Room")); // should fail
        queue.addRequest(new UC6Reservation("Neha", "Suite Room"));

        // Step 2: Inventory
        InventoryService inventory = new InventoryService();

        // Step 3: Allocation Service
        RoomAllocationService service = new RoomAllocationService();

        // Step 4: Process bookings
        service.processBookings(queue, inventory);

        System.out.println("\nAll bookings processed successfully!");
    }
}