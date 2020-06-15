package warehouse.pieces;

import warehouse.Color;

/**
 * A color decorator using Decorator pattern to allow pieces to be painted with some colors.
 */
public class ColoredPiece extends PieceDecorator {
    
    /**
     * A color the decorated piece is painted with.
     */
    private Color color;
    
    /**
     * Paint a piece with a color.
     * 
     * @param piece the piece to paint
     * @param color the color to use
     */
    public ColoredPiece(Piece piece, Color color) {
        super(piece);
        
        this.color = color;
    }
    
    /**
     * Get the color this piece is painted with.
     * 
     * @return the color this piece is painted with
     */
    public Color getColor() {
        return color;
    }
    
    /**
     * Get the price of this painted piece,
     * i.e. the price of this piece without color plus the price of this color.
     * 
     * @return the price of this colored piece
     */
    @Override
    public int getPrice() {
        return decoratedPiece.getPrice() + color.getPrice();
    }
    
    /**
     * Get a String representation of this colored piece.
     * 
     * @return a String representation of this colored piece
     */
    @Override
    public String toString() {
        return decoratedPiece.toString() + " (" + color + ")";
    }
}
