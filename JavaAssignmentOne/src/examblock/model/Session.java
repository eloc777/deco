package examblock.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a single Exam Session in a specific venue.
 * A session is a block of time in a venue where exams are conducted.
 * Sessions have unique numbers within each venue but not across venues.
 */
public class Session {
    protected final Venue venue;
    protected final int sessionNumber;
    protected final LocalDate day;
    protected final LocalTime startTime;
    protected ExamList exams;
    protected int studentCount;

    /**
     * Constructs a new empty Exam Session for a particular Venue.
     * The session has the same rows and columns of desks as the venue.
     *
     * @param venue         The exam venue for the new session.
     * @param sessionNumber The number (unique by venue) of the new session.
     * @param day           The date of the session.
     * @param start         The start time of the exam window.
     * @throws IllegalArgumentException if the venue is null.
     */
    public Session(Venue venue, int sessionNumber, LocalDate day, LocalTime start) {
        if (venue == null) {
            throw new IllegalArgumentException("Venue cannot be null.");
        }
        this.venue = venue;
        this.sessionNumber = sessionNumber;
        this.day = day;
        this.startTime = start;
        this.exams = new ExamList();
        this.studentCount = 0; // Initially no students are allocated
    }

    /**
     * Gets the venue of this session.
     *
     * @return The venue of this session.
     */
    public Venue getVenue() {
        return venue;
    }

    /**
     * Gets the session number of this session.
     *
     * @return The session number of this session.
     */
    public int getSessionNumber() {
        return sessionNumber;
    }

    /**
     * Gets the date of this session.
     *
     * @return The date of this session.
     */
    public LocalDate getDate() {
        return day;
    }

    /**
     * Gets the start time of this session.
     *
     * @return The start time of this session.
     */
    public LocalTime getTime() {
        return startTime;
    }

    /**
     * Gets the list of exams being held in this session.
     *
     * @return A list of exams being held in this session.
     */
    public ExamList getExams() {
        return exams;
    }

    /**
     * Counts the number of students already scheduled in this session.
     *
     * @return The number of students already scheduled in this session.
     */
    public int countStudents() {
        return studentCount;
    }

    /**
     * Allocates an exam to this session (venue and time).
     *
     * @param exam           The exam to be allocated to this venue and time slot.
     * @param numberStudents The number of students being added with this allocation.
     * @throws IllegalArgumentException if the exam is null or if numberStudents is negative.
     */
    public void scheduleExam(Exam exam, int numberStudents) {
        if (exam == null) {
            throw new IllegalArgumentException("Exam cannot be null.");
        }
        if (numberStudents < 0) {
            throw new IllegalArgumentException("Number of students cannot be negative.");
        }
        exams.add(exam);
        studentCount += numberStudents; // Update the student count
    }

    /**
     * Allocates students to desks for every exam in this session.
     *
     * @param exams  The current set of Year 12 Exams.
     * @param cohort All Year 12 students participating in exams.
     * @throws IllegalArgumentException if exams or cohort is null.
     */
    public void allocateStudents(ExamList exams, StudentList cohort) {
        if (exams == null || cohort == null) {
            throw new IllegalArgumentException("Exams and cohort cannot be null.");
        }
        // Implementation depends on ExamList and StudentList classes
        System.out.println("Allocating students to desks...");
    }

    /**
     * Prints the layout of desks in this session in the venue.
     * Prints a grid showing desk numbers and assigned student details for each desk.
     */
    public void printDesks() {
        System.out.println("Printing desk layout...");
        // Placeholder logic:
        for (int i = 0; i < studentCount; i++) {
            System.out.println("Desk " + (i + 1) + ": Student info here");
        }
    }

    /**
     * Returns a string representation of the state of this session, including its attributes and exams scheduled within it.
     *
     * @return A string representation of the state of this session.
     */
    @Override
    public String toString() {
        return "Session{" +
                "venue=" + venue +
                ", sessionNumber=" + sessionNumber +
                ", day=" + day +
                ", startTime=" + startTime +
                ", exams=" + exams +
                ", studentCount=" + studentCount +
                '}';
    }
}
