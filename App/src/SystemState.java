import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * ===============================================================
 * CLASS - SystemState
 * ===============================================================
 *
 * Stores complete system snapshot
 *
 * @version 12.0
 */

public class SystemState implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Reservation> reservations;
    private Map<String, Integer> inventory;

    public SystemState(List<Reservation> reservations,
                       Map<String, Integer> inventory) {
        this.reservations = reservations;
        this.inventory = inventory;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public Map<String, Integer> getInventory() {
        return inventory;
    }
}
