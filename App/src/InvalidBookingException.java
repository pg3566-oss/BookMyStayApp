/**
 * ===============================================================
 * CLASS - InvalidBookingException
 * ===============================================================
 *
 * Use Case 9: Error Handling & Validation
 *
 * Description:
 * Custom exception to represent invalid booking scenarios.
 *
 * @version 9.0
 */

public class InvalidBookingException extends Exception {

    /**
     * Constructor with error message.
     *
     * @param message error description
     */
    public InvalidBookingException(String message) {
        super(message);
    }
}
