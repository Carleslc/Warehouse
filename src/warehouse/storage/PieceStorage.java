package warehouse.storage;

import warehouse.Position;
import warehouse.pieces.Piece;

/**
 * A Storage of pieces with a name.
 */
public class PieceStorage extends Storage<Piece> {
    
    /**
     * Name of this storage.
     */
    private String name;

    /**
     * Create a new storage of pieces.
     * 
     * @param name the name of this storage
     * @param position the position of this storage in the warehouse
     */
    public PieceStorage(String name, Position position) {
        super(position);
        
        this.name = name;
    }
    
    /**
     * Calculate the total price of this storage of pieces.
     * 
     * @return sum of prices for all pieces in this storage
     */
    public int getTotalPrice() {
        // create a list of prices for each piece in this storage using the getPrice method reference from Piece
        // then sum every number in the list to get the total price of the pieces stored in this storage
        return objects.stream().mapToInt(Piece::getPrice).sum();
    }
    
    /**
     * Get a String representation of this storage.
     * 
     * @return a String representation of this storage with its name, list of pieces and total price
     */
    @Override
    public String toString() {
        return name + " STORAGE\n" + super.toString() + "\nTotal price of pieces in this storage: " + getTotalPrice();
    }

}
