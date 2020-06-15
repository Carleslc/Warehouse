package warehouse.pieces;

import warehouse.Position;
import warehouse.Priceable;
import warehouse.pieces.builder.PieceBuilder;
import warehouse.storage.Storage;

/**
 * A piece with a price that can be stored in a storage in the warehouse.
 * 
 * {@code getPrice} method specified by {@code Priceable} interface is implemented in every subclass of Piece.
 * That's why this class is abstract, so this class cannot be instantiated directly.
 */
public abstract class Piece implements Priceable {

    /**
     * The reference of this piece.
     */
    private int reference;

    /**
     * Storage where this piece should be stored.
     */
    private Storage<Piece> storage;

    /**
     * Create a new piece with a reference.
     * 
     * This constructor is called from children classes,
     * that's why this constructor has a protected visibility.
     * 
     * Pieces can be publicly created using the piece builder provided by {@code newBuilder()} static method.
     * 
     * @param reference the reference of this piece
     * @param storage where this piece should be stored
     */
    protected Piece(int reference, Storage<Piece> storage) {
        this.reference = reference;
        this.storage = storage;
    }
    
    /**
     * Create a copy of a piece.
     * 
     * @param piece the piece to copy
     */
    protected Piece(Piece piece) {
        this(piece.reference, piece.storage); // call the constructor above
    }
    
    /**
     * Get a new piece builder to create an instance of this Piece class
     * 
     * @return a new builder to create a piece
     */
    public static PieceBuilder.ReferenceStep newBuilder() {
        return PieceBuilder.newBuilder();
    }

    /**
     * Retrieve where this piece should be stored.
     * 
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
     * Get a String representation of this piece.
     * 
     * @return a String representation of this piece
     */
    @Override
    public String toString() {
        // Get the instance class name (RoundPiece, SquarePiece, CylindricalPiece...)
        String actualClass = this.getClass().getSimpleName();

        // Append the reference of this piece to the class name so this String is an identifier for this piece
        return actualClass + " #" + reference;
    }

}
