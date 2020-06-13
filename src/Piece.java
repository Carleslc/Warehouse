/**
 * A piece.
 *
 * This class cannot be instantiated directly, that's why this class is abstract.
 */
public abstract class Piece {

    /**
     * The reference of this piece.
     */
    private int reference;

    /**
     * Storage where this piece should be stored.
     */
    private Storage storage;

    /**
     * Create a new piece with a reference.
     * This constructor is called from children classes.
     * @param reference the reference of this piece
     * @param storage where this piece should be stored
     */
    public Piece(int reference, Storage storage) {
        this.reference = reference;
        this.storage = storage;
    }

    /**
     * Retrieve where this piece should be stored.
     * @return the position where this piece should be stored
     */
    public Position getStoragePosition() {
        return storage.getPosition();
    }

    /**
     * This piece is stored in its storage.
     */
    public void store() {
        // Store this piece in the storage where this piece should be stored
        storage.store(this);
    }

    /**
     * Get a String representation of this piece
     * @return a String representation of this piece
     */
    public String toString() {
        // Get the instance class name (RoundPiece, SquarePiece, CylindricalPiece...)
        String actualClass = this.getClass().getSimpleName();

        // Append the reference of this piece to the class name so this String is an identifier for this piece
        return actualClass + " #" + reference;
    }

}
