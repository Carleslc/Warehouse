import java.util.ArrayList;
import java.util.List;

/**
 * A storage bin located in a position in the warehouse that can store pieces.
 */
public class Storage {

    /**
     * List of pieces stored in this storage.
     */
    private List<Piece> pieces;

    /**
     * Position of this storage in the warehouse.
     */
    private Position position;

    /**
     * Create an empty storage.
     * @param position the position of this storage in the warehouse
     */
    public Storage(Position position) {
        this.position = position;
        pieces = new ArrayList<>();
    }

    /**
     * Adds a new piece to this storage.
     * @param piece the piece to store
     */
    public void store(Piece piece) {
        pieces.add(piece);

        System.out.println(piece + " stored at " + position);
    }

    /**
     * Number of pieces in this storage.
     * @return the number of pieces in this storage
     */
    public int getSize() {
        return pieces.size();
    }

    /**
     * Retrieve the position where this storage is located.
     * @return the position of this storage in the warehouse
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Get a String representation of this storage, listing each of the pieces included in this storage.
     * @return a String representation of this storage
     */
    public String toString() {
        return pieces.toString();
    }
}
