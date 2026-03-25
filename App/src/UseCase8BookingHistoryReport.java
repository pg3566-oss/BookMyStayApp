/**
 * ===============================================================
 * MAIN CLASS - UseCase8BookingHistoryReport
 * ===============================================================
 *
 * Use Case 8: Booking History & Reporting
 *
 * Description:
 * This class demonstrates how confirmed
 * bookings are stored and reported.
 *
 * @version 8.0
 */

public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        System.out.println("Booking History & Reporting");
        System.out.println();

        // Initialize components
        RoomInventory inventory = new RoomInventory();
        BookingRequestQueue queue = new BookingRequestQueue();
        RoomAllocationService allocationService =
                new RoomAllocationService();
        BookingHistory history = new BookingHistory();
        BookingReportService reportService =
                new BookingReportService();

        // Add booking requests
        queue.addRequest(new Reservation("Abhi", "Single"));
        queue.addRequest(new Reservation("Subha", "Double"));
        queue.addRequest(new Reservation("Vanmathi", "Suite"));

        // Process bookings
        while (queue.hasPendingRequests()) {

            Reservation request = queue.getNextRequest();

            // Allocate room
            allocationService.allocateRoom(request, inventory);

            // Store confirmed booking in history
            history.addReservation(request);
        }

        // Generate report
        reportService.displayAllBookings(history);
        reportService.displaySummary(history);
    }
}
