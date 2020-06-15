package warehouse.pieces.factory;

import warehouse.pieces.Piece;
import warehouse.pieces.SquarePiece;
import warehouse.pieces.builder.PieceBuilder;
import warehouse.storage.Storage;

/**
 * A piece factory that creates pieces of square type
 */
public class SquarePieceFactory extends PieceTypeFactory<SquarePiece> {
    
    /**
     * Creates a new piece factory that can create new square pieces with an incremental reference starting at 1
     * and a specific storage where each piece should be stored.
     * 
     * @param storage the storage where every piece created with this piece factory should be stored
     */
    public SquarePieceFactory(Storage<Piece> storage) {
        super(storage);
    }
    
    /**
     * Get a new piece builder with reference, storage and type square set.
     * 
     * @return a new piece builder with reference, storage and type square set.
     */
    @Override
    protected PieceBuilder.LastStep<SquarePiece> newBuilder() {
        return newBuilderTypeStep().square();
    }
    
}
