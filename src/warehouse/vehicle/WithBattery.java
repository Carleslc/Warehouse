package warehouse.vehicle;

/**
 * Represents an object that has batteries attached.
 */
public interface WithBattery {
    
    /**
     * Get the maximum amount of battery for this object in mAh.
     * 
     * @return maximum amount of battery that his object can have
     */
    int getMaxBattery();
    
    /**
     * Get the current amount of battery of this object in mAh.
     * 
     * @return current amount of battery of this object
     */
    int getCurrentBattery();
    
    /**
     * Get the remaining amount of battery of this object in percentage.
     * 
     * @return remaining amount of battery of this object
     */
    default int getRemainingBattery() {
        return (int) (100 * (((float) getCurrentBattery()) / getMaxBattery()));
    }
    
    /**
     * Checks if this object has enough remaining battery to consume some amount of charge
     * 
     * @param consumption the charge in mAh to check
     * @return true if this object has enough remaining battery to consume the charge specified by consumption, false otherwise
     */
    default boolean hasEnoughBattery(int consumption) {
        return consumption <= getCurrentBattery();
    }

}
