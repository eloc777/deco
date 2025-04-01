package examblock.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A collection object for holding and managing `Room`s.
 */
public class RoomList {
    /**
     * A list of rooms.
     */
    private List<Room> rooms;

    /**
     * Constructs an empty list of `Room`s.
     */
    public RoomList() {
        rooms = new ArrayList<>();
    }

    /**
     * Adds a `Room` to this list of `Room`s.
     *
     * @param room - the room object being added to this list.
     */
    public void addRoom(Room room) {
        rooms.add(room);
    }

    /**
     * Removes a given `Room` from the `RoomList`.
     *
     * @param room - the room to remove from this list.
     */
    public void removeRoom(Room room) {
        rooms.remove(room);
    }

    /**
     * Get the first `Room` with a matching `id`.
     *
     * @param id - the `id` of the `Room` to be found.
     * @return The first `Room` with a matching `id`, if it exists.
     * @throws IllegalStateException - throw an IllegalStateException if it can't find a matching
     *                               room
     *                               as that indicates there is a misalignment of the executing
     *                               state and the complete list of possible rooms.
     */
    public Room getRoom(String id) throws IllegalStateException {
        for (Room room : rooms) {
            if (room.roomId().equals(id)) {
                return room;
            }
        }
        throw new IllegalStateException("Room with ID '"
                + id
                + "' not found");
    }

    /**
     * Creates a new `List` holding `references` to all the `Room`s managed by this `RoomList`
     * and returns it.
     *
     * @return A new `List` holding `references` to all the `Room`s managed by this `RoomList`.
     */
    public List<Room> all() {
        return new ArrayList<>(rooms);
    }

    /**
     * Returns detailed string representations of the contents of this room list.
     *
     * @return Detailed string representations of the contents of this room list.
     */
    public String getFullDetail() {
        StringBuilder result = new StringBuilder("Room List - "
                + rooms.size()
                + " room(s)\n");

        for (int i = 0; i
                < rooms.size(); i++) {
            result.append(rooms.get(i).toString());
            if (i
                    < rooms.size()
                    - 1) {
                result.append("\n");
            }
        }

        return result.toString();
    }

    /**
     * Returns a brief string representation of the contents of this room list.
     *
     * @return A brief string representation of the contents of this room list.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Room List - "
                + rooms.size()
                + " room(s)\n");

        for (int i = 0; i
                < rooms.size(); i++) {
            result.append(rooms.get(i).toString());
            if (i
                    < rooms.size()
                    - 1) {
                result.append("\n");
            }
        }

        return result.toString();
    }
}
