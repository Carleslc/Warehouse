package warehouse.pieces.factory;

import warehouse.pieces.Piece;

/**
 * An interface for piece factories.
 */
public interface PieceFactory {
    
    /**
     * Create a piece.
     * 
     * @return a new piece instance
     */
    Piece create();

}
