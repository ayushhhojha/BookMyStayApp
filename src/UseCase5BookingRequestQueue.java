/**
 * Book My Stay App
 * Use Case 5: Booking Request Queue (FIFO)
 *
 * Demonstrates fair handling of booking requests using Queue.
 *
 * @author Ayush
 * @version 5.0
 */

import java.util.*;

// Reservation Class
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void displayReservation() {
        System.out.println("Guest: " + guestName + " | Room Type: " + roomType);
    }
}

// Booking Queue Class
class BookingRequestQueue {
    private Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    // Add request to queue
    public void addRequest(Reservation reservation) {
        queue.offer(reservation);
        System.out.println("Request added for: " + reservation.getGuestName());
    }

    // Display all requests
    public void displayQueue() {
        System.out.println("\n--- Booking Request Queue (FIFO) ---");

        if (queue.isEmpty()) {
            System.out.println("No pending requests.");
            return;
        }

        for (Reservation r : queue) {
            r.displayReservation();
        }
    }
}

// Main Class
public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("    BOOK MY STAY - VERSION 5.0         ");
        System.out.println("=======================================");

        // Initialize queue
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Add booking requests (FIFO order)
        bookingQueue.addRequest(new Reservation("Ayush", "Single Room"));
        bookingQueue.addRequest(new Reservation("Rahul", "Suite Room"));
        bookingQueue.addRequest(new Reservation("Priya", "Double Room"));

        // Display queue
        bookingQueue.displayQueue();

        System.out.println("\nAll requests stored in arrival order.");
        System.out.println("No allocation performed yet (FIFO maintained).");
    }
}