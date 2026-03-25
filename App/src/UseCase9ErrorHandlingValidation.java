import java.util.Scanner;

/**
 * ===============================================================
 * MAIN CLASS - UseCase9ErrorHandlingValidation
 * ===============================================================
 *
 * Updated Version: Accepts user input at runtime
 *
 * @version 9.1
 */

public class UseCase9ErrorHandlingValidation {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Booking Validation & Error Handling");
        System.out.println();

        // Initialize components
        RoomInventory inventory = new RoomInventory();
        BookingRequestQueue queue = new BookingRequestQueue();
        RoomAllocationService allocationService =
                new RoomAllocationService();
        BookingValidator validator = new BookingValidator();

        // Ask number of bookings
        System.out.print("Enter number of booking requests: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // consume newline

        // Take user input
        for (int i = 0; i < n; i++) {

            System.out.println("\nEnter details for booking " + (i + 1));

            System.out.print("Guest Name: ");
            String name = scanner.nextLine();

            System.out.print("Room Type (Single/Double/Suite): ");
            String roomType = scanner.nextLine();

            queue.addRequest(new Reservation(name, roomType));
        }

        System.out.println("\nProcessing Bookings...\n");

        // Process queue
        while (queue.hasPendingRequests()) {

            Reservation request = queue.getNextRequest();

            try {
                validator.validate(request, inventory);
                allocationService.allocateRoom(request, inventory);

            } catch (InvalidBookingException e) {

                System.out.println(
                        "Booking failed for Guest: "
                                + request.getGuestName()
                                + " - Reason: "
                                + e.getMessage()
                );
            }
        }

        System.out.println("\nSystem continues running safely.");

        scanner.close();
    }
}
