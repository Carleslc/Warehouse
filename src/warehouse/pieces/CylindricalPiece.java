package warehouse.pieces;

import warehouse.storage.Storage;

/**
 * A cylindrical piece.
 */
public class CylindricalPiece extends Piece {

    /**
     * Create a new cylindrical piece with a reference.
     * @param reference the reference of this piece
     * @param storage where this piece should be stored
     */
    public CylindricalPiece(int reference, Storage<Piece> storage) {
        // Call parent constructor
        super(reference, storage);
    }

    /**
     * Get the price of this cylindrical piece.
     * 
     * @return the price of this cylindrical piece
     */
    @Override
    public int getPrice() {
        return 20;
    }
    
}
