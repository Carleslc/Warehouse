package warehouse;

import java.util.LinkedList;
import java.util.Queue;

import warehouse.pieces.Piece;

/**
 * A conveyor that moves pieces to a picking point.
 *
 * Thanks to a presence detector this conveyor moves forward when there is no piece in the picking point,
 * so the first piece in this conveyor always stays in the picking point.
 */
public class Conveyor {

    /**
     * The picking point where this conveyor unloads each piece.
     */
    private PickingPoint pickingPoint;

    /**
     * The queue of pieces in this conveyor waiting to be loaded into the picking point.
     */
    private Queue<Piece> pieces;

    /**
     * Create a new conveyor.
     * @param pickingPointPosition the picking point position where pieces must be unloaded
     */
    public Conveyor(Position pickingPointPosition) {
        // Create the picking point where unload the first piece of this conveyor
        pickingPoint = new PickingPoint(pickingPointPosition);

        // Create a list for the rest of pieces in this conveyor
        // LinkedList implements the Java interface Queue
        pieces = new LinkedList<>();
    }

    /**
     * Adds a piece to this conveyor.
     *
     * If there is no piece in the picking point then this conveyor
     * moves forward to ensure the added piece is at the picking point.
     *
     * @param piece the piece to add
     */
    public void add(Piece piece) {
        // Append the piece to the queue of pieces waiting to be loaded into the picking point
        pieces.add(piece);

        System.out.println(piece + " added to the conveyor");

        // Move forward if there is no piece in the picking point
        if (pickingPointIsEmpty()) {
            moveForward();
        }
    }

    /**
     * Retrieves and removes the first piece of this conveyor, located at the picking point.
     *
     * After removing the piece from the picking point this conveyor is moved forward.
     *
     * @return the piece removed from the picking point, if there is one, or null otherwise
     */
    public Piece unloadPieceFromPickingPoint() {
        // Unload the current piece from the picking point
        Piece piece = pickingPoint.unload();

        moveForward();
        
        System.out.println("There are " + getSize() + " pieces remaining in the conveyor");

        return piece;
    }

    /**
     * Moves this conveyor forward, providing a piece into the picking point.
     */
    private void moveForward() {
        if (!pieces.isEmpty()) {
            // Retrieve and remove the first piece from the queue of pieces waiting to be loaded into the picking point.
            // If the queue is empty then NoSuchElementException is thrown, but he have checked that the queue is not empty
            Piece piece = pieces.remove();
            
            // Load the piece into the picking point
            pickingPoint.load(piece);

            System.out.println("Conveyor moves forward");
        }
    }

    /**
     * Position of the first piece of this conveyor
     * @return the picking point position of this conveyor
     */
    public Position getPickingPointPosition() {
        return pickingPoint.getPosition();
    }

    /**
     * Returns true if there is no piece in the picking point
     * @return true if there is no piece in the picking point, false otherwise
     */
    public boolean pickingPointIsEmpty() {
        return pickingPoint.isEmpty();
    }

    /**
     * Number of pieces in this conveyor
     * @return the number of pieces in this conveyor
     */
    public int getSize() {
        // Pieces in queue
        int size = pieces.size();

        // Picking point piece
        if (!pickingPointIsEmpty()) {
            size += 1;
        }

        return size;
    }
}
