package warehouse.vehicle;

import warehouse.Conveyor;
import warehouse.Position;
import warehouse.pieces.Piece;

/**
 * An Automated Guided Vehicle System that can load one piece from a conveyor
 * picking point and then, autonomously, go to the storage where the piece needs
 * to be stored, storing it and returning back to the picking point to process the next piece.
 */
public class AGVS extends ElectricVehicle {
    
    /**
     * Battery in mAh for this model of AGVS.
     */
    private static final int MAX_BATTERY = 5000;
    
    /**
     * Movement consumption in mAh for this model of AGVS.
     * 
     * Each move costs this amount of charge from the battery of the AGVS.
     */
    private static final int MOVE_BATTERY_CONSUMPTION = 100;
    
    /**
     * Time is simulated stopping the current thread for this amount of milliseconds.
     */
    private static final int MOVE_TIME = 0;
    
    /**
     * An identifier for this AGVS.
     */
    private int id;
    
    /**
     * Current position of this vehicle.
     */
    private Position position;

    /**
     * Current piece loaded into this vehicle.
     */
    private Piece load;

    /**
     * Create a new Automated Guided Vehicle System (AGVS) located in an initial position.
     * This AGVS has a battery of {@code MAX_BATTERY} mAh.
     * 
     * @param id an identifier for this vehicle
     * @param initialPosition the initial position for this vehicle
     */
    public AGVS(int id, Position initialPosition) {
        super(MAX_BATTERY); // call parent constructor with battery amount (mAh)
        
        this.id = id;
        position = initialPosition;
    }

    /**
     * Start this AGVS to consume the conveyor and move each piece from the picking
     * point to its storage.
     * 
     * @param conveyor the conveyor that provides pieces into a picking point
     * 
     * @throws CannotMoveException when this AGVS don't have enough remaining battery to move
     */
    public void consume(Conveyor conveyor) throws CannotMoveException {
        // Loop until no more moves are needed, when this AGVS is located at the picking point
        // and no more pieces are available at the conveyor picking point
        while (shouldMove(conveyor)) {
            // Check if this AGVS should move to the picking point or to a storage
            if (isEmpty()) {
                // There is no piece in the AGVS, so this AGVS should return back to the picking point
                Position target = conveyor.getPickingPointPosition();

                move(target);

                // Load a piece into the AGVS from the conveyor if AGVS is at the picking point
                if (position.equals(target)) {
                    // Lock the conveyor for this AGVS until piece is loaded
                    // to ensure no other AGVS (in another thread) tries to pick a piece from the picking point at the same time
                    synchronized (conveyor) {
                        if (!conveyor.pickingPointIsEmpty()) {
                            // Get the current piece from the picking point
                            Piece piece = conveyor.unloadPieceFromPickingPoint();

                            // Load the piece into this AGVS
                            load = piece;

                            notify("load " + piece);
                            notify(piece + " should be stored at " + piece.getStoragePosition());
                        }
                    }
                }
            } else {
                // There is a piece in the AGVS, so this AGVS should go to its storage position
                Position target = load.getStoragePosition();

                move(target);

                // Store the piece if AGVS is at the storage position
                if (position.equals(target)) {
                    // AGVS is at storage position, so it can store the piece
                    load.store();

                    // Remove the piece from this AGVS
                    load = null;
                }
            }
        }
    }

    /**
     * Retrieves the position of this AGVS in the warehouse.
     * 
     * @return the position of this AGVS
     */
    @Override
    public Position getPosition() {
        return position;
    }

    /**
     * Check if this AGVS is empty, so no piece is loaded to it
     * 
     * @return true if this AGVS has no piece loaded, false if this AGVS already has
     *         a piece loaded into it
     */
    public boolean isEmpty() {
        return load == null;
    }

    /**
     * Check if this AGVS should move to either a storage or the picking point
     * 
     * @param conveyor the conveyor that provides pieces into a picking point
     * @return true if either this AGVS or the conveyor picking point has a piece to
     *         store or AGVS is not in the picking point
     */
    private boolean shouldMove(Conveyor conveyor) {
        return !isEmpty() || !conveyor.pickingPointIsEmpty() || !position.equals(conveyor.getPickingPointPosition());
    }

    /**
     * Moves this AGVS one step closer to the target position.
     *
     * This AGVS can move up (y + 1), down (y - 1), right (x + 1) or left (x - 1).
     *
     * If target position is the same as this AGVS position then this AGVS won't
     * move.
     * 
     * Each move drains {@code MOVE_BATTERY_CONSUMPTION} amount of battery.
     *
     * @param target the target position
     * 
     * @throws CannotMoveException if this AGVS don't have enough remaining battery to move
     */
    private void move(Position target) throws CannotMoveException {
        // Calculate the next position of this AGVS
        Position next;
        if (position.getY() < target.getY()) {
            next = new Position(position.getX(), position.getY() + 1); // up
        } else if (position.getY() > target.getY()) {
            next = new Position(position.getX(), position.getY() - 1); // down
        } else if (position.getX() < target.getX()) {
            next = new Position(position.getX() + 1, position.getY()); // right
        } else if (position.getX() > target.getX()) {
            next = new Position(position.getX() - 1, position.getY()); // left
        } else {
            // AGVS is at target position
            next = position; // don't move
        }

        if (next.equals(position)) {
            notify(" is at " + position);
        } else if (hasEnoughBattery(MOVE_BATTERY_CONSUMPTION)) {
            position = next;
            
            // Moving drains some battery
            battery -= MOVE_BATTERY_CONSUMPTION;
            
            notify("moves to " + position);
            
            // Simulate some time spent moving stopping the current thread for an amount of time
            try {
                Thread.sleep(MOVE_TIME);
            } catch (InterruptedException e) {
                throw new CannotMoveException(e.getMessage());
            }
        } else {
            battery = 0;
            
            // Throw an exception when this AGVS runs out of battery
            throw new CannotMoveException(this + " has run out of battery!");
        }
    }
    
    /**
     * Sends a message.
     * 
     * @param message the message to send
     */
    private void notify(String message) {
        System.out.println(this + ": " + message);
    }
    
    /**
     * Get a String representation of this AGVS with its name and remaining battery percentage.
     * 
     * @return a String representation of this AGVS
     */
    @Override
    public String toString() {
        return "AGVS " + id + " (" + getRemainingBattery() + "%)";
    }
}
