import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ===============================================================
 * MAIN CLASS - UseCase12DataPersistenceRecovery
 * ===============================================================
 *
 * Use Case 12: Data Persistence & System Recovery
 *
 * @version 12.0
 */

public class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        System.out.println("System Start - Loading State");

        PersistenceService persistenceService =
                new PersistenceService();

        SystemState state = persistenceService.loadState();

        RoomInventory inventory = new RoomInventory();
        BookingHistory history = new BookingHistory();

        // Restore state if available
        if (state != null) {

            // Restore inventory
            Map<String, Integer> savedInventory =
                    state.getInventory();

            for (String type : savedInventory.keySet()) {
                inventory.updateAvailability(type,
                        savedInventory.get(type));
            }

            // Restore booking history
            for (Reservation r : state.getReservations()) {
                history.addReservation(r);
            }

        } else {
            System.out.println("Fresh system started.");
        }

        // Simulate new booking
        RoomAllocationService allocationService =
                new RoomAllocationService();

        Reservation r1 = new Reservation("Abhi", "Single");
        allocationService.allocateRoom(r1, inventory);
        history.addReservation(r1);

        // Save state before shutdown
        System.out.println("\nSaving system state...");

        List<Reservation> reservations =
                new ArrayList<>(history.getAllReservations());

        Map<String, Integer> inventoryMap =
                inventory.getRoomAvailability();

        SystemState newState =
                new SystemState(reservations, inventoryMap);

        persistenceService.saveState(newState);

        System.out.println("System shutdown complete.");
    }
}

