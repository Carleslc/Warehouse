package warehouse.vehicle;

/**
 * An exception standing for impossible movement of some object.
 */
public class CannotMoveException extends Exception {

    // Eclipse suggestion because Exception implements Serializable
    private static final long serialVersionUID = -7334435191095445393L;
    
    /**
     * Create a new exception for impossible movement of some object.
     * 
     * @param reason the reason why the object cannot move
     */
    public CannotMoveException(String reason) {
        super(reason);
    }

}
