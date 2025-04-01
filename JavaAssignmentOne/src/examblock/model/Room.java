package examblock.model;

public class Room {
    /**
     * The room identifier
     */
    private final String id;

    /**
     * Constructs a Room.
     *
     * @param id
     */
    public Room(String id) {

        this.id = id;
    }

    /**
     * Gets the identifier of the room
     *
     * @return identifier of the room
     */
    public String roomId() {
        return this.id;
    }

    /**
     * @return A string representation of the room
     */
    @Override
    public String toString() {
        return this.id;
    }
}
