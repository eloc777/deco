package examblock.model;

/**
 * Represents an exam venue consisting of one or more rooms.
 * The Venue class extends the Room class and manages multiple rooms within the venue.
 */
public class Venue extends Room {

    protected int roomCount;
    protected RoomList rooms;
    protected int rows;
    protected int columns;
    protected int totalDesks;
    protected boolean aara;

    /**
     * Constructs a new Venue object, consisting of one or more Rooms.
     *
     * @param id        the identifier of the venue (e.g., "E101" or "L1+L2").
     * @param roomCount the number of rooms used in the venue; must be one of 1, 2, or 3.
     * @param rooms     the list of Room objects that make up this venue (at least one room).
     * @param rows      the number of rows of desks in the venue, counted front to back.
     * @param columns   the number of columns of desks in the venue, counted left to right.
     * @param totalDesks the total number of desks in the venue (may be less than rows * columns).
     * @param aara      whether the venue is used for AARA exam sessions.
     */
    public Venue(String id, int roomCount, RoomList rooms, int rows, int columns, int totalDesks, boolean aara) {
        super(id);
        this.roomCount = roomCount;
        this.rooms = rooms;
        this.rows = rows;
        this.columns = columns;
        this.totalDesks = totalDesks;
        this.aara = aara;
    }

    /**
     * Gets the identifier of the venue.
     *
     * @return the identifier of the venue.
     */
    public String venueId() {
        return id;
    }

    /**
     * Gets the list of rooms that make up this venue.
     *
     * @return the list of rooms that make up this venue.
     */
    public RoomList getRooms() {
        return rooms;
    }

    /**
     * Gets the number of rows of desks in this venue.
     *
     * @return the number of rows of desks in this venue.
     */
    public int getRows() {
        return rows;
    }

    /**
     * Gets the number of columns of desks in this venue.
     *
     * @return the number of columns of desks in this venue.
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Gets the total number of desks in the venue (may be less than rows * columns).
     *
     * @return the total number of desks in the venue.
     */
    public int deskCount() {
        return totalDesks;
    }

    /**
     * Checks if this venue is an AARA venue.
     *
     * @return true if this venue is an AARA venue.
     */
    public boolean isAara() {
        return aara;
    }

    /**
     * Checks if the venue type is AARA or not.
     *
     * @param aara the venue type to check against (true for AARA, false for non-AARA).
     * @return true if this venue is the same AARA type as the parameter.
     */
    public boolean checkVenueType(boolean aara) {
        if (this.aara == aara) {
            System.out.println(this.aara ? "This is an AARA venue." : "This is NOT an AARA venue.");
            return true;
        } else {
            System.out.println(this.aara ? "This is an AARA venue." : "This is NOT an AARA venue.");
            return false;
        }
    }

    /**
     * Checks if a certain number of students will fit in this venue.
     *
     * @param numberStudents the number of students to check.
     * @return true if the students will fit in the venue, false otherwise.
     */
    public boolean willFit(int numberStudents) {
        if (numberStudents <= totalDesks) {
            return true;
        } else {
            System.out.println("This venue only has " + totalDesks + " desks, " + numberStudents + " students will not fit in this venue!");
            return false;
        }
    }

    /**
     * Returns a string representation of this venue, which is just the venue's identifier.
     *
     * @return the venue identifier as a string representation.
     */
    @Override
    public String toString() {
        return "Venue ID: " + venueId();
    }
}

