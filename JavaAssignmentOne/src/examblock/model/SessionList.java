package examblock.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * A collection object for holding and managing Exam Sessions.
 */
public class SessionList {
    /**
     * List of sessions
     */
    private List<Session> sessions;

    /**
     * Constructs a new empty SessionList.
     */
    public SessionList() {
        this.sessions = new ArrayList<>();
    }

    /**
     * Adds the given Session to this SessionList for it to manage.
     *
     * @param session The given Session for this SessionList to manage.
     * @throws IllegalArgumentException if the session is null.
     */
    public void add(Session session) {
        if (session
                == null) {
            throw new IllegalArgumentException("Session cannot be null.");
        }
        sessions.add(session);
    }

    /**
     * Removes the given Session from this SessionList.
     *
     * @param session The given Session to remove from this SessionList.
     * @throws IllegalArgumentException if the session is null or not found in the list.
     */
    public void remove(Session session) {
        if (session
                == null) {
            throw new IllegalArgumentException("Session cannot be null.");
        }
        if (!sessions.remove(session)) {
            throw new IllegalArgumentException("Session not found in the list.");
        }
    }

    /**
     * Creates a new list holding references to all the Sessions in this SessionList.
     *
     * @return A new list holding references to all the Sessions in this SessionList.
     */
    public List<Session> all() {
        return new ArrayList<>(sessions);
    }

    /**
     * Creates a new list holding references to those Sessions for a given Venue in this
     * SessionList.
     *
     * @param venue The exam venue for the list of sessions.
     * @return A new list holding references to all the sessions in this venue.
     */
    public List<Session> forVenue(Venue venue) {
        List<Session> venueSessions = new ArrayList<>();
        for (Session session : sessions) {
            if (session.getVenue().equals(venue)) {
                venueSessions.add(session);
            }
        }
        return venueSessions;
    }

    /**
     * Finds the sessionNumber of a session at a particular time in a given Venue.
     *
     * @param venue The venue object for the session we are looking for.
     * @param day   The session date.
     * @param start The start time of the exam session.
     * @return The sessionNumber of a session at a particular time in a given Venue, else zero if
     * no session exists
     * at that time.
     */
    public int getSessionNumber(Venue venue, LocalDate day, LocalTime start) {
        for (Session session : sessions) {
            if (session.getVenue().equals(venue)
                    && session.getDate().equals(day)
                    && session.getTime().equals(start)) {
                return session.getSessionNumber();
            }
        }
        return 0;
    }

    /**
     * Gets the Session with a matching Venue and sessionNumber.
     *
     * @param venue         The Venue for which the session is to be found.
     * @param sessionNumber The sessionNumber of the Session you are looking for.
     * @return The first Session with a matching Venue and sessionNumber, if it exists.
     * @throws IllegalStateException If no such session exists in this list.
     */
    public Session getSession(Venue venue, int sessionNumber) throws IllegalStateException {
        for (Session session : sessions) {
            if (session.getVenue().equals(venue)
                    && session.getSessionNumber()
                    == sessionNumber) {
                return session;
            }
        }
        throw new IllegalStateException();
    }

    /**
     * Gets the Session with a matching Venue and Exam scheduled.
     *
     * @param venue The Venue for which the session is to be found.
     * @param exam  The exam that has been allocated to this session in this venue.
     * @return The first Session with a matching Venue and Exam, if it exists.
     * @throws IllegalStateException If no such session exists in this list.
     */
    public Session getSession(Venue venue, Exam exam) throws IllegalStateException {
        for (Session session : sessions) {
            if (session.getVenue().equals(venue)) {
                for (Exam e : session.getExams()) {
                    if (exam.equals(e)) {
                        return session;
                    }
                }
            }
        }
        throw new IllegalStateException();
    }

    /**
     * Finds or creates a suitable session and calculates the total number of students in that
     * session after adding
     * `numberStudents`.
     *
     * @param venue          The exam venue for the session.
     * @param exam           The exam to be allocated to this session in this venue.
     * @param numberStudents The number of students to be allocated to this session.
     * @return The total number of students that will be in the session after adding
     * `numberStudents`.
     */
    public int getSessionNewTotal(Venue venue, Exam exam, int numberStudents) {
        // Find or create a suitable session
        Session targetSession = null;
        for (Session session : sessions) {
            if (session.getVenue().equals(venue)) {
                targetSession = session;
            }
        }

        // If no existing session is found, create one
        if (targetSession
                == null) {
            System.out.println("There is currently no exam session in that venue at that time.");
            System.out.println("Creating a new session...");
            int nextUniqueSessionNumber = getNextUniqueSessionNumber(venue);
            targetSession = new Session(venue, nextUniqueSessionNumber, LocalDate.now(),
                    LocalTime.now());
            add(targetSession);
        }

        // Calculate total number of students
        int existingStudentCount = targetSession.countStudents();
        int totalStudents = existingStudentCount
                + numberStudents;

        // Print details
        if (existingStudentCount
                > 0) {
            System.out.println("There are already "
                    + existingStudentCount
                    + " students who will "
                    + "be taking an exam"
                    + " in that venue.");
            System.out.println("Adding "
                    + numberStudents
                    + " students for this exam.");
        }
        System.out.println("That's a total of "
                + totalStudents
                + " students.");
        targetSession.scheduleExam(exam, numberStudents);

        return totalStudents;
    }

    /**
     * Allocates an exam to an existing or newly created session in a given Venue and prints
     * confirmation details.
     *
     * @param venue          The exam venue for the new/existing session.
     * @param exam           The exam to be allocated to this venue/session.
     * @param numberStudents The number of students being added with this allocation.
     */
    public void scheduleExam(Venue venue, Exam exam, int numberStudents) {
        Session targetSession = null;
        for (Session session : sessions) {
            if (session.getVenue().equals(venue)) {
                targetSession = session;
                break;
            }
        }

        // If no existing session is found, create one
        if (targetSession
                == null) {
            System.out.println("There is currently no exam session in that venue at that time.");
            System.out.println("Creating a new session...");
            int nextUniqueSessionNumber = getNextUniqueSessionNumber(venue);
            targetSession = new Session(venue, nextUniqueSessionNumber, LocalDate.now(),
                    LocalTime.now());
            add(targetSession);
        }

        System.out.println(exam.getSubject()
                + " exam added to "
                + venue.venueId()
                + ".");
    }

    /**
     * Helper method to determine the next unique session number for a given Venue.
     *
     * @param venue The Venue whose next unique Session Number is needed.
     * @return The next unique Session Number for that Venue.
     */
    private int getNextUniqueSessionNumber(Venue venue) {
        int maxCurrentNumber = 0;
        for (Session s : sessions) {
            if (s.getVenue().equals(venue)) {
                maxCurrentNumber = Math.max(maxCurrentNumber, s.getSessionNumber());
            }
        }
        return maxCurrentNumber
                + 1; // Next unique number
    }
}
