package warehouse;

/**
 * Colors with a price.
 */
public enum Color implements Priceable {
    // Common colors have a price of 5
    RED (5),
    GREEN (5),
    BLUE (5),
    
    // Special colors have a price of 10
    GOLD (10);
    
    /**
     * Price of this color
     */
    private int price;
    
    /**
     * Create a new Color.
     * 
     * This constructor is private because can only be used in this enum.
     * 
     * @param price the price of this color
     */
    private Color(int price) {
        this.price = price;
    }
    
    /**
     * Get the price of this color.
     * 
     * @return the price of this color
     */
    @Override
    public int getPrice() {
        return this.price;
    }
}
