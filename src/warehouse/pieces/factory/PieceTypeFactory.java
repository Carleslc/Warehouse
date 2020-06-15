package warehouse.pieces.factory;

import warehouse.pieces.Piece;
import warehouse.pieces.builder.PieceBuilder;
import warehouse.storage.Storage;

/**
 * A piece factory that creates pieces of a specific type (shape)
 * with an integer reference and a storage where the piece should be stored.
 *
 * @param <PieceType> a generic type standing for a subclass of Piece
 */
public abstract class PieceTypeFactory<PieceType extends Piece> implements PieceFactory {
    
    /**
     * The reference for the next Piece instance created.
     */
    private int reference;

    /**
     * The storage where every piece created with this piece factory should be stored.
     */
    private Storage<Piece> storage;

    /**
     * Creates a new piece factory that can create new pieces with an incremental reference starting at 1
     * and a specific storage where each piece should be stored.
     * 
     * This constructor only should be used by subclasses,
     * that's why this constructor has a protected visibility.
     * 
     * @param storage the storage where every piece created with this piece factory should be stored
     */
    protected PieceTypeFactory(Storage<Piece> storage) {
        this.reference = 1;
        this.storage = storage;
    }

    /**
     * Children classes should call this method to get a prepared builder,
     * with a reference and a storage already set.
     * 
     * This method cannot be used outside the hierarchy,
     * that's why this method has a protected visibility.
     * 
     * @return a piece builder to build a new piece
     */
    protected final PieceBuilder.TypeStep newBuilderTypeStep() { // This method cannot be override, so it's final.
        int reference = this.reference; // current reference to assign for the new piece
        
        // Add 1 to the reference so the next created piece with this factory has a different reference
        this.reference++;
        
        // create a new piece builder and set appropriate reference and storage
        return Piece.newBuilder().withReference(reference).shouldStoreAt(this.storage);
    }
    
    /**
     * Children classes must implement this method,
     * returning a new piece builder at the final step (where build() can be called to create the piece).
     * 
     * @return a new piece builder at the final step
     */
    protected abstract PieceBuilder.LastStep<PieceType> newBuilder();

    
    /**
     * Create a new piece of shape PieceType with a reference and a storage where this piece should be stored.
     * 
     * The first piece created with this method and this factory will have a reference of 1.
     * References are incremented sequentially by 1 for every call to this method.
     * 
     * @return a new piece instance
     */
    @Override
    public final PieceType create() { // This method cannot be override, so it's final.
        return newBuilder().build();
    }

}
