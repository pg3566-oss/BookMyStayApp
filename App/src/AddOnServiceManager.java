import java.util.*;

public class AddOnServiceManager {

    // Map: Reservation ID -> List of Services
    private Map<String, List<AddOnService>> servicesByReservation;

    // Constructor
    public AddOnServiceManager() {
        servicesByReservation = new HashMap<>();
    }

    // Add service to a reservation
    public void addService(String reservationId, AddOnService service) {

        // If reservation doesn't exist, create new list
        servicesByReservation.putIfAbsent(reservationId, new ArrayList<>());

        // Add service to list
        servicesByReservation.get(reservationId).add(service);
    }

    // Calculate total cost of services for a reservation
    public double calculateTotalServiceCost(String reservationId) {

        double total = 0.0;

        List<AddOnService> services = servicesByReservation.get(reservationId);

        if (services != null) {
            for (AddOnService service : services) {
                total += service.getCost();
            }
        }

        return total;
    }
}

