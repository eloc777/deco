package examblock.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A collection object for holding and managing Venues.
 * The VenueList class provides methods to add, remove, and manage venues.
 */
public class VenueList {
    /**
     * list of venues
     */
    private List<Venue> venues;

    /**
     * Constructs an empty list of Venues.
     */
    public VenueList() {
        this.venues = new ArrayList<>();
    }

    /**
     * Adds a Venue to this list of Venues.
     *
     * @param venue the Venue object being added to this list.
     */
    public void addVenue(Venue venue) {
        venues.add(venue);
    }

    /**
     * Removes a given Venue from the VenueList.
     *
     * @param venue the venue to remove from this list.
     */
    public void removeVenue(Venue venue) {
        venues.remove(venue);
    }

    /**
     * Gets the first Venue with a matching id.
     *
     * @param id the identifier of the Venue to be found.
     * @return the first Venue with a matching id, if it exists.
     * @throws IllegalStateException if the venue with the given id is not found.
     */
    public Venue getVenue(String id) throws IllegalStateException {
        for (Venue venue : venues) {
            if (venue.venueId().equals(id)) {
                return venue;
            }
        }
        throw new IllegalStateException("Venue with ID "
                + id
                + " not found.");
    }

    /**
     * Creates a new List holding references to all the Venues managed by this VenueList and
     * returns it.
     *
     * @return a new List holding references to all the Venues managed by this VenueList.
     */
    public List<Venue> all() {
        return new ArrayList<>(venues);
    }

    /**
     * Allocates Students to Desks for every Session in every Venue.
     * This method will allocate students based on sessions and exams for the given cohort of
     * students.
     *
     * @param sessions the current set of exam sessions allocated to venues.
     * @param exams    the current set of Year 12 exams.
     * @param cohort   all the Year 12 students.
     */
    public void allocateStudents(SessionList sessions, ExamList exams, StudentList cohort) {
        for (int i = 0; i
                < sessions.all().size(); i++) {
            sessions.all().get(i).allocateStudents(exams, cohort);
        }

    }

    /**
     * Prints the allocations of Students to Desks for every Session in every Venue.
     *
     * @param sessions the current set of exam sessions allocated to venues.
     */
    public void printAllocations(SessionList sessions) {
        for (Session session : sessions.all()) {
            session.printDesks();
        }
    }

    /**
     * Returns detailed string representations of the contents of this venue list.
     *
     * @return detailed string representations of the contents of this venue list.
     */
    public String getFullDetail() {
        StringBuilder sb = new StringBuilder("Venue List Details:\n");
        for (Venue venue : venues) {
            sb.append(venue.toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns a brief string representation of the contents of this venue list.
     * This is usually a short list or count of the venues in the list.
     *
     * @return a brief string representation of the contents of this venue list.
     */
    @Override
    public String toString() {
        StringBuilder summary = new StringBuilder();
        for (int i = 0; i
                < venues.size(); i++) {
            summary.append(i
                    + 1
                    + ".");
            summary.append(venues.get(i).toString());
            summary.append("\n");
        }
        return summary.toString();
    }
}

