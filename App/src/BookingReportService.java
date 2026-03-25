import java.util.List;

/**
 * ===============================================================
 * CLASS - BookingReportService
 * ===============================================================
 *
 * Use Case 8: Booking History & Reporting
 *
 * Description:
 * This class generates reports
 * from booking history data.
 *
 * Reporting logic is separated
 * from storage logic.
 *
 * @version 8.0
 */

public class BookingReportService {

    /**
     * Displays all booking records.
     *
     * @param history booking history
     */
    public void displayAllBookings(BookingHistory history) {

        List<Reservation> reservations = history.getAllReservations();

        System.out.println("Booking History Report");
        System.out.println();

        for (Reservation r : reservations) {

            System.out.println(
                    "Guest: " + r.getGuestName()
                            + ", Room Type: " + r.getRoomType()
            );
        }
    }

    /**
     * Displays total number of bookings.
     *
     * @param history booking history
     */
    public void displaySummary(BookingHistory history) {

        int total = history.getAllReservations().size();

        System.out.println();
        System.out.println("Total Bookings: " + total);
    }
}
