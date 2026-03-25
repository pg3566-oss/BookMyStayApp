/**
 * ===============================================================
 * CLASS - ConcurrentBookingProcessor
 * ===============================================================
 *
 * Use Case 11: Concurrent Booking Simulation
 *
 * Description:
 * Processes booking requests using multiple threads
 * while ensuring thread-safe operations.
 *
 * @version 11.0
 */

public class ConcurrentBookingProcessor implements Runnable {

    private BookingRequestQueue queue;
    private RoomInventory inventory;
    private RoomAllocationService allocationService;

    public ConcurrentBookingProcessor(BookingRequestQueue queue,
                                      RoomInventory inventory,
                                      RoomAllocationService allocationService) {
        this.queue = queue;
        this.inventory = inventory;
        this.allocationService = allocationService;
    }

    @Override
    public void run() {

        while (true) {

            Reservation request;

            // 🔒 Synchronize queue access (critical section)
            synchronized (queue) {

                if (!queue.hasPendingRequests()) {
                    break;
                }

                request = queue.getNextRequest();
            }

            // 🔒 Synchronize allocation (critical section)
            synchronized (inventory) {
                allocationService.allocateRoom(request, inventory);
            }
        }
    }
}
