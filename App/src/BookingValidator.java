import java.util.Map;

/**
 * ===============================================================
 * CLASS - BookingValidator
 * ===============================================================
 *
 * Use Case 9: Error Handling & Validation
 *
 * Description:
 * Validates booking requests before processing.
 *
 * @version 9.0
 */

public class BookingValidator {

    /**
     * Validates reservation input and system state.
     *
     * @param reservation booking request
     * @param inventory room inventory
     * @throws InvalidBookingException if validation fails
     */
    public void validate(Reservation reservation,
                         RoomInventory inventory)
            throws InvalidBookingException {

        String roomType = reservation.getRoomType();

        Map<String, Integer> availability =
                inventory.getRoomAvailability();

        // Validate room type
        if (!availability.containsKey(roomType)) {
            throw new InvalidBookingException(
                    "Invalid room type: " + roomType
            );
        }

        // Validate availability
        if (availability.get(roomType) <= 0) {
            throw new InvalidBookingException(
                    "No rooms available for type: " + roomType
            );
        }
    }
}
