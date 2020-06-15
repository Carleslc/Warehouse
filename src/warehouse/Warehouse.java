package warehouse;

import java.util.ArrayList;
import java.util.List;

import warehouse.pieces.factory.CylindricalPieceFactory;
import warehouse.pieces.factory.RandomPieceFactory;
import warehouse.pieces.factory.RoundPieceFactory;
import warehouse.pieces.factory.SquarePieceFactory;
import warehouse.storage.PieceStorage;
import warehouse.storage.Storage;
import warehouse.vehicle.AGVS;
import warehouse.vehicle.CannotMoveException;

public class Warehouse {

    /**
     * Number of random pieces to add to the conveyor.
     */
    private static final int NUMBER_OF_PIECES = 10;

    /**
     * Number of AGVS that will be moving pieces from the conveyor to their storages.
     */
    private static final int NUMBER_OF_VEHICLES = 3;

    /**
     * Move pieces in a warehouse from the picking point to their storage using many
     * Automated Guided Vehicle System (AGVS) vehicles.
     *
     * Creates three kind of storage bins for three kind of pieces, then adds some
     * random pieces to a conveyor that receives those pieces and unloads them to a
     * picking point, finally adds some AGVS that can load pieces from the picking
     * point and move them to their storage.
     * 
     * Each AGVS moves asynchronously in its own thread, so one AGVS can move one piece
     * at a time but many AGVS can move different pieces at the same time.
     * 
     * Note that actual execution order is indeterminate due to the asynchronous execution,
     * because of this and the randomness of the pieces creation process
     * different executions of the program will have different results.
     * 
     * @param args program arguments, not used
     */
    public static void main(String[] args) {
        // Create storage bins for each kind of piece
        PieceStorage cylindricalStorage = new PieceStorage("CYLINDRICAL", new Position(0, 1));
        PieceStorage squareStorage = new PieceStorage("SQUARE", new Position(0, 2));
        PieceStorage roundStorage = new PieceStorage("ROUND", new Position(0, 3));

        // Create a random piece factory to create pieces with random type and colors
        RandomPieceFactory randomPieceFactory = new RandomPieceFactory(
                new CylindricalPieceFactory(cylindricalStorage),
                new SquarePieceFactory(squareStorage),
                new RoundPieceFactory(roundStorage));

        // Create the conveyor with a picking point position
        Conveyor conveyor = new Conveyor(new Position(3, 2));

        // Create some random pieces, adding them to the conveyor
        for (int i = 0; i < NUMBER_OF_PIECES; i++) {
            conveyor.add(randomPieceFactory.create());
        }

        System.out.println("Picking point is at " + conveyor.getPickingPointPosition());

        // Create a storage of automated vehicles
        Storage<AGVS> vehicleStorage = new Storage<>(new Position(3, 3));

        // Add some AGVS to the vehicle storage
        for (int i = 1; i <= NUMBER_OF_VEHICLES; i++) {
            vehicleStorage.store(new AGVS(i, vehicleStorage.getPosition()));
        }

        // Start the main task of moving pieces from the picking point to their storages
        consume(vehicleStorage, conveyor);

        // Print final status, showing where each piece has been stored
        System.out.println(roundStorage);
        System.out.println(squareStorage);
        System.out.println(cylindricalStorage);
    }

    /**
     * Use available vehicles to move pieces from the picking point to their storages.
     * 
     * Each vehicle can move independently to the others, so each vehicle works
     * asynchronously in its own thread.
     * 
     * Information about the process will be printed to the console.
     * Note that printing order is indeterminate due to the asynchronous execution.
     * 
     * This method waits until all threads are terminated.
     * 
     * @param vehicleStorage storage with available AGVS vehicles
     * @param conveyor       the conveyor providing to a picking point the pieces
     *                       that the available vehicles will pick and move
     */
    private static void consume(Storage<AGVS> vehicleStorage, Conveyor conveyor) {
        // A list to store different running threads
        List<Thread> threads = new ArrayList<>();

        // Create a new thread for each vehicle available
        while (!vehicleStorage.isEmpty()) {
            // Retrieve the next AGVS from the vehicle storage
            AGVS agvs = vehicleStorage.remove();

            /*
             * Create a new thread for an asynchronous execution allowing multiple AGVS to
             * move at the same time.
             *
             * A lambda function without parameters is provided to the thread to run when
             * the thread start method is called.
             */
            Thread task = new Thread(() -> {
                try {
                    // Move pieces to their storage position using the AGVS
                    agvs.consume(conveyor);
                } catch (CannotMoveException e) {
                    // If the AGVS runs out of battery then we show a red message to the console
                    // Note that this can make a piece to not be stored because the vehicle stops
                    // halfway with a piece loaded
                    System.err.println(e.getMessage());
                }
            });

            // Start the thread, calling the lambda function defined above that starts moving the AGVS
            task.start();

            // Add the thread to the list so we can reference it later to wait until it finishes
            threads.add(task);
        }

        // Wait for threads to finish
        threads.forEach(t -> {
            try {
                t.join(); // wait until thread t has finished
            } catch (InterruptedException e) {
                e.printStackTrace(); // should not happen
            }
        });
    }
}
