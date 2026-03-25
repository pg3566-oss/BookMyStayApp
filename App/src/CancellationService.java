import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * ===============================================================
 * CLASS - CancellationService
 * ===============================================================
 *
 * Use Case 10: Booking Cancellation & Inventory Rollback
 *
 * Description:
 * Handles cancellation of confirmed bookings
 * and safely restores system state.
 *
 * Key Concepts:
 * - Stack (LIFO rollback)
 * - State reversal
 * - Controlled mutation
 *
 * @version 10.0
 */

public class CancellationService {

    // Tracks released room IDs (LIFO)
    private Stack<String> releasedRoomIds;

    // Tracks active reservations
    private Set<Reservation> activeReservations;

    public CancellationService() {
        releasedRoomIds = new Stack<>();
        activeReservations = new HashSet<>();
    }

    /**
     * Register confirmed reservation (for tracking)
     */
    public void addReservation(Reservation reservation) {
        activeReservations.add(reservation);
    }

    /**
     * Cancel a booking safely
     */
    public void cancelReservation(Reservation reservation,
                                  RoomInventory inventory) {

        // Validate existence
        if (!activeReservations.contains(reservation)) {
            System.out.println(
                    "Cancellation failed: Reservation not found"
            );
            return;
        }

        String roomType = reservation.getRoomType();
        Map<String, Integer> availability =
                inventory.getRoomAvailability();

        // Simulate room ID rollback (LIFO)
        String releasedRoomId = roomType + "-released";

        releasedRoomIds.push(releasedRoomId);

        // Restore inventory
        inventory.updateAvailability(
                roomType,
                availability.get(roomType) + 1
        );

        // Remove from active bookings
        activeReservations.remove(reservation);

        System.out.println(
                "Booking cancelled for Guest: "
                        + reservation.getGuestName()
                        + ", Room released: "
                        + releasedRoomId
        );
    }
}
