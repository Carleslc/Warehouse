package warehouse.pieces.factory;

import warehouse.pieces.Piece;
import warehouse.pieces.CylindricalPiece;
import warehouse.pieces.builder.PieceBuilder;
import warehouse.storage.Storage;

/**
 * A piece factory that creates pieces of cylindrical type
 */
public class CylindricalPieceFactory extends PieceTypeFactory<CylindricalPiece> {
    
    /**
     * Creates a new piece factory that can create new cylindrical pieces with an incremental reference starting at 1
     * and a specific storage where each piece should be stored.
     * 
     * @param storage the storage where every piece created with this piece factory should be stored
     */
    public CylindricalPieceFactory(Storage<Piece> storage) {
        super(storage);
    }
    
    /**
     * Get a new piece builder with reference, storage and type cylindrical set.
     * 
     * @return a new piece builder with reference, storage and type cylindrical set.
     */
    @Override
    protected PieceBuilder.LastStep<CylindricalPiece> newBuilder() {
        return newBuilderTypeStep().cylindrical();
    }
    
}
