import java.util.ArrayList;
import java.util.List;

/**
 * ===============================================================
 * CLASS - BookingHistory
 * ===============================================================
 *
 * Use Case 8: Booking History & Reporting
 *
 * Description:
 * This class stores all confirmed reservations
 * in the order they are processed.
 *
 * It acts as an in-memory historical record
 * of bookings.
 *
 * @version 8.0
 */

public class BookingHistory {

    /** List to store confirmed reservations */
    private List<Reservation> history;

    /** Constructor initializes booking history */
    public BookingHistory() {
        history = new ArrayList<>();
    }

    /**
     * Adds a confirmed reservation to history.
     *
     * @param reservation confirmed booking
     */
    public void addReservation(Reservation reservation) {
        history.add(reservation);
    }

    /**
     * Returns all stored reservations.
     *
     * @return list of reservations
     */
    public List<Reservation> getAllReservations() {
        return history;
    }
}
