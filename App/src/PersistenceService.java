import java.io.*;

/**
 * ===============================================================
 * CLASS - PersistenceService
 * ===============================================================
 *
 * Use Case 12: Data Persistence & System Recovery
 *
 * Description:
 * Handles saving and loading system state
 * using file-based serialization.
 *
 * @version 12.0
 */

public class PersistenceService {

    private static final String FILE_NAME = "system_state.ser";

    /**
     * Saves system state to file
     */
    public void saveState(SystemState state) {

        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            oos.writeObject(state);

            System.out.println("System state saved successfully.");

        } catch (IOException e) {

            System.out.println("Error saving system state.");
        }
    }

    /**
     * Loads system state from file
     */
    public SystemState loadState() {

        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            System.out.println("System state restored successfully.");

            return (SystemState) ois.readObject();

        } catch (FileNotFoundException e) {

            System.out.println("No previous state found. Starting fresh.");

        } catch (Exception e) {

            System.out.println("Error loading state. Starting fresh.");
        }

        return null;
    }
}
