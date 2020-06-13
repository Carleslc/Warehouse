/**
 * Picking point that has capacity for one piece.
 */
public class PickingPoint {

    /**
     * The position of this picking point.
     */
    private Position position;

    /**
     * The piece in this picking point.
     */
    private Piece piece;

    /**
     * Create a picking point.
     * @param position the position of this picking point
     */
    public PickingPoint(Position position) {
        this.position = position;
    }

    /**
     * Retrieves the position of this picking point.
     * @return the position of this picking point
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Load a piece into this picking point.
     *
     * If a piece already exists in this picking point then the piece is replaced.
     *
     * @param piece the piece to load into this picking point
     */
    public void load(Piece piece) {
        this.piece = piece;

        System.out.println(piece + " loaded into the picking point");
    }

    /**
     * Retrieves and removes the piece in this picking point.
     * @return the piece in this picking point, if there is one, or null otherwise
     */
    public Piece unload() {
        Piece piece = this.piece;

        // Remove the piece from this picking point
        this.piece = null;

        System.out.println(piece + " removed from the picking point");

        return piece;
    }

    /**
     * A presence detector sensor checks for piece presence in this picking point.
     *
     * @return true if there is no piece in this picking point, false otherwise
     */
    public boolean isEmpty() {
        return this.piece == null;
    }

}
