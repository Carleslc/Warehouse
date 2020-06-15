package warehouse.vehicle;

/**
 * A vehicle with battery.
 * 
 * This class cannot be instantiated directly, that's why this class is abstract.
 */
public abstract class ElectricVehicle implements Vehicle, WithBattery {

    /**
     * Maximum amount of battery for this object in mAh.
     */
    private final int maxBattery;
    
    /**
     * Current amount of battery in mAh.
     */
    protected int battery;
    
    /**
     * Create a new electric vehicle with a maximum amount of battery.
     * 
     * This electric vehicle is created with the battery fully charged.
     * 
     * @param maxBattery maximum amount of battery for this object in mAh
     */
    public ElectricVehicle(int maxBattery) {
        this.maxBattery = maxBattery;
        this.battery = maxBattery; // full charge
    }
    
    /**
     * Get the current amount of battery of this electric vehicle in mAh.
     * 
     * @return current amount of battery of this electric vehicle
     */
    @Override
    public int getCurrentBattery() {
        return battery;
    }
    
    /**
     * Get the maximum amount of battery of this electric vehicle in mAh.
     * 
     * @return maximum amount of battery that this electric vehicle can have
     */
    @Override
    public int getMaxBattery() {
        return maxBattery;
    }
    
}
