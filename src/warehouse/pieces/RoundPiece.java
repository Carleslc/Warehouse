package warehouse.pieces;

import warehouse.storage.Storage;

/**
 * A round piece.
 */
public class RoundPiece extends Piece {

    /**
     * Create a new round piece with a reference.
     * 
     * @param reference the reference of this piece
     * @param storage where this piece should be stored
     */
    public RoundPiece(int reference, Storage<Piece> storage) {
        // Call parent constructor
        super(reference, storage);
    }
    
    /**
     * Get the price of this round piece.
     * 
     * @return the price of this round piece
     */
    @Override
    public int getPrice() {
        return 10;
    }
}
