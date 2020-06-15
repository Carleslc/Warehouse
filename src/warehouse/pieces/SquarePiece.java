package warehouse.pieces;

import warehouse.storage.Storage;

/**
 * A square piece.
 */
public class SquarePiece extends Piece {

    /**
     * Create a new square piece with a reference.
     * 
     * @param reference the reference of this piece
     * @param storage where this piece should be stored
     */
    public SquarePiece(int reference, Storage<Piece> storage) {
        // Call parent constructor
        super(reference, storage);
    }
    
    /**
     * Get the price of this square piece.
     * 
     * @return the price of this square piece
     */
    @Override
    public int getPrice() {
        return 5;
    }
}
