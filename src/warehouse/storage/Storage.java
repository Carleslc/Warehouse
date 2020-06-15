package warehouse.storage;

import java.util.LinkedList;
import java.util.List;

import warehouse.Position;
import warehouse.Positionable;

/**
 * A storage bin located in a position in the warehouse that can store objects of type E.
 * 
 * @param <E> generic type of the objects that this storage can store
 */
public class Storage<E> implements Positionable {

    /**
     * List of objects of type E stored in this storage.
     */
    protected List<E> objects;

    /**
     * Position of this storage in the warehouse.
     */
    private Position position;

    /**
     * Create an empty storage.
     * 
     * @param position the position of this storage in the warehouse
     */
    public Storage(Position position) {
        this.position = position;
        objects = new LinkedList<>();
    }

    /**
     * Adds a new object to this storage.
     * 
     * @param object the object to store
     */
    public void store(E object) {
        objects.add(object);

        System.out.println(object + " stored at " + position);
    }
    
    /**
     * Removes an object from this storage.
     * 
     * @return the first object stored at this storage
     * 
     * @throws IllegalStateException if this storage is empty
     */
    public E remove() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("Storage is empty!");
        }
        return objects.remove(0);
    }

    /**
     * Number of objects stored in this storage.
     * 
     * @return the number of objects in this storage
     */
    public int getSize() {
        return objects.size();
    }
    
    /**
     * Checks if this storage is empty.
     * 
     * @return true if this storage has no elements stored, false otherwise
     */
    public boolean isEmpty() {
        return objects.isEmpty();
    }

    /**
     * Retrieve the position where this storage is located.
     * 
     * @return the position of this storage in the warehouse
     */
    @Override
    public Position getPosition() {
        return position;
    }

    /**
     * Get a String representation of this storage, listing each of the objects
     * included in this storage.
     * 
     * @return a String representation of this storage
     */
    @Override
    public String toString() {
        return "(" + getSize() + ") " + objects.toString();
    }
}
