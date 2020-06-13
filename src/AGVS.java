/**
 * An Automated Guided Vehicle System that can load one piece from a conveyor picking point
 * and then, autonomously, go to the storage where the piece needs to be stored, storing it
 * and returning back to the picking point to process the next piece.
 */
public class AGVS {

    /**
     * Current position of this vehicle.
     */
    private Position position;

    /**
     * Current piece loaded into this vehicle.
     */
    private Piece load;

    /**
     * Create a new Automated Guided Vehicle System located in an initial position.
     * @param initialPosition the initial position for this vehicle
     */
    public AGVS(Position initialPosition) {
        position = initialPosition;
    }

    /**
     * Start this AGVS to consume the conveyor and move each piece from the picking point to its storage.
     * @param conveyor the conveyor that provides pieces into a picking point
     */
    public void consume(Conveyor conveyor) {
        // Loop until no more moves are needed, when this AGVS is located at the picking point
        // and no more pieces are available at the conveyor picking point
        while (shouldMove(conveyor)) {
            // Check if this AGVS should move to the picking point or to a storage
            if (isEmpty()) {
                // There is no piece in the AGVS, so this AGVS should return back to the picking point
                Position target = conveyor.getPickingPointPosition();

                move(target);

                // Load a piece into the AGVS from the conveyor if AGVS is at the picking point
                if (position.equals(target)) {
                    System.out.println("There are " + conveyor.getSize() + " pieces remaining in the conveyor");

                    if (!conveyor.pickingPointIsEmpty()) {
                        // Get the current piece from the picking point
                        Piece piece = conveyor.unloadPieceFromPickingPoint();

                        // Load the piece into this AGVS
                        load = piece;
                        
                        System.out.println(piece + " is loaded into the AGVS");
                        System.out.println(piece + " should be stored at " + piece.getStoragePosition());
                    }
                }
            } else {
                // There is a piece in the AGVS, so this AGVS should go to its storage position
                Position target = load.getStoragePosition();

                move(target);

                // Store the piece if AGVS is at the storage position
                if (position.equals(target)) {
                    // AGVS is at storage position, so it can store the piece
                    load.store();

                    // Remove the piece from this AGVS
                    load = null;
                }
            }
        }
    }
    
    /**
     * Check if this AGVS is empty, so no piece is loaded to it
     * @return true if this AGVS has no piece loaded, false if this AGVS already has a piece loaded into it
     */
    public boolean isEmpty() {
        return load == null;
    }
    
    /**
     * Check if this AGVS should move to either a storage or the picking point
     * @param conveyor the conveyor that provides pieces into a picking point
     * @return true if either this AGVS or the conveyor picking point has a piece to store or AGVS is not in the picking point
     */
    private boolean shouldMove(Conveyor conveyor) {
        return !isEmpty() || !conveyor.pickingPointIsEmpty() || !position.equals(conveyor.getPickingPointPosition());
    }

    /**
     * Moves this AGVS one step closer to the target position.
     *
     * This AGVS can move up (y + 1), down (y - 1), right (x + 1) or left (x - 1).
     *
     * If target position is the same as this AGVS position then this AGVS won't move.
     *
     * @param target the target position
     */
    private void move(Position target) {
        // Calculate the next position of this AGVS
        Position next;
        if (position.getY() < target.getY()) {
            next = new Position(position.getX(), position.getY() + 1); // up
        } else if (position.getY() > target.getY()) {
            next = new Position(position.getX(), position.getY() - 1); // down
        } else if (position.getX() < target.getX()) {
            next = new Position(position.getX() + 1, position.getY()); // right
        } else if (position.getX() > target.getX()) {
            next = new Position(position.getX() - 1, position.getY()); // left
        } else {
            // AGVS is at target position
            next = position; // don't move
        }

        if (next.equals(position)) {
            System.out.println("AGVS is at " + position);
        } else {
            position = next;
            System.out.println("AGVS moves to " + position);
        }
    }
}
