
public class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {

        System.out.println("Concurrent Booking Simulation Started");

        // Shared resources
        RoomInventory inventory = new RoomInventory();
        BookingRequestQueue queue = new BookingRequestQueue();
        RoomAllocationService allocationService =
                new RoomAllocationService();

        // Add multiple booking requests
        queue.addRequest(new Reservation("Guest1", "Single"));
        queue.addRequest(new Reservation("Guest2", "Single"));
        queue.addRequest(new Reservation("Guest3", "Double"));
        queue.addRequest(new Reservation("Guest4", "Suite"));
        queue.addRequest(new Reservation("Guest5", "Single"));

        // Create threads (simulate multiple users)
        Thread t1 = new Thread(
                new ConcurrentBookingProcessor(queue, inventory, allocationService)
        );

        Thread t2 = new Thread(
                new ConcurrentBookingProcessor(queue, inventory, allocationService)
        );

        Thread t3 = new Thread(
                new ConcurrentBookingProcessor(queue, inventory, allocationService)
        );

        // Start threads
        t1.start();
        t2.start();
        t3.start();

        // Wait for all threads to complete
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }

        System.out.println("All bookings processed safely");
    }
}


