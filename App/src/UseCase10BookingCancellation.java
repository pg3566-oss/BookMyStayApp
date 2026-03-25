import java.util.*;

// Class 1: Booking (Data Model)
class Booking {
    String bookingId;
    String roomType;
    String roomId;
    boolean isCancelled;

    public Booking(String bookingId, String roomType, String roomId) {
        this.bookingId = bookingId;
        this.roomType = roomType;
        this.roomId = roomId;
        this.isCancelled = false;
    }
}

// Class 2: Main Logic Class
public class UseCase10BookingCancellation {

    static Map<String, Integer> inventory = new HashMap<>();
    static Map<String, Stack<String>> roomPool = new HashMap<>();
    static Map<String, Booking> bookings = new HashMap<>();
    static Stack<String> rollbackStack = new Stack<>();

    // Initialize inventory
    static void initialize() {
        inventory.put("Deluxe", 2);
        inventory.put("Suite", 1);

        Stack<String> deluxe = new Stack<>();
        deluxe.push("D101");
        deluxe.push("D102");

        Stack<String> suite = new Stack<>();
        suite.push("S201");

        roomPool.put("Deluxe", deluxe);
        roomPool.put("Suite", suite);
    }

    // Book room
    static void bookRoom(String bookingId, String roomType) {
        if (!inventory.containsKey(roomType) || inventory.get(roomType) == 0) {
            System.out.println("No rooms available.");
            return;
        }

        String roomId = roomPool.get(roomType).pop();
        inventory.put(roomType, inventory.get(roomType) - 1);

        Booking b = new Booking(bookingId, roomType, roomId);
        bookings.put(bookingId, b);

        System.out.println("Booked: " + bookingId + " -> " + roomId);
    }

    // Cancel booking (core logic)
    static void cancelBooking(String bookingId) {

        // Validation
        if (!bookings.containsKey(bookingId)) {
            System.out.println("Invalid Booking ID");
            return;
        }

        Booking b = bookings.get(bookingId);

        if (b.isCancelled) {
            System.out.println("Already Cancelled");
            return;
        }

        // Rollback steps (STRICT ORDER)
        rollbackStack.push(b.roomId); // Step 1
        inventory.put(b.roomType, inventory.get(b.roomType) + 1); // Step 2
        roomPool.get(b.roomType).push(b.roomId); // Step 3
        b.isCancelled = true; // Step 4

        System.out.println("Cancelled Booking: " + bookingId);
    }

    // Display system state
    static void display() {
        System.out.println("\nInventory: " + inventory);
        System.out.println("Rollback Stack: " + rollbackStack);
    }

    public static void main(String[] args) {

        initialize();

        Scanner sc = new Scanner(System.in);

        // Sample bookings
        bookRoom("B1", "Deluxe");
        bookRoom("B2", "Suite");

        display();

        System.out.print("\nEnter Booking ID to cancel: ");
        String id = sc.nextLine();

        cancelBooking(id);

        display();

        sc.close();
    }
}