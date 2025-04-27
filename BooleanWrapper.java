/**
 * A simple wrapper class for boolean values.
 * This class allows boolean values to be passed by reference.
 */
public class BooleanWrapper {
    private boolean value;
    
    /**
     * Constructs a BooleanWrapper with the specified value.
     * 
     * @param value the initial boolean value
     */
    public BooleanWrapper(boolean value) {
        this.value = value;
    }
    
    /**
     * Returns the current boolean value.
     * 
     * @return the current boolean value
     */
    public boolean get() {
        return value;
    }
    
    /**
     * Sets the boolean value.
     * 
     * @param value the new boolean value
     */
    public void set(boolean value) {
        this.value = value;
    }
}
