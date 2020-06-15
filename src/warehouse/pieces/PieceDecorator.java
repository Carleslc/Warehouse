package warehouse.pieces;

/**
 * An abstract piece decorator that uses Decorator pattern to composite pieces.
 */
public abstract class PieceDecorator extends Piece {
    
    /**
     * The decorated piece.
     * 
     * This instance can only be accessed from decorators,
     * that's why this field has a protected visibility.
     */
    protected Piece decoratedPiece;

    /**
     * Constructor called by children classes to decorate a piece.
     * 
     * @param piece the piece to decorate
     */
    protected PieceDecorator(Piece piece) {
        super(piece);
        
        this.decoratedPiece = piece;
    }

    /**
     * Get the price of the decorated piece.
     * 
     * @return the price of the decorated piece
     */
    @Override
    public int getPrice() {
        return decoratedPiece.getPrice();
    }
    
}
