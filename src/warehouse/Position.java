package warehouse;

/**
 * Position with coordinates x (horizontal axis) and y (vertical axis)
 */
public class Position {

    /**
     * Coordinate x
     */
    private int x;


    /**
     * Coordinate y
     */
    private int y;

    /**
     * Create a new position (x, y)
     * @param x the horizontal coordinate
     * @param y the vertical coordinate
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Create a new position centered to (0, 0)
     */
    public Position() {
        this(0, 0);
    }

    /**
     * Get the horizontal coordinate.
     * @return the x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Get the vertical coordinate.
     * @return the y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Checks if this position is equal to another position
     * @param other the another position
     * @return if this position is equal to other position
     */
    public boolean equals(Position other) {
        return this.x == other.x && this.y == other.y;
    }

    /**
     * Get a String representation of this position
     * @return a String representation of this position
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}
