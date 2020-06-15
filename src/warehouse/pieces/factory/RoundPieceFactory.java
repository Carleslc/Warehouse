package warehouse.pieces.factory;

import warehouse.pieces.Piece;
import warehouse.pieces.RoundPiece;
import warehouse.pieces.builder.PieceBuilder;
import warehouse.storage.Storage;

/**
 * A piece factory that creates pieces of round type
 */
public class RoundPieceFactory extends PieceTypeFactory<RoundPiece> {
    
    /**
     * Creates a new piece factory that can create new round pieces with an incremental reference starting at 1
     * and a specific storage where each piece should be stored.
     * 
     * @param storage the storage where every piece created with this piece factory should be stored
     */
    public RoundPieceFactory(Storage<Piece> storage) {
        super(storage);
    }
    
    /**
     * Get a new piece builder with reference, storage and type round set.
     * 
     * @return a new piece builder with reference, storage and type round set.
     */
    @Override
    protected PieceBuilder.LastStep<RoundPiece> newBuilder() {
        return newBuilderTypeStep().round();
    }
    
}
